package Proyecto.Dominio;

import Trabajador.Dominio.Rol;
import java.util.Date;

/**
 *
 * @author Juan Francisco Pascual Palacin
 */
public class Actividad {

    private String nombre;
    private int numero;
    private int id;
    private String descripcion;
    private int duracion;
    private Integer duracionReal;
    private Date fechaComienzo;
    private Date fechaFin;
    private Date fechaFinReal;
    private String estado;
    private Rol tipoRol;

    public Actividad() {
    }

    public Actividad(String nombre, int numero, int id, String descripcion, int duracion, Integer duracionReal, Date fechaComienzo, Date fechaFin, Date fechaFinReal, String estado, Rol tipoRol) {
        this.nombre = nombre;
        this.numero = numero;
        this.id = id;
        this.descripcion = descripcion;
        this.duracion = duracion;
        this.duracionReal = duracionReal;
        this.fechaComienzo = fechaComienzo;
        this.fechaFin = fechaFin;
        this.fechaFinReal = fechaFinReal;
        this.estado = estado;
        this.tipoRol = tipoRol;
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getDuracion() {
        return duracion;
    }

    public void setDuracion(int duracion) {
        this.duracion = duracion;
    }

    public Integer getDuracionReal() {
        return duracionReal;
    }

    public void setDuracionReal(Integer duracionReal) {
        this.duracionReal = duracionReal;
    }

    public Date getFechaComienzo() {
        return fechaComienzo;
    }

    public void setFechaComienzo(Date fechaComienzo) {
        this.fechaComienzo = fechaComienzo;
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

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Rol getTipoRol() {
        return tipoRol;
    }

    public void setTipoRol(Rol tipoRol) {
        this.tipoRol = tipoRol;
    }
}
