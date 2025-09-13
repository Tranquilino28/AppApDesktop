/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.softfriascorp.applz.local.guiceinyector;

import com.google.inject.AbstractModule;
import org.softfriascorp.applz.module_inventario.services.impl.ApiImpl_ServiceInventary;
import org.softfriascorp.applz.module_inventario.services.interfaces.Interface_serviceInventario;

/**
 *
 * @author usuario
 */
public class AppModule extends AbstractModule {
    @Override
    protected void configure() {
        // Vincula la interfaz con su implementación. Guice usará esta configuración.
        bind(Interface_serviceInventario.class).to(ApiImpl_ServiceInventary.class);
       
        
    }
}