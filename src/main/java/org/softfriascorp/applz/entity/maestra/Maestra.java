/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.softfriascorp.applz.entity.maestra;

import com.fasterxml.jackson.annotation.JsonInclude;

/**
 *
 * @author usuario
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Maestra {
     private Long id;

    private String nombreLargo;

    private String nombreCorto;

    public Maestra() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombreLargo() {
        return nombreLargo;
    }

    public void setNombreLargo(String nombreLargo) {
        this.nombreLargo = nombreLargo;
    }

    public String getNombreCorto() {
        return nombreCorto;
    }

    public void setNombreCorto(String nombreCorto) {
        this.nombreCorto = nombreCorto;
    }
    
    
}
