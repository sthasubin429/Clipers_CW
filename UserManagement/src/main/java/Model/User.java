/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

/**
 *
 * @author Subin
 */
public class User {
    private int user_id;
    private String full_name;
    private String username;
    private String user_email;
    private String user_password;
    private String user_status;
    private String created_date;
    private String user_role;

    public User(int user_id, String full_name, String username, String user_email, String user_password, String user_status, String created_date, String user_role) {
        this.user_id = user_id;
        this.full_name = full_name;
        this.username = username;
        this.user_email = user_email;
        this.user_password = user_password;
        this.user_status = user_status;
        this.created_date = created_date;
        this.user_role = user_role;
    }

    public User(int user_id, String full_name, String username, String user_email, String user_status, String created_date, String user_role) {
        this.user_id = user_id;
        this.full_name = full_name;
        this.username = username;
        this.user_email = user_email;
        this.user_status = user_status;
        this.created_date = created_date;
        this.user_role = user_role;
    }

    public User(int user_id, String full_name, String username, String user_email) {
        this.user_id = user_id;
        this.full_name = full_name;
        this.username = username;
        this.user_email = user_email;
    }
    
    

    public User(String full_name, String username, String user_email, String user_password, String created_date) {
        this.full_name = full_name;
        this.username = username;
        this.user_email = user_email;
        this.user_password = user_password;
        this.created_date = created_date;
    }

    public User(String full_name, String username, String user_email, String user_password, String created_date, String user_role) {
        this.full_name = full_name;
        this.username = username;
        this.user_email = user_email;
        this.user_password = user_password;
        this.created_date = created_date;
        this.user_role = user_role;
    }
    
    
    
    
    
    

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getFull_name() {
        return full_name;
    }

    public void setFull_name(String full_name) {
        this.full_name = full_name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUser_email() {
        return user_email;
    }

    public void setUser_email(String user_email) {
        this.user_email = user_email;
    }

    public String getUser_password() {
        return user_password;
    }

    public void setUser_password(String user_password) {
        this.user_password = user_password;
    }

    public String getUser_status() {
        return user_status;
    }

    public void setUser_status(String user_status) {
        this.user_status = user_status;
    }

    public String getCreated_date() {
        return created_date;
    }

    public void setCreated_date(String created_date) {
        this.created_date = created_date;
    }

    public String getUser_role() {
        return user_role;
    }

    public void setUser_role(String user_role) {
        this.user_role = user_role;
    }
    
}
