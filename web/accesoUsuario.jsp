<%-- 
    Document   : accesoUsuario
    Created on : 24-dic-2015, 17:43:49
    Author     : antonio
--%>

<%@page import="Trabajador.Dominio.Trabajador"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>GTPROS</title>
    </head>
    <body>
    <center>
        <form name = "Controlador" method="POST">
            <input type="hidden" name="accion" value="actividadesPentiendes" readonly="readonly" />
            <input type="submit" value="Ver actividades pendientes" />
        </form>
        <form name = "Controlador" method="POST">
            <input type="hidden" name="accion" value="vacaciones" readonly="readonly" />
            <input type="submit" value="     Reservar Vacaciones     " />
        </form>
        <br><!--
        <form name="Controlador" method="POST">
            <input type="hidden" name="accion" value="misProyectos2" readonly="readonly" />
            <input type="submit" value="Ver mis proyectos" />
        </form>-->

        <form name = "Controlador" method="POST">
            <input type="hidden" name="accion" value="proyectosCerrados" readonly="readonly" />
            <input type="submit" value="Obtener informes sobre proyectos cerrados" />
        </form>
        <form name = "Controlador" method="POST">
            <input type="hidden" name="accion" value="proyectosAbiertos" readonly="readonly" />
            <input type="submit" value="Obtener informes sobre proyectos abiertos" />
        </form>
        <%  HttpSession sesion = request.getSession();
            Trabajador t = (Trabajador) sesion.getAttribute("trabajador");
            if (t.getCategoria().getCategoria()==1) {
        %>
        <form name = "Controlador" method="POST">
            <input type="hidden" name="accion" value="misProyectos" readonly="readonly" />
            <input type="submit" value=" Ver mis proyectos en curso" />
        </form>
        <br>
        <form name = "Controlador" method="POST">
            <input type="hidden" name="accion" value="sobreesfuerzo" readonly="readonly" />
            <input type="submit" value=" Ver actividades con sobreesfuerzo" />
        </form>
        <form name="Controlador" method="POST">
            <input type="hidden" name="accion" value="finalizarActividades" readonly="readonly"/>
            <input type="submit" value="Finalizacion de actividades" />
        </form>
        <form name="Controlador" method="POST">
            <input type="hidden" name="accion" value="finalizarEtapas" readonly="readonly"/>
            <input type="submit" value="Finalizacion de etapas" />
        </form>
        <form name="Controlador" method="POST">
            <input type="hidden" name="accion" value="finalizarProyecto" readonly="readonly"/>
            <input type="submit" value="Finalizacion de proyecto" />
        </form>
        <%} else {

             }%>
    </center>
</body>
</html>
