/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.softfriascorp.applz.api.request;

import lombok.Data;

/**
 *
 * @author usuario
 */
@Data
public class UsuariosRequest {
    private Long usua_codigo;
    private String usua_usuario;
    private String usua_rol;
    private String hash_password;
    private Persona persona;

    @Data
    public static class Persona {
        private Integer identificacion;
        private String nombre;
        private String apellido;
        private String direccion;
        private Maestra tipoIdentificacion;
        private Maestra tipoSexo;
        private Maestra tipoEstado;
    }

    @Data
    public static class Maestra {
        private Long id;
    }
    
}
