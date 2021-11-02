<%-- 
    Document   : list_medecin
    Created on : 29 juil. 2021, 20:30:44
    Author     : Ericko
--%>

<%@page import="health.Medecin"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    ArrayList<Medecin> listMed = (ArrayList<Medecin>) request.getAttribute("listMed");
        ArrayList<String> specs = (ArrayList<String>) request.getAttribute("listSpecMed");
        int isany = (int) request.getAttribute("isany");
        int isanyP = (int) request.getAttribute("nbPages");
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Liste - Medecin</title>
       <jsp:include page="head_admin.jsp" />
        <h1 class="text-center">Liste des m&eacute;decins</h1>
        <div class="row">
            <div class="col-md-3"></div>
            <div class="col-md-6">
                <table class="table table-striped table-bordered table-sm">
                    <tr>
                        <th>#</th>
                        <th>Identifiant</th>
                        <th>Specialisation</th>
                    </tr>

                    <% for(Medecin med : listMed) {%>
                    <tr>
                        <td><%= med.getIdMedecin()%></td>
                        <td><%= med.getUsername()%></td>
                        <td><%= med.getSpecialisation()%></td>
                    </tr>
                    <% } %>
                </table>
                    <ul class="pagination">
                        <% for (int i = 1; i <= isanyP; i++) {%>
                            <li class="page-item"><a class="page-link" href="AdminServlet?table=med&action=list&page=<%= i%>"><%= i%></a></li>
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
