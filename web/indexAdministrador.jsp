<!DOCTYPE html>
<!--
To change this license header, choose License Headers in Project Properties.
To change this template file, choose Tools | Templates
and open the template in the editor.
-->
<html>
    <head>
        <title>GTPROS</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
    </head>
    <body>
    <center>
        <font size ="5">Bienvenido a GTPROS. Solo administradores</font>
        <br>
        <br>
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
                        Contrase�a
                    </td>

                    <td>
                        <input type="password" name="clave" size="50" />
                    </td>
                </tr>
            </table>
             <br>
            <input type="hidden" name="accion" value="entrarAdmin"/>
            <input type="submit" value="Acceder" />
        </form>
    </center>
</body>
</html>
