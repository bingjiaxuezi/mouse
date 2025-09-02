-- Test data insertion script

-- Insert test behavior data
INSERT INTO t_behavior_data (device_code, mouse_code, experiment_id, behavior_type, behavior_value, confidence, timestamp, create_by, create_time) VALUES
('CAM001', '12342345456', 3, 'normal', 'walking', 0.95, DATE_SUB(NOW(), INTERVAL 1 HOUR), 'system', NOW()),
('CAM001', '1234576', 3, 'abnormal', 'aggressive', 0.88, DATE_SUB(NOW(), INTERVAL 2 HOUR), 'system', NOW()),
('CAM002', '123458456', 3, 'normal', 'resting', 0.92, DATE_SUB(NOW(), INTERVAL 3 HOUR), 'system', NOW()),
('CAM001', '5ddeec8653f1487d', 3, 'abnormal', 'repetitive', 0.85, DATE_SUB(NOW(), INTERVAL 4 HOUR), 'system', NOW()),
('CAM002', '67890', 3, 'normal', 'exploring', 0.90, DATE_SUB(NOW(), INTERVAL 5 HOUR), 'system', NOW()),
('CAM001', '87654', 3, 'abnormal', 'freezing', 0.87, DATE_SUB(NOW(), INTERVAL 6 HOUR), 'system', NOW()),
('CAM002', '12342345456', 3, 'abnormal', 'spinning', 0.82, DATE_SUB(NOW(), INTERVAL 7 HOUR), 'system', NOW()),
('CAM001', '1234576', 3, 'normal', 'grooming', 0.93, DATE_SUB(NOW(), INTERVAL 8 HOUR), 'system', NOW()),
('CAM002', '123458456', 3, 'normal', 'feeding', 0.96, DATE_SUB(NOW(), INTERVAL 9 HOUR), 'system', NOW()),
('CAM001', '5ddeec8653f1487d', 3, 'abnormal', 'circling', 0.84, DATE_SUB(NOW(), INTERVAL 10 HOUR), 'system', NOW());

-- Insert test position tracking data
INSERT INTO t_position_track (device_code, mouse_code, experiment_id, x_coordinate, y_coordinate, z_coordinate, velocity, direction, timestamp, create_by, create_time) VALUES
('CAM001', '12342345456', 3, 10.5, 20.3, 0.0, 2.5, 45.0, DATE_SUB(NOW(), INTERVAL 1 HOUR), 'system', NOW()),
('CAM001', '1234576', 3, 15.2, 18.7, 0.0, 3.2, 90.0, DATE_SUB(NOW(), INTERVAL 2 HOUR), 'system', NOW()),
('CAM002', '123458456', 3, 8.9, 25.1, 0.0, 1.8, 180.0, DATE_SUB(NOW(), INTERVAL 3 HOUR), 'system', NOW()),
('CAM001', '5ddeec8653f1487d', 3, 22.4, 12.6, 0.0, 4.1, 270.0, DATE_SUB(NOW(), INTERVAL 4 HOUR), 'system', NOW()),
('CAM002', '67890', 3, 18.7, 30.2, 0.0, 2.9, 135.0, DATE_SUB(NOW(), INTERVAL 5 HOUR), 'system', NOW()),
('CAM001', '87654', 3, 5.3, 8.4, 0.0, 0.5, 0.0, DATE_SUB(NOW(), INTERVAL 6 HOUR), 'system', NOW()),
('CAM002', '12342345456', 3, 28.1, 15.9, 0.0, 5.2, 315.0, DATE_SUB(NOW(), INTERVAL 7 HOUR), 'system', NOW()),
('CAM001', '1234576', 3, 12.8, 22.5, 0.0, 2.1, 225.0, DATE_SUB(NOW(), INTERVAL 8 HOUR), 'system', NOW()),
('CAM002', '123458456', 3, 20.6, 5.7, 0.0, 3.7, 60.0, DATE_SUB(NOW(), INTERVAL 9 HOUR), 'system', NOW()),
('CAM001', '5ddeec8653f1487d', 3, 7.2, 28.3, 0.0, 1.3, 120.0, DATE_SUB(NOW(), INTERVAL 10 HOUR), 'system', NOW());

-- Insert test physiological state data
INSERT INTO t_physiological_state (device_code, mouse_code, experiment_id, state_type, state_value, unit, confidence, timestamp, create_by, create_time, remark) VALUES
('CAM001', '12342345456', 3, 'heart_rate', '320', 'bpm', 0.95, DATE_SUB(NOW(), INTERVAL 1 HOUR), 'system', NOW(), 'Normal range'),
('CAM001', '1234576', 3, 'body_temperature', '37.2', 'celsius', 0.92, DATE_SUB(NOW(), INTERVAL 2 HOUR), 'system', NOW(), 'Slightly elevated'),
('CAM002', '123458456', 3, 'activity_level', '0.75', 'normalized', 0.88, DATE_SUB(NOW(), INTERVAL 3 HOUR), 'system', NOW(), 'Moderate activity'),
('CAM001', '5ddeec8653f1487d', 3, 'heart_rate', '380', 'bpm', 0.90, DATE_SUB(NOW(), INTERVAL 4 HOUR), 'system', NOW(), 'Elevated due to stress'),
('CAM002', '67890', 3, 'body_temperature', '36.8', 'celsius', 0.94, DATE_SUB(NOW(), INTERVAL 5 HOUR), 'system', NOW(), 'Normal range'),
('CAM001', '87654', 3, 'activity_level', '0.25', 'normalized', 0.87, DATE_SUB(NOW(), INTERVAL 6 HOUR), 'system', NOW(), 'Low activity - resting'),
('CAM002', '12342345456', 3, 'heart_rate', '420', 'bpm', 0.85, DATE_SUB(NOW(), INTERVAL 7 HOUR), 'system', NOW(), 'High stress indicator'),
('CAM001', '1234576', 3, 'body_temperature', '37.0', 'celsius', 0.93, DATE_SUB(NOW(), INTERVAL 8 HOUR), 'system', NOW(), 'Normal range'),
('CAM002', '123458456', 3, 'activity_level', '0.90', 'normalized', 0.91, DATE_SUB(NOW(), INTERVAL 9 HOUR), 'system', NOW(), 'High activity - feeding'),
('CAM001', '5ddeec8653f1487d', 3, 'heart_rate', '350', 'bpm', 0.89, DATE_SUB(NOW(), INTERVAL 10 HOUR), 'system', NOW(), 'Moderate elevation');