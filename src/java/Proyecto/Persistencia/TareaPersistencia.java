package Proyecto.Persistencia;

import Persistencia.ConexionBD;
import java.sql.SQLException;
import java.util.Date;

/**
 *
 * @author dalonso
 */
public class TareaPersistencia {

    public static void guardarTareaIntroducida(String proyecto, String etapa, String actividad, String user, int numTarea, Date semana, String tipoTarea, int duracion) throws SQLException {
        String sql = "INSERT INTO Tarea VALUES ('" + proyecto + "', " + Integer.parseInt(etapa) + ", " + Integer.parseInt(actividad) + ", '" + user + "', " + numTarea + ", '" + semana + "','" + tipoTarea + "'," + duracion + ")";
        
        ConexionBD conexion = new ConexionBD();
        conexion.execute(sql);
        conexion.close();
    }
}
