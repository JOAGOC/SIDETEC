package ClasesSQL;

import static Conexión.Conexión.getConnection;
import javax.swing.table.DefaultTableModel;
import Interfaces.CRUD;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * Clase contenedora de los métodos que se comunican con la tabla de usuarios en la base de datos.
 * 
 */
public class Usuario implements CRUD {
    private int idUsuario;
    private String usuario;
    private String contraseña;
    private String rol;
    public boolean cambiarContra;

    /**
     * Constructor vacío de la clase Usuario.
     * Inicializa las variables de instancia usuario, contraseña y rol con una cadena vacía para evitar que sea nulas.
     */
    public Usuario() {
        usuario = contraseña = rol = "";
    }
    
    /**
     * Constructor de la clase Usuario que recibe los valores de las variables de instancia.
     *
     * @param idUsuario el ID del usuario
     * @param usuario el nombre de usuario
     * @param contraseña la contraseña del usuario
     * @param rol el rol del usuario
     * @param cambiarContra indica si el usuario debe cambiar su contraseña
     */
    public Usuario(int idUsuario, String usuario, String contraseña, String rol, boolean cambiarContra) {
        this.idUsuario = idUsuario;
        this.usuario = usuario;
        this.contraseña = contraseña;
        this.rol = rol;
        this.cambiarContra = cambiarContra;
    }

    /**
     * Constructor de la clase Usuario que recibe los valores de las variables de instancia.
     * No incluye la variable cambiarContra.
     *
     * @param idUsuario el ID del usuario
     * @param usuario el nombre de usuario
     * @param contraseña la contraseña del usuario
     * @param rol el rol del usuario
     */
    public Usuario(int idUsuario, String usuario, String contraseña, String rol) {
        this.idUsuario = idUsuario;
        this.usuario = usuario;
        this.contraseña = contraseña;
        this.rol = rol;
    }

    /**
     * Constructor de la clase Usuario que recibe los valores de las variables de instancia.
     * No incluye la variable idUsuario.
     *
     * @param usuario el nombre de usuario
     * @param contraseña la contraseña del usuario
     * @param rol el rol del usuario
     */
    public Usuario(String usuario, String contraseña, String rol) {
        this.usuario = usuario;
        this.contraseña = contraseña;
        this.rol = rol;
    }

    /**
     * Obtiene el ID del usuario.
     *
     * @return el ID del usuario
     */
    public int getIdUsuario() {
        return idUsuario;
    }

    /**
     * Obtiene el nombre de usuario.
     *
     * @return el nombre de usuario
     */
    public String getUsuario() {
        return usuario;
    }

    /**
     * Establece el nombre de usuario.
     *
     * @param usuario el nombre de usuario a establecer
     */
    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    /**
     * Obtiene la contraseña del usuario.
     *
     * @return la contraseña del usuario
     */
    public String getContraseña() {
        return contraseña;
    }

    /**
     * Establece la contraseña del usuario.
     *
     * @param contraseña la contraseña a establecer
     */
    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }

    /**
     * Obtiene el rol del usuario.
     *
     * @return el rol del usuario
     */
    public String getRol() {
        return rol;
    }

    /**
     * Establece el rol del usuario.
     *
     * @param rol el rol a establecer
     */
    public void setRol(String rol) {
        this.rol = rol;
    }

    /**
     * Inserta un nuevo usuario en la base de datos.
     * Utiliza los valores de las variables de instancia usuario, contraseña y rol.
     * No utiliza los atributos id y cambiarContra ya que, no tiene un id asociado
     * aún y, siempre que se registre un usuario requiere que se haga la rutina del
     * caso de uso cambiar contraseña.
     *
     * @return un mensaje indicando el resultado de la operación
     */
    public String insertar() {
        String query = String.format("Insert into Usuarios(usuario,contraseña,rol,cambiarContra) values ('%s','%s','Secretaria',1);", usuario,
                contraseña);
        try {
            getConnection().prepareStatement(query).executeUpdate();
        } catch (Exception e) {
            if (e instanceof java.sql.SQLIntegrityConstraintViolationException)
                return "El usuario que intentas registrar ya existe, prueba con otro nombre de usuario";
            return String.format("Error al ejecutar SQL: %s", e.getMessage());
        }
        return "Se registró correctamente";
    }
    
    /**
     * Actualiza el valor de la variable cambiarContra y contraseña en la base de datos.
     * Utilizado en el caso de uso cambiar contraseña.
     * Utiliza el valor de la variable idUsuario y contraseña.
     *
     * @return un mensaje indicando el resultado de la operación
     */
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

    /**
     * Actualiza los valores de las variables de instancia en la base de datos.
     * Utiliza los valores de las variables idUsuario, usuario, contraseña y rol.
     *
     * @return un mensaje indicando el resultado de la operación
     */
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

    /**
     * Elimina el usuario de la base de datos.
     * Utiliza el valor de la variable idUsuario.
     *
     * @return un mensaje indicando el resultado de la operación
     * @see #eliminar(int)
     */
    public String eliminar() {
        return eliminar(idUsuario);
    }

    /**
     * Elimina un usuario de la base de datos dado su ID.
     *
     * @param idUsuario el ID del usuario a eliminar
     * @return un mensaje indicando el resultado de la operación
     */
    public static String eliminar(int idUsuario) {
        String query = String.format("Delete from Usuarios where id_usuario=%d", idUsuario);
        try {
            getConnection().prepareStatement(query).executeUpdate();
        } catch (Exception e) {
            return String.format("Error al ejecutar SQL: %s", e.getMessage());
        }
        return "Se eliminó correctamente";
    }

    /**
     * Realiza una consulta a la base de datos y devuelve un DefaultTableModel con los resultados.
     * Consulta todos los usuarios de la tabla usuarios.
     *
     * @return un DefaultTableModel con los resultados de la consulta
     * @see CRUD#consultar(String)
     */
    public static DefaultTableModel Consultar() {
        return CRUD.consultar("Select * from usuarios");   
    }
    
    /**
     * Busca un usuario en la base de datos dado su nombre de usuario y contraseña.
     *
     * @param usuario el nombre de usuario
     * @param contraseña la contraseña
     * @return el objeto Usuario correspondiente si se encuentra, o null si no se encuentra
     */
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
