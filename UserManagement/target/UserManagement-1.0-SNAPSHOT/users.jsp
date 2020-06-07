<%-- 
    Document   : index.jsp
    Created on : May 15, 2020, 10:10:01 PM
    Author     : Subin
--%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<title>Users</title>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<style><%@include file="css/dashboard.css"%></style>
<link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Raleway">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<style>
html,body,h1,h2,h3,h4,h5 {font-family: "Raleway", sans-serif}
input[type=text] {
  width: 130px;
  box-sizing: border-box;
  border: 1px solid #ccc;
  border-radius: 4px;
  font-size: 16px;
  background-color: white;
  padding: 12px 20px 12px 40px;
  -webkit-transition: width 0.4s ease-in-out;
  transition: width 0.4s ease-in-out;
  padding:0;
}

input[type=text]:focus {
  width: 80%;}

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
        <h2><b><i class="fa fa-users"></i> User List</b></h2>
        <div>
          <div style="float: left;width: 80%;">
              <form action="/UserManagement/report">
                <div class="row">
                  <div class="col-25">
                    <label for="">Start Date</label>
                  </div>
                  <div class="col-75">
                    <input type="date" id="" name="startDate" placeholder="Start Date" required>
                  </div>
                </div>
                <div class="row">
                  <div class="col-25">
                    <label for="">End Date</label>
                  </div>
                  <div class="col-75">
                    <input type="date" id="" name="endDate" placeholder="End Date" required>
                  </div>
                </div>
                <div class="row">
                  <input type="submit" value="Generate Report">
                </div>
              </form>
              <br>
              
            <form action="/UserManagement/search">
                <div class="row">
                    <input type="text" name="searchKey" placeholder="Search by Username or Name">
                  </div>
            </form>
            </div>
      <div style="float: right;">
        <a href="/UserManagement/createuser" style="text-decoration:none;"><i class="fa fa-plus"></i>  <b>Add users</b></a>
      </div>
      </div>
    </header>
      <div class="w3-container" style="max-height:750px; overflow: auto;">
      
        <table class="w3-table-all w3-hoverable">
          <thead>
            <tr class="w3-light-grey">
                <tr>
                <th>ID</th>
                <th>Full Name</th>
                <th>Username</th>
                <th>Email</th>
                <th>Status</th>
                <th>Created Date</th>
                <th>Role</th>
                <th>Actions</th>
          </thead>
          <c:forEach var="user" items="${listUser}">
                <tr>
                    <td><c:out value="${user.user_id}" /></td>
                    <td><c:out value="${user.full_name}" /></td>
                    <td><c:out value="${user.username}" /></td>
                    <td><c:out value="${user.user_email}" /></td>
                    <td><c:out value="${user.user_status}" /></td>
                    <td><c:out value="${user.created_date}" /></td>
                    <td><c:out value="${user.user_role}" /></td>
                    <td>
                        <a href="/UserManagement/view?id=<c:out value='${user.user_id}' />"><i class="fa fa-eye"></i> View</a>
                        <a href="/UserManagement/delete?id=<c:out value='${user.user_id}' />" style="padding-left: 20px;"><i class="fa fa-trash"> Delete</i></a>         
                    </td>
                </tr>
            </c:forEach>
        </table>
      </div>
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