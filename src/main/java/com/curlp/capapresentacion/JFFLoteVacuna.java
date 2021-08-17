/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.curlp.capapresentacion;

import com.curlp.capadatos.CDFabricante;
import javax.swing.table.DefaultTableModel;
import com.curlp.capapresentacion.*;
import com.curlp.capadatos.CDLoteVacuna;
import com.curlp.capalogica.CLFabricante;
import com.curlp.capalogica.CLLoteVacuna;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

/**
 *
 * @author sanch
 */
public class JFFLoteVacuna extends javax.swing.JFrame {

    public JFFLoteVacuna() throws SQLException {
        initComponents();
        agregarIconos();
        this.poblarTablaLoteVacuna();
        llenarComboBoxFabricantes();
        this.jTFNumLote.requestFocus();
        this.setLocationRelativeTo(null);
    }

    // Metodo para limpiar los datos de la tabla
    private void limpiarTabla() {
        DefaultTableModel dtm = (DefaultTableModel) this.jTblLoteVacuna.getModel();
        while (dtm.getRowCount() > 0) {
            dtm.removeRow(0);
        }
    }

    // Metodo para poblar tabla loteVacuna
    private void poblarTablaLoteVacuna() throws SQLException {
        limpiarTabla();

        CDLoteVacuna cdlv = new CDLoteVacuna();
        List<CLLoteVacuna> miLista = cdlv.obtenerListaLotevacuna();
        DefaultTableModel temp = (DefaultTableModel) this.jTblLoteVacuna.getModel();

        miLista.stream().map((CLLoteVacuna cl) -> {
            Object[] fila = new Object[5];
            fila[0] = cl.getNumLoteVacuna();
            fila[1] = cl.getFechaFabricacion();
            fila[2] = cl.getFechaVencimiento();
            fila[3] = cl.getIdFbricante();
            fila[4] = cl.getNombreFabricante();
            return fila;
        }).forEachOrdered(temp::addRow);
    }

    // Metodo para habilitar y deshabilitar botones
    private void habilitarBotones(boolean agregar, boolean editar, boolean eliminar, boolean limpiar) {
        this.jBtnGuardar.setEnabled(agregar);
        this.jBtnEditar.setEnabled(editar);
        this.jBtnEliminar.setEnabled(eliminar);
        this.jBtnLimpiar.setEnabled(limpiar);
    }

    // Metodos para limpiar textFiled
    private void limpiarTextField() {
        this.jTFNumLote.setText("");
        this.jTFIdFabricante.setText("");
        this.jDCFechaFabricacion.setCalendar(null);
        this.jDCFechaVencimietno.setCalendar(null);
        this.jTFNumLote.requestFocus();

    }
    // Metodo para validar la TextField

    private boolean validarTextField() {
        boolean estado;
        estado = this.jTFNumLote.getText().equals("");
        return estado;
    }

    // Metodo de insertar un nuevo lote
    private void insertarLoteVacuna() {
        if (!validarTextField()) {
            // recuperar datos de los Combo Box
            try {
                java.sql.Date fechaFabricacion = new java.sql.Date(this.jDCFechaFabricacion.getDate().getTime());
                java.sql.Date fechaVencimietno = new java.sql.Date(this.jDCFechaVencimietno.getDate().getTime());
                CDLoteVacuna cdlv = new CDLoteVacuna();
                CLLoteVacuna cl = new CLLoteVacuna();
                cl.setNumLoteVacuna(this.jTFNumLote.getText().trim());
                cl.setFechaFabricacion(fechaFabricacion);
                cl.setFechaVencimiento(fechaVencimietno);
                cl.setIdFbricante(Integer.parseInt(jTFIdFabricante.getText()));
                cdlv.insertarLoteVacuna(cl);
                JOptionPane.showMessageDialog(null, "Registrado correctamente", "Proyecto Vacunación", JOptionPane.INFORMATION_MESSAGE);
                this.jTFNumLote.requestFocus();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Error al almacenar el nuevo lote" + e);
                this.jTFNumLote.requestFocus();
            }

        } else {
            JOptionPane.showMessageDialog(null, "Tiene que ingresar el numero de lote de vacunas", "Proyecto Vacunación", JOptionPane.INFORMATION_MESSAGE);
            this.jTFNumLote.requestFocus();
        }
    }

    //Metodo para llamar el metodo para insertar loteVacuna
    private void insertar() throws SQLException {
        insertarLoteVacuna();
        poblarTablaLoteVacuna();
        habilitarBotones(true, false, false, true);
        limpiarTextField();
    }

