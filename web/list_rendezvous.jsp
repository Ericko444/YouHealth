<%-- 
    Document   : list_medecin
    Created on : 29 juil. 2021, 20:30:44
    Author     : Ericko
--%>

<%@page import="health.RendezVous"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    ArrayList<RendezVous> listMed = (ArrayList<RendezVous>) request.getAttribute("listRdv");
        int isany = (int) request.getAttribute("isany");
        int isanyP = (int) request.getAttribute("nbPages");
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Liste - Medecin</title>
       <jsp:include page="head_admin.jsp" />
        <h1 class="text-center">Liste des rendez-vous</h1>
        <div class="row">
            <div class="col-md-3"></div>
            <div class="col-md-6">
                <table class="table table-striped table-bordered table-sm">
                    <tr>
                        <th>Id patient</th>
                        <th>Id medecin</th>
                        <th>Date rendez-vous</th>
                    </tr>

                    <% for(RendezVous med : listMed) {%>
                    <tr>
                        <td><%= med.getIdPatient()%></td>
                        <td><%= med.getIdMedecin()%></td>
                        <td><%= med.getDateRdv()%></td>
                    </tr>
                    <% } %>
                </table>
                    <ul class="pagination">
                        <% for (int i = 1; i <= isanyP; i++) {%>
                            <li class="page-item"><a class="page-link" href="AdminServlet?table=rdv&action=list&page=<%= i%>"><%= i%></a></li>
                        <% }%>
                    </ul>
                    <a href='index_admin.jsp'>Retour</a> 
            </div>
            <div class="col-md-3"></div>
        </div>
   
    
    <br>
    <br>
    </body>
</html>
