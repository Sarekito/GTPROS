<%-- 
    Document   : actividades
    Created on : 06-ene-2016, 11:39:05
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
        <h2>Las fechas son incorrectas</h2>
        <h1>Introducir datos de la actividad</h1>
        <table>
            <form action="Controlador" method="POST" onsubmit="return check();">
                <tr>
                    <td>
                        Fecha de inicio de la actividad:
                    </td>
                    <td>
                        <input id="ini" type="text" name="inicio" value="dd/mm/aaaa" onclick="borra1()"/>
                    </td>
                </tr>
                <tr>
                    <td>
                        Fecha de fin de la actividad:
                    </td>
                    <td>
                        <input id="fin" type="text" name="fin" value="dd/mm/aaaa" onclick="borra2()"/>
                    </td>
                </tr>
                <tr>
                    <td>
                        Duracion:
                    </td>
                    <td>
                        <input id="duracion" type="number" name="duracion" min="1"/>
                    </td>
                </tr>
                <tr>
                    <td>
                        Tipo de rol:   
                    </td>
                </tr>
                <tr>
                    <td>
                        <input type="radio" name="Rol" value="Analista" checked="checked" /> Analista
                    </td>
                </tr>
                <tr>
                    <td>
                        <input type="radio" name="Rol" value="Disenador" /> Diseñador
                </tr>
                <tr>
                    <td>
                        <input type="radio" name="Rol" value="AnalistaProgramador" /> Analista-Programador
                    </td>
                </tr>
                <tr>
                    <td>
                        <input type="radio" name="Rol" value="ResponsablePruebaas"/> Responsable de Pruebas
                    </td>
                </tr>
                <tr>
                    <td>
                        <input type="radio" name="Rol" value="Programador"/> Programador
                    </td>
                </tr>
                <tr>
                    <td>
                        <input type="radio" name="Rol" value="Probador"/> Probador
                    </td>
                </tr>
                <tr>
                    <td>
                        Descripción:
                    </td>
                    <td>
                        <input type ="text" id="descripcion" name="descripcion" spellcheck="true">
                    </td>
                </tr>
        </table>
        <br>
        <br>
        <input type="hidden" name="accion" value="asignarTrabajador" />
        <input type="submit" value="Planificar Actividades" />
    </form>
</center>
</body>
<script>
    function check() {
        //Omitimos comprobacion en radios ya que hay un checkeado predeterminado
        if ($('#ini').val() == "") {
            alert("Error en fecha inicio");
            return false;
        }
        if ($('#fin').val() == "") {
            alert("Error en fecha fin");
            return false;
        }
        if ($('#duracion').val() == "") {
            alert("Error en la duracion");
            return false;
        }
        if ($('#descripcion').val() == "") {
            alert("Error en la descripcion");
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
                //asdfasd
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
    function borra2() {
        document.getElementById("fin").value = ""
    }
</script>
</html>
