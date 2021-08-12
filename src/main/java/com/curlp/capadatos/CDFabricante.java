/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.curlp.capadatos;

import com.curlp.capalogica.CLFabricante;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;

/**
 *
 * @author Luis
 */
public class CDFabricante {
    
    // Declarar las variables de conexion y consulta
    private final Connection cn;
    PreparedStatement ps;
    ResultSet rs;
    Statement st;

    public CDFabricante() throws SQLException{
        this.cn = conexion.conectar();
    }

    //Metodos para insertar un fabricante
    public void insertarFabricante(CLFabricante cl)throws SQLException {
        String sql = "{CALL insertarFabricante(?)}";
        
        try {
            ps = cn.prepareCall(sql);
            ps.setString(1, cl.getNombreFabricante());
            ps.execute();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error" + e.getMessage());
        }
    }
    //Metodo para actualizar el fabricante en la tabla
    public void actualizarFabricante(CLFabricante cl)throws SQLException {
        String sql = "{CALL actualizarFabricante(?,?)}";
        
        try {
            ps = cn.prepareCall(sql);
            ps.setInt(1, cl.getIdFabricante());
            ps.setString(2, cl.getNombreFabricante());
            ps.execute();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error" + e.getMessage());
        }
    }
    //Metodo para eliminar el fabricante en la tabla
    public void eliminarFabricante(CLFabricante cl)throws SQLException {
        String sql = "{CALL eliminarFabricante(?)}";
        
        try {
            ps = cn.prepareCall(sql);
            ps.setInt(1, cl.getIdFabricante());
            ps.execute();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error" + e.getMessage());
        }
    }
    
    //Metodo para obtener el id de el Fabricante
}
