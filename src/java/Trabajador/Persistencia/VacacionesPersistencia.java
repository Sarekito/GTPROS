package Trabajador.Persistencia;

import Persistencia.ConexionBD;
import Persistencia.ObjectConverter;
import Trabajador.Dominio.Vacaciones;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author Francisco
 */
public class VacacionesPersistencia {

    private static final ObjectConverter<Vacaciones> converter = new ObjectConverter<Vacaciones>() {

        @Override
        public Vacaciones convert(ResultSet result) throws SQLException {
            return new Vacaciones(result.getString(1), result.getInt(2), result.getInt(3), result.getDate(4), result.getInt(5));
        }

        @Override
        public String createInsertQuery(Vacaciones object) {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }
    };

    public static ArrayList<Vacaciones> getVacaciones(String user, int year) throws SQLException {
        String sql = "SELECT * FROM Vacaciones V WHERE V.trabajador='" + user + "' AND V.ano = " + year;

        ConexionBD conexion = new ConexionBD();
        ArrayList<Vacaciones> vacaciones = conexion.searchAll(converter, sql);
        conexion.close();

        return vacaciones;
    }

    public static void guardaVacaciones(String user, int periodo, int year, Date inicio, int semanas) throws SQLException {
        String sql = "INSERT INTO Vacaciones VALUES ('" + user + "'," + periodo + ", " + (int) (year + 1900) + ", '" + (int) (inicio.getYear() + 1900) + "-" + (int) (inicio.getMonth() + 1) + "-" + inicio.getDate() + "', " + semanas + ")";

        ConexionBD conexion = new ConexionBD();
        conexion.execute(sql);
        conexion.close();
    }

    public static ArrayList<Vacaciones> dameVacaciones(String user) throws SQLException {
        String sql = "SELECT * FROM Vacaciones WHERE trabajador = '" + user + "'";

        ConexionBD conexion = new ConexionBD();
        ArrayList<Vacaciones> vacaciones = conexion.searchAll(converter, sql);
        conexion.close();

        return vacaciones;
    }
}
