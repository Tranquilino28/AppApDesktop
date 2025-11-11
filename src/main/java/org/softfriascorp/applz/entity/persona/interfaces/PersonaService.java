/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.softfriascorp.applz.entity.persona.interfaces;

import org.softfriascorp.applz.entity.persona.Persona;

/**
 *
 * @author usuario
 */
public interface PersonaService {
    
    
    Persona searchPersonaByIdentificacion(String identificacion);
    Persona save(Persona p);
    
}
