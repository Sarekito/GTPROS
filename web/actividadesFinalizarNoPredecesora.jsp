<%-- 
    Document   : actividades
    Created on : 06-ene-2016, 11:39:05
    Author     : antonio
--%>

<%@page import="Proyecto.Dominio.Actividad"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
    <center>
        <%Actividad a = (Actividad)request.getAttribute("predecesora");%>
        <h2> La actividad <%=a.getDescripcion()%> no comienza mas pronto</h2>
        <h1>Introducir datos de la actividad</h1>
        <table>
            <form action="Controlador" method="POST">
                <tr>
                    <td>
                        Fecha de inicio de la actividad:
                    </td>
                    <td>
                        <input type="date" name="inicio"/>
                    </td>
                </tr>
                <tr>
                    <td>
                        Fecha de fin de la actividad:
                    </td>
                    <td>
                        <input type="date" name="fin"/>
                    </td>
                </tr>
                <tr>
                    <td>
                        Duracion:
                    </td>
                    <td>
                        <input type="number" name="duracion" min="1"/>
                    </td>
                </tr>
                <tr>
                    <td>
                        Tipo de rol:   
                    </td>
                </tr>
                <tr>
                    <td>
                        <input type="radio" name="Rol" value="Analista" checked="checked" /> Analista
                    </td>
                </tr>
                <tr>
                    <td>
                        <input type="radio" name="Rol" value="Disenador" /> Diseñador
                </tr>
                <tr>
                    <td>
                        <input type="radio" name="Rol" value="AnalistaProgramador" /> Analista-Programador
                    </td>
                </tr>
                <tr>
                    <td>
                        <input type="radio" name="Rol" value="ResponsablePruebaas"/> Responsable de Pruebas
                    </td>
                </tr>
                <tr>
                    <td>
                        <input type="radio" name="Rol" value="Programador"/> Programador
                    </td>
                </tr>
                <tr>
                    <td>
                        <input type="radio" name="Rol" value="Probador"/> Probador
                    </td>
                </tr>
                <tr>
                    <td>
                        Descripción:
                    </td>
                    <td>
                        <textarea name="descripcion" rows="10" cols="60" >
                        </textarea>
                    </td>
                </tr>
                <%ArrayList<Actividad> ac = new ArrayList<Actividad>();
                    ac = (ArrayList<Actividad>) request.getAttribute("posiblesPredecesoras");
                    for (int i = 0; i < ac.size(); i++) {%>
                <tr>
                    <td>
                        <input type="checkbox" name=<%=i%> value="ON"/><%=ac.get(i).getDescripcion() %>
                    </td>
                </tr>
                <%
                        }%>
        </table>
        <br>
        <br>
        <input type="hidden" name="accion" value="actividadConPredecesoras" />
        <input type="submit" value="Planificar Actividades" />
    </form>
    <br>
    <br>
    <br>
    <form action="Controlador" method="POST">
        <input type="hidden" name="accion" value="finalizarPlanActividad" readonly="readonly" />
        <input type="submit" value="Finalizar planificacion de etapa"/>
    </form>
</center>
</body>
</html>

