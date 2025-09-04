/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.softfriascorp.applz.controllers;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import org.softfriascorp.applz.api.Response_dtos.TiposResponse;
import org.softfriascorp.applz.api.services.Impl_ServiceProducto;
import org.softfriascorp.applz.api.services.Impl_ServiceTipos;
import org.softfriascorp.applz.api.services.Producto_dto;
import org.softfriascorp.applz.controllers.interfaces.ListarProductoEnCuentaCliente;
import org.softfriascorp.applz.modelProductosVenta.VentaProductos;
import org.softfriascorp.applz.service.venta.service.ServiceVenta;
import org.softfriascorp.applz.util.Cambio_panel;
import org.softfriascorp.applz.util.TableManager;
import org.softfriascorp.applz.views.Frame_Work;
import org.softfriascorp.applz.views.PInventario;
import org.softfriascorp.applz.views.PPagos;
import org.softfriascorp.applz.views.PVenta;

/**
 *
 * @author usuario
 */
public class Controller_Inventario
        implements
        KeyListener,
        ActionListener,
        MouseListener,
        FocusListener,
        ListarProductoEnCuentaCliente {

    private PInventario inventario;

    public Controller_Inventario(
            PInventario inventario
    ) {

        this.inventario = inventario;

        this.inventario.txt_stock.setEditable(false);
        initListeners();

    }

    void initListeners() {

        this.inventario.txt_codigoBarras.addKeyListener(this);
        this.inventario.btn_guardar.addActionListener(this);

    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ENTER) {
            if (e.getSource() == inventario.txt_codigoBarras) {

                String code = inventario.txt_codigoBarras.getText();

                Producto_dto productoDto = Impl_ServiceProducto.searchProductStokZero(code);

                if (productoDto != null) {

                    disableComponents();

                    inventario.txt_nombreProducto.setText(productoDto.getNombre());

                    inventario.txt_precioProducto.setText(productoDto.getPrecio().toString());

                    inventario.txt_descripcionProducto.setText(productoDto.getDescripcion());

                    selectItemCmbx(inventario.cmbx_categoria, productoDto.getCategoria());

                    selectItemCmbx(inventario.cmbx_unidadMedida, productoDto.getMedida());

                    inventario.txt_stock.setText(productoDto.getStockDisponible().toString());

                    inventario.btn_guardar.setText("ACTUALIZAR");

                } else {

                    enableComponents();
                    inventario.txt_nombreProducto.setText("");

                    inventario.txt_precioProducto.setText("");

                    inventario.txt_descripcionProducto.setText("");

                    inventario.cmbx_categoria.addItem(code);

                    
                    
                    setItemsCmbx(inventario.cmbx_categoria, Impl_ServiceTipos.searchTipos("ticat"));
                    setItemsCmbx(inventario.cmbx_unidadMedida, Impl_ServiceTipos.searchTipos("timed"));
                    inventario.txt_stock.setText("");
                    
                    inventario.btn_guardar.setText("GUARDAR");
                }

            }
        }
    }

    private void selectItemCmbx(JComboBox cmbBox, String u) {

        cmbBox.removeAllItems();
        cmbBox.addItem(u);
        cmbBox.setSelectedItem(u);

    }

    private void setItemsCmbx(JComboBox cmbBox, List<TiposResponse> lista) {
        cmbBox.removeAllItems();
        cmbBox.addItem("SELECCIONE...");

        for (TiposResponse tipo : lista) {
            cmbBox.addItem(tipo.getNombreCorto());
        }

    }

    private void disableComponents() {

        disableEdicionComponents(inventario.txt_nombreProducto, false);
        disableEdicionComponents(inventario.txt_descripcionProducto, false);
        disableEdicionComponents(inventario.txt_stock, false);
        disableEdicionComponents(inventario.txt_precioProducto, false);
        disableEdicionComponents(inventario.cmbx_categoria, false);
        disableEdicionComponents(inventario.cmbx_unidadMedida, false);

    }

    private void enableComponents() {

        disableEdicionComponents(inventario.txt_nombreProducto, true);
        disableEdicionComponents(inventario.txt_descripcionProducto, true);
        disableEdicionComponents(inventario.txt_stock, true);
        disableEdicionComponents(inventario.txt_precioProducto, true);
        disableEdicionComponents(inventario.cmbx_categoria, true);
        disableEdicionComponents(inventario.cmbx_unidadMedida, true);

    }

    private void disableEdicionComponents(JComponent c, boolean b) {

        c.setEnabled(b);

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == inventario.btn_guardar) {
            
            
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void focusGained(FocusEvent e) {

    }

    @Override
    public void focusLost(FocusEvent e) {

    }

    @Override
    public void listarProducto(Producto_dto producto) {

    }

}
