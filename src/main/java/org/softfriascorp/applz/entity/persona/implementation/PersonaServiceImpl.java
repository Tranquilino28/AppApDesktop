/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.softfriascorp.applz.entity.persona.implementation;


import com.google.inject.Inject;
import org.softfriascorp.applz.config.Urls.UrlServer;
import org.softfriascorp.applz.entity.persona.Persona;
import org.softfriascorp.applz.entity.persona.interfaces.PersonaService;
import org.softfriascorp.applz.modules.login_module.connectoserver.ApiConecttion;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import reactor.core.publisher.Mono;

/**
 *
 * @author usuario
 */
public class PersonaServiceImpl implements PersonaService{
    
    private final String PERSONA_SEARCH_S1 = UrlServer.PERSONA_SEARCH_1S;
    private final String PERSONA_SAVE = UrlServer.PERSONA_SEARCH_1S;
    
     private final ApiConecttion webClient;

    
    
    @Inject
    public PersonaServiceImpl(ApiConecttion webConnection) {
        this.webClient = webConnection;
    }

    @Override
    public Persona searchPersonaByIdentificacion(String identificacion) {
        
        
        Persona persona = null;
        try{
            
         persona = webClient
                .getWebClient()
                .get()
                .uri(PERSONA_SEARCH_S1+"/"+identificacion)
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
                    .bodyToMono(Persona.class) //se mapea el objeto o resposneObject del backend
                    .block(); // bloquea, en app desktop (no reactivo)
        
        return persona;
        
        } catch (RuntimeException e) {
            // Aquí ya capturamos mensajes personalizados de arriba
            
            throw new RuntimeException(e.getMessage(), e);
            

        } catch (Exception e) {
            // Errores no esperados
            throw new RuntimeException(e.getMessage(), e);
           
        } 

    }
    @Override
    public Persona save(Persona p) {
        
        
        Persona persona = null;
        try{
            
        persona = webClient.getWebClient().post()
                .uri(PERSONA_SAVE)
                .contentType(MediaType.APPLICATION_JSON)
                    .accept(MediaType.APPLICATION_JSON)
                    .bodyValue(persona)
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
                    .bodyToMono(Persona.class) //se mapea el objeto o resposneObject del backend
                    .block(); // bloquea, en app desktop (no reactivo)
        
        return persona;
        
        } catch (RuntimeException e) {
            // Aquí ya capturamos mensajes personalizados de arriba
            
            throw new RuntimeException(e.getMessage(), e);
            

        } catch (Exception e) {
            // Errores no esperados
            throw new RuntimeException(e.getMessage(), e);
           
        } 

    }
    
    
    
    
    
}
