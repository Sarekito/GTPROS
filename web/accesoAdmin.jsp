<%-- 
    Document   : accesoAdmin
    Created on : 30-dic-2015, 20:44:55
    Author     : antonio
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>GTPROS</title>
    </head>
    <body>
        <br>
    <center>
        <form name = "Controlador" method="POST">
            <input type="hidden" name="accion" value="registrarTrabajador"/>
            <input type="submit" value="Registrar Trabajador" />
        </form>
        <br>
        <form name = "Controlador" method="POST">
            <input type="hidden" name="accion" value="registrarProyecto"/>
            <input type="submit" value="  Registrar Proyecto  " />
        </form>
    </center>
</body>
</html>