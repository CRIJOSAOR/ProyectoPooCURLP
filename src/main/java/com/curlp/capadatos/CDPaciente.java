/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.curlp.capadatos;

import com.curlp.capalogica.CLPaciente;
import java.util.List;
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
public class CDPaciente {
    private final Connection cn;
    PreparedStatement ps;
    ResultSet rs;
    Statement st;

    public CDPaciente() throws SQLException {
        this.cn = conexion.conectar();
    }
    
    public void insertarPaciente(CLPaciente cl) throws SQLException {
        String sql = "CALL insertarPaciente(?,?,?,?,?,?,?,?,?)";
        try{
            ps = cn.prepareCall(sql);
            ps.setString(1, cl.getNumIdentidad());
            ps.setString(2, cl.getNombres());
            ps.setString(3, cl.getApellidos());
            ps.setString(4, cl.getNumCelular());
            ps.setString(5, cl.getFechaNacimiento());
            ps.setString(6, cl.getLugarTrabajo());
            ps.setString(7, cl.getDireccion());
            ps.setInt(8, cl.getIdSexo());
            ps.setInt(9, cl.getIdProfesion());
            ps.execute();
            JOptionPane.showMessageDialog(null,"Registrado con Exito");
        } catch(SQLException e){
            JOptionPane.showMessageDialog(null,"Error al Ingresar -- Vuelva a intentarlo","Error",JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public void actualizarPaciente(CLPaciente cl) throws SQLException {
        String sql = "CALL actualizarPaciente(?,?,?,?,?,?,?,?,?)";
        try{
            ps = cn.prepareCall(sql);
            ps.setString(1, cl.getNumIdentidad());
            ps.setString(2, cl.getNombres());
            ps.setString(3, cl.getApellidos());
            ps.setString(4, cl.getNumCelular());
            ps.setString(5, cl.getFechaNacimiento());
            ps.setString(6, cl.getLugarTrabajo());
            ps.setString(7, cl.getDireccion());
            ps.setInt(8, cl.getIdSexo());
            ps.setInt(9, cl.getIdProfesion());
            ps.execute();
            JOptionPane.showMessageDialog(null,"Actualizado con Exito");
        } catch(SQLException e){
            JOptionPane.showMessageDialog(null,"Error al Actualizar -- Vuelva a intentarlo","Error",JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public void eliminarPaciente(CLPaciente cl) throws SQLException {
        String sql = "CALL eliminarPaciente(?)";
        try{
            ps = cn.prepareCall(sql);
            ps.setString(1, cl.getNumIdentidad());
            ps.execute();
            JOptionPane.showMessageDialog(null,"Eliminado con Exito");
        } catch(SQLException e){
            JOptionPane.showMessageDialog(null,"Error al Eliminar -- Vuelva a intentarlo","Error",JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public List<CLPaciente> mostrarPacientes() throws SQLException {
        String sql = "CALL mostrarPacientes()";
        List<CLPaciente> miLista = null;
        
        try{
            
            st = cn.createStatement();
            rs = st.executeQuery(sql);
            
            miLista = new ArrayList<>();
            
            while(rs.next()){
                CLPaciente cl = new CLPaciente();
                cl.setNumIdentidad(rs.getString("p.dniPaciente"));
                cl.setNombres(rs.getString("p.nombrePaciente"));
                cl.setApellidos(rs.getString("p.apellidoPaciente"));
                cl.setNumCelular(rs.getString("p.numCelular"));
                cl.setFechaNacimiento(rs.getString("p.fechaNacimiento"));
                cl.setLugarTrabajo(rs.getString("p.lugarTrabajo"));
                cl.setDireccion(rs.getString("p.direccionPaciente"));
                cl.setSexo(rs.getString("s.sexo"));
                cl.setProfesion(rs.getString("pr.profesion"));
                
                miLista.add(cl);
                
            }
        } catch(SQLException e){
            
            JOptionPane.showMessageDialog(null,"Error al mostrar -- Vuelva a intentarlo","Error",JOptionPane.ERROR_MESSAGE);
        }
        
        return miLista;
    }
    
    public CLPaciente mostrarPacienteX(String numIdentidad) throws SQLException {
        String sql = "CALL mostrarPacienteX(?)";
        CLPaciente cl = new CLPaciente();
        
        try{
            
            ps = cn.prepareStatement(sql);
            ps.setString(1, numIdentidad);
            rs = ps.executeQuery();
            
            cl.setNumIdentidad(rs.getString("p.dniPaciente"));
            cl.setNombres(rs.getString("p.nombrePaciente"));
            cl.setApellidos(rs.getString("p.apellidoPaciente"));
            cl.setNumCelular(rs.getString("p.numCelular"));
            cl.setFechaNacimiento(rs.getString("p.fechaNacimiento"));
            cl.setLugarTrabajo(rs.getString("p.lugarTrabajo"));
            cl.setDireccion(rs.getString("p.direccionPaciente"));
            cl.setSexo(rs.getString("s.sexo"));
            cl.setProfesion(rs.getString("pr.profesion"));
                
            
                
            
        } catch(SQLException e){
            
            JOptionPane.showMessageDialog(null,"Error al mostrar -- Vuelva a intentarlo","Error",JOptionPane.ERROR_MESSAGE);
        }
        
        return cl;
    }
    
    
}
