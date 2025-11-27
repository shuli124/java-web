package com.service;

import com.bean.User;
import java.sql.SQLException;
import java.util.ArrayList;

public interface UserService {
    void addUser(User user) throws SQLException;
    void updateUser(User user) throws SQLException;
    void deleteUser(int id) throws SQLException;
    User findUserById(int id) throws SQLException;
    ArrayList<User> findAllUsers() throws SQLException;
    User findUserByUsername(String username) throws SQLException;
    
    // 添加按用户名模糊查询的方法
    ArrayList<User> findUsersByUsernamePattern(String pattern) throws SQLException;
}