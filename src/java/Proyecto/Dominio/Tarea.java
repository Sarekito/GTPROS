package Proyecto.Dominio;

import java.util.Date;

/**
 *
 * @author Juan Francisco Pascual Palacin
 */
public class Tarea {

    private String nombreProyecto;
    private int numeroEtapa;
    private int idActividad;
    private String trabajador;
    private int numTarea;
    private Date semana;
    private TipoTarea tipoTarea;
    private int duracion;

    public Tarea() {
    }

    public Tarea(String nombreProyecto, int numeroEtapa, int idActividad, String trabajador, int numTarea, Date semana, TipoTarea tipoTarea, int duracion) {
        this.nombreProyecto = nombreProyecto;
        this.numeroEtapa = numeroEtapa;
        this.idActividad = idActividad;
        this.trabajador = trabajador;
        this.numTarea = numTarea;
        this.semana = semana;
        this.tipoTarea = tipoTarea;
        this.duracion = duracion;
    }

    public String getNombreProyecto() {
        return nombreProyecto;
    }

    public void setNombreProyecto(String nombreProyecto) {
        this.nombreProyecto = nombreProyecto;
    }

    public int getNumeroEtapa() {
        return numeroEtapa;
    }

    public void setNumeroEtapa(int numeroEtapa) {
        this.numeroEtapa = numeroEtapa;
    }

    public int getIdActividad() {
        return idActividad;
    }

    public void setIdActividad(int idActividad) {
        this.idActividad = idActividad;
    }

    public String getTrabajador() {
        return trabajador;
    }

    public void setTrabajador(String trabajador) {
        this.trabajador = trabajador;
    }

    public int getNumTarea() {
        return numTarea;
    }

    public void setNumTarea(int numTarea) {
        this.numTarea = numTarea;
    }

    public Date getSemana() {
        return semana;
    }

    public void setSemana(Date semana) {
        this.semana = semana;
    }

    public TipoTarea getTipoTarea() {
        return tipoTarea;
    }

    public void setTipoTarea(TipoTarea tipoTarea) {
        this.tipoTarea = tipoTarea;
    }

    public int getDuracion() {
        return duracion;
    }

    public void setDuracion(int duracion) {
        this.duracion = duracion;
    }
}
