package Proyecto.Despliegue;

import Proyecto.Dominio.Proyecto;
import Proyecto.Dominio.TrabajadoresProyecto;
import Proyecto.Persistencia.ActividadPersistencia;
import Proyecto.Persistencia.EtapaPersistencia;
import Proyecto.Persistencia.PersistenciaProyecto;
import Trabajador.Dominio.Trabajador;
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
}
