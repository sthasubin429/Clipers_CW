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
public class DeleteUserTest {
    UserDAO userDAO = new UserDAO();
    public DeleteUserTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        //Creates new user for testing
        try {
            String full_name = "Test User";
            String username = "deleteTestUser";
            String user_email = "deletetestuser@gmail.com";
            String user_password = "tuser1234";
            String created_date = "2020-06-07";
            User testUser = new User(full_name, username, user_email, user_password, created_date);
            
            userDAO.createUserClient(testUser);
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(DeleteUserTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @After
    public void tearDown() {
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
    
    @Test
    public void deleteUser(){
        try {
            
            //checks if the user exists in the database
            assertTrue(userDAO.checkEmail("deletetestuser@gmail.com"));
            assertTrue(userDAO.checkUsername("deleteTestUser"));
            assertTrue(userDAO.authenticate("deletetestuser@gmail.com", "tuser1234"));
            
            //deletes the user from the database
            userDAO.deleteUser(userDAO.getUserbyUsername("deleteTestUser").getUser_id());
            
            //checks if the user still exists in the datase
            assertFalse(userDAO.checkEmail("deleteTestUser"));
            assertFalse(userDAO.checkUsername("deletetestuser@gmail.com"));
            
            
            
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(DeleteUserTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}