/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.softfriascorp.applz.controllers;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JComponent;
import org.softfriascorp.applz.model.Usuario;
import org.softfriascorp.applz.service.impl.ImplService_Usuario;
import org.softfriascorp.applz.service.interfaces.CrudService;
import org.softfriascorp.applz.util.Cambio_panel;
import org.softfriascorp.applz.views.Frame_Work;
import org.softfriascorp.applz.views.PInventario;
import org.softfriascorp.applz.views.PLogin;
import org.softfriascorp.applz.views.PMenuHeader;
import org.softfriascorp.applz.views.PSliderMenu;
import org.softfriascorp.applz.views.PVenta;
import org.softfriascorp.applz.views.PSlider_Contenedor;

/**
 *
 * @author usuario
 */
public class Controler_SliderOptionMenu implements ActionListener{
    
    
    PSlider_Contenedor sliderMenuOption;
    PMenuHeader menu_de_opciones;
     PSliderMenu slider;
     PVenta venta;
     PInventario inventario;

    public Controler_SliderOptionMenu(PSlider_Contenedor som
            , PMenuHeader mo
            , PSliderMenu slider
            , PVenta venta 
            , PInventario inventario
    
    )
    {
        this.sliderMenuOption = som;
        this.menu_de_opciones = mo;
        this.slider = slider;
        this.venta = venta;
        this.inventario = inventario;
        
        
        this.slider.btn_ventas.addActionListener(this);
        this.slider.btn_inventario.addActionListener(this);
        
        Cambio_panel.addPanelWest(this.sliderMenuOption, this.slider, this.menu_de_opciones);
        
      
    }
private void showView(PSlider_Contenedor som, JComponent comp) {
        
        // Borrar el componente anterior en CENTER
    Component oldCenter = ((BorderLayout) som.getLayout()).getLayoutComponent(BorderLayout.CENTER);
    if (oldCenter != null) {
        som.remove(oldCenter);
    }
        som.add(comp, BorderLayout.CENTER);
        som.revalidate();
        som.repaint();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == slider.btn_ventas) {
            showView(sliderMenuOption,venta);
        }
        if (e.getSource() == slider.btn_inventario) {
            showView(sliderMenuOption,inventario);
        }
        
    }
    
}
