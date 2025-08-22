/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.softfriascorp.applz.controllers;

import java.awt.BorderLayout;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.management.ConstructorParameters;
import org.softfriascorp.applz.model.UsuarioPerfil;
import org.softfriascorp.applz.util.Cambio_panel;
import org.softfriascorp.applz.views.PMenuHeader;
import org.softfriascorp.applz.views.PSliderMenu;
import org.softfriascorp.applz.views.PSlider_Contenedor;

/**
 *
 * @author usuario
 */
public class Controller_MenuOptions implements ActionListener, MouseListener{
    PMenuHeader mo;
PSlider_Contenedor  som;
PSliderMenu slider;




    public Controller_MenuOptions(PMenuHeader mo, PSliderMenu slider ,PSlider_Contenedor  som) {
        this.mo = mo;
        this.som = som;
        this.slider = slider;
        this.mo.btn_menu.addActionListener(this);
        this.mo.btn_closeApp.addMouseListener(this);
        
    }
    
    
    void init(){
        mo.lbl_usuario_rol.setText("Rol : "+ UsuarioPerfil.getRol() + " || Usuario" + UsuarioPerfil.getNombreUsuario());
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == mo.btn_menu) {
            if (mo.btn_menu.isSelected()) {
                showSlidebar(som,mo,slider);
            }else{
                suprimirSlidebar(som, slider);
            }
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getSource() == mo.btn_closeApp) {
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

    private void showSlidebar(PSlider_Contenedor som, PMenuHeader mo,PSliderMenu slider) {
        //som.add(mo, BorderLayout.NORTH);
        som.add(slider, BorderLayout.WEST);
        som.revalidate();
        som.repaint();
    }
    private void suprimirSlidebar(PSlider_Contenedor som, PSliderMenu slider) {
       
        som.remove(slider);
        som.revalidate();
        som.repaint();
    }
}
