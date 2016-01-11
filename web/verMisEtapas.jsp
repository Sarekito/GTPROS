

<%@page import="Proyecto.Dominio.Etapa"%>
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
        <%      
            HttpSession sesion = request.getSession();
            ArrayList<Etapa> etapas = (ArrayList<Etapa>) sesion.getAttribute("etapasP");
        %>
        <center>
            <form action="Controlador" method ="post">
            <table>
                <tr>
                    <td>Nombre</td>
                    <td>Numero</td>
                    <td>Fecha inicio</td>
                    <td>Fecha fin</td>
                    <td>Fecha fin real</td>
                    <td>Estado</td>
                    <td>Operaciones</td>
                </tr>
                <% for(int i=0; i<etapas.size(); i++){%>
                <tr>
                    <td><% etapas.get(i).getNombre(); %></td>
                    <td><% etapas.get(i).getNumero(); %></td>
                    <td><% etapas.get(i).getFechaInicio(); %></td>
                    <td><% etapas.get(i).getFechaFin(); %></td>
                    <td><% etapas.get(i).getFechaFinReal(); %></td>
                    <td><% etapas.get(i).getEstado(); %></td>
                    <td><input type="hidden" name="eleccion" value=<%=i%> readonly="readonly"/><input type="hidden" name="accion" value="elegirEtapaA" readonly="readonly"/><input value="Seleccionar" type="submit" <% if(etapas.get(i).getEstado().equals("Finalizada")){%> disabled="true" <%}%>></td>
                </tr>
                <% }%>
            </table>
            </form>
        </center>
    </body>
    <script>
        
    </script>
</html>
