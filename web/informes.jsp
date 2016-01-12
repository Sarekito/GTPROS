<%@page import="Proyecto.Dominio.Proyecto"%>
<%@page import="java.util.List"%>
<%@page import="Proyecto.Dominio.InformeSeguimiento"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Informes No Enviados</title>
    </head>
    <body>
        <%
            Proyecto proyecto = (Proyecto) request.getAttribute("proyecto");
            List<InformeSeguimiento> informes = (List<InformeSeguimiento>) request.getAttribute("informes");
        %>
        <h1><%=proyecto.getNombre()%></h1>
        <p>Informes de actividad:</p>
        <%
            if (!informes.isEmpty()) {
        %>
        <table>
            <thead>
                <tr>
                    <th>Etapa</th>
                    <th>ID Actividad</th>
                    <th>Trabajador</th>
                    <th>Estado</th>
                </tr>
            </thead>
            <tbody>
                <%
                    for (InformeSeguimiento informe : informes) {
                %>
                <tr>
                    <td><%=informe.getNumeroEtapa()%></td>
                    <td><%=informe.getIdActividad()%></td>
                    <td><%=informe.getTrabajador()%></td>
                    <td><%=informe.getEstado().name()%></td>
                </tr>
                <%
                    }
                %>
            </tbody>
        </table>
        <%
        } else {
        %>
        <p>No hay informes sin enviar</p>
        <%
            }
        %>
    </body>
</html>
