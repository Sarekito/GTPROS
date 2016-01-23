package Proyecto.Persistencia;

import Persistencia.ConexionBD;
import Persistencia.ObjectConverter;
import Proyecto.Dominio.Actividad;
import Trabajador.Dominio.Rol;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

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
            actividad.setTipoRol(new Rol(result.getString("tipoRol")));

            return actividad;
        }

        @Override
        public String createInsertQuery(Actividad object) {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }
    };

    public static Actividad getActividad(String nombreProyecto, int numeroEtapa, int idActividad) throws SQLException {
        String sql = "SELECT * FROM Actividad WHERE nombre = '" + nombreProyecto + "' AND numero = " + numeroEtapa + " AND id = " + idActividad;

        ConexionBD conexion = new ConexionBD();
        Actividad actividad = conexion.search(actividadConverter, sql);
        conexion.close();

        return actividad;
    }

    public static int numeroActividadesAbiertas(String nombreProyecto, int numeroEtapa) throws SQLException {
        String sql = "SELECT * FROM Actividad WHERE nombre = '" + nombreProyecto + "' AND numero = " + numeroEtapa + " AND estado <> 'cerrado'";

        ConexionBD conexion = new ConexionBD();
        int count = conexion.count(sql);
        conexion.close();

        return count;
    }

    public static void guardaActividad(Actividad actividad) throws SQLException {
        ConexionBD conexion = new ConexionBD();
        Statement s = conexion.createStatement();
        s.execute("insert into Actividad values('" + actividad.getNombre() + "', "
                + actividad.getNumero() + ", " + actividad.getId() + ", '"
                + actividad.getDescripcion() + "', " + actividad.getDuracion() + ", null, '"
                + actividad.getFechaComienzo() + "', '" + actividad.getFechaFin()
                + "', null, '" + actividad.getEstado() + "', '" + actividad.getTipoRol().getRol() + "')");
        if (!actividad.getPredecesoras().isEmpty()) {
            for (int i = 0; i < actividad.getPredecesoras().size(); i++) {
                s.execute("insert into Antecesora values('" + actividad.getNombre() + "', "
                        //                        + actividad.getNumero() + ", " + actividad.getId() + ", '" + actividad.getPredecesoras().get(i).getNombre() + "', "
                        + actividad.getPredecesoras().get(i).getNumero() + ", " + actividad.getPredecesoras().get(i).getId() + ")");
            }
        }
        conexion.close();
    }

    public static ArrayList<Actividad> sobreesfuerzo(String user) throws SQLException {
        String sql = "SELECT A.* from  Actividad A, Proyecto P where P.nombre = A.nombre and P.estado = 'finalizado' and A.duracion<A.duracionReal";
        String sql2 = "Select A.* from  Actividad A, Proyecto P where P.nombre = A.nombre and P.estado = 'realizando' and A.duracion<A.duracionReal and P.jefeProyecto = '" + user + "'";

        ConexionBD conexion = new ConexionBD();
        ArrayList<Actividad> actividades = conexion.searchAll(actividadConverter, sql);
        ArrayList<Actividad> actividades2 = conexion.searchAll(actividadConverter, sql2);
        conexion.close();

        for (Actividad a : actividades2) {
            actividades.add(a);
        }

        return actividades;
    }

    public static ArrayList<Actividad> getCerrados(String nombre, int numero) throws SQLException {
        String sql = "SELECT * FROM Actividad WHERE nombre = '" + nombre + "' AND numero = " + numero;

        ConexionBD conexion = new ConexionBD();
        ArrayList<Actividad> actividades = conexion.searchAll(actividadConverter, sql);
        conexion.close();

        return actividades;
    }

    public static ArrayList<Actividad> getAbiertosNoJefe(String nombre, int numero, String idTrabajador) throws SQLException {
        String sql = "SELECT A.* FROM Actividad A, ActividadTrabajador AP WHERE A.nombre = '" + nombre + "' AND A.numero = " + numero + " AND AP.nombreProyecto=A.nombre AND AP.numeroEtapa = A.numero AND AP.idActividad = A.id AND AP.nombreTrabajador = '" + idTrabajador + "'";

        ConexionBD conexion = new ConexionBD();
        ArrayList<Actividad> actividades = conexion.searchAll(actividadConverter, sql);
        conexion.close();

        return actividades;
    }

    public static ArrayList<Actividad> actividadesAbiertasDe(String user) throws SQLException {
        String sql = "Select A.* from Actividad A, ActividadTrabajador AP where A.estado = 'realizando' and AP.nombreTrabajador = '" + user + "' and A.nombre = AP.nombreProyecto and A.numero = AP.numeroEtapa and A.id = AP.idActividad";

        ConexionBD conexion = new ConexionBD();
        ArrayList<Actividad> actividades = conexion.searchAll(actividadConverter, sql);
        conexion.close();

        return actividades;
    }

    public static void cerrarActividad(String proyecto, String etapa, String actividad) throws SQLException {
        String sql = "UPDATE Actividad SET estado = 'cerrado' WHERE nombre = '" + proyecto + "' AND numero = " + Integer.parseInt(etapa) + " AND id = " + Integer.parseInt(actividad);

        ConexionBD conexion = new ConexionBD();
        conexion.execute(sql);
        conexion.close();
    }

    public static ArrayList<Actividad> getMisActividadesActuales(String user) throws SQLException {
        String sql = "SELECT A.* FROM ActividadTrabajador AP, Actividad A, Proyecto P where AP.nombreProyecto = A.nombre and AP.numeroEtapa = A.numero and AP.idActividad = A.id and P.nombre = A.nombre and P.estado = 'realizando' and AP.nombreTrabajador = '" + user + "'";

        ConexionBD conexion = new ConexionBD();
        ArrayList<Actividad> actividades = conexion.searchAll(actividadConverter, sql);
        conexion.close();

        return actividades;
    }
}
