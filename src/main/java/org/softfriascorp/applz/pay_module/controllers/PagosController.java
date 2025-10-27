/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.softfriascorp.applz.pay_module.controllers;

import com.google.inject.Inject;
import com.google.zxing.pdf417.detector.Detector;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import org.softfriascorp.applz.Util.UtilValorMonedaCop;
import org.softfriascorp.applz.entity.detallesventa.DetallesVenta;
import org.softfriascorp.applz.factura.ShowFacturaPos;
import org.softfriascorp.applz.pay_module.views.PPagos;
import org.softfriascorp.applz.cuenta_module.submodules.cuenta_module.service.interfaces.CuentaService;
import org.softfriascorp.applz.entity.venta.service.interfaces.VentaService;

/**
 *
 * @author usuario
 */
public class PagosController implements ActionListener, KeyListener{
    
    private PPagos pago;
    
   
    private CuentaService servVenta;
    
    private VentaService ventaService;
    
    private Runnable 
            onPagoFinalizado, 
            onCancelarPago;
    
    @Inject
    public PagosController(PPagos pago, CuentaService cuenta, VentaService ventaService) {
        
        this.pago = pago;   
        this.servVenta = cuenta;
        this.ventaService = ventaService;
    }

    public void initConfig(){
        
        this.pago.btn_aceptar_pago.addActionListener(this);
        
        this.pago.txt_recibido.addKeyListener(this);     
        
        this.pago.btn_atras.addActionListener(this);
        
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == pago.btn_aceptar_pago){
            
            ventaService.saveVenta(servVenta);
            
            
            
            
            
           if (onPagoFinalizado != null) onPagoFinalizado.run();
        }
        if(e.getSource() == pago.btn_atras){
            
           if (onPagoFinalizado != null) onCancelarPago.run();
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
        if (e.getSource() == pago.txt_recibido){
            
            String valorRecibido = pago.txt_recibido.getText().trim();
            
            if (!valorRecibido.isEmpty()) {
                BigDecimal valorR = new BigDecimal(valorRecibido);
                
                String ValorFinal = UtilValorMonedaCop.formatMonedaCop(servVenta.calcularTotal().subtract(valorR));
                
                pago.txt_cambio.setText(ValorFinal);
            }else{
            pago.txt_cambio.setText("00,0");
        }
            
        }
    }
    public void setOnPagoFinalizado(Runnable onPagoFinalizado) {
        this.onPagoFinalizado = onPagoFinalizado;
    }
    
    public void setOnCancelarPago(Runnable onCancelarPago) {
        this.onCancelarPago = onCancelarPago;
    }
    
   public void ShowDetailCuenta(){
      
       List<DetallesVenta> detales = new ArrayList<>(servVenta.listarProductos().values());
      
       String total = UtilValorMonedaCop.formatMonedaCop(servVenta.calcularTotal());
       System.out.println("total : "+total);
       String det = ShowFacturaPos.factura(detales , total);
       
       
       pago.txt_totalpagar.setText(total);
       
        pago.txt_detallescompra.setText(det);
        
        
    }
    
   
   public void cerrarVenta(){
       
       
       
       
       
       
       
   }
    
    
    
}