
import static Conexión.Conexión.getConnection;
import java.sql.Date;

public class ExpedienteClínico {

    private int folio;
    private Date fecha;
    private String motivo, enfermedad, observaciones, tratamiento, estado;
    private int id_paciente;

    public ExpedienteClínico(int folio, Date fecha, String motivo, String enfermedad, String observaciones, String tratamiento, String estado, int id_paciente) {
        this.folio = folio;
        this.fecha = fecha;
        this.motivo = motivo;
        this.enfermedad = enfermedad;
        this.observaciones = observaciones;
        this.tratamiento = tratamiento;
        this.estado = estado;
        this.id_paciente = id_paciente;
    }

    public ExpedienteClínico(Date fecha, String motivo, String enfermedad, String observaciones, String tratamiento, String estado, int id_paciente) {
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

    public String actualizar() {
        String query = String.format("UPDATE expediente_clinico\n"
                + "SET fecha = '%s', motivo = '%s', enfermedad = '%s', observaciones = '%s', tratamiento = '%s', estado = '%s' WHERE folio = %d;", fecha, motivo, enfermedad, observaciones, tratamiento, estado, folio);
        try {
            getConnection().prepareStatement(query).executeUpdate();
        } catch (Exception e) {
            return String.format("Error al ejecutar SQL", e.getMessage());
        }
        return "Se actualizó correctamente";
    }

    public static void main(String[] args) {
        try {
            System.out.println(getConnection().prepareStatement("Select * from Expediente_Clinico;").executeQuery().getMetaData());
        } catch (Exception e) {
        }
    }
}
