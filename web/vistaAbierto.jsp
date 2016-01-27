<%-- 
    Document   : vistaCerrados
    Created on : 10-ene-2016, 10:33:49
    Author     : antonio
--%>

<%@page import="Proyecto.Dominio.Proyecto"%>
<%@page import="Trabajador.Dominio.Trabajador"%>
<%@page import="Proyecto.Dominio.Actividad"%>
<%@page import="Proyecto.Dominio.Etapa"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>GTPROS</title>
    </head>
    <body>
        <%Trabajador t = (Trabajador) request.getAttribute("trabajador");%>
        <%ArrayList<Etapa> etapas = (ArrayList<Etapa>) request.getAttribute("etapas");%>
        <%Proyecto p = (Proyecto) request.getAttribute("proyecto");%>
    <center>
        <h1>Etapas</h1>
        <table border = "1">
            <tr>
                <td>
                    Etapa
                </td>
                <td>
                    Duracion prevista
                </td>
                <td>
                    Duracion real
                </td>
                <td>
                    Cierre
                </td>
            </tr>
            <%for (int i = 0; i < etapas.size(); i++) {%>
            <tr>
                <td>
                    <%=etapas.get(i).getNumero()%>
                </td>
                <td>
                    <%=etapas.get(i).getDuracion()%>
                </td>
                <td>
                    <%if (etapas.get(i).getDuracionReal() == null) {%>
                    <%="aun no cerrada"%>
                    <%} else {%>
                    <%=etapas.get(i).getDuracionReal()%>
                    <%}%>
                </td>
                <td>
                    <%if (p.getJefe().equals(t.getUser())) {%>
                    <form action="Controlador" method="POST">
                        <input type="text" hidden="hidden" value="<%=i%>" name="elegir">
                        <input type="hidden" name="accion" value="finalizarEtapas" readonly="readonly" />
                        <input type="submit" value="Cerrar" />
                    </form>
                    <%} else {%>
                    <%="No disponible"%>
                    <%}%>
                </td>
            </tr>
            <%}%>
            <%ArrayList<Actividad> actividades = (ArrayList<Actividad>) request.getAttribute("actividades");%>
        </table>
        <h1>Actividades</h1>
        <table border = "1">
            <tr>
                <td>
                    Etapa
                </td> 
                <td>
                    Actividad
                </td>
                <td>
                    Descripcion
                </td>
                <td>
                    Duracion
                </td>
                <td>
                    Duracion real
                </td>
                <td>
                    Rol
                </td>
            </tr>
            <%for (int i = 0; i < actividades.size(); i++) {%>
            <tr>
                <td>
                    <%=actividades.get(i).getNumero()%>
                </td>
                <td>
                    <%=actividades.get(i).getId()%>
                </td>
                <td>
                    <%=actividades.get(i).getDescripcion()%>
                </td>
                <td>
                    <%=actividades.get(i).getDuracion()%>
                </td>
                <td>
                    <%if (actividades.get(i).getDuracionReal() == null) {%>
                    <%="realizando"%>
                    <%} else {%>
                    <%=actividades.get(i).getDuracionReal()%>
                    <%}%>
                </td>
                <td>
                    <%=actividades.get(i).getTipoRol()%>
                </td>
            </tr>
            <%}%>
        </table>
        <br><br>
        <form action="Controlador" method="POST">
            <input type="hidden" name="accion" value="aAcceso" readonly="readonly" />
            <input type="submit" value="Ir a inicio" />
        </form>
    </center>
</body>
</html>
