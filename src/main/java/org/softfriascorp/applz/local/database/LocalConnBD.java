/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.softfriascorp.applz.local.database;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author usuario
 */
public class LocalConnBD {
    
    private static Connection connection = null;
    private static String url = "bd/friastore.sqlite";
    private static String urlDataBase = "jdbc:sqlite:" + url + "";

    public static Connection getInstanceBD() throws SQLException {

        // Verificación de la existencia del archivo de la base de datos
        File dbFile = new File(url);

        if (!dbFile.exists()) {
            System.out.println("No se encuentran los archivos necesarios de la base de datos .");
            System.out.println("!SE CREARAN LOS ARCHIVOS NECESARIOS!");
            crearBDL(); // Crea la base de datos si no existe
            System.out.println("base de datos creada exitosamente");
        }

        // Reutilización de la conexión existente (opcional)
        if (connection != null && !connection.isClosed()) {
            //System.out.println("Reutilizando conexión existente a la base de datos.");

            /* se le da un tiempo de espera de 3s segundos para establecer una nueva conexion a la bd
            try {
                Thread.sleep(3000);
            } catch (InterruptedException ex) {
                Logger.getLogger(LocalConectionDB.class.getName()).log(Level.SEVERE, null, ex);
            }*/
            return connection;
        }

        // Creación de una nueva conexión si no hay una disponible
        //System.out.println("{{Estableciendo nueva coneccion a la base de datos.}}");
        /**
         * try { Thread.sleep(3000); } catch (InterruptedException ex) {
         * Logger.getLogger(LocalConectionDB.class.getName()).log(Level.SEVERE,
         * null, ex); }
         */
        connection = DriverManager.getConnection(urlDataBase);
        //System.out.println("Nueva conexión establecida a la base de datos.");
        return connection;
    }

