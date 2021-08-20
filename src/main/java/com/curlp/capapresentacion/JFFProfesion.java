/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.curlp.capapresentacion;

import com.curlp.capadatos.CDProfesion;
import com.curlp.capalogica.CLProfesiones;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.ImageIcon;

/**
 *
 * @author Letty Ochoa
 */
public class JFFProfesion extends javax.swing.JFrame {

    /**
     * Creates new form JFFProfesion
     */
    public JFFProfesion() throws SQLException {
        initComponents();
        agregarIconos();
        poblarTablaProfesion();
        encontrarCorrelativo();
        this.jTFProfesion.requestFocus();
        this.setLocationRelativeTo(null);

    }

    // Método para limpiar los datos de la tabla
    private void limpiatTablaProfesion() {
        DefaultTableModel dtm = (DefaultTableModel) this.jTbProfesion.getModel();
        while (dtm.getRowCount() > 0) {
            dtm.removeRow(0);
        }
    }

    // Método para poblar de datos la tabla profesion
    private void poblarTablaProfesion() throws SQLException {
        limpiatTablaProfesion();

        CDProfesion cdp = new CDProfesion();
        List<CLProfesiones> miLista = cdp.obtenerListaProfesiones();
        DefaultTableModel temp = (DefaultTableModel) this.jTbProfesion.getModel();

        miLista.stream().map((CLProfesiones cl) -> {
            Object[] fila = new Object[2];
            fila[0] = cl.getIdProfesion();
            fila[1] = cl.getProfesion();
            return fila;
        }).forEachOrdered(temp::addRow);
    }

    // Método para encontrar el correlativo de profesión
    private void encontrarCorrelativo() throws SQLException {
        CDProfesion cdp = new CDProfesion();
        CLProfesiones cl = new CLProfesiones();

        cl.setIdProfesion(cdp.autoIncrementarIDProfesion());
        this.jTFIdProfesion.setText(String.valueOf(cl.getIdProfesion()));
    }

    // Método para habilitar y deshabilitar botones
    private void habilitarBotones(boolean agregar, boolean actualizar, boolean eliminar, boolean limpiar) {
        this.jBtnAgregarProfesion.setEnabled(agregar);
        this.jBtnActualizarProfesion.setEnabled(actualizar);
        this.jBtnEliminarProfesion.setEnabled(eliminar);
        this.jBtnLimpiarProfesion.setEnabled(limpiar);
    }

    // Métodos para limpiar textFiled
    private void limpiarTextField() {
        this.jTFIdProfesion.setText("");
        this.jTFProfesion.setText("");
        this.jTFProfesion.requestFocus();
    }

    // Método para validar la TextField
    private boolean validarTextField() {
        boolean estado;

        estado = this.jTFProfesion.getText().equals("");

        return estado;
    }

    // Método para insertar una profesión en la tabla
    private void insertarProfesion() {
        if (!validarTextField()) {
            try {
                CDProfesion cdp = new CDProfesion();
                CLProfesiones cl = new CLProfesiones();
                cl.setProfesion(this.jTFProfesion.getText().trim());
                cdp.insertarProfesiones(cl);
                JOptionPane.showMessageDialog(null, "¡Registrado correctamente!", "COVA System", JOptionPane.INFORMATION_MESSAGE);
                this.jTFProfesion.requestFocus();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Error al almacenar" + e);
                this.jTFProfesion.requestFocus();
            }
        } else {
            JOptionPane.showMessageDialog(null, "Ingresar la profesión", "COVA System", JOptionPane.INFORMATION_MESSAGE);
            this.jTFProfesion.requestFocus();
        }
    }

    // Metodo para llamar el metodo de insertar profesión
    private void insertar() throws SQLException {
        insertarProfesion();
        habilitarBotones(true, false, false, true);
        poblarTablaProfesion();
        limpiarTextField();
        encontrarCorrelativo();
    }

    // Método para editar una profesión en la tabla
    private void editarProfesion() {
        if (!validarTextField()) {
            try {
                CDProfesion cdp = new CDProfesion();
                CLProfesiones cl = new CLProfesiones();
                cl.setIdProfesion(Integer.parseInt(this.jTFIdProfesion.getText().trim()));
                cl.setProfesion(this.jTFProfesion.getText().trim());
                cdp.actualizarProfesiones(cl);
                JOptionPane.showMessageDialog(null, "¡Registro actualizado!", "COVA System", JOptionPane.INFORMATION_MESSAGE);
                this.jTFProfesion.requestFocus();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Error al modificar" + e);
                this.jTFProfesion.requestFocus();
            }
        } else {
            JOptionPane.showMessageDialog(null, "Ingresar la profesión ", "COVA System", JOptionPane.INFORMATION_MESSAGE);
            this.jTFProfesion.requestFocus();
        }
    }

