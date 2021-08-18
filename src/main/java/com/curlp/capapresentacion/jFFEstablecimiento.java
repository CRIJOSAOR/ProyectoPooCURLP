/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
//este es el coreecto saa
package com.curlp.capapresentacion;

import com.curlp.capadatos.CDEstablecimiento;
import com.curlp.capalogica.CLEstablecimiento;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.ImageIcon;
/**
 *
 * @author usuario
 */
public class jFFEstablecimiento extends javax.swing.JFrame {

    /**
     * Creates new form jFFEstablecimiento
     */
    public jFFEstablecimiento() throws SQLException {
        initComponents();
        poblarTablasEstablecimiento();
        agregarIconos();
        //encontrarCorrelativo();
        //this.jTFNombre_Establecimiento.requestFocus();
        this.setLocationRelativeTo(null);

    }

    //metodo  limpiar tabla
    private void limpiarTabla() {
        DefaultTableModel dtm = (DefaultTableModel) this.jTblEstablecimiento.getModel();

        while (dtm.getRowCount() > 0) {
            dtm.removeRow(0);

        }

    }
    //metodo para poblar datos

    private void poblarTablasEstablecimiento() throws SQLException {
        limpiarTabla();

        CDEstablecimiento cde = new CDEstablecimiento();
        List<CLEstablecimiento> miLista = cde.obtenerListaEstablecimiento();
        DefaultTableModel temp = (DefaultTableModel) this.jTblEstablecimiento.getModel();

        miLista.stream().map((CLEstablecimiento cl) -> {
            Object[] fila = new Object[2];
            fila[0] = cl.getCodEstablecimiento();
            fila[1] = cl.getNombreEstablecimiento();
            return fila;
        }).forEachOrdered(temp::addRow);
    }
    // Metodo para crear el correlativo 

    private void encontrarCorrelativo() throws SQLException {
        CDEstablecimiento cde = new CDEstablecimiento();
        CLEstablecimiento cl = new CLEstablecimiento();

       //cl.setCodEstablecimiento(cde.autoIncrementarEstablecimiento());
        this.jTFCod_Establecimiento.setText(String.valueOf(cl.getCodEstablecimiento()));

    }

    //metodo para habilitar y deshabilitar botones
    private void habilitarBotones(boolean guardar, boolean editar, boolean eliminar, boolean limpiar) {
        this.jBtnGuardar.setEnabled(guardar);
        this.jBtnEditar.setEnabled(editar);
        this.jBtnEliminar.setEnabled(eliminar);
        this.jBtnLimpiar.setEnabled(limpiar);
    }

    //metodos para limpiar textFiled
    private void limpiarTextField() throws SQLException {
        this.jTFCod_Establecimiento.setEditable(true);

        this.jTFCod_Establecimiento.setText("");
        this.jTFNombre_Establecimiento.setText("");
        this.jTFCod_Establecimiento.requestFocus();
        //this.encontrarCorrelativo();
        this.habilitarBotones(true, false, false, true);

    }

    // metodo para validar la TextField
    private boolean validarTextField() {
        boolean estado;

        estado = !this.jTFNombre_Establecimiento.getText().equals("");

        return estado;
    }

