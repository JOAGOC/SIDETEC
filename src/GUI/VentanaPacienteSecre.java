package GUI;


import Conexión.Conexión;
import desplazable.Desface;
import java.awt.Color;
import java.awt.Font;
import java.awt.HeadlessException;
import java.awt.Toolkit;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import static javax.swing.JOptionPane.showMessageDialog;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import Componentes.DTable;
import Componentes.JImageBox;


public class VentanaPacienteSecre extends javax.swing.JFrame {
    private ImageIcon imagen;
    private ImageIcon icono; 
    Desface desplace;
    JTableHeader th;
     
    public VentanaPacienteSecre() {
        initComponents();
        desplace = new Desface();
        setLocationRelativeTo(null);
        m=(DefaultTableModel) tblPaciente.getModel();
        cargarDatos();
        txtID.setEditable(false);
        txtID.setEnabled(false);
        tblPaciente.getColumnModel().getColumn(0).setHeaderRenderer(new DTable(new Color(230,192,233),Color.BLACK));
        tblPaciente.getColumnModel().getColumn(1).setHeaderRenderer(new DTable(new Color(230,192,233),Color.BLACK));
        tblPaciente.getColumnModel().getColumn(2).setHeaderRenderer(new DTable(new Color(230,192,233),Color.BLACK));
        tblPaciente.getColumnModel().getColumn(3).setHeaderRenderer(new DTable(new Color(230,192,233),Color.BLACK));
        tblPaciente.getColumnModel().getColumn(4).setHeaderRenderer(new DTable(new Color(230,192,233),Color.BLACK));
        tblPaciente.getColumnModel().getColumn(5).setHeaderRenderer(new DTable(new Color(230,192,233),Color.BLACK));
        tblPaciente.getColumnModel().getColumn(6).setHeaderRenderer(new DTable(new Color(230,192,233),Color.BLACK));
        th=tblPaciente.getTableHeader();
        Font fuente=new Font("Arial",Font.BOLD,16);  
        th.setFont(fuente);
        /*this.Imagen(this.lblAgregar,"Imagenes\\ag.png");
        this.Imagen(this.lblEliminar,"Imagenes\\eli.png");
        this.Imagen(this.lblBuscar,"Imagenes\\bu.png");
        this.Imagen(this.lblLimpiar,"Imagenes\\li.png");
        this.Imagen(this.lblModificar,"Imagenes\\modi.png");*/
        
    }
     /*private void Imagen(JLabel lbl,String ruta){
        this.imagen=new ImageIcon(ruta);
        this.icono=new ImageIcon(
                this.imagen.getImage().getScaledInstance
                       (lbl.getWidth(),
                        lbl.getHeight(),
                        Image.SCALE_SMOOTH));
        lbl.setIcon(this.icono);
        repaint();
      }*/
    
    
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
    private void comboVacio(JComboBox c ) throws Exception{
        if(c.getSelectedIndex()<1)throw new Exception("Combo Vacio");
    }
    
