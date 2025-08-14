/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.softfriascorp.applz.controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import org.softfriascorp.applz.api.LoginHttp;
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

/**
 *
 * @author usuario
 */
public class Controlador_de_Vistas implements ActionListener, MouseListener, KeyListener {

    Frame_Work ventanaPrincipal;
    PFacturacion facturacion;
    PInventario inventario;
    PLogin vista_login;
    PMenuHeader menu_de_opciones;
    PPagos pagos;
    PRegister vista_registro_de_usuario;
    PSliderMenu slider;
    PVenta venta;
    PSlider_Contenedor panel_de_opciones_ocultable;

    public Controlador_de_Vistas(
            Frame_Work ventanaPrincipal,
             PFacturacion facturacion,
             PInventario inventario,
             PLogin vista_login,
             PMenuHeader menu_de_opciones,
             PPagos pagos,
             PRegister vista_registro_de_usuario,
             PSliderMenu slider,
             PVenta venta,
             PSlider_Contenedor panel_de_opciones_ocultable
    ) {
        this.ventanaPrincipal = ventanaPrincipal;
        this.facturacion = facturacion;
        this.inventario = inventario;
        this.vista_login = vista_login;
        this.menu_de_opciones = menu_de_opciones;
        this.pagos = pagos;
        this.vista_registro_de_usuario = vista_registro_de_usuario;
        this.slider = slider;
        this.venta = venta;
        this.panel_de_opciones_ocultable = panel_de_opciones_ocultable;

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
        

        this.menu_de_opciones.btn_menu.addActionListener(this);
        this.menu_de_opciones.btn_menu.addKeyListener(this);
        this.menu_de_opciones.btn_menu.addMouseListener(this);
        
        this.pagos.btn_atras.addActionListener(this);
        this.pagos.btn_atras.addKeyListener(this);
        this.pagos.btn_atras.addMouseListener(this);
        
        
       
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        
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

}
