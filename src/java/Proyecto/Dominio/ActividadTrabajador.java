/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Proyecto.Dominio;

/**
 *
 * @author antonio
 */
public class ActividadTrabajador {
    String nombreProyecto;
    int numeroEtapa;
    int idActividad;
    String nombreTrabajador;

    

    public ActividadTrabajador(Actividad ad, TrabajadoresProyecto tp){
        nombreProyecto = ad.getNombre();
        numeroEtapa = ad.getNumero();
        idActividad = ad.getId();
        nombreTrabajador = tp.getUser();
    }
    
    public String getNombreProyecto() {
        return nombreProyecto;
    }

    public int getNumeroEtapa() {
        return numeroEtapa;
    }

    public int getIdActividad() {
        return idActividad;
    }

    public String getNombreTrabajador() {
        return nombreTrabajador;
    }
}
