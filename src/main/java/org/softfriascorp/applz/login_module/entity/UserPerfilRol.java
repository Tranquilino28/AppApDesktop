/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.softfriascorp.applz.login_module.entity;

/**
 *
 * @author usuario
 */
public class UserPerfilRol {
    
    private static String nombre;
    private static String rol;
    private static Long sucursalId;

    public UserPerfilRol() {
    }

    
    public UserPerfilRol(String nombre, String rol) {
        this.nombre = nombre;
        this.rol = rol;
    }

    public static String getNombre() {
        return nombre;
    }

    public static void setNombre(String nombre) {
        UserPerfilRol.nombre = nombre;
    }

    public static String getRol() {
        return rol;
    }

    public static void setRol(String rol) {
        UserPerfilRol.rol = rol;
    }

    public static Long getSucursalId() {
        return sucursalId;
    }

    public static void setSucursalId(Long empresa_Id) {
        UserPerfilRol.sucursalId = empresa_Id;
    }

    
   
    
}
