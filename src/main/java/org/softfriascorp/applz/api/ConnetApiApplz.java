    /*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.softfriascorp.applz.api;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

/**
 *
 * @author usuario
 */
public class ConnetApiApplz {
    
    private static final String BASE_URL = "https://appap.onrender.com";

    public static void main(String[] args) throws Exception {
        
        
        LoginHttp.login("frias", "frias");

        /*HttpClient client = HttpClient.newHttpClient();

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(BASE_URL + ""))
                .GET()
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        System.out.println("Código HTTP: " + response.statusCode());
        System.out.println("Respuesta:");
        System.out.println(response.body());*/
    }
}
