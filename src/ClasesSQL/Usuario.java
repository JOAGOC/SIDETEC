package ClasesSQL;

import static Conexión.Conexión.getConnection;
import javax.swing.table.DefaultTableModel;
import static Interfaces.CRUD.cargarAutocompletar;
import Componentes.AutoCompleteTextField;
import Interfaces.CRUD;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Set;

public class Usuario implements CRUD {
    private int idUsuario;
    private String usuario;
    private String contraseña;
    private String rol;
    public boolean cambiarContra;

    public Usuario() {
    }
    
    public static void main(String[] args) {
        DefaultTableModel m;
        System.out.println((m = CRUD.consultar("Select * from Usuarios where usuario = 'Dentista' and Binary contraseña = 'dentis123';")).getDataVector().toString());
        boolean xd;
        System.out.println(xd = (boolean) m.getValueAt(0, 4));
        if (xd == true)
            System.out.println("Si es verdadero xd");
    }
    
    public Usuario(int idUsuario, String usuario, String contraseña, String rol, boolean cambiarContra) {
        this.idUsuario = idUsuario;
        this.usuario = usuario;
        this.contraseña = contraseña;
        this.rol = rol;
        this.cambiarContra = cambiarContra;
    }

    public Usuario(int idUsuario, String usuario, String contraseña, String rol) {
        this.idUsuario = idUsuario;
        this.usuario = usuario;
        this.contraseña = contraseña;
        this.rol = rol;
    }

    public Usuario(String usuario, String contraseña, String rol) {
        this.usuario = usuario;
        this.contraseña = contraseña;
        this.rol = rol;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getContraseña() {
        return contraseña;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    public String insertar() {
        String query = String.format("Insert into Usuarios(usuario,contraseña,rol,cambiarContra) values ('%s','%s','%s',1);", usuario,
                contraseña, rol);
        try {
            getConnection().prepareStatement(query).executeUpdate();
        } catch (Exception e) {
            if (e instanceof java.sql.SQLIntegrityConstraintViolationException)
                return "El usuario que intentas registrar ya existe, prueba con otro nombre de usuario";
            return String.format("Error al ejecutar SQL: %s", e.getMessage());
        }
        return "Se registró correctamente";
    }
    
    public String actualizar_cambiarContra() {
        String query = String.format("Update Usuarios set contraseña='%s', cambiarContra = 0 where id_usuario=%d",
                contraseña,idUsuario);
        try {
            getConnection().prepareStatement(query).executeUpdate();
        } catch (Exception e) {
            return String.format("Error al ejecutar SQL: %s", e.getMessage());
        }
        return "Se actualizó correctamente";
    }

    public String actualizar() {
        String query = String.format("Update Usuarios set usuario='%s',contraseña='%s',rol='%s',cambiarContra = 1 where id_usuario=%d",
                usuario, contraseña, rol, idUsuario);
        try {
            getConnection().prepareStatement(query).executeUpdate();
        } catch (Exception e) {
            if (e instanceof java.sql.SQLIntegrityConstraintViolationException)
                return "El nombre de usuario ya existe, prueba con otro nombre de usuario";
            return String.format("Error al ejecutar SQL: %s", e.getMessage());
        }
        return "Se actualizó correctamente";
    }

    public String eliminar() {
        String query = String.format("Delete from Usuarios where id_usuario=%d", idUsuario);
        try {
            getConnection().prepareStatement(query).executeUpdate();
        } catch (Exception e) {
            return String.format("Error al ejecutar SQL: %s", e.getMessage());
        }
        return "Se eliminó correctamente";
    }

    public static String eliminar(int idUsuario) {
        String query = String.format("Delete from Usuarios where id_usuario=%d", idUsuario);
        try {
            getConnection().prepareStatement(query).executeUpdate();
        } catch (Exception e) {
            return String.format("Error al ejecutar SQL: %s", e.getMessage());
        }
        return "Se eliminó correctamente";
    }

    public static DefaultTableModel Consultar() {
        return CRUD.consultar("Select * from usuarios");   
    }
    
    public static Usuario buscar(String usuario, String contraseña){
        String QUERY = "Select * from Usuarios where usuario = ? and Binary contraseña = ?";
        try {
            PreparedStatement stmn = getConnection().prepareStatement(QUERY);
            stmn.setObject(1, usuario);
            stmn.setObject(2, contraseña);
            ResultSet data = stmn.executeQuery();
            
            while(data.next()){
                return new Usuario(data.getInt(1), data.getString(2), data.getString(3),data.getString(4), data.getBoolean(5));
            }
        } catch (Exception e) {
            e.printStackTrace();
            
        }
        return null;
    }
}