    private void insertarEstablecimiento() {
        if (!validarTextField()) {
             JOptionPane.showMessageDialog(null, "tiene que ingresar nombre del Establecimiento", "Proyecto Vacunación",
                                           JOptionPane.INFORMATION_MESSAGE);
             this.jTFNombre_Establecimiento.requestFocus();
        }else{
            
        
            try {
                CDEstablecimiento cde = new CDEstablecimiento();
                CLEstablecimiento cl = new CLEstablecimiento();
                 cl.setCodEstablecimiento(Integer.parseInt(this.jTFCod_Establecimiento.getText().trim()));
                cl.setNombreEstablecimiento(this.jTFNombre_Establecimiento.getText().trim());
               
                cde.insertarEstablecimiento(cl);
                
                JOptionPane.showMessageDialog(null, "Registrado almacenado satifactoriamente", "Proyecto Vacunación",
                                               JOptionPane.INFORMATION_MESSAGE);
                
              
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "Error al almacenar" + ex);
                this.jTFNombre_Establecimiento.requestFocus();
            }
         } 
    }

    private void guardar() throws SQLException {
        insertarEstablecimiento();
        poblarTablasEstablecimiento();
        habilitarBotones(true, false, false, true);
        encontrarCorrelativo();
        limpiarTextField();

    }

    //metodo para actualicar un establecimiento
    private void actualizarEstablecimiento() {
        if (!validarTextField()) {
            JOptionPane.showMessageDialog(null, "tiene que ingresar el nombre del Establecimiento ", "Proyecto Vacunación",
                                                 JOptionPane.INFORMATION_MESSAGE);
               
            this.jTFNombre_Establecimiento.requestFocus();
            
               }else{
            try {
                CDEstablecimiento cde = new CDEstablecimiento();
                CLEstablecimiento cl = new CLEstablecimiento();
                cl.setCodEstablecimiento(Integer.parseInt(this.jTFCod_Establecimiento.getText().trim()));
                cl.setNombreEstablecimiento(this.jTFNombre_Establecimiento.getText().trim());
                cde.actualizarEstablecimiento(cl);

                JOptionPane.showMessageDialog(null, "Registro actualizado satisfactoriamente ", "Proyecto Vacunación",
                        JOptionPane.INFORMATION_MESSAGE);
                
                

            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "Error al modificar el registro" + ex);
                this.jTFNombre_Establecimiento.requestFocus();
            }
        }
    }

    //metodo para seleccionar los datos de la fila y modificarlos
    private void filaSeleccionada() {
        if (this.jTblEstablecimiento.getSelectedRow() != -1) {
            this.jTFCod_Establecimiento.setText(String.valueOf(this.jTblEstablecimiento.getValueAt(this.jTblEstablecimiento.getSelectedRow(), 0)));
            this.jTFNombre_Establecimiento.setText(String.valueOf(this.jTblEstablecimiento.getValueAt(this.jTblEstablecimiento.getSelectedRow(), 1)));
            this.jTFCod_Establecimiento.setEditable(false);
            this.jTFNombre_Establecimiento.requestFocus();
        }
    }

    // metodo para llamar el metodo de actualizar registro de la base de datos
    private void editar() throws SQLException {
        actualizarEstablecimiento();
        poblarTablasEstablecimiento();
        habilitarBotones(true, false, false, true);
        encontrarCorrelativo();
        limpiarTextField();

    }

    //metodo para eliminar
    public void eliminarEstablecimiento() {
        try {
            CDEstablecimiento cde = new CDEstablecimiento();
            CLEstablecimiento cl = new CLEstablecimiento();
            cl.setCodEstablecimiento(Integer.parseInt(this.jTFCod_Establecimiento.getText().trim()));
            cde.eliminarEstablecimiento(cl);

            JOptionPane.showMessageDialog(null, "Registro eliminado correctamente", "Proyecto Vacunación", JOptionPane.INFORMATION_MESSAGE);
            this.jTFNombre_Establecimiento.requestFocus();

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al eliminar " + e);
            this.jTFNombre_Establecimiento.requestFocus();
        }
    }

    public void eliminar() throws SQLException {
        int resp = JOptionPane.showConfirmDialog(null, "¿esta seguro de eliminar el registro?", "registro vacuna",
                JOptionPane.YES_NO_CANCEL_OPTION);
        if (resp == JOptionPane.YES_OPTION) {
            try {
                eliminarEstablecimiento();
                poblarTablasEstablecimiento();
                habilitarBotones(true, false, false, true);
                encontrarCorrelativo();
                limpiarTextField();

            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "Error " + ex);
            }

        } else {
            limpiarTextField();
        }
    }
