<%@page import="Proyecto.Dominio.Actividad"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>GTPROS</title>
    </head>
    <body>
        <%  HttpSession sesion = request.getSession();
        ArrayList<Actividad> actividades = (ArrayList<Actividad>) sesion.getAttribute("actividadesChosen");
            
        %>
    <center>
        <form action="Controlador" method ="post">
        <table>
            <tr>
                <td>Nombre Actividad</td>
                <td>Numero</td>
                <td>ID</td>
                <td>Acciones</td>
            </tr>
            <% for(int i=0; i<actividades.size(); i++){%>
            <tr>
                <td><% actividades.get(i).getNombre(); %></td>
                <td><% actividades.get(i).getNumero(); %></td>
                <td><% actividades.get(i).getId(); %></td>
                <td><input type="hidden" name="eleccion" value=<%=i%> readonly="readonly"/>
                    <input type="hidden" name="accion" value="elegirActividadD" readonly="readonly"/>
                    <input type="submit" value="Seleccionar" <% if(actividades.get(i).getEstado().equals("Finalizada")){%> disabled="true" <%}%>></td>
            </tr>
            <%}%>
        </table>
        </form>
    </center>
    </body>
</html>
