/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.curlp.presentacion;

import com.curlp.datos.CDPaciente;
import com.curlp.logica.CLPaciente;
import java.awt.Color;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author davidmendoza
 */
public class JFFPaciente extends javax.swing.JFrame {

    /**
     * Creates new form JFFPaciente
     */
    public JFFPaciente() throws SQLException {
        initComponents();
        agregarIconos();
        llenarTabla();
        this.setLocationRelativeTo(null);
        this.border = this.jTFnumIdentidad.getBorder();
    }
    /* se agrega miembro de clase tipo border para obtener el borde default. si hay errores el borde del componente sera rojo asi que:
       con el mienbro de clase border se puede establecer el borde default a todos los campos */
    private final Border border;
    
    DefaultTableModel modelo; // permitira manejar la jTable

    // metodo de clase que permite agregar iconos a los botones y labels del JFForm
    public final void  agregarIconos(){
        ImageIcon iconobtn = new ImageIcon("src/main/java/com/curlp/imagenes/logout.png");
        ImageIcon iconLogoTitulo = new ImageIcon("src/main/java/com/curlp/imagenes/user.png");
        ImageIcon iconbtnGuardar = new ImageIcon("src/main/java/com/curlp/imagenes/save.png");
        ImageIcon iconbtnEditar = new ImageIcon("src/main/java/com/curlp/imagenes/edit.png");
        ImageIcon iconbtnEliminar = new ImageIcon("src/main/java/com/curlp/imagenes/delete.png");
        this.jBTNSalir.setIcon(iconobtn);
        this.jLBiconoNombre.setIcon(iconLogoTitulo);
        this.jBtnGuardar.setIcon(iconbtnGuardar);
        this.jBtnEditar.setIcon(iconbtnEditar);
        this.jBtnEliminar.setIcon(iconbtnEliminar);
        
    }
    
