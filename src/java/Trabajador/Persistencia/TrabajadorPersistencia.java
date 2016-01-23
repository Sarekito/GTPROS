package Trabajador.Persistencia;

import Persistencia.ConexionBD;
import Persistencia.ObjectConverter;
import Trabajador.Dominio.Administrador;
import Trabajador.Dominio.Categoria;
import Trabajador.Dominio.Rol;
import Trabajador.Dominio.Trabajador;
import Trabajador.Dominio.Vacaciones;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;

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

    public static ArrayList<Vacaciones> getVacaciones(String user, int year) throws SQLException {
        ConexionBD conexion = new ConexionBD();

        Statement s = conexion.createStatement();
        ResultSet rs = s.executeQuery("select * from Vacaciones V where V.user='" + user + "' and V.ano = " + year);
        ArrayList<Vacaciones> vacaciones = new ArrayList<>();
        while (rs.next()) {
            vacaciones.add(new Vacaciones(rs.getString(1), rs.getInt(2), rs.getInt(3), rs.getDate(4), rs.getInt(5)));
        }
        conexion.close();

        return vacaciones;
    }

    public static void guardaVacaciones(String user, int periodo, int year, Date inicio, int semanas) throws SQLException {
        String sql = "INSERT INTO Vacaciones VALUES ('" + user + "'," + periodo + ", " + (int) (year + 1900) + ", '" + (int) (inicio.getYear() + 1900) + "-" + (int) (inicio.getMonth() + 1) + "-" + inicio.getDate() + "', " + semanas + ")";

        ConexionBD conexion = new ConexionBD();
        conexion.execute(sql);
        conexion.close();
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

    public static ArrayList<Vacaciones> dameVacaciones(String user) throws SQLException, ClassNotFoundException {
        String sql = "SELECT * FROM Vacaciones WHERE user = '" + user + "'";

        ArrayList<Vacaciones> vc = new ArrayList<>();
        ConexionBD conexion = new ConexionBD();
        Statement s = conexion.createStatement();

        ResultSet rs = s.executeQuery(sql);

        while (rs.next()) {
            vc.add(new Vacaciones(rs.getString(1), rs.getInt(2), rs.getInt(3), rs.getDate(4), rs.getInt(5)));
        }

        return vc;
    }

    public static ArrayList<Trabajador> getJefesSinProyecto() throws SQLException {
        String sql = "SELECT * FROM Trabajador T WHERE T.tipoCategoria = 1 AND NOT EXISTS(SELECT * FROM Proyecto P WHERE P.jefeProyecto = T.user AND P.estado <> 'cerrado')";
        
        ConexionBD conexion = new ConexionBD();
        ArrayList<Trabajador> trabajadores = conexion.searchAll(trabajadorConverter, sql);
        conexion.close();
        
        return trabajadores;
    }
}
