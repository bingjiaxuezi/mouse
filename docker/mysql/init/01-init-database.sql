-- 小鼠行为监测系统数据库初始化脚本
-- 支持19种行为类型识别和3种生理状态估测
-- 创建数据库
CREATE DATABASE IF NOT EXISTS mouse_behavior;
USE mouse_behavior;

-- 用户表
CREATE TABLE t_user (
    id BIGINT AUTO_INCREMENT PRIMARY KEY COMMENT '用户ID，主键',
    username VARCHAR(50) NOT NULL UNIQUE COMMENT '用户名，唯一',
    password VARCHAR(100) NOT NULL COMMENT '密码，加密存储',
    email VARCHAR(100) COMMENT '邮箱地址',
    phone VARCHAR(20) COMMENT '手机号码',
    real_name VARCHAR(50) COMMENT '真实姓名',
    role_id BIGINT COMMENT '角色ID',
    status TINYINT DEFAULT 1 COMMENT '状态：1-正常，0-禁用',
    create_time DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    update_time DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    INDEX idx_username (username),
    INDEX idx_status (status)
) COMMENT='用户信息表，存储系统用户基本信息';

-- 设备表
CREATE TABLE t_device (
    id BIGINT AUTO_INCREMENT PRIMARY KEY COMMENT '设备ID，主键',
    device_id VARCHAR(50) NOT NULL UNIQUE COMMENT '设备编号，唯一标识',
    device_name VARCHAR(100) NOT NULL COMMENT '设备名称',
    device_type VARCHAR(20) DEFAULT 'camera' COMMENT '设备类型：camera-摄像头，sensor-传感器',
    location VARCHAR(100) COMMENT '设备位置描述',
    ip_address VARCHAR(15) COMMENT 'IP地址',
    mac_address VARCHAR(17) COMMENT 'MAC地址',
    api_key VARCHAR(100) COMMENT 'API密钥',
    status TINYINT DEFAULT 1 COMMENT '设备状态：1-在线，0-离线',
    last_heartbeat DATETIME COMMENT '最后心跳时间',
    create_time DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    update_time DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    INDEX idx_device_id (device_id),
    INDEX idx_status (status)
) COMMENT='设备信息表，存储监测设备基本信息';

-- 实验项目表
CREATE TABLE t_experiment (
    id BIGINT AUTO_INCREMENT PRIMARY KEY COMMENT '实验ID，主键',
    experiment_name VARCHAR(100) NOT NULL COMMENT '实验名称',
    experiment_code VARCHAR(50) NOT NULL UNIQUE COMMENT '实验编码，唯一标识',
    description TEXT COMMENT '实验描述',
    researcher_id BIGINT COMMENT '研究员ID',
    start_time DATETIME COMMENT '实验开始时间',
    end_time DATETIME COMMENT '实验结束时间',
    status TINYINT DEFAULT 1 COMMENT '实验状态：1-进行中，2-已完成，0-已取消',
    create_time DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    update_time DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    INDEX idx_experiment_code (experiment_code),
    INDEX idx_researcher_id (researcher_id)
) COMMENT='实验项目表，存储实验基本信息';

-- 鼠笼表
CREATE TABLE t_cage (
    id BIGINT AUTO_INCREMENT PRIMARY KEY COMMENT '笼子ID，主键',
    cage_id VARCHAR(50) NOT NULL UNIQUE COMMENT '笼子编号，唯一标识',
    cage_name VARCHAR(100) COMMENT '笼子名称',
    cage_type VARCHAR(20) COMMENT '笼子类型：standard-标准笼，large-大型笼',
    location VARCHAR(100) COMMENT '笼子位置',
    device_id VARCHAR(50) COMMENT '关联设备编号',
    capacity INT DEFAULT 1 COMMENT '笼子容量',
    current_count INT DEFAULT 0 COMMENT '当前小鼠数量',
    status TINYINT DEFAULT 1 COMMENT '笼子状态：1-使用中，0-空闲',
    create_time DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    update_time DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    INDEX idx_cage_id (cage_id),
    INDEX idx_device_id (device_id)
) COMMENT='鼠笼信息表，存储实验笼基本信息';

