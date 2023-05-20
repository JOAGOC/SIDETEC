package GUI;

import com.toedter.calendar.JDateChooser;
import ClasesSQL.ExpedienteClínico;
import Componentes.ColorRenderer;
import desplazable.Desface;
import static Componentes.TagInputControl.*;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.ImageIcon;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;

public class VentanaExpedienteMenu extends javax.swing.JFrame {

    ColorRenderer colorRenderer = new ColorRenderer();
    private ImageIcon imagen;
    private ImageIcon icono;
    Desface desplace;
    JTableHeader th;

    public VentanaExpedienteMenu() {
        initComponents();
        tblCita.setModel(ExpedienteClínico.consultar());
        TableColumn col = tblCita.getColumnModel().getColumn(6);
        tblCita.getColumnModel().removeColumn(col);
        btnBuscar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                buscarPaciente();
            }
        });
    }

    private void buscarPaciente() {
        String folio = txtFolio.getText(),paciente = txtPaciente.getText(),fecha = casillaFecha.isSelected()? new SimpleDateFormat("yyyy-MM-dd").format(dtFecha.getDate()):"";
        System.out.println(folio.matches("^[1-9][0-9]*$")? folio : "");
        System.out.println(limpiarCadena(paciente));
        System.out.println(fecha);
        try {
            ExpedienteClínico.buscar(folio.matches("[1-9][0-9]*")? folio : "", paciente, fecha, rootPaneCheckingEnabled);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblCita = new javax.swing.JTable();
        btnBuscar = new javax.swing.JButton();
        txtFolio = new javax.swing.JTextField();
        txtPaciente = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        dtFecha = new com.toedter.calendar.JDateChooser();
        jLabel6 = new javax.swing.JLabel();
        casillaFecha = new javax.swing.JCheckBox();
        MenuPleglable1 = new javax.swing.JPanel();
        lblBack1 = new javax.swing.JLabel();
        lblPaciente1 = new javax.swing.JLabel();
        lblCitas1 = new javax.swing.JLabel();
        lblExpediente1 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        lblMenu1 = new javax.swing.JLabel();
        lblPass1 = new javax.swing.JLabel();
        jSeparator4 = new javax.swing.JSeparator();
        jSeparator5 = new javax.swing.JSeparator();
        jSeparator6 = new javax.swing.JSeparator();
        jSeparator7 = new javax.swing.JSeparator();
        jSeparator8 = new javax.swing.JSeparator();
        panel1 = new java.awt.Panel();
        jLabel12 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setMinimumSize(new java.awt.Dimension(1180, 1242));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel3.setBackground(new java.awt.Color(69, 204, 209));

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel4.setText("Folio");

        tblCita.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        tblCita.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Folio", "Paciente", "Fecha"
            }
        ));
        tblCita.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblCitaMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tblCita);

        btnBuscar.setText("Buscar");
        btnBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarActionPerformed(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel5.setText("Paciente");

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel6.setText("Fecha");

        casillaFecha.setOpaque(false);
        casillaFecha.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                casillaFechaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(35, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtFolio, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtPaciente, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(dtFecha, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(casillaFecha)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnBuscar))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 795, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel4)
                        .addComponent(txtFolio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnBuscar)
                        .addComponent(jLabel5)
                        .addComponent(txtPaciente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel6))
                    .addComponent(dtFecha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(casillaFecha))
                .addGap(15, 15, 15)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 438, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(31, Short.MAX_VALUE))
        );

        jPanel1.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 120, 860, 530));

        MenuPleglable1.setBackground(new java.awt.Color(69, 204, 209));
        MenuPleglable1.setMinimumSize(new java.awt.Dimension(0, 577));
        MenuPleglable1.setLayout(null);

        lblBack1.setBackground(new java.awt.Color(0, 0, 0));
        lblBack1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lblBack1.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblBack1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/icons8-volver-4.png"))); // NOI18N
        lblBack1.setText("Regresar           ");
        lblBack1.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 50));
        lblBack1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        lblBack1.setHorizontalTextPosition(javax.swing.SwingConstants.LEADING);
        lblBack1.setIconTextGap(10);
        lblBack1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblBack1MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                lblBack1MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                lblBack1MouseExited(evt);
            }
        });
        MenuPleglable1.add(lblBack1);
        lblBack1.setBounds(0, 650, 310, 70);

        lblPaciente1.setBackground(new java.awt.Color(0, 0, 0));
        lblPaciente1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lblPaciente1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblPaciente1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/icons8-añadir-usuario-masculino-52.png"))); // NOI18N
        lblPaciente1.setText("Registro de Paciente ");
        lblPaciente1.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 50));
        lblPaciente1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        lblPaciente1.setHorizontalTextPosition(javax.swing.SwingConstants.LEADING);
        lblPaciente1.setIconTextGap(10);
        lblPaciente1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblPaciente1MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                lblPaciente1MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                lblPaciente1MouseExited(evt);
            }
        });
        MenuPleglable1.add(lblPaciente1);
        lblPaciente1.setBounds(20, 180, 290, 54);

        lblCitas1.setBackground(new java.awt.Color(0, 0, 0));
        lblCitas1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lblCitas1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblCitas1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/icons8-planificación-de-tareas-52.png"))); // NOI18N
        lblCitas1.setText("Gestión de Citas      ");
        lblCitas1.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 50));
        lblCitas1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        lblCitas1.setHorizontalTextPosition(javax.swing.SwingConstants.LEADING);
        lblCitas1.setIconTextGap(10);
        lblCitas1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblCitas1MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                lblCitas1MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                lblCitas1MouseExited(evt);
            }
        });
        MenuPleglable1.add(lblCitas1);
        lblCitas1.setBounds(0, 310, 330, 54);

        lblExpediente1.setBackground(new java.awt.Color(0, 0, 0));
        lblExpediente1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lblExpediente1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblExpediente1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/icons8-docket-52.png"))); // NOI18N
        lblExpediente1.setText("Expediente Clínico    ");
        lblExpediente1.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 50));
        lblExpediente1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        lblExpediente1.setHorizontalTextPosition(javax.swing.SwingConstants.LEADING);
        lblExpediente1.setIconTextGap(10);
        lblExpediente1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblExpediente1MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                lblExpediente1MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                lblExpediente1MouseExited(evt);
            }
        });
        MenuPleglable1.add(lblExpediente1);
        lblExpediente1.setBounds(0, 430, 330, 54);

        jLabel9.setBackground(new java.awt.Color(0, 0, 0));
        jLabel9.setFont(new java.awt.Font("Times New Roman", 2, 24)); // NOI18N
        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel9.setText("Opciones");
        jLabel9.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 50));
        jLabel9.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jLabel9.setHorizontalTextPosition(javax.swing.SwingConstants.LEADING);
        jLabel9.setIconTextGap(10);
        jLabel9.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jLabel9MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jLabel9MouseExited(evt);
            }
        });
        MenuPleglable1.add(jLabel9);
        jLabel9.setBounds(0, 10, 270, 50);

        lblMenu1.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblMenu1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/menu (4).png"))); // NOI18N
        lblMenu1.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 10));
        lblMenu1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        lblMenu1.setIconTextGap(0);
        lblMenu1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblMenu1MouseClicked(evt);
            }
        });
        MenuPleglable1.add(lblMenu1);
        lblMenu1.setBounds(190, 10, 80, 50);

        lblPass1.setBackground(new java.awt.Color(0, 0, 0));
        lblPass1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lblPass1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblPass1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/icons8-contraseña-52.png"))); // NOI18N
        lblPass1.setText("Gestion Contraseñas ");
        lblPass1.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 50));
        lblPass1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        lblPass1.setHorizontalTextPosition(javax.swing.SwingConstants.LEADING);
        lblPass1.setIconTextGap(10);
        lblPass1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblPass1MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                lblPass1MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                lblPass1MouseExited(evt);
            }
        });
        MenuPleglable1.add(lblPass1);
        lblPass1.setBounds(10, 550, 310, 54);
        MenuPleglable1.add(jSeparator4);
        jSeparator4.setBounds(0, 640, 270, 30);
        MenuPleglable1.add(jSeparator5);
        jSeparator5.setBounds(0, 160, 270, 30);
        MenuPleglable1.add(jSeparator6);
        jSeparator6.setBounds(0, 280, 270, 10);
        MenuPleglable1.add(jSeparator7);
        jSeparator7.setBounds(0, 400, 270, 30);
        MenuPleglable1.add(jSeparator8);
        jSeparator8.setBounds(0, 520, 270, 30);

        jPanel1.add(MenuPleglable1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 269, 730));

        panel1.setBackground(new java.awt.Color(0, 108, 183));

        jLabel12.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(255, 255, 255));
        jLabel12.setText("Gestion Expediente Clínico");

        javax.swing.GroupLayout panel1Layout = new javax.swing.GroupLayout(panel1);
        panel1.setLayout(panel1Layout);
        panel1Layout.setHorizontalGroup(
            panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panel1Layout.createSequentialGroup()
                .addContainerGap(314, Short.MAX_VALUE)
                .addComponent(jLabel12)
                .addGap(305, 305, 305))
        );
        panel1Layout.setVerticalGroup(
            panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panel1Layout.createSequentialGroup()
                .addContainerGap(22, Short.MAX_VALUE)
                .addComponent(jLabel12)
                .addGap(19, 19, 19))
        );

        jPanel1.add(panel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 0, 940, 70));

        jPanel4.setBackground(new java.awt.Color(0, 108, 183));

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 940, Short.MAX_VALUE)
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 40, Short.MAX_VALUE)
        );

        jPanel1.add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 690, 940, 40));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 1180, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 726, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tblCitaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblCitaMouseClicked


    }//GEN-LAST:event_tblCitaMouseClicked

    private void lblBack1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblBack1MouseClicked

        MenuDentista login = new MenuDentista();
        login.setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_lblBack1MouseClicked

    private void lblBack1MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblBack1MouseEntered
        lblBack1.setForeground(Color.GRAY);
    }//GEN-LAST:event_lblBack1MouseEntered

    private void lblBack1MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblBack1MouseExited
        lblBack1.setForeground(Color.BLACK);
    }//GEN-LAST:event_lblBack1MouseExited

    private void lblPaciente1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblPaciente1MouseClicked
        VentanaPaciente next = new VentanaPaciente();
        next.setVisible(true);
        next.setLocationRelativeTo(null);
        this.dispose();
    }//GEN-LAST:event_lblPaciente1MouseClicked

    private void lblPaciente1MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblPaciente1MouseEntered
        lblPaciente1.setForeground(Color.BLACK);
    }//GEN-LAST:event_lblPaciente1MouseEntered

    private void lblPaciente1MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblPaciente1MouseExited
        lblPaciente1.setForeground(Color.GRAY);
    }//GEN-LAST:event_lblPaciente1MouseExited

    private void lblCitas1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblCitas1MouseClicked
        VentanaCitas exp = new VentanaCitas();
        exp.setVisible(true);
        exp.setLocationRelativeTo(null);
        this.dispose();
    }//GEN-LAST:event_lblCitas1MouseClicked

    private void lblCitas1MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblCitas1MouseEntered
        lblCitas1.setForeground(Color.GRAY);
    }//GEN-LAST:event_lblCitas1MouseEntered

    private void lblCitas1MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblCitas1MouseExited
        lblCitas1.setForeground(Color.BLACK);
    }//GEN-LAST:event_lblCitas1MouseExited

    private void lblExpediente1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblExpediente1MouseClicked
        VentanaExpediente exp = new VentanaExpediente();
        exp.setVisible(true);
        exp.setLocationRelativeTo(null);
        this.dispose();
    }//GEN-LAST:event_lblExpediente1MouseClicked

    private void lblExpediente1MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblExpediente1MouseEntered
        lblExpediente1.setForeground(Color.GRAY);
    }//GEN-LAST:event_lblExpediente1MouseEntered

    private void lblExpediente1MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblExpediente1MouseExited
        lblExpediente1.setForeground(Color.BLACK);
    }//GEN-LAST:event_lblExpediente1MouseExited

    private void jLabel9MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel9MouseEntered
        //lblExpediente.setForeground(Color.BLUE);
    }//GEN-LAST:event_jLabel9MouseEntered

    private void jLabel9MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel9MouseExited
        //lblExpediente.setForeground(Color.BLACK);
    }//GEN-LAST:event_jLabel9MouseExited

    private void lblMenu1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblMenu1MouseClicked
        if (MenuPleglable1.getX() == 0)
            desplace.desplazarIzquierda(this.MenuPleglable1, MenuPleglable1.getX(), -180, 10, 10);
        else if (MenuPleglable1.getX() == -180)
            desplace.desplazarDerecha(this.MenuPleglable1, MenuPleglable1.getX(), 0, 10, 10);
    }//GEN-LAST:event_lblMenu1MouseClicked

    private void lblPass1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblPass1MouseClicked
        Gestionar_Contraseñas pass = new Gestionar_Contraseñas();
        pass.setVisible(true);
        pass.setLocationRelativeTo(null);
        this.dispose();
    }//GEN-LAST:event_lblPass1MouseClicked

    private void lblPass1MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblPass1MouseEntered
        lblPass1.setForeground(Color.GRAY);
    }//GEN-LAST:event_lblPass1MouseEntered

    private void lblPass1MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblPass1MouseExited
        lblPass1.setForeground(Color.BLACK);
    }//GEN-LAST:event_lblPass1MouseExited
    private boolean isDateChooserNotEmpty(JDateChooser dateChooser) {
        Date selectedDate = dateChooser.getDate();
        return selectedDate != null;
    }
    private void btnBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarActionPerformed


    }//GEN-LAST:event_btnBuscarActionPerformed

    private void casillaFechaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_casillaFechaActionPerformed
        
    }//GEN-LAST:event_casillaFechaActionPerformed

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
                if ("Windows".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(VentanaCitas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(VentanaCitas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(VentanaCitas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(VentanaCitas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new VentanaExpedienteMenu().setVisible(true);
            }
        });
    }
    private DefaultTableModel m;
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel MenuPleglable1;
    private javax.swing.JButton btnBuscar;
    private javax.swing.JCheckBox casillaFecha;
    private com.toedter.calendar.JDateChooser dtFecha;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JSeparator jSeparator5;
    private javax.swing.JSeparator jSeparator6;
    private javax.swing.JSeparator jSeparator7;
    private javax.swing.JSeparator jSeparator8;
    private javax.swing.JLabel lblBack1;
    private javax.swing.JLabel lblCitas1;
    private javax.swing.JLabel lblExpediente1;
    private javax.swing.JLabel lblMenu1;
    private javax.swing.JLabel lblPaciente1;
    private javax.swing.JLabel lblPass1;
    private java.awt.Panel panel1;
    private javax.swing.JTable tblCita;
    private javax.swing.JTextField txtFolio;
    private javax.swing.JTextField txtPaciente;
    // End of variables declaration//GEN-END:variables
}
