package Proyecto.Despliegue;

import Excepciones.EtapaConActividadesAbiertasException;
import Excepciones.ProyectoConEtapasAbiertasException;
import Proyecto.Dominio.Actividad;
import Proyecto.Dominio.ActividadTrabajador;
import Proyecto.Dominio.Etapa;
import Proyecto.Dominio.InformeSeguimiento;
import Proyecto.Dominio.Proyecto;
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

    public void generar(String nombreProyecto, String jefe);

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

    public void cerrarActividad(String proyecto, String etapa, String actividad);

    boolean tieneActividadesAbiertas(String nombreProyecto, int numeroEtapa);

    boolean tieneEtapasAbiertas(String nombreProyecto);

}
