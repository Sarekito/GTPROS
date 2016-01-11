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
        <%ArrayList<Proyecto> misProyectos = (ArrayList<Proyecto>) request.getAttribute("misProyectos");%>
        <h1>Tus proyectos abiertos</h1>
        <form action="Controlador" method ="post">
        <table id="tabla1" border="5">
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
                    <!--<input type="hidden" name="accion" value="verProyecto" readonly="readonly" />-->
                    <input type="hidden" name="eleccion" value="<%=i%>" readonly="readonly" />
                    <input id="eligeP" type="button" value="Elegir" />
                </td>
            </tr>asdfasdf
            <%}%>
        </table>
         </form>
        <div id="exxxpecial">
            
        </div>
        </center>
        
    </body>
    <script>
        $(document).ready(function(){
           $("#tabla1").find('tr').each(function{
              $(this).find("td:eq(3)").find("input").click(function(){
                  proy = $(this).parent().find("td:eq(0)").html();
                  alert("Antonio, esto es una prueba de que el proyecto que has seleccionado es: "+proy);
                  $.ajax({
                      type: "POST",
                      url: "Controlador",
                      data: {accion: "dameEtapas", proy: proy},
                      success: function(response){
                          //Ocultamos la tabla 1 y append de lo recibido.
                          $('#tabla1').hide();
                          $('#exxxpecial').append(response);
                      }
                  });
              }); 
           }); 
        });
    </script>
</html>
