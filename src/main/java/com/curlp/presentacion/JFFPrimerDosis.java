/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.curlp.presentacion;

import com.curlp.datos.CDPaciente;
import com.curlp.logica.CLPaciente;
import com.curlp.presentacion.vistasPaciente.JFFAgregarPacienteManual;
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
public class JFFPrimerDosis extends javax.swing.JFrame {

    /**
     * Creates new form JFFMain
     */
    public JFFPrimerDosis() {
        initComponents();
        this.border = this.jTFnumIdentidad.getBorder();
        
    }
    
    
    //siguiente linea consigue el borde para devolver al default cuando sea necesario
    private final Border border;

        
    //metodos necesarios para funcionamiento de los botones
    
    public void salir(){
        this.setVisible(false);
    }
    
    public void regresar(){
       JFFSeleccion ventana = new JFFSeleccion();
       ventana.setVisible(true);
       this.setVisible(false);
    }
    
    public void inicio(){
       JFFMain ventana = new JFFMain();
       ventana.setVisible(true);
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
    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jBtnHome = new javax.swing.JButton();
        jBtnRegresar = new javax.swing.JButton();
        jBtnCancelar = new javax.swing.JButton();
        jBtnSegundaDosis1 = new javax.swing.JButton();
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
        jCboProfesion = new javax.swing.JComboBox<>();
        jLabel11 = new javax.swing.JLabel();
        jTFdia = new javax.swing.JTextField();
        jTFmes = new javax.swing.JTextField();
        jTFanio = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setLocation(new java.awt.Point(50, 25));
        setLocationByPlatform(true);
        setName("JFFMain"); // NOI18N
        setResizable(false);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jBtnHome.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jBtnHome.setForeground(new java.awt.Color(51, 102, 255));
        jBtnHome.setText("Inicio");
        jBtnHome.setMargin(new java.awt.Insets(2, 2, 2, 2));
        jBtnHome.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnHomeActionPerformed(evt);
            }
        });

        jBtnRegresar.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jBtnRegresar.setForeground(new java.awt.Color(51, 102, 255));
        jBtnRegresar.setText("Atras");
        jBtnRegresar.setMargin(new java.awt.Insets(2, 2, 2, 2));
        jBtnRegresar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnRegresarActionPerformed(evt);
            }
        });

        jBtnCancelar.setBackground(new java.awt.Color(255, 255, 255));
        jBtnCancelar.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jBtnCancelar.setForeground(new java.awt.Color(51, 102, 255));
        jBtnCancelar.setText("Cancelar");
        jBtnCancelar.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED, null, new java.awt.Color(51, 102, 255), null, null));
        jBtnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnCancelarActionPerformed(evt);
            }
        });

        jBtnSegundaDosis1.setBackground(new java.awt.Color(51, 102, 255));
        jBtnSegundaDosis1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jBtnSegundaDosis1.setForeground(new java.awt.Color(255, 255, 255));
        jBtnSegundaDosis1.setText("Registrar y Continuar");
        jBtnSegundaDosis1.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jBtnSegundaDosis1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnSegundaDosis1ActionPerformed(evt);
            }
        });

        jLabel4.setText("Ambos apellidos:");

        jLabel5.setText("Numero de celular:");

        jLabel10.setText("Profesion:");

        jCboSexo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "--Seleccione ", "Femenino ", "Masculino" }));

        jTFlugarTrabajo.setEnabled(false);

        jLabel3.setText("Ambos nombres:");

        jLabel7.setText("Si es asi, especifique donde:");

        jTAdireccion.setColumns(20);
        jTAdireccion.setRows(5);
        jScrollPane1.setViewportView(jTAdireccion);

        jCBtrabaja.setText("Trabaja?");
        jCBtrabaja.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCBtrabajaActionPerformed(evt);
            }
        });

        jLabel2.setText("Numero de Identidad(con guiones):");

        jLabel6.setText("Fecha de nacimiento:");

        jLabel9.setText("Sexo:");

        jLabel8.setText("Direccion de residencia:");

        jCboProfesion.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "--Seleccione", "Ingeniero" }));

        jLabel11.setFont(new java.awt.Font("Lucida Grande", 0, 10)); // NOI18N
        jLabel11.setText("Dia          Mes           Año");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(14, 14, 14)
                        .addComponent(jBtnRegresar, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jBtnSegundaDosis1, javax.swing.GroupLayout.PREFERRED_SIZE, 361, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jBtnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(61, 61, 61)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel5)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(jTFnumCelular))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel4)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(jTFapellidos)))
                                .addGap(27, 27, 27)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel10)
                                    .addComponent(jLabel8))
                                .addGap(202, 202, 202))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel2)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(jTFnumIdentidad))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel3)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 24, Short.MAX_VALUE)
                                        .addComponent(jTFnombres, javax.swing.GroupLayout.PREFERRED_SIZE, 372, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(8, 8, 8)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(0, 0, Short.MAX_VALUE)
                                        .addComponent(jBtnHome, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 299, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGroup(jPanel1Layout.createSequentialGroup()
                                                    .addGap(160, 160, 160)
                                                    .addComponent(jLabel7))
                                                .addGroup(jPanel1Layout.createSequentialGroup()
                                                    .addGap(80, 80, 80)
                                                    .addComponent(jTFlugarTrabajo, javax.swing.GroupLayout.PREFERRED_SIZE, 260, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addComponent(jCBtrabaja, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                        .addGap(0, 32, Short.MAX_VALUE))))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel6)
                                        .addGap(18, 18, 18)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addGap(47, 47, 47)
                                                .addComponent(jTFmes, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(6, 6, 6)
                                                .addComponent(jTFanio, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addComponent(jTFdia, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addGap(6, 6, 6)
                                                .addComponent(jLabel11))))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel9)
                                        .addGap(18, 18, 18)
                                        .addComponent(jCboSexo, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(0, 578, Short.MAX_VALUE)))))
                .addGap(15, 15, 15))
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                    .addContainerGap(601, Short.MAX_VALUE)
                    .addComponent(jCboProfesion, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(108, 108, 108)))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(55, 55, 55)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(jTFnumIdentidad, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(jTFnombres, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(22, 22, 22)
                        .addComponent(jBtnHome, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel7)
                        .addGap(4, 4, 4)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTFlugarTrabajo, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jCBtrabaja, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(jTFapellidos, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(jTFnumCelular, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel10)
                        .addGap(64, 64, 64)
                        .addComponent(jLabel8)))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(13, 13, 13)
                                .addComponent(jLabel6)
                                .addGap(28, 28, 28))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel11)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(9, 9, 9)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jTFmes, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jTFanio, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jTFdia, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel9)
                            .addComponent(jCboSexo, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(28, 28, 28)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jBtnRegresar, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jBtnSegundaDosis1, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jBtnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(18, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addGap(183, 183, 183)
                    .addComponent(jCboProfesion, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(224, Short.MAX_VALUE)))
        );

        jPanel2.setBackground(new java.awt.Color(51, 102, 255));

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Sin Derechos Reservados");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(406, 406, 406))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(21, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jBtnRegresarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnRegresarActionPerformed
        regresar();
    }//GEN-LAST:event_jBtnRegresarActionPerformed

    private void jBtnHomeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnHomeActionPerformed
        inicio();
    }//GEN-LAST:event_jBtnHomeActionPerformed

    private void jBtnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnCancelarActionPerformed
        limpiarCampos();
    }//GEN-LAST:event_jBtnCancelarActionPerformed

    private void jCBtrabajaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCBtrabajaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jCBtrabajaActionPerformed

    private void jBtnSegundaDosis1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnSegundaDosis1ActionPerformed
        try {
            registrarPaciente();
        } catch (SQLException ex) {
            Logger.getLogger(JFFAgregarPacienteManual.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jBtnSegundaDosis1ActionPerformed

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
            java.util.logging.Logger.getLogger(JFFPrimerDosis.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(JFFPrimerDosis.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(JFFPrimerDosis.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(JFFPrimerDosis.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
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
                new JFFPrimerDosis().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jBtnCancelar;
    private javax.swing.JButton jBtnHome;
    private javax.swing.JButton jBtnRegresar;
    private javax.swing.JButton jBtnSegundaDosis1;
    private javax.swing.JCheckBox jCBtrabaja;
    private javax.swing.JComboBox<String> jCboProfesion;
    private javax.swing.JComboBox<String> jCboSexo;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
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
