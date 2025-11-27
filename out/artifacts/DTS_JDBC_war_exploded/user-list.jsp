<%--
  Created by IntelliJ IDEA.
  User: 32029
  Date: 2025/11/20
  Time: 20:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.bean.User" %>
<%@ page import="java.util.ArrayList" %>
<html>
<head>
    <title>用户列表</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 20px;
            background-color: #f5f5f5;
        }
        .container {
            max-width: 1000px;
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
        table {
            border-collapse: collapse;
            width: 100%;
            margin-top: 20px;
        }
        th, td {
            border: 1px solid #ddd;
            padding: 12px;
            text-align: left;
        }
        th {
            background-color: #f2f2f2;
            font-weight: bold;
        }
        tr:nth-child(even) {
            background-color: #f9f9f9;
        }
        tr:hover {
            background-color: #f5f5f5;
        }
        .btn {
            padding: 8px 16px;
            margin: 2px;
            text-decoration: none;
            border-radius: 4px;
            color: white;
            display: inline-block;
        }
        .btn-edit {
            background-color: #FF9800;
        }
        .btn-delete {
            background-color: #f44336;
        }
        .btn-add {
            background-color: #4CAF50;
            padding: 10px 20px;
            text-decoration: none;
            border-radius: 4px;
            color: white;
            display: inline-block;
            margin-bottom: 20px;
        }
        .btn-search {
            background-color: #2196F3;
            padding: 10px 20px;
            border: none;
            border-radius: 4px;
            color: white;
            cursor: pointer;
        }
        .btn:hover {
            opacity: 0.8;
        }
        .back-link {
            text-align: center;
            margin-top: 20px;
        }
        .back-link a {
            color: #2196F3;
            text-decoration: none;
            font-weight: bold;
        }
        .error {
            color: red;
            font-weight: bold;
            margin-bottom: 15px;
        }
        .search-form {
            margin-bottom: 20px;
        }
        .search-form input[type="text"] {
            padding: 10px;
            width: 300px;
            border: 1px solid #ccc;
            border-radius: 4px;
        }
        .search-form input[type="submit"] {
            padding: 10px 20px;
            background-color: #2196F3;
            color: white;
            border: none;
            border-radius: 4px;
            cursor: pointer;
            margin-left: 10px;
        }
        .search-form input[type="submit"]:hover {
            background-color: #1976D2;
        }
        .search-results-info {
            margin-bottom: 15px;
            font-weight: bold;
            color: #2196F3;
        }
    </style>
</head>
<body>
<div class="container">
    <h1>用户列表</h1>
    
    <%
        String errorMessage = (String) request.getAttribute("errorMessage");
        if (errorMessage != null) {
    %>
        <div class="error"><%= errorMessage %></div>
    <%
        }
    %>
    
    <a href="user?action=edit" class="btn-add">添加新用户</a>
    
    <!-- 搜索表单 -->
    <div class="search-form">
        <form action="user" method="get">
            <input type="hidden" name="action" value="search">
            <input type="text" name="search" placeholder="输入用户名进行搜索..." 
                   value="<%= request.getAttribute("searchPattern") != null ? request.getAttribute("searchPattern") : "" %>">
            <input type="submit" value="搜索">
            <% if (request.getAttribute("searchPattern") != null) { %>
                <a href="user?action=list" class="btn-search">显示全部</a>
            <% } %>
        </form>
    </div>
    
    <% 
        String searchPattern = (String) request.getAttribute("searchPattern");
        ArrayList<User> users = (ArrayList<User>) request.getAttribute("users");
        if (searchPattern != null && users != null) {
    %>
        <div class="search-results-info">
            搜索 "<%= searchPattern %>" 找到 <%= users.size() %> 个结果
        </div>
    <% } %>
    
    <table>
        <tr>
            <th>ID</th>
            <th>用户名</th>
            <th>密码</th>
            <th>操作</th>
        </tr>
        <%
            if (users != null && !users.isEmpty()) {
                for (User user : users) {
        %>
            <tr>
                <td><%=user.getId()%></td>
                <td><%=user.getUsername()%></td>
                <td><%=user.getPassword()%></td>
                <td>
                    <a href="user?action=edit&id=<%=user.getId()%>" class="btn btn-edit">编辑</a>
                    <a href="user?action=delete&id=<%=user.getId()%>" class="btn btn-delete" 
                       onclick="return confirm('确定要删除这个用户吗？')">删除</a>
                </td>
            </tr>
        <%
                }
            } else {
        %>
            <tr>
                <td colspan="4" style="text-align: center;">暂无用户数据</td>
            </tr>
        <%
            }
        %>
    </table>
    
    <div class="back-link">
        <a href="index.jsp">← 返回首页</a>
    </div>
</div>
</body>
</html>