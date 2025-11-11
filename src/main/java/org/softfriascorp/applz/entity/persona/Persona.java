/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.softfriascorp.applz.entity.persona;

import com.fasterxml.jackson.annotation.JsonInclude;
import org.softfriascorp.applz.entity.maestra.Maestra;

/**
 *
 * @author usuario
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Persona {
    
    
    private Long id;

    private Maestra tipoIdentificacion;

    private String identificacion;

    private String nombre;

    private String apellido;

    private String direccion;

    private Maestra sexo;

    private Maestra estado;

    public Persona() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Maestra getTipoIdentificacion() {
        return tipoIdentificacion;
    }

    public void setTipoIdentificacion(Maestra tipoIdentificacion) {
        this.tipoIdentificacion = tipoIdentificacion;
    }

    public String getIdentificacion() {
        return identificacion;
    }

    public void setIdentificacion(String identificacion) {
        this.identificacion = identificacion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public Maestra getSexo() {
        return sexo;
    }

    public void setSexo(Maestra sexo) {
        this.sexo = sexo;
    }

    public Maestra getEstado() {
        return estado;
    }

    public void setEstado(Maestra estado) {
        this.estado = estado;
    }

    
    
}
