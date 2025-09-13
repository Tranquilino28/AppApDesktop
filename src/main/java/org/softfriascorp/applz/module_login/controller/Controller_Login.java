/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.softfriascorp.applz.module_login.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import org.softfriascorp.applz.module_login.auth.AuthService;
import org.softfriascorp.applz.local.model.UsuarioPerfil;
import org.softfriascorp.applz.local.service.impl.ImplService_Usuario;
import org.softfriascorp.applz.util.Cambio_panel;
import org.softfriascorp.applz.module_framework.views.Frame_Work;
import org.softfriascorp.applz.module_login.views.PLogin;
import org.softfriascorp.applz.module_framework.views.PMenuHeader;
import org.softfriascorp.applz.views.PRegister;
import org.softfriascorp.applz.module_ventas.views.PVenta;
import org.softfriascorp.applz.module_framework.views.PSlider_Contenedor;

/**
 *
 * @author usuario
 */
public class Controller_Login implements ActionListener, KeyListener{
    
    Frame_Work ventanaPrincipal;
    PSlider_Contenedor sliderMenu;    
    PLogin vista_login;
    PRegister vista_registro_de_usuario;
    
    PVenta venta;
    
    PMenuHeader menuHeader;
     
       
    ImplService_Usuario servicio_de_usuario;
   

    public Controller_Login(
            Frame_Work ventanaPrincipal 
            , PSlider_Contenedor sliderMenu
            , PMenuHeader menuHeader
            , PVenta venta
            , PLogin vista_login
            , PRegister pregistro
            , ImplService_Usuario service
            
            )
    {
        this.ventanaPrincipal = ventanaPrincipal;
        this.sliderMenu = sliderMenu;       
        this.menuHeader = menuHeader;
        this.venta = venta;
        
        this.vista_login = vista_login;        
        this.vista_registro_de_usuario = pregistro;
        
        this.servicio_de_usuario = service;
           
        this.vista_login.btn_login.addActionListener(this);
        this.vista_login.btn_login.addKeyListener(this);
        
        this.vista_login.btn_registrarse.addActionListener(this);
 
        this.vista_registro_de_usuario.btn_registro.addActionListener(this);
        
        this.vista_registro_de_usuario.btn_registro.addKeyListener(this);
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == vista_login.btn_login) {
            
           if( AuthService.login(vista_login.txt_usuario.getText(), vista_login.txt_usuario.getText())){
               
               menuHeader.lbl_usuario_rol.setText(("Empresa : "+ UsuarioPerfil.getEmpresa_Id()+ "---Rol : "+ UsuarioPerfil.getRol() + " || Usuario" + UsuarioPerfil.getSub()));
               
                Cambio_panel.addPanelVenta(
                        ventanaPrincipal.fw_Container
                        , menuHeader
                        , sliderMenu
                        , venta);
                
                
                //menuHeader.lbl_usuario_rol.setText(LoginHttp.getUsuarioRol());
           }            
        }  
        
       /* if (e.getSource() == plogin.btn_login) {
            
            try {
                if(service.validarUsuario(plogin.txt_usuario.getText(), plogin.txt_contrasena.getText())){
                    
                    Cambio_panel.addPanelCenter(fw.fw_Container, som);
                    
                }} catch (InvalidKeySpecException ex) {
                Logger.getLogger(Controller_Login.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        if (e.getSource() == plogin.btn_registrarse) {
            System.out.println("registrarse");
             Cambio_panel.addPanelCenter(fw.fw_Container, pregistro);
        }*/
                
    }

    @Override
    public void keyTyped(KeyEvent e) {
        
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getSource() == vista_login.btn_login){
            if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                if( AuthService.login(vista_login.txt_usuario.getText(), vista_login.txt_usuario.getText())){
                    
                     menuHeader.lbl_usuario_rol.setText(("Empresa : "+ UsuarioPerfil.getEmpresa_Id()+ "---Rol : "+ UsuarioPerfil.getRol()+ " || Usuario : " + UsuarioPerfil.getSub()));
                     
                     
                   Cambio_panel.addPanelVenta(
                        ventanaPrincipal.fw_Container
                        , menuHeader
                        , sliderMenu
                        , venta);
              }   
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        
    }
    
    
    
}
