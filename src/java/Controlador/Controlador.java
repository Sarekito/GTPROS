package Controlador;

import Proyecto.Despliegue.despliegueProyectoLocal;
import Proyecto.Dominio.Proyecto;
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
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String accion = request.getParameter("accion");
        String url;

        switch (accion) {
            case "Acceso":
                url = acceso(request);
                break;
            case "vacaciones":
                url = vacaciones(request);
                break;
            case "reservaVacaciones":
                url = reservaVacaciones(request);
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

        Administrador a = despliegueTrabajador.getAdministrador(usuario);
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
        ArrayList<Proyecto> misProyectos = despliegueProyecto.getMisProyectos(trabajador.getUser());
        request.setAttribute("misProyectos", misProyectos);
        sesion.setAttribute("misProyectos", misProyectos);
        System.out.println(misProyectos);
        return "/verProyectos.jsp";
    }

    private String verProyecto(HttpServletRequest request) {
        HttpSession sesion = request.getSession();
        ArrayList<Proyecto> misProyectos = (ArrayList<Proyecto>) sesion.getAttribute("misProyectos");

        int elegido = Integer.parseInt(request.getParameter("eleccion"));
        Proyecto proyecto = misProyectos.get(elegido);
        sesion.setAttribute("proyecto", proyecto);
        if (proyecto.getEstado().equals("pendiente")) {
            request.setAttribute("proyecto", proyecto);
            request.setAttribute("fallo", "no");
            return "/planificar.jsp";
        }
        if (proyecto.getEstado().equals("planificado")) {
            //TODO pagina de error

        }
        if (proyecto.getEstado().equals("realizando")) {
            //TODO pagina de informes

        }
        //TODO me planteo eliminar este return
        return null;
    }

    private String planificado(HttpServletRequest request) {
        HttpSession sesion = request.getSession();
        Trabajador trabajador = (Trabajador) sesion.getAttribute("trabajador");
        Proyecto proyecto = (Proyecto) sesion.getAttribute("proyecto");

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

            ArrayList<Trabajador> trabajadores = despliegueTrabajador.getTrabajadores();
            for (int i = 0; i < trabajadores.size(); i++) {
                if (despliegueTrabajador.getNumProyectosActivos(trabajadores.get(i)) > 1 || trabajador.getUser().equals(trabajadores.get(i).getUser())) {
                    trabajadores.remove(i);
                }
            }
            request.setAttribute("trabajadores", trabajadores);
            return "/elegirTrabajadores.jsp";
        }
    }
}
