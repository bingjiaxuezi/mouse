-- =============================================
-- A001 实验全链路数据插入脚本（去重、安全可重复执行）
-- 目标：串联 实验 → 实验笼 → 小鼠 → 行为记录 → 媒体 四级关系
-- 说明：
-- 1) 使用 INSERT ... SELECT + NOT EXISTS 或 INSERT IGNORE 避免重复插入
-- 2) 不硬编码ID，通过业务键解析（experiment_code、session_code、mouse_code 等）
-- 3) 已知：A001 关联笼子 CAGE003(cage_id=6, 实验组) 与 CAGE004(cage_id=7, 对照组)
-- =============================================

SET NAMES utf8mb4;
USE mouse_behavior;

-- =============================
-- 0) 实验记录（按需插入）
-- =============================
INSERT INTO sys_experiment 
 (experiment_code, experiment_name, experiment_type, experiment_template, experiment_objective, experiment_desc, experiment_status, planned_begin_time, planned_finish_time, actual_begin_time, actual_finish_time, experiment_duration, principal_researcher, co_researchers, experiment_team, experiment_config, qr_code_content, qr_code_image_url, total_cages, total_mice, build_by, build_time, modify_by, modify_time, del_flag, remark)
SELECT 'A001', '喵喵实验', 'drag_exp', '1234567', '请问是对方', '妈咪妈咪哄', 'DRAFT', '2025-09-08 08:00:00', '2025-09-10 08:00:00', '2025-09-08 08:00:00', '2025-09-10 08:00:00', 0, 88, '88', '88', '88', NULL, '2345678', 2, 8, 'admin', NOW(), '', NOW(), '0', 'A001初始化记录'
WHERE NOT EXISTS (SELECT 1 FROM sys_experiment WHERE experiment_code = 'A001');

-- 0.1) 绑定实验与笼子（按需插入）
INSERT INTO sys_experiment_cage (experiment_id, cage_id, bind_time, bind_status, cage_role, build_by, build_time, remark)
SELECT e.experiment_id, 6, NOW(), 'ACTIVE', 'EXPERIMENTAL', 'admin', NOW(), 'A001绑定CAGE003(实验组)'
FROM sys_experiment e
WHERE e.experiment_code = 'A001'
  AND NOT EXISTS (
      SELECT 1 FROM sys_experiment_cage ec
      WHERE ec.experiment_id = e.experiment_id AND ec.cage_id = 6 AND ec.bind_status = 'ACTIVE'
  );

INSERT INTO sys_experiment_cage (experiment_id, cage_id, bind_time, bind_status, cage_role, build_by, build_time, remark)
SELECT e.experiment_id, 7, NOW(), 'ACTIVE', 'CONTROL', 'admin', NOW(), 'A001绑定CAGE004(对照组)'
FROM sys_experiment e
WHERE e.experiment_code = 'A001'
  AND NOT EXISTS (
      SELECT 1 FROM sys_experiment_cage ec
      WHERE ec.experiment_id = e.experiment_id AND ec.cage_id = 7 AND ec.bind_status = 'ACTIVE'
  );

-- =============================
-- 1) 小鼠基础数据（8只；4实验组/4对照组）
-- 字段取最小必需集：mouse_code, mouse_name, gender, species, strain, birth_date, status, health_status
-- 其余字段使用默认或留空
-- =============================
INSERT IGNORE INTO sys_mouse (mouse_code, mouse_name, gender, species, strain, birth_date, status, health_status, create_by, create_time, update_by, update_time, del_flag, remark)
VALUES
 ('A001-M-EXP-01', 'A001-实验-01', 'M', 'Mus musculus', 'C57BL/6', '2024-10-01', '0', 'HEALTHY', 'admin', NOW(), '', NOW(), '0', 'A001实验组'),
 ('A001-M-EXP-02', 'A001-实验-02', 'F', 'Mus musculus', 'C57BL/6', '2024-10-01', '0', 'HEALTHY', 'admin', NOW(), '', NOW(), '0', 'A001实验组'),
 ('A001-M-EXP-03', 'A001-实验-03', 'M', 'Mus musculus', 'C57BL/6', '2024-09-15', '0', 'HEALTHY', 'admin', NOW(), '', NOW(), '0', 'A001实验组'),
 ('A001-M-EXP-04', 'A001-实验-04', 'F', 'Mus musculus', 'C57BL/6', '2024-09-15', '0', 'HEALTHY', 'admin', NOW(), '', NOW(), '0', 'A001实验组'),
 ('A001-M-CTL-01', 'A001-对照-01', 'M', 'Mus musculus', 'C57BL/6', '2024-10-05', '0', 'HEALTHY', 'admin', NOW(), '', NOW(), '0', 'A001对照组'),
 ('A001-M-CTL-02', 'A001-对照-02', 'F', 'Mus musculus', 'C57BL/6', '2024-10-05', '0', 'HEALTHY', 'admin', NOW(), '', NOW(), '0', 'A001对照组'),
 ('A001-M-CTL-03', 'A001-对照-03', 'M', 'Mus musculus', 'C57BL/6', '2024-09-20', '0', 'HEALTHY', 'admin', NOW(), '', NOW(), '0', 'A001对照组'),
 ('A001-M-CTL-04', 'A001-对照-04', 'F', 'Mus musculus', 'C57BL/6', '2024-09-20', '0', 'HEALTHY', 'admin', NOW(), '', NOW(), '0', 'A001对照组');

