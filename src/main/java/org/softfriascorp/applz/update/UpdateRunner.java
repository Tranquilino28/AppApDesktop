/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.softfriascorp.applz.update;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

/**
 *
 * @author usuario
 */
public class UpdateRunner {

    public static void replaceAndRestart() {
        try {
            // Archivos
            String currentJarName = getCurrentJarName();
            File currentJar = new File(currentJarName);
            File newJar = new File("APPLZ-NUEVO.jar");

            if (!newJar.exists()) {
                System.out.println("No hay nueva versión descargada.");
                return;
            }

        // Reemplaza el jar actual con el nuevo
            Files.deleteIfExists(currentJar.toPath());
            Files.move(newJar.toPath(), currentJar.toPath(), StandardCopyOption.REPLACE_EXISTING);

        // Reinicia usando el mismo nombre dinámico
            String command = "java -jar " + currentJar.getName();
            Runtime.getRuntime().exec(command);

        // Cierra la app actual
            System.exit(0);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String getCurrentJarName() {
        try {
            String path = new File(
                    UpdateRunner.class
                            .getProtectionDomain()
                            .getCodeSource()
                            .getLocation()
                            .toURI()
            ).getName();

            return path; // Retorna el nombre real del .jar
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}
