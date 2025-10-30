/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.softfriascorp.applz.cuenta_module.tablemanager;

import java.awt.Color;
import java.math.BigDecimal;
import java.util.List;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import org.softfriascorp.applz.Util.UtilValorMonedaCop;
import org.softfriascorp.applz.entity.detallesventa.DetallesVenta;

/**
 *
 * @author usuario
 */
public class VentaTableManager {
    
    JTable cuentaTable;
    DefaultTableModel CuentatableModel ;

    public DefaultTableModel getTableModelCuenta() {
        return CuentatableModel;
    }

    public void setTableModelCuenta(JTable cuentaTable) {
        this.cuentaTable = cuentaTable;
        
        this.CuentatableModel = (DefaultTableModel) cuentaTable.getModel();
        
        initConfig();
    }
       
    public void updateTableCuenta(List<DetallesVenta> detallesList){
        CuentatableModel.setRowCount(0); // limpiar tabla

        detallesList.forEach(dv -> {
            
            //-"+dv.getProducto().getDescuentoAplicado().intValue() +"   "+ UtilValorMonedaCop.formatMonedaCop(dv.getSubTotal())
                    
                    BigDecimal porcentageDescuento = dv.getProducto().getDescuentoAplicado();
                    String totalDetalle ;
            if (porcentageDescuento != null && porcentageDescuento.compareTo(BigDecimal.ZERO) > 0) {
                // Tiene descuento
               
                totalDetalle = "-"+porcentageDescuento.intValue() +"%    "+ UtilValorMonedaCop.formatMonedaCop(dv.getSubTotal());
                
                
            } else {
                // No tiene descuento
                totalDetalle = UtilValorMonedaCop.formatMonedaCop(dv.getSubTotal());
            }
            
            
            
            CuentatableModel.addRow(new Object[]{
                dv.getProducto().getCodigoBarras(),
                dv.getProducto().getDescripcion(),
                dv.getCantidad(),
                dv.getProducto().getMedida().getNombreCorto(),
                UtilValorMonedaCop.formatMonedaCop(dv.getProducto().getPrecio()),
                totalDetalle
                
                
                
            });
        });
    }
    
    
    public void clearDataTable(){
        CuentatableModel.setRowCount(0); // limpiar tabla
    }
    
    
     private void initConfig() {
        // 🎨 Fondo cian mate
        /*Color cianMate = new Color(212, 245, 255); // tono suave de cian
        cuentaTable.setBackground(cianMate);
*/
        // Color de texto y selección (opcional, para contraste)
        cuentaTable.setForeground(Color.DARK_GRAY);
        cuentaTable.setSelectionBackground(new Color(143, 202, 229)); // cian más oscuro
        //cuentaTable.setSelectionForeground(Color.red);

        // 🔹 Centrar columnas específicas
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);

        int[] columnasACentrar = {0, 2, 3, 4, 5};
        for (int col : columnasACentrar) {
            if (col < cuentaTable.getColumnCount()) {
                cuentaTable.getColumnModel().getColumn(col).setCellRenderer(centerRenderer);
            }
        }

        // Opcional: alto de filas y líneas suaves
        cuentaTable.setRowHeight(28);
        cuentaTable.setGridColor(new Color(160, 210, 210));
        cuentaTable.setShowGrid(true);
    }
     
     
    
    
}
