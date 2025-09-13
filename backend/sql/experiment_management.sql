-- ===========================
-- 实验管理系统数据表设计
-- 创建时间: 2025-01-17
-- 描述: 实验驱动的小鼠管理系统核心表结构
-- ===========================

-- ----------------------------
-- 1、实验主表 (重新设计，扩展原有实验表)
-- ----------------------------
DROP TABLE IF EXISTS sys_experiment;
CREATE TABLE sys_experiment (
  experiment_id         BIGINT(20)      NOT NULL AUTO_INCREMENT    COMMENT '实验ID',
  experiment_code       VARCHAR(50)     NOT NULL                   COMMENT '实验编号',
  experiment_name       VARCHAR(100)    NOT NULL                   COMMENT '实验名称',
  experiment_type       VARCHAR(50)     NOT NULL                   COMMENT '实验类型',
  experiment_template   VARCHAR(100)    DEFAULT NULL               COMMENT '实验模板名称',
  experiment_objective  TEXT                                       COMMENT '实验目标',
  experiment_desc       TEXT                                       COMMENT '实验描述',
  
  -- 实验状态管理
  experiment_status     VARCHAR(20)     DEFAULT 'DRAFT'            COMMENT '实验状态：DRAFT-草稿,READY-准备中,RUNNING-进行中,PAUSED-暂停,COMPLETED-已完成,CANCELLED-已取消,ARCHIVED-已归档',
  
  -- 实验时间管理
  planned_begin_time    DATETIME                                   COMMENT '计划开始时间',
  planned_finish_time   DATETIME                                   COMMENT '计划结束时间',
  actual_begin_time     DATETIME                                   COMMENT '实际开始时间',
  actual_finish_time    DATETIME                                   COMMENT '实际结束时间',
  experiment_duration   INT             DEFAULT 0                  COMMENT '实验周期(天)',
  
  -- 实验人员管理
  principal_researcher  BIGINT(20)      NOT NULL                   COMMENT '主要研究员ID',
  co_researchers        TEXT                                       COMMENT '协作研究员ID列表(JSON格式): [1,2,3]',
  experiment_team       VARCHAR(100)    DEFAULT NULL               COMMENT '实验团队名称',
  
  -- 实验配置
  experiment_config     TEXT                                       COMMENT  '实验配置参数(JSON格式): {"monitoring_hours":24,"analysis_interval":1}',
  qr_code_content       TEXT                                       COMMENT '实验二维码内容',
  qr_code_image_url     VARCHAR(500)    DEFAULT NULL               COMMENT '二维码图片URL',
  
  -- 实验统计
  total_cages          INT             DEFAULT 0                  COMMENT '关联笼子总数',
  total_mice           INT             DEFAULT 0                  COMMENT '关联小鼠总数',
  
  -- 系统字段
  build_by             VARCHAR(64)     DEFAULT ''                 COMMENT '创建者',
  build_time           DATETIME        DEFAULT CURRENT_TIMESTAMP    COMMENT '创建时间',
  modify_by            VARCHAR(64)     DEFAULT ''                 COMMENT '更新者',
  modify_time          DATETIME        DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  del_flag             CHAR(1)         DEFAULT '0'                COMMENT '删除标志（0代表存在 2代表删除）',
  remark               VARCHAR(500)    DEFAULT NULL               COMMENT '备注',
  
  PRIMARY KEY (experiment_id),
  UNIQUE KEY uk_experiment_code (experiment_code),
  KEY idx_experiment_status (experiment_status),
  KEY idx_principal_researcher (principal_researcher),
  KEY idx_planned_begin_time (planned_begin_time)
) ENGINE=INNODB AUTO_INCREMENT=1 COMMENT = '实验主表';

