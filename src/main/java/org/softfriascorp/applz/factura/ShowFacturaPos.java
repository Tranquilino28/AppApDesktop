/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.softfriascorp.applz.factura;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Map;
import org.softfriascorp.applz.Utils.UtilValorMonedaCop;
import org.softfriascorp.applz.entity.detallesventa.DetallesVenta;

/**
 *
 * @author usuario
 */
public class ShowFacturaPos {

    public static String factura(List<DetallesVenta> detalles, String total) {

        String cliente = "jorge mario bermudez";
        LocalDateTime fecha = LocalDateTime.now();

        // 🔹 Generamos texto para mostrar en JTextArea
        StringBuilder sb = new StringBuilder();
        sb.append("                     Avalon Plaza Frias\n");
        sb.append("     ----------------------------------------------------\n");
        sb.append("Cliente: ").append(cliente).append("\n");
        sb.append("Fecha:   ").append(fecha).append("\n");
        sb.append("--------------------------------------------------------\n");
        sb.append(String.format("%-15s %20s %20s %20s\n", "Producto", "Cant", "Medida", "Precio"));
        sb.append("--------------------------------------------------------\n");

        BigDecimal ahorroToal = BigDecimal.ZERO;
        for (DetallesVenta p : detalles) {

            String subvalor = UtilValorMonedaCop.formatMonedaCop(p.getProducto().getPrecio());

            BigDecimal porcentageDescuento = p.getProducto().getDescuentoAplicado();

            if (porcentageDescuento != null && porcentageDescuento.compareTo(BigDecimal.ZERO) > 0) {
                
                
                
                
                ahorroToal = ahorroToal.add(
                        p.getProducto().getPrecio()
                                .subtract(p.getProducto().getPrecioFinal()
                                        .multiply(BigDecimal.valueOf(p.getCantidad()))));

            }

            sb.append(String.format("%-15s %20s %20s %20s\n",
                    p.getProducto().getCodigoBarras(),
                    p.getCantidad(),
                    p.getProducto().getMedida().getNombreCorto(),
                    subvalor));
        }

        sb.append("--------------------------------------------------------\n");
        sb.append(String.format("%50s %13s\n", "TOTAL:", total));

        if (ahorroToal.compareTo(BigDecimal.ZERO) > 0) {
            
            sb.append("--------------------------------------------------------\n");
            sb.append(String.format("%-15s %13s\n", "descuento:", ahorroToal));
        }

        sb.append("\nGracias por su compra!\n");

        return sb.toString();
    }

}
