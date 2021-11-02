<%-- 
    Document   : upload_analyse
    Created on : 25 juil. 2021, 20:20:12
    Author     : Ericko
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>  
    <body>  
        <form action="UploadServlet" method="post" enctype="multipart/form-data">  
            Select File:<input type="file" name="file"/><br/>  
            <input type="submit" value="upload"/>  
        </form>  
    </body>  
</html>  