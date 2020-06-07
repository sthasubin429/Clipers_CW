<%-- 
    Document   : index
    Created on : Jun 1, 2020, 10:21:52 PM
    Author     : Subin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<title>User Management</title>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<style><%@include file="css/index.css"%></style>

<link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Raleway">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<style>
body,h1,h2,h3,h4,h5,h6 {font-family: "Raleway", sans-serif}

body, html {
  height: 100%;
  line-height: 1.8;
  font-size: 18px;
}

/* Full height image header */ 
.bgimg-1 {
  background-position: center;
  background-size: cover;
  background-image: url("https://github.com/sthasubin429/Ciphers_CW_img/blob/master/home-bg2.jpg?raw=true");
  min-height: 100%;
}

.w3-bar .w3-button {
  padding: 16px;
}
</style>
<body>

<!-- Navbar (sit on top) -->
<div class="w3-top">
  <div class="w3-bar w3-white w3-card" id="myNavbar">
    <a href="#home" class="w3-bar-item w3-button w3-wide" style="font-weight:300">USER MANAGEMENT</a>
    <!-- Right-sided navbar links -->
    <div class="w3-right w3-hide-small">
      <a href="/UserManagement/signin" class="w3-bar-item w3-button"> Login</a>
      <a href="/UserManagement/signup" class="w3-bar-item w3-button">Signup</a>
    </div>
    <!-- Hide right-floated links on small screens and replace them with a menu icon -->

    <a href="javascript:void(0)" class="w3-bar-item w3-button w3-right w3-hide-large w3-hide-medium" onclick="w3_open()">
      <i class="fa fa-bars"></i>
    </a>
  </div>
</div>

<!-- Sidebar on small screens when clicking the menu icon -->
<nav class="w3-sidebar w3-bar-block w3-black w3-card w3-animate-left w3-hide-medium w3-hide-large" style="display:none" id="mySidebar">
  <a href="javascript:void(0)" onclick="w3_close()" class="w3-bar-item w3-button w3-large w3-padding-16">Close ×</a>
  <a href="" class="w3-bar-item w3-button"> Login</a>
      <a href="" class="w3-bar-item w3-button">Signup</a>
</nav>

<!-- Header with full-height image -->
<header class="bgimg-1 w3-display-container w3-grayscale-min" id="home">
  <div class="w3-display-left w3-text-white" style="padding:24px 48px;text-align: center;padding-left: 29%;">
    <span class="w3-jumbo w3-hide-small">We help You manage users!</span><br>
    <span class="w3-xxlarge w3-hide-large w3-hide-medium">We help You manage users!</span>
    <p><a href="#team" class="w3-button w3-white w3-padding-large w3-large w3-margin-top w3-opacity w3-hover-opacity-off">Learn about our team</a></p>
  </div> 
  <div class="w3-display-bottomleft w3-text-grey w3-large" style="padding:24px 48px">
    <i class="fa fa-facebook-official w3-hover-opacity"></i>
    <i class="fa fa-instagram w3-hover-opacity"></i>
    <i class="fa fa-snapchat w3-hover-opacity"></i>
    <i class="fa fa-pinterest-p w3-hover-opacity"></i>
    <i class="fa fa-twitter w3-hover-opacity"></i>
    <i class="fa fa-linkedin w3-hover-opacity"></i>
  </div>
</header>



