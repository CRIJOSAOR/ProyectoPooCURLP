/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.curlp.capapresentacion;


import com.curlp.capadatos.CDPaciente;
import com.curlp.capadatos.CDVacunador;
import com.curlp.capalogica.CLPaciente;
import com.curlp.capalogica.CLVacunador;
import com.curlp.capapresentacion.*;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author davidmendoza
 */
public class JFFVisorDeVacunador extends javax.swing.JFrame {

    /**
     * Creates new form JFFPaciente
     * @throws java.sql.SQLException
     */
    JFFRegistroVacuna ventanaPrincipal = new JFFRegistroVacuna();
    
    public JFFVisorDeVacunador(JFFRegistroVacuna main) throws SQLException {
        initComponents();
        agregarIconos();
        llenarTabla();
        this.setLocationRelativeTo(null);   
        this.ventanaPrincipal = main;
    }
    /* se agrega miembro de clase tipo border para obtener el borde default. si hay errores el borde del componente sera rojo asi que:
       con el mienbro de clase border se puede establecer el borde default a todos los campos */
    
    
    DefaultTableModel modelo; // permitira manejar la jTable

    private JFFVisorDeVacunador() throws SQLException {
        initComponents();
        agregarIconos();
        llenarTabla();
        this.setLocationRelativeTo(null);
    }
    

    /**
        +++++++++++++++++++++++ Metodos para gestion de jTable ++++++++++++++++++++++++++++++++++++
    
    */
    
        private void limpiarTabla(){
        
        modelo = (DefaultTableModel) this.JTblVacunadores.getModel();
        
        while(modelo.getRowCount() > 0){
            modelo.removeRow(0);
        }
        
    }
    
