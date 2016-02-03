package Controlador;

import Proyecto.Despliegue.DespliegueProyectoLocal;

import Proyecto.Dominio.Actividad;
import Proyecto.Dominio.ActividadTrabajador;
import Proyecto.Dominio.Etapa;
import Proyecto.Dominio.InformeSeguimiento;

import Proyecto.Dominio.Proyecto;
import Proyecto.Dominio.Tarea;
import Proyecto.Dominio.TipoTarea;
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
import java.util.Scanner;
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
    Proyecto p;
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
    private ArrayList<Actividad> actEtapa, actividadesC, tmp2;
    private ArrayList<ActividadTrabajador> actividadTrabajador;
    ArrayList<Etapa> etapasC;
    ArrayList<Tarea> tareas;

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
            case "finPlanActividad":
                url = finPlanActividad(request);
                break;
            case "cierre":
                url = "/index.jsp";
                break;
            case "generaActividadTrabajador":
                url = otroTrabajador(request);
                break;
            case "guardarInforme":
                url = guardarInforme(request);
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
            case "informeApobado":
                url = aprobarInforme(request);
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
                url = ponerFechaEtapa(request);
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
                url = previoRegistro(request);
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
        //Old version
        //return "/accesoUsuario.jsp";
        //New version
        if (trabajador.getCategoria().getCategoria() == 10) {
            return "/accesoAdmin.jsp";

        } else {
            cerrados = despliegueProyecto.getMisProyectosActuales(trabajador);
            sesion.setAttribute("misProyectosActuales", cerrados);
            Proyecto planificar = despliegueProyecto.getProyectoPlanificar(trabajador.getUser());
            sesion.setAttribute("planificar", planificar);
            return "/seleccionProyectos.jsp";
        }
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
            String fechaTexto = request.getParameter("fecha1");
            Scanner sc = new Scanner(fechaTexto);
            sc.useDelimiter("/");
            String dia = sc.next();
            String mes = sc.next();
            String ano = sc.next();
            Date fechaElegida = Date.valueOf(ano + "-" + mes + "-" + dia);
            if (despliegueTrabajador.reservoVacaciones(trabajador.getUser(), (int) (1900 + fechaElegida.getYear()))) {
                return "/vacacionesReservadas.jsp";
            }
            if (semanas != 4 || fechaElegida.getDay() != 1 || fechaElegida.before(hoySql)) {
                return "/vacacionesErroneas.jsp";
            }
            ArrayList<Proyecto> tmpAct = despliegueProyecto.getMisProyectosActuales(trabajador);
            Date diaInicio = fechaElegida;
            Calendar c = Calendar.getInstance();
            c.setTime(fechaElegida);
            c.add(Calendar.DATE, (semanas * 7) - 1);
            Date fechaFin = new Date(c.getTime().getYear(), c.getTime().getMonth(), c.getTime().getDate());
            for (int i = 0; i < tmpAct.size(); i++) {
                if (!diaInicio.after(tmpAct.get(i).getFechaFin()) && !diaInicio.before(tmpAct.get(i).getFechaInicio())
                        || !fechaFin.after(tmpAct.get(i).getFechaFin()) && !fechaFin.before(tmpAct.get(i).getFechaInicio())
                        || !diaInicio.before(tmpAct.get(i).getFechaInicio()) && !fechaFin.after(tmpAct.get(i).getFechaFin())
                        || !diaInicio.after(tmpAct.get(i).getFechaInicio()) && !fechaFin.before(tmpAct.get(i).getFechaFin())) {
                    return "/vacacionesErroneas.jsp";

                }
            }
            despliegueTrabajador.reservaVacaciones(trabajador, 1, fechaElegida.getYear(), fechaElegida, semanas);
        } else {
            ArrayList<Proyecto> tmpAct = despliegueProyecto.getMisProyectosActuales(trabajador);
            int semanas1 = Integer.parseInt(request.getParameter("semanas1"));
            String fechaTexto = request.getParameter("fecha1");
            Scanner sc = new Scanner(fechaTexto);
            sc.useDelimiter("/");
            String dia = sc.next();
            String mes = sc.next();
            String ano = sc.next();
            Date fechaElegida1 = Date.valueOf(ano + "-" + mes + "-" + dia);
            Date diaInicio = fechaElegida1;
            Calendar cd = Calendar.getInstance();
            cd.setTime(fechaElegida1);
            cd.add(Calendar.DATE, (semanas1 * 7) - 1);
            Date fechaFin = new Date(cd.getTime().getYear(), cd.getTime().getMonth(), cd.getTime().getDate());
            for (int i = 0; i < tmpAct.size(); i++) {
                if (!diaInicio.after(tmpAct.get(i).getFechaFin()) && !diaInicio.before(tmpAct.get(i).getFechaInicio())
                        || !fechaFin.after(tmpAct.get(i).getFechaFin()) && !fechaFin.before(tmpAct.get(i).getFechaInicio())
                        || !diaInicio.before(tmpAct.get(i).getFechaInicio()) && !fechaFin.after(tmpAct.get(i).getFechaFin())
                        || !diaInicio.after(tmpAct.get(i).getFechaInicio()) && !fechaFin.before(tmpAct.get(i).getFechaFin())) {
                    return "/vacacionesErroneas.jsp";

                }
            }
            int semanas2 = Integer.parseInt(request.getParameter("semanas2"));
            fechaTexto = request.getParameter("fecha2");
            sc = new Scanner(fechaTexto);
            sc.useDelimiter("/");
            dia = sc.next();
            mes = sc.next();
            ano = sc.next();
            Date fechaElegida2 = Date.valueOf(ano + "-" + mes + "-" + dia);
            diaInicio = fechaElegida1;
            cd = Calendar.getInstance();
            cd.setTime(fechaElegida1);
            cd.add(Calendar.DATE, (semanas1 * 7) - 1);
            fechaFin = new Date(cd.getTime().getYear(), cd.getTime().getMonth(), cd.getTime().getDate());
            for (int i = 0; i < tmpAct.size(); i++) {
                if (!diaInicio.after(tmpAct.get(i).getFechaFin()) && !diaInicio.before(tmpAct.get(i).getFechaInicio())
                        || !fechaFin.after(tmpAct.get(i).getFechaFin()) && !fechaFin.before(tmpAct.get(i).getFechaInicio())
                        || !diaInicio.before(tmpAct.get(i).getFechaInicio()) && !fechaFin.after(tmpAct.get(i).getFechaFin())
                        || !diaInicio.after(tmpAct.get(i).getFechaInicio()) && !fechaFin.before(tmpAct.get(i).getFechaFin())) {
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
        String jefe = request.getParameter("gestor");
        String nombreProyecto = request.getParameter("nombre");
        Trabajador tr = despliegueTrabajador.getTrabajador(jefe);
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
        proyecto = (Proyecto) request.getSession().getAttribute("planificar");
        sesion.setAttribute("proyecto", proyecto);
        request.setAttribute("proyecto", proyecto);
        request.setAttribute("fallo", "no");
        return "/planificar.jsp";

    }

    private String planificado(HttpServletRequest request) {
        tp = new ArrayList<>();
        java.util.Date hoy = new java.util.Date();
        String fechaTexto = request.getParameter("inicio");
        Scanner sc = new Scanner(fechaTexto);
        sc.useDelimiter("/");
        String dia = sc.next();
        String mes = sc.next();
        String ano = sc.next();
        Date inicio = Date.valueOf(ano + "-" + mes + "-" + dia);
        if (inicio.before(hoy) || inicio.getDay() != 1) {
            request.setAttribute("proyecto", proyecto);
            request.setAttribute("fallo", "si");
            return "/planificar.jsp";
        } else {
            proyecto.setFechaInicio(inicio);
            trabajadores = despliegueTrabajador.getTrabajadores(proyecto.getJefe());
            for (int i = 0; i < trabajadores.size(); i++) {
                ArrayList<Proyecto> proyActuales = despliegueProyecto.getMisProyectosActuales(trabajadores.get(i));
                if (proyActuales.size() > 1) {
                    trabajadores.remove(i);
                }
            }
            elegidos = new ArrayList<>();
            request.getSession().setAttribute("trabajadores", trabajadores);
            return "/elegirTrabajadores.jsp";
        }
    }

    private String tomarDatos(HttpServletRequest request) {
        HttpSession sesion = request.getSession();
        actividadTrabajador = new ArrayList<>();
        etapas = new ArrayList<>();
        actividades = new ArrayList<>();
        sesion.setAttribute("actividadTrabajador", actividadTrabajador);
        actividades = new ArrayList<>();
        sesion.setAttribute("etapas", etapas);
        sesion.setAttribute("actividades", actividades);
        Trabajador tr = trabajadores.get(Integer.parseInt(request.getParameter("eleccion")));
        ArrayList<Proyecto> proyActuales = despliegueProyecto.getMisProyectosActuales(tr);
        int dedicacion = Integer.parseInt(request.getParameter("dedicacion"));
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
            request.getSession().setAttribute("trabajadores", trabajadores);
            tp.add(new TrabajadoresProyecto(proyecto.getNombre(), tr.getUser(), dedicacion));
            return "/elegirTrabajadores2.jsp";
        }

    }

    private String planificarActividades(HttpServletRequest request) {
        String fechaTexto = request.getParameter("inicio");
        Scanner sc = new Scanner(fechaTexto);
        sc.useDelimiter("/");
        String dia = sc.next();
        String mes = sc.next();
        String ano = sc.next();
        Date inicio = Date.valueOf(ano + "-" + mes + "-" + dia);
        if (inicio.before(proyecto.getFechaInicio()) || inicio.getDay() != 1) {
            return "/errorFechasEtapa.jsp";
        } else {
            actEtapa = new ArrayList<>();
            System.out.println(etapas.size());
            etapas.add(new Etapa(proyecto.getNombre(), (etapas.size() + 1), inicio, null, null, 0, 0, "realizando"));
            return "/actividades.jsp";
        }
    }

    private String asignarTrabajador(HttpServletRequest request) {
        java.util.Date hoy = new java.util.Date();
        String fechaTexto = request.getParameter("inicio");
        Scanner sc = new Scanner(fechaTexto);
        sc.useDelimiter("/");
        String dia = sc.next();
        String mes = sc.next();
        String ano = sc.next();
        Date begin = Date.valueOf(ano + "-" + mes + "-" + dia);
        fechaTexto = request.getParameter("fin");
        sc = new Scanner(fechaTexto);
        sc.useDelimiter("/");
        dia = sc.next();
        mes = sc.next();
        ano = sc.next();
        Date end = Date.valueOf(ano + "-" + mes + "-" + dia);
        if (begin.after(end) || begin.before(etapas.get(etapas.size() - 1).getFechaInicio())) {
            return "/actividadesError.jsp";
        }
        Actividad actividad = (new Actividad(proyecto.getNombre(), etapas.get(etapas.size() - 1).getNumero(), actEtapa.size(), request.getParameter("descripcion"), Integer.parseInt(request.getParameter("duracion")), null, begin, end, null, "planifcada", Rol.get(request.getParameter("Rol"))));
        restantes = new ArrayList<>();
        ArrayList<Actividad> simultaneas;
        for (int i = 0; i < tp.size(); i++) {

            Trabajador tmptr = despliegueTrabajador.getTrabajador(tp.get(i).getUser());
            if (actividad.getTipoRol().getCategoria().getCategoria() < tmptr.getCategoria().getCategoria()) {

            } else {
                simultaneas = new ArrayList<>();
                ArrayList<Actividad> simultaneas2 = new ArrayList<>();
                simultaneas2 = despliegueProyecto.misActividadesFecha(tp.get(i).getUser());
                for (int p = 0; p < simultaneas2.size(); p++) {
                    System.out.println(simultaneas2.get(p).getFechaComienzo() + " " + simultaneas2.get(p).getFechaFin());
                }
                if (simultaneas2 != null) {
                    for (int k = 0; k < simultaneas2.size(); k++) {
                        if (!(actividad.getFechaComienzo().before(simultaneas2.get(k).getFechaComienzo())) && !(actividad.getFechaComienzo().after(simultaneas2.get(k).getFechaFin()))
                                || !(actividad.getFechaFin().before(simultaneas2.get(k).getFechaComienzo())) && !(actividad.getFechaFin().after(simultaneas2.get(k).getFechaFin()))
                                || !(actividad.getFechaComienzo().before(simultaneas2.get(k).getFechaComienzo())) && !(actividad.getFechaFin().after(simultaneas2.get(k).getFechaFin()))
                                || !(actividad.getFechaComienzo().after(simultaneas2.get(k).getFechaComienzo())) && !(actividad.getFechaFin().before(simultaneas2.get(k).getFechaFin()))) {
                            simultaneas.add(simultaneas2.get(k));
                        }
                    }
                    for (int p = 0; p < simultaneas.size(); p++) {
                        System.out.println(simultaneas.get(p).getFechaComienzo() + " " + simultaneas.get(p).getFechaFin());
                    }
                    if (simultaneas.size() <= 3) {
                        restantes.add(tp.get(i));
                    }
                }
            }
        }
        System.out.println(restantes);
        ArrayList<TrabajadoresProyecto> tmpres = restantes;
        restantes = new ArrayList<TrabajadoresProyecto>();
        for (int i = 0; i < tmpres.size(); i++) {
            ArrayList<Vacaciones> vc = despliegueTrabajador.getVacaciones(tmpres.get(i).getUser());
            if (!vc.isEmpty()) {
                for (int j = 0; j < vc.size(); j++) {
                    java.util.Date inicio = vc.get(j).getInicio();
                    Calendar c = Calendar.getInstance();
                    c.setTime(vc.get(j).getInicio());
                    c.add(Calendar.DATE, (vc.get(j).getSemanas() * 7) - 1);
                    java.util.Date fin = c.getTime();
                    if (!(actividad.getFechaComienzo().before(inicio)) && (actividad.getFechaComienzo().after(fin))
                            || (actividad.getFechaFin().after(inicio)) && (actividad.getFechaFin().before(fin))
                            || (inicio.after(actividad.getFechaComienzo())) && (inicio.before(actividad.getFechaFin()))
                            || (fin.after(actividad.getFechaComienzo())) && (fin.before(actividad.getFechaFin()))) {
                        restantes.add(tmpres.get(i));
                        break;
                    }
                }
            } else {
                restantes.add(tmpres.get(i));
            }
        }
        System.out.println(restantes);
        actividades.add(actividad);
        actEtapa.add(actividad);
        request.getSession().setAttribute("trabajadoresProyecto", restantes);
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
        tareas = new ArrayList<>();
        Trabajador trabajador = (Trabajador) sesion.getAttribute("trabajador");
        Actividad act;
        sesion.setAttribute("trabajador", trabajador);
        if (trabajador.getUser().equals(p.getJefe())) {
            act = tmp2.get(Integer.parseInt(request.getParameter("elegida")));
            tareas = despliegueProyecto.getInformesActividad(act);
            sesion.setAttribute("tareas", tareas);
        } else {
            ArrayList<Integer> rest = new ArrayList<>();
            act = actividadesC.get(Integer.parseInt(request.getParameter("elegida")));
            ArrayList<Tarea> tmptareas = despliegueProyecto.getInformesActividadMios(act, trabajador);
            int cont = despliegueProyecto.getContribucion(trabajador, p);
            cont = (cont * 40) / 100;
            if (act.getFechaComienzo().equals(act.getFechaFin())) {
                ArrayList<Tarea> t1 = despliegueProyecto.getTareasTiempo(trabajador, act.getFechaComienzo(), act);
                if (t1 != null) {
                    for (int i = 0; i < t1.size(); i++) {
                        cont -= t1.get(i).getDuracion();
                    }
                }
                rest.add(cont);
            } else {
                Calendar inicio = Calendar.getInstance();
                inicio.setTime(act.getFechaComienzo());
                Calendar fin = Calendar.getInstance();
                fin.setTime(act.getFechaFin());
                Calendar i = Calendar.getInstance();
                int cont2;
                for (i.setTime(act.getFechaComienzo()); !i.after(fin); i.add(Calendar.DAY_OF_MONTH, 7)) {
                    cont2 = cont;
                    System.out.println(i.getTime().toString());
                    ArrayList<Tarea> t1 = despliegueProyecto.getTareasTiempo(trabajador, i.getTime(), act);
                    System.out.println(t1);
                    if (t1 != null) {
                        for (int j = 0; j < t1.size(); j++) {
                            cont2 -= t1.get(j).getDuracion();
                        }
                    }
                    rest.add(cont2);
                }
            }

            java.util.Date hoy = new java.util.Date();
            for (int i = 0; i < tmptareas.size(); i++) {
                if (!tmptareas.get(i).getSemana().after(hoy)) {
                    tareas.add(tmptareas.get(i));
                }
            }
            sesion.setAttribute("horas", rest);
            sesion.setAttribute("tareas", tareas);
        }

        sesion.setAttribute(
                "actividad", act);

        return "/informes.jsp";
    }

    public String aprobarInforme(HttpServletRequest request) {
        int tar = Integer.parseInt(request.getParameter("tareasAcepto"));
        System.out.println(tareas.get(tar).getIdActividad());
        despliegueProyecto.aprobarInforme(tareas.get(tar));
        request.getSession().setAttribute("trabajador", trabajador);
        return "/informesAprobados.jsp";
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
        actividadTrabajador.add(new ActividadTrabajador(actividades.get(actividades.size() - 1), tptmp));
        request.setAttribute("trabajadoresProyecto", restantes);
        return "/otroTrabajador.jsp";
    }

    private String finalizarActividad(HttpServletRequest request) {
        int elegido = Integer.parseInt(request.getParameter("elegir"));
        //request.getSession().setAttribute("elegir", elegido);
        Actividad act = tmp2.get(elegido);
        ArrayList<Tarea> tr = despliegueProyecto.getMisTareas(act);
        for (int i = 0; i < tr.size(); i++) {
            if (!tr.get(i).getEstado().equals("Aceptado")) {
                return "/errorCierreActividad.jsp";
            }
        }
        int duracion = 0;
        for (int i = 0; i < tr.size(); i++) {
            duracion += tr.get(i).getDuracion();

        }
        java.util.Date fechaFinReal = new java.util.Date();
        Calendar c = Calendar.getInstance();
        c.setTime(fechaFinReal);
        c.add(Calendar.DATE, (fechaFinReal.getDay() - 1) * -1);
        fechaFinReal = c.getTime();
        act.setFechaFinReal(fechaFinReal);
        act.setDuracionReal(duracion);
        act.setEstado("finalizado");
        despliegueProyecto.cierreActividad(act);
        request.getSession().setAttribute("cerrada", act);
        return "/actividadCerrada.jsp";
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
        java.util.Date hoy = new java.util.Date();
        String fechaTexto = request.getParameter("inicio");
        Scanner sc = new Scanner(fechaTexto);
        sc.useDelimiter("/");
        String dia = sc.next();
        String mes = sc.next();
        String ano = sc.next();
        Date begin = Date.valueOf(ano + "-" + mes + "-" + dia);
        fechaTexto = request.getParameter("fin");
        sc = new Scanner(fechaTexto);
        sc.useDelimiter("/");
        dia = sc.next();
        mes = sc.next();
        ano = sc.next();
        Date end = Date.valueOf(ano + "-" + mes + "-" + dia);
        Actividad actividad = (new Actividad(proyecto.getNombre(), etapas.get(etapas.size() - 1).getNumero(), actEtapa.size(), request.getParameter("descripcion"), Integer.parseInt(request.getParameter("duracion")), null, begin, end, null, "planifcada", Rol.get(request.getParameter("Rol"))));
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
            Trabajador tmptr = despliegueTrabajador.getTrabajador(tp.get(i).getUser());
            if (actividad.getTipoRol().getCategoria().getCategoria() >= tmptr.getCategoria().getCategoria()) {
                simultaneas = new ArrayList<>();
                ArrayList<Actividad> simultaneas2 = despliegueProyecto.misActividadesFecha(tp.get(i).getUser());
                if (simultaneas2 != null) {
                    for (int k = 0; k < simultaneas2.size(); k++) {
                        if (!(actividad.getFechaComienzo().before(simultaneas2.get(k).getFechaComienzo())) && !(actividad.getFechaComienzo().after(simultaneas2.get(k).getFechaFin()))
                                || !(actividad.getFechaFin().before(simultaneas2.get(k).getFechaComienzo())) && !(actividad.getFechaFin().after(simultaneas2.get(k).getFechaFin()))
                                || !(actividad.getFechaComienzo().before(simultaneas2.get(k).getFechaComienzo())) && !(actividad.getFechaFin().after(simultaneas2.get(k).getFechaFin()))
                                || !(actividad.getFechaComienzo().after(simultaneas2.get(k).getFechaComienzo())) && !(actividad.getFechaFin().before(simultaneas2.get(k).getFechaFin()))) {
                            simultaneas.add(simultaneas2.get(k));
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

                    if (simultaneas.size() <= 3) {
                        restantes.add(tp.get(i));
                    }
                } else {
                    restantes.add(tp.get(i));
                }
            }
        }

        for (int i = restantes.size() - 1; i >= 0; i--) {
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
                        System.out.println(2 + "--" + restantes);
                        break;
                    }
                }
            }
        }

        actividades.add(actividad);
        actEtapa.add(actividad);
        request.getSession().setAttribute("trabajadoresProyecto", restantes);
        return "/seleccionarTrabajadores.jsp";
    }

    private String planificarSigsEtapas(HttpServletRequest request) {
        System.out.println(etapas.get(etapas.size() - 1).getFechaFin());
        String fechaTexto = request.getParameter("inicio");
        Scanner sc = new Scanner(fechaTexto);
        sc.useDelimiter("/");
        String dia = sc.next();
        String mes = sc.next();
        String ano = sc.next();
        Date inicio = Date.valueOf(ano + "-" + mes + "-" + dia);
        if (inicio.before(proyecto.getFechaInicio()) || inicio.getDay() != 1 || !inicio.after(etapas.get(etapas.size() - 1).getFechaFin())) {
            return "/errorFechasEtapa.jsp";
        } else {
            actEtapa = new ArrayList<>();
            System.out.println(etapas.size());
            etapas.add(new Etapa(proyecto.getNombre(), (etapas.size() + 1), inicio, null, null, 0, 0, "realizando"));
            return "/actividades.jsp";
        }
    }

    private String finalizarPlanProyecto(HttpServletRequest request) {
        Date hoy = new Date(System.currentTimeMillis());
        int duracion = 0;
        for (int i = 0; i < etapas.size(); i++) {
            duracion += etapas.get(i).getDuracion();
            if (etapas.get(i).getFechaFin().after(hoy)) {
                hoy = new Date(etapas.get(i).getFechaFin().getTime());
            }
        }
        proyecto.setFechaFin(hoy);
        proyecto.setDuracion(duracion);
        despliegueProyecto.guardarProyecto(proyecto);
        despliegueProyecto.guardarEtapas(etapas);
        despliegueProyecto.guardarActividades(actividades);
        despliegueProyecto.guardarTrabajadores(tp);
        despliegueProyecto.guardarAsignaciones(actividadTrabajador);
        ArrayList<Tarea> tareas = new ArrayList<>();
        for (int i = 0; i < actividades.size(); i++) {
            ArrayList<ActividadTrabajador> at = new ArrayList<>();
            for (int j = 0; j < actividadTrabajador.size(); j++) {
                if (actividadTrabajador.get(j).getNumeroEtapa() == actividades.get(i).getNumero()
                        && actividadTrabajador.get(j).getIdActividad() == actividades.get(i).getId()) {
                    at.add(actividadTrabajador.get(j));
                }

            }
            Calendar c = Calendar.getInstance();
            c.setTime(new java.util.Date(actividades.get(i).getFechaComienzo().getYear(), actividades.get(i).getFechaComienzo().getMonth(), actividades.get(i).getFechaComienzo().getDate()));
            if (c.getTime().getYear() == actividades.get(i).getFechaFin().getYear() || c.getTime().getMonth() == actividades.get(i).getFechaFin().getMonth() || c.getTime().getDate() == actividades.get(i).getFechaFin().getDate()) {
                for (int j = 0; j < at.size(); j++) {
                    Tarea t = new Tarea(actividades.get(i).getNombre(), actividades.get(i).getNumero(), actividades.get(i).getId(), at.get(j).getNombreTrabajador(), c.getTime(), TipoTarea.documentacion, 0, "Pendiente");
                    tareas.add(t);
                    t = new Tarea(actividades.get(i).getNombre(), actividades.get(i).getNumero(), actividades.get(i).getId(), at.get(j).getNombreTrabajador(), c.getTime(), TipoTarea.elaboracion, 0, "Pendiente");
                    tareas.add(t);
                    t = new Tarea(actividades.get(i).getNombre(), actividades.get(i).getNumero(), actividades.get(i).getId(), at.get(j).getNombreTrabajador(), c.getTime(), TipoTarea.otras, 0, "Pendiente");
                    tareas.add(t);
                    t = new Tarea(actividades.get(i).getNombre(), actividades.get(i).getNumero(), actividades.get(i).getId(), at.get(j).getNombreTrabajador(), c.getTime(), TipoTarea.programas, 0, "Pendiente");
                    tareas.add(t);
                    t = new Tarea(actividades.get(i).getNombre(), actividades.get(i).getNumero(), actividades.get(i).getId(), at.get(j).getNombreTrabajador(), c.getTime(), TipoTarea.reuniones, 0, "Pendiente");
                    tareas.add(t);
                    t = new Tarea(actividades.get(i).getNombre(), actividades.get(i).getNumero(), actividades.get(i).getId(), at.get(j).getNombreTrabajador(), c.getTime(), TipoTarea.tratoUsuarios, 0, "Pendiente");
                    tareas.add(t);
                }
            } else {
                do {
                    for (int j = 0; j < at.size(); j++) {
                        Tarea t = new Tarea(actividades.get(i).getNombre(), actividades.get(i).getNumero(), actividades.get(i).getId(), at.get(j).getNombreTrabajador(), c.getTime(), TipoTarea.documentacion, 0, "Pendiente");
                        tareas.add(t);
                        t = new Tarea(actividades.get(i).getNombre(), actividades.get(i).getNumero(), actividades.get(i).getId(), at.get(j).getNombreTrabajador(), c.getTime(), TipoTarea.elaboracion, 0, "Pendiente");
                        tareas.add(t);
                        t = new Tarea(actividades.get(i).getNombre(), actividades.get(i).getNumero(), actividades.get(i).getId(), at.get(j).getNombreTrabajador(), c.getTime(), TipoTarea.otras, 0, "Pendiente");
                        tareas.add(t);
                        t = new Tarea(actividades.get(i).getNombre(), actividades.get(i).getNumero(), actividades.get(i).getId(), at.get(j).getNombreTrabajador(), c.getTime(), TipoTarea.programas, 0, "Pendiente");
                        tareas.add(t);
                        t = new Tarea(actividades.get(i).getNombre(), actividades.get(i).getNumero(), actividades.get(i).getId(), at.get(j).getNombreTrabajador(), c.getTime(), TipoTarea.reuniones, 0, "Pendiente");
                        tareas.add(t);
                        t = new Tarea(actividades.get(i).getNombre(), actividades.get(i).getNumero(), actividades.get(i).getId(), at.get(j).getNombreTrabajador(), c.getTime(), TipoTarea.tratoUsuarios, 0, "Pendiente");
                        tareas.add(t);
                    }
                    c.add(Calendar.DATE, 7);
                } while (c.getTime().getYear() != actividades.get(i).getFechaFin().getYear() || c.getTime().getMonth() != actividades.get(i).getFechaFin().getMonth() || c.getTime().getDate() != actividades.get(i).getFechaFin().getDate());
                for (int j = 0; j < at.size(); j++) {
                    Tarea t = new Tarea(actividades.get(i).getNombre(), actividades.get(i).getNumero(), actividades.get(i).getId(), at.get(j).getNombreTrabajador(), c.getTime(), TipoTarea.documentacion, 0, "Pendiente");
                    tareas.add(t);
                    t = new Tarea(actividades.get(i).getNombre(), actividades.get(i).getNumero(), actividades.get(i).getId(), at.get(j).getNombreTrabajador(), actividades.get(i).getFechaFin(), TipoTarea.elaboracion, 0, "Pendiente");
                    tareas.add(t);
                    t = new Tarea(actividades.get(i).getNombre(), actividades.get(i).getNumero(), actividades.get(i).getId(), at.get(j).getNombreTrabajador(), actividades.get(i).getFechaFin(), TipoTarea.otras, 0, "Pendiente");
                    tareas.add(t);
                    t = new Tarea(actividades.get(i).getNombre(), actividades.get(i).getNumero(), actividades.get(i).getId(), at.get(j).getNombreTrabajador(), actividades.get(i).getFechaFin(), TipoTarea.programas, 0, "Pendiente");
                    tareas.add(t);
                    t = new Tarea(actividades.get(i).getNombre(), actividades.get(i).getNumero(), actividades.get(i).getId(), at.get(j).getNombreTrabajador(), actividades.get(i).getFechaFin(), TipoTarea.reuniones, 0, "Pendiente");
                    tareas.add(t);
                    t = new Tarea(actividades.get(i).getNombre(), actividades.get(i).getNumero(), actividades.get(i).getId(), at.get(j).getNombreTrabajador(), actividades.get(i).getFechaFin(), TipoTarea.tratoUsuarios, 0, "Pendiente");
                    tareas.add(t);
                }

            }
        }
        for (int i = 0; i < tareas.size(); i++) {
            despliegueProyecto.guardarTarea(tareas.get(i));
        }
        return "/index.jsp";
    }

    private String sobreesfuerzo(HttpServletRequest request) {
        HttpSession sesion = request.getSession();
        Trabajador trabajador = (Trabajador) sesion.getAttribute("trabajador");

        if (trabajador == null) {
            return "/index.jsp";
        }

        ArrayList<Actividad> sobreesfuerzo = new ArrayList<>();
        sobreesfuerzo = despliegueProyecto.getSobreesfuerzo(trabajador.getUser());
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
        sesion.setAttribute("abiertos", cerrados);
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
        int elegir = Integer.parseInt(request.getParameter("elegir"));
        Etapa et = etapasC.get(elegir);
        int duracion = 0;
        request.getSession().setAttribute("elegir", elegir);
        ArrayList<Actividad> act = despliegueProyecto.getActividadesEtapa(et);
        for (int i = 0; i < act.size(); i++) {
            if (!act.get(i).getEstado().equals("finalizado")) {
                return "/errorCierreEtapa.jsp";
            }
            duracion += act.get(i).getDuracionReal();

        }
        et.setDuracionReal(duracion);
        et.setEstado("finalizado");
        java.util.Date fechaFinReal = new java.util.Date();
        Calendar c = Calendar.getInstance();
        c.setTime(fechaFinReal);
        c.add(Calendar.DATE, (fechaFinReal.getDay() - 1) * -1);
        fechaFinReal = c.getTime();
        et.setFechaFinReal(fechaFinReal);
        despliegueProyecto.cierreEtapa(et);
        request.getSession().setAttribute("etapaCerrada", et);
        return "/etapaCerrada.jsp";
    }

    private String finalizarProyecto(HttpServletRequest request) {
        ArrayList<Etapa> et = despliegueProyecto.getEtapas(p.getNombre());
        int duracion = 0;
        for (int i = 0; i < et.size(); i++) {
            duracion += et.get(i).getDuracionReal();
            if (!et.get(i).getEstado().equals("finalizado")) {
                return "/errorCierreProyecto.jsp";
            }
        }
        p.setDuracionReal(duracion);
        p.setEstado("cerrado");
        java.util.Date fechaFinReal = new java.util.Date();
        Calendar c = Calendar.getInstance();
        c.setTime(fechaFinReal);
        c.add(Calendar.DATE, (fechaFinReal.getDay() - 1) * -1);
        fechaFinReal = c.getTime();
        p.setFechaFinReal(fechaFinReal);
        despliegueProyecto.cierreProyecto(p);
        request.getSession().setAttribute("proyectoCerrado", p);
        return "/proyectoCerrado.jsp";
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
        int selected = Integer.parseInt(request.getParameter("eleccion"));
        p = cerrados.get(selected);
        sesion.setAttribute("proyecto", p);
        etapasC = despliegueProyecto.getEtapas(p.getNombre());
        actividadesC = new ArrayList<>();
        tmp2 = new ArrayList<>();
        for (int i = 0; i < etapasC.size(); i++) {
            ArrayList<Actividad> tmp = new ArrayList<>();
            tmp = despliegueProyecto.getActividadesCerrados(p.getNombre(), etapasC.get(i).getNumero());
            for (int j = 0; j < tmp.size(); j++) {
                tmp2.add(tmp.get(j));
                if (!despliegueProyecto.tieneAntecesoras(tmp2.get(j))) {
                    tmp2.get(j).setEstado("Predecesoras en realizacion");
                }
            }

        }
        java.util.Date hoy = new java.util.Date();
        if (!p.getJefe().equals(trabajador.getUser())) {
            for (int i = 0; i < tmp2.size(); i++) {
                if (despliegueProyecto.isAsignado(tmp2.get(i), trabajador.getUser()) && tmp2.get(i).getFechaComienzo().before(hoy)) {
                    actividadesC.add(tmp2.get(i));
                }
            }
            for (int i = 0; i < actividadesC.size(); i++) {
                if (!despliegueProyecto.tieneAntecesoras(actividadesC.get(i))) {
                    actividadesC.get(i).setEstado("Predecesoras en realizacion");
                }
            }
            sesion.setAttribute("actividades", actividadesC);
        } else {
            sesion.setAttribute("actividades", tmp2);
        }
        sesion.setAttribute("selected", selected);
        sesion.setAttribute("trabajador", trabajador);
        sesion.setAttribute("proyecto", p);
        sesion.setAttribute("etapas", etapasC);
        return "/vistaAbierto.jsp";
    }

    private String guardarInforme(HttpServletRequest request) {
        int j = Integer.parseInt(request.getParameter("inicio"));
        ArrayList<String> estado = new ArrayList<>();
        int duracion = 0;
        int limite = Integer.parseInt(request.getParameter("horas"));
        estado.add("documentacion");
        estado.add("elaboracion");
        estado.add("otras");
        estado.add("programas");
        estado.add("reuniones");
        estado.add("tratoUsuarios");
        for (int i = j; i < j + 6; i++) {
            duracion += Integer.parseInt(request.getParameter("get-" + i % 6));
        }
        if (limite < duracion) {
            return "/errorHoras.jsp";
        } else {
            for (int i = j; i < j + 6; i++) {
                tareas.get(i).setDuracion(Integer.parseInt(request.getParameter("get-" + i % 6)));
                tareas.get(i).setEstado("Enviado");
                despliegueProyecto.guardaInforme(tareas.get(i), estado.get(i % 6));
            }
            return "/informes.jsp";
        }

    }

    private String finPlanActividad(HttpServletRequest request) {
        Actividad ac = actEtapa.get(actEtapa.size() - 1);
        int numSemanas = (ac.getFechaFin().compareTo(ac.getFechaComienzo()) / 7) + 1;
        request.setAttribute("posiblesPredecesoras", actEtapa);
        return "/actividadesFinalizar.jsp";
    }

    private String ponerFechaEtapa(HttpServletRequest request) {
        etapas.get(etapas.size() - 1).setDuracion(0);
        Date hoy = new Date(System.currentTimeMillis());
        etapas.get(etapas.size() - 1).setFechaFin(hoy);
        for (int i = 0; i < actEtapa.size(); i++) {
            etapas.get(etapas.size() - 1).setDuracion(etapas.get(etapas.size() - 1).getDuracion() + actEtapa.get(i).getDuracion());
            if (hoy.before(actEtapa.get(i).getFechaFin())) {
                etapas.get(etapas.size() - 1).setFechaFin(actEtapa.get(i).getFechaFin());
            }
        }
        return "/elegirSigsEtapas.jsp";
    }

    private String previoRegistro(HttpServletRequest request) {
        ArrayList<Trabajador> tmpJefes = despliegueTrabajador.getJefes();
        ArrayList<Trabajador> jefes = new ArrayList<Trabajador>();
        for (int i = 0; i < tmpJefes.size(); i++) {
            ArrayList<Proyecto> proyectosDeJefe = despliegueProyecto.getMisProyectos(tmpJefes.get(i).getUser());
            if (proyectosDeJefe.isEmpty()) {
                jefes.add(tmpJefes.get(i));
            }
        }
        request.getSession().setAttribute("jefes", jefes);
        return "/registroProyecto.jsp";
    }
}
