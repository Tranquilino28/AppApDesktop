/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.softfriascorp.applz.controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.table.DefaultTableModel;
import org.softfriascorp.applz.api.Response_dtos.VentaResponse;
import org.softfriascorp.applz.api.services.Producto_dto;
import org.softfriascorp.applz.api.services.impl.ApiImpl_ServiceVenta;
import org.softfriascorp.applz.util.UtilFechaFormat;
import org.softfriascorp.applz.util.UtilFormat;
import org.softfriascorp.applz.views.Seguimiento_ventas;

/**
 *
 * @author usuario
 */
public class Controller_SeguimientoVentas implements ActionListener
{
    
    private Seguimiento_ventas historialVentas;

    DefaultTableModel tblHistorial;
    public void setHistorialVentas(Seguimiento_ventas historialVentas) {
        this.historialVentas = historialVentas;
        
        
        tblHistorial = (DefaultTableModel) this.historialVentas.tbl_ventas.getModel();
        
        initListener();
    }
    
    void initListener(){
        this.historialVentas.btn_actualizar.addActionListener(this);
        
    }

    @Override
    public void actionPerformed(ActionEvent e) {
      if(e.getSource() == historialVentas.btn_actualizar){
          
          List<VentaResponse> ventas = ApiImpl_ServiceVenta.getAllVenta();
          
          actualizarHistorial(ventas, tblHistorial);
          
          
      }
    }
     private void actualizarHistorial(List<VentaResponse> mapaProductos, DefaultTableModel tableModel) {

        tableModel.setRowCount(0); // limpiar tabla

               
        for (VentaResponse vp : mapaProductos) {
            tableModel.addRow(
                    new Object[]{
                        UtilFechaFormat.formatFechaDMA(vp.getFechaVenta()),
                        vp.getMetodoPago(),
                        vp.getNombreCliente(),
                       

                    }
            );
        }
    }
    
}
