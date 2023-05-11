package Conexión;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import static javax.swing.JOptionPane.showMessageDialog;

public class Conexión{
    private static Connection con;
    
    public static Connection getConnection() {
        if (con!=null)
            return con;
        try {
            String db = "jdbc:mysql://localhost:3306/consultorio_dental";
            con = DriverManager.getConnection(db, "root", "root");
            System.out.println("CONECTADO");
            con.prepareStatement("Set global time_zone = '-7:00'");
            return con;
        } catch (SQLException e) {
            showMessageDialog(null, "Error en la conexion. " + e.getMessage());
        }
        return null;
    }
}
