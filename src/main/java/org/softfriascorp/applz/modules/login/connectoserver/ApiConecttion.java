/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.softfriascorp.applz.modules.login.connectoserver;

import org.softfriascorp.applz.config.Urls.UrlServer;
import org.springframework.web.reactive.function.client.WebClient;

/**
 *
 * @author usuario
 */
public class ApiConecttion {
    
    private static final WebClient webClient = WebClient.builder()
            .baseUrl(UrlServer.API_URL_BASE) // puedes dejar solo el host/base
            .defaultHeader("Content-Type", "application/json")
            .build();

    public ApiConecttion() {
    }

    public static WebClient getWebClient() {
        return webClient;
    }
    
}
