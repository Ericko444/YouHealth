<%-- 
    Document   : patient_content_info
    Created on : 27 juil. 2021, 10:11:06
    Author     : Ericko
--%>

<%@page import="health.DetailsRendezVous"%>
<%@page import="java.util.ArrayList"%>
<%@page import="health.Patient"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    Patient p = (Patient)session.getAttribute("user-info");
    ArrayList<DetailsRendezVous> listRdv = (ArrayList<DetailsRendezVous>)request.getAttribute("listRdv");
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Info-Patient</title>
        <jsp:include page="head_patient.jsp" />
        <div class="row">
            <div class="col-md-4"></div>
            <div class="col-md-4">
                <h1 class="text-center">Info personnels</h1>
                <div class="img mb-2"> <img src="assets/img/patient.png" width="100" class="rounded-circle center-block"> </div>
                <h3>Nom d'utilisateur : <%= p.getUsername()%></h3>
                <h3>Date de naissance : <%= p.getDateDeNaissance()%></h3>
                <hr>
                <h3>Vos rendez-vous : </h3>
                <br>
                <% if(listRdv.isEmpty()) { %>
                    <div class="alert alert-info" role="alert">Votre calendrier de rendez-vous est vide</div>
                <% } else {%>
                    <table class="table">
                        <thead class="thead-dark">
                            <tr>
                                <th scope="col"> Medecin en charge</th>
                                <th scope="col">Specialisation</th>
                                <th scope="col">Date</th>
                            </tr>
                        </thead>
                        <tbody>
                               <% for(DetailsRendezVous r : listRdv ) {%>
                               <tr>
                                   <td><%= r.getMedecinName()%></td>                                  
                                   <td><%= r.getSpecialisation()%></td>
                                   <td><%= r.getDateRdv()%></td>
                               </tr>
                               <% } %>
                        </tbody>
                    </table>
                <% } %>
            </div>
            <div class="col-md-4"></div>
        </div>
        <jsp:include page="footer.jsp" />
    </body>
</html>
