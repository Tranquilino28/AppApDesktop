/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.softfriascorp.applz.entity.detallesventa;

import lombok.Data;
import org.softfriascorp.applz.entity.productos.dto.Api_Producto;
import org.softfriascorp.applz.entity.empresa.dto.EmpresaResponse;

/**
 *
 * @author usuario
 */

public class DetalleResponse {
        private Long id;
    private int cantidad;
    private Double precioUnitario;
    private Api_Producto producto;
    private EmpresaResponse empresa;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public Double getPrecioUnitario() {
        return precioUnitario;
    }

    public void setPrecioUnitario(Double precioUnitario) {
        this.precioUnitario = precioUnitario;
    }

    public Api_Producto getProducto() {
        return producto;
    }

    public void setProducto(Api_Producto producto) {
        this.producto = producto;
    }

    public EmpresaResponse getEmpresa() {
        return empresa;
    }

    public void setEmpresa(EmpresaResponse empresa) {
        this.empresa = empresa;
    }
    
    
}
