/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.softfriascorp.applz.entity.venta.service.implementation;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.inject.Inject;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.softfriascorp.applz.config.Urls.UrlServer;
import org.softfriascorp.applz.cuenta_module.submodules.cuenta_module.service.interfaces.CuentaService;
import org.softfriascorp.applz.entity.detallesventa.DetallesVenta;
import org.softfriascorp.applz.entity.maestra.Maestra;
import org.softfriascorp.applz.entity.producto.ProductoDto;
import org.softfriascorp.applz.entity.venta.Venta;
import org.softfriascorp.applz.entity.venta.VentaRequest;
import org.softfriascorp.applz.entity.venta.service.interfaces.VentaService;
import org.softfriascorp.applz.login_module.connectoserver.ApiConecttion;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

/**
 *
 * @author usuario
 */
public class VentaServiceImpl implements VentaService {

    private final ApiConecttion webClient;

    String SAVE_VENTA = UrlServer.VENTA_SAVE;

    @Inject
    public VentaServiceImpl(ApiConecttion webConnection) {
        this.webClient = webConnection;
    }

    @Override
    public Venta getVenta(String codigoVenta) {
        try {

            ProductoDto result = webClient.getWebClient().post()
                    .uri(SAVE_VENTA) // endpoint

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

                return null;

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
    public Venta saveVenta(CuentaService cuentaService) {

        try {
            List<DetallesVenta> detallesVentaService = new ArrayList<>(cuentaService.listarProductos().values());

            List<DetallesVenta> detallesVentaRequest = new ArrayList<>();

            VentaRequest ventaRequest = new VentaRequest();

            Maestra metodopago = new Maestra();
            metodopago.setId(97L);

            ventaRequest.setMetodoPago(metodopago);

            detallesVentaService.forEach(dv -> {

                DetallesVenta detalle = new DetallesVenta();
                ProductoDto producto = new ProductoDto();

                producto.setId(dv.getProducto().getId());

                detalle.setCantidad(dv.getCantidad());
                detalle.setPrecioUnitario(dv.getPrecioUnitario());

                detalle.setProducto(producto);

                detallesVentaRequest.add(detalle);
            });

            ventaRequest.setDetalles(detallesVentaRequest);

            ObjectMapper mapper = new ObjectMapper();

            try {
                String jsonBody = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(ventaRequest);
                System.out.println("=== JSON ENVIADO A LA API ===");
                System.out.println(jsonBody);
            } catch (Exception e) {
                e.printStackTrace();
            }

            Venta ventaResponse = webClient.getWebClient().post()
                    .uri(SAVE_VENTA) // endpoint
                    .contentType(MediaType.APPLICATION_JSON)
                    .accept(MediaType.APPLICATION_JSON)
                    .bodyValue(ventaRequest)
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
                    .bodyToMono(Venta.class) //se mapea el objeto o resposneObject del backend
                    .block();

            return ventaResponse;

        } catch (RuntimeException e) {

            throw new RuntimeException(e.getMessage(), e);
        }
    }

}
