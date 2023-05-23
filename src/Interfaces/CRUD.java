package Interfaces;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.swing.table.DefaultTableModel;
import com.mysql.cj.jdbc.result.ResultSetMetaData;

import Componentes.AutoCompleteTextField;

import static Conexión.Conexión.getConnection;

public interface CRUD{

   
    /**
    * Llena el autocompletar con la primer columna de resultados del query.
    *
    * @param query la consulta SQL para llenar el Autocomplete
    * @param atf el objeto autocomplete que se quiere llenar
    */
  public static void cargarAutocompletar(String query, AutoCompleteTextField atf){
    try {
        ResultSet data = getConnection().prepareStatement(query).executeQuery();
        ArrayList<String> registros = new ArrayList<String>();
        while(data.next()){
            registros.add(data.getObject(1).toString());
        }
        atf.setItems(registros.toArray(new String[registros.size()]));
    } catch (Exception e) {
        e.printStackTrace();
    }
}

    public static DefaultTableModel consultar(String query){
        DefaultTableModel m;
        try {
            m = consultar(getConnection().prepareStatement(query));
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return m;
    }

    public static DefaultTableModel consultar(PreparedStatement stmn){
        DefaultTableModel tabla = null;
        try {
            ResultSet data = stmn.executeQuery();
            ResultSetMetaData columnas = (ResultSetMetaData)data.getMetaData();
            
            java.util.function.Function<ResultSetMetaData,String[]> devlolverColumnas = (rs) -> {
                String[] nombreCol = null;
                try {
                    int colcount = rs.getColumnCount();
                    nombreCol = new String[colcount];
                    for(int i = 0;i<colcount;){
                        nombreCol[i++] = rs.getColumnLabel(i);
                    };
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
                return nombreCol;
            };
            
            tabla = new DefaultTableModel(devlolverColumnas.apply(columnas),0){
                public boolean isCellEditable(int row, int column) {
                    return false;
                }
            };
            int index = 0;
            Object[] registro = new Object[columnas.getColumnCount()];
            while(data.next()){
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

    public String insertar();
    public String eliminar();
    public String actualizar();
}