package Conexión;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.TimeZone;

import static javax.swing.JOptionPane.showMessageDialog;

public class Conexión{
    private static Connection con;
    
    public static Connection getConnection() {
        if (con!=null)
            return con;
        try {
            String db = "jdbc:mysql://localhost:3306/consultorio_dental?serverTimezone=America/Los_Angeles";
            TimeZone timeZone = TimeZone.getTimeZone("UTC-07:00");
            TimeZone.setDefault(timeZone);
            con = DriverManager.getConnection(db, "root", "admi1");
            con.prepareStatement("SET GLOBAL time_zone = '-07:00';").executeQuery();
            System.out.println("CONECTADO");
            return con;
        } catch (SQLException e) {
            showMessageDialog(null, "Error en la conexion. " + e.getMessage());
        }
        return null;
    }
}
