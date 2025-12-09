/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.softfriascorp.applz.modules.update;

import java.io.BufferedInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.PrintWriter;
import java.net.URL;
import java.net.URLConnection;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.logging.Logger;
import org.softfriascorp.applz.Utils.LoggerUtil;

/**
 *
 * @author usuario
 */
public class Updater {

    private static final Logger log = LoggerUtil.getLogger("Updater", "updater.log");

    public static void downloadUpdate(String size, String downloadUrl) {
        try {
            log.info("Descargando actualización...");
            
            InputStream in = new URL(downloadUrl).openStream();

            Files.copy(
                    in,
                    Paths.get("APPLZ-new.jar"),
                    StandardCopyOption.REPLACE_EXISTING
            );

            System.out.println("Descarga completada. Se aplicará al cerrar la aplicación.");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
public static void setupUpdateOnExit() {
    Runtime.getRuntime().addShutdownHook(new Thread(() -> {
        try {
            Path oldJar = Paths.get("APPLZ-jar-with-dependencies.jar");
            Path newJar = Paths.get("APPLZ-new.jar");

            if (Files.exists(newJar)) {
                System.out.println("Aplicando actualización...");

                // Borrar jar antiguo
                if (Files.exists(oldJar)) {
                    Files.delete(oldJar);
                }

                // Renombrar
                Files.move(newJar, oldJar);

                System.out.println("Actualización completada correctamente.");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }));
}



    public interface ProgressListener {
        void onProgress(int percentage);
    }

    public static void downloadWithProgress(String fileURL, String saveAs, long totalSize, ProgressListener listener)
            throws Exception {

        URL url = new URL(fileURL);
        URLConnection connection = url.openConnection();

        try (InputStream in = new BufferedInputStream(connection.getInputStream());
             FileOutputStream out = new FileOutputStream(saveAs)) {

            byte[] buffer = new byte[8192];
            long downloaded = 0;
            int bytesRead;

            while ((bytesRead = in.read(buffer)) != -1) {
                out.write(buffer, 0, bytesRead);
                downloaded += bytesRead;

                int percent = (int) ((downloaded * 100) / totalSize);
                listener.onProgress(percent);
            }
        }
    }

}
