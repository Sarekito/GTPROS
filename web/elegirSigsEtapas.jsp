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
        <title>GTPROS</title>
    </head>
    <body>
    <center>
        <h1>Introducir datos de la etapa</h1>
        <table>
            <form action="Controlador" method="POST" onsubmit="return checkitbra();">
                <tr>
                    <td>
                        Fecha de inicio de la etapa:
                    </td>
                    <td>
                        <input id="ini" type="text" name="inicio" value="dd/mm/aaaa" onclick="borra1()"/>
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
<script>
    function checkitbra() {
        if ($('#ini').val() == "") {
            alert("Fecha de inicio incorrecta");
            return false;
        }
        if ($('#fin').val() == "") {
            alert("Fecha de fin incorrecta");
            return false;
        }
         var patt = new RegExp(/^(0?[1-9]|[12][0-9]|3[01])[\/\-](0?[1-9]|1[012])[\/\-]\d{4}$/);
        var fechaInicioLocale = $('#ini').val();
        var fechaFinLocale = $('#fin').val();
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
        
        if (patt.test(fechaFinLocale)) {
            //Se cumple la fecha; comprobamos meses
            var diaFin = fechaFinLocale.subString(0, 2);
            var mesFin = fechaFinLocale.subString(3, 5);
            var anoFin = fechaFinLocale.subString(6);
            diaFin = parseInt(diaFin);
            mesFin = parseInt(mesFin);
            anoFin = parseInt(anoFin);
            if ((mesFin == 1) || (mesFin == 3) || (mesFin == 5) || (mesFin == 7) || (mesFin == 8) || (mesFin == 10) || (mesFin == 12)) {
                //MEses de 31 dias
                if ((diaFin < 32) && (diaFin > 0)) {
                    //Mu bien, campeon, salte del bucle
                } else {
                    alert("El mes: " + mesFin + " debe de tener el dia comprendido entre 01 y 31");
                    return false;
                }
            } else if ((mesFin == 2)) {
                //Febrero
                if (bisiesto(anoFin)) {
                    if ((diaFin < 30) && (diaFin > 0)) {
                        //Mu bien campeon
                    } else {
                        alert("El febrero del año " + anoFin + " es bisiesto, por lo que debe tener el dia comprendido entre 01 y 29");
                        return false;
                    }
                } else {
                    if ((diaFin < 29) && (diaFin > 0)) {
                        //Mu bien campeon
                    } else {
                        alert("El febrero del año " + anoFin + " no es bisiesto, por lo que debe tener el dia comprendido entre 01 y 28");
                        return false;
                    }
                }
            } else {
                //MEses de 30 dias
                if ((diaFin < 32) && (diaFin > 0)) {
                    //Mu bien, campeon, salte del bucle
                } else {
                    alert("El mes: " + mesFin + " debe de tener el dia comprendido entre 01 y 30");
                    return false;
                }
            }
        } else {
            alert("Introduzca una fecha de fin correcta");
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
    function borra1() {
        document.getElementById("ini").value = ""
    }
</script>
</html>
