<%-- 
    Document   : elegirEtapas
    Created on : 04-ene-2016, 18:40:45
    Author     : antonio
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
    <center>
        <h2>La etapa a planificar no puede acabar antes del fin de la anterior</h2>
        <h1>Introducir datos de la etapa</h1>
        <table>
            <form action="Controlador" method="POST">
                <tr>
                    <td>
                        Fecha de inicio de la etapa:
                    </td>
                    <td>
                        <input type="date" name="inicio"/>
                    </td>
                </tr>
                <tr>
                    <td>
                        Fecha de fin de la etapa:   
                    </td>
                    <td>
                        <input type="date" name="fin"/>
                    </td>
                </tr>
        </table>
        <br>
        <br>
        <input type="hidden" name="accion" value="planificarSigsEtapas" />
        <input type="submit" value="Planificar Actividades" />
    </form>
    <br>
    <br>
    <br>
    <form action="Controlador" method="POST">
        <input type="hidden" name="accion" value="finalizarPlanProyecto" readonly="readonly" />
        <input type="submit" value="Finalizar planificacion del proyecto"/>
    </form>
</center>
</body>
</html>
