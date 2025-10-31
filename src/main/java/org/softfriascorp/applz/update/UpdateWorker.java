/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.softfriascorp.applz.update;

/**
 *
 * @author usuario
 */
import javax.swing.*;
import java.io.File;
import java.io.IOException;
import java.net.URL;

/**
 * Ejecuta la verificación y descarga de la actualización en segundo plano.
 */
public class UpdateWorker extends SwingWorker<Void, Void> {

    private final UpdateChecker checker;
    private final UpdateDownloader downloader;
    private final AppUpdater updater;

    public UpdateWorker(UpdateChecker checker, UpdateDownloader downloader, AppUpdater updater) {
        this.checker = checker;
        this.downloader = downloader;
        this.updater = updater;
    }

    @Override
    protected Void doInBackground() {
        try {
            UpdateChecker.UpdateInfo info = checker.checkForUpdate();

            if (info != null) {
                System.out.println("Nueva versión encontrada: " + info.version);
                File newJar = downloader.download(info.downloadUrl);
                String hash = downloader.calculateSHA256(newJar);
                System.out.println("SHA-256 de la actualización: " + hash);

                // (Opcional) Validar contra hash remoto
                try {
                    String expectedHash = new String(new URL(info.downloadUrl + ".sha256").openStream().readAllBytes()).trim();
                    if (!expectedHash.equalsIgnoreCase(hash)) {
                        throw new IOException("El hash no coincide, descarga corrupta.");
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Error de integridad: " + e.getMessage(), "Actualización abortada", JOptionPane.ERROR_MESSAGE);
                    return null;
                }
                
                
                SwingUtilities.invokeLater(() -> {
                    
                    int res = JOptionPane.showConfirmDialog(null,
                            "Se ha descargado una nueva versión (" + info.version + ").\n¿Deseas reiniciar para aplicar la actualización?",
                            "Actualización disponible",
                            JOptionPane.YES_NO_OPTION);
                    if (res == JOptionPane.YES_OPTION) {
                        try {
                            updater.replaceAndRestart(newJar);
                        } catch (Exception e) {
                            e.printStackTrace();
                            JOptionPane.showMessageDialog(null,
                                    "Error al aplicar la actualización:\n" + e.getMessage(),
                                    "Error", JOptionPane.ERROR_MESSAGE);
                        }
                    }
                });
            } else {
                System.out.println("No hay actualizaciones disponibles.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
