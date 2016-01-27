<%-- 
    Document   : seleccionProyectos
    Created on : 27-ene-2016, 9:41:49
    Author     : dalonso
--%>

<%@page import="Proyecto.Dominio.Proyecto"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <script src="./js/jquery-1.12.0.js"></script>
        <title>GTPROS</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
    </head>
    <body>
    <center>
        <h1>Tus proyectos abiertos</h1>
        <br>
        <table id="tabla">
            <tr>
                <td>Proyecto</td>
                <td>Fecha inicio</td>
                <td>Jefe</td>
                <td>Estado</td>
                <td> </td>
            </tr>
            <%
                HttpSession sesion = request.getSession();
                ArrayList<Proyecto> p = (ArrayList<Proyecto>) sesion.getAttribute("misProyectosActuales");
                for (int i = 0; i < p.size(); i++) {
            %>
            <tr>
                <td><%=p.get(i).getNombre()%></td>
                <td><%=p.get(i).getFechaInicio()%></td>
                <td><%=p.get(i).getJefe()%></td>
                <td><%=p.get(i).getEstado()%></td>
                <td><input type="button" value="Seleccionar"></td>
            </tr>
            <% }%>
        </table>
    </center>
    <form id="formulario" action="Controlador" method ="post">
        <input type="hidden" name="accion" value="adquiereRol" readonly="readonly" />
        <input type="hidden" name="eleccion" id="eleccion" readonly="readonly" />
    </form>
</body>
<script>
    $(document).ready(function () {
        $("#tabla tr").find("td:eq(4)").find("input").click(function () {
            var nombreP = $(this).parent().parent().find("td:eq(0)").text();
            if(nombreP==""){
                alert("Error en la eleccion del nombre de Proyecto");
            } else{
                 $("#eleccion").val(nombreP);
                 $("#formulario").submit();
        });
    });
</script>
</html>
