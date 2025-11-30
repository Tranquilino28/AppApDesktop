/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package org.softfriascorp.applz;

import com.google.inject.Guice;
import com.google.inject.Injector;
import org.softfriascorp.applz.config.AppControllers;
import org.softfriascorp.applz.config.module.AppModule;

/**
 *
 * @author usuario
 */
public class APPLZ {

    public static void main(String[] args) {
        
       
        
        
/*
        String localVersion = VersionUtil.getLocalVersion();
        JSONObject serverData = UpdateCheker.getServerVersion();

        if (serverData != null) {
            String serverVersion = serverData.getString("version");
            String downloadUrl = serverData.getString("url");

            if (VersionComparator.isNewerVersion(localVersion, serverVersion)) {
               System.out.println("Nueva versión disponible: " + serverVersion);
                try {
                    Updater.downloadUpdate(downloadUrl, ".");
                    System.out.println("Actualización descargada. Reiniciando...");
                    UpdateRunner.replaceAndRestart();
                    
                    JOptionPane.showMessageDialog(null, 
    "Hay una nueva versión disponible. Se actualizará al reiniciar la aplicación.");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else {
                System.out.println("Tu aplicación está actualizada.");
            }

        }
        */
        Injector inject = Guice.createInjector(new AppModule());

        AppControllers appControllers = inject.getInstance(AppControllers.class);
        appControllers.initAppController();
        
    }

}
