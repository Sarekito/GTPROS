/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Trabajador.Despliegue;

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
    
    public Trabajador getTrabajador(String parameter);

    public boolean reservoVacaciones(String user, int date);

    public void reservaVacaciones(Trabajador t, int year, int i, Date fechaElegida, int semanas);

    public Administrador getAdministrador(String parameter);

    public void registrarTrabajador(Trabajador tr);

    public boolean buscaTrabajador(String user);

    public ArrayList<Trabajador> getTrabajadores(String jefe);

    public int getNumProyectosActivos(Trabajador t);

    public ArrayList<Vacaciones> getVacaciones(String user);

    java.util.ArrayList<Trabajador> getJefesSinProyecto();

    public ArrayList<Trabajador> getJefes();

}
