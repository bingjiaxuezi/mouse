USE mouse_behavior;

CREATE TABLE IF NOT EXISTS t_user (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(50) NOT NULL UNIQUE,
    password VARCHAR(100) NOT NULL,
    email VARCHAR(100),
    real_name VARCHAR(50),
    status TINYINT DEFAULT 1,
    create_time DATETIME DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE IF NOT EXISTS t_device (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    device_id VARCHAR(50) NOT NULL UNIQUE,
    device_name VARCHAR(100) NOT NULL,
    device_type VARCHAR(20) DEFAULT 'camera',
    location VARCHAR(100),
    status TINYINT DEFAULT 1,
    create_time DATETIME DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE IF NOT EXISTS t_experiment (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    experiment_name VARCHAR(100) NOT NULL,
    experiment_code VARCHAR(50) NOT NULL UNIQUE,
    description TEXT,
    researcher_id BIGINT,
    status TINYINT DEFAULT 1,
    create_time DATETIME DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE IF NOT EXISTS t_mouse (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    mouse_id VARCHAR(50) NOT NULL UNIQUE,
    mouse_name VARCHAR(50),
    species VARCHAR(50),
    gender ENUM('male', 'female'),
    birth_date DATE,
    weight DECIMAL(5,2),
    experiment_id BIGINT,
    status TINYINT DEFAULT 1,
    create_time DATETIME DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE IF NOT EXISTS t_behavior_data (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    device_id VARCHAR(50) NOT NULL,
    mouse_id VARCHAR(50),
    experiment_id BIGINT,
    behavior_type VARCHAR(50),
    behavior_value JSON,
    confidence DECIMAL(3,2),
    timestamp DATETIME NOT NULL,
    create_time DATETIME DEFAULT CURRENT_TIMESTAMP
);

INSERT INTO t_user (username, password, email, real_name) VALUES
('admin', 'admin123', 'admin@mouse.com', 'Administrator'),
('researcher1', 'pass123', 'researcher1@mouse.com', 'Researcher One');

INSERT INTO t_device (device_id, device_name, location) VALUES
('CAM001', 'Camera 1', 'Lab A - Cage 1'),
('CAM002', 'Camera 2', 'Lab A - Cage 2');

INSERT INTO t_experiment (experiment_name, experiment_code, description, researcher_id) VALUES
('Mouse Behavior Baseline', 'EXP001', 'Establish baseline behavior data', 2);

INSERT INTO t_mouse (mouse_id, mouse_name, species, gender, birth_date, weight, experiment_id) VALUES
('M001', 'Mouse 1', 'Mus musculus', 'male', '2024-01-15', 25.5, 1),
('M002', 'Mouse 2', 'Mus musculus', 'female', '2024-01-16', 22.3, 1);

SHOW TABLES;
SELECT 'Database initialization completed successfully!' as status;