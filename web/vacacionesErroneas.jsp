<%-- 
    Document   : vacacionesErroneas
    Created on : 25-dic-2015, 9:34:52
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
        <font size = "5" color="red">Los periodos vacacionales deben sumar cuatro semanas, empezar en lunes y no superponerse</font>
        <br><br>
        <form name="Controlador" method ="post" onsubmit="return comprueba();">
            <input type="checkbox" name="periodos" value="ON"/>Solicito las vacaciones en dos periodos
            <table>
                <tr>
                    <td>
                        Introduce el "lunes" de inicio del primer periodo
                    </td>
                </tr>
                <tr>
                    <td>
                        <input id="fecha1" type="text" name ="fecha1" value="dd/mm/aaaa" onclick="borra1()">
                    </td>
                </tr>
                <tr>
                    <td>
                        Introduce el numero de semanas
                    </td>
                </tr>
                <tr>
                    <td>
                        <input type="radio" name="semanas1" value="1" checked="checked"  />1
                    </td>
                </tr>
                <tr>
                    <td>
                        <input type="radio" name="semanas1" value="2" />2
                    </td>
                </tr>
                <tr>
                    <td>
                        <input type="radio" name="semanas1" value="3" />3
                    </td>
                </tr>
                <tr>
                    <td>
                        <input type="radio" name="semanas1" value="4" />4
                    </td>
                </tr>
                <tr>
                    <td>
                        Introduce el "lunes" de inicio del segundo periodo
                    </td>
                </tr>
                <tr>
                    <td>
                        <input id="fecha2" type="text" name ="fecha2" value="dd/mm/aaaa" onclick="borra1()">
                    </td>
                </tr>
                <tr>
                    <td>
                        Introduce el numero de semanas
                    </td>
                </tr>
                <tr>
                    <td>
                        <input type="radio" name="semanas2" value="1"   />1
                    </td>
                </tr>
                <tr>
                    <td>
                        <input type="radio" name="semanas2" value="2" />2
                    </td>
                </tr>
                <tr>
                    <td>
                        <input type="radio" name="semanas2" value="3" />3
                    </td>
                </tr>
                <tr>
                    <td>
                        <input type="radio" name="semanas2" value="4" />4
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
<script>
    function comprueba() {
        if ($('#fecha1').val() == "") {
            alert("Error en el periodo vacacional 1");
            return false;
        }
        return true;
    }
    function borra1() {
        document.getElementById("fecha1").value = ""
    }
    function borra2() {
        document.getElementById("fecha2").value = ""
    }
</script>
</html>