     private boolean validaCombo(JComboBox c){
          try{
           comboVacio(c);
       }catch(Exception e){
           showMessageDialog(this,e.getMessage()); c.requestFocus();return true;
       }
        return false;
    }
     private void colortabla(){
      tblPaciente.getColumnModel().getColumn(0).setHeaderRenderer(new DTable(new Color(230,192,233),Color.BLACK));
        tblPaciente.getColumnModel().getColumn(1).setHeaderRenderer(new DTable(new Color(230,192,233),Color.BLACK));
        tblPaciente.getColumnModel().getColumn(2).setHeaderRenderer(new DTable(new Color(230,192,233),Color.BLACK));
        tblPaciente.getColumnModel().getColumn(3).setHeaderRenderer(new DTable(new Color(230,192,233),Color.BLACK));
        tblPaciente.getColumnModel().getColumn(4).setHeaderRenderer(new DTable(new Color(230,192,233),Color.BLACK));
        tblPaciente.getColumnModel().getColumn(5).setHeaderRenderer(new DTable(new Color(230,192,233),Color.BLACK));
        tblPaciente.getColumnModel().getColumn(6).setHeaderRenderer(new DTable(new Color(230,192,233),Color.BLACK));
        th=tblPaciente.getTableHeader();
        Font fuente=new Font("Arial",Font.BOLD,16);  
        th.setFont(fuente);
     
     
     }//color tabla
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
                    colortabla();
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
            m.setRowCount(0);
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
            colortabla();
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
        lblDireccion = new javax.swing.JLabel();
        txtID = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblPaciente = new javax.swing.JTable();
        lblNombre = new javax.swing.JLabel();
        txtDireccion = new javax.swing.JTextField();
        cbxBuscar = new javax.swing.JComboBox<>();
        lblEliminar = new javax.swing.JLabel();
        lblApellido = new javax.swing.JLabel();
        txtTelefono = new javax.swing.JTextField();
        txtBuscar = new javax.swing.JTextField();
        txtEdad = new javax.swing.JTextField();
        lblEdad = new javax.swing.JLabel();
        lblGenero = new javax.swing.JLabel();
        cmbGenero = new javax.swing.JComboBox<>();
        lblModificar = new javax.swing.JLabel();
        lblLimpiar = new javax.swing.JLabel();
        lblBuscar = new javax.swing.JLabel();
        MenuPleglable1 = new javax.swing.JPanel();
        lblBack1 = new javax.swing.JLabel();
        lblPaciente1 = new javax.swing.JLabel();
        lblCitas1 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        lblMenu1 = new javax.swing.JLabel();
        jSeparator4 = new javax.swing.JSeparator();
        jSeparator5 = new javax.swing.JSeparator();
        jSeparator6 = new javax.swing.JSeparator();
        jSeparator7 = new javax.swing.JSeparator();
        jImageBox2 = new JImageBox();
        jPanel4 = new javax.swing.JPanel();
        panel1 = new java.awt.Panel();
        jLabel1 = new javax.swing.JLabel();
        lblTelefono = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();

        MenuPleglable.setBackground(new java.awt.Color(69, 204, 209));
        MenuPleglable.setMinimumSize(new java.awt.Dimension(0, 577));
        MenuPleglable.setLayout(null);

