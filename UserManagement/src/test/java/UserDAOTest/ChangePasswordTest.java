/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UserDAOTest;

import Dao.UserDAO;
import Model.User;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Subin
 */
public class ChangePasswordTest {
    UserDAO userDAO = new UserDAO();
    
    public ChangePasswordTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        //creates new user for testing

        try {
            String full_name = "Test User";
            String username = "changePasswordTest";
            String user_email = "changepasswordtest@gmail.com";
            String user_password = "oldpassword123";
            String created_date = "2020-06-07";
            User testUser = new User(full_name, username, user_email, user_password, created_date);
            
            userDAO.createUser(testUser);
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(ChangePasswordTest.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    
    @After
    public void tearDown() {
        //deletes the user after testing
        try {
            userDAO.deleteUser(userDAO.getUserbyUsername("changePasswordTest").getUser_id());
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(ChangePasswordTest.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
    
    @Test
    public void changePassword(){
        try {
            //gets user from databse
            User user = userDAO.getUserbyUsername("changePasswordTest");
            
            //checks if the user password with old credentials
            assertEquals(userDAO.getPassword(user.getUser_id()), "oldpassword123");            
            assertFalse(userDAO.getPassword(user.getUser_id()).equals("wrongpassword123"));
            
            //changes the user password
            user.setUser_password("newpass123456");
            userDAO.changePassword(user);
            
            //checks it the user has new password
            assertEquals(userDAO.getPassword(user.getUser_id()), "newpass123456");
            assertFalse(userDAO.getPassword(user.getUser_id()).equals("oldpassword123"));
            
            
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(ChangePasswordTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}