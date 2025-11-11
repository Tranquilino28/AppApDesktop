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
import org.softfriascorp.applz.entity.maestra.Maestra;
import org.softfriascorp.applz.entity.persona.Persona;
import org.softfriascorp.applz.modules.login_module.entity.AuthResponsePerfil;
import org.w3c.dom.UserDataHandler;

/**
 *
 * @author usuario
 */

@JsonInclude(JsonInclude.Include.NON_NULL)
public class VentaRequest {
    
    private Maestra metodoPago;
    
    private BigDecimal valorRecibido;
    
    private Persona cliente;
    
    private AuthResponsePerfil empleado;
    
    private Maestra estado;
    
   
    
    private List<DetallesVenta> detalles = new ArrayList<>();

    public VentaRequest() {
    }

    public BigDecimal getValorRecibido() {
        return valorRecibido;
    }

    public void setValorRecibido(BigDecimal valorRecibido) {
        this.valorRecibido = valorRecibido;
    }

    public Maestra getMetodoPago() {
        return metodoPago;
    }

    public void setMetodoPago(Maestra metodoPago) {
        this.metodoPago = metodoPago;
    }

    public List<DetallesVenta> getDetalles() {
        return detalles;
    }

    public void setDetalles(List<DetallesVenta> detalles) {
        this.detalles = detalles;
    }

    public Persona getCliente() {
        return cliente;
    }

    public void setCliente(Persona cliente) {
        this.cliente = cliente;
    }

    public AuthResponsePerfil getEmpleado() {
        return empleado;
    }

    public void setEmpleado(AuthResponsePerfil empleado) {
        this.empleado = empleado;
    }

    public Maestra getEstado() {
        return estado;
    }

    public void setEstado(Maestra estado) {
        this.estado = estado;
    }
   

}
