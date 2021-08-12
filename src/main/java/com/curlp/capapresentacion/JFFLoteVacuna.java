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

/**
 *
 * @author sanch
 */
public class JFFLoteVacuna extends javax.swing.JFrame {

    /**
     * Creates new form JFFLoteVacuna
     */
    public JFFLoteVacuna() throws SQLException {
        initComponents();
        this.poblarTablaLoteVacuna();
        this.setLocationRelativeTo(null);
    }

    // Metoddo para limpiar los datos de la tabla
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
            Object[] fila = new Object[2];
            fila[0] = cl.getNumLoteVacuna();
            fila[1] = cl.getFechaFabricacion();
            fila[2] = cl.getFechaVencimiento();
            fila[3] = cl.getIdFbricante();
            return fila;
        }).forEachOrdered(temp::addRow);
    }
    // Metodo para crear el correlativo 

    private void encontrarCorrelativo() throws SQLException {
        CDLoteVacuna cdlv = new CDLoteVacuna();
        CLLoteVacuna cl = new CLLoteVacuna();

        cl.setIdFbricante(cdlv.autoIncrementarLoteVacuna(cl));
        this.jTFIdFabricante.setText(String.valueOf(cl.getIdFbricante()));
    }

    // metodo para habilitar y deshabilitar botones
    private void habilitarBotones(boolean agregar, boolean editar, boolean eliminar, boolean limpiar) {
        this.jBtnGuardar.setEnabled(agregar);
        this.jBtnEditar.setEnabled(editar);
        this.jBtnEliminar.setEnabled(eliminar);
        this.jBtnLimpiar.setEnabled(limpiar);
    }

    //metodos para limpiar textFiled
    private void limpiarTextField() {
        this.jTFNumLote.setText("");
        this.jTFIdFabricante.setText("");
    }
    // metodo para validar la TextField

    private boolean validarTextField() {
        boolean estado;

        estado = this.jTFNumLote.getText().equals("");

        return estado;
    }
    // metodo de insertar profesion Double.parseDouble(this.jTFMonto.getText());

    private void insertarLoteVacuna() {
        if (!validarTextField()) {
            JOptionPane.showMessageDialog(null, "Tiene que ingresar el numero de lote de vacunas", "Proyecto Vacunación", JOptionPane.INFORMATION_MESSAGE);
            this.jTFNumLote.requestFocus();
        } else {
            try {
                CDLoteVacuna cdlv = new CDLoteVacuna();
                CLLoteVacuna cl = new CLLoteVacuna();
                cl.setNumLoteVacuna(this.jTFNumLote.getText().trim());
                cl.setFechaFabricacion(this.jDCFechaFabricacion.getDateFormatString().trim());
                cl.setFechaVencimiento(this.jDCFechaVencimiento.getDateFormatString().trim());
                cl.setIdFbricante(Integer.parseInt(this.jTFIdFabricante.getText().trim()));
                cdlv.insertarLoteVacuna(cl);
                JOptionPane.showMessageDialog(null, "Registrado correctamente", "Proyecto Vacunación", JOptionPane.INFORMATION_MESSAGE);
                this.jTFNumLote.requestFocus();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Error al almacenar" + e);
                this.jTFNumLote.requestFocus();
            }
        }
    }
    
        //Metodo para llamar el metodo para insertar loteVacuna
    private void insertar() throws SQLException {
        insertarLoteVacuna();
        poblarTablaLoteVacuna();
        habilitarBotones(true,false,false,false);
        limpiarTextField();
        encontrarCorrelativo();
    }
    // Metodo para llamar el metodo para actualizar profesion
    private void actualizarLoteVacuna() {
        if (!validarTextField()) {
            JOptionPane.showMessageDialog(null,"Ingresar el nombre de la profesion","Proyecto Vacunación",JOptionPane.INFORMATION_MESSAGE);
            this.jTFNumLote.requestFocus();
        } else {
            try {
                CDLoteVacuna cdlv = new CDLoteVacuna();
                CLLoteVacuna cl = new CLLoteVacuna();
                cl.setNumLoteVacuna(this.jTFNumLote.getText().trim());
                cl.setFechaFabricacion(this.jDCFechaFabricacion.getDateFormatString().trim());
                cl.setFechaVencimiento(this.jDCFechaVencimiento.getDateFormatString().trim());
                cl.setIdFbricante(Integer.parseInt(this.jTFIdFabricante.getText().trim()));
                cdlv.actualizarloteVacuna(cl);
                JOptionPane.showMessageDialog(null,"Registrado actualizado","Proyecto Vacunación",JOptionPane.INFORMATION_MESSAGE);
                this.jTFNumLote.requestFocus();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null,"Error al modificar" + e);
                this.jTFNumLote.requestFocus();
            }
        }
    }
       // metodo para seleccionar los datos de la fila y asi modificarlos
    private void filaSeleccionada() {
        if (this.jTblLoteVacuna.getSelectedRow() != -1) {
            this.jTFNumLote.setText(String .valueOf(this.jTblLoteVacuna.getValueAt(this.jTblLoteVacuna.getSelectedRow(),0)));
            //this.jDCFechaFabricacion.getDate(this.jTblLoteVacuna.getValueAt(this.jTblLoteVacuna.getSelectedRow(),0)));
        }
    }
    
    //metodo actualizar el registro
    private void editar() throws SQLException {
        actualizarLoteVacuna();
        this.poblarTablaLoteVacuna();
        habilitarBotones(true,false,false,false);
        limpiarTextField();
        encontrarCorrelativo();
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
        jPanel2 = new javax.swing.JPanel();
        jTFNumLote = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jTFIdFabricante = new javax.swing.JTextField();
        jDCFechaFabricacion = new com.toedter.calendar.JDateChooser();
        jDCFechaVencimiento = new com.toedter.calendar.JDateChooser();
        jBtnGuardar = new javax.swing.JButton();
        jBtnEditar = new javax.swing.JButton();
        jBtnEliminar = new javax.swing.JButton();
        jBtnLimpiar = new javax.swing.JButton();
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
        jLabel1.setText("X");
        jLabel1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jLabel1MousePressed(evt);
            }
        });
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 0, 50, -1));

        jLabel2.setBackground(new java.awt.Color(0, 153, 153));
        jLabel2.setFont(new java.awt.Font("Tahoma", 3, 24)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 153, 153));
        jLabel2.setText("Gestión de lotes de vacunas");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, 350, 30));

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
        jPanel2.add(jTFIdFabricante, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 130, 206, 28));
        jPanel2.add(jDCFechaFabricacion, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 90, 140, -1));
        jPanel2.add(jDCFechaVencimiento, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 90, 150, -1));

        jBtnGuardar.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jBtnGuardar.setForeground(new java.awt.Color(0, 153, 153));
        jBtnGuardar.setText("Guardar");
        jPanel2.add(jBtnGuardar, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 30, 110, 30));

        jBtnEditar.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jBtnEditar.setForeground(new java.awt.Color(0, 153, 153));
        jBtnEditar.setText("Editar");
        jBtnEditar.setEnabled(false);
        jPanel2.add(jBtnEditar, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 70, 110, -1));

        jBtnEliminar.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jBtnEliminar.setForeground(new java.awt.Color(0, 153, 153));
        jBtnEliminar.setText("Eliminar");
        jBtnEliminar.setEnabled(false);
        jPanel2.add(jBtnEliminar, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 110, 110, -1));

        jBtnLimpiar.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jBtnLimpiar.setForeground(new java.awt.Color(0, 153, 153));
        jBtnLimpiar.setText("Limpiar");
        jPanel2.add(jBtnLimpiar, new org.netbeans.lib.awtextra.AbsoluteConstraints(413, 150, 110, -1));

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 153)));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jTblLoteVacuna.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "# Lote de vacuna", "Fecha de fabricacion", "Fecha de vencimiento", "ID fabricante"
            }
        ));
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
    private com.toedter.calendar.JDateChooser jDCFechaFabricacion;
    private com.toedter.calendar.JDateChooser jDCFechaVencimiento;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
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
