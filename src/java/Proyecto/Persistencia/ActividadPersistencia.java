package Proyecto.Persistencia;

import Persistencia.ConexionBD;
import Persistencia.ObjectConverter;
import Proyecto.Dominio.Actividad;
import Trabajador.Dominio.RolEnum;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Francisco
 */
public class ActividadPersistencia {

    private static final ObjectConverter<Actividad> actividadConverter = new ObjectConverter<Actividad>() {

        @Override
        public Actividad convert(ResultSet result) throws SQLException {
            Actividad actividad = new Actividad();

            actividad.setNombre(result.getString("nombre"));
            actividad.setNumero(result.getInt("numero"));
            actividad.setId(result.getInt("id"));
            actividad.setDescripcion(result.getString("descripcion"));
            actividad.setDuracion(result.getInt("duracion"));

            int duracionReal = result.getInt("duracionReal");
            if (result.wasNull()) {
                actividad.setDuracionReal(null);
            } else {
                actividad.setDuracionReal(duracionReal);
            }
            actividad.setFechaComienzo(result.getDate("fechaComienzo"));
            actividad.setFechaFin(result.getDate("fechaFin"));
            actividad.setFechaFinReal(result.getDate("fechaFinReal"));
            actividad.setEstado(result.getString("estado"));
            actividad.setTipoRol(RolEnum.get(result.getString("tipoRol")));

            return actividad;
        }

        @Override
        public String createInsertQuery(Actividad object) {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }
    };

    public static Actividad getActividad(String nombreProyecto, int numeroEtapa, int idActividad) throws SQLException {
        try {
            String sql = "SELECT * FROM Actividad WHERE nombre = '" + nombreProyecto + "' AND numero = " + numeroEtapa + " AND id = " + idActividad;

            ConexionBD conexion = new ConexionBD();

            Actividad actividad = conexion.search(actividadConverter, sql);

            conexion.close();

            return actividad;
        } catch (ClassNotFoundException ex) {
            throw new SQLException(ex);
        }
    }

    public static int numeroActividadesAbiertas(String nombreProyecto, int numeroEtapa) throws SQLException {
        try {
            String sql = "SELECT * FROM Actividad WHERE nombre = '" + nombreProyecto + "' AND numero = " + numeroEtapa + " AND estado <> 'cerrado'";

            ConexionBD conexion = new ConexionBD();
            int count = conexion.count(sql);
            conexion.close();

            return count;
        } catch (ClassNotFoundException ex) {
            throw new SQLException(ex);
        }
    }
}
