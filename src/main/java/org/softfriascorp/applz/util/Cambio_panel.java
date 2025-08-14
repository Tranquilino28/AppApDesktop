/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.softfriascorp.applz.util;

import java.awt.BorderLayout;
import java.awt.Component;
import javax.swing.JPanel;

/**
 *
 * @author ntnfr
 */
public class Cambio_panel {

    
    public Cambio_panel() {
        
    }
    
    public static void addPanelWest(
              JPanel contenedor
            , JPanel panel_header
            , JPanel panel_izquierdo
            
    ) {
        
        //panel.setSize(contenedor.getSize());

        panel_izquierdo.setLocation(0, 0);

        contenedor.removeAll();
        contenedor.add(panel_header, BorderLayout.NORTH); // arriba - panel en header
        contenedor.add(panel_izquierdo, BorderLayout.WEST); // izquierda - panel en la izquierda
        contenedor.revalidate();
        contenedor.repaint();

    }
    public static void addPanelOnTop(JPanel contenedor, JPanel panel_header) {
        //panel.setSize(contenedor.getSize());

        //panel_header.setLocation(0, 0);

        contenedor.removeAll();
        contenedor.add(panel_header ,BorderLayout.WEST);
        contenedor.revalidate();
        contenedor.repaint();

    }
    
    public static void addPanelVenta(JPanel contenedor, JPanel menuHeader , JPanel menuSlider, JPanel vistaVentas) {
        //panel.setSize(contenedor.getSize());

        contenedor.setLocation(0, 0);

        contenedor.removeAll();
        contenedor.add(menuHeader, BorderLayout.NORTH);
        contenedor.add(menuSlider, BorderLayout.WEST);
        contenedor.add(vistaVentas, BorderLayout.CENTER);
        contenedor.revalidate();
        contenedor.repaint();

    }
    public static void addPanelEast(JPanel contenedor, JPanel panel) {
        //panel.setSize(contenedor.getSize());

        panel.setLocation(0, 0);

        contenedor.removeAll();
        contenedor.add(panel);
        contenedor.revalidate();
        contenedor.repaint();

    }
    public static void addPanelSout(JPanel contenedor, JPanel panel) {
        //panel.setSize(contenedor.getSize());

        panel.setLocation(0, 0);

        contenedor.removeAll();
        contenedor.add(panel);
        contenedor.revalidate();
        contenedor.repaint();

    }
    
    public static void work_next_panel(JPanel contenedor, JPanel panel) {
        //panel.setSize(contenedor.getSize());

        panel.setLocation(0, 0);

        contenedor.removeAll();
        contenedor.add(panel);
        contenedor.revalidate();
        contenedor.repaint();

    }

    public static void addPanelCenter(JPanel contenedor, JPanel panel) {
        //panel.setSize(contenedor.getSize());

        panel.setLocation(0, 0);

        contenedor.removeAll();
        contenedor.add(panel);
        contenedor.revalidate();
        contenedor.repaint();

    }

    public static void next_panel(JPanel contenedor, JPanel panel1) {
        //panel.setSize(contenedor.getSize());
        panel1.setLocation(0, 0);

        contenedor.removeAll();

        contenedor.add(panel1);

        contenedor.revalidate();
        contenedor.repaint();

    }

    public static void next_panel(
            JPanel contenedor,
            JPanel panel1,
            JPanel panel2,
            JPanel panel3) {

        //panel.setSize(contenedor.getSize());
        //panel1.setLocation(0, 0);

        contenedor.removeAll();

        contenedor.add(panel1, BorderLayout.WEST);
        contenedor.add(panel2, BorderLayout.NORTH);
        contenedor.add(panel3, BorderLayout.CENTER);

        contenedor.revalidate();
        contenedor.repaint();

    }

    public static void redimencionarPanel(JPanel panel, JPanel panelintro) {

    }
}
