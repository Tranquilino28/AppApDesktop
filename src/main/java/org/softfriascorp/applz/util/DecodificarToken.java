/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.softfriascorp.applz.util;
  import java.util.Base64;
import org.softfriascorp.applz.module_login.auth.AuthService;
import org.softfriascorp.applz.local.model.UsuarioPerfil;

/**
 *
 * @author usuario
 */
public class DecodificarToken {
    
   
   
       public static UsuarioPerfil getUserPerfil(){
         
       
        // Dividir en 3 partes: header.payload.signature
        String[] parts = AuthService.getToken().split("\\.");
        
        // Decodificar Header
        String header = new String(Base64.getUrlDecoder().decode(parts[0]));
        System.out.println("Header: " + header);
        /*
        // Decodificar Payload
        String payload = new String(Base64.getUrlDecoder().decode(parts[1]));
        System.out.println("Payload: " + payload);

        // Firma (no se decodifica, solo se usa para validar)
        System.out.println("Signature: " + parts[2]);
        
            // Decodificar payload
        String payloadJson = new String(Base64.getUrlDecoder().decode(parts[1]));

        // Convertir a objeto JSON
        JSONObject payload1 = new JSONObject(header);

        // Extraer datos y guardarlos en variables
        usuario = payload1.getString("sub");          // usuario
        rol = payload1.getString("rol");              // rol
        empresaId = payload1.getLong("empresa_Id");       // empresaId

        */
        
           return null;
       }
}


