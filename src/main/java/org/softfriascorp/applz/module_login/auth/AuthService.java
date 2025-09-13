/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.softfriascorp.applz.module_login.auth;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;
import org.softfriascorp.applz.module_login.dtos.DtoUserAuth;
import org.softfriascorp.applz.local.model.UsuarioPerfil;
import org.springframework.web.reactive.function.client.WebClient;

/**
 *
 * @author usuario
 */
public class AuthService {

    private static final String LOGIN_URL = "http://localhost:3066/auth/login";
    private static String token;

    private static final WebClient webClient = WebClient.builder()
            .baseUrl("http://localhost:3066") // puedes dejar solo el host/base
            .defaultHeader("Content-Type", "application/json")
            .build();

    public static boolean login(String username, String password) {
        try {
            // Crear el cuerpo del request
            Map<String, String> credentials = new HashMap<>();
            credentials.put("username", username);
            credentials.put("password", password);

            // Llamada al backend con POST
            Map<String, Object> result = webClient.post()
                    .uri("/api/auth/login") // endpoint
                    .bodyValue(credentials)
                    .retrieve()
                    .bodyToMono(Map.class) // aquí asume que tu backend devuelve {"token": "xxxxx"}
                    .block(); // ⚠️ bloquea, porque estás en app desktop (no reactivo)

            if (result != null && result.containsKey("token")) {
                token = result.get("token").toString();
                System.out.println("✅ Login exitoso. Token: " + token);

                usuarioLocal(token); // aquí puedes guardar el usuario en sesión local

                return true;
            } else {
                System.out.println("❌ Login fallido. No se recibió token");
                return false;
            }

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    private static void usuarioLocal(String token) {

        try {
            // Dividir en 3 partes: header.payload.signature
            String[] parts = token.split("\\.");

            // Decodificar payload
            String payloadJson = new String(Base64.getUrlDecoder().decode(parts[1]));

            // Mapear JSON a DTO
            ObjectMapper mapper = new ObjectMapper();
           
            DtoUserAuth usuario = mapper.readValue(payloadJson, DtoUserAuth.class);


            // Asignar a UsuarioPerfil
            UsuarioPerfil.setSub(usuario.getSub());
            UsuarioPerfil.setRol(usuario.getRol());
            UsuarioPerfil.setEmpresa_Id(usuario.getEmpresa_Id());

            //System.out.println("Usuario logueado: " + UsuarioPerfil.getSub());
            
// Aquí tu lógica para guardar token en sesión local o decodificar JWT
        System.out.println("Usuario autenticado");
        
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Error procesando JWT", e);
        }

        
    }

    public static String getToken() {
        return token;
    }
    
    public static WebClient getWebClient(){
        return webClient;
    }

}
