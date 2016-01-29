<%-- 
    Document   : errorCierreEtapa
    Created on : 27-ene-2016, 18:10:24
    Author     : antonio
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
    <center><font color="red"><h1>Proyecto imposible de cerrar. Cierre primero sus etapas pendientes</h1></font>
        <form action="Controlador" method="POST">
            <input type="hidden" name="eleccion" value="<%=request.getSession().getAttribute("selected")%>" readonly="readonly" />
            <input type="hidden" name="accion" value="infoProyectoAbierto" readonly="readonly" />
            <input type="submit" value="Volver" />
        </form>
    </center>
</body>
</html>
