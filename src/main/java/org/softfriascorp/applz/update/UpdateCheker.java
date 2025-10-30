/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.softfriascorp.applz.update;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import org.json.JSONObject;
import org.softfriascorp.applz.config.Urls.UrlServer;

/**
 *
 * @author usuario
 */
public class UpdateCheker {
    
public static JSONObject getServerVersion() {
        try {
            URL url = new URL(UrlServer.UPDATE_VALIDATE);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            
            BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String inputLine;
            StringBuilder response = new StringBuilder();
            
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();
            
            return new JSONObject(response.toString());
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
public class VersionComparator {
    public static boolean isNewerVersion(String local, String remote) {
        String[] localParts = local.split("\\.");
        String[] remoteParts = remote.split("\\.");
        for (int i = 0; i < Math.max(localParts.length, remoteParts.length); i++) {
            int l = i < localParts.length ? Integer.parseInt(localParts[i]) : 0;
            int r = i < remoteParts.length ? Integer.parseInt(remoteParts[i]) : 0;
            if (r > l) return true;
            else if (r < l) return false;
        }
        return false;
    }
}

}
