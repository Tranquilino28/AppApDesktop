/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.softfriascorp.applz.entity.producto.service.interfaces;

import java.util.List;
import org.softfriascorp.applz.entity.producto.ProductoDto;

/**
 *
 * @author usuario
 */
public interface ProductoService {
    
    
    ProductoDto findByCodigoBarras(String codigoBarras);
    List<ProductoDto> findByCoincidencia(String coincidencia);
    
    
    
}
