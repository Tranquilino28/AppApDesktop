/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.softfriascorp.applz.update;

/**
 *
 * @author usuario
 */
import java.io.*;

/**
 * Reemplaza el archivo JAR actual por el nuevo y reinicia la aplicación.
 */
public class AppUpdater {

    public void replaceAndRestart(File newJar) throws IOException {
        String currentJar = new File(
                AppUpdater.class.getProtectionDomain().getCodeSource().getLocation().getPath()
        ).getAbsolutePath();

        File script = new File("update_script.bat");
        try (PrintWriter writer = new PrintWriter(new FileWriter(script))) {
            writer.println("@echo off");
            writer.println("timeout /t 2 >nul");
            writer.println("copy /Y \"" + newJar.getAbsolutePath() + "\" \"" + currentJar + "\"");
            writer.println("start java -jar \"" + currentJar + "\"");
            writer.println("del \"%~f0\"");
        }

        Runtime.getRuntime().exec("cmd /c start " + script.getAbsolutePath());
        // Limpia posibles estados de descarga previos
        new UpdateDownloader().clearState();

        System.exit(0);
    }
}
