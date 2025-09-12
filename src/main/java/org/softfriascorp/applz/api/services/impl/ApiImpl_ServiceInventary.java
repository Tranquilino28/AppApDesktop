/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.softfriascorp.applz.api.services.impl;

import org.softfriascorp.applz.api.Response_dtos.Api_Producto;
import org.softfriascorp.applz.api.auth.AuthService;
import org.softfriascorp.applz.api.auth.dtotemp.Dto_PlayLoad;
import org.softfriascorp.applz.api.services.Producto_dto;
import org.softfriascorp.applz.api.services.intefaces.Interface_serviceInventario;
import org.softfriascorp.applz.util.UtilFormat;

import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClientResponseException;

/**
 *
 * @author usuario
 */

public class ApiImpl_ServiceInventary implements Interface_serviceInventario{

    @Override
    public Producto_dto addOrUpdateProduct(Api_Producto p) {
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
                .bodyValue(p)  // 🔹 aquí mandas la lista de productos como JSON
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

    @Override
    public Api_Producto buildProductToForm(
            String codigo
            , String nombre
            , String descripcion
            , String precio
            , String categoria
            , String medida
            , String stockDisponible
            , String cantidadIngreso) {
        
        
        Api_Producto apiProducto = new Api_Producto();
               
        apiProducto.setCodigoBarra(codigo);
        apiProducto.setNombre(nombre);
        apiProducto.setDescripcion(descripcion);
        
        Double valorUnitario = UtilFormat.stringToDouble(precio);  
        
        apiProducto.setPrecio(valorUnitario);
        apiProducto.setCategoria(categoria);
        apiProducto.setMedida(medida);
        
        int stockDisponible1 = UtilFormat.stringToInt(stockDisponible);
        int entrada1 =UtilFormat.stringToInt(cantidadIngreso);
        
        apiProducto.setStockDisponible(stockDisponible1+entrada1);        
        apiProducto.setEmpresa_id(Dto_PlayLoad.getEmpresa_Id());
        
        return apiProducto;
    }
    
}
