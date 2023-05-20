
import static Conexión.Conexión.getConnection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.sql.*;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import Conexión.Conexión;


public class Paciente {
    private int id;
    private String nombre;
    private String apellido;
    private int edad;
    private String genero;
    private String telefono;
    private String direccion;
    
    Connection cn;
    public Paciente(){}

    public Paciente(int id, String nombre, String apellido, int edad, String genero, String telefono, String direccion) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.edad = edad;
        this.genero = genero;
        this.telefono = telefono;
        this.direccion = direccion;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }
    
    public static Paciente consultar(int id){
        try {
            ResultSet row = getConnection()
                    .prepareStatement(
                            String.format(
                                    "Select * from pacientes where id like %d;",
                                    id))
                    .executeQuery();
            while (row.next()) {
                int index = 1;
                return new Paciente(row.getInt(index++),
                        row.getString(index++),
                        row.getString(index++),
                        row.getInt(index++),
                        row.getString(index++),
                        row.getString(index++),
                        row.getString(index++));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    
    public static Paciente consultar(String telefono){
        try {
            ResultSet row = getConnection()
                    .prepareStatement(
                            String.format(
                                    "Select * from pacientes where telefono like %s;",
                                    telefono))
                    .executeQuery();
            while (row.next()) {
                int index = 1;
                return new Paciente(row.getInt(index++),
                        row.getString(index++),
                        row.getString(index++),
                        row.getInt(index++),
                        row.getString(index++),
                        row.getString(index++),
                        row.getString(index++));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
  /*  
   public DefaultComboBoxModel ObtenerLista(String cadena){
    DefaultComboBoxModel modelo = new DefaultComboBoxModel();
    String[] palabras = cadena.split(" ");
    String sql = "SELECT CONCAT(nombre,' ',apellido) FROM pacientes WHERE ";

    Conexión con=new Conexión();
    cn=con.getConnection();
    
    for (int i = 0; i < palabras.length; i++) {
        if (i > 0) {
            sql += " OR ";
        }
        sql += "nombre LIKE '%"+palabras[i]+"%' OR apellido LIKE '%"+palabras[i]+"%'";
    }

 
    try {
        Statement st = cn.createStatement();
        ResultSet rs = st.executeQuery(sql);

        modelo.removeAllElements();
        while (rs.next()) {
            modelo.addElement(rs.getString(1)); 
        }
    } catch (SQLException ex) {
        JOptionPane.showMessageDialog(null, "Error al llamar la BD");
        System.out.println("Conexion incorrecta: "+ ex);
    }
    return modelo;
}
*/
public DefaultComboBoxModel ObtenerLista(String cadena){
    DefaultComboBoxModel modelo = new DefaultComboBoxModel();
    if (cadena.equals("")) {
        // Si la cadena está vacía, no hagas nada.
        return modelo;
    }

    String[] palabras = cadena.split(" ");  
    String sql = "SELECT CONCAT(nombre,' ',apellido) FROM pacientes WHERE ";

    Conexión con = new Conexión();
    cn = con.getConnection();
    
    for (int i = 0; i < palabras.length; i++) {
        if (i > 0) {
            sql += " AND ";
        }
        sql += "LOWER(CONCAT(nombre,' ',apellido)) LIKE LOWER('%"+palabras[i]+"%')";  
    }

    sql += " ORDER BY CASE WHEN LOWER(CONCAT(nombre,' ',apellido)) LIKE LOWER('"+cadena+"%') THEN 1 ELSE 2 END, CONCAT(nombre,' ',apellido)";

    try {
        Statement st = cn.createStatement();
        ResultSet rs = st.executeQuery(sql);

        modelo.removeAllElements();
        boolean pacienteEncontrado = false;
        while (rs.next()) {
            modelo.addElement(rs.getString(1)); 
            pacienteEncontrado = true;
        }

        if (!pacienteEncontrado) {
            JOptionPane.showMessageDialog(null, "El paciente que busca no está registrado.");
        }
    } catch (SQLException ex) {
        JOptionPane.showMessageDialog(null, "Error al llamar la BD");
        System.out.println("Conexion incorrecta: "+ ex);
    }
    return modelo;
}


    
}