-- ----------------------------
-- 2、实验模板表
-- ----------------------------
DROP TABLE IF EXISTS sys_experiment_template;
CREATE TABLE sys_experiment_template (
  template_id          BIGINT(20)      NOT NULL AUTO_INCREMENT    COMMENT '模板ID',
  template_code        VARCHAR(50)     NOT NULL                   COMMENT '模板编号',
  template_name        VARCHAR(100)    NOT NULL                   COMMENT '模板名称',
  template_type        VARCHAR(50)     NOT NULL                   COMMENT '模板类型',
  template_desc        TEXT                                       COMMENT '模板描述',
  
  -- 模板配置
  default_duration     INT             DEFAULT 7                  COMMENT '默认实验周期(天)',
  default_config       TEXT                                       COMMENT '默认配置参数(JSON格式)',
  required_equipment   TEXT                                       COMMENT '必需设备列表(JSON格式)',
  
  -- 模板状态
  template_status      CHAR(1)         DEFAULT '0'                COMMENT '模板状态（0启用 1禁用）',
  
  -- 系统字段
  build_by             VARCHAR(64)     DEFAULT ''                 COMMENT '创建者',
  build_time           DATETIME        DEFAULT CURRENT_TIMESTAMP    COMMENT '创建时间',
  modify_by            VARCHAR(64)     DEFAULT ''                 COMMENT '更新者',
  modify_time          DATETIME        DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  del_flag             CHAR(1)         DEFAULT '0'                COMMENT '删除标志（0代表存在 2代表删除）',
  remark               VARCHAR(500)    DEFAULT NULL               COMMENT '备注',
  
  PRIMARY KEY (template_id),
  UNIQUE KEY uk_template_code (template_code),
  KEY idx_template_type (template_type)
) ENGINE=INNODB AUTO_INCREMENT=1 COMMENT = '实验模板表';

-- ----------------------------
-- 3、笼子管理表
-- ----------------------------
DROP TABLE IF EXISTS sys_cage;
CREATE TABLE sys_cage (
  cage_id              BIGINT(20)      NOT NULL AUTO_INCREMENT    COMMENT '笼子ID',
  cage_code            VARCHAR(50)     NOT NULL                   COMMENT '笼子编号',
  cage_name            VARCHAR(100)    NOT NULL                   COMMENT '笼子名称',
  cage_type            VARCHAR(50)     DEFAULT 'STANDARD'         COMMENT '笼子类型：STANDARD-标准笼,LARGE-大型笼,SPECIAL-特殊笼',
  
  -- 笼子位置信息
  laboratory_room      VARCHAR(50)     NOT NULL                   COMMENT '实验室房间',
  rack_number          VARCHAR(20)     DEFAULT NULL               COMMENT '货架编号',
  position_row         INT             DEFAULT NULL               COMMENT '行位置',
  position_column      INT             DEFAULT NULL               COMMENT '列位置',
  
  -- 笼子规格
  max_capacity         INT             DEFAULT 5                  COMMENT '最大容量(只)',
  current_count        INT             DEFAULT 0                  COMMENT '当前小鼠数量',
  
  -- 笼子状态
  cage_status          VARCHAR(20)     DEFAULT 'AVAILABLE'        COMMENT '笼子状态：AVAILABLE-可用,OCCUPIED-占用中,MAINTENANCE-维护中,DAMAGED-损坏,RETIRED-退役',
  
  -- 环境参数
  temperature_range    VARCHAR(20)     DEFAULT '20-25'            COMMENT '温度范围(°C)',
  humidity_range       VARCHAR(20)     DEFAULT '40-70'            COMMENT '湿度范围(%)',
  
  -- 二维码管理
  qr_code_content      TEXT                                       COMMENT '笼子二维码内容',
  qr_code_image_url    VARCHAR(500)    DEFAULT NULL               COMMENT '二维码图片URL',
  
  -- 系统字段
  extend_info          TEXT                                       COMMENT '扩展信息(JSON格式)',
  extend_config        TEXT                                       COMMENT '扩展配置(JSON格式)',
  extend_data          TEXT                                       COMMENT '扩展数据(JSON格式)',
  extend_info1         TEXT                                       COMMENT '扩展信息1(JSON格式)',  
  extend_info2         TEXT                                       COMMENT '扩展信息2(JSON格式)',
  extend_info3         TEXT                                       COMMENT '扩展信息3(JSON格式)',
  extend_info4         TEXT                                       COMMENT '扩展信息4(JSON格式)',
  extend_info5         TEXT                                       COMMENT '扩展信息5(JSON格式)',
  build_by             VARCHAR(64)     DEFAULT ''                 COMMENT '创建者',
  build_time           DATETIME        DEFAULT CURRENT_TIMESTAMP    COMMENT '创建时间',
  modify_by            VARCHAR(64)     DEFAULT ''                 COMMENT '更新者',
  modify_time          DATETIME        DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  del_flag             CHAR(1)         DEFAULT '0'                COMMENT '删除标志（0代表存在 2代表删除）',
  remark               VARCHAR(500)    DEFAULT NULL               COMMENT '备注',
  
  PRIMARY KEY (cage_id),
  UNIQUE KEY uk_cage_code (cage_code),
  KEY idx_cage_status (cage_status),
  KEY idx_cage_type (cage_type),
  KEY idx_rack_number (rack_number),
  KEY idx_laboratory_room (laboratory_room)
) ENGINE=INNODB AUTO_INCREMENT=1 COMMENT = '笼子管理表';

