/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import health.Medecin;
import health.Patient;
import health.RendezVous;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import services.MedecinService;
import services.PatientService;
import services.RendezVousService;
import util.Util;

/**
 *
 * @author Ericko
 */
public class AdminServlet extends HttpServlet {
    HashMap<String, String> param;

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
             String spageid1 = request.getParameter("page");
            if(null != request.getParameter("table"))
            switch (request.getParameter("table")) {
                case "med":
                    if("list".equals(request.getParameter("action")))
                    {
                        if (request.getParameter("speci") != null) {
                            param = new HashMap<>();
                            out.print(request.getParameter("spec"));
                            param.put("specialisation", request.getParameter("spec"));
                        } else {
                            request.removeAttribute("listMed");
                            request.removeAttribute("isany");
                            request.removeAttribute("nbPages");
                        }
                        int isany = MedecinService.countItems(param);
                        int pageid1 = Integer.parseInt(spageid1);
                        int limite = 5;
                        int debut = (pageid1 - 1) * limite;
                        ArrayList<Medecin> list = MedecinService.listAllMedecin(limite, debut, param);
                        ArrayList<String> listType = MedecinService.listAllSpec();
                        
                        RequestDispatcher dispat = request.getRequestDispatcher("list_medecin.jsp");
                        request.setAttribute("listMed", list);
                        request.setAttribute("listSpecMed", listType);
                        request.setAttribute("isany", isany);
                        int nbPages;
                        nbPages = (int) Math.ceil(isany / limite);
                        request.setAttribute("nbPages", nbPages);
                        dispat.forward(request, response);
                    }
                    else if("add".equals(request.getParameter("action")))
                    {
                        String username = request.getParameter("username");
                        String password = request.getParameter("password");
                        String specialisation = request.getParameter("specialisation");
                        
                        Medecin med = new Medecin(username, password, specialisation);
                        med.insert();
                        out.print("<div class='alert alert-success'>Insertion reussie</div>"); 
                        out.print("<a href='index_admin.jsp'>Retour</div>");

                        
                    }   break;
                case "pat":
                    if("list".equals(request.getParameter("action")))
                    {
                        if (request.getParameter("age") != null) {
                            param = new HashMap<String, String>();
                            out.print(request.getParameter("age"));
                            param.put("age", request.getParameter("age"));
                        } else {
                            request.removeAttribute("listPat");
                            request.removeAttribute("isany");
                            request.removeAttribute("nbPages");
                        }
                        int isany = PatientService.countItems(param);
                        int pageid1 = Integer.parseInt(spageid1);
                        int limite = 5;
                        int debut = (pageid1 - 1) * limite;
                        ArrayList<Patient> list = PatientService.listAllPatient(limite, debut, param);
                        
                        RequestDispatcher dispat = request.getRequestDispatcher("list_patient.jsp");
                        request.setAttribute("listPat", list);
                        request.setAttribute("isany", isany);
                        int nbPages;
                        nbPages = (int) Math.ceil(isany / limite);
                        request.setAttribute("nbPages", nbPages);
                        dispat.forward(request, response);
                    }
                    else if("add".equals(request.getParameter("action")))
                    {
                        String username = request.getParameter("username");
                        String password = request.getParameter("password");
                        String dtn = request.getParameter("dtn");
                        Patient p = new Patient();
                        Date d = Util.convertStringToDate(dtn);
                        p = new Patient(d, username, password);
                        p.insert();
                         out.print("<div class='alert alert-success'>Insertion reussie</div>"); 
                        out.print("<a href='index_admin.jsp'>Retour</div>");
                    }   break;
                case "rdv":
                    if (request.getParameter("date") != null) {
                        param = new HashMap<String, String>();
                        out.print(request.getParameter("date"));
                        param.put("date", request.getParameter("date"));
                    } else {
                        request.removeAttribute("listRdv");
                        request.removeAttribute("isany");
                        request.removeAttribute("nbPages");
                    }   int isany = RendezVousService.countItems(param);
                    int pageid1 = Integer.parseInt(spageid1);
                    int limite = 5;
                    int debut = (pageid1 - 1) * limite;
                    ArrayList<RendezVous> list = RendezVousService.listAllRendezVous(limite, debut, param);
                    RequestDispatcher dispat = request.getRequestDispatcher("list_rendezvous.jsp");
                    request.setAttribute("listRdv", list);
                    request.setAttribute("isany", isany);
                    int nbPages;
                    nbPages = (int) Math.ceil(isany / limite);
                    request.setAttribute("nbPages", nbPages);
                    dispat.forward(request, response);
                    break;
                default:
                    break;
            }
            out.close();
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
            Logger.getLogger(AdminServlet.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(AdminServlet.class.getName()).log(Level.SEVERE, null, ex);
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
