package Proyecto.Dominio;

import java.util.Date;

/**
 *
 * @author Juan Francisco Pascual Palacin
 */
public class Etapa {

    private String nombre;
    private int numero;
    private Date fechaInicio;
    private Date fechaFin;
    private Date fechaFinReal;

    public Etapa() {
    }

    public Etapa(String nombre, int numero, Date fechaInicio, Date fechaFin, Date fechaFinReal) {
        this.nombre = nombre;
        this.numero = numero;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.fechaFinReal = fechaFinReal;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getNumero() {
        return numero;
    }
    
    public void setNumero(int numero) {
        this.numero = numero;
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
}
