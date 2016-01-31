package Trabajador.Despliegue;

import Excepciones.DatabaseException;
import Excepciones.TrabajadorYaRegistradoException;
import Trabajador.Dominio.Administrador;
import Trabajador.Dominio.Trabajador;
import Trabajador.Dominio.Vacaciones;
import java.util.ArrayList;
import java.util.Date;
import javax.ejb.Local;

/**
 *
 * @author antonio
 */
@Local
public interface DespliegueTrabajadorLocal {

    public Trabajador getTrabajador(String parameter) throws DatabaseException;

    public boolean reservoVacaciones(String user, int date) throws DatabaseException;

    public void reservaVacaciones(Trabajador t, int year, int i, Date fechaElegida, int semanas) throws DatabaseException;

    public Administrador getAdministrador(String parameter) throws DatabaseException;

    public void registrarTrabajador(Trabajador tr) throws DatabaseException, TrabajadorYaRegistradoException;

    public boolean buscaTrabajador(String user) throws DatabaseException;

    public ArrayList<Trabajador> getTrabajadores(String jefe) throws DatabaseException;

    public ArrayList<Vacaciones> getVacaciones(String user) throws DatabaseException;

    java.util.ArrayList<Trabajador> getJefesSinProyecto() throws DatabaseException;

}
