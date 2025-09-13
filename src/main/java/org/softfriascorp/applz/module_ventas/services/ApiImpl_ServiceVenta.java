/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.softfriascorp.applz.module_ventas.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.ArrayList;
import java.util.List;
import org.softfriascorp.applz.entity.ventas.dto.VentaResponse;
import org.softfriascorp.applz.module_login.auth.AuthService;
import org.softfriascorp.applz.entity.productos.dto.ProductoRequest;
import org.softfriascorp.applz.module_ventas.dtos.VentaRequest;
import org.softfriascorp.applz.local.model.UsuarioPerfil;
import org.softfriascorp.applz.local.modelProductosVenta.Productos_Carrito;
import org.softfriascorp.applz.local.service.venta.service.ServiceCarrito;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClientResponseException;

/**
 *
 * @author usuario
 */
public class ApiImpl_ServiceVenta {

    /**
     * Guarda la venta en la base de datos
     *
     * @param cuenta
     * @return
     */
    public static VentaResponse saveVenta(ServiceCarrito cuenta) {
        String token = AuthService.getToken();

        if (token == null || token.isEmpty()) {
            System.out.println("⚠️ Error: No hay un token de autenticación disponible. Por favor, inicie sesión primero.");
            return null;
        }

        List<ProductoRequest> productos = new ArrayList<>();

        for (Productos_Carrito sv : cuenta.listarProductos().values()) {
            System.out.println("se muestra el producto " + sv.getDescripcion());

            productos.add(new ProductoRequest(sv.getCodigoBarras(), sv.getCantidad()));

        }
        // Construir el request
        VentaRequest request = new VentaRequest(
                1L,              // ⚡ Id del cliente (ajusta según tu clase)
                 1L, // ⚡ Id del método de pago
                UsuarioPerfil.getEmpresa_Id(),// ⚡ Id de la empresa
                productos 
        );
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            String jsonRequest = objectMapper.writeValueAsString(request);
            System.out.println("JSON que se enviará: " + jsonRequest);
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            return AuthService.getWebClient().post()
                    .uri("/venta/save") // 🔹 tu endpoint para registrar ventas
                    .header("Authorization", "Bearer " + token)
                    .contentType(MediaType.APPLICATION_JSON)
                    .accept(MediaType.APPLICATION_JSON)
                    .bodyValue(request) // 🔹 aquí mandas la lista de productos como JSON
                    .retrieve()
                    .bodyToMono(VentaResponse.class) // 🔹 esperas un objeto de respuesta
                    .block();

        } catch (WebClientResponseException.NotFound e) {
            System.out.println("❌ err");
            return null;
        } catch (WebClientResponseException.Unauthorized e) {
            System.out.println("🚫 Token inválido o expirado. Código 401");
            return null;
        } catch (WebClientResponseException e) {
            System.out.println("⚠️ Error HTTP: " + e.getRawStatusCode() + " - " + e.getResponseBodyAsString());
            return null;
        } catch (Exception e) {
            System.out.println("💥 Error inesperado al guardar venta.");
            e.printStackTrace();

            return null;
        }
    } 
    
    public static List<VentaResponse> getAllVenta() {
        String token = AuthService.getToken();

        if (token == null || token.isEmpty()) {
            System.out.println("⚠️ Error: No hay un token de autenticación disponible. Por favor, inicie sesión primero.");
            return null;
        }

        

        try {
            return AuthService.getWebClient().get()
                    .uri("/venta/getAll") // 🔹 tu endpoint para registrar ventas
                    .header("Authorization", "Bearer " + token)                   
                    .accept(MediaType.APPLICATION_JSON)
                    // 🔹 aquí mandas la lista de productos como JSON
                    .retrieve()
                    .bodyToFlux(VentaResponse.class) // 🔹 esperas un objeto de respuesta
                    .collectList()
                    .block();

        } catch (WebClientResponseException.NotFound e) {
            System.out.println("❌ err");
            return null;
        } catch (WebClientResponseException.Unauthorized e) {
            System.out.println("🚫 Token inválido o expirado. Código 401");
            return null;
        } catch (WebClientResponseException e) {
            System.out.println("⚠️ Error HTTP: " + e.getRawStatusCode() + " - " + e.getResponseBodyAsString());
            return null;
        } catch (Exception e) {
            System.out.println("💥 Error inesperado al guardar venta.");
            e.printStackTrace();

            return null;
        }
    }

}
