/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.curlp.capadatos;

import com.curlp.capalogica.CLLoteVacuna;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author sanch
 */
public class CDLoteVacuna {

    // Declarar las variales de conexion y consulta:
    private final Connection cn;
    PreparedStatement ps;
    ResultSet rs;
    Statement st;

    public CDLoteVacuna() throws SQLException {
        this.cn = Conexion.conectar();

    }

    // Metodo insertar lote vacuna en la tabla
    public void insertarLoteVacuna(CLLoteVacuna cl) throws SQLException {
        String sql = "{CALL insertarLoteVacuna(?,?,?,?)}";
        try {

            ps = cn.prepareCall(sql);
            ps.setString(1, cl.getNumLoteVacuna());
            ps.setDate(2, cl.getFechaFabricacion());
            ps.setDate(3, cl.getFechaVencimiento());
            ps.setInt(4, cl.getIdFbricante());
            ps.execute();
           
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error" + e.getMessage());
        }
    }

    // Metodo para actualizar la profesion
    public void actualizarloteVacuna(CLLoteVacuna cl) throws SQLException {
        String sql = "{CALL actualizarloteVacuna(?,?,?,?)}";
        try {

            ps = cn.prepareCall(sql);
            ps.setString(1, cl.getNumLoteVacuna());
            ps.setDate(2, cl.getFechaFabricacion());
            ps.setDate(3, cl.getFechaVencimiento());
            ps.setInt(4, cl.getIdFbricante());
            ps.execute();

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error" + e.getMessage());
        }
    }

    // Metodo para elimiar un lote vacuna 
    public void eliminarLoteVacuna(CLLoteVacuna cl) throws SQLException {
        String sql = "{CALL eliminarLoteVacuna(?)}";
        try {

            ps = cn.prepareCall(sql);
            ps.setString(1, cl.getNumLoteVacuna());
            ps.execute();

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error" + e.getMessage());
        }
    }

    //metodo para obtener el id autoincrementado
    public int autoIncrementarLoteVacuna(CLLoteVacuna cl) throws SQLException {
        int idFabricante = 0;

        String sql = "{CALL autoIncrementarLoteVacuna()}";
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
    // Metodo para mostrar las profesiones ingresadas

    public List<CLLoteVacuna> obtenerListaLotevacuna() throws SQLException {

        String sql = "{CALL mostrarLoteVacuna()}";

        List<CLLoteVacuna> miLista = null;
        try {
            st = cn.createStatement();
            rs = st.executeQuery(sql);

            miLista = new ArrayList<>();
            while (rs.next()) {
                CLLoteVacuna cl = new CLLoteVacuna();
                cl.setNumLoteVacuna(rs.getString("lv.numLoteVacuna"));
                cl.setFechaFabricacion(rs.getDate("lv.fechaFabricacion"));
                cl.setFechaVencimiento(rs.getDate("lv.fechaVencimiento"));
                cl.setIdFbricante(rs.getInt("lv.idFabricante"));
                cl.setNombreFabricante(rs.getString("f.nombreFabricante"));

                miLista.add(cl);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error" + e.getMessage());
        }
        return miLista;
    }

    // METODO QUE PERMITE LLENAR EL COMBO DE loteVACUNA
    public List<String> cargarComboLoteVacuna() throws SQLException {

        String sql = "{CALL mostrarLoteVacuna()}";

        List<String> miLista = null;
        try {
            st = cn.createStatement();
            rs = st.executeQuery(sql);

            miLista = new ArrayList<>();
            miLista.add("--Seleccione--");
            while (rs.next()) {
                miLista.add(rs.getString("numLoteVacuna"));
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error" + e.getMessage());
        }
        return miLista;
    }
    public List<CLLoteVacuna> obtenerListaLotesPorCod(String codLote) throws SQLException {
        String sql = "CALL mostrarLotePorCod(?)";
        List<CLLoteVacuna> miLista = null;
        
        try{
            ps = cn.prepareStatement(sql);
            ps.setString(1, codLote);
            rs = ps.executeQuery();
            
            miLista = new ArrayList<>();
            
            while(rs.next()){
                CLLoteVacuna cl = new CLLoteVacuna();
                cl.setNumLoteVacuna(rs.getString("lv.numLoteVacuna"));
                cl.setFechaFabricacion(rs.getDate("lv.fechaFabricacion"));
                cl.setFechaVencimiento(rs.getDate("lv.fechaVencimiento"));
                cl.setIdFbricante(rs.getInt("lv.idFabricante"));
                cl.setNombreFabricante(rs.getString("f.nombreFabricante"));

                miLista.add(cl);
                
            }
        } catch(SQLException e){
            
            JOptionPane.showMessageDialog(null, "Error al mostrar lotes", "COVA System", JOptionPane.INFORMATION_MESSAGE);
        }
        
        return miLista;
    } 
}
