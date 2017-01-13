<%-- 
    Document   : header.jsp
    Created on : Mar 2, 2016, 1:11:54 PM
    Author     : naitik
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri= "http://java.sun.com/jsp/jstl/core" prefix="c" %>
<link href="css/bootstrap.css" rel="stylesheet">
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    </head>
    <body>
        <div class="container">
            <h3 class="text-center " style="color: #c0a16b;background-color:lightgoldenrodyellow" > Chat Application</h3>
            <br><form align="right" name="form1" method="post" action="/ChatApplication/logout">
                <label class="btn btn-default btn-sm glyphicon glyphicon-log-out ">
                    <input name="submit2"  class="btn btn-default btn-sm glyphicon glyphicon-log-out" type="submit" id="submit2" value="log out">
                </label>
            </form>
            
            
        </div>
    </body>
</html>
