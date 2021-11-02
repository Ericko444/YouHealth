<%-- 
    Document   : rendezvous
    Created on : 22 juil. 2021, 18:46:48
    Author     : Ericko
--%>

<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    ArrayList<String> listSpec = (ArrayList<String>) request.getAttribute("listSpec");
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Rendez-vous</title>
        <jsp:include page="head_patient.jsp" />
        <div class="jumbotron jumbotron-lg bg-primary jumbotron-fluid">
            <div class="container text-center text-white h-100">
                <h3>Recherche des m&eacute;decins disponibles au moment voulu</h3>
            </div>
        </div>
        <div class="container">
            <div class="row">
                <div class="col-md-2">

                </div>
                <div class="col-md-8">
                    <form method="POST" action="<%= request.getContextPath()%>/RendezVousServlet?action=searchMedecin&page=1">
                        <div class="row form-group">
                            <div class="col">
                                <label for="specialite">Sp&eacute;cialit&eacute;</label>
                                <select class="custom-select" name="specialiste" id="specialite">
                                    <option value="">Tous</option>
                                    <% for(String x : listSpec) {%>
                                    <option value="<%= x%>"><%= x%></option>
                                    <% } %>
                                </select>
                            </div>
                            <div class="col">
                                <label for="date">Date du rendez-vous</label>
                                <input type="datetime-local" name="dateRdv" id="date">
                            </div>
                        </div>
                        <button type="submit" class="btn btn-primary btn-round">Rechercher</button>
                    </form>
                </div>
                <div class="col-md-2">

                </div>
            </div>
        </div>
        <jsp:include page="footer.jsp" />
    </body>
</html>
