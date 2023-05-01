package Conexión;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import static javax.swing.JOptionPane.showMessageDialog;

public class Conexión {
    private static Connection con;
    
    public static Connection getConnection() {
        if (con!=null)
            return con;
        try {
            String db = "jdbc:mysql://sidetec.c4xkc1d1pseq.us-east-1.rds.amazonaws.com:3306/consultorio_dental";
            con = DriverManager.getConnection(db, "admin", "admin12345");
            System.out.println("CONECTADO");
            return con;
        } catch (SQLException e) {
            showMessageDialog(null, "Error en la conexion. " + e.getMessage());
        }
        return null;
    }
}