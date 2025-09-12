/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.softfriascorp.applz.service.venta.service;


import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import org.softfriascorp.applz.api.services.Producto_dto;
import org.softfriascorp.applz.modelProductosVenta.Productos_Carrito;
import org.softfriascorp.applz.service.venta.interfaces.Interface_Carrito;
import org.softfriascorp.applz.util.UtilFormat;

/**
 *
 * @author usuario
 */

public class ServiceCarrito  implements Interface_Carrito <String, Producto_dto >{

    private final Map<String, Productos_Carrito> mapaProductos = new HashMap<>();

    @Override
    public void agregarProducto(Producto_dto producto, int cantidad) {
        String codigo = producto.getCodigoBarra();

        if (mapaProductos.containsKey(codigo)) {
            Productos_Carrito existente = mapaProductos.get(codigo);
            int nuevaCantidad = existente.getCantidad() + cantidad;
            existente.setCantidad(nuevaCantidad);
            existente.setPrecioTotal(existente.getPrecioUnitario()
                    .multiply(BigDecimal.valueOf(nuevaCantidad)));
        } else {
           

            Productos_Carrito item = new Productos_Carrito(
                    producto.getCodigoBarra(),
                    producto.getDescripcion(),
                    cantidad,
                    producto.getMedida(),
                    producto.getPrecio(),
                    producto.getPrecio().multiply(UtilFormat.integerToBIgDecimal(producto.getStockDisponible()))
            );

            mapaProductos.put(codigo, item);
        }
    }

    @Override
    public void eliminarProducto(String codigoBarra) {
        mapaProductos.remove(codigoBarra);
    }

    @Override
    public void actualizarCantidad(String codigoBarra, int nuevaCantidad) {
        if (mapaProductos.containsKey(codigoBarra)) {
            Productos_Carrito producto = mapaProductos.get(codigoBarra);
            producto.setCantidad(nuevaCantidad);
            producto.setPrecioTotal(producto.getPrecioUnitario()
                    .multiply(BigDecimal.valueOf(nuevaCantidad)));
        }
    }

    @Override
    public Productos_Carrito buscarProducto(String codigoBarra) {
        return mapaProductos.get(codigoBarra);
    }

    @Override
    public Map<String, Productos_Carrito> listarProductos() {
        return mapaProductos;
    }

    @Override
    public BigDecimal calcularTotal() {
        return mapaProductos.values().stream()
                .map(Productos_Carrito::getPrecioTotal)
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