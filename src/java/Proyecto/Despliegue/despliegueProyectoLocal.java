/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Proyecto.Despliegue;

import Proyecto.Dominio.Proyecto;
import java.util.ArrayList;
import javax.ejb.Local;

/**
 *
 * @author antonio
 */
@Local
public interface despliegueProyectoLocal {

    public ArrayList<Proyecto> getMisProyectos(String jefe);

    public Proyecto getProyecto(String nombreProyecto);

    public void generar(String nombreProyecto, String jefe);
    
}
