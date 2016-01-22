<%@page import="Proyecto.Dominio.Proyecto"%>
<%@page import="java.util.ArrayList"%>
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
        <%
            HttpSession sesion = request.getSession();
            String miProyecto = (String) sesion.getAttribute("introDatosP");
            String miEtapa = (String) sesion.getAttribute("introDatosE");
            String miActividad = (String) sesion.getAttribute("introDatosA");
        %>
        <h1>Va a introducir datos sobre:</h1>
        <label><%=miProyecto%></label>
        <label><%=miEtapa%></label>
        <label><%=miActividad%></label>
        <table id="tabla1" border="5">
            <tr>
                <td>
                    Numero tarea
                </td>
                <td>
                    <input id="numeroTarea">
                </td>
            </tr>
            <tr>
                <td>
                    Semana
                </td>
                <td>
                    <input type="date" id="semana">
                </td>
            </tr>
            <tr>
                <td>
                    Tipo tarea
                </td>
                <td>
                    <input id="tipoTarea">
                </td>
            </tr>
            <tr>
                <td>
                    Duracion
                </td>
                <td>
                    <input id="duracion">
                </td>
            </tr>
        </table>
    
   
    <div id="error">
    </div>
</center>
<form id="formulario" action="Controlador" method ="post">
    <input type="hidden" name="accion" value="datosIntroducidosCorrectamente">
    <input type="hidden" id="mitarea" name="mitarea">
    <input type="date" hidden="hidden" id="misemana" name="misemana">
    <input type="hidden" id="mitipoTarea" name="mitipoTarea">
    <input type="hidden" id="miduracion" name="miduracion">
    <input type="submit" id="guardarTarea" value="Guardar">
</form>
</body>
<script>
    $(document).ready(function () {
        $('#guardarTarea').click(function () {
            var tarea = $('#numeroTarea').val();
            var semana = $('#semana').val();
            var tipoTarea = $('#tipoTarea').val();
            var duracion = $('#duracion').val();
            
            if ((tarea != "") && (semana != "") && (tipoTarea != "") && (duracion != "")) {
                $('#mitarea').val(tarea);
                $('#semana').val(semana);
                $('#tipoTarea').val(tipoTarea);
                $('#duracion').val(duracion);

                $('#formulario').submit();
            }
            else {
                $('#error').append("<font size =\"5\" color=\"red\">Por favor, reyene todos los campos para completar la tarea</font>");
            }
        });
    });
</script>
</html>
