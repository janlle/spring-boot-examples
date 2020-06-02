/*
 Navicat Premium Data Transfer

 Source Server         : local
 Source Server Type    : MySQL
 Source Server Version : 50718
 Source Host           : localhost:3306
 Source Schema         : boot

 Target Server Type    : MySQL
 Target Server Version : 50718
 File Encoding         : 65001

 Date: 27/05/2018 22:24:34
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for sys_permission_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_permission_role`;
CREATE TABLE `sys_permission_role`  (
  `id` int(11) NOT NULL,
  `permission_id` int(11) DEFAULT NULL,
  `role_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `permission`(`permission_id`) USING BTREE,
  INDEX `role`(`role_id`) USING BTREE,
  CONSTRAINT `permission` FOREIGN KEY (`permission_id`) REFERENCES `sys_permission` (`permission_id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `role` FOREIGN KEY (`role_id`) REFERENCES `sys_role` (`role_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_permission_role
-- ----------------------------
INSERT INTO `sys_permission_role` VALUES (1, 1, 1);
INSERT INTO `sys_permission_role` VALUES (2, 2, 1);
INSERT INTO `sys_permission_role` VALUES (3, 3, 1);
INSERT INTO `sys_permission_role` VALUES (4, 4, 1);
INSERT INTO `sys_permission_role` VALUES (5, 1, 2);
INSERT INTO `sys_permission_role` VALUES (6, 2, 2);
INSERT INTO `sys_permission_role` VALUES (7, 5, 1);
INSERT INTO `sys_permission_role` VALUES (8, 6, 1);
INSERT INTO `sys_permission_role` VALUES (9, 7, 1);
INSERT INTO `sys_permission_role` VALUES (10, 8, 1);
INSERT INTO `sys_permission_role` VALUES (11, 1, 4);
INSERT INTO `sys_permission_role` VALUES (12, 2, 4);
INSERT INTO `sys_permission_role` VALUES (13, 3, 4);
INSERT INTO `sys_permission_role` VALUES (14, 4, 4);
INSERT INTO `sys_permission_role` VALUES (15, 1, 3);
INSERT INTO `sys_permission_role` VALUES (16, 2, 3);
INSERT INTO `sys_permission_role` VALUES (17, 4, 3);

SET FOREIGN_KEY_CHECKS = 1;