public final void agregarIconos() {
        ImageIcon iconbtnGuardar = new ImageIcon("src/main/java/com/curlp/capaimagenes/save.png");
        ImageIcon iconbtnEditar = new ImageIcon("src/main/java/com/curlp/capaimagenes/edit.png");
        ImageIcon iconbtnEliminar = new ImageIcon("src/main/java/com/curlp/capaimagenes/delete.png");
        ImageIcon iconbtnLimpiar = new ImageIcon("src/main/java/com/curlp/capaimagenes/Limpiar.png");
        this.jBtnGuardar.setIcon(iconbtnGuardar);
        this.jBtnEditar.setIcon(iconbtnEditar);
        this.jBtnEliminar.setIcon(iconbtnEliminar);
        this.jBtnLimpiar .setIcon(iconbtnLimpiar);
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jTFCod_Establecimiento = new javax.swing.JTextField();
        jTFNombre_Establecimiento = new javax.swing.JTextField();
        jBtnGuardar = new javax.swing.JButton();
        jBtnEditar = new javax.swing.JButton();
        jBtnLimpiar = new javax.swing.JButton();
        jBtnEliminar = new javax.swing.JButton();
        jLblXEstablecimiento = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTblEstablecimiento = new javax.swing.JTable();
        jPanel4 = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(255, 255, 255));
        setUndecorated(true);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setFont(new java.awt.Font("Tahoma", 3, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 153, 153));
        jLabel1.setText("GESTION DE ESTABLECIMIENTO");

        jPanel2.setBackground(new java.awt.Color(0, 153, 153));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 26, Short.MAX_VALUE)
        );

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true), "Establecimiento", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 3, 12))); // NOI18N

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel2.setText("Nombre Establecimiento");

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel3.setText("Codigo de Establecimiento");

        jTFCod_Establecimiento.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTFCod_EstablecimientoActionPerformed(evt);
            }
        });

        jTFNombre_Establecimiento.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTFNombre_EstablecimientoActionPerformed(evt);
            }
        });

        jBtnGuardar.setBackground(new java.awt.Color(255, 255, 255));
        jBtnGuardar.setFont(new java.awt.Font("Tahoma", 3, 12)); // NOI18N
        jBtnGuardar.setForeground(new java.awt.Color(0, 153, 153));
        jBtnGuardar.setText("Guardar");
        jBtnGuardar.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jBtnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnGuardarActionPerformed(evt);
            }
        });

        jBtnEditar.setBackground(new java.awt.Color(255, 255, 255));
        jBtnEditar.setFont(new java.awt.Font("Tahoma", 3, 12)); // NOI18N
        jBtnEditar.setForeground(new java.awt.Color(0, 153, 153));
        jBtnEditar.setText("Editar");
        jBtnEditar.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jBtnEditar.setEnabled(false);
        jBtnEditar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnEditarActionPerformed(evt);
            }
        });

        jBtnLimpiar.setBackground(new java.awt.Color(255, 255, 255));
        jBtnLimpiar.setFont(new java.awt.Font("Tahoma", 3, 12)); // NOI18N
        jBtnLimpiar.setForeground(new java.awt.Color(0, 153, 153));
        jBtnLimpiar.setText("Limpiar");
        jBtnLimpiar.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jBtnLimpiar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnLimpiarActionPerformed(evt);
            }
        });

        jBtnEliminar.setBackground(new java.awt.Color(255, 255, 255));
        jBtnEliminar.setFont(new java.awt.Font("Tahoma", 3, 12)); // NOI18N
        jBtnEliminar.setForeground(new java.awt.Color(0, 153, 153));
        jBtnEliminar.setText("Eliminar");
        jBtnEliminar.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jBtnEliminar.setEnabled(false);
        jBtnEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnEliminarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(78, 78, 78)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jBtnGuardar, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(48, 48, 48)
                        .addComponent(jBtnEliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(74, 74, 74)
                        .addComponent(jBtnEditar, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(63, 63, 63)
                        .addComponent(jBtnLimpiar, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel3Layout.createSequentialGroup()
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 187, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jTFNombre_Establecimiento, javax.swing.GroupLayout.PREFERRED_SIZE, 423, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel3Layout.createSequentialGroup()
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 187, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jTFCod_Establecimiento, javax.swing.GroupLayout.PREFERRED_SIZE, 417, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(205, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTFCod_Establecimiento, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTFNombre_Establecimiento, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(37, 37, 37)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jBtnEditar, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jBtnGuardar, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jBtnEliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jBtnLimpiar, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLblXEstablecimiento.setFont(new java.awt.Font("Tahoma", 3, 48)); // NOI18N
        jLblXEstablecimiento.setForeground(new java.awt.Color(0, 153, 153));
        jLblXEstablecimiento.setText("x");
        jLblXEstablecimiento.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jLblXEstablecimientoMousePressed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 440, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(206, 206, 206)
                        .addComponent(jLblXEstablecimiento, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE))))
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLblXEstablecimiento, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTblEstablecimiento.setBackground(new java.awt.Color(204, 255, 204));
        jTblEstablecimiento.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true));
        jTblEstablecimiento.setFont(new java.awt.Font("Tahoma", 3, 11)); // NOI18N
        jTblEstablecimiento.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "cod Establecimiento", "nombre Establecimiento"
            }
        ));
        jTblEstablecimiento.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTblEstablecimientoMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTblEstablecimiento);

        jPanel4.setBackground(new java.awt.Color(0, 153, 153));

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 22, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(2, 2, 2)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(35, 35, 35)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 184, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jLblXEstablecimientoMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLblXEstablecimientoMousePressed
        this.dispose();

    }//GEN-LAST:event_jLblXEstablecimientoMousePressed

    private void jTFNombre_EstablecimientoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTFNombre_EstablecimientoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTFNombre_EstablecimientoActionPerformed

    private void jBtnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnGuardarActionPerformed
        try {
            guardar();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al guardar" + ex);
        }
    }//GEN-LAST:event_jBtnGuardarActionPerformed

    private void jTblEstablecimientoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTblEstablecimientoMouseClicked
        filaSeleccionada();
        habilitarBotones(false, true, true, true);
    }//GEN-LAST:event_jTblEstablecimientoMouseClicked

    private void jBtnEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnEditarActionPerformed
        try {
            editar();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al editar" + ex);
        }
    }//GEN-LAST:event_jBtnEditarActionPerformed

    private void jBtnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnEliminarActionPerformed
        try {
            eliminar();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al eliminar" + ex);
        }

    }//GEN-LAST:event_jBtnEliminarActionPerformed

    private void jBtnLimpiarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnLimpiarActionPerformed

        try {
            limpiarTextField();
        } catch (SQLException ex) {
            Logger.getLogger(jFFEstablecimiento.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jBtnLimpiarActionPerformed

    private void jTFCod_EstablecimientoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTFCod_EstablecimientoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTFCod_EstablecimientoActionPerformed

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
            java.util.logging.Logger.getLogger(jFFEstablecimiento.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(jFFEstablecimiento.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(jFFEstablecimiento.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(jFFEstablecimiento.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new jFFEstablecimiento().setVisible(true);
                } catch (SQLException ex) {
                    Logger.getLogger(jFFEstablecimiento.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jBtnEditar;
    private javax.swing.JButton jBtnEliminar;
    private javax.swing.JButton jBtnGuardar;
    private javax.swing.JButton jBtnLimpiar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLblXEstablecimiento;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField jTFCod_Establecimiento;
    private javax.swing.JTextField jTFNombre_Establecimiento;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTable jTblEstablecimiento;
    // End of variables declaration//GEN-END:variables

}
