/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.softfriascorp.applz.update;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

/**
 *
 * @author usuario
 */
public class Updater {
 
    public static void downloadUpdate(String fileURL, String saveDir) throws IOException {
        URL url = new URL(fileURL);
        try (InputStream in = url.openStream();
             FileOutputStream fos = new FileOutputStream(saveDir + "/AppNueva.jar")) {
            byte[] buffer = new byte[4096];
            int bytesRead;
            while ((bytesRead = in.read(buffer)) != -1) {
                fos.write(buffer, 0, bytesRead);
            }
        }
    }
}
 

