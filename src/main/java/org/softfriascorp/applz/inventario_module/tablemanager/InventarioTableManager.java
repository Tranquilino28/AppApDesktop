/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.softfriascorp.applz.inventario_module.tablemanager;

import org.softfriascorp.applz.cuenta_module.tablemanager.*;
import java.awt.Color;
import java.math.BigDecimal;
import java.util.List;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import org.softfriascorp.applz.Util.UtilValorMonedaCop;
import org.softfriascorp.applz.entity.producto.ProductoDto;

/**
 *
 * @author usuario
 */
public class InventarioTableManager {
    
    JTable inventarioTable;
    DefaultTableModel inventarioTableModel ;

    public DefaultTableModel getTableModelCuenta() {
        return inventarioTableModel;
    }

    public void setTableModelInventario(JTable inventarioTable) {
        this.inventarioTable = inventarioTable;
        
        this.inventarioTableModel = (DefaultTableModel) inventarioTable.getModel();
        
        initConfig();
    }
       
    public void addProductos(List<ProductoDto> productos){
        inventarioTableModel.setRowCount(0); // limpiar tabla

        productos.forEach(dv -> {
            
            inventarioTableModel.addRow(new Object[]{
                dv.getCodigoBarras()
                , dv.getCodigoBarras()
                , dv.getNombre()
                ,    dv.getDescripcion()
                , dv.getStockDisponible()
                    , dv.getMedida().getNombreCorto()
                    ,UtilValorMonedaCop.formatMonedaCop(dv.getPrecio())
                    ,UtilValorMonedaCop.formatMonedaCop(dv.getPrecio().multiply(BigDecimal.valueOf(dv.getStockDisponible())))
            });
        });
    }
    public void add(ProductoDto dv){
        inventarioTableModel.setRowCount(0); // limpiar tabla

        
            
            inventarioTableModel.addRow(new Object[]{
                dv.getCodigoBarras()
                , dv.getCodigoBarras()
                , dv.getNombre()
                ,    dv.getDescripcion()
                , dv.getStockDisponible()
                    , dv.getMedida().getNombreCorto()
                    ,UtilValorMonedaCop.formatMonedaCop(dv.getPrecio())
                    ,UtilValorMonedaCop.formatMonedaCop(dv.getPrecio().multiply(BigDecimal.valueOf(dv.getStockDisponible())))
            });
        
    }
    public void clearDataTable(){
        
         inventarioTableModel.setRowCount(0); // limpiar tabla    
    }
    
     private void initConfig() {
        // 🎨 Fondo cian mate
        /*Color cianMate = new Color(212, 245, 255); // tono suave de cian
        cuentaTable.setBackground(cianMate);
*/
        // Color de texto y selección (opcional, para contraste)
        inventarioTable.setForeground(Color.DARK_GRAY);
        inventarioTable.setSelectionBackground(new Color(143, 202, 229)); // cian más oscuro
        //cuentaTable.setSelectionForeground(Color.red);

        // 🔹 Centrar columnas específicas
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);

        int[] columnasACentrar = {0, 1, 4, 5, 6, 7};
        for (int col : columnasACentrar) {
            if (col < inventarioTable.getColumnCount()) {
                inventarioTable.getColumnModel().getColumn(col).setCellRenderer(centerRenderer);
            }
        }

        // Opcional: alto de filas y líneas suaves
        inventarioTable.setRowHeight(28);
        inventarioTable.setGridColor(new Color(160, 210, 210));
        
        inventarioTable.setShowGrid(true);
    }
    
     public String getDataRow(Integer ncolum){
         
        return inventarioTableModel.getValueAt(inventarioTable.getSelectedRow(), ncolum).toString();         
     }
}
