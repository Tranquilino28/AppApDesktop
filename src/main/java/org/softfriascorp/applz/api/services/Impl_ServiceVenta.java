/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.softfriascorp.applz.api.services;


import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import org.softfriascorp.applz.api.Response_dtos.VentaResponse;
import org.softfriascorp.applz.api.auth.AuthService;
import org.softfriascorp.applz.api.auth.dtotemp.Dto_PlayLoad;
import org.softfriascorp.applz.api.request.ProductoRequest;
import org.softfriascorp.applz.api.request.VentaRequest;
import org.softfriascorp.applz.api.request.UsuariosRequest;
import org.softfriascorp.applz.api.services.Producto_dto;
import org.softfriascorp.applz.model.UsuarioPerfil;
import org.softfriascorp.applz.modelProductosVenta.VentaProductos;
import org.softfriascorp.applz.service.venta.service.ServiceVenta;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;

/**
 *
 * @author usuario
 */
public class Impl_ServiceVenta{

   /**
    * Guarda la venta en la base de datos 
    * @param cuenta
    * @return 
    */
    public static VentaResponse saveVenta(ServiceVenta cuenta) {
        String token = AuthService.getToken();

        if (token == null || token.isEmpty()) {
            System.out.println("⚠️ Error: No hay un token de autenticación disponible. Por favor, inicie sesión primero.");
            return null;
        }
        
        List<ProductoRequest> productos = new ArrayList<>();
        
        for(VentaProductos sv : cuenta.listarProductos().values()){
            System.out.println("se muestra el producto "+ sv.getDescripcion());
            
            productos.add(new ProductoRequest(sv.getCodigoBarras(), sv.getCantidad()));
         
        }
         // Construir el request
    VentaRequest request = new VentaRequest(
        1L,              // ⚡ Id del cliente (ajusta según tu clase)
        1L,           // ⚡ Id del método de pago
        
        UsuarioPerfil.getEmpresa_Id(), 
            
            productos     // ⚡ Id de la empresa
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
                .bodyValue(request)  // 🔹 aquí mandas la lista de productos como JSON
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
    
}