-- =============================
-- 2) 小鼠入笼关系（sys_cage_mouse）：
-- CAGE003(cage_id=6) ← 实验组 4只；CAGE004(cage_id=7) ← 对照组 4只
-- 使用 INSERT IGNORE 通过 mouse_code 解析 mouse_id，设置 is_current=1
-- =============================
-- 实验组入笼
INSERT IGNORE INTO sys_cage_mouse (cage_id, mouse_id, move_in_date, is_current, social_group, create_by, create_time, remark)
SELECT 6, m.mouse_id, '2025-09-08', 1, 'EXPERIMENTAL', 'admin', NOW(), 'A001实验组入笼'
FROM sys_mouse m
WHERE m.mouse_code IN ('A001-M-EXP-01','A001-M-EXP-02','A001-M-EXP-03','A001-M-EXP-04');

-- 对照组入笼
INSERT IGNORE INTO sys_cage_mouse (cage_id, mouse_id, move_in_date, is_current, social_group, create_by, create_time, remark)
SELECT 7, m.mouse_id, '2025-09-08', 1, 'CONTROL', 'admin', NOW(), 'A001对照组入笼'
FROM sys_mouse m
WHERE m.mouse_code IN ('A001-M-CTL-01','A001-M-CTL-02','A001-M-CTL-03','A001-M-CTL-04');

-- 更新笼子当前计数
UPDATE sys_cage SET current_count = (
  SELECT COUNT(*) FROM sys_cage_mouse cm WHERE cm.cage_id = 6 AND cm.is_current = 1 AND cm.del_flag = '0'
) WHERE cage_id = 6;

UPDATE sys_cage SET current_count = (
  SELECT COUNT(*) FROM sys_cage_mouse cm WHERE cm.cage_id = 7 AND cm.is_current = 1 AND cm.del_flag = '0'
) WHERE cage_id = 7;

-- =============================
-- 3) 行为会话（每个笼子1个会话，关联实验ID与笼子ID）
-- 字段参考：session_code, experiment_id, cage_id, session_start_time, session_end_time,
--           planned_duration, session_status, session_type, session_config
-- =============================
INSERT INTO sys_behavior_session (
  session_code, experiment_id, cage_id, session_start_time, session_end_time,
  planned_duration, session_status, session_type, session_config, build_by, build_time, remark
)
SELECT 'A001_CAGE003_S1', e.experiment_id, 6, '2025-09-09 09:00:00', '2025-09-09 10:00:00', 60, 'COMPLETED', 'EXPERIMENT', '{"fps":30,"resolution":"1280x720"}', 'admin', NOW(), 'A001实验组行为会话'
FROM sys_experiment e
WHERE e.experiment_code = 'A001'
  AND NOT EXISTS (SELECT 1 FROM sys_behavior_session WHERE session_code = 'A001_CAGE003_S1');

INSERT INTO sys_behavior_session (
  session_code, experiment_id, cage_id, session_start_time, session_end_time,
  planned_duration, session_status, session_type, session_config, build_by, build_time, remark
)
SELECT 'A001_CAGE004_S1', e.experiment_id, 7, '2025-09-09 09:00:00', '2025-09-09 10:00:00', 60, 'COMPLETED', 'CONTROL', '{"fps":30,"resolution":"1280x720"}', 'admin', NOW(), 'A001对照组行为会话'
FROM sys_experiment e
WHERE e.experiment_code = 'A001'
  AND NOT EXISTS (SELECT 1 FROM sys_behavior_session WHERE session_code = 'A001_CAGE004_S1');

-- =============================
-- 4) 行为事件（每只小鼠2条事件，类型：1(LOCOMOTION),6(FEEDING),8(GROOMING),11(REST),13(EXPLORATION)）
-- 使用 INSERT ... SELECT + NOT EXISTS 解析 session_id 与 mouse_id 并去重
-- =============================
-- 实验组 - 会话 A001_CAGE003_S1
INSERT INTO sys_behavior_event (
  session_id, mouse_id, behavior_type_id, 
  event_start_time, event_end_time, duration_seconds,
  intensity_level, confidence_score, detection_method,
  start_x_coordinate, start_y_coordinate, end_x_coordinate, end_y_coordinate,
  max_velocity, avg_velocity, max_acceleration, distance_traveled,
  interaction_target_mouse_id, interaction_type,
  anomaly_flag, quality_score, validation_status, validator_id, validation_time,
  extend_info, extend_config, extend_data, extend_info1, extend_info2, extend_info3, extend_info4, extend_info5,
  build_by, build_time, modify_by, modify_time, del_flag, remark
)
SELECT 
  s.session_id, m.mouse_id, 1,
  '2025-09-09 09:05:00', '2025-09-09 09:07:00', 120,
  'NORMAL', 0.920, 'CV',
  120.500, 85.300, 245.800, 156.700,
  15.200, 8.700, NULL, 187.300,
  NULL, NULL,
  '0', 0.910, 'VALIDATED', NULL, NULL,
  NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL,
  'admin', NOW(), '', NOW(), '0', '正常运动行为'
