package Controlador;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import Trabajador.Despliegue.DespliegueTrabajadorLocal;
import Trabajador.Dominio.Trabajador;
import java.io.IOException;
import java.sql.Date;
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
    
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String accion = request.getParameter("accion");
        String url = null;
        switch(accion){
            case "Acceso":
                url = acceso(request);
                break;
            case "vacaciones":
                url = vacaciones(request);
                break;
            case "reservaVacaciones":
                url = reservaVacaiones(request);
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
        if(t == null){
            return "/error.jsp";
        }
        else{
            if(!t.getPassword().equals(request.getParameter("clave"))){
                return "/error.jsp";
            }
            else{
                return "/accesoUsuario.jsp";
            }
        }
    }

    private String vacaciones(HttpServletRequest request) {
        boolean vcc = despliegueTrabajador.reservoVacaciones(t.getUser());
        if(vcc){
            return "/vacacionesReservadas.jsp";
        }
        else{
            return"/reservarVacaciones.jsp";
        }
    }

    private String reservaVacaiones(HttpServletRequest request) {
        
        Date fechaElegida = Date.valueOf(request.getParameter("fecha"));
        int semanas = Integer.parseInt(request.getParameter("semanas"));
        despliegueTrabajador.reservaVacaciones(t, 1, fechaElegida, semanas);
        return "/vacacionesGuardadas.jsp";
    }

}
