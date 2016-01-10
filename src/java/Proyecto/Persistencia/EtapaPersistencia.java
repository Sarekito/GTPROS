package Proyecto.Persistencia;

import Persistencia.ConexionBD;
import Persistencia.ObjectConverter;
import Proyecto.Dominio.Etapa;
import Proyecto.Dominio.Proyecto;
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

    public static void guardarEtapa(Etapa etapa) throws SQLException {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (Exception e) {

        }
        Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost/PGP_grupo11", "PGP_grupo11", "P6AbQA8Z");
        Statement s = conexion.createStatement();
        System.out.println("insert into Etapa values('"+etapa.getNombre()+"', "+
                etapa.getNumero()+", '"+etapa.getFechaInicio()+"', '"+
                etapa.getFechaFin()+"', null, '"+etapa.getEstado()+"')");;
        s.execute("insert into Etapa values('"+etapa.getNombre()+"', "+
                etapa.getNumero()+", '"+etapa.getFechaInicio()+"', '"+
                etapa.getFechaFin()+"', null, '"+etapa.getEstado()+"')");
    }

    public static ArrayList<Etapa> getCerrados(String user) throws SQLException {
        ArrayList<Etapa> actuales = new ArrayList<>();
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (Exception e) {

        }
        Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost/PGP_grupo11", "PGP_grupo11", "P6AbQA8Z");
        Statement s = conexion.createStatement();
        ResultSet rs = s.executeQuery("Select * from Etapa where nombre = '"+user+"'");
        while (rs.next()) {
            actuales.add(new Etapa(rs.getString(1), rs.getInt(2), rs.getDate(3), rs.getDate(4), rs.getDate(5), rs.getString(6)));
        }
        return actuales;
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
