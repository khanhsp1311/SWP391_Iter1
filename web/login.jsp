<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta http-equiv="X-UA-Compatible" content="ie=edge">
        <title>Sign Up Form by Colorlib</title>

        <!-- Font Icon -->
        <link rel="stylesheet"
              href="fonts/material-icon/css/material-design-iconic-font.min.css">

        <!-- Main css -->
        <link rel="stylesheet" href="css/style.css">
    </head>
    <body>
        <input type="hidden" id ="status" value ="<%= request.getAttribute("status")%>">

        <div class="main">

            <!-- Sing in  Form -->
            <section class="sign-in">
                <div style="text-align: center"><img style="border-radius: 10px; height: 100px; width: 250px" src="data:image/png;base64,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"></div>
                <div class="container">
                    <div class="signin-content">
                        <div class="signin-image">
                            <figure>
                                <img src="images/signin-image.jpg" alt="sing up image">
                            </figure>
                            <a href="registration.jsp" class="signup-image-link">Create an
                                account</a>
                        </div>

                        <div class="signin-form">
                            <h2 class="form-title">Sign in</h2>
                            <form method="post" action="login" class="register-form"
                                  id="login-form">
                                <div class="form-group">
                                    <label for="username"><i
                                            class="zmdi zmdi-account material-icons-name"></i></label> <input
                                        type="text"  value="${cookie.email.value}" name="username" id="username"
                                        placeholder="Your Name" required="required" />
                                </div>
                                <div class="form-group">
                                    <label for="password"><i class="zmdi zmdi-lock"></i></label> <input
                                                type="password" value="${cookie.pass.value}"  name="password" id="password"
                                        placeholder="Password" required="required"/>
                                </div>
                                <div class="form-group">
                                    <input type="checkbox" name="remember-me" id="remember-me"
                                                     class="agree-term" value="1" /> <label for="remember-me"
                                           class="label-agree-term"><span><span></span></span>Remember
                                        me</label>
                                </div>
                                <div class="form-group">
                                    <a href ="forgotPassword.jsp"> Forgot Password</a>
                                </div>
                                <div class="form-group form-button">
                                    <input type="submit" name="signin" id="signin"
                                           class="form-submit" value="Log in" />
                                </div>
                            </form>
                            <div class="social-login">
                                <span class="social-label">Or login with</span>
                                <ul class="socials">
                                    <li><a href="#"><i
                                                class="display-flex-center zmdi zmdi-facebook"></i></a></li>
                                    <li><a href="#"><i
                                                class="display-flex-center zmdi zmdi-twitter"></i></a></li>
                                    <li><a href="#"><i
                                                class="display-flex-center zmdi zmdi-google"></i></a></li>
                                </ul>
                            </div>
                        </div>
                    </div>
                </div>
            </section>

        </div>

        <!-- JS -->
        <script src="vendor/jquery/jquery.min.js"></script>
        <script src="js/main.js"></script>
        <script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
        <link rel="stylesheet" href="alert/dist/sweetalert.css">

        <script type="text/javascript">
            var status = document.getElementById("status").value;
            if (status === "failed") {
                swal("Sorry", "Wrong username or password", "error");
            }
            if (status === "invalidEmail") {
                swal("Sorry", "Please Enter Username", "error");
            }
            if (status === "invalidPassword") {
                swal("Sorry", "Please Enter password", "error");
            }
        </script>
    </body>
    <!-- This templates was made by Colorlib (https://colorlib.com) -->
</html>