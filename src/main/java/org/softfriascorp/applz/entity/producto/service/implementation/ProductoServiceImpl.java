/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.softfriascorp.applz.entity.producto.service.implementation;

import com.google.inject.Inject;
import java.util.List;
import org.softfriascorp.applz.config.Urls.UrlServer;
import org.softfriascorp.applz.entity.producto.ProductoDto;
import org.softfriascorp.applz.entity.producto.service.interfaces.ProductoService;
import org.softfriascorp.applz.modules.login_module.connectoserver.ApiConecttion;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
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
    
    private final String PRODUCTOA_ALL = UrlServer.PRODUCTOS_ALL;
    private final String PRODUCTO_SAVE  = UrlServer.PRODUCTO_SAVE;
    private final String PRODUCTO_UPDATE  = UrlServer.PRODUCTO_UPDATE;
    
    
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
    


    @Override
    public List<ProductoDto> allProducts() {
       try{
         
             Flux<ProductoDto> flux = webClient.getWebClient().get()
                    .uri(PRODUCTOA_ALL) // endpoint
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

    @Override
    public ProductoDto save(ProductoDto productoRequest, Boolean update) {
        
        try {
            
            /*ObjectMapper mapper = new ObjectMapper();

            try {
                String jsonBody = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(productoRequest);
                System.out.println("=== JSON ENVIADO A LA API ===");
                System.out.println(jsonBody);
            } catch (Exception e) {
                e.printStackTrace();
            }
*/
            ProductoDto productoResponse = update ? 
                    
                    webClient.getWebClient()                    
                    .put()
                    .uri(PRODUCTO_UPDATE) // endpoint
                    .contentType(MediaType.APPLICATION_JSON)
                    .accept(MediaType.APPLICATION_JSON)
                    .bodyValue(productoRequest)
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
                    .block()
            :
            webClient.getWebClient()                    
                    .post()
                    .uri(PRODUCTO_SAVE) // endpoint
                    .contentType(MediaType.APPLICATION_JSON)
                    .accept(MediaType.APPLICATION_JSON)
                    .bodyValue(productoRequest)
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
                    .block()
            ;

            return productoResponse;

        } catch (RuntimeException e) {

            throw new RuntimeException(e.getMessage(), e);
        }
    
       
    }
}