-- 小鼠表（已联调通过，不可修改）
CREATE TABLE t_mouse (
    id BIGINT AUTO_INCREMENT PRIMARY KEY COMMENT '小鼠ID，主键',
    mouse_id VARCHAR(50) NOT NULL UNIQUE COMMENT '小鼠编号，唯一标识',
    mouse_name VARCHAR(50) COMMENT '小鼠名称',
    species VARCHAR(50) COMMENT '物种',
    strain VARCHAR(50) COMMENT '品系',
    gender ENUM('male', 'female') COMMENT '性别：male-雄性，female-雌性',
    birth_date DATE COMMENT '出生日期',
    weight DECIMAL(5,2) COMMENT '体重（克）',
    photo_url VARCHAR(500) COMMENT '照片URL',
    cage_id BIGINT COMMENT '所在笼子ID',
    experiment_id BIGINT COMMENT '参与实验ID',
    status TINYINT DEFAULT 1 COMMENT '状态：1-正常，0-异常',
    create_time DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    update_time DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    INDEX idx_mouse_id (mouse_id),
    INDEX idx_cage_id (cage_id),
    INDEX idx_experiment_id (experiment_id)
) COMMENT='小鼠基本信息表，存储实验小鼠的基础数据（已联调通过）';

-- 行为类型字典表
CREATE TABLE t_behavior_type (
    id BIGINT AUTO_INCREMENT PRIMARY KEY COMMENT '行为类型ID，主键',
    behavior_code VARCHAR(20) NOT NULL UNIQUE COMMENT '行为编码，唯一标识',
    behavior_name VARCHAR(50) NOT NULL COMMENT '行为名称',
    behavior_category VARCHAR(20) NOT NULL COMMENT '行为分类：basic-基础行为，social-社交行为，abnormal-异常行为',
    description TEXT COMMENT '行为描述',
    unit VARCHAR(20) COMMENT '计量单位：次数、时长等',
    normal_range VARCHAR(100) COMMENT '正常范围描述',
    status TINYINT DEFAULT 1 COMMENT '状态：1-启用，0-禁用',
    create_time DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    update_time DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    INDEX idx_behavior_code (behavior_code),
    INDEX idx_category (behavior_category)
) COMMENT='行为类型字典表，定义19种核心行为类型';

-- 行为数据表（优化版）
CREATE TABLE t_behavior_data (
    id BIGINT AUTO_INCREMENT PRIMARY KEY COMMENT '行为数据ID，主键',
    device_id VARCHAR(50) NOT NULL COMMENT '设备编号',
    mouse_id VARCHAR(50) NOT NULL COMMENT '小鼠编号，关联t_mouse.mouse_id',
    experiment_id BIGINT COMMENT '实验ID',
    behavior_code VARCHAR(20) NOT NULL COMMENT '行为编码，关联t_behavior_type.behavior_code',
    behavior_value DECIMAL(10,4) COMMENT '行为数值（次数、时长等）',
    confidence DECIMAL(3,2) COMMENT '置信度（0.00-1.00）',
    start_time DATETIME NOT NULL COMMENT '行为开始时间',
    end_time DATETIME COMMENT '行为结束时间',
    duration_seconds INT COMMENT '持续时长（秒）',
    position_x DECIMAL(8,4) COMMENT 'X坐标位置',
    position_y DECIMAL(8,4) COMMENT 'Y坐标位置',
    video_file VARCHAR(200) COMMENT '关联视频文件',
    frame_start INT COMMENT '起始帧号',
    frame_end INT COMMENT '结束帧号',
    metadata JSON COMMENT '扩展元数据（JSON格式）',
    create_time DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '记录创建时间',
    INDEX idx_device_time (device_id, start_time),
    INDEX idx_mouse_time (mouse_id, start_time),
    INDEX idx_experiment_time (experiment_id, start_time),
    INDEX idx_behavior_code (behavior_code),
    FOREIGN KEY (behavior_code) REFERENCES t_behavior_type(behavior_code)
) COMMENT='行为数据表，存储小鼠行为识别结果';