        lblBack.setBackground(new java.awt.Color(0, 0, 0));
        lblBack.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lblBack.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblBack.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/flecha-izquierda90.png"))); // NOI18N
        lblBack.setText("Regresar           ");
        lblBack.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 50));
        lblBack.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
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
        lblPaciente.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
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
        lblCitas.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
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
        lblExpediente.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
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
        MenuPleglable.add(jLabel9);
        jLabel9.setBounds(0, 60, 270, 50);

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
        lblPass.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/seguro.png"))); // NOI18N
        lblPass.setText("Gestion Contraseñas ");
        lblPass.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 50));
        lblPass.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
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

        txtNombre.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txtNombre.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtNombreKeyTyped(evt);
            }
        });
        jPanel1.add(txtNombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 150, 240, -1));

        txtApellido.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txtApellido.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtApellidoKeyTyped(evt);
            }
        });
        jPanel1.add(txtApellido, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 200, 240, -1));

        lblAgregar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/icons8-añadir-usuario-masculino-90.png"))); // NOI18N
        lblAgregar.setToolTipText("Agregar");
        lblAgregar.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        lblAgregar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblAgregarMouseClicked(evt);
            }
        });
        jPanel1.add(lblAgregar, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 600, 100, 90));

        jLabel12.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(153, 153, 153));
        jLabel12.setText("ID:");
        jPanel1.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 100, -1, -1));

        lblDireccion.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        lblDireccion.setText("Dirección:");
        jPanel1.add(lblDireccion, new org.netbeans.lib.awtextra.AbsoluteConstraints(760, 250, -1, -1));

        txtID.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jPanel1.add(txtID, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 100, 120, -1));

        tblPaciente.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        tblPaciente.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Nombre", "Apellidos", "Edad", "Genéro", "Teléfono", "Dirección"
            }
        ));
        tblPaciente.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        tblPaciente.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblPacienteMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblPaciente);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 370, 720, 230));

        lblNombre.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        lblNombre.setText("Nombre:");
        jPanel1.add(lblNombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 150, -1, -1));

        txtDireccion.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jPanel1.add(txtDireccion, new org.netbeans.lib.awtextra.AbsoluteConstraints(860, 250, 240, -1));

        cbxBuscar.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        cbxBuscar.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Seleccionar", "Nombre", "Apellido" }));
        cbxBuscar.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jPanel1.add(cbxBuscar, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 310, 230, -1));

        lblEliminar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/icons8-eliminar-90.png"))); // NOI18N
        lblEliminar.setToolTipText("Eliminar");
        lblEliminar.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        lblEliminar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblEliminarMouseClicked(evt);
            }
        });
        jPanel1.add(lblEliminar, new org.netbeans.lib.awtextra.AbsoluteConstraints(670, 610, 90, 80));

        lblApellido.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        lblApellido.setText("Apellido:");
        jPanel1.add(lblApellido, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 200, -1, -1));

        txtTelefono.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txtTelefono.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtTelefonoKeyTyped(evt);
            }
        });
        jPanel1.add(txtTelefono, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 250, 240, -1));

        txtBuscar.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
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
        jPanel1.add(txtBuscar, new org.netbeans.lib.awtextra.AbsoluteConstraints(670, 310, 350, 30));

        txtEdad.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txtEdad.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtEdadKeyTyped(evt);
            }
        });
        jPanel1.add(txtEdad, new org.netbeans.lib.awtextra.AbsoluteConstraints(860, 200, 240, -1));

        lblEdad.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        lblEdad.setText("Edad:");
        jPanel1.add(lblEdad, new org.netbeans.lib.awtextra.AbsoluteConstraints(800, 200, 60, -1));

        lblGenero.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        lblGenero.setText("Género:");
        jPanel1.add(lblGenero, new org.netbeans.lib.awtextra.AbsoluteConstraints(780, 140, -1, -1));

        cmbGenero.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        cmbGenero.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Seleccionar", "Masculino", "Femenino" }));
        cmbGenero.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jPanel1.add(cmbGenero, new org.netbeans.lib.awtextra.AbsoluteConstraints(860, 140, 240, 30));

        lblModificar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/editar.png"))); // NOI18N
        lblModificar.setToolTipText("Modificar");
        lblModificar.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        lblModificar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblModificarMouseClicked(evt);
            }
        });
        jPanel1.add(lblModificar, new org.netbeans.lib.awtextra.AbsoluteConstraints(800, 610, 110, 80));

        lblLimpiar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/icons8-clear-90.png"))); // NOI18N
        lblLimpiar.setToolTipText("Limpiar");
        lblLimpiar.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        lblLimpiar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblLimpiarMouseClicked(evt);
            }
        });
        jPanel1.add(lblLimpiar, new org.netbeans.lib.awtextra.AbsoluteConstraints(1010, 610, 90, 80));

        lblBuscar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/lupa.png"))); // NOI18N
        lblBuscar.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        lblBuscar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblBuscarMouseClicked(evt);
            }
        });
        jPanel1.add(lblBuscar, new org.netbeans.lib.awtextra.AbsoluteConstraints(1040, 300, 50, 50));

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
        lblBack1.setBounds(0, 660, 310, 70);

        lblPaciente1.setBackground(new java.awt.Color(0, 0, 0));
        lblPaciente1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lblPaciente1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblPaciente1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/anadir.png"))); // NOI18N
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
        lblPaciente1.setBounds(20, 180, 291, 66);

        lblCitas1.setBackground(new java.awt.Color(0, 0, 0));
        lblCitas1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lblCitas1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblCitas1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/calendario90.png"))); // NOI18N
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
        lblCitas1.setBounds(0, 310, 330, 66);

        jLabel7.setBackground(new java.awt.Color(0, 0, 0));
        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel7.setText("      OPCIONES   ");
        jLabel7.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 50));
        jLabel7.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jLabel7.setHorizontalTextPosition(javax.swing.SwingConstants.LEADING);
        jLabel7.setIconTextGap(10);
        jLabel7.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel7MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jLabel7MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jLabel7MouseExited(evt);
            }
        });
        MenuPleglable1.add(jLabel7);
        jLabel7.setBounds(0, 10, 270, 50);

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
        jImageBox2.setBounds(60, 60, 140, 0);

        jPanel1.add(MenuPleglable1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, -1, 269, 750));

        jPanel4.setBackground(new java.awt.Color(0, 108, 183));

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 920, Short.MAX_VALUE)
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 40, Short.MAX_VALUE)
        );

        jPanel1.add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 710, 920, 40));

        panel1.setBackground(new java.awt.Color(0, 108, 183));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Registro de Pacientes");

        javax.swing.GroupLayout panel1Layout = new javax.swing.GroupLayout(panel1);
        panel1.setLayout(panel1Layout);
        panel1Layout.setHorizontalGroup(
            panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panel1Layout.createSequentialGroup()
                .addContainerGap(312, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(340, 340, 340))
        );
        panel1Layout.setVerticalGroup(
            panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel1Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(jLabel1)
                .addContainerGap(20, Short.MAX_VALUE))
        );

        jPanel1.add(panel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 0, 910, 70));

        lblTelefono.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        lblTelefono.setText("Teléfono:");
        jPanel1.add(lblTelefono, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 250, -1, -1));

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Buscar"));
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 290, 720, 70));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 1174, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 743, Short.MAX_VALUE)
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

    void Buscar(String valor) {
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
    
    tblPaciente.setModel(modelo);
    String sql = "";
    if (valor.equals("")) {
        sql = "SELECT * FROM pacientes";
    } else {
        sql = "SELECT * FROM pacientes WHERE id LIKE '%" 
                + valor + "%' OR nombre LIKE '%" 
                + valor + "%' OR apellido LIKE '%" 
                + valor + "%' OR telefono LIKE '%" + valor + "%'";
    }
    String[] Datos = new String[7];
    try {
        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery(sql);
        if (!rs.next()) {
            showMessageDialog(null, "Paciente no encontrado");
        } else {
            do {
                Datos[0] = rs.getString(1);
                Datos[1] = rs.getString(2);
                Datos[2] = rs.getString(3);
                Datos[3] = rs.getString(4);
                Datos[4] = rs.getString(5);
                Datos[5] = rs.getString(6);
                Datos[6] = rs.getString(7);                
                modelo.addRow(Datos);
            } while (rs.next());
        }
        
        tblPaciente.setModel(modelo);
        colortabla();
        
    } catch(SQLException ex) {
        showMessageDialog(null, "Error en la conexión a la base de datos");
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
       if(validaCampo(txtNombre)){
            lblNombre.setForeground(Color.RED);
            return;
        }else lblNombre.setForeground(Color.BLACK);

        if(validaCampo(txtApellido)){
            lblApellido.setForeground(Color.RED);
            return;
        }
        else lblApellido.setForeground(Color.BLACK);
        
        if(validaCampo(txtTelefono)){
            lblTelefono.setForeground(Color.RED);
            return;
        }
        else lblTelefono.setForeground(Color.BLACK);
        
        if(validaCombo(cmbGenero)){
            lblGenero.setForeground(Color.RED);
            return;
        }else lblGenero.setForeground(Color.BLACK);
        
        if(validaCampo(txtEdad)){
            lblEdad.setForeground(Color.RED);
            return;
        }
        else lblDireccion.setForeground(Color.BLACK);
        
        if(validaCampo(txtDireccion)){
            lblDireccion.setForeground(Color.RED);
            return;
        }
        else lblDireccion.setForeground(Color.BLACK);
        
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
                colortabla();
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
                        colortabla();
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
          if(validaCampo(txtNombre)){
            lblNombre.setForeground(Color.RED);
            return;
        }else lblNombre.setForeground(Color.BLACK);

        if(validaCampo(txtApellido)){
            lblApellido.setForeground(Color.RED);
            return;
        }
        else lblApellido.setForeground(Color.BLACK);
        
        if(validaCampo(txtTelefono)){
            lblTelefono.setForeground(Color.RED);
            return;
        }
        else lblTelefono.setForeground(Color.BLACK);
        
        if(validaCombo(cmbGenero)){
            lblGenero.setForeground(Color.RED);
            return;
        }else lblGenero.setForeground(Color.BLACK);
        
        if(validaCampo(txtEdad)){
            lblEdad.setForeground(Color.RED);
            return;
        }
        else lblDireccion.setForeground(Color.BLACK);
        
        if(validaCampo(txtDireccion)){
            lblDireccion.setForeground(Color.RED);
            return;
        }
        else lblDireccion.setForeground(Color.BLACK);
        agregar();
    }//GEN-LAST:event_lblAgregarMouseClicked

    private void txtBuscarKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBuscarKeyReleased
       Buscar(txtBuscar.getText());
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

    private void lblCitas1MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblCitas1MouseExited
        lblCitas1.setForeground(Color.BLACK);
    }//GEN-LAST:event_lblCitas1MouseExited

    private void lblCitas1MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblCitas1MouseEntered
        lblCitas1.setForeground(Color.GRAY);
    }//GEN-LAST:event_lblCitas1MouseEntered

    private void lblPaciente1MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblPaciente1MouseExited
        lblPaciente1.setForeground(Color.GRAY);
    }//GEN-LAST:event_lblPaciente1MouseExited

    private void lblPaciente1MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblPaciente1MouseEntered
        lblPaciente1.setForeground(Color.BLACK);
    }//GEN-LAST:event_lblPaciente1MouseEntered

    private void lblPaciente1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblPaciente1MouseClicked
       
    }//GEN-LAST:event_lblPaciente1MouseClicked

    private void lblBack1MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblBack1MouseExited
        lblBack1.setForeground(Color.BLACK);
    }//GEN-LAST:event_lblBack1MouseExited

    private void lblBack1MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblBack1MouseEntered
        lblBack1.setForeground(Color.GRAY);
    }//GEN-LAST:event_lblBack1MouseEntered

    private void lblBack1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblBack1MouseClicked

        MenuSecretaria2 login = new MenuSecretaria2();
        login.setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_lblBack1MouseClicked

    private void lblCitas1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblCitas1MouseClicked
       VentanaCitasSecre exp=new VentanaCitasSecre();
        exp.setVisible(true);
        exp.setLocationRelativeTo(null);
        this.dispose();
    }//GEN-LAST:event_lblCitas1MouseClicked

    private void txtNombreKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNombreKeyTyped
       char c=evt.getKeyChar();
        String valida = Character.toString(c);
        if (!valida.matches("[a-zA-Z_]+") && !valida.matches("\b") && !valida.matches(" ")) {
            getToolkit().beep();
            evt.consume();
            JOptionPane.showMessageDialog(rootPane, "Ingrese solo letras");
        }
    }//GEN-LAST:event_txtNombreKeyTyped

    private void txtEdadKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtEdadKeyTyped
        char car = evt.getKeyChar();
    if(((car < '0') ||(car > '9')) &&(car != '\b')){
        evt.consume();
        JOptionPane.showMessageDialog(this, "Solo numeros");
    }
    if(txtEdad.getText().length()>=3){
        evt.consume();
        Toolkit.getDefaultToolkit().beep();
        JOptionPane.showMessageDialog(this, "Edad No Valida");
    }
    }//GEN-LAST:event_txtEdadKeyTyped

    private void txtTelefonoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTelefonoKeyTyped
        char car = evt.getKeyChar();
    if(((car < '0') ||(car > '9')) &&(car != '\b')){
        evt.consume();
        JOptionPane.showMessageDialog(this, "Solo numeros");
    }
    if(txtTelefono.getText().length()>=10){
        evt.consume();
        Toolkit.getDefaultToolkit().beep();
        JOptionPane.showMessageDialog(this, "Solo Ingresa 10 Digitos");
    }
    }//GEN-LAST:event_txtTelefonoKeyTyped

    private void txtApellidoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtApellidoKeyTyped
       char c=evt.getKeyChar();
        String valida = Character.toString(c);
        if (!valida.matches("[a-zA-Z_]+") && !valida.matches("\b") && !valida.matches(" ")) {
            getToolkit().beep();
            evt.consume();
            JOptionPane.showMessageDialog(rootPane, "Ingrese solo letras");
        }
    }//GEN-LAST:event_txtApellidoKeyTyped

    private void jLabel7MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel7MouseClicked
        if(MenuPleglable.getX()==0)
        desplace.desplazarIzquierda(this.MenuPleglable,MenuPleglable.getX(),-180, 10,10 );
        else if(MenuPleglable.getX()== -180)
        desplace.desplazarDerecha(this.MenuPleglable,MenuPleglable.getX(),0, 10,10);
    }//GEN-LAST:event_jLabel7MouseClicked

    
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
    private JImageBox jImageBox2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JSeparator jSeparator5;
    private javax.swing.JSeparator jSeparator6;
    private javax.swing.JSeparator jSeparator7;
    private javax.swing.JLabel lblAgregar;
    private javax.swing.JLabel lblApellido;
    private javax.swing.JLabel lblBack;
    private javax.swing.JLabel lblBack1;
    private javax.swing.JLabel lblBuscar;
    private javax.swing.JLabel lblCitas;
    private javax.swing.JLabel lblCitas1;
    private javax.swing.JLabel lblDireccion;
    private javax.swing.JLabel lblEdad;
    private javax.swing.JLabel lblEliminar;
    private javax.swing.JLabel lblExpediente;
    private javax.swing.JLabel lblGenero;
    private javax.swing.JLabel lblLimpiar;
    private javax.swing.JLabel lblMenu;
    private javax.swing.JLabel lblMenu1;
    private javax.swing.JLabel lblModificar;
    private javax.swing.JLabel lblNombre;
    private javax.swing.JLabel lblPaciente;
    private javax.swing.JLabel lblPaciente1;
    private javax.swing.JLabel lblPass;
    private javax.swing.JLabel lblTelefono;
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
