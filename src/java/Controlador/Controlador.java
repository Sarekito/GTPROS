package Controlador;

import Proyecto.Despliegue.DespliegueProyectoLocal;

import Proyecto.Dominio.Actividad;
import Proyecto.Dominio.ActividadTrabajador;
import Proyecto.Dominio.Etapa;
import Proyecto.Dominio.InformeSeguimiento;

import Proyecto.Dominio.Proyecto;
import Proyecto.Dominio.TrabajadoresProyecto;
import Trabajador.Despliegue.DespliegueTrabajadorLocal;
import Trabajador.Dominio.Administrador;
import Trabajador.Dominio.Categoria;
import Trabajador.Dominio.Rol;
import Trabajador.Dominio.Trabajador;
import Trabajador.Dominio.Vacaciones;
import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author antonio
 */
public class Controlador extends HttpServlet {

    @EJB
    private DespliegueProyectoLocal despliegueProyecto;

    @EJB
    private DespliegueTrabajadorLocal despliegueTrabajador;

    private Trabajador trabajador;
    private ArrayList<Trabajador> trabajadores;
    private ArrayList<Proyecto> cerrados;
    private Administrador a;
    private ArrayList<Proyecto> misProyectos;
    private Proyecto proyecto;
    private ArrayList<TrabajadoresProyecto> tp;
    private ArrayList<TrabajadoresProyecto> restantes;
    private ArrayList<Trabajador> elegidos;
    private ArrayList<Etapa> etapas;
    private ArrayList<Actividad> actividades;
    private ArrayList<Actividad> actEtapa;
    private ArrayList<ActividadTrabajador> actividadTrabajador;

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String accion = request.getParameter("accion");
        String url;
        switch (accion) {
            case "generaActividadTrabajador":
                url = otroTrabajador(request);
                break;
            case "proyectosAbiertos":
                url = proyectosAbiertos(request);
                break;
            case "infoProyectoCerrado":
                url = infoProyectoCerrado(request);
                break;
            case "infoProyectoAbierto":
                url = infoProyectoAbierto(request);
                break;
            case "Acceso":
                url = acceso(request);
                break;
            case "actividadConPredecesoras":
                url = actividadConPredecesoras(request);
                break;
            case "sobreesfuerzo":
                url = sobreesfuerzo(request);
                break;
            case "vacaciones":
                url = vacaciones(request);
                break;
            case "reservaVacaciones":
                url = reservaVacaciones(request);
                break;
            case "finalizarPlanActividad":
                url = "/elegirSigsEtapas.jsp";
                break;
            case "volverAPlanificar":
                url = volverAPlanificar(request);
                break;
            case "entrarAdmin":
                url = entrarAdmin(request);
                break;
            case "registrarTrabajador":
                url = "/registroTrabajador.jsp";
                break;
            case "registrarProyecto":
                url = "/registroProyecto.jsp";
                break;
            case "registroTrabajador":
                url = registroTrabajador(request);
                break;
            case "registroProyecto":
                url = registroProyecto(request);
                break;
            case "creacionExito":
                url = "/accesoAdmin.jsp";
                break;
            case "misProyectos":
                url = verMisProyectos(request);
                break;
            case "verProyecto":
                url = verProyecto(request);
                break;
            case "planificado":
                url = planificado(request);
                break;
            case "tomarDatos":
                url = tomarDatos(request);
                break;
            case "elegirEtapas":
                url = "/elegirEtapas.jsp";
                break;
            case "planificarActividades":
                url = planificarActividades(request);
                break;
            case "proyectosCerrados":
                url = proyectosCerrados(request);
                break;
            case "asignarTrabajador":
                url = asignarTrabajador(request);
                break;
            case "indiceInformes":
                url = indiceInformes(request);
                break;
            case "mostrarInformes":
                url = mostrarInformes(request);
                break;
            case "mostrarInformesPendientes":
                url = mostrarInformesPendientes(request);
                break;
            case "aprobarInforme":
                url = aprobarInforme(request);
                break;
            case "rechazarInforme":
                url = rechazarInforme(request);
                break;
            case "mostrarInformesNoEnviados":
                url = mostrarInformesNoEnviados(request);
                break;
            case "finalizarActividad":
                url = finalizarActividad(request);
                break;
            case "planificarSigsEtapas":
                url = planificarSigsEtapas(request);
                break;
            case "finalizarPlanProyecto":
                url = finalizarPlanProyecto(request);
                break;
            case "finalizarActividades":
                url = finalizarActividades(request);
                break;
            case "finalizarEtapas":
                url = finalizarEtapas(request);
                break;
            case "finalizarProyecto":
                url = finalizarProyecto(request);
                break;
            case "elegirEtapaA":
                url = elegidaEtapaDimeActividad(request);
                break;
            case "elegirActividadD":
                url = elegidaActividadDescribe(request);
                break;
            case "actividadesPentiendes":
                url = verActividadesPendientes(request);
                break;
            case "aAcceso":
                url = aAcceso(request);
                break;
            case "aIntroducirDatosActividad":
                url = aIntroducirDatosActividad(request);
                break;
            case "datosIntroducidosCorrectamente":
                url = datosIntroducidosCorrectamente(request);
                break;
            case "finalizarActividadElegida":
                url = finalizarActividadElegida(request);
                break;
            default:
                url = "/error.jsp";
                break;
        }
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(url);
        dispatcher.forward(request, response);
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

    private String acceso(HttpServletRequest request) {
        String usuario = request.getParameter("usuario");
        String clave = request.getParameter("clave");

        if (usuario == null || usuario.equals("") || clave == null || clave.equals("")) {
            request.setAttribute("error", "No se han introducido todos los parametros.");
            return "/index.jsp";
        }

        trabajador = despliegueTrabajador.getTrabajador(usuario);
        if (trabajador == null) {
            request.setAttribute("error", "No existe un trabajador con ese identificador.");
            return "/index.jsp";
        }

        if (!trabajador.getPassword().equals(clave)) {
            request.setAttribute("error", "La contraseña introducida es incorrecta.");
            return "/index.jsp";
        }

        HttpSession sesion = request.getSession();
        sesion.setAttribute("trabajador", trabajador);
        return "/accesoUsuario.jsp";
    }

