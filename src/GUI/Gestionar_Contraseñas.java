package GUI;

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
import ClasesSQL.Usuario;
import static javax.swing.JOptionPane.showConfirmDialog;
import javax.swing.table.TableColumn;

public class Gestionar_Contraseñas extends javax.swing.JFrame {

    DefaultTableModel tabla;
    Usuario usuario;

    public Gestionar_Contraseñas() {
        initComponents();
        usuario = new Usuario();
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
        ActionListener a;
        cmbRol.addActionListener(a = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (usuario.getRol().equals("Dentista"))
                    return;
                String selectedOption = (String) cmbRol.getSelectedItem();
                usuario.setRol(selectedOption);
            }
        });
        a.actionPerformed(null);
    }

    private void llenarTabla() {
        tablaUsuarios.setModel(tabla = Usuario.Consultar());
        TableColumn col = tablaUsuarios.getColumnModel().getColumn(0);
        tablaUsuarios.getColumnModel().removeColumn(col);
        col = tablaUsuarios.getColumnModel().getColumn(tablaUsuarios.getColumnModel().getColumnCount()-1);
        tablaUsuarios.getColumnModel().removeColumn(col);
    }
    
    private void eliminar() {
        if (tablaUsuarios.getSelectedRow()==-1){
            showMessageDialog(this, "Seleccione un usuario para eliminar");
            return;
        }
        if (usuario.getRol().equals("Dentista")) {
            showMessageDialog(this, "El usuario dentista no puede ser eliminado.");
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

    public static boolean confirmarContraseña(String contraseña1,String contraseña2) {
        if (!contraseña1.equals(contraseña2)) {
            showMessageDialog(null,
                    "Las contraseñas no coinciden", "", 1, null);
            return true;
        }
        return false;
    }
    
    private boolean confirmarContraseña() {
        if (!usuario.getContraseña().equals(txtConfirmar.getText())) {
            showMessageDialog(this,
                    "Las contraseñas no coinciden", "", 1, null);
            return true;
        }
        return false;
    }

    public static boolean contraseñaInválida(String contraseña) {
        if (!validarContraseña.apply(contraseña)) {
            showMessageDialog(null,
                    "La contraseña debe contener al menos una minúscula, una mayúscula y un número. Reescriba otra contraseña y vuelva a intentarlo",
                    "Contraseña Inválida", 1, null);
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
        return false;
    }

    private static final Function<String, Boolean> validarContraseña = (contraseña) -> {
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
        jScrollPane1 = new javax.swing.JScrollPane();
        tablaUsuarios = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        cmbRol = new javax.swing.JComboBox<>();
        jLabel1 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        btnRegresar = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JSeparator();
        jSeparator3 = new javax.swing.JSeparator();
        txtNombre = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        txtContraseña = new javax.swing.JTextField();
        txtConfirmar = new javax.swing.JTextField();
        jImageBox2 = new Componentes.JImageBox();
        jLabel5 = new javax.swing.JLabel();
        jSeparator4 = new javax.swing.JSeparator();
        btnActualizar = new javax.swing.JButton();
        btnEliminar = new javax.swing.JButton();
        btnRegistrar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        tablaUsuarios.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tablaUsuarios.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jScrollPane1.setViewportView(tablaUsuarios);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 80, 430, 270));

        jPanel2.setBackground(new java.awt.Color(0, 108, 183));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel8.setBackground(new java.awt.Color(69, 204, 209));
        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("Gestionar contraseñas de usuarios");
        jPanel2.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 20, -1, -1));

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 0, 470, 60));

        jPanel3.setBackground(new java.awt.Color(69, 204, 209));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        cmbRol.setBackground(new java.awt.Color(69, 204, 209));
        cmbRol.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Secretaria" }));
        jPanel3.add(cmbRol, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 340, 190, -1));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel1.setText("Usuario");
        jPanel3.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 100, 67, -1));

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel4.setText("Confirmar Contraseña");
        jPanel3.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 240, -1, -1));

        btnRegresar.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        btnRegresar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/icons8-volver-4.png"))); // NOI18N
        btnRegresar.setContentAreaFilled(false);
        btnRegresar.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnRegresar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRegresarActionPerformed(evt);
            }
        });
        jPanel3.add(btnRegresar, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 380, 50, 50));

        jSeparator1.setForeground(new java.awt.Color(0, 0, 0));
        jPanel3.add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 150, 190, -1));

        jSeparator3.setForeground(new java.awt.Color(0, 0, 0));
        jPanel3.add(jSeparator3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 220, 190, 20));

        txtNombre.setBackground(new java.awt.Color(69, 204, 209));
        txtNombre.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtNombre.setBorder(null);
        jPanel3.add(txtNombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 120, 190, 30));

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel2.setText("Contraseña");
        jPanel3.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 160, 89, -1));

        txtContraseña.setBackground(new java.awt.Color(69, 204, 209));
        txtContraseña.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtContraseña.setBorder(null);
        jPanel3.add(txtContraseña, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 190, 190, 30));

        txtConfirmar.setBackground(new java.awt.Color(69, 204, 209));
        txtConfirmar.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtConfirmar.setBorder(null);
        jPanel3.add(txtConfirmar, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 260, 200, 30));

        jImageBox2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/L1.png"))); // NOI18N
        jPanel3.add(jImageBox2, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 10, 110, -1));

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel5.setText("ROL");
        jPanel3.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 300, -1, -1));

        jSeparator4.setForeground(new java.awt.Color(0, 0, 0));
        jPanel3.add(jSeparator4, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 290, 190, 20));

        jPanel1.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 220, 440));

        btnActualizar.setBackground(new java.awt.Color(0, 108, 183));
        btnActualizar.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnActualizar.setForeground(new java.awt.Color(255, 255, 255));
        btnActualizar.setText("Actualizar");
        btnActualizar.setBorderPainted(false);
        btnActualizar.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jPanel1.add(btnActualizar, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 390, 110, 36));

        btnEliminar.setBackground(new java.awt.Color(0, 108, 183));
        btnEliminar.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnEliminar.setForeground(new java.awt.Color(255, 255, 255));
        btnEliminar.setText("Eliminar");
        btnEliminar.setBorderPainted(false);
        btnEliminar.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jPanel1.add(btnEliminar, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 390, 100, 36));
        btnEliminar.setVisible(true);

        btnRegistrar.setBackground(new java.awt.Color(0, 108, 183));
        btnRegistrar.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnRegistrar.setForeground(new java.awt.Color(255, 255, 255));
        btnRegistrar.setText("Agregar");
        btnRegistrar.setBorderPainted(false);
        btnRegistrar.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jPanel1.add(btnRegistrar, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 390, 120, 36));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
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
                if ("Windows".equals(info.getName())) {
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
    private Componentes.JImageBox jImageBox2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JTable tablaUsuarios;
    private javax.swing.JTextField txtConfirmar;
    private javax.swing.JTextField txtContraseña;
    private javax.swing.JTextField txtNombre;
    // End of variables declaration//GEN-END:variables
}
