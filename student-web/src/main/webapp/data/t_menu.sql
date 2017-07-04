/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50614
Source Host           : localhost:3306
Source Database       : mytestdb

Target Server Type    : MYSQL
Target Server Version : 50614
File Encoding         : 65001

Date: 2017-07-04 10:08:46
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
) ENGINE=InnoDB AUTO_INCREMENT=35 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_menu
-- ----------------------------
INSERT INTO `t_menu` VALUES ('1', '基本元素', 'fa-cube', '0', '', '0', '1', '99.000');
INSERT INTO `t_menu` VALUES ('2', '按钮', 'fa-bold', null, 'button.html', '1', '1', '1.000');
INSERT INTO `t_menu` VALUES ('3', '表单', 'fa-list-alt', null, 'form.html', '1', '1', '2.000');
INSERT INTO `t_menu` VALUES ('4', '表格', 'fa-table', null, 'table.html', '1', '1', '3.000');
INSERT INTO `t_menu` VALUES ('5', '导航', 'fa-paper-plane', null, 'nav.html', '1', '1', '4.000');
INSERT INTO `t_menu` VALUES ('6', 'Tab选项卡', 'fa-bars', null, 'tab.html', '1', '1', '5.000');
INSERT INTO `t_menu` VALUES ('7', '辅助性元素', 'fa-american-sign-language-interpreting', null, 'auxiliar.html', '1', '1', '6.000');
INSERT INTO `t_menu` VALUES ('8', '组件', 'fa-cogs', '0', null, '0', '1', '100.000');
INSERT INTO `t_menu` VALUES ('9', 'Datatable', 'fa-table', null, 'begtable.html', '8', '1', '1.000');
INSERT INTO `t_menu` VALUES ('10', 'Navbar组件', 'fa-navicon', null, 'navbar.html', '8', '1', '2.000');
INSERT INTO `t_menu` VALUES ('11', '第三方组件', 'fa-cubes', '0', null, '0', '1', '101.000');
INSERT INTO `t_menu` VALUES ('12', 'iCheck组件', 'fa-check-square-o', '0', 'icheck.html', '11', '1', '1.000');
INSERT INTO `t_menu` VALUES ('13', '地址本', 'fa-address-book', '0', null, '0', '1', '102.000');
INSERT INTO `t_menu` VALUES ('14', 'Github', 'fa-github', null, 'https://www.github.com/', '13', '1', '1.000');
INSERT INTO `t_menu` VALUES ('15', 'QQ', 'fa-qq', null, 'http://www.qq.com/', '13', '1', '2.000');
INSERT INTO `t_menu` VALUES ('16', 'Fly社区', 'fa-facebook-official', null, 'http://fly.layui.com/', '13', '1', '3.000');
INSERT INTO `t_menu` VALUES ('17', '新浪微博', 'fa-weibo', null, 'http://weibo.com/', '13', '1', '4.000');
INSERT INTO `t_menu` VALUES ('18', '这是一级导航', 'fa-stop-circle', null, 'http://fontawesome.io/icons/', '0', '1', '103.000');
INSERT INTO `t_menu` VALUES ('19', '系统管理', 'fa-cogs', '1', null, '0', '1', '90.000');
INSERT INTO `t_menu` VALUES ('20', '菜单管理', 'fa-reorder', null, '/sysMenu/menuManage', '19', '1', '1.000');
INSERT INTO `t_menu` VALUES ('21', '流程模板', 'fa-anchor', null, '/sysAct/findActModel', '19', '1', '2.000');
INSERT INTO `t_menu` VALUES ('22', '自动任务', 'fa-tasks', null, '/sysAutoTask/findAutoTask', '19', '1', '3.000');
INSERT INTO `t_menu` VALUES ('23', '辅助管理', 'fa-stop-circle', '0', null, '0', '1', '91.000');
INSERT INTO `t_menu` VALUES ('24', '图标查看', 'fa-stop-circle', null, 'http://fontawesome.io/icons/', '23', '1', '20.000');
INSERT INTO `t_menu` VALUES ('25', '模块依赖', 'fa-stop-circle', null, '/assManage/viewDependence', '23', '1', '16.000');
INSERT INTO `t_menu` VALUES ('26', '流程管理', 'fa-cogs', '0', '', '0', '1', '80.000');
INSERT INTO `t_menu` VALUES ('27', '流程部署', 'fa-anchor', null, '/sysAct/findActModel', '26', '1', '1.000');
INSERT INTO `t_menu` VALUES ('28', '申请管理', 'fa-cogs', '0', '', '0', '1', '70.000');
INSERT INTO `t_menu` VALUES ('29', '请假申请', 'fa-cogs', '0', '/applyLeave/findLeaveList', '28', '1', '1.000');
INSERT INTO `t_menu` VALUES ('30', '审批管理', 'fa-cogs', '0', '', '0', '1', '60.000');
INSERT INTO `t_menu` VALUES ('31', '请假审批', 'fa-cogs', '0', '/approvalLeave/findApprovalLeave', '30', '1', '1.000');
INSERT INTO `t_menu` VALUES ('32', '数据字典', 'fa-tasks', null, '/sysDictionary/findDictionary', '19', '1', '4.000');
INSERT INTO `t_menu` VALUES ('33', '请假申请-外置', 'fa-cogs', '0', '/formkey/applyLeave/findLeaveList', '28', '1', '1.000');
INSERT INTO `t_menu` VALUES ('34', '请假审批-外置', 'fa-cogs', '0', '/formkey/approvalLeave/findApprovalLeave', '30', '1', '2.000');
