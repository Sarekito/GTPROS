<%-- 
    Document   : reservarVacaciones
    Created on : 24-dic-2015, 18:04:14
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
        <form name="Controlador" method ="post">
            <table>
                <tr>
                    <td>
                        Introduce el "lunes" de inicio
                    </td>
                </tr>
                <tr>
                    <td>
                        <input type="date" name ="fecha">
                    </td>
                </tr>
                <tr>
                    <td>
                        Introduce el numero de semanas
                    </td>
                </tr>
                <tr>
                    <td>
                        <input type="radio" name="semanas" value="1" checked="checked"  />1
                    </td>
                </tr>
                <tr>
                    <td>
                        <input type="radio" name="semanas" value="2" />2
                    </td>
                </tr>
                <tr>
                    <td>
                        <input type="radio" name="semanas" value="3" />3
                    </td>
                </tr>
                <tr>
                    <td>
                        <input type="radio" name="semanas" value="4" />4
                    </td>
                </tr>
                <tr>
                    <td>
                        <input type="hidden" name="accion" value="reservaVacaciones" readonly="readonly" />
                        <input type="submit" value="Reservar vacaciones" />
                    </td>
                </tr>
            </table>
        </form>
    </center>
    </body>
</html>
