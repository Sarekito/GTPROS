package Trabajador.Despliegue;

import Trabajador.Dominio.Administrador;
import Trabajador.Dominio.Trabajador;
import Trabajador.Dominio.Vacaciones;
import Trabajador.Persistencia.TrabajadorPersistencia;
import Trabajador.Persistencia.VacacionesPersistencia;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;

/**
 *
 * @author antonio
 */
@Stateless
public class DespliegueTrabajador implements DespliegueTrabajadorLocal {

    @Override
    public Trabajador getTrabajador(String user) {
        Trabajador t = null;
        try {
            t = TrabajadorPersistencia.getTrabajador(user);
        } catch (SQLException ex) {
            Logger.getLogger(DespliegueTrabajador.class.getName()).log(Level.SEVERE, null, ex);
        }
        return t;
    }

    @Override
    public boolean reservoVacaciones(String user, int ano) {
        ArrayList<Vacaciones> vacaciones = null;
        try {
            vacaciones = VacacionesPersistencia.getVacaciones(user, ano);
        } catch (SQLException ex) {
            Logger.getLogger(DespliegueTrabajador.class.getName()).log(Level.SEVERE, null, ex);
        }

        return !vacaciones.isEmpty();
    }

    @Override
    public void reservaVacaciones(Trabajador t, int periodo, int year, Date fechaElegida, int semanas) {
        Vacaciones v = new Vacaciones(t.getUser(), periodo, fechaElegida.getYear(), fechaElegida, semanas);
        try {
            VacacionesPersistencia.guardaVacaciones(v.getUser(), v.getPeriodo(), year, v.getInicio(), v.getSemanas());
        } catch (SQLException ex) {
            Logger.getLogger(DespliegueTrabajador.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public Administrador getAdministrador(String user) {
        Administrador t = null;
        try {
            t = TrabajadorPersistencia.getAdministrador(user);
        } catch (SQLException ex) {
            Logger.getLogger(DespliegueTrabajador.class.getName()).log(Level.SEVERE, null, ex);
        }
        return t;
    }

    @Override
    public void registrarTrabajador(Trabajador tr) {
        try {
            TrabajadorPersistencia.registrarTrabajador(tr);
        } catch (SQLException ex) {
            Logger.getLogger(DespliegueTrabajador.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public boolean buscaTrabajador(String user) {
        Trabajador t = null;
        try {
            t = TrabajadorPersistencia.getTrabajador(user);
        } catch (SQLException ex) {
            Logger.getLogger(DespliegueTrabajador.class.getName()).log(Level.SEVERE, null, ex);
        }

        return t != null;
    }

    @Override
    public ArrayList<Trabajador> getTrabajadores(String jefe) {
        ArrayList<Trabajador> trabajadores = null;
        try {
            trabajadores = TrabajadorPersistencia.getTrabajadores(jefe);
        } catch (SQLException ex) {
            Logger.getLogger(DespliegueTrabajador.class.getName()).log(Level.SEVERE, null, ex);
        }
        return trabajadores;
    }

    @Override
    public int getNumProyectosActivos(Trabajador t) {
        int proyectos = 0;
        try {
            proyectos = TrabajadorPersistencia.getNumProyectos(t);
        } catch (SQLException ex) {
            Logger.getLogger(DespliegueTrabajador.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println(proyectos + " " + t.getUser());
        return proyectos;
    }

    @Override
    public ArrayList<Vacaciones> getVacaciones(String user) {
        ArrayList<Vacaciones> vc = new ArrayList<>();
        try {
            vc = VacacionesPersistencia.dameVacaciones(user);
        } catch (SQLException ex) {
            Logger.getLogger(DespliegueTrabajador.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DespliegueTrabajador.class.getName()).log(Level.SEVERE, null, ex);
        }
        return vc;
    }

    @Override
    public java.util.ArrayList<Trabajador> getJefesSinProyecto() {
        try {
            return TrabajadorPersistencia.getJefesSinProyecto();
        } catch (SQLException ex) {
            return null;
        }
    }
}
