<%-- 
    Document   : fiche_patient
    Created on : 30 juil. 2021, 08:54:23
    Author     : Ericko
--%>

<%@page import="health.DetailsRendezVous"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    ArrayList<DetailsRendezVous> listRdv = (ArrayList<DetailsRendezVous>) request.getAttribute("listRdv");
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Fiche Patient</title>
        <jsp:include page="head_medecin.jsp" />
        <div class="row">
        <div class="col-md-3"></div>
        <div class="col-md-6">
            <h1 class="text-center">Fiche patient</h1>
            <div class="fiche">
                <div class="img mb-2"> <img src="assets/img/patient.png" width="100" class="rounded-circle center-block"> </div>
                <p><strong>Nom : </strong><%= listRdv.get(0).getPatientName()%></p>
                <p><strong>Age : </strong><%= listRdv.get(0).getPatientAge()%></p>
            </div>
            <h2 class="text-center">Liste des rendez-vous du patient</h2>
            <table class="table">
                <thead class="thead-dark">
                    <tr>
                        <th scope="col"> #      </th>
                        <th scope="col"> Date</th>
                        <th scope="col"> Medecin en charge</th>
                        <th scope="col">Specialisation</th>
                    </tr>
                </thead>
                <tbody>
                    <% for (DetailsRendezVous rdv : listRdv) {%>
                    <tr>
                        <td><%= rdv.getIdPatient()%></td>                   
                        <td><%= rdv.getDateRdv()%></td>
                        <td><%= rdv.getMedecinName()%></td>
                        <td><%= rdv.getSpecialisation()%></td>
                    </tr>
                    <% }%>
                </tbody>
            </table>
         </div>
         <div class="col-md-3"></div>
         </div>
        <jsp:include page="footer.jsp" />
    </body>
</html>
