/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.softfriascorp.applz.controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import org.softfriascorp.applz.api.Ventas_Facruracion;
import org.softfriascorp.applz.api.modeldto.Producto_dto;
import org.softfriascorp.applz.modelProductosVenta.VentaProductos;
import org.softfriascorp.applz.util.Cambio_panel;
import org.softfriascorp.applz.util.TableManager;
import org.softfriascorp.applz.views.Frame_Work;
import org.softfriascorp.applz.views.PPagos;
import org.softfriascorp.applz.views.PVenta;

/**
 *
 * @author usuario
 */
public class Controller_Venta implements KeyListener, ActionListener, MouseListener {

    Frame_Work ventana;
    private PVenta venta;
    private PPagos pago;
    
    private DefaultTableModel listado_de_productos_venta;
    private DefaultTableModel listado_de_productos_en_stock;

    private TableManager tabla_de_producs;

    Map<String, VentaProductos> mapaProductos;

    public Controller_Venta(Frame_Work ventana, PVenta venta,PPagos pago) {
this.ventana = ventana;
        this.pago = pago;
        this.venta = venta;

        listado_de_productos_venta = (DefaultTableModel) this.venta.tabla_de_pedido.getModel();
        listado_de_productos_en_stock = (DefaultTableModel) this.venta.tabla_de_busqueda_de_productos.getModel();

        // this.tablaProductos = this.venta.tabla_de_busqueda_de_productos;
        this.venta.txt_busqueda_por_lector.addKeyListener(this);

        this.venta.txt_buscar_productos_stok.addKeyListener(this);
        
        this.venta.txt_cantidad.addKeyListener(this);
        
        
        this.venta.tabla_de_busqueda_de_productos.addMouseListener(this);
        
        this.venta.btn_pagar.addActionListener(this);
        
        this.venta.tabla_de_pedido.addKeyListener(this);

        initComponent();

        mapaProductos = new HashMap<>();

        //actualizarTablaProductos(Ventas_Facruracion.listarProductos(), listado_de_productos_en_stock);
    }

