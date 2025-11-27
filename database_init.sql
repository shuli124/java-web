-- 创建数据库
CREATE DATABASE IF NOT EXISTS jin1 CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;

-- 使用数据库
USE jin1;

-- 删除已存在的用户表（如果存在）
DROP TABLE IF EXISTS user;

-- 创建用户表
CREATE TABLE user (
    id INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(50) NOT NULL UNIQUE,
    password VARCHAR(50) NOT NULL
);

-- 插入示例数据
INSERT INTO user (username, password) VALUES 
('admin', 'admin123'),
('zhangsan', 'pass456'),
('lisi', 'pass789');