/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.softfriascorp.applz.modules.inventario_module.controllers;

import com.google.inject.Inject;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JOptionPane;
import org.softfriascorp.applz.entity.maestra.Maestra;
import org.softfriascorp.applz.entity.maestra.service.interfaces.MaestraService;
import org.softfriascorp.applz.entity.producto.ProductoDto;
import org.softfriascorp.applz.entity.producto.service.interfaces.ProductoService;
import org.softfriascorp.applz.modules.inventario_module.tablemanager.InventarioTableManager;
import org.softfriascorp.applz.modules.inventario_module.views.PInventario;

/**
 *
 * @author usuario
 */
public class InventarioController implements ActionListener, KeyListener {

    private PInventario inventario;
    private ProductoService prodcutoService;
    private InventarioTableManager inventarioTableManager;

    private MaestraService maestraService;
    
    private Boolean actualizarProducto = false;

    private Boolean cmbxlist = false;

    @Inject
    public InventarioController(
            PInventario inventario,
            ProductoService prodcutoService,
            InventarioTableManager inventarioTableManager,
            MaestraService maestraService
    ) {

        this.inventario = inventario;
        this.prodcutoService = prodcutoService;

        this.inventarioTableManager = inventarioTableManager;

        this.maestraService = maestraService;
    }

    public void initConfig() {
        
        

        this.inventario.txt_buscar.addActionListener(this);
        this.inventario.txt_buscar.addKeyListener(this);

        this.inventario.txt_codigoBarras.addKeyListener(this);

        this.inventario.cmbx_categoria.addActionListener(this);

        inventario.btn_guardar.addActionListener(this);

        this.inventario.btn_allProducts.addActionListener(this);

        this.inventarioTableManager.setTableModelInventario(inventario.tbl_tablaInventario);

        
        
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == inventario.btn_allProducts) {

            try {
                List<ProductoDto> productoList = prodcutoService.allProducts();

                inventarioTableManager.addProductos(productoList);
            } catch (RuntimeException ex) {
                inventarioTableManager.clearDataTable();
            }

        }
        
        if (e.getSource() == inventario.btn_guardar) {
            
            ProductoDto productoRequest = new ProductoDto();

            productoRequest.setCodigoBarras(inventario.txt_codigoBarras.getText().trim());
            productoRequest.setNombre(inventario.txt_nombreProducto.getText().trim());
            productoRequest.setDescripcion(inventario.txt_descripcionProducto.getText().trim());
            productoRequest.setPrecio(new BigDecimal(inventario.txt_precioProducto.getText().trim()));
            productoRequest.setStockDisponible(Integer.valueOf(inventario.txt_cantidadIngreso.getText().trim()));
            
           
            productoRequest.setCategoria((Maestra) inventario.cmbx_subcategoria.getSelectedItem());
            productoRequest.setMedida((Maestra) inventario.cmbx_unidadMedida.getSelectedItem());

            try {
               
                
                inventarioTableManager.add(prodcutoService.save(productoRequest, actualizarProducto));
              
                clearFormAddProducts();
                
            } catch (RuntimeException ex) {
                System.out.println(ex.getMessage());
                JOptionPane.showMessageDialog(
                        null,
                        "⚠️no se puede realizar esta accion en este momento "
                        + "\ncontactese con el servicio al cliente",
                        "Alerta",
                        JOptionPane.WARNING_MESSAGE
                );
            }
        }

        if (e.getSource() == inventario.cmbx_categoria) {
            if (!cmbxlist) {
                Maestra categoria = (Maestra) inventario.cmbx_categoria.getSelectedItem();

                List<Maestra> subCategorias = maestraService.findByPadre(categoria.getNombreCorto());

                setItemsCmbx(inventario.cmbx_subcategoria, subCategorias);
            }
        }

    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

