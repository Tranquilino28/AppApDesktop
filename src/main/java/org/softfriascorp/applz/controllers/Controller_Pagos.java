/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.softfriascorp.applz.controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.math.BigDecimal;
import java.util.Map;
import org.softfriascorp.applz.api.Response_dtos.VentaResponse;
import org.softfriascorp.applz.api.services.Impl_ServiceProducto;
import org.softfriascorp.applz.api.services.Impl_ServiceVenta;
import org.softfriascorp.applz.modelProductosVenta.VentaProductos;
import org.softfriascorp.applz.service.venta.service.ServiceVenta;
import org.softfriascorp.applz.util.UtilGeneradorFactura;
import org.softfriascorp.applz.util.UtilValorMonedaCop;
import static org.softfriascorp.applz.util.UtilValorMonedaCop.formatMonedaCop;
import org.softfriascorp.applz.views.PPagos;

/**
 *
 * @author usuario
 */
public class Controller_Pagos implements ActionListener, KeyListener{
    
    private PPagos pago;
    
    private String valor = "";
    
    private Map<String , VentaProductos> cuenta ;

    private ServiceVenta servVenta;
    
    public Controller_Pagos(PPagos pago, ServiceVenta servVenta) {
        
        this.pago = pago;   
        
        this.pago.btn_aceptar_pago.addActionListener(this);
        
        this.pago.txt_recibido.addKeyListener(this);       
        
        this.servVenta = servVenta;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }
    
    public void setCuenta(Map<String,VentaProductos> cuenta){
        this.cuenta = cuenta;
    }
    

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == pago.btn_aceptar_pago){
            UtilGeneradorFactura.generarTiket("1002 ", "faber",servVenta.listarProductos() );
            VentaResponse ventaResponse = Impl_ServiceVenta.saveVenta(servVenta);
            
          
            if(ventaResponse != null){
          
            System.out.println("codigo de la venta : "+ ventaResponse.getId() + " valor total : "+ventaResponse.getValorTotal());
            }
          
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
        if (e.getSource() == pago.txt_recibido) {

            char c = e.getKeyChar();
            if (!Character.isDigit(c)) {
                
                e.consume(); // Ignorar la entrada no numérica
            }
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if(e.getSource() == pago.txt_recibido){
            
            BigDecimal valorTotal = new BigDecimal(UtilValorMonedaCop.cleanFormatMoneda(pago.txt_totalpagar.getText()));
            
            String valor = pago.txt_recibido.getText().trim();
            
            if (! valor.isEmpty()) {
                
                BigDecimal valorRecibido = new BigDecimal(valor);            
            
                BigDecimal cambio = valorTotal.subtract(valorRecibido);

                pago.txt_cambio.setText(formatMonedaCop(cambio));
            }else{
                
                pago.txt_cambio.setText("");
            }
        }
    }
    
    
}