    private String vacaciones(HttpServletRequest request) {
        return "/reservarVacaciones.jsp";
    }

    private String reservaVacaciones(HttpServletRequest request) {
        HttpSession sesion = request.getSession();
        Trabajador trabajador = (Trabajador) sesion.getAttribute("trabajador");

        java.util.Date hoy = new java.util.Date();
        Date hoySql = new Date(hoy.getYear(), hoy.getMonth(), hoy.getDate());
        if (request.getParameter("periodos") == null) {
            int semanas = Integer.parseInt(request.getParameter("semanas1"));
            Date fechaElegida = Date.valueOf(request.getParameter("fecha1"));
            if (despliegueTrabajador.reservoVacaciones(trabajador.getUser(), (int) (1900 + fechaElegida.getYear()))) {
                return "/vacacionesReservadas.jsp";
            }
            if (semanas != 4 || fechaElegida.getDay() != 1 || fechaElegida.before(hoySql)) {
                return "/vacacionesErroneas.jsp";
            }
            ArrayList<Actividad> tmpAct = despliegueProyecto.misActividadesAbiertas(trabajador.getUser());
            Date diaInicio = fechaElegida;
            Calendar c = Calendar.getInstance();
            c.setTime(fechaElegida);
            c.add(Calendar.DATE, (semanas * 7) - 1);
            Date fechaFin = new Date(c.getTime().getYear(), c.getTime().getMonth(), c.getTime().getDate());
            for (int i = 0; i < tmpAct.size(); i++) {
                if (!diaInicio.after(tmpAct.get(i).getFechaComienzo()) && !diaInicio.before(tmpAct.get(i).getFechaFin())
                        || !diaInicio.after(tmpAct.get(i).getFechaComienzo()) && !fechaFin.before(tmpAct.get(i).getFechaFin())
                        || !tmpAct.get(i).getFechaComienzo().after(diaInicio) && !tmpAct.get(i).getFechaComienzo().before(fechaFin)
                        || !tmpAct.get(i).getFechaFin().after(diaInicio) && !tmpAct.get(i).getFechaFin().before(fechaFin)) {
                    return "/vacacionesErroneas.jsp";

                }
            }
            despliegueTrabajador.reservaVacaciones(trabajador, 1, fechaElegida.getYear(), fechaElegida, semanas);
        } else {
            ArrayList<Actividad> tmpAct = despliegueProyecto.misActividadesAbiertas(trabajador.getUser());
            int semanas1 = Integer.parseInt(request.getParameter("semanas1"));
            Date fechaElegida1 = Date.valueOf(request.getParameter("fecha1"));
            Date diaInicio = fechaElegida1;
            Calendar cd = Calendar.getInstance();
            cd.setTime(fechaElegida1);
            cd.add(Calendar.DATE, (semanas1 * 7) - 1);
            Date fechaFin = new Date(cd.getTime().getYear(), cd.getTime().getMonth(), cd.getTime().getDate());
            for (int i = 0; i < tmpAct.size(); i++) {
                if (!diaInicio.after(tmpAct.get(i).getFechaComienzo()) && !diaInicio.before(tmpAct.get(i).getFechaFin())
                        || !diaInicio.after(tmpAct.get(i).getFechaComienzo()) && !fechaFin.before(tmpAct.get(i).getFechaFin())
                        || !tmpAct.get(i).getFechaComienzo().after(diaInicio) && !tmpAct.get(i).getFechaComienzo().before(fechaFin)
                        || !tmpAct.get(i).getFechaFin().after(diaInicio) && !tmpAct.get(i).getFechaFin().before(fechaFin)) {
                    return "/vacacionesErroneas.jsp";

                }
            }
            int semanas2 = Integer.parseInt(request.getParameter("semanas2"));
            Date fechaElegida2 = Date.valueOf(request.getParameter("fecha2"));
            diaInicio = fechaElegida1;
            cd = Calendar.getInstance();
            cd.setTime(fechaElegida1);
            cd.add(Calendar.DATE, (semanas1 * 7) - 1);
            fechaFin = new Date(cd.getTime().getYear(), cd.getTime().getMonth(), cd.getTime().getDate());
            for (int i = 0; i < tmpAct.size(); i++) {
                if (!diaInicio.after(tmpAct.get(i).getFechaComienzo()) && !diaInicio.before(tmpAct.get(i).getFechaFin())
                        || !diaInicio.after(tmpAct.get(i).getFechaComienzo()) && !fechaFin.before(tmpAct.get(i).getFechaFin())
                        || !tmpAct.get(i).getFechaComienzo().after(diaInicio) && !tmpAct.get(i).getFechaComienzo().before(fechaFin)
                        || !tmpAct.get(i).getFechaFin().after(diaInicio) && !tmpAct.get(i).getFechaFin().before(fechaFin)) {
                    return "/vacacionesErroneas.jsp";

                }
            }
            if (despliegueTrabajador.reservoVacaciones(trabajador.getUser(), (int) (1900 + fechaElegida1.getYear()))) {
                return "/vacacionesReservadas.jsp";
            }
            Calendar c = Calendar.getInstance();
            c.setTime(fechaElegida1);
            c.add(Calendar.DATE, (semanas1 * 7) - 1);
            Date fechaFin1 = new Date(c.getTime().getYear(), c.getTime().getMonth(), c.getTime().getDate());
            if (semanas1 + semanas2 != 4 || fechaElegida2.getDay() != 1 || fechaElegida1.getDay() != 1
                    || fechaElegida1.after(fechaElegida2) || fechaElegida2.before(fechaFin1)
                    || fechaElegida1.before(hoySql) || fechaElegida2.before(hoySql)) {
                return "/vacacionesErroneas.jsp";
            } else {
                despliegueTrabajador.reservaVacaciones(trabajador, 1, fechaElegida1.getYear(), fechaElegida1, semanas1);
                despliegueTrabajador.reservaVacaciones(trabajador, 2, fechaElegida1.getYear(), fechaElegida2, semanas2);
            }

        }
        return "/vacacionesGuardadas.jsp";
    }

