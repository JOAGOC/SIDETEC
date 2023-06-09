package GUI;

import Conexión.Conexión;
import com.toedter.calendar.JDateChooser;

import Componentes.ColorRenderer;
import Componentes.DTable;
import Componentes.TagInputControl;
import static Interfaces.CRUD.cargarAutocompletar;
import desplazable.Desface;
import java.awt.Color;
import java.awt.Font;
import java.awt.HeadlessException;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import javax.swing.JOptionPane;
import static javax.swing.JOptionPane.showMessageDialog;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Tzihual
 */
public class VentanaCitasSecre extends javax.swing.JFrame {
    ColorRenderer colorRenderer = new ColorRenderer();
    Desface desplace;
    JTableHeader th;

    public VentanaCitasSecre() {
        initComponents();
        this.cargarDatos();
        setLocationRelativeTo(null);
        desplace = new Desface();
        fecha.setMinSelectableDate(new Date());
        int columnaEstado = 2; // reemplazar con el índice de la columna que contiene los estados
        tblCita.getColumnModel().getColumn(columnaEstado).setCellRenderer(colorRenderer);
        lblConfirmar.setVisible(false);
        lblFinalizar.setVisible(false);
        lblGuardar.setVisible(true);
        lblEliminar.setVisible(false);
        lblModificar.setVisible(false);
        lblDurante.setVisible(false);
        lblBloquear.setVisible(true);
        lblDisponible.setVisible(false);

        tblCita.getColumnModel().getColumn(0).setHeaderRenderer(new DTable(new Color(230, 192, 233), Color.BLACK));
        tblCita.getColumnModel().getColumn(1).setHeaderRenderer(new DTable(new Color(230, 192, 233), Color.BLACK));
        tblCita.getColumnModel().getColumn(2).setHeaderRenderer(new DTable(new Color(230, 192, 233), Color.BLACK));
        tblCita.getColumnModel().getColumn(3).setHeaderRenderer(new DTable(new Color(230, 192, 233), Color.BLACK));
        tblCita.getColumnModel().getColumn(4).setHeaderRenderer(new DTable(new Color(230, 192, 233), Color.BLACK));
        tblCita.getColumnModel().getColumn(5).setHeaderRenderer(new DTable(new Color(230, 192, 233), Color.BLACK));
        tblCita.getColumnModel().getColumn(6).setHeaderRenderer(new DTable(new Color(230, 192, 233), Color.BLACK));
        th = tblCita.getTableHeader();
        Font fuente = new Font("Arial", Font.BOLD, 16);
        th.setFont(fuente);

        cargarAutocompletar(
                "SELECT CONCAT(nombre, ' ', apellido, ' - ', telefono) FROM pacientes ORDER BY CONCAT(nombre, ' ', apellido)",
                cmbPaciente);

    }

    // <editor-fold defaultstate="collapsed" desc="Generated
    // Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        autoCompleteTextField1 = new Componentes.AutoCompleteTextField();
        jPanel1 = new javax.swing.JPanel();
        MenuPleglable1 = new javax.swing.JPanel();
        lblBack1 = new javax.swing.JLabel();
        lblPaciente1 = new javax.swing.JLabel();
        lblCitas1 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        lblMenu1 = new javax.swing.JLabel();
        jSeparator4 = new javax.swing.JSeparator();
        jSeparator5 = new javax.swing.JSeparator();
        jSeparator6 = new javax.swing.JSeparator();
        jSeparator7 = new javax.swing.JSeparator();
        jImageBox2 = new Componentes.JImageBox();
        panel1 = new java.awt.Panel();
        jLabel10 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        fecha = new com.toedter.calendar.JDateChooser();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblCita = new javax.swing.JTable();
        lblBuscar = new Componentes.JImageBox();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        lblDetalle = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        txtID = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        txtNombre = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        txtApellido = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        txtTelefono = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        txtDiaCita = new javax.swing.JTextField();
        txtHoraCita = new javax.swing.JTextField();
        tagInputControl1 = new Componentes.TagInputControl();
        cmbPaciente = new Componentes.AutoCompleteTextField();
        btnBuscar1 = new Componentes.JImageBox();
        jLabel16 = new javax.swing.JLabel();
        txtDuracion = new javax.swing.JTextField();
        lblDuracion = new javax.swing.JLabel();
        lblGuardar = new Componentes.JImageBox();
        lblEliminar = new Componentes.JImageBox();
        lblModificar = new Componentes.JImageBox();
        lblConfirmar = new Componentes.JImageBox();
        lblDurante = new Componentes.JImageBox();
        lblBloquear = new Componentes.JImageBox();
        lblFinalizar = new Componentes.JImageBox();
        lblDisponible = new Componentes.JImageBox();
        lblLimpiar = new javax.swing.JLabel();

        autoCompleteTextField1.setItems(new String[] { "Limpieza dental", "Dolor de muelas", "Caries dental",
                "Extracción dental", "Blanqueamiento dental", "Ortodoncia", "Diente astillado", "Problemas de encías",
                "Revisión de rutina", "Problemas de mordida", "Prótesis dental", "Inflamación dental",
                "Sensibilidad dental", "Implante dental" });

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setMinimumSize(new java.awt.Dimension(1180, 1242));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        MenuPleglable1.setBackground(new java.awt.Color(69, 204, 209));
        MenuPleglable1.setMinimumSize(new java.awt.Dimension(0, 577));
        MenuPleglable1.setLayout(null);

        lblBack1.setBackground(new java.awt.Color(0, 0, 0));
        lblBack1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lblBack1.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblBack1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/flecha-izquierda90.png"))); // NOI18N
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
        lblBack1.setBounds(0, 800, 310, 70);

        lblPaciente1.setBackground(new java.awt.Color(0, 0, 0));
        lblPaciente1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lblPaciente1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblPaciente1.setIcon(
                new javax.swing.ImageIcon(getClass().getResource("/img/icons8-añadir-usuario-masculino-52.png"))); // NOI18N
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
        lblPaciente1.setBounds(20, 180, 279, 54);

        lblCitas1.setBackground(new java.awt.Color(0, 0, 0));
        lblCitas1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lblCitas1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblCitas1.setIcon(
                new javax.swing.ImageIcon(getClass().getResource("/img/icons8-planificación-de-tareas-52.png"))); // NOI18N
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

        jLabel9.setBackground(new java.awt.Color(0, 0, 0));
        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel9.setText("      OPCIONES   ");
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
        MenuPleglable1.add(jSeparator4);
        jSeparator4.setBounds(0, 640, 270, 30);
        MenuPleglable1.add(jSeparator5);
        jSeparator5.setBounds(0, 160, 270, 30);
        MenuPleglable1.add(jSeparator6);
        jSeparator6.setBounds(0, 280, 270, 10);
        MenuPleglable1.add(jSeparator7);
        jSeparator7.setBounds(0, 400, 270, 30);
        MenuPleglable1.add(jImageBox2);
        jImageBox2.setBounds(50, 70, 170, 0);

