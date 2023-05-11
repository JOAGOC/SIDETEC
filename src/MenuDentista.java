import Conexión.Conexión;
import com.toedter.calendar.JDateChooser;
import desplazable.Desface;
import java.awt.Color;
import java.awt.Dimension;
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
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
public class MenuDentista extends javax.swing.JFrame {
    ColorRenderer colorRenderer = new ColorRenderer();
    private ImageIcon imagen;
    private ImageIcon icono;
    Desface desplace;
    public MenuDentista() {
        initComponents();
        desplace = new Desface();
        this.setLocationRelativeTo(null);
        tblCita.getColumnModel().getColumn(0).setHeaderRenderer(new DTable(new Color(230,192,233),Color.BLACK));
        tblCita.getColumnModel().getColumn(1).setHeaderRenderer(new DTable(new Color(230,192,233),Color.BLACK));
        tblCita.getColumnModel().getColumn(2).setHeaderRenderer(new DTable(new Color(230,192,233),Color.BLACK));
        tblCita.getColumnModel().getColumn(3).setHeaderRenderer(new DTable(new Color(230,192,233),Color.BLACK));
        tblCita.getColumnModel().getColumn(4).setHeaderRenderer(new DTable(new Color(230,192,233),Color.BLACK));
        tblCita.getColumnModel().getColumn(5).setHeaderRenderer(new DTable(new Color(230,192,233),Color.BLACK));
            }

