/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.softfriascorp.applz.model;

import lombok.Data;

/**
 *
 * @author usuario
 */

@Data
public class Persona {
    
    private Long id;

    private Integer identificacion;

    private String nombre;

    private String apellido;

    private String direccion;

    private String tipoIdentificacion;

    private String tipoSexo;

    private String tipoEstado;   
    
}