-- ----------------------------
-- 4、实验笼子关系表
-- ----------------------------
DROP TABLE IF EXISTS sys_experiment_cage;
CREATE TABLE sys_experiment_cage (
  relation_id          BIGINT(20)      NOT NULL AUTO_INCREMENT    COMMENT '关系ID',
  experiment_id        BIGINT(20)      NOT NULL                   COMMENT '实验ID',
  cage_id              BIGINT(20)      NOT NULL                   COMMENT '笼子ID',
  
  -- 绑定信息
  bind_time            DATETIME        NOT NULL                   COMMENT '绑定时间',
  unbind_time          DATETIME        DEFAULT NULL               COMMENT '解绑时间',
  bind_status          VARCHAR(20)     DEFAULT 'ACTIVE'           COMMENT '绑定状态：ACTIVE-激活,INACTIVE-未激活,UNBOUND-已解绑',
  
  -- 绑定方式
  bind_method          VARCHAR(20)     DEFAULT 'MANUAL'           COMMENT '绑定方式：MANUAL-手动绑定,QR_SCAN-二维码扫描,AUTO-自动绑定',
  bind_operator        VARCHAR(64)     DEFAULT ''                 COMMENT '绑定操作员',
  
  -- 实验配置
  cage_role            VARCHAR(50)     DEFAULT 'EXPERIMENTAL'     COMMENT '笼子角色：EXPERIMENTAL-实验组,CONTROL-对照组,BACKUP-备用组',
  monitoring_config    TEXT                                       COMMENT  '监控配置(JSON格式): {"monitoring_enabled":true,"recording_quality":"high"}',
  
  -- 系统字段
  build_by             VARCHAR(64)     DEFAULT ''                 COMMENT '创建者',
  build_time           DATETIME        DEFAULT CURRENT_TIMESTAMP    COMMENT '创建时间',
  modify_by            VARCHAR(64)     DEFAULT ''                 COMMENT '更新者',
  modify_time          DATETIME        DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  remark               VARCHAR(500)    DEFAULT NULL               COMMENT '备注',
  
  PRIMARY KEY (relation_id),
  UNIQUE KEY uk_experiment_cage (experiment_id, cage_id),
  KEY idx_experiment_id (experiment_id),
  KEY idx_bind_time (bind_time),
  KEY idx_cage_id (cage_id),
  KEY idx_bind_status (bind_status)
) ENGINE=INNODB AUTO_INCREMENT=1 COMMENT = '实验笼子关系表';

