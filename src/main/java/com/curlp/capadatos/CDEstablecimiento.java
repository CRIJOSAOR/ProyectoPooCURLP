/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.curlp.capadatos;

import com.curlp.capalogica.CLEstablecimiento;
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
 * @author usuario
 */
public class CDEstablecimiento {
//declarar variables de conexion y de consullta

    private final Connection cn;
    PreparedStatement ps;
    ResultSet rs;
    Statement st;

    public CDEstablecimiento() throws SQLException {
        this.cn = conexion.conectar();

    }

    // metodo para insertar Estableimiento en la tabla.
    public void insertarEstablecimiento(CLEstablecimiento cl) throws SQLException {
        String sql = "{CALL insertarEstablecimiento(?)}";

        try {
            ps = cn.prepareCall(sql);
            ps.setString(1, cl.getNombreEstablecimiento());
            ps.execute();

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error:" + e.getMessage());

        }
    }

    //metodo para actualizar Establecimiento en la tabla
    public void actualizarEstablecimiento(CLEstablecimiento cl) throws SQLException {
        String sql = "{CALL actualizarEstablecimiento(?)}";

        try {
            ps = cn.prepareCall(sql);
            ps.setInt(1, cl.getCodEstablecimiento());
            ps.setString(2, cl.getNombreEstablecimiento());
            ps.execute();

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error:" + e.getMessage());

        }
    }

    //metodo para eliminar establecimiento en la tabla
    public void eliminarEstablecimiento(CLEstablecimiento cl) throws SQLException {
        String sql = "{CALL eliminarEstablecimiento(?)}";

        try {
            ps = cn.prepareCall(sql);
            ps.setInt(1, cl.getCodEstablecimiento());
            ps.execute();

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error:" + e.getMessage());

        }
    }
    //metodo para obtener el cod autoincrementable del establecimiento

    public int autoincrementarEstablecimiento() throws SQLException {
        int codEstablecimiento = 0;
        String sql = "{CALL autoIncrementarEstablecimientoCOD( )}";

        try {
            st = cn.createStatement();
            rs = st.executeQuery(sql);
            rs.next();
            codEstablecimiento = rs.getInt("codEstablecimiento");

            if (codEstablecimiento == 0) {
                codEstablecimiento = 1;
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error:" + e.getMessage());
        }
        return codEstablecimiento;
    }
//metodo para poblar tablas de establecimiento

    public List<CLEstablecimiento> obtenerListaEstablecimiento() throws SQLException {

        String sql = "{CALL mostrarEstablecimiento( )}";

        List<CLEstablecimiento> miLista = null;

        try {
            st = cn.createStatement();
            rs = st.executeQuery(sql);
            miLista = new ArrayList<>();

            while (rs.next()) {
                CLEstablecimiento cl = new CLEstablecimiento();
                cl.setCodEstablecimiento(rs.getInt("codEstablecimiento"));
                cl.setNombreEstablecimiento(rs.getString("nombreEstablecimiento"));
                miLista.add(cl);

            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error:" + e.getMessage());
        }
        return miLista;

    }

    //metodo para llenar en combo del establecimiento
    public List<String> cargarComboEstablecimiento() throws SQLException {

        String sql = "{CALL mostrarEstablecimiento( )}";

        List<String> miLista = null;
        try {
            st = cn.createStatement();
            rs = st.executeQuery(sql);
            miLista = new ArrayList<>();
            miLista.add("Seleccione");

            while (rs.next()) {

                miLista.add(rs.getString("nombreEstablecimiento"));

            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error:" + e.getMessage());
        }
        return miLista;

    }
}
