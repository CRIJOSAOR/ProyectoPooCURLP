/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.curlp.presentacion.vistasPaciente;
import com.curlp.presentacion.*;
import com.curlp.datos.CDPaciente;
import com.curlp.logica.CLPaciente;
import java.awt.Color;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.JOptionPane;
import javax.swing.border.Border;



/**
 *
 * @author davidmendoza
 */
public class JFFAgregarPacienteManual extends javax.swing.JFrame {

    /**
     * Creates new form JFFMain
     */
    public JFFAgregarPacienteManual() {
        initComponents();
        this.border = this.jTFnumIdentidad.getBorder();
        inhabilitarBotonCerrar();
    }
    //siguiente linea consigue el borde para devolver al default cuando sea necesario
    private final Border border;

        
    //metodos necesarios para funcionamiento de los botones
    public final void inhabilitarBotonCerrar(){
        setDefaultCloseOperation(0);
    }
    
    public void salir(){
        this.setVisible(false);
    }
    
    //metodos para registrar un paciente
    public void limpiarCampos(){
        this.jTFnumIdentidad.setText("");
        this.jTFnombres.setText("");
        this.jTFapellidos.setText("");
        this.jTFnumCelular.setText("");
        this.jTFlugarTrabajo.setText("");
        this.jCboSexo.setSelectedIndex(0);
        this.jCboProfesion.setSelectedIndex(0);
        this.jCBtrabaja.setSelected(false);
        this.jTAdireccion.setText("");
        this.jTFdia.setText("");
        this.jTFmes.setText("");
        this.jTFanio.setText("");
        
        // devolver los bordes a default
        
        this.jTFnumIdentidad.setBorder(this.border);
        this.jTFnombres.setBorder(this.border);
        this.jTFnombres.setBorder(this.border);
        this.jTFapellidos.setBorder(this.border);
        this.jTFnumCelular.setBorder(this.border);
        this.jTFlugarTrabajo.setBorder(this.border);
        this.jCboSexo.setBorder(this.border);
        this.jCboProfesion.setBorder(this.border);
        this.jCBtrabaja.setBorder(this.border);
        this.jTAdireccion.setBorder(this.border);
        this.jTFdia.setBorder(this.border);
        this.jTFmes.setBorder(this.border);
        this.jTFanio.setBorder(this.border);
        
    }
    
