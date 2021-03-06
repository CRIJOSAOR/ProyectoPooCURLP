/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.curlp.capapresentacion;

import com.curlp.capadatos.CDPaciente;
import com.curlp.capadatos.CDProfesion;
import com.curlp.capadatos.CDVacunador;
import com.curlp.capalogica.CLPaciente;
import com.curlp.capalogica.CLProfesiones;
import com.curlp.capalogica.CLVacunador;
import java.awt.Color;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author raul
 */
public class JFFVacunador extends javax.swing.JFrame {

    private Border border;

    /**
     * Creates new form JFFVacunador
     */
    public JFFVacunador() throws SQLException {
        initComponents();
        poblarTablaVacunador();
        agregarIconos();
        
        this.JTFDNI.requestFocus();
        this.setLocationRelativeTo(null);
        this.borders = this.JTFDNI.getBorder();
    }
    
    private final Border borders;
    
    DefaultTableModel modelo;
    
    // Metodo para verificar las TextFields
    public boolean verificarCampos(){
        boolean verificador = true ;
        String errors = ""; 
        
        // verificacion de numero de identidad
        String numIdentidad = this.JTFDNI.getText();
        
        if(numIdentidad.length() == 15) { //se verifica si tiene el tamanio correcto de 15 elementos 
            //si el numero de identidad no tiene 15 elementos
            if(numIdentidad.charAt(4) != '-' && numIdentidad.charAt(9) != '-'){ //deben estar los guiones en la posicion correcta
                verificador = false;
                errors +=  "Debe agregar el número con los guiones, ejemplo: 0801-2000-00011 \n";
                this.JTFDNI.setBorder(BorderFactory.createLineBorder(Color.red));
            }
            
        } else {
            verificador = false;
            errors +=  "Debe ingresar correctamente el número de identidad, ejemplo: 0801-2000-00011 \n";
            this.JTFDNI.setBorder(BorderFactory.createLineBorder(Color.red));
        }

        // verificar que nombre y apellido este lleno
        
        if(this.JTFNombresV.getText().length() == 0) {
            verificador = false;
            errors +=  "debe ingresar el nombre \n";
            this.JTFNombresV.setBorder(BorderFactory.createLineBorder(Color.red));
            
        }
        
        if(this.JTFApellidosV.getText().length() == 0){
            verificador = false;
            errors +=  "debe ingresar el apellido \n";
            this.JTFApellidosV.setBorder(BorderFactory.createLineBorder(Color.red));
            
        }

        // verificar que el numero de telefono este lleno, tenga todos los numeros y el guion
        
        if(this.JTFCelularV.getText().length() == 0){
            verificador = false;
            errors +=  "debe ingresar el número de teléfono\n";
            this.JTFCelularV.setBorder(BorderFactory.createLineBorder(Color.red));
            
        }
        
        if(this.JTFCelularV.getText().length() == 9) {
            
            if('-' != this.JTFCelularV.getText().charAt(4)) {
                verificador = false;
                errors +=  "el número de teléfono debe llevar un guión\n";
                this.JTFCelularV.setBorder(BorderFactory.createLineBorder(Color.red));
            }
        } else {
            verificador = false;
            errors +=  "El número de teléfono es incorrecto, debe ir así: 0000-0000\n";
            this.JTFCelularV.setBorder(BorderFactory.createLineBorder(Color.red));
        }

        // verificar que el campo de direccion este lleno
        
        if(this.JTFDireccionV.getText().length() == 0) {
            verificador = false;
            errors +=  "No hay dirección ingresada \n";
            this.JTFDireccionV.setBorder(BorderFactory.createLineBorder(Color.red));
            
        }
        if(this.JTFEstado.getText().length() == 0) {
            verificador = false;
            errors +=  "No hay estado ingresado \n";
            this.JTFEstado.setBorder(BorderFactory.createLineBorder(Color.red));
            
        }
        
        if(verificador == false) {
           JOptionPane.showMessageDialog(null,errors);
        }
        return verificador;
    }
    
