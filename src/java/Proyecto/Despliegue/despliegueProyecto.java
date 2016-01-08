package Proyecto.Despliegue;

import Proyecto.Dominio.Actividad;
import Proyecto.Dominio.ActividadTrabajador;
import Proyecto.Dominio.Etapa;
import Proyecto.Dominio.InformeSeguimiento;
import Proyecto.Dominio.Proyecto;
import Proyecto.Dominio.TrabajadoresProyecto;
import Proyecto.Persistencia.ActividadPersistencia;
import Proyecto.Persistencia.EtapaPersistencia;
import Proyecto.Persistencia.InformeSeguimientoPersistencia;
import Proyecto.Persistencia.PersistenciaProyecto;
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
public class despliegueProyecto implements despliegueProyectoLocal {

    @Override
    public ArrayList<Proyecto> getMisProyectos(String jefe) {
        ArrayList<Proyecto> proyectos = new ArrayList<>();
        try {
            proyectos = PersistenciaProyecto.getMisProyectos(jefe);
        } catch (SQLException ex) {
            Logger.getLogger(despliegueProyecto.class.getName()).log(Level.SEVERE, null, ex);
        }
        return proyectos;
    }

    @Override
    public ArrayList<Proyecto> getMisProyectosActuales(Trabajador tr) {
        ArrayList<Proyecto> misProyectos = null;

        try {
            misProyectos = PersistenciaProyecto.getMisProyectosActuales(tr);
        } catch (SQLException ex) {
            Logger.getLogger(despliegueProyecto.class.getName()).log(Level.SEVERE, null, ex);
        }

        return misProyectos;
    }

    @Override
    public Proyecto getProyecto(String nombreProyecto) {
        Proyecto p = null;
        try {
            p = PersistenciaProyecto.getProyecto(nombreProyecto);
        } catch (SQLException ex) {
            Logger.getLogger(despliegueProyecto.class.getName()).log(Level.SEVERE, null, ex);
        }
        return p;
    }

    @Override
    public void generar(String nombreProyecto, String jefe) {
        try {
            PersistenciaProyecto.generar(nombreProyecto, jefe);
        } catch (SQLException ex) {
            Logger.getLogger(despliegueProyecto.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public boolean cerrarProyecto(String nombreProyecto) {
        try {
            if (EtapaPersistencia.numeroEtapasAbiertas(nombreProyecto) > 0) {
                return false;
            }

            PersistenciaProyecto.cerrarProyecto(nombreProyecto);
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(despliegueProyecto.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    @Override
    public boolean cerrarEtapa(String nombreProyecto, int numero) {
        try {
            if (ActividadPersistencia.numeroActividadesAbiertas(nombreProyecto, numero) > 0) {
                return false;
            }

            EtapaPersistencia.cerrarEtapa(nombreProyecto, numero);
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(despliegueProyecto.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    @Override
    public TrabajadoresProyecto dameTrabajadorProyecto(String user, String nombre) {
        TrabajadoresProyecto tp = null;
        try {
            tp = PersistenciaProyecto.getTrabajadorProyecto(user, nombre);
        } catch (SQLException ex) {
            Logger.getLogger(despliegueProyecto.class.getName()).log(Level.SEVERE, null, ex);
        }
        return tp;
    }

    @Override
    public ArrayList<InformeSeguimiento> getInformesProyecto(String nombreProyecto) {
        try {
            return InformeSeguimientoPersistencia.getInformesProyecto(nombreProyecto);
        } catch (SQLException ex) {
            Logger.getLogger(despliegueProyecto.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    @Override
    public void aprobarInforme(InformeSeguimiento informe) {
        try {
            InformeSeguimientoPersistencia.aprobarInforme(informe);
        } catch (SQLException ex) {
            Logger.getLogger(despliegueProyecto.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void rechazarInforme(InformeSeguimiento informe) {
        try {
            InformeSeguimientoPersistencia.rechazarInforme(informe);
        } catch (SQLException ex) {
            Logger.getLogger(despliegueProyecto.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public ArrayList<InformeSeguimiento> getInformesPendientesProyecto(String nombreProyecto) {
        try {
            return InformeSeguimientoPersistencia.getInformesPendientesProyecto(nombreProyecto);
        } catch (SQLException ex) {
            Logger.getLogger(despliegueProyecto.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    @Override
    public java.util.ArrayList<InformeSeguimiento> getInformesNoEnviadosProyecto(String nombreProyecto) {
        try {
            return InformeSeguimientoPersistencia.getInformesNoEnviados(nombreProyecto);
        } catch (SQLException ex) {
            Logger.getLogger(despliegueProyecto.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    @Override
    public void guardarProyecto(Proyecto proyecto) {
        try {
            PersistenciaProyecto.guardarProyecto(proyecto);
        } catch (SQLException ex) {
            Logger.getLogger(despliegueProyecto.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void guardarEtapas(ArrayList<Etapa> etapas) {
        etapas.get(0).setEstado("realizando");
        for (int i = 0;i<etapas.size();i++){
            try {
                EtapaPersistencia.guardarEtapa(etapas.get(i));
            } catch (SQLException ex) {
                Logger.getLogger(despliegueProyecto.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @Override
    public void guardarActividades(ArrayList<Actividad> actividades) {
        
        Date inicio = actividades.get(0).getFechaComienzo();   
        for (int i =0;i<actividades.size();i++){
            if (actividades.get(i).getFechaComienzo() == inicio){
                actividades.get(i).setEstado("realizando");
            }
            try {   
                ActividadPersistencia.guardaActividad(actividades.get(i));
            } catch (SQLException ex) {
                Logger.getLogger(despliegueProyecto.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @Override
    public void guardarTrabajadores(ArrayList<TrabajadoresProyecto> tp) {
        for(int i =0;i<tp.size();i++){
            try {
                PersistenciaProyecto.guardarTrabajadores(tp.get(i));
            } catch (SQLException ex) {
                Logger.getLogger(despliegueProyecto.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @Override
    public void guardarAsignaciones(ArrayList<ActividadTrabajador> actividadTrabajador) {
        for(int i =0;i<actividadTrabajador.size();i++){
            try {
                PersistenciaProyecto.guardarAsignaciones(actividadTrabajador.get(i));
            } catch (SQLException ex) {
                Logger.getLogger(despliegueProyecto.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
