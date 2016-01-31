package Trabajador.Despliegue;

import Excepciones.DatabaseException;
import Trabajador.Dominio.Administrador;
import Trabajador.Dominio.Trabajador;
import Trabajador.Dominio.Vacaciones;
import Trabajador.Persistencia.AdministradorPersistencia;
import Trabajador.Persistencia.TrabajadorPersistencia;
import Trabajador.Persistencia.VacacionesPersistencia;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import javax.ejb.Stateless;

/**
 *
 * @author antonio
 */
@Stateless
public class DespliegueTrabajador implements DespliegueTrabajadorLocal {

    @Override
    public Trabajador getTrabajador(String user) throws DatabaseException {
        try {
            return TrabajadorPersistencia.getTrabajador(user);
        } catch (SQLException ex) {
            throw new DatabaseException();
        }
    }

    @Override
    public boolean reservoVacaciones(String user, int ano) throws DatabaseException {
        try {
            ArrayList<Vacaciones> vacaciones = VacacionesPersistencia.getVacaciones(user, ano);

            return !vacaciones.isEmpty();
        } catch (SQLException ex) {
            throw new DatabaseException();
        }
    }

    @Override
    public void reservaVacaciones(Trabajador t, int periodo, int year, Date fechaElegida, int semanas) throws DatabaseException {
        try {
            Vacaciones v = new Vacaciones(t.getUser(), periodo, fechaElegida.getYear(), fechaElegida, semanas);
            VacacionesPersistencia.guardaVacaciones(v.getUser(), v.getPeriodo(), year, v.getInicio(), v.getSemanas());
        } catch (SQLException ex) {
            throw new DatabaseException();
        }
    }

    @Override
    public Administrador getAdministrador(String user) throws DatabaseException {
        try {
            return AdministradorPersistencia.getAdministrador(user);
        } catch (SQLException ex) {
            throw new DatabaseException();
        }
    }

    @Override
    public void registrarTrabajador(Trabajador tr) throws DatabaseException {
        try {
            TrabajadorPersistencia.registrarTrabajador(tr);
        } catch (SQLException ex) {
            throw new DatabaseException();
        }
    }

    @Override
    public boolean buscaTrabajador(String user) throws DatabaseException {
        return getTrabajador(user) != null;
    }

    @Override
    public ArrayList<Trabajador> getTrabajadores(String jefe) throws DatabaseException {
        try {
            return TrabajadorPersistencia.getTrabajadores(jefe);
        } catch (SQLException ex) {
            throw new DatabaseException();
        }
    }

    @Override
    public ArrayList<Vacaciones> getVacaciones(String user) throws DatabaseException {
        try {
            return VacacionesPersistencia.dameVacaciones(user);
        } catch (SQLException ex) {
            throw new DatabaseException();
        }
    }

    @Override
    public java.util.ArrayList<Trabajador> getJefesSinProyecto() throws DatabaseException {
        try {
            return TrabajadorPersistencia.getJefesSinProyecto();
        } catch (SQLException ex) {
            throw new DatabaseException();
        }
    }
}
