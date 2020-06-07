/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import Model.Activity;
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
public class ActivityDAO {
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
    
    public List<Activity> getAllActivity() throws ClassNotFoundException, SQLException{
        List<Activity> activityList = new ArrayList<>();
        connect();
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("select * from activitylog Order by date DESC");
            
            while (rs.next()) {
                String act = rs.getString("activity");
                String date = rs.getString("date");
                String time = rs.getString("time");
                String username = rs.getString("username");

                Activity activity= new Activity(act, date, time, username);
                
                activityList.add(activity);
            }
            stmt.close();
            rs.close();
            this.con.close();
        return activityList;
    }
    
    public void insertActivity(Activity activity) throws SQLException, ClassNotFoundException{
        connect();
        String sql = "INSERT INTO activitylog (activity, date, time, username) VALUES (?, ?, ?, ?)";
        PreparedStatement statement;
        statement = this.con.prepareStatement(sql);
        
        statement.setString(1, activity.getActivity());
        statement.setString(2, activity.getDate());
        statement.setString(3, activity.getTime());
        statement.setString(4, activity.getUsername());
        
        statement.executeUpdate();
        statement.close();
        this.con.close();
    }
    
    public List<Activity> getActivityByUsername(String current_username) throws ClassNotFoundException, SQLException{        
        
        List<Activity> activityList = new ArrayList<>();
        String sql = "SELECT * FROM `activitylog` WHERE username = ? ORDER BY date DESC";
        connect();
        
        PreparedStatement statement = this.con.prepareStatement(sql);
        statement.setString(1, current_username);
         
        ResultSet rs = statement.executeQuery();
            
            while (rs.next()) {
                String act = rs.getString("activity");
                String date = rs.getString("date");
                String time = rs.getString("time");
                String username = rs.getString("username");
                
                System.out.println(act);
                System.out.println(date);
                System.out.println(time);
                System.out.println(username);

                Activity activity= new Activity(act, date, time, username);
                
                activityList.add(activity);
            }
            statement.close();
            rs.close();
            this.con.close();
        return activityList;
    }
    
    public static void main(String [] args){
        ActivityDAO obj = new ActivityDAO();
        try {
            obj.getAllActivity();
            
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(ActivityDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            obj.getActivityByUsername("sandip1");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ActivityDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(ActivityDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
}
