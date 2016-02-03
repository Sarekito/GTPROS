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
             var patt = new RegExp(/^(0?[1-9]|[12][0-9]|3[01])[\/\-](0?[1-9]|1[012])[\/\-]\d{4}$/);
        var fechaInicioLocale = $('#inicio').val();
        if (patt.test(fechaInicioLocale)) {
            //Se cumple la fecha; comprobamos meses
            var diaInicio = fechaInicioLocale.subString(0, 2);
            var mesInicio = fechaInicioLocale.subString(3, 5);
            var anoInicio = fechaInicioLocale.subString(6);
            diaInicio = parseInt(diaInicio);
            mesInicio = parseInt(mesInicio);
            anoInicio = parseInt(anoInicio);
            if ((mesInicio == 1) || (mesInicio == 3) || (mesInicio == 5) || (mesInicio == 7) || (mesInicio == 8) || (mesInicio == 10) || (mesInicio == 12)) {
                //MEses de 31 dias
                if ((diaInicio < 32) && (diaInicio > 0)) {
                    //Mu bien, campeon, salte del bucle
                } else {
                    alert("El mes: " + mesInicio + " debe de tener el dia comprendido entre 01 y 31");
                    return false;
                }
            } else if ((mesInicio == 2)) {
                //Febrero
                if (bisiesto(anoInicio)) {
                    if ((diaInicio < 30) && (diaInicio > 0)) {
                        //Mu bien campeon
                    } else {
                        alert("El febrero del año " + anoInicio + " es bisiesto, por lo que debe tener el dia comprendido entre 01 y 29");
                        return false;
                    }
                } else {
                    if ((diaInicio < 29) && (diaInicio > 0)) {
                        //Mu bien campeon
                    } else {
                        alert("El febrero del año " + anoInicio + " no es bisiesto, por lo que debe tener el dia comprendido entre 01 y 28");
                        return false;
                    }
                }
            } else {
                //MEses de 30 dias
                if ((diaInicio < 32) && (diaInicio > 0)) {
                    //Mu bien, campeon, salte del bucle
                } else {
                    alert("El mes: " + mesInicio + " debe de tener el dia comprendido entre 01 y 30");
                    return false;
                }
            }
        } else {
            alert("Introduzca una fecha de inicio correcta");
            return false;
        }
        
        return true;
    }
    function bisiesto(ano) {
        if ((ano % 4 == 0) && ((ano % 100 != 0) || (ano % 400 == 0))) {
            return true;
        } else {
            return false;
        }
    }
    }
    function borra(){
        document.getElementById("inicio").value=""
    }
    
</script>
</html>
