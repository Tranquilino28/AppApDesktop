/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.softfriascorp.applz.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 *
 * @author usuario
 */
public class UtilFechaFormat {
    
    public  static String formatFechaDMA(LocalDateTime fecha){
       

        DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");

       
        return fecha.format(formato);
    }
}
