/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.softfriascorp.applz.config;

import com.google.inject.Inject;
import org.softfriascorp.applz.modules.login.controllers.LoginController;
import org.softfriascorp.applz.frameview_manager.controllers.MainFrameWorkController;
import org.softfriascorp.applz.modules.pay.controllers.PagosController;
import org.softfriascorp.applz.modules.venta.controllers.VentaController;
import org.softfriascorp.applz.modules.cuenta.service.interfaces.CuentaService;
import org.softfriascorp.applz.modules.inventario.controllers.InventarioController;
import org.softfriascorp.applz.modules.pay.controllers.ClienteValidatorClontroller;
import org.softfriascorp.applz.modules.update.AutoUpdateManager;



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
    private final ClienteValidatorClontroller fiadoController;
    
    private final CuentaService cuentaService;

  
    @Inject
    public AppControllers(
            MainFrameWorkController mainFrameWorkController
            ,LoginController loginController
            , VentaController ventaController
            , PagosController pagosController
            , InventarioController inventarioController
            , ClienteValidatorClontroller fiadoController
            
            , CuentaService cuentaService

            
    ) {
        this.mainFrameWorkController = mainFrameWorkController;
        this.loginController = loginController;
        this.ventaController = ventaController;
        this.pagosController = pagosController;
        this.inventarioController = inventarioController;
        this.fiadoController = fiadoController;
        
        this.cuentaService = cuentaService;
       
    }
    
   private void initViewControllers(){
        ventaController.initConfig();
       pagosController.initConfig();
       inventarioController.initConfig();
       fiadoController.initConfig();
       
    }
   
   public void initAppController() {
        mainFrameWorkController.initConf();
        loginController.initConfig();
       
        System.out.println("se iniciara la busqueda de actualizaciones automaticas");
       AutoUpdateManager.checkForUpdates();
       
      // updateService.checkForUpdatesAsync();
      initContex();
    }
       
   
   private  void initContex(){
       /**
        * listener de login 
        */
       loginController.setOnLoginExitoso(() ->{
           System.out.println("se habrira el marco de trabajo en POS");
       mainFrameWorkController.mostrarVista("ventas");
       
       initViewControllers();
       
       
       
       
       });
       
      
       
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
       
           fiadoController.showFormFiado("FIADO");
               
               });
       
       pagosController.setOnEfectivoActivado(()->{
       
           fiadoController.showFormFiado("EFECTIVO");
               
               });
       
       fiadoController.setOnFiadoFinalizado(()->{
           
           ventaController.eliminarCuenta();
       mainFrameWorkController.mostrarVista("ventas");
       
       });
       
       fiadoController.setOnFiadoCancelado(()->{
       
           fiadoController.clearCampos();
           fiadoController.habilitarCampos(true);
       
       });
       
    }
}
