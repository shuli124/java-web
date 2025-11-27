package com.service;
import com.bean.User;

import java.sql.SQLException;
import java.util.ArrayList;

public interface UserService {
    void addUser(User user) throws SQLException, ClassNotFoundException;
    ArrayList<User> FindAllUser() throws SQLException, ClassNotFoundException;
}
