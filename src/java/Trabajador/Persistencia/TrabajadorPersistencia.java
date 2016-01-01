package Trabajador.Persistencia;

import Trabajador.Dominio.Administrador;
import Trabajador.Dominio.Categoria;
import Trabajador.Dominio.Rol;
import Trabajador.Dominio.Trabajador;
import Trabajador.Dominio.Vacaciones;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author antonio
 */
public class TrabajadorPersistencia {

    public static Trabajador getTrabajador(String user) throws SQLException {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (Exception e) {

        }
        Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost/PGP_grupo11", "PGP_grupo11", "P6AbQA8Z");
        Statement s = conexion.createStatement();
        ResultSet rs = s.executeQuery("select * from Trabajador T where T.user='" + user + "'");
        if (rs.next()) {
            return new Trabajador(rs.getString(1), rs.getString(2), new Rol(rs.getString(3)), new Categoria(rs.getInt(4)));
        } else {
            return null;
        }
    }

    public static ArrayList<Vacaciones> getVacaciones(String user, int year) throws SQLException {
        ArrayList<Vacaciones> vacaciones = new ArrayList<>();
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (Exception e) {

        }

        Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost/PGP_grupo11", "PGP_grupo11", "P6AbQA8Z");
        Statement s = conexion.createStatement();
        ResultSet rs = s.executeQuery("select * from Vacaciones V where V.user='" + user + "' and V.ano = " + year);
        while (rs.next()) {
            vacaciones.add(new Vacaciones(rs.getString(1), rs.getInt(2), rs.getInt(3), rs.getDate(4), rs.getInt(5)));
        }
        return vacaciones;
    }

    public static void guardaVacaciones(String user, int periodo, int year, Date inicio, int semanas) throws SQLException {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (Exception e) {

        }
        Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost/PGP_grupo11", "PGP_grupo11", "P6AbQA8Z");
        Statement s = conexion.createStatement();
        s.execute("INSERT INTO Vacaciones VALUES ('" + user + "'," + periodo + ", " + (int) (year + 1900) + ", '" + (int) (inicio.getYear() + 1900) + "-" + (int) (inicio.getMonth() + 1) + "-" + inicio.getDate() + "', " + semanas + ")");
    }

    public static Administrador getAdministrador(String user) throws SQLException {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (Exception e) {

        }
        Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost/PGP_grupo11", "PGP_grupo11", "P6AbQA8Z");
        Statement s = conexion.createStatement();
        ResultSet rs = s.executeQuery("select * from Administrador T where T.user='" + user + "'");
        rs.next();
        return new Administrador(rs.getString(1), rs.getString(2));
    }

    public static void registrarTrabajador(Trabajador tr) throws SQLException {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (Exception e) {

        }
        Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost/PGP_grupo11", "PGP_grupo11", "P6AbQA8Z");
        Statement s = conexion.createStatement();
        s.execute("Insert into Trabajador values('" + tr.getUser() + "', '" + tr.getPassword() + "', '" + tr.getTipoRol().getRol() + "', " + tr.getCategoria().getCategoria() + ")");
    }

    public static ArrayList<Trabajador> getTrabajadores() throws SQLException {
        ArrayList<Trabajador> trabajadores = new ArrayList<>();
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (Exception e) {

        }
        Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost/PGP_grupo11", "PGP_grupo11", "P6AbQA8Z");
        Statement s = conexion.createStatement();
        ResultSet rs = s.executeQuery("select * from Trabajador T");
        while(rs.next()){
            trabajadores.add(new Trabajador(rs.getString(1), rs.getString(2), new Rol(rs.getString(3)), new Categoria(rs.getInt(4))));
        }
        return trabajadores;
    }

    public static int getNumProyectos(Trabajador t) throws SQLException {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (Exception e) {

        }
        Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost/PGP_grupo11", "PGP_grupo11", "P6AbQA8Z");
        Statement s = conexion.createStatement();
        ResultSet rs = s.executeQuery("select * from (select P.nombre from Proyecto P where P.estado<>'finalizado') P, TrabajadoresProyecto TP where TP.nombre = P.nombre and TP.user='"+t.getUser()+"'");
        int a = 0;
        while(rs.next()){
            a++;
        }
        return a;
    }

}