FROM sys_behavior_session s JOIN sys_mouse m 
  ON s.session_code = 'A001_CAGE003_S1' AND m.mouse_code = 'A001-M-EXP-01'
WHERE NOT EXISTS (
  SELECT 1 FROM sys_behavior_event e2 
  WHERE e2.session_id = s.session_id AND e2.mouse_id = m.mouse_id AND e2.behavior_type_id = 1 AND e2.event_start_time = '2025-09-09 09:05:00'
);

INSERT INTO sys_behavior_event (
  session_id, mouse_id, behavior_type_id, 
  event_start_time, event_end_time, duration_seconds,
  intensity_level, confidence_score, detection_method,
  start_x_coordinate, start_y_coordinate, end_x_coordinate, end_y_coordinate,
  max_velocity, avg_velocity, max_acceleration, distance_traveled,
  interaction_target_mouse_id, interaction_type,
  anomaly_flag, quality_score, validation_status, validator_id, validation_time,
  extend_info, extend_config, extend_data, extend_info1, extend_info2, extend_info3, extend_info4, extend_info5,
  build_by, build_time, modify_by, modify_time, del_flag, remark
)
SELECT 
  s.session_id, m.mouse_id, 6,
  '2025-09-09 09:15:00', '2025-09-09 09:18:00', 180,
  'HIGH', 0.900, 'CV',
  200.100, 120.400, 180.600, 95.200,
  22.100, 12.300, NULL, 89.400,
  NULL, NULL,
  '0', 0.890, 'VALIDATED', NULL, NULL,
  NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL,
  'admin', NOW(), '', NOW(), '0', '进食行为'
FROM sys_behavior_session s JOIN sys_mouse m 
  ON s.session_code = 'A001_CAGE003_S1' AND m.mouse_code = 'A001-M-EXP-01'
WHERE NOT EXISTS (
  SELECT 1 FROM sys_behavior_event e2 
  WHERE e2.session_id = s.session_id AND e2.mouse_id = m.mouse_id AND e2.behavior_type_id = 6 AND e2.event_start_time = '2025-09-09 09:15:00'
);

INSERT INTO sys_behavior_event (
  session_id, mouse_id, behavior_type_id, 
  event_start_time, event_end_time, duration_seconds,
  intensity_level, confidence_score, detection_method,
  start_x_coordinate, start_y_coordinate, end_x_coordinate, end_y_coordinate,
  max_velocity, avg_velocity, max_acceleration, distance_traveled,
  interaction_target_mouse_id, interaction_type,
  anomaly_flag, quality_score, validation_status, validator_id, validation_time,
  extend_info, extend_config, extend_data, extend_info1, extend_info2, extend_info3, extend_info4, extend_info5,
  build_by, build_time, modify_by, modify_time, del_flag, remark
)
SELECT 
  s.session_id, m.mouse_id, 13,
  '2025-09-09 09:10:00', '2025-09-09 09:14:00', 240,
  'LOW', 0.880, 'CV',
  150.200, 100.100, 165.800, 110.500,
  8.500, 4.200, NULL, 45.700,
  NULL, NULL,
  '0', 0.870, 'VALIDATED', NULL, NULL,
  NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL,
  'admin', NOW(), '', NOW(), '0', '探索行为'
FROM sys_behavior_session s JOIN sys_mouse m 
  ON s.session_code = 'A001_CAGE003_S1' AND m.mouse_code = 'A001-M-EXP-02'
WHERE NOT EXISTS (
  SELECT 1 FROM sys_behavior_event e2 
  WHERE e2.session_id = s.session_id AND e2.mouse_id = m.mouse_id AND e2.behavior_type_id = 13 AND e2.event_start_time = '2025-09-09 09:10:00'
);

INSERT INTO sys_behavior_event (
  session_id, mouse_id, behavior_type_id, 
  event_start_time, event_end_time, duration_seconds,
  intensity_level, confidence_score, detection_method,
  start_x_coordinate, start_y_coordinate, end_x_coordinate, end_y_coordinate,
  max_velocity, avg_velocity, max_acceleration, distance_traveled,
  interaction_target_mouse_id, interaction_type,
  anomaly_flag, quality_score, validation_status, validator_id, validation_time,
  extend_info, extend_config, extend_data, extend_info1, extend_info2, extend_info3, extend_info4, extend_info5,
  build_by, build_time, modify_by, modify_time, del_flag, remark
)
SELECT 
  s.session_id, m.mouse_id, 8,
  '2025-09-09 09:25:00', '2025-09-09 09:26:30', 90,
  'NORMAL', 0.910, 'CV',
  98.400, 76.200, 105.300, 80.100,
  6.800, 3.900, NULL, 12.400,
  NULL, NULL,
  '0', 0.900, 'VALIDATED', NULL, NULL,
  NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL,
  'admin', NOW(), '', NOW(), '0', '梳理行为'
