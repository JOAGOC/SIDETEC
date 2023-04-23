
import Conexión.Conexión;
import desplazable.Desface;
import java.awt.Color;
import java.awt.HeadlessException;
import java.awt.Image;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import static javax.swing.JOptionPane.showMessageDialog;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;


public class VentanaPaciente extends javax.swing.JFrame {
    private ImageIcon imagen;
    private ImageIcon icono; 
    Desface desplace;
     
    public VentanaPaciente() {
        initComponents();
        desplace = new Desface();
        setLocationRelativeTo(null);
        m=(DefaultTableModel) tblPaciente.getModel();
        cargarDatos();
        txtID.setEditable(false);
        txtID.setEnabled(false);
        this.Imagen(this.lblAgregar,"Imagenes\\ag.png");
        this.Imagen(this.lblEliminar,"Imagenes\\eli.png");
        this.Imagen(this.lblBuscar,"Imagenes\\bu.png");
        this.Imagen(this.lblLimpiar,"Imagenes\\li.png");
        this.Imagen(this.lblModificar,"Imagenes\\modi.png");
        
    }
     private void Imagen(JLabel lbl,String ruta){
        this.imagen=new ImageIcon(ruta);
        this.icono=new ImageIcon(
                this.imagen.getImage().getScaledInstance
                       (lbl.getWidth(),
                        lbl.getHeight(),
                        Image.SCALE_SMOOTH));
        lbl.setIcon(this.icono);
        repaint();
      }
private boolean validaCampo(JTextField t){
        try{   estaVacio(t);
        }catch(Exception e){
            showMessageDialog(this,e.getMessage());t.requestFocus(); return true;
        }
        return false;
    }
    
    private void estaVacio(JTextField t)throws Exception{
        String cad=t.getText().trim();
        if(cad.equals("")) throw new Exception("Campo vacio");
    }
    private void agregar(){
        try
            {
                Connection con = null;
                Conexión conect = new Conexión();
                con = conect.getConnection();
                Statement st = con.createStatement();
                String sql = "insert into pacientes (nombre,apellido,edad,genero, telefono,direccion) values (?,?,?,?,?,?)";
                PreparedStatement pst = con.prepareStatement(sql);
              
                pst.setString(1, txtNombre.getText());
                pst.setString(2, txtApellido.getText());
                pst.setString(3, txtEdad.getText());
                pst.setString(4, cmbGenero.getSelectedItem().toString());
                pst.setString(5, txtTelefono.getText());
                pst.setString(6, txtDireccion.getText());
                
                
                int n = pst.executeUpdate();
                if (n > 0)
                {
                    JOptionPane.showMessageDialog(this, "DATOS GUARDADOS CORRECTAMENTE");
                    limpiar();
                    vaciarTabla();
                    cargarDatos();
                    
                }
            } catch (SQLException | HeadlessException e)
            {
                JOptionPane.showMessageDialog(this, "LOS DATOS NO HAN SIDO GUARDADOS CORRECTAMENTE"+e, "Error", JOptionPane.ERROR_MESSAGE);
            }
        
         
    }//agregar
public void limpiar(){
        
       this.txtID.setText("");
       this.txtNombre.setText("");
       this.txtApellido.setText("");
       this.txtEdad.setText("");
       this.txtDireccion.setText("");
       this.txtTelefono.setText("");
       this.cmbGenero.setSelectedItem("Seleccionar");
       txtID.requestFocus();
    }//limpiar
    public void vaciarTabla(){
        DefaultTableModel m = (DefaultTableModel) tblPaciente.getModel();
        String titulos[] = {"ID","Nombre","Apellidos","Edad","Genero","Telefono","Direccion"};
        m = new DefaultTableModel(null,titulos);
        tblPaciente.setModel(m);
    }//vaciarTabla
    
