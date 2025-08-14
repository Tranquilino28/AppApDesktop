/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.softfriascorp.applz.controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.security.Provider;
import java.security.spec.InvalidKeySpecException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.softfriascorp.applz.model.Usuario;
import org.softfriascorp.applz.service.impl.ImplService_Usuario;
import org.softfriascorp.applz.service.interfaces.CrudService;
import org.softfriascorp.applz.util.Cambio_panel;
import org.softfriascorp.applz.util.PassSecure;
import org.softfriascorp.applz.views.Frame_Work;
import org.softfriascorp.applz.views.PLogin;
import org.softfriascorp.applz.views.PRegister;
import org.softfriascorp.applz.views.PSliderMenu;
import org.softfriascorp.applz.views.PVenta;
import org.softfriascorp.applz.views.PSlider_Contenedor;

/**
 *
 * @author usuario
 */
public class Controller_Registro implements ActionListener{
    
    Frame_Work fw;      
    PLogin plogin;
    PRegister pregistro;
    CrudService<Usuario, String> service;

    public Controller_Registro(Frame_Work fw ,PRegister pregistro, PLogin plogin, CrudService<Usuario, String> service)
    {
        this.fw = fw;         
        this.plogin = plogin;
        this.pregistro = pregistro;
        this.service = service;
        
        this.pregistro.btn_registro.addActionListener(this);
    }

    
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == pregistro.btn_registro) {
           /* try {
            
            
          
                String hashsalt = PassSecure.generateSalt();
                
                //String hashPass = PassSecure.hashPassword(pregistro.txt_contrasena.getText(), hashsalt);
                
                //service.save(new Usuario(568L, pregistro.txt_usuario.getText(), hashsalt, hashPass));
                    
                Cambio_panel.addPanelCenter(fw.fw_Container, plogin);
                   
            } catch (InvalidKeySpecException ex) {
                ex.printStackTrace();
            }*/
                
               
        }
    }
    
    
    
}