-- 位置轨迹表
CREATE TABLE t_position_track (
    id BIGINT AUTO_INCREMENT PRIMARY KEY COMMENT '轨迹ID，主键',
    device_id VARCHAR(50) NOT NULL COMMENT '设备编号',
    mouse_id VARCHAR(50) NOT NULL COMMENT '小鼠编号',
    experiment_id BIGINT COMMENT '实验ID',
    timestamp DATETIME NOT NULL COMMENT '时间戳',
    position_x DECIMAL(8,4) NOT NULL COMMENT 'X坐标',
    position_y DECIMAL(8,4) NOT NULL COMMENT 'Y坐标',
    velocity DECIMAL(8,4) COMMENT '移动速度（像素/秒）',
    direction DECIMAL(5,2) COMMENT '移动方向（角度）',
    zone VARCHAR(20) COMMENT '所在区域标识',
    create_time DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    INDEX idx_mouse_time (mouse_id, timestamp),
    INDEX idx_device_time (device_id, timestamp),
    INDEX idx_experiment_time (experiment_id, timestamp)
) COMMENT='位置轨迹表，存储小鼠移动轨迹数据';

-- 生理状态表
CREATE TABLE t_physiological_state (
    id BIGINT AUTO_INCREMENT PRIMARY KEY COMMENT '生理状态ID，主键',
    device_id VARCHAR(50) NOT NULL COMMENT '设备编号',
    mouse_id VARCHAR(50) NOT NULL COMMENT '小鼠编号',
    experiment_id BIGINT COMMENT '实验ID',
    state_type VARCHAR(20) NOT NULL COMMENT '状态类型：weight-体重，temperature-体温，health-健康状态',
    state_value DECIMAL(10,4) COMMENT '状态数值',
    state_level VARCHAR(20) COMMENT '状态等级：normal-正常，warning-警告，critical-严重',
    confidence DECIMAL(3,2) COMMENT '置信度（0.00-1.00）',
    measurement_time DATETIME NOT NULL COMMENT '测量时间',
    notes TEXT COMMENT '备注信息',
    create_time DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    INDEX idx_mouse_time (mouse_id, measurement_time),
    INDEX idx_state_type (state_type),
    INDEX idx_experiment_time (experiment_id, measurement_time)
) COMMENT='生理状态表，存储体重、体温、健康状态等生理指标';

-- 标注任务表
CREATE TABLE t_annotation_task (
    id BIGINT AUTO_INCREMENT PRIMARY KEY COMMENT '标注任务ID，主键',
    task_name VARCHAR(100) NOT NULL COMMENT '任务名称',
    task_type VARCHAR(20) COMMENT '任务类型：behavior-行为标注，quality-质量检查',
    video_file VARCHAR(200) COMMENT '视频文件路径',
    assignee_id BIGINT COMMENT '分配给的用户ID',
    status TINYINT DEFAULT 0 COMMENT '任务状态：0-待处理，1-进行中，2-已完成',
    priority TINYINT DEFAULT 1 COMMENT '优先级：1-低，2-中，3-高',
    deadline DATETIME COMMENT '截止时间',
    create_time DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    update_time DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    INDEX idx_assignee_id (assignee_id),
    INDEX idx_status (status)
) COMMENT='标注任务表，管理人工标注任务';

-- 标注结果表
CREATE TABLE t_annotation_result (
    id BIGINT AUTO_INCREMENT PRIMARY KEY COMMENT '标注结果ID，主键',
    task_id BIGINT NOT NULL COMMENT '标注任务ID',
    annotator_id BIGINT COMMENT '标注员ID',
    annotation_data JSON COMMENT '标注数据（JSON格式）',
    quality_score DECIMAL(3,2) COMMENT '质量评分（0.00-1.00）',
    review_status TINYINT DEFAULT 0 COMMENT '审核状态：0-待审核，1-通过，2-驳回',
    reviewer_id BIGINT COMMENT '审核员ID',
    review_comment TEXT COMMENT '审核意见',
    create_time DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    update_time DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    INDEX idx_task_id (task_id),
    INDEX idx_annotator_id (annotator_id)
) COMMENT='标注结果表，存储人工标注的结果数据';