    private String entrarAdmin(HttpServletRequest request) {
        final String errorCredenciales = "Credenciales incorrectas.";

        String usuario = request.getParameter("usuario");
        String clave = request.getParameter("clave");

        if (usuario == null || usuario.equals("") || clave == null || clave.equals("")) {
            request.setAttribute("error", "No se han introducido todos los parametros.");
            return "/indexAdministrador.jsp";
        }

        a = despliegueTrabajador.getAdministrador(usuario);
        if (a == null || !a.getPassword().equals(clave)) {
            request.setAttribute("error", errorCredenciales);
            return "/indexAdministrador.jsp";
        }

        HttpSession sesion = request.getSession();
        sesion.setAttribute("administrador", a);
        return "/accesoAdmin.jsp";
    }

    private String registroTrabajador(HttpServletRequest request) {
        Categoria cat = Categoria.get(Integer.parseInt(request.getParameter("nivel")));
        Trabajador tr = new Trabajador(request.getParameter("usuario"), request.getParameter("clave"), cat);
        boolean existe = despliegueTrabajador.buscaTrabajador(tr.getUser());
        if (!existe) {
            despliegueTrabajador.registrarTrabajador(tr);
            return "/creacionConExito.jsp";
        } else {
            return "/trabajadorCreado.jsp";
        }

    }

    private String registroProyecto(HttpServletRequest request) {
        String jefe = request.getParameter("jefe");
        String nombreProyecto = request.getParameter("nombre");

        Trabajador tr = despliegueTrabajador.getTrabajador(jefe);
        if (tr == null) {
            return "/errorProyectoNoJefe.jsp";
        }

        if (tr.getCategoria().getCategoria() != 1) {
            return "/errorNoPuedeSerJefe.jsp";
        }
        ArrayList<Proyecto> proyectosDeJefe = despliegueProyecto.getMisProyectos(jefe);
        if (!proyectosDeJefe.isEmpty()) {
            return "/errorProyectoJefe.jsp";
        }
        Proyecto p = despliegueProyecto.getProyecto(nombreProyecto);
        if (p != null) {
            return "/errorExisteProyecto.jsp";
        }
        despliegueProyecto.generar(nombreProyecto, jefe);
        ArrayList<TrabajadoresProyecto> tmpTP = new ArrayList<TrabajadoresProyecto>();
        tmpTP.add(new TrabajadoresProyecto(nombreProyecto, jefe, 0));
        despliegueProyecto.guardarTrabajadores(tmpTP);
        return "/creacionConExito.jsp";
    }

    private String verMisProyectos(HttpServletRequest request) {
        HttpSession sesion = request.getSession();
        Trabajador trabajador = (Trabajador) sesion.getAttribute("trabajador");

        if (trabajador == null) {
            return "/index.html";
        }
        misProyectos = despliegueProyecto.getMisProyectos(trabajador.getUser());
        request.setAttribute("misProyectos", misProyectos);
        sesion.setAttribute("misProyectos", misProyectos);

        return "/verProyectos.jsp";
    }

    private String verProyecto(HttpServletRequest request) {
        HttpSession sesion = request.getSession();
        misProyectos = (ArrayList<Proyecto>) sesion.getAttribute("misProyectos");

        int elegido = Integer.parseInt(request.getParameter("eleccion"));
        proyecto = misProyectos.get(elegido);
        sesion.setAttribute("proyecto", proyecto);
        if (proyecto.getEstado().equals("pendiente")) {
            request.setAttribute("proyecto", proyecto);
            request.setAttribute("fallo", "no");
            return "/planificar.jsp";
        }
        if (proyecto.getEstado().equals("realizando")) {
            //TODO pagina de informes (Ahora va a ser para profundizar en el proyecto)
            //Si no se planifica el proyecto, se profundiza, mostrando primeramente, las etapas
            ArrayList<Etapa> misEtapas = (ArrayList<Etapa>) despliegueProyecto.getEtapas((String) proyecto.getNombre());
            request.setAttribute("etapasP", misEtapas);
            sesion.setAttribute("etapasP", misEtapas);
            return "/verMisEtapas.jsp";
        }
        //TODO me planteo eliminar este return (ELIMINALO SI PONES UN ELSE FINAL O SIMILARES, SI NO, ESTA BIEN
        return null;
    }

    private String planificado(HttpServletRequest request) {
        tp = new ArrayList<>();
        java.util.Date hoy = new java.util.Date();
        Date inicio = Date.valueOf(request.getParameter("inicio"));
        Date fin = Date.valueOf(request.getParameter("fin"));
        if (inicio.before(hoy) || fin.before(hoy) || inicio.after(fin) || inicio.getDay() != 1 || fin.getDay() != 1) {
            request.setAttribute("proyecto", proyecto);
            request.setAttribute("fallo", "si");
            return "/planificar.jsp";
        } else {
            proyecto.setFechaInicio(inicio);
            proyecto.setFechaFin(fin);
            trabajadores = despliegueTrabajador.getTrabajadores(proyecto.getJefe());
            for (int i = 0; i < trabajadores.size(); i++) {
                ArrayList<Proyecto> proyActuales = despliegueProyecto.getMisProyectosActuales(trabajadores.get(i));
                for (int j = 0; j < proyActuales.size(); j++) {
                    Proyecto p1 = proyecto;
                    Proyecto p2 = proyActuales.get(j);
                    if (p1.getFechaInicio().after(p2.getFechaInicio()) && p1.getFechaInicio().before(p2.getFechaFin())
                            || p1.getFechaFin().after(p2.getFechaInicio()) && p1.getFechaFin().before(p2.getFechaFin())
                            || p2.getFechaInicio().after(p1.getFechaInicio()) && p2.getFechaInicio().before(p1.getFechaFin())
                            || p2.getFechaFin().after(p1.getFechaInicio()) && p2.getFechaFin().before(p1.getFechaFin())) {

                    } else {
                        proyActuales.remove(j);
                    }
                }
                if (proyActuales.size() > 1) {
                    trabajadores.remove(i);
                }
            }
            elegidos = new ArrayList<>();
            request.setAttribute("trabajadores", trabajadores);
            return "/elegirTrabajadores.jsp";
        }
    }

