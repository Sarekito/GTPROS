<%-- 
    Document   : seleccionProyectos
    Created on : 27-ene-2016, 9:41:49
    Author     : dalonso
--%>

<%@page import="Trabajador.Dominio.Trabajador"%>
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
        <table id="tabla" border = "4">
            <tr>
                <td>Proyecto</td>
                <td>Fecha inicio</td>
                <td>Jefe</td>
                <td>Estado</td>
                <td> </td>
            </tr>
            <%
                Trabajador trabajador = (Trabajador) request.getSession().getAttribute("trabajador");
                HttpSession sesion = request.getSession();
                ArrayList<Proyecto> p = (ArrayList<Proyecto>) sesion.getAttribute("misProyectosActuales");
                Proyecto plan = (Proyecto) sesion.getAttribute("planificar");
                for (int i = 0; i < p.size(); i++) {
                    if(!p.get(i).getEstado().equals("pendiente")){
            %>
            <tr>
                <td><%=p.get(i).getNombre()%></td>
                <td><%=p.get(i).getFechaInicio()%></td>
                <td><%=p.get(i).getJefe()%></td>
                <td><%=p.get(i).getEstado()%></td>
                <td><form action="Controlador" method ="post">
                        <input type="hidden" name="accion" value="infoProyectoAbierto" readonly="readonly" />
                        <input type="hidden" name="eleccion" value="<%=i%>" readonly="readonly" />
                    <input type="submit" value="Elegir" /></td>
                </form>
            </tr>
            <% }%>
            <% }%>
        </table>
        <br>
        <%if (plan != null) {%>
        <hr color = "gray" size="5px">
        <h1>Tus proyectos pendientes de planificacion</h1>
        <table>
            <td>
                nombre:
            </td>
            <td>
                <%=plan.getNombre()%>
            </td>
            <td>
                <form name = "Controlador" method="POST">
                    <input type="hidden" name="accion" value="verProyecto" readonly="readonly" />
                    <input type="submit" value="Planificar" />
                </form>
            </td>
        </table>
        <%}%>
        <br>
        <hr color = "gray" size="5px">
        <br>
        <form name = "Controlador" method="POST">
            <input type="hidden" name="accion" value="actividadesPentiendes" readonly="readonly" />
            <input type="submit" value="             Ver actividades pendientes             " />
        </form>
        <form name = "Controlador" method="POST">
            <input type="hidden" name="accion" value="proyectosCerrados" readonly="readonly" />
            <input type="submit" value="Obtener informes sobre proyectos cerrados" />
        </form>
        <form name = "Controlador" method="POST">
            <input type="hidden" name="accion" value="vacaciones" readonly="readonly" />
            <input type="submit" value="                  Reservar Vacaciones                 " />
        </form>
        <%if(trabajador.getCategoria().getCategoria()==1){%>
        <form name = "Controlador" method="POST">
            <input type="hidden" name="accion" value="sobreesfuerzo" readonly="readonly" />
            <input type="submit" value="        Ver actividades con sobreesfuerzo      " />
        </form>
        <%}%>
        <br>
        <br>
        <br>
        <form name = "Controlador" method="POST">
            <input type="hidden" name="accion" value="cierre"/>
            <input type="submit" value="  Cerrar sesion  " style="color: red" />
        </form>
    </center>
</body>
<script>
    $(document).ready(function () {
    $("#tabla tr").find("td:eq(4)").find("input").click(function () {
    var nombreP = $(this).parent().parent().find("td:eq(0)").text();
            if (nombreP == ""){
    alert("Error en la eleccion del nombre de Proyecto");
    } else{
    $("#eleccion").val(nombreP);
            $("#formulario").submit();
    });
    });
</script>
</html>
