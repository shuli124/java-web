package com.service.impl;

import com.bean.User;
import com.dao.UserDao;
import com.dao.impl.UserDaoImpl;
import com.service.UserService;

import java.sql.SQLException;
import java.util.ArrayList;

public class UserServiceImpl implements UserService {
    private UserDao userDao = new UserDaoImpl();
    @Override
    public void addUser(User user) throws SQLException, ClassNotFoundException {
        userDao.insert(user);
    }

    @Override
    public ArrayList<User> FindAllUser() throws SQLException, ClassNotFoundException {
        return userDao.selectAll();
    }
}
