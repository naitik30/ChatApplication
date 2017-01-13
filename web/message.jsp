<%-- 
    Document   : message
    Created on : Feb 23, 2016, 1:03:30 PM
    Author     : naitik
--%>

<div class="panel-body chat-box-main">
    <c:forEach var="message" items="${messages}">
        <div class="chat-box-left">
            ${message[0]}
        </div>
        <div class="chat-box-name-left">
            <img src="img/user.png" alt="bootstrap Chat box user image" class="img-circle" />
            -  ${message[1]}
        </div>
        <hr class="hr-clas" />
    </c:forEach>
</div>
