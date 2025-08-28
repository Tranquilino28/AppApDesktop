/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.softfriascorp.applz.service.venta.service;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import org.softfriascorp.applz.api.services.Producto_dto;
import org.softfriascorp.applz.modelProductosVenta.VentaProductos;
import org.softfriascorp.applz.service.venta.interfaces.Interface_ServVenta;

/**
 *
 * @author usuario
 */
public class ServiceVenta  implements Interface_ServVenta {

    private final Map<String, VentaProductos> mapaProductos = new HashMap<>();

    @Override
    public void agregarProducto(Producto_dto producto, int cantidad) {
        String codigo = producto.getCodigoBarra();

        if (mapaProductos.containsKey(codigo)) {
            VentaProductos existente = mapaProductos.get(codigo);
            int nuevaCantidad = existente.getCantidad() + cantidad;
            existente.setCantidad(nuevaCantidad);
            existente.setPrecioTotal(existente.getPrecioUnitario()
                    .multiply(BigDecimal.valueOf(nuevaCantidad)));
        } else {
            BigDecimal precioUnitario = BigDecimal.valueOf(producto.getPrecio());
            BigDecimal precioTotal = precioUnitario.multiply(BigDecimal.valueOf(cantidad));

            VentaProductos ventaProducto = new VentaProductos(
                    producto.getCodigoBarra(),
                    producto.getDescripcion(),
                    cantidad,
                    "UND",
                    precioUnitario,
                    precioTotal
            );

            mapaProductos.put(codigo, ventaProducto);
        }
    }

    @Override
    public void eliminarProducto(String codigoBarra) {
        mapaProductos.remove(codigoBarra);
    }

    @Override
    public void actualizarCantidad(String codigoBarra, int nuevaCantidad) {
        if (mapaProductos.containsKey(codigoBarra)) {
            VentaProductos producto = mapaProductos.get(codigoBarra);
            producto.setCantidad(nuevaCantidad);
            producto.setPrecioTotal(producto.getPrecioUnitario()
                    .multiply(BigDecimal.valueOf(nuevaCantidad)));
        }
    }

    @Override
    public VentaProductos buscarProducto(String codigoBarra) {
        return mapaProductos.get(codigoBarra);
    }

    @Override
    public Map<String, VentaProductos> listarProductos() {
        return mapaProductos;
    }

    @Override
    public BigDecimal calcularTotal() {
        return mapaProductos.values().stream()
                .map(VentaProductos::getPrecioTotal)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }
    

    @Override
    public void limpiarVenta() {
        mapaProductos.clear();
    }

    @Override
    public boolean tieneProductos() {
        return !mapaProductos.isEmpty();
    }
    
    @Override
    public boolean productoExiste(String codigoBarra){
        
        return mapaProductos.containsKey(codigoBarra);
    }
}
