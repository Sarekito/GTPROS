package Proyecto.Persistencia;

import Persistencia.ConexionBD;
import Persistencia.ObjectConverter;
import Proyecto.Dominio.ActividadTrabajador;
import Proyecto.Dominio.Proyecto;
import Proyecto.Dominio.TrabajadoresProyecto;
import Trabajador.Dominio.Trabajador;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author antonio
 */
public class ProyectoPersistencia {

    private static final ObjectConverter<Proyecto> proyectoConverter = new ObjectConverter<Proyecto>() {

        @Override
        public Proyecto convert(ResultSet result) throws SQLException {
            Proyecto proyecto = new Proyecto(result.getString("nombreProyecto"), result.getString("jefeProyecto"));
            proyecto.setFechaFinReal(result.getDate("fechaFinReal"));
            proyecto.setFechaInicio(result.getDate("fechaInicio"));
            proyecto.setFechaFin(result.getDate("fechaFin"));
            proyecto.setDuracion(result.getInt("duracion"));
            proyecto.setDuracionReal(result.getInt("duracionReal"));
            proyecto.setEstado(result.getString("estado"));
            return proyecto;
        }

        @Override
        public String createInsertQuery(Proyecto object) {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }
    };

    public static ArrayList<Proyecto> getMisProyectos(String jefe) throws SQLException {
        String sql = "select * from Proyecto P where P.jefeProyecto='" + jefe + "' and P.estado <> 'cerrado'";

        ConexionBD conexion = new ConexionBD();
        ArrayList<Proyecto> proyectos = conexion.searchAll(proyectoConverter, sql);
        conexion.close();

        return proyectos;
    }

    public static ArrayList<Proyecto> getProyectosActuales(String usuario) throws SQLException {
        String sql = "SELECT P.* FROM Proyecto P, TrabajadoresProyecto T WHERE P.nombre = T.nombre AND T.user = '" + usuario + "'";

        ConexionBD conexion = new ConexionBD();
        ArrayList<Proyecto> proyectos = conexion.searchAll(proyectoConverter, sql);
        conexion.close();

        return proyectos;
    }

    public static Proyecto getProyecto(String nombreProyecto) throws SQLException {
        String sql = "SELECT * FROM Proyecto P WHERE P.nombre = '" + nombreProyecto + "'";

        ConexionBD conexion = new ConexionBD();
        Proyecto proyecto = conexion.search(proyectoConverter, sql);
        conexion.close();

        return proyecto;
    }

    public static ArrayList<Proyecto> getProyectosFinalizados() throws SQLException {
        String sql = "SELECT * FROM Proyecto WHERE estado = 'cerrado'";

        ConexionBD conexion = new ConexionBD();
        ArrayList<Proyecto> proyectos = conexion.searchAll(proyectoConverter, sql);
        conexion.close();

        return proyectos;
    }

    public static void generar(String nombreProyecto, String jefe) throws SQLException {
        String sql = "INSERT INTO Proyecto(nombre, jefeProyecto, estado) VALUES ('" + nombreProyecto + "', '" + jefe + "', 'pendiente')";

        ConexionBD conexion = new ConexionBD();
        conexion.execute(sql);
        conexion.close();
    }

    public static void cerrarProyecto(String nombreProyecto) throws SQLException {
        String sql = "UPDATE Proyecto SET estado = 'cerrado' WHERE nombre = '" + nombreProyecto + "'";

        ConexionBD conexion = new ConexionBD();
        conexion.execute(sql);
        conexion.close();
    }

    public static ArrayList<Proyecto> getMisProyectosActuales(Trabajador tr) throws SQLException {
        String sql = "SELECT P.* FROM (SELECT * FROM TrabajadoresProyecto TP where TP.trabajador = '" + tr.getUser() + "')TP, Proyecto P where P.nombreProyecto = TP.nombreProyecto and P.estado = 'realizando'";

        ConexionBD conexion = new ConexionBD();
        ArrayList<Proyecto> proyectos = conexion.searchAll(proyectoConverter, sql);
        conexion.close();

        return proyectos;
    }

    public static TrabajadoresProyecto getTrabajadorProyecto(String user, String nombre) throws SQLException {
        String sql = "Select * from TrabajadoresProyecto TP where TP.user = '" + user + "' and TP.nombre = '" + nombre + "'";

        ConexionBD conexion = new ConexionBD();
        Statement s = conexion.createStatement();
        ResultSet rs = s.executeQuery(sql);

        if (rs.next()) {
            return new TrabajadoresProyecto(rs.getString("nombre"), rs.getString("user"), rs.getInt("porcentaje"));
        } else {
            return null;
        }
    }

    public static void guardarProyecto(Proyecto proyecto) throws SQLException {
        String sql = "UPDATE Proyecto P SET P.fechaInicio = '" + proyecto.getFechaInicio() + "', P.fechaFin = '" + proyecto.getFechaFin() + "' WHERE P.nombre = '" + proyecto.getNombre() + "'";
        //String sql2 = "UPDATE Proyecto P set estado = 'realizando' where P.nombre = '" + proyecto.getNombre() + "'";

        ConexionBD conexion = new ConexionBD();
        conexion.execute(sql);
        conexion.close();
    }

    public static void guardarTrabajadores(TrabajadoresProyecto get) throws SQLException {
        String sql = "INSERT INTO TrabajadoresProyecto VALUES ('" + get.getNombre() + "', '" + get.getUser() + "', " + get.getDedicacion() + ")";

        ConexionBD conexion = new ConexionBD();
        conexion.execute(sql);
        conexion.close();
    }

    public static void guardarAsignaciones(ActividadTrabajador get) throws SQLException {
        String sql = "INSERT INTO ActividadTrabajador VALUES ('" + get.getNombreProyecto() + "', " + get.getNumeroEtapa() + ", " + get.getIdActividad() + ", '" + get.getNombreTrabajador() + "', " + get.getHoras() + ")";

        ConexionBD conexion = new ConexionBD();
        conexion.execute(sql);
        conexion.close();
    }

    public static ArrayList<Proyecto> getCerrados() throws SQLException {
        String sql = "SELECT * FROM Proyecto WHERE estado = 'cerrado'";
        ConexionBD conexion = new ConexionBD();
        ArrayList<Proyecto> proyectos = conexion.searchAll(proyectoConverter, sql);
        conexion.close();

        return proyectos;
    }

    public static int getNumProyectos(Trabajador t) throws SQLException {
        String sql = "SELECT * FROM (SELECT P.nombre FROM Proyecto P WHERE P.estado <> 'finalizado') P, TrabajadoresProyecto TP WHERE TP.nombre = P.nombre AND TP.user='" + t.getUser() + "'";
        ConexionBD conexion = new ConexionBD();
        int count = conexion.count(sql);
        conexion.close();
        return count;
    }

    public static void cerrar(Proyecto p) throws SQLException {
        String sql = String.format("Update Proyecto Set estado = 'cerrado' where nombreProyecto = '%s'", p.getNombre());
        ConexionBD conexion = new ConexionBD();
        conexion.execute(sql);
        sql = String.format("Update Proyecto Set duracionReal = %d where nombreProyecto = '%s'", p.getDuracionReal(), p.getNombre());
        conexion.execute(sql);
        sql = String.format("Update Proyecto Set fechaFinReal = '%d-%d-%d' where nombreProyecto = '%s'", p.getFechaFinReal().getYear() + 1900, p.getFechaFinReal().getMonth() + 1, p.getFechaFinReal().getDate(), p.getNombre());
        conexion.execute(sql);
        conexion.close();
    }
}
