<%@taglib  uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<html>
    <head>
    	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
        <link rel="stylesheet" href="./css/common.css">
        <link rel="stylesheet" href="./css/login.css">
        <script src="https://kit.fontawesome.com/4b0b15a158.js" crossorigin="anonymous"></script>
        <title>Login</title>
    </head>
    <body>
        <div class="login-form">
        	<button class="btn" onclick="window.location.replace('./homepage')"><i class="fas fa-home"></i></button>
        	<h1>Sign In</h1>
        	<p style="margin-bottom: 40px">and let's get you to the D.D.D!</p>
            <form action="login" method="post">
                <div class="text-field">
                    <label for="username">Username</label>
                    <input autocomplete="off" type="text" name="username" placeholder="Enter your username" required="required" autofocus="autofocus" maxlength="24"/>
                </div>
                <div class="text-field">
                    <label for="password">Password</label>
                    <input autocomplete="off" type="password" name="password" placeholder="Enter your password"  required="required" maxlength="24"/>
                </div>
                <p class="text-danger">${mess}</p>
                <button type="submit" class="button-login">Sign in <i class="fas fa-sign-in-alt"></i></button>
            </form>
        </div>
    </body>
</html>