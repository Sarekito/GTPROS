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
    private int duracion;
    private int duracionReal;
    private String estado;

    public Etapa() {
    }

    public Etapa(String nombre, int numero, Date fechaInicio, Date fechaFin, Date fechaFinReal, int duracion, int duracionReal, String estado) {
        this.nombre = nombre;
        this.numero = numero;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.fechaFinReal = fechaFinReal;
        this.duracion = duracion;
        this.duracionReal = duracionReal;
        this.estado = estado;
    }

    public String getEstado() {
        return estado;
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

    public void setEstado(String estado) {
        this.estado = estado;
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
