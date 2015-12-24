package Trabajador.Persistencia;

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
import java.time.Instant;

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
        Connection conexion = DriverManager.getConnection ("jdbc:mysql://localhost/PGP_grupo11","PGP_grupo11", "P6AbQA8Z");
        Statement s = conexion.createStatement(); 
        ResultSet rs = s.executeQuery ("select * from Trabajador T where T.user='"+user+"'");
        rs.next();
        return new Trabajador(rs.getString(1), rs.getString(2), new Rol(rs.getString(3)), new Categoria(rs.getInt(4)));
    }

    public static ArrayList<Vacaciones> getVacaciones(String user) throws SQLException {
        ArrayList<Vacaciones> vacaciones = new ArrayList<>();
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (Exception e) {

        }
        
        Connection conexion = DriverManager.getConnection ("jdbc:mysql://localhost/PGP_grupo11","PGP_grupo11", "P6AbQA8Z");
        Statement s = conexion.createStatement(); 
        ResultSet rs = s.executeQuery ("select * from Vacaciones V where V.user='"+user+"' and V.ano = "+Date.from(Instant.now()).getYear());
        while(rs.next()){
            vacaciones.add(new Vacaciones(rs.getString(1), rs.getInt(2), rs.getInt(3), rs.getDate(4), rs.getInt(5)));
        }
        return vacaciones;
    }

    public static void guardaVacaciones(String user, int year, int periodo, Date inicio, int semanas) throws SQLException {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (Exception e) {

        }
        Connection conexion = DriverManager.getConnection ("jdbc:mysql://localhost/PGP_grupo11","PGP_grupo11", "P6AbQA8Z");
        Statement s = conexion.createStatement(); 
        s.execute("INSERT INTO Vacaciones VALUES ('"+user+"',"+periodo+", "+(int)(year+1900)+", '"+(int)(inicio.getYear()+1900)+"-"+(int)(inicio.getMonth()+1)+"-"+inicio.getDate()+"', "+semanas+")");   
    }
    
}
