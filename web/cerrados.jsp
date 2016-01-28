<%-- 
    Document   : verProyectos
    Created on : 30-dic-2015, 21:17:54
    Author     : antonio
--%>

<%@page import="Trabajador.Dominio.Trabajador"%>
<%@page import="Proyecto.Dominio.Proyecto"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>GTPROS</title>
    </head>
    <body>
    <center>
        <%Trabajador t = (Trabajador) request.getSession().getAttribute("trabajador");%>
        <%ArrayList<Proyecto> misProyectos = (ArrayList<Proyecto>) request.getAttribute("cerrados");%>
        <h1>Proyectos</h1>
        <form action="Controlador" method ="post">
            <table border="5">
                <tr>
                    <td>
                        Nombre del Proyecto
                    </td>

                    <td>
                        Duracion prevista
                    </td>

                    <td>
                        Duracion real
                    </td>
                    <td>

                    </td>
                    <%for (int i = 0; i < misProyectos.size(); i++) {%>
                <tr>
                    <td>
                        <%=misProyectos.get(i).getNombre()%>
                    </td>
                    <td>
                        <%=misProyectos.get(i).getDuracion()%>
                    </td>
                    <td>
                        <%=misProyectos.get(i).getDuracionReal()%>
                    </td>
                    <td>
                        <input type="hidden" name="accion" value="infoProyectoCerrado" readonly="readonly" />
                        <input type="hidden" name="eleccion" value="<%=i%>" readonly="readonly" />
                        <input type="submit" value="Elegir" />
                    </td>
                </tr>
                <%}%>
            </table>
        </form>
        <br>
        <form action="Controlador" method="POST">
            <input type="text" name="usuario" value="<%=t.getUser()%>" readonly="readonly" hidden="hidden"/>
            <input type="text" name="clave" value="<%=t.getPassword()%>" readonly="readonly" hidden="hidden" />
            <input type="hidden" name="accion" value="Acceso" readonly="readonly" />
            <input type="submit" value="Ir a inicio" />
        </form>
    </center>
</body>
</html>
