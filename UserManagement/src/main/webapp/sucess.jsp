<%-- 
    Document   : sucess
    Created on : May 18, 2020, 6:56:28 PM
    Author     : Subin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body
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
        <h1> Sucess </h1>
        
        <h1>Hi <%=userName %>, Login successful. Your Session ID=<%=sessionID %></h1>
        <br>
        <br>
        <form action="UserManagement/logout" method="post">
        <input type="submit" value="Logout" >
        </form>
        
        <a href="/UserManagement"> Home </a>
    </body>
</html>
