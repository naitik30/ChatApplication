<%-- 
    Document   : usergroup
    Created on : Feb 28, 2016, 4:43:35 PM
    Author     : naitik
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Groups</title>
        <!-- BOOTSTRAP CORE STYLE CSS -->
        <link href="css/bootstrap.css" rel="stylesheet" type="text/css"/>
    </head>
    <body>
        <c:import url="header.jsp"/>
        <div class="container">
        <div class="row pad-top pad-bottom">
            <div class=" col-lg-6 col-md-6 col-sm-6">`
                <table class="table table-bordered">
                    <tr>
                        <th>GroupName</th>
                    </tr>
                    <c:forEach var="group" items="${requestScope.groupList}">
                    <tr><td>
                        <form action="/ChatApplication/showgroupmes" method="POST">
                            <input type="hidden" name="grouphiddenname" value="${group}"/>
                            <button class="btn btn-primary" id="${group}" type="submit" class="btn btn-warning btn-sm" data-toggle="modal" 
                                    >${group}</button>
                        </form>
                
                    </td></tr>               
                    </c:forEach> 
            </table>
            </div>
        </div>
        </div>
    </body>
</html>
