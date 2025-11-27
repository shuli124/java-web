package com.test;

import com.bean.User;
import com.service.impl.UserServiceImpl;
import org.junit.Test;

import java.sql.SQLException;
import java.util.ArrayList;

public class UserServiceImplTest {
    @Test
  public void FindAllUserTest() throws SQLException, ClassNotFoundException {
        UserServiceImpl us = new UserServiceImpl();
        ArrayList<User> users = us.FindAllUser();
        for (User user : users) {
            System.out.println(user);
        }
    }
    @Test
    public void addUserTest() throws SQLException, ClassNotFoundException {
        UserServiceImpl us = new UserServiceImpl();
        User user = new User("maliu", "123321");
        us.addUser(user);
    }
}