    public void cargarDatos() {
         try {
            Connection con1 = null;
            DefaultTableModel m = (DefaultTableModel) tblPaciente.getModel();
            Conexión conect1 = new Conexión();
            con1 = conect1.getConnection();
            String dts[] = new String[7];
            String sql = "select * from pacientes";
            Statement st = con1.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while(rs.next()){
                dts[0] = rs.getString("id");
                dts[1] = rs.getString("nombre");
                dts[2] = rs.getString("apellido");
                dts[3] = rs.getString("edad");
                dts[4] = rs.getString("genero");
                dts[5] = rs.getString("telefono");
                dts[6] = rs.getString("direccion");
                m.addRow(dts);
            }
            tblPaciente.setModel(m);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "NO SE PUEDEN VISUALIZAR LOS DATOS DE LA TABLA "+ e, "Error", JOptionPane.ERROR_MESSAGE);
        
    }}//cargarDatos

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        MenuPleglable = new javax.swing.JPanel();
        lblBack = new javax.swing.JLabel();
        lblPaciente = new javax.swing.JLabel();
        lblCitas = new javax.swing.JLabel();
        lblExpediente = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        lblMenu = new javax.swing.JLabel();
        lblPass = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        txtNombre = new javax.swing.JTextField();
        txtApellido = new javax.swing.JTextField();
        lblAgregar = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        txtID = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblPaciente = new javax.swing.JTable();
        jLabel8 = new javax.swing.JLabel();
        txtDireccion = new javax.swing.JTextField();
        cbxBuscar = new javax.swing.JComboBox<>();
        lblEliminar = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txtTelefono = new javax.swing.JTextField();
        txtBuscar = new javax.swing.JTextField();
        txtEdad = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        cmbGenero = new javax.swing.JComboBox<>();
        lblModificar = new javax.swing.JLabel();
        lblLimpiar = new javax.swing.JLabel();
        lblBuscar = new javax.swing.JLabel();
        MenuPleglable1 = new javax.swing.JPanel();
        lblBack1 = new javax.swing.JLabel();
        lblPaciente1 = new javax.swing.JLabel();
        lblCitas1 = new javax.swing.JLabel();
        lblExpediente1 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        lblMenu1 = new javax.swing.JLabel();
        lblPass1 = new javax.swing.JLabel();
        panel1 = new java.awt.Panel();
        jLabel1 = new javax.swing.JLabel();

        MenuPleglable.setBackground(new java.awt.Color(69, 204, 209));
        MenuPleglable.setMinimumSize(new java.awt.Dimension(0, 577));
        MenuPleglable.setLayout(null);

        lblBack.setBackground(new java.awt.Color(0, 0, 0));
        lblBack.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lblBack.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblBack.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/flecha-izquierda90.png"))); // NOI18N
        lblBack.setText("Regresar           ");
        lblBack.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 50));
        lblBack.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
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
        lblBack.setBounds(0, 510, 310, 70);

        lblPaciente.setBackground(new java.awt.Color(0, 0, 0));
        lblPaciente.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lblPaciente.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblPaciente.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/anadir.png"))); // NOI18N
        lblPaciente.setText("Registro de Paciente ");
        lblPaciente.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 50));
        lblPaciente.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lblPaciente.setHorizontalTextPosition(javax.swing.SwingConstants.LEADING);
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
        lblPaciente.setBounds(20, 130, 291, 66);

        lblCitas.setBackground(new java.awt.Color(0, 0, 0));
        lblCitas.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lblCitas.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblCitas.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/calendario90.png"))); // NOI18N
        lblCitas.setText("Gestion de Citas      ");
        lblCitas.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 50));
        lblCitas.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lblCitas.setHorizontalTextPosition(javax.swing.SwingConstants.LEADING);
        lblCitas.setIconTextGap(10);
        lblCitas.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                lblCitasMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                lblCitasMouseExited(evt);
            }
        });
        MenuPleglable.add(lblCitas);
        lblCitas.setBounds(0, 220, 330, 66);

        lblExpediente.setBackground(new java.awt.Color(0, 0, 0));
        lblExpediente.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lblExpediente.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblExpediente.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/reportar90.png"))); // NOI18N
        lblExpediente.setText("Expediente Clínico    ");
        lblExpediente.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 50));
        lblExpediente.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lblExpediente.setHorizontalTextPosition(javax.swing.SwingConstants.LEADING);
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
        lblExpediente.setBounds(0, 320, 330, 66);

        jLabel9.setBackground(new java.awt.Color(0, 0, 0));
        jLabel9.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel9.setText("      OPCIONES   ");
        jLabel9.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 50));
        jLabel9.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
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
        MenuPleglable.add(jLabel9);
        jLabel9.setBounds(0, 60, 270, 50);

        lblMenu.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblMenu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/menu (4).png"))); // NOI18N
        lblMenu.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 10));
        lblMenu.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
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
        lblPass.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/seguro.png"))); // NOI18N
        lblPass.setText("Gestion Contraseñas ");
        lblPass.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 50));
        lblPass.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lblPass.setHorizontalTextPosition(javax.swing.SwingConstants.LEADING);
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
        lblPass.setBounds(10, 420, 310, 66);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        jPanel1.add(txtNombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 110, 360, -1));
        jPanel1.add(txtApellido, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 140, 360, -1));

        lblAgregar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblAgregarMouseClicked(evt);
            }
        });
        jPanel1.add(lblAgregar, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 640, 70, 70));

        jLabel12.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel12.setText("ID:");
        jPanel1.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 80, -1, -1));

        jLabel11.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel11.setText("Dirección:");
        jPanel1.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 260, -1, -1));
        jPanel1.add(txtID, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 80, 360, -1));

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel6.setText("Teléfono:");
        jPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 220, -1, -1));

        tblPaciente.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        tblPaciente.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Nombre", "Apellidos", "Edad", "Genéro", "Teléfono", "Dirección"
            }
        ));
        tblPaciente.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblPacienteMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblPaciente);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 350, 720, 270));

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel8.setText("Nombre:");
        jPanel1.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 110, -1, -1));
        jPanel1.add(txtDireccion, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 260, 360, -1));

        cbxBuscar.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        cbxBuscar.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Seleccionar", "Nombre", "Apellido", " " }));
        jPanel1.add(cbxBuscar, new org.netbeans.lib.awtextra.AbsoluteConstraints(890, 300, -1, -1));

        lblEliminar.setText("jLabel10");
        lblEliminar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblEliminarMouseClicked(evt);
            }
        });
        jPanel1.add(lblEliminar, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 640, 70, 70));

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel3.setText("Apellido:");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 140, -1, -1));
        jPanel1.add(txtTelefono, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 220, 360, -1));

        txtBuscar.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        txtBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtBuscarActionPerformed(evt);
            }
        });
        txtBuscar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtBuscarKeyReleased(evt);
            }
        });
        jPanel1.add(txtBuscar, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 300, 475, -1));
        jPanel1.add(txtEdad, new org.netbeans.lib.awtextra.AbsoluteConstraints(750, 180, 120, -1));

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel4.setText("Edad:");
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 180, -1, -1));

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel5.setText("Género:");
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 180, -1, -1));

        cmbGenero.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        cmbGenero.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Seleccionar", "Masculino", "Femenino" }));
        jPanel1.add(cmbGenero, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 180, 119, -1));

        lblModificar.setText("jLabel10");
        lblModificar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblModificarMouseClicked(evt);
            }
        });
        jPanel1.add(lblModificar, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 640, 70, 70));

        lblLimpiar.setText("jLabel10");
        lblLimpiar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblLimpiarMouseClicked(evt);
            }
        });
        jPanel1.add(lblLimpiar, new org.netbeans.lib.awtextra.AbsoluteConstraints(820, 640, 70, 70));

        lblBuscar.setText("jLabel10");
        lblBuscar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblBuscarMouseClicked(evt);
            }
        });
        jPanel1.add(lblBuscar, new org.netbeans.lib.awtextra.AbsoluteConstraints(1040, 280, 60, 60));

        MenuPleglable1.setBackground(new java.awt.Color(69, 204, 209));
        MenuPleglable1.setMinimumSize(new java.awt.Dimension(0, 577));
        MenuPleglable1.setLayout(null);

        lblBack1.setBackground(new java.awt.Color(0, 0, 0));
        lblBack1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lblBack1.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblBack1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/flecha-izquierda90.png"))); // NOI18N
        lblBack1.setText("Regresar           ");
        lblBack1.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 50));
        lblBack1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
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
        lblBack1.setBounds(0, 660, 310, 70);

        lblPaciente1.setBackground(new java.awt.Color(0, 0, 0));
        lblPaciente1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lblPaciente1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblPaciente1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/anadir.png"))); // NOI18N
        lblPaciente1.setText("Registro de Paciente ");
        lblPaciente1.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 50));
        lblPaciente1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
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
        lblPaciente1.setBounds(20, 170, 291, 66);

        lblCitas1.setBackground(new java.awt.Color(0, 0, 0));
        lblCitas1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lblCitas1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblCitas1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/calendario90.png"))); // NOI18N
        lblCitas1.setText("Gestion de Citas      ");
        lblCitas1.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 50));
        lblCitas1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lblCitas1.setHorizontalTextPosition(javax.swing.SwingConstants.LEADING);
        lblCitas1.setIconTextGap(10);
        lblCitas1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                lblCitas1MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                lblCitas1MouseExited(evt);
            }
        });
        MenuPleglable1.add(lblCitas1);
        lblCitas1.setBounds(0, 270, 330, 66);

        lblExpediente1.setBackground(new java.awt.Color(0, 0, 0));
        lblExpediente1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lblExpediente1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblExpediente1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/reportar90.png"))); // NOI18N
        lblExpediente1.setText("Expediente Clínico    ");
        lblExpediente1.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 50));
        lblExpediente1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
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
        lblExpediente1.setBounds(0, 390, 330, 66);

        jLabel7.setBackground(new java.awt.Color(0, 0, 0));
        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel7.setText("      OPCIONES   ");
        jLabel7.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 50));
        jLabel7.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
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
        MenuPleglable1.add(jLabel7);
        jLabel7.setBounds(10, 100, 270, 50);

        lblMenu1.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblMenu1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/menu (4).png"))); // NOI18N
        lblMenu1.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 10));
        lblMenu1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lblMenu1.setIconTextGap(0);
        lblMenu1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblMenu1MouseClicked(evt);
            }
        });
        MenuPleglable1.add(lblMenu1);
        lblMenu1.setBounds(0, 10, 270, 50);

        lblPass1.setBackground(new java.awt.Color(0, 0, 0));
        lblPass1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lblPass1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblPass1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/seguro.png"))); // NOI18N
        lblPass1.setText("Gestion Contraseñas ");
        lblPass1.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 50));
        lblPass1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
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
        lblPass1.setBounds(10, 510, 310, 66);

        jPanel1.add(MenuPleglable1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, -1, 269, 740));

        panel1.setBackground(new java.awt.Color(92, 225, 230));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel1.setText("Registro de Pacientes");

        javax.swing.GroupLayout panel1Layout = new javax.swing.GroupLayout(panel1);
        panel1.setLayout(panel1Layout);
        panel1Layout.setHorizontalGroup(
            panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panel1Layout.createSequentialGroup()
                .addContainerGap(569, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(363, 363, 363))
        );
        panel1Layout.setVerticalGroup(
            panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addContainerGap(28, Short.MAX_VALUE))
        );

        jPanel1.add(panel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1190, 70));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
