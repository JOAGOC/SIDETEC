package Interfaces;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.swing.table.DefaultTableModel;
import com.mysql.cj.jdbc.result.ResultSetMetaData;

import Componentes.AutoCompleteTextField;

import static Conexión.Conexión.getConnection;

/**
 * Interface contenedora de los métodos abstractos para manejar DML. Insertar, Eliminar y Actualizar. Y un métodos genéricos de consulta.
 */
public interface CRUD {

    /**
     * Llena el autocompletar con la primer columna de resultados del query.
     *
     * @param query la consulta SQL para llenar el Autocomplete
     * @param atf   el objeto autocomplete que se quiere llenar
     * @see AutoCompleteTextField
     */
    public static void cargarAutocompletar(String query, AutoCompleteTextField atf) {
        try {
            ResultSet data = getConnection().prepareStatement(query).executeQuery();
            ArrayList<String> registros = new ArrayList<String>();
            while (data.next()) {
                registros.add(data.getObject(1).toString());
            }
            atf.setItems(registros.toArray(new String[registros.size()]));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Realiza una consulta utilizando la conexión establecida y devuelve los
     * resultados en un DefaultTableModel.
     *
     * @param query la consulta SQL a ejecutar
     * @return un DefaultTableModel con los resultados de la consulta, o null si
     *         ocurre un error
     * @see CRUD#consultar(PreparedStatement)
     */
    public static DefaultTableModel consultar(String query) {
        DefaultTableModel m;
        try {
            m = consultar(getConnection().prepareStatement(query));
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return m;
    }

    /**
     * Realiza una consulta utilizando el PreparedStatement proporcionado y devuelve
     * los resultados en un DefaultTableModel.
     *
     * @param stmn el PreparedStatement que contiene la consulta SQL. Útil cuando
     *             una consulta SQL tiene parámetros que hay que especificar.
     * @return un DefaultTableModel con los resultados de la consulta, o null si
     *         ocurre un error. El modelo devuelto no tiene celdas editables.
     * @see CRUD#consultar(String)
     */
    public static DefaultTableModel consultar(PreparedStatement stmn) {
        DefaultTableModel tabla = null;
        try {
            ResultSet data = stmn.executeQuery();
            ResultSetMetaData columnas = (ResultSetMetaData) data.getMetaData();

            java.util.function.Function<ResultSetMetaData, String[]> devlolverColumnas = (rs) -> {
                String[] nombreCol = null;
                try {
                    int colcount = rs.getColumnCount();
                    nombreCol = new String[colcount];
                    for (int i = 0; i < colcount;) {
                        nombreCol[i++] = rs.getColumnLabel(i);
                    }
                    ;
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
                return nombreCol;
            };

            tabla = new DefaultTableModel(devlolverColumnas.apply(columnas), 0) {
                public boolean isCellEditable(int row, int column) {
                    return false;
                }
            };
            int index = 0;
            Object[] registro = new Object[columnas.getColumnCount()];
            while (data.next()) {
                while (index < columnas.getColumnCount())
                    registro[index++] = data.getObject(index);
                tabla.addRow(registro);
                index = 0;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return tabla;
    }

    /**
     * Método a ser implementado por las clases que implementen la interfaz CRUD.
     * Inserta un registro.
     *
     * @return una cadena de texto con el resultado de la operación
     */
    public String insertar();

    /**
     * Método a ser implementado por las clases que implementen la interfaz CRUD.
     * Elimina un registro.
     *
     * @return una cadena de texto con el resultado de la operación
     */
    public String eliminar();

    /**
     * Método a ser implementado por las clases que implementen la interfaz CRUD.
     * Actualiza un registro.
     *
     * @return una cadena de texto con el resultado de la operación
     */
    public String actualizar();

}