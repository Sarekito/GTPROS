/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Proyecto.Persistencia;

import Proyecto.Dominio.Proyecto;
import Trabajador.Dominio.Vacaciones;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author antonio
 */
public class PersistenciaProyecto {

    public static ArrayList<Proyecto> getMisProyectos(String jefe) throws SQLException {
        ArrayList<Proyecto> proyectos = new ArrayList<Proyecto>();
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (Exception e) {

        }
        Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost/PGP_grupo11", "PGP_grupo11", "P6AbQA8Z");
        Statement s = conexion.createStatement();
        System.out.println("select * from Proyecto P where P.jefeProyecto='" + jefe + "' and P.estado <> 'cerrado'");
        ResultSet rs = s.executeQuery("select * from Proyecto P where P.jefeProyecto='" + jefe + "' and P.estado <> 'cerrado'");
        while (rs.next()) {
            Proyecto p = new Proyecto(rs.getString("nombre"), rs.getDate("fechaInicio"), rs.getDate("fechaFin"), rs.getString("jefeProyecto"), rs.getString("estado"));
            proyectos.add(p);
        }
        return proyectos;
    }

    public static Proyecto getProyecto(String nombreProyecto) throws SQLException {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (Exception e) {

        }
        Connection conexion = DriverManager.getConnection("jdbc:mysql://localhostt/PGP_grupo11", "PGP_grupo11", "P6AbQA8Z");
        Statement s = conexion.createStatement();
        ResultSet rs = s.executeQuery("select * from Proyecto P where P.nombre ='" + nombreProyecto + "'");
        if (rs.next()) {
            Proyecto p = new Proyecto(rs.getString("nombre"), rs.getDate("fechaInicio"), rs.getDate("fechaFin"), rs.getString("jefeProyecto"), rs.getString("estado"));
            return p;
        }
        return null;
    }

    public static void generar(String nombreProyecto, String jefe) throws SQLException {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (Exception e) {

        }
        Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost/PGP_grupo11", "PGP_grupo11", "P6AbQA8Z");
        Statement s = conexion.createStatement();
        s.execute("insert into Proyecto(nombre, jefeProyecto, estado) values ('"+nombreProyecto+"', '"+jefe+"', 'pendiente')");
    }
    
}
