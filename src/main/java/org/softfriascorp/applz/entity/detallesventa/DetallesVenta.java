/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.softfriascorp.applz.entity.detallesventa;

import com.fasterxml.jackson.annotation.JsonInclude;
import java.math.BigDecimal;
import org.softfriascorp.applz.entity.producto.ProductoDto;

/**
 *
 * @author usuario
 */

@JsonInclude(JsonInclude.Include.NON_NULL)
public class DetallesVenta {

    private Long id;

    private String cantidad;

    private BigDecimal precioUnitario;

    private BigDecimal subTotal;

    private ProductoDto producto;

    public DetallesVenta() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCantidad() {
        return cantidad;
    }

    public void setCantidad(String cantidad) {
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
    
    public void calcularSubTotal(){
        
        subTotal  = precioUnitario.multiply(new BigDecimal(this.cantidad));        
    }
    
}