void llenarCamposTexto(String[] datos) {
    txtID.setText(datos[0]);
    txtNombre.setText(datos[1]);
    txtApellido.setText(datos[2]);
    txtEdad.setText(datos[3]);
    cmbGenero.setSelectedItem(datos[4]);
    txtTelefono.setText(datos[5]);
    txtDireccion.setText(datos[6]);
}

void Buscar(String valor){
    Connection con = null;
    Conexión conect = new Conexión();
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
        if (cbxBuscar.getSelectedItem().toString().equals("Nombre")) {
            sql = "SELECT * FROM pacientes WHERE nombre='"+valor+"'";
        } else if(cbxBuscar.getSelectedItem().toString().equals("Apellido")) {
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
            String[] vacio = {"", "", "", "", "", "", ""};
            llenarCamposTexto(vacio);
        }
        
    } catch(SQLException ex) {
        Logger.getLogger("ERROR AL BUSCAR"+ex);
    }
}
   
    private void lblPassMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblPassMouseExited
        lblPass.setForeground(Color.BLACK);
    }//GEN-LAST:event_lblPassMouseExited

    private void lblPassMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblPassMouseEntered
        lblPass.setForeground(Color.WHITE);
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

    private void jLabel9MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel9MouseExited
        //lblExpediente.setForeground(Color.BLACK);
    }//GEN-LAST:event_jLabel9MouseExited

    private void jLabel9MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel9MouseEntered
        //lblExpediente.setForeground(Color.BLUE);
    }//GEN-LAST:event_jLabel9MouseEntered

    private void lblExpedienteMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblExpedienteMouseExited
        lblExpediente.setForeground(Color.BLACK);
    }//GEN-LAST:event_lblExpedienteMouseExited

    private void lblExpedienteMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblExpedienteMouseEntered
        lblExpediente.setForeground(Color.WHITE);
    }//GEN-LAST:event_lblExpedienteMouseEntered

    private void lblExpedienteMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblExpedienteMouseClicked
        VentanaExpediente exp=new VentanaExpediente();
        exp.setVisible(true);
        exp.setLocationRelativeTo(null);
        this.dispose();
    }//GEN-LAST:event_lblExpedienteMouseClicked

    private void lblCitasMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblCitasMouseExited
        lblCitas.setForeground(Color.BLACK);
    }//GEN-LAST:event_lblCitasMouseExited

    private void lblCitasMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblCitasMouseEntered
        lblCitas.setForeground(Color.WHITE);
    }//GEN-LAST:event_lblCitasMouseEntered

    private void lblPacienteMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblPacienteMouseExited
        lblPaciente.setForeground(Color.BLACK);
    }//GEN-LAST:event_lblPacienteMouseExited

    private void lblPacienteMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblPacienteMouseEntered
        lblPaciente.setForeground(Color.WHITE);
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
        lblBack.setForeground(Color.WHITE);
    }//GEN-LAST:event_lblBackMouseEntered

    private void lblBackMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblBackMouseClicked

        VentanaInicio_SesionDentista login = new VentanaInicio_SesionDentista();
        login.setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_lblBackMouseClicked

    private void lblBuscarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblBuscarMouseClicked
        Buscar(txtBuscar.getText());
    }//GEN-LAST:event_lblBuscarMouseClicked

    private void lblModificarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblModificarMouseClicked
        int fila = tblPaciente.getSelectedRow();
        if (fila == -1)
        {
            JOptionPane.showMessageDialog(null, "DEBE SELECCIONAR UN REGISTRO", "Advertencia", JOptionPane.WARNING_MESSAGE);
        }
        else
        try
        {
            Connection con = null;
            Conexión conect = null;
            con = conect.getConnection();
            Statement st = con.createStatement();
            String sql = "update pacientes set nombre = ?, apellido = ?, edad=?, genero=?, telefono = ?, direccion = ? where id = ?";
            String dao=(String)tblPaciente.getValueAt(fila,0);
            PreparedStatement pst = con.prepareCall(sql);
            pst.setString(1, txtNombre.getText());
            pst.setString(2, txtApellido.getText());
            pst.setString(3, txtEdad.getText());
            pst.setString(4, cmbGenero.getSelectedItem().toString());
            pst.setString(5, txtTelefono.getText());
            pst.setString(6, txtDireccion.getText());
            pst.setString(7,dao);
            int n = pst.executeUpdate();
            if (n > 0)
            {
                JOptionPane.showMessageDialog(this, "DATOS ACTUALIZADOS CORRECTAMENTE");
                limpiar();
                vaciarTabla();
                cargarDatos();

            }
        } catch (SQLException | HeadlessException e)
        {
            JOptionPane.showMessageDialog(this, "LOS DATOS NO HAN SIDO ACTUALIZADOS CORRECTAMENTE"+e, "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_lblModificarMouseClicked

    private void lblLimpiarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblLimpiarMouseClicked
        limpiar();
    }//GEN-LAST:event_lblLimpiarMouseClicked

    private void lblEliminarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblEliminarMouseClicked
        int fila = tblPaciente.getSelectedRow();
        if (fila == -1)
        {
            JOptionPane.showMessageDialog(null, "DEBE SELECCIONAR UN REGISTRO", "Advertencia", JOptionPane.WARNING_MESSAGE);
        }
        else
        {
            int opc = JOptionPane.showConfirmDialog(this, "¿ESTA SEGURO QUE DESEA ELIMINAR ESTE REGISTRO?", "Pregunta", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
            if (opc == JOptionPane.YES_OPTION)
            {
                try
                {
                    Connection con = null;
                    Conexión conect = new Conexión();
                    con = conect.getConnection();
                    Statement st = con.createStatement();
                    String sql = "delete from pacientes where id = ?";
                    PreparedStatement pst = con.prepareStatement(sql);
                    pst.setInt(1, Integer.parseInt(txtID.getText()));
                    int n = pst.executeUpdate();
                    if (n > 0)
                    {
                        JOptionPane.showMessageDialog(this, "DATOS ELIMINADOS CORRECTAMENTE");
                        limpiar();
                        vaciarTabla();
                        cargarDatos();

                    }
                } catch (SQLException ex)
                {
                    JOptionPane.showMessageDialog(this, "DATOS NO ELIMINADOS CORRECTAMENTE" + ex.getMessage());
                }
            }
        }
    }//GEN-LAST:event_lblEliminarMouseClicked

    private void lblAgregarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblAgregarMouseClicked
        agregar();
    }//GEN-LAST:event_lblAgregarMouseClicked

    private void txtBuscarKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBuscarKeyReleased

    }//GEN-LAST:event_txtBuscarKeyReleased

    private void txtBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtBuscarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtBuscarActionPerformed

    private void tblPacienteMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblPacienteMouseClicked
        try
        {
            int fila = tblPaciente.getSelectedRow();
            txtID.setText(tblPaciente.getValueAt(fila, 0).toString());
            txtNombre.setText(tblPaciente.getValueAt(fila, 1).toString());
            txtApellido.setText(tblPaciente.getValueAt(fila, 2).toString());
            txtEdad.setText(tblPaciente.getValueAt(fila, 3).toString());
            cmbGenero.setSelectedItem(tblPaciente.getValueAt(fila, 4).toString());
            txtTelefono.setText(tblPaciente.getValueAt(fila, 5).toString());
            txtDireccion.setText(tblPaciente.getValueAt(fila, 6).toString());

            //btnAgregar.setVisible(false);
        } catch (Exception ex)
        {
            System.out.println("ERROR AL SELECCIONAR UNA FILA: " + ex.getMessage());
        }
    }//GEN-LAST:event_tblPacienteMouseClicked

    private void lblPass1MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblPass1MouseExited
        lblPass1.setForeground(Color.BLACK);
    }//GEN-LAST:event_lblPass1MouseExited

    private void lblPass1MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblPass1MouseEntered
        lblPass1.setForeground(Color.WHITE);
    }//GEN-LAST:event_lblPass1MouseEntered

    private void lblPass1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblPass1MouseClicked
        Gestionar_Contraseñas pass=new Gestionar_Contraseñas();
        pass.setVisible(true);
        pass.setLocationRelativeTo(null);
        this.dispose();
    }//GEN-LAST:event_lblPass1MouseClicked

    private void lblMenu1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblMenu1MouseClicked
        if(MenuPleglable1.getX()==0)
        desplace.desplazarIzquierda(this.MenuPleglable1,MenuPleglable1.getX(),-180, 10,10 );
        else if(MenuPleglable1.getX()== -180)
        desplace.desplazarDerecha(this.MenuPleglable1,MenuPleglable1.getX(),0, 10,10);
    }//GEN-LAST:event_lblMenu1MouseClicked

    private void jLabel7MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel7MouseExited
        //lblExpediente.setForeground(Color.BLACK);
    }//GEN-LAST:event_jLabel7MouseExited

    private void jLabel7MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel7MouseEntered
        //lblExpediente.setForeground(Color.BLUE);
    }//GEN-LAST:event_jLabel7MouseEntered

    private void lblExpediente1MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblExpediente1MouseExited
        lblExpediente1.setForeground(Color.BLACK);
    }//GEN-LAST:event_lblExpediente1MouseExited

    private void lblExpediente1MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblExpediente1MouseEntered
        lblExpediente1.setForeground(Color.WHITE);
    }//GEN-LAST:event_lblExpediente1MouseEntered

    private void lblExpediente1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblExpediente1MouseClicked
        VentanaExpediente exp=new VentanaExpediente();
        exp.setVisible(true);
        exp.setLocationRelativeTo(null);
        this.dispose();
    }//GEN-LAST:event_lblExpediente1MouseClicked

    private void lblCitas1MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblCitas1MouseExited
        lblCitas1.setForeground(Color.BLACK);
    }//GEN-LAST:event_lblCitas1MouseExited

    private void lblCitas1MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblCitas1MouseEntered
        lblCitas1.setForeground(Color.WHITE);
    }//GEN-LAST:event_lblCitas1MouseEntered

    private void lblPaciente1MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblPaciente1MouseExited
        lblPaciente1.setForeground(Color.WHITE);
    }//GEN-LAST:event_lblPaciente1MouseExited

    private void lblPaciente1MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblPaciente1MouseEntered
        lblPaciente1.setForeground(Color.BLACK);
    }//GEN-LAST:event_lblPaciente1MouseEntered

    private void lblPaciente1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblPaciente1MouseClicked
        VentanaPaciente next=new VentanaPaciente();
        next.setVisible(true);
        next.setLocationRelativeTo(null);
        this.dispose();
    }//GEN-LAST:event_lblPaciente1MouseClicked

    private void lblBack1MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblBack1MouseExited
        lblBack1.setForeground(Color.BLACK);
    }//GEN-LAST:event_lblBack1MouseExited

    private void lblBack1MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblBack1MouseEntered
        lblBack1.setForeground(Color.WHITE);
    }//GEN-LAST:event_lblBack1MouseEntered

    private void lblBack1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblBack1MouseClicked

        MenuDentista login = new MenuDentista();
        login.setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_lblBack1MouseClicked

    
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
            java.util.logging.Logger.getLogger(VentanaPaciente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(VentanaPaciente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(VentanaPaciente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(VentanaPaciente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new VentanaPaciente().setVisible(true);
            }
        });
    }
