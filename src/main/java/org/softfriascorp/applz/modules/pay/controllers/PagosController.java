/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.softfriascorp.applz.modules.pay.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.google.inject.Inject;
import com.google.zxing.pdf417.detector.Detector;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.JOptionPane;
import javax.swing.border.Border;
import org.softfriascorp.applz.Utils.UtilValorMonedaCop;
import org.softfriascorp.applz.entity.detallesventa.DetallesVenta;
import org.softfriascorp.applz.modules.factura.ShowFacturaPos;
import org.softfriascorp.applz.modules.pay.views.PPagos;
import org.softfriascorp.applz.modules.cuenta.service.interfaces.CuentaService;
import org.softfriascorp.applz.entity.maestra.Maestra;
import org.softfriascorp.applz.entity.maestra.service.interfaces.MaestraService;
import org.softfriascorp.applz.entity.venta.Venta;
import org.softfriascorp.applz.entity.venta.service.interfaces.VentaService;
import org.softfriascorp.applz.modules.factura.Factura;
import org.softfriascorp.applz.modules.login.entity.AuthResponsePerfil;
import org.softfriascorp.applz.modules.login.entity.UserPerfilRol;
import org.softfriascorp.applz.modules.pay.views.ClienteValidator;

/**
 *
 * @author usuario
 */
public class PagosController implements ActionListener, KeyListener {

    private PPagos pago;

    private CuentaService cuentaSrtvice;

    private VentaService ventaService;
    
    private final MaestraService maestraService;
    
   private final ClienteValidator fiado;

    private Runnable onPagoFinalizado,
            onCancelarPago,
            
            onFiadoActivado,
            onFiadoFinalizado
            
            , onEfectivoActivado
            ,onEfectivoFinalizado
            ;

    @Inject
    public PagosController(PPagos pago,ClienteValidator fiado, CuentaService cuenta, VentaService ventaService, MaestraService maestraService) {

        this.pago = pago;
        this.fiado = fiado;
        this.cuentaSrtvice = cuenta;
        this.ventaService = ventaService;
        this.maestraService = maestraService;
    }

    public void initConfig() {

        this.pago.btn_aceptar_pago.addActionListener(this);

        this.pago.txt_recibido.addKeyListener(this);

        this.pago.btn_atras.addActionListener(this);
        this.pago.btn_fiado.addActionListener(this);
        this.pago.btn_efectivo.addActionListener(this);
        
        //this.pago.btn_efectivo.setVisible(false);
        
        this.pago.btn_daviplata.setVisible(false);
        this.pago.btn_nequi.setVisible(false);
        this.pago.btn_tarjeta.setVisible(false);
        
        //this.pago.btn_atras.setVisible(false);
        
        
        
        cacheService();
    }
    
