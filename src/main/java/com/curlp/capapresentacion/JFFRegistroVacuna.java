/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.curlp.capapresentacion;

import com.curlp.capapresentacion.*;
import com.curlp.capadatos.CDDosis;
import com.curlp.capadatos.CDRegistroVacuna;
import com.curlp.capalogica.CLRegistroVacuna;
import java.sql.SQLException;
import java.text.ParseException;
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
public class JFFRegistroVacuna extends javax.swing.JFrame {

    /**
     * Creates new form JFFRegistroVacuna
     */
    public JFFRegistroVacuna() throws SQLException {
        initComponents();
        llenarComboBoxDosis();
    }
    

    public void llenarComboBoxDosis() throws SQLException {
        CDDosis dosis = new CDDosis();
        List<String> lista = dosis.cargarDosis();

        this.jCboDosis.removeAllItems();

        for (String x : lista) {
            this.jCboDosis.addItem(x);
        }
    }

    public void abrirVentanaSelectorPaciente() throws SQLException, ParseException {
        JFFVisorDePaciente visorPaciente = new JFFVisorDePaciente(this);
        visorPaciente.setVisible(true);
    }

    public void abrirVentanaSelectorVacunador() throws SQLException, ParseException {
        JFFVisorDeVacunador visorVacunador = new JFFVisorDeVacunador(this);
        visorVacunador.setVisible(true);
    }

    public void abrirVentanaSelectorLote() throws SQLException, ParseException {
        JFFVisorDeLote visorLote = new JFFVisorDeLote(this);
        visorLote.setVisible(true);
    }

    public void abrirVentanaSelectorEstablecimiento() throws SQLException, ParseException {
        JFFVisorDeEstablecimiento visorEst = new JFFVisorDeEstablecimiento(this);
        visorEst.setVisible(true);
    }

    public void llenarDatosPaciente(String numIdentidad, String nombre, String apellido, String fechaNacimiento) {
        jTFNumIdentidadPaciente.setText(numIdentidad);
        jTFNombrePaciente.setText(nombre);
        jTFApellidosPaciente.setText(apellido);
        jTFFechaNacimientoPaciente.setText(fechaNacimiento);
    }

    public void llenarDatosVacunador(String numIdentidad, String nombre, String apellido) {
        JTFNumIdentidadVacunador.setText(numIdentidad);
        jTFNombreVacunador.setText(nombre);
        jTFApellidoVacunador.setText(apellido);
    }

    public void llenarDatosLote(String numLote, String fechaVencimiento, String marca) {
        jTFNumLote.setText(numLote);
        jTFechaVencimiento.setText(fechaVencimiento);
        jTFmarcaVacuna.setText(marca);
    }

    public void llenarDatosEstablecimiento(String codigo, String nombre) {
        jTFCodigoEstablecimiento.setText(codigo);
        jTFNombreEstablecimiento.setText(nombre);
    }

