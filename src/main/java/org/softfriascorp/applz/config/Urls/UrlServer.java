/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.softfriascorp.applz.config.Urls;

/**
 *
 * @author usuario
 */
public class UrlServer {


    public static final String HEADER_DEFAULT = "Content-Type";
     public static final String HEADER_APPLICATION = "application/json";
     
    //  Datos del servidor
     
     public static final String PROTOCOL_WEB = "http://";
    public static final String SERVER_HOST = "localhost";
    public static final int SERVER_PORT = 8900;
    
    public static final String API_URL_BASE = PROTOCOL_WEB + SERVER_HOST + ":" + SERVER_PORT;
    
    
    
    public static final String MAESTRA_SEARCH_TIPO = API_URL_BASE + "/maestra/tipo";
    public static final String MAESTRA_SEARCH_CATEGORIAS = API_URL_BASE + "/maestra/categorias";
    public static final String MAESTRA_SEARCH_CATEGORIAS_PADRE = API_URL_BASE + "/maestra/cat";
    
    
    
    
    public static final String UDUARIOS_SAVE_ENDPOINT = "/usuario/save";
    public static final String USUARIOS_AUTH_LOGIN = "/auth/login";
    

    // Configuración de autenticaciónmarciano       
    public static final String LOGIN_ENDPOINT = API_URL_BASE + "/auth/login";
    public static final String REFRESH_TOKEN_ENDPOINT = API_URL_BASE + "/auth/refresh";
    
    
    
    
    public static final String _TOKEN_ENDPOINT = API_URL_BASE + "/auth/refresh";
    //  Endpoints generales
    public static final String PRODUCTOS_ENDPOINT = API_URL_BASE + "/producto";
    public static final String PRODUCTOS_SEARCH_2S = PRODUCTOS_ENDPOINT + "/search/2s";
    public static final String PRODUCTOS_SEARCH_3S = PRODUCTOS_ENDPOINT + "/search/3s";
    
    public static final String PRODUCTOS_SEARCH_4SPROMO = PRODUCTOS_ENDPOINT + "/";
    public static final String PRODUCTOS_ALL = PRODUCTOS_ENDPOINT + "/all";
    public static final String PRODUCTO_SAVE = PRODUCTOS_ENDPOINT + "/save";
    public static final String PRODUCTO_UPDATE = PRODUCTOS_ENDPOINT + "/update";
    
    
    public static final String REFRESHCATEGORIAS_ENDPOINT = API_URL_BASE + "/maes/categorias";
    public static final String MARCAS_ENDPOINT = API_URL_BASE + "/maes/marcas";
    
    
    
    public static final String VENTA_SAVE = API_URL_BASE + "/venta/save";

    public static final String UPDATE_VALIDATE = API_URL_BASE +"/update";
    
    // Otros valores de configuración
    public static final int TIMEOUT_MS = 10000;
    public static final String APP_VERSION = "1.0.0";

    private UrlServer() { // Evita que se instancie
    }
}
