package Proyecto.Persistencia;

import Persistencia.ConexionBD;
import Persistencia.ObjectConverter;
import Proyecto.Dominio.Actividad;
import Proyecto.Dominio.Etapa;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author Juan Francisco Pascual Palacin
 */
public class EtapaPersistencia {

    private static final ObjectConverter<Etapa> etapaConverter = new ObjectConverter<Etapa>() {

        @Override
        public Etapa convert(ResultSet result) throws SQLException {
            Etapa etapa = new Etapa();
            etapa.setNombre(result.getString("nombreProyecto"));
            etapa.setNumero(result.getInt("numeroEtapa"));
            etapa.setFechaInicio(result.getDate("fechaInicio"));
            etapa.setFechaFin(result.getDate("fechaFin"));
            etapa.setFechaFinReal(result.getDate("fechaFinReal"));
            etapa.setDuracion(result.getInt("duracion"));
            etapa.setDuracionReal(result.getInt("duracionReal"));
            etapa.setEstado(result.getString("estado"));

            return etapa;
        }

        @Override
        public String createInsertQuery(Etapa object) {
            return "INSERT INTO Etapa VALUES ('" + object.getNombre() + "', "
                    + object.getNumero() + ", '" + object.getFechaInicio() + "', '"
                    + object.getFechaFin() + "', null, '" + object.getEstado() + "')";
        }
    };

    public static Etapa getEtapa(String nombreProyecto, int numeroEtapa) throws SQLException {
        String sql = "SELECT * FROM Etapa WHERE nombre = '" + nombreProyecto + "' AND numero = " + numeroEtapa;

        ConexionBD conexion = new ConexionBD();
        Etapa etapa = conexion.search(etapaConverter, sql);
        conexion.close();

        return etapa;
    }

    public static void guardarEtapa(Etapa etapa) throws SQLException {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (Exception e) {

        }
        Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost/PGP_grupo11", "PGP_grupo11", "P6AbQA8Z");
        Statement s = conexion.createStatement();
        System.out.println("insert into Etapa values('"+etapa.getNombre()+"', "+
                etapa.getNumero()+", '"+etapa.getFechaInicio()+"', '"+
                etapa.getFechaFin()+"', null, "+etapa.getDuracion()+", NULL, '"+etapa.getEstado()+"')");
        s.execute("insert into Etapa values('"+etapa.getNombre()+"', "+
                etapa.getNumero()+", '"+etapa.getFechaInicio()+"', '"+
                etapa.getFechaFin()+"', null, "+etapa.getDuracion()+", NULL, '"+etapa.getEstado()+"')");
        conexion.close();
    }

    public static ArrayList<Etapa> getEtapasProyecto(String nombreProyecto) throws SQLException {
        String sql = "SELECT * FROM Etapa WHERE nombreProyecto = '" + nombreProyecto + "'";
        ConexionBD conexion = new ConexionBD();
        ArrayList<Etapa> etapas = conexion.searchAll(etapaConverter, sql);
        conexion.close();
        return etapas;
    }

    public static void cerrar(Etapa et) throws SQLException {
        String sql = String.format("Update Etapa Set estado = 'finalizado' where nombreProyecto = '%s' and numeroEtapa = %d", et.getNombre(), et.getNumero());
        ConexionBD conexion = new ConexionBD();
        conexion.execute(sql);
        sql = String.format("Update Etapa Set duracionReal = %d where nombreProyecto = '%s' and numeroEtapa = %d", et.getDuracionReal(), et.getNombre(), et.getNumero());
        conexion.execute(sql);
        sql = String.format("Update Etapa Set fechaFinReal = '%d-%d-%d' where nombreProyecto = '%s' and numeroEtapa = %d", et.getFechaFinReal().getYear() + 1900, et.getFechaFinReal().getMonth() + 1, et.getFechaFinReal().getDate(), et.getNombre(), et.getNumero());
        conexion.execute(sql);
        conexion.close();
    }

    public ArrayList<Etapa> getEtapasAbiertasProyecto(String nombreProyecto) throws SQLException {
        String sql = "SELECT * FROM Etapa WHERE nombre = '" + nombreProyecto + "' AND estado <> 'cerrado'";

        ConexionBD conexion = new ConexionBD();
        ArrayList<Etapa> etapas = conexion.searchAll(etapaConverter, sql);
        conexion.close();

        return etapas;
    }

    public static int numeroEtapasAbiertas(String nombreProyecto) throws SQLException {
        String sql = "SELECT * FROM Etapa WHERE nombre = '" + nombreProyecto + "' AND estado <> 'cerrado'";

        ConexionBD conexion = new ConexionBD();
        int count = conexion.count(sql);
        conexion.close();

        return count;
    }

    public static void cerrarEtapa(String nombreProyecto, int numeroEtapas) throws SQLException {
        String sql = "UPDATE Etapa SET estado = 'cerrado' WHERE nombre = '" + nombreProyecto + "' AND numero = " + numeroEtapas;

        ConexionBD conexion = new ConexionBD();
        conexion.execute(sql);
        conexion.close();
    }
}
