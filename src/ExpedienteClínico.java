
import static Conexión.Conexión.getConnection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.mysql.cj.protocol.Resultset;

public class ExpedienteClínico {

    private int folio;
    private Date fecha;
    private String motivo, enfermedad, observaciones, tratamiento, estado;
    private int id_paciente;

    public ExpedienteClínico(int folio, Date fecha, String motivo, String enfermedad, String observaciones,
            String tratamiento, String estado, int id_paciente) {
        this.folio = folio;
        this.fecha = fecha;
        this.motivo = motivo;
        this.enfermedad = enfermedad;
        this.observaciones = observaciones;
        this.tratamiento = tratamiento;
        this.estado = estado;
        this.id_paciente = id_paciente;
    }

    public ExpedienteClínico(Date fecha, String motivo, String enfermedad, String observaciones, String tratamiento,
            String estado, int id_paciente) {
        this.fecha = fecha;
        this.motivo = motivo;
        this.enfermedad = enfermedad;
        this.observaciones = observaciones;
        this.tratamiento = tratamiento;
        this.estado = estado;
        this.id_paciente = id_paciente;
    }

    public ExpedienteClínico() {

    }

    public String insertar() {
        String query = String.format("INSERT INTO expediente_clinico VALUES ('%s','%s','%s','%s','%s','%s',%d);",
                fecha, motivo, enfermedad, observaciones, tratamiento, estado, id_paciente);
        try {
            getConnection().prepareStatement(query).executeUpdate();
        } catch (Exception e) {
            return String.format("Error al ejecutar SQL", e.getMessage());
        }
        return "Se registró correctamente";
    }
/*
    public static String eliminar() {
        String query = String.format("Delete from expediente_clinico where folio=%d", folio);
        try {
            getConnection().prepareStatement(query).executeUpdate();
        } catch (Exception e) {
            return String.format("Error al ejecutar SQL: %s", e.getMessage());
        }
        return "Se eliminó correctamente";
    }*/

    public static String eliminar(int folio) {
        String query = String.format("Delete from expediente_clinico where folio=%d", folio);
        try {
            getConnection().prepareStatement(query).executeUpdate();
        } catch (Exception e) {
            return String.format("Error al ejecutar SQL: %s", e.getMessage());
        }
        return "Se eliminó correctamente";
    }


    public String actualizar(ExpedienteClínico old) {
        final String QUERY_INITIAL = "Update expediente_clinico Set ";
        String query = QUERY_INITIAL;
        query += (!fecha.equals(old.fecha)) ? String.format("fecha = '%s'", fecha) : "";
        query += (!motivo.equals(old.motivo)) ? String.format("motivo = '%s'", motivo) : "";
        query += (!enfermedad.equals(old.enfermedad)) ? String.format("enfermedad = '%s'", enfermedad) : "";
        query += (!observaciones.equals(old.observaciones)) ? String.format("observaciones = '%s'", observaciones) : "";
        query += (!tratamiento.equals(old.tratamiento)) ? String.format("tratamiento = '%s'", tratamiento) : "";
        query += (!estado.equals(old.estado)) ? String.format("estado = '%s'", estado) : "";
        if (QUERY_INITIAL.equals(query))
            return "No se ha registrado ninguna actualización en el expediente clínico debido a que los datos no han sido modificados. Por favor, revise los datos y realice las actualizaciones necesarias para continuar.";
        query = query.substring(0, query.length() - 2) + String.format("WHERE folio = %d", folio);
        return query;
    }

    public static void main(String[] args) {
        try {
            System.out.println(
                    getConnection().prepareStatement("Select * from pacientes;").executeQuery());
        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }
}
