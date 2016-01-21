<%@page import="Proyecto.Dominio.Actividad"%>
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
        <%  HttpSession sesion = request.getSession();
            ArrayList<Actividad> actividades = (ArrayList<Actividad>) sesion.getAttribute("misActividadesFinal");
        %>
    <center>
        <table>
            <tr>
                <td>Nombre proyecto</td>
                <td>Numero etapa</td>
                <td>ID actividad</td>
                <td>Estado</td>
                <td></td>
            </tr>
            <% for (int i = 0; i < actividades.size(); i++) {%>
            <tr>
                <td><%= actividades.get(i).getNombre()%></td>
                <td><%= actividades.get(i).getNumero()%></td>
                <td><%= actividades.get(i).getId()%></td>
                <td><%= actividades.get(i).getEstado()%></td>
                <td><input type="button" value="Finalizar actividad" <% if(actividades.get(i).getEstado()!="realizando"){%> disabled="disabled" <% } %>></td>
            </tr>
            <%}%>
        </table>

        <form id="formulario" method="post" action="Controlador">
            <input type="hidden" name="accion" value="finalizarActividadElegida">
            <input type="hidden" name="actividadElegida" id="chosenA">
            <input type="hidden" name="etapaElegida" id="chosenE">
            <input type="hidden" name="proyectoElegida" id="chosenP">
        </form>

        <form action="Controlador"  method ="post">
            <input type="hidden" name="accion" value="aAcceso">
            <input type="submit" value="A menu principal">
        </form>


    </center>
</body>
<script>
     $(document).ready(function (e) {
         $('table').find('tr').each(function(){
            $(this).find('td:eq(4)').find('input').click(function(){
                var miActividad = $(this).parentsUntil('tr').find('td:eq(0)').html();
                var miEtapa = $(this).parentsUntil('tr').find('td:eq(1)').html();
                var miProyecto = $(this).parentsUntil('tr').find('td:eq(2)').html();
                $('#chosenA').val(miActividad);
                $('#chosenE').val(miEtapa);
                $('#chosenP').val(miProyecto);
                //Trigger del submit
                $('#formulario').submit();
            }); 
         });
     });
</script>
</html>
