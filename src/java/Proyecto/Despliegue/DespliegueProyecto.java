package Proyecto.Despliegue;

import Excepciones.EtapaConActividadesAbiertasException;
import Excepciones.ProyectoConEtapasAbiertasException;
import Proyecto.Dominio.Actividad;
import Proyecto.Dominio.ActividadTrabajador;
import Proyecto.Dominio.Etapa;
import Proyecto.Dominio.InformeSeguimiento;
import Proyecto.Dominio.Proyecto;
import Proyecto.Dominio.Tarea;
import Proyecto.Dominio.TrabajadoresProyecto;
import Proyecto.Persistencia.TareaPersistencia;
import Proyecto.Persistencia.ActividadPersistencia;
import Proyecto.Persistencia.EtapaPersistencia;
import Proyecto.Persistencia.InformeSeguimientoPersistencia;
import Proyecto.Persistencia.ProyectoPersistencia;
import Trabajador.Dominio.Trabajador;
import java.util.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;

/**
 *
 * @author antonio
 */
@Stateless
public class DespliegueProyecto implements DespliegueProyectoLocal {

    @Override
    public ArrayList<Proyecto> getMisProyectos(String jefe) {
        ArrayList<Proyecto> proyectos = new ArrayList<>();
        try {
            proyectos = ProyectoPersistencia.getMisProyectos(jefe);
        } catch (SQLException ex) {
            Logger.getLogger(DespliegueProyecto.class.getName()).log(Level.SEVERE, null, ex);
        }
        return proyectos;
    }
    