    // Método para seleccionar los datos de la fila 
    private void filaSeleccionada() {
        if (this.jTbProfesion.getSelectedRow() != -1) {
            this.jTFIdProfesion.setText(String.valueOf(this.jTbProfesion.getValueAt(this.jTbProfesion.getSelectedRow(), 0)));
            this.jTFProfesion.setText(String.valueOf(this.jTbProfesion.getValueAt(this.jTbProfesion.getSelectedRow(), 1)));
        }
    }

    // Método para llamar el metodo de actualizar la tabla 
    private void editar() throws SQLException {
        editarProfesion();
        poblarTablaProfesion();
        habilitarBotones(true, false, false, false);
        limpiarTextField();
        encontrarCorrelativo();
    }

    // Método para eliminar una profesión
    private void eliminarProfesion() {
        if (!validarTextField()) {
            try {
                CDProfesion cdp = new CDProfesion();
                CLProfesiones cl = new CLProfesiones();
                cl.setIdProfesion(Integer.parseInt(this.jTFIdProfesion.getText().trim()));
                cdp.eliminarProfesiones(cl);
                JOptionPane.showMessageDialog(null, "¡Registro eliminado!", "COVA System", JOptionPane.INFORMATION_MESSAGE);
                this.jTFProfesion.requestFocus();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Error al eliminar" + e);
                this.jTFProfesion.requestFocus();
            }
        } else {
            JOptionPane.showMessageDialog(null, "Ingresar la profesión ", "COVA System", JOptionPane.INFORMATION_MESSAGE);
            this.jTFProfesion.requestFocus();
        }
    }

