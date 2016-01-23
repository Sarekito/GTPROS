package Proyecto.Persistencia;

import Persistencia.ConexionBD;
import Persistencia.ObjectConverter;
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

/**
 *
 * @author antonio
 */
public class PersistenciaProyecto {

    private static final ObjectConverter<Proyecto> proyectoConverter = new ObjectConverter<Proyecto>() {

        @Override
        public Proyecto convert(ResultSet result) throws SQLException {
            return new Proyecto(result.getString("nombre"), result.getDate("fechaInicio"), result.getDate("fechaFin"), result.getString("jefeProyecto"), result.getString("estado"));
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
        ArrayList<Proyecto> misProyectos = new ArrayList<>();
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (Exception e) {

        }
        Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost/PGP_grupo11", "PGP_grupo11", "P6AbQA8Z");
        Statement s = conexion.createStatement();
        ResultSet rs = s.executeQuery("Select P.* FROM (select * from TrabajadoresProyecto "
                + "TP where TP.user = '" + tr.getUser() + "')TP, Proyecto P where "
                + "P.nombre = TP.nombre and P.estado = 'realizando'");
        while (rs.next()) {
            misProyectos.add(new Proyecto(rs.getString(1), rs.getDate(2), rs.getDate(3), rs.getString(4), rs.getString(5)));
        }
        return misProyectos;

    }

    public static TrabajadoresProyecto getTrabajadorProyecto(String user, String nombre) throws SQLException {
        TrabajadoresProyecto tp;
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (Exception e) {

        }
        Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost/PGP_grupo11", "PGP_grupo11", "P6AbQA8Z");
        Statement s = conexion.createStatement();
        ResultSet rs = s.executeQuery("Select * from TrabajadoresProyecto TP where TP.user = '" + user + "' and TP.nombre = '" + nombre + "'");
        rs.next();
        tp = new TrabajadoresProyecto(rs.getString("nombre"), rs.getString("user"), rs.getInt("porcentaje"));
        return tp;
    }

    public static void guardarProyecto(Proyecto proyecto) throws SQLException {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (Exception e) {

        }
        Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost/PGP_grupo11", "PGP_grupo11", "P6AbQA8Z");
        Statement s = conexion.createStatement();
        System.out.println("update Proyecto P set P.fechaComienzo = '" + proyecto.getFechaInicio() + "' where P.nombre='" + proyecto.getNombre() + "'");
        s.execute("update Proyecto P set fechaInicio = '" + proyecto.getFechaInicio() + "' where P.nombre='" + proyecto.getNombre() + "'");
        s.execute("update Proyecto P set fechaFin = '" + proyecto.getFechaFin() + "' where P.nombre='" + proyecto.getNombre() + "'");
        //s.execute("update Proyecto P set estado = 'realizando' where P.nombre='"+proyecto.getNombre()+"'");
    }

    public static void guardarTrabajadores(TrabajadoresProyecto get) throws SQLException {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (Exception e) {

        }
        Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost/PGP_grupo11", "PGP_grupo11", "P6AbQA8Z");
        Statement s = conexion.createStatement();
        s.execute("insert into TrabajadoresProyecto values('" + get.getNombre() + "', '"
                + get.getUser() + "', " + get.getDedicacion() + ")");
    }

    public static void guardarAsignaciones(ActividadTrabajador get) throws SQLException {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (Exception e) {

        }
        Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost/PGP_grupo11", "PGP_grupo11", "P6AbQA8Z");
        Statement s = conexion.createStatement();
        s.execute("insert into ActividadTrabajador values('" + get.getNombreProyecto() + "', "
                + get.getNumeroEtapa() + ", " + get.getIdActividad() + ", '" + get.getNombreTrabajador() + "', " + get.getHoras() + ")");
    }

    public static ArrayList<Actividad> getMisActividadesActuales(String user) throws SQLException {
        ArrayList<Actividad> actuales = new ArrayList<>();
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (Exception e) {

        }
        Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost/PGP_grupo11", "PGP_grupo11", "P6AbQA8Z");
        Statement s = conexion.createStatement();
        ResultSet rs = s.executeQuery("Select A.nombre, A.numero, A.id, A.fechaComienzo, A.fechaFin"
                + " from ActividadTrabajador AP, Actividad A, Proyecto P where AP.nombreProyecto = A.nombre and AP.numeroEtapa = A.numero and AP.idActividad = A.id and P.nombre = A.nombre and P.estado = 'realizando' and AP.nombreTrabajador = '" + user + "'");
        while (rs.next()) {
            actuales.add(new Actividad(rs.getString(1), rs.getInt(2), rs.getInt(3), null, 0, null, rs.getDate(4), rs.getDate(5), null, null, null));
        }
        return actuales;
    }

    public static ArrayList<Proyecto> getCerrados() throws SQLException {
        ArrayList<Proyecto> actuales = new ArrayList<>();
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (Exception e) {

        }
        Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost/PGP_grupo11", "PGP_grupo11", "P6AbQA8Z");
        Statement s = conexion.createStatement();
        ResultSet rs = s.executeQuery("Select * from Proyecto where estado = 'finalizado'");
        while (rs.next()) {
            Proyecto p = new Proyecto(rs.getString(1), rs.getDate(2), rs.getDate(3), rs.getString(5), rs.getString(6));
            p.setFechaFinReal(rs.getDate(4));
            actuales.add(p);
        }
        return actuales;
    }

}
