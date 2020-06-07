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
public class LoginTest {
    
    UserDAO userDAO = new UserDAO();
    
    public 
        LoginTest() {
        
    }
    
    @BeforeClass
    public static void setUpClass() {

    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        //Set up Creates a new user in the database for the test
        try {
            String full_name = "Login User";
            String username = "loginUserTest";
            String user_email = "loginuser@gmail.com";
            String user_password = "tuser1234";
            String created_date = "2020-06-07";
            User testUser = new User(full_name, username, user_email, user_password, created_date);
            
            userDAO.createUser(testUser);
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(EditUserTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @After
    public void tearDown() {
        //delets the created user after the test is completed
        try {
            userDAO.deleteUser(userDAO.getUserbyUsername("loginUserTest").getUser_id());
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(EditUserTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
    
    @Test
    public void validLoginTest(){
            
        try {
            assertTrue(userDAO.checkEmail("loginuser@gmail.com"));
            assertTrue(userDAO.checkUsername("loginUserTest"));
            assertTrue(userDAO.authenticate("loginuser@gmail.com", "tuser1234"));
            
            //invalid password test
            assertFalse(userDAO.authenticate("loginuser@gmail.com", "Wrongpassword"));
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(LoginTest.class.getName()).log(Level.SEVERE, null, ex);
        }
            
    }
   
    @Test
    public void invalidEmailLoginTest(){
        try {
            assertFalse(userDAO.checkEmail("wrongEmail@gmail.com"));
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(LoginTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    @Test
    public void blankEmailLoginTest(){
        try {
            assertFalse(userDAO.checkEmail(""));
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(LoginTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    @Test
    public void invalidUsernameLoginTest(){
        try {
            assertFalse(userDAO.checkUsername("wrongUsername"));
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(LoginTest.class.getName()).log(Level.SEVERE, null, ex);
        }

        
    }
    
    @Test
    public void blankUsernameLoginTest(){

        try {
            assertFalse(userDAO.checkUsername(""));
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(LoginTest.class.getName()).log(Level.SEVERE, null, ex);
        }

        
    }
}

