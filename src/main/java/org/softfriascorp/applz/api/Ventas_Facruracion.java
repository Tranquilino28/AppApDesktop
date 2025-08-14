/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.softfriascorp.applz.api;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import static com.fasterxml.jackson.databind.type.LogicalType.Map;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.softfriascorp.applz.api.enumapi.ProductoApiUrl;
import org.softfriascorp.applz.api.modeldto.Producto_dto;

/**
 *
 * @author usuario
 */
public class Ventas_Facruracion {

    private static final String PRODUCTO_API_BASE_URL = "http://localhost:3066/producto/search/1/" ;  //"https://appap.onrender.com/api/auth/login";
    
    private static final String URL_FIND_ALL_PRODUCTO = "http://localhost:3066/producto/all";
    
    private static final String URL_FIND_SEARCH_CODIGO_BARRAS_OR_NOMBRE_PRODUCTO = "http://localhost:3066/producto/search/2/";
    
    private static String token;

    /**
     * Busca un producto por su código de barras haciendo una petición GET a la API.
     * @param codigo El código de barras del producto.
     * @return El objeto Producto si se encuentra, o null si no se encuentra o hay un error.
     */
    public static Producto_dto buscarProductoPorCodigo(String codigo) {
        String token = LoginHttp.getToken();
        
        if (token == null || token.isEmpty()) {
            System.out.println("Error: No hay un token de autenticación disponible. Por favor, inicie sesión primero.");
            return null;
        }

        try {
            // Construye la URL completa con el código
            URL url = new URL(PRODUCTO_API_BASE_URL + codigo);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();

            // Configura la conexión para una petición GET
            conn.setRequestMethod("GET");
            
            // Envía el token de autenticación en la cabecera "Authorization"
            conn.setRequestProperty("Authorization", "Bearer " + token);
            conn.setRequestProperty("Accept", "application/json");

            int responseCode = conn.getResponseCode();

                if (responseCode == HttpURLConnection.HTTP_OK) {
                    // Lee la respuesta del servidor
                    StringBuilder response = new StringBuilder();
                    try (BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream(), StandardCharsets.UTF_8))) {
                        String line;
                        while ((line = br.readLine()) != null) {
                            response.append(line.trim());
                        }
                    }

                    System.out.println("LA RESPUESTA FUE : "+response.toString());

                    // Parsea el JSON de la respuesta a un objeto Producto
                    ObjectMapper mapper = new ObjectMapper();
                    return mapper.readValue(response.toString(), Producto_dto.class);

                } else if (responseCode == HttpURLConnection.HTTP_NOT_FOUND) {
                    System.out.println("Producto no encontrado. Código de respuesta: " + responseCode);
                    return null;
                    } else {
                        System.out.println("Error al buscar el producto. Código de respuesta: " + responseCode);
                        return null;
                    }

        } catch (Exception e) {
            System.out.println("Ocurrió una excepción al buscar el producto.");
            e.printStackTrace();
            return null;
        }
    }
    
    
    
public static List<Producto_dto> buscarCoincidenciasCodigoNombre(String texto_a_buscar) {
       String token = LoginHttp.getToken();
        
        if (token == null || token.isEmpty()) {
            System.out.println("Error: No hay un token de autenticación disponible. Por favor, inicie sesión primero.");
            return null;
        }

        try {
            // Construye la URL completa con el código
            URL url = new URL(URL_FIND_SEARCH_CODIGO_BARRAS_OR_NOMBRE_PRODUCTO + texto_a_buscar);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();

            // Configura la conexión para una petición GET
            conn.setRequestMethod("GET");
            
            // Envía el token de autenticación en la cabecera "Authorization"
            conn.setRequestProperty("Authorization", "Bearer " + token);
            conn.setRequestProperty("Accept", "application/json");

            int responseCode = conn.getResponseCode();

                if (responseCode == HttpURLConnection.HTTP_OK) {
                    // Lee la respuesta del servidor
                    StringBuilder response = new StringBuilder();
                    try (BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream(), StandardCharsets.UTF_8))) {
                        String line;
                        while ((line = br.readLine()) != null) {
                            response.append(line.trim());
                        }
                    }

                    System.out.println("LA RESPUESTA FUE : "+response.toString());

                    
                    // Parsea el JSON de la respuesta a un objeto Producto
                    ObjectMapper mapper = new ObjectMapper();
                    

                    return mapper.readValue(response.toString(), new TypeReference<List<Producto_dto>>() {});

                } else if (responseCode == HttpURLConnection.HTTP_NOT_FOUND) {
                    System.out.println("Producto no encontrado. Código de respuesta: " + responseCode);
                    return null;
                    } else {
                        System.out.println("Error al buscar el producto. Código de respuesta: " + responseCode);
                        return null;
                    }

        } catch (Exception e) {
            System.out.println("Ocurrió una excepción al buscar el producto.");
            e.printStackTrace();
            return null;
        }
    
             
    }

    public static List<Producto_dto> listarProductos(){
     String token = LoginHttp.getToken();
        
        if (token == null || token.isEmpty()) {
            System.out.println("Error: No hay un token de autenticación disponible. Por favor, inicie sesión primero.");
            return null;
        }

        try {
            // Construye la URL completa con el código
            URL url = new URL(URL_FIND_ALL_PRODUCTO);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();

            // Configura la conexión para una petición GET
            conn.setRequestMethod("GET");
            
            // Envía el token de autenticación en la cabecera "Authorization"
            conn.setRequestProperty("Authorization", "Bearer " + token);
            conn.setRequestProperty("Accept", "application/json");

            int responseCode = conn.getResponseCode();

                if (responseCode == HttpURLConnection.HTTP_OK) {
                    // Lee la respuesta del servidor
                    StringBuilder response = new StringBuilder();
                    try (BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream(), StandardCharsets.UTF_8))) {
                        String line;
                        while ((line = br.readLine()) != null) {
                            response.append(line.trim());
                        }
                    }

                    System.out.println("LA RESPUESTA FUE : "+response.toString());

                    
                    // Parsea el JSON de la respuesta a un objeto Producto
                    ObjectMapper mapper = new ObjectMapper();
                    

                    return mapper.readValue(response.toString(), new TypeReference<List<Producto_dto>>() {});

                } else if (responseCode == HttpURLConnection.HTTP_NOT_FOUND) {
                    System.out.println("Producto no encontrado. Código de respuesta: " + responseCode);
                    return null;
                    } else {
                        System.out.println("Error al buscar el producto. Código de respuesta: " + responseCode);
                        return null;
                    }

        } catch (Exception e) {
            System.out.println("Ocurrió una excepción al buscar el producto.");
            e.printStackTrace();
            return null;
        }
    
           
        
}
    
  
}
