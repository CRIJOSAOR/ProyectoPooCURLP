/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.curlp.capadatos;

import com.curlp.capalogica.CLRegistroVacuna;
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
 * @author sanch
 */
public class CDRegistroVacuna {
     // Declarar las variales de conexion y consulta:
    private final Connection cn;
    PreparedStatement ps;
    ResultSet rs;
    Statement st;

    public CDRegistroVacuna() throws SQLException {
        this.cn = Conexion.conectar();

    }

    // Metodo insertar RegistroVacuna
    public void insertarRegistroVacuna(CLRegistroVacuna cl) throws SQLException {
        String sql = "{CALL insertarRegistroVacuna(?,?,?,?,?,?,?)}";
        try {

            ps = cn.prepareCall(sql);
            ps.setInt(1, cl.getIdRegistroVacuna());
            ps.setDate(2, cl.getFechaVacunacion());
            ps.setString(3, cl.getDniVacunador());
            ps.setInt(4, cl.getCodEstablecimiento());
            ps.setString(5, cl.getNumLoteVacuna());
            ps.setString(6, cl.getDniPaciente());
             ps.setInt(7, cl.getDosis());
            
            ps.execute();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "aqui esta el error" + e.getMessage());
        }
    }

    // Metodo para actualizar RegistroVacuna
    public void actualizarRegistroVacuna(CLRegistroVacuna cl) throws SQLException {
        String sql = "{CALL actualizarRegistroVacuna(?,?,?,?,?,?,?)}";
        try {

               ps = cn.prepareCall(sql);
            ps.setInt(1, cl.getIdRegistroVacuna());
            ps.setDate(2, cl.getFechaVacunacion());
            ps.setString(3, cl.getDniVacunador());
            ps.setInt(4, cl.getCodEstablecimiento());
            ps.setString(5, cl.getNumLoteVacuna());
            ps.setString(6, cl.getDniPaciente());
             ps.setInt(7, cl.getDosis());
            ps.execute();

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error" + e.getMessage());
        }
    }

    // Metodo para elimiar RegistroVacuna
    public void eliminarRegistroVacuna(CLRegistroVacuna cl) throws SQLException {
        String sql = "{CALL eliminarRegistroVacuna(?)}";
        try {

            ps = cn.prepareCall(sql);
            ps.setInt(1, cl.getIdRegistroVacuna());
            ps.execute();

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error" + e.getMessage());
        }
    }

    //metodo para obtener el id RegistroVacuna
    public int autoIncrementarRegistroVacuna(CLRegistroVacuna cl) throws SQLException {
        int idFabricante = 0;

        String sql = "{CALL autoIncrementarRegistroVacuna()}";
        try {
            st = cn.createStatement();
            rs = st.executeQuery(sql);
            rs.next();
            idFabricante = rs.getInt("idRegistroVacuna");
            if (idFabricante == 0) {
                idFabricante = 1;
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error" + e.getMessage());
        }
        return idFabricante;
    }
    // Metodo para mostrar RegistroVacunas

    public List<CLRegistroVacuna> obtenerListaRegistroVacuna() throws SQLException {

        String sql = "{CALL mostrarRegistroVacuna()}";

        List<CLRegistroVacuna> miLista = null;
        try {
            st = cn.createStatement();
            rs = st.executeQuery(sql);

            miLista = new ArrayList<>();
            while (rs.next()) {
                CLRegistroVacuna cl = new CLRegistroVacuna();
                                
                cl.setIdRegistroVacuna(rs.getInt("r.idRegistroVacuna"));
                cl.setFechaVacunacion(rs.getDate("r.fechaVacunacion"));
                cl.setDniVacunador(rs.getString("r.dniVacunador"));
                cl.setCodEstablecimiento(rs.getInt("r.codEstablecimiento"));
                cl.setNumLoteVacuna(rs.getString("r.numLoteVacuna"));
                cl.setDniPaciente(rs.getString("r.dniPaciente"));
                cl.setDosis(rs.getInt("d.dosis"));

                miLista.add(cl);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error" + e.getMessage());
        }
        return miLista;
    }

    // METODO QUE PERMITE LLENAR EL COMBO DE RegistroVacuna
    public List<String> cargarComboRegistroVacuna() throws SQLException {

        String sql = "{CALL mostrarRegistroVacuna()}";

        List<String> miLista = null;
        try {
            st = cn.createStatement();
            rs = st.executeQuery(sql);

            miLista = new ArrayList<>();
            miLista.add("--Seleccione--");
            while (rs.next()) {
                miLista.add(rs.getString("idRegistroVacuna"));
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error" + e.getMessage());
        }
        return miLista;
    }
}
//    public List<CLLoteVacuna> obtenerListaLotesPorCod(String codLote) throws SQLException {
//        String sql = "CALL mostrarLotePorCod(?)";
//        List<CLLoteVacuna> miLista = null;
//        
//        try{
//            ps = cn.prepareStatement(sql);
//            ps.setString(1, codLote);
//            rs = ps.executeQuery();
//            
//            miLista = new ArrayList<>();
//            
//            while(rs.next()){
//                CLLoteVacuna cl = new CLLoteVacuna();
//                cl.setNumLoteVacuna(rs.getString("lv.numLoteVacuna"));
//                cl.setFechaFabricacion(rs.getDate("lv.fechaFabricacion"));
//                cl.setFechaVencimiento(rs.getDate("lv.fechaVencimiento"));
//                cl.setIdFbricante(rs.getInt("lv.idFabricante"));
//                cl.setNombreFabricante(rs.getString("f.nombreFabricante"));
//
//                miLista.add(cl);
//                
//            }
//        } catch(SQLException e){
//            
//            JOptionPane.showMessageDialog(null, "Error al mostrar lotes", "COVA System", JOptionPane.INFORMATION_MESSAGE);
//        }
//        
//        return miLista;
//    } 
//}
