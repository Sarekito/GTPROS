<%-- 
    Document   : registroTrabajador
    Created on : 25-dic-2015, 17:34:58
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
        <form action="Controlador" method ="post">
            <table>
                <tr>
                    <td>
                        Usuario
                    </td>
                    <td>
                        <input type="text" name="usuario" size="50" />
                    </td>
                </tr>
                <tr>
                    <td>
                        Contraseña
                    </td>

                    <td>
                        <input type="text" name="clave" size="50" />
                    </td>
                </tr>
                 <tr>
                    <td>
                        <br>
                        Seleccionar rol
                    </td>
                </tr>
                <tr>
                    <td>
                        <input type="radio" name="rol" value="JefeProyecto" checked="checked"  />Jefe de Proyecto
                    </td>
                </tr>
                <tr>
                    <td>
                        <input type="radio" name="rol" value="Analista" />Analista
                    </td>
                </tr>
                <tr>
                    <td>
                        <input type="radio" name="rol" value="Disenador" />Diseñador
                    </td>
                </tr>
                <tr>
                    <td>
                        <input type="radio" name="rol" value="ResponsablePruebas" />Responsable del equipo de pruebas
                    </td>
                </tr>
                 <tr>
                    <td>
                        <input type="radio" name="rol" value="Programador" />Programador
                    </td>
                </tr>
                 <tr>
                    <td>
                        <input type="radio" name="rol" value="Probador" />Probador
                    </td>
                </tr>
            </table>
            <br>
            <input type="hidden" name="accion" value="registroTrabajador"/>
            <input type="submit" value="Acceder" />
        </form>
    </center>
    </body>
</html>
