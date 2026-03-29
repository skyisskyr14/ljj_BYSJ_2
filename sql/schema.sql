CREATE DATABASE IF NOT EXISTS cloud_yearbook DEFAULT CHARACTER SET utf8mb4;
USE cloud_yearbook;

CREATE TABLE IF NOT EXISTS user (
  id BIGINT PRIMARY KEY AUTO_INCREMENT,
  username VARCHAR(50) NOT NULL UNIQUE,
  password VARCHAR(255) NOT NULL,
  nickname VARCHAR(100),
  avatar_url VARCHAR(500),
  gender TINYINT DEFAULT 0,
  phone VARCHAR(20),
  email VARCHAR(100),
  graduation_destination VARCHAR(255),
  personal_profile TEXT,
  status TINYINT NOT NULL DEFAULT 0,
  role VARCHAR(20) NOT NULL DEFAULT 'USER',
  create_time DATETIME NOT NULL,
  update_time DATETIME NOT NULL,
  INDEX idx_user_role(role),
  INDEX idx_user_status(status)
);

CREATE TABLE IF NOT EXISTS `class` (
  id BIGINT PRIMARY KEY AUTO_INCREMENT,
  class_name VARCHAR(100) NOT NULL,
  grade VARCHAR(50),
  description TEXT,
  creator_id BIGINT NOT NULL,
  create_time DATETIME NOT NULL,
  update_time DATETIME NOT NULL,
  INDEX idx_class_grade(grade)
);

CREATE TABLE IF NOT EXISTS user_class (
  id BIGINT PRIMARY KEY AUTO_INCREMENT,
  user_id BIGINT NOT NULL,
  class_id BIGINT NOT NULL,
  join_time DATETIME NOT NULL,
  identity VARCHAR(50),
  UNIQUE KEY uk_user_class(user_id, class_id)
);

CREATE TABLE IF NOT EXISTS tag (
  id BIGINT PRIMARY KEY AUTO_INCREMENT,
  tag_name VARCHAR(50) NOT NULL UNIQUE,
  type VARCHAR(20),
  create_time DATETIME NOT NULL
);

CREATE TABLE IF NOT EXISTS user_tag (
  id BIGINT PRIMARY KEY AUTO_INCREMENT,
  user_id BIGINT NOT NULL,
  tag_id BIGINT NOT NULL,
  create_time DATETIME NOT NULL,
  UNIQUE KEY uk_user_tag(user_id, tag_id)
);

CREATE TABLE IF NOT EXISTS message (
  id BIGINT PRIMARY KEY AUTO_INCREMENT,
  content TEXT NOT NULL,
  sender_id BIGINT NOT NULL,
  parent_id BIGINT NOT NULL DEFAULT 0,
  status TINYINT NOT NULL DEFAULT 0,
  like_count INT NOT NULL DEFAULT 0,
  create_time DATETIME NOT NULL,
  update_time DATETIME NOT NULL,
  INDEX idx_message_sender(sender_id),
  INDEX idx_message_status(status)
);

CREATE TABLE IF NOT EXISTS notification (
  id BIGINT PRIMARY KEY AUTO_INCREMENT,
  user_id BIGINT NOT NULL,
  title VARCHAR(200) NOT NULL,
  content TEXT NOT NULL,
  type VARCHAR(50) NOT NULL,
  is_read TINYINT NOT NULL DEFAULT 0,
  related_id BIGINT,
  create_time DATETIME NOT NULL
);

CREATE TABLE IF NOT EXISTS operation_log (
  id BIGINT PRIMARY KEY AUTO_INCREMENT,
  user_id BIGINT,
  operation_type VARCHAR(100) NOT NULL,
  operation_detail VARCHAR(500),
  ip_address VARCHAR(50),
  user_agent TEXT,
  status TINYINT NOT NULL DEFAULT 0,
  create_time DATETIME NOT NULL,
  INDEX idx_log_type(operation_type),
  INDEX idx_log_time(create_time)
);

CREATE TABLE IF NOT EXISTS attachment (
  id BIGINT PRIMARY KEY AUTO_INCREMENT,
  user_id BIGINT NOT NULL,
  file_name VARCHAR(255) NOT NULL,
  file_path VARCHAR(500) NOT NULL,
  file_type VARCHAR(50),
  file_size BIGINT NOT NULL,
  biz_type VARCHAR(50),
  biz_id BIGINT,
  upload_time DATETIME NOT NULL
);
