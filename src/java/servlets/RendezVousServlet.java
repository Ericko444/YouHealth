/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import health.Medecin;
import health.Patient;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import services.MedecinService;
import services.RendezVousService;
import util.Util;

/**
 *
 * @author Ericko
 */
public class RendezVousServlet extends HttpServlet {

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
            switch (action) {
                case "searchMedecin":
                    {
                        String spec = request.getParameter("specialiste");
                        System.out.println(spec);
                        String date = request.getParameter("dateRdv");
                        System.out.println("Date = " + date);
                        ArrayList<Medecin> listAvailable = MedecinService.findFreeMedecinOnADate(spec, date);
                        int countResult = 0;
                        int limit = 5;
                        countResult = listAvailable.size();
                        if (countResult > limit) {
                            String idpage = request.getParameter("page");
                            int pageid1 = Integer.parseInt(idpage);
                            int debut = (pageid1 - 1) * limit;
                            listAvailable = MedecinService.findAvailable(spec, date, limit, debut);

                        }
                        int isany = countResult;
                        request.setAttribute("isany", isany);
                        int nbPages;
                        nbPages = (int) Math.ceil(isany / limit);
                        request.setAttribute("nbPages", nbPages);
                        if (date.length() > 16) {
                            request.setAttribute("date", date);
                        } else if (date.length() == 16) {
                            request.setAttribute("date", Util.convertDateTimeToTimestamp(date));
                        }
                        out.print(date);
                        request.setAttribute("specialite", spec);
                        request.setAttribute("docDispo", listAvailable);
                        request.getRequestDispatcher("result_doctors.jsp").forward(request, response);
                        break;
                    }
                case "init":
                    ArrayList<String> listSpec = MedecinService.listAllSpec();
                    request.setAttribute("listSpec", listSpec);
                    request.getRequestDispatcher("rendezvous.jsp").forward(request, response);
                    break;
                case "reserver":
                    {
                        HttpSession session = request.getSession(false);
                        Patient p = (Patient)session.getAttribute("user-info");
                        String idPatient = p.getIdPatient();
                        String idMedecin = request.getParameter("med");
                        String date = request.getParameter("date");
                        RendezVousService.insertNewRdv(idMedecin, idPatient, date);
                        response.sendRedirect("index_patient.jsp");
                        break;
                    }
                default:
                    out.println("No request!");
                    break;
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
            Logger.getLogger(RendezVousServlet.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(RendezVousServlet.class.getName()).log(Level.SEVERE, null, ex);
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
