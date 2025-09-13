/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.softfriascorp.applz.module_login.dtos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 *
 * @author usuario
 */
  @JsonIgnoreProperties(ignoreUnknown = true) // 👈 ignora campos no mapeados
public class DtoUserAuth {  
   private  String sub;
    private   String rol;
    private static Long empresa_Id;

    public DtoUserAuth(){}
    
    public DtoUserAuth(String sub, String rol, Long empresa_Id) {
        this.sub = sub;
        this.rol = rol;
        this.empresa_Id = empresa_Id;
    }

    public String getSub() {
        return sub;
    }

    public void setSub(String sub) {
        this.sub = sub;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    public static Long getEmpresa_Id() {
        return empresa_Id;
    }

    public void setEmpresa_Id(Long empresa_Id) {
        this.empresa_Id = empresa_Id;
    }
    
    
    
    

}
