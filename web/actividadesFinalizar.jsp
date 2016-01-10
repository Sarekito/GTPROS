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
    <script src="./js/jquery-1.12.0.js"></script>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
    <center>
        <h1>Introducir datos de la actividad</h1>
        <table>
            <form action="Controlador" method="POST" onsubmit="return comprueba();">
                <tr>
                    <td>
                        Fecha de inicio de la actividad:
                    </td>
                    <td>
                        <input id="ini" type="date" name="inicio"/>
                    </td>
                </tr>
                <tr>
                    <td>
                        Fecha de fin de la actividad:
                    </td>
                    <td>
                        <input id="fin" type="date" name="fin"/>
                    </td>
                </tr>
                <tr>
                    <td>
                        Duracion:
                    </td>
                    <td>
                        <input id="duracion" type="number" name="duracion" min="1"/>
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
                        <textarea id="descripcion" name="descripcion" rows="10" cols="60" >
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
<script>
    function comprueba(){
        <script>
    function check(){
        //Omitimos comprobacion en radios ya que hay un checkeado predeterminado
        if($('#ini').val()==""){
            alert("Error en fecha inicio");
            return false;
        }
        if($('#fin').val()==""){
            alert("Error en fecha fin");
            return false;
        }
        if($('#duracion').val()==""){
            alert("Error en la duracion");
            return false;
        }
        if($('#descripcion').val()==""){
            alert("Error en la descripcion");
            return false;
        }
        return true;
    }
</script>
    }
</script>
</html>


