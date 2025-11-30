/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.softfriascorp.applz.dashboards;

/**
 *
 * @author usuario
 */
import java.time.LocalDate;
import java.time.YearMonth;
import java.util.LinkedHashMap;
import java.util.Map;

public class VentasUtils {

    // Genera un mapa con ventas diarias del mes (rellena días faltantes con 0)
    public static Map<String, Double> generarVentasDiarias(int año, int mes, Map<Integer, Double> datos) {
        Map<String, Double> ventas = new LinkedHashMap<>();

        YearMonth yearMonth = YearMonth.of(año, mes);
        int dias = yearMonth.lengthOfMonth();

        for (int dia = 1; dia <= dias; dia++) {
            double monto = datos.getOrDefault(dia, 0.0);
            ventas.put(String.valueOf(dia), monto);
        }

        return ventas;
    }
}

