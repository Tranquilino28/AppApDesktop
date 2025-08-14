/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.softfriascorp.applz.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import static com.fasterxml.jackson.databind.type.LogicalType.Map;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author usuario
 */
public class LoginHttp {

    private static final String LOGIN_URL = "http://localhost:3066/api/auth/login" ;  //"https://appap.onrender.com/api/auth/login";
    private static String token;

    public static boolean login(String username, String password) {
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

    public static String getToken() {
        return token;
    }
    
  
}
