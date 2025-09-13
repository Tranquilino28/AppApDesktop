/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.softfriascorp.applz.entity.tiposmaster.services.impl;

import java.util.List;
import javax.swing.JOptionPane;
import org.softfriascorp.applz.entity.tiposmaster.dto.TiposResponse;
import org.softfriascorp.applz.module_login.auth.AuthService;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClientResponseException;



/**
 *
 * @author usuario
 */
public class Impl_ServiceTipos{

   /**
    * Guarda la venta en la base de datos 
    * @param tipo
    * @return 
    */
   public static List<TiposResponse> searchTipos(String tipo) {
        String token = AuthService.getToken();

        if (token == null || token.isEmpty()) {
            System.out.println("⚠️ Error: No hay un token de autenticación disponible. Por favor, inicie sesión primero.");
            return null;
        }
        try {
            return (List<TiposResponse>) AuthService.getWebClient().get()
                    .uri("/master/tipo/" + tipo) // se concatena el código al endpoint
                    .header("Authorization", "Bearer " + token)
                    .accept(MediaType.APPLICATION_JSON)
                    .retrieve()
                    .bodyToFlux(TiposResponse.class) // <<-- espera un array
                    .collectList()
                    .block(); // 🔹 bloquea hasta recibir la respuesta (ideal para escritorio)

        }catch (WebClientResponseException.BadRequest e) {
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
    
}