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
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title></title>
    </head>
    <body>
    <center>
        <%ArrayList<Trabajador> tr = (ArrayList<Trabajador>) request.getAttribute("trabajadores");%>
        <h1>Elige el equipo del proyecto</h1>
        <form action="Controlador" method ="post">
            <p>Introducir el porcentaje del trabajador:<input type="number" name="dedicacion" min="1" max="100"></p>
            Despues selecciona un trabajador
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
                        Elegir
                    </td>

                    <%for (int i = 0; i < tr.size(); i++) {%>
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
                        <input type="hidden" name="accion" value="tomarDatos" readonly="readonly" />
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
