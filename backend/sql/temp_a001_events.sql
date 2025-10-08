USE mouse_behavior;

START TRANSACTION;

-- 1) 删除旧事件（两场会话）
DELETE FROM sys_behavior_event
WHERE session_id IN (
  SELECT session_id FROM sys_behavior_session 
  WHERE session_code IN ('A001_CAGE003_S1','A001_CAGE004_S1')
);

-- 2) 插入新事件（扩展字段版）
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

-- 对照组
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

COMMIT;

-- 验证统计
SELECT s.session_code, COUNT(e.event_id) AS event_count
FROM sys_behavior_session s 
LEFT JOIN sys_behavior_event e ON s.session_id = e.session_id
WHERE s.session_code IN ('A001_CAGE003_S1','A001_CAGE004_S1')
GROUP BY s.session_code;
