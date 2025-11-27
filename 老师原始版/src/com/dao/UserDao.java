package com.dao;

import com.bean.User;

import java.sql.SQLException;
import java.util.ArrayList;

public interface UserDao {
    void insert(User user) throws SQLException, ClassNotFoundException;
    void delete(int id);
    void update(User user);
    ArrayList<User> selectAll() throws SQLException, ClassNotFoundException;
    User selectById(int id);
    ArrayList<User> selectByName(String name);
}
