package Proyecto.Persistencia;

import Persistencia.ConexionBD;
import Persistencia.ObjectConverter;
import Proyecto.Dominio.EstadoInforme;
import Proyecto.Dominio.InformeSeguimiento;
import java.sql.ResultSet;
import java.sql.SQLDataException;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Juan Francisco Pascual Palacin
 */
public class InformeSeguimientoPersistencia {

    private static final ObjectConverter<InformeSeguimiento> informeSeguimientoConverter = new ObjectConverter<InformeSeguimiento>() {

        @Override
        public InformeSeguimiento convert(ResultSet result) throws SQLException {
            InformeSeguimiento informe = new InformeSeguimiento();

            informe.setNombreProyecto(result.getString("nombreProyecto"));
            informe.setNumeroEtapa(result.getInt("numeroEtapa"));
            informe.setIdActividad(result.getInt("idActividad"));
            informe.setTrabajador(result.getString("trabajador"));
            informe.setNumTarea(result.getInt("numTarea"));
            informe.setEstado(EstadoInforme.get("estado"));

            return informe;
        }

        @Override
        public String createInsertQuery(InformeSeguimiento object) {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }
    };

    public static ArrayList<InformeSeguimiento> getInformesProyecto(String nombreProyecto) throws SQLException {
        try {
            String sql = "SELECT * FROM InformeSeguimiento WHERE nombreProyecto = '" + nombreProyecto + "'";

            ConexionBD conexion = new ConexionBD();
            ArrayList<InformeSeguimiento> informes = conexion.searchAll(informeSeguimientoConverter, sql);
            conexion.close();

            return informes;
        } catch (ClassNotFoundException ex) {
            throw new SQLException(ex);
        }
    }

    public static ArrayList<InformeSeguimiento> getInformesPendientesProyecto(String nombreProyecto) throws SQLException {
        try {
            String sql = "SELECT * FROM InformeSeguimiento WHERE nombreProyecto = '" + nombreProyecto + "' AND estado = 'pendiente'";

            ConexionBD conexion = new ConexionBD();
            ArrayList<InformeSeguimiento> informes = conexion.searchAll(informeSeguimientoConverter, sql);
            conexion.close();

            return informes;
        } catch (ClassNotFoundException ex) {
            throw new SQLException(ex);
        }
    }

    public static void aprobarInforme(InformeSeguimiento informe) throws SQLException {
        try {
            String sql = "UPDATE InformeSeguimiento SET estado = 'aprobado' WHERE nombreProyecto = '" + informe.getNombreProyecto() + "' AND numeroEtapa = " + informe.getNumeroEtapa() + " AND idActividad = " + informe.getIdActividad() + " AND trabajador = '" + informe.getTrabajador() + "' AND numTarea = " + informe.getNumTarea() + " AND semana = '" + informe.getSemana() + "'";

            ConexionBD conexion = new ConexionBD();

            conexion.execute(sql);

            conexion.close();
        } catch (ClassNotFoundException ex) {
            throw new SQLDataException(ex);
        }
    }

    public static void rechazarInforme(InformeSeguimiento informe) throws SQLException {
        try {
            String sql = "UPDATE InformeSeguimiento SET estado = 'rechazado' WHERE nombreProyecto = '" + informe.getNombreProyecto() + "' AND numeroEtapa = " + informe.getNumeroEtapa() + " AND idActividad = " + informe.getIdActividad() + " AND trabajador = '" + informe.getTrabajador() + "' AND numTarea = " + informe.getNumTarea() + " AND semana = '" + informe.getSemana() + "'";

            ConexionBD conexion = new ConexionBD();

            conexion.execute(sql);

            conexion.close();
        } catch (ClassNotFoundException ex) {
            throw new SQLDataException(ex);
        }
    }
}
