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

 Date: 31/01/2023 12:35:16
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for sys_permission
-- ----------------------------
DROP TABLE IF EXISTS `sys_permission`;
CREATE TABLE `sys_permission`  (
  `id` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '主键',
  `code` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '菜单权限编码',
  `name` varchar(300) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '菜单权限名称',
  `perms` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '授权（如：sys:user:add）',
  `url` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '访问地址url',
  `method` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '资源请求类型',
  `pid` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '父级菜单权限名称',
  `order_num` int(0) NULL DEFAULT 0 COMMENT '排序',
  `type` tinyint(0) NULL DEFAULT NULL COMMENT '菜单权限类型1：目录2：菜单3：按钮',
  `status` tinyint(0) NULL DEFAULT 1 COMMENT '1:正常0：禁用',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `deleted` tinyint(0) NULL DEFAULT 1 COMMENT '是否删除1：未删除0：已经删除',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_permission
-- ----------------------------
INSERT INTO `sys_permission` VALUES ('1', '', '组织管理', '', '', '', '0', 1, 1, 1, NULL, '2023-01-29 00:00:00', 1);
INSERT INTO `sys_permission` VALUES ('10', NULL, '删除用户', 'sys:user:delete', NULL, 'POST', '3', 0, 3, 1, '2023-01-03 12:20:02', '2023-01-03 12:20:02', 1);
INSERT INTO `sys_permission` VALUES ('11', NULL, '新增部门', 'sys:dept:add', '', 'POST', '4', 0, 3, 1, '2023-01-03 12:20:02', '2023-01-03 12:20:02', 1);
INSERT INTO `sys_permission` VALUES ('12', NULL, '删除部门', 'sys:dept:delete', '', 'POST', '4', 0, 3, 1, '2023-01-03 12:20:02', '2023-01-03 12:20:02', 1);
INSERT INTO `sys_permission` VALUES ('13', NULL, '修改部门', 'sys:dept:update', '', 'POST', '4', 0, 3, 1, '2023-01-03 12:20:02', '2023-01-03 12:20:02', 1);
INSERT INTO `sys_permission` VALUES ('14', NULL, '查询部门', 'sys:dept:select', '', 'POST', '4', 0, 3, 1, '2023-01-03 12:20:02', '2023-01-03 12:20:02', 1);
INSERT INTO `sys_permission` VALUES ('15', NULL, '查询用户', 'sys:user:select', NULL, 'POST', '3', 0, 3, 1, '2023-01-03 12:20:02', '2023-01-03 12:20:02', 1);
INSERT INTO `sys_permission` VALUES ('16', NULL, '新增角色', 'sys:role:add', '', 'POST', '5', 0, 3, 1, '2023-01-03 12:20:02', '2023-01-03 12:20:02', 1);
INSERT INTO `sys_permission` VALUES ('17', NULL, '删除角色', 'sys:role:delete', '', 'POST', '5', 0, 3, 1, '2023-01-03 12:20:02', '2023-01-03 12:20:02', 1);
INSERT INTO `sys_permission` VALUES ('18', NULL, '修改角色', 'sys:role:update', '', 'POST', '5', 0, 3, 1, '2023-01-03 12:20:02', '2023-01-03 12:20:02', 1);
INSERT INTO `sys_permission` VALUES ('19', NULL, '查询角色', 'sys:role:select', '', 'POST', '5', 0, 3, 1, '2023-01-03 12:20:02', '2023-01-03 12:20:02', 1);
INSERT INTO `sys_permission` VALUES ('2', NULL, '系统管理', NULL, NULL, NULL, '0', 2, 1, 1, '2023-01-03 12:20:02', '2023-01-03 12:20:02', 1);
INSERT INTO `sys_permission` VALUES ('20', NULL, '新增权限', 'sys:permission:add', '', 'POST', '6', 0, 3, 1, '2023-01-03 12:20:02', '2023-01-03 12:20:02', 1);
INSERT INTO `sys_permission` VALUES ('21', NULL, '删除权限', 'sys:permission:delete', '', 'POST', '6', 0, 3, 1, '2023-01-03 12:20:02', '2023-01-03 12:20:02', 1);
INSERT INTO `sys_permission` VALUES ('22', NULL, '修改权限', 'sys:permission:update', '', 'POST', '6', 0, 3, 1, '2023-01-03 12:20:02', '2023-01-03 12:20:02', 1);
INSERT INTO `sys_permission` VALUES ('23', NULL, '查询权限', 'sys:permission:select', '', 'POST', '6', 0, 3, 1, '2023-01-03 12:20:02', '2023-01-03 12:20:02', 1);
INSERT INTO `sys_permission` VALUES ('24', NULL, '新增日志', 'sys:log:add', '', NULL, '7', 0, 3, 1, '2023-01-03 12:20:02', '2023-01-03 12:20:02', 1);
INSERT INTO `sys_permission` VALUES ('24edb77b-24d0-4eba-8317-f25ac87a04b8', '', '方案管理', '', '1', '', '0', 1, 1, 1, '2023-01-31 04:15:20', '2023-01-31 04:15:20', 1);
INSERT INTO `sys_permission` VALUES ('25', NULL, '删除日志', 'sys:log:delete', '', NULL, '7', 0, 3, 1, '2023-01-03 12:20:02', '2023-01-03 12:20:02', 1);
INSERT INTO `sys_permission` VALUES ('26', NULL, '修改日志', 'sys:log:update', '', NULL, '7', 0, 3, 1, '2023-01-03 12:20:02', '2023-01-03 12:20:02', 1);
INSERT INTO `sys_permission` VALUES ('27', NULL, '查询日志', 'sys:log:select', '', NULL, '7', 0, 3, 1, '2023-01-03 12:20:02', '2023-01-03 12:20:02', 1);
INSERT INTO `sys_permission` VALUES ('3', '', '用户管理', 'sys:user:*', '/user/user.html', '', '1', 0, 2, 1, NULL, '2023-01-29 00:00:00', 1);
INSERT INTO `sys_permission` VALUES ('4', NULL, '部门管理', 'sys:dept:*', '/dept/dept.html', NULL, '1', 0, 2, 1, '2023-01-03 12:20:02', '2023-01-03 12:20:02', 1);
INSERT INTO `sys_permission` VALUES ('5', NULL, '角色管理', 'sys:role:*', '/role/role.html', NULL, '1', 0, 2, 1, '2023-01-03 12:20:02', '2023-01-03 12:20:02', 1);
INSERT INTO `sys_permission` VALUES ('6', NULL, '菜单权限管理', 'sys:permission:*', '/menus/menu.html', NULL, '1', 0, 2, 1, '2023-01-03 12:20:02', '2023-01-03 12:20:02', 1);
INSERT INTO `sys_permission` VALUES ('7', NULL, '日志管理', 'sys:log:*', '/log/log.html', NULL, '2', 0, 2, 1, '2023-01-03 12:20:02', '2023-01-03 12:20:02', 1);
INSERT INTO `sys_permission` VALUES ('8', NULL, '新增用户', 'sys:user:add', NULL, 'POST', '3', 0, 3, 1, '2023-01-03 12:20:02', '2023-01-03 12:20:02', 1);
INSERT INTO `sys_permission` VALUES ('9', NULL, '修改用户', 'sys:user:update', NULL, 'POST', '3', 0, 3, 1, '2023-01-03 12:20:02', '2023-01-03 12:20:02', 1);
INSERT INTO `sys_permission` VALUES ('9ef58863-735f-4d50-8359-09211c3900d9', '1', '方案管理1', '1', '1', '1', '24edb77b-24d0-4eba-8317-f25ac87a04b8', 1, 2, 1, '2023-01-31 04:20:13', '2023-01-31 04:20:13', 1);

SET FOREIGN_KEY_CHECKS = 1;
