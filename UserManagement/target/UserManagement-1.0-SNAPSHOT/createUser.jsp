<%-- 
    Document   : createUser
    Created on : Jun 4, 2020, 8:02:44 PM
    Author     : Subin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<title>Users</title>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">


<style><%@include file="css/dashboard.css"%></style>
<style><%@include file="css/login.css"%></style>
<link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Raleway">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<style>
    html,body,h1,h2,h3,h4,h5 {font-family: "Raleway", sans-serif}
</style>
<body class="w3-light-grey">
<!-- Top container -->
<div class="w3-bar w3-top w3-black w3-large" style="z-index:3">
  <button class="w3-bar-item w3-button w3-hide-large w3-hover-none w3-hover-text-light-grey" onclick="w3_open();"><i class="fa fa-bars"></i>  Menu</button>
  <span class="w3-bar-item w3-right">User Management</span>
</div>

<!-- Sidebar/menu -->
<nav class="w3-sidebar w3-collapse w3-white w3-animate-left" style="z-index:4;width:300px;top: opx;" id="mySidebar"><br>
  <div class="w3-container w3-row">
    <div class="w3-col s4">
      <img src="https://raw.githubusercontent.com/sthasubin429/Ciphers_CW_img/master/avatar2.png" class="w3-circle w3-margin-right" style="width:46px">
    </div>
    <div class="w3-col s4 w3-bar">
      <span>Welcome <strong> ${currentUser} </strong></span><br>
    </div>
  </div>
  <hr>

  <div class="w3-bar-block">
    <a href="#" class="w3-bar-item w3-button w3-padding-16 w3-hide-large w3-dark-grey w3-hover-black" onclick="w3_close()" title="close menu"><i class="fa fa-remove fa-fw"></i>  Close Menu</a>
    <a href="/UserManagement/dashboard" class="w3-bar-item w3-button w3-padding"><i class="fa fa-tachometer fa-fw"></i>  Overview</a>
    <a href="/UserManagement/users" class="w3-bar-item w3-button w3-padding"><i class="fa fa-users"></i>  Users</a>
    <a href="/UserManagement/activitylog" class="w3-bar-item w3-button w3-padding"><i class="fa fa-th"></i>  Activity Log</a>
    <a href="/UserManagement/dashboard" class="w3-bar-item w3-button w3-padding"><i class="fa fa-cog fa-fw"></i>  Settings</a><br><br>
    <a href="/UserManagement/logout" class="w3-bar-item w3-button w3-padding"><i class="fa fa-sign-out"></i>  Log Out</a>
  </div>
</nav>


<!-- Overlay effect when opening sidebar on small screens -->
<div class="w3-overlay w3-hide-large w3-animate-opacity" onclick="w3_close()" style="cursor:pointer" title="close side menu" id="myOverlay"></div>

<!-- !PAGE CONTENT! -->
<div class="w3-main" style="margin-left:300px;margin-top:0px;">
    <header class="w3-container" style="padding-top:48px">  
        <h2><b><i class="fa fa-users"></i> Add Users</b></h2>
    </header>
    <div class="w3-card">
        <div class="container">
            <form action="/UserManagement/adminCreateUser">
            <p ${value}>${errorMessage}</p>
            <div class="row">
              <div class="col-25">
                <label for="">Full Name</label>
              </div>
              <div class="col-75">
                <input type="text" id="" name="admin_fullname" placeholder="Full name"fc>
              </div>
            </div>
            <div class="row">
                <div class="col-25">
                  <label for="">E-mail</label>
                </div>
                <div class="col-75">
                  <input type="email" id="" name="admin_email" placeholder="Email">
                </div>
              </div>
            <div class="row">
              <div class="col-25">
                <label for="">Username</label>
              </div>
              <div class="col-75">
                <input type="text" id="" name="admin_username" placeholder="Username..">
              </div>
            </div>
            <div class="row">
                <div class="col-25">
                  <label for="">Password</label>
                </div>
                <div class="col-75">
                  <input type="password" id="" name="admin_password" placeholder="Password">
                </div>
              </div>
            <div class="row">
                <div class="col-25">
                  <label for="">Confirm Password</label>
                </div>
                <div class="col-75">
                  <input type="password" id="" name="admin_confirm_password" placeholder="Password">
                </div>
              </div>

            <div class="row">
              <div class="col-25">
                <label for="role">Role</label>
              </div>
              <div class="col-75">
                <select id="" name="admin_role">
                  <option value="client">Client</option>
                  <option value="admin">Admin</option>
                </select>
              </div>
            </div><br>
            <div class="row">
              <input type="submit" value="Submit">
            </div>
            </form>
          </div>
      </div>
      <hr>
    <!-- Footer -->
    <footer class="w3-container w3-padding-16 w3-light-grey">
        <p>Copyright &copy 2020 CIPHERS</p>
    </footer>

    <!-- End page content -->
</div>

<script>
// Get the Sidebar
var mySidebar = document.getElementById("mySidebar");

// Get the DIV with overlay effect
var overlayBg = document.getElementById("myOverlay");

// Toggle between showing and hiding the sidebar, and add overlay effect
function w3_open() {
  if (mySidebar.style.display === 'block') {
    mySidebar.style.display = 'none';
    overlayBg.style.display = "none";
  } else {
    mySidebar.style.display = 'block';
    overlayBg.style.display = "block";
  }
}

// Close the sidebar with the close button
function w3_close() {
  mySidebar.style.display = "none";
  overlayBg.style.display = "none";
}
</script>

</body>
</html>
