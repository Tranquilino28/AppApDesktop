/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.softfriascorp.applz.Utils;

import java.util.List;
import javax.swing.JComboBox;
import org.softfriascorp.applz.entity.maestra.Maestra;

/**
 *
 * @author usuario
 */
public class UtilManagerCombobox {
    public static  void selectItemCmbx(JComboBox cmbBox, Maestra m) {

        cmbBox.removeAllItems();
        cmbBox.addItem(m);
        cmbBox.setSelectedItem(m);

    }

    public static  void setItemsCmbx(JComboBox cmbBox, List<Maestra> lista, String tipo) {

        removeItemsCmbx(cmbBox,tipo);

        lista.forEach(lis -> {
            cmbBox.addItem(lis);
        });

    }

    public static void removeItemsCmbx(JComboBox cmbBox, String tipo) {

        cmbBox.removeAllItems();
        cmbBox.addItem(tipo);

    }
}