FROM sys_behavior_session s JOIN sys_mouse m 
  ON s.session_code = 'A001_CAGE003_S1' AND m.mouse_code = 'A001-M-EXP-02'
WHERE NOT EXISTS (
  SELECT 1 FROM sys_behavior_event e2 
  WHERE e2.session_id = s.session_id AND e2.mouse_id = m.mouse_id AND e2.behavior_type_id = 8 AND e2.event_start_time = '2025-09-09 09:25:00'
);

INSERT INTO sys_behavior_event (
  session_id, mouse_id, behavior_type_id, 
  event_start_time, event_end_time, duration_seconds,
  intensity_level, confidence_score, detection_method,
  start_x_coordinate, start_y_coordinate, end_x_coordinate, end_y_coordinate,
  max_velocity, avg_velocity, max_acceleration, distance_traveled,
  interaction_target_mouse_id, interaction_type,
  anomaly_flag, quality_score, validation_status, validator_id, validation_time,
  extend_info, extend_config, extend_data, extend_info1, extend_info2, extend_info3, extend_info4, extend_info5,
  build_by, build_time, modify_by, modify_time, del_flag, remark
)
SELECT 
  s.session_id, m.mouse_id, 1,
  '2025-09-09 09:20:00', '2025-09-09 09:22:30', 150,
  'HIGH', 0.890, 'CV',
  210.000, 140.000, 212.000, 141.500,
  12.900, 7.600, NULL, 32.800,
  NULL, NULL,
  '0', 0.880, 'VALIDATED', NULL, NULL,
  NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL,
  'admin', NOW(), '', NOW(), '0', '运动行为'
FROM sys_behavior_session s JOIN sys_mouse m 
  ON s.session_code = 'A001_CAGE003_S1' AND m.mouse_code = 'A001-M-EXP-03'
WHERE NOT EXISTS (
  SELECT 1 FROM sys_behavior_event e2 
  WHERE e2.session_id = s.session_id AND e2.mouse_id = m.mouse_id AND e2.behavior_type_id = 1 AND e2.event_start_time = '2025-09-09 09:20:00'
);

INSERT INTO sys_behavior_event (
  session_id, mouse_id, behavior_type_id, 
  event_start_time, event_end_time, duration_seconds,
  intensity_level, confidence_score, detection_method,
  start_x_coordinate, start_y_coordinate, end_x_coordinate, end_y_coordinate,
  max_velocity, avg_velocity, max_acceleration, distance_traveled,
  interaction_target_mouse_id, interaction_type,
  anomaly_flag, quality_score, validation_status, validator_id, validation_time,
  extend_info, extend_config, extend_data, extend_info1, extend_info2, extend_info3, extend_info4, extend_info5,
  build_by, build_time, modify_by, modify_time, del_flag, remark
)
SELECT 
  s.session_id, m.mouse_id, 11,
  '2025-09-09 09:40:00', '2025-09-09 09:45:00', 300,
  'LOW', 0.870, 'CV',
  205.300, 130.800, 206.100, 131.200,
  3.200, 1.800, NULL, 4.100,
  NULL, NULL,
  '0', 0.860, 'VALIDATED', NULL, NULL,
  NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL,
  'admin', NOW(), '', NOW(), '0', '静息行为'
FROM sys_behavior_session s JOIN sys_mouse m 
  ON s.session_code = 'A001_CAGE003_S1' AND m.mouse_code = 'A001-M-EXP-03'
WHERE NOT EXISTS (
  SELECT 1 FROM sys_behavior_event e2 
  WHERE e2.session_id = s.session_id AND e2.mouse_id = m.mouse_id AND e2.behavior_type_id = 11 AND e2.event_start_time = '2025-09-09 09:40:00'
);

INSERT INTO sys_behavior_event (
  session_id, mouse_id, behavior_type_id, 
  event_start_time, event_end_time, duration_seconds,
  intensity_level, confidence_score, detection_method,
  start_x_coordinate, start_y_coordinate, end_x_coordinate, end_y_coordinate,
  max_velocity, avg_velocity, max_acceleration, distance_traveled,
  interaction_target_mouse_id, interaction_type,
  anomaly_flag, quality_score, validation_status, validator_id, validation_time,
  extend_info, extend_config, extend_data, extend_info1, extend_info2, extend_info3, extend_info4, extend_info5,
  build_by, build_time, modify_by, modify_time, del_flag, remark
)
SELECT 
  s.session_id, m.mouse_id, 6,
  '2025-09-09 09:30:00', '2025-09-09 09:32:00', 120,
  'HIGH', 0.930, 'CV',
  190.700, 118.400, 175.200, 102.900,
  21.500, 11.900, NULL, 85.600,
  NULL, NULL,
  '0', 0.920, 'VALIDATED', NULL, NULL,
  NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL,
  'admin', NOW(), '', NOW(), '0', '进食行为'
