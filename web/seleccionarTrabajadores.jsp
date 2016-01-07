<%-- 
    Document   : elegirTrabajadores
    Created on : 31-dic-2015, 0:11:27
    Author     : antonio
--%>

<%@page import="Proyecto.Dominio.TrabajadoresProyecto"%>
<%@page import="Trabajador.Dominio.Trabajador"%>
<%@page import="java.util.ArrayList"%>
<%@page import="Trabajador.Dominio.Rol"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title></title>
    </head>
    <body>
    <center>
        <%ArrayList<TrabajadoresProyecto> tr = (ArrayList<TrabajadoresProyecto>) request.getAttribute("trabajadoresProyecto");%>
        <h1>Elige el equipo del proyecto</h1>
        <p>Introducir las del trabajador y pulsar elegir</p>
        <table border="5">
            <tr>
                <td>
                    Nombre
                </td>
                <td>
                    Porcentaje de dedicacion
                </td>
                <td>
                    Horas disponibles para este proyecto 
                </td>
                <%for (int i = 0; i < tr.size(); i++) {%>
            <form action="Controlador" method="POST">
                </tr>
                <tr>
                    <td>
                        <%=tr.get(i).getUser()%>
                    </td>
                    <td>
                        <%=tr.get(i).getDedicacion()%>
                    </td>
                    <td>
                        <%=(tr.get(i).getDedicacion()*40)/100%>
                    </td>
                    <td>
                        <input type="number" name="horasActividad" min="1" max="<%=(tr.get(i).getDedicacion()*40)/100%>"
                    </td>
                    <td>
                        <input type="hidden" name="accion" value="generaActividadTrabajador" readonly="readonly" />
                        <input type="hidden" name="eleccion" value=<%=i%> readonly="readonly" />
                        <input type="submit" value="Elegir" />
                    </td>
                </tr>
                 </form>
            <%}%>
        </table>
    </center>
</body>
</html>
