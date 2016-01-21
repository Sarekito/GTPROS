package Proyecto.Persistencia;

import Persistencia.ConexionBD;
import Persistencia.ObjectConverter;
import Proyecto.Dominio.Tarea;
import Proyecto.Dominio.TipoTarea;
import Proyecto.Dominio.Actividad;
import Proyecto.Dominio.ActividadTrabajador;
import Proyecto.Dominio.Proyecto;
import Proyecto.Dominio.TrabajadoresProyecto;
import Trabajador.Dominio.Trabajador;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author dalonso
 */
public class TareaPersistencia {
    public static void guardarTareaIntroducida(String proyecto, String etapa, String actividad, String user, int numTarea, Date semana, String tipoTarea, int duracion) throws SQLException{
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (Exception e) {

        }
        Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost/PGP_grupo11", "PGP_grupo11", "P6AbQA8Z");
        Statement s = conexion.createStatement();
        s.execute("insert into Tarea values('" + proyecto + "', "+ Integer.parseInt(etapa) + ", " + Integer.parseInt(actividad) + ", '" + user + "', "+numTarea+", '"+semana+"','"+tipoTarea+"',"+duracion+")");
    }
}
