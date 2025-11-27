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
    public void addUser(User user) throws SQLException {
        // 检查用户名是否已存在
        User existingUser = userDao.selectByUsername(user.getUsername());
        if (existingUser != null) {
            throw new SQLException("用户名 '" + user.getUsername() + "' 已存在，请选择其他用户名");
        }
        userDao.insert(user);
    }

    @Override
    public void updateUser(User user) throws SQLException {
        userDao.update(user);
    }

    @Override
    public void deleteUser(int id) throws SQLException {
        userDao.delete(id);
    }

    @Override
    public User findUserById(int id) throws SQLException {
        return userDao.selectById(id);
    }

    @Override
    public ArrayList<User> findAllUsers() throws SQLException {
        return userDao.selectAll();
    }

    @Override
    public User findUserByUsername(String username) throws SQLException {
        return userDao.selectByUsername(username);
    }
    
    @Override
    public ArrayList<User> findUsersByUsernamePattern(String pattern) throws SQLException {
        return userDao.selectByUsernamePattern(pattern);
    }
}