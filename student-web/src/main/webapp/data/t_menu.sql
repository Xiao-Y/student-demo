/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50614
Source Host           : localhost:3306
Source Database       : mytestdb

Target Server Type    : MYSQL
Target Server Version : 50614
File Encoding         : 65001

Date: 2017-07-07 09:08:40
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for t_menu
-- ----------------------------
DROP TABLE IF EXISTS `t_menu`;
CREATE TABLE `t_menu` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(255) DEFAULT NULL,
  `icon` varchar(255) DEFAULT NULL,
  `spread` tinyint(1) DEFAULT NULL,
  `href` varchar(255) DEFAULT NULL,
  `pid` int(11) DEFAULT NULL,
  `validind` tinyint(1) DEFAULT NULL,
  `displayno` double(11,3) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=46 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_menu
-- ----------------------------
INSERT INTO `t_menu` VALUES ('19', '系统管理', 'fa-cogs', '0', '', '0', '1', '90.000');
INSERT INTO `t_menu` VALUES ('20', '菜单管理', 'fa-reorder', null, '/sysMenu/menuManage', '19', '1', '1.000');
INSERT INTO `t_menu` VALUES ('23', '辅助管理', 'fa-stop-circle', '0', null, '0', '1', '91.000');
INSERT INTO `t_menu` VALUES ('24', '图标查看', 'fa-stop-circle', null, 'http://fontawesome.io/icons/', '23', '1', '20.000');
INSERT INTO `t_menu` VALUES ('25', '模块依赖', 'fa-stop-circle', null, '/assManage/viewDependence', '23', '1', '16.000');
INSERT INTO `t_menu` VALUES ('32', '数据字典', 'fa-tasks', null, '/sysDictionary/findDictionary', '19', '1', '4.000');
INSERT INTO `t_menu` VALUES ('37', 'Activiti', 'fa-anchor', '1', '', '0', '1', '1.000');
INSERT INTO `t_menu` VALUES ('38', '流程部署', 'fa-anchor', '0', '/sysAct/findActModel', '37', '1', '1.000');
INSERT INTO `t_menu` VALUES ('39', '请假申请', 'fa-cogs', '0', '/applyLeave/findLeaveList', '37', '1', '2.000');
INSERT INTO `t_menu` VALUES ('40', '请假审批', 'fa-cogs', '0', '/approvalLeave/findApprovalLeave', '37', '1', '3.000');
INSERT INTO `t_menu` VALUES ('41', '请假申请-外置', 'fa-cogs', '0', '/formkey/applyLeave/findLeaveList', '37', '1', '4.000');
INSERT INTO `t_menu` VALUES ('42', '请假审批-外置', 'fa-cogs', '0', '/formkey/approvalLeave/findApprovalLeave', '37', '1', '5.000');
INSERT INTO `t_menu` VALUES ('43', '自动任务', 'fa-tasks', '0', '', '0', '1', '2.000');
INSERT INTO `t_menu` VALUES ('44', '自动任务', 'fa-tasks', '0', '/sysAutoTask/findAutoTask', '43', '1', '1.000');
INSERT INTO `t_menu` VALUES ('45', '流程模板', 'fa-anchor', '0', '/sysAct/findActModel', '37', '1', '1.500');
