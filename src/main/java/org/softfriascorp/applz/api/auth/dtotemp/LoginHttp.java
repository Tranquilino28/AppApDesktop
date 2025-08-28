/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.softfriascorp.applz.api.auth.dtotemp;

import com.fasterxml.jackson.databind.ObjectMapper;
import static com.fasterxml.jackson.databind.type.LogicalType.Map;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONObject;
import org.softfriascorp.applz.model.UsuarioPerfil;
import org.softfriascorp.applz.util.DecodificarToken;

/**
 *
 * @author usuario
 */
public class LoginHttp {

    private static final String LOGIN_URL = "http://localhost:3066/api/auth/login";  //"https://appap.onrender.com/api/auth/login";
    private static final String GET_USER_ROLE_URL = "http://localhost:3066/api/admin/perfil";
    private static String token;

    private static boolean login(String username, String password) {
        try {
            URL url = new URL(LOGIN_URL);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();

            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setDoOutput(true);

            // Crear mapa de credenciales
            Map<String, String> credentials = new HashMap<>();
            credentials.put("username", username);
            credentials.put("password", password);

            // Convertir a JSON con Jackson
            ObjectMapper mapper = new ObjectMapper();
            String json = mapper.writeValueAsString(credentials);

            // Enviar JSON al servidor
            try (OutputStream os = conn.getOutputStream()) {
                os.write(json.getBytes(StandardCharsets.UTF_8));
            }

            int responseCode = conn.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                // Leer la respuesta
                StringBuilder response = new StringBuilder();
                try (BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream(), StandardCharsets.UTF_8))) {
                    String line;
                    while ((line = br.readLine()) != null) {
                        response.append(line.trim());
                    }
                }

                // Parsear JSON y obtener token
                Map<String, String> result = mapper.readValue(response.toString(), Map.class);
                token = result.get("token");

                System.out.println("Login exitoso. Token: " + token);

                usuarioLocal(token);

                return true;
            } else {
                System.out.println("Login fallido. Código: " + responseCode);
                return false;
            }

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    private static String getUsuarioRol() {
        try {
            URL url = new URL(GET_USER_ROLE_URL);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");

            // Cabecera con el token
            conn.setRequestProperty("Authorization", "Bearer " + token);
            conn.setRequestProperty("Accept", "application/json");

            if (conn.getResponseCode() == HttpURLConnection.HTTP_OK) {
                // Leer respuesta
                StringBuilder response = new StringBuilder();
                try (BufferedReader br = new BufferedReader(
                        new InputStreamReader(conn.getInputStream(), StandardCharsets.UTF_8))) {
                    String line;
                    while ((line = br.readLine()) != null) {
                        response.append(line.trim());
                    }
                }

                // Convertir JSON a objeto UsuarioPerfil
                ObjectMapper mapper = new ObjectMapper();
                UsuarioPerfil u = mapper.readValue(response.toString(), UsuarioPerfil.class);

                return u.toString();

            } else {
                System.out.println("Error: " + conn.getResponseCode());
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String getToken() {
        return token;
    }

    private static void usuarioLocal(String token) {
        // Dividir en 3 partes: header.payload.signature
        String[] parts = LoginHttp.getToken().split("\\.");

// Decodificar payload (segunda parte del token)
        String payloadJson = new String(Base64.getUrlDecoder().decode(parts[1]));

// Convertir a objeto JSON
        JSONObject payload1 = new JSONObject(payloadJson);

// Extraer datos y guardarlos en UsuarioPerfil
 /*       UsuarioPerfil.setNombreUsuario(payload1.getString("sub"));          // usuario
        UsuarioPerfil.setRol(payload1.getString("rol"));             // rol
        UsuarioPerfil.setEmpresaId(payload1.getLong("empresa_Id"));  // empresaId
        
        System.out.println(UsuarioPerfil.getNombreUsuario());
*/
    }
}
