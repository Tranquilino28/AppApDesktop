/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.softfriascorp.applz.cuenta_module.controllers;

import com.google.inject.Inject;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.BiConsumer;
import javax.swing.JComponent;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import org.softfriascorp.applz.Util.UtilValorMonedaCop;
import org.softfriascorp.applz.entity.detallesventa.DetallesVenta;
import org.softfriascorp.applz.entity.producto.ProductoDto;
import org.softfriascorp.applz.entity.producto.service.interfaces.ProductoService;
import org.softfriascorp.applz.mainframework_module.views.MainFrameWork;
import org.softfriascorp.applz.pay_module.views.PPagos;
import org.softfriascorp.applz.cuenta_module.entity.Cuenta;
import org.softfriascorp.applz.cuenta_module.views.PVenta;
import org.softfriascorp.applz.cuenta_module.submodules.cuenta_module.service.interfaces.CuentaService;
import org.softfriascorp.applz.cuenta_module.tablemanager.StockTableManager;
import org.softfriascorp.applz.cuenta_module.tablemanager.VentaTableManager;

/**
 *
 * @author usuario
 */
public class VentaController
        implements
        KeyListener,
        ActionListener,
        MouseListener,
        FocusListener //,ListarProductoEnCuentaCliente 
{

    private final MainFrameWork mainFrameWork;

    private PVenta venta;

    private PPagos pago;

    private VentaTableManager ventaTableManager;

    private DefaultTableModel tbl_stockProductos;
    StockTableManager stockTableManager;
    // private TableManager tabla_de_producs;
    //private ServiceCarrito servVenta;
    private Map<String, Cuenta> productosList;

    private BigDecimal valorTotalVenta;

    ProductoService productoService;
    CuentaService cuentaService;

    private final String PLACE_HOLDER_BUSQUEDA = "BUSQUEDA";
    private final String PLACE_HOLDER_BUSQUEDA_X_LECTOR = "LECTOR DE CODIGO";
    private final String PLACE_HOLDER_CANTIDAD = "CANTIDAD";

    JPopupMenu menu;
    JMenuItem editar;
    JMenuItem eliminar;
    
   private Runnable onPagoIniciado;

    // private final VentaService ventaService;
    @Inject
    public VentaController(
            MainFrameWork mainFrameWork,
            PVenta venta,
            PPagos pago,
            ProductoService productoService,
            CuentaService cuentaService,
            VentaTableManager ventaTableManager,
            StockTableManager stockTableManager
    ) {
        this.mainFrameWork = mainFrameWork;
        this.pago = pago;
        this.venta = venta;
        this.productoService = productoService;
        this.cuentaService = cuentaService;
        this.ventaTableManager = ventaTableManager;
        this.stockTableManager = stockTableManager;
    }

    public void initConfig() {
        initComponent();
    }

    private void initComponent() {
        
         this.venta.txt_busqueda_por_lector.setText(PLACE_HOLDER_BUSQUEDA_X_LECTOR);

        this.venta.txt_busqueda_por_lector.addFocusListener(this);
        this.venta.txt_busqueda_por_lector.addKeyListener(this);

        this.venta.txt_buscar_productos_stok.addFocusListener(this);
        this.venta.txt_buscar_productos_stok.addKeyListener(this);
this.venta.txt_buscar_productos_stok.setText(PLACE_HOLDER_BUSQUEDA);

        this.venta.txt_cantidad.addFocusListener(this);
        this.venta.txt_cantidad.addKeyListener(this);
this.venta.txt_cantidad.setText(PLACE_HOLDER_CANTIDAD);

        this.venta.btn_buscarProductoStok.addActionListener(this);

        this.venta.tabla_de_busqueda_de_productos.addMouseListener(this);

        this.venta.btn_pagar.addActionListener(this);

        this.venta.btn_eliminar.addActionListener(this);

        this.venta.tabla_de_pedido.addKeyListener(this);

        this.venta.tabla_de_pedido.addMouseListener(this);
        // tbl_cuentaProductos = (DefaultTableModel) this.venta.tabla_de_pedido.getModel();
        //tbl_stockProductos = (DefaultTableModel) this.venta.tabla_de_busqueda_de_productos.getModel();
        ventaTableManager.setTableModelCuenta(venta.tabla_de_pedido);

        stockTableManager.setTableModelStock(venta.tabla_de_busqueda_de_productos);

        initMenuContext();

        editar.addActionListener(this);
        eliminar.addActionListener(this);
    }

    private void initMenuContext() {
        menu = new JPopupMenu();
        editar = new JMenuItem("Editar");
        eliminar = new JMenuItem("Eliminar");

        menu.add(editar);
        menu.add(eliminar);
        
        menu.setSize(100, 30);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        
        JComponent componente = (JComponent) e.getSource();
        
        if (e.getSource() == venta.btn_buscarProductoStok) {

            Map<String, DetallesVenta> detven = cuentaService.listarProductos();

            for (DetallesVenta dv : detven.values()) {
                System.out.println("descripcion : " + dv.getProducto().getDescripcion()
                        + "\n cantidad : " + dv.getCantidad()
                        + "\n cantidad : " + dv.getSubTotal());

            }

        }
        if (e.getSource() == venta.btn_eliminar) {
            cuentaService.limpiarVenta();
            ventaTableManager.clearDataTable();
            venta.lbl_valortotal.setText("");
        }
        if(componente == eliminar){
            
            String codigoBarras = venta.tabla_de_pedido.getValueAt(venta.tabla_de_pedido.getSelectedRow(), 0).toString();
            
            if (!codigoBarras.isEmpty()) {
                cuentaService.eliminarProducto(codigoBarras);
                System.out.println("se ellimino el producto ");
            ventaTableManager.updateTableCuenta(new ArrayList<>(cuentaService.listarProductos().values()));
            venta.lbl_valortotal.setText(
                            UtilValorMonedaCop.formatMonedaCop(cuentaService.calcularTotal())
                    );
            
            }else{
                
               ventaTableManager.getTableModelCuenta().removeRow(venta.tabla_de_pedido.getSelectedRow());
            }
            
            
        }
        if(componente == venta.btn_pagar){
            System.out.println("se preciono el boton pagar");
            if (onPagoIniciado != null) {
                System.out.println("ok pagando");
                onPagoIniciado.run();
            }
            
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {

        if (e.getSource() == venta.txt_cantidad) {

            char c = e.getKeyChar();
            if (!Character.isDigit(c)) {
                e.consume(); // Ignorar la entrada no numérica

            }
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ENTER) {

            if (e.getSource() == venta.txt_busqueda_por_lector) {

                String codigoLector = venta.txt_busqueda_por_lector.getText();

                try {
                    ProductoDto productoDto = productoService.findByCodigoBarras(codigoLector);

                    String cant = venta.txt_cantidad.getText().trim();
                    Integer cantidad;

                    if (!cant.isEmpty() && cant.matches("\\d+")) {

                        cantidad = Integer.valueOf(cant);

                    } else {
                        cantidad = 1;
                    }

                    cuentaService.agregarProducto(productoDto, cantidad);

                    ventaTableManager.updateTableCuenta(new ArrayList<>(cuentaService.listarProductos().values()));

                    venta.lbl_valortotal.setText(
                            UtilValorMonedaCop.formatMonedaCop(cuentaService.calcularTotal())
                    );

                } catch (RuntimeException ex) {
                    System.out.println(ex.getCause());
                }

            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (e.getSource() == venta.txt_buscar_productos_stok) {

            String textCoincidente = venta.txt_buscar_productos_stok.getText().trim();
            List<ProductoDto> prodcutosList;

            if (!textCoincidente.isEmpty()) {

                try {

                    prodcutosList = productoService.findByCoincidencia(textCoincidente);

                    stockTableManager.updateTableCuenta(prodcutosList);

                } catch (RuntimeException ex) {

                    stockTableManager.clearDataTable();
                }

            } else {

                stockTableManager.clearDataTable();
            }

        }
    }

    @Override
    public void mouseClicked(MouseEvent e
    ) {
        if (e.getClickCount() == 2) { // doble clic

            if (e.getSource() == venta.tabla_de_busqueda_de_productos) {

                String codigoBarras = stockTableManager.getDataRow(0);

                try {
                    ProductoDto productoDto = productoService.findByCodigoBarras(codigoBarras);

                    String cant = venta.txt_cantidad.getText().trim();
                    Integer cantidad;

                    if (!cant.isEmpty() && cant.matches("\\d+")) {

                        cantidad = Integer.valueOf(cant);

                    } else {
                        cantidad = 1;
                    }

                    cuentaService.agregarProducto(productoDto, cantidad);

                    ventaTableManager.updateTableCuenta(new ArrayList<>(cuentaService.listarProductos().values()));

                    venta.lbl_valortotal.setText(
                            UtilValorMonedaCop.formatMonedaCop(cuentaService.calcularTotal())
                    );

                } catch (RuntimeException ex) {
                    System.out.println(ex.getCause());
                }

                /**
                 * busca en la coleccion si existe el producto y lo actualiza si
                 * no existe busca en la base de datos y lo añade a la lista
                 */
            }

        }
    }

    @Override
    public void mousePressed(MouseEvent e
    ) {
        if (e.isPopupTrigger()) {

            if (e.getSource() == venta.tabla_de_pedido) {

                showMenuContex(e);

            }

        }

    }

    @Override
    public void mouseReleased(MouseEvent e
    ) {
        if (e.isPopupTrigger()) {

            if (e.getSource() == venta.tabla_de_pedido) {

                showMenuContex(e);

            }

        }
    }

    @Override
    public void mouseEntered(MouseEvent e
    ) {

    }

    @Override
    public void mouseExited(MouseEvent e
    ) {

    }

    @Override
    public void focusGained(FocusEvent e
    ) {
        if (e.getSource() == venta.txt_busqueda_por_lector) {
            if (venta.txt_busqueda_por_lector.getText().equals(PLACE_HOLDER_BUSQUEDA_X_LECTOR)) {
                venta.txt_busqueda_por_lector.setText("");
                venta.txt_busqueda_por_lector.setForeground(Color.BLACK); // Vuelve a color normal
            }
        }

        if (e.getSource() == venta.txt_buscar_productos_stok) {
            if (venta.txt_buscar_productos_stok.getText().equals(PLACE_HOLDER_BUSQUEDA)) {
                venta.txt_buscar_productos_stok.setText("");
                venta.txt_buscar_productos_stok.setForeground(Color.BLACK); // Vuelve a color normal
            }
        }
        if (e.getSource() == venta.txt_cantidad) {
            if (venta.txt_cantidad.getText().equals(PLACE_HOLDER_CANTIDAD)) {
                venta.txt_cantidad.setText("");
                venta.txt_cantidad.setForeground(Color.BLACK); // Vuelve a color normal
            }
        }
    }

    @Override
    public void focusLost(FocusEvent e
    ) {
        if (e.getSource() == venta.txt_busqueda_por_lector) {
            if (venta.txt_busqueda_por_lector.getText().isEmpty()) {
                venta.txt_busqueda_por_lector.setText(PLACE_HOLDER_BUSQUEDA_X_LECTOR);
                venta.txt_busqueda_por_lector.setForeground(Color.GRAY); // Color placeholder
            }
        }
        if (e.getSource() == venta.txt_buscar_productos_stok) {
            if (venta.txt_buscar_productos_stok.getText().isEmpty()) {
                venta.txt_buscar_productos_stok.setText(PLACE_HOLDER_BUSQUEDA);
                venta.txt_buscar_productos_stok.setForeground(Color.GRAY); // Color placeholder
            }
        }
        if (e.getSource() == venta.txt_cantidad) {
            if (venta.txt_cantidad.getText().isEmpty()) {
                venta.txt_cantidad.setText(PLACE_HOLDER_CANTIDAD);
                venta.txt_cantidad.setForeground(Color.GRAY); // Color placeholder
            }
        }
    }

    void showMenuContex(MouseEvent e) {
        JTable tabla = venta.tabla_de_pedido;

        int fila = tabla.rowAtPoint(e.getPoint());

        if (fila >= 0 && fila < tabla.getRowCount()) {
            tabla.setRowSelectionInterval(fila, fila);
            menu.show(e.getComponent(), e.getX(), e.getY());
        }
    }
    
    public void setOnPagoIniciado(Runnable onPagoIniciado) {
        this.onPagoIniciado = onPagoIniciado;
    }

}
