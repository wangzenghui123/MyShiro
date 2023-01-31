/*
 Navicat Premium Data Transfer

 Source Server         : wzh
 Source Server Type    : MySQL
 Source Server Version : 80026
 Source Host           : localhost:3306
 Source Schema         : test01

 Target Server Type    : MySQL
 Target Server Version : 80026
 File Encoding         : 65001

 Date: 31/01/2023 12:36:53
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for sys_role_permission
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_permission`;
CREATE TABLE `sys_role_permission`  (
  `id` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '主键',
  `role_id` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '角色id',
  `permission_id` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '菜单权限id',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_role_permission
-- ----------------------------
INSERT INTO `sys_role_permission` VALUES ('1', '1', '3', NULL);
INSERT INTO `sys_role_permission` VALUES ('10', '1', '7', NULL);
INSERT INTO `sys_role_permission` VALUES ('2', '1', '4', NULL);
INSERT INTO `sys_role_permission` VALUES ('3', '1', '5', NULL);
INSERT INTO `sys_role_permission` VALUES ('4', '2', '3', NULL);
INSERT INTO `sys_role_permission` VALUES ('5', '2', '4', NULL);
INSERT INTO `sys_role_permission` VALUES ('6', '1', '6', NULL);
INSERT INTO `sys_role_permission` VALUES ('7', '3', '3', NULL);
INSERT INTO `sys_role_permission` VALUES ('8', '1', '2', NULL);
INSERT INTO `sys_role_permission` VALUES ('9', '1', '1', NULL);

SET FOREIGN_KEY_CHECKS = 1;
