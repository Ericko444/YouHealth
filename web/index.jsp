<%-- 
    Document   : index
    Created on : 6 juil. 2021, 09:06:21
    Author     : Ericko
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Home</title>
        <jsp:include page="head.jsp" />
        <div class="jumbotron jumbotron-lg bg-primary jumbotron-fluid">
            <div class="container text-center text-white h-100">
                <h1 class="display-2">You <strong>Health</strong></h1>
                <h5 class="font-weight-light">&quot;Votre sant&eacute; avant tout&quot;</h5>
                <br>
                <a class="btn btn-success" href="login.jsp">Se connecter</a>
            </div>
        </div>
        <div class="row mr-4 ml-4">
            <div class="col-md-6">
                <div class="card shadow-sm border-0">
                    <img class="card-img-top" src="<%= request.getContextPath()%>/assets/img/48604.jpg" alt="Card image cap">
                    <div class="card-body">
                        <h5 class="card-title">Consulter</h5>
                        <p class="card-text text-muted">
                             Vous pouvez consulter directement chez vous ou venir chez nous
                        </p>
                        <button href="consulter.jsp" class="btn btn-primary btn-round" data-toggle="modal" data-target="#exampleModalCenter">Consulter</button>
                    </div>
                </div>
            </div>
            <div class="col-md-6">
                <div class="card shadow-sm border-0">
                    <img class="card-img-top" src="<%= request.getContextPath()%>/assets/img/7615567.jpg" alt="Card image cap">
                    <div class="card-body">
                        <h5 class="card-title">Information - Auto-m&eacute;dication</h5>
                        <p class="card-text text-muted">
                             Si vous n'avez pas le temps de consulter, vous pouvez identifier directement les m&eacute;dicaments &agrave; prendre en cas d'urgence
                        </p>
                        <button href="#" class="btn btn-primary btn-round">S'informer</button>
                    </div>
                </div>
            </div>
        </div>
        <!-- The modal itself -->
        <div class="modal fade" id="exampleModalCenter" tabindex="-1" role="dialog" aria-labelledby="exampleModalCenterTitle" aria-hidden="true">
            <div class="modal-dialog modal-dialog-centered modal-lg" role="document">
                <div class="modal-content">
                    <div class="modal-header border-0">
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">×</span>
                        </button>
                    </div>
                    <div class="modal-body text-center">
                        <h3>Choisissez votre type de consultation</h3>
                        <div class="row mr-4 ml-4">
                            <div class="col-md-6">
                                <div class="card shadow-sm border-0">
                                    <img class="card-img-top" src="<%= request.getContextPath()%>/assets/img/7195117.jpg" alt="Card image cap">
                                    <div class="card-body">
                                        <h5 class="card-title">En Ligne</h5>
                                        <p class="card-text text-muted">
                                            Consulter en ligne
                                        </p>
                                        <a href="consulter.jsp" class="btn btn-primary btn-round">Consulter</a>
                                    </div>
                                </div>
                            </div>
                            <div class="col-md-6">
                                <div class="card shadow-sm border-0">
                                    <img class="card-img-top" src="<%= request.getContextPath()%>/assets/img/4225920.jpg" alt="Card image cap">
                                    <div class="card-body">
                                        <h5 class="card-title">En personne</h5>
                                        <p class="card-text text-muted">
                                            Prendre rendez-vous &agrave; la clinique
                                        </p>
                                        <a href="rendezvous.jsp" class="btn btn-primary btn-round">Prendre rendez-vous</a>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <jsp:include page="footer.jsp" />
    </body>
</html>