private DefaultTableModel m;
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel MenuPleglable;
    private javax.swing.JPanel MenuPleglable1;
    private javax.swing.JComboBox<String> cbxBuscar;
    private javax.swing.JComboBox<String> cmbGenero;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblAgregar;
    private javax.swing.JLabel lblBack;
    private javax.swing.JLabel lblBack1;
    private javax.swing.JLabel lblBuscar;
    private javax.swing.JLabel lblCitas;
    private javax.swing.JLabel lblCitas1;
    private javax.swing.JLabel lblEliminar;
    private javax.swing.JLabel lblExpediente;
    private javax.swing.JLabel lblExpediente1;
    private javax.swing.JLabel lblLimpiar;
    private javax.swing.JLabel lblMenu;
    private javax.swing.JLabel lblMenu1;
    private javax.swing.JLabel lblModificar;
    private javax.swing.JLabel lblPaciente;
    private javax.swing.JLabel lblPaciente1;
    private javax.swing.JLabel lblPass;
    private javax.swing.JLabel lblPass1;
    private java.awt.Panel panel1;
    private javax.swing.JTable tblPaciente;
    private javax.swing.JTextField txtApellido;
    private javax.swing.JTextField txtBuscar;
    private javax.swing.JTextField txtDireccion;
    private javax.swing.JTextField txtEdad;
    private javax.swing.JTextField txtID;
    private javax.swing.JTextField txtNombre;
    private javax.swing.JTextField txtTelefono;
    // End of variables declaration//GEN-END:variables
}