FROM sys_behavior_session s JOIN sys_mouse m 
  ON s.session_code = 'A001_CAGE003_S1' AND m.mouse_code = 'A001-M-EXP-04'
WHERE NOT EXISTS (
  SELECT 1 FROM sys_behavior_event e2 
  WHERE e2.session_id = s.session_id AND e2.mouse_id = m.mouse_id AND e2.behavior_type_id = 6 AND e2.event_start_time = '2025-09-09 09:30:00'
);

INSERT INTO sys_behavior_event (
  session_id, mouse_id, behavior_type_id, 
  event_start_time, event_end_time, duration_seconds,
  intensity_level, confidence_score, detection_method,
  start_x_coordinate, start_y_coordinate, end_x_coordinate, end_y_coordinate,
  max_velocity, avg_velocity, max_acceleration, distance_traveled,
  interaction_target_mouse_id, interaction_type,
  anomaly_flag, quality_score, validation_status, validator_id, validation_time,
  extend_info, extend_config, extend_data, extend_info1, extend_info2, extend_info3, extend_info4, extend_info5,
  build_by, build_time, modify_by, modify_time, del_flag, remark
)
SELECT 
  s.session_id, m.mouse_id, 8,
  '2025-09-09 09:45:00', '2025-09-09 09:46:30', 90,
  'NORMAL', 0.900, 'CV',
  110.200, 82.600, 116.900, 87.300,
  7.400, 4.100, NULL, 14.900,
  NULL, NULL,
  '0', 0.890, 'VALIDATED', NULL, NULL,
  NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL,
  'admin', NOW(), '', NOW(), '0', '梳理行为'
FROM sys_behavior_session s JOIN sys_mouse m 
  ON s.session_code = 'A001_CAGE003_S1' AND m.mouse_code = 'A001-M-EXP-04'
WHERE NOT EXISTS (
  SELECT 1 FROM sys_behavior_event e2 
  WHERE e2.session_id = s.session_id AND e2.mouse_id = m.mouse_id AND e2.behavior_type_id = 8 AND e2.event_start_time = '2025-09-09 09:45:00'
);

-- 对照组 - 会话 A001_CAGE004_S1
INSERT INTO sys_behavior_event (
  session_id, mouse_id, behavior_type_id, 
  event_start_time, event_end_time, duration_seconds,
  intensity_level, confidence_score, detection_method,
  start_x_coordinate, start_y_coordinate, end_x_coordinate, end_y_coordinate,
  max_velocity, avg_velocity, max_acceleration, distance_traveled,
  interaction_target_mouse_id, interaction_type,
  anomaly_flag, quality_score, validation_status, validator_id, validation_time,
  extend_info, extend_config, extend_data, extend_info1, extend_info2, extend_info3, extend_info4, extend_info5,
  build_by, build_time, modify_by, modify_time, del_flag, remark
)
SELECT 
  s.session_id, m.mouse_id, 11,
  '2025-09-09 09:05:00', '2025-09-09 09:10:00', 300,
  'LOW', 0.900, 'CV',
  208.400, 138.200, 209.500, 139.100,
  3.000, 1.600, NULL, 3.800,
  NULL, NULL,
  '0', 0.890, 'VALIDATED', NULL, NULL,
  NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL,
  'admin', NOW(), '', NOW(), '0', '静息行为'
FROM sys_behavior_session s JOIN sys_mouse m 
  ON s.session_code = 'A001_CAGE004_S1' AND m.mouse_code = 'A001-M-CTL-01'
WHERE NOT EXISTS (
  SELECT 1 FROM sys_behavior_event e2 
  WHERE e2.session_id = s.session_id AND e2.mouse_id = m.mouse_id AND e2.behavior_type_id = 11 AND e2.event_start_time = '2025-09-09 09:05:00'
);

INSERT INTO sys_behavior_event (
  session_id, mouse_id, behavior_type_id, 
  event_start_time, event_end_time, duration_seconds,
  intensity_level, confidence_score, detection_method,
  start_x_coordinate, start_y_coordinate, end_x_coordinate, end_y_coordinate,
  max_velocity, avg_velocity, max_acceleration, distance_traveled,
  interaction_target_mouse_id, interaction_type,
  anomaly_flag, quality_score, validation_status, validator_id, validation_time,
  extend_info, extend_config, extend_data, extend_info1, extend_info2, extend_info3, extend_info4, extend_info5,
  build_by, build_time, modify_by, modify_time, del_flag, remark
)
SELECT 
  s.session_id, m.mouse_id, 6,
  '2025-09-09 09:20:00', '2025-09-09 09:22:30', 150,
  'HIGH', 0.880, 'CV',
  195.800, 122.300, 178.900, 101.700,
  20.200, 11.500, NULL, 82.100,
  NULL, NULL,
  '0', 0.870, 'VALIDATED', NULL, NULL,
  NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL,
  'admin', NOW(), '', NOW(), '0', '进食行为'
