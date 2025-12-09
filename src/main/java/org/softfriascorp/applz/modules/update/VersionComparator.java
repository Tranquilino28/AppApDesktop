/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.softfriascorp.applz.modules.update;

/**
 *
 * @author usuario
 */
public class VersionComparator {

    public static int compare(String v1, String v2) {
        
        System.out.println("se compararan \n"
                + v1 +" != "+ v2);
        v1 = v1.replaceAll("[^0-9\\\\.]", "");
        v2 = v2.replaceAll("[^0-9\\\\.]", "");
        
        System.out.println("se realizo la limpieza \n"
                + v1 + " != "+v2);

        String[] a = v1.split("\\.");
        String[] b = v2.split("\\.");

        for (int i = 0; i < Math.max(a.length, b.length); i++) {
            int ai = i < a.length ? Integer.parseInt(a[i]) : 0;
            int bi = i < b.length ? Integer.parseInt(b[i]) : 0;
            if (ai != bi) return ai - bi;
        }
        return 0;
    }
}

