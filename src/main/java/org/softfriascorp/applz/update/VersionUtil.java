/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.softfriascorp.applz.update;



import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 *
 * @author usuario
 */
public class VersionUtil {

  

        public static String getLocalVersion() {
            try (InputStream input = VersionUtil.class.getResourceAsStream("/version.properties")) {
                Properties prop = new Properties();
                prop.load(input);
                return prop.getProperty("version");
            } catch (IOException ex) {
                ex.printStackTrace();
                return "0.0.0";
            }
        }
    

}
