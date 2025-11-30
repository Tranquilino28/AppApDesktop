/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.softfriascorp.applz.config.module;

import com.google.inject.AbstractModule;
import com.google.inject.Singleton;

import org.softfriascorp.applz.entity.producto.service.implementation.ProductoServiceImpl;
import org.softfriascorp.applz.entity.producto.service.interfaces.ProductoService;
import org.softfriascorp.applz.modules.inventario.views.PInventario;
import org.softfriascorp.applz.modules.login.connectoserver.ApiConecttion;
import org.softfriascorp.applz.modules.login.controllers.LoginController;
import org.softfriascorp.applz.modules.login.services.implementation.LoginServiceImpl;
import org.softfriascorp.applz.modules.login.services.interfaces.LoginService;
import org.softfriascorp.applz.modules.login.views.LoginPanel;
import org.softfriascorp.applz.modules.login.views.PRegister;
import org.softfriascorp.applz.frameview_manager.controllers.MainFrameWorkController;
import org.softfriascorp.applz.frameview_manager.services.implementation.MainFrameWorkServiceImpl;
import org.softfriascorp.applz.frameview_manager.services.interfaces.MainFrameWorkService;
import org.softfriascorp.applz.frameview_manager.views.MainFrameWork;
import org.softfriascorp.applz.frameview_manager.views.PMenuHeader;
import org.softfriascorp.applz.frameview_manager.views.PSliderMenu;
import org.softfriascorp.applz.frameview_manager.views.PSliderContent;
import org.softfriascorp.applz.modules.pay.controllers.PagosController;
import org.softfriascorp.applz.modules.pay.views.PPagos;
import org.softfriascorp.applz.modules.venta.controllers.VentaController;
import org.softfriascorp.applz.modules.cuenta.service.implementation.ServiceCarrito;
import org.softfriascorp.applz.modules.cuenta.service.interfaces.CuentaService;
import org.softfriascorp.applz.modules.venta.tablemanager.StockTableManager;
import org.softfriascorp.applz.modules.venta.tablemanager.VentaTableManager;
import org.softfriascorp.applz.modules.venta.views.PSearchVenta;
import org.softfriascorp.applz.modules.venta.views.PVenta;
import org.softfriascorp.applz.entity.maestra.service.implemnetation.MaestraServiceImpl;
import org.softfriascorp.applz.entity.maestra.service.interfaces.MaestraService;
import org.softfriascorp.applz.entity.persona.implementation.PersonaServiceImpl;
import org.softfriascorp.applz.entity.persona.interfaces.PersonaService;
import org.softfriascorp.applz.entity.venta.service.implementation.VentaServiceImpl;
import org.softfriascorp.applz.entity.venta.service.interfaces.VentaService;
import org.softfriascorp.applz.modules.cuenta.service.implementation.ConversorDeMedidaImpl;
import org.softfriascorp.applz.modules.cuenta.service.implementation.PriceCalculatorImple;
import org.softfriascorp.applz.modules.cuenta.service.interfaces.ConversorDeMedida;
import org.softfriascorp.applz.modules.cuenta.service.interfaces.PriceCalculator;
import org.softfriascorp.applz.modules.inventario.controllers.FormAddProductosController;
import org.softfriascorp.applz.modules.inventario.controllers.InventarioController;
import org.softfriascorp.applz.modules.inventario.tablemanager.InventarioTableManager;
import org.softfriascorp.applz.modules.inventario.views.FormAddProductos;
import org.softfriascorp.applz.modules.pay.controllers.ClienteValidatorClontroller;
import org.softfriascorp.applz.modules.pay.views.ClienteValidator;
import org.softfriascorp.applz.update.AppUpdater;
import org.softfriascorp.applz.update.UpdateChecker;
import org.softfriascorp.applz.update.UpdateDownloader;
import org.softfriascorp.applz.update.UpdateService;
import org.springframework.core.convert.ConversionService;

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
         * ventana y paneles en singleton
         * bind(MainFrameWork.class).in(Singleton.class);
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
        bind(PSearchVenta.class).in(Singleton.class);
        bind(ClienteValidator.class).in(Singleton.class);

        bind(MainFrameWorkController.class).in(Singleton.class);
        bind(LoginController.class).in(Singleton.class);
        bind(VentaController.class).in(Singleton.class);
        bind(PagosController.class).in(Singleton.class);
        bind(ClienteValidatorClontroller.class).in(Singleton.class);
        
        bind(FormAddProductosController.class).in(Singleton.class);
        
        /**
         * servicios
         */
        bind(MainFrameWorkService.class).to(MainFrameWorkServiceImpl.class);
        bind(LoginService.class).to(LoginServiceImpl.class);
        bind(ProductoService.class).to(ProductoServiceImpl.class);
        bind(VentaService.class).to(VentaServiceImpl.class);
        bind(PersonaService.class).to(PersonaServiceImpl.class);
        bind(CuentaService.class).to(ServiceCarrito.class).in(Singleton.class);

        bind(VentaTableManager.class).in(Singleton.class);
        bind(StockTableManager.class).in(Singleton.class);

        // https://github.com/Tranquilino28/AppApDesktop
        bind(UpdateChecker.class).toInstance(new UpdateChecker("Tranquilino28", "AppApDesktop", "1.0.0"));
        bind(UpdateDownloader.class).asEagerSingleton();
        bind(AppUpdater.class).asEagerSingleton();
        bind(UpdateService.class).asEagerSingleton();

        bind(InventarioController.class).in(Singleton.class);
        bind(InventarioTableManager.class).in(Singleton.class);

        bind(MaestraService.class).to(MaestraServiceImpl.class);
        
        bind(FormAddProductos.class).in(Singleton.class);
     
        
        bind(PriceCalculator.class).to(PriceCalculatorImple.class);
        
        bind(ConversorDeMedida.class).to(ConversorDeMedidaImpl.class);
    }

}
