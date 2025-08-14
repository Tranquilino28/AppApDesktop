/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.softfriascorp.applz.repository.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.softfriascorp.applz.database.LocalConnBD;
import org.softfriascorp.applz.model.Producto;
import org.softfriascorp.applz.repository.interfaces.Repository;

/**
 *
 * @author usuario
 */
public class ImplRepo_Producto implements Repository<Producto, Long>{

    // crea la coneccion ala base deatos 
    private Connection getLocalConectionBD() throws SQLException {

        return LocalConnBD.getInstanceBD();

    }
    
      
    @Override
    public List<Producto> listAll() {
        
       List<Producto> productos = new ArrayList<>();
       
        String sql ="SELECT * FROM TABLA_PRODUCTOS WHERE PROD_STOCKDISPOIBLE != 0 AND PROD_STOCKDISPOIBLE IS NOT NULL;";
        try (
                 /**
                  * Establece la conexión utilizando
                  * LocalConectionDB.getConnLocaBD() y se crea un statement
                  */
                 PreparedStatement stmt = getLocalConectionBD().prepareStatement(sql); // Crea una declaración para ejecutar consultas
                  ResultSet resultado = stmt.executeQuery()) {

            // Verifica si hay resultados (opcional)
            if (resultado.isBeforeFirst()) {

                System.out.println("** Datos de TABLA_PRODUCTO **");

                System.out.println("----------------------");

                /*try {
                    Thread.sleep(3000);
                } catch (InterruptedException ex) {
                    Logger.getLogger(LocalConectionDB.class.getName()).log(Level.SEVERE, null, ex);
                }
                 */
                // Recorre los resultados (si hay alguno)
                while (resultado.next()) {

                    System.out.println("ID: " + resultado.getInt("PROD_ID") + "\t Nombre: " + resultado.getString("PROD_NOMBRE"));

                    // Imprime otros valores de columna
                    productos.add(createProducto(resultado));
                    /**
                     * PERS_ID INT NOT NULL PRIMARY KEY ,\n" + "
                     * PERS_IDENTIFICACION INT NOT NULL,\n" + " PERS_NOMBRE
                     * VARCHAR(50) NOT NULL,\n" + " PERS_APELLIDO VARCHAR(50)
                     * NOT NULL,\n" + " PERS_DIRECCION VARCHAR(50) NOT NULL,\n"
                     * + " MAES_TIID INT NULL,\n" + " MAES_TISE INT NULL,\n" + "
                     * MAES_TIES INT NOT NULL,\n"
                     */
                }

            } else {

                System.out.println("No se encontraron registros en TABLA_PRODUCTOS.");

            }

        } catch (SQLException ex) {

            //Logger.getLogger(PruebaFactura.class.getName()).log(Level.SEVERE, null, ex);
        }
        return productos;
    }

    @Override
    public Producto findById(Long id) {
        return null;
    }

    @Override
    public void suprimir(Long id) {
        
    }

    @Override
    public void save(Producto t) {
        
    }
    
    
    
    public Producto createProducto(ResultSet resultado) throws SQLException {

        Producto prod = new Producto();

        prod.setId(resultado.getInt("PROD_ID"));
        prod.setCodigo(resultado.getString("PROD_CODIGO"));
        prod.setNombre(resultado.getString("PROD_NOMBRE"));
        prod.setDescripcion(resultado.getString("PROD_DESCRIPCION"));
        prod.setPrecio(resultado.getDouble("PROD_PRECIO"));
        prod.setUnidadmedida(resultado.getString("PROD_UNIDADMEDIDA"));
        prod.setMaes_ticatpro(resultado.getInt("MAES_TICATPRO"));
        prod.setStockDisponible(resultado.getInt("PROD_STOCKDISPOIBLE"));
        prod.setEmprId(resultado.getInt("EMPR_ID"));
        
        
        return prod;
    }
    
}
