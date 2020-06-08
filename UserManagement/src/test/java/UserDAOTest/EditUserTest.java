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
public class EditUserTest {
    UserDAO userDAO = new UserDAO();
    
    public EditUserTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        //Creates a new user in the database for testing
        try {
            String full_name = "Test User";
            String username = "editUserTest";
            String user_email = "editUserTest@gmail.com";
            String user_password = "tuser1234";
            String created_date = "2020-06-07";
            User testUser = new User(full_name, username, user_email, user_password, created_date);
            
            userDAO.createUserClient(testUser);
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(EditUserTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @After
    public void tearDown() {
        //Deletes the user created after the test is completed
        try {
            userDAO.deleteUser(userDAO.getUserbyUsername("newUsername").getUser_id());
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
    public void editUser(){
        try {
            
            User user = userDAO.getUserbyUsername("editUserTest");
            
            //checks if the created user has correct values
            assertEquals(user.getUsername(), "editUserTest");
            assertEquals(user.getFull_name(), "Test User");
            assertEquals(user.getUser_email(), "editUserTest@gmail.com");
            assertEquals(userDAO.getPassword(user.getUser_id()), "tuser1234");
            assertEquals(user.getCreated_date(), "2020-06-07");
            
            assertFalse(user.getFull_name().equals("asdasd"));
            assertFalse(user.getUsername().equals("Wrong user name"));
            assertFalse(user.getUser_email().equals("wrongemail@gmail.com"));
            
            
            //set values to be edited
            user.setUser_email("newemail@gmail.com");
            user.setUsername("newUsername");
            user.setFull_name("New Full Name");
            
            //edites the user in datase
            userDAO.updateUser(user);
            
            
            //checks if the update was sucessful or not
            assertEquals(user.getUsername(), "newUsername");
            assertEquals(user.getFull_name(), "New Full Name");
            assertEquals(user.getUser_email(), "newemail@gmail.com");
            assertEquals(userDAO.getPassword(user.getUser_id()), "tuser1234");
            assertEquals(user.getCreated_date(), "2020-06-07");
            
            assertFalse(user.getFull_name().equals("Test User"));
            assertFalse(user.getUsername().equals("editUserTest"));
            assertFalse(user.getUser_email().equals("editUserTest@gmail.com"));  
            
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(EditUserTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
}
