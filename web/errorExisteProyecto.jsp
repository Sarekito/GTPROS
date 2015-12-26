<%-- 
    Document   : errorExisteProyecto
    Created on : 27-dic-2015, 0:39:51
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
        <font size ="5" color="red">Ya hay un proyecto con ese nombre</font>
        <br>
        <br>
        <form action="Controlador" method ="post">
            <table>
                <tr>
                    <td>
                        Nombre del Proyecto
                    </td>
                    <td>
                        <input type="text" name="nombre" />
                    </td>
                </tr>
                <tr>
                    <td>
                        Jefe de Proyecto
                    </td>

                    <td>
                        <input type="text" name="jefe" />
                    </td>
                </tr>
            </table>
            <br>
            <input type="hidden" name="accion" value="registroProyecto"/>
            <input type="submit" value="Registro" />
        </form>
    </center>
</body>
</html>
