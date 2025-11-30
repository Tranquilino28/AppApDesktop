/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.softfriascorp.applz.modules.inventario.controllers;

import com.google.inject.Inject;
import java.awt.Color;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import org.softfriascorp.applz.Utils.UtilManagerCombobox;
import org.softfriascorp.applz.entity.maestra.Maestra;
import org.softfriascorp.applz.entity.maestra.service.interfaces.MaestraService;
import org.softfriascorp.applz.entity.producto.ProductoDto;
import org.softfriascorp.applz.entity.producto.ProductoRequest;
import org.softfriascorp.applz.entity.producto.service.interfaces.ProductoService;
import org.softfriascorp.applz.frameview_manager.util.ViewController;
import org.softfriascorp.applz.modules.inventario.tablemanager.InventarioTableManager;
import org.softfriascorp.applz.modules.inventario.views.FormAddProductos;
import org.softfriascorp.applz.modules.inventario.views.PInventario;

/**
 *
 * @author usuario
 */
public class InventarioController 
        implements 
        ActionListener
        , KeyListener
        , FocusListener
,ChangeListener
{

    
    private final String SEARCH = "BUSCAR";
    private final String SEL_CATEGORIA = "SELECCIONE";
    private final String SEL_SUBCATEGORIA= "SELECCIONE";
    private final String SEL_MEDIDA= "SELECCIONE";
    
     private List<Maestra> categorias = null;
     private List<Maestra> medidas = null;
     private List<Maestra> subCategorias = null;
     
     
    private PInventario inventario;
    private ProductoService prodcutoService;
    private InventarioTableManager inventarioTableManager;

    private MaestraService maestraService;
    
    private Boolean actualizarProducto = false;

    private Boolean cmbxlist = false;
    
    private FormAddProductos formProductos;
    
    /*
    componentes de entrada del panel FormProductos 
    */
        JTextField txt_codigoBarras;
        JTextField txt_nombreProducto;
        JTextField txt_descripcionProducto;
        JTextField txt_stock;
        JTextField txt_precioProducto;
        JComboBox cmbx_categoria;
        JComboBox cmbx_unidadMedida;
        JComboBox cmbx_subcategoria;
        JTextField txt_cantidadIngreso;
        JButton btn_guardar;

    @Inject
    public InventarioController(
            PInventario inventario,
            ProductoService prodcutoService,
            InventarioTableManager inventarioTableManager,
            MaestraService maestraService,
            FormAddProductos formAddroductos
    ) {

        this.inventario = inventario;
        this.prodcutoService = prodcutoService;

        this.inventarioTableManager = inventarioTableManager;

        this.maestraService = maestraService;
        this.formProductos = formAddroductos;
    }

    public void initConfig() {
        
        txt_codigoBarras = formProductos.txt_codigoBarras;
        txt_nombreProducto = formProductos.txt_nombreProducto;
        txt_descripcionProducto = formProductos.txt_descripcionProducto;
        txt_stock = formProductos.txt_stock;
        txt_precioProducto = formProductos.txt_precioProducto;
        cmbx_categoria = formProductos.cmbx_categoria;
        cmbx_unidadMedida = formProductos.cmbx_unidadMedida;
        cmbx_subcategoria = formProductos.cmbx_subcategoria;
        txt_cantidadIngreso = formProductos.txt_cantidadIngreso;
        btn_guardar = formProductos.btn_guardar;
        
        
        

        this.inventario.txt_buscar.addActionListener(this);
        this.inventario.txt_buscar.addKeyListener(this);

        this.txt_codigoBarras.addKeyListener(this);

        this.cmbx_categoria.addActionListener(this);

        btn_guardar.addActionListener(this);

        this.inventario.btn_allProducts.addActionListener(this);
        this.inventario.btn_stckMin.addActionListener(this);
        this.inventario.btn_stckMax.addActionListener(this);

        this.inventarioTableManager.setTableModelInventario(inventario.tbl_tablaInventario);

        this.inventario.txt_buscar.addFocusListener(this);
        
        this.inventario.cmbx_searchCategoria.addActionListener(this);
        this.inventario.cmbx_searchSubcategorias.addActionListener(this);
        
        this.inventario.jSlider1.addChangeListener(this);
        this.inventario.jSlider2.addChangeListener(this);
        
        
        this.inventario.btn_addProductos.addActionListener(this);
        
        
        cacheService();
        
    }

   public  void cacheService(){
       
       
       this.categorias = maestraService.findByPadre("CATEG");
       setItemsCmbx(inventario.cmbx_searchCategoria,SEL_CATEGORIA, categorias);
                       
       
       this.medidas = maestraService.findByPadre("MED");
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
        
        if (e.getSource() == btn_guardar) {
            
            ProductoRequest productoRequest = new ProductoRequest();

            productoRequest.setCodigoBarras(txt_codigoBarras.getText().trim());
            productoRequest.setNombre(txt_nombreProducto.getText().trim());
            productoRequest.setDescripcion(txt_descripcionProducto.getText().trim());
            productoRequest.setPrecio(new BigDecimal(txt_precioProducto.getText().trim()));
            productoRequest.setCantidad(
                    (
                    !txt_cantidadIngreso.getText().trim().isEmpty() 
                    ? txt_cantidadIngreso.getText().trim()
                    : "0"
                    )
            );
            
            Maestra categoria = (Maestra) cmbx_subcategoria.getSelectedItem();
            Maestra medida =  (Maestra) cmbx_unidadMedida.getSelectedItem();
           
            productoRequest.setCategoriaId(categoria.getId());
            productoRequest.setMedidaId(medida.getId());

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

        if (e.getSource() == cmbx_categoria) {
            if (!cmbxlist && !actualizarProducto) {
                Maestra categoria = (Maestra) cmbx_categoria.getSelectedItem();
                
                subCategorias = maestraService.findByPadre(categoria.getNombreCorto());

                setItemsCmbx(cmbx_subcategoria,SEL_SUBCATEGORIA, subCategorias);
            }
        }
        
        if (e.getSource() == inventario.cmbx_searchCategoria) {
            if (!cmbxlist) {
            
                Maestra categoria = (Maestra) inventario.cmbx_searchCategoria.getSelectedItem();
                
                subCategorias = maestraService.findByPadre(categoria.getNombreCorto());

                setItemsCmbx(inventario.cmbx_searchSubcategorias,SEL_SUBCATEGORIA, subCategorias);
            }
        }
        
        if (e.getSource() == inventario.cmbx_searchSubcategorias) {
            
            if (!cmbxlist) {
                
                int indexSubCateg = inventario.cmbx_searchSubcategorias.getSelectedIndex();
                
                if(indexSubCateg !=0 && indexSubCateg!= -1)
                    inventarioTableManager.addProductos(getProductosInventarios());
                
            }
        }
        if (e.getSource() == inventario.btn_addProductos) {
            
            if(inventario.btn_addProductos.isSelected()){
                
                ViewController.showPanelEast(inventario, formProductos);
                
            }else{
                ViewController.deletePanelEast(inventario, formProductos);
            }
            
        }
        

    }
    private List<ProductoDto> getProductosInventarios(){
        Maestra subCategoria = (Maestra) this.inventario.cmbx_searchSubcategorias.getSelectedItem();
        
        Integer stockMin = inventario.jSlider1.getValue();;
        Integer stockMax = inventario.jSlider1.getValue();;
        
        
        if (!inventario.btn_stckMin.isSelected()) {            
          stockMin =  null;            
        }if (!inventario.btn_stckMax.isSelected()) {            
          stockMax = null;            
        }
        
        
                String search = this.inventario.txt_buscar.getText().trim();

                if(search.equals(this.SEARCH)){
                    search = "";
                }
                
               return 
                        prodcutoService.searchInventario(search, subCategoria.getId(),stockMin, stockMax);

    }

   
    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

        if (e.getKeyCode() == KeyEvent.VK_ENTER) {

            if (e.getSource() == txt_codigoBarras) {

                String code = txt_codigoBarras.getText();

                if (!code.trim().isEmpty()) {

                    ProductoDto productoDto = null;
                    try {
                        productoDto = prodcutoService.findByCodigoBarras(code);
                    } catch (RuntimeException ex) {

                    }

                    if (productoDto != null) {
                        
                        actualizarProducto = true;
                        
                        enableComponents(actualizarProducto);

                        txt_nombreProducto.setText(productoDto.getNombre());

                        txt_precioProducto.setText(productoDto.getPrecio().toString());

                        txt_descripcionProducto.setText(productoDto.getDescripcion());
                        
                        selectItemCmbx(cmbx_unidadMedida, productoDto.getMedida());

                        String subCat = productoDto.getCategoria();           
                        
                        Maestra catPrincipal = maestraService.searcCategoriaPrincipal(subCat);
                       
                        
                        selectItemCmbx(cmbx_categoria, catPrincipal.getNombreLargo());

                        
                        selectItemCmbx(cmbx_subcategoria,subCat);

                        
                        txt_stock.setText(productoDto.getStock().toString());

                        btn_guardar.setText("ACTUALIZAR");

                    } else {

                        actualizarProducto = false;
                        
                        enableComponents(actualizarProducto);

                        txt_nombreProducto.setText("");

                        txt_precioProducto.setText("");

                        txt_descripcionProducto.setText("");

                        cmbx_categoria.addItem(code);

                        
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

                        setItemsCmbx(cmbx_categoria, SEL_CATEGORIA, categorias);
                        
                        removeItemsCmbx(cmbx_subcategoria, SEL_SUBCATEGORIA);
                        
                        setItemsCmbx(cmbx_unidadMedida, SEL_MEDIDA,medidas);

                        txt_stock.setText("");

                        btn_guardar.setText("GUARDAR");
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
            
            Integer indexSubCategoria = this.inventario.cmbx_searchSubcategorias.getSelectedIndex();
            
            System.out.println("el index de subcategoria es : " + indexSubCategoria);
            
            if(indexSubCategoria != 0 && indexSubCategoria != -1){
                System.out.println("busca filtrando");
                inventarioTableManager.addProductos(getProductosInventarios());
                
            }else{
                
                if(!coincidencia.isEmpty()){
                    System.out.println("busca coincidencias");
                    List<ProductoDto> prod = prodcutoService.findByCoincidencia(coincidencia);
                inventarioTableManager.addProductos(prod);
                }
                
                
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

    private void selectItemCmbx(JComboBox cmbBox, String m) {
        cmbxlist = true;
        
        cmbBox.removeAllItems();
        cmbBox.addItem(m);
        cmbBox.setSelectedItem(m);
        
        cmbxlist = false;
    }

    private void setItemsCmbx(JComboBox cmbBox,String title, List<Maestra> lista) {
       
        cmbxlist = true;
        
        removeItemsCmbx(cmbBox,title);
        
        
        for (Maestra tipo : lista) {

            cmbBox.addItem(tipo);
        }

        cmbxlist = false;
    }
    private void removeItemsCmbx(JComboBox cmbBox,String title){
        
        cmbxlist = true;
        
        cmbBox.removeAllItems();
        cmbBox.addItem(title);
        
        
    }

    private void enableComponents(Boolean estado) {

        disableEdicionComponents(txt_nombreProducto, estado);
        disableEdicionComponents(txt_descripcionProducto, estado);
        disableEdicionComponents(txt_stock, false);
        disableEdicionComponents(txt_precioProducto, estado);
        disableEdicionComponents(cmbx_categoria, estado);
        disableEdicionComponents(cmbx_unidadMedida, estado);
        disableEdicionComponents(cmbx_subcategoria, estado);
    }

    private void disableEdicionComponents(JComponent c, boolean b) {

        
        //c.setEnabled(b);
        c.setFocusable(b);
        c.setRequestFocusEnabled(b);
        
        if(c instanceof JComboBox && b == true){
            c.putClientProperty("JComboBox.isTableCellEditor", Boolean.TRUE);
        }if(c instanceof JComboBox && b == false){
            c.putClientProperty("JComboBox.isTableCellEditor", Boolean.FALSE);
            
        }
        
        
        if (b) {
            c.setBorder(BorderFactory.createLineBorder(Color.RED, 2));
            
        }else{
            c.setBorder( null);
        }

    }
    
    private void clearFormAddProducts(){
        txt_codigoBarras.setText("");
        txt_nombreProducto.setText("");
        txt_descripcionProducto.setText("");
        txt_stock.setText("");
        txt_precioProducto.setText("");
        removeItemsCmbx(cmbx_categoria,SEL_CATEGORIA);
        removeItemsCmbx(cmbx_unidadMedida,SEL_MEDIDA);
        removeItemsCmbx(cmbx_subcategoria,SEL_SUBCATEGORIA);
        txt_cantidadIngreso.setText("");
    }

    @Override
    public void focusGained(FocusEvent e) {
        if(e.getSource() == inventario.txt_buscar){
             if (inventario.txt_buscar.getText().equals(SEARCH)) {
                inventario.txt_buscar.setText("");
                inventario.txt_buscar.setForeground(Color.BLACK); // Vuelve a color normal
            }
        }
    }

    @Override
    public void focusLost(FocusEvent e) {
        if (inventario.txt_buscar.getText().isEmpty()) {
                inventario.txt_buscar.setText(SEARCH);
                inventario.txt_buscar.setForeground(Color.GRAY); // Color placeholder
            }
    }

    @Override
    public void stateChanged(ChangeEvent e) {
        if (e.getSource() == inventario.jSlider1){
            
           int minValue = inventario.jSlider1.getValue();
           
           inventario.btn_stckMin.setText(String.valueOf(minValue));
           
           if (inventario.btn_stckMax.isSelected()) { 
           
           inventarioTableManager.addProductos(getProductosInventarios());
           }
           
        } if (e.getSource() == inventario.jSlider2){
            
           int minValue = inventario.jSlider2.getValue();
           
           inventario.btn_stckMax.setText(String.valueOf(minValue));
           
           if (inventario.btn_stckMax.isSelected()) {                 
           
           inventarioTableManager.addProductos(getProductosInventarios());
           }
           
        }
    }
    
   

}
