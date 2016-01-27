<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <script src="./js/jquery-1.12.0.js"></script>
        <title>GTPROS</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
    </head>
    <body>
    <center>
        <font size ="5">Bienvenido a GTPROS</font>
        <br/>
        <%
            String error = (String) request.getAttribute("error");

            if (error != null) {
        %>
        <table>
            <tr>
                <td><%=error%></td>
            </tr>
        </table>
        <%
            }
        %>
        <br/>
        <form action="Controlador" method ="post">
            <table>
                <tr>
                    <td>Usuario</td>
                    <td><input type="text" name="usuario" size="50" /></td>
                </tr>
                <tr>
                    <td>Contraseña</td>
                    <td><input type="password" name="clave" size="50" /></td>
                </tr>
            </table>
            <br/>
            <input type="hidden" name="accion" value="Acceso"/>
            <input type="submit" value="Entrar" />
        </form>
        <!--<a href="indexAdministrador.jsp">Entrar como administrador</a>-->
        
        <br>
        <br>
        <br><a href="Documentacion/Diseno.pdf">Documento de Analisis y Diseño</a>
        <br><a href="Documentacion/Documento de Seguimiento.pdf">Documento de Seguimiento</a>
        <br><a href="Documentacion/Implementacion.pdf">Documento de Implementacion</a>
        <br><a href="Documentacion/Manual de Instalacion.pdf">Manual de Instalacion</a>
        <br><a href="Documentacion/Manual de usuario.pdf">Manual de usuario</a>
    </center>
</body>
</html>
