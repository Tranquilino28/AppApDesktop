/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package org.softfriascorp.applz;


import com.google.inject.Guice;
import com.google.inject.Injector;
import javax.swing.SwingUtilities;
import org.softfriascorp.applz.local.controllers.Controlador_de_Vistas;
import org.softfriascorp.applz.module_inventario.controller.Controller_Inventario;
import org.softfriascorp.applz.module_login.controller.Controller_Login;
import org.softfriascorp.applz.module_pagos.controller.Controller_Pagos;
import org.softfriascorp.applz.local.controllers.Controller_SeguimientoVentas;
import org.softfriascorp.applz.module_ventas.controllers.Controller_Venta;
import org.softfriascorp.applz.local.guiceinyector.AppModule;
import org.softfriascorp.applz.local.model.Usuario;
import org.softfriascorp.applz.local.repository.impl.ImplRepo_Usuario;
import org.softfriascorp.applz.local.repository.interfaces.Repository;
import org.softfriascorp.applz.local.service.impl.ImplService_Usuario;
import org.softfriascorp.applz.local.service.venta.service.ServiceCarrito;
import org.softfriascorp.applz.module_framework.views.Frame_Work;
import org.softfriascorp.applz.views.PFacturacion;
import org.softfriascorp.applz.module_inventario.views.PInventario;
import org.softfriascorp.applz.module_login.views.PLogin;
import org.softfriascorp.applz.module_framework.views.PMenuHeader;
import org.softfriascorp.applz.module_pagos.views.PPagos;
import org.softfriascorp.applz.views.PRegister;
import org.softfriascorp.applz.module_framework.views.PSliderMenu;
import org.softfriascorp.applz.module_ventas.views.PVenta;
import org.softfriascorp.applz.module_framework.views.PSlider_Contenedor;
import org.softfriascorp.applz.views.Seguimiento_ventas;


/**
 *
 * @author usuario
 */
public class APPLZ {

    public static void main(String[] args ){
        SwingUtilities.invokeLater(() -> {
            
          Injector inject = Guice.createInjector(new AppModule());
           
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
            
            PInventario panel_de_inventario = new PInventario();
            
            PPagos panel_de_pagos = new PPagos();
            
            //preparo el slider de menu             
            sliderContenedor.add(sliderMenu);
            
            
            Seguimiento_ventas historialVentas = new Seguimiento_ventas();
            
            //prepara el servicio de ventas 
            ServiceCarrito servicio_de_venta = new ServiceCarrito();
            
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
            
            Controller_Inventario controlller_inventario = inject.getInstance(Controller_Inventario.class);
            
            Controller_SeguimientoVentas controlller_seguimientoVentas = inject.getInstance(Controller_SeguimientoVentas.class);
            
            
            
            controlller_inventario.setInventario(panel_de_inventario);
            controlller_seguimientoVentas.setHistorialVentas(historialVentas);
            
            /* new Controller_Inventario(
                    panel_de_inventario
                    
                    
            
            );
             
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
                    , sliderMenu
                    , menu_de_opciones
                    , panel_de_venta
                    , panel_de_inventario
                    , historialVentas
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
