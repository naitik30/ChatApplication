<%-- 
    Document   : newgroup
    Created on : Feb 28, 2016, 3:28:15 PM
    Author     : naitik
--%>

<%-- 
    Document   : editgroup
    Created on : Feb 28, 2016, 1:18:30 AM
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
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Create Group</title>
    </head>
    <body>
        <c:import url="header.jsp"/>
        <h2 class="text-center"> Create Group !!</h2>
        </br>
        </br>
        </br>

        <div  class="container ">
            <input type="hidden" id="usernameList" value="${usernameList}"/>
        <input type="hidden" id="grouphiddenname" value="created"/>

            <form class="form-horizontal  "  action="/ChatApplication/newgroup"  method="POST" >
                <input type="hidden" name="grouphiddenname" value="editGroup"/>
                <div class="form-group ">
                    <label class="control-label col-sm-2" for="groupname">GroupName</label>
                    <div class="col-sm-6">
                        <input type="text" class="form-control" name="groupname" placeholder="Please Enter GroupName">
                    </div>
                </div>
                <div class="form-group ">
                    <label class="control-label col-sm-2" for="lastname">GroupMembers:</label>
                    <div class="col-sm-6">
                        <textarea type="text" class="form-control" name="userlist" id="groupmember" ></textarea>
                    </div>
                </div>
                <div class="form-group">        
                    <div class="col-sm-offset-2 col-sm-10">
                        <button type="submit" class="btn btn-default">Create</button>
                    </div>
                </div>
            </form>
        </div>

    </body>
    <script type ="text/javascript">
    $(document).ready(function () {
        var availableTags =document.getElementById('usernameList').value.replace("\[","").replace("\]","").split(",")  ; 
        
        function split( val ) {
      return val.split( /,\s*/ );
    }
    function extractLast( term ) {
      return split( term ).pop();
    }
               $( "#groupmember" )
      // don't navigate away from the field on tab when selecting an item
      .bind( "keydown", function( event ) {
        if ( event.keyCode === $.ui.keyCode.TAB &&
            $( this ).autocomplete( "instance" ).menu.active ) {
          event.preventDefault();
        }
      })
      .autocomplete({
        minLength: 0,
        source: function( request, response ) {
          // delegate back to autocomplete, but extract the last term
          response( $.ui.autocomplete.filter(
            availableTags, extractLast( request.term ) ) );
        },
        focus: function() {
          // prevent value inserted on focus
          return false;
        },
        select: function( event, ui ) {
          var terms = split( this.value );
          // remove the current input
          terms.pop();
          // add the selected item
          terms.push( ui.item.value );
          // add placeholder to get the comma-and-space at the end
          terms.push( "" );
          this.value = terms.join( ", " );
          return false;
        }
      });
 
    }
    );
    </script>


</html>

