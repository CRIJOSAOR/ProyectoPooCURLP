/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.curlp.capadatos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author davidmendoza
 */
public class CDDosis {
    private final Connection cn;
    PreparedStatement ps;
    ResultSet rs;
    Statement st;

    public CDDosis() throws SQLException {
        this.cn = Conexion.conectar();
    }
        public ArrayList<String> cargarDosis() throws SQLException {
        String sql  = "call mostrarDosis()";
        
        ArrayList<String> miLista = null;
        try{
            st = cn.createStatement();
            rs = st.executeQuery(sql);
            
            miLista = new ArrayList<>();
            miLista.add("--SELECCIONE--");
            
            while(rs.next()){
                miLista.add(rs.getString("dosis"));
            }
        } catch(SQLException e){
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
        }
        return miLista;
    }
    
}