FROM sys_behavior_session s JOIN sys_mouse m 
  ON s.session_code = 'A001_CAGE004_S1' AND m.mouse_code = 'A001-M-CTL-01'
WHERE NOT EXISTS (
  SELECT 1 FROM sys_behavior_event e2 
  WHERE e2.session_id = s.session_id AND e2.mouse_id = m.mouse_id AND e2.behavior_type_id = 6 AND e2.event_start_time = '2025-09-09 09:20:00'
);

INSERT INTO sys_behavior_event (
  session_id, mouse_id, behavior_type_id, 
  event_start_time, event_end_time, duration_seconds,
  intensity_level, confidence_score, detection_method,
  start_x_coordinate, start_y_coordinate, end_x_coordinate, end_y_coordinate,
  max_velocity, avg_velocity, max_acceleration, distance_traveled,
  interaction_target_mouse_id, interaction_type,
  anomaly_flag, quality_score, validation_status, validator_id, validation_time,
  extend_info, extend_config, extend_data, extend_info1, extend_info2, extend_info3, extend_info4, extend_info5,
  build_by, build_time, modify_by, modify_time, del_flag, remark
)
SELECT 
  s.session_id, m.mouse_id, 1,
  '2025-09-09 09:10:00', '2025-09-09 09:13:00', 180,
  'NORMAL', 0.890, 'CV',
  118.900, 80.700, 130.100, 88.600,
  14.600, 7.900, NULL, 41.300,
  NULL, NULL,
  '0', 0.880, 'VALIDATED', NULL, NULL,
  NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL,
  'admin', NOW(), '', NOW(), '0', '运动行为'
FROM sys_behavior_session s JOIN sys_mouse m 
  ON s.session_code = 'A001_CAGE004_S1' AND m.mouse_code = 'A001-M-CTL-02'
WHERE NOT EXISTS (
  SELECT 1 FROM sys_behavior_event e2 
  WHERE e2.session_id = s.session_id AND e2.mouse_id = m.mouse_id AND e2.behavior_type_id = 1 AND e2.event_start_time = '2025-09-09 09:10:00'
);

INSERT INTO sys_behavior_event (
  session_id, mouse_id, behavior_type_id, 
  event_start_time, event_end_time, duration_seconds,
  intensity_level, confidence_score, detection_method,
  start_x_coordinate, start_y_coordinate, end_x_coordinate, end_y_coordinate,
  max_velocity, avg_velocity, max_acceleration, distance_traveled,
  interaction_target_mouse_id, interaction_type,
  anomaly_flag, quality_score, validation_status, validator_id, validation_time,
  extend_info, extend_config, extend_data, extend_info1, extend_info2, extend_info3, extend_info4, extend_info5,
  build_by, build_time, modify_by, modify_time, del_flag, remark
)
SELECT 
  s.session_id, m.mouse_id, 8,
  '2025-09-09 09:25:00', '2025-09-09 09:27:00', 120,
  'NORMAL', 0.920, 'CV',
  112.300, 78.900, 118.400, 84.100,
  8.300, 4.500, NULL, 16.300,
  NULL, NULL,
  '0', 0.910, 'VALIDATED', NULL, NULL,
  NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL,
  'admin', NOW(), '', NOW(), '0', '梳理行为'
FROM sys_behavior_session s JOIN sys_mouse m 
  ON s.session_code = 'A001_CAGE004_S1' AND m.mouse_code = 'A001-M-CTL-02'
WHERE NOT EXISTS (
  SELECT 1 FROM sys_behavior_event e2 
  WHERE e2.session_id = s.session_id AND e2.mouse_id = m.mouse_id AND e2.behavior_type_id = 8 AND e2.event_start_time = '2025-09-09 09:25:00'
);

INSERT INTO sys_behavior_event (
  session_id, mouse_id, behavior_type_id, 
  event_start_time, event_end_time, duration_seconds,
  intensity_level, confidence_score, detection_method,
  start_x_coordinate, start_y_coordinate, end_x_coordinate, end_y_coordinate,
  max_velocity, avg_velocity, max_acceleration, distance_traveled,
  interaction_target_mouse_id, interaction_type,
  anomaly_flag, quality_score, validation_status, validator_id, validation_time,
  extend_info, extend_config, extend_data, extend_info1, extend_info2, extend_info3, extend_info4, extend_info5,
  build_by, build_time, modify_by, modify_time, del_flag, remark
)
SELECT 
  s.session_id, m.mouse_id, 13,
  '2025-09-09 09:15:00', '2025-09-09 09:19:00', 240,
  'LOW', 0.910, 'CV',
  148.600, 99.200, 162.300, 108.000,
  9.200, 4.800, NULL, 47.900,
  NULL, NULL,
  '0', 0.900, 'VALIDATED', NULL, NULL,
  NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL,
  'admin', NOW(), '', NOW(), '0', '探索行为'
FROM sys_behavior_session s JOIN sys_mouse m 
  ON s.session_code = 'A001_CAGE004_S1' AND m.mouse_code = 'A001-M-CTL-03'