    private String tomarDatos(HttpServletRequest request) {
        actividadTrabajador = new ArrayList<>();
        etapas = new ArrayList<>();
        actividades = new ArrayList<>();
        Trabajador tr = trabajadores.get(Integer.parseInt(request.getParameter("eleccion")));
        ArrayList<Proyecto> proyActuales = despliegueProyecto.getMisProyectosActuales(tr);
        int dedicacion = Integer.parseInt(request.getParameter("dedicacion"));
        for (int j = 0; j < proyActuales.size(); j++) {
            Proyecto p1 = proyecto;
            Proyecto p2 = proyActuales.get(j);
            if (p1.getFechaInicio().after(p2.getFechaInicio()) && p1.getFechaInicio().before(p2.getFechaFin())
                    || p1.getFechaFin().after(p2.getFechaInicio()) && p1.getFechaFin().before(p2.getFechaFin())
                    || p2.getFechaInicio().after(p1.getFechaInicio()) && p2.getFechaInicio().before(p1.getFechaFin())
                    || p2.getFechaFin().after(p1.getFechaInicio()) && p2.getFechaFin().before(p1.getFechaFin())) {

            } else {
                proyActuales.remove(j);
            }
        }
        if (!proyActuales.isEmpty()) {
            TrabajadoresProyecto tp2 = despliegueProyecto.dameTrabajadorProyecto(tr.getUser(), proyActuales.get(0).getNombre());
            if (tp2.getDedicacion() + dedicacion > 100) {
                request.setAttribute("trabajadores", trabajadores);
                if (elegidos.isEmpty()) {
                    return "/elegirTrabajadoresExceso.jsp";
                } else {
                    return "/elegirTrabajadoresExceso2.jsp";
                }
            } else {
                elegidos.add(tr);
                trabajadores.remove(Integer.parseInt(request.getParameter("eleccion")));
                request.setAttribute("trabajadores", trabajadores);
                tp.add(new TrabajadoresProyecto(proyecto.getNombre(), tr.getUser(), dedicacion));
                return "/elegirTrabajadores2.jsp";
            }
        } else {
            elegidos.add(tr);
            trabajadores.remove(Integer.parseInt(request.getParameter("eleccion")));
            request.setAttribute("trabajadores", trabajadores);
            tp.add(new TrabajadoresProyecto(proyecto.getNombre(), tr.getUser(), dedicacion));
            return "/elegirTrabajadores2.jsp";
        }

    }

    private String planificarActividades(HttpServletRequest request) {

        Date inicio = Date.valueOf(request.getParameter("inicio"));
        Date fin = Date.valueOf(request.getParameter("fin"));
        if (inicio.after(fin) || inicio.before(proyecto.getFechaInicio()) || fin.after(proyecto.getFechaFin()) || inicio.getDay() != 1 || fin.getDay() != 1) {
            return "/errorFechasEtapa.jsp";
        } else {
            actEtapa = new ArrayList<>();
            etapas.add(new Etapa(proyecto.getNombre(), etapas.size() + 1, inicio, fin, null, "pendiente"));
            return "/actividades.jsp";
        }
    }

    private String asignarTrabajador(HttpServletRequest request) {

        Actividad actividad = (new Actividad(proyecto.getNombre(), etapas.get(etapas.size() - 1).getNumero(), actEtapa.size(), request.getParameter("descripcion"), Integer.parseInt(request.getParameter("duracion")), null, Date.valueOf(request.getParameter("inicio")), Date.valueOf(request.getParameter("fin")), null, "planifcada", Rol.get(request.getParameter("Rol"))));
        restantes = new ArrayList<>();
        ArrayList<Actividad> simultaneas;
        for (int i = 0; i < tp.size(); i++) {
            restantes.add(tp.get(i));
            simultaneas = new ArrayList<>();
            simultaneas = despliegueProyecto.misActividadesFecha(tp.get(i).getUser());

            for (int k = 0; k < simultaneas.size(); k++) {
                if ((actividad.getFechaComienzo().before(simultaneas.get(k).getFechaComienzo())) && (actividad.getFechaComienzo().after(simultaneas.get(k).getFechaFin()))
                        || (actividad.getFechaFin().after(simultaneas.get(k).getFechaComienzo())) && (actividad.getFechaFin().before(simultaneas.get(k).getFechaFin()))
                        || (simultaneas.get(k).getFechaComienzo().after(actividad.getFechaComienzo())) && (simultaneas.get(k).getFechaComienzo().before(actividad.getFechaFin()))
                        || (simultaneas.get(k).getFechaFin().after(actividad.getFechaComienzo())) && (simultaneas.get(k).getFechaFin().before(actividad.getFechaFin()))) {
                    simultaneas.remove(k);
                }
            }

            if (simultaneas.size() > 3) {
                restantes.remove(i);
            }
        }
        for (int i = 0; i < restantes.size(); i++) {
            ArrayList<Vacaciones> vc = despliegueTrabajador.getVacaciones(restantes.get(i).getUser());

            if (vc != null) {
                for (int j = 0; j < vc.size(); j++) {
                    java.util.Date inicio = vc.get(j).getInicio();
                    Calendar c = Calendar.getInstance();
                    c.setTime(vc.get(j).getInicio());
                    c.add(Calendar.DATE, (vc.get(j).getSemanas() * 7) - 1);
                    java.util.Date fin = c.getTime();
                    if (!(actividad.getFechaComienzo().before(inicio)) && !(actividad.getFechaComienzo().after(fin))
                            || !(actividad.getFechaFin().after(inicio)) && !(actividad.getFechaFin().before(fin))
                            || !(inicio.after(actividad.getFechaComienzo())) && !(inicio.before(actividad.getFechaFin()))
                            || !(fin.after(actividad.getFechaComienzo())) && !(fin.before(actividad.getFechaFin()))) {
                        restantes.remove(i);
                        break;
                    }
                }
            }
        }
        actividades.add(actividad);
        actEtapa.add(actividad);
        request.setAttribute("trabajadoresProyecto", restantes);
        return "/seleccionarTrabajadores.jsp";

    }

