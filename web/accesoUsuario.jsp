<%-- 
    Document   : accesoUsuario
    Created on : 24-dic-2015, 17:43:49
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
    <center>
        <form name = "Controlador" method="POST">
            <input type="hidden" name="accion" value="vacaciones" readonly="readonly" />
            <input type="submit" value="     Reservar Vacaciones     " />
        </form>
        <br>
        <form name = "Controlador" method="POST">
            <input type="hidden" name="accion" value="misProyectos" readonly="readonly" />
            <input type="submit" value=" Ver mis proyectos en curso" />
        </form>
        <br>
        <form name = "Controlador" method="POST">
            <input type="hidden" name="accion" value="sobreesfuerzo" readonly="readonly" />
            <input type="submit" value=" Ver actividades con sobreesfuerzo" />
        </form>
        <form name = "Controlador" method="POST">
            <input type="hidden" name="accion" value="proyectosCerrados" readonly="readonly" />
            <input type="submit" value="Obtener informes sobre proyectos cerrados" />
        </form>
         <form name = "Controlador" method="POST">
            <input type="hidden" name="accion" value="proyectosAbiertos" readonly="readonly" />
            <input type="submit" value="Obtener informes sobre proyectos abiertos" />
        </form>
    </center>
    </body>
</html>
