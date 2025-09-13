/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package org.softfriascorp.applz.local.service.venta.interfaces;

import java.math.BigDecimal;
import java.util.Map;
import org.softfriascorp.applz.entity.productos.dto.Producto_dto;
import org.softfriascorp.applz.local.modelProductosVenta.Productos_Carrito;

/**
 *
 * @author usuario
 */
public interface Interface_Carrito <K , T>{
    
    
    void agregarProducto(Producto_dto producto, int cantidad);

    void eliminarProducto(String codigoBarra);

    void actualizarCantidad(String codigoBarra, int nuevaCantidad);

    Productos_Carrito buscarProducto(String codigoBarra);

    Map<String, Productos_Carrito> listarProductos();

    BigDecimal calcularTotal();

    void limpiarVenta();

    boolean tieneProductos();
    
    boolean productoExiste(String codigoBarra);
}
