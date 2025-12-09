/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.softfriascorp.applz.modules.update;

import java.util.logging.Logger;
import org.softfriascorp.applz.Utils.LoggerUtil;

/**
 *
 * @author usuario
 */
public class AutoUpdateManager {

    private static final Logger log = LoggerUtil.getLogger("AutoUpdate", "update.log");
    private static final String CURRENT_VERSION = "v1.0.0";

    public static void checkForUpdates() {
        try {
            GitHubUpdaterUtil.UpdateInfo info = GitHubUpdaterUtil.getLatestRelease();

            if (VersionComparator.compare(info.version(), CURRENT_VERSION) > 0) {
                log.info("Nueva versión disponible: " + info.version() + " d -- " + info.downloadUrl());

                // Lanza updater.jar y finaliza app
                //  Updater.downloadUpdate(info.size(), info.downloadUrl());
                //System.exit(0);
                Updater.downloadWithProgress(
                        info.downloadUrl(),
                        "APPLZ-new.jar",
                        info.size(),
                        percent -> System.out.println("Progreso: " + percent + "%")
                );
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
