/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.softfriascorp.applz.entity.venta;

import com.fasterxml.jackson.annotation.JsonInclude;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import org.softfriascorp.applz.entity.detallesventa.DetallesVenta;
import org.softfriascorp.applz.entity.detallesventa.DetallesVentaDtoRequest;
import org.softfriascorp.applz.entity.maestra.Maestra;
import org.softfriascorp.applz.entity.persona.Persona;
import org.softfriascorp.applz.modules.login.entity.AuthResponsePerfil;
import org.w3c.dom.UserDataHandler;

/**
 *
 * @author usuario
 */

@JsonInclude(JsonInclude.Include.NON_NULL)
public class VentaDtoRequest {
    
    private Long metodoPagoId;
    
    private BigDecimal valorRecibido;
    
    private String cliente;
    
    private Long empleadoId;
    
    private Long estadoId;
   
    
    private List<DetallesVentaDtoRequest> detalles = new ArrayList<>();

    public VentaDtoRequest() {
    }

    public BigDecimal getValorRecibido() {
        return valorRecibido;
    }

    public void setValorRecibido(BigDecimal valorRecibido) {
        this.valorRecibido = valorRecibido;
    }

    public Long getMetodoPagoId() {
        return metodoPagoId;
    }

    public void setMetodoPagoId(Long metodoPagoId) {
        this.metodoPagoId = metodoPagoId;
    }

    public String getCliente() {
        return cliente;
    }

    public void setCliente(String cliente) {
        this.cliente = cliente;
    }

    public Long getEmpleadoId() {
        return empleadoId;
    }

    public void setEmpleadoId(Long empleadoId) {
        this.empleadoId = empleadoId;
    }

    public Long getEstadoId() {
        return estadoId;
    }

    public void setEstadoId(Long estadoId) {
        this.estadoId = estadoId;
    }

    public List<DetallesVentaDtoRequest> getDetalles() {
        return detalles;
    }

    public void setDetalles(List<DetallesVentaDtoRequest> detalles) {
        this.detalles = detalles;
    }

    
}
