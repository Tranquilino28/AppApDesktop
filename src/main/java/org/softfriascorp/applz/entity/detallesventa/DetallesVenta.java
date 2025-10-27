/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.softfriascorp.applz.entity.detallesventa;

import java.math.BigDecimal;
import org.softfriascorp.applz.entity.producto.ProductoDto;

/**
 *
 * @author usuario
 */
public class DetallesVenta {

    private Long id;

    private Integer cantidad;

    private BigDecimal precioUnitario;

    private BigDecimal subTotal;

    private ProductoDto producto;

    public DetallesVenta() {
        
        
    }
    
    public void calcularSubTotal(){
        subTotal =  precioUnitario.multiply(BigDecimal.valueOf(cantidad));        
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public BigDecimal getPrecioUnitario() {
        return precioUnitario;
    }

    public void setPrecioUnitario(BigDecimal precioUnitario) {
        this.precioUnitario = precioUnitario;
    }

    public BigDecimal getSubTotal() {
        return subTotal;
    }

    public void setSubTotal(BigDecimal subTotal) {
        this.subTotal = subTotal;
    }

    public ProductoDto getProducto() {
        return producto;
    }

    public void setProducto(ProductoDto producto) {
        this.producto = producto;
    }
    
    
    

}