-- ----------------------------
-- 5、实验记录表(实验日报)
-- ----------------------------
DROP TABLE IF EXISTS sys_experiment_record;
CREATE TABLE sys_experiment_record (
  record_id            BIGINT(20)      NOT NULL AUTO_INCREMENT    COMMENT '记录ID',
  experiment_id        BIGINT(20)      NOT NULL                   COMMENT '实验ID',
  
  -- 记录基本信息
  record_date          DATE            NOT NULL                   COMMENT '记录日期',
  record_title         VARCHAR(200)    NOT NULL                   COMMENT '记录标题',
  record_content       LONGTEXT                                   COMMENT '记录内容(富文本)',
  record_type          VARCHAR(50)     DEFAULT 'DAILY'            COMMENT '记录类型：DAILY-日常记录,MILESTONE-里程碑,ISSUE-问题记录,OBSERVATION-观察记录',
  
  -- 实验状态统计
  experiment_status    VARCHAR(50)                                COMMENT '实验状态',
  total_cages          INT             DEFAULT 0                  COMMENT '总笼子数',
  active_cages         INT             DEFAULT 0                  COMMENT '活跃笼子数',
  total_mice           INT             DEFAULT 0                  COMMENT '总小鼠数',
  active_mice          INT             DEFAULT 0                  COMMENT '活跃小鼠数',
  
  -- 小鼠健康指标
  healthy_mice         INT             DEFAULT 0                  COMMENT '健康小鼠数',
  sick_mice            INT             DEFAULT 0                  COMMENT '生病小鼠数',
  dead_mice            INT             DEFAULT 0                  COMMENT '死亡小鼠数',
  average_weight       DECIMAL(8,2)                               COMMENT '平均体重(克)',
  
  -- 数据收集统计
  data_collected_today INT             DEFAULT 0                  COMMENT '当日收集数据量',
  data_collected_total INT             DEFAULT 0                  COMMENT '累计收集数据量',
  behavior_records     INT             DEFAULT 0                  COMMENT '行为记录数',
  
  -- 异常统计
  issues_count         INT             DEFAULT 0                  COMMENT '异常数量',
  resolved_issues      INT             DEFAULT 0                  COMMENT '已解决异常数量',
  
  -- 完成度统计
  completion_rate      DECIMAL(5,2)    DEFAULT 0.00               COMMENT '总体完成率(%)',
  daily_progress       DECIMAL(5,2)    DEFAULT 0.00               COMMENT '当日进度(%)',
  
  -- 环境数据
  temperature          DECIMAL(5,2)                               COMMENT '平均温度(℃)',
  humidity             DECIMAL(5,2)                               COMMENT '平均湿度(%)',
  light_cycle          VARCHAR(50)                                COMMENT '光照周期',
  
  -- 生成信息
  generated_by         VARCHAR(20)     DEFAULT 'MANUAL'           COMMENT '生成方式：SYSTEM-系统生成,MANUAL-手动生成',
  generation_time      DATETIME        DEFAULT CURRENT_TIMESTAMP  COMMENT '生成时间',
  
  -- 文件统计
  file_count           INT             DEFAULT 0                  COMMENT '关联文件数量',
  
  -- 系统字段
  extend_info          TEXT                                       COMMENT '扩展信息(JSON格式)',
  extend_config        TEXT                                       COMMENT '扩展配置(JSON格式)',
  extend_data          TEXT                                       COMMENT '扩展数据(JSON格式)',
  extend_info1         TEXT                                       COMMENT '扩展信息1(JSON格式)',  
  extend_info2         TEXT                                       COMMENT '扩展信息2(JSON格式)',
  extend_info3         TEXT                                       COMMENT '扩展信息3(JSON格式)',
  extend_info4         TEXT                                       COMMENT '扩展信息4(JSON格式)',
  extend_info5         TEXT                                       COMMENT '扩展信息5(JSON格式)',
  extend_info6         TEXT                                       COMMENT '扩展信息2(JSON格式)',
  extend_info7         TEXT                                       COMMENT '扩展信息3(JSON格式)',
  extend_info8        TEXT                                       COMMENT '扩展信息4(JSON格式)',
  extend_info9        TEXT                                       COMMENT '扩展信息5(JSON格式)',
  build_by             VARCHAR(64)     DEFAULT ''                 COMMENT '创建者',
  build_time           DATETIME        DEFAULT CURRENT_TIMESTAMP  COMMENT '创建时间',
  modify_by            VARCHAR(64)     DEFAULT ''                 COMMENT '更新者',
  modify_time          DATETIME        DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  remark               VARCHAR(500)    DEFAULT NULL               COMMENT '备注',
  
  PRIMARY KEY (record_id),
  UNIQUE KEY uk_experiment_date (experiment_id, record_date),
  KEY idx_experiment_id (experiment_id),
  KEY idx_record_date (record_date),
  KEY idx_record_type (record_type),
  KEY idx_generation_time (generation_time)
) ENGINE=INNODB AUTO_INCREMENT=1 COMMENT = '实验记录表(实验日报)';

