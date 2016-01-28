<%-- 
    Document   : informesAprobados
    Created on : 28-ene-2016, 11:25:32
    Author     : antonio
--%>

<%@page import="Trabajador.Dominio.Trabajador"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
    <center>
        <h1>La tarea ha sido aceptada</h1>
        <form action="Controlador" method="POST">
            <input type="hidden" name="eleccion" value="<%=request.getSession().getAttribute("selected")%>" readonly="readonly" />
            <input type="text" name="accion" value="infoProyectoAbierto" readonly="readonly" hidden="hidden"/>
            <input type="submit" value="Volver a seleccion de actividades" />
        </form>
    </center>
</body>
</html>
