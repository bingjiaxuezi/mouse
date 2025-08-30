-- 小鼠行为监测系统数据库初始化脚本
-- 创建数据库
CREATE DATABASE IF NOT EXISTS mouse_behavior;
USE mouse_behavior;

-- 用户表
CREATE TABLE t_user (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(50) NOT NULL UNIQUE,
    password VARCHAR(100) NOT NULL,
    email VARCHAR(100),
    phone VARCHAR(20),
    real_name VARCHAR(50),
    role_id BIGINT,
    status TINYINT DEFAULT 1,
    create_time DATETIME DEFAULT CURRENT_TIMESTAMP,
    update_time DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    INDEX idx_username (username),
    INDEX idx_status (status)
);

-- 设备表
CREATE TABLE t_device (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    device_id VARCHAR(50) NOT NULL UNIQUE,
    device_name VARCHAR(100) NOT NULL,
    device_type VARCHAR(20) DEFAULT 'camera',
    location VARCHAR(100),
    ip_address VARCHAR(15),
    mac_address VARCHAR(17),
    api_key VARCHAR(100),
    status TINYINT DEFAULT 1,
    last_heartbeat DATETIME,
    create_time DATETIME DEFAULT CURRENT_TIMESTAMP,
    update_time DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    INDEX idx_device_id (device_id),
    INDEX idx_status (status)
);

-- 实验项目表
CREATE TABLE t_experiment (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    experiment_name VARCHAR(100) NOT NULL,
    experiment_code VARCHAR(50) NOT NULL UNIQUE,
    description TEXT,
    researcher_id BIGINT,
    start_time DATETIME,
    end_time DATETIME,
    status TINYINT DEFAULT 1,
    create_time DATETIME DEFAULT CURRENT_TIMESTAMP,
    update_time DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    INDEX idx_experiment_code (experiment_code),
    INDEX idx_researcher_id (researcher_id)
);

-- 鼠笼表
CREATE TABLE t_cage (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    cage_id VARCHAR(50) NOT NULL UNIQUE,
    cage_name VARCHAR(100),
    cage_type VARCHAR(20),
    location VARCHAR(100),
    device_id VARCHAR(50),
    capacity INT DEFAULT 1,
    current_count INT DEFAULT 0,
    status TINYINT DEFAULT 1,
    create_time DATETIME DEFAULT CURRENT_TIMESTAMP,
    update_time DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    INDEX idx_cage_id (cage_id),
    INDEX idx_device_id (device_id)
);

-- 小鼠表
CREATE TABLE t_mouse (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    mouse_id VARCHAR(50) NOT NULL UNIQUE,
    mouse_name VARCHAR(50),
    species VARCHAR(50),
    strain VARCHAR(50),
    gender ENUM('male', 'female'),
    birth_date DATE,
    weight DECIMAL(5,2),
    photo_url VARCHAR(500),
    cage_id BIGINT,
    experiment_id BIGINT,
    status TINYINT DEFAULT 1,
    create_time DATETIME DEFAULT CURRENT_TIMESTAMP,
    update_time DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    INDEX idx_mouse_id (mouse_id),
    INDEX idx_cage_id (cage_id),
    INDEX idx_experiment_id (experiment_id)
);

-- 行为数据表
CREATE TABLE t_behavior_data (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    device_id VARCHAR(50) NOT NULL,
    mouse_id VARCHAR(50),
    experiment_id BIGINT,
    behavior_type VARCHAR(50),
    behavior_value JSON,
    confidence DECIMAL(3,2),
    timestamp DATETIME NOT NULL,
    video_file VARCHAR(200),
    create_time DATETIME DEFAULT CURRENT_TIMESTAMP,
    INDEX idx_device_timestamp (device_id, timestamp),
    INDEX idx_mouse_timestamp (mouse_id, timestamp),
    INDEX idx_experiment_timestamp (experiment_id, timestamp)
);

-- 标注任务表
CREATE TABLE t_annotation_task (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    task_name VARCHAR(100) NOT NULL,
    task_type VARCHAR(20),
    video_file VARCHAR(200),
    assignee_id BIGINT,
    status TINYINT DEFAULT 0,
    priority TINYINT DEFAULT 1,
    deadline DATETIME,
    create_time DATETIME DEFAULT CURRENT_TIMESTAMP,
    update_time DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    INDEX idx_assignee_id (assignee_id),
    INDEX idx_status (status)
);

