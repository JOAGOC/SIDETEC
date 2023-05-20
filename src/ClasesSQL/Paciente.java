package ClasesSQL;

import static Conexión.Conexión.getConnection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Paciente {
    private int id;
    private String nombre;
    private String apellido;
    private int edad;
    private String genero;
    private String telefono;
    private String direccion;

    public Paciente() {
    }

    public Paciente(int id, String nombre, String apellido, int edad, String genero, String telefono,
            String direccion) {
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

    public static Paciente consultar(int id) {
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

    public static Paciente consultar(String telefono) {
        try {
            String a;
            ResultSet row = getConnection().prepareStatement(a = String.format("Select * from pacientes where telefono like '%s';",
                                    telefono))
                    .executeQuery();
            System.out.println(a);
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
}
