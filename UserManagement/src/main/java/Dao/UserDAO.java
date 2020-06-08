    /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import Model.User;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 *
 * @author Subin
 * 
 * 
 * 
 * Contains all the queries with users table of the database
 */
public class UserDAO {
    private Connection con;
    
    
    /*
    Parameters: None
    Return Value: Void
    Establishes Connection with database
    
    */
    /**
     * 
     * @throws ClassNotFoundException
     * @throws SQLException 
     */
    public void connect() throws ClassNotFoundException, SQLException{
        
        try {
            //takes driver for connection
            Class.forName("com.mysql.jdbc.Driver");
            //Creating connection with mysql
            String url="localhost:3306";
            String db="user_management";
            String username="root";
            String password="";
            Connection conn = DriverManager.getConnection(
                    "jdbc:mysql://"+url+"/"+db, username, password);
            this.con = conn;
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println(e);
            throw new SQLException(e);
        }
    }
    
    /*
    Parameters:None
    Return Value: List of objects of Model Class User.
    Queries the data base and selects all the rows.
    Makes all the selected rows into the objects of Model class User.
    Returns the list of Model Class
    */
    /**
     * 
     * @return
     * @throws ClassNotFoundException
     * @throws SQLException 
     */
    public List<User> select_all() throws ClassNotFoundException, SQLException{
        List<User> listUser = new ArrayList<>();
        connect();
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("select * from users");
            
            while (rs.next()) {
                int user_id = rs.getInt("user_id");
                String full_name = rs.getString("full_name");
                String username = rs.getString("username");
                String user_email = rs.getString("user_email");
                String user_status = rs.getString("user_status");
                String created_date = rs.getString("created_date");
                String user_role = rs.getString("user_role");

                User user = new User(user_id, full_name, username, user_email, user_status, created_date, user_role);
                listUser.add(user);
            }
            stmt.close();
            rs.close();
            this.con.close();
        return listUser;
    }
    
    
    /*
    Parameters:Object of User Type
    Return Value:Void
    Takes in the object of User type. 
    Gets all the required values from the User objects.
    Gets the max user id from the database, adds one to it and makes it the new user id.
    Queries the database and inserts the new user into the database.
    Sets default value of user role as client.
    */
    /**
     * 
     * @param newUser
     * @throws ClassNotFoundException
     * @throws SQLException 
     */
    public void createUserClient(User newUser) throws ClassNotFoundException, SQLException{
        connect();
        String sql = "INSERT INTO users (user_id, full_name, username, user_email, user_password, created_date) VALUES (?, ?, ?, ?, ?, ?)";
        PreparedStatement statement;
        statement = this.con.prepareStatement(sql);
        
        Statement stmt = con.createStatement();
        //Retrieving the data
        ResultSet rs = stmt.executeQuery("select max(user_id) as max from users");
        rs.next();
        //Moving the cursor to the last row
        
        statement.setInt(1,rs.getInt("max")+1);
        statement.setString(2, newUser.getFull_name());
        statement.setString(3, newUser.getUsername());
        statement.setString(4, newUser.getUser_email());
        statement.setString(5, newUser.getUser_password());
        statement.setString(6, newUser.getCreated_date());
        statement.executeUpdate();
        statement.close();
        this.con.close();
    }
    
    
    /*
    Parameters:Object of User Type
    Return Value:Void
    Takes in the object of User type. 
    Gets all the required values from the User objects.
    Gets the max user id from the database, adds one to it and makes it the new user id.
    Queries the database and inserts the new user into the database.
    Sets value of user role as admin or user depending upon the paramerter
    */
    /**
     * 
     * @param newUser
     * @throws ClassNotFoundException
     * @throws SQLException 
     */
    public void createUserAdmin(User newUser) throws ClassNotFoundException, SQLException{
        connect();
        String sql = "INSERT INTO users (user_id, full_name, username, user_email, user_password, created_date, user_role) VALUES (?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement statement;
        statement = this.con.prepareStatement(sql);
        
        Statement stmt = con.createStatement();
        //Retrieving the data
        ResultSet rs = stmt.executeQuery("select max(user_id) as max from users");
        rs.next();
        //Moving the cursor to the last row
        
        statement.setInt(1,rs.getInt("max")+1);
        statement.setString(2, newUser.getFull_name());
        statement.setString(3, newUser.getUsername());
        statement.setString(4, newUser.getUser_email());
        statement.setString(5, newUser.getUser_password());
        statement.setString(6, newUser.getCreated_date());
        statement.setString(7, newUser.getUser_role());
        statement.executeUpdate();
        statement.close();
        this.con.close();
    }
    
