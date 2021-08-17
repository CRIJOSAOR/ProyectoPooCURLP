/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.curlp.capapresentacion;

import javax.swing.table.DefaultTableModel;
import com.curlp.capapresentacion.*;
import com.curlp.capadatos.CDLoteVacuna;
import com.curlp.capalogica.CLLoteVacuna;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;


public class jFFVisorLoteVacuna extends javax.swing.JFrame {

    public jFFVisorLoteVacuna() throws SQLException {
        initComponents();
        poblarTablaLoteVacuna();
        this.setLocationRelativeTo(null);
    }
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
    
    
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTblLoteVacuna = new javax.swing.JTable();
        jPfranjaSuperior1 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTblLoteVacuna1 = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        jPfranjaSuperior = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();

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

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);

        jPfranjaSuperior1.setBackground(new java.awt.Color(0, 153, 153));
        jPfranjaSuperior1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jTblLoteVacuna1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "# Lote de vacuna", "Fecha de fabricacion", "Fecha de vencimiento", "ID fabricante", "Fabricante"
            }
        ));
        jTblLoteVacuna1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTblLoteVacuna1MouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(jTblLoteVacuna1);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPfranjaSuperior.setBackground(new java.awt.Color(0, 153, 153));
        jPfranjaSuperior.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        jPanel1.add(jPfranjaSuperior, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 60, 790, 10));

        jLabel1.setBackground(new java.awt.Color(0, 153, 153));
        jLabel1.setFont(new java.awt.Font("Tahoma", 3, 48)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 153, 153));
        jLabel1.setText("X");
        jLabel1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel1MouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jLabel1MousePressed(evt);
            }
        });
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(740, 20, 40, 30));

        jLabel2.setBackground(new java.awt.Color(0, 153, 153));
        jLabel2.setFont(new java.awt.Font("Tahoma", 3, 24)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 153, 153));
        jLabel2.setText("Visor de lotes de vacunas");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 10, 360, 50));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2)
                    .addComponent(jPfranjaSuperior1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 792, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 260, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPfranjaSuperior1, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jTblLoteVacunaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTblLoteVacunaMouseClicked
        //filaSeleccionada();
       //habilitarBotones(false, true, true, true);
    }//GEN-LAST:event_jTblLoteVacunaMouseClicked

    private void jTblLoteVacuna1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTblLoteVacuna1MouseClicked
       // filaSeleccionada();
       // habilitarBotones(false, true, true, true);
    }//GEN-LAST:event_jTblLoteVacuna1MouseClicked

    private void jLabel1MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel1MousePressed
        this.dispose();
    }//GEN-LAST:event_jLabel1MousePressed

    private void jLabel1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel1MouseClicked
       this.dispose();
    }//GEN-LAST:event_jLabel1MouseClicked

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
            java.util.logging.Logger.getLogger(jFFVisorLoteVacuna.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(jFFVisorLoteVacuna.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(jFFVisorLoteVacuna.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(jFFVisorLoteVacuna.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new jFFVisorLoteVacuna().setVisible(true);
                } catch (SQLException ex) {
                    Logger.getLogger(jFFVisorLoteVacuna.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPfranjaSuperior;
    private javax.swing.JPanel jPfranjaSuperior1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTblLoteVacuna;
    private javax.swing.JTable jTblLoteVacuna1;
    // End of variables declaration//GEN-END:variables
}
