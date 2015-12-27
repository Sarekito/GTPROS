/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Proyecto.Despliegue;

import Proyecto.Dominio.Proyecto;
import Proyecto.Persistencia.PersistenciaProyecto;
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
}