    public boolean verificarCampos(){
        boolean verificador = true ;
        String errors = ""; 
        
        //-------------------------
        //verificacion de numero de identidad
        String numIdentidad = this.jTFnumIdentidad.getText();
        
        if(numIdentidad.length() == 15){ //se verifica si tiene el tamanio correcto de 15 elementos 
            //si el numero de identidad no tiene 15 elementos
            if(numIdentidad.charAt(4) != '-' && numIdentidad.charAt(9) != '-'){ //deben estar los guiones en la posicion correcta
                verificador = false;
                errors +=  "Debe Agregar el numero con los guiones ejemplo: 0801-2000-00011 \n";
                this.jTFnumIdentidad.setBorder(BorderFactory.createLineBorder(Color.red));
            }
            
        } else {
            verificador = false;
            errors +=  "Debe ingresar correctamente el numero de identidad, ejemplo: 0801-2000-00011 \n";
            this.jTFnumIdentidad.setBorder(BorderFactory.createLineBorder(Color.red));
        }
       
        //--------------------------
        //verificar que nombre y apellido este lleno
        
        if(this.jTFnombres.getText().length() == 0){
            verificador = false;
            errors +=  "debe Ingresar el nombre \n";
            this.jTFnombres.setBorder(BorderFactory.createLineBorder(Color.red));
            
        }
        
        if(this.jTFapellidos.getText().length() == 0){
            verificador = false;
            errors +=  "debe Ingresar el apellido \n";
            this.jTFapellidos.setBorder(BorderFactory.createLineBorder(Color.red));
            
        }
        
        //--------------------------
        // verificar que el numero de telefono este lleno, tenga todos los numeros y el guion
        
        if(this.jTFnumCelular.getText().length() == 0){
            verificador = false;
            errors +=  "debe Ingresar el numero de telefono\n";
            this.jTFnumCelular.setBorder(BorderFactory.createLineBorder(Color.red));
            
        }
        
        if(this.jTFnumCelular.getText().length() == 9){
            
            if('-' != this.jTFnumCelular.getText().charAt(4)){
                verificador = false;
                errors +=  "elnumero de telefono debe llevar un guion\n";
                this.jTFnumCelular.setBorder(BorderFactory.createLineBorder(Color.red));
            }
        } else {
            verificador = false;
            errors +=  "El numero de telefono es incorrecto, debe ir asi: 0000-0000\n";
            this.jTFnumCelular.setBorder(BorderFactory.createLineBorder(Color.red));
        }
        
        //--------------------------
        //verificar la fecha de naciemiento esto es temporal
        if(this.jTFdia.getText().length() == 0){
            verificador = false;
            errors +=  "Dia de nacimiento no registrado\n";
            this.jTFdia.setBorder(BorderFactory.createLineBorder(Color.red));
            
        }
        
        if(this.jTFmes.getText().length() == 0){
            verificador = false;
            errors +=  "Mes de nacimiento no registrado\n";
            this.jTFmes.setBorder(BorderFactory.createLineBorder(Color.red));
            
        }
        
        if(this.jTFanio.getText().length() == 0){
            verificador = false;
            errors +=  "Año de nacimiento no registrado\n";
            this.jTFanio.setBorder(BorderFactory.createLineBorder(Color.red));
            
        }
        
        //--------------------------
        // verificar que los combo box esten seleccionados
        
        if(this.jCboSexo.getSelectedIndex() == 0){
            verificador = false;
            errors +=  "Debe seleccionar un Sexo\n";
            this.jCboSexo.setBorder(BorderFactory.createLineBorder(Color.red));
        }
        
        if(this.jCboProfesion.getSelectedIndex() == 0){
            verificador = false;
            errors +=  "Debe seleccionar una Profesion\n";
            this.jCboProfesion.setBorder(BorderFactory.createLineBorder(Color.red));
        }
        
        //--------------------------
        //verificar si habilito Trabajo y en dado caso que haya algo escrito
        
        if(this.jCBtrabaja.isSelected()){
            if(this.jTFlugarTrabajo.getText().length() == 0 ){
                verificador = false;
                errors +=  "No registro ningun lugar de trabajo\n";
                this.jTFlugarTrabajo.setBorder(BorderFactory.createLineBorder(Color.red));
                
            
            }
        }
        
        //verificar que el campo de direccion este lleno
        
        if(this.jTAdireccion.getText().length() == 0){
            verificador = false;
            errors +=  "No hay direccion ingresada \n";
            this.jTAdireccion.setBorder(BorderFactory.createLineBorder(Color.red));
            
        }
        
        if(verificador == false){
           JOptionPane.showMessageDialog(null,errors);
        }
        return verificador;
    }
    
    public void registrarPaciente() throws SQLException{
        //instancias para el registro del paciente y clase paciente
        CDPaciente registro = new CDPaciente();  // registro sera quien inserte a la base de Datos
        CLPaciente paciente = new CLPaciente();  // paciente guardara la informacion que escribira el usuario
        
        // Antes de todo se debe verificar que los campos cumplan con lo requerido
        if(this.verificarCampos()){
            // recuperar datos de los Combo Box
            int idSexo = this.jCboSexo.getSelectedIndex();
            int idProfesion = this.jCboProfesion.getSelectedIndex();
            
            // temporalmente se recoje la fecha de esta forma
            String fecha = this.jTFanio.getText() + "-" + this.jTFmes.getText() + "-" + this.jTFdia.getText();
            
            //Siguiente paso es completar toda la informacion necesaria para la clase CLPaciente 
            
            paciente.setNumIdentidad(this.jTFnumIdentidad.getText());
            paciente.setNombres(this.jTFnombres.getText());
            paciente.setApellidos(this.jTFapellidos.getText());
            paciente.setNumCelular(this.jTFnumCelular.getText());
            paciente.setFechaNacimiento(fecha);
            paciente.setLugarTrabajo(this.jTFlugarTrabajo.getText());
            paciente.setDireccion(this.jTAdireccion.getText());
            paciente.setIdSexo(idSexo);
            paciente.setIdProfesion(idProfesion);
            
            //siguiente linea inserta el objeto Paciente a la base de datos
            
            registro.insertarPaciente(paciente);
            this.limpiarCampos();
        }
        
    }
    public void activarCampoLugarTrabajo(){
        if(this.jCBtrabaja.isSelected()){
            this.jTFlugarTrabajo.setEnabled(true);
        } else {
            this.jTFlugarTrabajo.setText("");
            this.jTFlugarTrabajo.setEnabled(false);
        }
        
    }
    
