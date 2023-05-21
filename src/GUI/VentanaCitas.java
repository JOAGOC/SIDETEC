package GUI;

import static Interfaces.CRUD.cargarAutocompletar;
import Conexión.Conexión;
import com.toedter.calendar.JDateChooser;

import ClasesSQL.Paciente;
import Componentes.AutoCompleteTextField;
import Componentes.ColorRenderer;
import Componentes.DTable;
import Componentes.JImageBox;
import Componentes.TagInputControl;
import desplazable.Desface;
import java.awt.Color;
import java.awt.Font;
import java.awt.HeadlessException;
import java.awt.Image;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

import static javax.swing.JOptionPane.showMessageDialog;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
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
public class VentanaCitas extends javax.swing.JFrame {
    ColorRenderer colorRenderer = new ColorRenderer();
    /**
     * Creates new form VentanaCitas
     */
    private ImageIcon imagen;
    private ImageIcon icono;
    Desface desplace;
    JTableHeader th;

    public VentanaCitas() {
        initComponents();
        this.cargarDatos();
        setLocationRelativeTo(null);
        desplace = new Desface();
        fecha.setMinSelectableDate(new Date());
        int columnaEstado = 2; // reemplazar con el índice de la columna que contiene los estados
        DefaultTableModel modelo = (DefaultTableModel) tblCita.getModel();

 modelo.addTableModelListener(new TableModelListener() {
    @Override
    public void tableChanged(TableModelEvent e) {
        if (e.getType() == TableModelEvent.UPDATE) {
            int row = e.getFirstRow()+1;
            int column = e.getColumn();
            
            if (column == 6) { // Si se modificó la columna "Duración"
                String duracion = (String) modelo.getValueAt(row, column);
                int duracionEnMinutos = getDuracionEnMinutos(duracion);
                int rowsToBlock = duracionEnMinutos / 30;
                
                // Verificar si alguna hora en el rango ya está bloqueada
                for (int i = 1; i < rowsToBlock; i++) {
                    if (row + i < modelo.getRowCount()) {
                        if (!modelo.getValueAt(row + i, 1).toString().isEmpty()) {
                            JOptionPane.showMessageDialog(null, "Horario ya está bloqueado");
                            return;
                        }
                    }
                }
                
                // Bloquear las horas
                for (int i = 1; i < rowsToBlock; i++) {
                    if (row + i < modelo.getRowCount()) {
                        modelo.setValueAt("Bloqueado", row + i, 2);
                    }
                }
            }
        }
    }
     
        }); 
        tblCita.getColumnModel().getColumn(columnaEstado).setCellRenderer(colorRenderer);
        lblConfirmar.setEnabled(false);
        lblFinalizar.setEnabled(false);
        lblGuardar.setEnabled(true);
        lblEliminar.setEnabled(false);
        lblModificar.setEnabled(false);

        // this.Imagen(this.lblGuardar,"Imagenes\\cita-medica.png");

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
        cargarAutocompletar("SELECT CONCAT(nombre, ' ', apellido) FROM pacientes ORDER BY CONCAT(nombre, ' ', apellido)",cmbPaciente);

          
    }
  private int getDuracionEnMinutos(String duracion) {
        switch (duracion) {
            case "30 min":
                return 30;
            case "45 min":
                return 45;
            case "1 hora":
                return 60;
            case "1 hora y media":
                return 90;
            case "2 horas":
                return 120;
            default:
                return 0;
        }
    }
    private void Imagen(JLabel lbl, String ruta) {
        this.imagen = new ImageIcon(ruta);
        this.icono = new ImageIcon(
                this.imagen.getImage().getScaledInstance(lbl.getWidth(),
                        lbl.getHeight(),
                        Image.SCALE_SMOOTH));

        lbl.setIcon(this.icono);
        repaint();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        autoCompleteTextField1 = new Componentes.AutoCompleteTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        jPanel1 = new javax.swing.JPanel();
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
        jPanel3 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        fecha = new com.toedter.calendar.JDateChooser();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblCita = new javax.swing.JTable();
        lblBuscar = new Componentes.JImageBox();
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
        jImageBox2 = new Componentes.JImageBox();
        panel1 = new java.awt.Panel();
        jLabel10 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        lblGuardar = new Componentes.JImageBox();
        lblEliminar = new Componentes.JImageBox();
        lblConfirmar = new Componentes.JImageBox();
        lblModificar = new Componentes.JImageBox();
        lblFinalizar = new Componentes.JImageBox();

        autoCompleteTextField1.setItems(new String[] {"Limpieza dental", "Dolor de muelas", "Caries dental", "Extracción dental", "Blanqueamiento dental", "Ortodoncia", "Diente astillado", "Problemas de encías", "Revisión de rutina", "Problemas de mordida", "Prótesis dental", "Inflamación dental", "Sensibilidad dental", "Implante dental"});

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setMinimumSize(new java.awt.Dimension(1180, 1242));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

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

        cmbPaciente.setItems(new String[] {"Limpieza dental", "Dolor de muelas", "Caries dental", "Extracción dental", "Blanqueamiento dental", "Ortodoncia", "Diente astillado", "Problemas de encías", "Revisión de rutina", "Problemas de mordida", "Prótesis dental", "Inflamación dental", "Sensibilidad dental", "Implante dental"});

        btnBuscar1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/lupa.png"))); // NOI18N
        btnBuscar1.setToolTipText("Cita Finalizada");
        btnBuscar1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnBuscar1MouseClicked(evt);
            }
        });

        jLabel16.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel16.setText("Duración:");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(39, 39, 39)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel8)
                                    .addComponent(jLabel6))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtID, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 204, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(jLabel5)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(lblDetalle)
                                .addGap(18, 18, 18)
                                .addComponent(tagInputControl1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 34, Short.MAX_VALUE)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel7)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtApellido, javax.swing.GroupLayout.PREFERRED_SIZE, 206, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(19, 19, 19)
                                .addComponent(jLabel11)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtTelefono, javax.swing.GroupLayout.PREFERRED_SIZE, 204, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel14)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtDiaCita, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel15)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txtHoraCita, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel16)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtDuracion, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(49, 49, 49))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cmbPaciente, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel2Layout.createSequentialGroup()
                    .addGap(345, 345, 345)
                    .addComponent(btnBuscar1, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(627, Short.MAX_VALUE)))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(cmbPaciente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(28, 28, 28)
                        .addComponent(jLabel5))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6)
                            .addComponent(txtID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(8, 8, 8)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7)
                    .addComponent(txtApellido, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel11)
                    .addComponent(txtTelefono, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblDetalle)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel14)
                                .addComponent(txtDiaCita, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel15)
                                .addComponent(txtHoraCita, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel16)
                            .addComponent(txtDuracion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 30, Short.MAX_VALUE))
                    .addComponent(tagInputControl1, javax.swing.GroupLayout.DEFAULT_SIZE, 96, Short.MAX_VALUE))
                .addContainerGap())
            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel2Layout.createSequentialGroup()
                    .addGap(10, 10, 10)
                    .addComponent(btnBuscar1, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(184, Short.MAX_VALUE)))
        );

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 470, 1020, 240));

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
            new Object [][] {

            },
            new String [] {
                "No. Fecha", "Hora", "Status", "Nombre paciente", "Telefono", "Detalle Cita", "Duración"
            }
        ));
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
                .addComponent(fecha, javax.swing.GroupLayout.PREFERRED_SIZE, 189, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(32, Short.MAX_VALUE)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 964, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(24, 24, 24))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(22, 22, 22)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(fecha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4)))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(lblBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(13, 13, 13)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 241, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(17, Short.MAX_VALUE))
        );

        jPanel1.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 120, 1020, 330));

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
        lblPaciente1.setBounds(20, 180, 279, 54);

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
                .addContainerGap(537, Short.MAX_VALUE)
                .addComponent(jLabel10)
                .addGap(340, 340, 340))
        );
        panel1Layout.setVerticalGroup(
            panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel1Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(jLabel10)
                .addContainerGap(20, Short.MAX_VALUE))
        );

        jPanel1.add(panel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 0, 1070, 70));

        jPanel4.setBackground(new java.awt.Color(0, 108, 183));

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1070, Short.MAX_VALUE)
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 40, Short.MAX_VALUE)
        );

        jPanel1.add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 830, 1070, 40));

        lblGuardar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/cita-medica (1).png"))); // NOI18N
        lblGuardar.setToolTipText("Guardar Cita");
        lblGuardar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblGuardarMouseClicked(evt);
            }
        });
        jPanel1.add(lblGuardar, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 730, 80, -1));

        lblEliminar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/borrar.png"))); // NOI18N
        lblEliminar.setToolTipText("Eliminar Cita");
        lblEliminar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblEliminarMouseClicked(evt);
            }
        });
        jPanel1.add(lblEliminar, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 730, 90, -1));

        lblConfirmar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/fecha-del-calendario.png"))); // NOI18N
        lblConfirmar.setToolTipText("Confirmar Cita");
        lblConfirmar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblConfirmarMouseClicked(evt);
            }
        });
        jPanel1.add(lblConfirmar, new org.netbeans.lib.awtextra.AbsoluteConstraints(750, 730, 70, -1));

        lblModificar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/editar1.png"))); // NOI18N
        lblModificar.setToolTipText("Modificar");
        lblModificar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblModificarMouseClicked(evt);
            }
        });
        jPanel1.add(lblModificar, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 730, 70, 70));

        lblFinalizar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/terminado.png"))); // NOI18N
        lblFinalizar.setToolTipText("Cita Finalizada");
        lblFinalizar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblFinalizarMouseClicked(evt);
            }
        });
        jPanel1.add(lblFinalizar, new org.netbeans.lib.awtextra.AbsoluteConstraints(860, 730, 80, 80));

        jScrollPane1.setViewportView(jPanel1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void lblBuscarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblBuscarMouseClicked
        JDateChooser dateChooser = new JDateChooser();
        if (isDateChooserNotEmpty(fecha)) {
            cargarDatos2();
        } else {
            // Muestra un mensaje de error indicando que la fecha no puede estar vacía
            JOptionPane.showMessageDialog(null, "Por favor, selecciona una fecha válida.", "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_lblBuscarMouseClicked

    private void btnBuscar1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnBuscar1MouseClicked
        String valorBusqueda = cmbPaciente.getText();
        Buscar(valorBusqueda);
        
    }//GEN-LAST:event_btnBuscar1MouseClicked

    public void cargarDatos() {
        try {
            Connection con1 = null;
            DefaultTableModel m = (DefaultTableModel) tblCita.getModel();
            Conexión conect1 = new Conexión();
            con1 = conect1.getConnection();
            String dts[] = new String[7];
            String sql = "SELECT gestion_cita.fecha, gestion_cita.horario, gestion_cita.estatus, " +
                    "CONCAT(pacientes.nombre, ' ', pacientes.apellido) AS 'Nombre Completo', " +
                    "pacientes.telefono AS 'Telefono', gestion_cita.detalleCita AS 'Detalle Cita',gestion_cita.duracion AS 'Duración' " +
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

            Conexión conect1 = new Conexión();
            con1 = conect1.getConnection();

            String dts[] = new String[7];
            String sql = "SELECT gestion_cita.fecha, gestion_cita.horario, gestion_cita.estatus, " +
                    "CONCAT(pacientes.nombre, ' ', pacientes.apellido) AS 'Nombre Completo', " +
                    "pacientes.telefono AS 'Telefono', gestion_cita.detalleCita AS 'Detalle Cita', gestion_cita.duracion AS 'Duración'  " +
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
      //txtBuscar.requestFocus();
    }// limpiar

    public void vaciarTabla() {
        DefaultTableModel m = (DefaultTableModel) tblCita.getModel();
        String titulos[] = { "No. Fecha", "Hora", "Status", "Nombre del paciente", "Telefono", "Detalle cita", "Duración" };
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
    
   void Buscar(String valor) {
    Connection con = null;
    Conexión conect = new Conexión();
    con = conect.getConnection();

    String[] palabras = valor.split(" ");
    String sql = "SELECT id, nombre, apellido, telefono FROM pacientes WHERE ";

    for (int i = 0; i < palabras.length; i++) {
        if (i > 0) {
            sql += " AND ";
        }
        sql += "CONCAT(nombre, ' ', apellido) LIKE ?";
    }

    try {
        PreparedStatement pst = con.prepareStatement(sql);
        for (int i = 0; i < palabras.length; i++) {
            pst.setString(i + 1, "%" + palabras[i] + "%");
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
            showMessageDialog(null, "Paciente no encontrado");
        }
    } 
    catch (SQLException ex) {
        System.out.println("ERROR AL BUSCAR: " + ex);
    }
}

    

    


    private void agregar() {
        if (validaMotivo(tagInputControl1)) {
            lblDetalle.setForeground(Color.RED);
            return;
        } else
            lblDetalle.setForeground(Color.BLACK);
        int fila = tblCita.getSelectedRow();
        if (fila == -1) {
            JOptionPane.showMessageDialog(null, "DEBE SELECCIONAR UN REGISTRO", "Advertencia",
                    JOptionPane.WARNING_MESSAGE);
        } else
            try {
                Connection con = null;
                Conexión conect = new Conexión();
                con = conect.getConnection();
                Statement st = con.createStatement();
                String sql = "UPDATE gestion_cita SET idPaciente = ?, estatus = 'Confirmar', detalleCita= ?, duracion= ? WHERE fecha = ? AND horario = ? AND estatus='Disponible'";
                String dao = (String) tblCita.getValueAt(fila, 2);
                PreparedStatement pst = con.prepareCall(sql);
                pst.setString(1, txtID.getText());
                pst.setString(2, tagInputControl1.getTags(0));
                pst.setString(3, txtDuracion.getText());
                pst.setString(4, txtDiaCita.getText());
                pst.setString(5, txtHoraCita.getText());
                int n = pst.executeUpdate();
                if (n > 0) {
                    JOptionPane.showMessageDialog(this, "CITA GUARDADA CORRECTAMENTE");
                    limpiar();
                    vaciarTabla();
                    Date fechaSeleccionada = fecha.getDate();
                    if (fechaSeleccionada == null) {
                        cargarDatos();
                    } else {
                        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                        String fechaFormateada = sdf.format(fechaSeleccionada);
                        cargarDatos2();
                        this.colorTabla();
                    }
                }
            } catch (SQLException | HeadlessException e) {
                JOptionPane.showMessageDialog(this, "LOS DATOS NO HAN SIDO GUARDADOS CORRECTAMENTE" + e, "Error",
                        JOptionPane.ERROR_MESSAGE);
            }

    }// agregar

    private void Eliminar() {
        int fila = tblCita.getSelectedRow();
        if (fila == -1) {
            JOptionPane.showMessageDialog(null, "DEBE SELECCIONAR UN REGISTRO", "Advertencia",
                    JOptionPane.WARNING_MESSAGE);
        } else
            try {
                Connection con = null;
                Conexión conect = new Conexión();
                con = conect.getConnection();
                Statement st = con.createStatement();
                String sql = "UPDATE gestion_cita SET idPaciente = NULL, estatus = 'Disponible', detalleCita='' WHERE fecha = ? AND horario = ?";
                PreparedStatement pst = con.prepareCall(sql);
                pst.setString(1, txtDiaCita.getText());
                pst.setString(2, txtHoraCita.getText());
                int n = pst.executeUpdate();
                if (n > 0) {
                    JOptionPane.showMessageDialog(this, "CITA ELIMINADA CORRECTAMENTE");
                    limpiar();
                    vaciarTabla();
                    Date fechaSeleccionada = fecha.getDate();
                    if (fechaSeleccionada == null) {
                        cargarDatos();
                        this.colorTabla();
                    } else {
                        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                        String fechaFormateada = sdf.format(fechaSeleccionada);
                        cargarDatos2();
                        this.colorTabla();
                    }
                }
            } catch (SQLException | HeadlessException e) {
                JOptionPane.showMessageDialog(this, "LOS DATOS NO HAN SIDO ELIMINADOS CORRECTAMENTE" + e, "Error",
                        JOptionPane.ERROR_MESSAGE);
            }

    }// Eliminar

    private void confirmar() {
        int fila = tblCita.getSelectedRow();
        if (fila == -1) {
            JOptionPane.showMessageDialog(null, "DEBE SELECCIONAR UN REGISTRO", "Advertencia",
                    JOptionPane.WARNING_MESSAGE);
        } else
            try {
                Connection con = null;
                Conexión conect = new Conexión();
                con = conect.getConnection();
                Statement st = con.createStatement();
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
                        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                        String fechaFormateada = sdf.format(fechaSeleccionada);
                        cargarDatos2();
                        this.colorTabla();
                    }
                }
            } catch (SQLException | HeadlessException e) {
                JOptionPane.showMessageDialog(this, "LOS DATOS NO HAN SIDO ACTUALIZADOS CORRECTAMENTE" + e, "Error",
                        JOptionPane.ERROR_MESSAGE);
            }

    }// Confirmar

    private void finalizarCita() {
        int fila = tblCita.getSelectedRow();
        if (fila == -1) {
            JOptionPane.showMessageDialog(null, "DEBE SELECCIONAR UN REGISTRO", "Advertencia",
                    JOptionPane.WARNING_MESSAGE);
        } else
            try {
                Connection con = null;
                Conexión conect = new Conexión();
                con = conect.getConnection();
                Statement st = con.createStatement();
                String sql = "UPDATE gestion_cita SET  estatus = 'Finalizada' WHERE fecha = ? AND horario = ? AND estatus='Confirmada'";
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
                        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                        String fechaFormateada = sdf.format(fechaSeleccionada);
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
                Conexión conect = new Conexión();
                con = conect.getConnection();
                Statement st = con.createStatement();
                String sql = "UPDATE gestion_cita SET  estatus = 'Bloqueada' WHERE fecha = ? AND horario = ? AND estatus='Disponible'";
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
                        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                        String fechaFormateada = sdf.format(fechaSeleccionada);
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



    


    private void txtHoraCitaActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_txtHoraCitaActionPerformed
        // TODO add your handling code here:
    }// GEN-LAST:event_txtHoraCitaActionPerformed

    private void fechaMouseClicked(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_fechaMouseClicked

    }// GEN-LAST:event_fechaMouseClicked

    private void tblCitaMouseClicked(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_tblCitaMouseClicked

        try {
            int fila = tblCita.getSelectedRow();
            txtDiaCita.setText(tblCita.getValueAt(fila, 0).toString());
            txtHoraCita.setText(tblCita.getValueAt(fila, 1).toString());
            txtTelefono.setText(tblCita.getValueAt(fila, 4).toString());
            String tags;
            if (!(tags = (String) tblCita.getValueAt(fila, 5)).equals("")) {
                tagInputControl1.setTags(tags);
            }

            if (tblCita.getValueAt(fila, 2).toString().equals("Disponible")) {
                lblConfirmar.setEnabled(false);
                lblFinalizar.setEnabled(false);
                lblGuardar.setEnabled(true);
                lblEliminar.setEnabled(false);
                lblModificar.setEnabled(false);
            } else if (tblCita.getValueAt(fila, 2).toString().equals("Confirmar")) {
                lblConfirmar.setEnabled(true);
                lblGuardar.setEnabled(false);
                lblFinalizar.setEnabled(false);
            }
            if (tblCita.getValueAt(fila, 2).toString().equals("Confirmada")) {
                lblFinalizar.setEnabled(true);
                lblGuardar.setEnabled(false);
                lblConfirmar.setEnabled(false);
            }

            if (tblCita.getValueAt(fila, 2).toString().equals("Confirmar")
                    || tblCita.getValueAt(fila, 2).toString().equals("Confirmada")
                    || tblCita.getValueAt(fila, 2).toString().equals("Finalizada")) {
                lblEliminar.setEnabled(true);
                lblGuardar.setEnabled(false);
            }
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


    private void lblBack1MouseClicked(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_lblBack1MouseClicked

        MenuDentista login = new MenuDentista();
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
        VentanaPaciente next = new VentanaPaciente();
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

    private void lblExpediente1MouseClicked(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_lblExpediente1MouseClicked
        VentanaExpedienteMenu exp = new VentanaExpedienteMenu();
        exp.setVisible(true);
        exp.setLocationRelativeTo(null);
        this.dispose();
    }// GEN-LAST:event_lblExpediente1MouseClicked

    private void lblExpediente1MouseEntered(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_lblExpediente1MouseEntered
        lblExpediente1.setForeground(Color.GRAY);
    }// GEN-LAST:event_lblExpediente1MouseEntered

    private void lblExpediente1MouseExited(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_lblExpediente1MouseExited
        lblExpediente1.setForeground(Color.BLACK);
    }// GEN-LAST:event_lblExpediente1MouseExited

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

    private void lblPass1MouseClicked(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_lblPass1MouseClicked
        Gestionar_Contraseñas pass = new Gestionar_Contraseñas();
        pass.setVisible(true);
        pass.setLocationRelativeTo(null);
        this.dispose();
    }// GEN-LAST:event_lblPass1MouseClicked

    private void lblPass1MouseEntered(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_lblPass1MouseEntered
        lblPass1.setForeground(Color.GRAY);
    }// GEN-LAST:event_lblPass1MouseEntered

    private void lblPass1MouseExited(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_lblPass1MouseExited
        lblPass1.setForeground(Color.BLACK);
    }// GEN-LAST:event_lblPass1MouseExited

    private boolean isDateChooserNotEmpty(JDateChooser dateChooser) {
        Date selectedDate = dateChooser.getDate();
        return selectedDate != null;
    }

    private void btnMostrarActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_btnMostrarActionPerformed
        JDateChooser dateChooser = new JDateChooser();
        if (isDateChooserNotEmpty(fecha)) {
            cargarDatos2();
        } else {
            // Muestra un mensaje de error indicando que la fecha no puede estar vacía
            JOptionPane.showMessageDialog(null, "Por favor, selecciona una fecha válida.", "Error",
                    JOptionPane.ERROR_MESSAGE);
        }

    }// GEN-LAST:event_btnMostrarActionPerformed

    private void lblGuardarMouseClicked(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_lblGuardarMouseClicked
        agregar();

    }// GEN-LAST:event_lblGuardarMouseClicked

    private void lblEliminarMouseClicked(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_lblEliminarMouseClicked
        Eliminar();
    }// GEN-LAST:event_lblEliminarMouseClicked

    private void lblConfirmarMouseClicked(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_lblConfirmarMouseClicked
        confirmar();
        lblConfirmar.setEnabled(false);
    }// GEN-LAST:event_lblConfirmarMouseClicked

    private void lblModificarMouseClicked(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_lblModificarMouseClicked
        if (validaMotivo(tagInputControl1)) {
            lblDetalle.setForeground(Color.RED);
            return;
        } else
            lblDetalle.setForeground(Color.BLACK);
        int fila = tblCita.getSelectedRow();
        if (fila == -1) {
            JOptionPane.showMessageDialog(null, "DEBE SELECCIONAR UN REGISTRO", "Advertencia",
                    JOptionPane.WARNING_MESSAGE);
        } else {
            try {
                Connection con = null;
                Conexión conect = new Conexión();
                con = conect.getConnection();
                Statement st = con.createStatement();
                String sql = "UPDATE gestion_cita SET detalleCita = ? WHERE fecha = ? AND horario = ?";
                PreparedStatement pst = con.prepareCall(sql);
                // Obtener el detalle seleccionado en el JComboBox
                pst.setString(1, tagInputControl1.getTags(0));
                pst.setString(2, txtDiaCita.getText());
                pst.setString(3, txtHoraCita.getText());
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
                        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                        String fechaFormateada = sdf.format(fechaSeleccionada);
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

    private void lblFinalizarMouseClicked(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_lblFinalizarMouseClicked
        String text = txtTelefono.getText(), fecha, tags, estatus;
        fecha = tblCita.getValueAt(tblCita.getSelectedRow(), 0).toString();
        estatus = tblCita.getValueAt(tblCita.getSelectedRow(), 2).toString();
        tags = tagInputControl1.getTags(0);
        if (!estatus.equals("Finalizada"))
            finalizarCita();
        Paciente p = Paciente.consultar(text);
        new VentanaExpediente(p, fecha, tags).setVisible(true);
    }// GEN-LAST:event_lblFinalizarMouseClicked

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
                if ("Windows".equals(info.getName())) {
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
        // </editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new VentanaCitas().setVisible(true);
            }
        });
    }

    private DefaultTableModel m;
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
    private javax.swing.JSeparator jSeparator8;
    private javax.swing.JLabel lblBack1;
    private Componentes.JImageBox lblBuscar;
    private javax.swing.JLabel lblCitas1;
    private Componentes.JImageBox lblConfirmar;
    private javax.swing.JLabel lblDetalle;
    private Componentes.JImageBox lblEliminar;
    private javax.swing.JLabel lblExpediente1;
    private Componentes.JImageBox lblFinalizar;
    private Componentes.JImageBox lblGuardar;
    private javax.swing.JLabel lblMenu1;
    private Componentes.JImageBox lblModificar;
    private javax.swing.JLabel lblPaciente1;
    private javax.swing.JLabel lblPass1;
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
