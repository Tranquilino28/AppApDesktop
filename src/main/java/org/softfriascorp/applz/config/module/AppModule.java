/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.softfriascorp.applz.config.module;

import com.google.inject.AbstractModule;
import com.google.inject.Singleton;

import org.softfriascorp.applz.entity.producto.service.implementation.ProductoServiceImpl;
import org.softfriascorp.applz.entity.producto.service.interfaces.ProductoService;
import org.softfriascorp.applz.inventario_module.views.PInventario;
import org.softfriascorp.applz.login_module.connectoserver.ApiConecttion;
import org.softfriascorp.applz.login_module.controllers.LoginController;
import org.softfriascorp.applz.login_module.services.implementation.LoginServiceImpl;
import org.softfriascorp.applz.login_module.services.interfaces.LoginService;
import org.softfriascorp.applz.login_module.views.LoginPanel;
import org.softfriascorp.applz.login_module.views.PRegister;
import org.softfriascorp.applz.mainframework_module.controllers.MainFrameWorkController;
import org.softfriascorp.applz.mainframework_module.services.implementation.MainFrameWorkServiceImpl;
import org.softfriascorp.applz.mainframework_module.services.interfaces.MainFrameWorkService;
import org.softfriascorp.applz.mainframework_module.views.MainFrameWork;
import org.softfriascorp.applz.mainframework_module.views.PMenuHeader;
import org.softfriascorp.applz.mainframework_module.views.PSliderMenu;
import org.softfriascorp.applz.mainframework_module.views.PSliderContent;
import org.softfriascorp.applz.pay_module.controllers.PagosController;
import org.softfriascorp.applz.pay_module.views.PPagos;
import org.softfriascorp.applz.cuenta_module.controllers.VentaController;
import org.softfriascorp.applz.cuenta_module.submodules.cuenta_module.service.implementation.ServiceCarrito;
import org.softfriascorp.applz.cuenta_module.submodules.cuenta_module.service.interfaces.CuentaService;
import org.softfriascorp.applz.cuenta_module.tablemanager.StockTableManager;
import org.softfriascorp.applz.cuenta_module.tablemanager.VentaTableManager;
import org.softfriascorp.applz.cuenta_module.views.PFacturacion;
import org.softfriascorp.applz.cuenta_module.views.PVenta;
import org.softfriascorp.applz.entity.venta.service.implementation.VentaServiceImpl;
import org.softfriascorp.applz.entity.venta.service.interfaces.VentaService;

/**
 *
 * @author usuario
 */

public class AppModule extends AbstractModule {
  
    @Override
    protected void configure() {
        /**
         * servicio web
         */
        
        bind(ApiConecttion.class).in(Singleton.class);
        
        /**
         * ventana y paneles en singleton  bind(MainFrameWork.class).in(Singleton.class);
         */
        
        bind(MainFrameWork.class).in(Singleton.class);
        bind(PMenuHeader.class).in(Singleton.class);
        bind(PSliderMenu.class).in(Singleton.class);
        
        bind(PSliderContent.class).in(Singleton.class);
        bind(PPagos.class).in(Singleton.class);
        bind(PVenta.class).in(Singleton.class);
        
        bind(PRegister.class).in(Singleton.class);
        bind(PInventario.class).in(Singleton.class);
        bind(LoginPanel.class).in(Singleton.class);
        bind(PFacturacion.class).in(Singleton.class);
        
        
        bind(MainFrameWorkController.class).in(Singleton.class);
        bind(LoginController.class).in(Singleton.class);
        bind(VentaController.class).in(Singleton.class);
        bind(PagosController.class).in(Singleton.class);
        
        
                
        /**
         * servicios 
         */
        
        bind(MainFrameWorkService.class).to(MainFrameWorkServiceImpl.class);
        bind(LoginService.class).to(LoginServiceImpl.class);
        bind(ProductoService.class).to(ProductoServiceImpl.class);
        bind(VentaService.class).to(VentaServiceImpl.class);
        
        
        bind(CuentaService.class).to(ServiceCarrito.class).in(Singleton.class);
        
        bind(VentaTableManager.class).in(Singleton.class);
        bind(StockTableManager.class).in(Singleton.class);
    }
      
}
