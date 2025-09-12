/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.softfriascorp.applz.modelProductosVenta;

import java.math.BigDecimal;

/**
 *
 * @author usuario
 */
public class Productos_Carrito {
    private String codigoBarras;
    private String descripcion;
    private int cantidad;
    private String unidad_de_medida;
    private BigDecimal precioUnitario;
    private BigDecimal precioTotal;
    
   

    public Productos_Carrito(String codigo, String descripcion, int cantidad, String unidad_de_medida, BigDecimal precioUnitario, BigDecimal precioTotal) {
        this.codigoBarras = codigo;
        this.descripcion = descripcion;
        this.cantidad = cantidad;
        this.unidad_de_medida = unidad_de_medida;
        this.precioUnitario = precioUnitario;
        this.precioTotal = precioTotal;
    }

    public String getCodigoBarras() {
        return codigoBarras;
    }

    public void setCodigoBarras(String codigoBarras) {
        this.codigoBarras = codigoBarras;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public String getUnidad_de_medida() {
        return unidad_de_medida;
    }

    public void setUnidad_de_medida(String unidad_de_medida) {
        this.unidad_de_medida = unidad_de_medida;
    }

    public BigDecimal getPrecioUnitario() {
        return precioUnitario;
    }

    public void setPrecioUnitario(BigDecimal precioUnitario) {
        this.precioUnitario = precioUnitario;
    }

    public BigDecimal getPrecioTotal() {
        return precioTotal;
    }

    public void setPrecioTotal(BigDecimal precioTotal) {
        this.precioTotal = precioTotal;
    }

   
    
}
