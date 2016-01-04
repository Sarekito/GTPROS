package Proyecto.Dominio;

import java.sql.Date;

/**
 *
 * @author Juan Francisco Pascual Palacin
 */
public class InformeSeguimiento {

    private String nombreProyecto;
    private int numeroEtapa;
    private int idActividad;
    private String trabajador;
    private int numTarea;
    private Date semana;
    private EstadoInforme estado;

    public InformeSeguimiento() {
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

    public EstadoInforme getEstado() {
        return estado;
    }

    public void setEstado(EstadoInforme estado) {
        this.estado = estado;
    }
}
