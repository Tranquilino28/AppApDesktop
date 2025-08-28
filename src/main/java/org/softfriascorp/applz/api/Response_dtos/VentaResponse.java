/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.softfriascorp.applz.api.Response_dtos;

import java.time.LocalDateTime;
import java.util.List;
import lombok.Data;

/**
 *
 * @author usuario
 */



public class VentaResponse {
    private Long id;
    private Double valorTotal;
    private LocalDateTime fechaVenta;
    private String metodoPago;
    private String nombreCliente;
    private EmpresaResponse empresa;
    private List<DetalleResponse> detalles;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(Double valorTotal) {
        this.valorTotal = valorTotal;
    }

    public LocalDateTime getFechaVenta() {
        return fechaVenta;
    }

    public void setFechaVenta(LocalDateTime fechaVenta) {
        this.fechaVenta = fechaVenta;
    }

    public String getMetodoPago() {
        return metodoPago;
    }

    public void setMetodoPago(String metodoPago) {
        this.metodoPago = metodoPago;
    }

    public String getNombreCliente() {
        return nombreCliente;
    }

    public void setNombreCliente(String nombreCliente) {
        this.nombreCliente = nombreCliente;
    }

    public EmpresaResponse getEmpresa() {
        return empresa;
    }

    public void setEmpresa(EmpresaResponse empresa) {
        this.empresa = empresa;
    }

    public List<DetalleResponse> getDetalles() {
        return detalles;
    }

    public void setDetalles(List<DetalleResponse> detalles) {
        this.detalles = detalles;
    }
    
    
}