    public static void crearBDL() {
        try {
            // Conexión a la base de datos
            connection = DriverManager.getConnection(urlDataBase);
            System.out.println("el archivo friastore.sqlite creado exitosamente \n"
                     + "se intentara establecer una nueva coneccion al archivo.");

            if (connection != null) {
                System.out.println("¡Conexión exitosa! \n"
                         + "se creara la base de datos para esta tienda");

                // Creación de la base de datos
                Statement stmt = connection.createStatement();

                // Ejecución de cada CREATE TABLE por separado
                String create_sql1 = "CREATE TABLE TABLA_MAESTRA (\n"
                         + "	MAES_ID INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,\n"
                         + "	MAES_NOMBRELARGO	VARCHAR(50) NOT NULL,\n"
                         + "	MAES_NOMBRECORTO	VARCHAR(20) NOT NULL,\n"
                         + "	MAES_DEPENDENCIA	INT,\n"
                         + "	MAES_ESTADO	INT NOT NULL\n"
                         + ");";

                String create_sql2 = "CREATE TABLE TABLA_EMPRESA (\n"
                         + "	EMPR_ID	INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,\n"
                         + "	EMPR_NIT	INT NOT NULL,\n"
                         + "	EMPR_NOMBRE	VARCHAR(100) NOT NULL,\n"
                         + "	EMPR_DESCRIPCION	VARCHAR(2000) NOT NULL,\n"
                         + "	EMPR_DIRECCION	VARCHAR(200) NOT NULL,\n"
                         + "	EMPR_TELEFONO	VARCHAR(11) NOT NULL,\n"
                         + "	EMPR_EMAIL	VARCHAR(50) NOT NULL,\n"
                         + "	EMPR_LOGO	LONGBLOB NULL,\n"
                         + "	MAES_TICAT	INT  NULL,\n"
                         + "	MAES_TIEM	INT NOT NULL,\n"
                         + "	FOREIGN KEY(MAES_TICAT) REFERENCES TABLA_MAESTRA(MAES_ID)\n"
                         + "      FOREIGN KEY(MAES_TIEM) REFERENCES TABLA_MAESTRA(MAES_ID)"
                         + ");";

                String create_sql3 = "CREATE TABLE TABLA_PERSONAS (\n"
                         + "	PERS_ID	INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,\n"
                         + "	PERS_IDENTIFICASION	INT NOT NULL,\n"
                         + "	PERS_NOMBRE	VARCHAR(50) NOT NULL,\n"
                         + "	PERS_APELLIDO	VARCHAR(50) NOT NULL,\n"
                         + "	PERS_DIRECCION	VARCHAR(50) NOT NULL,\n"
                         + "	MAES_TIID	INT,\n"
                         + "	MAES_TISE	INT,\n"
                         + "	MAES_TIES	INT NOT NULL,\n"
                         + "      MAES_TIPER      INT NOT NULL,\n"
                         + "	FOREIGN KEY(MAES_TISE) REFERENCES TABLA_MAESTRA(MAES_ID),\n"
                         + "	FOREIGN KEY(MAES_TIES) REFERENCES TABLA_MAESTRA(MAES_ID),\n"
                         + "	FOREIGN KEY(MAES_TIID) REFERENCES TABLA_MAESTRA(MAES_ID)\n"
                         + "      FOREIGN KEY(MAES_TIPER) REFERENCES TABLA_MAESTRA(MAES_ID)\n"
                         + ");"; 

                String create_sql4 = "CREATE TABLE TABLA_PRODUCTOS (\n"
                         + "	PROD_ID	INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,\n"
                         + "	PROD_CODIGO	INT NOT NULL,\n"
                         + "	PROD_NOMBRE	VARCHAR(50) NOT NULL,\n"
                         + "	PROD_DESCRIPCION	VARCHAR(200) NOT NULL,\n"
                         + "	PROD_PRECIO	DECIMAL(10, 2) NOT NULL,\n"
                         + "      PROD_UNIDADMEDIDA VARCHAR(10) NOT NULL,\n"
                         + "	MAES_TICATPRO	INT NOT NULL,\n"
                         + "	PROD_STOCKDISPONIBLE	INT NOT NULL,\n"
                         + "	EMPR_ID	INT NOT NULL,\n"
                         + "	FOREIGN KEY(EMPR_ID) REFERENCES TABLA_EMPRESA(EMPR_ID),\n"
                         + "      FOREIGN KEY (MAES_TICATPRO) REFERENCES TABLA_MAESTRA (MAES_ID)\n"
                         + ");";	

                String create_sql5 = "CREATE TABLE TABLA_USUARIOS (\n"
                         + "	USUA_ID	INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,\n"
                         + "	USUA_CODIGO	INT NOT NULL,\n"
                         + "	USUA_USER	VARCHAR(50) NOT NULL,\n"
                         + "	USUA_HASHSALT	VARCHAR(200) NOT NULL,\n"
                         + "    USUA_HASHPASSWORD VARCHAR(200) NOT NULL,\n"
                         + "      PERS_ID INT NOT NULL,\n"
                         + "      FOREIGN KEY (PERS_ID) REFERENCES TABLA_PERSONAS (PERS_ID)\n"
                         + "      );";
	

                String create_sql6 = "CREATE TABLE TABLA_VENTAS (\n"
                         + "	VENT_ID	INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,\n"
                         + "	VENT_VALORTOTAL	DECIMAL(10, 2) NOT NULL,\n"
                         + "	VENT_FECHAVENTA	DATETIME NOT NULL,\n"
                         + "	VENT_ESTADO	TEXT NOT NULL DEFAULT 'ACTIVA',\n"
                         + "	PERS_IDEMPLEADO	INTEGER NOT NULL,\n"
                          + "	PERS_IDCLIENTE	INTEGER NOT NULL,\n"
                         + "      FOREIGN KEY (PERS_IDEMPLEADO) REFERENCES TABLA_PERSONAS (PERS_ID)\n"
                         + "	FOREIGN KEY (PERS_IDCLIENTE) REFERENCES TABLA_PERSONAS (PERS_ID)\n"
                         + ");";

                String create_sql7 = "CREATE TABLE TABLA_DETALLEVENTAS (\n"
                         + "	DEVE_ID INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,\n"
                         + "	VENT_ID INT NOT NULL,\n"
                         + "	PROD_ID INT NOT NULL,\n"
                         + "	DEVE_CANTIDAD INT NOT NULL,\n"
                         + "	DEVE_PRECIOUNITARIO DECIMAL(10,2) NOT NULL,\n"
                         + "	DEVE_SUBTOTAL DECIMAL(10,2) AS (DEVE_CANTIDAD * DEVE_PRECIOUNITARIO) STORED,\n"
                         + "	FOREIGN KEY(VENT_ID) REFERENCES TABLA_VENTAS(VENT_ID),\n"
                         + "	FOREIGN KEY(PROD_ID) REFERENCES TABLA_PRODUCTOS(PROD_ID)\n"
                         + ");";

                stmt.executeUpdate(create_sql1);
                stmt.executeUpdate(create_sql2);
                stmt.executeUpdate(create_sql3);
                stmt.executeUpdate(create_sql4);
                stmt.executeUpdate(create_sql5);
                stmt.executeUpdate(create_sql6);
                stmt.executeUpdate(create_sql7);

                /* String insert_sql ="INSERT INTO TABLA_MAESTRA("
                + "MAES_NOMBRELARGO, "
                + "MAES_NOMBRECORTO, "
                + "MAES_DEPENDENCIA, "
                + "MAES_ESTADO)"
                + " "
                + " VALUES("
                + " 'TIPO_ESTADO', 'TE', NULL, 1),"
                + "('ACTIVO', 'ACT', 1, 1),"
                + "('INACTIVO', 'INA',1, 1);";
                
                
                stmt.executeUpdate(insert_sql);
                */
                stmt.close();

            }
        } catch (SQLException ex) {
            System.err.println("Error al crear la base de datos: " + ex.getMessage());
        }
    }
}
