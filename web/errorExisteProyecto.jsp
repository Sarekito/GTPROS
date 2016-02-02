<%-- 
    Document   : registroDeProyecto
    Created on : 26-dic-2015, 23:43:58
    Author     : antonio
--%>

<%@page import="Trabajador.Dominio.Trabajador"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <script src="./js/jquery-1.12.0.js"></script>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <%ArrayList<Trabajador> jefes = (ArrayList<Trabajador>)request.getSession().getAttribute("jefes");%>
        <title>GTPROS</title>
    </head>
    <body>
    <center>
        <font size ="5" color="red">Error, ya hay un proyecto con ese nombre</font>
        <br>
        <br>
        <form action="Controlador" method ="post" onsubmit="return asdf();">
            <table>
                <tr>
                    <td>
                        Nombre del Proyecto
                    </td>
                    <td>
                        <input id="nombre" type="text" name="nombre" />
                    </td>
                </tr>
                <tr>
                    <td>Gestor del Proyecto 
                        <select id="nivel" name="gestor">
                            <%for (int i =0;i<jefes.size();i++){%>
                            <option value="<%=jefes.get(i).getUser()%>">
                                <%=jefes.get(i).getUser()%>
                            </option>
                            <%}%>
                        </select>
                    </td>
                </tr>
            </table>
            <br>
            <input type="hidden" name="accion" value="registroProyecto"/>
            <input type="submit" value="Registro" />
        </form>
    </center>
</body>
<script>
    function asdf() {
        if ($('#nombre').val() == "") {
            alert("Nombre de proyecto incorrecto");
            return false;
        }
        if ($("#jefe").val() == "") {
            alert("Nombre de Jefe de Proyecto incorrecto");
            return false;
        }
        return true;
    }
</script>