/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.softfriascorp.applz.mainframework_module.controllers;

import jakarta.inject.Inject;
import java.awt.Dimension;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import org.softfriascorp.applz.modules.inventario_module.views.PInventario;
import org.softfriascorp.applz.modules.login_module.views.LoginPanel;
import org.softfriascorp.applz.modules.login_module.views.PRegister;
import org.softfriascorp.applz.mainframework_module.services.interfaces.MainFrameWorkService;
import org.softfriascorp.applz.mainframework_module.util.viewController;
import org.softfriascorp.applz.mainframework_module.views.MainFrameWork;
import org.softfriascorp.applz.mainframework_module.views.PMenuHeader;
import org.softfriascorp.applz.mainframework_module.views.PSliderMenu;
import org.softfriascorp.applz.mainframework_module.views.PSliderContent;
import org.softfriascorp.applz.modules.pay_module.views.PPagos;
import org.softfriascorp.applz.modules.venta_module.views.PFacturacion;
import org.softfriascorp.applz.modules.venta_module.views.PVenta;

/**
 *
 * @author usuario
 */
public class MainFrameWorkController implements ActionListener, MouseListener, KeyListener, ComponentListener {

    private final String URL_LOGO001 = "/images/001.png";
    private Image imagenOriginalCache;

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

    private Runnable viewVentas;

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
    //   , UpdateListener update

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

        // this.update = update;
    }

    public void initConf() {
        ventanaPrincipal.setSize(840, 510);
        //activarPantallaCompleta();

        //ajustarATamañoPantalla();
        viewController.nextView(ventanaPrincipal.fw_Container, loginPanel);

        menuHeaderPanel.btn_menu.setSelected(true);

        ventanaPrincipal.setLocationRelativeTo(null);
        ventanaPrincipal.setVisible(true);

        initListener();

        // Ejecutar el actualizador en segundo plano
        // UpdateWorker worker = new UpdateWorker( menuHeaderPanel.lbl_updateMessage);
        // worker.execute(); // ? inicia el hilo SwingWorker
        //maximizarVentana();
        cargarImagenOriginal();

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

        this.sliderMenuOptions.btn_salir.addActionListener(this);

        this.loginPanel.lbl_logo001.addComponentListener(this);

    }

    @Override
    public void actionPerformed(ActionEvent e) {

        /**
         * botones de la vista login
         *
         */
        if (e.getSource() == loginPanel.btn_registrarse) {

        }

        /**
         * botones del Header Menu
         *
         * menu -> si el boton del menu esta seleccionado que muestre el
         * slidermenu ccaso contrario que lo oculte
         */
        if (e.getSource() == menuHeaderPanel.btn_menu) {

            if (menuHeaderPanel.btn_menu.isSelected()) {

                viewController.showSlidebar(ventanaPrincipal.fw_Container, sliderMenuOptions);
            } else {
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
            viewController.showModulePanel(ventanaPrincipal.fw_Container, ventaPanel);
        }
        if (e.getSource() == sliderMenuOptions.btn_salir) {
            mostrarVista("CerrarSeccion");
        }

    }

    @Override
    public void mouseClicked(MouseEvent e) {

        if (e.getSource() == menuHeaderPanel.btn_closeApp) {

            System.exit(0);

        }
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
            case "login" ->
                viewController.nextView(ventanaPrincipal.fw_Container, loginPanel);
            case "ventas" ->
                viewController.backView(ventanaPrincipal.fw_Container, menuHeaderPanel, ventaPanel);
            case "pagos" ->
                viewController.nextView(ventanaPrincipal.fw_Container, pagosPanel);
            case "inventario" ->
                viewController.backView(ventanaPrincipal.fw_Container, menuHeaderPanel, ventaPanel);
            case "CerrarSeccion" ->
                viewController.nextView(ventanaPrincipal.fw_Container, loginPanel);
        }
    }

    public void activarPantallaCompleta() {

        JFrame frame = this.ventanaPrincipal;
        // 1. Obtener el entorno gráfico local y el dispositivo de pantalla predeterminado
        GraphicsDevice dispositivo = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();

        // 2. Comprobar si el dispositivo soporta el modo de pantalla completa
        if (dispositivo.isFullScreenSupported()) {

            // Opcional: Si quieres quitar los bordes de la ventana (título/botones de Windows)
            // Esto debe hacerse antes de hacerla visible.
            // frame.setUndecorated(true); 
            // 3. Establecer el JFrame en modo de pantalla completa
            dispositivo.setFullScreenWindow(frame);
        } else {
            // En caso de que no se soporte el modo exclusivo (raro hoy en día)
            System.out.println("El modo de pantalla completa no es compatible en este dispositivo.");
            // Se puede usar el método maximizado como alternativa.
            frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
            frame.setVisible(true);
        }
    }

    public void maximizarVentana() {

        // Maximiza la ventana en ambas direcciones. 
        // Esto se comporta como si el usuario hiciera clic en el botón Maximizar.
        this.ventanaPrincipal.setExtendedState(JFrame.MAXIMIZED_BOTH);

        this.ventanaPrincipal.setVisible(true);
    }

    public void ajustarATamañoPantalla() {
        // 1. Obtener el tamaño de la pantalla del usuario (resolución)
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

        // 2. Establecer el tamaño del JFrame
        this.ventanaPrincipal.setSize(screenSize.width, screenSize.height);

        this.ventanaPrincipal.setVisible(true);
    }

    public void ajustarImagenALabel(JLabel label) { // Ya no necesita la ruta como parámetro

        if (this.imagenOriginalCache == null) {
            System.err.println("❌ Error de caché: La imagen original no se cargó.");
            return;
        }

        // Obtener dimensiones
        int anchoLabel = label.getWidth();
        int altoLabel = label.getHeight();

        if (anchoLabel <= 0 || altoLabel <= 0) {
            // Es normal que sea 0 durante el inicio o layout
            return;
        }

        // Escalar la imagen (operación rápida)
        Image imagenEscalada = this.imagenOriginalCache.getScaledInstance(
                anchoLabel,
                altoLabel,
                Image.SCALE_SMOOTH
        );

        // Asignar el nuevo icono
        label.setIcon(new ImageIcon(imagenEscalada));
    }

    // ... tu método componentResized ahora llama a ajustarImagenALabel(label)
    private void cargarImagenOriginal() {
        try (InputStream is = this.getClass().getResourceAsStream(URL_LOGO001)) {
            if (is != null) {
                this.imagenOriginalCache = ImageIO.read(is);
            } else {
                System.err.println("❌ ERROR: La imagen inicial NO fue encontrada.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void componentResized(ComponentEvent e) {
        // Asegúrate de que el path de la imagen sea accesible aquí

        ajustarImagenALabel((JLabel) e.getComponent());
    }

    @Override
    public void componentMoved(ComponentEvent e) {

    }

    @Override
    public void componentShown(ComponentEvent e) {

    }

    @Override
    public void componentHidden(ComponentEvent e) {

    }
}
