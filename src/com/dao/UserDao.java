package com.dao;

import com.bean.User;

import java.sql.SQLException;
import java.util.ArrayList;

public interface UserDao {
    void insert(User user) throws SQLException;
    void update(User user) throws SQLException;
    void delete(int id) throws SQLException;
    User selectById(int id) throws SQLException;
    ArrayList<User> selectAll() throws SQLException;
    User selectByUsername(String username) throws SQLException;
    
    // 添加按用户名模糊查询的方法
    ArrayList<User> selectByUsernamePattern(String pattern) throws SQLException;
}