    private boolean validarTextField() {
        boolean estado;
        estado = this.jTFNumerodeRegistro.getText().equals("");
        return estado;
    }
    // Métodos para limpiar textFiled
    private void limpiarTextField() {
        this.jTFNumerodeRegistro.setText("");
        this.jTFNumIdentidadPaciente.setText("");
        this.jTFNombrePaciente.setText("");
        this.jTFApellidosPaciente.setText("");
        this.jTFCodigoEstablecimiento.setText("");
        this.jTFNombreEstablecimiento.setText("");
        this.jTFFechaNacimientoPaciente.setText("");
        this.jDCFechaVacunacion.setCalendar(null);
        this.jTFNumLote.setText("");
        this.jTFechaVencimiento.setText("");
        this.jTFmarcaVacuna.setText("");
        this.jCboDosis.setSelectedIndex(0);
        this.JTFNumIdentidadVacunador.setText("");
        this.jTFNombreVacunador.setText("");
        this.jTFApellidoVacunador.setText("");

        this.jTFNumerodeRegistro.requestFocus();
    }
    // Metodo de insertar un  RegistroVacuna
    private void insertarLoteVacuna() {
        if (!validarTextField()) {
            // recuperar datos de los Combo Box
            try {
                int dosis = this.jCboDosis.getSelectedIndex();
                java.sql.Date fechaVacunacion = new java.sql.Date(this.jDCFechaVacunacion.getDate().getTime());
                CDRegistroVacuna cdrv = new CDRegistroVacuna();
                CLRegistroVacuna cl = new CLRegistroVacuna();
                cl.setIdRegistroVacuna(Integer.parseInt(this.jTFNumerodeRegistro.getText().trim()));
                cl.setFechaVacunacion(fechaVacunacion);
                cl.setDniVacunador(this.JTFNumIdentidadVacunador.getText().trim());
                cl.setCodEstablecimiento(Integer.parseInt(this.jTFCodigoEstablecimiento.getText().trim()));
                cl.setNumLoteVacuna(this.jTFNumLote.getText().trim().toUpperCase());
                cl.setDniPaciente(this.jTFNumIdentidadPaciente.getText().trim().toUpperCase());
                cl.setDosis(dosis);

                cdrv.insertarRegistroVacuna(cl);
                JOptionPane.showMessageDialog(null, "¡Registrado correctamente!", "COVA System", JOptionPane.INFORMATION_MESSAGE);
                this.jTFNumerodeRegistro.requestFocus();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Error al almacenar el nuevo lote" + e);
                this.jTFNumerodeRegistro.requestFocus();
            }

        } else {
            JOptionPane.showMessageDialog(null, "Tiene que ingresar los datos del lote de vacunas", "COVA System", JOptionPane.INFORMATION_MESSAGE);
            this.jTFNumerodeRegistro.requestFocus();
        }
    }

    //Metodo para llamar el metodo para insertar  RegistroVacuna
    private void insertar() throws SQLException {
        insertarLoteVacuna();
       // poblarTablaLoteVacuna();
       // habilitarBotones(true, false, false, true);
        limpiarTextField();
    }
     // Metodo para llamar el metodo para actualizar un lote
    private void actualizarLoteVacuna() {
        if (!validarTextField()) {
            try {
                int dosis = this.jCboDosis.getSelectedIndex();
                java.sql.Date fechaVacunacion = new java.sql.Date(this.jDCFechaVacunacion.getDate().getTime());
                CDRegistroVacuna cdrv = new CDRegistroVacuna();
                CLRegistroVacuna cl = new CLRegistroVacuna();
                 cl.setIdRegistroVacuna(Integer.parseInt(this.jTFNumerodeRegistro.getText().trim()));
                cl.setFechaVacunacion(fechaVacunacion);
                cl.setDniVacunador(this.JTFNumIdentidadVacunador.getText().trim());
                cl.setCodEstablecimiento(Integer.parseInt(this.jTFCodigoEstablecimiento.getText().trim()));
                cl.setNumLoteVacuna(this.jTFNumLote.getText().trim().toUpperCase());
                cl.setDniPaciente(this.jTFNumIdentidadPaciente.getText().trim().toUpperCase());
                cl.setDosis(dosis);
                cdrv.actualizarRegistroVacuna(cl);
                JOptionPane.showMessageDialog(null, "¡Registrado actualizado!", "COVA System", JOptionPane.INFORMATION_MESSAGE);
                this.jTFNumerodeRegistro.requestFocus();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Error al modificar" + e);
                this.jTFNumerodeRegistro.requestFocus();
            }
        } else {
            JOptionPane.showMessageDialog(null, "Ingresar los nuevos datos del lote", "COVA System", JOptionPane.INFORMATION_MESSAGE);
            this.jTFNumerodeRegistro.requestFocus();
        }
    }



    // Metodo actualizar el registro loteVacuna
    private void editar() throws SQLException {
        actualizarLoteVacuna();
               limpiarTextField();
    }

