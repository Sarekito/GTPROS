<%-- 
    Document   : planificar
    Created on : 30-dic-2015, 22:53:15
    Author     : antonio
--%>

<%@page import="Proyecto.Dominio.Proyecto"%>
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
        <h1>Planifique este proyecto</h1>
        <%Proyecto proyecto = (Proyecto) request.getSession().getAttribute("planificar");%>
        <form action="Controlador" method ="post" onsubmit="return comprueba();">
            <input type="hidden" name="accion" value="planificado" readonly="readonly" />
            <table border = "5">
                <tr>
                    <td>
                        Nombre del Proyecto
                    </td>
                    <td>
                        Fecha de inicio del proyecto
                    </td>
                </tr>
                <tr>
                    <td>
                        <%=proyecto.getNombre()%>
                    </td>
                    <td>
                        <input id="inicio" type="text" name = "inicio" onclick="borra()" value="dd/mm/aaaa"> 
                    </td>
                    <td>
                        <input type="submit" value="Guardar" />
                    </td>
                </tr>
            </table>
        </form>
    </center>
</body>
<script>
    function comprueba() {
        if ($('#inicio').val() == "" || $('#inicio').val() == "dd/mm/aaaa") {
            return false;
        }
        else{
            return true
        }
    }
    function borra(){
        document.getElementById("inicio").value=""
    }
    
</script>
</html>