-- ----------------------------
-- 6、实验文件管理表
-- ----------------------------
DROP TABLE IF EXISTS sys_experiment_file;
CREATE TABLE sys_experiment_file (
  file_id              BIGINT(20)      NOT NULL AUTO_INCREMENT    COMMENT '文件ID',
  experiment_id        BIGINT(20)      NOT NULL                   COMMENT '实验ID',
  record_id            BIGINT(20)                                 COMMENT '关联实验记录ID',
  
  -- 文件基本信息
  file_name            VARCHAR(200)    NOT NULL                   COMMENT '文件名称',
  file_original_name   VARCHAR(200)    NOT NULL                   COMMENT '原始文件名',
  file_path            VARCHAR(500)    NOT NULL                   COMMENT '文件路径',
  file_type            VARCHAR(50)     NOT NULL                   COMMENT '文件类型：IMAGE-图片,VIDEO-视频,DOCUMENT-文档,DATA-数据文件,AUDIO-音频',
  file_size            BIGINT(20)      DEFAULT 0                  COMMENT '文件大小(字节)',
  file_extension       VARCHAR(20)                                COMMENT '文件扩展名',
  
  -- 文件分类和描述
  file_category        VARCHAR(50)     DEFAULT 'OTHER'            COMMENT '文件分类：PROTOCOL-实验方案,RESULT-实验结果,REPORT-实验报告,IMAGE-图片资料,DATA-数据文件,OTHER-其他',
  file_description     TEXT                                       COMMENT '文件描述',
  file_tags            VARCHAR(500)                               COMMENT '文件标签(逗号分隔)',
  
  -- MinIO存储信息
  bucket_name          VARCHAR(100)    DEFAULT 'experiment'       COMMENT 'MinIO存储桶名称',
  object_name          VARCHAR(500)    NOT NULL                   COMMENT 'MinIO对象名称',
  
  -- 文件状态
  file_status          VARCHAR(20)     DEFAULT 'ACTIVE'           COMMENT '文件状态：ACTIVE-活跃,ARCHIVED-归档,DELETED-删除',
  download_count       INT             DEFAULT 0                  COMMENT '下载次数',
  
  -- 版本信息
  version              VARCHAR(20)     DEFAULT '1.0'              COMMENT '文件版本',
  parent_file_id       BIGINT(20)                                 COMMENT '父文件ID(用于版本管理)',
  
  -- 系统字段
  extend_info          TEXT                                       COMMENT '扩展信息(JSON格式)',
  extend_config        TEXT                                       COMMENT '扩展配置(JSON格式)',
  extend_data          TEXT                                       COMMENT '扩展数据(JSON格式)',
  extend_info1         TEXT                                       COMMENT '扩展信息1(JSON格式)',  
  extend_info2         TEXT                                       COMMENT '扩展信息2(JSON格式)',
  extend_info3         TEXT                                       COMMENT '扩展信息3(JSON格式)',
  extend_info4         TEXT                                       COMMENT '扩展信息4(JSON格式)',
  extend_info5         TEXT                                       COMMENT '扩展信息5(JSON格式)',
  build_by             VARCHAR(64)     DEFAULT ''                 COMMENT '创建者',
  build_time           DATETIME        DEFAULT CURRENT_TIMESTAMP  COMMENT '创建时间',
  modify_by            VARCHAR(64)     DEFAULT ''                 COMMENT '更新者',
  modify_time          DATETIME        DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  del_flag             CHAR(1)         DEFAULT '0'                COMMENT '删除标志（0代表存在 1代表删除）',
  remark               VARCHAR(500)    DEFAULT NULL               COMMENT '备注',
  
  PRIMARY KEY (file_id),
  KEY idx_experiment_id (experiment_id),
  KEY idx_record_id (record_id),
  KEY idx_file_type (file_type),
  KEY idx_file_category (file_category),
  KEY idx_parent_file_id (parent_file_id)
) ENGINE=INNODB AUTO_INCREMENT=1 COMMENT = '实验文件管理表';