    public String indiceInformes(HttpServletRequest request) {
        HttpSession sesion = request.getSession();
        Trabajador trabajador = (Trabajador) sesion.getAttribute("trabajador");

        if (trabajador == null) {
            return "/index.jsp";
        }

        String nombreProyecto = request.getParameter("nombreProyecto");
        if (nombreProyecto == null) {
            request.setAttribute("error", "No se ha indicado un nombre de proyecto.");
            return "/accesoUsuario.jsp";
        }

        Proyecto proyecto = despliegueProyecto.getProyecto(nombreProyecto);
        if (proyecto == null) {
            request.setAttribute("error", "No existe un proyecto con el nombre '" + nombreProyecto + "'.");
            return "/accesoUsuario.jsp";
        }

        if (!trabajador.getUser().equals(proyecto.getJefe())) {
            request.setAttribute("error", "No puedes acceder a un proyecto que no es tuyo");
            return "/accesoUsuario.jsp";
        }

        request.setAttribute("proyecto", proyecto);

        return "/indiceInformes.jsp";
    }

    public String mostrarInformes(HttpServletRequest request) {
        HttpSession sesion = request.getSession();
        Trabajador trabajador = (Trabajador) sesion.getAttribute("trabajador");

        if (trabajador == null) {
            return "/index.jsp";
        }

        String nombreProyecto = request.getParameter("nombreProyecto");
        if (nombreProyecto == null) {
            request.setAttribute("error", "No se ha indicado un nombre de proyecto.");
            return "/accesoUsuario.jsp";
        }

        Proyecto proyecto = despliegueProyecto.getProyecto(nombreProyecto);
        if (proyecto == null) {
            request.setAttribute("error", "No existe un proyecto con el nombre '" + nombreProyecto + "'.");
            return "/accesoUsuario.jsp";
        }

        if (!trabajador.getUser().equals(proyecto.getJefe())) {
            request.setAttribute("error", "No puedes acceder a un proyecto que no es tuyo");
            return "/accesoUsuario.jsp";
        }

        ArrayList<InformeSeguimiento> informes = despliegueProyecto.getInformesProyecto(proyecto.getNombre());
        request.setAttribute("informes", informes);
        request.setAttribute("proyecto", proyecto);

        return "/informes.jsp";
    }

    public String aprobarInforme(HttpServletRequest request) {
        HttpSession sesion = request.getSession();
        Trabajador trabajador = (Trabajador) sesion.getAttribute("trabajador");

        if (trabajador == null) {
            return "/index.jsp";
        }

        String nombreProyecto = request.getParameter("nombreProyecto");
        if (nombreProyecto == null) {
            request.setAttribute("error", "No se ha indicado un nombre de proyecto.");
            return "/accesoUsuario.jsp";
        }

        Proyecto proyecto = despliegueProyecto.getProyecto(nombreProyecto);
        if (proyecto == null) {
            request.setAttribute("error", "No existe un proyecto con el nombre '" + nombreProyecto + "'.");
            return "/accesoUsuario.jsp";
        }

        if (proyecto.getJefe().equals(trabajador.getUser())) {
            request.setAttribute("error", "No puedes aprobar informes de proyectos que no lideras");
            return "/accesoUsuario.jsp";
        }

        InformeSeguimiento informe = new InformeSeguimiento();
        informe.setNombreProyecto(nombreProyecto);
        informe.setNumeroEtapa(Integer.parseInt(request.getParameter("numeroEtapa")));
        informe.setIdActividad(Integer.parseInt(request.getParameter("idActividad")));
        informe.setTrabajador(request.getParameter("trabajador"));
        informe.setNumTarea(Integer.parseInt(request.getParameter("numTarea")));
        informe.setSemana(java.sql.Date.valueOf(request.getParameter("semana")));

        despliegueProyecto.aprobarInforme(informe);

        ArrayList<InformeSeguimiento> informes = despliegueProyecto.getInformesPendientesProyecto(proyecto.getNombre());
        request.setAttribute("informes", informes);
        request.setAttribute("proyecto", proyecto);

        return "/informesPendientes.jsp";
    }

    public String rechazarInforme(HttpServletRequest request) {
        HttpSession sesion = request.getSession();
        Trabajador trabajador = (Trabajador) sesion.getAttribute("trabajador");

        if (trabajador == null) {
            return "/index.jsp";
        }

        String nombreProyecto = request.getParameter("nombreProyecto");
        if (nombreProyecto == null) {
            request.setAttribute("error", "No se ha indicado un nombre de proyecto.");
            return "/accesoUsuario.jsp";
        }

        Proyecto proyecto = despliegueProyecto.getProyecto(nombreProyecto);
        if (proyecto == null) {
            request.setAttribute("error", "No existe un proyecto con el nombre '" + nombreProyecto + "'.");
            return "/accesoUsuario.jsp";
        }

        if (proyecto.getJefe().equals(trabajador.getUser())) {
            request.setAttribute("error", "No puedes rechazar informes de proyectos que no lideras");
            return "/accesoUsuario.jsp";
        }

        InformeSeguimiento informe = new InformeSeguimiento();
        informe.setNombreProyecto(request.getParameter("nombreProyecto"));
        informe.setNumeroEtapa(Integer.parseInt(request.getParameter("numeroEtapa")));
        informe.setIdActividad(Integer.parseInt(request.getParameter("idActividad")));
        informe.setTrabajador(request.getParameter("trabajador"));
        informe.setNumTarea(Integer.parseInt(request.getParameter("numTarea")));
        informe.setSemana(java.sql.Date.valueOf(request.getParameter("semana")));

        despliegueProyecto.rechazarInforme(informe);

        ArrayList<InformeSeguimiento> informes = despliegueProyecto.getInformesPendientesProyecto(proyecto.getNombre());
        request.setAttribute("informes", informes);
        request.setAttribute("proyecto", proyecto);

        return "/informesPendientes.jsp";
    }

    public String mostrarInformesPendientes(HttpServletRequest request) {
        HttpSession sesion = request.getSession();
        Trabajador trabajador = (Trabajador) sesion.getAttribute("trabajador");

        if (trabajador == null) {
            return "/index.jsp";
        }

        String nombreProyecto = request.getParameter("nombreProyecto");
        if (nombreProyecto == null) {
            request.setAttribute("error", "No se ha indicado un nombre de proyecto.");
            return "/accesoUsuario.jsp";
        }

        Proyecto proyecto = despliegueProyecto.getProyecto(nombreProyecto);
        if (proyecto == null) {
            request.setAttribute("error", "No existe un proyecto con el nombre '" + nombreProyecto + "'.");
            return "/accesoUsuario.jsp";
        }

        if (!trabajador.getUser().equals(proyecto.getJefe())) {
            request.setAttribute("error", "No puedes acceder a un proyecto que no es tuyo");
            return "/accesoUsuario.jsp";
        }

        ArrayList<InformeSeguimiento> informes = despliegueProyecto.getInformesPendientesProyecto(proyecto.getNombre());
        request.setAttribute("informes", informes);
        request.setAttribute("proyecto", proyecto);

        return "/informesPendientes.jsp";
    }