   /* private void Imagen(JLabel lbl,String ruta){
        this.imagen=new ImageIcon(ruta);
        this.icono=new ImageIcon(
                this.imagen.getImage().getScaledInstance
                       (lbl.getWidth(),
                        lbl.getHeight(),
                        Image.SCALE_SMOOTH));
        lbl.setIcon(this.icono);
        repaint();
      }*/
    public void cargarDatos() {
         try {
            Connection con1 = null;
            DefaultTableModel m = (DefaultTableModel) tblCita.getModel();
            Conexión conect1 = new Conexión();
            con1 = conect1.getConnection();
            String dts[] = new String[6];
            String sql = "SELECT gestion_cita.fecha, gestion_cita.horario, gestion_cita.estatus, " +
             "CONCAT(pacientes.nombre, ' ', pacientes.apellido) AS 'Nombre Completo', " +
             "pacientes.telefono AS 'Telefono', gestion_cita.detalleCita AS 'Detalle Cita' " +
             "FROM gestion_cita LEFT JOIN pacientes ON gestion_cita.idPaciente = pacientes.id where fecha=current_date()";

            Statement st = con1.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while(rs.next()){
                dts[0] = rs.getString("gestion_cita.fecha");
                dts[1] = rs.getString("gestion_cita.horario");
                dts[2] = rs.getString("gestion_cita.estatus");
                dts[3] = rs.getString("Nombre Completo");
                dts[4] = rs.getString("pacientes.telefono");
                dts[5] = rs.getString("Detalle Cita");
                m.addRow(dts);
            }
            int columnaEstado = 2; // reemplazar con el índice de la columna que contiene los estados
            tblCita.getColumnModel().getColumn(columnaEstado).setCellRenderer(colorRenderer);
            tblCita.setModel(m);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "NO SE PUEDEN VISUALIZAR LOS DATOS DE LA TABLA "+ e, "Error", JOptionPane.ERROR_MESSAGE);
        
    }}//cargarDatos
    public void cargarDatos2() {
    try {
        Date fechaSeleccionada = fecha.getDate();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-d");
        String fechaFormateada = sdf.format(fechaSeleccionada);

        Connection con1 = null;
        DefaultTableModel m = (DefaultTableModel) tblCita.getModel();
        m.setRowCount(0); // Limpiar todas las filas existentes de la tabla antes de cargar los nuevos datos

        Conexión conect1 = new Conexión();
        con1 = conect1.getConnection();

        String dts[] = new String[6];
        String sql = "SELECT gestion_cita.fecha, gestion_cita.horario, gestion_cita.estatus, " +
                     "CONCAT(pacientes.nombre, ' ', pacientes.apellido) AS 'Nombre Completo', " +
                     "pacientes.telefono AS 'Telefono', gestion_cita.detalleCita AS 'Detalle Cita' " +
                     "FROM gestion_cita LEFT JOIN pacientes ON gestion_cita.idPaciente = pacientes.id " +
                     "WHERE fecha = '" + fechaFormateada + "' and gestion_cita.estatus='Confirmada'";
        Statement st = con1.createStatement();
        ResultSet rs = st.executeQuery(sql);

        while (rs.next()) {
            dts[0] = rs.getString("gestion_cita.fecha");
            dts[1] = rs.getString("gestion_cita.horario");
            dts[2] = rs.getString("gestion_cita.estatus");
            dts[3] = rs.getString("Nombre Completo");
            dts[4] = rs.getString("pacientes.telefono");
            dts[5] = rs.getString("Detalle Cita");
            m.addRow(dts);
        }
        int columnaEstado = 2; // reemplazar con el índice de la columna que contiene los estados
        tblCita.getColumnModel().getColumn(columnaEstado).setCellRenderer(colorRenderer);
        tblCita.setModel(m);
    } catch (SQLException e) {
        JOptionPane.showMessageDialog(null, "NO SE PUEDEN VISUALIZAR LOS DATOS DE LA TABLA " + e, "Error", JOptionPane.ERROR_MESSAGE);
    }
}
 /* private void finalizarCita(){
        int fila = tblCita.getSelectedRow();
        if (fila == -1)
        {
            JOptionPane.showMessageDialog(null, "DEBE SELECCIONAR UN REGISTRO", "Advertencia", JOptionPane.WARNING_MESSAGE);
        }
        else
        try
        {
            Connection con = null;
            Conexión conect = new Conexión();
            con = conect.getConnection();
            Statement st = con.createStatement();
            String sql = "UPDATE gestion_cita SET  estatus = 'Finalizada' WHERE fecha = ? AND horario = ? AND estatus='Confirmada'";
            PreparedStatement pst = con.prepareCall(sql);
            pst.setString(1, txtDiaCita.getText());
            pst.setString(2, txtHoraCita.getText());
            int n = pst.executeUpdate();
            if (n > 0)
            {
                JOptionPane.showMessageDialog(this, "CITA FINALIZADA CORRECTAMENTE");
                limpiar();
                vaciarTabla();
                Date fechaSeleccionada = fecha.getDate();
                if (fechaSeleccionada == null) {
                cargarDatos();
                } else {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                String fechaFormateada = sdf.format(fechaSeleccionada);
                cargarDatos2();
                }
            }
        } catch (SQLException | HeadlessException e)
        {
            JOptionPane.showMessageDialog(this, "LOS DATOS NO HAN SIDO ACTUALIZADOS CORRECTAMENTE"+e, "Error", JOptionPane.ERROR_MESSAGE);
        }
        
         
    }//FinalizarCita
    */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        MenuPleglable = new javax.swing.JPanel();
        lblBack = new javax.swing.JLabel();
        lblPaciente = new javax.swing.JLabel();
        lblCitas = new javax.swing.JLabel();
        lblExpediente = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        lblMenu = new javax.swing.JLabel();
        lblPass = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jSeparator2 = new javax.swing.JSeparator();
        jSeparator3 = new javax.swing.JSeparator();
        jSeparator4 = new javax.swing.JSeparator();
        jSeparator5 = new javax.swing.JSeparator();
        jPanel4 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        fecha = new com.toedter.calendar.JDateChooser();
        jLabel3 = new javax.swing.JLabel();
        txtBuscar = new javax.swing.JTextField();
        cbxBuscar = new javax.swing.JComboBox<>();
        jImageBox1 = new JImageBox();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblCita = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        MenuPleglable.setBackground(new java.awt.Color(69, 204, 209));
        MenuPleglable.setMinimumSize(new java.awt.Dimension(0, 577));
        MenuPleglable.setLayout(null);

        lblBack.setBackground(new java.awt.Color(0, 0, 0));
        lblBack.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lblBack.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblBack.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/icons8-volver-4.png"))); // NOI18N
        lblBack.setText("Regresar           ");
        lblBack.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 50));
        lblBack.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        lblBack.setFocusable(false);
        lblBack.setHorizontalTextPosition(javax.swing.SwingConstants.LEADING);
        lblBack.setIconTextGap(10);
        lblBack.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblBackMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                lblBackMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                lblBackMouseExited(evt);
            }
        });
        MenuPleglable.add(lblBack);
        lblBack.setBounds(10, 510, 280, 70);

        lblPaciente.setBackground(new java.awt.Color(0, 0, 0));
        lblPaciente.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lblPaciente.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblPaciente.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/icons8-añadir-usuario-masculino-52.png"))); // NOI18N
        lblPaciente.setText("Registro de Paciente ");
        lblPaciente.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 50));
        lblPaciente.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        lblPaciente.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        lblPaciente.setIconTextGap(10);
        lblPaciente.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblPacienteMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                lblPacienteMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                lblPacienteMouseExited(evt);
            }
        });
        MenuPleglable.add(lblPaciente);
        lblPaciente.setBounds(11, 130, 300, 54);

        lblCitas.setBackground(new java.awt.Color(0, 0, 0));
        lblCitas.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lblCitas.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblCitas.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/icons8-planificación-de-tareas-52.png"))); // NOI18N
        lblCitas.setText("Gestion de Citas      ");
        lblCitas.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 50));
        lblCitas.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        lblCitas.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        lblCitas.setIconTextGap(10);
        lblCitas.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblCitasMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                lblCitasMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                lblCitasMouseExited(evt);
            }
        });
        MenuPleglable.add(lblCitas);
        lblCitas.setBounds(0, 220, 330, 54);

        lblExpediente.setBackground(new java.awt.Color(0, 0, 0));
        lblExpediente.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lblExpediente.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblExpediente.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/icons8-docket-52.png"))); // NOI18N
        lblExpediente.setText("Expediente Clínico    ");
        lblExpediente.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 50));
        lblExpediente.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        lblExpediente.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        lblExpediente.setIconTextGap(10);
        lblExpediente.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblExpedienteMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                lblExpedienteMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                lblExpedienteMouseExited(evt);
            }
        });
        MenuPleglable.add(lblExpediente);
        lblExpediente.setBounds(0, 320, 330, 54);

        jLabel7.setBackground(new java.awt.Color(0, 0, 0));
        jLabel7.setFont(new java.awt.Font("Times New Roman", 2, 24)); // NOI18N
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel7.setText("      Opciones");
        jLabel7.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 50));
        jLabel7.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jLabel7.setHorizontalTextPosition(javax.swing.SwingConstants.LEADING);
        jLabel7.setIconTextGap(10);
        jLabel7.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jLabel7MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jLabel7MouseExited(evt);
            }
        });
        MenuPleglable.add(jLabel7);
        jLabel7.setBounds(0, 0, 270, 50);

        lblMenu.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblMenu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/menu (4).png"))); // NOI18N
        lblMenu.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 10));
        lblMenu.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        lblMenu.setIconTextGap(0);
        lblMenu.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblMenuMouseClicked(evt);
            }
        });
        MenuPleglable.add(lblMenu);
        lblMenu.setBounds(0, 0, 270, 50);

        lblPass.setBackground(new java.awt.Color(0, 0, 0));
        lblPass.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lblPass.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblPass.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/icons8-contraseña-52.png"))); // NOI18N
        lblPass.setText("Gestion Contraseñas ");
        lblPass.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 50));
        lblPass.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        lblPass.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        lblPass.setIconTextGap(10);
        lblPass.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblPassMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                lblPassMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                lblPassMouseExited(evt);
            }
        });
        MenuPleglable.add(lblPass);
        lblPass.setBounds(10, 420, 310, 54);
        MenuPleglable.add(jSeparator1);
        jSeparator1.setBounds(0, 500, 270, 30);
        MenuPleglable.add(jSeparator2);
        jSeparator2.setBounds(0, 120, 270, 30);
        MenuPleglable.add(jSeparator3);
        jSeparator3.setBounds(0, 300, 270, 30);
        MenuPleglable.add(jSeparator4);
        jSeparator4.setBounds(0, 410, 270, 30);
        MenuPleglable.add(jSeparator5);
        jSeparator5.setBounds(0, 200, 270, 30);

        jPanel4.setBackground(new java.awt.Color(0, 108, 183));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Bienvenido Dentista");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(322, 322, 322)
                .addComponent(jLabel1)
                .addContainerGap(466, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addComponent(jLabel1)
                .addContainerGap(25, Short.MAX_VALUE))
        );

        jPanel5.setBackground(new java.awt.Color(0, 108, 183));

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 965, Short.MAX_VALUE)
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 29, Short.MAX_VALUE)
        );

        jPanel3.setBackground(new java.awt.Color(69, 204, 209));

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel4.setText("Fecha:");
        jLabel4.setMaximumSize(new java.awt.Dimension(86, 22));
        jLabel4.setMinimumSize(new java.awt.Dimension(86, 22));

        fecha.setMinSelectableDate(new java.util.Date(-62135744327000L));
        fecha.setMinimumSize(new java.awt.Dimension(64, 23));
        fecha.setPreferredSize(new java.awt.Dimension(150, 22));
        fecha.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                fechaMouseClicked(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel3.setText("Paciente:");

        txtBuscar.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtBuscar.setPreferredSize(new java.awt.Dimension(200, 22));

        cbxBuscar.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        cbxBuscar.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Seleccionar", "Nombre", "Apellido", "Id" }));
        cbxBuscar.setMinimumSize(new java.awt.Dimension(130, 28));
        cbxBuscar.setPreferredSize(new java.awt.Dimension(140, 28));

        jImageBox1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/lupa.png"))); // NOI18N
        jImageBox1.setPreferredSize(new java.awt.Dimension(32, 32));
        jImageBox1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jImageBox1MouseClicked(evt);
            }
        });

        tblCita.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        tblCita.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "No. Fecha", "Hora", "Status", "Nombre del paciente", "Telefono", "Detalle Cita"
            }
        ));
        tblCita.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblCitaMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tblCita);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 766, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(fecha, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(30, 30, 30)
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(30, 30, 30)
                        .addComponent(cbxBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jImageBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(26, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                                .addGap(9, 9, 9)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(txtBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(cbxBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel3)))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGap(13, 13, 13)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(fecha, javax.swing.GroupLayout.DEFAULT_SIZE, 29, Short.MAX_VALUE)
                                    .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 21, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jImageBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 334, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(MenuPleglable, javax.swing.GroupLayout.PREFERRED_SIZE, 269, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(36, 36, 36)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(78, 78, 78))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(MenuPleglable, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(21, 21, 21)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 37, Short.MAX_VALUE)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(-10, 0, 1180, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tblCitaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblCitaMouseClicked
        try
        {
            int fila = tblCita.getSelectedRow();

        } catch (Exception ex)
        {
            System.out.println("ERROR AL SELECCIONAR UNA FILA: " + ex.getMessage());
        }
    }//GEN-LAST:event_tblCitaMouseClicked

    private void jImageBox1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jImageBox1MouseClicked
        Buscar(txtBuscar.getText());
        JDateChooser dateChooser = new JDateChooser();
        if (isDateChooserNotEmpty(fecha)) {
            cargarDatos2();
        } else {
            // Muestra un mensaje de error indicando que la fecha no puede estar vacía
            JOptionPane.showMessageDialog(null, "Por favor, selecciona una fecha válida.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_jImageBox1MouseClicked

    private void fechaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_fechaMouseClicked

    }//GEN-LAST:event_fechaMouseClicked

    private void lblPassMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblPassMouseExited
        lblPass.setForeground(Color.BLACK);
    }//GEN-LAST:event_lblPassMouseExited

    private void lblPassMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblPassMouseEntered
        lblPass.setForeground(Color.GRAY);
    }//GEN-LAST:event_lblPassMouseEntered

    private void lblPassMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblPassMouseClicked
        Gestionar_Contraseñas pass=new Gestionar_Contraseñas();
        pass.setVisible(true);
        pass.setLocationRelativeTo(null);
        this.dispose();
    }//GEN-LAST:event_lblPassMouseClicked

    private void lblMenuMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblMenuMouseClicked
        if(MenuPleglable.getX()==0)
        desplace.desplazarIzquierda(this.MenuPleglable,MenuPleglable.getX(),-180, 10,10 );
        else if(MenuPleglable.getX()== -180)
        desplace.desplazarDerecha(this.MenuPleglable,MenuPleglable.getX(),0, 10,10);
    }//GEN-LAST:event_lblMenuMouseClicked

    private void jLabel7MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel7MouseExited
        //lblExpediente.setForeground(Color.BLACK);
    }//GEN-LAST:event_jLabel7MouseExited

    private void jLabel7MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel7MouseEntered
        //lblExpediente.setForeground(Color.BLUE);
    }//GEN-LAST:event_jLabel7MouseEntered

    private void lblExpedienteMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblExpedienteMouseExited
        lblExpediente.setForeground(Color.BLACK);
    }//GEN-LAST:event_lblExpedienteMouseExited

    private void lblExpedienteMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblExpedienteMouseEntered
        lblExpediente.setForeground(Color.GRAY);
    }//GEN-LAST:event_lblExpedienteMouseEntered

    private void lblExpedienteMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblExpedienteMouseClicked
        VentanaExpedienteMenu exp = new VentanaExpedienteMenu();
        exp.setVisible(true);
        exp.setLocationRelativeTo(null);
        this.dispose();
    }//GEN-LAST:event_lblExpedienteMouseClicked

    private void lblCitasMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblCitasMouseExited
        lblCitas.setForeground(Color.BLACK);
    }//GEN-LAST:event_lblCitasMouseExited

    private void lblCitasMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblCitasMouseEntered
        lblCitas.setForeground(Color.GRAY);
    }//GEN-LAST:event_lblCitasMouseEntered

    private void lblCitasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblCitasMouseClicked
        VentanaCitas ci=new VentanaCitas();
        ci.setVisible(true);
        ci.setLocationRelativeTo(null);
        this.dispose();
    }//GEN-LAST:event_lblCitasMouseClicked

    private void lblPacienteMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblPacienteMouseExited
        lblPaciente.setForeground(Color.BLACK);
    }//GEN-LAST:event_lblPacienteMouseExited

    private void lblPacienteMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblPacienteMouseEntered
        lblPaciente.setForeground(Color.GRAY);
    }//GEN-LAST:event_lblPacienteMouseEntered

    private void lblPacienteMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblPacienteMouseClicked
        VentanaPaciente next=new VentanaPaciente();
        next.setVisible(true);
        next.setLocationRelativeTo(null);
        this.dispose();
    }//GEN-LAST:event_lblPacienteMouseClicked

    private void lblBackMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblBackMouseExited
        lblBack.setForeground(Color.BLACK);
    }//GEN-LAST:event_lblBackMouseExited

    private void lblBackMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblBackMouseEntered
        lblBack.setForeground(Color.GRAY);
    }//GEN-LAST:event_lblBackMouseEntered

    private void lblBackMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblBackMouseClicked
        VentanaInicio_SesionDentista login = new VentanaInicio_SesionDentista();
        login.setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_lblBackMouseClicked
