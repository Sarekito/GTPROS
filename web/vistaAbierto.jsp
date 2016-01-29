<%-- 
    Document   : vistaCerrados
    Created on : 10-ene-2016, 10:33:49
    Author     : antonio
--%>

<%@page import="java.util.Date"%>
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
        <%Trabajador t = (Trabajador) request.getSession().getAttribute("trabajador");%>
        <%ArrayList<Etapa> etapas = (ArrayList<Etapa>) request.getSession().getAttribute("etapas");%>
        <%Proyecto p = (Proyecto) request.getSession().getAttribute("proyecto");%>
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
                <%if (p.getJefe().equals(t.getUser())) {%>
                <td>
                    Cierre
                </td>
                <%}%>
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
                    <%="realizando"%>
                    <%} else {%>
                    <%=etapas.get(i).getDuracionReal()%>
                    <%}%>
                </td>
                <%if (p.getJefe().equals(t.getUser())) {%>
                <td>
                    <%if (!etapas.get(i).getEstado().equals("finalizado")) {%>
                    <form action="Controlador" method="POST">
                        <input type="text" hidden="hidden" value="<%=i%>" name="elegir">
                        <input type="hidden" name="accion" value="finalizarEtapas" readonly="readonly" />
                        <input type="submit" value="Cerrar" />
                    </form>
                    <%} else {%>
                    Etapa cerrada
                    <%}%>
                </td>
                <%}%>
            </tr>
            <%}%>
            <%ArrayList<Actividad> actividades = (ArrayList<Actividad>) request.getSession().getAttribute("actividades");%>
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
                <td>
                    Ver informes
                </td>
                <%if (p.getJefe().equals(t.getUser())) {%>
                <td>
                    Cierre
                </td>
                <%}%>
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
                    <%if (actividades.get(i).getDuracionReal() == null) {
                            if (actividades.get(i).getFechaComienzo().after(new Date())) {%>
                    Aun no ha comenzado
                    <%} else {%>
                    <%="Aun no acabo"%>
                    <%}%>
                    <%} else {%>
                    <%=actividades.get(i).getDuracionReal()%>
                    <%}%>
                </td>
                <td>
                    <%=actividades.get(i).getTipoRol()%>
                </td>
                <td>

                    <form action="Controlador" method="POST">
                        <input type="text" hidden="hidden" name="elegida" value="<%=i%>">
                        <input type="hidden" name="accion" value="mostrarInformes" readonly="readonly" />
                        <input type="submit" value="Ver" />
                    </form>

                </td>
                <%if (p.getJefe().equals(t.getUser()) && actividades.get(i).getEstado().equals("realizando") && !actividades.get(i).getFechaComienzo().after(new Date())) {%>
                <td>

                    <form action="Controlador" method="POST">
                        <input type="text" hidden="hidden" value="<%=i%>" name="elegir">
                        <input type="hidden" name="accion" value="finalizarActividad" readonly="readonly" />
                        <input type="submit" value="Cerrar" />
                    </form>

                </td>
                <%}
                    if ((p.getJefe().equals(t.getUser()) && actividades.get(i).getEstado().equals("finalizado"))) {%>
                <td>ya esta cerrada</td>
                <%}%>

                <%if ((p.getJefe().equals(t.getUser()) && actividades.get(i).getEstado().equals("Predecesoras en realizacion"))) {%>
                <td>predecesoras pendientes</td>
                <%}%>

            </tr>
            <%}%>
        </table>
        <br><br>
        <form action="Controlador" method="POST">
            <input type="hidden" name="accion" value="finalizarProyecto" readonly="readonly" />
            <input type="submit" value="Cerrar proyecto" />
        </form>
        <br><br><br><br>
        <form action="Controlador" method="POST">
            <input type="text" name="usuario" value="<%=t.getUser()%>" readonly="readonly" hidden="hidden"/>
            <input type="text" name="clave" value="<%=t.getPassword()%>" readonly="readonly" hidden="hidden" />
            <input type="hidden" name="accion" value="Acceso" readonly="readonly" />
            <input type="submit" value="Ir a inicio" />
        </form>
    </center>
</body>
</html>
