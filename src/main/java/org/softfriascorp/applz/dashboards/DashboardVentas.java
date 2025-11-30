/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.softfriascorp.applz.dashboards;

/**
 *
 * @author usuario
 */


import org.jfree.chart.*;
import org.jfree.chart.plot.*;
import org.jfree.data.category.*;
import org.jfree.data.general.DefaultPieDataset;

import javax.swing.*;
import java.awt.*;
import java.util.Map;

public class DashboardVentas extends JPanel {

    public DashboardVentas(Map<String, Double> ventasMensuales, 
                           Map<String, Double> ventasDiarias, 
                           Map<String, Double> ventasAnuales) {

        setLayout(new GridLayout(1, 3, 10, 10));

        add(crearGraficoBarras("Ventas Mensuales", ventasMensuales));
        add(crearGraficoBarras("Ventas Diarias", ventasDiarias));
        add(crearGraficoTorta("Ventas Anuales", ventasAnuales));
    }

    private JPanel crearGraficoBarras(String titulo, Map<String, Double> data) {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();

        data.forEach((categoria, valor) -> dataset.addValue(valor, "Ventas", categoria));

        JFreeChart chart = ChartFactory.createBarChart(
                titulo,
                "Periodo",
                "Monto ($)",
                dataset,
                PlotOrientation.VERTICAL,
                false, true, false);

        ChartPanel panel = new ChartPanel(chart);
        return panel;
    }

    private JPanel crearGraficoTorta(String titulo, Map<String, Double> data) {
        DefaultPieDataset dataset = new DefaultPieDataset();
        data.forEach(dataset::setValue);

        JFreeChart chart = ChartFactory.createPieChart(titulo, dataset, true, true, false);
        ChartPanel panel = new ChartPanel(chart);
        return panel;
    }
}