-- ----------------------------
-- 初始化数据和索引优化
-- ----------------------------

-- 插入测试实验记录数据
INSERT INTO sys_experiment_record 
(experiment_id, record_date, record_title, record_content, record_type, 
 experiment_status, total_cages, active_cages, total_mice, active_mice, 
 healthy_mice, sick_mice, dead_mice, average_weight, 
 data_collected_today, data_collected_total, behavior_records, 
 issues_count, resolved_issues, completion_rate, daily_progress, 
 temperature, humidity, light_cycle, generated_by, build_by) 
VALUES 
(1, CURDATE(), CONCAT('实验记录-', DATE_FORMAT(CURDATE(), '%Y-%m-%d')), 
 '今日实验进展顺利，所有笼子和小鼠状态正常。观察到小鼠活动水平较高，食欲正常。', 'DAILY', 
 'RUNNING', 5, 5, 20, 18, 
 18, 0, 0, 25.5, 
 150, 3500, 45, 
 2, 1, 75.50, 5.20, 
 22.5, 65.0, '12:12', 'MANUAL', 'admin');

-- 添加索引优化
ALTER TABLE sys_experiment_record ADD INDEX idx_experiment_status (experiment_status);
ALTER TABLE sys_experiment_file ADD INDEX idx_file_status (file_status);

-- 创建视图：实验记录汇总视图
CREATE OR REPLACE VIEW v_experiment_record_summary AS
SELECT 
    r.record_id,
    r.experiment_id,
    r.record_date,
    r.record_title,
    r.record_type,
    r.experiment_status,
    r.total_mice,
    r.active_mice,
    r.completion_rate,
    COUNT(f.file_id) as file_count,
    SUM(f.file_size) as total_file_size,
    r.build_time
FROM sys_experiment_record r
LEFT JOIN sys_experiment_file f ON r.record_id = f.record_id AND f.del_flag = '0'
GROUP BY r.record_id;

-- 创建视图：实验文件统计视图
CREATE OR REPLACE VIEW v_experiment_file_stats AS
SELECT 
    experiment_id,
    COUNT(*) as total_files,
    SUM(file_size) as total_size,
    COUNT(CASE WHEN file_type = 'IMAGE' THEN 1 END) as image_count,
    COUNT(CASE WHEN file_type = 'VIDEO' THEN 1 END) as video_count,
    COUNT(CASE WHEN file_type = 'DOCUMENT' THEN 1 END) as document_count,
    COUNT(CASE WHEN file_type = 'DATA' THEN 1 END) as data_count,
    COUNT(CASE WHEN file_type = 'AUDIO' THEN 1 END) as audio_count
