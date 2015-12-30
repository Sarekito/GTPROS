/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Trabajador.Despliegue;

import Trabajador.Dominio.Administrador;
import Trabajador.Dominio.Trabajador;
import Trabajador.Dominio.Vacaciones;
import Trabajador.Persistencia.TrabajadorPersistencia;
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

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
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
            vacaciones = TrabajadorPersistencia.getVacaciones(user, ano);
        } catch (SQLException ex) {
            Logger.getLogger(DespliegueTrabajador.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (vacaciones.size() == 0) {
            return false;
        } else {
            return true;
        }
    }

    @Override
    public void reservaVacaciones(Trabajador t, int periodo, int year, Date fechaElegida, int semanas) {
        Vacaciones v = new Vacaciones(t.getUser(), periodo, fechaElegida.getYear(), fechaElegida, semanas);
        try {
            TrabajadorPersistencia.guardaVacaciones(v.getUser(), v.getPeriodo(), year , v.getInicio(), v.getSemanas());
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
        if (t==null){
            return false;
        }
        else{
            return true;
        }
    }
}
