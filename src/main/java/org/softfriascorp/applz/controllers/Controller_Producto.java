/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.softfriascorp.applz.controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import org.softfriascorp.applz.service.interfaces.CrudService;
import org.softfriascorp.applz.views.PVenta;

/**
 *
 * @author usuario
 */
public class Controller_Producto implements ActionListener{
    
    PVenta pventa;
    CrudService<Object,Object> service;

    public Controller_Producto(PVenta pventa, CrudService<Object,Object> service)
    {
        this.pventa = pventa;
        this.service = service;
        
        
    }

    
    
    @Override
    public void actionPerformed(ActionEvent e) {
        
    }
    
    
    
}
