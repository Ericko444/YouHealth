<%-- 
    Document   : index_admin
    Created on : 29 juil. 2021, 19:02:06
    Author     : Ericko
--%>

<%@page import="health.Admin"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
        session = request.getSession(false);
        Admin m = (Admin) session.getAttribute("user-info");
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Home - Admin</title>
        <jsp:include page="head_admin.jsp" />
        <div class="jumbotron jumbotron-lg bg-primary jumbotron-fluid">
            <div class="container text-center text-white h-100">
                <h1 class="display-2">Hello, Admin <%= m.getUsername()%></h1>
            </div>
        </div>
    <h1 class="text-center">Data</h1>
        <div class="container">
            <div class="row">
                <div class="col-md-4">
                        <div class="card p-2 py-3 text-center">
                            <div class="img mb-2"> <img src="assets/img/doctor.png" width="70" class="rounded-circle"> </div>
                            <div class="mt-4 apointment"> <a class="btn btn-primary text-uppercase" href="AdminServlet?table=med&action=list&page=1">M&eacute;decins</a> </div>
                        </div>
                </div>
                <div class="col-md-4">
                     <div class="card p-2 py-3 text-center">
                        <div class="img mb-2"> <img src="assets/img/patient.png" width="70" class="rounded-circle"> </div>
                        <div class="mt-4 apointment"> <a class="btn btn-primary text-uppercase" href="AdminServlet?table=pat&action=list&page=1">Patient</a> </div>
                    </div>
                </div>
                <div class="col-md-4">
                    <div class="card p-2 py-3 text-center">
                        <div class="img mb-2"> <img src="assets/img/medical-appointment.png" width="70" class="rounded-circle"> </div>
                        <div class="mt-4 apointment"> <a class="btn btn-primary text-uppercase" href="AdminServlet?table=rdv&action=list&page=1">Rendez-vous</a> </div>
                    </div>
                </div>
            </div>
        </div>
        
        <jsp:include page="footer.jsp" />
    </body>
</html>
