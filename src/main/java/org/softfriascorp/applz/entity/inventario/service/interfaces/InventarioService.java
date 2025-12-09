/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package org.softfriascorp.applz.entity.inventario.service.interfaces;

import java.util.List;
import org.softfriascorp.applz.entity.producto.ProductoDto;

/**
 *
 * @author usuario
 */
public interface InventarioService {
    
    
    
    List<ProductoDto> searchDinamicFiltersAtributesProducts(
            String nameOrCodeBarr
            , Long categoriaId
            , Integer stockMin
            , Integer stockMax
    );
    
    
    
}
