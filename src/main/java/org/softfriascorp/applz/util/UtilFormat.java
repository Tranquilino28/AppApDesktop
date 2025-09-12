/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.softfriascorp.applz.util;

import java.awt.event.KeyEvent;
import java.math.BigDecimal;
import java.text.NumberFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import javax.swing.JTextField;

/**
 *
 * @author usuario
 */
public class UtilFormat {

    private UtilFormat() {
    }

    public static String horaActual() {

        LocalDateTime ahora = LocalDateTime.now();
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");

        return ahora.format(formato);

    }

    public static String formatCoingCop(BigDecimal monto) {

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
    public static String cleanNumber(String v) {

        //se limpian los nueros quitandoles los miles y decimales
        return v.replaceAll("[^\\d]", "") // Eliminar todo excepto números y comas
                .replace(",", ".");  // Reemplazar comas por puntos para formato decimal

    }

    /**
     * obtiene la cantidad de productos alistar en ventas
     *
     * @return cantida de prodcutos
     */
    public static int getCantidad(JTextField venta) {

        String cant = venta.getText();

        if (cant.matches("\\d+")) {

            return Integer.parseInt(cant);
        }

        return 1;
    }

    public static BigDecimal doubleToBIgDecimal(Double valor) {

        return new BigDecimal(String.valueOf(valor));
    }

    public static BigDecimal integerToBIgDecimal(Integer valor) {

        return new BigDecimal(String.valueOf(valor));
    }

    public static BigDecimal stringToBIgDecimal(String valor) {

        return new BigDecimal(valor);
    }

    
    public static void validateDecimalInput(KeyEvent e, JTextField t) {

        char c = e.getKeyChar();
        // Si el carácter NO es un dígito
        if (!Character.isDigit(c)) {
            // Y si el carácter tampoco es un punto
            if (c != '.') {
                e.consume(); // Ignorar si no es dígito ni punto
            } else {
                // Si el carácter ES un punto, verificar si ya hay uno en el texto.
                if (t.getText().trim().contains(".")) {
                    System.out.println("tiene mas de un punto eliminando resto");
                    e.consume(); // Ignorar si ya hay un punto.
                }
            }
        }
    }
    
    public static void validateNumberInput(KeyEvent e) {
        char c = e.getKeyChar();
        if (!Character.isDigit(c)) {
            e.consume(); // Ignorar la entrada no numérica

        }
    }
    
    
    
    public static Double stringToDouble(String v){        
        try {
            return Double.parseDouble(v);
        } catch (Exception e) {
            return 00.00;
        }
    }
    
    public static Integer stringToInt(String v){        
        try {
            return Integer.parseInt(v);
        } catch (Exception e) {
            return 0;
        }
    }
    
}
