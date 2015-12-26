/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Proyecto.Dominio;

import java.sql.Date;

/**
 *
 * @author antonio
 */
public class Proyecto {

    private final String nombre;
    private Date fechaInicio;
    private Date fechaFin;
    private final String jefe;
    private Date fechaFinReal;
    
    public Date getFechaFinReal() {
        return fechaFinReal;
    }

    public void setFechaFinReal(Date fechaFinReal) {
        this.fechaFinReal = fechaFinReal;
    }

    public Proyecto(String nombre, String jefe) {
        this.nombre = nombre;
        this.jefe = jefe;
    }

    public Proyecto(String nombre, Date fechaInicio, Date fechaFin, String jefe, String estado) {
        this.nombre = nombre;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.jefe = jefe;
        this.estado = estado;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getNombre() {
        return nombre;
    }

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public Date getFechaFin() {
        return fechaFin;
    }

    public String getJefe() {
        return jefe;
    }

    public String getEstado() {
        return estado;
    }
    private String estado;
    
}
