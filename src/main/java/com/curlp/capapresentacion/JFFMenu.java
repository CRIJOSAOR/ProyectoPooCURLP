/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.curlp.capapresentacion;
import com.curlp.capapresentacion.*;
import java.awt.Image;
import java.sql.SQLException;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
/**
 *
 * @author Letty Ochoa
 */
public class JFFMenu extends javax.swing.JFrame {

    /**
     * Creates new form JFFMenu
     */
    public JFFMenu() {
        initComponents();
        agregarIconos();
        this.setLocationRelativeTo(null);
    }
        
    // Método de clase que permite agregar iconos a los botones y labels del JFForm
    public final void agregarIconos() {
        ImageIcon iconPaciente = new ImageIcon("src/main/java/com/curlp/capaimagenes/vacunado.png");
        ImageIcon iconVacunador = new ImageIcon("src/main/java/com/curlp/capaimagenes/doctor.png");
        ImageIcon iconRegistroVacuna = new ImageIcon("src/main/java/com/curlp/capaimagenes/registro.png");
        ImageIcon iconLoteVacuna = new ImageIcon("src/main/java/com/curlp/capaimagenes/vacuna.png");
        ImageIcon iconProfesiones = new ImageIcon("src/main/java/com/curlp/capaimagenes/profesion.png");
        ImageIcon iconEstablecimiento = new ImageIcon("src/main/java/com/curlp/capaimagenes/establecimiento.png");
        ImageIcon iconFabricante = new ImageIcon("src/main/java/com/curlp/capaimagenes/fabricante.png");
        ImageIcon iconSalir = new ImageIcon("src/main/java/com/curlp/capaimagenes/logout.png");
        this.jBtnPaciente.setIcon(iconPaciente);
        this.jBtnVacunador.setIcon(iconVacunador);
        this.jBtnRegistroVacuna.setIcon(iconRegistroVacuna);
        this.jBtnLoteVacuna.setIcon(iconLoteVacuna);
        this.jBtnProfesiones.setIcon(iconProfesiones);
        this.jBtnEstablecimiento.setIcon(iconEstablecimiento);
        this.jBtnFabricante.setIcon(iconFabricante);
        this.jBtnSalir.setIcon(iconSalir);
        escalarImagen("src/main/java/com/curlp/capaimagenes/COVA.png");
    }
    // Función para que una imagen tome el tamaño de un  jLabel 
    private void escalarImagen(String ico) {
        ImageIcon img = new ImageIcon(ico);
        Icon icon = new ImageIcon(img.getImage().getScaledInstance(this.jLblLogo.getWidth(), this.jLblLogo.getHeight(), Image.SCALE_DEFAULT));
        this.jLblLogo.setIcon(icon);
        this.repaint();
    }
    
    // Procedimientos para llamar a los forms 
    
    public void jffPaciente() throws SQLException {
        JFFPaciente ventanaPaciente = new JFFPaciente();
        ventanaPaciente.setVisible(true);
    }
    
    public void jffVacunador() throws SQLException {
        JFFVacunador ventanaVacunador = new JFFVacunador();
        ventanaVacunador.setVisible(true);
    }
    
    public void jffRegistroVacuna() throws SQLException {
        JFFRegistroVacuna ventanaRegistroVacuna = new JFFRegistroVacuna();
        ventanaRegistroVacuna.setVisible(true);
    }
    
    public void jffLoteVacuna() throws SQLException {
        JFFLoteVacuna ventanaLoteVacuna = new JFFLoteVacuna();
        ventanaLoteVacuna.setVisible(true);
    }
    
    public void jffProfesion() throws SQLException {
        JFFProfesion ventanaProfesion = new JFFProfesion();
        ventanaProfesion.setVisible(true);
    }
    
    public void jffFabricante() throws SQLException {
        JFFFabricante ventanaFabricante = new JFFFabricante();
        ventanaFabricante.setVisible(true);
    }
    
