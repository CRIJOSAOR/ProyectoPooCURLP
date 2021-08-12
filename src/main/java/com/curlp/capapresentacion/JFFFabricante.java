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
        this.JTFNombreFabricante.requestFocus();
        this.setLocationRelativeTo(null);

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

    //metodo para habilitar y deshabilitar botones
    private void habilitarBotones(boolean agregar, boolean actualizar, boolean eliminar, boolean limpiar) {
        this.jBtnAgregar.setEnabled(agregar);
        this.jBtnEditar.setEnabled(actualizar);
        this.jBtnEliminar.setEnabled(eliminar);
        this.jBtnLimpiar.setEnabled(limpiar);
    }

    //metodos para limpiar textFiled
    private void limpiarTextField() {
        this.jTFIDFabricante.setText("");
        this.JTFNombreFabricante.setText("");
        this.JTFNombreFabricante.requestFocus();
    }

    // metodo para validar la TextField
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
                JOptionPane.showMessageDialog(null,"Registrado correctamente","Proyecto Vacunación",JOptionPane.INFORMATION_MESSAGE);
                this.JTFNombreFabricante.requestFocus();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null,"Error al almacenar" + e);
                this.JTFNombreFabricante.requestFocus();
            }
        } else {
            JOptionPane.showMessageDialog(null,"Ingresar el nombre del fabricante","Proyecto Vacunación",JOptionPane.INFORMATION_MESSAGE);
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
                JOptionPane.showMessageDialog(null,"Registrado actualizado","Proyecto Vacunación",JOptionPane.INFORMATION_MESSAGE);
                this.JTFNombreFabricante.requestFocus();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null,"Error al modificar" + e);
                this.JTFNombreFabricante.requestFocus();
            }  
        } else {
            JOptionPane.showMessageDialog(null,"Ingresar el nombre del fabricante","Proyecto Vacunación",JOptionPane.INFORMATION_MESSAGE);
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
                JOptionPane.showMessageDialog(null,"Registrado eliminado","Proyecto Vacunación",JOptionPane.INFORMATION_MESSAGE);
                this.JTFNombreFabricante.requestFocus();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null,"Error al eliminar" + e);
                this.JTFNombreFabricante.requestFocus();
            }
        } else {
            JOptionPane.showMessageDialog(null,"Ingresar el nombre del fabricante","Proyecto Vacunación",JOptionPane.INFORMATION_MESSAGE);
            this.JTFNombreFabricante.requestFocus();
        }
    }

    // Metodo para eliminar el metodo de actualizar la tabla 
    private void eliminar() throws SQLException {
         int resp = JOptionPane.showConfirmDialog(null, "Seguro que desea eliminar el registro","Proyecto Vacunacion",JOptionPane.YES_NO_OPTION);
        if (resp == JOptionPane.YES_OPTION) {
            try {
                eliminarFabricante();
                poblarTablaFabricante();
                habilitarBotones(true,false,false,false);
                limpiarTextField();
                encontrarCorrelativo();
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null,"Error: " + ex);
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
        jBTNSalir = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLBiconoNombre = new javax.swing.JLabel();
        jPfranjaSuperior = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Fabricante", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Trebuchet MS", 1, 11))); // NOI18N

        jLabel2.setFont(new java.awt.Font("Trebuchet MS", 1, 11)); // NOI18N
        jLabel2.setText("ID FABRICANTE");

        jTFIDFabricante.setEditable(false);

        jLabel4.setFont(new java.awt.Font("Trebuchet MS", 1, 11)); // NOI18N
        jLabel4.setText("NOMBRE FABRICANTE");

        jBtnAgregar.setFont(new java.awt.Font("Trebuchet MS", 1, 11)); // NOI18N
        jBtnAgregar.setText("AGREGAR");
        jBtnAgregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnAgregarActionPerformed(evt);
            }
        });

        jBtnEditar.setFont(new java.awt.Font("Trebuchet MS", 1, 11)); // NOI18N
        jBtnEditar.setText("EDITAR");
        jBtnEditar.setEnabled(false);
        jBtnEditar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnEditarActionPerformed(evt);
            }
        });

        jBtnEliminar.setFont(new java.awt.Font("Trebuchet MS", 1, 11)); // NOI18N
        jBtnEliminar.setText("ELIMINAR");
        jBtnEliminar.setEnabled(false);
        jBtnEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnEliminarActionPerformed(evt);
            }
        });

        jBtnLimpiar.setFont(new java.awt.Font("Trebuchet MS", 1, 11)); // NOI18N
        jBtnLimpiar.setText("LIMPIAR");
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
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jBtnEditar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jTFIDFabricante)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.LEADING))
                        .addGap(0, 216, Short.MAX_VALUE))
                    .addComponent(JTFNombreFabricante)
                    .addComponent(jBtnAgregar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jBtnEliminar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jBtnLimpiar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTFIDFabricante, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(JTFNombreFabricante, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jBtnAgregar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jBtnEditar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jBtnEliminar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jBtnLimpiar)
                .addContainerGap(44, Short.MAX_VALUE))
        );

        jPanel2.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, 360, 290));

        jTblFabricante.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID ", "FABRICANTE"
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

        jPanel2.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 10, 360, 270));

        jPanel1.setBackground(new java.awt.Color(0, 153, 153));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 800, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 10, Short.MAX_VALUE)
        );

        jPanel2.add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 330, 800, 10));

        getContentPane().add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(-10, 70, 830, 340));

        jPTitulo.setBackground(new java.awt.Color(255, 255, 255));

        jBTNSalir.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jBTNSalir.setText("Salir");
        jBTNSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBTNSalirActionPerformed(evt);
            }
        });

        jLabel1.setBackground(new java.awt.Color(0, 153, 153));
        jLabel1.setFont(new java.awt.Font("Tahoma", 3, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 153, 153));
        jLabel1.setText("Fabricante");

        javax.swing.GroupLayout jPTituloLayout = new javax.swing.GroupLayout(jPTitulo);
        jPTitulo.setLayout(jPTituloLayout);
        jPTituloLayout.setHorizontalGroup(
            jPTituloLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPTituloLayout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addComponent(jLBiconoNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 260, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 318, Short.MAX_VALUE)
                .addComponent(jBTNSalir, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(19, 19, 19))
        );
        jPTituloLayout.setVerticalGroup(
            jPTituloLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPTituloLayout.createSequentialGroup()
                .addGroup(jPTituloLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPTituloLayout.createSequentialGroup()
                        .addGap(16, 16, 16)
                        .addGroup(jPTituloLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jBTNSalir, javax.swing.GroupLayout.DEFAULT_SIZE, 38, Short.MAX_VALUE)
                            .addComponent(jLabel1)))
                    .addGroup(jPTituloLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLBiconoNombre, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );

        getContentPane().add(jPTitulo, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 800, 60));

        jPfranjaSuperior.setBackground(new java.awt.Color(0, 153, 153));

        javax.swing.GroupLayout jPfranjaSuperiorLayout = new javax.swing.GroupLayout(jPfranjaSuperior);
        jPfranjaSuperior.setLayout(jPfranjaSuperiorLayout);
        jPfranjaSuperiorLayout.setHorizontalGroup(
            jPfranjaSuperiorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 800, Short.MAX_VALUE)
        );
        jPfranjaSuperiorLayout.setVerticalGroup(
            jPfranjaSuperiorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 10, Short.MAX_VALUE)
        );

        getContentPane().add(jPfranjaSuperior, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 60, 800, 10));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jBTNSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBTNSalirActionPerformed
        this.dispose();
    }//GEN-LAST:event_jBTNSalirActionPerformed

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
    private javax.swing.JButton jBTNSalir;
    private javax.swing.JButton jBtnAgregar;
    private javax.swing.JButton jBtnEditar;
    private javax.swing.JButton jBtnEliminar;
    private javax.swing.JButton jBtnLimpiar;
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
