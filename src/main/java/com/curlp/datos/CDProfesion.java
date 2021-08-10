/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.curlp.datos;

import com.curlp.logica.CLProfesiones;
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
 * @author Letty Ochoa
 */
public class CDProfesion {

    // Paso 1 Declarar las variables conexion y consulta
    private final Connection cn;
    PreparedStatement ps;
    ResultSet rs;
    Statement st;

    public CDProfesion() throws SQLException {
        this.cn = Conexion.conectar();
    }

    //Insertar una profesion en la tabla Profesion
    public void insertarProfesiones(CLProfesiones cl) throws SQLException {
        String sql = "{CALL insertarProfesion(?)}";

        try {
            ps = cn.prepareCall(sql);
            ps.setString(1, cl.getProfesion());
            ps.execute();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error" + e.getMessage());

        }
    }

    //Metodo para actualizar la profesion
    public void actualizarProfesiones(CLProfesiones cl) throws SQLException {
        String sql = "{CALL actualizarProfesion(?,?)}";

        try {
            ps = cn.prepareCall(sql);
            ps.setInt(1, cl.getIdProfesion());
            ps.setString(2, cl.getProfesion());
            ps.execute();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error" + e.getMessage());

        }
    }

    //Metodo para eliminar profesion
    public void eliminarProfesiones(CLProfesiones cl) throws SQLException {
        String sql = "{CALL eliminarProfesion(?)}";

        try {
            ps = cn.prepareCall(sql);
            ps.setInt(1, cl.getIdProfesion());
            ps.execute();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error" + e.getMessage());
        }
    }

    //Metodo obtener el Id autoincrementado
    public int autoIncrementarIDProfesion() throws SQLException {
        int idProfesion = 0;
        String sql = "{CALL autoIncrementarProfesion()}";

        try {
            st = cn.createStatement();
            rs = st.executeQuery(sql);
            rs.next();

            idProfesion = rs.getInt("idProfesion");

            if (idProfesion == 0) {
                idProfesion = 1;
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error" + e.getMessage());
        }
        return idProfesion;
    }

    //Metodo para mostrar las profesiones ingresadas
    public List<CLProfesiones> obtenerListaProfesiones() throws SQLException {

        String sql = "{CALL mostrarProfesiones()}";

        List<CLProfesiones> miLista = null;
        try {
            st = cn.createStatement();
            rs = st.executeQuery(sql);

            miLista = new ArrayList<>();
            while (rs.next()) {
                CLProfesiones cl = new CLProfesiones();

                cl.setIdProfesion(rs.getInt("idProfesion"));
                cl.setProfesion(rs.getString("profesion"));
                miLista.add(cl);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error" + e.getMessage());
        }
        return miLista;
    }
    
    //metodo que nos permitira llenar el combo de profesiones
    public List<String> cragarComboProfesiones() throws SQLException {

        String sql = "{CALL mostrarProfesiones()}";

        List<String> miLista = null;
        try {
            st = cn.createStatement();
            rs = st.executeQuery(sql);
            miLista = new ArrayList<>();
            miLista.add("--SELECCIONE--");
            while (rs.next()) {
               miLista.add(rs.getString("Profesiones"));
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error" + e.getMessage());
        }
        return miLista;
    }
}

