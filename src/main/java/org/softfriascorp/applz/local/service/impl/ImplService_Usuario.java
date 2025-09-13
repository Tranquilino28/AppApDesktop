/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.softfriascorp.applz.local.service.impl;

import java.security.spec.InvalidKeySpecException;
import org.softfriascorp.applz.local.model.Producto;
import org.softfriascorp.applz.local.model.Usuario;
import org.softfriascorp.applz.local.repository.interfaces.Repository;
import org.softfriascorp.applz.local.service.interfaces.AbstracService;
import org.softfriascorp.applz.util.PassSecure;

/**
 *
 * @author usuario
 */
public class ImplService_Usuario extends AbstracService<Usuario,String> {
    
    public ImplService_Usuario(Repository<Usuario, String> repo) {
        super(repo);
    }

    public boolean validarUsuario(String usuario, String contrasena) throws InvalidKeySpecException{
        Usuario u = repo.findById(usuario);
        return u != null && PassSecure.verifyPassword(contrasena, u.getHash_salt(), u.getHash_password());
    }

    @Override
    public void save(Usuario t) {
        super.save(t); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/OverriddenMethodBody
    }
    
   
    
    
    
}
