/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.softfriascorp.applz.mainframework_module.util;

import java.awt.BorderLayout;
import java.awt.Component;
import javax.swing.JComponent;


/**
 *
 * @author usuario
 */
public class viewController {
    
    public  static void beforeView(
            JComponent contenedor
            ,JComponent header
            ,JComponent slidermenu
            ,JComponent newPanelCenter
    ) {
        contenedor.setLocation(0, 0);

        contenedor.removeAll();
        contenedor.add(header, BorderLayout.NORTH);
        
        if(slidermenu != null){
            contenedor.add(slidermenu, BorderLayout.WEST);
        }
        
        contenedor.add(newPanelCenter, BorderLayout.CENTER);
        contenedor.revalidate();
        contenedor.repaint();

    }
   public  static void showSlidebar(JComponent contMainWindow, JComponent SliderMenuOptions) {
        //som.add(mo, BorderLayout.NORTH);
        contMainWindow.add(SliderMenuOptions, BorderLayout.WEST);
        contMainWindow.revalidate();
        contMainWindow.repaint();
    }

    public  static void suprimirSlidebar(JComponent som, JComponent slider) {

        som.remove(slider);
        som.revalidate();
        som.repaint();
    }

   public  static void showView(JComponent som, JComponent comp) {

        // Borrar el componente anterior en CENTER
        Component oldCenter = ((BorderLayout) som.getLayout()).getLayoutComponent(BorderLayout.CENTER);
        
        if (oldCenter != null) {
            som.remove(oldCenter);
        }
        
        som.add(comp, BorderLayout.CENTER);
        som.revalidate();
        som.repaint();
    }
    
    public  static void showModulePanel(JComponent contMainWindow, JComponent newPanel) {

        // Borrar el componente anterior en CENTER
        Component oldPanel = ((BorderLayout) contMainWindow.getLayout()).getLayoutComponent(BorderLayout.CENTER);
        
        if (oldPanel != null) {
            contMainWindow.remove(oldPanel);
        }
        
        contMainWindow.add(newPanel, BorderLayout.CENTER);
        contMainWindow.revalidate();
        contMainWindow.repaint();
    }
    
    
    public static void addPanelCenter(JComponent contenedor, JComponent panel) {
        //panel.setSize(contenedor.getSize());

        panel.setLocation(0, 0);

        contenedor.removeAll();
        contenedor.add(panel, BorderLayout.NORTH);
        contenedor.revalidate();
        contenedor.repaint();

    }
    public static void nextView(JComponent contenedor, JComponent panel) {
        //panel.setSize(contenedor.getSize());

        panel.setLocation(0, 0);

        contenedor.removeAll();
        contenedor.add(panel, BorderLayout.CENTER);
        contenedor.revalidate();
        contenedor.repaint();

    }
    public static void backView(JComponent contenedor,JComponent header, JComponent panel) {
        //panel.setSize(contenedor.getSize());

         // Borrar el componente anterior en CENTER
         // Borrar el componente anterior en CENTER
        
        
        panel.setLocation(0, 0);

        contenedor.removeAll();
        contenedor.add(header,BorderLayout.NORTH);
        contenedor.add(panel, BorderLayout.CENTER);
        contenedor.revalidate();
        contenedor.repaint();

    }
    
}