<!-- Team Section -->
<div class="w3-container" style="padding:100px 16px" id="team">
  <h3 class="w3-center">OUR TEAM</h3>
  <div class="w3-row-padding w3-grayscale" style="margin-top:64px;text-align: center;">
    <div class="w3-col l3 m6 w3-margin-bottom">
      <div class="w3-card">
        <img src="https://raw.githubusercontent.com/sthasubin429/Ciphers_CW_img/master/1.PNG" alt="Sandip Thapa" style="width:100%">
        <div class="w3-container">
          <h3>Sandip Thapa</h3>
          <p class="w3-opacity">Front End Developer</p>
        </div>
      </div>
    </div>
    <div class="w3-col l3 m6 w3-margin-bottom">
      <div class="w3-card">
        <img src="https://raw.githubusercontent.com/sthasubin429/Ciphers_CW_img/master/2.PNG" alt="Subin Shrestha" style="width:100%">
        <div class="w3-container">
          <h3>Subin Shrestha</h3>
          <p class="w3-opacity">Back-end Developer</p>
        </div>
      </div>
    </div>
    <div class="w3-col l3 m6 w3-margin-bottom">
      <div class="w3-card">
        <img src="https://raw.githubusercontent.com/sthasubin429/Ciphers_CW_img/master/3.PNG" alt="Smita Shrestha" style="width:100%">
        <div class="w3-container">
          <h3>Smita Shrestha</h3>
          <p class="w3-opacity">Content Manager</p>
        </div>
      </div>
    </div>
    <div class="w3-col l3 m6 w3-margin-bottom">
      <div class="w3-card">
        <img src="https://raw.githubusercontent.com/sthasubin429/Ciphers_CW_img/master/4.PNG" alt="Sagar Kunwar" style="width:100%">
        <div class="w3-container">
          <h3>Sagar Kunwar</h3>
          <p class="w3-opacity">Data Analyst.</p>
        </div>
      </div>
    </div>
    <div class="w3-col l3 m6 w3-margin-bottom">
      <div class="w3-card">
        <img src="https://raw.githubusercontent.com/sthasubin429/Ciphers_CW_img/master/5.PNG" alt="Sankalpa Shrestha" style="width:100%">
        <div class="w3-container">
          <h3>Sankalpa Shrestha</h3>
          <p class="w3-opacity">Quality Analyst</p>
        </div>
      </div>
    </div>
    <div class="w3-col l3 m6 w3-margin-bottom">
      <div class="w3-card">
        <img src="https://raw.githubusercontent.com/sthasubin429/Ciphers_CW_img/master/6.PNG" alt="Sahan Dangol" style="width:100%">
        <div class="w3-container">
          <h3>Sahan Dangol</h3>
          <p class="w3-opacity">UI/UX Designer</p>
        </div>
      </div>
    </div>

    
    
  </div>
</div>

<!-- Promo Section "Statistics" -->
<div class="w3-container w3-row w3-center w3-dark-grey w3-padding-64">
  <div class="w3-quarter">
    <span class="w3-xxlarge">14+</span>
    <br>Partners
  </div>
  <div class="w3-quarter">
    <span class="w3-xxlarge">55+</span>
    <br>Projects Done
  </div>
  <div class="w3-quarter">
    <span class="w3-xxlarge">89+</span>
    <br>Happy Clients
  </div>
  <div class="w3-quarter">
    <span class="w3-xxlarge">150+</span>
    <br>Meetings
  </div>
</div>






<!-- Footer -->
<footer class="w3-center w3-black w3-padding-64">
  <a href="#home" class="w3-button w3-light-grey"><i class="fa fa-arrow-up w3-margin-right"></i>To the top</a>
  <div class="w3-xlarge w3-section">
    <i class="fa fa-facebook-official w3-hover-opacity"></i>
    <i class="fa fa-instagram w3-hover-opacity"></i>
    <i class="fa fa-snapchat w3-hover-opacity"></i>
    <i class="fa fa-pinterest-p w3-hover-opacity"></i>
    <i class="fa fa-twitter w3-hover-opacity"></i>
    <i class="fa fa-linkedin w3-hover-opacity"></i>
  </div>
  <p>Copyright &copy 2020 CIPHERS</p>
</footer>
 
<script>

// Toggle between showing and hiding the sidebar when clicking the menu icon
var mySidebar = document.getElementById("mySidebar");

function w3_open() {
  if (mySidebar.style.display === 'block') {
    mySidebar.style.display = 'none';
  } else {
    mySidebar.style.display = 'block';
  }
}

// Close the sidebar with the close button
function w3_close() {
    mySidebar.style.display = "none";
}
</script>

</body>
</html>

