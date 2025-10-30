/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.softfriascorp.applz.update;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingWorker;
import org.json.JSONObject;
import org.softfriascorp.applz.update.UpdateCheker.VersionComparator;

/**
 *
 * @author usuario
 */

public class UpdateWorker extends SwingWorker<Void, String> {

    
    private final JLabel statusLabel;

    public UpdateWorker( JLabel statusLabel) {
       
        this.statusLabel = statusLabel;
    }

    @Override
    protected Void doInBackground() {
        try {
            publish("🔄");
        String localVersion = VersionUtil.getLocalVersion();
        JSONObject serverData = UpdateCheker.getServerVersion();
        if (serverData != null) {
            String serverVersion = serverData.getString("version");
            String downloadUrl = serverData.getString("url");

            if (VersionComparator.isNewerVersion(localVersion, serverVersion)) {
               // System.out.println("Nueva versión disponible: " + serverVersion);
                try {
                /*   Updater.downloadUpdate(downloadUrl, ".");
                   System.out.println("Actualización descargada. Reiniciando...");
                   UpdateRunner.replaceAndRestart();
                    
                    JOptionPane.showMessageDialog(null, 
    "Hay una nueva versión disponible. Se actualizará al reiniciar la aplicación.");
*/
                
            // Detectar nombre del jar actual dinámicamente
            String currentJarName = new File(
                UpdateWorker.class.getProtectionDomain().getCodeSource().getLocation().toURI()
            ).getName();

            File currentJar = new File(currentJarName);
            File newJar = new File("update-temp.jar");

            // Descargar nueva versión
            publish("⬇️ Descargando nueva versión...");
            downloadNewVersion(downloadUrl, newJar);

            if (!newJar.exists() || newJar.length() == 0) {
                publish("⚠️ No se encontró nueva actualización.");
                return null;
            }

            publish("⚙");
            Files.deleteIfExists(currentJar.toPath());
            Files.move(newJar.toPath(), currentJar.toPath(), StandardCopyOption.REPLACE_EXISTING);

            publish("✅ Actualización completa.");
            String command = "java -jar " + currentJar.getName();
            Runtime.getRuntime().exec(command);

            System.exit(0);

                } catch (IOException e) {
                    e.printStackTrace();
                }
                
                
            } else {
                statusLabel.setText(localVersion);
            }

        }
        
        } catch (Exception e) {
            e.printStackTrace();
            publish("❌ Error en la actualización: " + e.getMessage());
        }

        return null;
    }

    @Override
    protected void process(java.util.List<String> messages) {
        // Este método se ejecuta en el hilo de la interfaz (seguro para Swing)
        String lastMessage = messages.get(messages.size() - 1);
        statusLabel.setText(lastMessage);
    }

    private void downloadNewVersion(String url, File destination) throws IOException {
        try (InputStream in = new URL(url).openStream()) {
            Files.copy(in, destination.toPath(), StandardCopyOption.REPLACE_EXISTING);
        }
    }
}