    public String mostrarInformesNoEnviados(HttpServletRequest request) {
        HttpSession sesion = request.getSession();
        Trabajador trabajador = (Trabajador) sesion.getAttribute("trabajador");

        if (trabajador == null) {
            return "/index.jsp";
        }

        String nombreProyecto = request.getParameter("nombreProyecto");
        if (nombreProyecto == null) {
            request.setAttribute("error", "No se ha indicado un nombre de proyecto.");
            return "/accesoUsuario.jsp";
        }

        Proyecto proyecto = despliegueProyecto.getProyecto(nombreProyecto);
        if (proyecto == null) {
            request.setAttribute("error", "No existe un proyecto con el nombre '" + nombreProyecto + "'.");
            return "/accesoUsuario.jsp";
        }

        if (!trabajador.getUser().equals(proyecto.getJefe())) {
            request.setAttribute("error", "No puedes acceder a un proyecto que no es tuyo");
            return "/accesoUsuario.jsp";
        }

        ArrayList<InformeSeguimiento> informes = despliegueProyecto.getInformesNoEnviadosProyecto(proyecto.getNombre());
        request.setAttribute("informes", informes);
        request.setAttribute("proyecto", proyecto);

        return "/informesNoEnviados.jsp";

    }

    private String otroTrabajador(HttpServletRequest request) {
        int elegido = Integer.parseInt(request.getParameter("eleccion"));
        TrabajadoresProyecto tptmp = tp.get(elegido);
        restantes.remove(elegido);
        int tiempo = Integer.parseInt(request.getParameter("horasActividad"));
        actividadTrabajador.add(new ActividadTrabajador(actividades.get(actividades.size() - 1), tptmp, tiempo));
        request.setAttribute("trabajadoresProyecto", restantes);
        return "/otroTrabajador.jsp";
    }

    private String finalizarActividad(HttpServletRequest request) {
        Actividad ac = actEtapa.get(actEtapa.size() - 1);
        int numSemanas = (ac.getFechaFin().compareTo(ac.getFechaComienzo()) / 7) + 1;
        int horasActividades = 0;
        for (int i = 0; i < actividadTrabajador.size(); i++) {
            if (actividadTrabajador.get(i).getNumeroEtapa() == ac.getNumero() && actividadTrabajador.get(i).getIdActividad() == ac.getId()) {
                horasActividades += actividadTrabajador.get(i).getHoras() * numSemanas;
            }
        }
        if (ac.getDuracion() == horasActividades) {
            request.setAttribute("posiblesPredecesoras", actEtapa);
            return "/actividadesFinalizar.jsp";

        } else {
            return "/actividadConDesigualdad.jsp";
        }
    }

    private String volverAPlanificar(HttpServletRequest request) {
        for (int i = 0; i < tp.size() - restantes.size(); i++) {
            actividadTrabajador.remove(actividadTrabajador.size() - 1);
        }
        restantes = new ArrayList<>();
        for (int i = 0; i < tp.size(); i++) {
            restantes.add(tp.get(i));
        }
        request.setAttribute("trabajadoresProyecto", restantes);
        return "/seleccionarTrabajadores.jsp";
    }

    private String actividadConPredecesoras(HttpServletRequest request) {

        Actividad actividad = new Actividad(proyecto.getNombre(), etapas.get(etapas.size() - 1).getNumero(),
                actEtapa.size(), request.getParameter("descripcion"),
                Integer.parseInt(request.getParameter("duracion")), null,
                Date.valueOf(request.getParameter("inicio")),
                Date.valueOf(request.getParameter("fin")), null, "planifcada",
                Rol.get(request.getParameter("Rol")));
        for (int i = 0; i < actEtapa.size(); i++) {
            if (request.getParameter("" + i) != null) {
                if (!actEtapa.get(i).getFechaFin().before(actividad.getFechaComienzo())) {
                    request.setAttribute("posiblesPredecesoras", actEtapa);
                    request.setAttribute("predecesora", actEtapa.get(i));
                    return "/actividadesFinalizarNoPredecesora.jsp";
                } else {
                    actividad.addPredecesora(actEtapa.get(i));
                }
            }

        }

        restantes = new ArrayList<>();
        ArrayList<Actividad> simultaneas;
        for (int i = 0; i < tp.size(); i++) {
            restantes.add(tp.get(i));
            simultaneas = new ArrayList<>();
            simultaneas = despliegueProyecto.misActividadesFecha(tp.get(i).getUser());

            for (int k = 0; k < simultaneas.size(); k++) {
                if ((actividad.getFechaComienzo().before(simultaneas.get(k).getFechaComienzo())) && (actividad.getFechaComienzo().after(simultaneas.get(k).getFechaFin()))
                        || (actividad.getFechaFin().after(simultaneas.get(k).getFechaComienzo())) && (actividad.getFechaFin().before(simultaneas.get(k).getFechaFin()))
                        || (simultaneas.get(k).getFechaComienzo().after(actividad.getFechaComienzo())) && (simultaneas.get(k).getFechaComienzo().before(actividad.getFechaFin()))
                        || (simultaneas.get(k).getFechaFin().after(actividad.getFechaComienzo())) && (simultaneas.get(k).getFechaFin().before(actividad.getFechaFin()))) {
                    simultaneas.remove(k);
                }
            }
            for (int k = 0; k < actividadTrabajador.size(); k++) {
                if (actividadTrabajador.get(k).getNombreTrabajador().equals(tp.get(i).getUser())) {
                    for (int j = 0; j < actividades.size(); j++) {
                        if (actividades.get(j).getNumero() == actividadTrabajador.get(k).getNumeroEtapa()
                                && actividades.get(j).getId() == actividadTrabajador.get(k).getIdActividad()) {
                            if (!(actividad.getFechaComienzo().before(actividades.get(j).getFechaComienzo())) && !(actividad.getFechaComienzo().after(actividades.get(j).getFechaFin()))
                                    || !(actividad.getFechaFin().after(actividades.get(j).getFechaComienzo())) && !(actividad.getFechaFin().before(actividades.get(j).getFechaFin()))
                                    || !(actividades.get(j).getFechaComienzo().after(actividad.getFechaComienzo())) && !(actividades.get(j).getFechaComienzo().before(actividad.getFechaFin()))
                                    || !(actividades.get(j).getFechaFin().after(actividad.getFechaComienzo())) && !(actividades.get(j).getFechaFin().before(actividad.getFechaFin()))) {

                                simultaneas.add(actividades.get(j));
                            }
                        }
                    }
                }
            }
            if (simultaneas.size() > 3) {
                restantes.remove(i);
            }
        }
        for (int i = 0; i < restantes.size(); i++) {
            ArrayList<Vacaciones> vc = despliegueTrabajador.getVacaciones(restantes.get(i).getUser());

            if (vc != null) {
                for (int j = 0; j < vc.size(); j++) {
                    java.util.Date inicio = vc.get(j).getInicio();
                    Calendar c = Calendar.getInstance();
                    c.setTime(vc.get(j).getInicio());
                    c.add(Calendar.DATE, (vc.get(j).getSemanas() * 7) - 1);
                    java.util.Date fin = c.getTime();
                    if (!(actividad.getFechaComienzo().before(inicio)) && !(actividad.getFechaComienzo().after(fin))
                            || !(actividad.getFechaFin().after(inicio)) && !(actividad.getFechaFin().before(fin))
                            || !(inicio.after(actividad.getFechaComienzo())) && !(inicio.before(actividad.getFechaFin()))
                            || !(fin.after(actividad.getFechaComienzo())) && !(fin.before(actividad.getFechaFin()))) {
                        restantes.remove(i);
                        break;
                    }
                }
            }
        }
        actividades.add(actividad);
        actEtapa.add(actividad);
        request.setAttribute("trabajadoresProyecto", restantes);
        return "/seleccionarTrabajadores.jsp";
    }