    void initComponent() {

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

       

        if (e.getKeyCode() == KeyEvent.VK_ENTER) {

            /**
             * busca un prosucto del codigo que captura el lector de campode
             * busqueda
             */
            if (e.getSource() == venta.txt_busqueda_por_lector) {
                // Aquí pones la lógica para la búsqueda en la base de datos
                // Por ejemplo :

                String codigoLector = venta.txt_busqueda_por_lector.getText();

                //System.out.println(prod.toString());
                if (mapaProductos.containsKey(codigoLector)) {

                    VentaProductos venta = mapaProductos.get(codigoLector);

                    venta.setCantidad(venta.getCantidad() + getCantidad());
                    venta.setPrecioTotal(venta.getPrecioUnitario() * venta.getCantidad());

                    actualizarTablaVentas(mapaProductos, listado_de_productos_venta);

                } else {
                    Producto_dto prod = Ventas_Facruracion.buscarProductoPorCodigo(codigoLector);
                    listar(prod);
                }

            }
        }

        if (e.getKeyCode() == KeyEvent.VK_DELETE) {
           // System.out.println("delete");

            /**
             * borra un producto de la cuenta
             */
            if (e.getSource() == venta.tabla_de_pedido) {
                //System.out.println("delete productos");
                String codigo = listado_de_productos_venta.getValueAt(venta.tabla_de_pedido.getSelectedRow(), 0).toString();

                mapaProductos.remove(codigo);

                actualizarTablaVentas(mapaProductos, listado_de_productos_venta);
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
                List<Producto_dto> prod = Ventas_Facruracion.buscarCoincidenciasCodigoNombre(codigoLector);

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
     * obtiene la cantidad de productos alistar en ventas
     *
     * @return cantida de prodcutos
     */
    public int getCantidad() {
        String cant = venta.txt_cantidad.getText();
        if (cant.matches("\\d+")) {
            return Integer.parseInt(cant);
        }

        return 1;
    }

    /**
     * lista un nuevo producto traido de la base de datos
     *
     * @param producto
     */
    public void listar(Producto_dto producto) {

        int cantidad = getCantidad();
        Double valorTotal;

        //creamos la lista de los productos a vender 
        VentaProductos ventaProducto = new VentaProductos(producto.getCodigoBarra(), producto.getDescripcion(), cantidad, "UND", producto.getPrecio(), (producto.getPrecio() * cantidad));

        // Agregamos al map
        mapaProductos.put(ventaProducto.getCodigoBarras(), ventaProducto);

        actualizarTablaVentas(mapaProductos, listado_de_productos_venta);

        //Mostrar valor total a pagar por ele usuario
        this.venta.lbl_valortotal.setText("$ " + String.valueOf(actualizarValorTotal(mapaProductos)));

    }

    /**
     * suma todo el valor de cada producto y retorna el valor total
     *
     * @param mapaProductos
     * @return valor total a pagar en formato double
     */
    private Double actualizarValorTotal(Map<String, VentaProductos> mapaProductos) {

        //forma funcional para sumar todos los valores de preciototal
        return mapaProductos.values().stream()
                .mapToDouble(p -> p.getPrecioTotal())
                .sum();

    }

    /**
     * actualiza la tabla de productos a vender
     *
     * @param mapaProductos
     * @param tabla_de_productos nm
     */
    private void actualizarTablaVentas(Map<String, VentaProductos> mapaProductos, DefaultTableModel tabla_de_productos) {

        tabla_de_productos.setRowCount(0); // limpiar tabla
        for (VentaProductos vp : mapaProductos.values()) {
            tabla_de_productos.addRow(new Object[]{
                vp.getCodigoBarras(),
                vp.getDescripcion(),
                vp.getCantidad(),
                vp.getUnidad_de_medida(),
                vp.getPrecioUnitario(),
                vp.getPrecioTotal()
            });
        }
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
    public void validarProductoEnCuenta(String codigo) {

        if (!codigo.isEmpty() && codigo.matches("\\d+")) {
            if (mapaProductos.containsKey(codigo)) {

                VentaProductos venta = mapaProductos.get(codigo);

                venta.setCantidad(venta.getCantidad() + getCantidad());
                venta.setPrecioTotal(venta.getPrecioUnitario() * venta.getCantidad());

                actualizarTablaVentas(mapaProductos, listado_de_productos_venta);

            } else {
                Producto_dto prod = Ventas_Facruracion.buscarProductoPorCodigo(codigo);
                listar(prod);
            }

        }

    }
    
    public void prepararFactura(Map<String, VentaProductos> cuenta_a_pagar){
         // Encabezado de la factura
    StringBuilder factura = new StringBuilder();
    factura.append("=========== FACTURA DE VENTA ===========\n");
    factura.append(String.format("%-20s %-10s %-12s %-12s\n", "Producto", "Cantidad", "P.Unitario", "Subtotal"));
    factura.append("-----------------------------------------\n");

    double total = 0.0;

    // Recorrer el mapa y mostrar cada producto
    for (Map.Entry<String, VentaProductos> entry : cuenta_a_pagar.entrySet()) {
        VentaProductos vp = entry.getValue();

        String nombre = vp.getDescripcion();
        int cantidad = vp.getCantidad();
        double precioUnitario = vp.getPrecioUnitario();
        double subtotal = cantidad * precioUnitario;

        factura.append(String.format("%-20s %-10d %-12.2f %-12.2f\n",
                nombre, cantidad, precioUnitario, subtotal));

        total += subtotal;
    }

    factura.append("-----------------------------------------\n");
    factura.append(String.format("%-20s %-10s %-12s %-12.2f\n", "TOTAL", "", "", total));
    factura.append("=========================================\n");

    // Mostrar en pantalla (por consola, o en GUI si quieres)
    pago.txt_detallescompra.setText(factura.toString());
        
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == venta.btn_pagar){
            
            Cambio_panel.next_panel(ventana.fw_Container, pago);
            prepararFactura(mapaProductos);
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
}
