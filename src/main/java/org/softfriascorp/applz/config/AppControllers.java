/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.softfriascorp.applz.config;

import com.google.inject.Inject;
import org.softfriascorp.applz.modules.login_module.controllers.LoginController;
import org.softfriascorp.applz.mainframework_module.controllers.MainFrameWorkController;
import org.softfriascorp.applz.modules.pay_module.controllers.PagosController;
import org.softfriascorp.applz.modules.venta_module.controllers.VentaController;
import org.softfriascorp.applz.modules.cuenta_module.service.interfaces.CuentaService;
import org.softfriascorp.applz.modules.inventario_module.controllers.InventarioController;
import org.softfriascorp.applz.modules.pay_module.controllers.FiadoController;
import org.softfriascorp.applz.update.UpdateService;


/**
 *
 * @author usuario
 */
public class AppControllers {
    
    
   private final MainFrameWorkController mainFrameWorkController;
    private final LoginController loginController;
    private final VentaController ventaController;
    private final PagosController pagosController;
    private final InventarioController inventarioController;
    private final FiadoController fiadoController;
    
    private final CuentaService cuentaService;
    private final UpdateService updateService;

  
    @Inject
    public AppControllers(
            MainFrameWorkController mainFrameWorkController
            ,LoginController loginController
            , VentaController ventaController
            , PagosController pagosController
            , InventarioController inventarioController
            , FiadoController fiadoController
            
            , CuentaService cuentaService
            , UpdateService updateService

            
    ) {
        this.mainFrameWorkController = mainFrameWorkController;
        this.loginController = loginController;
        this.ventaController = ventaController;
        this.pagosController = pagosController;
        this.inventarioController = inventarioController;
        this.fiadoController = fiadoController;
        
        this.cuentaService = cuentaService;
        
       this.updateService = updateService;
       
    }
    
   public void initConfig(){
        
       mainFrameWorkController.initConf();
       loginController.initConfig();
       ventaController.initConfig();
       pagosController.initConfig();
       inventarioController.initConfig();
       fiadoController.initConfig();
      
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
        
       pagosController.setOnFiadoActivado(()->{
       
           fiadoController.showFormFiado();
               
               });
       
       fiadoController.setOnFiadoFinalizado(()->{
           
           ventaController.eliminarCuenta();
       mainFrameWorkController.mostrarVista("ventas");
       
       });
       
       fiadoController.setOnFiadoCancelado(()->{
       
           fiadoController.clearCampos();
           fiadoController.habilitarCampos(true);
       
       });
        
        initConfig();
        
    }
       
       
   
    
    
    
    
}
