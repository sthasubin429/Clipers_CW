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
 */
public class UserDAO {
    private Connection con;
    
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
    
    public void createUser(User newUser) throws ClassNotFoundException, SQLException{
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
    
    public boolean checkAdmin(String Username) throws ClassNotFoundException, SQLException{
        User user = null;
        
        user = this.getUserbyUsername(Username);
        return user.getUser_role().equals("Admin");
        
    }
    
    public boolean checkAdminbyEmail(String email) throws ClassNotFoundException, SQLException{
        User user = null;
        
        user = this.getUserbyEmail(email);
        return user.getUser_role().equals("Admin");
    
    }
    
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
    
    
//    
//        public static void main(String [] args) throws ClassNotFoundException, SQLException{
//        UserDAO obj = new UserDAO();
//        List<User> userlist = obj.select_all();
//        
//        for(User user : userlist) {
//            System.out.println(user.getUser_id());
//            System.out.println(user.getFull_name());
//            System.out.println(user.getUsername());
//            System.out.println(user.getUser_email());
//            System.out.println(user.getUser_status());
//            System.out.println(user.getUser_password());
//            
//        }
//        obj.authenticate("subin@gmail.com", "subin");
//        
//
//    }
}
