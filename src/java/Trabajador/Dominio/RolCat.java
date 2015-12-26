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
public class RolCat {
    public static Categoria dameCat(Rol r){
        switch(r.getRol()){
            case "JefeProyecto":
                return new Categoria(1);
            case "Analista":
                return new Categoria(2);
            case "AnalistaProgramador":
                return new Categoria(3);
            case "Disenador":
                return new Categoria(3);
            case "ResponsablePruebas":
                return new Categoria(3);
            case "Programador":
                return new Categoria(4);
            case "Probador":
                return new Categoria(4);
            default:
                return null;
        }
    }
}
