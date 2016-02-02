package Trabajador.Persistencia;

import Persistencia.ConexionBD;
import Persistencia.ObjectConverter;
import Trabajador.Dominio.Categoria;
import Trabajador.Dominio.Trabajador;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author antonio
 */
public class TrabajadorPersistencia {

    private static final ObjectConverter<Trabajador> trabajadorConverter = new ObjectConverter<Trabajador>() {

        @Override
        public Trabajador convert(ResultSet result) throws SQLException {
            return new Trabajador(result.getString(1), result.getString(2), Categoria.get(result.getInt(3)));
        }

        @Override
        public String createInsertQuery(Trabajador tr) {
            return "INSERT INTO Trabajador VALUES ('" + tr.getUser() + "', '" + tr.getPassword() + "', NULL, " + tr.getCategoria().getCategoria() + ")";
        }
    };

    public static Trabajador getTrabajador(String user) throws SQLException {
        String sql = "SELECT * FROM Trabajador T WHERE T.user = '" + user + "'";
        ConexionBD conexion = new ConexionBD();
        Trabajador t = conexion.search(trabajadorConverter, sql);
        conexion.close();

        return t;
    }

    public static void registrarTrabajador(Trabajador tr) throws SQLException {
        ConexionBD conexion = new ConexionBD();
        conexion.insert(trabajadorConverter, tr);
        conexion.close();
    }

    public static ArrayList<Trabajador> getTrabajadores(String jefe) throws SQLException {
        String sql = "SELECT * FROM Trabajador T WHERE T.user <> '" + jefe + "' and tipoCategoria <>10";
        ConexionBD conexion = new ConexionBD();
        ArrayList<Trabajador> trabajadores = conexion.searchAll(trabajadorConverter, sql);
        conexion.close();

        return trabajadores;
    }

    public static ArrayList<Trabajador> getJefesSinProyecto() throws SQLException {
        String sql = "SELECT * FROM Trabajador T WHERE T.tipoCategoria = 1 AND NOT EXISTS(SELECT * FROM Proyecto P WHERE P.jefeProyecto = T.user AND P.estado <> 'cerrado')";
        ConexionBD conexion = new ConexionBD();
        ArrayList<Trabajador> trabajadores = conexion.searchAll(trabajadorConverter, sql);
        conexion.close();

        return trabajadores;
    }

    public static ArrayList<Trabajador> getJefes() throws SQLException {
        String sql = "SELECT * FROM Trabajador T WHERE T.tipoCategoria = 1";
        ConexionBD conexion = new ConexionBD();
        ArrayList<Trabajador> trabajadores = conexion.searchAll(trabajadorConverter, sql);
        conexion.close();
        return trabajadores;
    }
}