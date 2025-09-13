/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.softfriascorp.applz.module_ventas.controllers;



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
import java.util.List;
import java.util.Map;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import org.softfriascorp.applz.entity.productos.service.Api_ServiceProducto;
import org.softfriascorp.applz.entity.productos.dto.Producto_dto;
import org.softfriascorp.applz.local.controllers.interfaces.ListarProductoEnCuentaCliente;
import org.softfriascorp.applz.local.modelProductosVenta.Productos_Carrito;
import org.softfriascorp.applz.local.service.venta.service.ServiceCarrito;
import org.softfriascorp.applz.util.Cambio_panel;
import org.softfriascorp.applz.util.TableManager;
import org.softfriascorp.applz.util.UtilFormat;
import org.softfriascorp.applz.module_framework.views.Frame_Work;
import org.softfriascorp.applz.module_pagos.views.PPagos;
import org.softfriascorp.applz.module_ventas.views.PVenta;

/**
 *
 * @author usuario
 */
public class Controller_Venta 
        implements 
        KeyListener
        , ActionListener
        , MouseListener
        , FocusListener
        , ListarProductoEnCuentaCliente{

    private  Frame_Work ventana;
    private PVenta venta;
    private PPagos pago;
    private DefaultTableModel listado_de_productos_venta;
    private DefaultTableModel listado_de_productos_en_stock;
    private TableManager tabla_de_producs;
    private ServiceCarrito servVenta;   
   
   private Map<String , Productos_Carrito> mapaProductos;
   private BigDecimal valorTotalVenta;
   
   private final String  PLACE_HOLDER_BUSQUEDA = "BUSQUEDA"; 
   private final String  PLACE_HOLDER_BUSQUEDA_X_LECTOR = "BUSQUEDA X LECTOR DE CODIGO";
   private final String  PLACE_HOLDER_CANTIDAD = "CANTIDAD";
   
   
   
   public Controller_Venta(
            Frame_Work ventana
            , PVenta venta
            , PPagos pago
            , ServiceCarrito servVenta
    ) {
        this.ventana = ventana;
        this.pago = pago;
        this.venta = venta;
        this.servVenta = servVenta;
        
        
        mapaProductos = servVenta.listarProductos();
        valorTotalVenta = BigDecimal.ZERO;

      
        initComponent();

        

        //actualizarTablaProductos(Ventas_Facruracion.listarProductos(), listado_de_productos_en_stock);
    }

    private void initComponent() {

         listado_de_productos_venta = (DefaultTableModel) this.venta.tabla_de_pedido.getModel();
        listado_de_productos_en_stock = (DefaultTableModel) this.venta.tabla_de_busqueda_de_productos.getModel();    
        
        this.venta.txt_busqueda_por_lector.addFocusListener(this);
        this.venta.txt_buscar_productos_stok.addFocusListener(this);
        this.venta.txt_cantidad.addFocusListener(this);
     
        // this.tablaProductos = this.venta.tabla_de_busqueda_de_productos;
        this.venta.txt_busqueda_por_lector.addKeyListener(this);

        this.venta.txt_buscar_productos_stok.addKeyListener(this);

        this.venta.txt_cantidad.addKeyListener(this);

        this.venta.tabla_de_busqueda_de_productos.addMouseListener(this);

        this.venta.btn_pagar.addActionListener(this);
        this.venta.btn_eliminar.addActionListener(this);

        this.venta.tabla_de_pedido.addKeyListener(this);

        
        tabla_de_producs = new TableManager(venta.tabla_de_busqueda_de_productos);

        TableManager.centrarCeldas(venta.tabla_de_busqueda_de_productos, 0, 2);

    }

    @Override
    public void keyTyped(KeyEvent e) {

        if (e.getSource() == venta.txt_cantidad) {

            char c = e.getKeyChar();
            if (!Character.isDigit(c)) {
                e.consume(); // Ignorar la entrada no numérica

            } else {

                /* String currentText = venta.txt_cantidad.getText();
                String newText = currentText + c;
                if (!isValidValue(newText)) {
                    panelVentas.label_msjerror.setText("¡ la cantidad maxima 30 !");
                } else {
                    limpiarLabel(panelVentas.label_msjerror);
                }*/
            }
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
            /**
             * busca un prosducto del codigo que captura el lector en el campo de
             * busqueda
             */
        if (e.getKeyCode() == KeyEvent.VK_ENTER) {

           
            if (e.getSource() == venta.txt_busqueda_por_lector) {
              
                String codigoLector = venta.txt_busqueda_por_lector.getText();

                if (servVenta.productoExiste(codigoLector)) {
                    
                    validarProductoEnCuenta(codigoLector);
                       
                } else {
                    
                    //añade un nuevo registro a la cuenta 
                   
                    Producto_dto prod = Api_ServiceProducto.searchProductCode(codigoLector);
                        
                    listarProducto(prod);
                    
                }

            }
        }

        if (e.getKeyCode() == KeyEvent.VK_DELETE) {
           
            /**
             * borra un producto de la cuenta
             */
            if (e.getSource() == venta.tabla_de_pedido) {
                //System.out.println("delete productos");
                String codigo = listado_de_productos_venta.getValueAt(venta.tabla_de_pedido.getSelectedRow(), 0).toString();

                mapaProductos.remove(codigo);

               
                
                actualizarTablaVentas(mapaProductos, listado_de_productos_venta);

        //Mostrar valor total a pagar por ele usuario
        
       
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

        /**
         * captura las entradas del techado y la asigna a una variable si el
         * texto no es vacio hace la busqueda del producto por el contrario
         * limpia la tabla
         */
        if (e.getSource() == venta.txt_buscar_productos_stok) {

            String codigoLector = venta.txt_buscar_productos_stok.getText().trim();

            if (!codigoLector.isEmpty()) {
                
                List<Producto_dto> prod = Api_ServiceProducto.searchCoincidencias(codigoLector);

                System.out.println(prod.toString());
                
                actualizarTablaProductos(prod, listado_de_productos_en_stock);
            } else {
                limpiarTabla(listado_de_productos_en_stock);
            }

        }
    }

    void limpiarTabla(DefaultTableModel t) {
        t.setRowCount(0); // limpiar tabla
    }

    @Override
    public void mouseClicked(MouseEvent e) {

        if (e.getClickCount() == 2) { // doble clic

            if (e.getSource() == venta.tabla_de_busqueda_de_productos) {

                String codigo = listado_de_productos_en_stock.getValueAt(venta.tabla_de_busqueda_de_productos.getSelectedRow(), 0).toString();

                /**
                 * busca en la coleccion si existe el producto y lo actualiza si
                 * no existe busca en la base de datos y lo añade a la lista
                 */
                validarProductoEnCuenta(codigo);
            }

        }
    }
    
   

    /**
     * lista un nuevo producto traido de la base de datos
     *
     * @param producto
     */
    @Override
    public void listarProducto(Producto_dto producto) {

        int cantidad = UtilFormat.getCantidad(venta.txt_cantidad);
        Double valorTotal;
        
        

        //creamos la lista de los productos a vender 
        Productos_Carrito ventaProducto = 
                new Productos_Carrito(
                        producto.getCodigoBarra()
                        , producto.getDescripcion()
                        , cantidad
                        , "UND"
                        , producto.getPrecio()
                        , producto.getPrecio().multiply(BigDecimal.valueOf( UtilFormat.getCantidad(venta.txt_cantidad)))
                );

        // Agregamos al map
        mapaProductos.put(ventaProducto.getCodigoBarras(), ventaProducto);

        actualizarTablaVentas(mapaProductos, listado_de_productos_venta);

     
        

    }
    
    private void mostrarValorCuenta(Map<String, Productos_Carrito> mapaProductos){
        
        if (! mapaProductos.isEmpty()) {
            
            this.venta.lbl_valortotal.setText(UtilFormat.formatCoingCop(actualizarValorTotal(mapaProductos)));           
        }else{
            
            listado_de_productos_venta.setRowCount(0);
            
            this.venta.lbl_valortotal.setText("000.00");
        }
        
        
        
        
        
    }

    /**
     * suma todo el valor de cada producto y retorna el valor total
     *
     * @param mapaProductos
     * @return valor total a pagar en formato double
     */
    private BigDecimal actualizarValorTotal(Map<String, Productos_Carrito> mapaProductos) {
             //forma funcional para sumar todos los valores de preciototal 
             /*
             Double v =  mapaProductos.values().stream()
                                        .mapToDouble(p -> p.getPrecioTotal())
                                        .sum();
        */
             BigDecimal totalVenta = mapaProductos.values()
            .stream()
            .map(Productos_Carrito::getPrecioTotal) // tomar el precioTotal de cada producto
            .reduce(BigDecimal.ZERO, BigDecimal::add); // sumarlos


        //actualiza la varibale gobal
        valorTotalVenta = totalVenta;
             
        return totalVenta;

    }

    /**
     * actualiza la tabla de productos a vender
     *
     * @param mapaProductos
     * @param tabla_de_productos nm
     */
    private void actualizarTablaVentas(Map<String, Productos_Carrito> mapaProductos, DefaultTableModel tabla_de_productos) {
        tabla_de_productos.setRowCount(0); // limpiar tabla
        
            for (Productos_Carrito vp : mapaProductos.values()) {
                tabla_de_productos.addRow(new Object[]{
                    vp.getCodigoBarras(),
                    vp.getDescripcion(),
                    vp.getCantidad(),
                    vp.getUnidad_de_medida(),
                    
                        UtilFormat.formatCoingCop(vp.getPrecioUnitario()),
                        UtilFormat.formatCoingCop(vp.getPrecioTotal())
                });
            }
        
        mostrarValorCuenta(mapaProductos);
        actualizarValorTotal(mapaProductos);
    }

    /**
     * actualiza la tabla de productos en stock
     *
     * @param mapaProductos
     * @param tabla_de_productos nm
     */
    private void actualizarTablaProductos(List<Producto_dto> mapaProductos, DefaultTableModel tabla_de_productos) {

        tabla_de_productos.setRowCount(0); // limpiar tabla
        for (Producto_dto vp : mapaProductos) {
            tabla_de_productos.addRow(new Object[]{
                vp.getCodigoBarra(),
                vp.getDescripcion(),
                vp.getStockDisponible()

            });
        }
    }

    /**
     * busca en la coleccion si existe el producto y lo actualiza si no existe
     * busca en la base de datos y lo añade a la lista
     */
    private void validarProductoEnCuenta(String codigo) {

        if (!codigo.isEmpty() && codigo.matches("\\d+")) {
            if (mapaProductos.containsKey(codigo)) {

                Productos_Carrito venta = mapaProductos.get(codigo);

                venta.setCantidad(venta.getCantidad() + UtilFormat.getCantidad(this.venta.txt_cantidad));
                venta.setPrecioTotal(venta.getPrecioUnitario().multiply(BigDecimal.valueOf( venta.getCantidad())));

                actualizarTablaVentas(mapaProductos, listado_de_productos_venta);

                this.venta.lbl_valortotal.setText(UtilFormat.formatCoingCop(actualizarValorTotal(mapaProductos)));

            } else {
                Producto_dto prod = Api_ServiceProducto.searchProductCode(codigo);
                listarProducto(prod);
            }

        }

    }

    /**
     * %-20s % → indica que viene un valor a formatear. - → alinea el texto a la
     * izquierda dentro del espacio asignado. 20 → ancho total del campo (20
     * caracteres). s → el valor será tratado como String.
     *
     * %-10s Igual que arriba, pero con ancho 10.
     *
     * %-12s
     *
     * Igual, pero con ancho 12. \n Salto de línea.
     *
     * @param cuenta_a_pagar
     */
    
        
    private void prepararFactura(Map<String, Productos_Carrito> cuenta_a_pagar) {
        StringBuilder factura = new StringBuilder();
        factura.append("=============== FACTURA DE VENTA ===============\n");
        factura.append("                  AVALON PLAZA \n");
        factura.append("                  NIT 0321649-7\n");
        factura.append("             DIR-CAÑAVERALES LA GUAJIRA\n");
        factura.append("                    FECHA: "+UtilFormat.horaActual()+"\n");
        factura.append("-----------------------------------------------------------------\n");

        factura.append(String.format("%-20s %-40s %-8s %-10s %-10s\n", "Codigo", "Producto", "Cant", "P.Unit", "Subtotal"));
        factura.append("-----------------------------------------------------------------\n");

        BigDecimal total = BigDecimal.ZERO;
        int anchoDescripcion = 28;

        for (Productos_Carrito vp : mapaProductos.values()) {
            String codigo = vp.getCodigoBarras();
            String nombre = resumirTexto(vp.getDescripcion(), anchoDescripcion);
            int cantidad = vp.getCantidad();
            BigDecimal precioUnitario = vp.getPrecioUnitario();
            BigDecimal subtotal = precioUnitario.multiply(BigDecimal.valueOf( cantidad ));

            factura.append(String.format("%-20s %-40s %-8d %-10.2s %-10.2s\n",
                    codigo, nombre, cantidad, UtilFormat.formatCoingCop(precioUnitario), UtilFormat.formatCoingCop(subtotal)));

            total.add(subtotal) ;
        }

        factura.append("-----------------------------------------------------------------\n");
        factura.append(String.format("%-20s %-40s %-8s %-10s %-10.2s\n", "TOTAL", "", "", "", UtilFormat.formatCoingCop(total)));
        factura.append("============================================\n");

        pago.txt_detallescompra.setText(factura.toString());
    }

    /**
     * valida que el texto no supere los 40 caracteres, si los supera corta el
     * texto a cuarenta(40 - 6) caracteres
     *
     * @param texto
     * @param ancho
     * @return texto con longitud de 40 - 6
     */
    private String resumirTexto(String texto, int ancho) {
        
        if (texto.length() <= ancho) {
            
            return texto;
        }
        // Dejar espacio para los "..."
        return texto.substring(0, ancho - 6) + "";
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == venta.btn_pagar) {

            if (valorTotalVenta.compareTo(BigDecimal.ZERO) > 0) {
                prepararFactura(mapaProductos);
             
             
            
            pago.txt_totalpagar.setText(UtilFormat.formatCoingCop(valorTotalVenta));
            
            
            Cambio_panel.next_panel(ventana.fw_Container, pago);
            }else{
                JOptionPane.showMessageDialog(
                    null, 
                    "⚠ No tine Productos en la cuenta ", 
                    "Advertencia", 
                    JOptionPane.WARNING_MESSAGE
                );
            }
             
        }
        
        if(e.getSource() == venta.btn_eliminar){            
            
            mapaProductos.clear();
            
            valorTotalVenta = BigDecimal.ZERO;
            
            mostrarValorCuenta(mapaProductos);
        }

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
        if (e.getSource() == venta.txt_busqueda_por_lector) {
            if (venta.txt_busqueda_por_lector.getText().equals(PLACE_HOLDER_BUSQUEDA_X_LECTOR)) {
            venta.txt_busqueda_por_lector.setText("");
            venta.txt_busqueda_por_lector.setForeground(Color.BLACK); // Vuelve a color normal
        }
        }
        
        if (e.getSource() == venta.txt_buscar_productos_stok){
            if (venta.txt_buscar_productos_stok.getText().equals(PLACE_HOLDER_BUSQUEDA)) {
                venta.txt_buscar_productos_stok.setText("");
                venta.txt_buscar_productos_stok.setForeground(Color.BLACK); // Vuelve a color normal
            }
        }
        if (e.getSource() == venta.txt_cantidad){
            if (venta.txt_cantidad.getText().equals(PLACE_HOLDER_CANTIDAD)) {
            venta.txt_cantidad.setText("");
            venta.txt_cantidad.setForeground(Color.BLACK); // Vuelve a color normal
        }
        }
        
    }

    @Override
    public void focusLost(FocusEvent e) {
        if (e.getSource() == venta.txt_busqueda_por_lector) {
            if (venta.txt_busqueda_por_lector.getText().isEmpty()) {
                venta.txt_busqueda_por_lector.setText(PLACE_HOLDER_BUSQUEDA_X_LECTOR);
                venta.txt_busqueda_por_lector.setForeground(Color.GRAY); // Color placeholder
            }
        }
        if (e.getSource() == venta.txt_buscar_productos_stok){
            if (venta.txt_buscar_productos_stok.getText().isEmpty()) {
                venta.txt_buscar_productos_stok.setText(PLACE_HOLDER_BUSQUEDA);
                venta.txt_buscar_productos_stok.setForeground(Color.GRAY); // Color placeholder
            }
        }
        if (e.getSource() == venta.txt_cantidad){
            if (venta.txt_cantidad.getText().isEmpty()) {
                venta.txt_cantidad.setText(PLACE_HOLDER_CANTIDAD);
                venta.txt_cantidad.setForeground(Color.GRAY); // Color placeholder
            }
        }
    }

    

}