WHERE NOT EXISTS (
  SELECT 1 FROM sys_behavior_event e2 
  WHERE e2.session_id = s.session_id AND e2.mouse_id = m.mouse_id AND e2.behavior_type_id = 13 AND e2.event_start_time = '2025-09-09 09:15:00'
);

INSERT INTO sys_behavior_event (
  session_id, mouse_id, behavior_type_id, 
  event_start_time, event_end_time, duration_seconds,
  intensity_level, confidence_score, detection_method,
  start_x_coordinate, start_y_coordinate, end_x_coordinate, end_y_coordinate,
  max_velocity, avg_velocity, max_acceleration, distance_traveled,
  interaction_target_mouse_id, interaction_type,
  anomaly_flag, quality_score, validation_status, validator_id, validation_time,
  extend_info, extend_config, extend_data, extend_info1, extend_info2, extend_info3, extend_info4, extend_info5,
  build_by, build_time, modify_by, modify_time, del_flag, remark
)
SELECT 
  s.session_id, m.mouse_id, 6,
  '2025-09-09 09:35:00', '2025-09-09 09:37:00', 120,
  'HIGH', 0.900, 'CV',
  192.400, 121.100, 176.600, 104.800,
  21.000, 11.700, NULL, 84.200,
  NULL, NULL,
  '0', 0.890, 'VALIDATED', NULL, NULL,
  NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL,
  'admin', NOW(), '', NOW(), '0', '进食行为'
FROM sys_behavior_session s JOIN sys_mouse m 
  ON s.session_code = 'A001_CAGE004_S1' AND m.mouse_code = 'A001-M-CTL-03'
WHERE NOT EXISTS (
  SELECT 1 FROM sys_behavior_event e2 
  WHERE e2.session_id = s.session_id AND e2.mouse_id = m.mouse_id AND e2.behavior_type_id = 6 AND e2.event_start_time = '2025-09-09 09:35:00'
);

INSERT INTO sys_behavior_event (
  session_id, mouse_id, behavior_type_id, 
  event_start_time, event_end_time, duration_seconds,
  intensity_level, confidence_score, detection_method,
  start_x_coordinate, start_y_coordinate, end_x_coordinate, end_y_coordinate,
  max_velocity, avg_velocity, max_acceleration, distance_traveled,
  interaction_target_mouse_id, interaction_type,
  anomaly_flag, quality_score, validation_status, validator_id, validation_time,
  extend_info, extend_config, extend_data, extend_info1, extend_info2, extend_info3, extend_info4, extend_info5,
  build_by, build_time, modify_by, modify_time, del_flag, remark
)
SELECT 
  s.session_id, m.mouse_id, 1,
  '2025-09-09 09:20:00', '2025-09-09 09:23:30', 210,
  'NORMAL', 0.890, 'CV',
  116.200, 79.300, 128.800, 87.900,
  15.000, 8.100, NULL, 43.600,
  NULL, NULL,
  '0', 0.880, 'VALIDATED', NULL, NULL,
  NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL,
  'admin', NOW(), '', NOW(), '0', '运动行为'
FROM sys_behavior_session s JOIN sys_mouse m 
  ON s.session_code = 'A001_CAGE004_S1' AND m.mouse_code = 'A001-M-CTL-04'
WHERE NOT EXISTS (
  SELECT 1 FROM sys_behavior_event e2 
  WHERE e2.session_id = s.session_id AND e2.mouse_id = m.mouse_id AND e2.behavior_type_id = 1 AND e2.event_start_time = '2025-09-09 09:20:00'
);

INSERT INTO sys_behavior_event (
  session_id, mouse_id, behavior_type_id, 
  event_start_time, event_end_time, duration_seconds,
  intensity_level, confidence_score, detection_method,
  start_x_coordinate, start_y_coordinate, end_x_coordinate, end_y_coordinate,
  max_velocity, avg_velocity, max_acceleration, distance_traveled,
  interaction_target_mouse_id, interaction_type,
  anomaly_flag, quality_score, validation_status, validator_id, validation_time,
  extend_info, extend_config, extend_data, extend_info1, extend_info2, extend_info3, extend_info4, extend_info5,
  build_by, build_time, modify_by, modify_time, del_flag, remark
)
SELECT 
  s.session_id, m.mouse_id, 11,
  '2025-09-09 09:45:00', '2025-09-09 09:47:00', 120,
  'LOW', 0.880, 'CV',
  207.100, 137.400, 208.000, 138.000,
  2.800, 1.500, NULL, 3.200,
  NULL, NULL,
  '0', 0.870, 'VALIDATED', NULL, NULL,
  NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL,
  'admin', NOW(), '', NOW(), '0', '静息行为'
FROM sys_behavior_session s JOIN sys_mouse m 
  ON s.session_code = 'A001_CAGE004_S1' AND m.mouse_code = 'A001-M-CTL-04'
WHERE NOT EXISTS (
  SELECT 1 FROM sys_behavior_event e2 
  WHERE e2.session_id = s.session_id AND e2.mouse_id = m.mouse_id AND e2.behavior_type_id = 11 AND e2.event_start_time = '2025-09-09 09:45:00'
);

