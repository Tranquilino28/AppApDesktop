/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package org.softfriascorp.applz;

import com.google.inject.Guice;
import com.google.inject.Injector;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import javax.swing.JOptionPane;
import org.json.JSONObject;
import org.softfriascorp.applz.config.AppControllers;
import org.softfriascorp.applz.config.module.AppModule;
import org.softfriascorp.applz.update.UpdateCheker;
import org.softfriascorp.applz.update.UpdateCheker.VersionComparator;
import org.softfriascorp.applz.update.UpdateRunner;
import org.softfriascorp.applz.update.Updater;
import org.softfriascorp.applz.update.VersionUtil;

/**
 *
 * @author usuario
 */
public class APPLZ {

    public static void main(String[] args) {
/*
        String localVersion = VersionUtil.getLocalVersion();
        JSONObject serverData = UpdateCheker.getServerVersion();

        if (serverData != null) {
            String serverVersion = serverData.getString("version");
            String downloadUrl = serverData.getString("url");

            if (VersionComparator.isNewerVersion(localVersion, serverVersion)) {
               // System.out.println("Nueva versión disponible: " + serverVersion);
                try {
                    Updater.downloadUpdate(downloadUrl, ".");
                    //System.out.println("Actualización descargada. Reiniciando...");
                    UpdateRunner.replaceAndRestart();
                    
                    JOptionPane.showMessageDialog(null, 
    "Hay una nueva versión disponible. Se actualizará al reiniciar la aplicación.");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else {
                System.out.println("Tu aplicación está actualizada.");
            }

        }*/
        Injector inject = Guice.createInjector(new AppModule());

        AppControllers appControllers = inject.getInstance(AppControllers.class);
        appControllers.initContex();
        /*
         MainFrameWorkController mainFrameController  = inject.getInstance(MainFrameWorkController.class);
          
         LoginController loginController  = inject.getInstance(LoginController.class);
         VentaController ventaController = inject.getInstance(VentaController.class);
         PagosController pagosController = inject.getInstance(PagosController.class);
         
         
         loginController.initConfig();
         ventaController.initConfig();
         pagosController.initConfig();
         
         
         
         mainFrameController.initConf();
         */
        /**
         * creamos las instacnicas de las interfaces de los servicios locales
         * para la inyeccion de dependencias de guice *
         *
         * //
         *
         * Controller_Inventario controlller_inventario =
         * inject.getInstance(Controller_Inventario.class);
         *
         * Controller_SeguimientoVentas controlller_seguimientoVentas =
         * inject.getInstance(Controller_SeguimientoVentas.class);
         *
         *
         *
         *
         *
         *
         * //prepara el repositorio CRUD de la base de datos
         * Repository<Usuario, String> repoUsuario = new ImplRepo_Usuario();
         *
         * //prepara el servicio para hacer uso del crud atravez del
         * repositorio ImplService_Usuario servUsuario = new
         * ImplService_Usuario(repoUsuario);
         *
         * //preparo la ventana *frame* y las vistas-*panel* Frame_Work
         * ventanaPrincipal = new Frame_Work();
         *
         * PFacturacion facturacion = new PFacturacion();
         *
         * PSlider_Contenedor sliderContenedor = new PSlider_Contenedor(); *
         * PSliderMenu sliderMenu = new PSliderMenu();
         *
         * PMenuHeader menu_de_opciones = new PMenuHeader();
         *
         * PLogin panel_de_login = new PLogin();
         *
         * PRegister panel_de_registro = new PRegister();
         *
         * PVenta panel_de_venta = new PVenta();
         *
         * PInventario panel_de_inventario = new PInventario();
         *
         * PPagos panel_de_pagos = new PPagos();
         *
         * //preparo el slider de menu sliderContenedor.add(sliderMenu);
         *
         *
         * Seguimiento_ventas historialVentas = new Seguimiento_ventas();
         *
         * //prepara el servicio de ventas ServiceCarrito servicio_de_venta =
         * new ServiceCarrito();
         *
         * //se inyectan a los controladores los parametros con los paneles y
         * servicios new Controller_Login(
         *
         * ventanaPrincipal , sliderContenedor , menu_de_opciones ,
         * panel_de_venta , panel_de_login , panel_de_registro , servUsuario );
         *
         * new Controller_Pagos(panel_de_pagos, servicio_de_venta);
         *
         *
         * // VentaService ventaService =
         * inject.getInstance(VentaService.class);
         *
         * new Controller_Venta(
         *
         * ventanaPrincipal , panel_de_venta , panel_de_pagos ,
         * servicio_de_venta // , ventaService );
         *
         *
         * /*
         * controlller_inventario.setInventario(panel_de_inventario);
         * controlller_seguimientoVentas.setHistorialVentas(historialVentas);
         *
         * new Controller_Inventario( panel_de_inventario
         *
         *
         *
         * );
         *
         * //controlador de opciones y menus new
         * Controler_SliderOptionMenu(slider_de_opciones , menu_de_opciones ,
         * slider , panel_de_venta , ánel_de_inventario ); new
         * Controller_MenuOptions(menu_de_opciones , slider , slider_de_opciones
         * ); new Controller_Registro(ventanaPrincipal , panel_de_registro ,
         * panel_de_login , servUsuario ); *
         *
         * new Controlador_de_Vistas( ventanaPrincipal , sliderContenedor ,
         * sliderMenu , menu_de_opciones , panel_de_venta , panel_de_inventario
         * , historialVentas , panel_de_login , panel_de_registro ,
         * panel_de_pagos , facturacion );
         *
         * //fw.setUndecorated(true);
         *
         * ventanaPrincipal.setSize(600, 400);
         * ventanaPrincipal.fw_Container.add(panel_de_login);
         * ventanaPrincipal.setLocationRelativeTo(null);
         * ventanaPrincipal.setVisible(true);
         *
         *
         *
         * /**
         * creamos las instacnicas de las interfaces de los servicios locales
         * para la inyeccion de dependencias de guice *
         *
         * //servicios de ventas Injector inject = Guice.createInjector(new
         * AppModule());
         *
         * Controller_Inventario controlller_inventario =
         * inject.getInstance(Controller_Inventario.class);
         *
         * Controller_SeguimientoVentas controlller_seguimientoVentas =
         * inject.getInstance(Controller_SeguimientoVentas.class);
         *
         *
         *
         *
         *
         *
         * //prepara el repositorio CRUD de la base de datos
         * Repository<Usuario, String> repoUsuario = new ImplRepo_Usuario();
         *
         * //prepara el servicio para hacer uso del crud atravez del
         * repositorio ImplService_Usuario servUsuario = new
         * ImplService_Usuario(repoUsuario);
         *
         * //preparo la ventana *frame* y las vistas-*panel* Frame_Work
         * ventanaPrincipal = new Frame_Work();
         *
         * PFacturacion facturacion = new PFacturacion();
         *
         * PSlider_Contenedor sliderContenedor = new PSlider_Contenedor(); *
         * PSliderMenu sliderMenu = new PSliderMenu();
         *
         * PMenuHeader menu_de_opciones = new PMenuHeader();
         *
         * PLogin panel_de_login = new PLogin();
         *
         * PRegister panel_de_registro = new PRegister();
         *
         * PVenta panel_de_venta = new PVenta();
         *
         * PInventario panel_de_inventario = new PInventario();
         *
         * PPagos panel_de_pagos = new PPagos();
         *
         * //preparo el slider de menu sliderContenedor.add(sliderMenu);
         *
         *
         * Seguimiento_ventas historialVentas = new Seguimiento_ventas();
         *
         * //prepara el servicio de ventas ServiceCarrito servicio_de_venta =
         * new ServiceCarrito();
         *
         * //se inyectan a los controladores los parametros con los paneles y
         * servicios new Controller_Login(
         *
         * ventanaPrincipal , sliderContenedor , menu_de_opciones ,
         * panel_de_venta , panel_de_login , panel_de_registro , servUsuario );
         *
         * new Controller_Pagos(panel_de_pagos, servicio_de_venta);
         *
         *
         * // VentaService ventaService =
         * inject.getInstance(VentaService.class);
         *
         * new Controller_Venta(
         *
         * ventanaPrincipal , panel_de_venta , panel_de_pagos ,
         * servicio_de_venta // , ventaService );
         *
         *
         * /*
         * controlller_inventario.setInventario(panel_de_inventario);
         * controlller_seguimientoVentas.setHistorialVentas(historialVentas);
         *
         * new Controller_Inventario( panel_de_inventario
         *
         *
         *
         * );
         *
         * //controlador de opciones y menus new
         * Controler_SliderOptionMenu(slider_de_opciones , menu_de_opciones ,
         * slider , panel_de_venta , ánel_de_inventario ); new
         * Controller_MenuOptions(menu_de_opciones , slider , slider_de_opciones
         * ); new Controller_Registro(ventanaPrincipal , panel_de_registro ,
         * panel_de_login , servUsuario ); *
         *
         * new Controlador_de_Vistas( ventanaPrincipal , sliderContenedor ,
         * sliderMenu , menu_de_opciones , panel_de_venta , panel_de_inventario
         * , historialVentas , panel_de_login , panel_de_registro ,
         * panel_de_pagos , facturacion );
         *
         * //fw.setUndecorated(true);
         *
         * ventanaPrincipal.setSize(600, 400);
         * ventanaPrincipal.fw_Container.add(panel_de_login);
         * ventanaPrincipal.setLocationRelativeTo(null);
         * ventanaPrincipal.setVisible(true); }
         *
         * /**
         * creamos las instacnicas de las interfaces de los servicios locales
         * para la inyeccion de dependencias de guice *
         *
         * //servicios de ventas Injector inject = Guice.createInjector(new
         * AppModule());
         *
         * Controller_Inventario controlller_inventario =
         * inject.getInstance(Controller_Inventario.class);
         *
         * Controller_SeguimientoVentas controlller_seguimientoVentas =
         * inject.getInstance(Controller_SeguimientoVentas.class);
         *
         *
         *
         *
         *
         *
         * //prepara el repositorio CRUD de la base de datos
         * Repository<Usuario, String> repoUsuario = new ImplRepo_Usuario();
         *
         * //prepara el servicio para hacer uso del crud atravez del
         * repositorio ImplService_Usuario servUsuario = new
         * ImplService_Usuario(repoUsuario);
         *
         * //preparo la ventana *frame* y las vistas-*panel* Frame_Work
         * ventanaPrincipal = new Frame_Work();
         *
         * PFacturacion facturacion = new PFacturacion();
         *
         * PSlider_Contenedor sliderContenedor = new PSlider_Contenedor(); *
         * PSliderMenu sliderMenu = new PSliderMenu();
         *
         * PMenuHeader menu_de_opciones = new PMenuHeader();
         *
         * PLogin panel_de_login = new PLogin();
         *
         * PRegister panel_de_registro = new PRegister();
         *
         * PVenta panel_de_venta = new PVenta();
         *
         * PInventario panel_de_inventario = new PInventario();
         *
         * PPagos panel_de_pagos = new PPagos();
         *
         * //preparo el slider de menu sliderContenedor.add(sliderMenu);
         *
         *
         * Seguimiento_ventas historialVentas = new Seguimiento_ventas();
         *
         * //prepara el servicio de ventas ServiceCarrito servicio_de_venta =
         * new ServiceCarrito();
         *
         * //se inyectan a los controladores los parametros con los paneles y
         * servicios new Controller_Login(
         *
         * ventanaPrincipal , sliderContenedor , menu_de_opciones ,
         * panel_de_venta , panel_de_login , panel_de_registro , servUsuario );
         *
         * new Controller_Pagos(panel_de_pagos, servicio_de_venta);
         *
         *
         * // VentaService ventaService =
         * inject.getInstance(VentaService.class);
         *
         * new Controller_Venta(
         *
         * ventanaPrincipal , panel_de_venta , panel_de_pagos ,
         * servicio_de_venta // , ventaService );
         *
         *
         * /*
         * controlller_inventario.setInventario(panel_de_inventario);
         * controlller_seguimientoVentas.setHistorialVentas(historialVentas);
         *
         * new Controller_Inventario( panel_de_inventario
         *
         *
         *
         * );
         *
         * //controlador de opciones y menus new
         * Controler_SliderOptionMenu(slider_de_opciones , menu_de_opciones ,
         * slider , panel_de_venta , ánel_de_inventario ); new
         * Controller_MenuOptions(menu_de_opciones , slider , slider_de_opciones
         * ); new Controller_Registro(ventanaPrincipal , panel_de_registro ,
         * panel_de_login , servUsuario ); *
         *
         * new Controlador_de_Vistas( ventanaPrincipal , sliderContenedor ,
         * sliderMenu , menu_de_opciones , panel_de_venta , panel_de_inventario
         * , historialVentas , panel_de_login , panel_de_registro ,
         * panel_de_pagos , facturacion );
         *
         * //fw.setUndecorated(true);
         *
         * ventanaPrincipal.setSize(600, 400);
         * ventanaPrincipal.fw_Container.add(panel_de_login);
         * ventanaPrincipal.setLocationRelativeTo(null);
         * ventanaPrincipal.setVisible(true);
         *
         *
         *
         * /**
         * creamos las instacnicas de las interfaces de los servicios locales
         * para la inyeccion de dependencias de guice *
         *
         * //servicios de ventas Injector inject = Guice.createInjector(new
         * AppModule());
         *
         * Controller_Inventario controlller_inventario =
         * inject.getInstance(Controller_Inventario.class);
         *
         * Controller_SeguimientoVentas controlller_seguimientoVentas =
         * inject.getInstance(Controller_SeguimientoVentas.class);
         *
         *
         *
         *
         *
         *
         * //prepara el repositorio CRUD de la base de datos
         * Repository<Usuario, String> repoUsuario = new ImplRepo_Usuario();
         *
         * //prepara el servicio para hacer uso del crud atravez del
         * repositorio ImplService_Usuario servUsuario = new
         * ImplService_Usuario(repoUsuario);
         *
         * //preparo la ventana *frame* y las vistas-*panel* Frame_Work
         * ventanaPrincipal = new Frame_Work();
         *
         * PFacturacion facturacion = new PFacturacion();
         *
         * PSlider_Contenedor sliderContenedor = new PSlider_Contenedor(); *
         * PSliderMenu sliderMenu = new PSliderMenu();
         *
         * PMenuHeader menu_de_opciones = new PMenuHeader();
         *
         * PLogin panel_de_login = new PLogin();
         *
         * PRegister panel_de_registro = new PRegister();
         *
         * PVenta panel_de_venta = new PVenta();
         *
         * PInventario panel_de_inventario = new PInventario();
         *
         * PPagos panel_de_pagos = new PPagos();
         *
         * //preparo el slider de menu sliderContenedor.add(sliderMenu);
         *
         *
         * Seguimiento_ventas historialVentas = new Seguimiento_ventas();
         *
         * //prepara el servicio de ventas ServiceCarrito servicio_de_venta =
         * new ServiceCarrito();
         *
         * //se inyectan a los controladores los parametros con los paneles y
         * servicios new Controller_Login(
         *
         * ventanaPrincipal , sliderContenedor , menu_de_opciones ,
         * panel_de_venta , panel_de_login , panel_de_registro , servUsuario );
         *
         * new Controller_Pagos(panel_de_pagos, servicio_de_venta);
         *
         *
         * // VentaService ventaService =
         * inject.getInstance(VentaService.class);
         *
         * new Controller_Venta(
         *
         * ventanaPrincipal , panel_de_venta , panel_de_pagos ,
         * servicio_de_venta // , ventaService );
         *
         *
         * /*
         * controlller_inventario.setInventario(panel_de_inventario);
         * controlller_seguimientoVentas.setHistorialVentas(historialVentas);
         *
         * new Controller_Inventario( panel_de_inventario
         *
         *
         *
         * );
         *
         * //controlador de opciones y menus new
         * Controler_SliderOptionMenu(slider_de_opciones , menu_de_opciones ,
         * slider , panel_de_venta , ánel_de_inventario ); new
         * Controller_MenuOptions(menu_de_opciones , slider , slider_de_opciones
         * ); new Controller_Registro(ventanaPrincipal , panel_de_registro ,
         * panel_de_login , servUsuario ); *
         *
         * new Controlador_de_Vistas( ventanaPrincipal , sliderContenedor ,
         * sliderMenu , menu_de_opciones , panel_de_venta , panel_de_inventario
         * , historialVentas , panel_de_login , panel_de_registro ,
         * panel_de_pagos , facturacion );
         *
         * //fw.setUndecorated(true);
         *
         * ventanaPrincipal.setSize(600, 400);
         * ventanaPrincipal.fw_Container.add(panel_de_login);
         * ventanaPrincipal.setLocationRelativeTo(null);
         * ventanaPrincipal.setVisible(true); }
         *
         * /**
         * creamos las instacnicas de las interfaces de los servicios locales
         * para la inyeccion de dependencias de guice *
         *
         * //servicios de ventas Injector inject = Guice.createInjector(new
         * AppModule());
         *
         * Controller_Inventario controlller_inventario =
         * inject.getInstance(Controller_Inventario.class);
         *
         * Controller_SeguimientoVentas controlller_seguimientoVentas =
         * inject.getInstance(Controller_SeguimientoVentas.class);
         *
         *
         *
         *
         *
         *
         * //prepara el repositorio CRUD de la base de datos
         * Repository<Usuario, String> repoUsuario = new ImplRepo_Usuario();
         *
         * //prepara el servicio para hacer uso del crud atravez del
         * repositorio ImplService_Usuario servUsuario = new
         * ImplService_Usuario(repoUsuario);
         *
         * //preparo la ventana *frame* y las vistas-*panel* Frame_Work
         * ventanaPrincipal = new Frame_Work();
         *
         * PFacturacion facturacion = new PFacturacion();
         *
         * PSlider_Contenedor sliderContenedor = new PSlider_Contenedor(); *
         * PSliderMenu sliderMenu = new PSliderMenu();
         *
         * PMenuHeader menu_de_opciones = new PMenuHeader();
         *
         * PLogin panel_de_login = new PLogin();
         *
         * PRegister panel_de_registro = new PRegister();
         *
         * PVenta panel_de_venta = new PVenta();
         *
         * PInventario panel_de_inventario = new PInventario();
         *
         * PPagos panel_de_pagos = new PPagos();
         *
         * //preparo el slider de menu sliderContenedor.add(sliderMenu);
         *
         *
         * Seguimiento_ventas historialVentas = new Seguimiento_ventas();
         *
         * //prepara el servicio de ventas ServiceCarrito servicio_de_venta =
         * new ServiceCarrito();
         *
         * //se inyectan a los controladores los parametros con los paneles y
         * servicios new Controller_Login(
         *
         * ventanaPrincipal , sliderContenedor , menu_de_opciones ,
         * panel_de_venta , panel_de_login , panel_de_registro , servUsuario );
         *
         * new Controller_Pagos(panel_de_pagos, servicio_de_venta);
         *
         *
         * // VentaService ventaService =
         * inject.getInstance(VentaService.class);
         *
         * new Controller_Venta(
         *
         * ventanaPrincipal , panel_de_venta , panel_de_pagos ,
         * servicio_de_venta // , ventaService );
         *
         *
         * /*
         * controlller_inventario.setInventario(panel_de_inventario);
         * controlller_seguimientoVentas.setHistorialVentas(historialVentas);
         *
         * new Controller_Inventario( panel_de_inventario
         *
         *
         *
         * );
         *
         * //controlador de opciones y menus new
         * Controler_SliderOptionMenu(slider_de_opciones , menu_de_opciones ,
         * slider , panel_de_venta , ánel_de_inventario ); new
         * Controller_MenuOptions(menu_de_opciones , slider , slider_de_opciones
         * ); new Controller_Registro(ventanaPrincipal , panel_de_registro ,
         * panel_de_login , servUsuario ); *
         *
         * new Controlador_de_Vistas( ventanaPrincipal , sliderContenedor ,
         * sliderMenu , menu_de_opciones , panel_de_venta , panel_de_inventario
         * , historialVentas , panel_de_login , panel_de_registro ,
         * panel_de_pagos , facturacion );
         *
         * //fw.setUndecorated(true);
         *
         * ventanaPrincipal.setSize(600, 400);
         * ventanaPrincipal.fw_Container.add(panel_de_login);
         * ventanaPrincipal.setLocationRelativeTo(null);
         * ventanaPrincipal.setVisible(true);
         *
         *
         *
         * /**
         * creamos las instacnicas de las interfaces de los servicios locales
         * para la inyeccion de dependencias de guice *
         *
         * //servicios de ventas Injector inject = Guice.createInjector(new
         * AppModule());
         *
         * Controller_Inventario controlller_inventario =
         * inject.getInstance(Controller_Inventario.class);
         *
         * Controller_SeguimientoVentas controlller_seguimientoVentas =
         * inject.getInstance(Controller_SeguimientoVentas.class);
         *
         *
         *
         *
         *
         *
         * //prepara el repositorio CRUD de la base de datos
         * Repository<Usuario, String> repoUsuario = new ImplRepo_Usuario();
         *
         * //prepara el servicio para hacer uso del crud atravez del
         * repositorio ImplService_Usuario servUsuario = new
         * ImplService_Usuario(repoUsuario);
         *
         * //preparo la ventana *frame* y las vistas-*panel* Frame_Work
         * ventanaPrincipal = new Frame_Work();
         *
         * PFacturacion facturacion = new PFacturacion();
         *
         * PSlider_Contenedor sliderContenedor = new PSlider_Contenedor(); *
         * PSliderMenu sliderMenu = new PSliderMenu();
         *
         * PMenuHeader menu_de_opciones = new PMenuHeader();
         *
         * PLogin panel_de_login = new PLogin();
         *
         * PRegister panel_de_registro = new PRegister();
         *
         * PVenta panel_de_venta = new PVenta();
         *
         * PInventario panel_de_inventario = new PInventario();
         *
         * PPagos panel_de_pagos = new PPagos();
         *
         * //preparo el slider de menu sliderContenedor.add(sliderMenu);
         *
         *
         * Seguimiento_ventas historialVentas = new Seguimiento_ventas();
         *
         * //prepara el servicio de ventas ServiceCarrito servicio_de_venta =
         * new ServiceCarrito();
         *
         * //se inyectan a los controladores los parametros con los paneles y
         * servicios new Controller_Login(
         *
         * ventanaPrincipal , sliderContenedor , menu_de_opciones ,
         * panel_de_venta , panel_de_login , panel_de_registro , servUsuario );
         *
         * new Controller_Pagos(panel_de_pagos, servicio_de_venta);
         *
         *
         * // VentaService ventaService =
         * inject.getInstance(VentaService.class);
         *
         * new Controller_Venta(
         *
         * ventanaPrincipal , panel_de_venta , panel_de_pagos ,
         * servicio_de_venta // , ventaService );
         *
         *
         * /*
         * controlller_inventario.setInventario(panel_de_inventario);
         * controlller_seguimientoVentas.setHistorialVentas(historialVentas);
         *
         * new Controller_Inventario( panel_de_inventario
         *
         *
         *
         * );
         *
         * //controlador de opciones y menus new
         * Controler_SliderOptionMenu(slider_de_opciones , menu_de_opciones ,
         * slider , panel_de_venta , ánel_de_inventario ); new
         * Controller_MenuOptions(menu_de_opciones , slider , slider_de_opciones
         * ); new Controller_Registro(ventanaPrincipal , panel_de_registro ,
         * panel_de_login , servUsuario ); *
         *
         * new Controlador_de_Vistas( ventanaPrincipal , sliderContenedor ,
         * sliderMenu , menu_de_opciones , panel_de_venta , panel_de_inventario
         * , historialVentas , panel_de_login , panel_de_registro ,
         * panel_de_pagos , facturacion );
         *
         * //fw.setUndecorated(true);
         *
         * ventanaPrincipal.setSize(600, 400);
         * ventanaPrincipal.fw_Container.add(panel_de_login);
         * ventanaPrincipal.setLocationRelativeTo(null);
         * ventanaPrincipal.setVisible(true); }
         *
         * /**
         * creamos las instacnicas de las interfaces de los servicios locales
         * para la inyeccion de dependencias de guice *
         *
         * //servicios de ventas Injector inject = Guice.createInjector(new
         * AppModule());
         *
         * Controller_Inventario controlller_inventario =
         * inject.getInstance(Controller_Inventario.class);
         *
         * Controller_SeguimientoVentas controlller_seguimientoVentas =
         * inject.getInstance(Controller_SeguimientoVentas.class);
         *
         *
         *
         *
         *
         *
         * //prepara el repositorio CRUD de la base de datos
         * Repository<Usuario, String> repoUsuario = new ImplRepo_Usuario();
         *
         * //prepara el servicio para hacer uso del crud atravez del
         * repositorio ImplService_Usuario servUsuario = new
         * ImplService_Usuario(repoUsuario);
         *
         * //preparo la ventana *frame* y las vistas-*panel* Frame_Work
         * ventanaPrincipal = new Frame_Work();
         *
         * PFacturacion facturacion = new PFacturacion();
         *
         * PSlider_Contenedor sliderContenedor = new PSlider_Contenedor(); *
         * PSliderMenu sliderMenu = new PSliderMenu();
         *
         * PMenuHeader menu_de_opciones = new PMenuHeader();
         *
         * PLogin panel_de_login = new PLogin();
         *
         * PRegister panel_de_registro = new PRegister();
         *
         * PVenta panel_de_venta = new PVenta();
         *
         * PInventario panel_de_inventario = new PInventario();
         *
         * PPagos panel_de_pagos = new PPagos();
         *
         * //preparo el slider de menu sliderContenedor.add(sliderMenu);
         *
         *
         * Seguimiento_ventas historialVentas = new Seguimiento_ventas();
         *
         * //prepara el servicio de ventas ServiceCarrito servicio_de_venta =
         * new ServiceCarrito();
         *
         * //se inyectan a los controladores los parametros con los paneles y
         * servicios new Controller_Login(
         *
         * ventanaPrincipal , sliderContenedor , menu_de_opciones ,
         * panel_de_venta , panel_de_login , panel_de_registro , servUsuario );
         *
         * new Controller_Pagos(panel_de_pagos, servicio_de_venta);
         *
         *
         * // VentaService ventaService =
         * inject.getInstance(VentaService.class);
         *
         * new Controller_Venta(
         *
         * ventanaPrincipal , panel_de_venta , panel_de_pagos ,
         * servicio_de_venta // , ventaService );
         *
         *
         * /*
         * controlller_inventario.setInventario(panel_de_inventario);
         * controlller_seguimientoVentas.setHistorialVentas(historialVentas);
         *
         * new Controller_Inventario( panel_de_inventario
         *
         *
         *
         * );
         *
         * //controlador de opciones y menus new
         * Controler_SliderOptionMenu(slider_de_opciones , menu_de_opciones ,
         * slider , panel_de_venta , ánel_de_inventario ); new
         * Controller_MenuOptions(menu_de_opciones , slider , slider_de_opciones
         * ); new Controller_Registro(ventanaPrincipal , panel_de_registro ,
         * panel_de_login , servUsuario ); *
         *
         * new Controlador_de_Vistas( ventanaPrincipal , sliderContenedor ,
         * sliderMenu , menu_de_opciones , panel_de_venta , panel_de_inventario
         * , historialVentas , panel_de_login , panel_de_registro ,
         * panel_de_pagos , facturacion );
         *
         * //fw.setUndecorated(true);
         *
         * ventanaPrincipal.setSize(600, 400);
         * ventanaPrincipal.fw_Container.add(panel_de_login);
         * ventanaPrincipal.setLocationRelativeTo(null);
         * ventanaPrincipal.setVisible(true);
         *
         *
         *
         * /**
         * creamos las instacnicas de las interfaces de los servicios locales
         * para la inyeccion de dependencias de guice *
         *
         * //servicios de ventas Injector inject = Guice.createInjector(new
         * AppModule());
         *
         * Controller_Inventario controlller_inventario =
         * inject.getInstance(Controller_Inventario.class);
         *
         * Controller_SeguimientoVentas controlller_seguimientoVentas =
         * inject.getInstance(Controller_SeguimientoVentas.class);
         *
         *
         *
         *
         *
         *
         * //prepara el repositorio CRUD de la base de datos
         * Repository<Usuario, String> repoUsuario = new ImplRepo_Usuario();
         *
         * //prepara el servicio para hacer uso del crud atravez del
         * repositorio ImplService_Usuario servUsuario = new
         * ImplService_Usuario(repoUsuario);
         *
         * //preparo la ventana *frame* y las vistas-*panel* Frame_Work
         * ventanaPrincipal = new Frame_Work();
         *
         * PFacturacion facturacion = new PFacturacion();
         *
         * PSlider_Contenedor sliderContenedor = new PSlider_Contenedor(); *
         * PSliderMenu sliderMenu = new PSliderMenu();
         *
         * PMenuHeader menu_de_opciones = new PMenuHeader();
         *
         * PLogin panel_de_login = new PLogin();
         *
         * PRegister panel_de_registro = new PRegister();
         *
         * PVenta panel_de_venta = new PVenta();
         *
         * PInventario panel_de_inventario = new PInventario();
         *
         * PPagos panel_de_pagos = new PPagos();
         *
         * //preparo el slider de menu sliderContenedor.add(sliderMenu);
         *
         *
         * Seguimiento_ventas historialVentas = new Seguimiento_ventas();
         *
         * //prepara el servicio de ventas ServiceCarrito servicio_de_venta =
         * new ServiceCarrito();
         *
         * //se inyectan a los controladores los parametros con los paneles y
         * servicios new Controller_Login(
         *
         * ventanaPrincipal , sliderContenedor , menu_de_opciones ,
         * panel_de_venta , panel_de_login , panel_de_registro , servUsuario );
         *
         * new Controller_Pagos(panel_de_pagos, servicio_de_venta);
         *
         *
         * // VentaService ventaService =
         * inject.getInstance(VentaService.class);
         *
         * new Controller_Venta(
         *
         * ventanaPrincipal , panel_de_venta , panel_de_pagos ,
         * servicio_de_venta // , ventaService );
         *
         *
         * /*
         * controlller_inventario.setInventario(panel_de_inventario);
         * controlller_seguimientoVentas.setHistorialVentas(historialVentas);
         *
         * new Controller_Inventario( panel_de_inventario
         *
         *
         *
         * );
         *
         * //controlador de opciones y menus new
         * Controler_SliderOptionMenu(slider_de_opciones , menu_de_opciones ,
         * slider , panel_de_venta , ánel_de_inventario ); new
         * Controller_MenuOptions(menu_de_opciones , slider , slider_de_opciones
         * ); new Controller_Registro(ventanaPrincipal , panel_de_registro ,
         * panel_de_login , servUsuario ); *
         *
         * new Controlador_de_Vistas( ventanaPrincipal , sliderContenedor ,
         * sliderMenu , menu_de_opciones , panel_de_venta , panel_de_inventario
         * , historialVentas , panel_de_login , panel_de_registro ,
         * panel_de_pagos , facturacion );
         *
         * //fw.setUndecorated(true);
         *
         * ventanaPrincipal.setSize(600, 400);
         * ventanaPrincipal.fw_Container.add(panel_de_login);
         * ventanaPrincipal.setLocationRelativeTo(null);
         * ventanaPrincipal.setVisible(true); }
         */
    }

}
