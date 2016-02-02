package Proyecto.Persistencia;

import Persistencia.ConexionBD;
import Proyecto.Dominio.Actividad;
import Proyecto.Dominio.Tarea;
import Proyecto.Dominio.TipoTarea;
import Trabajador.Dominio.Trabajador;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.sql.Date;

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

    public static ArrayList<Tarea> getDeActividad(Actividad act) throws SQLException, ClassNotFoundException {
        ArrayList<Tarea> tareas = new ArrayList<>();
        String sql = "(select * from Tarea where nombreProyecto = '" + act.getNombre() + "' and numeroEtapa = " + act.getNumero() + " and idActividad = " + act.getId()+"  order by(tipoTarea)) order by(semana)";
        String DATABASE_DRIVER = "com.mysql.jdbc.Driver";
        String DATABASE_URL = "jdbc:mysql://localhost:3306/PGP_grupo11?zeroDateTimeBehavior=convertToNull";
        String DATABASE_USER = "PGP_grupo11";
        String DATABASE_PASSWORD = "P6AbQA8Z";
        Class.forName(DATABASE_DRIVER);
        Connection conexion = DriverManager.getConnection(DATABASE_URL, DATABASE_USER, DATABASE_PASSWORD);
        Statement s = conexion.createStatement();
        ResultSet rs = s.executeQuery(sql);
        while (rs.next()) {
            Tarea t = new Tarea(rs.getString("nombreProyecto"), rs.getInt("numeroEtapa"), rs.getInt("idActividad"), rs.getString("trabajador"), rs.getDate("semana"), TipoTarea.get(rs.getString("tipoTarea")), rs.getInt("duracion"), rs.getString("estado"));
            tareas.add(t);
        }
        return tareas;
    }

    public static void aprobar(Tarea get) throws SQLException {
        String sql = "UPDATE Tarea SET estado = 'Aceptado' where nombreProyecto = '"+get.getNombreProyecto()+"' and numeroEtapa = "+get.getNumeroEtapa()+" and idActividad = "+get.getIdActividad()+" and trabajador = '"+get.getTrabajador()+"' and semana = '"+get.getSemana()+"'";
        ConexionBD conexion = new ConexionBD();
        conexion.execute(sql);
        conexion.close();
    }

    public static ArrayList<Tarea> getDeActividadMios(Actividad act, Trabajador trabajador) throws SQLException, ClassNotFoundException {
       ArrayList<Tarea> tareas = new ArrayList<>();
        String sql = "(select * from Tarea where nombreProyecto = '" + act.getNombre() + "' and numeroEtapa = " + act.getNumero() + " and idActividad = " + act.getId()+" and trabajador = '"+trabajador.getUser()+"'  order by(tipoTarea)) order by(semana)";
        String DATABASE_DRIVER = "com.mysql.jdbc.Driver";
        String DATABASE_URL = "jdbc:mysql://localhost:3306/PGP_grupo11?zeroDateTimeBehavior=convertToNull";
        String DATABASE_USER = "PGP_grupo11";
        String DATABASE_PASSWORD = "P6AbQA8Z";
        Class.forName(DATABASE_DRIVER);
        Connection conexion = DriverManager.getConnection(DATABASE_URL, DATABASE_USER, DATABASE_PASSWORD);
        Statement s = conexion.createStatement();
        ResultSet rs = s.executeQuery(sql);
        while (rs.next()) {
            Tarea t = new Tarea(rs.getString("nombreProyecto"), rs.getInt("numeroEtapa"), rs.getInt("idActividad"), rs.getString("trabajador"), rs.getDate("semana"), TipoTarea.get(rs.getString("tipoTarea")), rs.getInt("duracion"), rs.getString("estado"));
            tareas.add(t);
        }
        return tareas;
    }

    public static void guardaInforme(Tarea get, String str) throws SQLException {
        String sql = "UPDATE Tarea SET duracion = "+get.getDuracion()+" where semana = '"+get.getSemana()+"' and tipoTarea = '"+str+"' and nombreProyecto = '"+get.getNombreProyecto()+"' and numeroEtapa = "+get.getNumeroEtapa()+" and idActividad = "+get.getIdActividad()+" and trabajador = '"+get.getTrabajador()+"'";
        ConexionBD conexion = new ConexionBD();
        conexion.execute(sql);
        conexion.close();
        sql = "UPDATE Tarea SET estado = 'Enviado' where semana = '"+get.getSemana()+"' and tipoTarea = '"+str+"' and nombreProyecto = '"+get.getNombreProyecto()+"' and numeroEtapa = "+get.getNumeroEtapa()+" and idActividad = "+get.getIdActividad()+" and trabajador = '"+get.getTrabajador()+"'";
        conexion = new ConexionBD();
        conexion.execute(sql);
        conexion.close();
    }
    
    public static void almacena(Tarea get) throws SQLException {
        String sql = "Insert into Tarea values ('"+get.getNombreProyecto()+"', "+get.getNumeroEtapa()+", "+get.getIdActividad()+", '"+get.getTrabajador()+"', '"+(get.getSemana().getYear()+1900)+"-"+(get.getSemana().getMonth()+1)+"-"+get.getSemana().getDate()+"', '"+get.getTipoTarea().toString()+"', 0, 'Pendiente')";
        System.out.println("Insert into Tarea values ('"+get.getNombreProyecto()+"', "+get.getNumeroEtapa()+", "+get.getIdActividad()+", '"+get.getTrabajador()+"', '"+(get.getSemana().getYear()+1900)+"-"+(get.getSemana().getMonth()+1)+"-"+get.getSemana().getDate()+"', '"+get.getTipoTarea().toString()+"', 0, 'Pendiente')");
        ConexionBD conexion = new ConexionBD();
        conexion.execute(sql);
        conexion.close();
    }
}
