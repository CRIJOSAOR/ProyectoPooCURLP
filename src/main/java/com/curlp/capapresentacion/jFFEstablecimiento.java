/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.curlp.capapresentacion;

import com.curlp.capadatos.CDEstablecimiento;
import com.curlp.capalogica.CLEstablecimiento;

import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

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
        encontrarCorrelativo();
        this.jTFNombre_Establecimiento.requestFocus();
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

        CDEstablecimiento cdc = new CDEstablecimiento();
        List<CLEstablecimiento> miLista = cdc.obtenerListaEstablecimiento();
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
        CDEstablecimiento cdp = new CDEstablecimiento();
        CLEstablecimiento cl = new CLEstablecimiento();

        cl.setCodEstablecimiento(cdp.autoincrementarEstablecimiento());
        this.jTFCod_Establecimiento.setText(String.valueOf(cl.getCodEstablecimiento()));

    }

    //metodo para habilitar y deshabilitar botones
    private void habilitarBotones(boolean agregar, boolean editar, boolean eliminar, boolean limpiar) {
        this.jBtnAgregrar.setEnabled(agregar);
        this.jBtnEditar.setEnabled(editar);
        this.jBtnEliminar.setEnabled(eliminar);
        this.jBtnLimpiar.setEnabled(limpiar);
    }

    //metodos para limpiar textFiled
    private void limpiarTextField() {
        this.jTFCod_Establecimiento.setText("");
        this.jTFNombre_Establecimiento.setText("");
        this.jTFNombre_Establecimiento.requestFocus();

    }

    // metodo para validar la TextField
    private boolean validarTextField() {
        boolean estado;

        estado = this.jTFNombre_Establecimiento.getText().equals("");

        return estado;
    }

    private void insertarEstablecimiento() {
        if (!validarTextField()) {
            JOptionPane.showMessageDialog(null, "Ingrese el nombre del Establecimiento ", "Proyecto Vacunación",
                    JOptionPane.INFORMATION_MESSAGE);
            
            this.jTFNombre_Establecimiento.requestFocus();
        } else {
            try {
                CDEstablecimiento cde = new CDEstablecimiento();
                CLEstablecimiento cl = new CLEstablecimiento();
                cl.setNombreEstablecimiento(this.jTFNombre_Establecimiento.getText().trim());
                cde.insertarEstablecimiento(cl);
                JOptionPane.showMessageDialog(null, "Registrado correctamente", "Proyecto Vacunación",
                        JOptionPane.INFORMATION_MESSAGE);
                
                this.jTFNombre_Establecimiento.requestFocus();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Error al almacenar" + e);
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
            JOptionPane.showMessageDialog(null, "Ingrese el nombre del Establecimiento ", "Proyecto Vacunación",
                    JOptionPane.INFORMATION_MESSAGE);
            
            this.jTFNombre_Establecimiento.requestFocus();
        } else {
            try {
                CDEstablecimiento cde = new CDEstablecimiento();
                CLEstablecimiento cl = new CLEstablecimiento();
                cl.setCodEstablecimiento(Integer.parseInt(this.jTFCod_Establecimiento.getText().trim()));
                cl.setNombreEstablecimiento(this.jTFNombre_Establecimiento.getText().trim());
                cde.actualizarEstablecimiento(cl);

                JOptionPane.showMessageDialog(null, "Registrado actualizado", "Proyecto Vacunación",
                        JOptionPane.INFORMATION_MESSAGE);
                
                this.jTFNombre_Establecimiento.requestFocus();

            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Error al modificar almacenar" + e);
                this.jTFNombre_Establecimiento.requestFocus();
            }
        }
    }

    //metodo para seleccionar los datos de la fila y modificarlos
    private void filaSeleccionada() {
        if (this.jTblEstablecimiento.getSelectedRow() != -1) {
            this.jTFCod_Establecimiento.setText(String.valueOf(this.jTblEstablecimiento.getValueAt(this.jTblEstablecimiento.getSelectedRow(), 0)));
            this.jTFNombre_Establecimiento.setText(String.valueOf(this.jTblEstablecimiento.getValueAt(this.jTblEstablecimiento.getSelectedRow(), 1)));

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

            JOptionPane.showMessageDialog(null, "Registrado eliminado", "Proyecto Vacunación", JOptionPane.INFORMATION_MESSAGE);
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

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLblXEstablecimiento = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jTFCod_Establecimiento = new javax.swing.JTextField();
        jTFNombre_Establecimiento = new javax.swing.JTextField();
        jBtnGuardar = new javax.swing.JButton();
        jBtnEditar = new javax.swing.JButton();
        jBtnLimpiar = new javax.swing.JButton();
        jBtnEliminar = new javax.swing.JButton();
        jBtnAgregrar = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTblEstablecimiento = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(153, 153, 153));

        jPanel1.setBackground(new java.awt.Color(51, 102, 255));

        jLabel1.setFont(new java.awt.Font("Tahoma", 3, 48)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("ESTABLECIMIENTO");

        jLblXEstablecimiento.setFont(new java.awt.Font("Tahoma", 0, 48)); // NOI18N
        jLblXEstablecimiento.setForeground(new java.awt.Color(255, 0, 0));
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
                .addContainerGap(198, Short.MAX_VALUE)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 515, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(155, 155, 155)
                .addComponent(jLblXEstablecimiento, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLblXEstablecimiento, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 17, Short.MAX_VALUE))
        );

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true), "Establecimiento", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 3, 12))); // NOI18N

        jLabel2.setFont(new java.awt.Font("Tahoma", 3, 12)); // NOI18N
        jLabel2.setText("Nombre Establecimiento");

        jLabel3.setFont(new java.awt.Font("Tahoma", 3, 12)); // NOI18N
        jLabel3.setText("Codigo de Establecimiento");

        jTFNombre_Establecimiento.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTFNombre_EstablecimientoActionPerformed(evt);
            }
        });

        jBtnGuardar.setBackground(new java.awt.Color(255, 255, 255));
        jBtnGuardar.setFont(new java.awt.Font("Tahoma", 3, 12)); // NOI18N
        jBtnGuardar.setText("Guardar");
        jBtnGuardar.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jBtnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnGuardarActionPerformed(evt);
            }
        });

        jBtnEditar.setBackground(new java.awt.Color(255, 255, 255));
        jBtnEditar.setFont(new java.awt.Font("Tahoma", 3, 12)); // NOI18N
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
        jBtnLimpiar.setText("Limpiar");
        jBtnLimpiar.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jBtnLimpiar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnLimpiarActionPerformed(evt);
            }
        });

        jBtnEliminar.setBackground(new java.awt.Color(255, 255, 255));
        jBtnEliminar.setFont(new java.awt.Font("Tahoma", 3, 12)); // NOI18N
        jBtnEliminar.setText("Eliminar");
        jBtnEliminar.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jBtnEliminar.setEnabled(false);
        jBtnEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnEliminarActionPerformed(evt);
            }
        });

        jBtnAgregrar.setBackground(new java.awt.Color(255, 255, 255));
        jBtnAgregrar.setFont(new java.awt.Font("Tahoma", 3, 12)); // NOI18N
        jBtnAgregrar.setText("Agregar");
        jBtnAgregrar.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jBtnAgregrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnAgregrarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(134, 134, 134)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 187, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jTFNombre_Establecimiento))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 187, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jTFCod_Establecimiento, javax.swing.GroupLayout.PREFERRED_SIZE, 333, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(39, 39, 39)
                        .addComponent(jBtnAgregrar, javax.swing.GroupLayout.PREFERRED_SIZE, 318, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jBtnLimpiar, javax.swing.GroupLayout.PREFERRED_SIZE, 274, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 84, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jBtnEliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jBtnGuardar, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jBtnEditar, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(33, 33, 33))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTFCod_Establecimiento, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jBtnGuardar, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTFNombre_Establecimiento, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(41, 41, 41)
                        .addComponent(jBtnEditar, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(43, 43, 43)
                        .addComponent(jBtnEliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(30, 30, 30)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jBtnLimpiar, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jBtnAgregrar, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(23, Short.MAX_VALUE))
        );

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 34, Short.MAX_VALUE)
        );

        jTblEstablecimiento.setBackground(new java.awt.Color(204, 204, 204));
        jTblEstablecimiento.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true));
        jTblEstablecimiento.setForeground(new java.awt.Color(0, 48, 177));
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

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 891, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 184, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(204, 204, 204))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jLblXEstablecimientoMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLblXEstablecimientoMousePressed
        this.dispose();

    }//GEN-LAST:event_jLblXEstablecimientoMousePressed

    private void jTFNombre_EstablecimientoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTFNombre_EstablecimientoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTFNombre_EstablecimientoActionPerformed

    private void jBtnAgregrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnAgregrarActionPerformed

    }//GEN-LAST:event_jBtnAgregrarActionPerformed

    private void jBtnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnGuardarActionPerformed
        try {
            guardar();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al almacenar" + ex);
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
            JOptionPane.showMessageDialog(null, "Error al actualizar" + ex);
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

        limpiarTextField();
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
    private javax.swing.JButton jBtnAgregrar;
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
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField jTFCod_Establecimiento;
    private javax.swing.JTextField jTFNombre_Establecimiento;
    private javax.swing.JTable jTblEstablecimiento;
    // End of variables declaration//GEN-END:variables

}