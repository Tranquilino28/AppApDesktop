/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.softfriascorp.applz.modules.login.controllers;

import com.google.inject.Inject;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import org.softfriascorp.applz.modules.login.entity.UserPerfilRol;
import org.softfriascorp.applz.modules.login.services.interfaces.LoginService;
import org.softfriascorp.applz.modules.login.views.LoginPanel;
import org.softfriascorp.applz.modules.login.views.PRegister;
import org.softfriascorp.applz.frameview_manager.util.ViewController;
import org.softfriascorp.applz.frameview_manager.views.MainFrameWork;
import org.softfriascorp.applz.frameview_manager.views.PMenuHeader;
import org.softfriascorp.applz.frameview_manager.views.PSliderContent;
import org.softfriascorp.applz.frameview_manager.views.PSliderMenu;
import org.softfriascorp.applz.modules.venta.views.PVenta;

/**
 *
 * @author usuario
 */
public class LoginController implements ActionListener, KeyListener {

    MainFrameWork ventanaPrincipal;

    LoginPanel loginPanel;

    PSliderMenu sliderMenu;

    PRegister vista_registro_de_usuario;

    PVenta venta;

    PMenuHeader menuHeader;

    LoginService serviceLogin;

    private Runnable onLoginExitoso;

    @Inject
    public LoginController(
            MainFrameWork ventanaPrincipal,
             PMenuHeader menuHeader,
             PSliderMenu sliderMenu,
             LoginPanel loginPanel,
             PVenta venta,
             PRegister pregistro,
             LoginService serviceLogin
    ) {

        this.ventanaPrincipal = ventanaPrincipal;
        this.menuHeader = menuHeader;
        this.sliderMenu = sliderMenu;

        this.loginPanel = loginPanel;

        this.venta = venta;
        this.vista_registro_de_usuario = pregistro;

        this.serviceLogin = serviceLogin;

    }

    public void initConfig() {
        initListeners();
    }

    private void initListeners() {
        this.loginPanel.btn_login.addActionListener(this);
        this.loginPanel.btn_login.addKeyListener(this);

        this.loginPanel.btn_registrarse.addActionListener(this);

        this.vista_registro_de_usuario.btn_registro.addActionListener(this);

        this.vista_registro_de_usuario.btn_registro.addKeyListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == loginPanel.btn_login) {

            login();

        }

    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getSource() == loginPanel.btn_login) {
            if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                login();
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    private void login() {

        Boolean autenticated = false;

        try {
            autenticated = serviceLogin.authUser(loginPanel.txt_usuario.getText(), loginPanel.txt_contrasena.getText());
        } catch (RuntimeException ex) {

            

            loginPanel.lbl_nota.setText(ex.getMessage());

            return;
        }

        if (autenticated) {

            menuHeader.lbl_usuario_rol.setText(
                    "Rol : " + UserPerfilRol.getRol()
                    + " \t|| Usuario : " + UserPerfilRol.getNombre());

            if (this.onLoginExitoso != null) {
                onLoginExitoso.run();
            }
            /*
        viewController.beforeView(
        ventanaPrincipal.fw_Container
        , menuHeader
        , sliderMenu
        , venta);*/
        }

    }

    public void setOnLoginExitoso(Runnable onLoginExitoso) {
        this.onLoginExitoso = onLoginExitoso;
    }

}
