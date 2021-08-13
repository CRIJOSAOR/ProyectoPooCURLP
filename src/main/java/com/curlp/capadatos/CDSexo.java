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
public class CDSexo {
    private final Connection cn;
    PreparedStatement ps;
    ResultSet rs;
    Statement st;

    public CDSexo() throws SQLException {
        this.cn = conexion.conectar();
    }
    
    public ArrayList<String> cargarSexos() throws SQLException {
        String sql  = "call mostrarSexos()";
        
        ArrayList<String> miLista = null;
        try{
            st = cn.createStatement();
            rs = st.executeQuery(sql);
            
            miLista = new ArrayList<>();
            miLista.add("--SELECCIONAR--");
            
            while(rs.next()){
                miLista.add(rs.getString("sexo"));
            }
        } catch(SQLException e){
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
        }
        return miLista;
    }
    

}
