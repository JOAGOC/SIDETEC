import static Conexión.Conexión.getConnection;
import java.sql.ResultSet;
import javax.swing.table.DefaultTableModel;
import com.mysql.cj.jdbc.result.ResultSetMetaData;
import com.mysql.cj.protocol.Resultset;

public class Usuario {
    private int idUsuario;
    private String usuario;
    private String contraseña;
    private String rol;

    public Usuario(){}
    
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

    public String insertar(){
        String query = String.format("Insert into Usuarios(usuario,contraseña,rol) values ('%s','%s','%s');",usuario,contraseña,rol);
        try {
            getConnection().prepareStatement(query).executeUpdate();
        } catch (Exception e) {
            if (e instanceof java.sql.SQLIntegrityConstraintViolationException)
                return "El usuario que intentas registrar ya existe, prueba con otro nombre de usuario";
            return String.format("Error al ejecutar SQL: %s",e.getMessage());
        }
        return "Se registró correctamente";
    }
    
    public String actualizar() {
        String query = String.format("Update Usuarios set usuario='%s',contraseña='%s',rol='%s' where id_usuario=%d",usuario,contraseña,rol,idUsuario);
        try {
            getConnection().prepareStatement(query).executeUpdate();
        } catch (Exception e) {
            if (e instanceof java.sql.SQLIntegrityConstraintViolationException)
                return "El nombre de usuario ya existe, prueba con otro nombre de usuario";
            return String.format("Error al ejecutar SQL: %s",e.getMessage());
        }
        return "Se actualizó correctamente";
    }

    public String eliminar() {
        String query = String.format("Delete from Usuarios where id_usuario=%d",idUsuario);
        try {
            getConnection().prepareStatement(query).executeUpdate();
        } catch (Exception e) {
            return String.format("Error al ejecutar SQL: %s",e.getMessage());
        }
        return "Se eliminó correctamente";
    }

    public static String eliminar(int idUsuario) {
        String query = String.format("Delete from Usuarios where id_usuario=%d",idUsuario);
        try {
            getConnection().prepareStatement(query).executeUpdate();
        } catch (Exception e) {
            return String.format("Error al ejecutar SQL: %s",e.getMessage());
        }
        return "Se eliminó correctamente";
    }

    public static DefaultTableModel Consultar(){
        String query = "Select*from Usuarios where rol like 'Secretaria'";
        DefaultTableModel tabla = null;
        try {
            ResultSet data = getConnection().prepareStatement(query).executeQuery();
            ResultSetMetaData columnas = (ResultSetMetaData)data.getMetaData();
            java.util.function.Function<ResultSetMetaData,String[]> devlolverColumnas = (rs) -> {
                String[] nombreCol = null;
                try {
                    int colcount = rs.getColumnCount();
                    nombreCol = new String[colcount];
                    for(int i = 0;i<colcount;){
                        nombreCol[i++] = rs.getColumnName(i);
                    };
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
                return nombreCol;
            };
            tabla = new DefaultTableModel(devlolverColumnas.apply(columnas),0);
            int index = 0;
            Object[] registro = new Object[4];
            while(data.next()){
                registro[index++] = data.getInt(index); 
                registro[index++] = data.getString(index); 
                registro[index++] = data.getString(index); 
                registro[index++] = data.getString(index); 
                tabla.addRow(registro);
                index = 0;
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return tabla;
    }
}