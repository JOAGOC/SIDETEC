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

/**
 * Representa un expediente clínico de un paciente.
 * Permite realizar operaciones CRUD en la base de datos relacionadas con los expedientes clínicos.
 * Implementa la interfaz Cloneable; para poder crear copias a partir de objetos existentes, y la interfaz CRUD.
 */
public class ExpedienteClínico implements Cloneable, CRUD {

    private static final String CONSULTA_TABLA = "Select folio as Folio, fecha as Fecha, motivo as Motivo, enfermedad as Enfermedad, observaciones as Observaciones,\n"
            + "tratamiento as Tratamiento, CONCAT_WS(' ',nombre, apellido,'-',id) as Paciente\n"
            + "from expediente_clinico inner join pacientes on id_paciente = id where estado like '1'";

    /**
     * Enumeración que define los estados posibles de un expediente clínico.
     */
    public enum Estado {
        Inactivo("0"),
        Activo("1");

        /**
         * Devuelve el estado correspondiente al carácter proporcionado.
         * 
         * @param c Carácter que representa el estado.
         * @return Estado correspondiente al carácter.
         * @throws IllegalArgumentException si el carácter no es válido.
         */
        public static Estado fromChar(String c) {
            for (Estado estado : values()) {
                if (estado.c.equals(c)) {
                    return estado;
                }
            }
            throw new IllegalArgumentException("Carácter inválido: " + c);
        }

        public final String c;

        /**
         * Constructor privado para definir los estados y su caracter asociado.
         * @param c
         */
        private Estado(String c) {
            this.c = c;
        }
    }

    /**
     * Elimina un expediente clínico de la base de datos dado su número de folio.
     *
     * @param folio Número de folio del expediente clínico a eliminar.
     * @return Mensaje indicando el resultado de la operación.
     */
    public static String eliminar(int folio) {
        String query = String.format("Delete from expediente_clinico where folio=%d", folio);
        try {
            getConnection().prepareStatement(query).executeUpdate();
        } catch (Exception e) {
            return String.format("Error al ejecutar SQL: %s", e.getMessage());
        }
        return "Se eliminó correctamente";
    }

