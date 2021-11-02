<%-- 
    Document   : result_doctors
    Created on : 24 juil. 2021, 20:00:06
    Author     : Ericko
--%>

<%@page import="health.Medecin"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    ArrayList<Medecin> listDispo = (ArrayList<Medecin>) request.getAttribute("docDispo");
        String date = String.valueOf(request.getAttribute("date"));
        String spec = String.valueOf(request.getAttribute("specialite"));
        int isany = (int) request.getAttribute("isany");
        int isanyP = (int) request.getAttribute("nbPages");
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Medecins disponibles</title>
        <jsp:include page="head_patient.jsp" />
        <div class="row">
            <div class="col-md-1">
            </div>
            <div class="col-md-10">
                <h2>Liste des m&eacute;decins <%= spec%> disponibles le <%= date%></h2>
                <div class="row">
                    <% for (Medecin med : listDispo) {%>
                    <div class="col-md-4 mt-4">
                        <div class="card p-2 py-3 text-center">
                            <div class="img mb-2"> <img src="assets/img/doctor.png" width="70" class="rounded-circle"> </div>
                            <h5 class="mb-0"><%= med.getUsername()%></h5> <small><%= med.getSpecialisation()%></small>
                            <div class="mt-4 apointment"> <button type="button" class="btn btn-primary" data-toggle="modal" data-target="#exampleModal" data-whatever="@<%= med.getIdMedecin() + "|" + med.getUsername() + "|" + date%>">Prendre rendez-vous</button> </div>
                        </div>
                    </div>
                    <% } %>
                </div>
                <ul class="pagination">
                    <% for (int i = 1; i <= isanyP; i++) {%>
                    <li class="page-item"><a class="page-link" href="<%= request.getContextPath()%>/RendezVousServlet?action=searchMedecin&page=<%= i%>&dateRdv=<%= date%>&specialiste=<%= spec%>"><%= i%></a></li>
                        <% }%>
                </ul>
            </div>
            <div class="col-md-1">
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
                        <h5>D&eacute;tails :</h5>
                        <p></p>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-secondary" data-dismiss="modal">Annuler</button>
                        <a href="#" class="btn btn-primary">Confirmer</a>
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
            var detail = 'Le rendez-vous avec le docteur '+ recip2[1] + ' aura lieu le ' + recip2[2]
            var link = 'RendezVousServlet?action=reserver&med='+ recip2[0].split("@")[1] + '&date=' + recip2[2]
            var modal = $(this)
            modal.find('.modal-title').text('Vous voulez vraiment prendre rendez-vous avec le docteur ' + recip2[1] + '?')
            modal.find('.modal-body p').text(detail)
            modal.find('.modal-footer a').attr("href", link)
          })
        </script>
    </body>
</html>
