package com.utils;

import com.bean.User;
import com.mysql.jdbc.Driver;

import java.sql.*;
import java.util.ArrayList;

public class JDBCUtils {
    public static Connection con;
    public static Connection getConnection() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.jdbc.Driver");
        String url="jdbc:mysql://localhost:3306/jin1";
        String username="root";
        String password="123456";
        con = DriverManager.getConnection(url, username, password);
        return con;
    }
    public static void close(Connection con, PreparedStatement ps, ResultSet rs) throws SQLException {
        if(con!=null){
            con.close();
            con = null;
        } else if(ps!=null){
            ps.close();
            ps = null;
        }else if(rs!=null){
            rs.close();
            rs = null;
        }
    }
}
