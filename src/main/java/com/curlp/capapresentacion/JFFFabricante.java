/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.curlp.capapresentacion;

import com.curlp.capadatos.CDFabricante;
import com.curlp.capalogica.CLFabricante;
import com.curlp.capapresentacion.*;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Luis
 */
public class JFFFabricante extends javax.swing.JFrame {

    /**
     * Creates new form JFFFabricante
     */
    public JFFFabricante() throws SQLException {
        initComponents();
        poblarTablaFabricante();
        encontrarCorrelativo();
        agregarIconos();
        this.JTFNombreFabricante.requestFocus();
        this.setLocationRelativeTo(null);

    }

    public final void agregarIconos() {
        ImageIcon iconLogoTitulo = new ImageIcon("src/main/java/com/curlp/capaimagenes/user.png");
        ImageIcon iconbtnGuardar = new ImageIcon("src/main/java/com/curlp/capaimagenes/save.png");
        ImageIcon iconbtnEditar = new ImageIcon("src/main/java/com/curlp/capaimagenes/edit.png");
        ImageIcon iconbtnEliminar = new ImageIcon("src/main/java/com/curlp/capaimagenes/delete.png");
        ImageIcon iconbtnLimpiar = new ImageIcon("src/main/java/com/curlp/capaimagenes/Limpiar.png");
        ImageIcon iconbtnSalir = new ImageIcon("src/main/java/com/curlp/capaimagenes/logout.png");
        this.jLBiconoNombre.setIcon(iconLogoTitulo);
        this.jBtnAgregar.setIcon(iconbtnGuardar);
        this.jBtnEditar.setIcon(iconbtnEditar);
        this.jBtnEliminar.setIcon(iconbtnEliminar);
        this.jBtnLimpiar.setIcon(iconbtnLimpiar);
        this.jBtnSalir.setIcon(iconbtnSalir);
    }

    // Metodo para limpiar los datos de la tabla
    private void limpiatTablaFabricante() {
        DefaultTableModel dtm = (DefaultTableModel) this.jTblFabricante.getModel();
        while (dtm.getRowCount() > 0) {
            dtm.removeRow(0);
        }
    }

    // Metodo para poblar de datos la tabla
    private void poblarTablaFabricante() throws SQLException {
        limpiatTablaFabricante();

        CDFabricante cdp = new CDFabricante();
        List<CLFabricante> miLista = cdp.obtenerListaFabricantes();
        DefaultTableModel temp = (DefaultTableModel) this.jTblFabricante.getModel();

        miLista.stream().map((CLFabricante cl) -> {
            Object[] fila = new Object[2];
            fila[0] = cl.getIdFabricante();
            fila[1] = cl.getNombreFabricante();
            return fila;
        }).forEachOrdered(temp::addRow);
    }

    // Metodo para encontrar el correlativo de el fabricante
    private void encontrarCorrelativo() throws SQLException {
        CDFabricante cdp = new CDFabricante();
        CLFabricante cl = new CLFabricante();

        cl.setIdFabricante(cdp.autoIncrementarIDFabricante());
        this.jTFIDFabricante.setText(String.valueOf(cl.getIdFabricante()));
    }

    // Metodo para habilitar y deshabilitar botones
    private void habilitarBotones(boolean agregar, boolean actualizar, boolean eliminar, boolean limpiar) {
        this.jBtnAgregar.setEnabled(agregar);
        this.jBtnEditar.setEnabled(actualizar);
        this.jBtnEliminar.setEnabled(eliminar);
        this.jBtnLimpiar.setEnabled(limpiar);
    }

    // Metodos para limpiar textFiled
    private void limpiarTextField() {
        this.JTFNombreFabricante.setText("");
        this.JTFNombreFabricante.requestFocus();
    }

    // Metodo para validar la TextField
    private boolean validarTextField() {
        boolean estado;

        estado = this.JTFNombreFabricante.getText().equals("");

        return estado;
    }

