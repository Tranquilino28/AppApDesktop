/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.softfriascorp.applz.Utils;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.Locale;

/**
 *
 * @author usuario
 */
public class UtilValorMonedaCop {
    
    
    public static String formatMonedaCop(BigDecimal monto) {

        //se convierte el valor de tipo String de la tabla a BigDecimal
        
        //se establece/o fotrmato la moneda a correspondiente a colombia o cop
        NumberFormat formatoMoneda = NumberFormat.getCurrencyInstance(new Locale("es", "CO"));

        //se le easignan dos decimales al valor
        formatoMoneda.setMaximumFractionDigits(2); // Máximo 2 decimales
        formatoMoneda.setMinimumFractionDigits(2); // Mínimo 2 decimales

        // Formatear el monto
        return formatoMoneda.format(monto);

    }
    
    //limpia el valor en pesos 
    public static String dcleanFormatMoneda(String v) {
        
        //se limpian los nueros quitandoles los miles y decimales
        return v.replaceAll("[^\\d]", "") // Eliminar todo excepto números y comas
                 .replace(",", ".");  // Reemplazar comas por puntos para formato decimal

    }
    
    public static String cleanFormatMoneda(String v) {
    if (v == null || v.trim().isEmpty()) {
        return "0";
    }
    return v.replace("\u00A0", "")   // quita espacios no separables (NBSP)
            .replace("$", "")        // quita símbolo de moneda
            .replace(" ", "")        // quita espacios normales
            .replace(".", "")        // quita separadores de miles
            .replace(",", ".")       // convierte decimal
            .trim();
}

    
    
}
