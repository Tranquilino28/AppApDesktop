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
public interface Api_InterfaceServProducto {
    
    
    
    Producto_dto saveOrUbdate(Api_Producto producto);
    
}
