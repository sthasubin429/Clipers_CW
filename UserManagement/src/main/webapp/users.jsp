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
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
<link rel="stylesheet" href="https://cdn.datatbales.net/1.10.19/css/dataTables.bootstrap4.min.css">
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

.dataTables_filter{
    display:flex;
    justify-content: flex-end;
}

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
                  <div style="display: flex;">
                    <div style="display: flex; flex-direction: column; margin-left: 20px" >
                        <label for="">Start Date</label>
                        <input type="date" id="" name="startDate" placeholder="Start Date" required>
                    </div>
                    <div style="display: flex; flex-direction: column; margin-left: 20px">
                        <label for="">End Date</label>
                        <input type="date" id="" name="endDate" placeholder="End Date" required>
                    </div>
                    <div style="margin-left: 20px; margin-top: 12px">
                        <input type="submit" value="Generate Report">
                    </div>
                  </div>
                
              </form>
              <br>
              
            </div>
      <div style="float: right;">
        <a href="/UserManagement/createuser" style="text-decoration:none;"><i class="fa fa-plus"></i>  <b>Add users</b></a>
      </div>
      </div>
    </header>
      <div class="container mb-3 mt-3">
      
        <table class="table table-striped table-bordered mydatatable" style="width:100%">
          <thead>
                <tr>
                <th>ID</th>
                <th>Full Name</th>
                <th>Username</th>
                <th>Email</th>
                <th>Status</th>
                <th>Created Date</th>
                <th>Role</th>
                <th>Actions</th>
                </tr>
          </thead>
          <tbody>
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
          </tbody>
          <tfoot>

          </tfoot>
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

<script src="https://code.jquery.com/jquery-3.3.1.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>

<script src="https://cdn.datatables.net/1.10.19/js/jquery.dataTables.min.js"></script>
<script src="https://cdn.datatables.net/1.10.19/js/dataTables.bootstrap4.min.js"></script>

<script>
    $('.mydatatable').DataTable();
</script>

</body>
</html>