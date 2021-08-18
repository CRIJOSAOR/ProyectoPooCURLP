/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.curlp.capadatos;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.DriverManager;

/**
 *
 * @author sanch
 */
public class Conexion {
    private static String url = "jdbc:mysql://localhost:3306/proyectopoocurlpbd?zeroDateTimeBehavior=CONVERT_TO_NULL";
    private static String user = "root" ;
    private static String clave = "Alejandra*2001*"; 
    
    
    public static Connection conectar() throws SQLException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            return DriverManager.getConnection(url, user, clave);
        } catch (ClassNotFoundException e) {
            throw new SQLException(e.getMessage());
        }
    }
}