    // Metodo para eliminar el metodo de actualizar la tabla 
    private void eliminar() throws SQLException {
        int resp = JOptionPane.showConfirmDialog(null, "¿Está seguro de eliminar éste registro?", "COVA System", JOptionPane.YES_NO_OPTION);
        if (resp == JOptionPane.YES_OPTION) {
            try {
                eliminarProfesion();
                poblarTablaProfesion();
                habilitarBotones(true, false, false, false);
                limpiarTextField();
                encontrarCorrelativo();
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "Error: " + ex);
            }
        } else {
            limpiarTextField();
        }
    }

    // metodo de clase que permite agregar iconos a los botones y labels del JFForm
    public final void agregarIconos() {
        ImageIcon iconBtnGuardar = new ImageIcon("src/main/java/com/curlp/capaimagenes/save.png");
        ImageIcon iconBtnActualizar = new ImageIcon("src/main/java/com/curlp/capaimagenes/edit.png");
        ImageIcon iconBtnLimpiar = new ImageIcon("src/main/java/com/curlp/capaimagenes/Limpiar.png");
        ImageIcon iconBtnEliminar = new ImageIcon("src/main/java/com/curlp/capaimagenes/delete.png");
        ImageIcon iconBtnSalir = new ImageIcon("src/main/java/com/curlp/capaimagenes/logout.png");
        ImageIcon iconLogoTitulo = new ImageIcon("src/main/java/com/curlp/capaimagenes/user.png");
        this.jBtnAgregarProfesion.setIcon(iconBtnGuardar);
        this.jBtnActualizarProfesion.setIcon(iconBtnActualizar);
        this.jBtnEliminarProfesion.setIcon(iconBtnEliminar);
        this.jBtnLimpiarProfesion.setIcon(iconBtnLimpiar);
        this.jBtnSalir.setIcon(iconBtnSalir);
        this.jLblUser.setIcon(iconLogoTitulo);
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
        jLabel3 = new javax.swing.JLabel();
        jLblUser = new javax.swing.JLabel();
        jBtnSalir = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jTFProfesion = new javax.swing.JTextField();
        jBtnAgregarProfesion = new javax.swing.JButton();
        jBtnLimpiarProfesion = new javax.swing.JButton();
        jBtnActualizarProfesion = new javax.swing.JButton();
        jBtnEliminarProfesion = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        jTFIdProfesion = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTbProfesion = new javax.swing.JTable();
        jPanel4 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setUndecorated(true);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel3.setFont(new java.awt.Font("Tahoma", 3, 24)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(0, 153, 153));
        jLabel3.setText("Gestión de Profesión");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 20, 280, -1));
        jPanel1.add(jLblUser, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, 70, 40));

        jBtnSalir.setBackground(new java.awt.Color(255, 255, 255));
        jBtnSalir.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jBtnSalir.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jBtnSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnSalirActionPerformed(evt);
            }
        });
        jPanel1.add(jBtnSalir, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 11, 70, 30));

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 102, 102)), "Profesión", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 14))); // NOI18N

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel2.setText("Nombre profesión");

        jTFProfesion.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N

        jBtnAgregarProfesion.setBackground(new java.awt.Color(255, 255, 255));
        jBtnAgregarProfesion.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jBtnAgregarProfesion.setForeground(new java.awt.Color(0, 153, 153));
        jBtnAgregarProfesion.setText("Agregar");
        jBtnAgregarProfesion.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jBtnAgregarProfesion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnAgregarProfesionActionPerformed(evt);
            }
        });

        jBtnLimpiarProfesion.setBackground(new java.awt.Color(255, 255, 255));
        jBtnLimpiarProfesion.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jBtnLimpiarProfesion.setForeground(new java.awt.Color(0, 153, 153));
        jBtnLimpiarProfesion.setText("Limpiar");
        jBtnLimpiarProfesion.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jBtnLimpiarProfesion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnLimpiarProfesionActionPerformed(evt);
            }
        });

        jBtnActualizarProfesion.setBackground(new java.awt.Color(255, 255, 255));
        jBtnActualizarProfesion.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jBtnActualizarProfesion.setForeground(new java.awt.Color(0, 153, 153));
        jBtnActualizarProfesion.setText("Editar");
        jBtnActualizarProfesion.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jBtnActualizarProfesion.setEnabled(false);
        jBtnActualizarProfesion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnActualizarProfesionActionPerformed(evt);
            }
        });

        jBtnEliminarProfesion.setBackground(new java.awt.Color(255, 255, 255));
        jBtnEliminarProfesion.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jBtnEliminarProfesion.setForeground(new java.awt.Color(0, 153, 153));
        jBtnEliminarProfesion.setText("Eliminar");
        jBtnEliminarProfesion.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jBtnEliminarProfesion.setEnabled(false);
        jBtnEliminarProfesion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnEliminarProfesionActionPerformed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel4.setText("Id profesión");

        jTFIdProfesion.setEditable(false);
        jTFIdProfesion.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(46, 46, 46)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel4)))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(25, 25, 25)
                        .addComponent(jBtnAgregarProfesion, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(42, 42, 42)
                        .addComponent(jBtnActualizarProfesion, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 44, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jBtnEliminarProfesion, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(36, 36, 36)
                        .addComponent(jBtnLimpiarProfesion, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jTFProfesion, javax.swing.GroupLayout.DEFAULT_SIZE, 248, Short.MAX_VALUE)
                    .addComponent(jTFIdProfesion))
                .addGap(44, 44, 44))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addContainerGap(21, Short.MAX_VALUE)
                        .addComponent(jLabel4))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jTFIdProfesion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTFProfesion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jBtnActualizarProfesion, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jBtnEliminarProfesion, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jBtnAgregarProfesion, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jBtnLimpiarProfesion, javax.swing.GroupLayout.DEFAULT_SIZE, 39, Short.MAX_VALUE)))
                .addContainerGap())
        );

        jPanel2.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, 640, 160));

        jTbProfesion.setBackground(new java.awt.Color(204, 255, 204));
        jTbProfesion.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jTbProfesion.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jTbProfesion.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Id Profesión", "Profesión"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTbProfesion.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTbProfesionMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTbProfesion);

        jPanel2.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 210, 640, 220));

        jPanel4.setBackground(new java.awt.Color(0, 153, 153));

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 690, Short.MAX_VALUE)
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 20, Short.MAX_VALUE)
        );

        jPanel2.add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, -10, 690, 20));

        jPanel5.setBackground(new java.awt.Color(0, 153, 153));

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 690, Short.MAX_VALUE)
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 20, Short.MAX_VALUE)
        );

        jPanel2.add(jPanel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 450, 690, -1));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, 468, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jBtnAgregarProfesionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnAgregarProfesionActionPerformed
        try {
            insertar();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al almacenar" + e);
        }
    }//GEN-LAST:event_jBtnAgregarProfesionActionPerformed

    private void jTbProfesionMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTbProfesionMouseClicked
        filaSeleccionada();
        habilitarBotones(false, true, true, true);

    }//GEN-LAST:event_jTbProfesionMouseClicked

    private void jBtnActualizarProfesionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnActualizarProfesionActionPerformed
        try {
            editar();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al actualizar" + ex);
        }
    }//GEN-LAST:event_jBtnActualizarProfesionActionPerformed

    private void jBtnEliminarProfesionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnEliminarProfesionActionPerformed
        try {
            eliminar();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al eliminar" + ex);
        }
    }//GEN-LAST:event_jBtnEliminarProfesionActionPerformed

    private void jBtnLimpiarProfesionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnLimpiarProfesionActionPerformed
        limpiarTextField();
    }//GEN-LAST:event_jBtnLimpiarProfesionActionPerformed

    private void jBtnSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnSalirActionPerformed
        this.dispose();
    }//GEN-LAST:event_jBtnSalirActionPerformed

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
            java.util.logging.Logger.getLogger(JFFProfesion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(JFFProfesion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(JFFProfesion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(JFFProfesion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new JFFProfesion().setVisible(true);
                } catch (SQLException ex) {
                    Logger.getLogger(JFFProfesion.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jBtnActualizarProfesion;
    private javax.swing.JButton jBtnAgregarProfesion;
    private javax.swing.JButton jBtnEliminarProfesion;
    private javax.swing.JButton jBtnLimpiarProfesion;
    private javax.swing.JButton jBtnSalir;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLblUser;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField jTFIdProfesion;
    private javax.swing.JTextField jTFProfesion;
    private javax.swing.JTable jTbProfesion;
    // End of variables declaration//GEN-END:variables
}
