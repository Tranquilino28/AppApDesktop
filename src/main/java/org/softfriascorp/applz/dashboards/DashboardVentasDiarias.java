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
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

import javax.swing.*;
import java.awt.*;
import java.util.Map;

public class DashboardVentasDiarias extends JPanel {

    private ChartPanel chartPanel;
    private DefaultCategoryDataset dataset;

    public DashboardVentasDiarias(Map<String, Double> ventasIniciales) {
        setLayout(new BorderLayout());

        dataset = new DefaultCategoryDataset();
        actualizarDataset(ventasIniciales);

        JFreeChart chart = ChartFactory.createBarChart(
                "Ventas Diarias del Mes",
                "Día",
                "Monto ($)",
                dataset,
                PlotOrientation.VERTICAL,
                false, true, false
        );

        chartPanel = new ChartPanel(chart);
        add(chartPanel, BorderLayout.CENTER);
    }

    public void actualizarDataset(Map<String, Double> nuevasVentas) {
        dataset.clear();
        nuevasVentas.forEach((dia, monto) -> dataset.addValue(monto, "Ventas", dia));
    }
}

