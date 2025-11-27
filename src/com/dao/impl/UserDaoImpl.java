package com.dao.impl;

import com.bean.User;
import com.dao.UserDao;
import com.utils.JDBCUtils;

import java.sql.*;
import java.util.ArrayList;

public class UserDaoImpl implements UserDao {
    @Override
    public void insert(User user) throws SQLException {
        Connection con = null;
        PreparedStatement ps = null;
        try {
            con = JDBCUtils.getConnection();
            String sql = "INSERT INTO user(username, password) VALUES(?, ?)";
            ps = con.prepareStatement(sql);
            ps.setString(1, user.getUsername());
            ps.setString(2, user.getPassword());
            ps.executeUpdate();
        } finally {
            JDBCUtils.close(con, ps, null);
        }
    }

    @Override
    public void update(User user) throws SQLException {
        Connection con = null;
        PreparedStatement ps = null;
        try {
            con = JDBCUtils.getConnection();
            String sql = "UPDATE user SET username=?, password=? WHERE id=?";
            ps = con.prepareStatement(sql);
            ps.setString(1, user.getUsername());
            ps.setString(2, user.getPassword());
            ps.setInt(3, user.getId());
            ps.executeUpdate();
        } finally {
            JDBCUtils.close(con, ps, null);
        }
    }

    @Override
    public void delete(int id) throws SQLException {
        Connection con = null;
        PreparedStatement ps = null;
        try {
            con = JDBCUtils.getConnection();
            String sql = "DELETE FROM user WHERE id=?";
            ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            ps.executeUpdate();
        } finally {
            JDBCUtils.close(con, ps, null);
        }
    }

    @Override
    public User selectById(int id) throws SQLException {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            con = JDBCUtils.getConnection();
            String sql = "SELECT * FROM user WHERE id=?";
            ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            if (rs.next()) {
                String username = rs.getString("username");
                String password = rs.getString("password");
                return new User(id, username, password);
            }
            return null;
        } finally {
            JDBCUtils.close(con, ps, rs);
        }
    }

    @Override
    public ArrayList<User> selectAll() throws SQLException {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            con = JDBCUtils.getConnection();
            String sql = "SELECT * FROM user";
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            ArrayList<User> users = new ArrayList<>();
            while (rs.next()) {
                int id = rs.getInt("id");
                String username = rs.getString("username");
                String password = rs.getString("password");
                User user = new User(id, username, password);
                users.add(user);
            }
            return users;
        } finally {
            JDBCUtils.close(con, ps, rs);
        }
    }

    @Override
    public User selectByUsername(String username) throws SQLException {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            con = JDBCUtils.getConnection();
            String sql = "SELECT * FROM user WHERE username=?";
            ps = con.prepareStatement(sql);
            ps.setString(1, username);
            rs = ps.executeQuery();
            if (rs.next()) {
                int id = rs.getInt("id");
                String password = rs.getString("password");
                return new User(id, username, password);
            }
            return null;
        } finally {
            JDBCUtils.close(con, ps, rs);
        }
    }
    
    @Override
    public ArrayList<User> selectByUsernamePattern(String pattern) throws SQLException {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            con = JDBCUtils.getConnection();
            String sql = "SELECT * FROM user WHERE username LIKE ?";
            ps = con.prepareStatement(sql);
            ps.setString(1, "%" + pattern + "%");
            rs = ps.executeQuery();
            ArrayList<User> users = new ArrayList<>();
            while (rs.next()) {
                int id = rs.getInt("id");
                String username = rs.getString("username");
                String password = rs.getString("password");
                User user = new User(id, username, password);
                users.add(user);
            }
            return users;
        } finally {
            JDBCUtils.close(con, ps, rs);
        }
    }
}