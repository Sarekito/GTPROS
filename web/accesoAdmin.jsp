<%-- 
    Document   : accesoAdmin
    Created on : 25-dic-2015, 11:44:09
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
        <br>
    <center>
        <form name = "Controlador" method="POST">
            <input type="hidden" name="accion" value="registroTrabajador" readonly="readonly" />
            <input type="submit" value="Registrar Trabajador" />
        </form>
        <br>
        <form name = "Controlador" method="POST">
            <input type="hidden" name="accion" value=registroProyecto" readonly="readonly" />
            <input type="submit" value="  Registrar Proyecto  " size = "50"/>
        </form>
    </center>
</body>
</html>
