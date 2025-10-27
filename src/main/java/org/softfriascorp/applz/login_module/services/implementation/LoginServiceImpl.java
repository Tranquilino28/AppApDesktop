
package org.softfriascorp.applz.login_module.services.implementation;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

import org.softfriascorp.applz.login_module.services.interfaces.LoginService;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.inject.Inject;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import org.softfriascorp.applz.config.Urls.UrlServer;
import org.softfriascorp.applz.login_module.connectoserver.ApiConecttion;
import org.softfriascorp.applz.login_module.entity.UserPerfilRol;
import org.softfriascorp.applz.login_module.entity.AuthResponsePerfil;
import org.springframework.http.HttpStatusCode;
import reactor.core.publisher.Mono;

/**
 *
 * @author usuario
 */
public class LoginServiceImpl implements LoginService{

    
    private final String LOGIN_URL = UrlServer.USUARIOS_AUTH_LOGIN;  //"https://appap.onrender.com/api/auth/login";
   // private static String token;
  
   private final ApiConecttion webClient;
   
   @Inject
    public LoginServiceImpl(ApiConecttion webClient) {
        this.webClient = webClient;
    }

    
    
    @Override
    public Boolean authUser(String userName, String password) {
         try {
            // Crear el cuerpo del request
            Map<String, String> credentials = new HashMap<>();
            credentials.put("userName", userName);
            credentials.put("password", password);

            System.out.println("json generado : " + credentials.toString());

             AuthResponsePerfil result = webClient.getWebClient().post()
                    .uri(LOGIN_URL) // endpoint
                    .bodyValue(credentials)
                    .retrieve()
                    .onStatus(
                            HttpStatusCode::is4xxClientError,
                            response -> response.bodyToMono(String.class)
                                    .flatMap(body -> Mono.error(
                                    new RuntimeException("Error del cliente ("
                                            + response.statusCode().value() + "): " + body)
                            ))
                    )
                    .onStatus(
                            HttpStatusCode::is5xxServerError,
                            response -> response.bodyToMono(String.class)
                                    .flatMap(body -> Mono.error(
                                    new RuntimeException("Error del servidor ("
                                            + response.statusCode().value() + "): " + body)
                            ))
                    )
                    .bodyToMono(AuthResponsePerfil.class) //se mapea el objeto o resposneObject del backend
                    .block(); // bloquea, en app desktop (no reactivo)

            // Si llega aquí, el login fue exitoso
            if (result != null) {
                UserPerfilRol.setNombre(result.getUserName());
                UserPerfilRol.setRol(result.getRole());
                UserPerfilRol.setSucursalId(result.getSucursal());
                return true;
            }

        } catch (RuntimeException e) {
            // Aquí ya capturamos mensajes personalizados de arriba
            
            throw new RuntimeException(e.getMessage());
            

        } catch (Exception e) {
            // Errores no esperados
            
            System.err.println(e.getMessage());

        }
        
        return false;
    }
}
