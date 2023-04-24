
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.util.function.Function;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import static javax.swing.JOptionPane.showMessageDialog;
import org.mindrot.jbcrypt.BCrypt;
import static javax.swing.JOptionPane.showConfirmDialog;

public class Gestionar_Contraseñas extends javax.swing.JFrame {

    DefaultTableModel tabla;
    Usuario usuario;

    public Gestionar_Contraseñas() {
        initComponents();
        llenarTabla();
        tablaUsuarios.getSelectionModel().addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            @Override
            public void valueChanged(javax.swing.event.ListSelectionEvent event) {
                if (event.getValueIsAdjusting()) {
                    // No hacer nada si aún no se ha terminado de seleccionar la fila
                    return;
                }
                formularioActualizar();
            }
        });
        btnActualizar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                actualizar();
            }
        });
        btnRegistrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                registrar();
            }
        });
        btnEliminar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                eliminar();
            }
        });
        txtNombre.addFocusListener(new FocusListener() {
            @Override
            public void focusLost(FocusEvent e) {
                usuario.setUsuario(txtNombre.getText());
            }

            @Override
            public void focusGained(FocusEvent e) {
            }
        });
        txtContraseña.addFocusListener(new FocusListener() {
            @Override
            public void focusLost(FocusEvent e) {
                usuario.setContraseña(txtContraseña.getText());
            }

            @Override
            public void focusGained(FocusEvent e) {
            }
        });
        cmbRol.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String selectedOption = (String) cmbRol.getSelectedItem();
                usuario.setRol(selectedOption);
            }
        });
        usuario = new Usuario();
    }

    private void llenarTabla() {
        tablaUsuarios.setModel(tabla = Usuario.Consultar());
        var col = tablaUsuarios.getColumnModel().getColumn(0);
        tablaUsuarios.getColumnModel().removeColumn(col);
    }
    
    private void eliminar() {
        if (tablaUsuarios.getSelectedRow()==-1){
            showMessageDialog(this, "Seleccione un usuario para eliminar");
            return;
        }
        String s = "";
        if (showConfirmDialog(this, "El usuario "+usuario.getUsuario()+" será eliminado.", "Confirmar eliminación.",
                JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE, null) == JOptionPane.YES_OPTION) {
            showMessageDialog(this, s = usuario.eliminar());
        }
        if (s.startsWith("Se elimi")) {
            llenarTabla();
            vaciarCampos();
        }
    }
    
    private void registrar() {
        if (camposVacíos() || contraseñaInválida()
                || confirmarContraseña()) {
            return;
        }
        String s = "";
        showMessageDialog(this, s = usuario.insertar());
        if (s.startsWith("Se regis")) {
            llenarTabla();
            vaciarCampos();
        }
    }

    private void actualizar() {
        if (camposVacíos() || contraseñaInválida()
                || confirmarContraseña()) {
            return;
        }
        String s = "";
        if (showConfirmDialog(this, "El usuario actualizará sus datos.", "Confirmar actualización.",
                JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE, null) == JOptionPane.YES_OPTION) {
            showMessageDialog(this, s = usuario.actualizar());
        }
        if (s.startsWith("Se actua")) {
            llenarTabla();
            vaciarCampos();
        }
    }

    private void vaciarCampos() {
        JTextField campos[] = {txtNombre, txtContraseña, txtConfirmar};
        for (JTextField campo : campos) {
            campo.setText("");
        }
        cmbRol.setSelectedIndex(0);
    }

    private boolean confirmarContraseña() {
        if (!usuario.getContraseña().equals(txtConfirmar.getText())) {
            showMessageDialog(this,
                    "Las contraseñas no coinciden", "", 1, null);
            return true;
        }
        return false;
    }

    private boolean contraseñaInválida() {
        if (!validarContraseña.apply(txtContraseña.getText())) {
            showMessageDialog(this,
                    "La contraseña debe contener al menos una minúscula, una mayúscula y un número. Reescriba otra contraseña y vuelva a intentarlo",
                    "Contraseña Inválida", 1, null);
            return true;
        }
        return false;
    }

    private boolean camposVacíos() {
        JTextField campos[] = {txtNombre, txtContraseña, txtConfirmar};
        for (JTextField campo : campos) {
            if ("".equals(campo.getText())) {
                showMessageDialog(this, "Rellene todos los campos para continuar.", "Campo vacío", 1, null);
                return true;
            }
        }
        if (cmbRol.getSelectedIndex()==0){
            showMessageDialog(this, "Rellene todos los campos para continuar.", "Campo vacío", 1, null);
            return true;
        }
        return false;
    }

    private static Function<String, Boolean> validarContraseña = (contraseña) -> {
        String regex = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d).+$";
        /*
         * Expresión regular para una contraseña con al menos una minúscula,
         * mayúscula y número sin importar el orden de aparición
         */
        Pattern pattern = Pattern.compile(regex);
        // Genera el grafo de transición de prefijo (Autómata)
        Matcher matcher = pattern.matcher(contraseña);
        // Instancia el autómata
        return matcher.matches();
        // Devuelve si la expresión es válida o no
    };

    // <editor-fold defaultstate="collapsed" desc="Generated
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txtConfirmar = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        btnRegresar = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        txtContraseña = new javax.swing.JTextField();
        txtNombre = new javax.swing.JTextField();
        btnActualizar = new javax.swing.JButton();
        jTextField1 = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablaUsuarios = new javax.swing.JTable();
        btnEliminar = new javax.swing.JButton();
        btnRegistrar = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        cmbRol = new javax.swing.JComboBox<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel5.setText("Gestionar contraseñas de usuarios");

        jLabel3.setText("Buscar");

        jLabel4.setText("Confirmar Contraseña");

        btnRegresar.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        btnRegresar.setText("Regresar");
        btnRegresar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRegresarActionPerformed(evt);
            }
        });

        jLabel2.setText("Contraseña");

        jLabel1.setText("Nombre");

        btnActualizar.setText("Actualizar");

        tablaUsuarios.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(tablaUsuarios);

        btnEliminar.setText("Eliminar");

        btnRegistrar.setText("Registrar");

        jLabel6.setText("Rol");

        cmbRol.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Selecciona un rol", "Dentista", "Secretaria" }));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(btnEliminar)
                        .addGap(218, 218, 218)
                        .addComponent(btnActualizar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 115, Short.MAX_VALUE)
                        .addComponent(btnRegresar)
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(btnRegistrar))
                                .addGap(78, 78, 78))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(cmbRol, javax.swing.GroupLayout.PREFERRED_SIZE, 184, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap())))))
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 261, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 375, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 191, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(27, 27, 27)))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 182, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtContraseña, javax.swing.GroupLayout.PREFERRED_SIZE, 182, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtConfirmar, javax.swing.GroupLayout.PREFERRED_SIZE, 182, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(250, Short.MAX_VALUE)
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cmbRol, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnRegistrar)
                .addGap(48, 48, 48)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnEliminar)
                    .addComponent(btnActualizar)
                    .addComponent(btnRegresar))
                .addContainerGap())
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jLabel5)
                    .addGap(18, 18, 18)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel3))
                    .addGap(18, 18, 18)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 304, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addComponent(jLabel1)
                            .addGap(12, 12, 12)
                            .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(17, 17, 17)
                            .addComponent(jLabel2)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(txtContraseña, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jLabel4)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(txtConfirmar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(538, 538, 538)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(310, 310, 310))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnRegresarActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_btnRegresarActionPerformed
        MenuDentista next = new MenuDentista();
        next.setVisible(true);
        next.setLocationRelativeTo(null);
        this.dispose();
    }// GEN-LAST:event_btnRegresarActionPerformed

    private void formularioActualizar() {
        try{
            int row = tablaUsuarios.getSelectedRow();
            int id = (Integer) tabla.getValueAt(row, 0);
            String usuario = (String) tabla.getValueAt(row, 1);
            String contraseña = (String) tabla.getValueAt(row, 2);
            String rol = (String) tabla.getValueAt(row, 3);
            this.usuario = new Usuario(id, usuario, contraseña, rol);
            txtNombre.setText(usuario);
            txtContraseña.setText(contraseña);
            cmbRol.setSelectedItem(rol);
        } catch(Exception e){}
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
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Gestionar_Contraseñas.class.getName())
                    .log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Gestionar_Contraseñas.class.getName())
                    .log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Gestionar_Contraseñas.class.getName())
                    .log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Gestionar_Contraseñas.class.getName())
                    .log(java.util.logging.Level.SEVERE, null, ex);
        }
        // </editor-fold>

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Gestionar_Contraseñas().setVisible(true);

                // System.out.println(validarContraseña.apply(contraseña)? "Válida":"Inválida");
                // if (!validarContraseña.apply(contraseña))
                // return;
            }
        });
    }

    private static void encriptarContraseña(String contraseña) {
        String sal = BCrypt.gensalt(13);
        // Se utiliza la librerí­a BCrypt para el proceso de encriptación. Generamos una
        // sal aleatoria.
        String contraseñaEncriptada = BCrypt.hashpw(contraseña, sal);
        // Genera el hash de contraseña en base al esquema de OpenBSD
        System.out.println("Contraseña encriptada: " + contraseñaEncriptada);
        System.out.println("Longitud de la contraseña: " + contraseña.length());
        System.out.println("Longitud de la contraseña encriptada: " + contraseñaEncriptada.length());
        System.out.println(
                BCrypt.checkpw(contraseña, "$2a$04$Zbn2ua1Fq6I06jLs8Xf.UeagWmkpIMVvXc49KWeY0T8WvqsChV5nC") ? "Válida"
                : "Inválida");
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnActualizar;
    private javax.swing.JButton btnEliminar;
    private javax.swing.JButton btnRegistrar;
    private javax.swing.JButton btnRegresar;
    private javax.swing.JComboBox<String> cmbRol;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTable tablaUsuarios;
    private javax.swing.JTextField txtConfirmar;
    private javax.swing.JTextField txtContraseña;
    private javax.swing.JTextField txtNombre;
    // End of variables declaration//GEN-END:variables
}
