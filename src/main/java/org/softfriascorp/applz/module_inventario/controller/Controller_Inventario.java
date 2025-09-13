/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.softfriascorp.applz.module_inventario.controller;

import com.google.inject.Inject;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;
import org.softfriascorp.applz.entity.productos.dto.Api_Producto;
import org.softfriascorp.applz.entity.tiposmaster.dto.TiposResponse;
import org.softfriascorp.applz.entity.productos.service.Api_ServiceProducto;
import org.softfriascorp.applz.entity.tiposmaster.services.impl.Impl_ServiceTipos;
import org.softfriascorp.applz.entity.productos.dto.Producto_dto;
import org.softfriascorp.applz.module_inventario.services.interfaces.Interface_serviceInventario;
import org.softfriascorp.applz.util.UtilFormat;
import org.softfriascorp.applz.module_inventario.views.PInventario;

/**
 *
 * @author usuario
 */
public class Controller_Inventario
        implements
        KeyListener,
        ActionListener,
        MouseListener,
        FocusListener {

    private PInventario inventario;
    private final String PLACE_HOLDER_BUSCAR = "BUSCAR";

    private DefaultTableModel tablaInventario;
    
    
    private final Interface_serviceInventario servInventary;
  

    // Guice inyecta automáticamente ServiceVenta aquí
   
    
    @Inject
    public Controller_Inventario(Interface_serviceInventario serviceInventary){
        this.servInventary = serviceInventary;
        
    }

    public void setInventario(PInventario inventario) {
        this.inventario = inventario;
        
        
        this.inventario = inventario;

        this.tablaInventario = (DefaultTableModel) this.inventario.tbl_tablaInventario.getModel();

        this.inventario.txt_stock.setEditable(false);

        initListeners();
        
    }
    
    

    void initListeners() {

        this.inventario.txt_codigoBarras.addKeyListener(this);

        this.inventario.btn_guardar.addActionListener(this);
        this.inventario.btn_actualizarInventario.addActionListener(this);

        this.inventario.txt_buscar.addKeyListener(this);
        this.inventario.txt_precioProducto.addKeyListener(this);
        this.inventario.txt_cantidadIngreso.addKeyListener(this);
        this.inventario.txt_buscar.addFocusListener(this);
    }

    @Override
    public void keyTyped(KeyEvent e) {
        if (e.getSource() == inventario.txt_cantidadIngreso) {

            UtilFormat.validateNumberInput(e);
        }
        if (e.getSource() == inventario.txt_precioProducto) {

            UtilFormat.validateDecimalInput(e, inventario.txt_precioProducto);
        }
        if (e.getSource() == inventario.txt_codigoBarras) {

            UtilFormat.validateNumberInput(e);
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ENTER) {
            if (e.getSource() == inventario.txt_codigoBarras) {

                String code = inventario.txt_codigoBarras.getText();

                Producto_dto productoDto = Api_ServiceProducto.searchProductStokZero(code);

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

            cmbBox.addItem(tipo.getNombreLargo());
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

    private void limpiarCamposRegistro() {

        inventario.txt_codigoBarras.setText("");
        inventario.txt_nombreProducto.setText("");
        inventario.txt_descripcionProducto.setText("");
        inventario.txt_stock.setText("");
        inventario.txt_precioProducto.setText("");

        inventario.cmbx_categoria.removeAllItems();
        inventario.cmbx_unidadMedida.removeAllItems();
        inventario.txt_cantidadIngreso.setText("");

    }

    private void enableComponents() {

        disableEdicionComponents(inventario.txt_nombreProducto, true);
        disableEdicionComponents(inventario.txt_descripcionProducto, true);
        //disableEdicionComponents(inventario.txt_stock, true);
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

        if (e.getSource() == inventario.btn_actualizarInventario) {

            List<Producto_dto> listProductos = Api_ServiceProducto.getAllProducts();

            actualizarTablaInventario(listProductos, tablaInventario);
        }

        if (e.getSource() == inventario.btn_guardar) {
            //se usa operador ternario para obtener el valor si el campo es null o vacio se coloca 0

            
            if (validateCampos(inventario.txt_codigoBarras) == true
                    && validateCampos(inventario.txt_nombreProducto) == true
                    && validateCampos(inventario.txt_descripcionProducto) == true
                    && validateCampos(inventario.txt_precioProducto) == true
                    && validateCampos(inventario.cmbx_categoria) == true
                    && validateCampos(inventario.cmbx_unidadMedida) == true
                    && validateCampos(inventario.txt_cantidadIngreso) == true) {
                
          
                servInventary.addOrUpdateProduct(crearProducto());
                
                
                limpiarCamposRegistro();
            } else {
                System.out.println("algo fallo");
            }
        }
    }

        
   private Api_Producto crearProducto(){
       return servInventary.buildProductToForm(
                        inventario.txt_codigoBarras.getText().trim()
                        , inventario.txt_nombreProducto.getText().trim()
                        , inventario.txt_descripcionProducto.getText().trim()
                        , inventario.txt_precioProducto.getText().trim()
                        , inventario.cmbx_categoria.getSelectedItem().toString()
                        , inventario.cmbx_unidadMedida.getSelectedItem().toString()
                        , inventario.txt_stock.getText().trim()
                        , inventario.txt_cantidadIngreso.getText().trim()
                );
    }
    
    boolean validateCampos(JComponent tx) {
        if (tx instanceof JTextField) {

            JTextField jt = (JTextField) tx;

            if (validarCamposVacios(jt.getText())) {
                jt.setBorder(new LineBorder(Color.RED, 2));
                return false;
            } else {
                jt.setBorder(new LineBorder(Color.gray, 0));
            }

        }
        if (tx instanceof JComboBox) {

            JComboBox jc = (JComboBox) tx;
            if (validarCamposVacios(jc.getSelectedItem().toString())) {
                jc.setBorder(new LineBorder(Color.RED, 2));
                return false;
            } else {
                jc.setBorder(new LineBorder(Color.gray, 0));
            }

        }

        return true;
    }

    private boolean validarCamposVacios(String text) {

        System.out.println(text + "\n");
        return text.trim().isEmpty()
                || text.trim().equals("")
                || text.trim().equals(null)
                || text.trim().equals("SELECCIONE...");

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

        if (e.getSource() == inventario.txt_buscar) {
            if (inventario.txt_buscar.getText().equals(PLACE_HOLDER_BUSCAR)) {
                inventario.txt_buscar.setText("");
                inventario.txt_buscar.setForeground(Color.BLACK); // Color placeholder
            }
        }
    }

    @Override
    public void focusLost(FocusEvent e) {
        if (e.getSource() == inventario.txt_buscar) {
            if (inventario.txt_buscar.getText().isEmpty()) {
                inventario.txt_buscar.setText(PLACE_HOLDER_BUSCAR);
                inventario.txt_buscar.setForeground(Color.GRAY); // Color placeholder
            }
        }
    }

    private void actualizarTablaInventario(List<Producto_dto> mapaProductos, DefaultTableModel tabla_de_productos) {

        tabla_de_productos.setRowCount(0); // limpiar tabla

        for (Producto_dto vp : mapaProductos) {
            tabla_de_productos.addRow(
                    new Object[]{
                        "unknow",
                        vp.getCodigoBarra(),
                        vp.getNombre(),
                        vp.getDescripcion(),
                        vp.getStockDisponible(),
                        vp.getMedida(),
                        vp.getPrecio(),
                        UtilFormat.formatCoingCop(vp.getValorTotal())

                    }
            );
        }
    }

    
}
