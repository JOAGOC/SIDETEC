import java.sql.ResultSet;
import javax.swing.table.DefaultTableModel;
import com.mysql.cj.jdbc.result.ResultSetMetaData;
import static Conexión.Conexión.getConnection;

public interface CRUD{
    public static DefaultTableModel consultar(String query){
        DefaultTableModel tabla = null;
        try {
            ResultSet data = getConnection().prepareStatement(query).executeQuery();
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
            
            tabla = new DefaultTableModel(devlolverColumnas.apply(columnas),0);
            int index = 0;
            Object[] registro = new Object[columnas.getColumnCount()];
            while(data.next()){
                while (index < columnas.getColumnCount())
                    registro[index++] = data.getObject(index);
                tabla.addRow(registro);
                index = 0;
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return tabla;
    }

    public String insertar();
    public String eliminar();
    public String actualizar();
}