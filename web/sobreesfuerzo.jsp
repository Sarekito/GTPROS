<%-- 
    Document   : sobreesfuerzo
    Created on : 10-ene-2016, 8:35:37
    Author     : antonio
--%>

<%@page import="Proyecto.Dominio.Actividad"%>
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
        <%ArrayList<Actividad> se = (ArrayList<Actividad>)request.getAttribute("sobreesfuerzo");%>
        <table>
             <tr>
             <td>
                 Nombre de proyecto
             </td>
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
                 Durscion estimada
             </td>
             <td>
                 Duracion real
             </td>
         </tr>
         <%for (int i = 0;i<se.size();i++){%>   
         <tr>
             <td>
                 <%=se.get(i).getNombre()%>
             </td>
             <td>
                 <%=se.get(i).getNumero()%>
             </td>
             <td>
                 <%=se.get(i).getId()%>
             </td>
             <td>
                 <%=se.get(i).getDescripcion()%>
             </td>
             <td>
                 <%=se.get(i).getDuracion()%>
             </td>
             <td>
                 <%=se.get(i).getDuracionReal()%>
             </td>
         </tr>
         <%}%>
        </table>
        
    </center>
    </body>
</html>
