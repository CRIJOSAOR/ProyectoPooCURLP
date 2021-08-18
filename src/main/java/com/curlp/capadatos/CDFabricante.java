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
import java.util.ArrayList;
import java.util.List;
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

    public CDFabricante() throws SQLException {
        this.cn = Conexion.conectar();
    }

    //Metodos para insertar un fabricante
    public void insertarFabricante(CLFabricante cl) throws SQLException {
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
    public void actualizarFabricante(CLFabricante cl) throws SQLException {
        String sql = "{CALL actualizarFabricante(?,?)}";

        try {
            ps = cn.prepareCall(sql);
            ps.setInt(2, cl.getIdFabricante());
            ps.setString(1, cl.getNombreFabricante());
            ps.execute();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error" + e.getMessage());
        }
    }

    //Metodo para eliminar el fabricante en la tabla
    public void eliminarFabricante(CLFabricante cl) throws SQLException {
        String sql = "{CALL eliminarFabricante(?)}";

        try {
            ps = cn.prepareCall(sql);
            ps.setInt(1, cl.getIdFabricante());
            ps.execute();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error" + e.getMessage());
        }
    }

    //Metodo para eliminar el fabricante X en la tabla
    public void eliminarFabricanteX(CLFabricante cl) throws SQLException {
        String sql = "{CALL eliminarFabricanteX(?)}";

        try {
            ps = cn.prepareCall(sql);
            ps.setInt(1, cl.getIdFabricante());
            ps.execute();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error" + e.getMessage());
        }
    }
    // Metodo obtener el Id autoincrementado
    public int autoIncrementarIDFabricante() throws SQLException {
        int idFabricante = 0;
        String sql = "{CALL autoIncrementarFabricante()}";

        try {
            st = cn.createStatement();
            rs = st.executeQuery(sql);
            rs.next();

            idFabricante = rs.getInt("idFabricante");

            if (idFabricante == 0) {
                idFabricante = 1;
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error" + e.getMessage());
        }
        return idFabricante;
    }
    
    //Metodo para mostrar el Fabricante
    public List<CLFabricante> obtenerListaFabricantes() throws SQLException {

        String sql = "{CALL mostrarFabricante()}";

        List<CLFabricante> miLista = null;

        try {
            st = cn.createStatement();
            rs = st.executeQuery(sql);

            miLista = new ArrayList<>();
            while (rs.next()) {
                CLFabricante cl = new CLFabricante();

                cl.setIdFabricante(rs.getInt("idFabricante"));
                cl.setNombreFabricante(rs.getString("nombreFabricante"));
                miLista.add(cl);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error" + e.getMessage());
        }
        return miLista;
    }
    // Metodo que perimite llenar el combo de Fabricante

    public List<String> cargarComboFabricante() throws SQLException {

        String sql = "{CALL mostrarFabricante()}";

        List<String> miLista = null;

        try {
            st = cn.createStatement();
            rs = st.executeQuery(sql);

            miLista = new ArrayList<>();
            miLista.add("--Seleccione --");
            while (rs.next()) {
                miLista.add(rs.getString("nombreFabricante"));
                
              
                
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error" + e.getMessage());
        }
        return miLista;
    }
    
    public int obtenerIdFabricante(String nombreFabricante){
        int idFabricante = 0;
        String sql = "{CALL obtenerIdFabricante(?)}";

        try {
            ps = cn.prepareStatement(sql);
            ps.setString(1, nombreFabricante);
            rs = ps.executeQuery();
            rs.next();
            idFabricante = rs.getInt("idFabricante");
            
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error" + e.getMessage());
        }
        return idFabricante;        
    }
    
}