    // Metodo para llamar el metodo para actualizar un lote
    private void actualizarLoteVacuna() {
        if (!validarTextField()) {
            try {
                java.sql.Date fechaFabricacion = new java.sql.Date(this.jDCFechaFabricacion.getDate().getTime());
                java.sql.Date fechaVencimietno = new java.sql.Date(this.jDCFechaVencimietno.getDate().getTime());
                CDLoteVacuna cdlv = new CDLoteVacuna();
                CLLoteVacuna cl = new CLLoteVacuna();
                cl.setNumLoteVacuna(this.jTFNumLote.getText().trim());
                cl.setFechaFabricacion(fechaFabricacion);
                cl.setFechaVencimiento(fechaVencimietno);
                cl.setIdFbricante(Integer.parseInt(jTFIdFabricante.getText()));
                cdlv.actualizarloteVacuna(cl);
                JOptionPane.showMessageDialog(null, "Registrado actualizado", "Proyecto Vacunación", JOptionPane.INFORMATION_MESSAGE);
                this.jTFNumLote.requestFocus();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Error al modificar" + e);
                this.jTFNumLote.requestFocus();
            }
        } else {
            JOptionPane.showMessageDialog(null, "Ingresar los nuevos datos del lote", "Proyecto Vacunación", JOptionPane.INFORMATION_MESSAGE);
            this.jTFNumLote.requestFocus();
        }
    }

    // Metodo para seleccionar los datos de la fila y asi modificarlos
    private void filaSeleccionada() throws ParseException {
        if (this.jTblLoteVacuna.getSelectedRow() != -1) {
            this.jTFNumLote.setText(String.valueOf(this.jTblLoteVacuna.getValueAt(this.jTblLoteVacuna.getSelectedRow(), 0)));
//            Date fechaParseada = (Date) new SimpleDateFormat("dd/MM/yyyy").parse((String)this.jTblLoteVacuna.getValueAt(this.jTblLoteVacuna.getSelectedRow(), 1));
//            this.jDCFechaFabricacion.setDate(fechaParseada);
//            Date fechaParseada1 = (Date) new SimpleDateFormat("dd/MM/yyyy").parse((String)this.jTblLoteVacuna.getValueAt(this.jTblLoteVacuna.getSelectedRow(), 2));
//            this.jDCFechaVencimietno.setDate(fechaParseada);
            this.jTFIdFabricante.setText(String.valueOf(this.jTblLoteVacuna.getValueAt(this.jTblLoteVacuna.getSelectedRow(), 3)));
        }
    }

    // Metodo actualizar el registro loteVacuna
    private void editar() throws SQLException {
        actualizarLoteVacuna();
        this.poblarTablaLoteVacuna();
        habilitarBotones(true, false, false, false);
        limpiarTextField();
    }

    // Metodo para eliminar lote
    private void eliminarLoteVacuna() {
        if (!validarTextField()) {
            try {
                CDLoteVacuna cdlv = new CDLoteVacuna();
                CLLoteVacuna cl = new CLLoteVacuna();
                cl.setNumLoteVacuna((this.jTFNumLote.getText().trim()));
                cdlv.eliminarLoteVacuna(cl);
                JOptionPane.showMessageDialog(null, "Registrado eliminado", "Proyecto Vacunación", JOptionPane.INFORMATION_MESSAGE);
                this.jTFNumLote.requestFocus();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Error al eliminar" + e);
                this.jTFNumLote.requestFocus();
            }
        } else {
            JOptionPane.showMessageDialog(null, "Ingresar el numero de lote", "Proyecto Vacunación", JOptionPane.INFORMATION_MESSAGE);
            this.jTFNumLote.requestFocus();
        }
    }

