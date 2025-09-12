/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.softfriascorp.applz.api.services.impl;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.List;
import javax.swing.JOptionPane;
import org.softfriascorp.applz.api.Response_dtos.Api_Producto;
import org.softfriascorp.applz.api.auth.AuthService;
import org.softfriascorp.applz.api.services.Producto_dto;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClientResponseException;

/**
 *
 * @author usuario
 */
public class Api_ServiceProducto {

    private static final String PRODUCTO_API_BASE_URL = "http://localhost:3066/producto/search/1/";  //"https://appap.onrender.com/api/auth/login";

    private static final String URL_FIND_ALL_PRODUCTO = "http://localhost:3066/producto/all";

    private static final String URL_FIND_SEARCH_CODIGO_BARRAS_OR_NOMBRE_PRODUCTO = "http://localhost:3066/producto/search/2/";

    //private static String token;
   
    public static Producto_dto saveOrUbdate(Api_Producto producto) {
        String token = AuthService.getToken();

        if (token == null || token.isEmpty()) {
            System.out.println("Error: No hay un token de autenticación disponible. Por favor, inicie sesión primero.");
            return null;
        }
        try {
            return AuthService.getWebClient().put()
                    .uri("/producto/addtoupdate") // 🔹 tu endpoint para registrar ventas
                .header("Authorization", "Bearer " + token)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .bodyValue(producto)  // 🔹 aquí mandas la lista de productos como JSON
                .retrieve()
                .bodyToMono(Producto_dto.class) // 🔹 esperas un objeto de respuesta
                .block();  // 🔹 bloquea hasta recibir la respuesta (ideal para escritorio)

        } catch (WebClientResponseException.NotFound e) {
            System.out.println("❌ Producto no encontrado. Código 404");
            return null;
        } catch (WebClientResponseException.Unauthorized e) {
            System.out.println("🚫 Token inválido o expirado. Código 401");
            return null;
        } catch (WebClientResponseException e) {
            System.out.println("⚠️ Error HTTP: " + e.getRawStatusCode() + " - " + e.getResponseBodyAsString());
            return null;
        } catch (Exception e) {
            System.out.println("💥 Error inesperado al buscar el producto.");
            e.printStackTrace();
            return null;
        }

    }

    public static List<Producto_dto> getAllProducts() {
        String token = AuthService.getToken();

        if (token == null || token.isEmpty()) {
            System.out.println("Error: No hay un token de autenticación disponible. Por favor, inicie sesión primero.");
            return null;
        }
        try {
            return (List<Producto_dto>) AuthService.getWebClient().get()
                    .uri("/producto/all") // se concatena el código al endpoint
                    .header("Authorization", "Bearer " + token)
                    .accept(MediaType.APPLICATION_JSON)
                    .retrieve()
                    .bodyToFlux(Producto_dto.class) // <<-- espera un array
                    .collectList()
                    .block(); // 🔹 bloquea hasta recibir la respuesta (ideal para escritorio)

        } catch (WebClientResponseException.NotFound e) {
            System.out.println("❌ Producto no encontrado. Código 404");
            return null;
        } catch (WebClientResponseException.Unauthorized e) {
            System.out.println("🚫 Token inválido o expirado. Código 401");
            return null;
        } catch (WebClientResponseException e) {
            System.out.println("⚠️ Error HTTP: " + e.getRawStatusCode() + " - " + e.getResponseBodyAsString());
            return null;
        } catch (Exception e) {
            System.out.println("💥 Error inesperado al buscar el producto.");
            e.printStackTrace();
            return null;
        }

    }

    public static Producto_dto searchProductCode(String code) {
        String token = AuthService.getToken();

        if (token == null || token.isEmpty()) {
            System.out.println("⚠️ Error: No hay un token de autenticación disponible. Por favor, inicie sesión primero.");
            return null;
        }
        try {
            return (Producto_dto) AuthService.getWebClient().get()
                    .uri("/producto/search/1/" + code) // se concatena el código al endpoint
                    .header("Authorization", "Bearer " + token)
                    .accept(MediaType.APPLICATION_JSON)
                    .retrieve()
                    .bodyToMono(Producto_dto.class)
                    .block(); // 🔹 bloquea hasta recibir la respuesta (ideal para escritorio)

        } catch (WebClientResponseException.BadRequest e) {
            // ✅ Manejo del error 400 (stock insuficiente)
            System.out.println("⚠️ Error de Stock: " + e.getResponseBodyAsString());
            JOptionPane.showMessageDialog(null, "Error de Stock: " + e.getResponseBodyAsString(), "Venta Fallida", JOptionPane.WARNING_MESSAGE);
            return null;
        } catch (WebClientResponseException.NotFound e) {
            System.out.println("❌ Producto no encontrado. Código 404");
            return null;
        } catch (WebClientResponseException.Unauthorized e) {
            System.out.println("🚫 Token inválido o expirado. Código 401");
            return null;
        } catch (WebClientResponseException e) {
            System.out.println("⚠️ Error HTTP: " + e.getRawStatusCode() + " - " + e.getResponseBodyAsString());
            return null;
        } catch (Exception e) {
            System.out.println("💥 Error inesperado al buscar el producto.");
            e.printStackTrace();
            return null;
        }

    }

