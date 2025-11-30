/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.softfriascorp.applz.modules.venta.tablemanager;

import java.awt.Color;
import java.util.List;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import org.softfriascorp.applz.entity.producto.ProductoDto;

/**
 *
 * @author usuario
 */
public class StockTableManager {
    
    JTable stockTable;
    DefaultTableModel stockTableModel ;

    public DefaultTableModel getTableModelCuenta() {
        return stockTableModel;
    }

    public void setTableModelStock(JTable stockTable) {
        this.stockTable = stockTable;
        
        this.stockTableModel = (DefaultTableModel) stockTable.getModel();
        
        initConfig();
    }
       
    public void updateTableCuenta(List<ProductoDto> productos){
        stockTableModel.setRowCount(0); // limpiar tabla

        productos.forEach(dv -> {
            
            stockTableModel.addRow(new Object[]{
                dv.getCodigoBarras(),
                dv.getNombre(),
               dv.getStock() + " "+dv.getMedida()
            });
        });
    }
    
    public void clearDataTable(){
        
         stockTableModel.setRowCount(0); // limpiar tabla    
    }
    
     private void initConfig() {
        // 🎨 Fondo cian mate
        /*Color cianMate = new Color(212, 245, 255); // tono suave de cian
        cuentaTable.setBackground(cianMate);
*/
        // Color de texto y selección (opcional, para contraste)
        stockTable.setForeground(Color.DARK_GRAY);
        stockTable.setSelectionBackground(new Color(143, 202, 229)); // cian más oscuro
        //cuentaTable.setSelectionForeground(Color.red);

        // 🔹 Centrar columnas específicas
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);

        int[] columnasACentrar = {0, 2, 3, 4, 5};
        for (int col : columnasACentrar) {
            if (col < stockTable.getColumnCount()) {
                stockTable.getColumnModel().getColumn(col).setCellRenderer(centerRenderer);
            }
        }

        // Opcional: alto de filas y líneas suaves
        stockTable.setRowHeight(28);
        stockTable.setGridColor(new Color(160, 210, 210));
        
        stockTable.setShowGrid(true);
    }
    
     public String getDataRow(Integer ncolum){
         
        return stockTableModel.getValueAt(stockTable.getSelectedRow(), ncolum).toString();         
     }
}
