<%@page import="Proyecto.Dominio.Proyecto"%>
<%@page import="java.util.List"%>
<%@page import="Proyecto.Dominio.InformeSeguimiento"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Informes pendientes de aprobacion</title>
    </head>
    <body>
        <%
            Proyecto proyecto = (Proyecto) request.getAttribute("proyecto");
            List<InformeSeguimiento> informes = (List<InformeSeguimiento>) request.getAttribute("informes");
        %>
        <h1><%=proyecto.getNombre()%></h1>
        <p>Informes pendientes de aprobacion:</p>
        <%
            if (!informes.isEmpty()) {
        %>
        <table>
            <thead>
                <tr>
                    <th>Etapa</th>
                    <th>ID Actividad</th>
                    <th>Trabajador</th>
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
                    <td>
                        <form action="Controlador" method="post">
                            <input name="accion" type="hidden" value="aprobarInforme"/>
                            <input name="nombreProyecto" type="hidden" value="<%=informe.getNombreProyecto()%>"/>
                            <input name="numeroEtapa" type="hidden" value="<%=informe.getNumeroEtapa()%>"/>
                            <input name="idActividad" type="hidden" value="<%=informe.getIdActividad()%>"/>
                            <input name="trabajador" type="hidden" value="<%=informe.getTrabajador()%>"/>
                            <input name="numTarea" type="hidden" value="<%=informe.getNumTarea()%>"/>
                            <input name="semana" type="hidden" value="<%=informe.getSemana()%>"/>
                            <input type="submit" value="Aprobar"/>
                        </form>
                    </td>
                    <td>
                        <form action="Controlador" method="post">
                            <input name="accion" type="hidden" value="rechazarInforme"/>
                            <input name="nombreProyecto" type="hidden" value="<%=informe.getNombreProyecto()%>"/>
                            <input name="numeroEtapa" type="hidden" value="<%=informe.getNumeroEtapa()%>"/>
                            <input name="idActividad" type="hidden" value="<%=informe.getIdActividad()%>"/>
                            <input name="trabajador" type="hidden" value="<%=informe.getTrabajador()%>"/>
                            <input name="numTarea" type="hidden" value="<%=informe.getNumTarea()%>"/>
                            <input name="semana" type="hidden" value="<%=informe.getSemana()%>"/>
                            <input type="submit" value="Rechazar"/>
                        </form>
                    </td>
                </tr>
                <%
                    }
                %>
            </tbody>
        </table>
        <%
        } else {
        %>
        <p>No hay informes pendientes</p>
        <%
            }
        %>
    </body>
</html>
