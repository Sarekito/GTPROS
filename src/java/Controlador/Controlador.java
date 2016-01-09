package Controlador;

import Proyecto.Despliegue.despliegueProyectoLocal;

import Proyecto.Dominio.Actividad;
import Proyecto.Dominio.ActividadTrabajador;
import Proyecto.Dominio.Etapa;
import Proyecto.Dominio.InformeSeguimiento;

import Proyecto.Dominio.Proyecto;
import Proyecto.Dominio.TrabajadoresProyecto;
import Trabajador.Despliegue.DespliegueTrabajadorLocal;
import Trabajador.Dominio.Administrador;
import Trabajador.Dominio.Rol;
import Trabajador.Dominio.RolCat;
import Trabajador.Dominio.Trabajador;
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
    private despliegueProyectoLocal despliegueProyecto;

    @EJB
    private DespliegueTrabajadorLocal despliegueTrabajador;

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    Trabajador t;
    ArrayList<Trabajador> trabajadores;
    Administrador a;
    ArrayList<Proyecto> misProyectos;
    Proyecto proyecto;
    ArrayList<TrabajadoresProyecto> tp;
    ArrayList<TrabajadoresProyecto> restantes;
    ArrayList<Trabajador> elegidos;
    ArrayList<Etapa> etapas;
    ArrayList<Actividad> actividades;
    ArrayList<Actividad> actEtapa;
    ArrayList<ActividadTrabajador> actividadTrabajador;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String accion = request.getParameter("accion");
        String url;
        switch (accion) {
            case "generaActividadTrabajador":
                url = otroTrabajador(request);
                break;
            case "Acceso":
                url = acceso(request);
                break;
            case "actividadConPredecesoras":
                url = actividadConPredecesoras(request);
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
            case "asignarTrabajador":
                url = asignarTrabajador(request);
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

        Trabajador trabajador = despliegueTrabajador.getTrabajador(usuario);
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
            } else {
                despliegueTrabajador.reservaVacaciones(trabajador, 1, fechaElegida.getYear(), fechaElegida, semanas);
            }
        } else {
            int semanas1 = Integer.parseInt(request.getParameter("semanas1"));
            Date fechaElegida1 = Date.valueOf(request.getParameter("fecha1"));
            int semanas2 = Integer.parseInt(request.getParameter("semanas2"));
            Date fechaElegida2 = Date.valueOf(request.getParameter("fecha2"));
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
        Trabajador tr = new Trabajador(request.getParameter("usuario"), request.getParameter("clave"), new Rol(request.getParameter("rol")), RolCat.dameCat(new Rol(request.getParameter("rol"))));
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
            //TODO pagina de informes

        }
        //TODO me planteo eliminar este return
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
            trabajadores = despliegueTrabajador.getTrabajadores();
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
        Actividad actividad = (new Actividad(proyecto.getNombre(), etapas.get(etapas.size() - 1).getNumero(), actEtapa.size(), request.getParameter("descripcion"), Integer.parseInt(request.getParameter("duracion")), null, Date.valueOf(request.getParameter("inicio")), Date.valueOf(request.getParameter("fin")), null, "planifcada", new Rol(request.getParameter("Rol"))));
        actividades.add(actividad);
        actEtapa.add(actividad);
        restantes = new ArrayList<>();
        ArrayList<Actividad> simultaneas;
        for (int i = 0; i < tp.size(); i++) {
            restantes.add(tp.get(i));
            simultaneas = new ArrayList<>();
            simultaneas = despliegueProyecto.misActividadesFecha(tp.get(i).getUser());
            System.out.println(simultaneas);
            for (int k = 0; k < simultaneas.size(); k++) {
                if ((actividad.getFechaComienzo().before(simultaneas.get(k).getFechaComienzo())) && (actividad.getFechaComienzo().after(simultaneas.get(k).getFechaFin()))
                        || (actividad.getFechaFin().after(simultaneas.get(k).getFechaComienzo())) && (actividad.getFechaFin().before(simultaneas.get(k).getFechaFin()))
                        || (simultaneas.get(k).getFechaComienzo().after(actividad.getFechaComienzo())) && (simultaneas.get(k).getFechaComienzo().before(actividad.getFechaFin()))
                        || (simultaneas.get(k).getFechaFin().after(actividad.getFechaComienzo())) && (simultaneas.get(k).getFechaFin().before(actividad.getFechaFin()))) {
                    simultaneas.remove(k);
                }
            }
            System.out.println(simultaneas);
            if (simultaneas.size() > 3) {
                restantes.remove(i);
            }
        }
        request.setAttribute("trabajadoresProyecto", restantes);
        return "/seleccionarTrabajadores.jsp";

    }

    public String mostrarInformes(HttpServletRequest request) {
        HttpSession sesion = request.getSession();
        Trabajador trabajador = (Trabajador) sesion.getAttribute("trabajador");
        proyecto = (Proyecto) sesion.getAttribute("proyecto");

        if (trabajador == null) {
            return "/index.jsp";
        }

        if (!trabajador.getUser().equals(proyecto.getJefe())) {
            request.setAttribute("error", "No puedes acceder a un proyecto que no es tuyo");
            return "/accesoUsuario.jsp";
        }

        ArrayList<InformeSeguimiento> informes = despliegueProyecto.getInformesProyecto(proyecto.getNombre());
        request.setAttribute("informes", informes);

        return "/informes.jsp";
    }

    public String aprobarInforme(HttpServletRequest request) {
        HttpSession sesion = request.getSession();
        Trabajador trabajador = (Trabajador) sesion.getAttribute("trabajador");
        proyecto = (Proyecto) sesion.getAttribute("proyecto");

        if (trabajador == null) {
            return "/index.jsp";
        }

        if (proyecto.getJefe().equals(trabajador.getUser())) {
            request.setAttribute("error", "No puedes aprobar informes de proyectos que no lideras");
            return "/accesoUsuario.jsp";
        }

        InformeSeguimiento informe = new InformeSeguimiento();
        informe.setNombreProyecto(request.getParameter("nombreProyecto"));
        informe.setNumeroEtapa(Integer.parseInt(request.getParameter("numeroEtapa")));
        informe.setIdActividad(Integer.parseInt(request.getParameter("idActividad")));
        informe.setTrabajador(request.getParameter("trabajador"));
        informe.setNumTarea(Integer.parseInt(request.getParameter("numTarea")));
        informe.setSemana(java.sql.Date.valueOf(request.getParameter("semana")));

        despliegueProyecto.aprobarInforme(informe);

        ArrayList<InformeSeguimiento> informes = despliegueProyecto.getInformesPendientesProyecto(proyecto.getNombre());
        request.setAttribute("informes", informes);

        return "/informesPendientes.jsp";
    }

    public String rechazarInforme(HttpServletRequest request) {
        HttpSession sesion = request.getSession();
        Trabajador trabajador = (Trabajador) sesion.getAttribute("trabajador");
        proyecto = (Proyecto) sesion.getAttribute("proyecto");

        if (trabajador == null) {
            return "/index.jsp";
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

        return "/informesPendientes.jsp";
    }

    public String mostrarInformesPendientes(HttpServletRequest request) {
        HttpSession sesion = request.getSession();
        Trabajador trabajador = (Trabajador) sesion.getAttribute("trabajador");
        proyecto = (Proyecto) sesion.getAttribute("proyecto");

        if (trabajador == null) {
            return "/index.jsp";
        }

        if (!trabajador.getUser().equals(proyecto.getJefe())) {
            request.setAttribute("error", "No puedes acceder a un proyecto que no es tuyo");
            return "/accesoUsuario.jsp";
        }

        ArrayList<InformeSeguimiento> informes = despliegueProyecto.getInformesPendientesProyecto(proyecto.getNombre());
        request.setAttribute("informes", informes);

        return "/informesPendientes.jsp";
    }

    public String mostrarInformesNoEnviados(HttpServletRequest request) {
        HttpSession sesion = request.getSession();
        Trabajador trabajador = (Trabajador) sesion.getAttribute("trabajador");
        proyecto = (Proyecto) sesion.getAttribute("proyecto");

        if (trabajador == null) {
            return "/index.jsp";
        }

        if (!trabajador.getUser().equals(proyecto.getJefe())) {
            request.setAttribute("error", "No puedes acceder a un proyecto que no es tuyo");
            return "/accesoUsuario.jsp";
        }

        ArrayList<InformeSeguimiento> informes = despliegueProyecto.getInformesNoEnviadosProyecto(proyecto.getNombre());
        request.setAttribute("informes", informes);

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
                new Rol(request.getParameter("Rol")));
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
            System.out.println(simultaneas);
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
            System.out.println(simultaneas);
            if (simultaneas.size() > 3) {
                restantes.remove(i);
            }
            actividades.add(actividad);
            actEtapa.add(actividad);
        }
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
}
