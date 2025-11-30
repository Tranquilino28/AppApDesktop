/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.softfriascorp.applz.modules.factura;

import java.math.BigDecimal;
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

    /*
    {
    "id": 44,
    "codigoVenta": "bfb5267e-0272-4ef5-9159-56c45a509da3",
    "valorTotal": 22600.00,
    "valorRecibido": 34500.90,
    "valorDevuelto": -11900.90,
    "fechaVenta": "2025-11-22T16:12:39.690466",
    "metodoPago": "EFECTIVO",
    "empleado": "juan pablo",
    "cliente": "00",
    "estado": "FACTURADO",
    "detalles": [
        {
            "id": 77,
            "cantidad": "3.20",
            "precioUnitario": 5500.00,
            "subTotal": 17600.00,
            "producto": {
                "id": 16,
                "codigoBarras": "770123456098",
                "nombre": "arroz suelto",
                "descripcion": "arroz semiseleccionado ",
                "precio": 4100.00,
                "descuento": null,
                "precioFinal": null,
                "categoria": "FRUTAS",
                "medida": "KG",
                "stock": "900.60"
            }
        }
     */

    public static Boolean okPrint(Venta venta) {
        try {
            // 1. Cargar y compilar el diseño .jrxml
            JasperReport report = JasperCompileManager.compileReport("src/main/resources/plantillasFacturaPos/factura.jrxml");

            // 2. Datos dinámicos para la factura
            Map<String, Object> parameters = new HashMap<>();
            parameters.put("empresa", "FRIAS INCTECH");
            parameters.put("fecha", "" + venta.getFechaVenta().getDayOfMonth()
                    + "-" + venta.getFechaVenta().getMonthValue()
                    + "-" + venta.getFechaVenta().getYear());

            parameters.put("nit", "236548-5821-5");
            parameters.put("telefono", "0180007843");
            parameters.put("cliente", venta.getCliente());
            parameters.put("estado", venta.getEstado());
            parameters.put("total", venta.getValorTotal());
            parameters.put("recibido", venta.getValorRecibido());
            parameters.put("devuelto", venta.getValorDevuelto());
            
            
            // 3. Datos de detalle (productos)
            /*  "detalles": [
            {
            "id": 77,
            "cantidad": "3.20",
            "precioUnitario": 5500.00,
            "subTotal": 17600.00,
            "producto": {
            "id": 16,
            "codigoBarras": "770123456098",
            "nombre": "arroz suelto",
            "descripcion": "arroz semiseleccionado ",
            "precio": 4100.00,
            "descuento": null,
            "precioFinal": null,
            "categoria": "FRUTAS",
            "medida": "KG",
            "stock": "900.60"
            }*/
            List<Map<String, Object>> data = new ArrayList<>();

            venta.getDetalles().forEach(dv -> {
                String barra = dv.getProducto().getCodigoBarras();
                String descripcion = dv.getProducto().getDescripcion();
                String medida = dv.getProducto().getMedida();
                
                data.add(Map.of(
                        "codigoBarras", barra,
                         "descripcion", descripcion,
                         "cantidad", dv.getCantidad(),
                         "medida", medida,
                         "precioUnitario", dv.getPrecioUnitario(),
                         "descuento", new BigDecimal("9.0"),
                         "subTotal", dv.getSubTotal()
                ));

            });

            JRDataSource dataSource = new JRBeanCollectionDataSource(data);

            // 4. Llenar el reporte
            JasperPrint print = JasperFillManager.fillReport(report, parameters, dataSource);

            // 5. Mostrar en un visor Swing
            JasperViewer.viewReport(print, false);

            // 6. (Opcional) Exportar a PDF
            JasperExportManager.exportReportToPdfFile(print, venta.getCliente() +venta.getFechaVenta().getYear()+venta.getFechaVenta().getMonthValue()+venta.getFechaVenta().getDayOfMonth()+ ".pdf");

            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
