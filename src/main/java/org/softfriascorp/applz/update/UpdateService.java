/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.softfriascorp.applz.update;

/**
 *
 * @author usuario
 */

import com.google.inject.Inject;


/**
 * Servicio que gestiona todo el flujo de actualización (inyectable con Guice).
 */
public class UpdateService {

    private final UpdateChecker checker;
    private final UpdateDownloader downloader;
    private final AppUpdater updater;

    @Inject
    public UpdateService(UpdateChecker checker, UpdateDownloader downloader, AppUpdater updater) {
        this.checker = checker;
        this.downloader = downloader;
        this.updater = updater;
    }

    /**
     * Lanza la verificación automática en segundo plano.
     */
    public void checkForUpdatesAsync() {
        new UpdateWorker(checker, downloader, updater).execute();
    }
}
