<%-- 
    Document   : creacionConExito
    Created on : 26-dic-2015, 23:18:03
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
        <font size ="5" color="green">
        La creacion se ha realizado con exito
        </font>
        <br>
        <br>
        <form action="Controlador" method ="post">
            <input type="hidden" name="accion" value="creacionExito"/>
            <input type="submit" value="Volver al Index"/>
        </form>
    </center>
    </body>
</html>
