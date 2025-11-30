/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.softfriascorp.applz.modules.roleAccess;

/**
 *
 * @author usuario
 */
public enum RoleKey {
    
    // 1. Definición con valores específicos
    ADMIN("ADMIN", 3), // API devuelve "ADMIN", Nivel 3 (más alto)
    VENDEDOR("VENDEDOR", 2), // API devuelve "VENDEDOR", Nivel 2
    CLIENTE("CLIENTE", 1),   // API devuelve "CLIENTE", Nivel 1
    DESCONOCIDO("DESCONOCIDO", 0); // Manejo de errores

    // 2. Campos complementarios (Atributos)
    private final String apiValue;
    private final int nivelAcceso;

    // 3. Constructor
    RoleKey(String apiValue, int nivelAcceso) {
        this.apiValue = apiValue;
        this.nivelAcceso = nivelAcceso;
    }

    // 4. Método de Conversión (el más importante)
    public static RoleKey fromApiValue(String apiValue) {
        if (apiValue == null) {
            return DESCONOCIDO;
        }
        // Itera sobre todos los valores del enum para encontrar la coincidencia
        for (RoleKey rol : values()) {
            if (rol.apiValue.equalsIgnoreCase(apiValue)) {
                return rol;
            }
        }
        return DESCONOCIDO; // Devuelve DESCONOCIDO si no encuentra el rol
    }

    // 5. Métodos Getters para acceder a la lógica
    public String getApiValue() {
        return apiValue;
    }

    public int getNivelAcceso() {
        return nivelAcceso;
    }

    
}
