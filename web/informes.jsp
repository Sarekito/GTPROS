<%@page import="Proyecto.Dominio.ActividadTrabajador"%>
<%@page import="Proyecto.Dominio.Actividad"%>
<%@page import="Proyecto.Dominio.Proyecto"%>
<%@page import="Trabajador.Dominio.Trabajador"%>
<%@page import="Proyecto.Dominio.TipoTarea"%>
<%@page import="Proyecto.Dominio.Tarea"%>
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
        <%ArrayList<Tarea> tareas = (ArrayList<Tarea>) request.getSession().getAttribute("tareas");%>
        <%Proyecto p = (Proyecto) request.getSession().getAttribute("proyecto");%>
        <%ArrayList<ArrayList<Tarea>> tas = new ArrayList<ArrayList<Tarea>>();%>
        <%Actividad ac = (Actividad) request.getSession().getAttribute("actividad");%>
        <%Trabajador trabajador = (Trabajador) request.getSession().getAttribute("trabajador");%>

        <h1>Etapas</h1> 
        <%ArrayList<Integer> at = (ArrayList<Integer>) request.getSession().getAttribute("horas");%>
        <%for (int j = 0; j < tareas.size(); j = j + 6) {%>
        <%if (!p.getJefe().equals(trabajador.getUser()) && !ac.getEstado().equals("finalizado")) {%>
        <%if(!tareas.get(j).getEstado().equals("Aceptado")){%>
        <h4>Horas restantes en esta semana: <%=at.get((int) j / 6)%></h4>
        <%}%><%}%>
        <h3>Semana: <%=tareas.get(j).getSemana()%> Trabajador: <%=tareas.get(j).getTrabajador()%> Estado: <%=tareas.get(j).getEstado()%></h3>
        <table border = "1">
            <form action="Controlador" method="POST">
                <input type="text" value ="<%=at.get((int) j / 6)%>" name="horas" hidden="hidden"/>
                <tr>
                    <td>
                        Etapa
                    </td>
                    <td>
                        Actividad
                    </td>
                    <td>
                        Tipo de tarea
                    </td>
                    <td>
                        Duracion
                    </td>
                    <%if (!p.getJefe().equals(trabajador.getUser()) && !ac.getEstado().equals("finalizado") && !tareas.get(j).getEstado().equals("Aceptado")) {%>
                    <td>
                        Nueva aportacion
                    </td>
                    <%}%>
                </tr>
                <%for (int i = j; i < j + 6; i++) {%>
                <tr>
                    <td>
                        <%=tareas.get(i).getNumeroEtapa()%>
                    </td>
                    <td>
                        <%=tareas.get(i).getIdActividad()%>
                    </td>
                    <td>
                        <%=TipoTarea.getLegible(tareas.get(i).getTipoTarea().toString())%>
                    </td>
                    <td>
                        <%=tareas.get(i).getDuracion()%>
                    </td>
                    <%if (!p.getJefe().equals(trabajador.getUser()) && !ac.getEstado().equals("finalizado") && !tareas.get(j).getEstado().equals("Aceptado")) {%>
                    <td>
                        <input type="number" max="40" min="0" name="get-<%=i % 6%>">
                    </td>
                    <%}%>
                </tr>
                <%}%>
        </table>
        <br>
        <%if (!p.getJefe().equals(trabajador.getUser()) && !ac.getEstado().equals("finalizado") && !tareas.get(j).getEstado().equals("Aceptado")) {%>
        <input type="text" name="accion" value="guardarInforme" readonly="readonly" hidden="hidden" />
        <input type="text" name="inicio" value="<%=j%>" hidden="hidden"/>
        <input type="submit" value="Guardar informe" />
        <%}%>
    </form>
    <br>
    <%if (tareas.get(j).getEstado().equals("Enviado") && trabajador.getUser().equals(p.getJefe())) {%>
    <form action="Controlador" method="POST">
        <input type="hidden" name="tareasAcepto" value="<%=j%>" readonly="readonly" />
        <input type="hidden" name="accion" value="aprobarInforme" readonly="readonly" />
        <input type="submit" value="Aceptar informe" />
    </form>
    <%}%>
    <%}%>
    <br>
    <form action="Controlador" method="POST">
        <input type="text" name="usuario" value="<%=trabajador.getUser()%>" readonly="readonly" hidden="hidden"/>
        <input type="text" name="clave" value="<%=trabajador.getPassword()%>" readonly="readonly" hidden="hidden" />
        <input type="hidden" name="accion" value="Acceso" readonly="readonly" />
        <input type="submit" value="Ir a inicio" />
    </form>
</center>
</body>
</html>
