package Proyecto.Persistencia;

import Persistencia.ConexionBD;
import Persistencia.ObjectConverter;
import Proyecto.Dominio.Actividad;
import Proyecto.Dominio.Etapa;
import Proyecto.Dominio.Tarea;
import Proyecto.Dominio.TipoTarea;
import Trabajador.Dominio.Rol;
import Trabajador.Dominio.Trabajador;
import java.sql.Connection;
import java.sql.DriverManager;
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

            actividad.setNombre(result.getString("nombreProyecto"));
            actividad.setNumero(result.getInt("numeroEtapa"));
            actividad.setId(result.getInt("idActividad"));
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
            actividad.setTipoRol(Rol.get(result.getString("tipoRol")));

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
                + actividad.getDescripcion() + "', " + actividad.getDuracion() + ", NULL, '"
                + actividad.getFechaComienzo() + "', '" + actividad.getFechaFin()
                + "', NULL, '" + actividad.getEstado() + "', '" + actividad.getTipoRol().name() + "')");
        if (!actividad.getPredecesoras().isEmpty()) {
            for (int i = 0; i < actividad.getPredecesoras().size(); i++) {
                s.execute("INSERT INTO Antecesora values ('"+actividad.getNombre()+
                        "', "+actividad.getNumero()+", "+actividad.getId()+", '"
                        +actividad.getPredecesoras().get(i).getNombre()+"', "+
                        actividad.getPredecesoras().get(i).getNumero()+", "+
                        actividad.getPredecesoras().get(i).getId());
            }
        }
        conexion.close();
    }
    
    public static void cerrar(Actividad act) throws SQLException {
        String sql = String.format("Update Actividad Set estado = 'finalizado' where nombreProyecto = '%s' and numeroEtapa = %d and idActividad = %d", act.getNombre(), act.getNumero(), act.getId());
        ConexionBD conexion = new ConexionBD();
        conexion.execute(sql);
        sql = String.format("Update Actividad Set duracionReal = %d where nombreProyecto = '%s' and numeroEtapa = %d and idActividad = %d", act.getDuracionReal(), act.getNombre(), act.getNumero(), act.getId());
        conexion.execute(sql);
        sql = String.format("Update Actividad Set fechaFinReal = '%d-%d-%d' where nombreProyecto = '%s' and numeroEtapa = %d and idActividad = %d", act.getFechaFinReal().getYear()+1900, act.getFechaFinReal().getMonth()+1, act.getFechaFinReal().getDate(), act.getNombre(), act.getNumero(), act.getId());
        conexion.execute(sql);
        conexion.close();
    }
    
    public static ArrayList<Actividad> sobreesfuerzo(String user) throws SQLException {
        String sql = "SELECT A.* from  Actividad A, Proyecto P where P.nombreProyecto = A.nombreProyecto and P.estado = 'cerrado' and A.duracion<A.duracionReal";
        String sql2 = "Select A.* from  Actividad A, Proyecto P where P.nombreProyecto = A.nombreProyecto and P.estado = 'realizando' and A.duracion<A.duracionReal and P.jefeProyecto = '" + user + "'";

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
        String sql = "SELECT * FROM Actividad WHERE nombreProyecto = '" + nombre + "' AND numeroEtapa = " + numero;

        ConexionBD conexion = new ConexionBD();
        ArrayList<Actividad> actividades = conexion.searchAll(actividadConverter, sql);
        conexion.close();

        return actividades;
    }

    public static ArrayList<Actividad> getAbiertosNoJefe(String nombre, int numero, String idTrabajador) throws SQLException {
        String sql = "SELECT A.* FROM Actividad A, ActividadTrabajador AP WHERE A.nombreProyecto = '" + nombre + "' AND A.numeroEtapa = " + numero + " AND AP.nombreProyecto=A.nombre AND AP.numeroEtapa = A.numeroEtapa AND AP.idActividad = A.idActividad AND AP.nombreTrabajador = '" + idTrabajador + "'";
        ConexionBD conexion = new ConexionBD();
        ArrayList<Actividad> actividades = conexion.searchAll(actividadConverter, sql);
        conexion.close();

        return actividades;
    }

    public static ArrayList<Actividad> actividadesAbiertasDe(String user) throws SQLException {
        String sql = "Select A.* from Actividad A, ActividadTrabajador AP where A.estado = 'realizando' and AP.nombreTrabajador = '" + user + "' and A.nombreProyecto = AP.nombreProyecto and A.numeroEtapa = AP.numeroEtapa and A.idActividad = AP.idActividad";
        ConexionBD conexion = new ConexionBD();
        ArrayList<Actividad> actividades = conexion.searchAll(actividadConverter, sql);
        conexion.close();

        return actividades;
    }

    public static void cerrarActividad(String proyecto, int etapa, int actividad) throws SQLException {
        String sql = "UPDATE Actividad SET estado = 'cerrado' WHERE nombre = '" + proyecto + "' AND numero = " + etapa + " AND id = " + actividad;

        ConexionBD conexion = new ConexionBD();
        conexion.execute(sql);
        conexion.close();
    }

    public static ArrayList<Actividad> getMisActividadesActuales(String user) throws SQLException {
       String sql = "SELECT A.* FROM ActividadTrabajador AP, Actividad A, Proyecto P where AP.nombreProyecto = A.nombreProyecto and AP.numeroEtapa = A.numeroEtapa and AP.idActividad = A.idActividad and A.estado <> 'finalizado' and P.estado = 'realizando' and AP.nombreTrabajador = '" + user + "'";
        ConexionBD conexion = new ConexionBD();
        ArrayList<Actividad> actividades = conexion.searchAll(actividadConverter, sql);
        conexion.close();
        return actividades;
    }

    public static boolean isAsignado(Actividad get, String user) throws SQLException {
        String sql = String.format("Select * from ActividadTrabajador where nombreProyecto = '%s' and numeroEtapa = %d and idActividad = %d and nombreTrabajador = '%s'", get.getNombre(), get.getNumero(), get.getId(), user);
        System.out.println(sql);
        ConexionBD conexion = new ConexionBD();
        return conexion.existe(sql);
    }

    public static ArrayList<Actividad> getActividadesEtapa(Etapa et) throws SQLException {
        String sql = "SELECT * FROM Actividad  WHERE nombreProyecto = '" + et.getNombre() + "' and numeroEtapa = " + et.getNumero();
        ConexionBD conexion = new ConexionBD();
        ArrayList<Actividad> actividades = conexion.searchAll(actividadConverter, sql);
        conexion.close();
        return actividades;
    }

    public static int getDedicacion(Actividad act, Trabajador trabajador) throws SQLException, ClassNotFoundException {
        String sql = "select horas from ActividadTrabajador where nombreProyecto = '" + act.getNombre() + "' and numeroEtapa = " + act.getNumero() + " and idActividad = " + act.getId() + " and nombreTrabajador = '" + trabajador.getUser() + "'";
        String DATABASE_DRIVER = "com.mysql.jdbc.Driver";
        String DATABASE_URL = "jdbc:mysql://localhost:3306/PGP_grupo11?zeroDateTimeBehavior=convertToNull";
        String DATABASE_USER = "PGP_grupo11";
        String DATABASE_PASSWORD = "P6AbQA8Z";
        Class.forName(DATABASE_DRIVER);
        Connection conexion = DriverManager.getConnection(DATABASE_URL, DATABASE_USER, DATABASE_PASSWORD);
        Statement s = conexion.createStatement();
        ResultSet rs = s.executeQuery(sql);
        rs.next();
        return rs.getInt("horas");
    }

    public static ArrayList<Tarea> getTareaActividad(Actividad act) throws ClassNotFoundException, SQLException {
        ArrayList<Tarea> ta = new ArrayList<>();
        String sql = "SELECT * FROM Tarea  WHERE nombreProyecto = '" + act.getNombre() + "' and numeroEtapa = " + act.getNumero() + " and idActividad = " + act.getId();
        String DATABASE_DRIVER = "com.mysql.jdbc.Driver";
        String DATABASE_URL = "jdbc:mysql://localhost:3306/PGP_grupo11?zeroDateTimeBehavior=convertToNull";
        String DATABASE_USER = "PGP_grupo11";
        String DATABASE_PASSWORD = "P6AbQA8Z";
        Class.forName(DATABASE_DRIVER);
        Connection conexion = DriverManager.getConnection(DATABASE_URL, DATABASE_USER, DATABASE_PASSWORD);
        Statement s = conexion.createStatement();
        ResultSet rs = s.executeQuery(sql);
        while (rs.next()) {
            ta.add(new Tarea(rs.getString("nombreProyecto"), rs.getInt("numeroEtapa"), rs.getInt("idActividad"), rs.getString("trabajador"), rs.getDate("semana"), TipoTarea.get(rs.getString("tipoTareA")), rs.getInt("duracion"), rs.getString("estado")));
        }
        return ta;
    }

    public static ArrayList<Actividad> getPredecesoras(Actividad get) throws SQLException {
        String sql = "SELECT * FROM Actividad A, Antecesora P  WHERE A.nombreProyecto = P.proyecto1 and A.numeroEtapa = P.idEtapa1 and A.idActividad = P.idActividad1 and A.estado = 'realizando' and P.proyecto2 = '" + get.getNombre() + "' and P.idEtapa2 = " + get.getNumero() + " and P.idActividad2 = " + get.getId();
        ConexionBD conexion = new ConexionBD();
        ArrayList<Actividad> actividades = conexion.searchAll(actividadConverter, sql);
        conexion.close();
        return actividades;
    }
}
