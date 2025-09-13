/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.softfriascorp.applz.entity.productos.enumapi;

/**
 *
 * @author usuario
 */
public enum Url_ApiProductos {
    
    URL_FIND_SEARCH_CODIGO_BARRAS("http://localhost:3066/producto/search/1/"),
    URL_FIND_ALL("http://localhost:3066/producto/all"),
    URL_FIND_BY_CODIGO_OR_NOMBRE("http://localhost:3066/producto/search/2/"); // Puedes ajustar si cambia

    private final String url;

    Url_ApiProductos(String url) {
        this.url = url;
    }

    public String getUrl() {
        return url;
    }
}