-- 系统日志表
CREATE TABLE t_system_log (
    id BIGINT AUTO_INCREMENT PRIMARY KEY COMMENT '日志ID，主键',
    log_level VARCHAR(10) COMMENT '日志级别：DEBUG，INFO，WARN，ERROR',
    log_source VARCHAR(50) COMMENT '日志来源模块',
    log_message TEXT COMMENT '日志内容',
    user_id BIGINT COMMENT '操作用户ID',
    ip_address VARCHAR(15) COMMENT '客户端IP地址',
    user_agent VARCHAR(200) COMMENT '用户代理信息',
    create_time DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    INDEX idx_log_level (log_level),
    INDEX idx_create_time (create_time)
) COMMENT='系统日志表，记录系统操作和异常信息';

-- 文件管理表
CREATE TABLE t_file_info (
    id BIGINT AUTO_INCREMENT PRIMARY KEY COMMENT '文件ID，主键',
    file_name VARCHAR(200) NOT NULL COMMENT '文件名',
    file_path VARCHAR(500) COMMENT '文件路径',
    file_size BIGINT COMMENT '文件大小（字节）',
    file_type VARCHAR(50) COMMENT '文件类型',
    mime_type VARCHAR(100) COMMENT 'MIME类型',
    md5_hash VARCHAR(32) COMMENT 'MD5哈希值',
    upload_user_id BIGINT COMMENT '上传用户ID',
    status TINYINT DEFAULT 1 COMMENT '文件状态：1-正常，0-已删除',
    create_time DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    INDEX idx_file_name (file_name),
    INDEX idx_upload_user_id (upload_user_id)
) COMMENT='文件管理表，存储上传文件的元信息';

-- 插入行为类型字典数据
INSERT INTO t_behavior_type (behavior_code, behavior_name, behavior_category, description, unit, normal_range) VALUES
('SLEEP', '睡眠', 'basic', '小鼠处于睡眠状态，活动量极低', '分钟', '12-16小时/天'),
('EAT', '进食', 'basic', '小鼠进食行为', '次数', '5-8次/天'),
('DRINK', '饮水', 'basic', '小鼠饮水行为', '次数', '8-12次/天'),
('DEFECATE', '排便', 'basic', '小鼠排便行为', '次数', '20-30次/天'),
('COUGH', '咳嗽', 'abnormal', '小鼠咳嗽行为，可能表示呼吸道问题', '次数', '0-2次/天'),
('GROOM', '舔毛', 'basic', '小鼠自我清洁行为', '分钟', '2-4小时/天'),
('WALK', '走动', 'basic', '小鼠正常移动行为', '米', '100-500米/天'),
('RUN', '奔跑', 'basic', '小鼠快速移动行为', '次数', '10-30次/天'),
('CLIMB', '攀爬', 'basic', '小鼠攀爬行为', '次数', '5-15次/天'),
('EXPLORE', '探索', 'basic', '小鼠探索环境行为', '分钟', '1-3小时/天'),
('REST', '休息', 'basic', '小鼠静止休息但未睡眠', '分钟', '4-8小时/天'),
('SOCIAL', '社交', 'social', '多只小鼠间的社交互动', '次数', '5-20次/天'),
('AGGRESSIVE', '攻击', 'social', '小鼠攻击性行为', '次数', '0-3次/天'),
('MATING', '交配', 'social', '小鼠交配行为', '次数', '0-5次/天'),
('NEST', '筑巢', 'basic', '小鼠筑巢整理行为', '次数', '1-3次/天'),
('FREEZE', '僵直', 'abnormal', '小鼠恐惧或应激反应', '次数', '0-2次/天'),
('CIRCLE', '转圈', 'abnormal', '小鼠异常转圈行为', '次数', '0-1次/天'),
('SEIZURE', '癫痫', 'abnormal', '小鼠癫痫发作', '次数', '0次/天'),
('SCRATCH', '抓挠', 'basic', '小鼠抓挠行为', '次数', '10-30次/天');

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