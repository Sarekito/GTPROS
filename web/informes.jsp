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
        <%ArrayList<Tarea> tareas = (ArrayList<Tarea>) request.getAttribute("tareas");%>
        <%ArrayList<ArrayList<Tarea>> tas = new ArrayList<ArrayList<Tarea>>();%>
        <%Trabajador trabajador = (Trabajador)request.getAttribute("trabajador");%>
    <center>
        <h1>Etapas</h1>
        <%for (int j = 0; j < tareas.size(); j = j + 6) {%>
        <h3>Semana: <%=tareas.get(j).getSemana()%> Trabajador: <%=tareas.get(j).getTrabajador()%> Estado: <%=tareas.get(j).getEstado()%></h3>
        <table border = "1">
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
            </tr>
            <%}%>
        </table>
        <br>
        <%if (tareas.get(j).getEstado().equals("Enviado")) {%>
        <form action="Controlador" method="POST">
            <input type="hidden" name="tareasAcepto" value="<%=j%>" readonly="readonly" />
            <input type="hidden" name="accion" value="aprobarInforme" readonly="readonly" />
            <input type="submit" value="Aceptar informe" />
        </form>
        <%}%>
        <%}%>
    </center>
</body>
</html>
