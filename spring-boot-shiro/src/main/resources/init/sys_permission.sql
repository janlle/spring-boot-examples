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

 Date: 27/05/2018 22:24:25
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for sys_permission
-- ----------------------------
DROP TABLE IF EXISTS `sys_permission`;
CREATE TABLE `sys_permission`  (
  `permission_id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `url` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `available` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `parent_id` int(11) DEFAULT NULL,
  `resource_type` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `permission` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  PRIMARY KEY (`permission_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 9 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_permission
-- ----------------------------
INSERT INTO `sys_permission` VALUES (1, '用户查询', 'user/list', '1', 0, 'menu', 'user:select');
INSERT INTO `sys_permission` VALUES (2, '用户添加', 'user/add', '1', 1, 'button', 'user:add');
INSERT INTO `sys_permission` VALUES (3, '用户删除', 'user/delete', '1', 1, 'button', 'user:delete');
INSERT INTO `sys_permission` VALUES (4, '用户修改', 'user/update', '1', 0, 'button', 'user:update');
INSERT INTO `sys_permission` VALUES (5, '管理员添加', 'admin/add', '1', 0, 'button', 'admin:add');
INSERT INTO `sys_permission` VALUES (6, '管理员修改', 'admin/update', '1', 0, 'button', 'admin:update');
INSERT INTO `sys_permission` VALUES (7, '管理员删除', 'admin/delete', '1', 0, 'button', 'admin:delete');
INSERT INTO `sys_permission` VALUES (8, '管理员查询', 'admin/list', '1', 0, 'button', 'admin:select');

SET FOREIGN_KEY_CHECKS = 1;
