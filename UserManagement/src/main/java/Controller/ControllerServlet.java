/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Dao.UserDAO;
import Model.User;

import Dao.ActivityDAO;
import Model.Activity;

import Utility.EmailUtility;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpSession;

import java.io.File;
import javax.servlet.http.Part;

import java.text.DateFormat;  
import java.text.SimpleDateFormat;  
import java.util.Date;  
import java.util.Calendar;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Formatter;
import javax.mail.MessagingException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import org.apache.commons.lang3.RandomStringUtils;

/**
 *
 * @author Subin
 */
@WebServlet(name = "ControllerServlet", urlPatterns = {"/ControllerServlet"})
public class ControllerServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try ( PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet ControllerServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ControllerServlet at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }
    
    private final UserDAO userDAO;
    private final ActivityDAO activityDAO;
    private static final String UPLOAD_DIR = "ProfilePicures";
    
    
    public ControllerServlet(){
        this.userDAO = new UserDAO();
        this.activityDAO = new ActivityDAO();
    }
    
    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException{ 
            doGet(request, response);
       
    }
    
    

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
         String action = request.getServletPath();
        System.out.println(action);
        
        /*
            Switch cases checks for the url path and calls the correspoding function
        */        
        switch (action) {
            
            case "/":
                index(request,response);
                break;
                
            case "/dashboard":{
             try {
                 dashboard(request,response);
             } catch (ClassNotFoundException | SQLException ex) {
                 Logger.getLogger(ControllerServlet.class.getName()).log(Level.SEVERE, null, ex);
                 response.sendRedirect("/UserManagement/servererror");
             }
            }
                break; 
 
            case "/users":
             {
                 try {
                     users(request,response);
                 } catch (ClassNotFoundException | SQLException ex) {
                     Logger.getLogger(ControllerServlet.class.getName()).log(Level.SEVERE, null, ex);
                     response.sendRedirect("/UserManagement/servererror");
                 }
             }
                break;
                
            case "/createuser":{
             try {
                 createUser(request,response);
             } catch (SQLException | ClassNotFoundException ex) {
                 Logger.getLogger(ControllerServlet.class.getName()).log(Level.SEVERE, null, ex);
                 response.sendRedirect("/UserManagement/servererror");
             }
            }
            break;
            
            case "/adminCreateUser":
             {
                 try {
                     createUserAdmin(request,response);
                 } catch (SQLException | ClassNotFoundException | UnsupportedEncodingException | NoSuchAlgorithmException ex) {
                     Logger.getLogger(ControllerServlet.class.getName()).log(Level.SEVERE, null, ex);
                     response.sendRedirect("/UserManagement/servererror");
                 }
             }
                break;


            case "/signup":{
             try {
                 signup(request, response);
             } catch (ClassNotFoundException | SQLException ex) {
                 Logger.getLogger(ControllerServlet.class.getName()).log(Level.SEVERE, null, ex);
                 response.sendRedirect("/UserManagement/servererror");
             }
            }
                break;  
                
            case "/register":{
            try {
                register(request, response);
            } catch (ClassNotFoundException | SQLException | NoSuchAlgorithmException ex) {
                Logger.getLogger(ControllerServlet.class.getName()).log(Level.SEVERE, null, ex);
                response.sendRedirect("/UserManagement/servererror");
            }
            }
                break;
                
            case "/signin":{
             try {
                 signin(request, response);
             } catch (ClassNotFoundException | SQLException ex) {
                 Logger.getLogger(ControllerServlet.class.getName()).log(Level.SEVERE, null, ex);
                 response.sendRedirect("/UserManagement/servererror");
             }
            }
                break;
            
            case "/login":{
            try {
                login(request, response);
            } catch (UnsupportedEncodingException | NoSuchAlgorithmException | ClassNotFoundException | SQLException ex) {
                Logger.getLogger(ControllerServlet.class.getName()).log(Level.SEVERE, null, ex);
                response.sendRedirect("/UserManagement/servererror");
            }
            }
                break;
                
            case "/view":
            {
                try {
                    view(request, response);
                } catch (ClassNotFoundException | SQLException ex) {
                    Logger.getLogger(ControllerServlet.class.getName()).log(Level.SEVERE, null, ex);
                    response.sendRedirect("/UserManagement/servererror");
                }
            }
                break;
                
            case "/edit":
             {
                 try {
                     edit(request,response);
                 } catch (NullPointerException | ClassNotFoundException | SQLException ex) {
                     Logger.getLogger(ControllerServlet.class.getName()).log(Level.SEVERE, null, ex);
                     response.sendRedirect("/UserManagement/servererror");
                 }
             }
                break;

            case "/editUser":
             {
                 try {
                     editUser(request,response);
                 } catch (NullPointerException | ClassNotFoundException | SQLException ex) {
                     Logger.getLogger(ControllerServlet.class.getName()).log(Level.SEVERE, null, ex);
                     response.sendRedirect("/UserManagement/servererror");
                 }
             }
                break;

            case "/logout":
                logout(request,response);
                break;
            
            case "/delete":
             {
                 try {
                     delete(request,response);
                 } catch (ClassNotFoundException | SQLException ex) {
                     Logger.getLogger(ControllerServlet.class.getName()).log(Level.SEVERE, null, ex);
                     response.sendRedirect("/UserManagement/servererror");
                 }
             }
                break;

            case "/changepassword":
             {
                 try {
                     changepassword(request,response);
                 } catch (ClassNotFoundException | SQLException ex) {
                     Logger.getLogger(ControllerServlet.class.getName()).log(Level.SEVERE, null, ex);
                     response.sendRedirect("/UserManagement/servererror");
                 }
             }
                break;
                
             case "/changepasswordsubmit":
             {
                 try {
                     changePasswordSubmit(request,response);
                 } catch (ClassNotFoundException | SQLException | UnsupportedEncodingException | NoSuchAlgorithmException ex) {
                     Logger.getLogger(ControllerServlet.class.getName()).log(Level.SEVERE, null, ex);
                     response.sendRedirect("/UserManagement/servererror");
                 }
             }
                break;


                
            case "/accessdenied":{
                accessdenied(request,response);
            }
                break;
                
            case "/activitylog":
             {
                 try {
                     activitylog(request,response);
                 } catch (SQLException | ClassNotFoundException ex) {
                     Logger.getLogger(ControllerServlet.class.getName()).log(Level.SEVERE, null, ex);
                     response.sendRedirect("/UserManagement/servererror");
                 }
             }
                break;
            case "/forgotpassword":
             {
                 try {
                     forgotPassword(request,response);
                 } catch (ClassNotFoundException | SQLException ex) {
                     Logger.getLogger(ControllerServlet.class.getName()).log(Level.SEVERE, null, ex);
                     response.sendRedirect("/UserManagement/servererror");
                 }
             }
                break;
                
            case "/resetpassword":
             {
                 try {
                     resetPassword(request,response);
                 } catch (UnsupportedEncodingException | NoSuchAlgorithmException | ClassNotFoundException | SQLException ex) {
                     Logger.getLogger(ControllerServlet.class.getName()).log(Level.SEVERE, null, ex);
                     response.sendRedirect("/UserManagement/servererror");
                 }
             }
                break;
            case "/report":
             {
                 try {
                     getReport(request,response);
                 } catch (ClassNotFoundException | SQLException ex) {
                     Logger.getLogger(ControllerServlet.class.getName()).log(Level.SEVERE, null, ex);
                     response.sendRedirect("/UserManagement/servererror");
                     
                 }
             }
                break;
                
            case "/servererror":
                serverError(request,response);
                break;
                
            default:
                notFound(request,response);
                break;
        }
    }

    
    
    //Renders landing page
    /**
     * 
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException 
     */
    private void index(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        RequestDispatcher dispatcher = request.getRequestDispatcher("index_new.jsp");
        dispatcher.forward(request, response);
    
    }
    
    
    //Reders page not found page if the given url does not match
    /**
     * 
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException 
     */
    private void notFound(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        RequestDispatcher dispatcher = request.getRequestDispatcher("pagenotfound.jsp");
        dispatcher.forward(request, response);
    }

    /**
     * 
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException 
     */
    // Renders access denied page if the uesr try to acess resourses that are restricted
    private void accessdenied(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        RequestDispatcher dispatcher = request.getRequestDispatcher("accessdenied.jsp");
        dispatcher.forward(request, response);
    
    }
    
    
    //Renders server error page if any error occurs in the server
    /**
     * 
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException 
     */
    private void serverError(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        RequestDispatcher dispatcher = request.getRequestDispatcher("servererror.jsp");
        dispatcher.forward(request, response);
    
    }
    
    
    
    /**
     *Renders Admin Dashboard
     * Checks the user is logged in or not. Redirects to sign in page if not logged in
     * Checks if the  user is logged in user is admin or not. Redirects to access denied page if not admin
     */
    /**
     * 
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     * @throws ClassNotFoundException
     * @throws SQLException 
     */
    private void dashboard(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ClassNotFoundException, SQLException{
        HttpSession session = request.getSession();
        
        String userName = null;
        Cookie[] cookies = request.getCookies();
        if(cookies !=null){
        for(Cookie cookie : cookies){
                if(cookie.getName().equals("user")) userName = cookie.getValue();
        }
        }
        if(session.getAttribute("user") == null){
            response.sendRedirect("/UserManagement/signin");
        }
        else if (!this.userDAO.checkAdmin(userName)){
            response.sendRedirect("/UserManagement/accessdenied");
        }
        
        else{
            request.setAttribute("currentUser", userName);
            RequestDispatcher dispatcher = request.getRequestDispatcher("dashboard.jsp");
            dispatcher.forward(request, response);
        }
        
    
    }
    
    
    
    /**
     *Renders user Profile
     * Allows admin to view profile of other users as well
     *If the user is not admin, Allows to view the page only if the logged in user and requested user profile are of same user.
     * Redirects to access denied page otherwise
     */
    /**
     * 
     * @param request
     * @param response
     * @throws ClassNotFoundException
     * @throws SQLException
     * @throws ServletException
     * @throws IOException 
     */
    private void users(HttpServletRequest request, HttpServletResponse response) throws ClassNotFoundException, SQLException, ServletException, IOException{
        HttpSession session = request.getSession();
        
        String userName = null;
        Cookie[] cookies = request.getCookies();
        if(cookies !=null){
        for(Cookie cookie : cookies){
                if(cookie.getName().equals("user")) userName = cookie.getValue();
        }
        }
        if(session.getAttribute("user") == null){
            response.sendRedirect("/UserManagement/signin");
        }
        else if (!this.userDAO.checkAdmin(userName)){
            response.sendRedirect("/UserManagement/accessdenied");
        }
        
        else{
            List<User> listUser = this.userDAO.select_all();
            request.setAttribute("listUser", listUser);
            request.setAttribute("currentUser", userName);
            RequestDispatcher dispatcher = request.getRequestDispatcher("users.jsp");
            dispatcher.forward(request, response);
        }
        
    }
    
    
    /**
     * Renders list of all user profiles with in the specified date range
     * Function called after generate report function is called in user page
     * Allows admin to view profile of other users as well
     * If the user is not admin, Allows to view the page only if the logged in user and requested user profile are of same user.
     * Redirects to access denied page otherwise
     * 
     * 
     * @param request
     * @param response
     * @throws ClassNotFoundException
     * @throws SQLException
     * @throws ServletException
     * @throws IOException 
     */
    private void getReport(HttpServletRequest request, HttpServletResponse response) throws ClassNotFoundException, SQLException, ServletException, IOException{
        HttpSession session = request.getSession();
        String startDate = request.getParameter("startDate");
        String endDate = request.getParameter("endDate");
        
        
        String userName = null;
        Cookie[] cookies = request.getCookies();
        if(cookies !=null){
        for(Cookie cookie : cookies){
                if(cookie.getName().equals("user")) userName = cookie.getValue();
        }
        }
        if(session.getAttribute("user") == null){
            response.sendRedirect("/UserManagement/signin");
        }
        else if (!this.userDAO.checkAdmin(userName)){
            response.sendRedirect("/UserManagement/accessdenied");
        }
        
        else{
            List<User> listUser = this.userDAO.report(startDate, endDate);
            request.setAttribute("listUser", listUser);
            request.setAttribute("currentUser", userName);
            RequestDispatcher dispatcher = request.getRequestDispatcher("users.jsp");
            dispatcher.forward(request, response);
        }
        
    }
   
    /**
     *Renders create user page
     * Only allows access to admin
     */
    /**
     * 
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     * @throws SQLException
     * @throws ClassNotFoundException 
     */
    private void createUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException, ClassNotFoundException{
        HttpSession session = request.getSession();
        
        String userName = null;
        Cookie[] cookies = request.getCookies();
        if(cookies !=null){
        for(Cookie cookie : cookies){
                if(cookie.getName().equals("user")) userName = cookie.getValue();
        }
        }
        if(session.getAttribute("user") == null){
            response.sendRedirect("/UserManagement/signin");
        }
        else if (!this.userDAO.checkAdmin(userName)){
            response.sendRedirect("/UserManagement/accessdenied");
        }
        
        else{
            
            request.setAttribute("errorMessage", "Welcome");
            request.setAttribute("value", "Hidden");
            request.setAttribute("currentUser", userName);
            RequestDispatcher dispatcher = request.getRequestDispatcher("createUser.jsp");
            dispatcher.forward(request, response);
        }
    
    }
    
    
    
    /**
     * This function is called after the form in create user page is submitted
     *Action function of the create user page.
     * Checks if the email is not empty, password is of required length, passwords match, username and email is unique
     * Hashes the password and calls the function to store new user into the databse
     */
    /**
     * 
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     * @throws SQLException
     * @throws ClassNotFoundException
     * @throws UnsupportedEncodingException
     * @throws NoSuchAlgorithmException 
     */
    private void createUserAdmin(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException, ClassNotFoundException, UnsupportedEncodingException, NoSuchAlgorithmException{
          HttpSession session = request.getSession();
        
            String userName = null;
            Cookie[] cookies = request.getCookies();
            if(cookies !=null){
            for(Cookie cookie : cookies){
                    if(cookie.getName().equals("user")) userName = cookie.getValue();
            }
            }

          String fullName = request.getParameter("admin_fullname");
          String username = request.getParameter("admin_username");
          String email = request.getParameter("admin_email");
          String password = request.getParameter("admin_password");
          String conformPassword = request.getParameter("admin_confirm_password");
          String role = request.getParameter("admin_role");
    
          Date date = Calendar.getInstance().getTime();  
          DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");  
          String created_date = dateFormat.format(date);
          request.setAttribute("errorMessage", "Welcome");
          request.setAttribute("value", "Hidden");
            
          if(email.isEmpty()){
              request.setAttribute("errorMessage", "Email cannot be empty");
              request.setAttribute("value", "");
              request.setAttribute("currentUser", userName);
              RequestDispatcher dispatcher = request.getRequestDispatcher("createUser.jsp");
              dispatcher.forward(request, response);
          }
          else if(password.length() < 8){
              request.setAttribute("errorMessage", "Password Must be 8 character or Longer");
              request.setAttribute("value", "");
              request.setAttribute("currentUser", userName);
              RequestDispatcher dispatcher = request.getRequestDispatcher("createUser.jsp");
              dispatcher.forward(request, response);
          
          }
          else if (password.compareTo(conformPassword) != 0){
              request.setAttribute("errorMessage", "Passwords do not match");
              request.setAttribute("value", "");
              request.setAttribute("currentUser", userName);
              RequestDispatcher dispatcher = request.getRequestDispatcher("createUser.jsp");
              dispatcher.forward(request, response);
              
          }
          
          else if (this.userDAO.checkEmail(email)){
              request.setAttribute("errorMessage", "Email already in use, Please use different email");
              request.setAttribute("value", "");
              request.setAttribute("currentUser", userName);
              RequestDispatcher dispatcher = request.getRequestDispatcher("createUser.jsp");
              dispatcher.forward(request, response);
          }
          
          else if (this.userDAO.checkUsername(username)){
              request.setAttribute("errorMessage", "Username already in use, Please use different username");
              request.setAttribute("value", "");
              request.setAttribute("currentUser", userName);
              RequestDispatcher dispatcher = request.getRequestDispatcher("createUser.jsp");
              dispatcher.forward(request, response);
          }
          else{
            
                String enc_password = encryptPassword(password);
                User newUser = new User(fullName, username, email, enc_password, created_date, role);
                this.userDAO.createUserAdmin(newUser);  
                
                insertActivity("User Created", username);
                
                
                request.setAttribute("errorMessage", "Welcome");
                request.setAttribute("value", "Hidden");
                response.sendRedirect("/UserManagement/users");
      
          }
    
    }
    
    /**
     *Renders Sign up page for clients
     * Checks if any user is currently logged in or not, redirects to current user's profile if true
     */
    /**
     * 
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     * @throws ClassNotFoundException
     * @throws SQLException 
     */
    private void signup(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ClassNotFoundException, SQLException {
        HttpSession session = request.getSession();
        if(session.getAttribute("user") != null){
            User currentUser = this.userDAO.getUserbyUsername((String) session.getAttribute("user"));
            response.sendRedirect("/UserManagement/view?id="+currentUser.getUser_id());
        }
        else{
            RequestDispatcher dispatcher = request.getRequestDispatcher("register.jsp");
            request.setAttribute("errorMessage", "Welcome");
            request.setAttribute("value", "Hidden");
            dispatcher.forward(request, response);
        }
        
    }
    
    /**
     * 
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     * @throws ClassNotFoundException
     * @throws SQLException
     * @throws NoSuchAlgorithmException 
     * 
     * Function called after form in signup page is submitted
     * Checks if the email is not blank, password is of appropriate length, passwords match and the email and username is unique.
     * Renders the same sign in page with appropriate message if the above conditions are not met
     * Else calls the function to store new users into the database
     */
    private void register(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ClassNotFoundException, SQLException, NoSuchAlgorithmException {
        
          String fullName = request.getParameter("fullName");
          String username = request.getParameter("username");
          String email = request.getParameter("email");
          String password = request.getParameter("password");
          String conformPassword = request.getParameter("conformPassword");
    
          Date date = Calendar.getInstance().getTime();  
          DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");  
          String created_date = dateFormat.format(date);  
          request.setAttribute("errorMessage", "Welcome");
          request.setAttribute("value", "Hidden");
            
          if(email.isEmpty()){
              request.setAttribute("errorMessage", "Email cannot be empty");
              request.setAttribute("value", "");
              RequestDispatcher dispatcher = request.getRequestDispatcher("register.jsp");
              dispatcher.forward(request, response);
          }
          else if(password.length() < 8){
              request.setAttribute("errorMessage", "Password Must be 8 character or Longer");
              request.setAttribute("value", "");
              RequestDispatcher dispatcher = request.getRequestDispatcher("register.jsp");
              dispatcher.forward(request, response);
          
          }
          else if (password.compareTo(conformPassword) != 0){
              request.setAttribute("errorMessage", "Passwords do not match");
              request.setAttribute("value", "");
              RequestDispatcher dispatcher = request.getRequestDispatcher("register.jsp");
              dispatcher.forward(request, response);
              
          }
          
          else if (this.userDAO.checkEmail(email)){
              request.setAttribute("errorMessage", "Email already in use, Please use different email");
              request.setAttribute("value", "");
              RequestDispatcher dispatcher = request.getRequestDispatcher("register.jsp");
              dispatcher.forward(request, response);
          }
          
          else if (this.userDAO.checkUsername(username)){
              request.setAttribute("errorMessage", "Username already in use, Please use different username");
              request.setAttribute("value", "");
              RequestDispatcher dispatcher = request.getRequestDispatcher("register.jsp");
              dispatcher.forward(request, response);
          }
          else{
            
                String enc_password = encryptPassword(password);
                User newUser = new User(fullName, username, email, enc_password, created_date);
                this.userDAO.createUserClient(newUser);  
                
                HttpSession session = request.getSession();
                User currentUser = this.userDAO.getUserbyEmail(email);
                session.setAttribute("user", currentUser.getUsername());

                session.setMaxInactiveInterval(30*60);
                Cookie userName = new Cookie("user", currentUser.getUsername());
                userName.setMaxAge(30*60);
                response.addCookie(userName);

                request.setAttribute("errorMessage", "Welcome");
                request.setAttribute("value", "Hidden");
                
                insertActivity("User Created", username);


                if (this.userDAO.checkAdminbyEmail(email)){
                    response.sendRedirect("/UserManagement/dashboard");
                }
                else{
                    response.sendRedirect("/UserManagement/view?id="+currentUser.getUser_id());
                }
          }
    }
    
    
    /**
     * 
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     * @throws ClassNotFoundException
     * @throws SQLException 
     *  Renders the sign in page
     * Checks if the any user is currently logged in or not.
     * If any user is logged in redirects to current user's profile
     */
    private void signin(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ClassNotFoundException, SQLException {
        HttpSession session = request.getSession();
        if(session.getAttribute("user") != null){
            User currentUser = this.userDAO.getUserbyUsername((String) session.getAttribute("user"));
            response.sendRedirect("/UserManagement/view?id="+currentUser.getUser_id());
        }
        else{
            request.setAttribute("errorMessage", "Welcome");
            request.setAttribute("value", "Hidden");
            RequestDispatcher dispatcher = request.getRequestDispatcher("login.jsp");
            dispatcher.forward(request, response);
        }
    }
    
    
    /**
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     * @throws UnsupportedEncodingException
     * @throws NoSuchAlgorithmException
     * @throws ClassNotFoundException
     * @throws SQLException 
     * 
     * Function called after form in sign in page is submitted
     * Checks the email is a valid email, password submitted matches the password in the database.
     * Renders sign in page with appropriate message if the above conditions are not met
     * Else creates the session with the given usrname
     * If the user is admin renders dashboard 
     * if the user is client renders user profile
     * 
     */
    private void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, UnsupportedEncodingException, NoSuchAlgorithmException, ClassNotFoundException, SQLException {
        String email = request.getParameter("signin_email");
        String password = request.getParameter("signin_password");
        String enc_password = encryptPassword(password);
        
        
        request.setAttribute("errorMessage", "Welcome");
        request.setAttribute("value", "Hidden");        
        if (this.userDAO.checkEmail(email) && this.userDAO.authenticate(email, enc_password)){
            HttpSession session = request.getSession();
            User currentUser = this.userDAO.getUserbyEmail(email);
            session.setAttribute("user", currentUser.getUsername());
            
            session.setMaxInactiveInterval(30*60);
            Cookie userName = new Cookie("user", currentUser.getUsername());
            userName.setMaxAge(30*60);
            response.addCookie(userName);
            
            request.setAttribute("errorMessage", "Welcome");
            request.setAttribute("value", "Hidden");
            
            insertActivity("Logged in", currentUser.getUsername());
            
            
            if (this.userDAO.checkAdminbyEmail(email)){
                response.sendRedirect("/UserManagement/dashboard");
            }
            else{
                response.sendRedirect("/UserManagement/view?id="+currentUser.getUser_id());
            }
            
        }
        
        else{
            request.setAttribute("errorMessage", "Email address or password doesnot match. Please try again");
            request.setAttribute("value", " ");
            RequestDispatcher dispatcher = request.getRequestDispatcher("login.jsp");
            dispatcher.forward(request, response);
        }

        
    }
    
    
    /**
     * Renders user profile for user
     * Allows access to admin
     * if user is not logged in redirects to sign in page
     * if the logged in user and requested user profile is not same redirects to access denied page
     * 
     * 
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     * @throws ClassNotFoundException
     * @throws SQLException 
     */
    private void view(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ClassNotFoundException, SQLException{
        int id = Integer.parseInt(request.getParameter("id"));
        HttpSession session = request.getSession();
        
        if(session.getAttribute("user") == null){
            response.sendRedirect("/UserManagement/signin");
        }
        else if (!this.userDAO.checkID(id)){
            response.sendRedirect("/UserManagement/pagenotfound");
        }
        
        
        else{
            User user;
            user = this.userDAO.getUserbyID(id);
            User current_user = this.userDAO.getUserbyUsername((String) session.getAttribute("user"));
            if (session.getAttribute("user").equals(user.getUsername()) || current_user.getUser_role().equals("Admin")){
                List<Activity> userActivity = this.activityDAO.getActivityByUsername(user.getUsername());
                request.setAttribute("oneUser", user);
                request.setAttribute("userActivity", userActivity);
                RequestDispatcher dispatcher = request.getRequestDispatcher("view.jsp");
                dispatcher.forward(request, response);
            }
            else{
                response.sendRedirect("/UserManagement/accessdenied");
            }
            
        }
        
    }
    
    
    /**
     * Renders form to edit user details
     * Redirects to sign in page if the user is not sign in page,
     * 
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     * @throws NullPointerException 
     */
    private void edit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, NullPointerException, ClassNotFoundException, SQLException{
        int id = Integer.parseInt(request.getParameter("id"));
        HttpSession session = request.getSession();
        
        if(session.getAttribute("user") == null){
            response.sendRedirect("/UserManagement/signin");
        }
        else if (!this.userDAO.checkID(id)){
            response.sendRedirect("/UserManagement/pagenotfound");
        }
        
        else{
            User user;
            user = this.userDAO.getUserbyID(id);
            User current_user = this.userDAO.getUserbyUsername((String) session.getAttribute("user"));

            if(session.getAttribute("user").equals(user.getUsername()) || current_user.getUser_role().equals("Admin")){
                request.setAttribute("currentUser", user);
                RequestDispatcher dispatcher = request.getRequestDispatcher("edit.jsp");
                dispatcher.forward(request, response);
            }                
            else{
                response.sendRedirect("/UserManagement/accessdenied");
            }
                
        }
        
    
    }
    
    
    /**
     * Function called after form in edit page is submitted
     * Renders the same page with appropriate message if the username is empty, email and username are not unique
     * Else Updates the users name, email and username into the database
     * 
     * 
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     * @throws NullPointerException
     * @throws ClassNotFoundException
     * @throws SQLException 
     */
    private void editUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, NullPointerException, ClassNotFoundException, SQLException{
        HttpSession session = request.getSession();
        String id = request.getParameter("edit_id");
        String username = request.getParameter("edit_username");
        String email = request.getParameter("edit_email");
        String name = request.getParameter("edit_name");
        
        int int_id = Integer.parseInt(id);
        
        if(session.getAttribute("user") == null){
            response.sendRedirect("/UserManagement/signin");
        }
        
        if(email.isEmpty()){
              request.setAttribute("errorMessage", "Email cannot be empty");
              request.setAttribute("value", "");
              User user = this.userDAO.getUserbyID(int_id);
              request.setAttribute("currentUser", user);
              RequestDispatcher dispatcher = request.getRequestDispatcher("edit.jsp");
              dispatcher.forward(request, response);
        }
          
          else if (this.userDAO.editCheckEmail(int_id, email)){
              request.setAttribute("errorMessage", "Email already in use, Please use different email");
              request.setAttribute("value", "");
              User user = this.userDAO.getUserbyID(int_id);
              request.setAttribute("currentUser", user);
              RequestDispatcher dispatcher = request.getRequestDispatcher("edit.jsp");
              dispatcher.forward(request, response);
          }
          
          else if (this.userDAO.editCheckUsername(int_id, username)){
              request.setAttribute("errorMessage", "Username already in use, Please use different username");
              request.setAttribute("value", "");
              User user = this.userDAO.getUserbyID(int_id);
              request.setAttribute("currentUser", user);
              RequestDispatcher dispatcher = request.getRequestDispatcher("edit.jsp");
              dispatcher.forward(request, response);
          }
        
        else{
            int intID = Integer.parseInt(id);
            User userUpdate = new User(intID, name, username, email);
            
            
            if (this.userDAO.updateUser(userUpdate)){
                session.setAttribute("user", userUpdate.getUsername());
                insertActivity("User Edited", userUpdate.getUsername());
                response.sendRedirect("/UserManagement/view?id="+id);
            }
                      
            else{
               response.sendRedirect("/UserManagement/edit?id="+id);
            }
            
        }
        
    
    }
    
    /**
     * Invalidates the current session and Redirects to sign in page
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException 
     */
     private void logout(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        Cookie[] cookies = request.getCookies();
    	if(cookies != null){
    	for(Cookie cookie : cookies){
    		if(cookie.getName().equals("JSESSIONID")){
    			System.out.println("JSESSIONID="+cookie.getValue());
    			break;
    		}
    	}
    	}
    	//invalidate the session if exists
    	HttpSession session = request.getSession(false);
    	if(session != null){
            	session.invalidate();
                response.sendRedirect("/UserManagement/signin");
    	}
        else{
            response.sendRedirect("/UserManagement/signin");
        }
    }
     
     /**
      * Deletes the user from the database and logs out user
      * Redirects to sign in page if the user is ont logged in 
      * Redirects to page not found if the id in the url does not exist in the database
      * Redirects to access denied if the user is not admin or the currently logged in user and the profile to be deleted are not same
      * 
      * @param request
      * @param response
      * @throws ServletException
      * @throws IOException
      * @throws ClassNotFoundException
      * @throws SQLException 
      */
     private void delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ClassNotFoundException, SQLException{
        int id = Integer.parseInt(request.getParameter("id"));
        HttpSession session = request.getSession();
        User user;
        user = this.userDAO.getUserbyID(id);
        
        if(session.getAttribute("user") == null){
            response.sendRedirect("/UserManagement/signin");
        }
        else if(!this.userDAO.checkEmail(user.getUser_email())){
            response.sendRedirect("UserManagement/pagenotfound");
        }
        
        else{
            User current_user = this.userDAO.getUserbyUsername((String) session.getAttribute("user"));
            if (current_user.getUsername().equals(user.getUsername()) || current_user.getUser_role().equals("Admin")){
                this.userDAO.deleteUser(id);
                if (current_user.getUser_role().equals("Admin")){
                    response.sendRedirect("/UserManagement/dashboard");
                }
                else{
                    response.sendRedirect("/UserManagement/logout");
                }
                
            }
            else{
                response.sendRedirect("/UserManagement/accessdenied");
            }
            
        }
         
    
    }
     
     
     /**
      * Renders Change password form 
      * If the user is not logged in redirects to sign in page
      * Redirects to access denied page it the logged in user is not admin 
      * and user whose password to be changed is not as same as the logged in user
      * 
      * @param request
      * @param response
      * @throws ServletException
      * @throws IOException
      * @throws ClassNotFoundException
      * @throws SQLException 
      */
     
     private void changepassword(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ClassNotFoundException, SQLException{
        int id = Integer.parseInt(request.getParameter("id"));
        HttpSession session = request.getSession();
        
        request.setAttribute("errorMessage", "Welcome");
        request.setAttribute("value", "Hidden");
        
        if(session.getAttribute("user") == null){
            response.sendRedirect("/UserManagement/signin");
        }
        
        else if (!this.userDAO.checkID(id)){
            response.sendRedirect("/UserManagement/pagenotfound");
        }
        
        else{
            User user;
            user = this.userDAO.getUserbyID(id);
            User current_user = this.userDAO.getUserbyUsername((String) session.getAttribute("user"));
            if (session.getAttribute("user").equals(user.getUsername()) || current_user.getUser_role().equals("Admin")){
                request.setAttribute("errorMessage", "Welcome");
                request.setAttribute("value", "Hidden");
                request.setAttribute("oneUser", user);
                RequestDispatcher dispatcher = request.getRequestDispatcher("changepassword.jsp");
                dispatcher.forward(request, response);
    
            }
            else{
                response.sendRedirect("/UserManagement/accessdenied");
            }
            
        }
        
    }
     
     /**
      * Function called after form in change password is submitted
      * Hashes the password and Updates the password in database
      * Checks if the password is of appropriate length, passwords match both old password and confirm password
      * Renders same page with appropriate message if the conditions are not met
      * 
      * @param request
      * @param response
      * @throws ServletException
      * @throws IOException
      * @throws ClassNotFoundException
      * @throws SQLException
      * @throws UnsupportedEncodingException
      * @throws NoSuchAlgorithmException 
      */
     private void changePasswordSubmit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ClassNotFoundException, SQLException, UnsupportedEncodingException, NoSuchAlgorithmException{
       
        HttpSession session = request.getSession();
        
        String id = request.getParameter("ch_pass_id");
        String old_password = request.getParameter("old_password");
        String new_password = request.getParameter("new_password");
        String confirm_password = request.getParameter("confirm_password");
        
        
        String enc_old_password = encryptPassword(old_password);
        String enc_new_password = encryptPassword(new_password);
        String enc_confirm_password = encryptPassword(confirm_password);
        
        request.setAttribute("errorMessage", "Welcome");
        request.setAttribute("value", "Hidden");
                
        User user = this.userDAO.getUserbyID(Integer.parseInt(id));
        System.out.println(Integer.parseInt(id));
        User current_user = this.userDAO.getUserbyUsername((String) session.getAttribute("user"));
        
        if(session.getAttribute("user") == null){
            response.sendRedirect("/UserManagement/signin");
        }
        
        else if(new_password.length() < 8){
              request.setAttribute("errorMessage", "New Password Must be 8 character or Longer");
              request.setAttribute("value", "");
              request.setAttribute("oneUser", user);
              RequestDispatcher dispatcher = request.getRequestDispatcher("changepassword.jsp");
              dispatcher.forward(request, response);
          
          }
        else if (new_password.compareTo(confirm_password) != 0){
            request.setAttribute("errorMessage", "Passwords do not match");
            request.setAttribute("value", "");
            request.setAttribute("oneUser", user);
            RequestDispatcher dispatcher = request.getRequestDispatcher("changepassword.jsp");
            dispatcher.forward(request, response);

        }
        
        
        else if ( !this.userDAO.getPassword(Integer.parseInt(id)).equals(enc_old_password)){
            request.setAttribute("errorMessage", "Your Old Password doesnot Match");
            request.setAttribute("value", "");
            request.setAttribute("oneUser", user);
            RequestDispatcher dispatcher = request.getRequestDispatcher("changepassword.jsp");
            dispatcher.forward(request, response);
        
        }
        
        else if (session.getAttribute("user").equals(user.getUsername()) || current_user.getUser_role().equals("Admin")){            
            user.setUser_password(enc_new_password);
            if (this.userDAO.changePassword(user)){
                insertActivity("Password Changed", user.getUsername());
                if (current_user.getUser_role().equals("Admin")){
                    response.sendRedirect("/UserManagement/dashboard");
                    
                }
                else{
                     response.sendRedirect("/UserManagement/view?id="+id);
                }
            
            }
            else{
                request.setAttribute("errorMessage", "Password change Failed");
                request.setAttribute("value", "");
            }
        }
        else{
                response.sendRedirect("/UserManagement/accessdenied");
            }
       
        
    }
     
     /**
      * Renders Activity log of all the users 
      * Redirects to sign in page if no user is logged in.
      * Redirects to access denied page if the logged in user is not admin.
      * 
      * 
      * @param request
      * @param response
      * @throws ServletException
      * @throws IOException
      * @throws SQLException
      * @throws ClassNotFoundException 
      */
      private void activitylog(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException, ClassNotFoundException{
        HttpSession session = request.getSession();
        
        String userName = null;
        Cookie[] cookies = request.getCookies();
        if(cookies !=null){
        for(Cookie cookie : cookies){
                if(cookie.getName().equals("user")) userName = cookie.getValue();
        }
        }
        if(session.getAttribute("user") == null){
            response.sendRedirect("/UserManagement/signin");
        }
        else if (!this.userDAO.checkAdmin(userName)){
            response.sendRedirect("/UserManagement/accessdenied");
        }
        
        else{
            List<Activity> listActivity = this.activityDAO.getAllActivity();
            request.setAttribute("listActivity", listActivity);
            request.setAttribute("currentUser", userName);
            RequestDispatcher dispatcher = request.getRequestDispatcher("activitylog.jsp");
            dispatcher.forward(request, response);
        }
    
    }
      
      /**
       * Renders Page to forget password page
       * Redirects to user profile if user is already logged in
       * @param request
       * @param response
       * @throws ServletException
       * @throws IOException
       * @throws ClassNotFoundException
       * @throws SQLException
       * @throws SQLException 
       */
      private void forgotPassword(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ClassNotFoundException, SQLException, SQLException{
        HttpSession session = request.getSession();
        if(session.getAttribute("user") != null){
            User currentUser = null;
            try {
                currentUser = this.userDAO.getUserbyUsername((String) session.getAttribute("user"));
            } catch (SQLException ex) {
                Logger.getLogger(ControllerServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
            response.sendRedirect("/UserManagement/view?id="+currentUser.getUser_id());
        }
        else{
            request.setAttribute("errorMessage", "Welcome");
            request.setAttribute("value", "Hidden");
            RequestDispatcher dispatcher = request.getRequestDispatcher("forgotpassword.jsp");
            dispatcher.forward(request, response);
        }
    
    }
      
      
      /**
       * Function called after form in forgot password is submitted.
       * Checks if the email is valid
       * Renders same page with appropriate message if the email address does not exist in the databse
       * Else gets the email entered, generates a random password,
       * Hashes the random password and updates it in the database 
       * and sends the random password to user via email
       * 
       * 
       * @param request
       * @param response
       * @throws UnsupportedEncodingException
       * @throws NoSuchAlgorithmException
       * @throws ClassNotFoundException
       * @throws SQLException
       * @throws ServletException
       * @throws IOException 
       */
      private void resetPassword(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException, NoSuchAlgorithmException, ClassNotFoundException, SQLException, ServletException, IOException{
          String recipient = request.getParameter("reset_email");   
          
          String host = "smtp.gmail.com";
          String port = "587";
          String email = "ciphers.cw@gmail.com";
          String name = "Ciphers CW";
          String pass = "heraldCollege1234";
          
        String subject = "Your Password has been reset";
        
        if (this.userDAO.checkEmail(recipient)){
            String newPassword = RandomStringUtils.randomAlphanumeric(10);
            String enc_newPassword = encryptPassword(newPassword);
            
            User user = this.userDAO.getUserbyEmail(recipient);
            user.setUser_password(enc_newPassword);
            
            this.userDAO.changePassword(user);
            String content = "Hi, this is your new password: " + newPassword;
            content += "\nNote: for security reason, "
                    + "you must change your password after logging in.";


            try {
                EmailUtility.sendEmail(host, port, email, name, pass,
                        recipient, subject, content);
            } catch (MessagingException ex) {
                Logger.getLogger(ControllerServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
                request.setAttribute("errorMessage", "Your password has been reset. Please check your e-mail.");
                request.setAttribute("value", "");
                insertActivity("Password Reset", this.userDAO.getUserbyEmail(recipient).getUsername());
                RequestDispatcher dispatcher = request.getRequestDispatcher("forgotpassword.jsp");
                dispatcher.forward(request, response);
        
        }
        
        else{
            request.setAttribute("errorMessage", "Email Address Doesnot Exist, Please Enter Correct Email address");
            request.setAttribute("value", "");
            RequestDispatcher dispatcher = request.getRequestDispatcher("forgotpassword.jsp");
            dispatcher.forward(request, response);
        }
 
        
 
        
 
    
          

    }

    //Utility MEthods
      
    /**
     * utility function that inserts activity log into the data base
     * formats date and time and stores the activity one into the databse
     * 
     * @param activityName
     * @param username
     * @throws SQLException
     * @throws ClassNotFoundException 
     */
      
    private void insertActivity(String activityName, String username) throws SQLException, ClassNotFoundException{
        Date date = Calendar.getInstance().getTime();  
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");  
        String current_date = dateFormat.format(date);  
        DateFormat timeFormat = new SimpleDateFormat("hh:mm a");
        String current_time = timeFormat.format(date);
        
        Activity activity = new Activity(activityName, current_date, current_time, username);
        this.activityDAO.insertActivity(activity);
       
    
    }  
    
    
    /**
     * Function to encrypt password using SHA-1 hash
     * 
     * @param password
     * @return
     * @throws UnsupportedEncodingException
     * @throws NoSuchAlgorithmException 
     */
    private static String encryptPassword(String password) throws UnsupportedEncodingException, NoSuchAlgorithmException{
        String sha1 = "";
            MessageDigest crypt = MessageDigest.getInstance("SHA-1");
            crypt.reset();
            crypt.update(password.getBytes("UTF-8"));
            sha1 = byteToHex(crypt.digest());

        return sha1;
    }

    private static String byteToHex(final byte[] hash){
        Formatter formatter = new Formatter();
        for (byte b : hash)
        {
            formatter.format("%02x", b);
        }
        String result = formatter.toString();
        formatter.close();
        return result;
    }  

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
