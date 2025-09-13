/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.softfriascorp.applz.local.service.impl;

import org.softfriascorp.applz.local.model.Producto;
import org.softfriascorp.applz.local.repository.interfaces.Repository;
import org.softfriascorp.applz.local.service.interfaces.AbstracService;

/**
 *
 * @author usuario
 */
public class ImplService_Producto extends AbstracService<Producto,Long> {
    
    public ImplService_Producto(Repository<Producto, Long> repo) {
        super(repo);
    }
    
    
    
}
