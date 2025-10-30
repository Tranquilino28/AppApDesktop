/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.softfriascorp.applz.config;

import com.google.inject.Inject;
import java.util.ArrayList;
import java.util.List;
import org.softfriascorp.applz.entity.detallesventa.DetallesVenta;
import org.softfriascorp.applz.login_module.controllers.LoginController;
import org.softfriascorp.applz.mainframework_module.controllers.MainFrameWorkController;
import org.softfriascorp.applz.pay_module.controllers.PagosController;
import org.softfriascorp.applz.cuenta_module.controllers.VentaController;
import org.softfriascorp.applz.cuenta_module.submodules.cuenta_module.service.interfaces.CuentaService;
import org.softfriascorp.applz.update.UpdateWorker;

/**
 *
 * @author usuario
 */
public class AppControllers {
    
    
    MainFrameWorkController mainFrameWorkController;
    LoginController loginController;
    VentaController ventaController;
    PagosController pagosController;
    CuentaService cuentaService;
    
    
    @Inject
    public AppControllers(MainFrameWorkController mainFrameWorkController
            ,LoginController loginController
            , VentaController ventaController
            , PagosController pagosController
            
            , CuentaService cuentaService
    ) {
        this.mainFrameWorkController = mainFrameWorkController;
        this.loginController = loginController;
        this.ventaController = ventaController;
        this.pagosController = pagosController;
        
        this.cuentaService = cuentaService;
        
        
    }
    
   public void initConfig(){
        
       mainFrameWorkController.initConf();
       loginController.initConfig();
       ventaController.initConfig();
       pagosController.initConfig();
       
      
    }
   
   
   public void initContex(){
       
        
        ventaController.setOnPagoIniciado(() -> {
            System.out.println("se inicio el pago");
           
            mainFrameWorkController.mostrarVista("pagos");
            

             pagosController.ShowDetailCuenta();
            
        });

        pagosController.setOnPagoFinalizado(() -> {
            
            System.out.println("finalizo el pago");
            
            ventaController.eliminarCuenta();
            
            mainFrameWorkController.mostrarVista("ventas");
            
        });
        
        pagosController.setOnCancelarPago(() -> {
            
            System.out.println("canceando pago");            
            
            mainFrameWorkController.mostrarVista("ventas");
        });
        
        initConfig();
        
    }
       
       
   
    
    
    
    
}
