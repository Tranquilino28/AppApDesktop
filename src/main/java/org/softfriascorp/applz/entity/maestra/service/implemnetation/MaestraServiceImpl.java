/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.softfriascorp.applz.entity.maestra.service.implemnetation;

import com.google.inject.Inject;
import java.util.List;
import org.softfriascorp.applz.config.Urls.UrlServer;
import org.softfriascorp.applz.entity.maestra.Maestra;
import org.softfriascorp.applz.entity.maestra.service.interfaces.MaestraService;
import org.softfriascorp.applz.entity.producto.ProductoDto;
import org.softfriascorp.applz.login_module.connectoserver.ApiConecttion;
import org.springframework.http.HttpStatusCode;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 *
 * @author usuario
 */
public class MaestraServiceImpl implements MaestraService{

      private final String SEARCH_TIPO = UrlServer.MAESTRA_SEARCH_TIPO;
    private final String SEARCH_CATEGORIAS = UrlServer.MAESTRA_SEARCH_CATEGORIAS;   
    private final String SEARCH_CATEGORIA_PRINCIPAL = UrlServer.MAESTRA_SEARCH_CATEGORIAS_PADRE;
    
    private final String PRODUCTOA_ALL = UrlServer.PRODUCTOS_ALL;
    
    
    private final ApiConecttion webClient;

    
    
    @Inject
    public MaestraServiceImpl(ApiConecttion webConnection) {
        this.webClient = webConnection;
    }
    
    @Override
    public Maestra findByTipo(String nombreCorto) {
       
         try{
         
             Maestra result = webClient.getWebClient().get()
                    .uri(SEARCH_TIPO+"/"+nombreCorto) // endpoint
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
                    .bodyToMono(Maestra.class) //se mapea el objeto o resposneObject del backend
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
    public List<Maestra> findByPadre(String nombreCorto) {
        try{
         
             Flux<Maestra> flux = webClient.getWebClient().get()
                    .uri(SEARCH_CATEGORIAS+"/"+nombreCorto) // endpoint
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
                    .bodyToFlux(Maestra.class) ;//se mapea el objeto o resposneObject del backend
                     
             List<Maestra> result = flux.collectList().block();

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
    public Maestra searcCategoriaPrincipal(String subCategoria) {
        try{
         
             Maestra result = webClient.getWebClient().get()
                    .uri(SEARCH_CATEGORIA_PRINCIPAL+"/"+subCategoria) // endpoint
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
                    .bodyToMono(Maestra.class) //se mapea el objeto o resposneObject del backend
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
    
}