    // Metodo llamar eliminar 
    private void eliminar() throws SQLException {
        int resp = JOptionPane.showConfirmDialog(null, "Seguro que desea eliminar este lote", "Proyecto Vacunacion", JOptionPane.YES_NO_OPTION);
        if (resp == JOptionPane.YES_OPTION) {
            try {
                eliminarLoteVacuna();
                poblarTablaLoteVacuna();
                habilitarBotones(true, false, false, false);
                limpiarTextField();
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "Error: " + ex);
            }
        } else {
            limpiarTextField();
        }
    }

    // Metodo de clase que permite agregar iconos a los botones y labels del JFForm
    public final void agregarIconos() {
        ImageIcon iconobtn = new ImageIcon("src/main/java/com/curlp/capaimagenes/logout.png");
        ImageIcon iconbtnGuardar = new ImageIcon("src/main/java/com/curlp/capaimagenes/save.png");
        ImageIcon iconbtnEditar = new ImageIcon("src/main/java/com/curlp/capaimagenes/edit.png");
        ImageIcon iconbtnEliminar = new ImageIcon("src/main/java/com/curlp/capaimagenes/delete.png");
        ImageIcon iconLimpiar = new ImageIcon("src/main/java/com/curlp/capaimagenes/Limpiar.png");
        ImageIcon iconUser = new ImageIcon("src/main/java/com/curlp/capaimagenes/user.png");
        this.jBtnGuardar.setIcon(iconbtnGuardar);
        this.jBtnEditar.setIcon(iconbtnEditar);
        this.jBtnEliminar.setIcon(iconbtnEliminar);
        this.jBtnLimpiar.setIcon(iconLimpiar);
        this.jLabel1.setIcon(iconobtn);
        this.jLbUser.setIcon(iconUser);
    }

    // Metodo para llenar el combobox
    public void llenarComboBoxFabricantes() throws SQLException {
        CDFabricante fabricantes = new CDFabricante();
        List<String> listaFabricantes = fabricantes.cargarComboFabricante();
        this.jCboFabricantes.removeAllItems();
        for (String x : listaFabricantes) {
            this.jCboFabricantes.addItem(x);
        }
    }

    // Metodo para mostrar el id del fabricante segun la seleccion nombre del fabricante
    public void mostrarIdFabricante() throws SQLException {
        CDFabricante datos1 = new CDFabricante();
        int position = this.jCboFabricantes.getSelectedIndex();
        if (position > 0) {
            int idFabricante = datos1.obtenerIdFabricante(this.jCboFabricantes.getItemAt(position));
            this.jTFIdFabricante.setText(String.valueOf(idFabricante));
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

        jCalendar1 = new com.toedter.calendar.JCalendar();
        jPanel1 = new javax.swing.JPanel();
        jPfranjaSuperior = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLbUser = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jTFNumLote = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jTFIdFabricante = new javax.swing.JTextField();
        jBtnGuardar = new javax.swing.JButton();
        jBtnEditar = new javax.swing.JButton();
        jBtnEliminar = new javax.swing.JButton();
        jBtnLimpiar = new javax.swing.JButton();
        jDCFechaVencimietno = new com.toedter.calendar.JDateChooser();
        jDCFechaFabricacion = new com.toedter.calendar.JDateChooser();
        jCboFabricantes = new javax.swing.JComboBox<>();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTblLoteVacuna = new javax.swing.JTable();
        jPfranjaSuperior1 = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPfranjaSuperior.setBackground(new java.awt.Color(0, 153, 153));
        jPfranjaSuperior.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        jPanel1.add(jPfranjaSuperior, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 60, 540, 10));

        jLabel1.setBackground(new java.awt.Color(0, 153, 153));
        jLabel1.setFont(new java.awt.Font("Tahoma", 3, 48)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 153, 153));
        jLabel1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jLabel1MousePressed(evt);
            }
        });
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 0, 50, 50));

        jLabel2.setBackground(new java.awt.Color(0, 153, 153));
        jLabel2.setFont(new java.awt.Font("Tahoma", 3, 24)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 153, 153));
        jLabel2.setText("Gestión de lotes de vacunas");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 10, 350, 30));
        jPanel1.add(jLbUser, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 10, 40, 40));

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Registrar un lote de vacuna"));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        jPanel2.add(jTFNumLote, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 20, 157, 30));

        jLabel3.setText("Numero de lote:");
        jPanel2.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 30, -1, -1));

        jLabel4.setText("Fecha de vencimiento:");
        jPanel2.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 70, -1, -1));

        jLabel5.setText("Fecha de fabricacion:");
        jPanel2.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 70, -1, -1));

        jLabel6.setText("ID Fabricante:");
        jPanel2.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 140, -1, -1));

        jTFIdFabricante.setEditable(false);
        jPanel2.add(jTFIdFabricante, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 130, 206, 28));

        jBtnGuardar.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jBtnGuardar.setForeground(new java.awt.Color(0, 153, 153));
        jBtnGuardar.setText("Guardar");
        jBtnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnGuardarActionPerformed(evt);
            }
        });
        jPanel2.add(jBtnGuardar, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 30, 110, 30));

        jBtnEditar.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jBtnEditar.setForeground(new java.awt.Color(0, 153, 153));
        jBtnEditar.setText("Editar");
        jBtnEditar.setEnabled(false);
        jBtnEditar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnEditarActionPerformed(evt);
            }
        });
        jPanel2.add(jBtnEditar, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 70, 110, -1));

        jBtnEliminar.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jBtnEliminar.setForeground(new java.awt.Color(0, 153, 153));
        jBtnEliminar.setText("Eliminar");
        jBtnEliminar.setEnabled(false);
        jBtnEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnEliminarActionPerformed(evt);
            }
        });
        jPanel2.add(jBtnEliminar, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 110, 110, -1));

        jBtnLimpiar.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jBtnLimpiar.setForeground(new java.awt.Color(0, 153, 153));
        jBtnLimpiar.setText("Limpiar");
        jBtnLimpiar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnLimpiarActionPerformed(evt);
            }
        });
        jPanel2.add(jBtnLimpiar, new org.netbeans.lib.awtextra.AbsoluteConstraints(413, 150, 110, -1));

        jDCFechaVencimietno.setDateFormatString("dd/MM/yyyy");
        jPanel2.add(jDCFechaVencimietno, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 90, 180, -1));

        jDCFechaFabricacion.setDateFormatString("dd/MM/yyyy");
        jPanel2.add(jDCFechaFabricacion, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 90, 180, -1));

        jCboFabricantes.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "--Seleccione", " " }));
        jCboFabricantes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCboFabricantesActionPerformed(evt);
            }
        });
        jPanel2.add(jCboFabricantes, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 170, 210, -1));

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 153)));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jTblLoteVacuna.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "# Lote de vacuna", "Fecha de fabricacion", "Fecha de vencimiento", "ID fabricante", "Fabricante"
            }
        ));
        jTblLoteVacuna.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTblLoteVacunaMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTblLoteVacuna);

        jPanel3.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 20, 500, 260));

        jPfranjaSuperior1.setBackground(new java.awt.Color(0, 153, 153));
        jPfranjaSuperior1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jPfranjaSuperior1, javax.swing.GroupLayout.PREFERRED_SIZE, 530, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 537, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 201, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 293, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPfranjaSuperior1, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jLabel1MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel1MousePressed
        this.dispose();
    }//GEN-LAST:event_jLabel1MousePressed

    private void jTblLoteVacunaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTblLoteVacunaMouseClicked
        try {
            filaSeleccionada();
            habilitarBotones(false, true, true, true);
        } catch (ParseException ex) {
            Logger.getLogger(JFFLoteVacuna.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }//GEN-LAST:event_jTblLoteVacunaMouseClicked

    private void jBtnEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnEditarActionPerformed
        try {
            editar();
        } catch (SQLException ex) {
            Logger.getLogger(null, "Error al actualizar" + ex);
        }
    }//GEN-LAST:event_jBtnEditarActionPerformed

    private void jBtnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnGuardarActionPerformed
        try {
            insertar();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al almacenar" + e);
        }
    }//GEN-LAST:event_jBtnGuardarActionPerformed

    private void jBtnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnEliminarActionPerformed
        try {
            eliminar();
        } catch (SQLException ex) {
            Logger.getLogger(null, "Error al ELIMINAR" + ex);
        }
    }//GEN-LAST:event_jBtnEliminarActionPerformed

    private void jBtnLimpiarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnLimpiarActionPerformed
        this.limpiarTextField();
    }//GEN-LAST:event_jBtnLimpiarActionPerformed

    private void jCboFabricantesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCboFabricantesActionPerformed
        try {
            mostrarIdFabricante();
        } catch (SQLException ex) {
            Logger.getLogger(JFFLoteVacuna.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jCboFabricantesActionPerformed

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
            java.util.logging.Logger.getLogger(JFFLoteVacuna.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(JFFLoteVacuna.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(JFFLoteVacuna.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(JFFLoteVacuna.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new JFFLoteVacuna().setVisible(true);
                } catch (SQLException ex) {
                    Logger.getLogger(JFFLoteVacuna.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        );
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jBtnEditar;
    private javax.swing.JButton jBtnEliminar;
    private javax.swing.JButton jBtnGuardar;
    private javax.swing.JButton jBtnLimpiar;
    private com.toedter.calendar.JCalendar jCalendar1;
    private javax.swing.JComboBox<String> jCboFabricantes;
    private com.toedter.calendar.JDateChooser jDCFechaFabricacion;
    private com.toedter.calendar.JDateChooser jDCFechaVencimietno;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLbUser;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPfranjaSuperior;
    private javax.swing.JPanel jPfranjaSuperior1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField jTFIdFabricante;
    private javax.swing.JTextField jTFNumLote;
    private javax.swing.JTable jTblLoteVacuna;
    // End of variables declaration//GEN-END:variables
}
