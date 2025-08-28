/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package org.softfriascorp.applz.service.venta.interfaces;

import java.math.BigDecimal;
import java.util.Map;
import org.softfriascorp.applz.api.services.Producto_dto;
import org.softfriascorp.applz.modelProductosVenta.VentaProductos;

/**
 *
 * @author usuario
 */
public interface Interface_ServVenta {
    
    
    void agregarProducto(Producto_dto producto, int cantidad);

    void eliminarProducto(String codigoBarra);

    void actualizarCantidad(String codigoBarra, int nuevaCantidad);

    VentaProductos buscarProducto(String codigoBarra);

    Map<String, VentaProductos> listarProductos();

    BigDecimal calcularTotal();

    void limpiarVenta();

    boolean tieneProductos();
    
    boolean productoExiste(String codigoBarra);
}
