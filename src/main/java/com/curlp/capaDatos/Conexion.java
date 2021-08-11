package com.curlp.capaDatos;

import java.sql.*;

public class Conexion {

    private static String url = "jdbc:mysql://localhost:3306/proyectopoocurlpbd?zeroDateTimeBehavior=CONVERT_TO_NULL";
    private static String user = "root";
    private static String clave = "Cjso.1616";

    public static Connection conectar() throws SQLException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver"); 
            //obtener la cadena de conexion
            return DriverManager.getConnection(url, user, clave);
            
        } catch (ClassNotFoundException e) {
            throw new SQLException(e.getMessage());
        }
    }
}
