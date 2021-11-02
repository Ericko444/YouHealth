<%-- 
    Document   : login
    Created on : 6 juil. 2021, 09:45:16
    Author     : Ericko
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    String message = null;
    if(request.getAttribute("message") != null)
    {
        message = (String)request.getAttribute("message");
    }
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login</title>
        <jsp:include page="head.jsp" />
        <br>
        <% if(message != null) { %>
            <div class="row">
                <div class="col-md-3"></div>           
                <div class="col-md-6 text-center">
                    <div class="alert alert-danger shadow-lg" role="alert"><%= message%></div>
                </div>
                <div class="col-md-3"></div>
            </div>
        <% } %>
        <br>
        <div class="form row">
            <div class="col-md-5"></div>
            <div class="col-md-2">
                <form action="LoginServlet" method="POST">
                    <h1>Login</h1>
                    <br>
                    <br>
                    <div class="form-group">
                        <input type="text" class="form-control" id="username" name="username" placeholder="Entrez votre nom d'utilisateur" required=""> 
                    </div>
                    <div class="form-group">
                        <input type="password" class="form-control" id="password" name="password" placeholder="Mot de passe" required="">
                    </div>
                    <div class="form-check">
                        <label class="form-check-label">
                            <input type="radio" class="form-check-input" name="type" value="patient">Patient
                        </label>
                    </div>
                    <div class="form-check">
                        <label class="form-check-label">
                            <input type="radio" class="form-check-input" name="type" value="medecin">M&eacute;decin
                        </label>
                    </div>
                    <div class="form-check disabled">
                        <label class="form-check-label">
                            <input type="radio" class="form-check-input" name="type" value="admin">Admin
                        </label>
                    </div>
                    <small id="emailHelp" class="form-text text-muted">Veuillez bien v&eacute;rifier vos informations avant de valider</small>
                    <div class="col">
                        <button type="submit" class="btn btn-success btn-round">Se connecter</button>
                    </div>
                </form>
            </div>
            <div class="col-md-5"></div>
        </div>
        <br>
        <br>
        <jsp:include page="footer.jsp" />
    </body>
</html>
