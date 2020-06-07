<%-- 
    Document   : signin
    Created on : May 18, 2020, 5:34:36 PM
    Author     : Subin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html lang="en" dir="ltr">
  <head>
    <meta charset="utf-8">
    <title>Sign Up </title>
    <style><%@include file="css/styles.css"%></style>
  </head>
  <body>
    <div class="overlay"></div>
    
<form class="box" action="/UserManagement/register" method="post">
    
  <a href="index.html"><img src="https://raw.githubusercontent.com/sthasubin429/Ciphers_CW_img/master/logo.png" alt="logo"></a>
  <h1>Sign Up</h1>
  
  <p ${value}>  ${errorMessage} </p>
  <input type="text" name="fullName" placeholder="Full Name" required>
  <input type="text" name="username" placeholder="Username" required>
  <input type="email" name="email" placeholder="Email" required>
  <input type="password" name="password" placeholder="Password"required>
  <input type="password" name="conformPassword" placeholder="Confirm Password" required>
  <input type="submit" name="button" value="Sign Up">
  <p>Already have an account? <a href="/UserManagement/signin">Sign In</a></p>
</form>


  </body>
</html>

          