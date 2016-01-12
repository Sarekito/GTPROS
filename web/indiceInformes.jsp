<%@page import="Proyecto.Dominio.Proyecto"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Indice de informes</title>
    </head>
    <body>
        <%
            Proyecto proyecto = (Proyecto) request.getAttribute("proyecto");
        %>
        <h1>Informes de '<%=proyecto.getNombre()%>'</h1>
        <form action="Controlador" method="POST">
            <input type="hidden" name="accion" value="mostrarInformes" readonly/>
            <input type="hidden" name="nombreProyecto" value="<%=proyecto.getNombre()%>" readonly/>
            <input type="submit" value=" Informes de actividad" />
        </form>
        <br/>
        <form action="Controlador" method="POST">
            <input type="hidden" name="accion" value="mostrarInformesPendientes" readonly/>
            <input type="hidden" name="nombreProyecto" value="<%=proyecto.getNombre()%>" readonly/>
            <input type="submit" value=" Informes de actividad" />
        </form>
        <br/>
        <form action="Controlador" method="POST">
            <input type="hidden" name="accion" value="mostrarInformes" readonly/>
            <input type="hidden" name="nombreProyecto" value="<%=proyecto.getNombre()%>" readonly/>
            <input type="submit" value=" Informes de actividad" />
        </form>
    </body>
</html>
