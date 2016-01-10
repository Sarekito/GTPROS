<%-- 
    Document   : trabajadorCreado
    Created on : 26-dic-2015, 23:14:43
    Author     : antonio
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <script src="./js/jquery-1.12.0.js"></script>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>GTPROS</title>
    </head>
    <body>
    <center>
        <br>
        <font size ="5" color="red">Ese nombre de usuario ya esta asignado</font>
        <br>
        <br>
        <form action="Controlador" method ="post" onsubmit="return comprueba();">
            <table>
                <tr>
                    <td>
                        Usuario
                    </td>
                    <td>
                        <input id="user" type="text" name="usuario" size="50" />
                    </td>
                </tr>
                <tr>
                    <td>
                        Contraseña
                    </td>

                    <td>
                        <input id="pass" type="text" name="clave" size="50" />
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
    <script>
        function comprueba(){
            if($('#user').val()==""){
                alert("Error en el usuario");
                return false;
            }
            if($('#pass').val()==""){
                alert("Error en la contraseña");
                return false;
            }
            return true;
        }
    </script>
</html>
