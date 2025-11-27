package com.test;

import com.bean.User;
import com.service.impl.UserServiceImpl;
import com.service.UserService;

import java.sql.SQLException;
import java.util.ArrayList;

public class UserServiceImplTest {
    
    public static void main(String[] args) {
        UserServiceImplTest test = new UserServiceImplTest();
        
        try {
            System.out.println("Running all tests...\n");
            
            test.testAddUser();
            System.out.println("testAddUser passed");
            
            test.testFindAllUsers();
            System.out.println("testFindAllUsers passed");
            
            test.testUpdateUser();
            System.out.println("testUpdateUser passed");
            
            test.testDeleteUser();
            System.out.println("testDeleteUser passed");
            
            test.testFindUserById();
            System.out.println("testFindUserById passed");
            
            test.testFindUserByUsername();
            System.out.println("testFindUserByUsername passed");
            
            test.testFindUsersByUsernamePattern();
            System.out.println("testFindUsersByUsernamePattern passed");
            
            test.testAddUserDuplicate();
            System.out.println("testAddUserDuplicate passed");
            
            System.out.println("\nAll tests passed!");
        } catch (Exception e) {
            System.err.println("Test failed: " + e.getMessage());
            e.printStackTrace();
        }
    }
    
    public void testFindAllUsers() throws SQLException {
        UserServiceImpl us = new UserServiceImpl();
        ArrayList<User> users = us.findAllUsers();
        if (users != null) {
            for (User user : users) {
                System.out.println(user);
            }
        }
    }
    
    public void testAddUser() throws SQLException {
        UserServiceImpl us = new UserServiceImpl();
        User user = new User("maliu", "123321");
        
        User existingUser = us.findUserByUsername("maliu");
        if (existingUser != null) {
            us.deleteUser(existingUser.getId());
        }
        
        us.addUser(user);
        User newUser = us.findUserByUsername("maliu");
        if (newUser != null) {
            System.out.println("Added user: " + newUser);
            us.deleteUser(newUser.getId());
        }
    }
    
    public void testUpdateUser() throws SQLException {
        UserServiceImpl us = new UserServiceImpl();
        User user = new User("upd_test", "pwd1");
        
        User existingUser = us.findUserByUsername("upd_test");
        if (existingUser != null) {
            us.deleteUser(existingUser.getId());
        }
        
        us.addUser(user);
        User newUser = us.findUserByUsername("upd_test");
        if (newUser != null) {
            newUser.setPassword("pwd2");
            us.updateUser(newUser);
            User updatedUser = us.findUserByUsername("upd_test");
            System.out.println("Updated user: " + updatedUser);
            us.deleteUser(newUser.getId());
        }
    }
    
    public void testDeleteUser() throws SQLException {
        UserServiceImpl us = new UserServiceImpl();
        User user = new User("del_test", "password");
        
        User existingUser = us.findUserByUsername("del_test");
        if (existingUser != null) {
            us.deleteUser(existingUser.getId());
        }
        
        us.addUser(user);
        User newUser = us.findUserByUsername("del_test");
        if (newUser != null) {
            int userId = newUser.getId();
            us.deleteUser(userId);
            User deletedUser = us.findUserById(userId);
            if (deletedUser == null) {
                System.out.println("Deleted user successfully");
            }
        }
    }
    
    public void testFindUserById() throws SQLException {
        UserServiceImpl us = new UserServiceImpl();
        User user = new User("id_test", "password");
        
        User existingUser = us.findUserByUsername("id_test");
        if (existingUser != null) {
            us.deleteUser(existingUser.getId());
        }
        
        us.addUser(user);
        User newUser = us.findUserByUsername("id_test");
        if (newUser != null) {
            User foundUser = us.findUserById(newUser.getId());
            System.out.println("Found user by ID: " + foundUser);
            us.deleteUser(newUser.getId());
        }
    }
    
    public void testFindUserByUsername() throws SQLException {
        UserServiceImpl us = new UserServiceImpl();
        User user = new User("name_test", "password");
        
        User existingUser = us.findUserByUsername("name_test");
        if (existingUser != null) {
            us.deleteUser(existingUser.getId());
        }
        
        us.addUser(user);
        User newUser = us.findUserByUsername("name_test");
        if (newUser != null) {
            System.out.println("Found user by username: " + newUser);
            us.deleteUser(newUser.getId());
        }
    }
    
    public void testFindUsersByUsernamePattern() throws SQLException {
        UserServiceImpl us = new UserServiceImpl();
        User user1 = new User("pat1", "password");
        User user2 = new User("pat2", "password");
        
        User existingUser1 = us.findUserByUsername("pat1");
        if (existingUser1 != null) {
            us.deleteUser(existingUser1.getId());
        }
        
        User existingUser2 = us.findUserByUsername("pat2");
        if (existingUser2 != null) {
            us.deleteUser(existingUser2.getId());
        }
        
        us.addUser(user1);
        us.addUser(user2);
        
        ArrayList<User> users = us.findUsersByUsernamePattern("pat");
        if (users != null) {
            System.out.println("Pattern search results:");
            for (User u : users) {
                if (u.getUsername().contains("pat")) {
                    System.out.println(u);
                }
            }
        }
        
        for (User u : users) {
            if ("pat1".equals(u.getUsername()) || "pat2".equals(u.getUsername())) {
                us.deleteUser(u.getId());
            }
        }
    }
    
    public void testAddUserDuplicate() throws SQLException {
        UserServiceImpl us = new UserServiceImpl();
        User user1 = new User("dup_test", "pwd1");
        User user2 = new User("dup_test", "pwd2");
        
        User existingUser = us.findUserByUsername("dup_test");
        if (existingUser != null) {
            us.deleteUser(existingUser.getId());
        }
        
        us.addUser(user1);
        
        try {
            us.addUser(user2);
        } catch (SQLException e) {
            System.out.println("Caught expected exception: " + e.getMessage());
        } finally {
            User newUser = us.findUserByUsername("dup_test");
            if (newUser != null) {
                us.deleteUser(newUser.getId());
            }
        }
    }
}