FROM sys_experiment_file 
WHERE del_flag = '0'
GROUP BY experiment_id;

-- 添加注释
ALTER TABLE sys_experiment_record COMMENT = '实验记录表 - 支持富文本内容和多媒体文件关联的实验日报系统';
ALTER TABLE sys_experiment_file COMMENT = '实验文件管理表 - 支持图片、视频、文档等多媒体文件管理';

-- 完成提示
SELECT '实验记录系统数据库优化完成！' as message;
SELECT '包含实验记录表和优化后的文件管理表' as tables;
SELECT '支持富文本记录和多媒体文件管理' as features;

-- ----------------------------
-- 7、更新现有小鼠表，增加笼子关联
-- ----------------------------
ALTER TABLE t_mouse ADD COLUMN cage_id BIGINT(20) DEFAULT NULL COMMENT '笼子ID' AFTER experiment_id;
ALTER TABLE t_mouse ADD KEY idx_cage_id (cage_id);

-- ----------------------------
-- 初始化实验模板数据
-- ----------------------------
INSERT INTO sys_experiment_template VALUES
(1, 'TPL001', '标准行为监测实验', 'BEHAVIOR', '用于监测小鼠日常行为模式的标准实验模板', 7, 
 '{"monitoring_duration":"24h","sampling_rate":"1fps","behaviors":["sleeping","eating","moving","grooming"]}', 
'["camera","sensor"]', '0', 'admin', NOW(), '', NULL, '0', '标准行为监测实验模板');

INSERT INTO sys_experiment_template VALUES
(2, 'TPL002', '药物效应评估实验', 'DRUG_TEST', '用于评估药物对小鼠行为影响的实验模板', 14, 
'{"monitoring_duration":"24h","sampling_rate":"2fps","drug_administration":true,"control_group":true}', 
'["camera","sensor","dispenser"]', '0', 'admin', NOW(), '', NULL, '0', '药物效应评估实验模板');

-- ----------------------------
-- 初始化笼子数据
-- ----------------------------
INSERT INTO sys_cage VALUES
(1, 'CAGE001', '实验室A-1号笼子', 'STANDARD', '实验室A', 'RACK01', 1, 1, 5, 0, 'AVAILABLE', '20-25', '40-70', 
'{"cage_id":"CAGE001","type":"standard"}', NULL, 'admin', NOW(), '', NULL, '0', '标准实验笼子');

INSERT INTO sys_cage VALUES
(2, 'CAGE002', '实验室A-2号笼子', 'STANDARD', '实验室A', 'RACK01', 1, 2, 5, 0, 'AVAILABLE', '20-25', '40-70', 
'{"cage_id":"CAGE002","type":"standard"}', NULL, 'admin', NOW(), '', NULL, '0', '标准实验笼子');

INSERT INTO sys_cage VALUES
(3, 'CAGE003', '实验室B-1号笼子', 'LARGE', '实验室B', 'RACK02', 1, 1, 8, 0, 'AVAILABLE', '18-23', '35-65', 
'{"cage_id":"CAGE003","type":"large"}', NULL, 'admin', NOW(), '', NULL, '0', '大型实验笼子');

-- ----------------------------
-- 初始化示例实验数据
-- ----------------------------
INSERT INTO sys_experiment VALUES
(1, 'EXP001', '小鼠睡眠行为研究', 'BEHAVIOR', '标准行为监测实验', '研究小鼠的睡眠模式和昼夜节律', 
'通过24小时连续监测，分析小鼠的睡眠行为特征', 'READY', 
'2025-01-20 09:00:00', '2025-01-27 18:00:00', NULL, NULL, 7, 
1, '[2]', '行为研究组', 
'{"monitoring_hours":24,"analysis_interval":1,"behaviors":["sleeping","active"]}', 
'{"experiment_id":"EXP001","type":"behavior_study"}', NULL, 
2, 0, 'admin', NOW(), '', NULL, '0', '小鼠睡眠行为研究实验');

