/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.softfriascorp.applz.local.model;




/**
 *
 * @author usuario
 */

public class UsuarioPerfil {
   private static String sub;
    private static  String rol;
    private static Long empresa_Id;
    private static String nombre;

    private UsuarioPerfil() {
    }

    public static String getSub() {
        return sub;
    }

    public static void setSub(String sub) {
        UsuarioPerfil.sub = sub;
    }

    public static String getRol() {
        return rol;
    }

    public static void setRol(String rol) {
        UsuarioPerfil.rol = rol;
    }

    public static Long getEmpresa_Id() {
        return empresa_Id;
    }

    public static void setEmpresa_Id(Long empresa_Id) {
        UsuarioPerfil.empresa_Id = empresa_Id;
    }

    public static String getNombre() {
        return nombre;
    }

    public static void setNombre(String nombre) {
        UsuarioPerfil.nombre = nombre;
    }
    
    
    @Override
    public String toString() {
        return "Rol : "+ rol + " || Usuario" + sub;
    }

    
   
}