    public void jffEstablecimiento() throws SQLException {
        jFFEstablecimiento ventanaEstablecimiento = new jFFEstablecimiento();
        ventanaEstablecimiento.setVisible(true);
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
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLblLogo = new javax.swing.JLabel();
        jBtnSalir = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        jPanel7 = new javax.swing.JPanel();
        jBtnPaciente = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jBtnRegistroVacuna = new javax.swing.JButton();
        jBtnLoteVacuna = new javax.swing.JButton();
        jBtnVacunador = new javax.swing.JButton();
        jBtnFabricante = new javax.swing.JButton();
        jBtnProfesiones = new javax.swing.JButton();
        jBtnEstablecimiento = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Tahoma", 3, 48)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 153, 153));
        jLabel1.setText("COVA Sytem");
        jPanel2.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 20, 320, 100));
        jPanel2.add(jLblLogo, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 150, 140));

        jBtnSalir.setBackground(new java.awt.Color(255, 255, 255));
        jBtnSalir.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jBtnSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnSalirActionPerformed(evt);
            }
        });
        jPanel2.add(jBtnSalir, new org.netbeans.lib.awtextra.AbsoluteConstraints(740, 10, 70, 60));

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 830, 140));

        jPanel3.setBackground(new java.awt.Color(0, 153, 153));
        jPanel3.setForeground(new java.awt.Color(0, 153, 153));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("MENÚ");
        jPanel3.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 10, -1, -1));

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));
        jPanel5.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel6.setBackground(new java.awt.Color(255, 255, 255));
        jPanel6.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        jPanel5.add(jPanel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 20, 370, 10));

        jPanel3.add(jPanel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 20, 360, 10));

        jPanel7.setBackground(new java.awt.Color(255, 255, 255));
        jPanel7.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        jPanel3.add(jPanel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 20, 380, 10));

        jPanel1.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 140, 830, 40));

        jBtnPaciente.setBackground(new java.awt.Color(255, 255, 255));
        jBtnPaciente.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jBtnPaciente.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jBtnPaciente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnPacienteActionPerformed(evt);
            }
        });
        jPanel1.add(jBtnPaciente, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 190, 140, 120));

        jPanel4.setBackground(new java.awt.Color(0, 153, 153));
        jPanel4.setForeground(new java.awt.Color(0, 153, 153));
        jPanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        jPanel1.add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 490, 830, 20));

        jBtnRegistroVacuna.setBackground(new java.awt.Color(255, 255, 255));
        jBtnRegistroVacuna.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jBtnRegistroVacuna.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jBtnRegistroVacuna.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnRegistroVacunaActionPerformed(evt);
            }
        });
        jPanel1.add(jBtnRegistroVacuna, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 190, 140, 120));

        jBtnLoteVacuna.setBackground(new java.awt.Color(255, 255, 255));
        jBtnLoteVacuna.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jBtnLoteVacuna.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jBtnLoteVacuna.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnLoteVacunaActionPerformed(evt);
            }
        });
        jPanel1.add(jBtnLoteVacuna, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 190, 140, 120));

        jBtnVacunador.setBackground(new java.awt.Color(255, 255, 255));
        jBtnVacunador.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jBtnVacunador.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jBtnVacunador.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnVacunadorActionPerformed(evt);
            }
        });
        jPanel1.add(jBtnVacunador, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 190, 140, 120));

        jBtnFabricante.setBackground(new java.awt.Color(255, 255, 255));
        jBtnFabricante.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jBtnFabricante.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jBtnFabricante.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnFabricanteActionPerformed(evt);
            }
        });
        jPanel1.add(jBtnFabricante, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 340, 140, 110));

        jBtnProfesiones.setBackground(new java.awt.Color(255, 255, 255));
        jBtnProfesiones.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jBtnProfesiones.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jBtnProfesiones.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnProfesionesActionPerformed(evt);
            }
        });
        jPanel1.add(jBtnProfesiones, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 340, 140, 110));

        jBtnEstablecimiento.setBackground(new java.awt.Color(255, 255, 255));
        jBtnEstablecimiento.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jBtnEstablecimiento.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jBtnEstablecimiento.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnEstablecimientoActionPerformed(evt);
            }
        });
        jPanel1.add(jBtnEstablecimiento, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 340, 140, 110));

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(0, 153, 153));
        jLabel3.setText("Fabricante");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 460, -1, -1));

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(0, 153, 153));
        jLabel4.setText("Paciente");
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 320, -1, -1));

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(0, 153, 153));
        jLabel5.setText("Vacunador");
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 320, -1, -1));

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(0, 153, 153));
        jLabel6.setText("Registro de Vacunas");
        jPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 320, -1, -1));

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(0, 153, 153));
        jLabel7.setText("Lote de Vacunas");
        jPanel1.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(670, 320, -1, -1));

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(0, 153, 153));
        jLabel8.setText("Profesiones");
        jPanel1.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 460, -1, -1));

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(0, 153, 153));
        jLabel9.setText("Establecimiento");
        jPanel1.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 460, -1, -1));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jBtnPacienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnPacienteActionPerformed
        try {
            jffPaciente();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "¡Error!", "COVA System", JOptionPane.INFORMATION_MESSAGE);
        }
    }//GEN-LAST:event_jBtnPacienteActionPerformed

    private void jBtnVacunadorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnVacunadorActionPerformed
        try {
            jffVacunador();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "¡Error!", "COVA System", JOptionPane.INFORMATION_MESSAGE);
        }
    }//GEN-LAST:event_jBtnVacunadorActionPerformed

    private void jBtnFabricanteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnFabricanteActionPerformed
        try {
            jffFabricante();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "¡Error!", "COVA System", JOptionPane.INFORMATION_MESSAGE);
        }
    }//GEN-LAST:event_jBtnFabricanteActionPerformed

    private void jBtnLoteVacunaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnLoteVacunaActionPerformed
        try {
            jffLoteVacuna();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "¡Error!", "COVA System", JOptionPane.INFORMATION_MESSAGE);
        }
    }//GEN-LAST:event_jBtnLoteVacunaActionPerformed

    private void jBtnProfesionesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnProfesionesActionPerformed
        try {
            jffProfesion();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "¡Error!", "COVA System", JOptionPane.INFORMATION_MESSAGE);
        }
                
    }//GEN-LAST:event_jBtnProfesionesActionPerformed

    private void jBtnEstablecimientoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnEstablecimientoActionPerformed
        try {
            jffEstablecimiento();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "¡Error!", "COVA System", JOptionPane.INFORMATION_MESSAGE);
        }
    }//GEN-LAST:event_jBtnEstablecimientoActionPerformed

    private void jBtnRegistroVacunaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnRegistroVacunaActionPerformed
        try {
            jffRegistroVacuna();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "¡Error!", "COVA System", JOptionPane.INFORMATION_MESSAGE);
        }
    }//GEN-LAST:event_jBtnRegistroVacunaActionPerformed

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
            java.util.logging.Logger.getLogger(JFFMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(JFFMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(JFFMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(JFFMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new JFFMenu().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jBtnEstablecimiento;
    private javax.swing.JButton jBtnFabricante;
    private javax.swing.JButton jBtnLoteVacuna;
    private javax.swing.JButton jBtnPaciente;
    private javax.swing.JButton jBtnProfesiones;
    private javax.swing.JButton jBtnRegistroVacuna;
    private javax.swing.JButton jBtnSalir;
    private javax.swing.JButton jBtnVacunador;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel jLblLogo;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    // End of variables declaration//GEN-END:variables
}
