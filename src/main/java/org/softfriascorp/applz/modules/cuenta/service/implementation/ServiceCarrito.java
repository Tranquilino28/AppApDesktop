/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.softfriascorp.applz.modules.cuenta.service.implementation;

import com.google.inject.Inject;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import org.softfriascorp.applz.entity.detallesventa.DetallesVenta;
import org.softfriascorp.applz.entity.producto.ProductoDto;
import org.softfriascorp.applz.modules.cuenta.service.interfaces.CuentaService;
import org.softfriascorp.applz.entity.maestra.Maestra;
import org.softfriascorp.applz.entity.persona.Persona;
import org.softfriascorp.applz.modules.cuenta.service.interfaces.ConversorDeMedida;
import org.softfriascorp.applz.modules.cuenta.service.interfaces.PriceCalculator;
import org.softfriascorp.applz.modules.login.entity.AuthResponsePerfil;
import org.softfriascorp.applz.modules.login.entity.UserPerfilRol;

/**
 *
 * @author usuario
 */
public class ServiceCarrito implements CuentaService<String, DetallesVenta> {

    private BigDecimal valorRecibido;

    private Maestra tipoPago;

    private AuthResponsePerfil empleado;

    private Persona cliente;

    private Maestra metdoPago;

    private Maestra estado;

    private final Map<String, DetallesVenta> detallesMap = new LinkedHashMap<>();

    ConversorDeMedida conversorMedida;
    PriceCalculator priceCalculator;

    @Inject
    public ServiceCarrito(ConversorDeMedida conversorMedida, PriceCalculator priceCalculator) {
        this.conversorMedida = conversorMedida;
        this.priceCalculator = priceCalculator;
    }

    private static final Set<String> UNIDADES_PESABLES
            = Set.of("LT", "GR", "KG");

    public boolean esPesable(String unidadMedida) {
        return UNIDADES_PESABLES.contains(unidadMedida.toUpperCase());
    }

    @Override
    public void agregarProducto(ProductoDto producto, String cantidad) {

        String codigo = producto.getCodigoBarras();

        DetallesVenta detalle;

        if (detallesMap.containsKey(codigo)) {
            detalle = detallesMap.get(codigo);

            BigDecimal cantidadActual = new BigDecimal(detalle.getCantidad());

            BigDecimal cantActualizada = cantidadActual.add(new BigDecimal(cantidad));

            if (esPesable(detalle.getProducto().getMedida())) {

                detalle.setCantidad(cantActualizada.toString());

                BigDecimal gramos = conversorMedida.convertirAGramos(cantActualizada, detalle.getProducto().getMedida());

                BigDecimal valorGramo = priceCalculator.calcularPrecioPorGramo(detalle.getPrecioUnitario(), detalle.getProducto().getMedida());

                detalle.setSubTotal(valorGramo.multiply(gramos));

                return;
            }

            detalle.setCantidad(String.valueOf(cantActualizada.intValueExact()));
            detalle.setSubTotal(detalle.getPrecioUnitario().multiply(cantActualizada));

            return;

        }

        detalle = new DetallesVenta();

        detalle.setPrecioUnitario(producto.getPrecioFinal());

        detalle.setProducto(producto);

        if (esPesable(producto.getMedida())) {

            detalle.setCantidad(cantidad);

            BigDecimal gramos = conversorMedida.convertirAGramos(new BigDecimal(cantidad), producto.getMedida());

            BigDecimal valorGramo = priceCalculator.calcularPrecioPorGramo(detalle.getPrecioUnitario(), detalle.getProducto().getMedida());

            detalle.setSubTotal(valorGramo.multiply(gramos));

        } else {
            detalle.setCantidad(cantidad);

            detalle.calcularSubTotal();
        }

        detallesMap.put(codigo, detalle);

    }

    @Override
    public void eliminarProducto(String codigoBarra) {
        detallesMap.remove(codigoBarra);
    }

    @Override
    public void actualizarCantidad(String codigoBarra, String nuevaCantidad) {
        if (detallesMap.containsKey(codigoBarra)) {

            DetallesVenta producto = detallesMap.get(codigoBarra);

            producto.setCantidad(nuevaCantidad);

            producto.calcularSubTotal();
        }
    }

    @Override
    public DetallesVenta buscarProducto(String codigoBarra) {
        return detallesMap.get(codigoBarra);
    }

    @Override
    public Map<String, DetallesVenta> listarProductos() {
        return detallesMap;
    }

    @Override
    public BigDecimal calcularTotal() {
        return detallesMap.values().stream()
                .map(DetallesVenta::getSubTotal)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    @Override
    public void limpiarVenta() {
        detallesMap.clear();
    }

    @Override
    public boolean tieneProductos() {
        return !detallesMap.isEmpty();
    }

    @Override
    public boolean productoExiste(String codigoBarra) {

        return detallesMap.containsKey(codigoBarra);
    }

    @Override
    public BigDecimal getCambio() {

        return calcularTotal().subtract(valorRecibido);
    }

    @Override
    public void setTipoDePago(Maestra tipoDePago) {
        this.tipoPago = tipoDePago;
    }

    @Override
    public Maestra getTipoDePago() {
        return tipoPago;
    }

    @Override
    public void setValorRecibido(BigDecimal valorRecibido) {
        this.valorRecibido = valorRecibido;

    }

    @Override
    public BigDecimal getValorRecibido() {
        return valorRecibido;
    }

    @Override
    public BigDecimal getSaldoPendiente() {
        return calcularTotal().subtract(valorRecibido);
    }

    @Override
    public void setMetodoPago(Maestra metodoPago) {
        this.metdoPago = metodoPago;
    }

    @Override
    public void setEmpleado(AuthResponsePerfil empleado) {
        this.empleado = empleado;
    }

    @Override
    public void setEstado(Maestra estado) {
        this.estado = estado;
    }

    @Override
    public Maestra getMetodoPago() {
        return this.metdoPago;
    }

    @Override
    public AuthResponsePerfil getEmpleado() {
        return this.empleado;
    }

    @Override
    public Maestra getEstado() {
        return this.estado;
    }

    @Override
    public void setCliente(Persona cliente) {
        this.cliente = cliente;
    }

    @Override
    public Persona getCliente() {
        return this.cliente;
    }

}
