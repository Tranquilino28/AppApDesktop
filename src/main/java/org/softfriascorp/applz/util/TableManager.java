/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.softfriascorp.applz.util;

import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

/**
 *
 * @author usuario
 */
public class TableManager {
    


    private JTable productosTable;
    private DefaultTableModel tableModel;

    // Constructor: Recibe la JTable que va a gestionar
    public TableManager(JTable table) {
        this.productosTable = table;
        // Se asegura de que la tabla use un DefaultTableModel si no lo tiene ya
        // Esto es útil si la JTable fue creada en el diseñador sin un modelo inicial.
        if (!(table.getModel() instanceof DefaultTableModel)) {
            // Definir los nombres de las columnas
            String[] columnNames = {"CODIGO", "Descripción", "Stock"};
            this.tableModel = new DefaultTableModel(columnNames, 0) {
                @Override
                public boolean isCellEditable(int row, int column) {
                    // Aquí puedes definir qué celdas son editables.
                    // Por ejemplo, ninguna editable por defecto, o solo algunas columnas.
                    // Para hacer una columna editable por índice:
                    // return column == 1; // Solo la columna "Descripción" editable
                    return false; // Todas las celdas no editables por defecto
                }
            };
            this.productosTable.setModel(this.tableModel);
        } else {
            this.tableModel = (DefaultTableModel) table.getModel();
            // Asegurarse de que las columnas tengan los nombres correctos si ya hay un modelo
            String[] currentColumnNames = {"Código", "Descripción", "Stock"};
            for (int i = 0; i < currentColumnNames.length; i++) {
                if (i < tableModel.getColumnCount()) {
                    tableModel.setColumnIdentifiers(currentColumnNames); // Esto reinicia los identificadores de columna
                }
            }
        }
        
        // Configurar los anchos de columna automáticamente al instanciar el manager
        setupTableColumns();
    }

    /**
     * Configura los anchos y propiedades de las columnas de la JTable.
     */
    private void setupTableColumns() {
        // Desactivar el auto-redimensionamiento de la tabla
        productosTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        // Obtener el modelo de columnas
        // Columnas: 0=Código, 1=Descripción, 2=Stock

        // --- Columna "Código" (índice 0) ---
        TableColumn codigoColumn = productosTable.getColumnModel().getColumn(0);
        codigoColumn.setPreferredWidth(80);
        codigoColumn.setMinWidth(70);
        codigoColumn.setMaxWidth(120);

        // --- Columna "Descripción" (índice 1) ---
        TableColumn descripcionColumn = productosTable.getColumnModel().getColumn(1);
        descripcionColumn.setPreferredWidth(160);
        descripcionColumn.setMinWidth(10);
        // No se establece maxWidth para permitir expansión, o puedes poner un límite (ej. 500)

        // --- Columna "Stock" (índice 2) ---
        TableColumn stockColumn = productosTable.getColumnModel().getColumn(2);
        stockColumn.setPreferredWidth(60);
        stockColumn.setMinWidth(40);
        stockColumn.setMaxWidth(80);
        
        // Opcional: Alinear el texto de la columna Stock a la derecha para números
        DefaultTableCellRenderer rightRenderer = new DefaultTableCellRenderer();
        rightRenderer.setHorizontalAlignment(JLabel.RIGHT);
        stockColumn.setCellRenderer(rightRenderer);
    }
    
    //metodos para centrar valores en columnas
    public static void centrarCeldas(JTable tabla, int celda_1){
         // Crear renderizador centrado
        DefaultTableCellRenderer centrado = new DefaultTableCellRenderer();
        centrado.setHorizontalAlignment(SwingConstants.CENTER);

        // Aplicar el renderizador solo a las columnas de Código y Stock
        tabla.getColumnModel().getColumn(celda_1).setCellRenderer(centrado); // Código
        
    }
    public static void centrarCeldas(JTable tabla, int celda_1,int celda_2){
         // Crear renderizador centrado
        DefaultTableCellRenderer centrado = new DefaultTableCellRenderer();
        centrado.setHorizontalAlignment(SwingConstants.CENTER);

        // Aplicar el renderizador solo a las columnas de Código y Stock
        tabla.getColumnModel().getColumn(celda_1).setCellRenderer(centrado); // Código
        tabla.getColumnModel().getColumn(celda_2).setCellRenderer(centrado); // Stock
        
        
    }
    
    public static void centrarCeldas(JTable tabla, int celda_1,int celda_2,int celda_3){
         // Crear renderizador centrado
        DefaultTableCellRenderer centrado = new DefaultTableCellRenderer();
        centrado.setHorizontalAlignment(SwingConstants.CENTER);

        // Aplicar el renderizador solo a las columnas de Código y Stock
        tabla.getColumnModel().getColumn(celda_1).setCellRenderer(centrado); // Código
        tabla.getColumnModel().getColumn(celda_2).setCellRenderer(centrado); // Stock
        tabla.getColumnModel().getColumn(celda_3).setCellRenderer(centrado); // Stock
        
    }
    
    
    
    
    

    /**
     * Añade una nueva fila de datos a la tabla.
     * @param rowData Un arreglo de objetos con los datos de la fila.
     */
    
    
    public void addRow(Object[] rowData) {
        tableModel.addRow(rowData);
    }

    
    
    
    
    
    
    /**
     * Elimina todas las filas de la tabla.
     */
    public void clearTable() {
        tableModel.setRowCount(0);
    }

    /**
     * Retorna el DefaultTableModel asociado a la JTable.
     * Útil si necesitas manipular el modelo directamente (ej. añadir/eliminar filas masivamente).
     */
    public DefaultTableModel getTableModel() {
        return tableModel;
    }

    // Puedes añadir más métodos para interactuar con la tabla si es necesario,
    // como obtener datos de una fila seleccionada, etc.

}
