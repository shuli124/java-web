package com.servlet;

import com.bean.User;
import com.service.UserService;
import com.service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

@WebServlet("/user")
public class UserServlet extends HttpServlet {
    private UserService userService = new UserServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        
        try {
            if ("list".equals(action)) {
                listUsers(req, resp);
            } else if ("edit".equals(action)) {
                showEditForm(req, resp);
            } else if ("delete".equals(action)) {
                deleteUser(req, resp);
            } else if ("search".equals(action)) {
                searchUsers(req, resp);
            } else {
                listUsers(req, resp);
            }
        } catch (SQLException e) {
            throw new ServletException(e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        String action = req.getParameter("action");
        
        try {
            if ("insert".equals(action)) {
                insertUser(req, resp);
            } else if ("update".equals(action)) {
                updateUser(req, resp);
            } else if ("search".equals(action)) {
                searchUsers(req, resp);
            } else {
                listUsers(req, resp);
            }
        } catch (SQLException e) {
            // 处理数据库异常，比如重复键错误
            req.setAttribute("errorMessage", "操作失败: " + e.getMessage());
            try {
                listUsers(req, resp);
            } catch (SQLException ex) {
                throw new ServletException(ex);
            }
        }
    }

    private void listUsers(HttpServletRequest req, HttpServletResponse resp) throws SQLException, ServletException, IOException {
        ArrayList<User> users = userService.findAllUsers();
        req.setAttribute("users", users);
        req.getRequestDispatcher("/user-list.jsp").forward(req, resp);
    }

    private void showEditForm(HttpServletRequest req, HttpServletResponse resp) throws SQLException, ServletException, IOException{
        String idStr = req.getParameter("id");
        if (idStr != null && !idStr.isEmpty()) {
            int id = Integer.parseInt(idStr);
            User user = userService.findUserById(id);
            req.setAttribute("user", user);
        }
        req.getRequestDispatcher("/user-form.jsp").forward(req,resp);
    }

    private void insertUser(HttpServletRequest req, HttpServletResponse resp) throws SQLException, IOException, ServletException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        
        User user = new User(username, password);
        try {
            userService.addUser(user);
            resp.sendRedirect("user?action=list");
        } catch (SQLException e) {
            // 捕获重复键异常并重定向回表单页面显示错误
            req.setAttribute("errorMessage", "用户名已存在，请选择其他用户名");
            req.setAttribute("user", user); // 保持表单数据
            req.getRequestDispatcher("/user-form.jsp").forward(req, resp);
        }
    }

    private void updateUser(HttpServletRequest req, HttpServletResponse resp) throws SQLException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        
        User user = new User(id, username, password);
        userService.updateUser(user);
        resp.sendRedirect("user?action=list");
    }

    private void deleteUser(HttpServletRequest req, HttpServletResponse resp) throws SQLException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        userService.deleteUser(id);
        resp.sendRedirect("user?action=list");
    }
    
    private void searchUsers(HttpServletRequest req, HttpServletResponse resp) throws SQLException, ServletException, IOException {
        String searchPattern = req.getParameter("search");
        ArrayList<User> users;
        
        if (searchPattern != null && !searchPattern.trim().isEmpty()) {
            users = userService.findUsersByUsernamePattern(searchPattern);
            req.setAttribute("searchPattern", searchPattern);
        } else {
            users = userService.findAllUsers();
        }
        
        req.setAttribute("users", users);
        req.getRequestDispatcher("/user-list.jsp").forward(req, resp);
    }
}