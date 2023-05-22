package GUI;

import ClasesSQL.Usuario;
import Conexión.Conexión;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Calendar;
import java.util.GregorianCalendar;
import javax.swing.JOptionPane;

import Componentes.JImageBox;
import javax.swing.JFrame;
import static javax.swing.JOptionPane.showConfirmDialog;
import static javax.swing.JOptionPane.showMessageDialog;

public class VentanaInicio_SesionDentista extends javax.swing.JFrame {

    private Usuario usr;

    /**
     * Creates new form VentanaInicio_SesionDentista
     */

    public VentanaInicio_SesionDentista() {
        initComponents();
        setLocationRelativeTo(null);
        lblHora.setText("Fecha: "+fechacomp);   
    }
  

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jImageBox1 = new Componentes.JImageBox();
        lblHora = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jImageBox2 = new Componentes.JImageBox();
        jLabel2 = new javax.swing.JLabel();
        cmbRol = new javax.swing.JComboBox<>();
        txtUsuarioP1 = new javax.swing.JTextField();
        txtPasswordP1 = new javax.swing.JPasswordField();
        jImageBox3 = new Componentes.JImageBox();
        jCheckBox1P1 = new javax.swing.JCheckBox();
        btnInicioP1 = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        txtPasswordP2 = new javax.swing.JPasswordField();
        jImageBox5 = new Componentes.JImageBox();
        jCheckBoxP2 = new javax.swing.JCheckBox();
        btnInicioP2 = new javax.swing.JButton();
        jImageBox6 = new Componentes.JImageBox();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        txtPasswordConfP2 = new javax.swing.JPasswordField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(0, 108, 183));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel4.setText("Bienvenido");
        jPanel2.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 160, -1, -1));

        jImageBox1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/logoD.png"))); // NOI18N
        jPanel2.add(jImageBox1, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, -40, 370, 230));

        lblHora.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jPanel2.add(lblHora, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 520, 160, 30));

        jPanel3.setOpaque(false);
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jImageBox2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/icons8-dentist-60.png"))); // NOI18N
        jPanel3.add(jImageBox2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 70, 50, -1));

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel2.setText("Rol:");
        jPanel3.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, -1, -1));

        cmbRol.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        cmbRol.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Selecciona", "Dentista", "Secretaria" }));
        cmbRol.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
        cmbRol.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jPanel3.add(cmbRol, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 10, 260, 40));

        txtUsuarioP1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jPanel3.add(txtUsuarioP1, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 70, 260, 40));

        txtPasswordP1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jPanel3.add(txtPasswordP1, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 140, 260, 40));

        jImageBox3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/icons8-contraseña-60.png"))); // NOI18N
        jPanel3.add(jImageBox3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 140, 50, -1));

        jCheckBox1P1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jCheckBox1P1.setText("Mostrar");
        jCheckBox1P1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jCheckBox1P1.setOpaque(false);
        jCheckBox1P1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBox1P1ActionPerformed(evt);
            }
        });
        jPanel3.add(jCheckBox1P1, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 190, -1, -1));

        btnInicioP1.setBackground(new java.awt.Color(69, 204, 209));
        btnInicioP1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        btnInicioP1.setText("Iniciar sesión");
        btnInicioP1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnInicioP1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnInicioP1ActionPerformed(evt);
            }
        });
        jPanel3.add(btnInicioP1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 250, 310, 40));

        jPanel2.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 200, 346, 307));

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));
        jPanel4.setForeground(new java.awt.Color(255, 255, 255));
        jPanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel5.setText("Confirmar Contraseña:");
        jPanel4.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 150, -1, -1));

        txtPasswordP2.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jPanel4.add(txtPasswordP2, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 90, 260, 40));

        jImageBox5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/icons8-contraseña-60.png"))); // NOI18N
        jPanel4.add(jImageBox5, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 80, 50, -1));

        jCheckBoxP2.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jCheckBoxP2.setText("Mostrar");
        jCheckBoxP2.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jCheckBoxP2.setOpaque(false);
        jCheckBoxP2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBoxP2ActionPerformed(evt);
            }
        });
        jPanel4.add(jCheckBoxP2, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 220, -1, -1));

        btnInicioP2.setBackground(new java.awt.Color(69, 204, 209));
        btnInicioP2.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        btnInicioP2.setText("Confirmar");
        btnInicioP2.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnInicioP2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnInicioP2ActionPerformed(evt);
            }
        });
        jPanel4.add(btnInicioP2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 250, 310, 40));

        jImageBox6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/icons8-contraseña-60.png"))); // NOI18N
        jPanel4.add(jImageBox6, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 160, 50, -1));

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel6.setText("Por favor, Cambia tu contraseña");
        jPanel4.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 20, -1, -1));

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel7.setText("Nueva Contraseña:");
        jPanel4.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 70, -1, -1));

        txtPasswordConfP2.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jPanel4.add(txtPasswordConfP2, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 170, 260, 40));

        jPanel2.add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 200, -1, -1));
        jPanel4.setVisible(false);

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(53, 45, 460, 560));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 562, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 649, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnInicioP1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnInicioP1ActionPerformed
        usr = Usuario.buscar(txtUsuarioP1.getText(), txtPasswordP1.getText());
        if (usr == null) {
            showMessageDialog(this, "Usuario o contraseña incorrectos");
        }
        if (usr.cambiarContra) {
            int option = showConfirmDialog(this, "Se detecto que su contraseña se actualizo, ¿Desea cambiarla?", "Cambia tu contraseña", JOptionPane.YES_NO_CANCEL_OPTION);
            if (option == JOptionPane.YES_OPTION) {
                jPanel3.setVisible(false);
                jPanel4.setVisible(true);
            } else if (option == JOptionPane.NO_OPTION) {
                usr.actualizar_cambiarContra();
                siguienteVentana();
            } else if (option == JOptionPane.CANCEL_OPTION) {
                siguienteVentana();
            }
        }
