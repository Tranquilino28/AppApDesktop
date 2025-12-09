/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.softfriascorp.applz.entity.inventario.service.implementation;

import com.google.inject.Inject;
import java.util.List;
import org.softfriascorp.applz.config.Urls.UrlServer;
import org.softfriascorp.applz.entity.inventario.service.interfaces.InventarioService;
import org.softfriascorp.applz.entity.producto.ProductoDto;
import org.softfriascorp.applz.modules.login.connectoserver.ApiConecttion;
import org.springframework.http.HttpStatusCode;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 *
 * @author usuario
 */
public class InventarioServiceImpl implements InventarioService {

    private final String GET_PRODUCTS_FILTER_DINAMICS = UrlServer.INVENTARIO_PRODUCTOS_V1;

        private final ApiConecttion webClient;
        
    @Inject
    public InventarioServiceImpl(ApiConecttion webConnection) {
        this.webClient = webConnection;
    }

    @Override
    public List<ProductoDto> searchDinamicFiltersAtributesProducts(String search, Long categoriaId, Integer stockMin, Integer stockMax) {

        String URL = GET_PRODUCTS_FILTER_DINAMICS ;
        
        if(!search.equals("")){
            URL +=  "search=" + search ;
        }
        if(categoriaId != null){
            URL += "&categoriaId=" + categoriaId;
        }        
       
        if (stockMin != null) {
            URL += "&stockMin=" + stockMin;
             System.out.println(stockMin + " || " + stockMax);
        }
        if (stockMax != null) {
            URL += "&stockMax=" + stockMax;
             System.out.println(stockMin + " || " + stockMax);
        }

        try {

            Flux<ProductoDto> flux = webClient.getWebClient().get()
                    .uri(URL) // endpoint
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
                    .bodyToFlux(ProductoDto.class);//se mapea el objeto o resposneObject del backend

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
