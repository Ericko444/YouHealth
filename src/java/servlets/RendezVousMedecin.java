/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import health.DetailsRendezVous;
import health.Medecin;
import health.RendezVous;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import services.RendezVousService;

/**
 *
 * @author Ericko
 */
@WebServlet(name = "RendezVousMedecin", urlPatterns = {"/RendezVousMedecin"})
public class RendezVousMedecin extends HttpServlet {

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
            throws ServletException, IOException, Exception {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            String action = request.getParameter("action");
            HttpSession session = request.getSession(false);
            Medecin med = (Medecin) session.getAttribute("user-info");
            if(null != action)
            {
                switch (action) {
                    case "all":
                        out.print(med.getIdMedecin());
                        ArrayList<DetailsRendezVous> allRdv = RendezVousService.findByMed(med.getIdMedecin());
                        out.print(allRdv.get(0).getPatientAge());
                        request.setAttribute("listRdv", allRdv);
                        request.getRequestDispatcher("liste_rdv.jsp").forward(request, response);
                        break;
                    case "annuler":{
                        String id = request.getParameter("rdv");
                        RendezVousService.AnnulerRdv(id);
                        response.sendRedirect(request.getContextPath() + "/RendezVousMedecin?action=all");
                        break;
                    }
                    case "modif":{
                        String id = request.getParameter("rdv");
                        String date = request.getParameter("dateRdv");
                        RendezVousService.ModifierRdv(id, date);
                        response.sendRedirect(request.getContextPath() + "/RendezVousMedecin?action=all");
                            break;
                        }
                    default:
                        break;
                }
            }
        }
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
        try {
            processRequest(request, response);
        } catch (Exception ex) {
            Logger.getLogger(RendezVousMedecin.class.getName()).log(Level.SEVERE, null, ex);
        }
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
        try {
            processRequest(request, response);
        } catch (Exception ex) {
            Logger.getLogger(RendezVousMedecin.class.getName()).log(Level.SEVERE, null, ex);
        }
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

}
