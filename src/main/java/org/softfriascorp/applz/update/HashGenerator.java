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
import java.nio.file.*;
import java.security.*;

/**
 * Genera el hash SHA-256 del JAR compilado y lo guarda
 * en un archivo .sha256 junto al mismo.
 * 
 * Uso:
 *   1️⃣ Compila tu app → genera app.jar
 *   2️⃣ Ejecuta esta clase → genera app.jar.sha256
 * 
 *   java -cp build/classes org.softfriascorp.updater.HashGenerator
 */
public class HashGenerator {

    public static void main(String[] args) {
        try {
            // 📍 Ajusta la ruta de tu jar final según tu proyecto
            File jarFile = new File("app.jar");

            if (!jarFile.exists()) {
                System.err.println("❌ No se encontró el archivo " + jarFile.getAbsolutePath());
                return;
            }

            String hash = calculateSHA256(jarFile);
            saveHashFile(jarFile, hash);
            System.out.println("✅ Hash generado y guardado: " + jarFile.getName() + ".sha256");
            System.out.println("🔢 Valor SHA-256: " + hash);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static String calculateSHA256(File file) throws Exception {
        MessageDigest digest = MessageDigest.getInstance("SHA-256");

        try (InputStream is = new FileInputStream(file);
             DigestInputStream dis = new DigestInputStream(is, digest)) {
            byte[] buffer = new byte[8192];
            while (dis.read(buffer) != -1) { /* leer completo */ }
        }

        byte[] hash = digest.digest();
        StringBuilder hex = new StringBuilder();
        for (byte b : hash) {
            hex.append(String.format("%02x", b));
        }

        return hex.toString();
    }

    private static void saveHashFile(File jarFile, String hash) throws IOException {
        Path hashFile = Paths.get(jarFile.getAbsolutePath() + ".sha256");
        Files.writeString(hashFile, hash, StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);
    }
}
