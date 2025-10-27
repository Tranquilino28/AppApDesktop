/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.softfriascorp.applz.mainframework_module.controllers;

import jakarta.inject.Inject;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JComponent;
import javax.swing.JPanel;
import org.softfriascorp.applz.inventario_module.views.PInventario;
import org.softfriascorp.applz.login_module.views.LoginPanel;
import org.softfriascorp.applz.login_module.views.PRegister;
import org.softfriascorp.applz.mainframework_module.services.interfaces.MainFrameWorkService;
import org.softfriascorp.applz.mainframework_module.util.viewController;
import org.softfriascorp.applz.mainframework_module.views.MainFrameWork;
import org.softfriascorp.applz.mainframework_module.views.PMenuHeader;
import org.softfriascorp.applz.mainframework_module.views.PSliderMenu;
import org.softfriascorp.applz.mainframework_module.views.PSliderContent;
import org.softfriascorp.applz.pay_module.views.PPagos;
import org.softfriascorp.applz.cuenta_module.views.PFacturacion;
import org.softfriascorp.applz.cuenta_module.views.PVenta;


/**
 *
 * @author usuario
 */
public class MainFrameWorkController implements ActionListener, MouseListener, KeyListener {

    private final MainFrameWork ventanaPrincipal;
    private final PMenuHeader menuHeaderPanel;
   private final PSliderMenu sliderMenuOptions;
   
   
    private final LoginPanel loginPanel;    

    private final PVenta ventaPanel;
    
    private final PInventario inventarioPanel;
   // private final Seguimiento_ventas historyVentasPanel;

    private final PPagos pagosPanel;
    
    private final PFacturacion facturacionPanel;

    
    private final MainFrameWorkService mainFrameService;
    
    
    
    @Inject
    public MainFrameWorkController(
            MainFrameWork ventanaPrincipal,
            PSliderContent contWithsliderMenu,
            PSliderMenu sliderMenu,
            PMenuHeader menuHeader,
             
             LoginPanel vista_login,
             
             PVenta venta,
             
             PInventario inventario,
             
            // Seguimiento_ventas seguimiento,
             
             PRegister pregistro,
             
             PPagos pagos,
             
             PFacturacion facturacion,
             
            MainFrameWorkService mainFrameService
            
    ) {
        this.ventanaPrincipal = ventanaPrincipal;
        
        this.sliderMenuOptions = sliderMenu;
        this.menuHeaderPanel = menuHeader;
        this.ventaPanel = venta;
        this.inventarioPanel = inventario;
       
        this.facturacionPanel = facturacion;
        this.loginPanel = vista_login;
       
        this.pagosPanel = pagos;
        
        this.mainFrameService = mainFrameService;
    }

    public void initConf() {
        ventanaPrincipal.setSize(600, 400);

        viewController.nextView(ventanaPrincipal.fw_Container, loginPanel);
 
        menuHeaderPanel.btn_menu.setSelected(true);
         
        ventanaPrincipal.setLocationRelativeTo(null);
        ventanaPrincipal.setVisible(true);

        
        initListener();
    }

    private void initListener() {
        // vista de ventas
        this.facturacionPanel.btn_buscarProductoStok.addActionListener(this);
        this.facturacionPanel.btn_buscarProductoStok.addKeyListener(this);
        this.facturacionPanel.btn_buscarProductoStok.addMouseListener(this);
                
        //vista de facturacion
        this.ventaPanel.btn_pagar.addActionListener(this);
        this.ventaPanel.btn_pagar.addKeyListener(this);
        this.ventaPanel.btn_pagar.addMouseListener(this);
        
        //vista de login
        this.loginPanel.btn_login.addActionListener(this);
        this.loginPanel.btn_login.addKeyListener(this);
        this.loginPanel.btn_login.addMouseListener(this);

        this.loginPanel.btn_registrarse.addActionListener(this);
        this.loginPanel.btn_registrarse.addKeyListener(this);
        this.loginPanel.btn_registrarse.addMouseListener(this);

        //vista de headerMenu
        this.menuHeaderPanel.btn_menu.addActionListener(this);
        this.menuHeaderPanel.btn_menu.addKeyListener(this);
        this.menuHeaderPanel.btn_menu.addMouseListener(this);

        //salir de la app
        this.menuHeaderPanel.btn_closeApp.addMouseListener(this); //salir de la app

        //vista de pagos
        this.pagosPanel.btn_atras.addActionListener(this);
        this.pagosPanel.btn_atras.addKeyListener(this);
        this.pagosPanel.btn_atras.addMouseListener(this);

        //sliderMenu
        this.sliderMenuOptions.btn_inventario.addActionListener(this);
        this.sliderMenuOptions.btn_facturacion.addActionListener(this);
        this.sliderMenuOptions.btn_ventas.addActionListener(this);

    }
    
    

    @Override
    public void actionPerformed(ActionEvent e) {

        /**
         * botones de la vista login
         
        */
        if(e.getSource() == loginPanel.btn_registrarse){            
            
        }
        
        /**
         * botones del Header Menu
         * 
         * menu -> si el boton del menu esta seleccionado que muestre el
         * slidermenu ccaso contrario que lo oculte
         */
       
        if (e.getSource() == menuHeaderPanel.btn_menu) {
            
            if (menuHeaderPanel.btn_menu.isSelected()) {
                
              viewController.showSlidebar(ventanaPrincipal.fw_Container,sliderMenuOptions);
            }else{
               viewController.suprimirSlidebar(ventanaPrincipal.fw_Container, sliderMenuOptions);
            }
        }
        
      
        /**
         * botones del sliderMenu
         */  
        if (e.getSource() == sliderMenuOptions.btn_inventario) {
            viewController.showModulePanel(ventanaPrincipal.fw_Container, inventarioPanel);
                    
        }
        
        if (e.getSource() == sliderMenuOptions.btn_ventas) {
            
        } 
       

    }

    @Override
    public void mouseClicked(MouseEvent e) {
/*
        if(e.getSource() == menuHeaderPanel.btn_closeApp){
            
            viewController.nextView(ventanaPrincipal.fw_Container, loginPanel);
            
        }*/
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
    
    
    public void mostrarVista(String vista) {
    switch (vista) {
        case "login" -> viewController.nextView(ventanaPrincipal.fw_Container, loginPanel);
        case "ventas" -> viewController.showModulePanel(ventanaPrincipal.fw_Container, ventaPanel);
        case "pagos" -> viewController.showModulePanel(ventanaPrincipal.fw_Container, pagosPanel);
        case "inventario" -> viewController.showModulePanel(ventanaPrincipal.fw_Container, inventarioPanel);
        case "CerrarSeccion" -> viewController.nextView(ventanaPrincipal.fw_Container, loginPanel);
    }
}

}
