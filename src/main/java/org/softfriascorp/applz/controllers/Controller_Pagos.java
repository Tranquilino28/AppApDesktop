/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.softfriascorp.applz.controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import org.softfriascorp.applz.views.PPagos;

/**
 *
 * @author usuario
 */
public class Controller_Pagos implements ActionListener{
    
    private PPagos pago;

    public Controller_Pagos(PPagos pago) {
        
        this.pago = pago;   
        
        this.pago.btn_aceptar_pago.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == pago.btn_aceptar_pago){
            
            
        }
    }
    
    
}
