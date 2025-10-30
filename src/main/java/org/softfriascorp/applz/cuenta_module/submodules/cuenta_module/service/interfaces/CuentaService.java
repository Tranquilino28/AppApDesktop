/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package org.softfriascorp.applz.cuenta_module.submodules.cuenta_module.service.interfaces;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.Map;
import org.softfriascorp.applz.entity.detallesventa.DetallesVenta;
import org.softfriascorp.applz.entity.producto.ProductoDto;
import org.softfriascorp.applz.cuenta_module.entity.Cuenta;
import org.softfriascorp.applz.entity.maestra.Maestra;

/**
 *
 * @author usuario
 */
public interface CuentaService <K , T>{
    
    
    void agregarProducto(ProductoDto producto, int cantidad);

    void eliminarProducto(K k);

    void actualizarCantidad(K k, int nuevaCantidad);

    T buscarProducto(K k);

    Map<K , T> listarProductos();

    BigDecimal calcularTotal();

    void limpiarVenta();

    boolean tieneProductos();
    
    boolean productoExiste(K k);
    
    
    void setValorRecibido(BigDecimal valorRecibido);
     BigDecimal getValorRecibido();
    
    BigDecimal getCambio();
    
    BigDecimal getSaldoPendiente();
    
    void setTipoDePago(Maestra tipoDePago);   
    Maestra getTipoDePago();
    
}
