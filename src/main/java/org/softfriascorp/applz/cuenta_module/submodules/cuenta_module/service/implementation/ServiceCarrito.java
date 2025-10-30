/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.softfriascorp.applz.cuenta_module.submodules.cuenta_module.service.implementation;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import org.softfriascorp.applz.entity.detallesventa.DetallesVenta;
import org.softfriascorp.applz.entity.producto.ProductoDto;
import org.softfriascorp.applz.cuenta_module.submodules.cuenta_module.service.interfaces.CuentaService;
import org.softfriascorp.applz.entity.maestra.Maestra;

/**
 *
 * @author usuario
 */
public class ServiceCarrito implements CuentaService<String, DetallesVenta> {

    private BigDecimal valorRecibido = BigDecimal.ZERO;
    private Maestra tipoPago;
    
    private final Map<String, DetallesVenta> detallesMap = new LinkedHashMap<>();

    @Override
    public void agregarProducto(ProductoDto producto, int cantidad) {

        String codigo = producto.getCodigoBarras();
        BigDecimal porcentageDescuento = producto.getDescuentoAplicado();

        if (detallesMap.containsKey(codigo)) {
            DetallesVenta existente = detallesMap.get(codigo);

            int nuevaCantidad = existente.getCantidad() + cantidad;

            existente.setCantidad(nuevaCantidad);
            
            if (porcentageDescuento != null && porcentageDescuento.compareTo(BigDecimal.ZERO) > 0) {
                // Tiene descuento
                existente.setPrecioUnitario(producto.getPrecioFinal());
                
                
                
            } else {
                // No tiene descuento
                existente.setPrecioUnitario(producto.getPrecio());
            }
            
            existente.calcularSubTotal();

        } else {

            DetallesVenta item = new DetallesVenta();

            item.setCantidad(cantidad);if (porcentageDescuento != null && porcentageDescuento.compareTo(BigDecimal.ZERO) > 0) {
                // Tiene descuento
                item.setPrecioUnitario(producto.getPrecioFinal());
                
            } else {
                // No tiene descuento
                item.setPrecioUnitario(producto.getPrecio());
            }
            
            item.setProducto(producto);
            item.calcularSubTotal();

            detallesMap.put(codigo, item);
        }
    }

    @Override
    public void eliminarProducto(String codigoBarra) {
        detallesMap.remove(codigoBarra);
    }

    @Override
    public void actualizarCantidad(String codigoBarra, int nuevaCantidad) {
        if (detallesMap.containsKey(codigoBarra)) {
            DetallesVenta producto = detallesMap.get(codigoBarra);
            producto.setCantidad(nuevaCantidad);
            producto.calcularSubTotal();
        }
    }

    @Override
    public DetallesVenta buscarProducto(String codigoBarra) {
        return detallesMap.get(codigoBarra);
    }

    @Override
    public Map<String, DetallesVenta> listarProductos() {
        return detallesMap;
    }

    @Override
    public BigDecimal calcularTotal() {
        return detallesMap.values().stream()
                .map(DetallesVenta::getSubTotal)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    @Override
    public void limpiarVenta() {
        detallesMap.clear();
    }

    @Override
    public boolean tieneProductos() {
        return !detallesMap.isEmpty();
    }

    @Override
    public boolean productoExiste(String codigoBarra) {

        return detallesMap.containsKey(codigoBarra);
    }

    @Override
    public BigDecimal getCambio() {
            
        return calcularTotal().subtract(valorRecibido);
    }

    @Override
    public void setTipoDePago(Maestra tipoDePago) {
        this.tipoPago = tipoDePago;
    }

    @Override
    public Maestra getTipoDePago() {
        return tipoPago;
    }

    @Override
    public void setValorRecibido(BigDecimal valorRecibido) {
         this.valorRecibido = valorRecibido;
        
    }

    @Override
    public BigDecimal getValorRecibido() {
        return valorRecibido;
    }

    @Override
    public BigDecimal getSaldoPendiente() {
       return calcularTotal().subtract(valorRecibido);
    }

}
