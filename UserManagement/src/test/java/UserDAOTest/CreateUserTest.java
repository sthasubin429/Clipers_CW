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
public class CreateUserTest {
    
    UserDAO userDAO = new UserDAO();;
    
    public CreateUserTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
            
        
        
    }
    @Before
    public void setUp() {

    }
    
    @After
    public void tearDown() {
        //deletes the created user

        try {
            userDAO.deleteUser(userDAO.getUserbyUsername("createusertest").getUser_id());
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(CreateUserTest.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    @Test
    public void createUser() {
        
            
        try {
            //Sets values for new users
            String full_name = "Test User";
            String username = "createusertest";
            String user_email = "createuser@gmail.com";
            String user_password = "tuser1234";
            String created_date = "2020-06-07";
            User testUser = new User(full_name, username, user_email, user_password, created_date);
            
            //creates new user
            userDAO.createUserClient(testUser);
                
            User user = userDAO.getUserbyUsername("createusertest");
            
            //checks the created user has correct values
            assertEquals(user.getUsername(), "createusertest");
            assertEquals(user.getFull_name(), "Test User");
            assertEquals(user.getUser_email(), "createuser@gmail.com");
            assertEquals(userDAO.getPassword(user.getUser_id()), "tuser1234");
            assertEquals(user.getCreated_date(), "2020-06-07");
            assertFalse(user.getFull_name().equals("asdasd"));
            assertFalse(user.getUsername().equals("Wrong user name"));
            assertFalse(user.getUser_email().equals("wrongemail@gmail.com"));
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(CreateUserTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
            
}
