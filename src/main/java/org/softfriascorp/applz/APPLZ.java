/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package org.softfriascorp.applz;


import java.util.HashMap;
import java.util.Map;
import javax.swing.SwingUtilities;
import org.softfriascorp.applz.controllers.Controlador_de_Vistas;
import org.softfriascorp.applz.controllers.Controler_SliderOptionMenu;
import org.softfriascorp.applz.controllers.Controller_Login;
import org.softfriascorp.applz.controllers.Controller_MenuOptions;
import org.softfriascorp.applz.controllers.Controller_Pagos;
import org.softfriascorp.applz.controllers.Controller_Registro;
import org.softfriascorp.applz.controllers.Controller_Venta;
import org.softfriascorp.applz.model.Usuario;
import org.softfriascorp.applz.modelProductosVenta.VentaProductos;
import org.softfriascorp.applz.repository.impl.ImplRepo_Usuario;
import org.softfriascorp.applz.repository.interfaces.Repository;
import org.softfriascorp.applz.service.impl.ImplService_Usuario;
import org.softfriascorp.applz.service.venta.service.ServiceVenta;
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
public class APPLZ {

    public static void main(String[] args ){
        SwingUtilities.invokeLater(() -> {
           
            //prepara el repositorio CRUD  de la base de datos
            Repository<Usuario, String> repoUsuario = new ImplRepo_Usuario();
            
            //prepara el servicio para hacer uso del crud atravez del repositorio
            ImplService_Usuario servUsuario = new ImplService_Usuario(repoUsuario);
            
            //preparo la ventana *frame* y las vistas-*panel*
            Frame_Work ventanaPrincipal = new Frame_Work();
            
            PFacturacion facturacion = new PFacturacion();
            
            PSlider_Contenedor sliderContenedor = new PSlider_Contenedor();   
            
            PSliderMenu sliderMenu = new  PSliderMenu();
            
            PMenuHeader menu_de_opciones = new PMenuHeader();
            
            PLogin panel_de_login = new PLogin();
            
            PRegister panel_de_registro = new PRegister();
            
            PVenta panel_de_venta = new PVenta();
            
            PInventario ánel_de_inventario = new PInventario();
            
            PPagos panel_de_pagos = new PPagos();
            
            //preparo el slider de menu             
            sliderContenedor.add(sliderMenu);
            
            
            //prepara el servicio de ventas 
            ServiceVenta servicio_de_venta = new ServiceVenta();
            
            //se inyectan a los controladores los parametros con los paneles y servicios
            new Controller_Login(
                    
                    ventanaPrincipal
                    , sliderContenedor
                    , menu_de_opciones
                    , panel_de_venta
                    , panel_de_login
                    , panel_de_registro
                    , servUsuario
            );
            
            new Controller_Pagos(panel_de_pagos, servicio_de_venta);
                  
            new Controller_Venta(
                    ventanaPrincipal
                    , panel_de_venta
                    , panel_de_pagos
                    , servicio_de_venta
            );
            
            /*  
            //controlador de opciones  y menus 
            new Controler_SliderOptionMenu(slider_de_opciones
                    , menu_de_opciones
                    , slider
                    , panel_de_venta
                    , ánel_de_inventario
            );
            new Controller_MenuOptions(menu_de_opciones
                    , slider
                    , slider_de_opciones
            );
            new Controller_Registro(ventanaPrincipal
                    , panel_de_registro
                    , panel_de_login
                    , servUsuario
            );            
            */
            
            new Controlador_de_Vistas(
                   ventanaPrincipal
                    , sliderContenedor
                    , menu_de_opciones
                    , panel_de_venta
                    , panel_de_login
                    , panel_de_registro
                    , panel_de_pagos
                    , facturacion
            );
            
            //fw.setUndecorated(true);
            
            ventanaPrincipal.setSize(600, 400);           
            ventanaPrincipal.fw_Container.add(panel_de_login);
            ventanaPrincipal.setLocationRelativeTo(null);            
            ventanaPrincipal.setVisible(true);
        });
        
    }

   

    
}
