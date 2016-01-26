/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Trabajador.Dominio;

/**
 *
 * @author antonio
 */
public class Trabajador {

    private String user;
    private String password;
    private Categoria categoria;

    public Trabajador(String user, String password, Categoria categoria) {
        this.user = user;
        this.password = password;
        this.categoria = categoria;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

}