    private String planificarSigsEtapas(HttpServletRequest request) {
        Date inicio = Date.valueOf(request.getParameter("inicio"));
        Date fin = Date.valueOf(request.getParameter("fin"));
        if (inicio.after(fin) || inicio.before(proyecto.getFechaInicio()) || fin.after(proyecto.getFechaFin()) || inicio.getDay() != 1 || fin.getDay() != 1) {
            return "/errorFechasEtapa.jsp";
        } else {
            actEtapa = new ArrayList<>();
            Etapa etapa = new Etapa(proyecto.getNombre(), etapas.size() + 1, inicio, fin, null, "pendiente");
            if (etapas.get(etapas.size() - 1).getFechaFin().before(etapa.getFechaInicio())) {
                etapas.add(etapa);
                return "/actividades.jsp";
            } else {
                return "/errorEtapa.jsp";
            }

        }
    }

    private String finalizarPlanProyecto(HttpServletRequest request) {
        despliegueProyecto.guardarProyecto(proyecto);
        despliegueProyecto.guardarEtapas(etapas);
        despliegueProyecto.guardarActividades(actividades);
        despliegueProyecto.guardarTrabajadores(tp);
        despliegueProyecto.guardarAsignaciones(actividadTrabajador);
        return "/accesoUsuario.jsp";
    }

    private String sobreesfuerzo(HttpServletRequest request) {
        HttpSession sesion = request.getSession();
        Trabajador trabajador = (Trabajador) sesion.getAttribute("trabajador");

        if (trabajador == null) {
            return "/index.jsp";
        }

        ArrayList<Actividad> sobreesfuerzo = new ArrayList<>();
        sobreesfuerzo = despliegueProyecto.getSobreesfuerzo(trabajador.getUser());
        System.out.println(sobreesfuerzo);
        request.setAttribute("sobreesfuerzo", sobreesfuerzo);
        return "/sobreesfuerzo.jsp";
    }

    private String proyectosCerrados(HttpServletRequest request) {
        cerrados = despliegueProyecto.getProyectosCerrados();
        request.setAttribute("cerrados", cerrados);
        return "/cerrados.jsp";
    }

    private String infoProyectoCerrado(HttpServletRequest request) {
        HttpSession sesion = request.getSession();
        Trabajador trabajador = (Trabajador) sesion.getAttribute("trabajador");

        if (trabajador == null) {
            return "/index.jsp";
        }

        Proyecto p = cerrados.get(Integer.parseInt(request.getParameter("eleccion")));
        ArrayList<Etapa> etapasC = despliegueProyecto.getEtapas(p.getNombre());
        ArrayList<Actividad> actividadesC = new ArrayList<>();
        for (int i = 0; i < etapasC.size(); i++) {
            ArrayList<Actividad> tmp = new ArrayList<Actividad>();
            tmp = despliegueProyecto.getActividadesCerrados(p.getNombre(), etapasC.get(i).getNumero());
            for (int j = 0; j < tmp.size(); j++) {
                actividadesC.add(tmp.get(j));
            }
        }
        request.setAttribute("etapas", etapasC);
        request.setAttribute("actividades", actividadesC);
        return "/vistaCerrados.jsp";
    }

    private String proyectosAbiertos(HttpServletRequest request) {
        HttpSession sesion = request.getSession();
        Trabajador trabajador = (Trabajador) sesion.getAttribute("trabajador");

        if (trabajador == null) {
            return "/index.jsp";
        }

        cerrados = despliegueProyecto.getMisProyectosActuales(trabajador);
        request.setAttribute("abiertos", cerrados);
        return "/abiertos.jsp";
    }

    private String finalizarActividades(HttpServletRequest request) {
        HttpSession sesion = request.getSession();
        ArrayList<Proyecto> todosProyectos = despliegueProyecto.getMisProyectos(trabajador.getUser());
        ArrayList<Actividad> todasActividades = null;
        for (int w = 0; w < todosProyectos.size(); w++) {
            ArrayList<Etapa> todasEtapas = despliegueProyecto.getEtapas(todosProyectos.get(w).getNombre());
            for (int ww = 0; ww < todasEtapas.size(); ww++) {
                todasActividades = despliegueProyecto.getActividadesCerrados(todosProyectos.get(w).getNombre(), todasEtapas.get(ww).getNumero());
            }
        }
        sesion.setAttribute("misActividadesFinal", todasActividades);
        return "/verActividadesAFinalizar.jsp";
    }

