<%-- 
    Document   : verProyectos
    Created on : 30-dic-2015, 21:17:54
    Author     : antonio
--%>

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
        <%ArrayList<Proyecto> misProyectos = (ArrayList<Proyecto>) request.getAttribute("misProyectos");%>
        <h1>Tus proyectos abiertos</h1>
        <table border="5">
            <tr>
                <td>
                    Nombre del Proyecto
                </td>

                <td>
                    Estado
                </td>
                <td>
                    Gestor de Proyecto
                </td>
                <td>

                </td>
                <%for (int i = 0; i < misProyectos.size(); i++) {%>
            <tr>
                <td>
                    <%=misProyectos.get(i).getNombre()%>
                </td>
                <td>
                    <%=misProyectos.get(i).getEstado()%>
                </td>
                <td>
                    <%=misProyectos.get(i).getJefe()%>
                </td>
                <td>
                    <form action="Controlador" method ="post">
                        <input type="hidden" name="accion" value="verProyecto" readonly="readonly" />
                        <input type="hidden" name="eleccion" value="<%=i%>" readonly="readonly" />
                        <input type="submit" value="Elegir" />
                    </form>
                </td>
                <td>
                    <form action="Controlador" method ="post">
                        <input type="hidden" name="accion" value="indiceInformes" readonly/>
                        <input type="hidden" name="nombreProyecto" value="<%=misProyectos.get(i).getNombre()%>" readonly/>
                        <input type="submit" value="Informes de actividades" />
                    </form>
                </td>
            </tr>
            <%}%>
        </table>
    </center>
</body>
</html>
