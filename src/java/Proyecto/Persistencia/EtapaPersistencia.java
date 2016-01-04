package Proyecto.Persistencia;

import Persistencia.ConexionBD;
import Persistencia.ObjectConverter;
import Proyecto.Dominio.Etapa;
import java.sql.ResultSet;
import java.sql.SQLException;
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

            etapa.setNombre(result.getString("nombre"));
            etapa.setNumero(result.getInt("numero"));
            etapa.setFechaInicio(result.getDate("fechaInicio"));
            etapa.setFechaFin(result.getDate("fechaFin"));
            etapa.setFechaFinReal(result.getDate("fechaFinReal"));

            return etapa;
        }

        @Override
        public String createInsertQuery(Etapa object) {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }
    };

    public static Etapa getEtapa(String nombreProyecto, int numeroEtapa) throws SQLException {
        try {
            String sql = "SELECT * FROM Etapa WHERE nombre = '" + nombreProyecto + "' AND numero = " + numeroEtapa;

            ConexionBD conexion = new ConexionBD();
            Etapa etapa = conexion.search(etapaConverter, sql);
            conexion.close();

            return etapa;
        } catch (ClassNotFoundException ex) {
            throw new SQLException(ex);
        }
    }

    public ArrayList<Etapa> getEtapasProyecto(String nombreProyecto) throws SQLException {
        try {
            String sql = "SELECT * FROM Etapa WHERE nombre = '" + nombreProyecto + "'";

            ConexionBD conexion = new ConexionBD();
            ArrayList<Etapa> etapas = conexion.searchAll(etapaConverter, sql);
            conexion.close();

            return etapas;
        } catch (ClassNotFoundException ex) {
            throw new SQLException(ex);
        }
    }

    public ArrayList<Etapa> getEtapasAbiertasProyecto(String nombreProyecto) throws SQLException {
        try {
            String sql = "SELECT * FROM Etapa WHERE nombre = '" + nombreProyecto + "' AND estado <> 'cerrado'";

            ConexionBD conexion = new ConexionBD();
            ArrayList<Etapa> etapas = conexion.searchAll(etapaConverter, sql);
            conexion.close();

            return etapas;
        } catch (ClassNotFoundException ex) {
            throw new SQLException(ex);
        }
    }
    
    public static int numeroEtapasAbiertas(String nombreProyecto) throws SQLException {
        try {
            String sql = "SELECT * FROM Etapa WHERE nombre = '" + nombreProyecto + "' AND estado <> 'cerrado'";

            ConexionBD conexion = new ConexionBD();
            int count = conexion.count(sql);
            conexion.close();

            return count;
        } catch (ClassNotFoundException ex) {
            throw new SQLException(ex);
        }
    }

    public static void cerrarEtapa(String nombreProyecto, int numeroEtapas) throws SQLException {
        try {
            String sql = "UPDATE Etapa SET estado = 'cerrado' WHERE nombre = '" + nombreProyecto + "' AND numero = " + numeroEtapas;

            ConexionBD conexion = new ConexionBD();
            conexion.execute(sql);
            conexion.close();
        } catch (ClassNotFoundException ex) {
            throw new SQLException(ex);
        }
    }
}
