package ClasesSQL;

import static Conexión.Conexión.getConnection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import Interfaces.CRUD;
import javax.swing.table.DefaultTableModel;



public class ExpedienteClínico implements Cloneable, CRUD {

    private static final String CONSULTA_TABLA = "Select folio as Folio, fecha as Fecha, motivo as Motivo, enfermedad as Enfermedad, observaciones as Observaciones,\n"
            + "tratamiento as Tratamiento, CONCAT_WS(' ',nombre, apellido,'-',id) as Paciente\n"
            + "from expediente_clinico inner join pacientes on id_paciente = id where estado like '1'";

    public enum Estado {
        Inactivo("0"),
        Activo("1");

        public static Estado fromChar(String c) {
            for (Estado estado : values()) {
                if (estado.c.equals(c)) {
                    return estado;
                }
            }
            throw new IllegalArgumentException("Carácter inválido: " + c);
        }

        public final String c;

        private Estado(String c) {
            this.c = c;
        }
    }

    public static String eliminar(int folio) {
        String query = String.format("Delete from expediente_clinico where folio=%d", folio);
        try {
            getConnection().prepareStatement(query).executeUpdate();
        } catch (Exception e) {
            return String.format("Error al ejecutar SQL: %s", e.getMessage());
        }
        return "Se eliminó correctamente";
    }

