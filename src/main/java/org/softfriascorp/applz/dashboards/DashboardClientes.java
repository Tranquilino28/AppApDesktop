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

public class DashboardClientes extends JPanel {

    public DashboardClientes(Map<String, Double> topClientes) {
        setLayout(new BorderLayout());

        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        topClientes.forEach((cliente, total) -> dataset.addValue(total, "Clientes", cliente));

        JFreeChart chart = ChartFactory.createBarChart(
                "Clientes que más compran",
                "Cliente",
                "Total Compras ($)",
                dataset,
                PlotOrientation.HORIZONTAL,
                false, true, false
        );

        ChartPanel chartPanel = new ChartPanel(chart);
        add(chartPanel, BorderLayout.CENTER);
    }
}
