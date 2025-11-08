/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.softfriascorp.applz.entity.maestra.service.interfaces;

import java.util.List;
import org.softfriascorp.applz.entity.maestra.Maestra;

/**
 *
 * @author usuario
 */
public interface MaestraService {
    
    Maestra findByTipo(String nombreCorto);
    List<Maestra> findByPadre(String nombreCorto);
    
    Maestra searcCategoriaPrincipal(String subCategoria);
}