        jPanel1.add(MenuPleglable1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 269, 870));

        panel1.setBackground(new java.awt.Color(0, 108, 183));

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setText("Gestión de Citas");

        javax.swing.GroupLayout panel1Layout = new javax.swing.GroupLayout(panel1);
        panel1.setLayout(panel1Layout);
        panel1Layout.setHorizontalGroup(
                panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panel1Layout.createSequentialGroup()
                                .addContainerGap(527, Short.MAX_VALUE)
                                .addComponent(jLabel10)
                                .addGap(340, 340, 340)));
        panel1Layout.setVerticalGroup(
                panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(panel1Layout.createSequentialGroup()
                                .addGap(21, 21, 21)
                                .addComponent(jLabel10)
                                .addContainerGap(20, Short.MAX_VALUE)));

        jPanel1.add(panel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 0, 1060, 70));

        jPanel4.setBackground(new java.awt.Color(0, 108, 183));

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
                jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 1050, Short.MAX_VALUE));
        jPanel4Layout.setVerticalGroup(
                jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 40, Short.MAX_VALUE));

        jPanel1.add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 830, 1050, 40));

        jPanel3.setBackground(new java.awt.Color(69, 204, 209));

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel4.setText("Fecha:");

        fecha.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                fechaMouseClicked(evt);
            }
        });

        tblCita.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        tblCita.setModel(new javax.swing.table.DefaultTableModel(
                new Object[][] {

                },
                new String[] {
                        "No. Fecha", "Hora", "Estatus", "Nombre paciente", "Telefono", "Detalle Cita", "Duración"
                }));
        tblCita.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblCitaMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tblCita);

        lblBuscar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/lupa.png"))); // NOI18N
        lblBuscar.setToolTipText("Cita Finalizada");
        lblBuscar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblBuscarMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
                jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGap(53, 53, 53)
                                .addComponent(jLabel4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(fecha, javax.swing.GroupLayout.PREFERRED_SIZE, 189,
                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(lblBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 40,
                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                                .addContainerGap(22, Short.MAX_VALUE)
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 964,
                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(24, 24, 24)));
        jPanel3Layout.setVerticalGroup(
                jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(jPanel3Layout.createSequentialGroup()
                                                .addGap(22, 22, 22)
                                                .addGroup(jPanel3Layout
                                                        .createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(fecha, javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(jLabel4)))
                                        .addGroup(jPanel3Layout.createSequentialGroup()
                                                .addContainerGap()
                                                .addComponent(lblBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 46,
                                                        javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(13, 13, 13)
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 241,
                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(17, Short.MAX_VALUE)));

        jPanel1.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 120, 1010, 330));

        jPanel2.setBackground(new java.awt.Color(69, 204, 209));

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel2.setText("Paciente:");

        lblDetalle.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        lblDetalle.setText("Detalle:");

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel6.setText("ID:");
        jLabel6.setEnabled(false);

        txtID.setEditable(false);
        txtID.setEnabled(false);

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel8.setText("Nombre:");
        jLabel8.setEnabled(false);

        txtNombre.setEditable(false);
        txtNombre.setEnabled(false);

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel7.setText("Apellido:");
        jLabel7.setEnabled(false);

        txtApellido.setEditable(false);
        txtApellido.setEnabled(false);

        jLabel11.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel11.setText("Teléfono:");
        jLabel11.setEnabled(false);

        txtTelefono.setEditable(false);
        txtTelefono.setEnabled(false);

        jLabel14.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel14.setText("Día de la cita:");
        jLabel14.setEnabled(false);

        jLabel15.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel15.setText("Hora de la cita:");
        jLabel15.setEnabled(false);

        txtDiaCita.setEnabled(false);

        txtHoraCita.setEnabled(false);
        txtHoraCita.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtHoraCitaActionPerformed(evt);
            }
        });

        tagInputControl1.setTagInputField(autoCompleteTextField1);

        cmbPaciente.setItems(new String[] { "Limpieza dental", "Dolor de muelas", "Caries dental", "Extracción dental",
                "Blanqueamiento dental", "Ortodoncia", "Diente astillado", "Problemas de encías", "Revisión de rutina",
                "Problemas de mordida", "Prótesis dental", "Inflamación dental", "Sensibilidad dental",
                "Implante dental" });

        btnBuscar1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/lupa.png"))); // NOI18N
        btnBuscar1.setToolTipText("Cita Finalizada");
        btnBuscar1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnBuscar1MouseClicked(evt);
            }
        });

        jLabel16.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel16.setText("horas");
        jLabel16.setEnabled(false);

        lblDuracion.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        lblDuracion.setText("Duración:");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
                jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(39, 39, 39)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(jPanel2Layout.createSequentialGroup()
                                                .addGroup(jPanel2Layout
                                                        .createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                        .addComponent(jLabel8)
                                                        .addComponent(jLabel6))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addGroup(jPanel2Layout
                                                        .createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(txtID, javax.swing.GroupLayout.PREFERRED_SIZE, 74,
                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addGroup(jPanel2Layout.createSequentialGroup()
                                                                .addComponent(txtNombre,
                                                                        javax.swing.GroupLayout.PREFERRED_SIZE, 204,
                                                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addGap(18, 18, 18)
                                                                .addGroup(jPanel2Layout.createParallelGroup(
                                                                        javax.swing.GroupLayout.Alignment.LEADING)
                                                                        .addGroup(jPanel2Layout.createSequentialGroup()
                                                                                .addComponent(jLabel14)
                                                                                .addPreferredGap(
                                                                                        javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                                .addComponent(txtDiaCita,
                                                                                        javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                        106,
                                                                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                .addGap(18, 18, 18)
                                                                                .addComponent(jLabel15)
                                                                                .addGap(18, 18, 18)
                                                                                .addComponent(txtHoraCita,
                                                                                        javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                        96,
                                                                                        javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                        .addGroup(jPanel2Layout.createSequentialGroup()
                                                                                .addComponent(jLabel7)
                                                                                .addPreferredGap(
                                                                                        javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                                .addComponent(txtApellido,
                                                                                        javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                        206,
                                                                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                .addGap(19, 19, 19)
                                                                                .addComponent(jLabel11)
                                                                                .addPreferredGap(
                                                                                        javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                                .addComponent(txtTelefono,
                                                                                        javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                        204,
                                                                                        javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                        .addGroup(jPanel2Layout.createSequentialGroup()
                                                                                .addComponent(lblDuracion)
                                                                                .addPreferredGap(
                                                                                        javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                                .addComponent(txtDuracion,
                                                                                        javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                        106,
                                                                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                .addPreferredGap(
                                                                                        javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                                                .addComponent(jLabel16))))))
                                        .addComponent(jLabel5)
                                        .addGroup(jPanel2Layout.createSequentialGroup()
                                                .addComponent(lblDetalle)
                                                .addGap(18, 18, 18)
                                                .addComponent(tagInputControl1, javax.swing.GroupLayout.PREFERRED_SIZE,
                                                        javax.swing.GroupLayout.DEFAULT_SIZE,
                                                        javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(jPanel2Layout.createSequentialGroup()
                                                .addComponent(jLabel2)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(cmbPaciente, javax.swing.GroupLayout.PREFERRED_SIZE, 200,
                                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(btnBuscar1, javax.swing.GroupLayout.PREFERRED_SIZE,
                                                        javax.swing.GroupLayout.DEFAULT_SIZE,
                                                        javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(260, 260, Short.MAX_VALUE)));
        jPanel2Layout.setVerticalGroup(
                jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout
                                        .createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addGroup(jPanel2Layout.createSequentialGroup()
                                                .addContainerGap()
                                                .addGroup(jPanel2Layout
                                                        .createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                        .addComponent(jLabel2)
                                                        .addComponent(cmbPaciente,
                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                javax.swing.GroupLayout.PREFERRED_SIZE)))
                                        .addComponent(btnBuscar1, javax.swing.GroupLayout.Alignment.TRAILING,
                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(jPanel2Layout.createSequentialGroup()
                                                .addGap(28, 28, 28)
                                                .addComponent(jLabel5))
                                        .addGroup(jPanel2Layout.createSequentialGroup()
                                                .addGap(18, 18, 18)
                                                .addGroup(jPanel2Layout
                                                        .createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                        .addComponent(jLabel6)
                                                        .addComponent(txtID, javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                javax.swing.GroupLayout.PREFERRED_SIZE))))
                                .addGap(8, 8, 8)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(jPanel2Layout.createSequentialGroup()
                                                .addGroup(jPanel2Layout
                                                        .createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                        .addComponent(jLabel8)
                                                        .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addGap(20, 20, 20)
                                                .addGroup(jPanel2Layout
                                                        .createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(lblDetalle)
                                                        .addComponent(tagInputControl1,
                                                                javax.swing.GroupLayout.PREFERRED_SIZE, 77,
                                                                javax.swing.GroupLayout.PREFERRED_SIZE)))
                                        .addGroup(jPanel2Layout.createSequentialGroup()
                                                .addGroup(jPanel2Layout
                                                        .createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                        .addComponent(jLabel7)
                                                        .addComponent(txtApellido,
                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(jLabel11)
                                                        .addComponent(txtTelefono,
                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addGap(20, 20, 20)
                                                .addGroup(jPanel2Layout
                                                        .createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                        .addComponent(jLabel14)
                                                        .addComponent(txtDiaCita,
                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(jLabel15)
                                                        .addComponent(txtHoraCita,
                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addGap(18, 18, 18)
                                                .addGroup(jPanel2Layout
                                                        .createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addGroup(jPanel2Layout
                                                                .createParallelGroup(
                                                                        javax.swing.GroupLayout.Alignment.BASELINE)
                                                                .addComponent(txtDuracion,
                                                                        javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                        javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addComponent(lblDuracion))
                                                        .addComponent(jLabel16))))
                                .addContainerGap(21, Short.MAX_VALUE)));

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 470, 1010, 240));

        lblGuardar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/cita-medica (1).png"))); // NOI18N
        lblGuardar.setToolTipText("Guardar Cita");
        lblGuardar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblGuardarMouseClicked(evt);
            }
        });
        jPanel1.add(lblGuardar, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 730, 90, -1));

        lblEliminar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/borrar.png"))); // NOI18N
        lblEliminar.setToolTipText("Eliminar Cita");
        lblEliminar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblEliminarMouseClicked(evt);
            }
        });
        jPanel1.add(lblEliminar, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 720, 100, -1));

        lblModificar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/editar1.png"))); // NOI18N
        lblModificar.setToolTipText("Modificar");
        lblModificar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblModificarMouseClicked(evt);
            }
        });
        jPanel1.add(lblModificar, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 720, -1, 90));

        lblConfirmar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/fecha-del-calendario.png"))); // NOI18N
        lblConfirmar.setToolTipText("Confirmar Cita");
        lblConfirmar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblConfirmarMouseClicked(evt);
            }
        });
        jPanel1.add(lblConfirmar, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 730, 80, -1));

        lblDurante.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/calendario2.png"))); // NOI18N
        lblDurante.setToolTipText("Durante");
        lblDurante.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblDuranteMouseClicked(evt);
            }
        });
        jPanel1.add(lblDurante, new org.netbeans.lib.awtextra.AbsoluteConstraints(780, 730, 90, -1));

        lblBloquear.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/cuaderno.png"))); // NOI18N
        lblBloquear.setToolTipText("Cita bloqueada");
        lblBloquear.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblBloquearMouseClicked(evt);
            }
        });
        jPanel1.add(lblBloquear, new org.netbeans.lib.awtextra.AbsoluteConstraints(900, 720, 90, -1));

        lblFinalizar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/terminado.png"))); // NOI18N
        lblFinalizar.setToolTipText("Cita Finalizada");
        lblFinalizar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblFinalizarMouseClicked(evt);
            }
        });
        jPanel1.add(lblFinalizar, new org.netbeans.lib.awtextra.AbsoluteConstraints(1010, 720, 90, 100));

        lblDisponible.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/cuaderno3.png"))); // NOI18N
        lblDisponible.setToolTipText("Disponible");
        lblDisponible.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblDisponibleMouseClicked(evt);
            }
        });
        jPanel1.add(lblDisponible, new org.netbeans.lib.awtextra.AbsoluteConstraints(1130, 720, -1, 90));

        lblLimpiar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/icons8-clear-90.png"))); // NOI18N
        lblLimpiar.setToolTipText("Limpiar");
        lblLimpiar.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        lblLimpiar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblLimpiarMouseClicked(evt);
            }
        });
        jPanel1.add(lblLimpiar, new org.netbeans.lib.awtextra.AbsoluteConstraints(1230, 730, 80, 70));

        lblBloquear.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/cuaderno.png"))); // NOI18N
        lblBloquear.setToolTipText("Cita bloqueada");
        lblBloquear.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblBloquearMouseClicked(evt);
            }
        });
        jPanel1.add(lblBloquear, new org.netbeans.lib.awtextra.AbsoluteConstraints(900, 720, 90, -1));

        lblFinalizar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/terminado.png"))); // NOI18N
        lblFinalizar.setToolTipText("Cita Finalizada");
        lblFinalizar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblFinalizarMouseClicked(evt);
            }
        });
        jPanel1.add(lblFinalizar, new org.netbeans.lib.awtextra.AbsoluteConstraints(1010, 720, 90, 100));

        lblDisponible.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/cuaderno3.png"))); // NOI18N
        lblDisponible.setToolTipText("Disponible");
        lblDisponible.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblDisponibleMouseClicked(evt);
            }
        });
        jPanel1.add(lblDisponible, new org.netbeans.lib.awtextra.AbsoluteConstraints(1130, 720, -1, 90));

        lblLimpiar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/icons8-clear-90.png"))); // NOI18N
        lblLimpiar.setToolTipText("Limpiar");
        lblLimpiar.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        lblLimpiar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblLimpiarMouseClicked(evt);
            }
        });
        jPanel1.add(lblLimpiar, new org.netbeans.lib.awtextra.AbsoluteConstraints(1230, 730, 80, 70));

        jScrollPane1.setViewportView(jPanel1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 1331, Short.MAX_VALUE));
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING,
                                javax.swing.GroupLayout.DEFAULT_SIZE, 768, Short.MAX_VALUE));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    public void cargarDatos() {
        try {
            Connection con1 = null;
            DefaultTableModel m = (DefaultTableModel) tblCita.getModel();
            con1 = Conexión.getConnection();
            String dts[] = new String[7];
            String sql = "SELECT gestion_cita.fecha, gestion_cita.horario, gestion_cita.estatus, " +
                    "CONCAT(pacientes.nombre, ' ', pacientes.apellido) AS 'Nombre Completo', " +
                    "pacientes.telefono AS 'Telefono', gestion_cita.detalleCita AS 'Detalle Cita',gestion_cita.duracion AS 'Duración' "
                    +
                    "FROM gestion_cita LEFT JOIN pacientes ON gestion_cita.idPaciente = pacientes.id where fecha=current_date()";

            Statement st = con1.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                dts[0] = rs.getString("gestion_cita.fecha");
                dts[1] = rs.getString("gestion_cita.horario");
                dts[2] = rs.getString("gestion_cita.estatus");
                dts[3] = rs.getString("Nombre Completo");
                dts[4] = rs.getString("pacientes.telefono");
                dts[5] = rs.getString("Detalle Cita");
                dts[6] = rs.getString("Duración");
                m.addRow(dts);
            }
            int columnaEstado = 2; // reemplazar con el índice de la columna que contiene los estados
            tblCita.getColumnModel().getColumn(columnaEstado).setCellRenderer(colorRenderer);
            tblCita.setModel(m);
            this.colorTabla();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "NO SE PUEDEN VISUALIZAR LOS DATOS DE LA TABLA " + e, "Error",
                    JOptionPane.ERROR_MESSAGE);

        }
    }// cargarDatos

    public void cargarDatos2() {
        try {
            Date fechaSeleccionada = fecha.getDate();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-d");
            String fechaFormateada = sdf.format(fechaSeleccionada);

            Connection con1 = null;
            DefaultTableModel m = (DefaultTableModel) tblCita.getModel();
            m.setRowCount(0); // Limpiar todas las filas existentes de la tabla antes de cargar los nuevos
                              // datos

            con1 = Conexión.getConnection();

            String dts[] = new String[7];
            String sql = "SELECT gestion_cita.fecha, gestion_cita.horario, gestion_cita.estatus, " +
                    "CONCAT(pacientes.nombre, ' ', pacientes.apellido) AS 'Nombre Completo', " +
                    "pacientes.telefono AS 'Telefono', gestion_cita.detalleCita AS 'Detalle Cita', gestion_cita.duracion AS 'Duración'  "
                    +
                    "FROM gestion_cita LEFT JOIN pacientes ON gestion_cita.idPaciente = pacientes.id " +
                    "WHERE fecha = '" + fechaFormateada + "'";
            Statement st = con1.createStatement();
            ResultSet rs = st.executeQuery(sql);

            while (rs.next()) {
                dts[0] = rs.getString("gestion_cita.fecha");
                dts[1] = rs.getString("gestion_cita.horario");
                dts[2] = rs.getString("gestion_cita.estatus");
                dts[3] = rs.getString("Nombre Completo");
                dts[4] = rs.getString("pacientes.telefono");
                dts[5] = rs.getString("Detalle Cita");
                dts[6] = rs.getString("Duración");
                m.addRow(dts);
            }
            int columnaEstado = 2; // reemplazar con el índice de la columna que contiene los estados
            tblCita.getColumnModel().getColumn(columnaEstado).setCellRenderer(colorRenderer);
            tblCita.setModel(m);
            this.colorTabla();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "NO SE PUEDEN VISUALIZAR LOS DATOS DE LA TABLA " + e, "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    void llenarCamposTexto(String[] datos) {
        txtID.setText(datos[0]);
        txtNombre.setText(datos[1]);
        txtApellido.setText(datos[2]);
        txtTelefono.setText(datos[3]);
        // txtDuracion.setText(datos[6]);
    }

    public void limpiar() {
        this.txtID.setText("");
        this.txtNombre.setText("");
        this.txtApellido.setText("");
        this.txtHoraCita.setText("");
        this.txtDiaCita.setText("");
        this.txtTelefono.setText("");
        this.tagInputControl1.clear();
        this.cmbPaciente.setText("");
        this.txtDuracion.setText("");
        // txtBuscar.requestFocus();
    }// limpiar

    public void vaciarTabla() {
        DefaultTableModel m = (DefaultTableModel) tblCita.getModel();
        String titulos[] = { "No. Fecha", "Hora", "Estatus", "Nombre del paciente", "Telefono", "Detalle cita",
                "Duración" };
        m = new DefaultTableModel(null, titulos);
        tblCita.setModel(m);
    }// vaciarTabla

    private void tagsVacio(TagInputControl c) throws Exception {
        if (c.getTags().isEmpty())
            throw new Exception("Motivo vacío");
    }

    private boolean validaMotivo(TagInputControl c) {
        try {
            tagsVacio(c);
        } catch (Exception e) {
            showMessageDialog(this, e.getMessage());
            c.requestFocus();
            return true;
        }
        return false;
    }

    private boolean validaCampo(JTextField t) {
        try {
            estaVacio(t);
        } catch (Exception e) {
            showMessageDialog(this, e.getMessage());
            t.requestFocus();
            return true;
        }
        return false;
    }

    private void estaVacio(JTextField t) throws Exception {
        String cad = t.getText().trim();
        if (cad.equals(""))
            throw new Exception("Campo vacio");
    }

    void Buscar(String valor) {

        Connection con = null;
        
        con = Conexión.getConnection();

        String[] palabras = valor.split(" ");
        String sql = "SELECT id, nombre, apellido, telefono FROM pacientes WHERE ";

        for (int i = 0; i < palabras.length; i++) {
            if (i > 0) {
                sql += " OR ";
            }
            sql += "(CONCAT(nombre, ' ', apellido) LIKE ? OR telefono LIKE ?)";
        }

        try {
            PreparedStatement pst = con.prepareStatement(sql);
            for (int i = 0; i < palabras.length; i++) {
                pst.setString(2 * i + 1, "%" + palabras[i] + "%");
                pst.setString(2 * i + 2, "%" + palabras[i] + "%");
            }

            ResultSet rs = pst.executeQuery();

            boolean encontrado = false;
            String[] datos = new String[4];
            while (rs.next()) {
                datos[0] = rs.getString(1);
                datos[1] = rs.getString(2);
                datos[2] = rs.getString(3);
                datos[3] = rs.getString(4);

                encontrado = true;
            }

            if (encontrado) {
                llenarCamposTexto(datos);
            } else {
                // Limpia los campos de texto si no se encuentra el registro
                String[] vacio = { "", "", "", "" };
                llenarCamposTexto(vacio);
                showMessageDialog(null, "No existe el paciente o está mal escrito el nombre o teléfono");
            }
        } catch (SQLException ex) {
            System.out.println("ERROR AL BUSCAR: " + ex);
        }

    }

    private void agregar2() {
        int fila1 = tblCita.getSelectedRow();
        String valorColumna1 = tblCita.getValueAt(fila1, 0).toString();
        String valorColumna2 = tblCita.getValueAt(fila1, 1).toString();

        LocalDate fechaActual = LocalDate.now();
        DateTimeFormatter formatterFecha = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        ZoneId zonaHoraria = ZoneId.of("America/Tijuana");
        LocalTime horaActual = LocalTime.now(zonaHoraria);
        DateTimeFormatter formatterHora = DateTimeFormatter.ofPattern("HH:mm:ss");
        String horaActualString = horaActual.format(formatterHora);
        System.out.println("Hora actual en Tijuana: " + horaActualString);

        // Parsear la fecha y hora de la tabla
        LocalDate fechaTabla = LocalDate.parse(valorColumna1, formatterFecha);
        LocalTime horaTabla = LocalTime.parse(valorColumna2, formatterHora);

        // Comparar la fecha y hora actual con la fecha y hora de la tabla
        if (fechaActual.isAfter(fechaTabla) || (fechaActual.equals(fechaTabla) && horaActual.isAfter(horaTabla))) {
            // La fecha y hora actual son posteriores a la fecha y hora de la tabla,
            // continuar con el método
            // ...
            showMessageDialog(this, "No se aceptan fechar y horas anteriores", "Advertencia",
                    JOptionPane.WARNING_MESSAGE);
            return;

        } else {
            // La fecha y hora actual son anteriores o iguales a la fecha y hora de la
            // tabla, no continuar con el método
            String duracion = txtDuracion.getText().trim();

            // Validar el formato de duración (00:00)
            if (!duracion.matches("\\d{2}:\\d{2}")) {
                JOptionPane.showMessageDialog(this, "El formato de duración debe ser 00:00", "Advertencia",
                        JOptionPane.WARNING_MESSAGE);
                return;
            }
            if (validaMotivo(tagInputControl1)) {
                lblDetalle.setForeground(Color.RED);
                return;
            } else {
                lblDetalle.setForeground(Color.BLACK);
            }

            int fila = tblCita.getSelectedRow();
            if (fila == -1) {
                JOptionPane.showMessageDialog(null, "DEBE SELECCIONAR UN REGISTRO", "Advertencia",
                        JOptionPane.WARNING_MESSAGE);
            } else {
                try {
                    Connection con = null;
                    
                    con = Conexión.getConnection();
                    

                    // Confirmar la cita original
                    String sql = "UPDATE gestion_cita SET idPaciente = ?, estatus = 'Confirmar', detalleCita= ?, duracion= ? WHERE fecha = ? AND horario = ? AND estatus='Disponible'";
                    PreparedStatement pst = con.prepareCall(sql);
                    pst.setString(1, txtID.getText());
                    pst.setString(2, tagInputControl1.getTags(0));
                    pst.setString(3, txtDuracion.getText());
                    pst.setString(4, txtDiaCita.getText());
                    pst.setString(5, txtHoraCita.getText());
                    int n = pst.executeUpdate();

                    if (n > 0) {
                        // Convertir la duración a minutos
                        String duracionString = txtDuracion.getText();
                        int duracionMinutos;
                        if (duracionString.contains(":")) {
                            // Si la duración está en formato HH:mm
                            int horas = Integer.parseInt(duracionString.split(":")[0]);
                            int minutos = Integer.parseInt(duracionString.split(":")[1]);
                            duracionMinutos = horas * 60 + minutos;
                        } else {
                            // Si la duración está en minutos
                            duracionMinutos = Integer.parseInt(duracionString);
                        }

                        // Bloquear las citas en función de la fecha, la hora y la duración
                        String bloquearCitasSQL = "{CALL actualizarCitas(?, ?, ?)}";
                        CallableStatement bloquearCitasStmt = con.prepareCall(bloquearCitasSQL);
                        bloquearCitasStmt.setString(1, txtDiaCita.getText());
                        bloquearCitasStmt.setString(2, txtHoraCita.getText());
                        bloquearCitasStmt.setInt(3, duracionMinutos);
                        bloquearCitasStmt.execute();

                        // Cambiar el estado de las casillas debajo de la cita agregada a "Bloqueado"
                        int index = tblCita.getSelectedRow();
                        int rows = tblCita.getRowCount();
                        for (int i = index + 1; i < rows; i++) {
                            String estado = (String) tblCita.getValueAt(i, 1); // Obtener el estado actual de la casilla
                            if (estado.equals("Disponible")) {
                                tblCita.setValueAt("Bloqueado", i, 1); // Cambiar el estado en la columna
                                                                       // correspondiente
                            }
                        }

                        JOptionPane.showMessageDialog(this, "CITA GUARDADA CORRECTAMENTE");
                        limpiar();
                        vaciarTabla();
                        Date fechaSeleccionada = fecha.getDate();
                        if (fechaSeleccionada == null) {
                            cargarDatos();
                        } else {
                            cargarDatos2();
                            this.colorTabla();
                        }
                    }
                } catch (SQLException | HeadlessException e) {
                    JOptionPane.showMessageDialog(this, "LOS DATOS NO HAN SIDO GUARDADOS CORRECTAMENTE" + e, "Error",
                            JOptionPane.ERROR_MESSAGE);
                }
            }

        }

    }// agregar2

    /*
     * private void Eliminar() {
     * int fila = tblCita.getSelectedRow();
     * if (fila == -1) {
     * JOptionPane.showMessageDialog(null, "DEBE SELECCIONAR UN REGISTRO",
     * "Advertencia",
     * JOptionPane.WARNING_MESSAGE);
     * } else
     * try {
     * Connection con = null;
     * 
     * con = Conexión.getConnection();
     * 
     * String sql =
     * "UPDATE gestion_cita SET idPaciente = NULL, estatus = 'Disponible', detalleCita='' WHERE fecha = ? AND horario = ?"
     * ;
     * PreparedStatement pst = con.prepareCall(sql);
     * pst.setString(1, txtDiaCita.getText());
     * pst.setString(2, txtHoraCita.getText());
     * int n = pst.executeUpdate();
     * if (n > 0) {
     * JOptionPane.showMessageDialog(this, "CITA ELIMINADA CORRECTAMENTE");
     * limpiar();
     * vaciarTabla();
     * Date fechaSeleccionada = fecha.getDate();
     * if (fechaSeleccionada == null) {
     * cargarDatos();
     * this.colorTabla();
     * } else {
     * 
     * cargarDatos2();
     * this.colorTabla();
     * }
     * }
     * } catch (SQLException | HeadlessException e) {
     * JOptionPane.showMessageDialog(this,
     * "LOS DATOS NO HAN SIDO ELIMINADOS CORRECTAMENTE" + e, "Error",
     * JOptionPane.ERROR_MESSAGE);
     * }
     * 
     * }// Eliminar
     */
    private void eliminarCita() {

        int fila = tblCita.getSelectedRow();

        if (fila == -1) {
            JOptionPane.showMessageDialog(null, "DEBE SELECCIONAR UN REGISTRO", "Advertencia",
                    JOptionPane.WARNING_MESSAGE);
        } else {
            int opc = JOptionPane.showConfirmDialog(this, "¿ESTA SEGURO QUE DESEA ELIMINAR ESTÁ CITA?", "Pregunta",
                    JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
            if (opc == JOptionPane.YES_OPTION) {
                try {
                    Connection con = null;
                    
                    con = Conexión.getConnection();
                    
                    String fechaCita = txtDiaCita.getText();
                    String horaCita = txtHoraCita.getText();

                    // Obtener la duración de la cita seleccionada
                    String duracionString = (String) tblCita.getValueAt(fila, 6);
                    int horas = Integer.parseInt(duracionString.split(":")[0]);
                    int minutos = Integer.parseInt(duracionString.split(":")[1]);
                    int duracionMinutos = horas * 60 + minutos;

                    // Desbloquear las citas en función de la fecha, la hora y la duración
                    String desbloquearCitasSQL = "{CALL desbloquearCitas(?, ?, ?)}";
                    CallableStatement desbloquearCitasStmt = con.prepareCall(desbloquearCitasSQL);
                    desbloquearCitasStmt.setString(1, fechaCita);
                    desbloquearCitasStmt.setString(2, horaCita);
                    desbloquearCitasStmt.setInt(3, duracionMinutos);
                    desbloquearCitasStmt.execute();

                    String sql = "UPDATE gestion_cita SET idPaciente = NULL, estatus = 'Disponible', detalleCita = '', duracion = NULL WHERE fecha = ? AND horario = ?";
                    PreparedStatement pst = con.prepareCall(sql);
                    pst.setString(1, fechaCita);
                    pst.setString(2, horaCita);
                    int n = pst.executeUpdate();
                    if (n > 0) {
                        // Cambiar el estado de las casillas bloqueadas a "Disponible"
                        int index = tblCita.getSelectedRow();
                        int rows = tblCita.getRowCount();
                        for (int i = index + 1; i < rows; i++) {
                            String estado = (String) tblCita.getValueAt(i, 1);
                            if (estado.equals("Bloqueado")) {
                                tblCita.setValueAt("Disponible", i, 1); // Cambiar el estado en la columna
                                                                        // correspondiente
                            }
                        }

                        JOptionPane.showMessageDialog(this, "CITA ELIMINADA CORRECTAMENTE");
                        limpiar();
                        vaciarTabla();
                        Date fechaSeleccionada = fecha.getDate();
                        if (fechaSeleccionada == null) {
                            cargarDatos();
                            this.colorTabla();
                        } else {
                            cargarDatos2();
                            this.colorTabla();
                        }
                    }

                } catch (SQLException | HeadlessException e) {
                    JOptionPane.showMessageDialog(this, "LOS DATOS NO HAN SIDO ELIMINADOS CORRECTAMENTE" + e, "Error",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
        }
    }// eliminarCita

    private void confirmar() {
        int fila = tblCita.getSelectedRow();
        if (fila == -1) {
            JOptionPane.showMessageDialog(null, "DEBE SELECCIONAR UN REGISTRO", "Advertencia",
                    JOptionPane.WARNING_MESSAGE);
        } else
            try {
                Connection con = null;
                
                con = Conexión.getConnection();
                
                String sql = "UPDATE gestion_cita SET  estatus = 'Confirmada' WHERE fecha = ? AND horario = ? AND estatus='Confirmar'";
                PreparedStatement pst = con.prepareCall(sql);
                pst.setString(1, txtDiaCita.getText());
                pst.setString(2, txtHoraCita.getText());
                int n = pst.executeUpdate();
                if (n > 0) {
                    JOptionPane.showMessageDialog(this, "CITA CONFIRMADA CORRECTAMENTE");
                    limpiar();
                    vaciarTabla();
                    Date fechaSeleccionada = fecha.getDate();
                    if (fechaSeleccionada == null) {
                        cargarDatos();
                    } else {
                        
                        cargarDatos2();
                        this.colorTabla();
                    }
                }
            } catch (SQLException | HeadlessException e) {
                JOptionPane.showMessageDialog(this, "LOS DATOS NO HAN SIDO ACTUALIZADOS CORRECTAMENTE" + e, "Error",
                        JOptionPane.ERROR_MESSAGE);
            }

    }// Confirmar

    private void DuranteCita() {
        int fila = tblCita.getSelectedRow();
        if (fila == -1) {
            JOptionPane.showMessageDialog(null, "DEBE SELECCIONAR UN REGISTRO", "Advertencia",
                    JOptionPane.WARNING_MESSAGE);
        } else
            try {
                Connection con = null;
                
                con = Conexión.getConnection();
                
                String sql = "UPDATE gestion_cita SET  estatus = 'Durante' WHERE fecha = ? AND horario = ? AND estatus='Confirmada'";
                PreparedStatement pst = con.prepareCall(sql);
                pst.setString(1, txtDiaCita.getText());
                pst.setString(2, txtHoraCita.getText());
                int n = pst.executeUpdate();
                if (n > 0) {
                    JOptionPane.showMessageDialog(this, "La dentista está atendiendo al paciente");
                    limpiar();
                    vaciarTabla();
                    Date fechaSeleccionada = fecha.getDate();
                    if (fechaSeleccionada == null) {
                        cargarDatos();
                        this.colorTabla();
                    } else {
                        
                        cargarDatos2();
                        this.colorTabla();
                    }
                }
            } catch (SQLException | HeadlessException e) {
                JOptionPane.showMessageDialog(this, "LOS DATOS NO HAN SIDO ACTUALIZADOS CORRECTAMENTE" + e, "Error",
                        JOptionPane.ERROR_MESSAGE);
            }

    }// DuranteCita

    private void finalizarCita() {
        int fila = tblCita.getSelectedRow();
        if (fila == -1) {
            JOptionPane.showMessageDialog(null, "DEBE SELECCIONAR UN REGISTRO", "Advertencia",
                    JOptionPane.WARNING_MESSAGE);
        } else
            try {
                Connection con = null;
                
                con = Conexión.getConnection();
                
                String sql = "UPDATE gestion_cita SET  estatus = 'Finalizada' WHERE fecha = ? AND horario = ? AND estatus='Durante'";
                PreparedStatement pst = con.prepareCall(sql);
                pst.setString(1, txtDiaCita.getText());
                pst.setString(2, txtHoraCita.getText());
                int n = pst.executeUpdate();
                if (n > 0) {
                    JOptionPane.showMessageDialog(this, "CITA FINALIZADA CORRECTAMENTE");
                    limpiar();
                    vaciarTabla();
                    Date fechaSeleccionada = fecha.getDate();
                    if (fechaSeleccionada == null) {
                        cargarDatos();
                        this.colorTabla();
                    } else {
                        
                        cargarDatos2();
                        this.colorTabla();
                    }
                }
            } catch (SQLException | HeadlessException e) {
                JOptionPane.showMessageDialog(this, "LOS DATOS NO HAN SIDO ACTUALIZADOS CORRECTAMENTE" + e, "Error",
                        JOptionPane.ERROR_MESSAGE);
            }

    }// FinalizarCita

    private void bloquearCita() {
        int fila = tblCita.getSelectedRow();
        if (fila == -1) {
            JOptionPane.showMessageDialog(null, "DEBE SELECCIONAR UN REGISTRO", "Advertencia",
                    JOptionPane.WARNING_MESSAGE);
        } else
            try {
                Connection con = null;
                
                con = Conexión.getConnection();
                
                String sql = "UPDATE gestion_cita SET  estatus = 'Bloqueado' WHERE fecha = ? AND horario = ? AND estatus='Disponible'";
                PreparedStatement pst = con.prepareCall(sql);
                pst.setString(1, txtDiaCita.getText());
                pst.setString(2, txtHoraCita.getText());
                int n = pst.executeUpdate();
                if (n > 0) {
                    JOptionPane.showMessageDialog(this, "CITA BLOQUEADA CORRECTAMENTE");
                    limpiar();
                    vaciarTabla();
                    Date fechaSeleccionada = fecha.getDate();
                    if (fechaSeleccionada == null) {
                        cargarDatos();
                    } else {
                        
                        cargarDatos2();
                        this.colorTabla();
                    }
                }
            } catch (SQLException | HeadlessException e) {
                JOptionPane.showMessageDialog(this, "LOS DATOS NO HAN SIDO ACTUALIZADOS CORRECTAMENTE" + e, "Error",
                        JOptionPane.ERROR_MESSAGE);
            }

    }// Bloquear

    private void DisponibleCita() {
        int fila = tblCita.getSelectedRow();
        if (fila == -1) {
            JOptionPane.showMessageDialog(null, "DEBE SELECCIONAR UN REGISTRO", "Advertencia",
                    JOptionPane.WARNING_MESSAGE);
        } else
            try {
                Connection con = null;
                
                con = Conexión.getConnection();
                
                String sql = "UPDATE gestion_cita SET  estatus = 'Disponible' WHERE fecha = ? AND horario = ? AND estatus='Bloqueado'";
                PreparedStatement pst = con.prepareCall(sql);
                pst.setString(1, txtDiaCita.getText());
                pst.setString(2, txtHoraCita.getText());
                int n = pst.executeUpdate();
                if (n > 0) {
                    JOptionPane.showMessageDialog(this, "CITA CAMBIADA CORRECTAMENTE");
                    limpiar();
                    vaciarTabla();
                    Date fechaSeleccionada = fecha.getDate();
                    if (fechaSeleccionada == null) {
                        cargarDatos();
                    } else {
                        
                        cargarDatos2();
                        this.colorTabla();
                    }
                }
            } catch (SQLException | HeadlessException e) {
                JOptionPane.showMessageDialog(this, "LOS DATOS NO HAN SIDO ACTUALIZADOS CORRECTAMENTE" + e, "Error",
                        JOptionPane.ERROR_MESSAGE);
            }

    }// Bloquear

    private void colorTabla() {
        tblCita.getColumnModel().getColumn(0).setHeaderRenderer(new DTable(new Color(230, 192, 233), Color.BLACK));
        tblCita.getColumnModel().getColumn(1).setHeaderRenderer(new DTable(new Color(230, 192, 233), Color.BLACK));
        tblCita.getColumnModel().getColumn(2).setHeaderRenderer(new DTable(new Color(230, 192, 233), Color.BLACK));
        tblCita.getColumnModel().getColumn(3).setHeaderRenderer(new DTable(new Color(230, 192, 233), Color.BLACK));
        tblCita.getColumnModel().getColumn(4).setHeaderRenderer(new DTable(new Color(230, 192, 233), Color.BLACK));
        tblCita.getColumnModel().getColumn(5).setHeaderRenderer(new DTable(new Color(230, 192, 233), Color.BLACK));
        tblCita.getColumnModel().getColumn(6).setHeaderRenderer(new DTable(new Color(230, 192, 233), Color.BLACK));
        th = tblCita.getTableHeader();
        Font fuente = new Font("Arial", Font.BOLD, 16);
        th.setFont(fuente);
        tblCita.getColumnModel().getColumn(5).setHeaderRenderer(new DTable(new Color(230, 192, 233), Color.BLACK));
    }

    private void lblBack1MouseClicked(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_lblBack1MouseClicked

        MenuSecretaria2 login = new MenuSecretaria2();
        login.setVisible(true);
        this.setVisible(false);
    }// GEN-LAST:event_lblBack1MouseClicked

    private void lblBack1MouseEntered(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_lblBack1MouseEntered
        lblBack1.setForeground(Color.GRAY);
    }// GEN-LAST:event_lblBack1MouseEntered

    private void lblBack1MouseExited(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_lblBack1MouseExited
        lblBack1.setForeground(Color.BLACK);
    }// GEN-LAST:event_lblBack1MouseExited

    private void lblPaciente1MouseClicked(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_lblPaciente1MouseClicked
        VentanaPacienteSecre next = new VentanaPacienteSecre();
        next.setVisible(true);
        next.setLocationRelativeTo(null);
        this.dispose();
    }// GEN-LAST:event_lblPaciente1MouseClicked

    private void lblPaciente1MouseEntered(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_lblPaciente1MouseEntered
        lblPaciente1.setForeground(Color.BLACK);
    }// GEN-LAST:event_lblPaciente1MouseEntered

    private void lblPaciente1MouseExited(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_lblPaciente1MouseExited
        lblPaciente1.setForeground(Color.GRAY);
    }// GEN-LAST:event_lblPaciente1MouseExited

    private void lblCitas1MouseClicked(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_lblCitas1MouseClicked

    }// GEN-LAST:event_lblCitas1MouseClicked

    private void lblCitas1MouseEntered(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_lblCitas1MouseEntered
        lblCitas1.setForeground(Color.GRAY);
    }// GEN-LAST:event_lblCitas1MouseEntered

    private void lblCitas1MouseExited(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_lblCitas1MouseExited
        lblCitas1.setForeground(Color.BLACK);
    }// GEN-LAST:event_lblCitas1MouseExited

    private void jLabel9MouseEntered(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_jLabel9MouseEntered
        // lblExpediente.setForeground(Color.BLUE);
    }// GEN-LAST:event_jLabel9MouseEntered

    private void jLabel9MouseExited(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_jLabel9MouseExited
        // lblExpediente.setForeground(Color.BLACK);
    }// GEN-LAST:event_jLabel9MouseExited

    private void lblMenu1MouseClicked(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_lblMenu1MouseClicked
        if (MenuPleglable1.getX() == 0)
            desplace.desplazarIzquierda(this.MenuPleglable1, MenuPleglable1.getX(), -180, 10, 10);
        else if (MenuPleglable1.getX() == -180)
            desplace.desplazarDerecha(this.MenuPleglable1, MenuPleglable1.getX(), 0, 10, 10);
    }// GEN-LAST:event_lblMenu1MouseClicked

    private void fechaMouseClicked(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_fechaMouseClicked
        
    }// GEN-LAST:event_fechaMouseClicked

    private void tblCitaMouseClicked(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_tblCitaMouseClicked
        try {
            int fila = tblCita.getSelectedRow();
            txtDiaCita.setText(tblCita.getValueAt(fila, 0).toString());
            txtHoraCita.setText(tblCita.getValueAt(fila, 1).toString());
            try {
                txtTelefono.setText(tblCita.getValueAt(fila, 4).toString());
                String tags;
                if (!(tags = (String) tblCita.getValueAt(fila, 5)).equals("")) {
                    tagInputControl1.setTags(tags);
                }

                if (!tblCita.getValueAt(fila, 6).toString().equals("")) {
                    txtDuracion.setText(tblCita.getValueAt(fila, 6).toString());
                }
            } catch (Exception e) {
            }

            switch (tblCita.getValueAt(fila, 2).toString()) {
                case "Disponible":
                    lblConfirmar.setVisible(false);
                    lblGuardar.setVisible(true);
                    lblFinalizar.setVisible(false);
                    lblDurante.setVisible(false);
                    lblBloquear.setVisible(true);
                    lblEliminar.setVisible(false);
                    lblModificar.setVisible(false);
                    lblDisponible.setVisible(false);

                    break;
                case "Confirmar":
                    lblConfirmar.setVisible(true);
                    lblGuardar.setVisible(false);
                    lblFinalizar.setVisible(false);
                    lblDurante.setVisible(false);
                    lblBloquear.setVisible(false);
                    lblEliminar.setVisible(true);
                    lblModificar.setVisible(true);
                    lblDisponible.setVisible(false);
                    break;
                case "Confirmada":
                    lblConfirmar.setVisible(false);
                    lblGuardar.setVisible(false);
                    lblFinalizar.setVisible(false);
                    lblDurante.setVisible(true);
                    lblBloquear.setVisible(false);
                    lblEliminar.setVisible(true);
                    lblModificar.setVisible(true);
                    lblDisponible.setVisible(false);
                    break;
                case "Durante":
                    lblConfirmar.setVisible(false);
                    lblGuardar.setVisible(false);
                    lblFinalizar.setVisible(true);
                    lblDurante.setVisible(false);
                    lblBloquear.setVisible(false);
                    lblEliminar.setVisible(true);
                    lblModificar.setVisible(true);
                    lblDisponible.setVisible(false);
                    break;
                case "Finalizada":
                    lblConfirmar.setVisible(false);
                    lblGuardar.setVisible(false);
                    lblFinalizar.setVisible(false);
                    lblDurante.setVisible(false);
                    lblBloquear.setVisible(false);
                    lblEliminar.setVisible(true);
                    lblModificar.setVisible(true);
                    lblDisponible.setVisible(false);
                    break;
                case "Bloqueado":
                    lblConfirmar.setVisible(false);
                    lblGuardar.setVisible(false);
                    lblFinalizar.setVisible(false);
                    lblDurante.setVisible(false);
                    lblBloquear.setVisible(false);
                    lblEliminar.setVisible(false);
                    lblModificar.setVisible(false);
                    lblDisponible.setVisible(true);
                    break;
                default:

                    break;
            }
            System.out.println(tblCita.getValueAt(fila, 2));
            String cellValue = tblCita.getValueAt(fila, 3).toString();
            String[] parts = cellValue.split(" ");
            if (parts.length == 2) {

                txtNombre.setText(parts[0]);
                txtApellido.setText(parts[1]);
            }
            if (parts.length == 3) {

                txtNombre.setText(parts[0]);
                txtApellido.setText(parts[1] + " " + parts[2]);
            }
            if (parts.length == 4) {

                txtNombre.setText(parts[0] + " " + parts[1]);
                txtApellido.setText(parts[2] + " " + parts[3]);
            }
        } catch (Exception ex) {
            System.out.println("ERROR AL SELECCIONAR UNA FILA: " + ex.getMessage());
        }
    }// GEN-LAST:event_tblCitaMouseClicked

    private void lblBuscarMouseClicked(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_lblBuscarMouseClicked
        if (isDateChooserNotEmpty(fecha)) {
            cargarDatos2();
        } else {
            // Muestra un mensaje de error indicando que la fecha no puede estar vacía
            JOptionPane.showMessageDialog(null, "Por favor, selecciona una fecha válida.", "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }// GEN-LAST:event_lblBuscarMouseClicked

    private void txtHoraCitaActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_txtHoraCitaActionPerformed
        
    }// GEN-LAST:event_txtHoraCitaActionPerformed

    private void btnBuscar1MouseClicked(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_btnBuscar1MouseClicked
        String valorBusqueda = cmbPaciente.getText();
        Buscar(valorBusqueda);

    }// GEN-LAST:event_btnBuscar1MouseClicked

    private void lblGuardarMouseClicked(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_lblGuardarMouseClicked
        // agregar();
        if (txtDiaCita.getText().toString().equals("")) {
            JOptionPane.showMessageDialog(this, "Debe elegir un día de cita", "Advertencia",
                    JOptionPane.WARNING_MESSAGE);
            return;
        }
        if (txtHoraCita.getText().toString().equals("")) {
            JOptionPane.showMessageDialog(this, "Debe elegir una hora de cita", "Advertencia",
                    JOptionPane.WARNING_MESSAGE);
            return;
        }
        // Convertir la fecha y hora de la cita a LocalDateTime
        String fechaCitaString = txtDiaCita.getText();
        String horaCitaString = txtHoraCita.getText();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"); // ajusta el formato de
                                                                                          // acuerdo a cómo ingreses las
                                                                                          // fechas y horas
        LocalDateTime fechaHoraCita = LocalDateTime.parse(fechaCitaString + " " + horaCitaString, formatter);

        // Validar si la fecha y hora de la cita son antes de la fecha y hora actuales
        if (fechaHoraCita.isBefore(fechaHoraCita)) {
            JOptionPane.showMessageDialog(this, "No puedes seleccionar una fecha y/o hora en el pasado", "Advertencia",
                    JOptionPane.WARNING_MESSAGE);
            return;
        }
        int fila = tblCita.getSelectedRow();
        if (tblCita.getValueAt(fila, 2).equals("Disponible")) {
            lblConfirmar.setVisible(false);
            lblGuardar.setVisible(true);
            lblFinalizar.setVisible(false);
            lblDurante.setVisible(false);
            lblBloquear.setVisible(true);
            lblEliminar.setVisible(false);
            String nombre = txtNombre.getText();
            String apellido = txtApellido.getText();
            String nombreC = nombre + " " + apellido;
            guardarCita(nombreC, txtHoraCita.getText(), txtDiaCita.getText(), tagInputControl1.getTags(0),
                    txtDuracion.getText());
            agregar2();
        } else {
            showMessageDialog(null, "No se puede agendar la cita, revisa el estatus");
        }
    }// GEN-LAST:event_lblGuardarMouseClicked

    public void guardarCita(String nombrePaciente, String hora, String fecha, String detalleCita, String duracion) {
        // Obtener la fecha y hora actual como parte del nombre del archivo
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd_HHmmss");
        String timeStamp = dateFormat.format(new Date());
        String nombre = txtNombre.getText();
        String apellido = txtApellido.getText();
        String nombreC = nombre + " " + apellido;
        // Crear el nombre del archivo de texto con la marca de tiempo
        String nombreArchivo = DIRECTORIO_CITAS + nombreC + "_cita_" + timeStamp + ".txt";
        // Crear una cadena de texto con los datos de la cita
        String cita = "Nombre del paciente: " + nombrePaciente + "\n" +
                "Hora: " + hora + "\n" +
                "Fecha: " + fecha + "\n" +
                "Detalle de la cita: " + detalleCita + "\n" +
                "Duración: " + duracion + " horas\n\n";

        // Guardar la cita en el archivo de texto
        // Guardar la cita en el archivo de texto
        try (BufferedWriter escritor = new BufferedWriter(new FileWriter(nombreArchivo))) {
            escritor.write("------------ CONSULTORIO DENTAL ------------\n");
            escritor.write("------------ Dra. Luz Elena Hernández ------\n\n");
            escritor.write("------------ Tu próxima cita es: ------------\n\n");
            escritor.write(cita);
            escritor.write("************ En caso de no poder asistir a la cita ************\n");
            escritor.write("************ favor de llamar 24hr antes al número 3112541256 ************\n");

            System.out.println("La cita se ha guardado correctamente en el archivo: " + nombreArchivo);
        } catch (IOException e) {
            System.out.println("Ha ocurrido un error al guardar la cita en el archivo.");
            e.printStackTrace();
        }

    }

    private void lblEliminarMouseClicked(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_lblEliminarMouseClicked
        int fila = tblCita.getSelectedRow();

        if (tblCita.getValueAt(fila, 2).toString().equals("Disponible")) {
            showMessageDialog(null, "REVISE EL ESTATUS DE LA CITA", "Advertencia",
                    JOptionPane.WARNING_MESSAGE);
        } else {
            eliminarCita();
        }
    }// GEN-LAST:event_lblEliminarMouseClicked

    private void lblModificarMouseClicked(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_lblModificarMouseClicked
        if (validaMotivo(tagInputControl1)) {
            lblDetalle.setForeground(Color.RED);
            return;
        } else {
            lblDetalle.setForeground(Color.BLACK);
        }
        if (validaCampo(txtDuracion)) {
            lblDuracion.setForeground(Color.RED);
            return;
        } else
            lblDuracion.setForeground(Color.BLACK);

        int fila = tblCita.getSelectedRow();
        if (fila == -1) {
            JOptionPane.showMessageDialog(null, "DEBE SELECCIONAR UN REGISTRO", "Advertencia",
                    JOptionPane.WARNING_MESSAGE);
        } else {
            try {
                Connection con = null;
                
                con = Conexión.getConnection();
                

                // Obtener la duración actual de la cita seleccionada
                String duracionActual = (String) tblCita.getValueAt(fila, 6);
                // Obtener la nueva duración ingresada
                String nuevaDuracion = txtDuracion.getText();

                // Verificar si la duración ha cambiado
                if (!duracionActual.equals(nuevaDuracion)) {
                    // Convertir la duración actual y la nueva duración a minutos
                    int duracionActualMinutos = convertirDuracionADuracionMinutos(duracionActual);
                    int nuevaDuracionMinutos = convertirDuracionADuracionMinutos(nuevaDuracion);

                    // Desbloquear las citas correspondientes a la duración actual
                    desbloquearCitas(con, txtDiaCita.getText(), txtHoraCita.getText(), duracionActualMinutos);

                    // Bloquear las citas correspondientes a la nueva duración
                    bloquearCitas(con, txtDiaCita.getText(), txtHoraCita.getText(), nuevaDuracionMinutos);
                }

                String sql = "UPDATE gestion_cita SET detalleCita = ?, duracion = ? WHERE fecha = ? AND horario = ?";
                PreparedStatement pst = con.prepareCall(sql);
                pst.setString(1, tagInputControl1.getTags(0));
                pst.setString(2, nuevaDuracion);
                pst.setString(3, txtDiaCita.getText());
                pst.setString(4, txtHoraCita.getText());
                int n = pst.executeUpdate();
                if (n > 0) {
                    JOptionPane.showMessageDialog(this, "CITA MODIFICADA CORRECTAMENTE");
                    limpiar();
                    vaciarTabla();
                    Date fechaSeleccionada = fecha.getDate();
                    if (fechaSeleccionada == null) {
                        cargarDatos();
                        this.colorTabla();
                    } else {
                        
                        cargarDatos2();
                        this.colorTabla();
                    }
                }
            } catch (SQLException | HeadlessException e) {
                JOptionPane.showMessageDialog(this, "LOS DATOS NO HAN SIDO MODIFICADOS CORRECTAMENTE" + e, "Error",
                        JOptionPane.ERROR_MESSAGE);
            }
        }

        limpiar();
    }// GEN-LAST:event_lblModificarMouseClicked

    private int convertirDuracionADuracionMinutos(String duracion) {
        if (duracion.contains(":")) {
            // Si la duración está en formato HH:mm
            int horas = Integer.parseInt(duracion.split(":")[0]);
            int minutos = Integer.parseInt(duracion.split(":")[1]);
            return horas * 60 + minutos;
        } else {
            // Si la duración está en formato decimal
            float duracionDecimal = Float.parseFloat(duracion);
            return Math.round(duracionDecimal * 60);
        }
    }

    private void bloquearCitas(Connection con, String fechaCita, String horaCita, int duracion) throws SQLException {
        // Calcular la hora de finalización de la cita
        LocalTime horaFinCita = LocalTime.parse(horaCita).plusMinutes(duracion);

        // Actualizar el estado de las citas en el rango de tiempo de la cita actual a
        // "Bloqueado"
        String sql = "UPDATE gestion_cita SET estatus = 'Bloqueado' WHERE fecha = ? AND horario >= ? AND horario < ? AND estatus = 'Disponible'";
        PreparedStatement pst = con.prepareStatement(sql);
        pst.setString(1, fechaCita);
        pst.setString(2, horaCita);
        pst.setString(3, horaFinCita.toString());
        pst.executeUpdate();
    }

    private void desbloquearCitas(Connection con, String fechaCita, String horaCita, int duracion) throws SQLException {
        // Calcular la hora de finalización de la cita
        LocalTime horaFinCita = LocalTime.parse(horaCita).plusMinutes(duracion);

        // Actualizar el estado de las citas en el rango de tiempo de la cita actual a
        // "Disponible"
        String sql = "UPDATE gestion_cita SET estatus = 'Disponible' WHERE fecha = ? AND horario >= ? AND horario < ? AND estatus = 'Bloqueado'";
        PreparedStatement pst = con.prepareStatement(sql);
        pst.setString(1, fechaCita);
        pst.setString(2, horaCita);
        pst.setString(3, horaFinCita.toString());
        pst.executeUpdate();
    }

    private void lblConfirmarMouseClicked(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_lblConfirmarMouseClicked
        confirmar();
    }// GEN-LAST:event_lblConfirmarMouseClicked

    private void lblDuranteMouseClicked(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_lblDuranteMouseClicked

        DuranteCita();

    }// GEN-LAST:event_lblDuranteMouseClicked

    private void lblBloquearMouseClicked(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_lblBloquearMouseClicked
        bloquearCita();
    }// GEN-LAST:event_lblBloquearMouseClicked

    private void lblFinalizarMouseClicked(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_lblFinalizarMouseClicked
        finalizarCita();
    }// GEN-LAST:event_lblFinalizarMouseClicked

    private void lblDisponibleMouseClicked(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_lblDisponibleMouseClicked
        DisponibleCita();
    }// GEN-LAST:event_lblDisponibleMouseClicked

    private void lblLimpiarMouseClicked(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_lblLimpiarMouseClicked
        limpiar();
    }// GEN-LAST:event_lblLimpiarMouseClicked

    private boolean isDateChooserNotEmpty(JDateChooser dateChooser) {
        Date selectedDate = dateChooser.getDate();
        return selectedDate != null;
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
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
            java.util.logging.Logger.getLogger(VentanaCitas.class.getName()).log(java.util.logging.Level.SEVERE, null,
                    ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(VentanaCitas.class.getName()).log(java.util.logging.Level.SEVERE, null,
                    ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(VentanaCitas.class.getName()).log(java.util.logging.Level.SEVERE, null,
                    ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(VentanaCitas.class.getName()).log(java.util.logging.Level.SEVERE, null,
                    ex);
        }
        // </editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new VentanaCitasSecre().setVisible(true);
            }
        });
    }

    private static final String DIRECTORIO_CITAS = "Citas/";
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel MenuPleglable1;
    private Componentes.AutoCompleteTextField autoCompleteTextField1;
    private Componentes.JImageBox btnBuscar1;
    private Componentes.AutoCompleteTextField cmbPaciente;
    private com.toedter.calendar.JDateChooser fecha;
    private Componentes.JImageBox jImageBox2;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JSeparator jSeparator5;
    private javax.swing.JSeparator jSeparator6;
    private javax.swing.JSeparator jSeparator7;
    private javax.swing.JLabel lblBack1;
    private Componentes.JImageBox lblBloquear;
    private Componentes.JImageBox lblBuscar;
    private javax.swing.JLabel lblCitas1;
    private Componentes.JImageBox lblConfirmar;
    private javax.swing.JLabel lblDetalle;
    private Componentes.JImageBox lblDisponible;
    private javax.swing.JLabel lblDuracion;
    private Componentes.JImageBox lblDurante;
    private Componentes.JImageBox lblEliminar;
    private Componentes.JImageBox lblFinalizar;
    private Componentes.JImageBox lblGuardar;
    private javax.swing.JLabel lblLimpiar;
    private javax.swing.JLabel lblMenu1;
    private Componentes.JImageBox lblModificar;
    private javax.swing.JLabel lblPaciente1;
    private java.awt.Panel panel1;
    private Componentes.TagInputControl tagInputControl1;
    private javax.swing.JTable tblCita;
    private javax.swing.JTextField txtApellido;
    private javax.swing.JTextField txtDiaCita;
    private javax.swing.JTextField txtDuracion;
    private javax.swing.JTextField txtHoraCita;
    private javax.swing.JTextField txtID;
    private javax.swing.JTextField txtNombre;
    private javax.swing.JTextField txtTelefono;
    // End of variables declaration//GEN-END:variables
}