    // Metodo para insertar un fabricante en la tabla
    private void insertarFabricante() {
        if (!validarTextField()) {
            try {
                CDFabricante cdp = new CDFabricante();
                CLFabricante cl = new CLFabricante();
                cl.setNombreFabricante(this.JTFNombreFabricante.getText().trim());
                cdp.insertarFabricante(cl);
                JOptionPane.showMessageDialog(null, "¡Registrado correctamente!", "Cova System", JOptionPane.INFORMATION_MESSAGE);
                this.JTFNombreFabricante.requestFocus();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "¡Error al almacenar!" + e);
                this.JTFNombreFabricante.requestFocus();
            }
        } else {
            JOptionPane.showMessageDialog(null, "Ingresar el nombre del fabricante", "Cova System", JOptionPane.INFORMATION_MESSAGE);
            this.JTFNombreFabricante.requestFocus();
        }
    }

    // Metodo para llamar el metodo de insertar ciudad
    private void insertar() throws SQLException {
        insertarFabricante();
        poblarTablaFabricante();
        habilitarBotones(true, false, false, false);
        limpiarTextField();
        encontrarCorrelativo();
    }

    // Metodo para editar un fabricante en la tabla
    private void editarFabricante() {
        if (!validarTextField()) {
            try {
                CDFabricante cdp = new CDFabricante();
                CLFabricante cl = new CLFabricante();
                cl.setIdFabricante(Integer.parseInt(this.jTFIDFabricante.getText().trim()));
                cl.setNombreFabricante(this.JTFNombreFabricante.getText().trim());
                cdp.actualizarFabricante(cl);
                JOptionPane.showMessageDialog(null, "¡Registro actualizado!", "Cova System", JOptionPane.INFORMATION_MESSAGE);
                this.JTFNombreFabricante.requestFocus();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "¡Error al modificar!" + e);
                this.JTFNombreFabricante.requestFocus();
            }
        } else {
            JOptionPane.showMessageDialog(null, "Ingresar el nombre del fabricante", "Cova System", JOptionPane.INFORMATION_MESSAGE);
            this.JTFNombreFabricante.requestFocus();
        }
    }

    // metodo para seleccionar los datos de la fila 
    private void filaSeleccionada() {
        if (this.jTblFabricante.getSelectedRow() != -1) {
            this.jTFIDFabricante.setText(String.valueOf(this.jTblFabricante.getValueAt(this.jTblFabricante.getSelectedRow(), 0)));
            this.JTFNombreFabricante.setText(String.valueOf(this.jTblFabricante.getValueAt(this.jTblFabricante.getSelectedRow(), 1)));
        }
    }

    // Metodo para llamar el metodo de actualizar la tabla 
    private void editar() throws SQLException {
        editarFabricante();
        poblarTablaFabricante();
        habilitarBotones(true, false, false, false);
        limpiarTextField();
        encontrarCorrelativo();
    }

    // Metodo para eliminar
    private void eliminarFabricante() {
        if (!validarTextField()) {
            try {
                CDFabricante cdp = new CDFabricante();
                CLFabricante cl = new CLFabricante();
                cl.setIdFabricante(Integer.parseInt(this.jTFIDFabricante.getText().trim()));
                cdp.eliminarFabricante(cl);
                JOptionPane.showMessageDialog(null, "¡Registro eliminado!", "Cova System", JOptionPane.INFORMATION_MESSAGE);
                this.JTFNombreFabricante.requestFocus();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "¡Error al eliminar!" + e);
                this.JTFNombreFabricante.requestFocus();
            }
        } else {
            JOptionPane.showMessageDialog(null, "Ingresar el nombre del fabricante", "Cova System", JOptionPane.INFORMATION_MESSAGE);
            this.JTFNombreFabricante.requestFocus();
        }
    }

    // Metodo para eliminar el metodo de actualizar la tabla 
    private void eliminar() throws SQLException {
        int resp = JOptionPane.showConfirmDialog(null, "¿Seguro que desea eliminar el registro?", "Cova System", JOptionPane.YES_NO_OPTION);
        if (resp == JOptionPane.YES_OPTION) {
            try {
                eliminarFabricante();
                poblarTablaFabricante();
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

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jTFIDFabricante = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        JTFNombreFabricante = new javax.swing.JTextField();
        jBtnAgregar = new javax.swing.JButton();
        jBtnEditar = new javax.swing.JButton();
        jBtnEliminar = new javax.swing.JButton();
        jBtnLimpiar = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTblFabricante = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        jPTitulo = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLBiconoNombre = new javax.swing.JLabel();
        jBtnSalir = new javax.swing.JButton();
        jPfranjaSuperior = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Fabricante", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 14))); // NOI18N

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel2.setText("Id fabricante");

        jTFIDFabricante.setEditable(false);

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel4.setText("Nombre fabricante");

        jBtnAgregar.setBackground(new java.awt.Color(255, 255, 255));
        jBtnAgregar.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jBtnAgregar.setForeground(new java.awt.Color(0, 153, 153));
        jBtnAgregar.setText("Agregar");
        jBtnAgregar.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jBtnAgregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnAgregarActionPerformed(evt);
            }
        });

        jBtnEditar.setBackground(new java.awt.Color(255, 255, 255));
        jBtnEditar.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jBtnEditar.setForeground(new java.awt.Color(0, 153, 153));
        jBtnEditar.setText("Editar");
        jBtnEditar.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jBtnEditar.setEnabled(false);
        jBtnEditar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnEditarActionPerformed(evt);
            }
        });

        jBtnEliminar.setBackground(new java.awt.Color(255, 255, 255));
        jBtnEliminar.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jBtnEliminar.setForeground(new java.awt.Color(0, 153, 153));
        jBtnEliminar.setText("Eliminar");
        jBtnEliminar.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jBtnEliminar.setEnabled(false);
        jBtnEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnEliminarActionPerformed(evt);
            }
        });

        jBtnLimpiar.setBackground(new java.awt.Color(255, 255, 255));
        jBtnLimpiar.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jBtnLimpiar.setForeground(new java.awt.Color(0, 153, 153));
        jBtnLimpiar.setText("Limpiar");
        jBtnLimpiar.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jBtnLimpiar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnLimpiarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(58, 58, 58)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jTFIDFabricante, javax.swing.GroupLayout.PREFERRED_SIZE, 248, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 194, Short.MAX_VALUE)
                        .addComponent(JTFNombreFabricante, javax.swing.GroupLayout.PREFERRED_SIZE, 248, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(jBtnAgregar, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jBtnEditar, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(25, 25, 25)
                        .addComponent(jBtnEliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jBtnLimpiar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGap(27, 27, 27))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jTFIDFabricante, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(22, 22, 22)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(JTFNombreFabricante, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 29, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE, false)
                    .addComponent(jBtnEditar, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jBtnAgregar, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jBtnEliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jBtnLimpiar, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20))
        );

        jPanel2.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, 650, 180));

        jTblFabricante.setBackground(new java.awt.Color(204, 255, 204));
        jTblFabricante.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jTblFabricante.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jTblFabricante.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Id Fabricante", "Nombre Fabricante"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTblFabricante.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTblFabricanteMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(jTblFabricante);

        jPanel2.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 210, 650, 220));

        jPanel1.setBackground(new java.awt.Color(0, 153, 153));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 690, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 20, Short.MAX_VALUE)
        );

        jPanel2.add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 460, 690, 20));

        getContentPane().add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(-10, 70, 700, 480));

        jPTitulo.setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setBackground(new java.awt.Color(0, 153, 153));
        jLabel1.setFont(new java.awt.Font("Tahoma", 3, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 153, 153));
        jLabel1.setText("Gestión de Fabricante");

        jBtnSalir.setBackground(new java.awt.Color(255, 255, 255));
        jBtnSalir.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jBtnSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnSalirActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPTituloLayout = new javax.swing.GroupLayout(jPTitulo);
        jPTitulo.setLayout(jPTituloLayout);
        jPTituloLayout.setHorizontalGroup(
            jPTituloLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPTituloLayout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addComponent(jLBiconoNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(109, 109, 109)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 268, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 177, Short.MAX_VALUE)
                .addComponent(jBtnSalir, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPTituloLayout.setVerticalGroup(
            jPTituloLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPTituloLayout.createSequentialGroup()
                .addGroup(jPTituloLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPTituloLayout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel1))
                    .addGroup(jPTituloLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLBiconoNombre, javax.swing.GroupLayout.DEFAULT_SIZE, 38, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPTituloLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jBtnSalir, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );

        getContentPane().add(jPTitulo, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 690, 60));

        jPfranjaSuperior.setBackground(new java.awt.Color(0, 153, 153));

        javax.swing.GroupLayout jPfranjaSuperiorLayout = new javax.swing.GroupLayout(jPfranjaSuperior);
        jPfranjaSuperior.setLayout(jPfranjaSuperiorLayout);
        jPfranjaSuperiorLayout.setHorizontalGroup(
            jPfranjaSuperiorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 690, Short.MAX_VALUE)
        );
        jPfranjaSuperiorLayout.setVerticalGroup(
            jPfranjaSuperiorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 10, Short.MAX_VALUE)
        );

        getContentPane().add(jPfranjaSuperior, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 60, 690, 10));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jBtnAgregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnAgregarActionPerformed
        try {
            insertar();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al almacenar" + ex);
        }
    }//GEN-LAST:event_jBtnAgregarActionPerformed

    private void jTblFabricanteMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTblFabricanteMouseClicked
        filaSeleccionada();
        habilitarBotones(false, true, true, true);
    }//GEN-LAST:event_jTblFabricanteMouseClicked

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
        limpiarTextField();
    }//GEN-LAST:event_jBtnLimpiarActionPerformed

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
            java.util.logging.Logger.getLogger(JFFFabricante.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(JFFFabricante.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(JFFFabricante.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(JFFFabricante.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new JFFFabricante().setVisible(true);
                } catch (SQLException ex) {
                    Logger.getLogger(JFFFabricante.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField JTFNombreFabricante;
    private javax.swing.JButton jBtnAgregar;
    private javax.swing.JButton jBtnEditar;
    private javax.swing.JButton jBtnEliminar;
    private javax.swing.JButton jBtnLimpiar;
    private javax.swing.JButton jBtnSalir;
    private javax.swing.JLabel jLBiconoNombre;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPTitulo;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPfranjaSuperior;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextField jTFIDFabricante;
    private javax.swing.JTable jTblFabricante;
    // End of variables declaration//GEN-END:variables
}