private boolean isDateChooserNotEmpty(JDateChooser dateChooser) {
    Date selectedDate = dateChooser.getDate();
    return selectedDate != null;
}void Buscar(String valor){
    Connection con = null;
    Conexión conect = new Conexión();
    con = conect.getConnection();
    String sql="";
    if (valor.equals("")) {
        sql = "Select id,nombre,apellido, telefono from pacientes;";
    } else {
        if (cbxBuscar.getSelectedItem().toString().equals("Nombre")) {
            sql = "SELECT id,nombre,apellido, telefono FROM pacientes WHERE nombre='"+valor+"'";
        } else if(cbxBuscar.getSelectedItem().toString().equals("Apellido")) {
            sql="SELECT id,nombre,apellido, telefono FROM pacientes WHERE apellido='"+valor+"'";
        } else if (cbxBuscar.getSelectedItem().toString().equals("Id")) {
            sql="SELECT id,nombre,apellido, telefono FROM pacientes WHERE id='"+valor+"'"; }
    }
    String []Datos = new String [4];
    try {
        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery(sql);
        boolean encontrado = false;
        while(rs.next()){
            Datos[0] = rs.getString(1);
            Datos[1] = rs.getString(2);
            Datos[2] = rs.getString(3);
            Datos[3] = rs.getString(4);
            
            encontrado = true;
        }

        if (encontrado) {
            llenarCamposTexto(Datos);
        } else {
            // Limpia los campos de texto si no se encuentra el registro
            String[] vacio = {"", "", "", "", "", "", ""};
            llenarCamposTexto(vacio);
        }
        
    } catch(SQLException ex) {
        Logger.getLogger("ERROR AL BUSCAR"+ex);
    }
}
void llenarCamposTexto(String[] datos) {
   /* txtID.setText(datos[0]);
    txtNombre.setText(datos[1]);
    txtApellido.setText(datos[2]);
    txtTelefono.setText(datos[3]);*/
}
/*
public void limpiar(){
        
       this.txtID.setText("");
       this.txtNombre.setText("");
       this.txtApellido.setText("");
       this.txtHoraCita.setText("");
       this.txtDiaCita.setText("");
       this.txtTelefono.setText("");
       this.cmbDetalle.setSelectedItem("-Seleccionar-");
       txtBuscar.requestFocus();
    }//limpiar*/
  public void vaciarTabla(){
        DefaultTableModel m = (DefaultTableModel) tblCita.getModel();
        String titulos[] = {"No. Fecha","Hora","Status","Nombre del paciente","Telefono","Detalle cita"};
        m = new DefaultTableModel(null,titulos);
        tblCita.setModel(m);
    }//vaciarTabla
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
            java.util.logging.Logger.getLogger(MenuDentista.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MenuDentista.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MenuDentista.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MenuDentista.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MenuDentista().setVisible(true);
            }
        });
    }
private DefaultTableModel m;
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel MenuPleglable;
    private javax.swing.JComboBox<String> cbxBuscar;
    private com.toedter.calendar.JDateChooser fecha;
    private JImageBox jImageBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JSeparator jSeparator5;
    private javax.swing.JLabel lblBack;
    private javax.swing.JLabel lblCitas;
    private javax.swing.JLabel lblExpediente;
    private javax.swing.JLabel lblMenu;
    private javax.swing.JLabel lblPaciente;
    private javax.swing.JLabel lblPass;
    private javax.swing.JTable tblCita;
    private javax.swing.JTextField txtBuscar;
    // End of variables declaration//GEN-END:variables
}