    private void llenarTabla() throws SQLException{
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
    private void limpiarTablaVacunador(){
       
        DefaultTableModel dtm = (DefaultTableModel) this.JTblVacunadores.getModel();
        
        while (dtm.getRowCount() > 0) {
            dtm.removeRow(0);
        }
        
    }
    
    private void llenarTablarPorNombre(String nombreVacunador) throws SQLException {
        limpiarTablaVacunador();
        // instanciar una clase tipo CDPaciente para la conexion con la base de datos 
        CDVacunador cdv = new CDVacunador();
        
        // recuperar todos los pacientes en forma de lista
        List<CLVacunador> listaVacunadores = cdv.obtenerListaVacunadoresPorNombre(nombreVacunador);
        // instanciamos un model 
        modelo = (DefaultTableModel) this.JTblVacunadores.getModel();
      
        // llenar cada fila con un ciclo      
        listaVacunadores.stream().map((CLVacunador cl) ->{
            Object[] fila = new Object[6];
            fila[0] = cl.getDniVacunador();
            fila[1] = cl.getNombresV();
            fila[2] = cl.getApellidosV();
            fila[3] = cl.getDireccionV();
            fila[4] = cl.getNumCelularV();
            fila[5] = cl.getEstado();
            return fila;
        }).forEachOrdered(modelo::addRow);
        
        
    }
    
    private void seleccionarFila() throws SQLException, ParseException{
        if(this.JTblVacunadores.getSelectedRow() != -1){
                
            String id, nombre, apellido, fechaNacimiento;
            
            id = (String.valueOf(this.JTblVacunadores.getValueAt(this.JTblVacunadores.getSelectedRow(), 0)));
            nombre = (String.valueOf(this.JTblVacunadores.getValueAt(this.JTblVacunadores.getSelectedRow(), 1)));
            apellido = (String.valueOf(this.JTblVacunadores.getValueAt(this.JTblVacunadores.getSelectedRow(), 2)));
            
            this.ventanaPrincipal.llenarDatosVacunador(id, nombre, apellido);
            
            this.setVisible(false);
            this.dispose();
        } else {
            JOptionPane.showMessageDialog(null, "Por favor seleccione un vacunador", "COVA System", JOptionPane.INFORMATION_MESSAGE);
        }
    } 
    

    
    /**
        +++++++++++++++++++++++ Metodos secundarios  ++++++++++++++++++++++++++++++++++++
    
    */
    
    // metodo de clase que permite agregar iconos a los botones y labels del JFForm
    public final void  agregarIconos(){
        ImageIcon iconoBtn = new ImageIcon("src/main/java/com/curlp/capaimagenes/logout.png");
        ImageIcon iconLogoTitulo = new ImageIcon("src/main/java/com/curlp/capaimagenes/user.png");

        this.jBTNSalir.setIcon(iconoBtn);
        this.jLBiconoNombre.setIcon(iconLogoTitulo);
    }



    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPMOpciones = new javax.swing.JPopupMenu();
        jMIEditar = new javax.swing.JMenuItem();
        jMiEliminar = new javax.swing.JMenuItem();
        jPfranjaSuperior = new javax.swing.JPanel();
        jPFranjaInferior = new javax.swing.JPanel();
        jPTitulo = new javax.swing.JPanel();
        jBTNSalir = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLBiconoNombre = new javax.swing.JLabel();
        jPMostrarPacientes = new javax.swing.JPanel();
        jTFBusqueda = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        jBtnSeleccionar = new javax.swing.JButton();
        jBtnMostrarTodos1 = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        JTblVacunadores = new javax.swing.JTable();

        jMIEditar.setText("Seleccionar");
        jMIEditar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMIEditarActionPerformed(evt);
            }
        });
        jPMOpciones.add(jMIEditar);

        jMiEliminar.setText("Eliminar");
        jMiEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMiEliminarActionPerformed(evt);
            }
        });
        jPMOpciones.add(jMiEliminar);

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("GESTION DE PACIENTES");
        setBackground(new java.awt.Color(255, 255, 255));
        setUndecorated(true);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPfranjaSuperior.setBackground(new java.awt.Color(0, 153, 153));

        javax.swing.GroupLayout jPfranjaSuperiorLayout = new javax.swing.GroupLayout(jPfranjaSuperior);
        jPfranjaSuperior.setLayout(jPfranjaSuperiorLayout);
        jPfranjaSuperiorLayout.setHorizontalGroup(
            jPfranjaSuperiorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 950, Short.MAX_VALUE)
        );
        jPfranjaSuperiorLayout.setVerticalGroup(
            jPfranjaSuperiorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 10, Short.MAX_VALUE)
        );

        getContentPane().add(jPfranjaSuperior, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 60, 950, 10));

        jPFranjaInferior.setBackground(new java.awt.Color(0, 153, 153));

        javax.swing.GroupLayout jPFranjaInferiorLayout = new javax.swing.GroupLayout(jPFranjaInferior);
        jPFranjaInferior.setLayout(jPFranjaInferiorLayout);
        jPFranjaInferiorLayout.setHorizontalGroup(
            jPFranjaInferiorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 950, Short.MAX_VALUE)
        );
        jPFranjaInferiorLayout.setVerticalGroup(
            jPFranjaInferiorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 20, Short.MAX_VALUE)
        );

        getContentPane().add(jPFranjaInferior, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 560, 950, 20));

        jPTitulo.setBackground(new java.awt.Color(255, 255, 255));

        jBTNSalir.setBackground(new java.awt.Color(255, 255, 255));
        jBTNSalir.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jBTNSalir.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jBTNSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBTNSalirActionPerformed(evt);
            }
        });

        jLabel1.setBackground(new java.awt.Color(0, 153, 153));
        jLabel1.setFont(new java.awt.Font("Tahoma", 3, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 153, 153));
        jLabel1.setText("Seleccionar un Vacunador");

        javax.swing.GroupLayout jPTituloLayout = new javax.swing.GroupLayout(jPTitulo);
        jPTitulo.setLayout(jPTituloLayout);
        jPTituloLayout.setHorizontalGroup(
            jPTituloLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPTituloLayout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addComponent(jLBiconoNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 255, Short.MAX_VALUE)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 332, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(142, 142, 142)
                .addComponent(jBTNSalir, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(24, 24, 24))
        );
        jPTituloLayout.setVerticalGroup(
            jPTituloLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPTituloLayout.createSequentialGroup()
                .addGroup(jPTituloLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPTituloLayout.createSequentialGroup()
                        .addGap(16, 16, 16)
                        .addGroup(jPTituloLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jBTNSalir, javax.swing.GroupLayout.DEFAULT_SIZE, 33, Short.MAX_VALUE)
                            .addComponent(jLabel1)))
                    .addGroup(jPTituloLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLBiconoNombre, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );

        getContentPane().add(jPTitulo, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 950, 60));

        jPMostrarPacientes.setBackground(new java.awt.Color(255, 255, 255));

        jTFBusqueda.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTFBusquedaKeyReleased(evt);
            }
        });

        jLabel14.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel14.setText("Buscar por nombre o apellido:");

        jBtnSeleccionar.setBackground(new java.awt.Color(255, 255, 255));
        jBtnSeleccionar.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        jBtnSeleccionar.setForeground(new java.awt.Color(0, 153, 153));
        jBtnSeleccionar.setText("Seleccionar");
        jBtnSeleccionar.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jBtnSeleccionar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnSeleccionarActionPerformed(evt);
            }
        });

        jBtnMostrarTodos1.setBackground(new java.awt.Color(255, 255, 255));
        jBtnMostrarTodos1.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        jBtnMostrarTodos1.setForeground(new java.awt.Color(0, 153, 153));
        jBtnMostrarTodos1.setText("Mostrar Todos");
        jBtnMostrarTodos1.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jBtnMostrarTodos1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnMostrarTodos1ActionPerformed(evt);
            }
        });

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
        jScrollPane2.setViewportView(JTblVacunadores);

        javax.swing.GroupLayout jPMostrarPacientesLayout = new javax.swing.GroupLayout(jPMostrarPacientes);
        jPMostrarPacientes.setLayout(jPMostrarPacientesLayout);
        jPMostrarPacientesLayout.setHorizontalGroup(
            jPMostrarPacientesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPMostrarPacientesLayout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(jPMostrarPacientesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPMostrarPacientesLayout.createSequentialGroup()
                        .addComponent(jLabel14)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTFBusqueda, javax.swing.GroupLayout.PREFERRED_SIZE, 434, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jBtnMostrarTodos1, javax.swing.GroupLayout.PREFERRED_SIZE, 278, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPMostrarPacientesLayout.createSequentialGroup()
                        .addGap(322, 322, 322)
                        .addComponent(jBtnSeleccionar, javax.swing.GroupLayout.PREFERRED_SIZE, 278, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 914, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(13, Short.MAX_VALUE))
        );
        jPMostrarPacientesLayout.setVerticalGroup(
            jPMostrarPacientesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPMostrarPacientesLayout.createSequentialGroup()
                .addGap(81, 81, 81)
                .addGroup(jPMostrarPacientesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTFBusqueda, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel14)
                    .addComponent(jBtnMostrarTodos1, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 367, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jBtnSeleccionar, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(34, 34, 34))
        );

        getContentPane().add(jPMostrarPacientes, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 950, 580));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jBTNSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBTNSalirActionPerformed
        this.dispose();
    }//GEN-LAST:event_jBTNSalirActionPerformed

    private void jTFBusquedaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTFBusquedaKeyReleased
        String Busqueda;
        Busqueda = this.jTFBusqueda.getText();
        
        try{
            this.llenarTablarPorNombre(Busqueda);
        } catch (SQLException ex){
            JOptionPane.showMessageDialog(null,"Error: " + ex.getMessage());
        }
    }//GEN-LAST:event_jTFBusquedaKeyReleased

    private void jMIEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMIEditarActionPerformed

    }//GEN-LAST:event_jMIEditarActionPerformed

    private void jMiEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMiEliminarActionPerformed

    }//GEN-LAST:event_jMiEliminarActionPerformed

    private void jBtnSeleccionarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnSeleccionarActionPerformed
        try {
            seleccionarFila();
        } catch (SQLException ex) {
            Logger.getLogger(JFFVisorDeVacunador.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParseException ex) {
            Logger.getLogger(JFFVisorDeVacunador.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jBtnSeleccionarActionPerformed

    private void jBtnMostrarTodos1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnMostrarTodos1ActionPerformed
        try{
            this.llenarTabla();
            this.jTFBusqueda.setText("");
        } catch(SQLException ex){
            JOptionPane.showMessageDialog(null,"Error: " + ex.getMessage());
        }
    }//GEN-LAST:event_jBtnMostrarTodos1ActionPerformed

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
            java.util.logging.Logger.getLogger(JFFVisorDeVacunador.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(JFFVisorDeVacunador.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(JFFVisorDeVacunador.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(JFFVisorDeVacunador.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
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
                    new JFFVisorDeVacunador().setVisible(true);
                } catch (SQLException ex) {
                    Logger.getLogger(JFFVisorDeVacunador.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable JTblVacunadores;
    private javax.swing.JButton jBTNSalir;
    private javax.swing.JButton jBtnMostrarTodos1;
    private javax.swing.JButton jBtnSeleccionar;
    private javax.swing.JLabel jLBiconoNombre;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JMenuItem jMIEditar;
    private javax.swing.JMenuItem jMiEliminar;
    private javax.swing.JPanel jPFranjaInferior;
    private javax.swing.JPopupMenu jPMOpciones;
    private javax.swing.JPanel jPMostrarPacientes;
    private javax.swing.JPanel jPTitulo;
    private javax.swing.JPanel jPfranjaSuperior;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextField jTFBusqueda;
    // End of variables declaration//GEN-END:variables
}
