
<%-- 
    Document   : admin
    Created on : Feb 27, 2016, 12:45:05 PM
    Author     : naitik
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri= "http://java.sun.com/jsp/jstl/core" prefix="c" %>
<link href="css/bootstrap.css" rel="stylesheet">
<!--<link href="bootstrap/css/main.css" rel="stylesheet" type="text/css"/>-->
<script src="js/jquery-1.11.1.js" type="text/javascript"></script>
<link rel="stylesheet" href="//code.jquery.com/ui/1.11.4/themes/smoothness/jquery-ui.css">
<script src="js/jquery-ui.js" type="text/javascript"></script>
<script src="js/jquery.textcomplete.js" type="text/javascript"></script>
<!--<link href="css/style.css" rel="stylesheet" type="text/css"/>-->
<script src="js/bootstrap.js" type="text/javascript"></script><!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Admin !!!!</title>
    </head>
    <div class="container">

        <c:import url="header.jsp"/>
        <h4 class="text-center">WelCome Admin !!!!</h4>
    </div>
    </br>


         
        <form action="/ChatApplication/newgroup" method="POST">
            <input type="hidden" name="grouphiddenname" value="new"/>
            <div class="row">
                <div class="col-md-6"></div>
                <div class="col-md-6">
            <button class="btn btn-primary" id="createGroup" type="submit" class="btn btn-warning btn-sm" data-toggle="modal" 
                    >New Group</button>
            </div>
</div>
         </form>
    <div class="container">
        <input type="hidden" id="usernameList" value="${usernameList}"/>
        <table class="table table-bordered">
            <tr>
                <th>GroupName</th>
                <th>UserList</th>
                <th>Edit</th>
                <th> Delete</th>
            </tr>
            <c:forEach var="group" items="${requestScope.groupList}" >
                <tr>
                    <td>${group.groupName}</td>
                    <td class="userlist">
                        <c:forEach var="username" items="${group.userList}" varStatus="loop">
                            ${username}<c:if test="${!loop.last}">,</c:if>

                        </c:forEach>
                    </td>
                    <td>
                        <form action="/ChatApplication/groupedit" method="POST">
                            <input type="hidden" name="grouphiddenname" value="${group.groupName}"/>
                            <button class="edit-button" id="${group.groupName}" type="submit" class="btn btn-warning btn-sm" data-toggle="modal" 
                                    >Edit</button>
                        </form>
                    </td>
                    <td>
                        <button id="${group.groupName}" type="button" class="delete" >Delete</button>
                    </td>
                </tr>
            </c:forEach> 
        </table>
    </div>

    <script type="text/JavaScript">
    $(function() {
        $(".delete").click(function(){
        var element = $(this);
        var group = element.attr("id");
        var info = 'group_name=' + group;
        if(confirm("Are you sure you want to delete this?"))
        {
            $.ajax({
            type: "POST",
            url: "/ChatApplication/deletegroup",
            data: info,
            success: function(){
                window.location.reload();
            }
            });
        }
        return false;
        });
    });
    </script>



</html>
