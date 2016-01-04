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
            return "Insert into Trabajador values('" + tr.getUser() + "', '" + tr.getPassword() + "', '" + tr.getTipoRol().getRol() + "', " + tr.getCategoria().getCategoria() + ")";
        }
    };

    public static Trabajador getTrabajador(String user) throws SQLException {
        try {
            ConexionBD conexion = new ConexionBD();
            Trabajador t = conexion.search(trabajadorConverter, "select * from Trabajador T where T.user='" + user + "'");
            conexion.close();

            return t;
        } catch (ClassNotFoundException ex) {
            throw new SQLException(ex);
        }
    }

    public static ArrayList<Vacaciones> getVacaciones(String user, int year) throws SQLException {
        try {
            ConexionBD conexion = new ConexionBD();

            Statement s = conexion.createStatement();
            ResultSet rs = s.executeQuery("select * from Vacaciones V where V.user='" + user + "' and V.ano = " + year);

            ArrayList<Vacaciones> vacaciones = new ArrayList<>();
            while (rs.next()) {
                vacaciones.add(new Vacaciones(rs.getString(1), rs.getInt(2), rs.getInt(3), rs.getDate(4), rs.getInt(5)));
            }
            conexion.close();

            return vacaciones;
        } catch (ClassNotFoundException ex) {
            throw new SQLException(ex);
        }
    }

    public static void guardaVacaciones(String user, int periodo, int year, Date inicio, int semanas) throws SQLException {
        try {
            ConexionBD conexion = new ConexionBD();
            conexion.execute("INSERT INTO Vacaciones VALUES ('" + user + "'," + periodo + ", " + (int) (year + 1900) + ", '" + (int) (inicio.getYear() + 1900) + "-" + (int) (inicio.getMonth() + 1) + "-" + inicio.getDate() + "', " + semanas + ")");
            conexion.close();
        } catch (ClassNotFoundException ex) {
            throw new SQLException(ex);
        }
    }

    public static Administrador getAdministrador(String user) throws SQLException {
        try {
            ConexionBD conexion = new ConexionBD();
            Statement s = conexion.createStatement();
            ResultSet rs = s.executeQuery("select * from Administrador T where T.user='" + user + "'");

            Administrador administrador = null;
            if (rs.next()) {
                administrador = new Administrador(rs.getString(1), rs.getString(2));
            }

            conexion.close();

            return administrador;
        } catch (ClassNotFoundException ex) {
            throw new SQLException(ex);
        }
    }

    public static void registrarTrabajador(Trabajador tr) throws SQLException {
        try {
            ConexionBD conexion = new ConexionBD();
            conexion.insert(trabajadorConverter, tr);
            conexion.close();
        } catch (ClassNotFoundException ex) {
            throw new SQLException(ex);
        }
    }

    public static ArrayList<Trabajador> getTrabajadores() throws SQLException {
        try {
            ConexionBD conexion = new ConexionBD();
            ArrayList<Trabajador> trabajadores = conexion.searchAll(trabajadorConverter, "select * from Trabajador T");
            conexion.close();

            return trabajadores;
        } catch (ClassNotFoundException ex) {
            throw new SQLException(ex);
        }
    }

    public static int getNumProyectos(Trabajador t) throws SQLException {
        try {
            ConexionBD conexion = new ConexionBD();
            int count = conexion.count("select * from (select P.nombre from Proyecto P where P.estado<>'finalizado') P, TrabajadoresProyecto TP where TP.nombre = P.nombre and TP.user='" + t.getUser() + "'");
            conexion.close();

            return count;
        } catch (ClassNotFoundException ex) {
            throw new SQLException(ex);
        }
    }
}
