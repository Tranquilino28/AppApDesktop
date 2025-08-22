/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.softfriascorp.applz.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author usuario
 */

public class UsuarioPerfil {
   private static String nombreUsuario;
    private static  String rol;
    private static Long empresaId;
    private static String nombre;

    private UsuarioPerfil() {
    }

    public static String getNombreUsuario() {
        return nombreUsuario;
    }

    public static void setNombreUsuario(String nombreUsuario) {
        UsuarioPerfil.nombreUsuario = nombreUsuario;
    }

    public static String getRol() {
        return rol;
    }

    public static void setRol(String rol) {
        UsuarioPerfil.rol = rol;
    }

    public static Long getEmpresaId() {
        return empresaId;
    }

    public static void setEmpresaId(Long empresaId) {
        UsuarioPerfil.empresaId = empresaId;
    }

    public static String getNombre() {
        return nombre;
    }

    public static void setNombre(String nombre) {
        UsuarioPerfil.nombre = nombre;
    }
    
    
    @Override
    public String toString() {
        return "Rol : "+ rol + " || Usuario" + nombreUsuario;
    }

    
   
}
