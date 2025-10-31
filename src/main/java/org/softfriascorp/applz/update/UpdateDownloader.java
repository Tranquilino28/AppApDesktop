/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.softfriascorp.applz.update;

/**
 *
 * @author usuario
 */

import org.json.JSONObject;
import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;
import java.security.MessageDigest;
import java.security.DigestInputStream;

/**
 * Descarga el nuevo archivo JAR con soporte de reanudación y verificación SHA-256.
 */
public class UpdateDownloader {

    private final File stateFile = new File("update/state.json");

    public File download(String url) throws IOException {
        File tempDir = new File("update");
        if (!tempDir.exists()) {
            tempDir.mkdirs();
        }

        File output = new File(tempDir, "app_new.jar");

        JSONObject prevState = loadState();
        long downloadedBytes = (output.exists()) ? output.length() : 0;

        // Si hay estado previo y misma URL, continuar desde donde iba
        if (prevState != null && prevState.optString("url").equals(url)) {
            downloadedBytes = prevState.optLong("bytes", downloadedBytes);
            System.out.println("Reanudando descarga desde byte " + downloadedBytes);
        }

        // Si ya existe el archivo y su hash coincide, no descargar de nuevo
        String savedHash = loadSavedHash(output);
        if (output.exists() && savedHash != null) {
            String actualHash = calculateSHA256(output);
            if (savedHash.equalsIgnoreCase(actualHash)) {
                System.out.println("Archivo ya descargado y verificado. Se omite descarga.");
                return output; // ya está completo
            }
        }

        URL downloadUrl = new URL(url);
        URLConnection connection = downloadUrl.openConnection();
        if (downloadedBytes > 0) {
            connection.setRequestProperty("Range", "bytes=" + downloadedBytes + "-");
        }

        try (InputStream in = connection.getInputStream();
             RandomAccessFile raf = new RandomAccessFile(output, "rw")) {

            raf.seek(downloadedBytes);
            byte[] buffer = new byte[8192];
            int bytesRead;
            long totalBytes = downloadedBytes;

            while ((bytesRead = in.read(buffer)) != -1) {
                raf.write(buffer, 0, bytesRead);
                totalBytes += bytesRead;
                saveState(url, totalBytes, null); // guardar progreso cada bloque
            }
        }

        // ✅ Calcular y guardar hash final
        String finalHash = calculateSHA256(output);
        saveHash(output, finalHash);
        System.out.println("Descarga completa. Hash guardado: " + finalHash);

        // Descarga finalizada: borrar estado
        clearState();

        return output;
    }

    public String calculateSHA256(File file) throws IOException {
        try (InputStream in = new FileInputStream(file)) {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            DigestInputStream dis = new DigestInputStream(in, digest);
            byte[] buffer = new byte[8192];
            while (dis.read(buffer) != -1) { /* leer completo */ }
            byte[] hash = digest.digest();
            StringBuilder hex = new StringBuilder();
            for (byte b : hash) {
                hex.append(String.format("%02x", b));
            }
            return hex.toString();
        } catch (Exception e) {
            throw new IOException("Error al calcular hash SHA-256", e);
        }
    }

    /**
     * Guarda el estado actual de la descarga (versión, tamaño, hash parcial).
     */
    private void saveState(String url, long downloadedBytes, String hash) {
        try {
            JSONObject json = new JSONObject();
            json.put("url", url);
            json.put("bytes", downloadedBytes);
            if (hash != null) {
                json.put("hash", hash);
            }
            Files.writeString(stateFile.toPath(), json.toString(),
                    StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Carga el estado previo de la descarga si existe.
     */
    private JSONObject loadState() {
        try {
            if (stateFile.exists()) {
                String content = Files.readString(stateFile.toPath());
                return new JSONObject(content);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Borra el estado al finalizar la actualización.
     */
    public void clearState() {
        if (stateFile.exists()) {
            stateFile.delete();
        }
    }

    public void saveHash(File jarFile, String hash) {
        try {
            File hashFile = new File(jarFile.getAbsolutePath() + ".sha256");
            try (FileWriter fw = new FileWriter(hashFile, false)) {
                fw.write(hash);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String loadSavedHash(File jarFile) {
        File hashFile = new File(jarFile.getAbsolutePath() + ".sha256");
        if (!hashFile.exists()) {
            return null;
        }
        try {
            return Files.readString(hashFile.toPath()).trim();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
