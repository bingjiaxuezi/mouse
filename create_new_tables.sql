-- Create behavior type dictionary table
CREATE TABLE IF NOT EXISTS `t_behavior_type` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'Behavior type ID',
  `type_code` varchar(50) NOT NULL COMMENT 'Behavior type code',
  `type_name` varchar(100) NOT NULL COMMENT 'Behavior type name',
  `category` varchar(50) NOT NULL COMMENT 'Behavior category',
  `description` varchar(500) DEFAULT NULL COMMENT 'Behavior description',
  `is_active` tinyint(1) DEFAULT 1 COMMENT 'Is active',
  `create_by` varchar(64) DEFAULT '' COMMENT 'Creator',
  `create_time` datetime DEFAULT NULL COMMENT 'Create time',
  `update_by` varchar(64) DEFAULT '' COMMENT 'Updater',
  `update_time` datetime DEFAULT NULL COMMENT 'Update time',
  `remark` varchar(500) DEFAULT NULL COMMENT 'Remark',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_type_code` (`type_code`),
  KEY `idx_category` (`category`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='Behavior type dictionary table';

-- Create position track table
CREATE TABLE IF NOT EXISTS `t_position_track` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'Track record ID',
  `device_code` varchar(50) NOT NULL COMMENT 'Device code',
  `mouse_code` varchar(50) NOT NULL COMMENT 'Mouse code',
  `experiment_id` bigint NOT NULL COMMENT 'Experiment ID',
  `x_coordinate` decimal(10,4) NOT NULL COMMENT 'X coordinate',
  `y_coordinate` decimal(10,4) NOT NULL COMMENT 'Y coordinate',
  `z_coordinate` decimal(10,4) DEFAULT NULL COMMENT 'Z coordinate',
  `velocity` decimal(8,4) DEFAULT NULL COMMENT 'Velocity',
  `direction` decimal(6,2) DEFAULT NULL COMMENT 'Direction',
  `timestamp` datetime NOT NULL COMMENT 'Record time',
  `create_by` varchar(64) DEFAULT '' COMMENT 'Creator',
  `create_time` datetime DEFAULT NULL COMMENT 'Create time',
  PRIMARY KEY (`id`),
  KEY `idx_device_mouse_time` (`device_code`,`mouse_code`,`timestamp`),
  KEY `idx_experiment_time` (`experiment_id`,`timestamp`),
  CONSTRAINT `fk_position_device` FOREIGN KEY (`device_code`) REFERENCES `t_device` (`device_code`),
  CONSTRAINT `fk_position_mouse` FOREIGN KEY (`mouse_code`) REFERENCES `t_mouse` (`mouse_code`),
  CONSTRAINT `fk_position_experiment` FOREIGN KEY (`experiment_id`) REFERENCES `t_experiment` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='Mouse position track table';

-- Create physiological state table
CREATE TABLE IF NOT EXISTS `t_physiological_state` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'Physiological state record ID',
  `device_code` varchar(50) NOT NULL COMMENT 'Device code',
  `mouse_code` varchar(50) NOT NULL COMMENT 'Mouse code',
  `experiment_id` bigint NOT NULL COMMENT 'Experiment ID',
  `state_type` varchar(50) NOT NULL COMMENT 'State type',
  `state_value` decimal(10,4) NOT NULL COMMENT 'State value',
  `unit` varchar(20) NOT NULL COMMENT 'Unit',
  `confidence` decimal(5,4) DEFAULT NULL COMMENT 'Confidence',
  `timestamp` datetime NOT NULL COMMENT 'Record time',
  `create_by` varchar(64) DEFAULT '' COMMENT 'Creator',
  `create_time` datetime DEFAULT NULL COMMENT 'Create time',
  `remark` varchar(500) DEFAULT NULL COMMENT 'Remark',
  PRIMARY KEY (`id`),
  KEY `idx_device_mouse_type_time` (`device_code`,`mouse_code`,`state_type`,`timestamp`),
  KEY `idx_experiment_time` (`experiment_id`,`timestamp`),
  CONSTRAINT `fk_physio_device` FOREIGN KEY (`device_code`) REFERENCES `t_device` (`device_code`),
  CONSTRAINT `fk_physio_mouse` FOREIGN KEY (`mouse_code`) REFERENCES `t_mouse` (`mouse_code`),
  CONSTRAINT `fk_physio_experiment` FOREIGN KEY (`experiment_id`) REFERENCES `t_experiment` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='Mouse physiological state table';

-- Insert behavior type dictionary data
INSERT INTO `t_behavior_type` (`type_code`, `type_name`, `category`, `description`, `create_by`, `create_time`) VALUES
('sleep', 'Sleep', 'physiological', 'Mouse sleeping state', 'system', NOW()),
('eat', 'Eating', 'feeding', 'Mouse eating behavior', 'system', NOW()),
('drink', 'Drinking', 'feeding', 'Mouse drinking behavior', 'system', NOW()),
('defecate', 'Defecation', 'physiological', 'Mouse defecation behavior', 'system', NOW()),
('urinate', 'Urination', 'physiological', 'Mouse urination behavior', 'system', NOW()),
('cough', 'Coughing', 'physiological', 'Mouse coughing behavior', 'system', NOW()),
('groom', 'Grooming', 'grooming', 'Mouse self-grooming behavior', 'system', NOW()),
('walk', 'Walking', 'movement', 'Mouse normal walking', 'system', NOW()),
('run', 'Running', 'movement', 'Mouse fast movement', 'system', NOW()),
('climb', 'Climbing', 'movement', 'Mouse climbing behavior', 'system', NOW()),
('jump', 'Jumping', 'movement', 'Mouse jumping behavior', 'system', NOW()),
('dig', 'Digging', 'movement', 'Mouse digging behavior', 'system', NOW()),
('explore', 'Exploring', 'movement', 'Mouse exploring environment', 'system', NOW()),
('rest', 'Resting', 'physiological', 'Mouse resting state', 'system', NOW()),
('social_interact', 'Social Interaction', 'social', 'Social behavior with other mice', 'system', NOW()),
('aggressive', 'Aggressive Behavior', 'social', 'Mouse aggressive behavior', 'system', NOW()),
('freeze', 'Freezing', 'physiological', 'Mouse fear freezing response', 'system', NOW()),
('scratch', 'Scratching', 'grooming', 'Mouse scratching behavior', 'system', NOW()),
('sniff', 'Sniffing', 'movement', 'Mouse sniffing behavior', 'system', NOW());