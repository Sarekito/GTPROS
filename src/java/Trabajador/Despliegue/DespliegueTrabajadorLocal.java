/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Trabajador.Despliegue;

import Trabajador.Dominio.Administrador;
import Trabajador.Dominio.Trabajador;
import java.util.Date;
import javax.ejb.Local;

/**
 *
 * @author antonio
 */
@Local
public interface DespliegueTrabajadorLocal {
    public Trabajador getTrabajador(String parameter);

    public boolean reservoVacaciones(String user);

    public void reservaVacaciones(Trabajador t, int i, Date fechaElegida, int semanas);

    public Administrador getAdministrador(String parameter);
}
