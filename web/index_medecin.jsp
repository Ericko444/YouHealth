<%-- 
    Document   : index_medecin
    Created on : 26 juil. 2021, 21:08:25
    Author     : Ericko
--%>

<%@page import="health.Medecin"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
        session = request.getSession(false);
        Medecin m = (Medecin) session.getAttribute("user-info");
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Home - Medecin</title>
        <jsp:include page="head_medecin.jsp" />
        <div class="jumbotron jumbotron-lg bg-primary jumbotron-fluid">
            <div class="container text-center text-white h-100">
                <h1 class="display-2">Hello, Medecin <%= m.getUsername()%></h1>
            </div>
        </div>
        <jsp:include page="footer.jsp" />
    </body>
</html>
