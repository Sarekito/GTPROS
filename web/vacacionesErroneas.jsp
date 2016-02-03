<%-- 
    Document   : vacacionesErroneas
    Created on : 25-dic-2015, 9:34:52
    Author     : antonio
--%>
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
        <font size = "5" color="red">Los periodos vacacionales deben sumar cuatro semanas, empezar en lunes y no superponerse</font>
        <br><br>
        <form name="Controlador" method ="post" onsubmit="return comprueba();">
            <input type="checkbox" name="periodos" value="ON"/>Solicito las vacaciones en dos periodos
            <table>
                <tr>
                    <td>
                        Introduce el "lunes" de inicio del primer periodo
                    </td>
                </tr>
                <tr>
                    <td>
                        <input id="fecha1" type="text" name ="fecha1" value="dd/mm/aaaa" onclick="borra1()">
                    </td>
                </tr>
                <tr>
                    <td>
                        Introduce el numero de semanas
                    </td>
                </tr>
                <tr>
                    <td>
                        <input type="radio" name="semanas1" value="1" checked="checked"  />1
                    </td>
                </tr>
                <tr>
                    <td>
                        <input type="radio" name="semanas1" value="2" />2
                    </td>
                </tr>
                <tr>
                    <td>
                        <input type="radio" name="semanas1" value="3" />3
                    </td>
                </tr>
                <tr>
                    <td>
                        <input type="radio" name="semanas1" value="4" />4
                    </td>
                </tr>
                <tr>
                    <td>
                        Introduce el "lunes" de inicio del segundo periodo
                    </td>
                </tr>
                <tr>
                    <td>
                        <input id="fecha2" type="text" name ="fecha2" value="dd/mm/aaaa" onclick="borra1()">
                    </td>
                </tr>
                <tr>
                    <td>
                        Introduce el numero de semanas
                    </td>
                </tr>
                <tr>
                    <td>
                        <input type="radio" name="semanas2" value="1"   />1
                    </td>
                </tr>
                <tr>
                    <td>
                        <input type="radio" name="semanas2" value="2" />2
                    </td>
                </tr>
                <tr>
                    <td>
                        <input type="radio" name="semanas2" value="3" />3
                    </td>
                </tr>
                <tr>
                    <td>
                        <input type="radio" name="semanas2" value="4" />4
                    </td>
                </tr>
                <tr>
                    <td>
                        <input type="hidden" name="accion" value="reservaVacaciones" readonly="readonly" />
                        <input type="submit" value="Reservar vacaciones" />
                    </td>
                </tr>
            </table>
        </form>
    </center>
</body>
<script>
    function comprueba() {
        if ($('#fecha1').val() == "") {
            alert("Error en el periodo vacacional 1");
            return false;
        }
         var patt = new RegExp(/^(0?[1-9]|[12][0-9]|3[01])[\/\-](0?[1-9]|1[012])[\/\-]\d{4}$/);
        var fechaInicioLocale = $('#fecha1').val();
        var fechaFinLocale = $('#fecha2').val();
        if (patt.test(fechaInicioLocale)) {
            //Se cumple la fecha; comprobamos meses
            var diaInicio = fechaInicioLocale.subString(0, 2);
            var mesInicio = fechaInicioLocale.subString(3, 5);
            var anoInicio = fechaInicioLocale.subString(6);
            diaInicio = parseInt(diaInicio);
            mesInicio = parseInt(mesInicio);
            anoInicio = parseInt(anoInicio);
            //asdfsasd
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
        document.getElementById("fecha1").value = ""
    }
    function borra2() {
        document.getElementById("fecha2").value = ""
    }
</script>
</html>