    /*
    Parameters: two Strings, one as email and other as passwrod
    Return Value: Boolean
    Queries the database and gets the user that matches the input email.
    Checks if the password associated with the given email in the database matches the password in the input
    Returns true if the passwords match and false otherwise.
    */
    /** 
     *@param email
     * @param password
     * @return
     * @throws ClassNotFoundException
     * @throws SQLException 
     */
    public boolean authenticate(String email, String password) throws ClassNotFoundException, SQLException{
        
        String sql = "SELECT user_email, user_password FROM `users` WHERE user_email = ? ";
        connect();
        
        PreparedStatement statement = this.con.prepareStatement(sql);
        statement.setString(1, email);
         
        ResultSet resultSet = statement.executeQuery();
        String db_email = null;
        String db_password = null;
        
        if (resultSet.next()) {
            
            db_email = resultSet.getString("user_email");
            
            db_password = resultSet.getString("user_password");
        }
        
        return password.compareTo(db_password) == 0;
        
    }
    
    /*
    Parameters:Object of User Type
    Return Value: Boolean
    Queries the database and updates full name, username and email as per the parameter.
    Returns true if the query runs sucessfully and false otherwise
    */
    /**
     * 
     * @param userUpdate
     * @return 
     */
    public boolean updateUser(User userUpdate){
        try {
            connect();
            String sql = "UPDATE users SET full_name = ?, username = ?, user_email = ? WHERE user_id = ? ";
            PreparedStatement statement;
            statement = this.con.prepareStatement(sql);
            
            statement.setString(1, userUpdate.getFull_name());
            statement.setString(2, userUpdate.getUsername());
            statement.setString(3, userUpdate.getUser_email());
            statement.setInt(4, userUpdate.getUser_id());
            statement.executeUpdate();
            statement.close();
            this.con.close();
            return true;
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
       
    }
    
    /*
    Parameters:integer
    Return Value:void
    queries the database and deletes the row associated with the given id
    */
    /**
     * 
     * @param id 
     */
    public void deleteUser(int id){
        try {
            User user = null;
            String sql = "DELETE FROM users WHERE user_id = ?";
            connect();   
            PreparedStatement statement = con.prepareStatement(sql);
            statement.setInt(1, id);
            statement.executeUpdate();
            statement.close();
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /*
    Parameters:String
    Return Value:boolean
    Uses the predefied select all method to get all the users in the databse.
    loops through all the users and checks if the email already exists in the database.
    Returns true if found and false otherwise.
    */
    /**
     * 
     * @param email
     * @return
     * @throws ClassNotFoundException
     * @throws SQLException 
     */
    public boolean checkEmail(String email) throws ClassNotFoundException, SQLException{
        UserDAO obj = new UserDAO();
        List<User> userlist = obj.select_all();
        int found = 0;
        for(User user : userlist) {
            if (email.compareTo(user.getUser_email()) == 0){
                found = 1;
                break;
            }
        }      
        
        return found != 0;
    }
    
    /*
    Parameters:String
    Return Value:boolean
    Uses the predefied select all method to get all the users in the databse.
    loops through all the users and checks if the username already exists in the database.
    Returns true if found and false otherwise.
    */
    /**
     * 
     * @param username
     * @return
     * @throws ClassNotFoundException
     * @throws SQLException 
     */
    public boolean checkUsername(String username) throws ClassNotFoundException, SQLException{
        UserDAO obj = new UserDAO();
        List<User> userlist = obj.select_all();
        int found = 0;
        for(User user : userlist) {
            if (username.compareTo(user.getUsername()) == 0){
                found = 1;
                break;
            }
        }      
        
        return found != 0;
    }
    
    /**
     * Uses the predefined select all method to get all the users form the database.
     * loops though all the users and checks if the username already exists in the database.
     * returns true if found and false otherwise
     * @param id
     * @return
     * @throws ClassNotFoundException
     * @throws SQLException 
     */
    public boolean checkID(int id) throws ClassNotFoundException, SQLException{
        UserDAO obj = new UserDAO();
        List<User> userlist = obj.select_all();
        int found = 0;
        for(User user : userlist) {
            if (id == user.getUser_id()){
                found = 1;
                break;
            }
        }      
        
        return found != 0;
    }
    
    /*
    Parameters:integer as id and String as email
    Return Value:boolean
    Queries the database and selects all the users except the given email
    Returns true if found and false otherwise
    */
    /**
     * 
     * @param id
     * @param email
     * @return
     * @throws ClassNotFoundException
     * @throws SQLException 
     */
    public boolean editCheckEmail(int id, String email) throws ClassNotFoundException, SQLException{
        connect();
        String sql = "SELECT user_email FROM `users` WHERE user_id not in (?)";
        
        PreparedStatement statement = this.con.prepareStatement(sql);
        statement.setInt(1, id);
         
        ResultSet rs = statement.executeQuery();
                  
        while (rs.next()) {
            String db_email = rs.getString("user_email");
            if (db_email.equals(email)){
                return true;
            }
        }
            rs.close();
            this.con.close();
        
        return false;
    }
    
    /*
    Parameters:integer as id and String as username
    Return Value:boolean
    Queries the database and selects all the users except the given username
    Returns true if found and false otherwise
    */
    /**
     * 
     * @param id
     * @param username
     * @return
     * @throws ClassNotFoundException
     * @throws SQLException 
     */
    public boolean editCheckUsername(int id, String username) throws ClassNotFoundException, SQLException{
        connect();
        String sql = "SELECT username FROM `users` WHERE user_id not in (?)";
        
        PreparedStatement statement = this.con.prepareStatement(sql);
        statement.setInt(1, id);
         
        ResultSet rs = statement.executeQuery();
        
        while (rs.next()) {
            String db_username = rs.getString("username");
            if (db_username.equals(username)){
                return true;
            }
        }
            rs.close();
            this.con.close();
        
        return false;
    }
    

    /*
    Parameters:integer as id
    Return Value: object of user type
    queries the database and returs the user associated with the gived userid
    */
    /**
     * 
     * @param id
     * @return
     * @throws ClassNotFoundException
     * @throws SQLException 
     */
    public User getUserbyID(int id) throws ClassNotFoundException, SQLException{
        User user = null;
        String sql = "SELECT * FROM users WHERE user_id = ?";
         
        connect();
         
        PreparedStatement statement = con.prepareStatement(sql);
        statement.setInt(1, id);
         
        ResultSet rs = statement.executeQuery();
         
        if (rs.next()) {
            int user_id = rs.getInt("user_id");
                String full_name = rs.getString("full_name");
                String username = rs.getString("username");
                String user_email = rs.getString("user_email");
                String user_status = rs.getString("user_status");
                String created_date = rs.getString("created_date");
                String user_role = rs.getString("user_role");
                user = new User(user_id,full_name, username, user_email, user_status, created_date, user_role);
                
        }
         
        rs.close();
        statement.close();
         
        return user;
    
    }
    /*
    Parameters:String as email
    Return Value: object of user type
    queries the database and returs the user associated with the gived email
    */
    /**
     * 
     * @param email
     * @return
     * @throws ClassNotFoundException
     * @throws SQLException 
     */
    public User getUserbyEmail(String email) throws ClassNotFoundException, SQLException{
        User user = null;
        String sql = "SELECT * FROM users WHERE user_email = ?";
         
        connect();
         
        PreparedStatement statement = con.prepareStatement(sql);
        statement.setString(1, email);
         
        ResultSet rs = statement.executeQuery();
         
        if (rs.next()) {
            int user_id = rs.getInt("user_id");
                String full_name = rs.getString("full_name");
                String username = rs.getString("username");
                String user_email = rs.getString("user_email");
                String user_status = rs.getString("user_status");
                String created_date = rs.getString("created_date");
                String user_role = rs.getString("user_role");
                user = new User(user_id,full_name, username, user_email, user_status, created_date,user_role);
                
        }
         
        rs.close();
        statement.close();
         
        return user;
    
    }
    

    /*
    Parameters:String as email
    Return Value: object of user type
    queries the database and returs the user associated with the gived email
    */
    /**
     * 
     * @param Username
     * @return
     * @throws ClassNotFoundException
     * @throws SQLException 
     */
    public User getUserbyUsername(String Username) throws ClassNotFoundException, SQLException{
        User user = null;
        String sql = "SELECT * FROM users WHERE username = ?";
         
        connect();
         
        PreparedStatement statement = con.prepareStatement(sql);
        statement.setString(1, Username);
         
        ResultSet rs = statement.executeQuery();
         
        if (rs.next()) {
            int user_id = rs.getInt("user_id");
                String full_name = rs.getString("full_name");
                String username = rs.getString("username");
                String user_email = rs.getString("user_email");
                String user_status = rs.getString("user_status");
                String created_date = rs.getString("created_date");
                String user_role = rs.getString("user_role");
                user = new User(user_id,full_name, username, user_email, user_status, created_date,user_role);
                
        }
        
         
        rs.close();
        statement.close();
         
        return user;
    
    }
    
    /*
    Parameters:String as username
    Return Value:boolean
    checks weather user with the given username is admine or not
    */
    /**
     * 
     * @param Username
     * @return
     * @throws ClassNotFoundException
     * @throws SQLException 
     */
    public boolean checkAdmin(String Username) throws ClassNotFoundException, SQLException{
        User user = null;
        
        user = this.getUserbyUsername(Username);
        return user.getUser_role().equals("Admin");
        
    }
    
    /*
    Parameters:String as email
    Return Value:boolean
    Checks weather user with the given email is admin or not
    */
    /**
     * 
     * @param email
     * @return
     * @throws ClassNotFoundException
     * @throws SQLException 
     */
    public boolean checkAdminbyEmail(String email) throws ClassNotFoundException, SQLException{
        User user = null;
        
        user = this.getUserbyEmail(email);
        return user.getUser_role().equals("Admin");
    
    }
    
    
    /*
    Parameters:int as id
    Return Value:String
    Queries the database and selects the user associated with the given id
    returns the passoword of the user
    */
    /**
     * 
     * @param id
     * @return
     * @throws ClassNotFoundException
     * @throws SQLException 
     */
    public String getPassword(int id) throws ClassNotFoundException, SQLException{
        String sql = "SELECT user_password FROM users WHERE user_id = ?";
        String password = null;
         
        connect();
         
        PreparedStatement statement = con.prepareStatement(sql);
        
        statement.setInt(1, id);
         
        ResultSet rs = statement.executeQuery();
        
        if (rs.next()) {
            password = rs.getString("user_password");
                
        }
         
        
   
        rs.close();
        statement.close();
         
        return password;
    }
    
    /*
    Parameters:Object of user Type
    Return Value:boolean
    Queries the database and updates the password of the given user
    returns true if sucessful.
    */
    /**
     * 
     * @param userPasswordChange
     * @return
     * @throws ClassNotFoundException
     * @throws SQLException 
     */
    public boolean changePassword(User userPasswordChange) throws ClassNotFoundException, SQLException{
        
    try {
            connect();
            String sql = "UPDATE users SET user_password = ? WHERE user_id = ? ";
            PreparedStatement statement;
            statement = this.con.prepareStatement(sql);
            
            statement.setString(1, userPasswordChange.getUser_password());
            statement.setInt(2, userPasswordChange.getUser_id());
            statement.executeUpdate();
            
            statement.close();
            this.con.close();
            return true;
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
    
    /**
     * Queries database and returns user created with in the given date range
     * 
     * @param startDate
     * @param endDate
     * @return
     * @throws SQLException
     * @throws ClassNotFoundException 
     */
    public List<User> report(String startDate, String endDate) throws SQLException, ClassNotFoundException{
        List<User> listUser = new ArrayList<>();
        connect();
        String sql = "SELECT * FROM users WHERE created_date >= ? AND created_date <= ? ";
        PreparedStatement statement = this.con.prepareStatement(sql);
            statement.setString(1, startDate);
            statement.setString(2, endDate);
         
        ResultSet rs = statement.executeQuery();
            
            while (rs.next()) {
                int user_id = rs.getInt("user_id");
                String full_name = rs.getString("full_name");
                String username = rs.getString("username");
                String user_email = rs.getString("user_email");
                String user_status = rs.getString("user_status");
                String created_date = rs.getString("created_date");
                String user_role = rs.getString("user_role");

                User user = new User(user_id, full_name, username, user_email, user_status, created_date, user_role);
                listUser.add(user);
            }
            statement.close();
            rs.close();
            this.con.close();
        return listUser;
        
    }
    
}