    // Metodo para insertar un vacunador
    private void insertarVacunador() {
        if (!verificarCampos()) {
            JOptionPane.showMessageDialog(null,"Ingresar los datos del vacunador","COVA System",JOptionPane.INFORMATION_MESSAGE);
            this.JTFDNI.requestFocus();
        } else {
            try {
                CDVacunador cdv = new CDVacunador();
                CLVacunador cl = new CLVacunador();
                cl.setDniVacunador(this.JTFDNI.getText().trim());
                cl.setNombresV(this.JTFNombresV.getText().trim());
                cl.setApellidosV(this.JTFApellidosV.getText().trim());
                cl.setDireccionV(this.JTFDireccionV.getText().trim());
                cl.setNumCelularV(this.JTFCelularV.getText().trim());
                cl.setEstado(this.JTFEstado.getText().trim());
                cdv.insertarVacunador(cl);
                JOptionPane.showMessageDialog(null,"¡Registrado correctamente!","COVA System",JOptionPane.INFORMATION_MESSAGE);
                this.JTFDNI.requestFocus();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null,"¡Error al almacenar!" + e);
                this.JTFDNI.requestFocus();
            }
        }
    }
    
    //metodo para poblar de datos la tabla
    private void poblarTablaVacunador() throws SQLException {
        
        limpiarTablaVacunador();
        
        CDVacunador cdv = new CDVacunador();
        List<CLVacunador> miLista = cdv.obtenerListaVacunadores();
        DefaultTableModel temp = (DefaultTableModel) this.JTblVacunadores.getModel();
        
        miLista.stream().map((CLVacunador cl) -> {
            Object[] fila = new Object[6];
            fila[0] = cl.getDniVacunador();
            fila[1] = cl.getNombresV();
            fila[2] = cl.getApellidosV();
            fila[3] = cl.getDireccionV();
            fila[4] = cl.getNumCelularV();
            fila[5] = cl.getEstado();
            return fila;
    }).forEachOrdered(temp::addRow);
    }
    
    // Metodo para actualizar al vacunador
    private void actualizarVacunador() {
        if (!verificarCampos()) {
            JOptionPane.showMessageDialog(null,"Ingresar los datos del vacunador","COVA System",JOptionPane.INFORMATION_MESSAGE);
            this.JTFDNI.requestFocus();
        } else {
            try {
                CDVacunador cdv = new CDVacunador();
                CLVacunador cl = new CLVacunador();
                cl.setDniVacunador(this.JTFDNI.getText().trim());
                cl.setNombresV(this.JTFNombresV.getText().trim());
                cl.setApellidosV(this.JTFApellidosV.getText().trim());
                cl.setDireccionV(this.JTFDireccionV.getText().trim());
                cl.setNumCelularV(this.JTFCelularV.getText().trim());
                cl.setEstado(this.JTFEstado.getText().trim());
                cdv.actualizarVacunador(cl);
                JOptionPane.showMessageDialog(null,"¡Registrado correctamente!","COVA System",JOptionPane.INFORMATION_MESSAGE);
                this.JTFDNI.requestFocus();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null,"¡Error al almacenar!" + e);
                this.JTFDNI.requestFocus();
            }
        }   
    }
    
    // Metodo para seleccionar los datos de la fila y poder modificarlos
    private void filaSeleccionada() {
        if (this.JTblVacunadores.getSelectedRow() != -1 ) {
            this.JTFDNI.setText(String.valueOf(this.JTblVacunadores.getValueAt(this.JTblVacunadores.getSelectedRow(), 0)));
            this.JTFNombresV.setText(String.valueOf(this.JTblVacunadores.getValueAt(this.JTblVacunadores.getSelectedRow(), 1)));
            this.JTFApellidosV.setText(String.valueOf(this.JTblVacunadores.getValueAt(this.JTblVacunadores.getSelectedRow(), 2)));
            this.JTFDireccionV.setText(String.valueOf(this.JTblVacunadores.getValueAt(this.JTblVacunadores.getSelectedRow(), 3)));
            this.JTFCelularV.setText(String.valueOf(this.JTblVacunadores.getValueAt(this.JTblVacunadores.getSelectedRow(), 4)));
            this.JTFEstado.setText(String.valueOf(this.JTblVacunadores.getValueAt(this.JTblVacunadores.getSelectedRow(), 5)));
        }
    }
    
    // metodo de clase que permite agregar iconos a los botones y labels del JFForm
    public final void agregarIconos() {
        ImageIcon iconLogoTitulo = new ImageIcon("src/main/java/com/curlp/capaimagenes/user.png");
        ImageIcon iconbtnRegistrar = new ImageIcon("src/main/java/com/curlp/capaimagenes/save.png");
        ImageIcon iconbtnActualizar = new ImageIcon("src/main/java/com/curlp/capaimagenes/edit.png");
        ImageIcon iconImage = new ImageIcon("src/main/java/com/curlp/capaimagenes/image.jpg");
        ImageIcon iconbtnEliminar = new ImageIcon("src/main/java/com/curlp/capaimagenes/delete.png");
        ImageIcon iconbtnSalir = new ImageIcon("src/main/java/com/curlp/capaimagenes/logout.png");
        ImageIcon iconbtnEditar = new ImageIcon("src/main/java/com/curlp/capaimagenes/limpiar.png");
        this.jBtnRegistrar.setIcon(iconbtnRegistrar);
        this.jLBiconoNombres.setIcon(iconLogoTitulo);
        this.jBtnActualizar.setIcon(iconbtnActualizar);
        this.jBtnEliminar.setIcon(iconbtnEliminar);
        this.jBtnSalir.setIcon(iconbtnSalir);
        this.jBtnLimpiar.setIcon(iconbtnEditar);
        
    }
     
    // Metodo para limpiar tabla
    private void limpiarTablaVacunador(){
       
        DefaultTableModel dtm = (DefaultTableModel) this.JTblVacunadores.getModel();
        
        while (dtm.getRowCount() > 0) {
            dtm.removeRow(0);
        }
        
    }
    // Metodo para limpiar TextFields
    private void limpiarTextField() {
        this.JTFDNI.setText("");
        this.JTFNombresV.setText("");
        this.JTFApellidosV.setText("");
        this.JTFDireccionV.setText("");
        this.JTFCelularV.setText("");
        this.JTFEstado.setText("");
    }    
   
    // Metodo para limpiar 
    public void limpiarCampos(){
        this.JTFDNI.setText("");
        this.JTFNombresV.setText("");
        this.JTFApellidosV.setText("");
        this.JTFCelularV.setText("");
        this.JTFDireccionV.setText("");
        this.JTFEstado.setText("");
        
        // devolver los bordes a default
        
        this.JTFDNI.setBorder(this.borders);
        this.JTFNombresV.setBorder(this.borders);
        this.JTFApellidosV.setBorder(this.borders);
        this.JTFDireccionV.setBorder(this.borders);
        this.JTFCelularV.setBorder(this.borders);
        this.JTFEstado.setBorder(this.borders);
        
    }
    
    //Metodo para desahibilitar botones
    private void habilitarBotones(boolean registrar, boolean actualizar, boolean eliminar) {
        this.jBtnRegistrar.setEnabled(registrar);
        this.jBtnActualizar.setEnabled(actualizar);
        this.jBtnEliminar.setEnabled(eliminar);
    }
     
    
    // Metodo para insertar
    private void insertar() throws SQLException {
        insertarVacunador();
        poblarTablaVacunador();
        limpiarCampos();
        habilitarBotones(true,false,false);
        limpiarTextField();
        
    }
    
    // Metodo para actualizar
    private void actualizar() throws SQLException {
        actualizarVacunador();
        poblarTablaVacunador();
        limpiarCampos();
        habilitarBotones(true,true,false);
        limpiarTextField();
        
    }
    
    // Metodo para eliminar
    private void eliminarVacunador() {
        try {
            CDVacunador cdv = new CDVacunador();
            CLVacunador cl = new CLVacunador();
            cl.setDniVacunador(this.JTFDNI.getText().trim());
            cdv.eliminarVacunador(cl);
            JOptionPane.showMessageDialog(null, "¡Registro eliminado satisfactoriamente!", "COVA System", JOptionPane.INFORMATION_MESSAGE);
            this.JTFDNI.requestFocus();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al eliminar el registro" + e);
            this.JTFDNI.requestFocus();
        }    
    }
    
    private void eliminar() throws SQLException {
        int resp = JOptionPane.showConfirmDialog(null, "¿Está seguro que desea eliminar el registro del vacunador?",
                                                 "COVA System", JOptionPane.YES_NO_OPTION);
        
        if (resp == JOptionPane.YES_OPTION) {
            try {
                eliminarVacunador();
                poblarTablaVacunador();
                limpiarCampos();
                habilitarBotones(true, false, true);
                limpiarTextField();
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "Error al eliminar el registro: "+ ex);        
            }
        } else {
            limpiarCampos();
        }
            
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jBtnSalir = new javax.swing.JButton();
        jLBiconoNombres = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        JTblVacunadores = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        Celular = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        JTFDNI = new javax.swing.JTextField();
        JTFCelularV = new javax.swing.JTextField();
        JTFApellidosV = new javax.swing.JTextField();
        JTFNombresV = new javax.swing.JTextField();
        JTFDireccionV = new javax.swing.JTextField();
        JTFEstado = new javax.swing.JTextField();
        jBtnRegistrar = new javax.swing.JButton();
        jBtnActualizar = new javax.swing.JButton();
        jBtnEliminar = new javax.swing.JButton();
        jBtnLimpiar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setForeground(new java.awt.Color(153, 204, 255));

        jLabel1.setBackground(new java.awt.Color(0, 153, 153));
        jLabel1.setFont(new java.awt.Font("Tahoma", 3, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 153, 153));
        jLabel1.setText("Registro de Vacunador");

        jBtnSalir.setBackground(new java.awt.Color(255, 255, 255));
        jBtnSalir.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jBtnSalir.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jBtnSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnSalirActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(59, 59, 59)
                .addComponent(jLBiconoNombres, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(246, 246, 246)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 294, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 497, Short.MAX_VALUE)
                .addComponent(jBtnSalir, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addComponent(jLabel1)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jBtnSalir, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addComponent(jLBiconoNombres, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap())
        );

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1260, 70));

        jPanel3.setBackground(new java.awt.Color(0, 153, 153));

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1260, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 10, Short.MAX_VALUE)
        );

        getContentPane().add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 70, 1260, 10));

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));

        JTblVacunadores.setBackground(new java.awt.Color(204, 255, 204));
        JTblVacunadores.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 102, 102)));
        JTblVacunadores.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        JTblVacunadores.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "DNI", "Nombres", "Apellidos", "Dirección", "Número celular", "Estado"
            }
        ));
        JTblVacunadores.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                JTblVacunadoresMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(JTblVacunadores);

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 102, 102)), "Ingrese sus datos", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 14))); // NOI18N

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel2.setText("Número de Identidad:");

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel3.setText("Nombres:");

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel4.setText("Apellidos:");

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel5.setText("Dirección de residencia:");

        Celular.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        Celular.setText("Celular:");

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel6.setText("Estado:");

        JTFCelularV.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JTFCelularVActionPerformed(evt);
            }
        });

        jBtnRegistrar.setBackground(new java.awt.Color(255, 255, 255));
        jBtnRegistrar.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jBtnRegistrar.setForeground(new java.awt.Color(0, 153, 153));
        jBtnRegistrar.setText("Registrar");
        jBtnRegistrar.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jBtnRegistrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnRegistrarActionPerformed(evt);
            }
        });

        jBtnActualizar.setBackground(new java.awt.Color(255, 255, 255));
        jBtnActualizar.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jBtnActualizar.setForeground(new java.awt.Color(0, 153, 153));
        jBtnActualizar.setText("Actualizar");
        jBtnActualizar.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jBtnActualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnActualizarActionPerformed(evt);
            }
        });

        jBtnEliminar.setBackground(new java.awt.Color(255, 255, 255));
        jBtnEliminar.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jBtnEliminar.setForeground(new java.awt.Color(0, 153, 153));
        jBtnEliminar.setText("Eliminar");
        jBtnEliminar.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jBtnEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnEliminarActionPerformed(evt);
            }
        });

        jBtnLimpiar.setBackground(new java.awt.Color(255, 255, 255));
        jBtnLimpiar.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jBtnLimpiar.setForeground(new java.awt.Color(0, 153, 153));
        jBtnLimpiar.setText("Limpiar");
        jBtnLimpiar.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jBtnLimpiar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnLimpiarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(JTFDNI, javax.swing.GroupLayout.PREFERRED_SIZE, 251, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(59, 59, 59))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(Celular)
                                .addGap(18, 18, 18)
                                .addComponent(JTFCelularV, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(JTFNombresV, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel4)
                            .addComponent(jLabel6))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(JTFApellidosV)
                            .addComponent(JTFEstado)))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel5)
                            .addComponent(jBtnRegistrar, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(76, 76, 76)
                                .addComponent(JTFDireccionV))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(11, 11, 11)
                                .addComponent(jBtnActualizar, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 42, Short.MAX_VALUE)
                                .addComponent(jBtnEliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(34, 34, 34)
                                .addComponent(jBtnLimpiar, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addGap(43, 43, 43))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(JTFDNI, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.TRAILING))
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(34, 34, 34)
                        .addComponent(jLabel3))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(JTFApellidosV, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(JTFNombresV, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(Celular)
                            .addComponent(JTFCelularV, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6))
                        .addGap(67, 67, 67))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(JTFEstado, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(JTFDireccionV, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jBtnRegistrar, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jBtnActualizar, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jBtnLimpiar, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jBtnEliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(96, 96, 96))
        );

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 600, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 390, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 379, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(71, Short.MAX_VALUE))
        );

        getContentPane().add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 80, 1260, 480));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void JTFCelularVActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JTFCelularVActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_JTFCelularVActionPerformed

    private void jBtnRegistrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnRegistrarActionPerformed
        try {
            insertar();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,"No se registro el vacunador");
        }
    
    }//GEN-LAST:event_jBtnRegistrarActionPerformed

    private void jBtnActualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnActualizarActionPerformed
        try {
            actualizar();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,"No se registro el vacunador");
        }
    
    }//GEN-LAST:event_jBtnActualizarActionPerformed

    private void JTblVacunadoresMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_JTblVacunadoresMouseClicked
        filaSeleccionada();
        habilitarBotones(false,true,true);
    }//GEN-LAST:event_JTblVacunadoresMouseClicked

    private void jBtnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnEliminarActionPerformed
        try {
            eliminar();
        } catch (SQLException ex) {
            Logger.getLogger(JFFVacunador.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jBtnEliminarActionPerformed

    private void jBtnSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnSalirActionPerformed
        this.dispose();
    }//GEN-LAST:event_jBtnSalirActionPerformed

    private void jBtnLimpiarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnLimpiarActionPerformed
        limpiarCampos();
    }//GEN-LAST:event_jBtnLimpiarActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(JFFVacunador.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(JFFVacunador.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(JFFVacunador.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(JFFVacunador.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    
                    new JFFVacunador().setVisible(true);
                } catch (SQLException ex) {
                    Logger.getLogger(JFFVacunador.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Celular;
    private javax.swing.JTextField JTFApellidosV;
    private javax.swing.JTextField JTFCelularV;
    private javax.swing.JTextField JTFDNI;
    private javax.swing.JTextField JTFDireccionV;
    private javax.swing.JTextField JTFEstado;
    private javax.swing.JTextField JTFNombresV;
    private javax.swing.JTable JTblVacunadores;
    private javax.swing.JButton jBtnActualizar;
    private javax.swing.JButton jBtnEliminar;
    private javax.swing.JButton jBtnLimpiar;
    private javax.swing.JButton jBtnRegistrar;
    private javax.swing.JButton jBtnSalir;
    private javax.swing.JLabel jLBiconoNombres;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane2;
    // End of variables declaration//GEN-END:variables

    
}
