import Conexión.Conexión;
import java.awt.HeadlessException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class VentanaExpediente extends javax.swing.JFrame {

    public VentanaExpediente() {
        initComponents();
        setLocationRelativeTo(null);

        txtFolio.setEnabled(false);
       // llenarCmbPaciente();
        dtFecha2.setEnabled(false);
    }
    /*
private void llenarCmbPaciente(){
        
        try {
                Connection con = null;
                    Conexión conect = new Conexión();
                    con = conect.getConnection();
                    Statement st = con.createStatement();
            String query="SELECT id,nombre,apellido FROM pacientes";
            ResultSet result= st.executeQuery(query);
            int id;
            String nombre,apellido;
            while(result.next()){
                id=result.getInt("id");
                nombre=result.getString("nombre");
                apellido=result.getString("apellido");
                cmbPaciente.addItem((id+"-"+nombre+" "+apellido));
            }
        } catch (SQLException ex) {
            System.out.println("Error de conexión "+ex); return;
        }
        }//llenarCmbEmpresa*/
private void agregar(){
     DateFormat di = new SimpleDateFormat("dd/MM/yyyy");
     DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        try
            {
                Connection con = null;
                Conexión conect = new Conexión();
                con = conect.getConnection();
                Statement st = con.createStatement();
                String sql = "insert into expediente_clinico (fecha_inicio,fecha_actualizacion,motivo, enfermedad, observaciones,id_paciente, estado) values (?,?,?,?,?,?,?)";
                PreparedStatement pst = con.prepareStatement(sql);
                //String A[]=cmbPaciente.getSelectedItem().toString().split("-");
                pst.setString(1, di.format(dtFecha1.getDate()));
                pst.setString(2, di.format(dtFecha1.getDate()));
                pst.setString(3, txtMotivo.getText());
                pst.setString(4, txtEnfermedad.getText());
                pst.setString(5, txtObservaciones.getText());
                //pst.setString(6, A[0]);
               
                if (btnVigente.isSelected()) {
                pst.setString(7,btnVigente.getText());
}               else if (btnInactivo.isSelected()) {
                pst.setString(7,btnInactivo.getText());
}               else {
}
                int n = pst.executeUpdate();
                if (n > 0)
                {
                    JOptionPane.showMessageDialog(this, "DATOS GUARDADOS CORRECTAMENTE");
                    limpiar();
                    
                    
                }
            } catch (SQLException | HeadlessException e)
            {
                JOptionPane.showMessageDialog(this, "LOS DATOS NO HAN SIDO GUARDADOS CORRECTAMENTE"+e, "Error", JOptionPane.ERROR_MESSAGE);
            }
        
         
    }//agregar
public void limpiar(){
        
       
       this.txtFolio.setText("");
       this.txtMotivo.setText("");
       this.txtEnfermedad.setText("");
       this.txtObservaciones.setText("");
       this.dtFecha1.setDate(null);
       this.dtFecha2.setDate(null);
       this.btnInactivo.setSelected(false);
       this.btnVigente.setSelected(false);
      
    }//limpiar
 void llenarCamposTexto(String[] datos) {
     DateFormat di = new SimpleDateFormat("dd/MM/yyyy");
     DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
     txtFolio.setText(datos[0]);
     txtMotivo.setText(datos[1]);
     txtEnfermedad.setText(datos[2]);
      txtObservaciones.setText(datos[3]);
       try {
        Date fecha1 = di.parse(datos[4]);
        dtFecha1.setDate(fecha1);
    } catch (ParseException e) {
        e.printStackTrace();
    }
      try {
        Date fecha2 = df.parse(datos[5]);
        dtFecha2.setDate(fecha2);
    } catch (ParseException e) {
        e.printStackTrace();
    }
       boolean inactivo = Boolean.parseBoolean(datos[6]);
    btnInactivo.setSelected(inactivo);
    btnVigente.setSelected(!inactivo);
}

void Buscar(String valor){
    Connection con = null;
    Conexión conect = null;
    con = conect.getConnection();
    DefaultTableModel modelo = new DefaultTableModel();
    modelo.addColumn("ID");
    modelo.addColumn("Nombre");
    modelo.addColumn("Apellido");
    modelo.addColumn("Edad");
    modelo.addColumn("Genéro");
    modelo.addColumn("Teléfono");
    modelo.addColumn("Dirección");

    String sql="";
    if (valor.equals("")) {
        sql = "SELECT * FROM pacientes";
    } else {
        if (cmbBuscar.getSelectedItem().toString().equals("Nombre")) {
            sql = "SELECT * FROM pacientes WHERE nombre='"+valor+"'";
        } else if(cmbBuscar.getSelectedItem().toString().equals("Apellido")) {
            sql="SELECT * FROM pacientes WHERE apellido='"+valor+"'";
        }
    }
    String []Datos = new String [7];
    try {
        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery(sql);
        boolean encontrado = false;
        while(rs.next()){
            Datos[0] = rs.getString(1);
            Datos[1] = rs.getString(2);
            Datos[2] = rs.getString(3);
            Datos[3] = rs.getString(4);
            Datos[4] = rs.getString(5);
            Datos[5] = rs.getString(6);
            Datos[6] = rs.getString(7);

            modelo.addRow(Datos);
            encontrado = true;
        }

        if (encontrado) {
            llenarCamposTexto(Datos);
        } else {
            // Limpia los campos de texto si no se encuentra el registro
            String[] vacio = {"", "", "", "", "", ""};
            llenarCamposTexto(vacio);
        }
        
    } catch(SQLException ex) {
        Logger.getLogger("ERROR AL BUSCAR"+ex);
    }
}
    
 

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnGrupo1 = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        txtFolio = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        cmbBuscar = new javax.swing.JComboBox<>();
        txtBuscar = new javax.swing.JTextField();
        dtFecha1 = new com.toedter.calendar.JDateChooser();
        jLabel4 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        btnAgregar = new javax.swing.JButton();
        btnLimpiar = new javax.swing.JButton();
        btnPaciente = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        lblID = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        btnVigente = new javax.swing.JRadioButton();
        btnInactivo = new javax.swing.JRadioButton();
        jLabel6 = new javax.swing.JLabel();
        jImageBox1 = new JImageBox();
        jImageBox2 = new JImageBox();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel2.setBackground(new java.awt.Color(0, 108, 183));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel1.setText("Expediente Clínico");
        jPanel2.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 10, -1, -1));

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 850, 60));

        jPanel3.setBackground(new java.awt.Color(69, 204, 209));
        jPanel3.setLayout(null);
        jPanel3.add(txtFolio);
        txtFolio.setBounds(192, 16, 95, 22);

        jLabel10.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel10.setText("Folio:");
        jPanel3.add(jLabel10);
        jLabel10.setBounds(112, 16, 43, 22);

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel2.setText("Buscar paciente:");
        jPanel3.add(jLabel2);
        jLabel2.setBounds(30, 60, 129, 22);

        cmbBuscar.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        cmbBuscar.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Seleccionar", "Nombre", "Apellido" }));
        jPanel3.add(cmbBuscar);
        cmbBuscar.setBounds(470, 60, 119, 25);
        jPanel3.add(txtBuscar);
        txtBuscar.setBounds(180, 60, 265, 26);
        jPanel3.add(dtFecha1);
        dtFecha1.setBounds(183, 98, 130, 22);

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel4.setText("Fecha de inicio:");
        jPanel3.add(jLabel4);
        jLabel4.setBounds(26, 98, 122, 22);

        jLabel9.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel9.setText("Observaciones:");
        jPanel3.add(jLabel9);
        jLabel9.setBounds(30, 250, 119, 22);

        btnAgregar.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        btnAgregar.setText("Agregar");
        btnAgregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarActionPerformed(evt);
            }
        });
        jPanel3.add(btnAgregar);
        btnAgregar.setBounds(277, 396, 93, 31);

        btnLimpiar.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        btnLimpiar.setText("Limpiar");
        btnLimpiar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLimpiarActionPerformed(evt);
            }
        });
        jPanel3.add(btnLimpiar);
        btnLimpiar.setBounds(524, 396, 89, 31);

        btnPaciente.setText("Pacientes");
        btnPaciente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPacienteActionPerformed(evt);
            }
        });
        jPanel3.add(btnPaciente);
        btnPaciente.setBounds(120, 401, 83, 25);

        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel7.setText("Status:");
        jPanel3.add(jLabel7);
        jLabel7.setBounds(370, 357, 55, 22);

        jButton1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jButton1.setText("Modificar");
        jPanel3.add(jButton1);
        jButton1.setBounds(386, 400, 103, 31);

        lblID.setForeground(new java.awt.Color(255, 255, 255));
        jPanel3.add(lblID);
        lblID.setBounds(279, 1, 107, 22);

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel5.setText("Motivo de la consulta:");
        jPanel3.add(jLabel5);
        jLabel5.setBounds(30, 200, 174, 22);

        btnVigente.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        btnVigente.setText("Vigente");
        jPanel3.add(btnVigente);
        btnVigente.setBounds(437, 355, 85, 31);

        btnInactivo.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        btnInactivo.setText("Inactivo");
        jPanel3.add(btnInactivo);
        btnInactivo.setBounds(531, 355, 89, 31);

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel6.setText("Enfermedad actual:");
        jPanel3.add(jLabel6);
        jLabel6.setBounds(26, 153, 220, 22);

        jImageBox1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jImageBox1MouseClicked(evt);
            }
        });
        jPanel3.add(jImageBox1);
        jImageBox1.setBounds(17, 396, 81, 0);

        jImageBox2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jImageBox2MouseClicked(evt);
            }
        });
        jPanel3.add(jImageBox2);
        jImageBox2.setBounds(590, 56, 50, 0);

        jPanel1.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 80, 790, 460));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, 560));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnAgregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarActionPerformed
        agregar();
    }//GEN-LAST:event_btnAgregarActionPerformed

    private void btnLimpiarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLimpiarActionPerformed
       limpiar();
    }//GEN-LAST:event_btnLimpiarActionPerformed

    private void btnPacienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPacienteActionPerformed
        VentanaPaciente next=new VentanaPaciente();
        next.setVisible(true);
        next.setLocationRelativeTo(null);
        this.dispose();
    }//GEN-LAST:event_btnPacienteActionPerformed

    private void jImageBox1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jImageBox1MouseClicked
        MenuDentista next=new MenuDentista();
        next.setVisible(true);
        next.setLocationRelativeTo(null);
        this.dispose();       
    }//GEN-LAST:event_jImageBox1MouseClicked

    private void jImageBox2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jImageBox2MouseClicked
        Buscar(txtBuscar.getText());
    }//GEN-LAST:event_jImageBox2MouseClicked

    
    public static void main(String args[]) {
        
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
            java.util.logging.Logger.getLogger(VentanaExpediente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(VentanaExpediente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(VentanaExpediente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(VentanaExpediente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new VentanaExpediente().setVisible(true);
            }
        });
    }
private DefaultTableModel m;
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAgregar;
    private javax.swing.ButtonGroup btnGrupo1;
    private javax.swing.JRadioButton btnInactivo;
    private javax.swing.JButton btnLimpiar;
    private javax.swing.JButton btnPaciente;
    private javax.swing.JRadioButton btnVigente;
    private javax.swing.JComboBox<String> cmbBuscar;
    private com.toedter.calendar.JDateChooser dtFecha1;
    private javax.swing.JButton jButton1;
    private JImageBox jImageBox1;
    private JImageBox jImageBox2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JLabel lblID;
    private javax.swing.JTextField txtBuscar;
    private javax.swing.JTextField txtFolio;
    // End of variables declaration//GEN-END:variables
}
