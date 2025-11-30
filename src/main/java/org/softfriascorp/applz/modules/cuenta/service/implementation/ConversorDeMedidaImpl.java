package org.softfriascorp.applz.modules.cuenta.service.implementation;



import java.math.BigDecimal;
import java.math.RoundingMode;
import org.softfriascorp.applz.modules.cuenta.service.interfaces.ConversorDeMedida;


public class ConversorDeMedidaImpl implements ConversorDeMedida {

    private static final BigDecimal GRAMOS_POR_LIBRA = new BigDecimal("453.59237");
    private static final BigDecimal GRAMOS_POR_KILO = new BigDecimal("1000");
    private static final BigDecimal ONE = BigDecimal.ONE;

    // ===============================================================
    // CONVERSIONES A GRAMOS
    // ===============================================================


    private BigDecimal librasAGramos(BigDecimal libras) {
        if (libras == null) return BigDecimal.ZERO;
        return libras.multiply(GRAMOS_POR_LIBRA).setScale(6, RoundingMode.HALF_UP);
    }


    private BigDecimal kilosAGramos(BigDecimal kilos) {
        if (kilos == null) return BigDecimal.ZERO;
        return kilos.multiply(GRAMOS_POR_KILO).setScale(6, RoundingMode.HALF_UP);
    }


    private BigDecimal gramosAGramos(BigDecimal gramos) {
        if (gramos == null) return BigDecimal.ZERO;
        return gramos.setScale(6, RoundingMode.HALF_UP);
    }

    // ===============================================================
    // CONVERSIONES DESDE GRAMOS
    // ===============================================================


    private BigDecimal gramosALibras(BigDecimal gramos) {
        if (gramos == null) return BigDecimal.ZERO;
        return gramos.divide(GRAMOS_POR_LIBRA, 2, RoundingMode.HALF_UP);
    }


    private BigDecimal gramosAKilos(BigDecimal gramos) {
        if (gramos == null) return BigDecimal.ZERO;
        return gramos.divide(GRAMOS_POR_KILO, 2, RoundingMode.HALF_UP);
    }

    // ===============================================================
    // DETECCIÓN DE UNIDAD
    // ===============================================================


    private boolean esGramo(String unidad) {
        return unidad != null && unidad.equalsIgnoreCase("GR");
    }


    private boolean esKilo(String unidad) {
        return unidad != null && unidad.equalsIgnoreCase("KG");
    }


    private boolean esLibra(String unidad) {
        return unidad != null && unidad.equalsIgnoreCase("LB");
    }

    // ===============================================================
    // CONVERSIÓN GENÉRICA A GRAMOS
    // ===============================================================


    public BigDecimal convertirAGramos(BigDecimal valor, String unidad) {

        if (valor == null) return BigDecimal.ZERO;

        if (esLibra(unidad)) return librasAGramos(valor);
        if (esKilo(unidad)) return kilosAGramos(valor);
        if (esGramo(unidad)) return gramosAGramos(valor);

        throw new IllegalArgumentException("Unidad no soportada: " + unidad);
    }

    // ===============================================================
    // CONVERSION DE GRAMOS A UNIDAD DESTINO
    // ===============================================================


    public BigDecimal convertirDesdeGramos(BigDecimal gramos, String unidadDestino) {

        if (gramos == null) return BigDecimal.ZERO;

        if (esLibra(unidadDestino)) return gramosALibras(gramos);
        if (esKilo(unidadDestino)) return gramosAKilos(gramos);
        if (esGramo(unidadDestino)) return gramosAGramos(gramos);

        throw new IllegalArgumentException("Unidad no soportada: " + unidadDestino);
    }
}
