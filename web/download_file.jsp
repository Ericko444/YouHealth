<%-- 
    Document   : download_file
    Created on : 26 juil. 2021, 18:08:13
    Author     : Ericko
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Archives</title>
        <jsp:include page="head_patient.jsp" />
        <a href="servlet/DownloadServlet?file=monFichier.pdf">monFichier.pdf</a>
        <a href="servlet/DownloadServlet?file=licenses.txt">licenses.txt</a>
        <jsp:include page="footer.jsp" />
    </body>
</html>
