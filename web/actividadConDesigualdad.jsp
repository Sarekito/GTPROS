<%-- 
    Document   : actividadConDesigualdad
    Created on : 07-ene-2016, 17:55:47
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
        <h1>Las horas totales asignadas a la actividad y las horas que suman los trabajadores en las semanas que dura el proyecto no coinciden</h1>
    <center>
        <form action="Controlador" method="POST">
            <input type="hidden" name="accion" value="volverAPlanificar" readonly="readonly" />
            <input type="submit" value="Volver a seleccionar trabajadores" />
        </form> 
    </center>
    </body> 
</html>
