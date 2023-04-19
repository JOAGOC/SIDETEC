import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import static javax.swing.JOptionPane.showMessageDialog;

public class Conexión {
    Connection con;
    public Connection getConnection() {
        try {
            String db = "jdbc:mysql://127.0.0.1:3306/consultorio_dental";
            con = DriverManager.getConnection(db, "root", "root");
            System.out.println("CONECTADO");
            return con;
        } catch (SQLException e) {
            showMessageDialog(null, "Error en la conexion. " + e.getMessage());
        }
        return null;
    }
}