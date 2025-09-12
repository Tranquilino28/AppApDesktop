/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.softfriascorp.applz.util;
   

/**
 *
 * @author usuario
 */



import com.lowagie.text.*;
import com.lowagie.text.pdf.*;

import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.List;
import java.util.Map;
import org.softfriascorp.applz.modelProductosVenta.Productos_Carrito;


public class UtilGeneradorFactura {

    private static final DecimalFormat df = new DecimalFormat("#,##0.00");

    public static void generarTiket(String nombreArchivo, String cliente, Map<String, Productos_Carrito> productos) {
        try {
            // Dimensiones de ticket 80mm (≈226 puntos)
            Rectangle pageSize = new Rectangle(226, 800); // alto inicial (se expande)
            Document documento = new Document(pageSize, 10, 10, 10, 10);
            PdfWriter writer = PdfWriter.getInstance(documento, new FileOutputStream(nombreArchivo+".pdf"));
            documento.open();

            Font fontTitulo = new Font(Font.HELVETICA, 10, Font.BOLD);
            Font fontNormal = new Font(Font.HELVETICA, 8, Font.NORMAL);

            // Encabezado
            Paragraph titulo = new Paragraph("FACTURA DE VENTA", fontTitulo);
            titulo.setAlignment(Element.ALIGN_CENTER);
            documento.add(titulo);
            documento.add(new Paragraph("Cliente: " + cliente, fontNormal));
            documento.add(new Paragraph("----------------------------------------------"));

            // Tabla de productos
            PdfPTable tabla = new PdfPTable(4);
            tabla.setWidthPercentage(100);
            tabla.setWidths(new float[]{2, 1, 2, 2}); // Código, Cant, P.Unit, Total

            // Encabezados
            tabla.addCell(celda("Prod.", fontNormal, true));
            tabla.addCell(celda("Cant.", fontNormal, true));
            tabla.addCell(celda("P.Unit", fontNormal, true));
            tabla.addCell(celda("Total", fontNormal, true));

            BigDecimal totalFactura = BigDecimal.ZERO;

            for (Productos_Carrito p : productos.values()) {
                tabla.addCell(celda(p.getDescripcion(), fontNormal, false));
                tabla.addCell(celda(String.valueOf(p.getCantidad()), fontNormal, false));
                tabla.addCell(celda(df.format(p.getPrecioUnitario()), fontNormal, false));
                tabla.addCell(celda(df.format(p.getPrecioTotal()), fontNormal, false));

                totalFactura = totalFactura.add(p.getPrecioTotal());
            }

            documento.add(tabla);

            documento.add(new Paragraph("----------------------------------------------"));
            documento.add(new Paragraph("TOTAL: " + df.format(totalFactura), fontTitulo));
            documento.add(new Paragraph("-🙃--------------------------------------------🙃-"));     
            documento.add(new Paragraph("✅✅✅✅✅✅✅✅✅✅✅✅✅✅✅✅✅✅✅✅✅✅✅✅✅✅✅✅✅✅"));
            
            
            documento.close();
            writer.close();

            System.out.println("✅ Ticket generado en: " + nombreArchivo);

        } catch (DocumentException | IOException e) {
            e.printStackTrace();
        }
    }

    private static PdfPCell celda(String texto, Font font, boolean encabezado) {
        PdfPCell cell = new PdfPCell(new Phrase(texto, font));
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        cell.setBorder(Rectangle.NO_BORDER);
        if (encabezado) {
            cell.setBorder(Rectangle.BOTTOM);
        }
        return cell;
    }



}
