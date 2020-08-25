/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 80021
Source Host           : 127.0.0.1:3306
Source Database       : work

Target Server Type    : MYSQL
Target Server Version : 80021
File Encoding         : 65001

Date: 2020-08-25 18:19:53
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for t_user_info
-- ----------------------------
DROP TABLE IF EXISTS `t_user_info`;
CREATE TABLE `t_user_info` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(128) COLLATE utf8mb4_general_ci NOT NULL DEFAULT '',
  `age` int NOT NULL DEFAULT '0',
  `desc` varchar(225) COLLATE utf8mb4_general_ci NOT NULL DEFAULT '',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- ----------------------------
-- Records of t_user_info
-- ----------------------------
INSERT INTO `t_user_info` VALUES ('1', '王亮', '185', '猪头人');
INSERT INTO `t_user_info` VALUES ('2', '李白', '0', '里');
