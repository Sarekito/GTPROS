<%-- 
    Document   : elegirEtapas
    Created on : 04-ene-2016, 18:40:45
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
        <h1>Introducir datos de la etapa</h1>
        <table>
            <form action="Controlador" method="POST" onsubmit="return controla();">
                <tr>
                    <td>
                        Fecha de inicio de la etapa:
                    </td>
                    <td>
                        <input id="ini" type="text" name="inicio" value="dd/mm/aaaa" onclick="borra1()"/>
                    </td>
                </tr>
        </table>
        <br>
        <br>
        <input type="hidden" name="accion" value="planificarActividades" />
        <input type="submit" value="Planificar Actividades" />
    </form>
</center>
</body>
<script>
    function controla() {
        if ($('#ini').val() == "" || $('#ini').val() == "dd/mm/aaaa") {
            return false;
        }
        else {
            return true;
        }
    }
    function borra1() {
        document.getElementById("ini").value = ""
    }
</script>
</html>
