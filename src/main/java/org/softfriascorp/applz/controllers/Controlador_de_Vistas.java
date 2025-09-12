/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.softfriascorp.applz.controllers;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import org.softfriascorp.applz.api.auth.dtotemp.LoginHttp;
import org.softfriascorp.applz.service.impl.ImplService_Usuario;
import org.softfriascorp.applz.util.Cambio_panel;
import org.softfriascorp.applz.views.Frame_Work;
import org.softfriascorp.applz.views.PFacturacion;
import org.softfriascorp.applz.views.PInventario;
import org.softfriascorp.applz.views.PLogin;
import org.softfriascorp.applz.views.PMenuHeader;
import org.softfriascorp.applz.views.PPagos;
import org.softfriascorp.applz.views.PRegister;
import org.softfriascorp.applz.views.PSliderMenu;
import org.softfriascorp.applz.views.PVenta;
import org.softfriascorp.applz.views.PSlider_Contenedor;
import org.softfriascorp.applz.views.Seguimiento_ventas;

/**
 *
 * @author usuario
 */
public class Controlador_de_Vistas implements ActionListener, MouseListener, KeyListener {

    private Frame_Work ventanaPrincipal;
    private PSlider_Contenedor contWithsliderMenu;    
    private PLogin vista_login;
    private PRegister vista_registro_de_usuario;
    private PSliderMenu sliderMenu;
    
    
    private PVenta venta;
    private PInventario inventario;
    private Seguimiento_ventas seguimiento;
    
    
    
    private PPagos pagos;
    
    private PMenuHeader menuHeader;
    
    private PFacturacion facturacion;
     
   
    public Controlador_de_Vistas(
            Frame_Work ventanaPrincipal 
            , PSlider_Contenedor contWithsliderMenu
            , PSliderMenu sliderMenu
            , PMenuHeader menuHeader
            , PVenta venta
            , PInventario inventario
            , Seguimiento_ventas seguimiento
            , PLogin vista_login
            , PRegister pregistro
            , PPagos pagos
            , PFacturacion facturacion
            
            
            )
    {
        this.ventanaPrincipal = ventanaPrincipal;
        this.contWithsliderMenu = contWithsliderMenu;   
        this.sliderMenu = sliderMenu;
        this.menuHeader = menuHeader;
        this.venta = venta;
        this.inventario = inventario;
        this.seguimiento = seguimiento;
        this.facturacion = facturacion;
        this.vista_login = vista_login;        
        this.vista_registro_de_usuario = pregistro;
        this.pagos = pagos;
        
        initListener();
    }

    private void initListener() {
        facturacion.btn_buscarProductoStok.addActionListener(this);
        facturacion.btn_buscarProductoStok.addKeyListener(this);
        facturacion.btn_buscarProductoStok.addMouseListener(this);

        this.vista_login.btn_login.addActionListener(this);
        this.vista_login.btn_login.addKeyListener(this);
        this.vista_login.btn_login.addMouseListener(this);
        
        this.vista_login.btn_registrarse.addActionListener(this);
        this.vista_login.btn_registrarse.addKeyListener(this);
        this.vista_login.btn_registrarse.addMouseListener(this);
        
        

        this.menuHeader.btn_menu.addActionListener(this);
        this.menuHeader.btn_menu.addKeyListener(this);
        this.menuHeader.btn_menu.addMouseListener(this);
        
        this.pagos.btn_atras.addActionListener(this);
        this.pagos.btn_atras.addKeyListener(this);
        this.pagos.btn_atras.addMouseListener(this);
        
      
        
        this.sliderMenu.btn_inventario.addActionListener(this);
        this.sliderMenu.btn_facturacion.addActionListener(this);
         this.sliderMenu.btn_ventas.addActionListener(this);
       
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        
       if(e.getSource() == pagos.btn_atras || e.getSource() == sliderMenu.btn_facturacion){
           Cambio_panel.addPanelVenta(
                   ventanaPrincipal.fw_Container
                   ,  menuHeader
                   , contWithsliderMenu
                   , venta
           );
       }
       
       
       if (e.getSource() == menuHeader.btn_menu) {
            if (contWithsliderMenu.isVisible()) {
                contWithsliderMenu.setVisible(false);
            }else{
                contWithsliderMenu.setVisible(true);
            }
        }
       
        if (e.getSource() == sliderMenu.btn_inventario) {
            Cambio_panel.addPanelVenta(
                        ventanaPrincipal.fw_Container
                        , menuHeader
                        , contWithsliderMenu
                        , inventario);
        } 
        if (e.getSource() == sliderMenu.btn_ventas) {
            Cambio_panel.addPanelVenta(
                        ventanaPrincipal.fw_Container
                        , menuHeader
                        , contWithsliderMenu
                        , seguimiento);
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    private void showSlidebar(PSlider_Contenedor som, PMenuHeader mo,PSliderMenu slider) {
        //som.add(mo, BorderLayout.NORTH);
        som.add(slider, BorderLayout.WEST);
        som.revalidate();
        som.repaint();
    }
    private void suprimirSlidebar(PSlider_Contenedor som, PSliderMenu slider) {
       
        som.remove(slider);
        som.revalidate();
        som.repaint();
    }
}
