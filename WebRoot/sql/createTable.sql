
SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for logs
-- ----------------------------
DROP TABLE IF EXISTS logs;
CREATE TABLE logs (
  id int(11) NOT NULL AUTO_INCREMENT,
  username varchar(20) DEFAULT NULL,
  log_info varchar(50) DEFAULT NULL,
  model_name varchar(50) DEFAULT NULL,
  method_name varchar(50) DEFAULT NULL,
  ip varchar(15) DEFAULT NULL,
  log_time timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  user_id int(11) DEFAULT NULL,
  PRIMARY KEY (id),
  KEY fk_user_log (user_id),
  CONSTRAINT logs_ibfk_1 FOREIGN KEY (user_id) REFERENCES user (id) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of logs
-- ----------------------------

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS user;
CREATE TABLE user (
  id int(11) NOT NULL AUTO_INCREMENT,
  username varchar(20) DEFAULT NULL,
  password varchar(32) DEFAULT NULL,
  nickname varchar(20) DEFAULT NULL,
  sex char(1) DEFAULT NULL,
  age int(11) DEFAULT NULL,
  tel varchar(15) DEFAULT NULL,
  pic varchar(100) DEFAULT NULL,
  PRIMARY KEY (id),
  UNIQUE KEY unique_username (username) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------