    private String finalizarEtapas(HttpServletRequest request) {
        return "/verEtapasAFinalizar.jsp";
    }

    private String finalizarProyecto(HttpServletRequest request) {
        return "/verProyectoAFinalizar.jsp";
    }

    private String elegidaEtapaDimeActividad(HttpServletRequest request) {
        HttpSession sesion = request.getSession();
        proyecto = (Proyecto) request.getAttribute("proyecto");
        ArrayList<Etapa> etapaP = (ArrayList<Etapa>) request.getAttribute("etapaP");
        Etapa etapaChosen = etapaP.get((int) request.getAttribute("eleccion"));
        int etapaX = etapaChosen.getNumero();
        Trabajador trabajador = (Trabajador) sesion.getAttribute("trabajador");
        ArrayList<Actividad> actividadesE = despliegueProyecto.getActividadesAbiertasNoJefe(proyecto.getNombre(), etapaX, trabajador.getUser());
        //Habria que comprobar que hay actividades pero lo suponemos
        sesion.setAttribute("actividadesChosen", actividadesE);
        return "/veamosActividades.jsp";
    }

    private String elegidaActividadDescribe(HttpServletRequest request) {
        return "/veamosAFondoActividades.jsp";
    }

    private String finalizarActividadElegida(HttpServletRequest request) {
        String actividadElegida = (String) request.getAttribute("actividadElegida");
        String etapaElegida = (String) request.getAttribute("etapaElegida");
        String proyectoElegido = (String) request.getAttribute("proyectoElegida");
        try {
            despliegueProyecto.cerrarActividad(proyectoElegido, Integer.parseInt(etapaElegida), Integer.parseInt(actividadElegida));
            return "/actividadCerradaConExito.jsp";
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "/index.jsp";
    }

    private String verActividadesPendientes(HttpServletRequest request) {
        HttpSession sesion = request.getSession();
        Trabajador trabajador = (Trabajador) sesion.getAttribute("trabajador");
        if (trabajador == null) {
            return "/index.jsp";
        }

        ArrayList<Actividad> actividadesTotales = despliegueProyecto.misActividadesAbiertas(trabajador.getUser());
        sesion.setAttribute("misActividadesPendientes", actividadesTotales);
        return "/verActividadesPendientes.jsp";
    }

    private String aAcceso(HttpServletRequest request) {
        HttpSession sesion = request.getSession();
        if (sesion.getAttribute("trabajador") != null) {
            return "/accesoUsuario.jsp";
        } else {
            return "/index.jsp";
        }
    }

    private String aIntroducirDatosActividad(HttpServletRequest request) {
        HttpSession sesion = request.getSession();
        Trabajador trabajador = (Trabajador) sesion.getAttribute("trabajador");
        if (trabajador == null) {
            return "/index.jsp";
        }
        String introProyecto = request.getParameter("chosenProject");
        String introEtapa = request.getParameter("chosenEtapa");
        String introActividad = request.getParameter("chosenActividad");

        if ((!"".equals(introProyecto)) && (!"".equals(introEtapa)) && (!"".equals(introActividad))) {

            sesion.setAttribute("introDatosP", introProyecto);
            sesion.setAttribute("introDatosE", introEtapa);
            sesion.setAttribute("introDatosA", introActividad);

            return "/introducirDatosActividad.jsp";
        } else {
            //Error en la recepcion de datos
            return "/verActividadesPendientes.jsp";
        }
    }

    private String datosIntroducidosCorrectamente(HttpServletRequest request) {
        HttpSession sesion = request.getSession();
        String tarea = request.getParameter("mitarea");
        int numTarea = Integer.parseInt(tarea);
        java.util.Date semana1 = new java.util.Date();
        Date semana = new Date(semana1.getYear(), semana1.getMonth(), semana1.getDate());
        String tipoTarea = (String) request.getParameter("mitipoTarea");
        String duracionNuestra = (String) request.getParameter("miduracion");
        int duracion = Integer.parseInt(duracionNuestra);
        String user = trabajador.getUser();
        String proyectoNuestro = (String) sesion.getAttribute("introDatosP");
        String etapa = (String) sesion.getAttribute("introDatosE");
        String actividad = (String) sesion.getAttribute("introDatosA");

        try {
            despliegueProyecto.guardarTareaIntroducida(proyectoNuestro, etapa, actividad, user, numTarea, semana, tipoTarea, duracion);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "/accesoUsuario.jsp";
    }

    private String infoProyectoAbierto(HttpServletRequest request) {
        HttpSession sesion = request.getSession();
        Trabajador trabajador = (Trabajador) sesion.getAttribute("trabajador");

        if (trabajador == null) {
            return "/index.jsp";
        }
        Proyecto p = cerrados.get(Integer.parseInt(request.getParameter("eleccion")));
        ArrayList<Etapa> etapasC = despliegueProyecto.getEtapas(p.getNombre());
        ArrayList<Actividad> actividadesC = new ArrayList<>();
        ArrayList<Actividad> tmp2 = new ArrayList<>();
        for (int i = 0; i < etapasC.size(); i++) {
            ArrayList<Actividad> tmp = new ArrayList<>();
            tmp = despliegueProyecto.getActividadesCerrados(p.getNombre(), etapasC.get(i).getNumero());
            for (int j = 0; j < tmp.size(); j++) {
                tmp2.add(tmp.get(j));
            }
        }
        System.out.println(tmp2);
        if(!p.getJefe().equals(trabajador.getUser())){
            for (int i = 0;i<tmp2.size();i++){
                System.out.println(despliegueProyecto.isAsignado(tmp2.get(i), trabajador.getUser()));
                if (despliegueProyecto.isAsignado(tmp2.get(i), trabajador.getUser())){
                    actividadesC.add(tmp2.get(i));
                }
            }
        request.setAttribute("actividades", actividadesC);
        }
        else{
            request.setAttribute("actividades", tmp2);
        }
        request.setAttribute("etapas", etapasC);
        
        return "/vistaCerrados.jsp";
    }
}
