/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package org.softfriascorp.applz.api.services.intefaces;

import org.softfriascorp.applz.api.Response_dtos.Api_Producto;
import org.softfriascorp.applz.api.services.Producto_dto;



/**
 *
 * @author usuario
 */
public interface Interface_serviceInventario {
    
    
    Producto_dto addOrUpdateProduct(Api_Producto p);
    
    Api_Producto buildProductToForm(
            String codigo
            , String nombre
            , String descripcion
            , String precio
            , String categoria 
            , String medida 
            , String stockDisponible
            , String cantidadIngreso
    );
    
    
}