    @Override
    public Proyecto getProyectoPlanificar(String user) {
        try {
            return ProyectoPersistencia.getPlanificar(user);
        } catch (SQLException ex) {
            Logger.getLogger(DespliegueProyecto.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public ArrayList<Proyecto> getMisProyectosActuales(Trabajador tr) {
        ArrayList<Proyecto> misProyectos = null;

        try {
            misProyectos = ProyectoPersistencia.getMisProyectosActuales(tr);
        } catch (SQLException ex) {
            Logger.getLogger(DespliegueProyecto.class.getName()).log(Level.SEVERE, null, ex);
        }

        return misProyectos;
    }

    @Override
    public Proyecto getProyecto(String nombreProyecto) {
        Proyecto p = null;
        try {
            p = ProyectoPersistencia.getProyecto(nombreProyecto);
        } catch (SQLException ex) {
            Logger.getLogger(DespliegueProyecto.class.getName()).log(Level.SEVERE, null, ex);
        }
        return p;
    }

    @Override
    public void generar(String nombreProyecto, String jefe) {
        try {
            ProyectoPersistencia.generar(nombreProyecto, jefe);
        } catch (SQLException ex) {
            Logger.getLogger(DespliegueProyecto.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void cerrarProyecto(String nombreProyecto) throws ProyectoConEtapasAbiertasException {
        try {
            if (tieneEtapasAbiertas(nombreProyecto)) {
                throw new ProyectoConEtapasAbiertasException();
            }

            ProyectoPersistencia.cerrarProyecto(nombreProyecto);
        } catch (SQLException ex) {
            Logger.getLogger(DespliegueProyecto.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void cerrarEtapa(String nombreProyecto, int numero) throws EtapaConActividadesAbiertasException {
        try {
            if (tieneActividadesAbiertas(nombreProyecto, numero)) {
                throw new EtapaConActividadesAbiertasException();
            }

            EtapaPersistencia.cerrarEtapa(nombreProyecto, numero);
        } catch (SQLException ex) {
            Logger.getLogger(DespliegueProyecto.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public TrabajadoresProyecto dameTrabajadorProyecto(String user, String nombre) {
        TrabajadoresProyecto tp = null;
        try {
            tp = ProyectoPersistencia.getTrabajadorProyecto(user, nombre);
        } catch (SQLException ex) {
            Logger.getLogger(DespliegueProyecto.class.getName()).log(Level.SEVERE, null, ex);
        }
        return tp;
    }

    @Override
    public ArrayList<InformeSeguimiento> getInformesProyecto(String nombreProyecto) {
        try {
            return InformeSeguimientoPersistencia.getInformesProyecto(nombreProyecto);
        } catch (SQLException ex) {
            Logger.getLogger(DespliegueProyecto.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    @Override
    public void aprobarInforme(InformeSeguimiento informe) {
        try {
            InformeSeguimientoPersistencia.aprobarInforme(informe);
        } catch (SQLException ex) {
            Logger.getLogger(DespliegueProyecto.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void rechazarInforme(InformeSeguimiento informe) {
        try {
            InformeSeguimientoPersistencia.rechazarInforme(informe);
        } catch (SQLException ex) {
            Logger.getLogger(DespliegueProyecto.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public ArrayList<InformeSeguimiento> getInformesPendientesProyecto(String nombreProyecto) {
        try {
            return InformeSeguimientoPersistencia.getInformesPendientesProyecto(nombreProyecto);
        } catch (SQLException ex) {
            Logger.getLogger(DespliegueProyecto.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    @Override
    public java.util.ArrayList<InformeSeguimiento> getInformesNoEnviadosProyecto(String nombreProyecto) {
        try {
            return InformeSeguimientoPersistencia.getInformesNoEnviados(nombreProyecto);
        } catch (SQLException ex) {
            Logger.getLogger(DespliegueProyecto.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    @Override
    public void guardarProyecto(Proyecto proyecto) {
        try {
            ProyectoPersistencia.guardarProyecto(proyecto);
        } catch (SQLException ex) {
            Logger.getLogger(DespliegueProyecto.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void guardarEtapas(ArrayList<Etapa> etapas) {
        etapas.get(0).setEstado("realizando");
        for (int i = 0; i < etapas.size(); i++) {
            try {
                EtapaPersistencia.guardarEtapa(etapas.get(i));
            } catch (SQLException ex) {
                Logger.getLogger(DespliegueProyecto.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @Override
    public void guardarActividades(ArrayList<Actividad> actividades) {

        Date inicio = actividades.get(0).getFechaComienzo();
        for (int i = 0; i < actividades.size(); i++) {
            if (actividades.get(i).getFechaComienzo() == inicio) {
                actividades.get(i).setEstado("realizando");
            }
            try {
                ActividadPersistencia.guardaActividad(actividades.get(i));
            } catch (SQLException ex) {
                Logger.getLogger(DespliegueProyecto.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @Override
    public void guardarTrabajadores(ArrayList<TrabajadoresProyecto> tp) {
        for (int i = 0; i < tp.size(); i++) {
            try {
                ProyectoPersistencia.guardarTrabajadores(tp.get(i));
            } catch (SQLException ex) {
                Logger.getLogger(DespliegueProyecto.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @Override
    public void guardarAsignaciones(ArrayList<ActividadTrabajador> actividadTrabajador) {
        for (int i = 0; i < actividadTrabajador.size(); i++) {
            try {
                ProyectoPersistencia.guardarAsignaciones(actividadTrabajador.get(i));
            } catch (SQLException ex) {
                Logger.getLogger(DespliegueProyecto.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @Override
    public ArrayList<Actividad> misActividadesFecha(String user) {
        ArrayList<Actividad> at = null;
        try {
            at = ActividadPersistencia.getMisActividadesActuales(user);
        } catch (SQLException ex) {
            Logger.getLogger(DespliegueProyecto.class.getName()).log(Level.SEVERE, null, ex);
        }
        return at;
    }

    @Override
    public ArrayList<Actividad> getSobreesfuerzo(String user) {
        ArrayList<Actividad> at = null;
        try {
            at = ActividadPersistencia.sobreesfuerzo(user);
        } catch (SQLException ex) {
            Logger.getLogger(DespliegueProyecto.class.getName()).log(Level.SEVERE, null, ex);
        }
        return at;
    }

    @Override
    public ArrayList<Proyecto> getProyectosCerrados() {
        ArrayList<Proyecto> cerrados = null;
        try {
            cerrados = ProyectoPersistencia.getCerrados();
        } catch (SQLException ex) {
            Logger.getLogger(DespliegueProyecto.class.getName()).log(Level.SEVERE, null, ex);
        }
        return cerrados;
    }

    @Override
    public ArrayList<Etapa> getEtapas(String nombre) {
        ArrayList<Etapa> cerrados = null;
        try {
            cerrados = EtapaPersistencia.getEtapasProyecto(nombre);
        } catch (SQLException ex) {
            Logger.getLogger(DespliegueProyecto.class.getName()).log(Level.SEVERE, null, ex);
        }
        return cerrados;
    }

    @Override
    public ArrayList<Actividad> getActividadesCerrados(String nombre, int numero) {
        ArrayList<Actividad> cerrados = null;
        try {
            cerrados = ActividadPersistencia.getCerrados(nombre, numero);
        } catch (SQLException ex) {
            Logger.getLogger(DespliegueProyecto.class.getName()).log(Level.SEVERE, null, ex);
        }
        return cerrados;
    }

    @Override
    public ArrayList<Actividad> getActividadesAbiertasNoJefe(String nombre, int numero, String idTrabajador) {
        ArrayList<Actividad> cerrados = null;
        try {
            cerrados = ActividadPersistencia.getAbiertosNoJefe(nombre, numero, idTrabajador);
        } catch (SQLException ex) {
            Logger.getLogger(DespliegueProyecto.class.getName()).log(Level.SEVERE, null, ex);
        }
        return cerrados;
    }

    @Override
    public ArrayList<Actividad> misActividadesAbiertas(String user) {
        ArrayList<Actividad> actividades = null;
        try {
            actividades = ActividadPersistencia.actividadesAbiertasDe(user);
        } catch (SQLException ex) {
            Logger.getLogger(DespliegueProyecto.class.getName()).log(Level.SEVERE, null, ex);
        }
        return actividades;
    }

    @Override
    public void guardarTareaIntroducida(String proyecto, String etapa, String actividad, String user, int numTarea, Date semana, String tipoTarea, int duracion) {
        try {
            TareaPersistencia.guardarTareaIntroducida(proyecto, etapa, actividad, user, numTarea, (java.sql.Date) semana, tipoTarea, duracion);
        } catch (SQLException ex) {
            Logger.getLogger(DespliegueProyecto.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void cerrarActividad(String proyecto, int etapa, int actividad) {
        try {
            ActividadPersistencia.cerrarActividad(proyecto, etapa, actividad);
        } catch (SQLException ex) {
            Logger.getLogger(DespliegueProyecto.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public boolean tieneActividadesAbiertas(String nombreProyecto, int numeroEtapa) {
        try {
            return ActividadPersistencia.numeroActividadesAbiertas(nombreProyecto, numeroEtapa) > 0;
        } catch (SQLException ex) {
            return false;
        }
    }

    @Override
    public boolean tieneEtapasAbiertas(String nombreProyecto) {
        try {
            return EtapaPersistencia.numeroEtapasAbiertas(nombreProyecto) > 0;
        } catch (SQLException ex) {
            return false;
        }
    }

    @Override
    public boolean isAsignado(Actividad get, String user) {

        try {
            return ActividadPersistencia.isAsignado(get, user);
        } catch (SQLException ex) {
            Logger.getLogger(DespliegueProyecto.class.getName()).log(Level.SEVERE, null, ex);
        }

        return false;

    }

    @Override
    public ArrayList<Actividad> getActividadesEtapa(Etapa et) {
        ArrayList<Actividad> act = null;
        try {
            act = ActividadPersistencia.getActividadesEtapa(et);
        } catch (SQLException ex) {
            Logger.getLogger(DespliegueProyecto.class.getName()).log(Level.SEVERE, null, ex);
        }
        return act;
    }

    @Override
    public ArrayList<Tarea> getInformesActividad(Actividad act) {
        ArrayList<Tarea> tar = null;
        try {
            tar = TareaPersistencia.getDeActividad(act);
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(DespliegueProyecto.class.getName()).log(Level.SEVERE, null, ex);
        }
        return tar;
    }

    @Override
    public void aprobarInforme(Tarea tar) {
        try {
            TareaPersistencia.aprobar(tar);
        } catch (SQLException ex) {
            Logger.getLogger(DespliegueProyecto.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @Override
    public ArrayList<Tarea> getInformesActividadMios(Actividad act, Trabajador trabajador) {
        ArrayList<Tarea> tar = null;
        try {
            tar = TareaPersistencia.getDeActividadMios(act, trabajador);
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(DespliegueProyecto.class.getName()).log(Level.SEVERE, null, ex);
        }
        return tar;
    }

    @Override
    public void guardaInforme(Tarea get, String get0) {
        try {
            TareaPersistencia.guardaInforme(get, get0);
        } catch (SQLException ex) {
            Logger.getLogger(DespliegueProyecto.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public int getDedicacion(Actividad act, Trabajador trabajador) {
        try {
            return ActividadPersistencia.getDedicacion(act, trabajador);
        } catch (SQLException ex) {
            Logger.getLogger(DespliegueProyecto.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DespliegueProyecto.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }

    @Override
    public ArrayList<Tarea> getMisTareas(Actividad act) {
        ArrayList<Tarea> tar = null;
        try {
            tar = ActividadPersistencia.getTareaActividad(act);
        } catch (SQLException ex) {
            Logger.getLogger(DespliegueProyecto.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DespliegueProyecto.class.getName()).log(Level.SEVERE, null, ex);
        }
        return tar;
    }

    @Override
    public boolean tieneAntecesoras(Actividad get) {
        ArrayList<Actividad> ant = null;
        try {
            ant = ActividadPersistencia.getPredecesoras(get);
        } catch (SQLException ex) {
            Logger.getLogger(DespliegueProyecto.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ant.isEmpty();
    }

    @Override
    public void cierreActividad(Actividad act) {
        try {
            ActividadPersistencia.cerrar(act);
        } catch (SQLException ex) {
            Logger.getLogger(DespliegueProyecto.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void cierreEtapa(Etapa et) {
    try {
            EtapaPersistencia.cerrar(et);
        } catch (SQLException ex) {
            Logger.getLogger(DespliegueProyecto.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void cierreProyecto(Proyecto p) {
        try {
            ProyectoPersistencia.cerrar(p);
        } catch (SQLException ex) {
            Logger.getLogger(DespliegueProyecto.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Override
    public void guardarTarea(Tarea get) {
        try {
            TareaPersistencia.almacena(get);
        } catch (SQLException ex) {
            Logger.getLogger(DespliegueProyecto.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public int getContribucion(Trabajador trabajador, Proyecto proyecto) {
        try {
            return ProyectoPersistencia.contribucion(trabajador, proyecto);
        } catch (SQLException ex) {
            Logger.getLogger(DespliegueProyecto.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DespliegueProyecto.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }

    @Override
    public ArrayList<Actividad> getActividades(Trabajador trabajador, Proyecto proyecto) {
        try {
            ActividadPersistencia.getActividades(trabajador, proyecto);
        } catch (SQLException ex) {
            Logger.getLogger(DespliegueProyecto.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public ArrayList<Tarea> getTareasTiempo(Trabajador trabajador, Date fechaComienzo, Actividad act) {
        try {
            return TareaPersistencia.getTareasFecha(trabajador, fechaComienzo, act);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DespliegueProyecto.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(DespliegueProyecto.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

}