    /**
        +++++++++++++++++++++++ Metodos para ingreso de Paciente ++++++++++++++++++++++++++++++++++++
    
    */
    // metodo de clase que verifica si los campos para insertar un paciente cumplen con los requerimientos
    public boolean verificarCampos(){
        boolean verificador = true ;
        String errors = ""; 
        
        //-------------------------
        // verificacion de numero de identidad
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
        // verificar que nombre y apellido este lleno
        
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
        // verificar si habilito Trabajo y en dado caso que haya algo escrito
        
        if(this.jCBtrabaja.isSelected()){
            if(this.jTFlugarTrabajo.getText().length() == 0 ){
                verificador = false;
                errors +=  "No registro ningun lugar de trabajo\n";
                this.jTFlugarTrabajo.setBorder(BorderFactory.createLineBorder(Color.red));
                
            
            }
        }
        
        // verificar que el campo de direccion este lleno
        
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
        // instancias para el registro del paciente y clase paciente
        CDPaciente registro = new CDPaciente();  // registro sera quien inserte a la base de Datos
        CLPaciente paciente = new CLPaciente();  // paciente guardara la informacion que escribira el usuario
        
        // Antes de todo se debe verificar que los campos cumplan con lo requerido
        if(this.verificarCampos()){
            // recuperar datos de los Combo Box
            int idSexo = this.jCboSexo.getSelectedIndex();
            int idProfesion = this.jCboProfesion.getSelectedIndex();
            
            // temporalmente se recoje la fecha de esta forma
            
            
            // Siguiente paso es completar toda la informacion necesaria para la clase CLPaciente 
            
            paciente.setNumIdentidad(this.jTFnumIdentidad.getText());
            paciente.setNombres(this.jTFnombres.getText());
            paciente.setApellidos(this.jTFapellidos.getText());
            paciente.setNumCelular(this.jTFFecha.getText());
            paciente.setFechaNacimiento(this.jTFFecha.getText());
            paciente.setLugarTrabajo(this.jTFlugarTrabajo.getText());
            paciente.setDireccion(this.jTAdireccion.getText());
            paciente.setIdSexo(idSexo);
            paciente.setIdProfesion(idProfesion);
            
            // siguiente linea inserta el objeto Paciente a la base de datos
            
            registro.insertarPaciente(paciente);
            //this.limpiarCampos();
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
        this.jTFFecha.setText("");
        
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
        this.jTFFecha.setBorder(this.border);
        
    }
    
    /**
        +++++++++++++++++++++++ Metodos para manipulacion de la tabla ++++++++++++++++++++++++++++++++++++
    
    */
    
    private void limpiarTabla(){
        
        modelo = (DefaultTableModel) this.jTBPacientes.getModel();
        
        while(modelo.getRowCount() > 0){
            modelo.removeRow(0);
        }
        
    }
    
    private void llenarTabla() throws SQLException{
        
        // limpiar la tabla
        this.limpiarTabla();
        
        //instanciamos un model 
        modelo = (DefaultTableModel) this.jTBPacientes.getModel();
        
        // instanciar una clase tipo CDPaciente para la conexion con la base de datos 
        CDPaciente registro = new CDPaciente();
        
        // recuperar todos los pacientes en forma de lista
        
        List<CLPaciente> listaPacientes = registro.mostrarPacientes();
        
        // llenar cada fila con un ciclo
        int cantidadRegistros = listaPacientes.size(); // se encuentra el tamanio de la lista
        
        for(int i = 0 ; i < cantidadRegistros ; i++){
            
            CLPaciente persona = (CLPaciente) listaPacientes.get(i); // se consigue el tamanio de la lista
            Object[] fila = new Object[5];
            fila[0] = persona.getNumIdentidad();
            fila[1] = persona.getNombres();
            fila[2] = persona.getApellidos();
            fila[3] = persona.getNumCelular();
            fila[4] = persona.getDireccion();
            
            // agregar al modal
            modelo.addRow(fila);
        // reasignar el modal a la jtabel
        this.jTBPacientes.setModel(modelo);
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

        jPTitulo = new javax.swing.JPanel();
        jBTNSalir = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLBiconoNombre = new javax.swing.JLabel();
        jPfranjaSuperior = new javax.swing.JPanel();
        jPFranjaInferior = new javax.swing.JPanel();
        JPDatosPaciente = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jCboSexo = new javax.swing.JComboBox<>();
        jTFnumIdentidad = new javax.swing.JTextField();
        jTFlugarTrabajo = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jTFnombres = new javax.swing.JTextField();
        jTFFecha = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTAdireccion = new javax.swing.JTextArea();
        jCBtrabaja = new javax.swing.JCheckBox();
        jLabel2 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jTFapellidos = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jCboProfesion = new javax.swing.JComboBox<>();
        jTFnumCelular = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        JPBotonesGestion = new javax.swing.JPanel();
        jBtnEditar = new javax.swing.JButton();
        jBtnGuardar = new javax.swing.JButton();
        jBtnEliminar = new javax.swing.JButton();
        jBtnLimpiar = new javax.swing.JButton();
        jPTable = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTBPacientes = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(255, 255, 255));
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

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
        jLabel1.setText("Gestion de Pacientes");

        javax.swing.GroupLayout jPTituloLayout = new javax.swing.GroupLayout(jPTitulo);
        jPTitulo.setLayout(jPTituloLayout);
        jPTituloLayout.setHorizontalGroup(
            jPTituloLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPTituloLayout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addComponent(jLBiconoNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 260, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 468, Short.MAX_VALUE)
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

        getContentPane().add(jPTitulo, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 950, 60));

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

        JPDatosPaciente.setBackground(new java.awt.Color(255, 255, 255));
        JPDatosPaciente.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Datos de Paciente", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Lucida Grande", 0, 13), new java.awt.Color(51, 51, 51))); // NOI18N
        JPDatosPaciente.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel4.setText("Apellido:");
        JPDatosPaciente.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 70, -1, -1));

        jLabel5.setText("Celular:");
        JPDatosPaciente.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 110, -1, -1));

        jLabel10.setText("Profesion:");
        JPDatosPaciente.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 180, -1, -1));

        jCboSexo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "--Seleccione", "Femenino", "Masculino" }));
        JPDatosPaciente.add(jCboSexo, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 140, 210, 30));

        jTFnumIdentidad.setToolTipText("");
        JPDatosPaciente.add(jTFnumIdentidad, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 20, 230, 30));

        jTFlugarTrabajo.setEnabled(false);
        JPDatosPaciente.add(jTFlugarTrabajo, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 230, 190, 30));

        jLabel3.setText("Nombre:");
        JPDatosPaciente.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 70, -1, -1));

        jLabel7.setFont(new java.awt.Font("Lucida Grande", 0, 10)); // NOI18N
        jLabel7.setText("Si es asi, especifique donde:");
        JPDatosPaciente.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 220, -1, -1));
        JPDatosPaciente.add(jTFnombres, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 60, 120, 30));
        JPDatosPaciente.add(jTFFecha, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 100, 120, 30));

        jTAdireccion.setColumns(20);
        jTAdireccion.setRows(5);
        jScrollPane1.setViewportView(jTAdireccion);

        JPDatosPaciente.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 290, 370, 90));

        jCBtrabaja.setText("Trabaja?");
        jCBtrabaja.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCBtrabajaActionPerformed(evt);
            }
        });
        JPDatosPaciente.add(jCBtrabaja, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 230, 100, 30));

        jLabel2.setText("Numero de Identidad:");
        JPDatosPaciente.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 30, -1, -1));

        jLabel6.setFont(new java.awt.Font("Lucida Grande", 0, 10)); // NOI18N
        jLabel6.setText("nacimiento:");
        JPDatosPaciente.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 110, -1, -1));
        JPDatosPaciente.add(jTFapellidos, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 60, 120, 30));

        jLabel9.setText("Sexo:");
        JPDatosPaciente.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 140, -1, -1));

        jLabel8.setText("Direccion de residencia:");
        JPDatosPaciente.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 270, -1, -1));

        jCboProfesion.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "--Seleccione", "Ingeniero" }));
        JPDatosPaciente.add(jCboProfesion, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 180, 210, 30));
        JPDatosPaciente.add(jTFnumCelular, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 100, 120, 30));

        jLabel11.setFont(new java.awt.Font("Lucida Grande", 0, 10)); // NOI18N
        jLabel11.setText("Fecha de ");
        JPDatosPaciente.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 100, -1, -1));

        getContentPane().add(JPDatosPaciente, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 70, 420, 390));

        JPBotonesGestion.setBackground(new java.awt.Color(255, 255, 255));
        JPBotonesGestion.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jBtnEditar.setText("Editar");
        jBtnEditar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnEditarActionPerformed(evt);
            }
        });

        jBtnGuardar.setText("Guardar");
        jBtnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnGuardarActionPerformed(evt);
            }
        });

        jBtnEliminar.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        jBtnEliminar.setText("Eliminar");
        jBtnEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnEliminarActionPerformed(evt);
            }
        });

        jBtnLimpiar.setText("Limpiar");
        jBtnLimpiar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnLimpiarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout JPBotonesGestionLayout = new javax.swing.GroupLayout(JPBotonesGestion);
        JPBotonesGestion.setLayout(JPBotonesGestionLayout);
        JPBotonesGestionLayout.setHorizontalGroup(
            JPBotonesGestionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(JPBotonesGestionLayout.createSequentialGroup()
                .addGap(36, 36, 36)
                .addGroup(JPBotonesGestionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jBtnLimpiar, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(JPBotonesGestionLayout.createSequentialGroup()
                        .addComponent(jBtnGuardar, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jBtnEditar, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jBtnEliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(41, Short.MAX_VALUE))
        );
        JPBotonesGestionLayout.setVerticalGroup(
            JPBotonesGestionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(JPBotonesGestionLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(JPBotonesGestionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jBtnEditar, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jBtnEliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jBtnGuardar, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jBtnLimpiar)
                .addContainerGap(10, Short.MAX_VALUE))
        );

        getContentPane().add(JPBotonesGestion, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 460, 420, 100));

        jPTable.setBackground(new java.awt.Color(255, 255, 255));

        jTBPacientes.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Identidad", "Nombres", "Apellidos", "Num Celular", "Direccion"
            }
        ));
        jTBPacientes.setShowGrid(true);
        jScrollPane2.setViewportView(jTBPacientes);

        javax.swing.GroupLayout jPTableLayout = new javax.swing.GroupLayout(jPTable);
        jPTable.setLayout(jPTableLayout);
        jPTableLayout.setHorizontalGroup(
            jPTableLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPTableLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 517, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(615, 615, 615))
        );
        jPTableLayout.setVerticalGroup(
            jPTableLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPTableLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 482, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(80, 80, 80))
        );

        getContentPane().add(jPTable, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 70, 530, 490));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jBTNSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBTNSalirActionPerformed
        this.dispose();
    }//GEN-LAST:event_jBTNSalirActionPerformed

    private void jCBtrabajaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCBtrabajaActionPerformed
        activarCampoLugarTrabajo();

    }//GEN-LAST:event_jCBtrabajaActionPerformed

    private void jBtnEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnEditarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jBtnEditarActionPerformed

    private void jBtnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnGuardarActionPerformed
        try {
            registrarPaciente();
            llenarTabla();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,"No se registro el paciente");
        }
    }//GEN-LAST:event_jBtnGuardarActionPerformed

    private void jBtnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnEliminarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jBtnEliminarActionPerformed

    private void jBtnLimpiarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnLimpiarActionPerformed
        // TODO add your handling code here:
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
            java.util.logging.Logger.getLogger(JFFPaciente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(JFFPaciente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(JFFPaciente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(JFFPaciente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new JFFPaciente().setVisible(true);
                } catch (SQLException ex) {
                    Logger.getLogger(JFFPaciente.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel JPBotonesGestion;
    private javax.swing.JPanel JPDatosPaciente;
    private javax.swing.JButton jBTNSalir;
    private javax.swing.JButton jBtnEditar;
    private javax.swing.JButton jBtnEliminar;
    private javax.swing.JButton jBtnGuardar;
    private javax.swing.JButton jBtnLimpiar;
    private javax.swing.JCheckBox jCBtrabaja;
    private javax.swing.JComboBox<String> jCboProfesion;
    private javax.swing.JComboBox<String> jCboSexo;
    private javax.swing.JLabel jLBiconoNombre;
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
    private javax.swing.JPanel jPFranjaInferior;
    private javax.swing.JPanel jPTable;
    private javax.swing.JPanel jPTitulo;
    private javax.swing.JPanel jPfranjaSuperior;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextArea jTAdireccion;
    private javax.swing.JTable jTBPacientes;
    private javax.swing.JTextField jTFFecha;
    private javax.swing.JTextField jTFapellidos;
    private javax.swing.JTextField jTFlugarTrabajo;
    private javax.swing.JTextField jTFnombres;
    private javax.swing.JTextField jTFnumCelular;
    private javax.swing.JTextField jTFnumIdentidad;
    // End of variables declaration//GEN-END:variables
}
