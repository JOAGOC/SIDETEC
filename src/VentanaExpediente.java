import static javax.swing.JOptionPane.showMessageDialog;
import static javax.swing.JOptionPane.showConfirmDialog;
import Conexión.Conexión;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class VentanaExpediente extends javax.swing.JFrame {

    private List<ExpedienteClínico> expedientes;
    private ExpedienteClínico expedienteSel;
    private Paciente paciente;

    public VentanaExpediente() {
        iniciar();
    }

    public VentanaExpediente(Paciente paciente, String fecha, String tags) {
        iniciar();
        this.paciente = paciente;
        txtPaciente.setText(paciente.getNombre() + " " + paciente.getApellido());

        expedientes = ExpedienteClínico.consultar(paciente.getId());
        expedientes.forEach((expediente) -> cmbExpedientes
                .addItem(expediente.getFecha().toString() + " - " + expediente.getFolio()));
        for (ExpedienteClínico expedienteClínico : expedientes) {
            if (expedienteClínico.getFecha().toString().equals(fecha)) {
                expedienteSel = expedienteClínico;
                break;
            }
        }

        cmbExpedientes.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (cmbExpedientes.getSelectedIndex() == 0
                        || expedientes.get(cmbExpedientes.getSelectedIndex() - 1).equals(expedienteSel))
                    return;
                expedienteSel = expedientes.get(cmbExpedientes.getSelectedIndex() - 1);
                tagsEnfermedad.clear();
                tagsMotivo.clear();
                cargarExpediente();
            }
        });

        if (expedienteSel == null) {
            tagsMotivo.setTags(tags);
            try {
                dtFecha1.setDate(new SimpleDateFormat("yyyy-MM-dd").parse(fecha));
            } catch (ParseException e) {
                e.printStackTrace();
            }
            return;
        }

        cmbExpedientes.setSelectedItem(expedienteSel.getFecha().toString() + " - " + expedienteSel.getFolio());
        cargarExpediente();
    }

    private void cargarExpediente() {
        txtFolio.setText(expedienteSel.getFolio() + "");
        tagsMotivo.setTags(expedienteSel.getMotivo());
        tagsEnfermedad.setTags(expedienteSel.getEnfermedad());
        txtObservaciones.setText(expedienteSel.getObservaciones());
        txtTratamiento.setText(expedienteSel.getTratamiento());
        dtFecha1.setDate(expedienteSel.getFecha());
    }

    private void iniciar() {
        initComponents();
        setLocationRelativeTo(null);
        txtTratamiento.setLineWrap(true);
        txtTratamiento.setWrapStyleWord(true);
        txtObservaciones.setLineWrap(true);
        txtObservaciones.setWrapStyleWord(true);
    }

    // <editor-fold defaultstate="collapsed" desc="Generated
    // <editor-fold defaultstate="collapsed" desc="Generated
    // <editor-fold defaultstate="collapsed" desc="Generated
    // Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        autoCompleteTextField2 = new AutoCompleteTextField();
        autoCompleteTextField1 = new AutoCompleteTextField();
        autoCompleteTextField3 = new AutoCompleteTextField();
        autoCompleteTextField4 = new AutoCompleteTextField();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        txtFolio = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        dtFecha1 = new com.toedter.calendar.JDateChooser();
        jLabel4 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        tagsMotivo = new TagInputControl();
        tagsEnfermedad = new TagInputControl();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtTratamiento = new javax.swing.JTextArea();
        cmbExpedientes = new javax.swing.JComboBox<>();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        txtPaciente = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtObservaciones = new javax.swing.JTextArea();
        btnEliminar = new JImageBox();
        btnAgregar1 = new JImageBox();
        btnModificar1 = new JImageBox();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jImageBox2 = new JImageBox();

        autoCompleteTextField2.setItems(new String[] { "Amalgama", "Blanqueamiento dental", "Caries dental",
                "Dolor de muelas", "Diente astillado", "Extracción dental", "Frenillos dentales", "Inflamación dental",
                "Implante dental", "Limpieza dental", "Ortodoncia", "Problemas de encías ", "Problemas de mordida",
                "Prótesis dental ", "Resina", "Revisión de rutina", "Sensibilidad dental" });

        autoCompleteTextField1.setItems(new String[] { "Bruxismo", "Caries dental", "Cálculos dentales",
                "Diente impactado", "Diente astillado o roto", "Gingivitis", "Halitosis (mal aliento)",
                "Infección dental", "Lesiones de la mucosa oral", "Lesiones de la lengua o labios", "Maloclusión",
                "Periodontitis", "Placa dental", "Quistes dentales", "Sensibilidad dental" });

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel2.setBackground(new java.awt.Color(0, 108, 183));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Expediente Clínico");
        jPanel2.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 9, -1, 40));

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1150, 60));

        jPanel3.setBackground(new java.awt.Color(69, 204, 209));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        txtFolio.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jPanel3.add(txtFolio, new org.netbeans.lib.awtextra.AbsoluteConstraints(106, 20, 85, -1));

        jLabel10.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel10.setText("Folio:");
        jPanel3.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 18, -1, -1));
        jPanel3.add(dtFecha1, new org.netbeans.lib.awtextra.AbsoluteConstraints(106, 87, 260, 28));

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel4.setText("Fecha:");
        jPanel3.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 87, 70, -1));

        jLabel9.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel9.setText("Observaciones:");
        jPanel3.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(730, 150, 142, -1));

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel5.setText("Motivo de la consulta:");
        jPanel3.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 150, 186, -1));

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel6.setText("Enfermedad actual:");
        jPanel3.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 150, 170, -1));

        tagsMotivo.setButtonColor(new java.awt.Color(0, 108, 183));
        tagsMotivo.setButtonTextColor(new java.awt.Color(255, 255, 255));
        tagsMotivo.setTagInputField(autoCompleteTextField2);
        jPanel3.add(tagsMotivo, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 183, 273, 212));

        tagsEnfermedad.setButtonColor(new java.awt.Color(0, 108, 183));
        tagsEnfermedad.setButtonTextColor(new java.awt.Color(255, 255, 255));
        tagsEnfermedad.setTagInputField(autoCompleteTextField1);
        jPanel3.add(tagsEnfermedad, new org.netbeans.lib.awtextra.AbsoluteConstraints(315, 183, 285, 212));

        txtTratamiento.setColumns(20);
        txtTratamiento.setRows(5);
        jScrollPane1.setViewportView(txtTratamiento);

        jPanel3.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 309, 347, -1));

        cmbExpedientes.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        cmbExpedientes.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Selecciona un expediente" }));
        jPanel3.add(cmbExpedientes, new org.netbeans.lib.awtextra.AbsoluteConstraints(867, 20, 220, 30));

        jLabel11.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel11.setText("Expedientes");
        jPanel3.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(760, 20, -1, 30));

        jLabel12.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel12.setText("Paciente:");
        jPanel3.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 50, -1, -1));

        txtPaciente.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jPanel3.add(txtPaciente, new org.netbeans.lib.awtextra.AbsoluteConstraints(106, 48, 260, 30));

        jLabel13.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel13.setText("Tratamiento:");
        jPanel3.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(750, 280, 110, -1));

        txtObservaciones.setColumns(20);
        txtObservaciones.setRows(5);
        jScrollPane2.setViewportView(txtObservaciones);

        jPanel3.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 180, 353, -1));

        btnEliminar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/icons8-eliminar-60.png"))); // NOI18N
        btnEliminar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnEliminarMouseClicked(evt);
            }
        });
        jPanel3.add(btnEliminar, new org.netbeans.lib.awtextra.AbsoluteConstraints(1010, 330, -1, 75));

        btnAgregar1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/icons8-agregar-carpeta-64.png"))); // NOI18N
        btnAgregar1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnAgregar1MouseClicked(evt);
            }
        });
        jPanel3.add(btnAgregar1, new org.netbeans.lib.awtextra.AbsoluteConstraints(1010, 180, 60, -1));

        btnModificar1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/icons8-editar-archivo-50.png"))); // NOI18N
        btnModificar1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnModificar1MouseClicked(evt);
            }
        });
        jPanel3.add(btnModificar1, new org.netbeans.lib.awtextra.AbsoluteConstraints(1010, 260, 60, -1));

        jLabel2.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        jPanel3.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 140, 470, 270));

        jLabel3.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        jPanel3.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 140, 590, 270));

        jImageBox2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/L1.png"))); // NOI18N
        jPanel3.add(jImageBox2, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 10, 110, -1));

        jPanel1.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 80, 1100, 430));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1150, 530));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnModificar1MouseClicked(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_btnModificar1MouseClicked
        if (showConfirmDialog(this, "¿Desea actualizar el expediente clínico?", "Confirmar",
                JOptionPane.YES_NO_OPTION) != 0)
            return;
        expedienteSel.setMotivo(tagsMotivo.getTags(0));
        expedienteSel.setEnfermedad(tagsEnfermedad.getTags(0));
        expedienteSel.setObservaciones(TagInputControl.limpiarCadena(txtObservaciones.getText()));
        expedienteSel.setTratamiento(TagInputControl.limpiarCadena(txtTratamiento.getText()));
        String retorno;
        showMessageDialog(this, retorno = expedienteSel.actualizar());
        if (retorno.equals("Los datos del expediente clínico han sido actualizados correctamente."))
            this.dispose();
    }// GEN-LAST:event_btnModificar1MouseClicked

    private void btnEliminarMouseClicked(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_btnEliminarMouseClicked
        if (showConfirmDialog(this, "¿Desea Eliminar el expediente clínico?", "Confirmar",
                JOptionPane.YES_NO_OPTION) != 0)
            return;
        String retorno;
        showMessageDialog(this,retorno = expedienteSel.eliminar());
        if (retorno.equals("Se eliminó correctamente"))
            this.dispose();
    }// GEN-LAST:event_btnEliminarMouseClicked

    private void btnAgregar1MouseClicked(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_btnAgregar1MouseClicked
        try {
            // if (validarCampos())
            // throw new Exception("Lo siento, debes completar todos los campos del
            // expediente clínico antes de registrar.\nPor favor, asegúrate de ingresar la
            // información requerida en todos los campos y vuelve a intentarlo.");
            if (showConfirmDialog(this, "¿Desea registrar el expediente clínico?", "Confirmar",
                    JOptionPane.YES_NO_OPTION) != 0)
                return;
            String retorno;
            showMessageDialog(this, retorno = new ExpedienteClínico(new java.sql.Date(dtFecha1.getDate().getTime()),
                    tagsMotivo.getTags(0),
                    tagsEnfermedad.getTags(0),
                    TagInputControl.limpiarCadena(txtObservaciones.getText()),
                    TagInputControl.limpiarCadena(txtTratamiento.getText()),
                    ExpedienteClínico.Estado.Activo,
                    paciente.getId()).insertar());
            if (retorno.equals("Se registró correctamente"))
                this.dispose();
        } catch (Exception e) {
            showMessageDialog(this, e.getMessage());
        }
        return;
    }// GEN-LAST:event_btnAgregar1MouseClicked

    private boolean validarCampos() {
        return tagsMotivo.getTags().isEmpty() || tagsEnfermedad.getTags().isEmpty();
    }

    public static void main(String args[]) {

        // <editor-fold defaultstate="collapsed" desc=" Look and feel setting code
        // (optional) ">
        /*
         * If Nimbus (introduced in Java SE 6) is not available, stay with the default
         * look and feel.
         * For details see
         * http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Windows".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(VentanaExpediente.class.getName()).log(java.util.logging.Level.SEVERE,
                    null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(VentanaExpediente.class.getName()).log(java.util.logging.Level.SEVERE,
                    null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(VentanaExpediente.class.getName()).log(java.util.logging.Level.SEVERE,
                    null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(VentanaExpediente.class.getName()).log(java.util.logging.Level.SEVERE,
                    null, ex);
        }
        // </editor-fold>

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new VentanaExpediente().setVisible(true);
            }
        });
    }

    private DefaultTableModel m;
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private AutoCompleteTextField autoCompleteTextField1;
    private AutoCompleteTextField autoCompleteTextField2;
    private AutoCompleteTextField autoCompleteTextField3;
    private AutoCompleteTextField autoCompleteTextField4;
    private JImageBox btnAgregar1;
    private JImageBox btnEliminar;
    private JImageBox btnModificar1;
    private javax.swing.JComboBox<String> cmbExpedientes;
    private com.toedter.calendar.JDateChooser dtFecha1;
    private JImageBox jImageBox2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private TagInputControl tagsEnfermedad;
    private TagInputControl tagsMotivo;
    private javax.swing.JTextField txtFolio;
    private javax.swing.JTextArea txtObservaciones;
    private javax.swing.JTextField txtPaciente;
    private javax.swing.JTextArea txtTratamiento;
    // End of variables declaration//GEN-END:variables
}
