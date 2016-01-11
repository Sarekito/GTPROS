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
        <%ArrayList<Proyecto> misProyectos = (ArrayList<Proyecto>) request.getAttribute("misProyectosPP");%>
        <h1>Tus proyectos abiertos</h1>
         <form action="Controlador" method ="post">
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
            <%for(int i = 0;i<misProyectos.size();i++){%>
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
                    <input type="hidden" name="accion" value="verEtapasProyectoUsuarioNoJefe" readonly="readonly" />
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
