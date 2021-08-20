/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.curlp.capapresentacion;


import com.curlp.capadatos.CDPaciente;
import com.curlp.capadatos.CDProfesion;
import com.curlp.capadatos.CDSexo;
import com.curlp.capalogica.CLPaciente;
import com.curlp.capalogica.CLProfesiones;
import com.curlp.librerias.TextPrompt;
import java.awt.Color;
import java.util.Date;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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
     * @throws java.sql.SQLException
     */
    public JFFPaciente() throws SQLException {
        initComponents();
        agregarIconos();
        llenarTabla();
        llenarComboBoxProfesiones();
        llenarComboBoxSexo();
        this.setLocationRelativeTo(null);
        this.border = this.jTFnumIdentidad.getBorder(); 
        TextPrompt placeHolder = new TextPrompt("Ejemplo: Miguel Abraham",this.jTFnombres);
    }
    /* se agrega miembro de clase tipo border para obtener el borde default. si hay errores el borde del componente sera rojo asi que:
       con el mienbro de clase border se puede establecer el borde default a todos los campos */
    private final Border border;
    
    DefaultTableModel modelo; // permitira manejar la jTable
    
    /**
        +++++++++++++++++++++++ Metodos para gestion de Paciente ++++++++++++++++++++++++++++++++++++
    
    */
    public boolean registrarPaciente() throws SQLException{
        // instancias para el registro del paciente y clase paciente
        CDPaciente registro = new CDPaciente();  // registro sera quien inserte a la base de Datos
        CLPaciente paciente = new CLPaciente();  // paciente guardara la informacion que escribira el usuario
        CDProfesion profesion = new CDProfesion();
        
        boolean validar = false; // variable para retorno asi si hay un error no manda a recargar la tabla ni limpia campos permitiendo corregir errores
        
        // Antes de todo se debe verificar que los campos cumplan con lo requerido
        if(this.verificarCampos(1)){
            // recuperar datos de los Combo Box
            int idProfesion = profesion.obtenerIdProfesion(this.jCboProfesion.getItemAt(this.jCboProfesion.getSelectedIndex()).trim());
            //int idProfesion = this.getIdProfesion( this.jCboProfesion.getItemAt(this.jCboProfesion.getSelectedIndex()));
            int idSexo = this.jCboSexo.getSelectedIndex();
            
            // recoleccion de la fecha del jDateChooser
            java.sql.Date fechaSql = new java.sql.Date(this.jDCFechaNacimiento.getDate().getTime());
            
            // Siguiente paso es completar toda la informacion necesaria para la clase CLPaciente 
            
            paciente.setNumIdentidad(this.jTFnumIdentidad.getText().trim());
            paciente.setNombres(this.jTFnombres.getText().trim());
            paciente.setApellidos(this.jTFapellidos.getText().trim());
            paciente.setNumCelular(this.jTFnumCelular.getText().trim());
            paciente.setFechaNacimiento(fechaSql);
            paciente.setLugarTrabajo(this.jTFlugarTrabajo.getText().trim());
            paciente.setDireccion(this.jTAdireccion.getText().trim());
            paciente.setIdSexo(idSexo);
            paciente.setIdProfesion(idProfesion);
            
            // siguiente linea inserta el objeto Paciente a la base de datos
            
            registro.insertarPaciente(paciente);
            limpiarCampos();
            validar = true;
        }
        return validar;
    }

    public boolean actualizarPaciente() throws SQLException{
        // instancias para el registro del paciente y clase paciente
        CDPaciente registro = new CDPaciente();  // registro sera quien inserte a la base de Datos
        CLPaciente paciente = new CLPaciente();  // paciente guardara la informacion que escribira el usuario
        
        boolean validar = false; // variable para retorno asi si hay un error no manda a recargar la tabla ni limpia campos permitiendo corregir errores
        
        // Antes de todo se debe verificar que los campos cumplan con lo requerido
        if(this.verificarCampos(2)){
            // recuperar datos de los Combo Box
            int idSexo = this.jCboSexo.getSelectedIndex();
            int idProfesion = getIdProfesion(this.jCboProfesion.getItemAt(this.jCboProfesion.getSelectedIndex()));
            
            // recoleccion de la fecha del jDateChooser
            java.sql.Date fechaSql = new java.sql.Date(this.jDCFechaNacimiento.getDate().getTime());
            
            // Siguiente paso es completar toda la informacion necesaria para la clase CLPaciente 
            
            paciente.setNumIdentidad(this.jTFnumIdentidad.getText().trim());
            paciente.setNombres(this.jTFnombres.getText().trim());
            paciente.setApellidos(this.jTFapellidos.getText().trim());
            paciente.setNumCelular(this.jTFnumCelular.getText().trim());
            paciente.setFechaNacimiento(fechaSql);
            paciente.setLugarTrabajo(this.jTFlugarTrabajo.getText().trim());
            paciente.setDireccion(this.jTAdireccion.getText().trim());
            paciente.setIdSexo(idSexo);
            paciente.setIdProfesion(idProfesion);
            
            // siguiente linea inserta el objeto Paciente a la base de datos
            
            registro.actualizarPaciente(paciente);
            this.limpiarCampos();
            validar = true;
        }
        return validar;
    } 
    
    public void eliminarPaciente() throws SQLException {
        try{
            CDPaciente registro = new CDPaciente();  // registro sera quien inserte a la base de Datos
            CLPaciente paciente = new CLPaciente(); 
        
            // paciente.setNumIdentidad(this.jTFnumIdentidad.getText().trim());
            if(jTBPacientes.getSelectedRow() != -1){
                paciente.setNumIdentidad(String.valueOf(this.jTBPacientes.getValueAt(this.jTBPacientes.getSelectedRow(), 0)));
                registro.eliminarPaciente(paciente);
            }
            
            
        } catch (SQLException ex){
                JOptionPane.showMessageDialog(null, "Error al eliminar");
        }
        
    }

    /**
        +++++++++++++++++++++++ Metodos para gestion de jTable ++++++++++++++++++++++++++++++++++++
    
    */
    
        private void limpiarTabla(){
        
        modelo = (DefaultTableModel) this.jTBPacientes.getModel();
        
        while(modelo.getRowCount() > 0){
            modelo.removeRow(0);
        }
        
    }
    
    private void llenarTabla() throws SQLException{
        
        // limpiar la tabla
        limpiarTabla();
        
        // instanciar una clase tipo CDPaciente para la conexion con la base de datos 
        CDPaciente registro = new CDPaciente();
        
        // recuperar todos los pacientes en forma de lista
        List<CLPaciente> listaPacientes = registro.mostrarPacientes();
        // instanciamos un model 
        modelo = (DefaultTableModel) this.jTBPacientes.getModel();
      
        // llenar cada fila con un ciclo      
        listaPacientes.stream().map((CLPaciente persona) ->{
            Object[] fila = new Object[9];
            fila[0] = persona.getNumIdentidad();
            fila[1] = persona.getNombres();
            fila[2] = persona.getApellidos();
            fila[3] = persona.getNumCelular();
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            fila[4] = (String) sdf.format(persona.getFechaNacimiento());
            fila[5] = persona.getLugarTrabajo();
            fila[6] = persona.getDireccion();
            fila[7] = persona.getSexo();
            fila[8] = persona.getProfesion();
            return fila;
        }).forEachOrdered(modelo::addRow);    
    }
    
    private void llenarTablarPorNombre(String nombreCliente) throws SQLException {
        limpiarTabla();
        // instanciar una clase tipo CDPaciente para la conexion con la base de datos 
        CDPaciente registro = new CDPaciente();
        
        // recuperar todos los pacientes en forma de lista
        List<CLPaciente> listaPacientes = registro.obtenerListaPacientesPorNombre(nombreCliente);
        // instanciamos un model 
        modelo = (DefaultTableModel) this.jTBPacientes.getModel();
      
        // llenar cada fila con un ciclo      
        listaPacientes.stream().map((CLPaciente persona) ->{
            Object[] fila = new Object[9];
            fila[0] = persona.getNumIdentidad();
            fila[1] = persona.getNombres();
            fila[2] = persona.getApellidos();
            fila[3] = persona.getNumCelular();
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            fila[4] = (String) sdf.format(persona.getFechaNacimiento());
            fila[5] = persona.getLugarTrabajo();
            fila[6] = persona.getDireccion();
            fila[7] = persona.getSexo();
            fila[8] = persona.getProfesion();
            return fila;
        }).forEachOrdered(modelo::addRow);
        
        
    }
    
    private void seleccionarFila() throws SQLException, ParseException{
        if(this.jTBPacientes.getSelectedRow() != -1){
            
            // preparar el espacio para editar el registro
            this.jTFnumIdentidad.setEditable(false);
            this.jTFnombres.requestFocus();
            activarBotones(false,true,true);
            this.jTabbedPane1.setSelectedIndex(0);
            
            // insertar los datos en los campos
            this.jTFnumIdentidad.setText(String.valueOf(this.jTBPacientes.getValueAt(this.jTBPacientes.getSelectedRow(), 0)));
            this.jTFnombres.setText(String.valueOf(this.jTBPacientes.getValueAt(this.jTBPacientes.getSelectedRow(), 1)));
            this.jTFapellidos.setText(String.valueOf(this.jTBPacientes.getValueAt(this.jTBPacientes.getSelectedRow(), 2)));
            this.jTFnumCelular.setText(String.valueOf(this.jTBPacientes.getValueAt(this.jTBPacientes.getSelectedRow(), 3)));
            this.jTFlugarTrabajo.setText(String.valueOf(this.jTBPacientes.getValueAt(this.jTBPacientes.getSelectedRow(), 5)));
            this.jTAdireccion.setText(String.valueOf(this.jTBPacientes.getValueAt(this.jTBPacientes.getSelectedRow(), 6)));

            // asignar un lugar de trabajo si lo tiene y en dado caso activar el checkBox
            if(String.valueOf(this.jTBPacientes.getValueAt(this.jTBPacientes.getSelectedRow(), 5)).length() != 0){
                this.jCBtrabaja.setSelected(true);   
            } else {
                this.jCBtrabaja.setSelected(false);
            }

            activarCampoLugarTrabajo();

            // Buscar el index adecuado para el sexo y mostrarlo en el comboBox
            String sexo = String.valueOf(this.jTBPacientes.getValueAt(this.jTBPacientes.getSelectedRow(), 7)).trim();

            if(sexo.charAt(0) == 'F'){
                this.jCboSexo.setSelectedIndex(1);
            } else {
                this.jCboSexo.setSelectedIndex(2);
            }

            // Buscar el index correcto para mostrar la profesion ingresada
            String profesion = String.valueOf(this.jTBPacientes.getValueAt(this.jTBPacientes.getSelectedRow(), 8));
            int posicion = getSelecProfesion(profesion);
            this.jCboProfesion.setSelectedIndex(posicion);

            
            // asignar la fecha al jDateChooser
            Date fechaParseada = (Date) new SimpleDateFormat("dd/MM/yyyy").parse((String) this.jTBPacientes.getValueAt(this.jTBPacientes.getSelectedRow(), 4));
            this.jDCFechaNacimiento.setDate(fechaParseada);
            
            
            
        }
    }
    
    /**
        +++++++++++++++++++++++ Metodos para funcionamiendo de Botones ++++++++++++++++++++++++++++++++++++
    
    */
    
    public void guardar() throws SQLException{
        try{
                if (registrarPaciente()){
                    llenarTabla();
                    limpiarCampos();
                }

        } catch (SQLException ex){
                JOptionPane.showMessageDialog(null, "Error al ingresar");
        }
    }
    
    public void editar() throws SQLException{
        try{
                if(actualizarPaciente()){
                    llenarTabla();
                    limpiarCampos();
                }

        } catch (SQLException ex){
                JOptionPane.showMessageDialog(null, "Error al guardar cambios");
        }
    }
    
    public void eliminar() throws SQLException{
        int respuesta = JOptionPane.showConfirmDialog(null,"¿Está seguro de eliminar éste registro?", "Eliminar un Paciente", JOptionPane.YES_NO_OPTION);
        if(respuesta == JOptionPane.YES_OPTION){
            try{
                eliminarPaciente();
                llenarTabla(); 

            } catch (SQLException ex){
                JOptionPane.showMessageDialog(null, "Error al eliminar");
            }
        } 
    }  
    
    /**
        +++++++++++++++++++++++ Metodos para llenar los ComboBox ++++++++++++++++++++++++++++++++++++
    
    */   
    
    public void llenarComboBoxProfesiones() throws SQLException{
        CDProfesion profesiones = new CDProfesion();
        List<String> listaProfesiones = profesiones.cragarComboProfesiones();
        
        this.jCboProfesion.removeAllItems();
        
        for (String x: listaProfesiones){
             this.jCboProfesion.addItem(x);
        }
    }
    
    public void llenarComboBoxSexo() throws SQLException {
        CDSexo cds = new CDSexo();
        List<String> sexos = cds.cargarSexos();
        
        this.jCboSexo.removeAllItems();
        
        for (String x: sexos){
             this.jCboSexo.addItem(x);
        }
    }  
    
    /**
        +++++++++++++++++++++++ Metodos secundarios necesarios en metodos anteriores ++++++++++++++++++++++++++++++++++++
    
    */
    
    // metodo de clase que permite agregar iconos a los botones y labels del JFForm
    public final void  agregarIconos(){
        ImageIcon iconobtn = new ImageIcon("src/main/java/com/curlp/capaimagenes/logout.png");
        ImageIcon iconLogoTitulo = new ImageIcon("src/main/java/com/curlp/capaimagenes/user.png");
        ImageIcon iconbtnGuardar = new ImageIcon("src/main/java/com/curlp/capaimagenes/save.png");
        ImageIcon iconbtnEditar = new ImageIcon("src/main/java/com/curlp/capaimagenes/edit.png");
        ImageIcon iconImage = new ImageIcon("src/main/java/com/curlp/capaimagenes/image.jpg");
        this.jBTNSalir.setIcon(iconobtn);
        this.jLBiconoNombre.setIcon(iconLogoTitulo);
        this.jBtnGuardar.setIcon(iconbtnGuardar);
        this.jBtnEditar.setIcon(iconbtnEditar);
        this.jLimagenGrande.setIcon(iconImage);
    }

    // metodo de clase que verifica si los campos para insertar un paciente cumplen con los requerimientos
    public boolean verificarCampos( int caso){
        boolean verificador = true ;
        String errors = ""; 
        
        //-------------------------
        
        
        if(caso == 1 && jTFnumIdentidad.getValue() == null) { //se verifica si tiene el tamanio correcto de 15 elementos 
            //si el numero de identidad no tiene 15 elementos
            verificador = false;
            errors +=  "Debe ingresar correctamente el numero de identidad, ejemplo: 0801-2000-00011 \n";
            this.jTFnumIdentidad.setBorder(BorderFactory.createLineBorder(Color.red));
        }
       
        //--------------------------
        // verificar que nombre y apellido este lleno
        
        if(this.jTFnombres.getText().length() == 0){
            
            verificador = false;
            errors +=  "Por favor ingrese un nombre \n";
            this.jTFnombres.setBorder(BorderFactory.createLineBorder(Color.red));
            
        }
        
        if(this.jTFapellidos.getText().length() == 0){
            verificador = false;
            errors +=  "Por favor ingrese un apellido \n";
            this.jTFapellidos.setBorder(BorderFactory.createLineBorder(Color.red));
            
        }
        
        //--------------------------
        // verificar que el numero de telefono este lleno, tenga todos los numeros 

        if(caso == 1 && this.jTFnumCelular.getValue() == null){
            verificador = false;
            errors +=  "El número de teléfono debe tener 9 digitos, por ejemplo: 0000-0000\n";
            this.jTFnumCelular.setBorder(BorderFactory.createLineBorder(Color.red));
            
        }
        

        //--------------------------
        // verificar que los combo box esten seleccionados
        
        if(this.jCboSexo.getSelectedIndex() == 0){
            verificador = false;
            errors +=  "Por favor seleccione un sexo\n";
            this.jCboSexo.setBorder(BorderFactory.createLineBorder(Color.red));
        }
        
        if(this.jCboProfesion.getSelectedIndex() == 0){
            verificador = false;
            errors +=  "Por favor seleccione una profesión\n";
            this.jCboProfesion.setBorder(BorderFactory.createLineBorder(Color.red));
        }
        
        //--------------------------
        // verificar si habilito Trabajo y en dado caso que haya algo escrito
        
        if(this.jCBtrabaja.isSelected()){
            if(this.jTFlugarTrabajo.getText().length() == 0 ){
                verificador = false;
                errors +=  "Si trabaja por favor introduzca el lugar de empleo\n";
                this.jTFlugarTrabajo.setBorder(BorderFactory.createLineBorder(Color.red));
                
            
            }
        }
        
        // verificar que el campo de direccion este lleno
        
        if(this.jTAdireccion.getText().length() == 0){
            verificador = false;
            errors +=  "Por Favor introduza un dirección\n";
            this.jTAdireccion.setBorder(BorderFactory.createLineBorder(Color.red));
            
        }
        // verificar si se introdujo una fecha al jDayChooser
        if(this.jDCFechaNacimiento.getDate() == null){
            verificador = false;
            errors +=  "Por Favor introduza una fecha de nacimiento\n";
            
        } 
        
        if(verificador == false){
           JOptionPane.showMessageDialog(null,errors);
        }
        return verificador;
    }


    public int getIdProfesion(String profesion) throws SQLException{
        CDProfesion datos = new CDProfesion();
        List<CLProfesiones> listaProfeciones = datos.obtenerListaProfesiones();
        int index = 1;
        for(int i = 0; i < listaProfeciones.size();i++){
            String profesionDB;
            profesionDB = listaProfeciones.get(i).getProfesion().trim();
            
            if(profesionDB.equals(profesion.trim())){
                index = listaProfeciones.get(i).getIdProfesion();
            }
        } 
        return index;
    }
    
    public int getSelecProfesion(String profesion){
        int index = 0;
        
       
        for (int i = 1; i < this.jCboProfesion.getItemCount() ; i++){
            
            if(profesion.trim().equals(String.valueOf(this.jCboProfesion.getItemAt(i)).trim())) {
                index = i ;    
            }
        }
        return index;
    }
    public void activarCampoLugarTrabajo(){
        if(this.jCBtrabaja.isSelected()){
            this.jTFlugarTrabajo.setEnabled(true);
            jLBmensaje.setForeground(Color.black);
        } else {
            this.jTFlugarTrabajo.setText("");
            this.jTFlugarTrabajo.setEnabled(false);
            jLBmensaje.setForeground(Color.white);
        }
        
    }
    
    public void limpiarCampos(){
        this.jTFnumIdentidad.requestFocus();
        this.jTFnumIdentidad.setEditable(true);
        activarBotones(true,false,true);
        
        this.jTFnumIdentidad.setValue(null);
        this.jTFnombres.setText("");
        this.jTFapellidos.setText("");
        this.jTFnumCelular.setValue(null);
        this.jTFlugarTrabajo.setText("");
        this.jCboSexo.setSelectedIndex(0);
        this.jCboProfesion.setSelectedIndex(0);
        this.jCBtrabaja.setSelected(false);
        this.jTAdireccion.setText("");
        this.jDCFechaNacimiento.setCalendar(null);
        
        
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
        
        
    }
    
    
    private void activarBotones(boolean estadoGuardar, boolean estadoEditar, boolean estadoLimpiar){
        this.jBtnGuardar.setEnabled(estadoGuardar);
        this.jBtnEditar.setEnabled(estadoEditar);
        this.jBtnLimpiar.setEnabled(estadoLimpiar);
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
        jPTitulo = new javax.swing.JPanel();
        jBTNSalir = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLBiconoNombre = new javax.swing.JLabel();
        jPfranjaSuperior = new javax.swing.JPanel();
        jPFranjaInferior = new javax.swing.JPanel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPgestionar = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jCboSexo = new javax.swing.JComboBox<>();
        jTFlugarTrabajo = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jTFnombres = new javax.swing.JTextField();
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
        jLBmensaje = new javax.swing.JLabel();
        jBtnEditar = new javax.swing.JButton();
        jBtnGuardar = new javax.swing.JButton();
        jBtnLimpiar = new javax.swing.JButton();
        jLimagenGrande = new javax.swing.JLabel();
        jTFnumIdentidad = new javax.swing.JFormattedTextField();
        jTFnumCelular = new javax.swing.JFormattedTextField();
        jDCFechaNacimiento = new com.toedter.calendar.JDateChooser();
        jPMostrarPacientes = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTBPacientes = new javax.swing.JTable();

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

        jPTitulo.setBackground(new java.awt.Color(255, 255, 255));

        jBTNSalir.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jBTNSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBTNSalirActionPerformed(evt);
            }
        });

        jLabel1.setBackground(new java.awt.Color(0, 153, 153));
        jLabel1.setFont(new java.awt.Font("Tahoma", 3, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 153, 153));
        jLabel1.setText("Gestión de Pacientes");

        javax.swing.GroupLayout jPTituloLayout = new javax.swing.GroupLayout(jPTitulo);
        jPTitulo.setLayout(jPTituloLayout);
        jPTituloLayout.setHorizontalGroup(
            jPTituloLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPTituloLayout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addComponent(jLBiconoNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 260, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 558, Short.MAX_VALUE)
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

        getContentPane().add(jPTitulo, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1040, 60));

        jPfranjaSuperior.setBackground(new java.awt.Color(0, 153, 153));

        javax.swing.GroupLayout jPfranjaSuperiorLayout = new javax.swing.GroupLayout(jPfranjaSuperior);
        jPfranjaSuperior.setLayout(jPfranjaSuperiorLayout);
        jPfranjaSuperiorLayout.setHorizontalGroup(
            jPfranjaSuperiorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1040, Short.MAX_VALUE)
        );
        jPfranjaSuperiorLayout.setVerticalGroup(
            jPfranjaSuperiorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 10, Short.MAX_VALUE)
        );

        getContentPane().add(jPfranjaSuperior, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 60, 1040, 10));

        jPFranjaInferior.setBackground(new java.awt.Color(0, 153, 153));

        javax.swing.GroupLayout jPFranjaInferiorLayout = new javax.swing.GroupLayout(jPFranjaInferior);
        jPFranjaInferior.setLayout(jPFranjaInferiorLayout);
        jPFranjaInferiorLayout.setHorizontalGroup(
            jPFranjaInferiorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1040, Short.MAX_VALUE)
        );
        jPFranjaInferiorLayout.setVerticalGroup(
            jPFranjaInferiorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 20, Short.MAX_VALUE)
        );

        getContentPane().add(jPFranjaInferior, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 560, 1040, 20));

        jPgestionar.setBackground(new java.awt.Color(255, 255, 255));
        jPgestionar.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel4.setText("Apellido:");
        jPgestionar.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 80, -1, -1));

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel5.setText("Celular:");
        jPgestionar.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 130, -1, -1));

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel10.setText("Profesión:");
        jPgestionar.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 180, -1, -1));

        jCboSexo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "--Seleccione", "Femenino", "Masculino" }));
        jCboSexo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCboSexoActionPerformed(evt);
            }
        });
        jPgestionar.add(jCboSexo, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 170, 210, 30));

        jTFlugarTrabajo.setEnabled(false);
        jTFlugarTrabajo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTFlugarTrabajoKeyPressed(evt);
            }
        });
        jPgestionar.add(jTFlugarTrabajo, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 220, 190, 30));

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel3.setText("Nombre:");
        jPgestionar.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 80, -1, -1));

        jTFnombres.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTFnombresKeyPressed(evt);
            }
        });
        jPgestionar.add(jTFnombres, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 70, 190, 30));

        jTAdireccion.setColumns(20);
        jTAdireccion.setRows(5);
        jTAdireccion.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTAdireccionKeyPressed(evt);
            }
        });
        jScrollPane1.setViewportView(jTAdireccion);

        jPgestionar.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 270, 510, 90));

        jCBtrabaja.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jCBtrabaja.setText("Trabaja?");
        jCBtrabaja.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCBtrabajaActionPerformed(evt);
            }
        });
        jPgestionar.add(jCBtrabaja, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 220, 100, 30));

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel2.setText("Número de Identidad:");
        jPgestionar.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 40, -1, -1));

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel6.setText("nacimiento:");
        jPgestionar.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 120, -1, -1));

        jTFapellidos.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTFapellidosKeyPressed(evt);
            }
        });
        jPgestionar.add(jTFapellidos, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 70, 180, 30));

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel9.setText("Sexo:");
        jPgestionar.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 180, -1, -1));

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel8.setText("Dirección de residencia:");
        jPgestionar.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 250, -1, -1));

        jCboProfesion.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "--Seleccione", "Ingeniero" }));
        jCboProfesion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCboProfesionActionPerformed(evt);
            }
        });
        jPgestionar.add(jCboProfesion, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 170, 210, 30));

        jLabel11.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel11.setText("Fecha de ");
        jPgestionar.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 110, -1, -1));

        jLBmensaje.setBackground(new java.awt.Color(255, 255, 255));
        jLBmensaje.setFont(new java.awt.Font("Lucida Grande", 0, 10)); // NOI18N
        jLBmensaje.setForeground(new java.awt.Color(255, 255, 255));
        jLBmensaje.setText("Si es asi, especifique donde:");
        jPgestionar.add(jLBmensaje, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 210, -1, -1));

        jBtnEditar.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        jBtnEditar.setForeground(new java.awt.Color(0, 153, 153));
        jBtnEditar.setText("Editar");
        jBtnEditar.setEnabled(false);
        jBtnEditar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnEditarActionPerformed(evt);
            }
        });
        jPgestionar.add(jBtnEditar, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 370, 170, 40));

        jBtnGuardar.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        jBtnGuardar.setForeground(new java.awt.Color(0, 153, 153));
        jBtnGuardar.setText("Guardar");
        jBtnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnGuardarActionPerformed(evt);
            }
        });
        jPgestionar.add(jBtnGuardar, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 370, 260, 40));

        jBtnLimpiar.setForeground(new java.awt.Color(51, 51, 51));
        jBtnLimpiar.setText("Limpiar");
        jBtnLimpiar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnLimpiarActionPerformed(evt);
            }
        });
        jPgestionar.add(jBtnLimpiar, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 370, 110, 40));
        jPgestionar.add(jLimagenGrande, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 10, 370, 400));

        try {
            jTFnumIdentidad.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("####-####-#####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        jTFnumIdentidad.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTFnumIdentidadActionPerformed(evt);
            }
        });
        jTFnumIdentidad.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTFnumIdentidadKeyPressed(evt);
            }
        });
        jPgestionar.add(jTFnumIdentidad, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 30, 230, 30));

        try {
            jTFnumCelular.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("####-####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        jTFnumCelular.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTFnumCelularKeyPressed(evt);
            }
        });
        jPgestionar.add(jTFnumCelular, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 120, 190, 30));

        jDCFechaNacimiento.setDateFormatString("dd/MM/yyyy");
        jPgestionar.add(jDCFechaNacimiento, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 110, 200, -1));

        jTabbedPane1.addTab("Gestionar", jPgestionar);

        jPMostrarPacientes.setBackground(new java.awt.Color(255, 255, 255));

        jTBPacientes.setBackground(new java.awt.Color(204, 255, 204));
        jTBPacientes.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jTBPacientes.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Identidad", "Nombres", "Apellidos", "Num Celular", "Fecha de Nacimiento", "Lugar Trabajo", "Direccion", "Sexo", "Profesion"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, true, true, true, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTBPacientes.setComponentPopupMenu(jPMOpciones);
        jTBPacientes.setShowGrid(true);
        jScrollPane2.setViewportView(jTBPacientes);

        javax.swing.GroupLayout jPMostrarPacientesLayout = new javax.swing.GroupLayout(jPMostrarPacientes);
        jPMostrarPacientes.setLayout(jPMostrarPacientesLayout);
        jPMostrarPacientesLayout.setHorizontalGroup(
            jPMostrarPacientesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPMostrarPacientesLayout.createSequentialGroup()
                .addContainerGap(16, Short.MAX_VALUE)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 997, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPMostrarPacientesLayout.setVerticalGroup(
            jPMostrarPacientesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPMostrarPacientesLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 410, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(88, 88, 88))
        );

        jTabbedPane1.addTab("Historal de Registros", jPMostrarPacientes);

        getContentPane().add(jTabbedPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 80, 1040, 470));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jBTNSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBTNSalirActionPerformed
        this.dispose();
    }//GEN-LAST:event_jBTNSalirActionPerformed

    private void jCBtrabajaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCBtrabajaActionPerformed
        activarCampoLugarTrabajo();
    }//GEN-LAST:event_jCBtrabajaActionPerformed

    private void jBtnEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnEditarActionPerformed
        try {
            editar();
        } catch (SQLException ex) {
            Logger.getLogger(JFFPaciente.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jBtnEditarActionPerformed

    private void jBtnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnGuardarActionPerformed
        try {
            guardar();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,"No se registro el paciente");
        }
    }//GEN-LAST:event_jBtnGuardarActionPerformed

    private void jBtnLimpiarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnLimpiarActionPerformed
        limpiarCampos();
    }//GEN-LAST:event_jBtnLimpiarActionPerformed

    private void jTFnumIdentidadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTFnumIdentidadActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTFnumIdentidadActionPerformed

    private void jMIEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMIEditarActionPerformed
        try {
            //llama al metodo siguiente para eliminar cualquier registro seleccionado con el click derecho en la jtable
            this.seleccionarFila();
        } catch (SQLException ex) {
            Logger.getLogger(JFFPaciente.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParseException ex) {
            Logger.getLogger(JFFPaciente.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jMIEditarActionPerformed

    private void jMiEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMiEliminarActionPerformed
        try {
            eliminar();
        } catch (SQLException ex) {
            Logger.getLogger(JFFPaciente.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jMiEliminarActionPerformed

    private void jTFnombresKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTFnombresKeyPressed
        if(jTFnombres.getText().length() >=  0 ){ jTFnombres.setBorder(this.border); }
    }//GEN-LAST:event_jTFnombresKeyPressed

    private void jTFnumCelularKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTFnumCelularKeyPressed
        if(jTFnumCelular.getText().length() >=  0 ){ jTFnumCelular.setBorder(this.border); }
    }//GEN-LAST:event_jTFnumCelularKeyPressed

    private void jTFapellidosKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTFapellidosKeyPressed
        if(jTFapellidos.getText().length() >=  0 ){ jTFapellidos.setBorder(this.border); }
    }//GEN-LAST:event_jTFapellidosKeyPressed

    private void jTFlugarTrabajoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTFlugarTrabajoKeyPressed
        if(jTFlugarTrabajo.getText().length() >=  0 ){ jTFlugarTrabajo.setBorder(this.border); }
    }//GEN-LAST:event_jTFlugarTrabajoKeyPressed

    private void jTFnumIdentidadKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTFnumIdentidadKeyPressed
        if(jTFnumIdentidad.getText().length() >=  0 ){ jTFnumIdentidad.setBorder(this.border); }
    }//GEN-LAST:event_jTFnumIdentidadKeyPressed

    private void jTAdireccionKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTAdireccionKeyPressed
        if(jTAdireccion.getText().length() >=  0 ){ jTAdireccion.setBorder(this.border); }
    }//GEN-LAST:event_jTAdireccionKeyPressed

    private void jCboSexoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCboSexoActionPerformed
        if(jCboSexo.getSelectedIndex() >  0 ){ jCboSexo.setBorder(this.border); }
    }//GEN-LAST:event_jCboSexoActionPerformed

    private void jCboProfesionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCboProfesionActionPerformed
        if(jCboProfesion.getSelectedIndex() >  0 ){ jCboProfesion.setBorder(this.border); }
    }//GEN-LAST:event_jCboProfesionActionPerformed

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
                    new JFFPaciente().setVisible(true);
                } catch (SQLException ex) {
                    Logger.getLogger(JFFPaciente.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jBTNSalir;
    private javax.swing.JButton jBtnEditar;
    private javax.swing.JButton jBtnGuardar;
    private javax.swing.JButton jBtnLimpiar;
    private javax.swing.JCheckBox jCBtrabaja;
    private javax.swing.JComboBox<String> jCboProfesion;
    private javax.swing.JComboBox<String> jCboSexo;
    private com.toedter.calendar.JDateChooser jDCFechaNacimiento;
    private javax.swing.JLabel jLBiconoNombre;
    private javax.swing.JLabel jLBmensaje;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel jLimagenGrande;
    private javax.swing.JMenuItem jMIEditar;
    private javax.swing.JMenuItem jMiEliminar;
    private javax.swing.JPanel jPFranjaInferior;
    private javax.swing.JPopupMenu jPMOpciones;
    private javax.swing.JPanel jPMostrarPacientes;
    private javax.swing.JPanel jPTitulo;
    private javax.swing.JPanel jPfranjaSuperior;
    private javax.swing.JPanel jPgestionar;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextArea jTAdireccion;
    private javax.swing.JTable jTBPacientes;
    private javax.swing.JTextField jTFapellidos;
    private javax.swing.JTextField jTFlugarTrabajo;
    private javax.swing.JTextField jTFnombres;
    private javax.swing.JFormattedTextField jTFnumCelular;
    private javax.swing.JFormattedTextField jTFnumIdentidad;
    private javax.swing.JTabbedPane jTabbedPane1;
    // End of variables declaration//GEN-END:variables
}
