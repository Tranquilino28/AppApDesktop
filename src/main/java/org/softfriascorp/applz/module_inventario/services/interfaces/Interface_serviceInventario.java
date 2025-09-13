/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package org.softfriascorp.applz.module_inventario.services.interfaces;

import org.softfriascorp.applz.entity.productos.dto.Api_Producto;
import org.softfriascorp.applz.entity.productos.dto.Producto_dto;



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
