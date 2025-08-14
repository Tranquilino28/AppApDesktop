/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.softfriascorp.applz.modelProductosVenta;

/**
 *
 * @author usuario
 */
public class VentaProductos {
    private String codigoBarras;
    private String descripcion;
    private int cantidad;
    private String unidad_de_medida;
    private Double precioUnitario;
    private Double precioTotal;
    
   public void VentaProducto(){}

    public VentaProductos(String codigo, String descripcion, int cantidad, String unidad_de_medida, Double precioUnitario, Double precioTotal) {
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

    public Double getPrecioUnitario() {
        return precioUnitario;
    }

    public void setPrecioUnitario(Double precioUnitario) {
        this.precioUnitario = precioUnitario;
    }

    public Double getPrecioTotal() {
        return precioTotal;
    }

    public void setPrecioTotal(Double precioTotal) {
        this.precioTotal = precioTotal;
    }

   
    
}
