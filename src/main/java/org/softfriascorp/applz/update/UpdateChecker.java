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
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Verifica la última versión disponible en GitHub Releases.
 */
public class UpdateChecker {

    private final String apiUrl;
    private final String currentVersion;

    public UpdateChecker(String githubUser, String repoName, String currentVersion) {
        this.apiUrl = "https://api.github.com/repos/" + githubUser + "/" + repoName + "/releases/latest";
        this.currentVersion = currentVersion;
    }

    public UpdateInfo checkForUpdate() {
        try {
            HttpURLConnection conn = (HttpURLConnection) new URL(apiUrl).openConnection();
            conn.setRequestProperty("Accept", "application/vnd.github+json");
            conn.setRequestProperty("User-Agent", "Java-Update-Agent");

            if (conn.getResponseCode() == 200) {
                BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                StringBuilder response = new StringBuilder();
                String line;
                while ((line = reader.readLine()) != null) response.append(line);
                reader.close();

                JSONObject json = new JSONObject(response.toString());
                String latestVersion = json.getString("tag_name");
                String assetUrl = json.getJSONArray("assets").getJSONObject(0).getString("browser_download_url");

                if (!currentVersion.equalsIgnoreCase(latestVersion)) {
                    return new UpdateInfo(latestVersion, assetUrl);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null; // No hay actualización o error
    }

    public static class UpdateInfo {
        public final String version;
        public final String downloadUrl;

        public UpdateInfo(String version, String downloadUrl) {
            this.version = version;
            this.downloadUrl = downloadUrl;
        }
    }
}
