
import Conexión.Conexión;
import java.awt.Image;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;


public class VentanaInicio_SesionDentista extends javax.swing.JFrame {

    /**
     * Creates new form VentanaInicio_SesionDentista
     */
    
    public VentanaInicio_SesionDentista() {
        initComponents(); 
        setLocationRelativeTo(null);
        lblHora.setText("Fecha: "+fechacomp);
        
    /*  this.Imagen(this.lbLogo,"Imagenes\\logoD.png");
        this.Imagen(this.lblUsuario,"Imagenes\\usuario.png");
        this.Imagen(this.lblContraseña,"Imagenes\\candado.png");
       
        this.setSize(650, 780);*/
        
    }
   /* private ImageIcon imagen;
    private Icon icono;
    private void Imagen(JLabel lbl,String ruta){
        this.imagen=new ImageIcon(ruta);
        this.icono=new ImageIcon(
                this.imagen.getImage().getScaledInstance
                       (lbl.getWidth(),
                        lbl.getHeight(),
                        Image.SCALE_SMOOTH));
        lbl.setIcon(this.icono);
        repaint();
      }*/

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        txtPassword = new javax.swing.JPasswordField();
        btnInicio = new javax.swing.JButton();
        jCheckBox1 = new javax.swing.JCheckBox();
        cmbRol = new javax.swing.JComboBox<>();
        jLabel1 = new javax.swing.JLabel();
        jImageBox1 = new JImageBox();
        jImageBox2 = new JImageBox();
        jImageBox3 = new JImageBox();
        jLabel2 = new javax.swing.JLabel();
        txtUsuario = new javax.swing.JTextField();
        lblHora = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(0, 108, 183));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        txtPassword.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jPanel2.add(txtPassword, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 340, 260, 40));

        btnInicio.setBackground(new java.awt.Color(69, 204, 209));
        btnInicio.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        btnInicio.setText("Iniciar sesión");
        btnInicio.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnInicio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnInicioActionPerformed(evt);
            }
        });
        jPanel2.add(btnInicio, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 450, 310, 40));

        jCheckBox1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jCheckBox1.setText("Mostrar");
        jCheckBox1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jCheckBox1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jCheckBox1MouseClicked(evt);
            }
        });
        jPanel2.add(jCheckBox1, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 390, -1, -1));

        cmbRol.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        cmbRol.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Selecciona", "Dentista", "Secretaria" }));
        cmbRol.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
        cmbRol.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jPanel2.add(cmbRol, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 210, 260, 40));

        jLabel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
        jPanel2.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 340, 260, 40));

        jImageBox1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/logoD.png"))); // NOI18N
        jPanel2.add(jImageBox1, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 0, 370, 230));

        jImageBox2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/icons8-dentist-60.png"))); // NOI18N
        jPanel2.add(jImageBox2, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 270, 50, -1));

        jImageBox3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/icons8-contraseña-60.png"))); // NOI18N
        jPanel2.add(jImageBox3, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 340, 50, -1));

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel2.setText("Rol:");
        jPanel2.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 220, -1, -1));

        txtUsuario.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jPanel2.add(txtUsuario, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 270, 260, 40));

        lblHora.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jPanel2.add(lblHora, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 520, 160, 30));

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

    private void btnInicioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnInicioActionPerformed
   consultarUsuario(txtUsuario.getText(),txtPassword.getText());
   
    }//GEN-LAST:event_btnInicioActionPerformed

    private void jCheckBox1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jCheckBox1MouseClicked
        if( jCheckBox1.isSelected())txtPassword.setEchoChar((char)0);
        else txtPassword.setEchoChar('*');
    }//GEN-LAST:event_jCheckBox1MouseClicked

public void consultarUsuario(String user, String pass)
    {
        // TODO add your handling code here:
         Connection con = null;
         Conexión conect = null;
        // Se inicializa a null
        String usuarioCorrecto = null;
        String passCorrecto = null;
    try {
        con = conect.getConnection();
        PreparedStatement pst = con.prepareStatement("SELECT usuario, contraseña FROM Usuarios where usuario='"+user+"'");
        ResultSet rs = pst.executeQuery();
        if (rs.next()) {
            usuarioCorrecto = rs.getString(1);
            passCorrecto = rs.getString(2);
        }
        if (user.equals(usuarioCorrecto) && pass.equals(passCorrecto)) {
            if(cmbRol.getSelectedItem().toString().equals("Dentista")){
               MenuDentista next=new MenuDentista();
            next.setVisible(true);
            next.setLocationRelativeTo(null);
            this.dispose();
            }else {
                if(cmbRol.getSelectedItem().toString().equals("Secretaria")){
                MenuSecretaria next=new MenuSecretaria();
            next.setVisible(true);
            next.setLocationRelativeTo(null);
            this.dispose();
                }}//if user
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
Calendar fecha=new GregorianCalendar();
String año=Integer.toString(fecha.get(Calendar.YEAR));
String mes=Integer.toString(fecha.get(Calendar.MONTH));
String dia=Integer.toString(fecha.get(Calendar.DATE));
String fechacomp=dia+"/"+mes+"/"+año;

String hora=Integer.toString(fecha.get(Calendar.HOUR_OF_DAY));
String min=Integer.toString(fecha.get(Calendar.MINUTE));
String horacomp=hora+":"+min;

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnInicio;
    private javax.swing.JComboBox<String> cmbRol;
    private javax.swing.JCheckBox jCheckBox1;
    private JImageBox jImageBox1;
    private JImageBox jImageBox2;
    private JImageBox jImageBox3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JLabel lblHora;
    private javax.swing.JPasswordField txtPassword;
    private javax.swing.JTextField txtUsuario;
    // End of variables declaration//GEN-END:variables
}
