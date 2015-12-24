/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Trabajador.Dominio;

import java.util.Date;

/**
 *
 * @author antonio
 */
public class Vacaciones {
    private String user;
    private int Periodo;
    private int ano;
    private Date inicio;
    private int semanas;

    public Vacaciones(String user, int Periodo, int ano, Date inicio, int semanas) {
        this.user = user;
        this.Periodo = Periodo;
        this.ano = ano;
        this.inicio = inicio;
        this.semanas = semanas;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public int getPeriodo() {
        return Periodo;
    }

    public void setPeriodo(int Periodo) {
        this.Periodo = Periodo;
    }

    public int getAno() {
        return ano;
    }

    public void setAno(int ano) {
        this.ano = ano;
    }

    public Date getInicio() {
        return inicio;
    }

    public void setInicio(Date inicio) {
        this.inicio = inicio;
    }

    public int getSemanas() {
        return semanas;
    }

    public void setSemanas(int semanas) {
        this.semanas = semanas;
    }
    
}
