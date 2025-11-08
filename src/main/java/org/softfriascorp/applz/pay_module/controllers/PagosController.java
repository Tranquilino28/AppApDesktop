/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.softfriascorp.applz.pay_module.controllers;

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
import org.softfriascorp.applz.factura.ShowFacturaPos;
import org.softfriascorp.applz.pay_module.views.PPagos;
import org.softfriascorp.applz.cuenta_module.submodules.cuenta_module.service.interfaces.CuentaService;
import org.softfriascorp.applz.entity.venta.service.interfaces.VentaService;

/**
 *
 * @author usuario
 */
public class PagosController implements ActionListener, KeyListener {

    private PPagos pago;

    private CuentaService cuentaSrtvice;

    private VentaService ventaService;

    private Runnable onPagoFinalizado,
            onCancelarPago;

    @Inject
    public PagosController(PPagos pago, CuentaService cuenta, VentaService ventaService) {

        this.pago = pago;
        this.cuentaSrtvice = cuenta;
        this.ventaService = ventaService;
    }

    public void initConfig() {

        this.pago.btn_aceptar_pago.addActionListener(this);

        this.pago.txt_recibido.addKeyListener(this);

        this.pago.btn_atras.addActionListener(this);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == pago.btn_aceptar_pago) {
            
            
            if (cuentaSrtvice.getCambio().compareTo(BigDecimal.ZERO) < 0
                    || cuentaSrtvice.getCambio().compareTo(BigDecimal.ZERO) == 0) {

                ventaService.saveVenta(cuentaSrtvice);

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
