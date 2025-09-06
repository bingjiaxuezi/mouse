-- ----------------------------
-- 老鼠行为监测系统数据表
-- ----------------------------

-- ----------------------------
-- 1、设备表
-- ----------------------------
drop table if exists t_device;
create table t_device (
  id                bigint(20)      not null auto_increment    comment '设备ID',
  device_code       varchar(50)     not null                   comment '设备编号',
  device_name       varchar(100)    not null                   comment '设备名称',
  device_type       varchar(50)     not null                   comment '设备类型',
  location          varchar(200)    default ''                 comment '设备位置',
  status            char(1)         default '0'                comment '设备状态（0正常 1故障 2维护）',
  create_by         varchar(64)     default ''                 comment '创建者',
  create_time       datetime                                   comment '创建时间',
  update_by         varchar(64)     default ''                 comment '更新者',
  update_time       datetime                                   comment '更新时间',
  remark            varchar(500)    default null               comment '备注',
  primary key (id),
  unique key uk_device_code (device_code)
) engine=innodb auto_increment=1 comment = '设备表';

-- ----------------------------
-- 2、实验表
-- ----------------------------
drop table if exists t_experiment;
create table t_experiment (
  id                bigint(20)      not null auto_increment    comment '实验ID',
  experiment_name   varchar(100)    not null                   comment '实验名称',
  experiment_code   varchar(50)     not null                   comment '实验编号',
  description       text                                       comment '实验描述',
  researcher_id     bigint(20)      not null                   comment '研究员ID',
  status            char(1)         default '0'                comment '实验状态（0进行中 1已完成 2已暂停）',
  create_by         varchar(64)     default ''                 comment '创建者',
  create_time       datetime                                   comment '创建时间',
  update_by         varchar(64)     default ''                 comment '更新者',
  update_time       datetime                                   comment '更新时间',
  remark            varchar(500)    default null               comment '备注',
  primary key (id),
  unique key uk_experiment_code (experiment_code),
  key idx_researcher_id (researcher_id)
) engine=innodb auto_increment=1 comment = '实验表';

-- ----------------------------
-- 3、老鼠表
-- ----------------------------
drop table if exists t_mouse;
create table t_mouse (
  id                bigint(20)      not null auto_increment    comment '老鼠ID',
  mouse_code        varchar(50)     not null                   comment '老鼠编号',
  mouse_name        varchar(100)    not null                   comment '老鼠名称',
  species           varchar(50)     not null                   comment '物种',
  gender            char(1)         not null                   comment '性别（M雄性 F雌性）',
  birth_date        date                                       comment '出生日期',
  weight            decimal(8,2)    default null               comment '体重（克）',
  photo_url         varchar(500)    default null               comment '照片URL',
  experiment_id     bigint(20)      default null               comment '实验ID',
  status            char(1)         default '0'                comment '状态（0正常 1死亡 2转移）',
  create_by         varchar(64)     default ''                 comment '创建者',
  create_time       datetime                                   comment '创建时间',
  update_by         varchar(64)     default ''                 comment '更新者',
  update_time       datetime                                   comment '更新时间',
  remark            varchar(500)    default null               comment '备注',
  primary key (id),
  unique key uk_mouse_code (mouse_code),
  key idx_experiment_id (experiment_id)
) engine=innodb auto_increment=1 comment = '老鼠表';

-- ----------------------------
-- 4、行为数据表
-- ----------------------------
drop table if exists t_behavior_data;
create table t_behavior_data (
  id                bigint(20)      not null auto_increment    comment '行为数据ID',
  device_code       varchar(50)     not null                   comment '设备编号',
  mouse_code        varchar(50)     not null                   comment '老鼠编号',
  experiment_id     bigint(20)      not null                   comment '实验ID',
  behavior_type     varchar(50)     not null                   comment '行为类型',
  behavior_value    varchar(200)    not null                   comment '行为值',
  confidence        decimal(5,4)    default null               comment '置信度',
  timestamp         datetime        not null                   comment '时间戳',
  create_by         varchar(64)     default ''                 comment '创建者',
  create_time       datetime                                   comment '创建时间',
  update_by         varchar(64)     default ''                 comment '更新者',
  update_time       datetime                                   comment '更新时间',
  remark            varchar(500)    default null               comment '备注',
  primary key (id),
  key idx_device_code (device_code),
  key idx_mouse_code (mouse_code),
  key idx_experiment_id (experiment_id),
  key idx_timestamp (timestamp)
) engine=innodb auto_increment=1 comment = '行为数据表';

-- ----------------------------
-- 初始化测试数据
-- ----------------------------

-- 插入测试设备数据
insert into t_device values(1, 'DEV001', '行为监测摄像头1号', '摄像头', '实验室A-1号笼子', '0', 'admin', sysdate(), '', null, '用于监测老鼠行为的高清摄像头');
insert into t_device values(2, 'DEV002', '行为监测摄像头2号', '摄像头', '实验室A-2号笼子', '0', 'admin', sysdate(), '', null, '用于监测老鼠行为的高清摄像头');
insert into t_device values(3, 'DEV003', '温度传感器1号', '传感器', '实验室A', '0', 'admin', sysdate(), '', null, '监测实验室温度');

-- 插入测试实验数据
insert into t_experiment values(1, '老鼠行为模式研究', 'EXP001', '研究老鼠在不同环境下的行为模式变化', 1, '0', 'admin', sysdate(), '', null, '为期3个月的行为研究实验');
insert into t_experiment values(2, '老鼠学习能力测试', 'EXP002', '测试老鼠的学习和记忆能力', 1, '0', 'admin', sysdate(), '', null, '迷宫学习实验');

-- 插入测试老鼠数据
insert into t_mouse values(1, 'M001', '小白1号', 'C57BL/6', 'M', '2024-01-01', 25.5, 1, '0', 'admin', sysdate(), '', null, '健康的雄性实验鼠');
insert into t_mouse values(2, 'M002', '小白2号', 'C57BL/6', 'F', '2024-01-01', 22.3, 1, '0', 'admin', sysdate(), '', null, '健康的雌性实验鼠');
insert into t_mouse values(3, 'M003', '小白3号', 'BALB/c', 'M', '2024-01-15', 24.8, 2, '0', 'admin', sysdate(), '', null, '用于学习能力测试的雄性实验鼠');

-- 插入测试行为数据
insert into t_behavior_data values(1, 'DEV001', 'M001', 1, '移动', '坐标(100,200)', 0.95, '2024-01-20 10:30:00', 'admin', sysdate(), '', null, 'AI识别的移动行为');
insert into t_behavior_data values(2, 'DEV001', 'M001', 1, '进食', '持续时间30秒', 0.88, '2024-01-20 11:00:00', 'admin', sysdate(), '', null, 'AI识别的进食行为');
insert into t_behavior_data values(3, 'DEV002', 'M002', 1, '休息', '持续时间120秒', 0.92, '2024-01-20 11:30:00', 'admin', sysdate(), '', null, 'AI识别的休息行为');