package Controlador;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import Trabajador.Despliegue.DespliegueTrabajadorLocal;
import Trabajador.Dominio.Administrador;
import Trabajador.Dominio.Rol;
import Trabajador.Dominio.RolCat;
import Trabajador.Dominio.Trabajador;
import java.io.IOException;
import java.sql.Date;
import java.util.Calendar;
import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author antonio
 */
public class Controlador extends HttpServlet {

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
    Administrador a;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String accion = request.getParameter("accion");
        String url = null;
        switch (accion) {
            case "Acceso":
                url = acceso(request);
                break;
            case "vacaciones":
                url = vacaciones(request);
                break;
            case "reservaVacaciones":
                url = reservaVacaiones(request);
                break;
            case "entrarAdmin":
                url = entrarAdmin(request);
                break;
            case "registrarTrabajador":
                url = "/registroTrabajador.jsp";
                break;
            case "registrarProyecto":
                url = "/registroProyecto";
                break;
            case "registroTrabajador":
                url = registroTrabajador(request);
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
        t = despliegueTrabajador.getTrabajador(request.getParameter("usuario"));
        if (t == null) {
            return "/error.jsp";
        } else {
            if (!t.getPassword().equals(request.getParameter("clave"))) {
                return "/error.jsp";
            } else {
                return "/accesoUsuario.jsp";
            }
        }
    }

    private String vacaciones(HttpServletRequest request) {
        boolean vcc = despliegueTrabajador.reservoVacaciones(t.getUser());
        if (vcc) {
            return "/vacacionesReservadas.jsp";
        } else {
            return "/reservarVacaciones.jsp";
        }
    }

    private String reservaVacaiones(HttpServletRequest request) {
        java.util.Date hoy = new java.util.Date();
        Date hoySql = new Date(hoy.getYear(), hoy.getMonth(), hoy.getDate());
        if (request.getParameter("periodos") == null) {
            int semanas = Integer.parseInt(request.getParameter("semanas1"));
            Date fechaElegida = Date.valueOf(request.getParameter("fecha1"));
            if (semanas != 4 || fechaElegida.getDay() != 1 || fechaElegida.before(hoySql)) {
                return "/vacacionesErroneas.jsp";
            } else {
                despliegueTrabajador.reservaVacaciones(t, 1, fechaElegida, semanas);
            }
        } else {
            int semanas1 = Integer.parseInt(request.getParameter("semanas1"));
            Date fechaElegida1 = Date.valueOf(request.getParameter("fecha1"));
            int semanas2 = Integer.parseInt(request.getParameter("semanas2"));
            Date fechaElegida2 = Date.valueOf(request.getParameter("fecha2"));
            Calendar c = Calendar.getInstance();
            c.setTime(fechaElegida1);
            c.add(Calendar.DATE, (semanas1 * 7) - 1);
            Date fechaFin1 = new Date(c.getTime().getYear(), c.getTime().getMonth(), c.getTime().getDate());
            System.out.println(fechaElegida1.toLocalDate());
            System.out.println(fechaElegida2.toLocalDate());
            System.out.println(fechaFin1.toLocalDate());
            if (semanas1 + semanas2 != 4 || fechaElegida2.getDay() != 1 || fechaElegida1.getDay() != 1
                    || fechaElegida1.after(fechaElegida2) || fechaElegida2.before(fechaFin1)
                    || fechaElegida1.before(hoySql) || fechaElegida2.before(hoySql)) {
                return "/vacacionesErroneas.jsp";
            } else {
                despliegueTrabajador.reservaVacaciones(t, 1, fechaElegida1, semanas1);
                despliegueTrabajador.reservaVacaciones(t, 2, fechaElegida2, semanas2);
            }

        }
        return "/vacacionesGuardadas.jsp";
    }

    private String entrarAdmin(HttpServletRequest request) {
        a = despliegueTrabajador.getAdministrador(request.getParameter("usuario"));
        if (a == null) {
            return "/error.jsp";
        } else {
            if (!a.getPassword().equals(request.getParameter("clave"))) {
                return "/error.jsp";
            } else {
                return "/accesoAdmin.jsp";
            }
        }
    }

    private String registroTrabajador(HttpServletRequest request) {
        Trabajador tr = new Trabajador(request.getParameter("usuario"), request.getParameter("clave"), new Rol(request.getParameter("rol")), RolCat.dameCat(new Rol(request.getParameter("rol"))));
        boolean existe = despliegueTrabajador.buscaTrabajador(tr.getUser());
        if(!existe){
            despliegueTrabajador.registrarTrabajador(tr);
            return "/creacionConExito.jsp";
        }
        else{
            return "/trabajadorCreado.jsp";
        }
        
    }
}
