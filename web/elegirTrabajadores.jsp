<%-- 
    Document   : elegirTrabajadores
    Created on : 31-dic-2015, 0:11:27
    Author     : antonio
--%>

<%@page import="Trabajador.Dominio.Trabajador"%>
<%@page import="java.util.ArrayList"%>
<%@page import="Trabajador.Dominio.Rol"%>
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
        <%ArrayList<Trabajador> tr = (ArrayList<Trabajador>) request.getAttribute("trabajadores");%>
        <h1>Elige el equipo del proyecto</h1>
        <p>Introducir el porcentaje del trabajador y pulsar elegir</p>
        <table border="5">
            <tr>
                <td>
                    Nombre
                </td>
                <td>
                    Rol
                </td>
                <td>
                    Categoria
                </td>
                <td>
                    Porcentaje
                </td>
                <td>
                    Elegir
                </td>

                <%for (int i = 0; i < tr.size(); i++) {%>
            <form action="Controlador" method="POST" onsubmit="return checkit();">
                </tr>
                <tr>
                    <td>
                        <%=tr.get(i).getUser()%>
                    </td>
                    <td>
                        <%=tr.get(i).getTipoRol().getRol()%>
                    </td>
                    <td>
                        <%=tr.get(i).getCategoria().getCategoria()%>
                    </td>
                    <td>
                        <input id="dedicacion" type="number" name="dedicacion" min="1" max="100">
                    </td>
                    <td>
                        <input type="hidden" name="accion" value="tomarDatos" readonly="readonly" />
                        <input type="hidden" name="eleccion" value=<%=i%> readonly="readonly" />
                        <input type="submit" value="Elegir" />
                    </td>
                </tr>
            </form>
            <%}%>
        </table>
    </center>
</body>
<script>
    function checkit(){
        if($('#dedicacion').val()==""){
            return false;
        } else{
            return true;
        }
    }
</script>
</html>
