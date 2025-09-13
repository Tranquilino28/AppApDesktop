/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.softfriascorp.applz.local.repository.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.print.PrintException;
import org.softfriascorp.applz.local.database.LocalConnBD;
import org.softfriascorp.applz.local.model.Producto;
import org.softfriascorp.applz.local.model.Usuario;
import org.softfriascorp.applz.local.repository.interfaces.Repository;

/**
 *
 * @author usuario
 */
public class ImplRepo_Usuario implements Repository<Usuario, String>{

    // crea la coneccion ala base deatos 
    private Connection getLocalConectionBD() throws SQLException {

        return LocalConnBD.getInstanceBD();

    }

    @Override
    public List<Usuario> listAll() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Usuario findById(String id) {
       String sql = "SELECT"
               + " USUA_USER "              
               + ", USUA_HASHSALT"
               + ", USUA_HASHPASSWORD"
               + " FROM TABLA_USUARIOS"
               + " WHERE"
               + " USUA_USER = ?" ;
       
        try(PreparedStatement stmt = getLocalConectionBD().prepareStatement(sql)) {
            
            stmt.setString(1, id);
            ResultSet r =stmt.executeQuery();
           
            if (r.next()) {
               
               return createUsuario(r);
                
            }else{
                
                System.out.println("Usuario null");
                
            }
            
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        
        
        return null;
    }

    @Override
    public void suprimir(String id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void save(Usuario t) {
        String sql = "INSERT INTO TABLA_USUARIOS ("
                     + " USUA_CODIGO,\n"
                     + " USUA_USER,\n"
                     + " USUA_HASHSALT,\n"
                     + " USUA_HASHPASSWORD,\n"                    
                     + " PERS_ID) "
                     + " VALUES ("
                     + "?,?,?,?,? );";
        
        try(PreparedStatement stmt = getLocalConectionBD().prepareStatement(sql)) {
        
                stmt.setLong(1, t.getCodigo());
                stmt.setString(2, t.getUsuario());
                stmt.setString(3, t.getHash_salt());
                stmt.setString(4, t.getHash_password());
                stmt.setInt(5, 1);
                
                stmt.executeUpdate();
                
        } catch (SQLException ex) {
           ex.printStackTrace();
        }
    }
    private Usuario createUsuario(ResultSet r) throws SQLException {
        // se crea el objeto y con los resultados de la consuta se rellena con el  id codigo y el usuario 
        
       return  new Usuario(
               r.getString("USUA_USER")
               ,r.getString("USUA_HASHSALT")
               ,r.getString("USUA_HASHPASSWORD")
                );
    }
}
