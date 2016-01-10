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
        <%String fallo = (String) request.getAttribute("fallo");%>
        <%if(fallo.equals("si")){%>
        <h2><font color = "red">Los datos no son correctos</font></h2>
        <h1>Planifique este proyecto</h1>
        <%}%>
        <%Proyecto proyecto = (Proyecto) request.getAttribute("proyecto");%>
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
                    <td>
                        Fecha de fin de proyecto estimada
                    </td>
                </tr>
                <tr>
                    <td>
                        <%=proyecto.getNombre()%>
                    </td>
                    <td>
                        <input id="inicio" type="date" name = "inicio">
                    </td>
                    <td>
                        <input id="fin" type="date" name = "fin">
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
    function comprueba(){
        if($('#inicio').val()==""){
            return false;
        }
        if($('#fin').val()==""){
            return false;
        }
        if(($('#fin').val()!="")&&($('#inicio').val()!="")){
            return true;
        }
    }
</script>
</html>
