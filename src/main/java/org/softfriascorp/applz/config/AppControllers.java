/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.softfriascorp.applz.config;

import com.google.inject.Inject;
import org.softfriascorp.applz.login_module.controllers.LoginController;
import org.softfriascorp.applz.mainframework_module.controllers.MainFrameWorkController;
import org.softfriascorp.applz.pay_module.controllers.PagosController;
import org.softfriascorp.applz.cuenta_module.controllers.VentaController;
import org.softfriascorp.applz.cuenta_module.submodules.cuenta_module.service.interfaces.CuentaService;
import org.softfriascorp.applz.inventario_module.controllers.InventarioController;
import org.softfriascorp.applz.update.UpdateService;


/**
 *
 * @author usuario
 */
public class AppControllers {
    
    
    MainFrameWorkController mainFrameWorkController;
    LoginController loginController;
    VentaController ventaController;
    PagosController pagosController;
    InventarioController inventarioController;
    
    CuentaService cuentaService;
    UpdateService updateService;

  
    @Inject
    public AppControllers(
            MainFrameWorkController mainFrameWorkController
            ,LoginController loginController
            , VentaController ventaController
            , PagosController pagosController
            , InventarioController inventarioController
            
            , CuentaService cuentaService
            , UpdateService updateService

            
    ) {
        this.mainFrameWorkController = mainFrameWorkController;
        this.loginController = loginController;
        this.ventaController = ventaController;
        this.pagosController = pagosController;
        this.inventarioController = inventarioController;
        
        this.cuentaService = cuentaService;
        
       this.updateService = updateService;
    }
    
   public void initConfig(){
        
       mainFrameWorkController.initConf();
       loginController.initConfig();
       ventaController.initConfig();
       pagosController.initConfig();
       inventarioController.initConfig();
      
      // updateService.checkForUpdatesAsync();
    }
   
   
   public void initContex(){
       
        /**
         * listener de ventas
         */
        ventaController.setOnPagoIniciado(() -> {
            System.out.println("se inicio el pago");
           
            mainFrameWorkController.mostrarVista("pagos");
            

             pagosController.ShowDetailCuenta();
            
        });

        /**
         * listener de pagos 
         */
        pagosController.setOnPagoFinalizado(() -> {
            
            System.out.println("finalizo el pago");
            
            ventaController.eliminarCuenta();
            
            mainFrameWorkController.mostrarVista("ventas");
            
        });
        
        pagosController.setOnCancelarPago(() -> {
            
            System.out.println("canceando pago");            
            
            mainFrameWorkController.mostrarVista("ventas");
        });
        
        
        /**
         * listener de menuSlider
         */
        
        
        
        initConfig();
        
    }
       
       
   
    
    
    
    
}
