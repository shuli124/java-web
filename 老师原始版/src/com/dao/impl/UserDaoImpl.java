package com.dao.impl;

import com.bean.User;
import com.dao.UserDao;
import com.utils.JDBCUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class UserDaoImpl implements UserDao {
    private Connection con;
    private String sql;
    private PreparedStatement ps;
    private ResultSet rs;
    @Override
    public void insert(User user) throws SQLException, ClassNotFoundException {
        con = JDBCUtils.getConnection();
        sql = "insert into user(username,password) values(?,?)";
        ps = con.prepareStatement(sql);
        ps.setString(1,user.getUsername());
        ps.setString(2,user.getPassword());
        ps.executeUpdate();
        JDBCUtils.close(con,ps,null);
    }

    @Override
    public void delete(int id) {

    }

    @Override
    public void update(User user) {

    }

    @Override
    public ArrayList<User> selectAll() throws SQLException, ClassNotFoundException {
        con = JDBCUtils.getConnection();
        sql = "select * from user";
        ps = con.prepareStatement(sql);
        rs = ps.executeQuery();
        ArrayList<User> users = new ArrayList<>();
        while (rs.next()){
            int id = rs.getInt("id");
            String username = rs.getString("username");
            String password = rs.getString("password");
            User user = new User(id, username, password);
            users.add(user);
        }
        return users;
    }

    @Override
    public User selectById(int id) {
        return null;
    }

    @Override
    public ArrayList<User> selectByName(String name) {
        return null;
    }
}
