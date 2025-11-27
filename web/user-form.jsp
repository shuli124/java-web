<%--
  Created by IntelliJ IDEA.
  User: 32029
  Date: 2025/11/20
  Time: 20:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.bean.User" %>
<html>
<head>
    <title>用户表单</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 20px;
            background-color: #f5f5f5;
        }
        .container {
            max-width: 600px;
            margin: 0 auto;
            background-color: white;
            padding: 20px;
            border-radius: 8px;
            box-shadow: 0 0 10px rgba(0,0,0,0.1);
        }
        h1 {
            color: #333;
            text-align: center;
            background: linear-gradient(45deg, #2196F3, #21CBF3);
            color: white;
            padding: 20px;
            margin: -20px -20px 20px -20px;
            border-radius: 8px 8px 0 0;
        }
        .form-group {
            margin-bottom: 20px;
        }
        label {
            display: block;
            margin-bottom: 5px;
            font-weight: bold;
            color: #555;
        }
        input[type=text], input[type=password] {
            width: 100%;
            padding: 12px;
            border: 1px solid #ccc;
            border-radius: 4px;
            box-sizing: border-box;
            font-size: 16px;
        }
        input[type=submit] {
            background-color: #4CAF50;
            color: white;
            padding: 12px 20px;
            border: none;
            border-radius: 4px;
            cursor: pointer;
            font-size: 16px;
            font-weight: bold;
            margin-right: 10px;
        }
        input[type=submit]:hover {
            background-color: #45a049;
        }
        .btn-cancel {
            background-color: #9E9E9E;
            text-decoration: none;
            padding: 12px 20px;
            color: white;
            border-radius: 4px;
        }
        .btn-cancel:hover {
            background-color: #757575;
        }
        .error {
            color: red;
            font-weight: bold;
            margin-bottom: 15px;
        }
    </style>
</head>
<body>
<div class="container">
    <h1>用户表单</h1>
    
    <%
        String errorMessage = (String) request.getAttribute("errorMessage");
        if (errorMessage != null) {
    %>
        <div class="error"><%= errorMessage %></div>
    <%
        }
    %>
    
    <%
        User user = (User) request.getAttribute("user");
        String action = (user != null && user.getId() > 0) ? "update" : "insert";
        String title = (user != null && user.getId() > 0) ? "编辑用户" : "添加用户";
        String buttonLabel = (user != null && user.getId() > 0) ? "更新" : "添加";
    %>
    
    <h2><%=title%></h2>
    
    <form action="user?action=<%=action%>" method="post">
        <% if (user != null && user.getId() > 0) { %>
            <input type="hidden" name="id" value="<%=user.getId()%>">
        <% } %>
        
        <div class="form-group">
            <label for="username">用户名:</label>
            <input type="text" id="username" name="username" 
                   value="<%=user != null ? user.getUsername() : ""%>" required>
        </div>
        
        <div class="form-group">
            <label for="password">密码:</label>
            <input type="password" id="password" name="password" 
                   value="<%=user != null ? user.getPassword() : ""%>" required>
        </div>
        
        <input type="submit" value="<%=buttonLabel%>用户">
        <a href="user?action=list" class="btn-cancel">取消</a>
    </form>
    
    <div class="back-link">
        <a href="user?action=list">← 返回用户列表</a>
    </div>
</div>
</body>
</html>