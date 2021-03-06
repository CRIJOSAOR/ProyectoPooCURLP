/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.curlp.capadatos;

import com.curlp.capalogica.CLPaciente;
import com.curlp.capalogica.CLVacunador;
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
 * @author raul
 */
public class CDVacunador {
    
    //Declarar variables de conexion y de consulta.
    private final Connection cn;
    PreparedStatement ps;
    ResultSet rs;
    Statement st;

    public CDVacunador() throws SQLException {
        this.cn = Conexion.conectar();
    }
    
    //Registrar un vacunador en la tabla.
    public void insertarVacunador(CLVacunador cl) throws SQLException {
        
        String sql = "{CALL insertarVacunador(?,?,?,?,?,?)}";
        
        try {
            ps = cn.prepareCall(sql);
            ps.setString(1, cl.getDniVacunador());
            ps.setString(2, cl.getNombresV());
            ps.setString(3, cl.getApellidosV());
            ps.setString(4, cl.getDireccionV());
            ps.setString(5, cl.getNumCelularV());
            ps.setString(6, cl.getEstado());
            ps.execute();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error: "+ e.getMessage());
        }
    }    
    
    //Metodo para actualizar el vacunador en la tabla
    public void actualizarVacunador(CLVacunador cl) throws SQLException {
        
       String sql = "{CALL actualizarVacunador(?,?,?,?,?,?)}";
        
       try {
           ps = cn.prepareCall(sql);
           ps.setString(1, cl.getDniVacunador());
           ps.setString(2, cl.getNombresV());
           ps.setString(3, cl.getApellidosV());
           ps.setString(4, cl.getDireccionV());
           ps.setString(5, cl.getNumCelularV());
           ps.setString(6, cl.getEstado());
           ps.execute();
       } catch (SQLException e) {
           JOptionPane.showMessageDialog(null, "Error: "+ e.getMessage());
       }
    }
    //Metodo para eliminar un vacunador de la tabla.
    public void eliminarVacunador(CLVacunador cl) throws SQLException {
        
       String sql = "{CALL eliminarVacunador(?)}";
        
       try {
           ps = cn.prepareCall(sql);
           ps.setString(1, cl.getDniVacunador());
           ps.execute();
       } catch (SQLException e) {
           JOptionPane.showMessageDialog(null, "Error: "+ e.getMessage());
       }
    }
    
    //Metodo para poblar de datos la tabla.
    public List<CLVacunador> obtenerListaVacunadores() throws SQLException {

        String sql = "{CALL mostrarVacunadores()}";

        List<CLVacunador> miListaV = null;

        try {
            st = cn.createStatement();
            rs = st.executeQuery(sql);

            miListaV = new ArrayList<>();

            while (rs.next()) {
                CLVacunador cl = new CLVacunador();

                cl.setDniVacunador(rs.getString("dniVacunador"));
                cl.setNombresV(rs.getString("nombre"));
                cl.setApellidosV(rs.getString("apellidos"));
                cl.setDireccionV(rs.getString("direccion"));
                cl.setNumCelularV(rs.getString("numCelular"));
                cl.setEstado(rs.getString("estado"));
                miListaV.add(cl);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
        }
        return miListaV;
    }
    
    public CLVacunador mostrarVacunadorX(String dniVacunador) throws SQLException {
        String sql = "CALL mostrarVacunadorX(?)";
        CLVacunador cl = new CLVacunador();

        try {

            ps = cn.prepareStatement(sql);
            ps.setString(1, dniVacunador);
            rs = ps.executeQuery();
            rs.next();
            cl.setDniVacunador(rs.getString("dniVacunador"));
            cl.setNombresV(rs.getString("nombre"));
            cl.setApellidosV(rs.getString("apellidos"));
            cl.setDireccionV(rs.getString("direccion"));
            cl.setNumCelularV(rs.getString("numCelular")); 
            cl.setEstado(rs.getString("estado"));

        } catch (SQLException e) {

            JOptionPane.showMessageDialog(null, "Error al mostrar -- Vuelva a intentarlo", "Error", JOptionPane.ERROR_MESSAGE);
        }

        return cl;
    }
    
    //Metodo que nos va a permitir llenar Combo de Vacunador.
    public List<String> cargarComboVacunadores() throws SQLException {

        String sql = "{CALL mostrarVacunadores()}";

        List<String> miListaV = null;

        try {
            st = cn.createStatement();
            rs = st.executeQuery(sql);

            miListaV = new ArrayList<>();
            miListaV.add("--Seleccione--");

            while (rs.next()) {
                miListaV.add(rs.getString("nombre"));
               
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
        }
        return miListaV;
    }
    


    public List<CLVacunador> obtenerListaVacunadoresPorNombre(String nombreVacunador) throws SQLException {
        String sql = "CALL mostrarVacunadorPorNombre(?)";
        List<CLVacunador> miLista = null;
        
        try{
            ps = cn.prepareStatement(sql);
            ps.setString(1, nombreVacunador);
            rs = ps.executeQuery();
            
            miLista = new ArrayList<>();
            
            while(rs.next()){
                CLVacunador cl = new CLVacunador();
                cl.setDniVacunador(rs.getString("dniVacunador"));
                cl.setNombresV(rs.getString("nombre"));
                cl.setApellidosV(rs.getString("apellidos"));
                cl.setDireccionV(rs.getString("direccion"));
                cl.setNumCelularV(rs.getString("numCelular")); 
                cl.setEstado(rs.getString("estado"));
                
                miLista.add(cl);
                
            }
        } catch(SQLException e){
            
            JOptionPane.showMessageDialog(null, "Error al mostrar vacunadores", "COVA System", JOptionPane.INFORMATION_MESSAGE);
        }
        
        return miLista;
    } 
}