    public void inicio(){
       JFFMain ventana = new JFFMain();
       ventana.setVisible(true);
       this.setVisible(false);
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
        jBtnSalir = new javax.swing.JButton();
        jBtnRegistrarPaciente = new javax.swing.JButton();
        jBtnLimpiar = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jCboSexo = new javax.swing.JComboBox<>();
        jTFnumIdentidad = new javax.swing.JTextField();
        jTFlugarTrabajo = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jTFnombres = new javax.swing.JTextField();
        jTFnumCelular = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTAdireccion = new javax.swing.JTextArea();
        jCBtrabaja = new javax.swing.JCheckBox();
        jLabel2 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jTFapellidos = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jTFdia = new javax.swing.JTextField();
        jTFmes = new javax.swing.JTextField();
        jTFanio = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jCboProfesion = new javax.swing.JComboBox<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Registro Manual de Pacientes");
        setLocation(new java.awt.Point(50, 25));
        setLocationByPlatform(true);
        setName("JFFMain"); // NOI18N
        setResizable(false);

        jPanel2.setBackground(new java.awt.Color(51, 102, 255));

        jBtnSalir.setBackground(new java.awt.Color(255, 255, 255));
        jBtnSalir.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jBtnSalir.setForeground(new java.awt.Color(51, 102, 255));
        jBtnSalir.setText("Cerrar");
        jBtnSalir.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED, null, new java.awt.Color(51, 102, 255), null, null));
        jBtnSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnSalirActionPerformed(evt);
            }
        });

        jBtnRegistrarPaciente.setBackground(new java.awt.Color(255, 255, 255));
        jBtnRegistrarPaciente.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jBtnRegistrarPaciente.setForeground(new java.awt.Color(51, 102, 255));
        jBtnRegistrarPaciente.setText("Registrar");
        jBtnRegistrarPaciente.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED, null, new java.awt.Color(51, 102, 255), null, null));
        jBtnRegistrarPaciente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnRegistrarPacienteActionPerformed(evt);
            }
        });

        jBtnLimpiar.setBackground(new java.awt.Color(255, 255, 255));
        jBtnLimpiar.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jBtnLimpiar.setForeground(new java.awt.Color(51, 102, 255));
        jBtnLimpiar.setText("Limpiar");
        jBtnLimpiar.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED, null, new java.awt.Color(51, 102, 255), null, null));
        jBtnLimpiar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnLimpiarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jBtnRegistrarPaciente, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jBtnLimpiar, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jBtnSalir, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(14, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jBtnRegistrarPaciente, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jBtnSalir, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jBtnLimpiar, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel4.setText("Ambos apellidos:");
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(15, 137, -1, -1));

        jLabel5.setText("Numero de celular:");
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(15, 178, -1, -1));

        jLabel10.setText("Profesion:");
        jPanel1.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(15, 388, -1, -1));

        jCboSexo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "--Seleccione", "Femenino", "Masculino" }));
        jPanel1.add(jCboSexo, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 280, 250, 40));

        jTFnumIdentidad.setToolTipText("");
        jPanel1.add(jTFnumIdentidad, new org.netbeans.lib.awtextra.AbsoluteConstraints(15, 46, 270, 30));

        jTFlugarTrabajo.setEnabled(false);
        jPanel1.add(jTFlugarTrabajo, new org.netbeans.lib.awtextra.AbsoluteConstraints(95, 346, 190, 30));

        jLabel3.setText("Ambos nombres:");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(15, 95, -1, -1));

        jLabel7.setText("Si es asi, especifique donde:");
        jPanel1.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(107, 326, -1, -1));
        jPanel1.add(jTFnombres, new org.netbeans.lib.awtextra.AbsoluteConstraints(128, 88, 157, 30));
        jPanel1.add(jTFnumCelular, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 171, 145, 30));

        jTAdireccion.setColumns(20);
        jTAdireccion.setRows(5);
        jScrollPane1.setViewportView(jTAdireccion);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(15, 484, 270, -1));

        jCBtrabaja.setText("Trabaja?");
        jCBtrabaja.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCBtrabajaActionPerformed(evt);
            }
        });
        jPanel1.add(jCBtrabaja, new org.netbeans.lib.awtextra.AbsoluteConstraints(15, 346, 100, 30));

        jLabel2.setText("Numero de Identidad(con guiones):");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(51, 18, -1, -1));

        jLabel6.setText("Fecha de nacimiento:");
        jPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(15, 228, -1, -1));
        jPanel1.add(jTFapellidos, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 130, 155, 30));

        jLabel9.setText("Sexo:");
        jPanel1.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(15, 260, -1, -1));

        jLabel8.setText("Direccion de residencia:");
        jPanel1.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(15, 462, -1, -1));
        jPanel1.add(jTFdia, new org.netbeans.lib.awtextra.AbsoluteConstraints(154, 219, 41, 35));
        jPanel1.add(jTFmes, new org.netbeans.lib.awtextra.AbsoluteConstraints(201, 219, 41, 35));
        jPanel1.add(jTFanio, new org.netbeans.lib.awtextra.AbsoluteConstraints(248, 219, 47, 35));

        jLabel1.setFont(new java.awt.Font("Lucida Grande", 0, 10)); // NOI18N
        jLabel1.setText("Dia          Mes           Año");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 210, -1, -1));

        jCboProfesion.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "--Seleccione", "Ingeniero" }));
        jPanel1.add(jCboProfesion, new org.netbeans.lib.awtextra.AbsoluteConstraints(15, 410, 250, 40));

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
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jBtnSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnSalirActionPerformed
        salir();
    }//GEN-LAST:event_jBtnSalirActionPerformed

    private void jCBtrabajaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCBtrabajaActionPerformed
        activarCampoLugarTrabajo();
        
    }//GEN-LAST:event_jCBtrabajaActionPerformed

    private void jBtnRegistrarPacienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnRegistrarPacienteActionPerformed
        try {
            registrarPaciente();
        } catch (SQLException ex) {
            Logger.getLogger(JFFAgregarPacienteManual.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jBtnRegistrarPacienteActionPerformed

    private void jBtnLimpiarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnLimpiarActionPerformed
        // TODO add your handling code here
        limpiarCampos();
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
            java.util.logging.Logger.getLogger(JFFAgregarPacienteManual.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(JFFAgregarPacienteManual.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(JFFAgregarPacienteManual.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(JFFAgregarPacienteManual.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
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
                new JFFAgregarPacienteManual().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jBtnLimpiar;
    private javax.swing.JButton jBtnRegistrarPaciente;
    private javax.swing.JButton jBtnSalir;
    private javax.swing.JCheckBox jCBtrabaja;
    private javax.swing.JComboBox<String> jCboProfesion;
    private javax.swing.JComboBox<String> jCboSexo;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea jTAdireccion;
    private javax.swing.JTextField jTFanio;
    private javax.swing.JTextField jTFapellidos;
    private javax.swing.JTextField jTFdia;
    private javax.swing.JTextField jTFlugarTrabajo;
    private javax.swing.JTextField jTFmes;
    private javax.swing.JTextField jTFnombres;
    private javax.swing.JTextField jTFnumCelular;
    private javax.swing.JTextField jTFnumIdentidad;
    // End of variables declaration//GEN-END:variables
}
