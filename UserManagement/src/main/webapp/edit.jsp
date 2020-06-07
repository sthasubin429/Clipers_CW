<%-- 
    Document   : edit
    Created on : Jun 3, 2020, 8:21:03 PM
    Author     : Subin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<title>User Profile</title>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<style><%@include file="css/userprofile.css"%></style>
<style><%@include file="css/login.css"%></style>
<link rel='stylesheet' href='https://fonts.googleapis.com/css?family=Roboto'>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<style>
html,body,h1,h2,h3,h4,h5,h6 {font-family: "Roboto", sans-serif}
</style>
<body class="w3-light-grey">
    
    <%
//allow access only if session exists
String user = null;
if(session.getAttribute("user") == null){
	response.sendRedirect("/UserManagement/signin");
        
}else user = (String) session.getAttribute("user");
String userName = null;
String sessionID = null;
Cookie[] cookies = request.getCookies();
if(cookies !=null){
for(Cookie cookie : cookies){
	if(cookie.getName().equals("user")) userName = cookie.getValue();
	if(cookie.getName().equals("JSESSIONID")) sessionID = cookie.getValue();
}
}
%>

<!-- Page Container -->
<div class="w3-content w3-margin-top" style="max-width:1400px;">

  <!-- The Grid -->
  <div class="w3-row-padding">
  
    <!-- Left Column -->
    <div class="w3-third">
    
      <div class="w3-white w3-text-grey w3-card-4">
        <div class="w3-display-container">
          <img src="https://raw.githubusercontent.com/sthasubin429/Ciphers_CW_img/master/user_default2.png" style="width:100%" alt="image">
          <div class="w3-display-bottomleft w3-container w3-text-black">
            
          </div>
        </div>
        <div class="w3-container">
          <h2>${currentUser.full_name}</h2>
          <hr>
          <p><i class="fa fa-envelope fa-fw w3-margin-right w3-large w3-text-teal"></i>${currentUser.user_email}</p>
          <hr>
          <p class="w3-large"><i class="fa fa-calendar fa-fw w3-margin-right w3-text-teal"></i>Joined on:<small> ${currentUser.created_date}</small></p>
        <hr>
        <p><a href="/UserManagement/logout" style="text-decoration: none;"><i class="fa fa-sign-out fa-fw w3-margin-right w3-large w3-text-teal"></i>Log Out</a></p>

        </div>
      </div><br>

    <!-- End Left Column -->
    </div>

    <!-- Right Column -->
    <div class="w3-twothird">
      <div class="w3-container w3-card w3-white w3-margin-bottom">
        <h2 class="w3-text-grey w3-padding-16"><i class="fa fa-user fa-fw w3-margin-right w3-xxlarge w3-text-teal"></i>Edit Profile</h2>
        <div class="w3-container">
            <div class="container">
                <form action="/UserManagement/editUser">
                 <input type="hidden" name="edit_id" value="${currentUser.user_id}">
                 <p ${value}>  ${errorMessage} </p>
                 
                <div class="row">
                  <div class="col-25">
                    <label for="">Full Name</label>
                  </div>
                  <div class="col-75">
                    <input type="text" id="" name="edit_name" placeholder="Fullname" value="${currentUser.full_name}" required>
                  </div>
                </div>
                <div class="row">
                  <div class="col-25">
                    <label for="">Username</label>
                  </div>
                  <div class="col-75">
                    <input type="text" id="" name="edit_username" placeholder="Username" value="${currentUser.username}" required>
                  </div>
                </div>
                  <div class="row">
                    <div class="col-25">
                      <label for="">E-mail</label>
                    </div>
                    <div class="col-75">
                      <input type="email" id="" name="edit_email" placeholder="Email" value="${currentUser.user_email}" required>
                    </div>
                  </div><br>
                <div class="row">
                  <input type="submit" value="Save Changes">
                </div>
                </form>
              </div>
        </div>
        <hr>
        <div style="padding-bottom: 40px;">
            <p><a href="/UserManagement/view?id=${currentUser.user_id}" style="text-decoration: none;float: left;"><i class="fa fa-arrow-left fa-fw w3-margin-right w3-large w3-text-teal"></i>Go back to Profile</a></p>
        </div>
      </div>
    </div>

  <!-- End Grid -->
  </div>
  
  <!-- End Page Container -->
</div>

<footer class="w3-container w3-teal w3-center w3-margin-top">
  <p>Find us on social media.</p>
  <i class="fa fa-facebook-official w3-hover-opacity"></i>
  <i class="fa fa-instagram w3-hover-opacity"></i>
  <i class="fa fa-snapchat w3-hover-opacity"></i>
  <i class="fa fa-pinterest-p w3-hover-opacity"></i>
  <i class="fa fa-twitter w3-hover-opacity"></i>
  <i class="fa fa-linkedin w3-hover-opacity"></i>
  <p>Copyright &copy 2020 CIPHERS</p>
</footer>

</body>
</html>