        if (e.getKeyCode() == KeyEvent.VK_ENTER) {

            if (e.getSource() == inventario.txt_codigoBarras) {

                String code = inventario.txt_codigoBarras.getText();

                if (!code.trim().isEmpty()) {

                    ProductoDto productoDto = null;
                    try {
                        productoDto = prodcutoService.findByCodigoBarras(code);
                    } catch (RuntimeException ex) {

                    }

                    if (productoDto != null) {
                        
                        actualizarProducto = true;
                        disableComponents();

                        inventario.txt_nombreProducto.setText(productoDto.getNombre());

                        inventario.txt_precioProducto.setText(productoDto.getPrecio().toString());

                        inventario.txt_descripcionProducto.setText(productoDto.getDescripcion());
                        
                        selectItemCmbx(inventario.cmbx_unidadMedida, productoDto.getMedida());

                        Maestra subCat = productoDto.getCategoria();           
                        
                        Maestra catPrincipal = maestraService.searcCategoriaPrincipal(subCat.getNombreCorto());
                       
                        
                        selectItemCmbx(inventario.cmbx_categoria, catPrincipal);

                        
                        selectItemCmbx(inventario.cmbx_subcategoria,subCat);

                        
                        inventario.txt_stock.setText(productoDto.getStockDisponible().toString());

                        inventario.btn_guardar.setText("ACTUALIZAR");

                    } else {

                        actualizarProducto = false;
                        enableComponents();

                        inventario.txt_nombreProducto.setText("");

                        inventario.txt_precioProducto.setText("");

                        inventario.txt_descripcionProducto.setText("");

                        inventario.cmbx_categoria.addItem(code);

                        List<Maestra> categorias = null;
                        List<Maestra> medidas = null;
                        try {
                            
                            categorias = maestraService.findByPadre("CATEG");
                            medidas = maestraService.findByPadre("MED");

                        } catch (RuntimeException ex) {

                            JOptionPane.showMessageDialog(
                                    null,
                                    "⚠️no se puede realizar el ingreso de un producto en este momento "
                                    + "\ncontactese con el servicio al cliente",
                                    "Alerta",
                                    JOptionPane.WARNING_MESSAGE
                            );

                            System.out.println(ex.getMessage());
                            return;
                        }

                        setItemsCmbx(inventario.cmbx_categoria, categorias);
                        setItemsCmbx(inventario.cmbx_unidadMedida, medidas);

                        inventario.txt_stock.setText("");

                        inventario.btn_guardar.setText("GUARDAR");
                    }
                } else {
                    JOptionPane.showMessageDialog(
                            null,
                            "⚠️ ingrese codigo de barras",
                            "Alerta",
                            JOptionPane.WARNING_MESSAGE
                    );
                }
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        String coincidencia = inventario.txt_buscar.getText().trim();

        if (e.getSource() == inventario.txt_buscar) {
            if (!coincidencia.isEmpty()) {
                try {
                    List<ProductoDto> productoList = prodcutoService.findByCoincidencia(coincidencia);

                    inventarioTableManager.addProductos(productoList);
                } catch (RuntimeException ex) {
                    inventarioTableManager.clearDataTable();
                }
            } else {

                inventarioTableManager.clearDataTable();

            }
        }

    }

    void viewResultadosSearc() {

        try {
            List<ProductoDto> productoList = prodcutoService.allProducts();

            inventarioTableManager.addProductos(productoList);
        } catch (RuntimeException ex) {
            inventarioTableManager.clearDataTable();
        }

    }

    private void selectItemCmbx(JComboBox cmbBox, Maestra m) {
        cmbxlist = true;
        
        cmbBox.removeAllItems();
        cmbBox.addItem(m);
        cmbBox.setSelectedItem(m);
        
        cmbxlist = false;
    }

    private void setItemsCmbx(JComboBox cmbBox, List<Maestra> lista) {
       
        cmbxlist = true;
        
        removeItemsCmbx(cmbBox);
        
        
        for (Maestra tipo : lista) {

            cmbBox.addItem(tipo);
        }

        cmbxlist = false;
    }
    private void removeItemsCmbx(JComboBox cmbBox){
        
        cmbxlist = true;
        
        cmbBox.removeAllItems();
        cmbBox.addItem("SELECCIONE...");
        
        
    }

    private void disableComponents() {

        disableEdicionComponents(inventario.txt_nombreProducto, false);
        disableEdicionComponents(inventario.txt_descripcionProducto, false);
        disableEdicionComponents(inventario.txt_stock, false);
        disableEdicionComponents(inventario.txt_precioProducto, false);
        disableEdicionComponents(inventario.cmbx_categoria, false);
        disableEdicionComponents(inventario.cmbx_unidadMedida, false);
        disableEdicionComponents(inventario.cmbx_subcategoria, false);
    }

    private void enableComponents() {

        disableEdicionComponents(inventario.txt_nombreProducto, true);
        disableEdicionComponents(inventario.txt_descripcionProducto, true);
        disableEdicionComponents(inventario.txt_stock, false);
        disableEdicionComponents(inventario.txt_precioProducto, true);
        disableEdicionComponents(inventario.cmbx_categoria, true);
        disableEdicionComponents(inventario.cmbx_unidadMedida, true);
        disableEdicionComponents(inventario.cmbx_subcategoria, true);
    }

    private void disableEdicionComponents(JComponent c, boolean b) {

        c.setEnabled(b);

    }
    
    private void clearFormAddProducts(){
        inventario.txt_codigoBarras.setText("");
        inventario.txt_nombreProducto.setText("");
        inventario.txt_descripcionProducto.setText("");
        inventario.txt_stock.setText("");
        inventario.txt_precioProducto.setText("");
        removeItemsCmbx(inventario.cmbx_categoria);
        removeItemsCmbx(inventario.cmbx_unidadMedida);
        removeItemsCmbx(inventario.cmbx_subcategoria);
        inventario.txt_cantidadIngreso.setText("");
    }
    
   

}
