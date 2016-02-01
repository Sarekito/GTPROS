package Proyecto.Despliegue;

import Excepciones.DatabaseException;
import Excepciones.EtapaConActividadesAbiertasException;
import Excepciones.ProyectoConEtapasAbiertasException;
import Proyecto.Dominio.Actividad;
import Proyecto.Dominio.ActividadTrabajador;
import Proyecto.Dominio.Etapa;
import Proyecto.Dominio.InformeSeguimiento;
import Proyecto.Dominio.Proyecto;
import Proyecto.Dominio.Tarea;
import Proyecto.Dominio.TrabajadoresProyecto;
import Trabajador.Dominio.Trabajador;
import java.util.ArrayList;
import java.util.Date;
import javax.ejb.Local;

/**
 *
 * @author antonio
 */
@Local
public interface DespliegueProyectoLocal {

    public ArrayList<Proyecto> getMisProyectos(String jefe);

    public Proyecto getProyecto(String nombreProyecto);

    public void generarProyecto(String nombreProyecto, String jefe) throws DatabaseException;

    void cerrarProyecto(String nombreProyecto) throws ProyectoConEtapasAbiertasException;

    void cerrarEtapa(String nombreProyecto, int numero) throws EtapaConActividadesAbiertasException;

    public ArrayList<Proyecto> getMisProyectosActuales(Trabajador get);

    public TrabajadoresProyecto dameTrabajadorProyecto(String user, String nombre);

    java.util.ArrayList<InformeSeguimiento> getInformesProyecto(String nombreProyecto);

    void aprobarInforme(InformeSeguimiento informe);

    void rechazarInforme(InformeSeguimiento informe);

    java.util.ArrayList<InformeSeguimiento> getInformesPendientesProyecto(String nombreProyecto);

    java.util.ArrayList<InformeSeguimiento> getInformesNoEnviadosProyecto(String nombreProyecto);

    public void guardarProyecto(Proyecto proyecto);

    public void guardarEtapas(ArrayList<Etapa> etapas);

    public void guardarActividades(ArrayList<Actividad> actividades);

    public void guardarTrabajadores(ArrayList<TrabajadoresProyecto> tp);

    public void guardarAsignaciones(ArrayList<ActividadTrabajador> actividadTrabajador);

    public ArrayList<Actividad> misActividadesFecha(String user);

    public ArrayList<Actividad> getSobreesfuerzo(String user);

    public ArrayList<Proyecto> getProyectosCerrados();

    public ArrayList<Etapa> getEtapas(String nombre);

    public ArrayList<Actividad> getActividadesCerrados(String nombre, int numero);

    public ArrayList<Actividad> getActividadesAbiertasNoJefe(String nombre, int numero, String idTrabajador);

    public ArrayList<Actividad> misActividadesAbiertas(String user);

    public void guardarTareaIntroducida(String proyecto, String etapa, String actividad, String user, int numTarea, Date semana, String tipoTarea, int duracion);

    public void cerrarActividad(String proyecto, int etapa, int actividad);

    boolean tieneActividadesAbiertas(String nombreProyecto, int numeroEtapa);

    boolean tieneEtapasAbiertas(String nombreProyecto);

    public boolean isAsignado(Actividad get, String user);

    public ArrayList<Actividad> getActividadesEtapa(Etapa et);

    public ArrayList<Tarea> getInformesActividad(Actividad act);

    public void aprobarInforme(Tarea tar);

    public ArrayList<Tarea> getInformesActividadMios(Actividad act, Trabajador trabajador);

    public void guardaInforme(Tarea get, String get0);

    public int getDedicacion(Actividad act, Trabajador trabajador);

    public ArrayList<Tarea> getMisTareas(Actividad act);

    public boolean tieneAntecesoras(Actividad get);

    public void cierreActividad(Actividad act);

    public void cierreEtapa(Etapa et);

    public void cierreProyecto(Proyecto p);
}