    public static Producto_dto searchProductStokZero(String code) {
        String token = AuthService.getToken();

        if (token == null || token.isEmpty()) {
            System.out.println("⚠️ Error: No hay un token de autenticación disponible. Por favor, inicie sesión primero.");
            return null;
        }
        try {
            return (Producto_dto) AuthService.getWebClient().get()
                    .uri("/producto/search/1z/" + code) // se concatena el código al endpoint
                    .header("Authorization", "Bearer " + token)
                    .accept(MediaType.APPLICATION_JSON)
                    .retrieve()
                    .bodyToMono(Producto_dto.class)
                    .block(); // 🔹 bloquea hasta recibir la respuesta (ideal para escritorio)

        } catch (WebClientResponseException.BadRequest e) {
            // ✅ Manejo del error 400 (stock insuficiente)
            System.out.println("⚠️ Error de Stock: " + e.getResponseBodyAsString());
            JOptionPane.showMessageDialog(null, "Error de Stock: " + e.getResponseBodyAsString(), "Venta Fallida", JOptionPane.WARNING_MESSAGE);
            return null;
        } catch (WebClientResponseException.NotFound e) {
            System.out.println("❌ Producto no encontrado. Código 404");
            return null;
        } catch (WebClientResponseException.Unauthorized e) {
            System.out.println("🚫 Token inválido o expirado. Código 401");
            return null;
        } catch (WebClientResponseException e) {
            System.out.println("⚠️ Error HTTP: " + e.getRawStatusCode() + " - " + e.getResponseBodyAsString());
            return null;
        } catch (Exception e) {
            System.out.println("💥 Error inesperado al buscar el producto.");
            e.printStackTrace();
            return null;
        }

    }

    public static List<Producto_dto> searchCoincidencias(String text) {
        String token = AuthService.getToken();

        if (token == null || token.isEmpty()) {
            System.out.println("⚠️ Error: No hay un token de autenticación disponible. Por favor, inicie sesión primero.");
            return null;
        }

        try {
            return (List<Producto_dto>) AuthService.getWebClient().get()
                    .uri("/producto/search/2/" + text) // se concatena el código al endpoint
                    .header("Authorization", "Bearer " + token)
                    .accept(MediaType.APPLICATION_JSON)
                    .retrieve()
                    .bodyToFlux(Producto_dto.class) // <<-- espera un array
                    .collectList()
                    .block(); // 🔹 bloquea hasta recibir la respuesta (ideal para escritorio)

        } catch (WebClientResponseException.BadRequest e) {
            // ✅ Manejo del error 400 (stock insuficiente)
            System.out.println("⚠️ Error de Stock: " + e.getResponseBodyAsString());
            JOptionPane.showMessageDialog(null, "Error de Stock: " + e.getResponseBodyAsString(), "Venta Fallida", JOptionPane.WARNING_MESSAGE);
            return null;
        } catch (WebClientResponseException.NotFound e) {
            System.out.println("❌ Producto no encontrado. Código 404");
            return null;
        } catch (WebClientResponseException.Unauthorized e) {
            System.out.println("🚫 Token inválido o expirado. Código 401");
            return null;
        } catch (WebClientResponseException e) {
            System.out.println("⚠️ Error HTTP: " + e.getRawStatusCode() + " - " + e.getResponseBodyAsString());
            return null;
        } catch (Exception e) {
            System.out.println("💥 Error inesperado al buscar el producto.");
            e.printStackTrace();

            return null;
        }
    }

    /**
     * Busca un producto por su código de barras haciendo una petición GET a la
     * API.
     *
     * @param codigo El código de barras del producto.
     * @return El objeto Producto si se encuentra, o null si no se encuentra o
     * hay un error.
     */
    private static Producto_dto buscarProductoPorCodigo(String codigo) {
        String token = AuthService.getToken();

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

                System.out.println("LA RESPUESTA FUE : " + response.toString());

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

    private static List<Producto_dto> buscarCoincidenciasCodigoNombre(String texto_a_buscar) {
        String token = AuthService.getToken();

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

                System.out.println("LA RESPUESTA FUE : " + response.toString());

                // Parsea el JSON de la respuesta a un objeto Producto
                ObjectMapper mapper = new ObjectMapper();

                return mapper.readValue(response.toString(), new TypeReference<List<Producto_dto>>() {
                });

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

    private static List<Producto_dto> listarProductos() {
        String token = AuthService.getToken();

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

                System.out.println("LA RESPUESTA FUE : " + response.toString());

                // Parsea el JSON de la respuesta a un objeto Producto
                ObjectMapper mapper = new ObjectMapper();

                return mapper.readValue(response.toString(), new TypeReference<List<Producto_dto>>() {
                });

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
