<%-- 
    Document   : actividadCerrada
    Created on : 29-ene-2016, 11:29:22
    Author     : antonio
--%>

<%@page import="Proyecto.Dominio.Actividad"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <%Actividad a = (Actividad)request.getSession().getAttribute("cerrada");%>
    <center>
        <h2>Resumen</h2>
        <table>
            <tr>
                <td>
                    Proyecto
                </td>
                <td>
                    <%=a.getNombre()%>
                </td>
            </tr>
            <tr>
                <td>
                    Etapa
                </td>
                <td>
                    <%=a.getNumero()%>
                </td>
            </tr>
            <tr>
                <td>
                    Actividad
                </td>
                <td>
                    <%=a.getId() %>
                </td>
            </tr>
            <tr>
                <td>
                    Duracion real
                </td>
                <td> 
                    <%=a.getDuracionReal()%>
                </td>
            </tr>
            <tr>
                <td>
                    Fecha de finalizacion
                </td>
                <td>
                    <%=a.getFechaFinReal()%>
                </td>
            </tr>
        </table>
    </center>
    </body>
</html>
