/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.softfriascorp.applz.local.service.interfaces;

import java.security.Provider.Service;
import java.util.List;
import org.softfriascorp.applz.local.repository.interfaces.Repository;

/**
 *
 * @author usuario
 * @param <T>
 * @param <ID>
 */
public abstract class AbstracService<T,ID> implements CrudService<T, ID>{
    
    protected final Repository<T,ID> repo;

    public AbstracService(Repository<T, ID> repo) {
        this.repo = repo;
    }

    @Override
    public List<T> listAll() {
        return repo.listAll();
    }

    @Override
    public T findById(ID id) {
       return repo.findById(id);
    }

    @Override
    public void suprimir(ID id) {
        repo.suprimir(id);
    }

    @Override
    public void save(T t) {
        repo.save(t);
    }
    
    public T findByCod(String cod) {
        
        
       return null;
    }
    
}