    public static List<ExpedienteClínico> consultar(int id_paciente) {
        try {
            ResultSet row = getConnection()
                    .prepareStatement(
                            String.format(
                                    "Select * from expediente_clinico where estado = '1' and id_paciente = %d order by fecha,folio desc;",
                                    id_paciente))
                    .executeQuery();
            List<ExpedienteClínico> expedientes = new ArrayList<ExpedienteClínico>();
            while (row.next()) {
                int index = 1;
                expedientes.add(new ExpedienteClínico(row.getInt(index++),
                        row.getDate(index++),
                        row.getString(index++),
                        row.getString(index++),
                        row.getString(index++),
                        row.getString(index++),
                        Estado.fromChar(row.getString(index++)),
                        row.getInt(index++)));
            }
            return expedientes;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static DefaultTableModel consultar() {
        return CRUD.consultar(
                CONSULTA_TABLA);
    }

    public static DefaultTableModel buscar(String folio, String paciente, String fecha, boolean estricto) throws Exception{
        return estricto ? buscarAND(folio, paciente, fecha):buscarOR(folio, paciente, fecha);
    }

    private static DefaultTableModel buscarOR(String folio, String paciente, String fecha) throws Exception {
        final String INITIAL = CONSULTA_TABLA + " AND (";
        String query = INITIAL;
        List<Object> values = new ArrayList<>();
        if (!folio.equals("")) {
            query += "folio = ? OR ";
            values.add(Integer.parseInt(folio));
        }
        if (!paciente.equals("")) {
            query += "concat_ws(' ',nombre,apellido) like ? OR ";
            values.add("%" + paciente + "%");
        }
        if (!fecha.equals("")) {
            query += "fecha like ? OR ";
            values.add(fecha);
        }
        if (query.equals(INITIAL))
            throw new Exception("No se estableció ningún filtro para buscar.");
        query = query.substring(0, query.length()-4) + ");";
        PreparedStatement pstmt = getConnection().prepareStatement(query);
        for (int i = 0; i < values.size(); i++) {
            pstmt.setObject(i + 1, values.get(i));
        }
        System.out.println(pstmt.toString());
        return CRUD.consultar(pstmt);
    }

    private static DefaultTableModel buscarAND(String folio, String paciente, String fecha) throws Exception {
        final String INITIAL = CONSULTA_TABLA + " ";
        String query = INITIAL;
        List<Object> values = new ArrayList<>();
        do {
            if (!folio.equals("")) {
                query += "AND folio = ? ";
                values.add(Integer.parseInt(folio));
                break;
            }
            if (!paciente.equals("")) {
                query += "AND concat_ws(' ',nombre,apellido) like ? ";
                values.add("%" + paciente + "%");
            }
            if (!fecha.equals("")) {
                query += "AND fecha like ? ";
                values.add(fecha);
            }
            break;
        } while (false);
        if (query.equals(INITIAL))
            throw new Exception("No se estableció ningún filtro para buscar.");
        PreparedStatement pstmt = getConnection().prepareStatement(query);
        for (int i = 0; i < values.size(); i++) {
            pstmt.setObject(i + 1, values.get(i));
        }
        System.out.println(pstmt.toString());
        return CRUD.consultar(pstmt);
    }

    private int folio;
    private Date fecha;
    private String motivo, enfermedad, observaciones, tratamiento;
    private Estado estado;

    private int id_paciente;

    private ExpedienteClínico old;

    public ExpedienteClínico(int folio, Date fecha, String motivo, String enfermedad, String observaciones,
            String tratamiento, Estado estado, int id_paciente) {
        this.folio = folio;
        this.fecha = fecha;
        this.motivo = motivo;
        this.enfermedad = enfermedad;
        this.observaciones = observaciones;
        this.tratamiento = tratamiento;
        this.estado = estado;
        this.id_paciente = id_paciente;
        try {
            old = (ExpedienteClínico) this.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
    }

    public ExpedienteClínico(Date fecha, String motivo, String enfermedad, String observaciones, String tratamiento,
            Estado estado, int id_paciente) {
        this.fecha = fecha;
        this.motivo = motivo;
        this.enfermedad = enfermedad;
        this.observaciones = observaciones;
        this.tratamiento = tratamiento;
        this.estado = estado;
        this.id_paciente = id_paciente;
        try {
            old = (ExpedienteClínico) this.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
    }

    public ExpedienteClínico() {
    }

    public String insertar() {
        String query = "INSERT INTO expediente_clinico(fecha, motivo, enfermedad, observaciones, tratamiento, estado, id_paciente) VALUES (?,?,?,?,?,?,?);";
        // (fecha, motivo, enfermedad, observaciones, tratamiento, estado, id_paciente);
        try {
            PreparedStatement stmn = getConnection().prepareStatement(query);
            int index = 1;
            stmn.setString(index++, fecha.toString());
            stmn.setString(index++, motivo);
            stmn.setString(index++, enfermedad);
            stmn.setString(index++, observaciones);
            stmn.setString(index++, tratamiento);
            stmn.setString(index++, estado.c);
            stmn.setInt(index++, id_paciente);
            System.out.println(stmn);
            stmn.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
            return String.format("Error al ejecutar SQL", e.getMessage());
        }
        return "Se registró correctamente";
    }

    public String eliminar() {
        String query = "Update expediente_clinico set estado = ? where folio = ?";
        try {
            PreparedStatement stmnt = getConnection().prepareStatement(query);
            int index = 1;
            stmnt.setObject(index++, Estado.Inactivo.c);
            stmnt.setObject(index++, folio);
            stmnt.executeUpdate();
        } catch (Exception e) {
            return String.format("Error al ejecutar SQL: %s", e.getMessage());
        }
        return "Se eliminó correctamente";
    }

    public String actualizar() {
        final String QUERY_INITIAL = "UPDATE expediente_clinico SET ";
        String query = QUERY_INITIAL;
        List<Object> values = new ArrayList<>();
        if (!fecha.toString().equals(old.fecha.toString())) {
            query += "fecha = ?, ";
            values.add(fecha);
        }
        if (!motivo.equals(old.motivo)) {
            query += "motivo = ?, ";
            values.add(motivo);
        }
        if (!enfermedad.equals(old.enfermedad)) {
            query += "enfermedad = ?, ";
            values.add(enfermedad);
        }
        if (!observaciones.equals(old.observaciones)) {
            query += "observaciones = ?, ";
            values.add(observaciones);
        }
        if (!tratamiento.equals(old.tratamiento)) {
            query += "tratamiento = ?, ";
            values.add(tratamiento);
        }
        if (!estado.equals(old.estado)) {
            query += "estado = ?, ";
            values.add(estado.c + "");
        }
        if (QUERY_INITIAL.equals(query)) {
            return "No se ha registrado ninguna actualización en el expediente clínico debido a que los datos no han sido modificados. Por favor, revise los datos y realice las actualizaciones necesarias para continuar.";
        }
        query = query.substring(0, query.length() - 2) + String.format(" WHERE folio = %d;", folio);
        try (
                PreparedStatement pstmt = getConnection().prepareStatement(query)) {
            for (int i = 0; i < values.size(); i++) {
                pstmt.setObject(i + 1, values.get(i));
            }
            System.out.println(pstmt.toString());
            pstmt.executeUpdate();
            return "Los datos del expediente clínico han sido actualizados correctamente.";
        } catch (SQLException e) {
            return "Ha ocurrido un error al actualizar los datos del expediente clínico: " + e.getMessage();
        }
    }
/**
     * 
     * Verifica si un expediente clínico existe en una fecha concreta.
     * 
     * @param fecha Fecha en que se quiere comprobar la existencia de un expediente.
     * @return True. Si se ha encontrado el expediente. De lo contrario, retorna False.
     */
    public static boolean existeBD(String fecha, int idPaciente){
        PreparedStatement stm = null;
        try {
            stm = getConnection().prepareStatement(CONSULTA_TABLA + " and fecha like ? and id_paciente = ?");
            stm.setString(1,fecha);
            stm.setInt(2, idPaciente);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return CRUD.consultar(stm).getRowCount()!=0;
    }
    public int getFolio() {
        return folio;
    }

    public void setFolio(int folio) {
        this.folio = folio;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getMotivo() {
        return motivo;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }

    public String getEnfermedad() {
        return enfermedad;
    }

    public void setEnfermedad(String enfermedad) {
        this.enfermedad = enfermedad;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public String getTratamiento() {
        return tratamiento;
    }

    public void setTratamiento(String tratamiento) {
        this.tratamiento = tratamiento;
    }

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    public int getId_paciente() {
        return id_paciente;
    }

    public void setId_paciente(int id_paciente) {
        this.id_paciente = id_paciente;
    }
}
