<%-- 
    Document   : login
    Created on : May 18, 2020, 11:25:54 PM
    Author     : Subin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>


<!DOCTYPE html>
<html lang="en" dir="ltr">
  <head>
    <meta charset="utf-8">
    <title>Login</title>
    
 
    <style><%@include file="css/styles.css"%></style>
     
  </head>
  <body>
<div class="overlay"></div>
<form class="box" action="/UserManagement/login" method="post">

  <a href="index.html"><img src="https://raw.githubusercontent.com/sthasubin429/Ciphers_CW_img/master/logo.png" alt="logo"></a>

  <h1>Sign in</h1>
  <p ${value}>${errorMessage}</p>
  <input type="email" name="signin_email" placeholder="Email" required>
  <input type="password" id="psw" name="signin_password" placeholder="Password" required style="margin-bottom: 2px;">
  <div class="group">
    <label for="remember" class="remember"><input type="checkbox" id="remember"> Remember Me</label>
    <a href="/UserManagement/forgotpassword" class="forgot">Forgot Password?</a>
  </div>
  <div>
      <input type="submit" name="" value="Login">
  </div>
  
  <p>Don't have an account? <a href="/UserManagement/signup">Sign Up</a></p>
</form>
  </body>
</html>
