<%-- 
    Document   : actividadCerrada
    Created on : 29-ene-2016, 11:29:22
    Author     : antonio
--%>

<%@page import="Proyecto.Dominio.Proyecto"%>
<%@page import="Trabajador.Dominio.Trabajador"%>
<%@page import="Proyecto.Dominio.Actividad"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <%Proyecto a = (Proyecto) request.getSession().getAttribute("proyectoCerrado");%>
        <%Trabajador t = (Trabajador) request.getSession().getAttribute("trabajador");%>
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
                    <%String cierre = (a.getFechaFinReal().getYear() + 1900) + "-" + (a.getFechaFinReal().getMonth() + 1) + "-" + a.getFechaFinReal().getDate();%>
                    <%=cierre%>
                </td>
            </tr>
        </table>
        <br>
        <form action="Controlador" method="POST">
            <input type="text" name="usuario" value="<%=t.getUser()%>" readonly="readonly" hidden="hidden"/>
            <input type="text" name="clave" value="<%=t.getPassword()%>" readonly="readonly" hidden="hidden" />
            <input type="hidden" name="accion" value="Acceso" readonly="readonly" />
            <input type="submit" value="Ir a inicio" />
        </form>
    </center>
</body>
</html>