-- =============================
-- 4.1) 行为媒体文件（为上述会话的事件插入视频媒体，按需）
-- 规则：每个事件插入1个VIDEO，文件名按 A001_EVENT_<event_id>.mp4
-- =============================
INSERT INTO sys_behavior_media (
  event_id, file_name, file_path, file_format, duration_seconds, start_timestamp, end_timestamp,
  storage_type, media_type, build_by, build_time, remark
)
SELECT 
  e.event_id,
  CONCAT('A001_EVENT_', e.event_id, '.mp4'),
  CONCAT('/data/media/behavior/A001/', 'A001_EVENT_', e.event_id, '.mp4'),
  'mp4',
  e.duration_seconds,
  0.000,
  e.duration_seconds,
  'LOCAL',
  'VIDEO',
  'admin',
  NOW(),
  'A001行为事件视频文件'
FROM sys_behavior_event e
JOIN sys_behavior_session s ON s.session_id = e.session_id
WHERE s.session_code IN ('A001_CAGE003_S1','A001_CAGE004_S1')
  AND NOT EXISTS (
    SELECT 1 FROM sys_behavior_media md WHERE md.event_id = e.event_id AND md.media_type = 'VIDEO'
  );

-- =============================
-- 5) 会话统计修正（事件插入后更新）
-- =============================
UPDATE sys_behavior_session s
JOIN (
  SELECT session_id, COUNT(*) AS ev_cnt, COUNT(DISTINCT mouse_id) AS mouse_cnt
  FROM sys_behavior_event GROUP BY session_id
) x ON s.session_id = x.session_id
SET s.total_events = x.ev_cnt,
    s.total_mice   = x.mouse_cnt;

-- =============================
-- 6) 复核与验证查询
-- =============================
-- 6.1 全链路验证：实验 → 实验笼 → 入笼小鼠 → 行为事件
SELECT e.experiment_code, ec.cage_id, c.cage_code, COUNT(DISTINCT cm.mouse_id) AS mice_in_cage,
       COUNT(DISTINCT s.session_id) AS sessions,
       COUNT(DISTINCT ev.event_id)  AS events
FROM sys_experiment e
LEFT JOIN sys_experiment_cage ec ON ec.experiment_id = e.experiment_id AND ec.bind_status = 'ACTIVE'
LEFT JOIN sys_cage c ON c.cage_id = ec.cage_id
LEFT JOIN sys_cage_mouse cm ON cm.cage_id = c.cage_id AND cm.is_current = 1 AND cm.del_flag = '0'
LEFT JOIN sys_behavior_session s ON s.cage_id = c.cage_id AND s.experiment_id = e.experiment_id
LEFT JOIN sys_behavior_event ev ON ev.session_id = s.session_id
WHERE e.experiment_code = 'A001'
GROUP BY e.experiment_code, ec.cage_id, c.cage_code
ORDER BY ec.cage_id;

-- 6.2 小鼠明细与事件分布
SELECT m.mouse_code, cm.cage_id, c.cage_code,
       COUNT(ev.event_id) AS event_count,
       ROUND(AVG(ev.duration_seconds),2) AS avg_duration,
       ROUND(AVG(ev.confidence_score),3) AS avg_confidence
FROM sys_mouse m
LEFT JOIN sys_cage_mouse cm ON cm.mouse_id = m.mouse_id AND cm.is_current = 1 AND cm.del_flag = '0'
LEFT JOIN sys_cage c ON c.cage_id = cm.cage_id
LEFT JOIN sys_behavior_session s ON s.cage_id = c.cage_id
LEFT JOIN sys_behavior_event ev ON ev.session_id = s.session_id AND ev.mouse_id = m.mouse_id
WHERE m.mouse_code LIKE 'A001-%'
GROUP BY m.mouse_code, cm.cage_id, c.cage_code
ORDER BY m.mouse_code;

-- 6.3 各表记录数
SELECT 'Experiment' table_name, COUNT(*) record_count FROM sys_experiment WHERE experiment_code='A001'
UNION ALL SELECT 'CageMouse', COUNT(*) FROM sys_cage_mouse WHERE cage_id IN (6,7)
UNION ALL SELECT 'Sessions', COUNT(*) FROM sys_behavior_session WHERE session_code IN ('A001_CAGE003_S1','A001_CAGE004_S1')
UNION ALL SELECT 'Events', COUNT(*) FROM sys_behavior_event ev JOIN sys_behavior_session s ON ev.session_id = s.session_id WHERE s.session_code IN ('A001_CAGE003_S1','A001_CAGE004_S1')
UNION ALL SELECT 'Media', COUNT(*) FROM sys_behavior_media md JOIN sys_behavior_event e ON md.event_id = e.event_id JOIN sys_behavior_session s ON e.session_id = s.session_id WHERE s.session_code IN ('A001_CAGE003_S1','A001_CAGE004_S1');

-- =============================================
-- 结束
-- =============================================