<!DOCTYPE html>

<html >
    <head>
        <meta charset="UTF-8">
        <title>Chat Application</title>
        <link href='http://fonts.googleapis.com/css?family=Titillium+Web:400,300,600' rel='stylesheet' type='text/css'>
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                        <link rel="stylesheet" href="css/normalize.css">
        <link rel="stylesheet" href="css/style.css">
    </head>

    <body>
    <center>
        <c:if test="${requestScope.message != null}">
            <div class="container">
                <div class="alert alert-warning">
                    <h4> ${requestScope.message}</h4>
                </div>
            </div>
        </c:if>
        <c:if test="${requestScope.newusermessage != null}">
            <div class="container">
                <div class="alert alert-success">
                    <h4> ${requestScope.newusermessage}</h4>
                </div>
            </div>
        </c:if>
    </center>
        <div class="form">

            <ul class="tab-group">
                <li class="tab active"><a href="#signup">Sign Up</a></li>
                <li class="tab"><a href="#login">Log In</a></li>
            </ul>

            <div class="tab-content">
                <div id="signup">   
                    <h1>Sign Up </h1>
                    
                    <form action="/ChatApplication/login" method="post">
                        <input type="hidden" name="loginType" value="signup"/>          
                        <div class="top-row">
                            <div class="field-wrap">
                                <label>
                                    First Name<span class="req">*</span>
                                </label>
                                <input type="text" id="first_name"  name="first_name" required  autocomplete="off" />
                            </div>

                            <div class="field-wrap">
                                <label>
                                    Last Name<span class="req">*</span>
                                </label>
                                <input type="text" id="last_name" name="last_name" required autocomplete="off"/>
                            </div>
                        </div>

                        <div class="field-wrap">
                            <label>
                                Email Address<span class="req">*</span>
                            </label>
                            <input type="email" id="email" name="email" required autocomplete="off"/>
                        </div>

                        <div class="field-wrap">
                            <label>
                                Set A Password<span class="req">*</span>
                            </label>
                            <input type="password" id="password" name="password" required autocomplete="off"/>
                        </div>

                        <button type="submit" class="button button-block"/>Get Started</button>

                    </form>

                </div>

                <div id="login">   
                    <h1>Welcome Back!</h1>

                    <form action="/ChatApplication/login" method="post">
                        <input type="hidden" name="loginType" value="login"/>
                        <div class="field-wrap">
                            <label>
                                Email Address<span class="req">*</span>
                            </label>
                            <input type="email" id="email" name="email" required autocomplete="off"/>
                        </div>

                        <div class="field-wrap">
                            <label>
                                Password<span class="req">*</span>
                            </label>
                            <input type="password" id="password" name="password" required autocomplete="off"/>
                        </div>

                        <p class="forgot"><a href="#">Forgot Password?</a></p>

                        <button class="button button-block"/>Log In</button>

                    </form>

                </div>

            </div><!-- tab-content -->

        </div> <!-- /form -->
        <script src='http://cdnjs.cloudflare.com/ajax/libs/jquery/2.1.3/jquery.min.js'></script>

        <script src="js/index.js"></script>

    </body>
</html>