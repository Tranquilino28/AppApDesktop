package org.softfriascorp.applz.modules.cuenta.service.interfaces;

import java.math.BigDecimal;

public interface ConversorDeMedida {
    BigDecimal convertirAGramos(BigDecimal valor, String unidad);
    BigDecimal convertirDesdeGramos(BigDecimal gramos, String unidadDestino);
}
