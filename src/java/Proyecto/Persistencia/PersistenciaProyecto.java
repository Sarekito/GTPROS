package Proyecto.Persistencia;

import Persistencia.ConexionBD;
import Persistencia.ObjectConverter;
import Proyecto.Dominio.Proyecto;
import java.sql.ResultSet;
import java.sql.SQLException;
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
        try {
            String sql = "select * from Proyecto P where P.jefeProyecto='" + jefe + "' and P.estado <> 'cerrado'";

            ConexionBD conexion = new ConexionBD();
            System.out.println(sql);
            ArrayList<Proyecto> proyectos = conexion.searchAll(proyectoConverter, sql);
            conexion.close();

            return proyectos;
        } catch (ClassNotFoundException ex) {
            throw new SQLException(ex);
        }
    }
    
    public static ArrayList<Proyecto> getProyectosActuales(String usuario) throws SQLException {
        try {
            String sql = "SELECT P.* FROM Proyecto P, TrabajadoresProyecto T WHERE P.nombre = T.nombre AND T.user = '" + usuario + "'";
            
            ConexionBD conexion = new ConexionBD();
            ArrayList<Proyecto> proyectos = conexion.searchAll(proyectoConverter, sql);
            conexion.close();
            
            return proyectos;
        } catch (ClassNotFoundException ex) {
            throw new SQLException(ex);
        }
    }

    public static Proyecto getProyecto(String nombreProyecto) throws SQLException {
        try {
            ConexionBD conexion = new ConexionBD();
            Proyecto proyecto = conexion.search(proyectoConverter, "select * from Proyecto P where P.nombre ='" + nombreProyecto + "'");
            conexion.close();

            return proyecto;
        } catch (ClassNotFoundException ex) {
            throw new SQLException(ex);
        }
    }

    public static ArrayList<Proyecto> getProyectosFinalizados() throws SQLException {
        try {
            String sql = "SELECT * FROM Proyecto WHERE estado = 'cerrado'";

            ConexionBD conexion = new ConexionBD();

            ArrayList<Proyecto> proyectos = conexion.searchAll(proyectoConverter, sql);

            conexion.close();

            return proyectos;
        } catch (ClassNotFoundException ex) {
            throw new SQLException(ex);
        }
    }

    public static void generar(String nombreProyecto, String jefe) throws SQLException {
        try {
            ConexionBD conexion = new ConexionBD();

            conexion.execute("insert into Proyecto(nombre, jefeProyecto, estado) values ('" + nombreProyecto + "', '" + jefe + "', 'pendiente')");

            conexion.close();
        } catch (ClassNotFoundException ex) {
            throw new SQLException(ex);
        }
    }

    public static void cerrarProyecto(String nombreProyecto) throws SQLException {
        try {
            String sql = "UPDATE Proyecto SET estado = 'cerrado' WHERE nombre = '" + nombreProyecto + "'";
            ConexionBD conexion = new ConexionBD();
            conexion.execute(sql);
            conexion.close();
        } catch (ClassNotFoundException ex) {
            throw new SQLException(ex);
        }
    }
}
