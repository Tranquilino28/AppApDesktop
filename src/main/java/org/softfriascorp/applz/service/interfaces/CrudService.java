/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package org.softfriascorp.applz.service.interfaces;

import java.util.List;

/**
 *
 * @author usuario
 */
public interface CrudService <T,ID>{
     List<T> listAll();
    
    T findById(ID id);
    
    void suprimir(ID id);
    
    void save(T t);
}