    /**
     * Consulta los expedientes clínicos de un paciente en la base de datos.
     *
     * @param id_paciente ID del paciente.
     * @return Lista de expedientes clínicos del paciente.
     */
    public static List<ExpedienteClínico> consultar(int id_paciente) {
        try {
            ResultSet row = getConnection()
                    .prepareStatement(
                            String.format(
                                    "Select * from expediente_clinico where estado = '1' and id_paciente = %d order by fecha,folio desc;",
                                    id_paciente))
                    .executeQuery();
            if (row.next()){
                List<ExpedienteClínico> expedientes = new ArrayList<ExpedienteClínico>();
                Paciente paciente = Paciente.consultar(id_paciente);
                do {
                    int index = 1;
                    expedientes.add(new ExpedienteClínico(row.getInt(index++),
                            row.getDate(index++),
                            row.getString(index++),
                            row.getString(index++),
                            row.getString(index++),
                            row.getString(index++),
                            Estado.fromChar(row.getString(index++)),
                            paciente));
                } while (row.next());
                return expedientes;
            }
            return null;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    /** 
     * @return Devuelve un DefaultTableModel con la consulta general.
     */
    public static DefaultTableModel consultar() {
        return CRUD.consultar(
                CONSULTA_TABLA);
    }

    /**
     * Busca expedientes clínicos utilizando criterios estrictos o no estrictos.
     * @see #buscarAND(String, String, String)
     * @see #buscarOR(String, String, String)
     * 
     * @param folio    El número de folio del expediente clínico.
     * @param paciente El nombre de un paciente asignado al expediente que se busca.
     *                 Por defecto, se busca que el nombre se contenga en el nombre
     *                 completo.
     * @param fecha La fecha a buscar en el formato "yyyy-MM-dd". Se utiliza como criterio de filtro para buscar registros en los expedientes clínicos de la base de datos.
     * @param estricto Un valor booleano que determina si la búsqueda debe ser estricta o no. Si se
     * establece en verdadero, la búsqueda solo arrojará resultados que coincidan con todos los
     * criterios de búsqueda (folio, paciente y fecha). Si se establece en falso, la búsqueda arrojará
     * resultados que coincidan con cualquiera de los criterios de búsqueda.
     * @return Devuelve un DefaultTableModel con los valores encontrados.
     */
    public static DefaultTableModel buscar(String folio, String paciente, String fecha, boolean estricto) throws Exception{
        return estricto ? buscarAND(folio, paciente, fecha):buscarOR(folio, paciente, fecha);
    }

    /**
     * Busca expedientes clínicos según los filtros dados utilizando la cláusula OR
     * y devuelve los resultados en un objeto DefaultTableModel.
     * 
     * La función busca encontrar cualquier coincidencia, sea por folio, paciente o fecha.
     * 
     * @see CRUD#consultar(String)
     * @see CRUD#consultar(PreparedStatement)
     * @param folio    El número de folio del expediente clínico.
     * @param paciente El nombre de un paciente asignado al expediente que se busca.
     *                 Por defecto, se busca que el nombre se contenga en el nombre
     *                 completo.
     * @param fecha    La fecha de los expedientes clínicos que se buscan.
     * @return Un DefaultTableModel con los registros que encontró.
     * @throws Exception Si se llama la función y los parámetros {@link #folio},
     *                   {@link #fecha} y {@link #paciente} son vacíos.
     */
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

    /**
     * Busca expedientes clínicos según los filtros dados utilizando la cláusula AND
     * y devuelve los resultados en un objeto DefaultTableModel.
     * 
     * Si se especifica el folio, sólo se busca por folio para evitar ambigüedades
     * en la búsqueda. Por el contrario, la función devolverá las coincidencias de
     * fecha y nombre del paciente, según se especifiquen o no.
     * 
     * @see CRUD#consultar(String)
     * @see CRUD#consultar(PreparedStatement)
     * @param folio    El número de folio del expediente clínico.
     * @param paciente El nombre de un paciente asignado al expediente que se busca.
     *                 Por defecto, se busca que el nombre se contenga en el nombre
     *                 completo.
     * @param fecha    La fecha de los expedientes clínicos que se buscan.
     * @return Un DefaultTableModel con los registros que encontró.
     * @throws Exception Si se llama la función y los parámetros {@link #folio},
     *                   {@link #fecha} y {@link #paciente} son vacíos.
     */
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
    private Paciente paciente;
    private ExpedienteClínico old;

    /**
     * Constructor de la clase ExpedienteClínico.
     *
     * @param folio Número de folio del expediente clínico.
     * @param fecha Fecha del expediente clínico.
     * @param motivo Motivo del expediente clínico.
     * @param enfermedad Enfermedad registrada en el expediente clínico.
     * @param observaciones Observaciones del expediente clínico.
     * @param tratamiento Tratamiento del expediente clínico.
     * @param estado Estado del expediente clínico.
     * @param paciente Paciente asociado al expediente clínico.
     */
    public ExpedienteClínico(int folio, Date fecha, String motivo, String enfermedad, String observaciones,
            String tratamiento, Estado estado, Paciente paciente) {
        this.folio = folio;
        this.fecha = fecha;
        this.motivo = motivo;
        this.enfermedad = enfermedad;
        this.observaciones = observaciones;
        this.tratamiento = tratamiento;
        this.estado = estado;
        this.paciente = paciente;
        try {
            old = (ExpedienteClínico) this.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
    }

    /**
     * El constructor inicializa las variables de instancia con los valores pasados
     * como parámetros y también crea una copia del objeto usando el método clone()
     * y la guarda en la variable "old" (viejo). Esto para su uso en el método actualizar.
     * 
     * @param fecha
     * @param motivo
     * @param enfermedad
     * @param observaciones
     * @param tratamiento
     * @param estado
     * @param paciente
     */
    public ExpedienteClínico(Date fecha, String motivo, String enfermedad, String observaciones, String tratamiento,
            Estado estado, Paciente paciente) {
        this.fecha = fecha;
        this.motivo = motivo;
        this.enfermedad = enfermedad;
        this.observaciones = observaciones;
        this.tratamiento = tratamiento;
        this.estado = estado;
        this.paciente = paciente;
        try {
            old = (ExpedienteClínico) this.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
    }

    public ExpedienteClínico() {
    }

    /**
     * Devuelve el número de folio del expediente clínico.
     *
     * @return Número de folio del expediente clínico.
     */
    public int getFolio() {
        return folio;
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


    /**
     * Establece el número de folio del expediente clínico.
     *
     * @param folio Número de folio del expediente clínico.
     */
    public void setFolio(int folio) {
        this.folio = folio;
    }

    /**
     * Devuelve la fecha del expediente clínico.
     *
     * @return Fecha del expediente clínico.
     */
    public Date getFecha() {
        return fecha;
    }

    /**
     * Establece la fecha del expediente clínico.
     *
     * @param fecha Fecha del expediente clínico.
     */
    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    /**
     * Devuelve el motivo del expediente clínico.
     *
     * @return Motivo del expediente clínico.
     */
    public String getMotivo() {
        return motivo;
    }

    /**
     * Establece el motivo del expediente clínico.
     *
     * @param motivo Motivo del expediente clínico.
     */
    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }

    /**
     * Devuelve la enfermedad registrada en el expediente clínico.
     *
     * @return Enfermedad registrada en el expediente clínico.
     */
    public String getEnfermedad() {
        return enfermedad;
    }

    /**
     * Establece la enfermedad registrada en el expediente clínico.
     *
     * @param enfermedad Enfermedad registrada en el expediente clínico.
     */
    public void setEnfermedad(String enfermedad) {
        this.enfermedad = enfermedad;
    }

    /**
     * Devuelve las observaciones del expediente clínico.
     *
     * @return Observaciones del expediente clínico.
     */
    public String getObservaciones() {
        return observaciones;
    }

    /**
     * Establece las observaciones del expediente clínico.
     *
     * @param observaciones Observaciones del expediente clínico.
     */
    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    /**
     * Devuelve el tratamiento del expediente clínico.
     *
     * @return Tratamiento del expediente clínico.
     */
    public String getTratamiento() {
        return tratamiento;
    }

    /**
     * Establece el tratamiento del expediente clínico.
     *
     * @param tratamiento Tratamiento del expediente clínico.
     */
    public void setTratamiento(String tratamiento) {
        this.tratamiento = tratamiento;
    }

    /**
     * Devuelve el estado del expediente clínico.
     *
     * @return Estado del expediente clínico.
     */
    public Estado getEstado() {
        return estado;
    }

    /**
     * Establece el estado del expediente clínico.
     *
     * @param estado Estado del expediente clínico.
     */
    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    /**
     * Devuelve el paciente asociado al expediente clínico.
     *
     * @return Paciente asociado al expediente clínico.
     */
    public Paciente getPaciente() {
        return paciente;
    }

    /**
     * Establece el paciente asociado al expediente clínico.
     *
     * @param paciente Paciente asociado al expediente clínico.
     */
    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }

    /**
     * Clona el expediente clínico.
     *
     * @return Clon del expediente clínico.
     * @throws CloneNotSupportedException si la clonación no es compatible.
     */
    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    /**
     * Inserta el expediente clínico en la base de datos.
     *
     * @return Mensaje indicando el resultado de la operación.
     */
    @Override
    public String insertar() {
        String query = "Insert into expediente_clinico (fecha,motivo,enfermedad,observaciones,tratamiento,estado,id_paciente) values (?,?,?,?,?,?,?)";
        try {
            PreparedStatement ps = getConnection().prepareStatement(query);
            ps.setDate(1, fecha);
            ps.setString(2, motivo);
            ps.setString(3, enfermedad);
            ps.setString(4, observaciones);
            ps.setString(5, tratamiento);
            ps.setString(6, estado.c);
            ps.setInt(7, paciente.getId());
            ps.executeUpdate();
            return "Expediente clínico registrado correctamente";
        } catch (Exception e) {
            return String.format("Error al ejecutar SQL: %s", e.getMessage());
        }
    }

    /**
     * Actualiza el expediente clínico en la base de datos.
     *
     * @return Mensaje indicando el resultado de la operación.
     */
    @Override
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
     * Elimina el expediente clínico de la base de datos.
     *
     * @return Mensaje indicando el resultado de la operación.
     */
    @Override
    public String eliminar() {
        return eliminar(folio);
    }
}