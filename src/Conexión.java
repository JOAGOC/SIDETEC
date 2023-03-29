
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Tzihual
 */
public class Conexión {
    Connection con;
    public Connection getConnection() {
        try {
            String db = "jdbc:mysql://127.0.0.1:3306/consultorio_dental";
            con = DriverManager.getConnection(db, "root", "root");
            System.out.println("CONECTADO");
            return con;
        } catch (SQLException e) {
            
            JOptionPane.showMessageDialog(null, "Error en la conexion. " + e.getMessage());
        }
        
        return null;
    }
    
    
     public static void main(String[] args) {
        Conexión c=new Conexión();
        c.getConnection();
    }
}
