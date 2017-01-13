<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<html>
    <head>
        <meta charset="utf-8" />
        <title>Chat Application</title>
        <link href="css/bootstrap.css" rel="stylesheet" type="text/css"/>
        <link href="css/font-awesome.css" rel="stylesheet" type="text/css"/>
        <link href="css/style.css" rel="stylesheet" type="text/css"/>
        <script src="js/jquery-1.11.1.js" type="text/javascript"></script>
        <link rel="stylesheet" href="//code.jquery.com/ui/1.11.4/themes/smoothness/jquery-ui.css">
        <script src="js/jquery-ui.js" type="text/javascript"></script>
        <script src="js/jquery.textcomplete.js" type="text/javascript"></script>
        <script src="js/bootstrap.js" type="text/javascript"></script>
    </head>

    <body>
        <div class="container">
            <div class="row pad-top pad-bottom">
                <div class=" col-lg-6 col-md-6 col-sm-6">
                    <div class="chat-box-div">
                        <div class="chat-box-head">
                            GROUP CHAT HISTORY
                        </div>
                        <div class="panel-body chat-box-main" id="chatboxReload">
                            <c:forEach var="message" items="${messages}">
                                <div class="chat-box-left">
                                    ${message[0]}
                                </div>
                                <div class="chat-box-name-left">
                                    -  ${message[1]}
                                </div>
                                <hr class="hr-clas" />
                            </c:forEach>
                        </div>
                        <div id="autoload"></div>
                        <div class="chat-box-footer">
                            <div class="input-group">
                                <input type="text" id="message" class="form-control" name="message" required   placeholder="Enter Text Here..." />
                                <span class="input-group-btn">
                                    <button class="btn btn-info" type="button" name="button_send" id="button_send">SEND</button>
                                </span>
                                </form>
                            </div>
                        </div>

                    </div>

                </div>

                <div class="col-lg-3 col-md-3 col-sm-3">
                </div>
                <div class="col-lg-3 col-md-3 col-sm-3">
                    <div class="chat-box-new-div">
                        <div class="chat-box-new-head">
                            Group Member (${fn:length(groupList)})
                        </div>
                        <div class="panel-body chat-box-new">


                            <c:forEach var="user" items="${groupList}">

                                <img src="img/user.gif" alt="bootstrap Chat box user image" class="img-circle" />
                                ${user}

                                <hr class="hr-clas-low" />
                            </c:forEach>
                        </div>

                    </div>

                </div>
            </div>
        </div>

        <!-- USING SCRIPTS BELOW TO REDUCE THE LOAD TIME -->
        <!-- CORE JQUERY SCRIPTS FILE -->
        <script src="js/jquery-1.11.1.js" type="text/javascript"></script>
        <script type="text/javascript">
            var messages = [];
            $(document).ready(function () {
                $('#button_send').click(function () {
                    var rel = $('#message').val();
                    if ($.trim(rel) != '')
                    {
                        $.ajax({
                            url: "/ChatApplication/message",
                            method: "POST",
                            data: {message: rel},
                            dataType: "text",
                            success: function (data)
                            {
                                $('#message').val('');
                                refresh();

                            }
                        });
                    }

                });
                refresh();
            });
            var counter = 0;
            window.setInterval("refreshDiv()", 1000);
            function refreshDiv() {
                $.ajax({
                    url: "/ChatApplication/messageread",
                    method: "POST",
                    data: {message: null},
                    dataType: "text",
                    success: function (data)
                    {
                        var messagesJson = JSON.parse(data);
                        var t = "";
                        for (var i = 0; i < messagesJson["jsonArray"].length; i++)
                        {
                            t = t + " <div class=\"chat-box-left\">";
                            t = t + messagesJson["jsonArray"][i]["mesg"];
                            t = t + "<\/div>";
                            t = t + " <div class=\"chat-box-name-left\">- ";
                            t = t + messagesJson["jsonArray"][i]["sender"];
                            t = t + "<\/div><hr class=\"hr-clas\" />";
                        }

                        $('#chatboxReload').html(t);
                        );

//                                reloadjsp(messages);
                    }
                });


            }

            setInterval(function () {
                var randomnumber = Math.floor(Math.random() * 100);
                $('#show').text(
                        'I am getting refreshed every 3 seconds..! Random Number ==> '
                        + randomnumber);
            }, 3000);
            function refresh()
            {
//                setTimeout(function () {
//                    $('#autoload').load('message.jsp');
//                    refresh();
//                }, 200);
            }
        </script>
        <!-- CORE BOOTSTRAP SCRIPTS  FILE -->
        <link href="css/bootstrap.css" rel="stylesheet" type="text/css"/>
    </body>

</html>
