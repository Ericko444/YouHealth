<%-- 
    Document   : liste_rdv
    Created on : 25 juil. 2021, 10:53:30
    Author     : Ericko
--%>

<%@page import="health.DetailsRendezVous"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    ArrayList<DetailsRendezVous> mesRdv = (ArrayList<DetailsRendezVous>) request.getAttribute("listRdv");
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Liste des rendez-vous - Medecin</title>
        <jsp:include page="head_medecin.jsp" />
        <div class="text-center mb-5">
            <h1>Vos Rendez-vous</h1>
        </div>
        <div class="container">
            <div class="row">
                <div class="col-md-1"></div>
                <div class="col-md-10">
                    <table class="table">
                        <thead class="thead-dark">
                            <tr>
                                <th scope="col"> #      </th>
                                <th scope="col"> Date</th>
                                <th scope="col"> Nom</th>
                                <th scope="col"> Age</th>
                                <th scope="col"> Modification</th>
                                <th scope="col"> Annulation</th>
                                <th scope="col"> Fiche</th>
                            </tr>
                        </thead>
                        <tbody>
                            <% for(DetailsRendezVous rdv : mesRdv) {%>
                            <tr>
                                <td><%= rdv.getIdPatient()%></td>                   
                                <td><%= rdv.getDateRdv()%></td>
                                <td><%= rdv.getPatientName()%></td>
                                <td><%= rdv.getPatientAge()%></td>
                                <td><button type="button" class="btn btn-primary" data-toggle="modal" data-target="#exampleModal" data-whatever="<%=  rdv.getIdRdv()+ "|" + rdv.getIdPatient() + "|" +rdv.getDateRdv()%>">Modifier</button></td>
                                <td>
                                    <form action="RendezVousMedecin?action=annuler&rdv=<%= rdv.getIdRdv()%>" method="POST">
                                        <button type="submit" class="btn btn-danger">Annuler</button>
                                    </form
                                </td>
                                <td><a href="PatientServlet?action=fiche&pat=<%= rdv.getIdPatient()%>" class="btn btn-success">Voir fiche</a></td>
                            </tr>
                            <% } %>
                        </tbody>
                    </table>
                </div>
                <div class="col-md-1"></div>
            </div>
        </div>
        <div class="modal fade" id="exampleModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
            <div class="modal-dialog" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title" id="exampleModalLabel">New message</h5>
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>
                    <div class="modal-body">
                        <form method="POST" action="">
                            <p></p>
                            <div class="form-group">
                                <label for="date">Date du rendez-vous</label>
                                <input type="datetime-local" name="dateRdv" id="date">
                            </div>
                            <button type="button" class="btn btn-secondary" data-dismiss="modal">Annuler</button>
                            <button type="submit" class="btn btn-primary" >Valider</button>
                        </form>
                    </div>
                </div>
            </div>
        </div>
        <jsp:include page="footer.jsp" />
        <script type="text/javascript">
                $('#exampleModal').on('show.bs.modal', function (event) {
                var button = $(event.relatedTarget) 
                var recipient = button.data('whatever')
                const recip2 = recipient.split("|")
                var details = 'Changement du rendez-vous avec le patient '+recip2[1]+' le '+recip2[2]
                var link = 'RendezVousMedecin?action=modif&rdv='+recip2[0]
                var modal = $(this)
                modal.find('.modal-title').text('Modification du rendez-vous du ' + recip2[2])
                modal.find('.modal-body p').text(details)
                modal.find('.modal-body form').attr("action", link)
              })
        </script>
    </body>
</html>
