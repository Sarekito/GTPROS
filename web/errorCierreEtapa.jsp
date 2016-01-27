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
    <center><font color="red"><h1>Etapa imposible de cerrar. Cierre primero sus actividades pendientes</h1></font>
        <%request.setAttribute("trabajador", request.getAttribute("trabajador"));%>
        <form action="Controlador" method="POST">
            <input type="hidden" name="eleccion" value="<%=request.getAttribute("elegir")%>" readonly="readonly" />
            <input type="hidden" name="accion" value="infoProyectoAbierto" readonly="readonly" />
            <input type="submit" value="Volver" />
        </form>
    </center>
</body>
</html>
