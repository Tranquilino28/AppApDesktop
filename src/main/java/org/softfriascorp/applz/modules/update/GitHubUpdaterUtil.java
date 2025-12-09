/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.softfriascorp.applz.modules.update;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 *
 * @author usuario
 */
public class GitHubUpdaterUtil {

    private static final String API_URL
            = "https://api.github.com/repos/Tranquilino28/appAP/releases/latest";

    public static UpdateInfo getLatestRelease() throws Exception {

        URL url = new URL(API_URL);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestProperty("Accept", "application/vnd.github+json");

        try (InputStream in = conn.getInputStream()) {
            String json = new String(in.readAllBytes());
            JSONObject obj = new JSONObject(json);

            String version = obj.getString("tag_name");
            
            JSONArray assets = obj.getJSONArray("assets");
            
          
             Long size = null;
            
            String downloadUrl = null;

            for (int i = 0; i < assets.length(); i++) {
                JSONObject asset = assets.getJSONObject(i);
                if (asset.getString("name").equals("APPLZ-jar-with-dependencies.jar")) {
                    
                    size = asset.getLong("size");
                    
                    downloadUrl = asset.getString("browser_download_url");
                    break;
                }
            }

            return new UpdateInfo(version, size, downloadUrl);
        }
    }

    public record UpdateInfo(String version,Long size, String downloadUrl) {

    }
}
