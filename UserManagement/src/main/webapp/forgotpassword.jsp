<%-- 
    Document   : forgotpassword
    Created on : Jun 6, 2020, 7:42:31 PM
    Author     : Subin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en" dir="ltr">
  <head>
    <meta charset="utf-8">
    <title>Forgot Password</title>
    <style><%@include file="css/styles.css"%></style>
  <body>
<div class="overlay"></div>
<form class="box" action="/UserManagement/resetpassword" method="post">
  <a href="index.html"><img src="https://raw.githubusercontent.com/sthasubin429/Ciphers_CW_img/master/logo.png" alt="logo"></a>
  <p ${value}>${errorMessage}</p>
  <h1 style="text-transform: capitalize;">Forgot Your Password?</h1>
  <input type="email" name="reset_email" placeholder="Enter your e-mail" required>
  
  <div>
      <input type="submit" name="" value="Reset">
  </div>
  
  <p>Don't have an account? <a href="/UserManagement/signup">Sign Up</a></p>
  <p>Already have an account? <a href="/UserManagement/signin">Sign in</a></p>
</form>
      


  </body>
</html>

