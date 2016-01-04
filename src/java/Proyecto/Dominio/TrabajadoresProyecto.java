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
public class TrabajadoresProyecto {
    private String nombre;
    private String user;
    private int dedicacion;

    public TrabajadoresProyecto(String nombre, String user, int dedicacion) {
        this.nombre = nombre;
        this.user = user;
        this.dedicacion = dedicacion;
    }

    public String getNombre() {
        return nombre;
    }

    public String getUser() {
        return user;
    }

    public int getDedicacion() {
        return dedicacion;
    }
}