-- ----------------------------
-- 初始化实验笼子关系数据
-- ----------------------------
INSERT INTO sys_experiment_cage VALUES
(1, 1, 1, NOW(), NULL, 'ACTIVE', 'MANUAL', 'admin', 'EXPERIMENTAL', 
'{"monitoring_enabled":true,"recording_quality":"high"}', 
'admin', NOW(), '', NULL, '实验组笼子');

INSERT INTO sys_experiment_cage VALUES
(2, 1, 2, NOW(), NULL, 'ACTIVE', 'MANUAL', 'admin', 'CONTROL', 
'{"monitoring_enabled":true,"recording_quality":"high"}', 
'admin', NOW(), '', NULL, '对照组笼子');

-- ----------------------------
-- 创建视图：实验概览视图
-- ----------------------------
CREATE OR REPLACE VIEW v_experiment_overview AS
WITH experiment_stats AS (
    SELECT 
        e.experiment_id,
        COUNT(DISTINCT ec.cage_id) as cage_count,
        COUNT(DISTINCT m.id) as mouse_count
    FROM sys_experiment e
    LEFT JOIN sys_experiment_cage ec ON e.experiment_id = ec.experiment_id AND ec.bind_status = 'ACTIVE'
    LEFT JOIN t_mouse m ON e.experiment_id = m.experiment_id
    WHERE e.del_flag = '0'
    GROUP BY e.experiment_id
)
SELECT 
    e.experiment_id,
    e.experiment_code,
    e.experiment_name,
    e.experiment_type,
    e.experiment_status,
    e.planned_begin_time,
    e.planned_finish_time,
    e.actual_begin_time,
    e.actual_finish_time,
    u.user_name as principal_researcher_name,
    COALESCE(es.cage_count, 0) as total_cages,
    COALESCE(es.mouse_count, 0) as total_mice,
    e.build_time,
    e.modify_time
FROM sys_experiment e
LEFT JOIN sys_user u ON e.principal_researcher = u.user_id
LEFT JOIN experiment_stats es ON e.experiment_id = es.experiment_id
WHERE e.del_flag = '0'
ORDER BY e.build_time DESC;

-- ----------------------------
-- 创建索引优化查询性能
-- ----------------------------
CREATE INDEX idx_experiment_cage_bind_time ON sys_experiment_cage(bind_time);
CREATE INDEX idx_experiment_progress_stage ON sys_experiment_progress(progress_stage);
CREATE INDEX idx_experiment_file_build_time ON sys_experiment_file(build_time);

-- ----------------------------
-- 添加外键约束（可选，根据需要启用）
-- ----------------------------
-- ALTER TABLE sys_experiment_cage ADD CONSTRAINT fk_exp_cage_experiment FOREIGN KEY (experiment_id) REFERENCES sys_experiment(experiment_id);
-- ALTER TABLE sys_experiment_cage ADD CONSTRAINT fk_exp_cage_cage FOREIGN KEY (cage_id) REFERENCES sys_cage(cage_id);
-- ALTER TABLE sys_experiment_progress ADD CONSTRAINT fk_exp_progress_experiment FOREIGN KEY (experiment_id) REFERENCES sys_experiment(experiment_id);
-- ALTER TABLE sys_experiment_file ADD CONSTRAINT fk_exp_file_experiment FOREIGN KEY (experiment_id) REFERENCES sys_experiment(experiment_id);

-- ===========================
-- 表结构创建完成
-- 包含以下核心表：
-- 1. sys_experiment - 实验主表
-- 2. sys_experiment_template - 实验模板表
-- 3. sys_cage - 笼子管理表
-- 4. sys_experiment_cage - 实验笼子关系表
-- 5. sys_experiment_progress - 实验进度记录表
-- 6. sys_experiment_file - 实验文件管理表
-- 7. v_experiment_overview - 实验概览视图
-- ===========================