    // Metodo para eliminar lote
    private void eliminarLoteVacuna() {
        if (!validarTextField()) {
            try {
                CDRegistroVacuna cdrv = new CDRegistroVacuna();
                CLRegistroVacuna cl = new CLRegistroVacuna();
                cl.setIdRegistroVacuna(Integer.parseInt(this.jTFNumerodeRegistro.getText().trim()));
                cdrv.eliminarRegistroVacuna(cl);
                JOptionPane.showMessageDialog(null, "¡Registrado eliminado!", "COVA System", JOptionPane.INFORMATION_MESSAGE);
                this.jTFNumerodeRegistro.requestFocus();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Error al eliminar" + e);
                this.jTFNumerodeRegistro.requestFocus();
            }
        } else {
            JOptionPane.showMessageDialog(null, "Ingresar el número de lote", "COVA System", JOptionPane.INFORMATION_MESSAGE);
            this.jTFNumerodeRegistro.requestFocus();
        }
    }

    // Metodo llamar eliminar 
    private void eliminar() throws SQLException {
        int resp = JOptionPane.showConfirmDialog(null, "¿Seguro que desea eliminar este lote?", "COVA System", JOptionPane.YES_NO_OPTION);
        if (resp == JOptionPane.YES_OPTION) {
            eliminarLoteVacuna();
            limpiarTextField();
        } else {
            limpiarTextField();
        }
    }

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
       //this.jBtnLimpiar.setIcon(iconLimpiar);
        this.jBtnSalir.setIcon(iconobtn);
        this.jLblUser.setIcon(iconUser);
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
        jPfranjaSuperior = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jBtnSalir = new javax.swing.JButton();
        jLblUser = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jBtnBuscarLote = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        jTFechaVencimiento = new javax.swing.JTextField();
        jTFNumLote = new javax.swing.JTextField();
        jDCFechaVacunacion = new com.toedter.calendar.JDateChooser();
        jLabel10 = new javax.swing.JLabel();
        jTFmarcaVacuna = new javax.swing.JTextField();
        jCboDosis = new javax.swing.JComboBox<>();
        jLabel3 = new javax.swing.JLabel();
        jTFNumerodeRegistro = new javax.swing.JTextField();
        jPanel3 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jBtnBuscarEstablecimiento = new javax.swing.JButton();
        jTFNombreEstablecimiento = new javax.swing.JTextField();
        jTFCodigoEstablecimiento = new javax.swing.JTextField();
        jPanel4 = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jBtnBuscarVacunador = new javax.swing.JButton();
        jLabel15 = new javax.swing.JLabel();
        jTFApellidoVacunador = new javax.swing.JTextField();
        jTFNombreVacunador = new javax.swing.JTextField();
        JTFNumIdentidadVacunador = new javax.swing.JTextField();
        jPanel5 = new javax.swing.JPanel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jBtnBuscarPaciente = new javax.swing.JButton();
        jLabel19 = new javax.swing.JLabel();
        jTFFechaNacimientoPaciente = new javax.swing.JTextField();
        jTFApellidosPaciente = new javax.swing.JTextField();
        jTFNombrePaciente = new javax.swing.JTextField();
        jTFNumIdentidadPaciente = new javax.swing.JTextField();
        jPfranjaSuperior1 = new javax.swing.JPanel();
        jBtnGuardar = new javax.swing.JButton();
        jBtnEditar = new javax.swing.JButton();
        jBtnEliminar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPfranjaSuperior.setBackground(new java.awt.Color(0, 153, 153));
        jPfranjaSuperior.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        jPanel1.add(jPfranjaSuperior, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 60, 740, 10));

        jLabel1.setBackground(new java.awt.Color(0, 153, 153));
        jLabel1.setFont(new java.awt.Font("Tahoma", 3, 48)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 153, 153));
        jLabel1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jLabel1MousePressed(evt);
            }
        });
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 10, 50, 50));

        jLabel2.setBackground(new java.awt.Color(0, 153, 153));
        jLabel2.setFont(new java.awt.Font("Tahoma", 3, 24)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 153, 153));
        jLabel2.setText("Registro de Vacunación");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 10, 350, 30));

        jBtnSalir.setBackground(new java.awt.Color(255, 255, 255));
        jBtnSalir.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jBtnSalir.setText("Salir");
        jBtnSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnSalirActionPerformed(evt);
            }
        });
        jPanel1.add(jBtnSalir, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 10, -1, -1));
        jPanel1.add(jLblUser, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 14, 40, 40));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 740, -1));

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 102, 102)), "Datos de Vacunación", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 14))); // NOI18N
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel4.setText("# de Lote Vacuna");
        jPanel2.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 20, -1, -1));

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel5.setText("Fecha de Vencimiento");
        jPanel2.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 20, -1, -1));

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel6.setText("# de Dosis");
        jPanel2.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 80, -1, -1));

        jBtnBuscarLote.setBackground(new java.awt.Color(255, 255, 255));
        jBtnBuscarLote.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jBtnBuscarLote.setForeground(new java.awt.Color(0, 153, 153));
        jBtnBuscarLote.setText("Buscar");
        jBtnBuscarLote.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnBuscarLoteActionPerformed(evt);
            }
        });
        jPanel2.add(jBtnBuscarLote, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 100, 110, 30));

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel7.setText("Fecha de Aplicación");
        jPanel2.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 20, -1, -1));

        jTFechaVencimiento.setEditable(false);
        jPanel2.add(jTFechaVencimiento, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 40, 210, 30));

        jTFNumLote.setEditable(false);
        jPanel2.add(jTFNumLote, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 40, 200, 30));

        jDCFechaVacunacion.setDateFormatString("dd/MM/yyyy");
        jPanel2.add(jDCFechaVacunacion, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 40, 180, -1));

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel10.setText("Marca Vacuna");
        jPanel2.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 80, -1, -1));

        jTFmarcaVacuna.setEditable(false);
        jPanel2.add(jTFmarcaVacuna, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 100, 170, 30));

        jCboDosis.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jPanel2.add(jCboDosis, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 100, -1, -1));

        getContentPane().add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 390, 710, 140));

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel3.setText("Número de Registro:");
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 90, -1, -1));
        getContentPane().add(jTFNumerodeRegistro, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 80, 157, 30));

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 102, 102)), "Datos del Establecimiento", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 14))); // NOI18N
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel8.setText("Código  Establecimiento:");
        jPanel3.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 20, -1, -1));

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel9.setText("Nombre Establecimiento");
        jPanel3.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 20, -1, -1));

        jBtnBuscarEstablecimiento.setBackground(new java.awt.Color(255, 255, 255));
        jBtnBuscarEstablecimiento.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jBtnBuscarEstablecimiento.setForeground(new java.awt.Color(0, 153, 153));
        jBtnBuscarEstablecimiento.setText("Buscar");
        jBtnBuscarEstablecimiento.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnBuscarEstablecimientoActionPerformed(evt);
            }
        });
        jPanel3.add(jBtnBuscarEstablecimiento, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 70, 110, 30));

        jTFNombreEstablecimiento.setEditable(false);
        jPanel3.add(jTFNombreEstablecimiento, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 40, 200, 30));

        jTFCodigoEstablecimiento.setEditable(false);
        jPanel3.add(jTFCodigoEstablecimiento, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 40, 157, 30));

        getContentPane().add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 270, 710, 110));

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));
        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 102, 102)), "Datos del Vacunador", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 14))); // NOI18N
        jPanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel12.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel12.setText("Nombres:");
        jPanel4.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 20, -1, -1));

        jLabel13.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel13.setText("Apellidos");
        jPanel4.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 20, -1, -1));

        jBtnBuscarVacunador.setBackground(new java.awt.Color(255, 255, 255));
        jBtnBuscarVacunador.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jBtnBuscarVacunador.setForeground(new java.awt.Color(0, 153, 153));
        jBtnBuscarVacunador.setText("Buscar");
        jBtnBuscarVacunador.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnBuscarVacunadorActionPerformed(evt);
            }
        });
        jPanel4.add(jBtnBuscarVacunador, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 100, 110, 30));

        jLabel15.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel15.setText("Número de Identidad:");
        jPanel4.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 20, -1, -1));

        jTFApellidoVacunador.setEditable(false);
        jPanel4.add(jTFApellidoVacunador, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 40, 210, 30));

        jTFNombreVacunador.setEditable(false);
        jPanel4.add(jTFNombreVacunador, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 40, 200, 30));

        JTFNumIdentidadVacunador.setEditable(false);
        jPanel4.add(JTFNumIdentidadVacunador, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 40, 157, 30));

        getContentPane().add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 540, 710, 140));

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));
        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 102, 102)), "Datos del Paciente", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 14))); // NOI18N
        jPanel5.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel16.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel16.setText("Nombres:");
        jPanel5.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 20, -1, -1));

        jLabel17.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel17.setText("Apellidos");
        jPanel5.add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 20, -1, -1));

        jLabel18.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel18.setText("Fecha de Nacimiento");
        jPanel5.add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 80, -1, -1));

        jBtnBuscarPaciente.setBackground(new java.awt.Color(255, 255, 255));
        jBtnBuscarPaciente.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jBtnBuscarPaciente.setForeground(new java.awt.Color(0, 153, 153));
        jBtnBuscarPaciente.setText("Buscar");
        jBtnBuscarPaciente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnBuscarPacienteActionPerformed(evt);
            }
        });
        jPanel5.add(jBtnBuscarPaciente, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 100, 110, 30));

        jLabel19.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel19.setText("Número de Identidad:");
        jPanel5.add(jLabel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 20, -1, -1));

        jTFFechaNacimientoPaciente.setEditable(false);
        jPanel5.add(jTFFechaNacimientoPaciente, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 100, 170, 30));

        jTFApellidosPaciente.setEditable(false);
        jPanel5.add(jTFApellidosPaciente, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 40, 210, 30));

        jTFNombrePaciente.setEditable(false);
        jPanel5.add(jTFNombrePaciente, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 40, 200, 30));

        jTFNumIdentidadPaciente.setEditable(false);
        jPanel5.add(jTFNumIdentidadPaciente, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 40, 157, 30));

        getContentPane().add(jPanel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 120, 710, 140));

        jPfranjaSuperior1.setBackground(new java.awt.Color(0, 153, 153));
        jPfranjaSuperior1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        getContentPane().add(jPfranjaSuperior1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 770, 740, 20));

        jBtnGuardar.setBackground(new java.awt.Color(255, 255, 255));
        jBtnGuardar.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jBtnGuardar.setForeground(new java.awt.Color(0, 153, 153));
        jBtnGuardar.setText("Guardar");
        jBtnGuardar.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jBtnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnGuardarActionPerformed(evt);
            }
        });
        getContentPane().add(jBtnGuardar, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 80, 110, 30));

        jBtnEditar.setBackground(new java.awt.Color(255, 255, 255));
        jBtnEditar.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jBtnEditar.setForeground(new java.awt.Color(0, 153, 153));
        jBtnEditar.setText("Editar");
        jBtnEditar.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jBtnEditar.setEnabled(false);
        jBtnEditar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnEditarActionPerformed(evt);
            }
        });
        getContentPane().add(jBtnEditar, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 81, 110, 30));

        jBtnEliminar.setBackground(new java.awt.Color(255, 255, 255));
        jBtnEliminar.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jBtnEliminar.setForeground(new java.awt.Color(0, 153, 153));
        jBtnEliminar.setText("Eliminar");
        jBtnEliminar.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jBtnEliminar.setEnabled(false);
        jBtnEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnEliminarActionPerformed(evt);
            }
        });
        getContentPane().add(jBtnEliminar, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 80, 110, 30));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jBtnBuscarLoteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnBuscarLoteActionPerformed
        try {
            this.abrirVentanaSelectorLote();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al buscar lotes", "COVA System", JOptionPane.INFORMATION_MESSAGE);
        } catch (ParseException ex) {
            JOptionPane.showMessageDialog(null, "Error al buscar lotes", "COVA System", JOptionPane.INFORMATION_MESSAGE);
        }
    }//GEN-LAST:event_jBtnBuscarLoteActionPerformed

    private void jBtnBuscarEstablecimientoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnBuscarEstablecimientoActionPerformed
        try {
            this.abrirVentanaSelectorEstablecimiento();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al buscar establecimientos", "COVA System", JOptionPane.INFORMATION_MESSAGE);
        } catch (ParseException ex) {
            JOptionPane.showMessageDialog(null, "Error al buscar establecimientos", "COVA System", JOptionPane.INFORMATION_MESSAGE);
        }
    }//GEN-LAST:event_jBtnBuscarEstablecimientoActionPerformed

    private void jBtnBuscarVacunadorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnBuscarVacunadorActionPerformed
        try {
            this.abrirVentanaSelectorVacunador();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al buscar vacunador", "COVA System", JOptionPane.INFORMATION_MESSAGE);
        } catch (ParseException ex) {
            JOptionPane.showMessageDialog(null, "Error al buscar vacunador", "COVA System", JOptionPane.INFORMATION_MESSAGE);
        }
    }//GEN-LAST:event_jBtnBuscarVacunadorActionPerformed

    private void jBtnBuscarPacienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnBuscarPacienteActionPerformed
        try {
            abrirVentanaSelectorPaciente();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al buscar un paciente", "COVA System", JOptionPane.INFORMATION_MESSAGE);
        } catch (ParseException ex) {
            JOptionPane.showMessageDialog(null, "Error al buscar un paciente", "COVA System", JOptionPane.INFORMATION_MESSAGE);
        }
    }//GEN-LAST:event_jBtnBuscarPacienteActionPerformed

    private void jBtnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnGuardarActionPerformed
        try {
            insertar();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al almacenar" + e);
        }
    }//GEN-LAST:event_jBtnGuardarActionPerformed

    private void jBtnEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnEditarActionPerformed
        try {
            editar();
        } catch (SQLException ex) {
            Logger.getLogger(null, "Error al actualizar" + ex);
        }
    }//GEN-LAST:event_jBtnEditarActionPerformed

    private void jBtnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnEliminarActionPerformed
        try {
            eliminar();
        } catch (SQLException ex) {
            Logger.getLogger(null, "Error al ELIMINAR" + ex);
        }
    }//GEN-LAST:event_jBtnEliminarActionPerformed

    private void jBtnSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnSalirActionPerformed
        this.dispose();
    }//GEN-LAST:event_jBtnSalirActionPerformed

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
            java.util.logging.Logger.getLogger(JFFRegistroVacuna.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(JFFRegistroVacuna.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(JFFRegistroVacuna.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(JFFRegistroVacuna.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new JFFRegistroVacuna().setVisible(true);
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(null, "Error al iniciar", "COVA System", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField JTFNumIdentidadVacunador;
    private javax.swing.JButton jBtnBuscarEstablecimiento;
    private javax.swing.JButton jBtnBuscarLote;
    private javax.swing.JButton jBtnBuscarPaciente;
    private javax.swing.JButton jBtnBuscarVacunador;
    private javax.swing.JButton jBtnEditar;
    private javax.swing.JButton jBtnEliminar;
    private javax.swing.JButton jBtnGuardar;
    private javax.swing.JButton jBtnSalir;
    private javax.swing.JComboBox<String> jCboDosis;
    private com.toedter.calendar.JDateChooser jDCFechaVacunacion;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel jLblUser;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPfranjaSuperior;
    private javax.swing.JPanel jPfranjaSuperior1;
    private javax.swing.JTextField jTFApellidoVacunador;
    private javax.swing.JTextField jTFApellidosPaciente;
    private javax.swing.JTextField jTFCodigoEstablecimiento;
    private javax.swing.JTextField jTFFechaNacimientoPaciente;
    private javax.swing.JTextField jTFNombreEstablecimiento;
    private javax.swing.JTextField jTFNombrePaciente;
    private javax.swing.JTextField jTFNombreVacunador;
    private javax.swing.JTextField jTFNumIdentidadPaciente;
    private javax.swing.JTextField jTFNumLote;
    private javax.swing.JTextField jTFNumerodeRegistro;
    private javax.swing.JTextField jTFechaVencimiento;
    private javax.swing.JTextField jTFmarcaVacuna;
    // End of variables declaration//GEN-END:variables
}
