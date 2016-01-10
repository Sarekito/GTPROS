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
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
    <center>
        <%ArrayList<Proyecto> misProyectos = (ArrayList<Proyecto>) request.getAttribute("cerrados");%>
        <h1>Tus proyectos abiertos</h1>
         <form action="Controlador" method ="post">
        <table border="5">
            <tr>
                <td>
                   Nombre del Proyecto
                </td>
                
                <td>
                   Fecha de inicico
                </td>
                 <td>
                    Fecha de fin prevista
                </td>
                 <td>
                    Fecha de fin real
                </td>
                <td>
                  
                </td>
            <%for(int i = 0;i<misProyectos.size();i++){%>
            <tr>
                <td>
                    <%=misProyectos.get(i).getNombre()%>
                </td>
                <td>
                    <%=misProyectos.get(i).getFechaInicio()%>
                </td>
                <td>
                    <%=misProyectos.get(i).getFechaFin()%>
                </td>
                 <td>
                    <%=misProyectos.get(i).getFechaFinReal()%>
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
    </center>
</body>
</html>
