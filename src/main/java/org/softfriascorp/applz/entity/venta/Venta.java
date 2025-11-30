/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.softfriascorp.applz.entity.venta;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import org.softfriascorp.applz.entity.detallesventa.DetallesVenta;
import org.softfriascorp.applz.entity.maestra.Maestra;

/**
 *
 * @author usuario
 */
public class Venta {
    
    /* "codigoVenta": "0464b33d-3dff-465b-8645-a18117a565a1",
    "valorTotal": 23800.00,
    "valorRecibido": 34500.90,
    "valorDevuelto": -10700.90,
    "fechaVenta": "2025-11-30T15:27:51.6868402",
    "metodoPago": "EFECTIVO",
    "empleado": "juan pablo",
    "cliente": "00",
    "estado": "FACTURADO",*/
    
    private Long id;

    private String codigoVenta;

    private BigDecimal valorTotal;
    
    private BigDecimal valorRecibido;
    
    private BigDecimal valorDevuelto;
    
    private LocalDateTime fechaVenta;

    private String metodoPago;
    
    private String empleado;
    
    private String cliente;
    
    private String estado;

    private List<DetallesVenta> detalles = new ArrayList<>();

    public Venta() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCodigoVenta() {
        return codigoVenta;
    }

    public void setCodigoVenta(String codigoVenta) {
        this.codigoVenta = codigoVenta;
    }

    public BigDecimal getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(BigDecimal valorTotal) {
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

    public List<DetallesVenta> getDetalles() {
        return detalles;
    }

    public void setDetalles(List<DetallesVenta> detalles) {
        this.detalles = detalles;
    }

    public BigDecimal getValorRecibido() {
        return valorRecibido;
    }

    public void setValorRecibido(BigDecimal valorRecibido) {
        this.valorRecibido = valorRecibido;
    }

    public BigDecimal getValorDevuelto() {
        return valorDevuelto;
    }

    public void setValorDevuelto(BigDecimal valorDevuelto) {
        this.valorDevuelto = valorDevuelto;
    }

    public String getEmpleado() {
        return empleado;
    }

    public void setEmpleado(String empleado) {
        this.empleado = empleado;
    }

    public String getCliente() {
        return cliente;
    }

    public void setCliente(String cliente) {
        this.cliente = cliente;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    
    
    
}
