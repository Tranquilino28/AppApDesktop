/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.softfriascorp.applz.modules.factura;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.view.JasperViewer;
import org.softfriascorp.applz.entity.venta.Venta;


/**
 *
 * @author usuario
 */
public class Factura {

 public static Boolean  okPrint(Venta venta) {
        try {
            // 1. Cargar y compilar el diseño .jrxml
            JasperReport report = JasperCompileManager.compileReport("src/main/resources/plantillasFacturaPos/factura.jrxml");
            String cliente = "Tranquilino Antonio Frias";
            
            
            
            // 2. Datos dinámicos para la factura
            Map<String, Object> parameters = new HashMap<>();
            
            parameters.put("cliente",cliente );
            parameters.put("fecha", venta.getFechaVenta());
            parameters.put("total", venta.getValorTotal());

            // 3. Datos de detalle (productos)
            
             List<Map<String, Object>> data = new ArrayList<>();
            venta.getDetalles().forEach(dv -> {
            data.add(Map.of("producto", dv.getProducto().getNombre(), "cantidad", dv.getCantidad(), "precio", dv.getPrecioUnitario()));
            
            });
            
            JRDataSource dataSource = new JRBeanCollectionDataSource(data);

            // 4. Llenar el reporte
            JasperPrint print = JasperFillManager.fillReport(report, parameters, dataSource);

            // 5. Mostrar en un visor Swing
            JasperViewer.viewReport(print, false);

            // 6. (Opcional) Exportar a PDF
            JasperExportManager.exportReportToPdfFile(print, cliente+"_"+venta.getCodigoVenta()+".pdf");

            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
