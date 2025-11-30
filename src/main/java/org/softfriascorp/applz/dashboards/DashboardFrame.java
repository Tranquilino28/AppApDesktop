/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.softfriascorp.applz.dashboards;

/**
 *
 * @author usuario
 */


import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import static org.softfriascorp.applz.dashboards.VentasUtils.generarVentasDiarias;

public class DashboardFrame extends JFrame {

    private DashboardVentasDiarias panelVentasDiarias;

    public DashboardFrame() {
        setTitle("📊 Ventas Diarias por Mes");
        setSize(900, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));

        // Datos simulados (por ejemplo, enero tiene ventas en algunos días)
        Map<Integer, Double> enero = Map.of(1, 200.0, 5, 350.0, 10, 500.0, 25, 1000.0);
        Map<Integer, Double> febrero = Map.of(2, 150.0, 14, 600.0, 20, 700.0, 30,850.67);
        Map<Integer, Double> marzo = Map.of(3, 100.0, 15, 800.0);
        Map<Integer, Double> abril = Map.of(4, 100.0, 15, 800.0,23,1500.78);
        
        
        Map<String, Map<Integer, Double>> datos = Map.of(
                "Enero", enero,
                "Febrero", febrero,
                "Marzo", marzo
                ,"Abril", abril
        );

        JComboBox<String> comboMes = new JComboBox<>(new String[]{"Enero", "Febrero", "Marzo","Abril"});
        comboMes.setFont(new Font("Segoe UI", Font.BOLD, 16));

        // Panel inicial (Enero)
        Map<String, Double> ventasIniciales = generarVentasDiarias(2025, 1, enero);
        panelVentasDiarias = new DashboardVentasDiarias(ventasIniciales);

        // Evento de cambio de mes
        comboMes.addActionListener(e -> {
            String mesSeleccionado = (String) comboMes.getSelectedItem();
            int mesNumero = switch (mesSeleccionado) {
                case "Enero" -> 1;
                case "Febrero" -> 2;
                case "Marzo" -> 3;
                case "abril" -> 4;
                default -> 1;
            };

            Map<Integer, Double> datosMes = datos.getOrDefault(mesSeleccionado, new HashMap<>());
            Map<String, Double> ventas = generarVentasDiarias(2025, mesNumero, datosMes);

            panelVentasDiarias.actualizarDataset(ventas);
        });

        add(comboMes, BorderLayout.NORTH);
        add(panelVentasDiarias, BorderLayout.CENTER);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new DashboardFrame().setVisible(true));
    }

/*
    public DashboardFrame() {
        setTitle("📊 Panel de Control de Ventas");
        setSize(1200, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));

        // Datos simulados (en tu caso los traerás del backend o BD)
        Map<String, Double> ventasMensuales = new LinkedHashMap<>();
        ventasMensuales.put("Enero", 120000.0);
        ventasMensuales.put("Febrero", 150000.0);
        ventasMensuales.put("Marzo", 100000.0);

        Map<String, Double> ventasDiarias = new LinkedHashMap<>();
        ventasDiarias.put("Lunes", 50000.0);
        ventasDiarias.put("Martes", 45000.0);
        ventasDiarias.put("Miércoles", 60000.0);

        Map<String, Double> ventasAnuales = new LinkedHashMap<>();
        ventasAnuales.put("2023", 900000.0);
        ventasAnuales.put("2024", 1200000.0);

        Map<String, Double> topClientes = new LinkedHashMap<>();
        topClientes.put("Carlos", 200000.0);
        topClientes.put("Ana", 150000.0);
        topClientes.put("Pedro", 120000.0);

        // Paneles
        JTabbedPane tabs = new JTabbedPane();
        tabs.addTab("📈 Ventas", new DashboardVentas(ventasMensuales, ventasDiarias, ventasAnuales));
        tabs.addTab("🧍 Clientes", new DashboardClientes(topClientes));

        add(tabs, BorderLayout.CENTER);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new DashboardFrame().setVisible(true));
    }*/
}
