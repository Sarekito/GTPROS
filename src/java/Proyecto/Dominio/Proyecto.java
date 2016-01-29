/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Proyecto.Dominio;

import java.util.Date;

/**
 *
 * @author antonio
 */
public class Proyecto {

    private final String nombre;
    private Date fechaInicio;
    private Date fechaFin;
    private Date fechaFinReal;
    private int duracion;
    private int duracionReal;
    private final String jefe;
    private String estado;

    public Proyecto(String nombre, String jefe) {
        this.nombre = nombre;
        this.jefe = jefe;
    }

    public String getNombre() {
        return nombre;
    }

    public String getJefe() {
        return jefe;
    }
    
    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public Date getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin;
    }

    public Date getFechaFinReal() {
        return fechaFinReal;
    }

    public void setFechaFinReal(Date fechaFinReal) {
        this.fechaFinReal = fechaFinReal;
    }

    public int getDuracion() {
        return duracion;
    }

    public void setDuracion(int duracion) {
        this.duracion = duracion;
    }

    public int getDuracionReal() {
        return duracionReal;
    }

    public void setDuracionReal(int duracionReal) {
        this.duracionReal = duracionReal;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
    
}