-- 标注结果表
CREATE TABLE t_annotation_result (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    task_id BIGINT NOT NULL,
    annotator_id BIGINT,
    annotation_data JSON,
    quality_score DECIMAL(3,2),
    review_status TINYINT DEFAULT 0,
    reviewer_id BIGINT,
    review_comment TEXT,
    create_time DATETIME DEFAULT CURRENT_TIMESTAMP,
    update_time DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    INDEX idx_task_id (task_id),
    INDEX idx_annotator_id (annotator_id)
);

-- 系统日志表
CREATE TABLE t_system_log (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    log_level VARCHAR(10),
    log_source VARCHAR(50),
    log_message TEXT,
    user_id BIGINT,
    ip_address VARCHAR(15),
    user_agent VARCHAR(200),
    create_time DATETIME DEFAULT CURRENT_TIMESTAMP,
    INDEX idx_log_level (log_level),
    INDEX idx_create_time (create_time)
);

-- 文件管理表
CREATE TABLE t_file_info (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    file_name VARCHAR(200) NOT NULL,
    file_path VARCHAR(500),
    file_size BIGINT,
    file_type VARCHAR(50),
    mime_type VARCHAR(100),
    md5_hash VARCHAR(32),
    upload_user_id BIGINT,
    status TINYINT DEFAULT 1,
    create_time DATETIME DEFAULT CURRENT_TIMESTAMP,
    INDEX idx_file_name (file_name),
    INDEX idx_upload_user_id (upload_user_id)
);

-- 插入初始数据
-- 管理员用户 (密码: admin123)
INSERT INTO t_user (username, password, email, real_name, role_id) VALUES
('admin', '$2a$10$7JB720yubVSOfvVMe6/YqO4wkhWGEn67XVb1Q0K1EFyLWY1uIHe1a', 'admin@mouse.com', '系统管理员', 1),
('researcher1', '$2a$10$7JB720yubVSOfvVMe6/YqO4wkhWGEn67XVb1Q0K1EFyLWY1uIHe1a', 'researcher1@mouse.com', '研究员1', 2),
('researcher2', '$2a$10$7JB720yubVSOfvVMe6/YqO4wkhWGEn67XVb1Q0K1EFyLWY1uIHe1a', 'researcher2@mouse.com', '研究员2', 2);

-- 测试设备
INSERT INTO t_device (device_id, device_name, location, api_key) VALUES
('CAM001', '1号摄像头', '实验室A-1号笼', 'test-api-key-001'),
('CAM002', '2号摄像头', '实验室A-2号笼', 'test-api-key-002'),
('CAM003', '3号摄像头', '实验室B-1号笼', 'test-api-key-003');

-- 测试实验项目
INSERT INTO t_experiment (experiment_name, experiment_code, description, researcher_id) VALUES
('小鼠行为基线测试', 'EXP001', '建立小鼠正常行为基线数据', 2),
('药物A效果评估', 'EXP002', '测试药物A对小鼠行为的影响', 2),
('昼夜节律研究', 'EXP003', '研究小鼠昼夜活动规律', 3);

-- 测试鼠笼
INSERT INTO t_cage (cage_id, cage_name, location, device_id) VALUES
('CAGE001', '1号标准笼', '实验室A-1号位', 'CAM001'),
('CAGE002', '2号标准笼', '实验室A-2号位', 'CAM002'),
('CAGE003', '3号大型笼', '实验室B-1号位', 'CAM003');

-- 测试小鼠
INSERT INTO t_mouse (mouse_id, mouse_name, species, strain, gender, birth_date, weight, cage_id, experiment_id) VALUES
('M001', '小白1号', 'Mus musculus', 'C57BL/6', 'male', '2024-01-15', 25.5, 1, 1),
('M002', '小白2号', 'Mus musculus', 'C57BL/6', 'female', '2024-01-16', 22.3, 1, 1),
('M003', '小黑1号', 'Mus musculus', 'BALB/c', 'male', '2024-01-20', 26.8, 2, 2),
('M004', '小花1号', 'Mus musculus', 'C57BL/6', 'female', '2024-01-22', 23.1, 3, 3);

-- 创建数据库用户
CREATE USER IF NOT EXISTS 'mouse_user'@'%' IDENTIFIED BY 'mouse_pass';
GRANT ALL PRIVILEGES ON mouse_behavior.* TO 'mouse_user'@'%';
FLUSH PRIVILEGES;

-- 显示创建结果
SHOW TABLES;
SELECT 'Database initialization completed successfully!' as status;