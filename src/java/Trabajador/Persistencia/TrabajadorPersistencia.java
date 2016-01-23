package Trabajador.Persistencia;

import Persistencia.ConexionBD;
import Persistencia.ObjectConverter;
import Trabajador.Dominio.Administrador;
import Trabajador.Dominio.Categoria;
import Trabajador.Dominio.Rol;
import Trabajador.Dominio.Trabajador;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author antonio
 */
public class TrabajadorPersistencia {

    private static final ObjectConverter<Trabajador> trabajadorConverter = new ObjectConverter<Trabajador>() {

        @Override
        public Trabajador convert(ResultSet result) throws SQLException {
            return new Trabajador(result.getString(1), result.getString(2), new Rol(result.getString(3)), new Categoria(result.getInt(4)));
        }

        @Override
        public String createInsertQuery(Trabajador tr) {
            return "INSERT INTO Trabajador VALUES ('" + tr.getUser() + "', '" + tr.getPassword() + "', '" + tr.getTipoRol().getRol() + "', " + tr.getCategoria().getCategoria() + ")";
        }
    };

    public static Trabajador getTrabajador(String user) throws SQLException {
        String sql = "SELECT * FROM Trabajador T WHERE T.user = '" + user + "'";

        ConexionBD conexion = new ConexionBD();
        Trabajador t = conexion.search(trabajadorConverter, sql);
        conexion.close();

        return t;
    }

    public static Administrador getAdministrador(String user) throws SQLException {
        String sql = "SELECT * FROM Administrador T WHERE T.user = '" + user + "'";

        ConexionBD conexion = new ConexionBD();
        Statement s = conexion.createStatement();
        ResultSet rs = s.executeQuery(sql);

        Administrador administrador = null;
        if (rs.next()) {
            administrador = new Administrador(rs.getString(1), rs.getString(2));
        }

        conexion.close();

        return administrador;
    }

    public static void registrarTrabajador(Trabajador tr) throws SQLException {
        ConexionBD conexion = new ConexionBD();
        conexion.insert(trabajadorConverter, tr);
        conexion.close();
    }

    public static ArrayList<Trabajador> getTrabajadores(String jefe) throws SQLException {
        String sql = "SELECT * FROM Trabajador T WHERE T.user <> '" + jefe + "'";

        ConexionBD conexion = new ConexionBD();
        ArrayList<Trabajador> trabajadores = conexion.searchAll(trabajadorConverter, sql);
        conexion.close();

        return trabajadores;
    }

    public static int getNumProyectos(Trabajador t) throws SQLException {
        String sql = "SELECT * FROM (SELECT P.nombre FROM Proyecto P WHERE P.estado <> 'finalizado') P, TrabajadoresProyecto TP WHERE TP.nombre = P.nombre AND TP.user='" + t.getUser() + "'";

        ConexionBD conexion = new ConexionBD();
        int count = conexion.count(sql);
        conexion.close();

        return count;
    }

    public static ArrayList<Trabajador> getJefesSinProyecto() throws SQLException {
        String sql = "SELECT * FROM Trabajador T WHERE T.tipoCategoria = 1 AND NOT EXISTS(SELECT * FROM Proyecto P WHERE P.jefeProyecto = T.user AND P.estado <> 'cerrado')";

        ConexionBD conexion = new ConexionBD();
        ArrayList<Trabajador> trabajadores = conexion.searchAll(trabajadorConverter, sql);
        conexion.close();

        return trabajadores;
    }
}