     private void cacheService() {
        try{ 
        Maestra efectivo = maestraService.findByTipo("EFE");
        
        this.pago.btn_efectivo.putClientProperty("btnEfectivo", efectivo);
        
        }catch(RuntimeException ex){
            System.out.println(ex.getMessage()+" maestra efectivo");            
        }
        try{
        Maestra fiado = maestraService.findByTipo("FIDO");
        
        this.pago.btn_fiado.putClientProperty("btnFiado", fiado);
        }catch(RuntimeException ex){
            System.out.println(ex.getMessage()+" maestra fiado");              
        }
    }
    

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == pago.btn_aceptar_pago) {            
            
            if (cuentaSrtvice.getCambio().compareTo(BigDecimal.ZERO) < 0
                    || cuentaSrtvice.getCambio().compareTo(BigDecimal.ZERO) == 0) {
                
                /*AuthResponsePerfil usuario = new AuthResponsePerfil();
                
                usuario.setId(UserPerfilRol.getId());
                */
                cuentaSrtvice.setValorRecibido(
                        new BigDecimal(
                                this.pago.txt_recibido.getText().trim()
                                
                        )
                );
                System.out.println("valor recibido : " + cuentaSrtvice.getValorRecibido().toString());
               
                //cuentaSrtvice.setEmpleado(usuario);
                
                
                if(cuentaSrtvice.getMetodoPago() == null ){
              Maestra metodoPago = (Maestra) this.pago.btn_efectivo.getClientProperty("btnEfectivo");
                
               cuentaSrtvice.setMetodoPago(metodoPago);
               
                   
               }
               
               cuentaSrtvice.setEstado(maestraService.findByTipo("FIN"));
               
              Venta ventaActual = ventaService.saveVenta(cuentaSrtvice);
              
                ObjectMapper mapper = new ObjectMapper();
mapper.registerModule(new JavaTimeModule());
            try {
                String jsonBody = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(ventaActual);
                System.out.println("=== JSON FACTURA ===");
                System.out.println(jsonBody);
            } catch (Exception es) {
                es.printStackTrace();
            }
                Factura.okPrint(ventaActual);
                

            } else {
                
                JOptionPane.showMessageDialog(
                        null,
                        "⚠️ No no se puede procesar la venta.\nrevisar el valor recibido.",
                        "Alerta",
                        JOptionPane.WARNING_MESSAGE
                );
                return ;
            }

            if (onPagoFinalizado != null) {
                onPagoFinalizado.run();
            }
        }
        
        if (e.getSource() == pago.btn_atras) {

            if (onPagoFinalizado != null) {
                onCancelarPago.run();
            }
        }
        
        if (e.getSource() == pago.btn_fiado) {
                     

            if (onFiadoActivado != null) {
                onFiadoActivado.run();
            }
        } if (e.getSource() == pago.btn_efectivo) {
                     

            if (onEfectivoActivado != null) {
                onEfectivoActivado.run();
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
        if (e.getSource() == pago.txt_recibido) {

            String valorRecibido = pago.txt_recibido.getText().trim();
            
            
            
            if (!valorRecibido.isEmpty()) {
                
                cuentaSrtvice.setValorRecibido(new BigDecimal(valorRecibido));
                
                if (cuentaSrtvice.getSaldoPendiente().compareTo(BigDecimal.ZERO) > 0) {
                    
                    pago.lbl_cambioSaldoPendiente.setText("PEND");
                    
                    pago.txt_cambio.setBorder(BorderFactory.createLineBorder(new Color(255, 0, 0), 2));
                    
                    pago.txt_cambio.setText(UtilValorMonedaCop.formatMonedaCop(cuentaSrtvice.getCambio()));
                    
                    

                } else {
                    
                    pago.lbl_cambioSaldoPendiente.setText("CAMB");
                    
                    pago.txt_cambio.setBorder(null);
                    
                    pago.txt_cambio.setText(UtilValorMonedaCop.formatMonedaCop(cuentaSrtvice.getCambio()));
                    
                }

            } else {
                pago.txt_cambio.setText("00,0");
            }

        }
    }
    public void setOnFiadoActivado(Runnable onFiadoActivado) {
        this.onFiadoActivado = onFiadoActivado;
    }
    public void setOnFiadoFinalizado(Runnable onFiadoFinalizado) {
        this.onFiadoFinalizado = onFiadoFinalizado;
    }
    
    public void setOnEfectivoActivado(Runnable onEfectivoIniciado) {
        this.onEfectivoActivado = onEfectivoIniciado;
    }
    public void setOnEfectivoFinalizado(Runnable onEfectivoFinalizado) {
        this.onEfectivoFinalizado = onEfectivoFinalizado;
    }
    
    public void setOnPagoFinalizado(Runnable onPagoFinalizado) {
        this.onPagoFinalizado = onPagoFinalizado;
    }

    public void setOnCancelarPago(Runnable onCancelarPago) {
        this.onCancelarPago = onCancelarPago;
    }
    
    

    public void ShowDetailCuenta() {

        List<DetallesVenta> detales = new ArrayList<>(cuentaSrtvice.listarProductos().values());

        String total = UtilValorMonedaCop.formatMonedaCop(cuentaSrtvice.calcularTotal());
        //System.out.println("total : " + total);
        String det = ShowFacturaPos.factura(detales, total);

        pago.txt_totalpagar.setText(total);

        pago.txt_detallescompra.setText(det);

    }

    public void cerrarVenta() {

    }
}
