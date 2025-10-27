/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.softfriascorp.applz.entity.producto.service.implementation;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.inject.Inject;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.softfriascorp.applz.config.Urls.UrlServer;
import org.softfriascorp.applz.entity.producto.ProductoDto;
import org.softfriascorp.applz.entity.producto.service.interfaces.ProductoService;
import org.softfriascorp.applz.login_module.connectoserver.ApiConecttion;
import org.softfriascorp.applz.login_module.entity.AuthResponsePerfil;
import org.softfriascorp.applz.login_module.entity.UserPerfilRol;
import org.springframework.http.HttpStatusCode;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 *
 * @author usuario
 */
public class ProductoServiceImpl implements ProductoService{
    
    private final String SEARCH_2S_PRODUCTO = UrlServer.PRODUCTOS_SEARCH_2S;
    private final String SEARCH_3S_PRODUCTO = UrlServer.PRODUCTOS_SEARCH_3S;
    private final String SEARCH_4SPROMO = UrlServer.PRODUCTOS_SEARCH_4SPROMO;
    
    private final ApiConecttion webClient;

    
    
    @Inject
    public ProductoServiceImpl(ApiConecttion webConnection) {
        this.webClient = webConnection;
    }

    @Override
    public ProductoDto findByCodigoBarras(String codigoBarras) {
        try{
         
             ProductoDto result = webClient.getWebClient().get()
                    .uri(SEARCH_4SPROMO+"/"+codigoBarras+"/info-precio") // endpoint
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
                    .bodyToMono(ProductoDto.class) //se mapea el objeto o resposneObject del backend
                    .block(); // bloquea, en app desktop (no reactivo)

            // Si llega aquí, el login fue exitoso
            if (result != null) {
                
                 return result;
               
               
            }

        } catch (RuntimeException e) {
            // Aquí ya capturamos mensajes personalizados de arriba
            
            throw new RuntimeException(e.getMessage(), e);
            

        } catch (Exception e) {
            // Errores no esperados
            throw new RuntimeException(e.getMessage(), e);
            

        }    
        
        return null;
    }

    @Override
    public List<ProductoDto> findByCoincidencia(String coincidencia) {
       try{
         
             Flux<ProductoDto> flux = webClient.getWebClient().get()
                    .uri(SEARCH_3S_PRODUCTO+"/"+coincidencia) // endpoint
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
                    .bodyToFlux(ProductoDto.class) ;//se mapea el objeto o resposneObject del backend
                     
             List<ProductoDto> result = flux.collectList().block();

            // Si llega aquí, el login fue exitoso
            if (result != null) {
                
                 return result;
               
               
            }

        } catch (RuntimeException e) {
            // Aquí ya capturamos mensajes personalizados de arriba
            
            throw new RuntimeException(e.getMessage(), e);
            

        } catch (Exception e) {
            // Errores no esperados
            throw new RuntimeException(e.getMessage(), e);
            

        }    
        
        return null;
        
        
    }
    
}
