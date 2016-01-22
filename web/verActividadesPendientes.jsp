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
    <center>
        <%  HttpSession sesion = request.getSession();
            ArrayList<Actividad> actividades = (ArrayList<Actividad>) sesion.getAttribute("misActividadesPendientes");
        %>
        <table>
            <tr>
                <td>Nombre proyecto</td>
                <td>Numero etapa</td>
                <td>ID actividad</td>
                <td>Descripcion</td>
                <td>Duracion</td>
                <td>Duracion real</td>
                <td>Fecha comienzo</td>
                <td>Fecha fin</td>
                <td>Fecha fin real</td>
                <td>Estado</td>
                <td>Tipo rol</td>
                <td></td>
            </tr>
            <% for (int i = 0; i < actividades.size(); i++) {%>
            <tr>
                <td><%= actividades.get(i).getNombre()%></td>
                <td><%= actividades.get(i).getNumero() %></td>
                <td><%= actividades.get(i).getId() %></td>
                <td><%= actividades.get(i).getDescripcion() %></td>
                <td><%= actividades.get(i).getDuracion() %></td>
                <td><%= actividades.get(i).getDuracionReal() %></td>
                <td><%= actividades.get(i).getFechaComienzo() %></td>
                <td><%= actividades.get(i).getFechaFin() %></td>
                <td><%= actividades.get(i).getFechaFinReal() %></td>
                <td><%= actividades.get(i).getEstado() %></td>
                <td><%= actividades.get(i).getTipoRol().getRol() %></td>
                <td><input type="button" value="Introducir datos actividad"></td>
            </tr>
            <%}%>
        </table>
        
        <form id="formulario" method="post" action="Controlador">
            <input type="hidden" name="accion" value="aIntroducirDatosActividad">
            <input type="hidden" name="chosenProject" id="chosenP">
            <input type="hidden" name="chosenEtapa" id="chosenE">
            <input type="hidden" name="chosenActividad" id="chosenA">
        </form>
        
        <form action="Controlador"  method ="post">
            <input type="hidden" name="accion" value="aAcceso">
            <input type="submit" value="A menu principal">
        </form>
    </center>
</body>
<script>
     $(document).ready(function() {
         $('table').find('tr').each(function(){
            $(this).find('td:eq(11)').find('input').click(function(){
                var miProyecto = $(this).parentsUntil('tr').parent().find('td:eq(0)').html();
                var miEtapa = $(this).parentsUntil('tr').parent().find('td:eq(1)').html();
                var miActividad = $(this).parentsUntil('tr').parent().find('td:eq(2)').html();
                alert("mi proyecto es: "+miProyecto+", mi etapa es: "+miEtapa+" y mi actividad elegida es: "+miActividad);
                $('#chosenP').val(miProyecto);
                $('#chosenE').val(miEtapa);
                $('#chosenA').val(miActividad);
                //Trigger del submit
                $('#formulario').submit();
            }); 
         });
     });
</script>
</html>