//        consultarUsuario(txtUsuario.getText(),txtPassword.getText());
    }//GEN-LAST:event_btnInicioP1ActionPerformed

    private void siguienteVentana() {
        JFrame next = usr.getRol().equals("Dentista") ? new MenuDentista() : new MenuSecretaria2();
        next.setVisible(true);
        next.setLocationRelativeTo(null);
        this.dispose();
    }
    
    private void btnInicioP2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnInicioP2ActionPerformed
        if (Gestionar_Contraseñas.contraseñaInválida(txtPasswordP2.getText()) || Gestionar_Contraseñas.confirmarContraseña(txtPasswordP2.getText(), txtPasswordConfP2.getText())) {
            return;
        }
        if (igualContraseña()) {
            int option = showConfirmDialog(this, "La Contraseña es igual a la establecida en un principio, ¿Desea dejarla igual?", "Confirmar", JOptionPane.YES_NO_CANCEL_OPTION);
            if (option != JOptionPane.YES_OPTION) {
                return;
            }
        }
        usr.setContraseña(txtPasswordConfP2.getText());
        showMessageDialog(this, usr.actualizar_cambiarContra());
        siguienteVentana();
    }//GEN-LAST:event_btnInicioP2ActionPerformed

    private void jCheckBox1P1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBox1P1ActionPerformed
        if (jCheckBox1P1.isSelected()){
            txtPasswordP1.setEchoChar((char) 0);
        }
        else{
            txtPasswordP1.setEchoChar('•');
        }
    }//GEN-LAST:event_jCheckBox1P1ActionPerformed

    private void jCheckBoxP2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBoxP2ActionPerformed
        if (jCheckBoxP2.isSelected()){
            txtPasswordP2.setEchoChar((char) 0);
            txtPasswordConfP2.setEchoChar((char) 0);
        }
        else{
            txtPasswordP2.setEchoChar('•');
            txtPasswordConfP2.setEchoChar('•');
        }
    }//GEN-LAST:event_jCheckBoxP2ActionPerformed

    private boolean igualContraseña() {
        return usr.getContraseña().equals(txtPasswordConfP2.getText());
    }

    public void consultarUsuario(String user, String pass) {
        // TODO add your handling code here:
        Connection con = null;
        Conexión conect = null;
        // Se inicializa a null
        String usuarioCorrecto = null;
        String passCorrecto = null;
        try {
            con = conect.getConnection();
            PreparedStatement pst = con.prepareStatement("SELECT usuario, contraseña FROM Usuarios where usuario='" + user + "'");
            // PreparedStatement pst2 = con.prepareStatement("SET GLOBAL time_zone = '-3:00'");
            ResultSet rs = pst.executeQuery();
            // ResultSet rs2 = pst2.executeQuery();
            if (rs.next()) {
                usuarioCorrecto = rs.getString(1);
                passCorrecto = rs.getString(2);
            }
            if (user.equals(usuarioCorrecto) && pass.equals(passCorrecto)) {
                if (cmbRol.getSelectedItem().toString().equals("Dentista")) {
                    MenuDentista next = new MenuDentista();
                    next.setVisible(true);
                    next.setLocationRelativeTo(null);
                    this.dispose();
                } else {
                    if (cmbRol.getSelectedItem().toString().equals("Secretaria")) {
                        MenuSecretaria2 next = new MenuSecretaria2();
                        next.setVisible(true);
                        next.setLocationRelativeTo(null);
                        this.dispose();
                    }
                }//if user
            } else if (!user.equals(usuarioCorrecto) || !pass.equals(passCorrecto)) {
                JOptionPane.showMessageDialog(null, "Usuario o contraseña incorrectos");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error " + e);
        }
    }

    public static void main(String args[]) {

        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Windows".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(VentanaInicio_SesionDentista.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(VentanaInicio_SesionDentista.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(VentanaInicio_SesionDentista.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(VentanaInicio_SesionDentista.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new VentanaInicio_SesionDentista().setVisible(true);
            }
        });
    }
    Calendar fecha = new GregorianCalendar();
    String año = Integer.toString(fecha.get(Calendar.YEAR));
    String mes = Integer.toString(fecha.get(Calendar.MONTH));
    String dia = Integer.toString(fecha.get(Calendar.DATE));
    String fechacomp = dia + "/" + mes + "/" + año;

    String hora = Integer.toString(fecha.get(Calendar.HOUR_OF_DAY));
    String min = Integer.toString(fecha.get(Calendar.MINUTE));
    String horacomp = hora + ":" + min;

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnInicioP1;
    private javax.swing.JButton btnInicioP2;
    private javax.swing.JComboBox<String> cmbRol;
    private javax.swing.JCheckBox jCheckBox1P1;
    private javax.swing.JCheckBox jCheckBoxP2;
    private Componentes.JImageBox jImageBox1;
    private Componentes.JImageBox jImageBox2;
    private Componentes.JImageBox jImageBox3;
    private Componentes.JImageBox jImageBox5;
    private Componentes.JImageBox jImageBox6;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JLabel lblHora;
    private javax.swing.JPasswordField txtPasswordConfP2;
    private javax.swing.JPasswordField txtPasswordP1;
    private javax.swing.JPasswordField txtPasswordP2;
    private javax.swing.JTextField txtUsuarioP1;
    // End of variables declaration//GEN-END:variables
}
