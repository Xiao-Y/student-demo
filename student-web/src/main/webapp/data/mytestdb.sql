/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50614
Source Host           : localhost:3306
Source Database       : mytestdb

Target Server Type    : MYSQL
Target Server Version : 50614
File Encoding         : 65001

Date: 2017-01-20 18:05:05
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for treenodes
-- ----------------------------
DROP TABLE IF EXISTS `treenodes`;
CREATE TABLE `treenodes` (
  `id` int(11) NOT NULL,
  `nodename` varchar(20) DEFAULT NULL,
  `pid` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of treenodes
-- ----------------------------
INSERT INTO `treenodes` VALUES ('1', 'A', '0');
INSERT INTO `treenodes` VALUES ('2', 'B', '1');
INSERT INTO `treenodes` VALUES ('3', 'C', '1');
INSERT INTO `treenodes` VALUES ('4', 'D', '2');
INSERT INTO `treenodes` VALUES ('5', 'E', '2');
INSERT INTO `treenodes` VALUES ('6', 'F', '3');
INSERT INTO `treenodes` VALUES ('7', 'G', '6');
INSERT INTO `treenodes` VALUES ('8', 'H', '0');
INSERT INTO `treenodes` VALUES ('9', 'I', '8');
INSERT INTO `treenodes` VALUES ('10', 'J', '8');
INSERT INTO `treenodes` VALUES ('11', 'K', '8');
INSERT INTO `treenodes` VALUES ('12', 'L', '9');
INSERT INTO `treenodes` VALUES ('13', 'M', '9');
INSERT INTO `treenodes` VALUES ('14', 'N', '12');
INSERT INTO `treenodes` VALUES ('15', 'O', '12');
INSERT INTO `treenodes` VALUES ('16', 'P', '15');
INSERT INTO `treenodes` VALUES ('17', 'Q', '15');

-- ----------------------------
-- Table structure for t_areainfo
-- ----------------------------
DROP TABLE IF EXISTS `t_areainfo`;
CREATE TABLE `t_areainfo` (
  `id` int(11) NOT NULL DEFAULT '0',
  `level` int(11) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `parentId` int(11) DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_areainfo
-- ----------------------------
INSERT INTO `t_areainfo` VALUES ('1', '0', '中国', '0', '0');
INSERT INTO `t_areainfo` VALUES ('2', '0', '华北区', '1', '0');
INSERT INTO `t_areainfo` VALUES ('3', '0', '华南区', '1', '0');
INSERT INTO `t_areainfo` VALUES ('4', '0', '北京', '2', '0');
INSERT INTO `t_areainfo` VALUES ('5', '0', '海淀区', '4', '0');
INSERT INTO `t_areainfo` VALUES ('6', '0', '丰台区', '4', '0');
INSERT INTO `t_areainfo` VALUES ('7', '0', '朝阳区', '4', '0');
INSERT INTO `t_areainfo` VALUES ('8', '0', '北京XX区1', '4', '0');
INSERT INTO `t_areainfo` VALUES ('9', '0', '北京XX区2', '4', '0');
INSERT INTO `t_areainfo` VALUES ('10', '0', '北京XX区3', '4', '0');
INSERT INTO `t_areainfo` VALUES ('11', '0', '北京XX区4', '4', '0');
INSERT INTO `t_areainfo` VALUES ('12', '0', '北京XX区5', '4', '0');
INSERT INTO `t_areainfo` VALUES ('13', '0', '北京XX区6', '4', '0');
INSERT INTO `t_areainfo` VALUES ('14', '0', '北京XX区7', '4', '0');
INSERT INTO `t_areainfo` VALUES ('15', '0', '北京XX区8', '4', '0');
INSERT INTO `t_areainfo` VALUES ('16', '0', '北京XX区9', '4', '0');
INSERT INTO `t_areainfo` VALUES ('17', '0', '北京XX区10', '4', '0');
INSERT INTO `t_areainfo` VALUES ('18', '0', '北京XX区11', '4', '0');
INSERT INTO `t_areainfo` VALUES ('19', '0', '北京XX区12', '4', '0');
INSERT INTO `t_areainfo` VALUES ('20', '0', '北京XX区13', '4', '0');
INSERT INTO `t_areainfo` VALUES ('21', '0', '北京XX区14', '4', '0');
INSERT INTO `t_areainfo` VALUES ('22', '0', '北京XX区15', '4', '0');
INSERT INTO `t_areainfo` VALUES ('23', '0', '北京XX区16', '4', '0');
INSERT INTO `t_areainfo` VALUES ('24', '0', '北京XX区17', '4', '0');
INSERT INTO `t_areainfo` VALUES ('25', '0', '北京XX区18', '4', '0');
INSERT INTO `t_areainfo` VALUES ('26', '0', '北京XX区19', '4', '0');
INSERT INTO `t_areainfo` VALUES ('27', '0', '北京XX区1', '4', '0');
INSERT INTO `t_areainfo` VALUES ('28', '0', '北京XX区2', '4', '0');
INSERT INTO `t_areainfo` VALUES ('29', '0', '北京XX区3', '4', '0');
INSERT INTO `t_areainfo` VALUES ('30', '0', '北京XX区4', '4', '0');
INSERT INTO `t_areainfo` VALUES ('31', '0', '北京XX区5', '4', '0');
INSERT INTO `t_areainfo` VALUES ('32', '0', '北京XX区6', '4', '0');
INSERT INTO `t_areainfo` VALUES ('33', '0', '北京XX区7', '4', '0');
INSERT INTO `t_areainfo` VALUES ('34', '0', '北京XX区8', '4', '0');
INSERT INTO `t_areainfo` VALUES ('35', '0', '北京XX区9', '4', '0');
INSERT INTO `t_areainfo` VALUES ('36', '0', '北京XX区10', '4', '0');
INSERT INTO `t_areainfo` VALUES ('37', '0', '北京XX区11', '4', '0');
INSERT INTO `t_areainfo` VALUES ('38', '0', '北京XX区12', '4', '0');
INSERT INTO `t_areainfo` VALUES ('39', '0', '北京XX区13', '4', '0');
INSERT INTO `t_areainfo` VALUES ('40', '0', '北京XX区14', '4', '0');
INSERT INTO `t_areainfo` VALUES ('41', '0', '北京XX区15', '4', '0');
INSERT INTO `t_areainfo` VALUES ('42', '0', '北京XX区16', '4', '0');
INSERT INTO `t_areainfo` VALUES ('43', '0', '北京XX区17', '4', '0');
INSERT INTO `t_areainfo` VALUES ('44', '0', '北京XX区18', '4', '0');
INSERT INTO `t_areainfo` VALUES ('45', '0', '北京XX区19', '4', '0');
INSERT INTO `t_areainfo` VALUES ('46', '0', 'xx省1', '1', '0');
INSERT INTO `t_areainfo` VALUES ('47', '0', 'xx省2', '1', '0');
INSERT INTO `t_areainfo` VALUES ('48', '0', 'xx省3', '1', '0');
INSERT INTO `t_areainfo` VALUES ('49', '0', 'xx省4', '1', '0');
INSERT INTO `t_areainfo` VALUES ('50', '0', 'xx省5', '1', '0');
INSERT INTO `t_areainfo` VALUES ('51', '0', 'xx省6', '1', '0');
INSERT INTO `t_areainfo` VALUES ('52', '0', 'xx省7', '1', '0');
INSERT INTO `t_areainfo` VALUES ('53', '0', 'xx省8', '1', '0');
INSERT INTO `t_areainfo` VALUES ('54', '0', 'xx省9', '1', '0');
INSERT INTO `t_areainfo` VALUES ('55', '0', 'xx省10', '1', '0');
INSERT INTO `t_areainfo` VALUES ('56', '0', 'xx省11', '1', '0');
INSERT INTO `t_areainfo` VALUES ('57', '0', 'xx省12', '1', '0');
INSERT INTO `t_areainfo` VALUES ('58', '0', 'xx省13', '1', '0');
INSERT INTO `t_areainfo` VALUES ('59', '0', 'xx省14', '1', '0');
INSERT INTO `t_areainfo` VALUES ('60', '0', 'xx省15', '1', '0');
INSERT INTO `t_areainfo` VALUES ('61', '0', 'xx省16', '1', '0');
INSERT INTO `t_areainfo` VALUES ('62', '0', 'xx省17', '1', '0');
INSERT INTO `t_areainfo` VALUES ('63', '0', 'xx省18', '1', '0');
INSERT INTO `t_areainfo` VALUES ('64', '0', 'xx省19', '1', '0');

-- ----------------------------
-- Table structure for t_article
-- ----------------------------
DROP TABLE IF EXISTS `t_article`;
CREATE TABLE `t_article` (
  `article_id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL,
  `title` varchar(100) NOT NULL,
  `content` text NOT NULL,
  PRIMARY KEY (`article_id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_article
-- ----------------------------
INSERT INTO `t_article` VALUES ('1', '1', 'test_title', 'test_content');
INSERT INTO `t_article` VALUES ('2', '1', 'test_title_2', 'test_content_2');
INSERT INTO `t_article` VALUES ('3', '1', 'test_title_3', 'test_content_3');
INSERT INTO `t_article` VALUES ('4', '1', 'test_title_4', 'test_content_4');

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
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_menu
-- ----------------------------
INSERT INTO `t_menu` VALUES ('1', '基本元素', 'fa-cubes', '1', '', '0', '1', '99.000');
INSERT INTO `t_menu` VALUES ('2', '按钮', '&#xe641;', null, 'button.html', '1', '1', '1.000');
INSERT INTO `t_menu` VALUES ('3', '表单', '&#xe63c;', null, 'form.html', '1', '1', '2.000');
INSERT INTO `t_menu` VALUES ('4', '表格', '&#xe63c;', null, 'table.html', '1', '1', '3.000');
INSERT INTO `t_menu` VALUES ('5', '导航', '&#xe609;', null, 'nav.html', '1', '1', '4.000');
INSERT INTO `t_menu` VALUES ('6', 'Tab选项卡', '&#xe62a;', null, 'tab.html', '1', '1', '5.000');
INSERT INTO `t_menu` VALUES ('7', '辅助性元素', '&#xe60c;', null, 'auxiliar.html', '1', '1', '6.000');
INSERT INTO `t_menu` VALUES ('8', '组件', 'fa-cogs', '0', null, '0', '1', '100.000');
INSERT INTO `t_menu` VALUES ('9', 'Datatable', 'fa-table', null, 'begtable.html', '8', '1', '1.000');
INSERT INTO `t_menu` VALUES ('10', 'Navbar组件', 'fa-navicon', null, 'navbar.html', '8', '1', '2.000');
INSERT INTO `t_menu` VALUES ('11', '第三方组件', '&#x1002;', '0', null, '0', '1', '101.000');
INSERT INTO `t_menu` VALUES ('12', 'iCheck组件', 'fa-check-square-o', '0', 'icheck.html', '11', '1', '1.000');
INSERT INTO `t_menu` VALUES ('13', '地址本', 'fa-address-book', '0', null, '0', '1', '102.000');
INSERT INTO `t_menu` VALUES ('14', 'Github', 'fa-github', null, 'https://www.github.com/', '13', '1', '1.000');
INSERT INTO `t_menu` VALUES ('15', 'QQ', 'fa-qq', null, 'http://www.qq.com/', '13', '1', '2.000');
INSERT INTO `t_menu` VALUES ('16', 'Fly社区', '&#xe609;', null, 'http://fly.layui.com/', '13', '1', '3.000');
INSERT INTO `t_menu` VALUES ('17', '新浪微博', 'fa-weibo', null, 'http://weibo.com/', '13', '1', '4.000');
INSERT INTO `t_menu` VALUES ('18', '这是一级导航', 'fa-stop-circle', null, 'https://www.baidu.com', '0', '1', '103.000');

-- ----------------------------
-- Table structure for t_permission
-- ----------------------------
DROP TABLE IF EXISTS `t_permission`;
CREATE TABLE `t_permission` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `permissionname` varchar(32) DEFAULT NULL,
  `role_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `id` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_permission
-- ----------------------------
INSERT INTO `t_permission` VALUES ('1', 'add', '2');
INSERT INTO `t_permission` VALUES ('2', 'del', '1');
INSERT INTO `t_permission` VALUES ('3', 'update', '2');
INSERT INTO `t_permission` VALUES ('4', 'query', '3');
INSERT INTO `t_permission` VALUES ('5', 'user:query', '1');
INSERT INTO `t_permission` VALUES ('6', 'user:edit', '2');

-- ----------------------------
-- Table structure for t_role
-- ----------------------------
DROP TABLE IF EXISTS `t_role`;
CREATE TABLE `t_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `rolename` varchar(32) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `id` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_role
-- ----------------------------
INSERT INTO `t_role` VALUES ('1', 'admin');
INSERT INTO `t_role` VALUES ('2', 'manager');
INSERT INTO `t_role` VALUES ('3', 'normal');
INSERT INTO `t_role` VALUES ('4', 'sa');
INSERT INTO `t_role` VALUES ('5', 'admin');
INSERT INTO `t_role` VALUES ('6', 'general');
INSERT INTO `t_role` VALUES ('7', 'sa');
INSERT INTO `t_role` VALUES ('8', 'admin');
INSERT INTO `t_role` VALUES ('9', 'general');
INSERT INTO `t_role` VALUES ('10', 'sa');
INSERT INTO `t_role` VALUES ('11', 'admin');
INSERT INTO `t_role` VALUES ('12', 'general');
INSERT INTO `t_role` VALUES ('13', 'sa');
INSERT INTO `t_role` VALUES ('14', 'admin');
INSERT INTO `t_role` VALUES ('15', 'general');
INSERT INTO `t_role` VALUES ('16', 'sa');
INSERT INTO `t_role` VALUES ('17', 'admin');
INSERT INTO `t_role` VALUES ('18', 'general');
INSERT INTO `t_role` VALUES ('19', 'sa');
INSERT INTO `t_role` VALUES ('20', 'admin');
INSERT INTO `t_role` VALUES ('21', 'general');

-- ----------------------------
-- Table structure for t_system_log
-- ----------------------------
DROP TABLE IF EXISTS `t_system_log`;
CREATE TABLE `t_system_log` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `module` varchar(100) DEFAULT NULL COMMENT '模块名',
  `function` varchar(2000) DEFAULT NULL COMMENT '功能',
  `operation` varchar(2000) DEFAULT NULL COMMENT '操作',
  `note` varchar(2000) DEFAULT NULL COMMENT '备注',
  `content` varchar(2000) DEFAULT NULL COMMENT '操作内容',
  `run_class` varchar(2000) DEFAULT NULL COMMENT '运行类全名',
  `create_time` datetime DEFAULT NULL COMMENT '执行时间',
  `user_id` int(11) DEFAULT NULL COMMENT '操作人id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_system_log
-- ----------------------------
INSERT INTO `t_system_log` VALUES ('1', '测试', '测试查询列表', '查询', '非异步', '[参数1，类型：User，值：]', 'com.billow.service.impl.UserServiceImpl.findUserList', '2017-01-12 17:55:56', null);
INSERT INTO `t_system_log` VALUES ('2', '测试', '测试查询列表', '查询', '非异步', '[参数1，类型：User，值：]', 'com.billow.service.impl.UserServiceImpl.findUserList', '2017-01-12 18:15:04', null);
INSERT INTO `t_system_log` VALUES ('3', '测试', '测试查询列表', '查询', '非异步', '[参数1，类型：BindingAwareModelMap，值：][参数2，类型：User，值：][参数3，类型：Request，值：', 'com.billow.controller.TestController.testList', '2017-01-12 19:06:07', null);
INSERT INTO `t_system_log` VALUES ('4', '测试', '测试查询列表', '查询', '非异步', '[参数1，类型：BindingAwareModelMap，值：][参数2，类型：User，值：][参数3，类型：Request，值：', 'com.billow.controller.TestController.testList', '2017-01-12 19:06:21', null);
INSERT INTO `t_system_log` VALUES ('5', '测试', '测试查询列表', '查询', '非异步', '[参数1，类型：BindingAwareModelMap，值：][参数2，类型：User，值：][参数3，类型：Request，值：', 'com.billow.controller.TestController.testList', '2017-01-12 19:12:39', null);
INSERT INTO `t_system_log` VALUES ('6', '测试', '测试查询列表', '查询', '非异步', '[参数1，类型：BindingAwareModelMap，值：][参数2，类型：User，值：][参数3，类型：Request，值：', 'com.billow.controller.TestController.testList', '2017-01-12 19:27:44', null);
INSERT INTO `t_system_log` VALUES ('7', '测试', '测试查询列表', '查询', '非异步', '[参数1，类型：BindingAwareModelMap，值：[参数2，类型：User，值：][参数3，类型：Request，值：', 'com.billow.controller.TestController.testList', '2017-01-16 20:30:33', null);
INSERT INTO `t_system_log` VALUES ('8', '测试', '测试查询列表', '查询', '非异步', '[参数1，类型：BindingAwareModelMap，值：[参数2，类型：User，值：(getPassword : 33)(getUserName : 11)(getPhoneNumber : 44)(getAge : 22)][参数3，类型：Request，值：', 'com.billow.controller.TestController.testList', '2017-01-16 20:40:52', null);
INSERT INTO `t_system_log` VALUES ('9', '测试', '测试查询列表', '查询', '非异步', '[参数1，类型：BindingAwareModelMap，值：[参数2，类型：User，值：(getPassword : 333)(getUserName : 11)(getPhoneNumber : 444)(getAge : 222)][参数3，类型：Request，值：', 'com.billow.controller.TestController.testList', '2017-01-16 20:43:57', null);
INSERT INTO `t_system_log` VALUES ('10', '测试', '测试查询列表', '查询', '非异步', '[参数2，类型：User，值：(getPassword : 333)(getUserName : 11)(getPhoneNumber : 444)(getAge : 222)]', 'com.billow.controller.TestController.testList', '2017-01-16 20:45:37', null);
INSERT INTO `t_system_log` VALUES ('11', '测试', '测试查询列表', '查询', '非异步', '[参数1，类型：User，值：(getPassword : 33)(getUserName : 11)(getAge : 22)(getPhoneNumber : 444)]', 'com.billow.controller.TestController.testList', '2017-01-16 20:53:18', null);
INSERT INTO `t_system_log` VALUES ('12', '测试', '测试查询列表', '查询', '非异步', '[参数1，类型：User，值：(getAge : 22)(getSystemLog : com.billow.model.SystemLog@51cf2611)(getUserName : 11)(getPassword : 33)(getPhoneNumber : 44)]', 'com.billow.controller.TestController.testList', '2017-01-17 09:00:05', null);
INSERT INTO `t_system_log` VALUES ('13', '测试', '测试查询列表', '查询', '非异步', '[参数1，类型：User，值：(getAge : 22)(getSystemLog : com.billow.model.SystemLog@3b7b64a3)(getUserName : 11)(getPassword : 33)(getPhoneNumber : )]', 'com.billow.controller.TestController.testList', '2017-01-17 09:01:28', null);
INSERT INTO `t_system_log` VALUES ('14', '测试', '测试查询列表', '查询', '非异步', '[参数1，类型：User，值：[{\"age\":22,\"password\":\"33\",\"phoneNumber\":\"\",\"systemLog\":{\"function\":\"\",\"module\":\"ee\",\"operation\":\"ttt\"},\"userName\":\"11\"},{\"$ref\":\"$[0]\"},{\"$ref\":\"$[0]\"},{\"$ref\":\"$[0]\"},{\"$ref\":\"$[0]\"},{\"$ref\":\"$[0]\"},{\"$ref\":\"$[0]\"},{\"$ref\":\"$[0]\"},{\"$ref\":\"$[0]\"},{\"$ref\":\"$[0]\"},{\"$ref\":\"$[0]\"},{\"$ref\":\"$[0]\"}]]', 'com.billow.controller.TestController.testList', '2017-01-17 09:05:01', null);
INSERT INTO `t_system_log` VALUES ('15', '测试', '测试查询列表', '查询', '非异步', '[参数1，类型：User，值：[{\"age\":22,\"password\":\"33\",\"phoneNumber\":\"\",\"systemLog\":{\"function\":\"\",\"module\":\"ee\",\"operation\":\"ttt\"},\"userName\":\"11\"},{\"$ref\":\"$[0]\"},{\"$ref\":\"$[0]\"},{\"$ref\":\"$[0]\"},{\"$ref\":\"$[0]\"}]]', 'com.billow.controller.TestController.testList', '2017-01-17 09:06:35', null);
INSERT INTO `t_system_log` VALUES ('16', '测试', '测试查询列表', '查询', '非异步', '[参数1，类型：User，值：(getPassword : 33)(getUserName : 11)(getPhoneNumber : )(getAge : 22)(getSystemLog : com.billow.model.SystemLog@e2a01da)]', 'com.billow.controller.TestController.testList', '2017-01-17 09:08:20', null);
INSERT INTO `t_system_log` VALUES ('17', '测试', '测试查询列表', '查询', '非异步', '[参数1，类型：User，值：{\"age\":22,\"password\":\"33\",\"phoneNumber\":\"\",\"systemLog\":{\"function\":\"\",\"module\":\"ee\",\"operation\":\"ttt\"},\"userName\":\"11\"}]', 'com.billow.controller.TestController.testList', '2017-01-17 09:10:13', null);
INSERT INTO `t_system_log` VALUES ('18', '测试', '测试查询列表', '查询', '非异步', '[参数1,类型:User,值:{\"age\":22,\"password\":\"33\",\"phoneNumber\":\"\",\"systemLog\":{\"function\":\"\",\"module\":\"ee\",\"operation\":\"ttt\"},\"userName\":\"11\"}]', 'com.billow.controller.TestController.testList', '2017-01-17 09:15:22', null);
INSERT INTO `t_system_log` VALUES ('19', '用户管理', '查询用户列表', '查询', '非异步', '[参数1,类型:User,值:{\"pageNo\":1,\"pageSize\":10,\"recordCount\":21}]', 'com.billow.controller.UserController.findUserList', '2017-01-18 18:00:51', null);
INSERT INTO `t_system_log` VALUES ('20', '用户管理', '查询用户列表', '查询', '非异步', '[参数1,类型:User,值:{\"pageNo\":2,\"pageSize\":10,\"recordCount\":21}]', 'com.billow.controller.UserController.findUserList', '2017-01-18 18:01:08', null);
INSERT INTO `t_system_log` VALUES ('21', '用户管理', '查询用户列表', '查询', '非异步', '[参数1,类型:User,值:{\"pageNo\":3,\"pageSize\":10,\"recordCount\":21}]', 'com.billow.controller.UserController.findUserList', '2017-01-18 18:01:14', null);
INSERT INTO `t_system_log` VALUES ('22', '用户管理', '查询用户列表', '查询', '非异步', '[参数1,类型:User,值:{\"pageNo\":1,\"pageSize\":10,\"recordCount\":21}]', 'com.billow.controller.UserController.findUserList', '2017-01-18 18:01:49', null);

-- ----------------------------
-- Table structure for t_user
-- ----------------------------
DROP TABLE IF EXISTS `t_user`;
CREATE TABLE `t_user` (
  `user_id` int(11) NOT NULL AUTO_INCREMENT,
  `user_name` varchar(10) DEFAULT NULL,
  `age` int(11) DEFAULT NULL,
  `password` varchar(10) DEFAULT NULL,
  `phone_number` varchar(20) NOT NULL,
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=53 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_user
-- ----------------------------
INSERT INTO `t_user` VALUES ('1', '张三666', '25', null, '15507529497');
INSERT INTO `t_user` VALUES ('2', '22222', '34', null, '15555555555');
INSERT INTO `t_user` VALUES ('3', 'eee', '23', null, '');
INSERT INTO `t_user` VALUES ('4', '张三', '24', null, '');
INSERT INTO `t_user` VALUES ('5', 'ggg', '22', null, '');
INSERT INTO `t_user` VALUES ('6', null, null, null, '');
INSERT INTO `t_user` VALUES ('7', null, null, null, '');
INSERT INTO `t_user` VALUES ('8', 'gggr', '34', null, '');
INSERT INTO `t_user` VALUES ('9', 'hhh', '23', null, '');
INSERT INTO `t_user` VALUES ('37', 'XXXX', null, null, '22222');
INSERT INTO `t_user` VALUES ('40', 'XXXX', null, null, '22222');
INSERT INTO `t_user` VALUES ('41', 'XXXX', null, null, '22222');
INSERT INTO `t_user` VALUES ('42', 'XXXX', null, null, '22222');
INSERT INTO `t_user` VALUES ('43', 'XXXX', null, null, '22222');
INSERT INTO `t_user` VALUES ('44', 'XXXX', null, null, '22222');
INSERT INTO `t_user` VALUES ('45', 'XXXX', null, null, '22222');
INSERT INTO `t_user` VALUES ('46', 'XXXX', null, null, '22222');
INSERT INTO `t_user` VALUES ('48', 'XXXX', null, null, '22222');
INSERT INTO `t_user` VALUES ('49', 'XXXX', null, null, '22222');
INSERT INTO `t_user` VALUES ('51', 'XXXX', null, null, '22222');
INSERT INTO `t_user` VALUES ('52', 'XXXX', null, null, '22222');

-- ----------------------------
-- Table structure for t_user_role
-- ----------------------------
DROP TABLE IF EXISTS `t_user_role`;
CREATE TABLE `t_user_role` (
  `user_id` int(11) DEFAULT NULL,
  `role_id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_user_role
-- ----------------------------
INSERT INTO `t_user_role` VALUES ('1', '1');
INSERT INTO `t_user_role` VALUES ('1', '3');
INSERT INTO `t_user_role` VALUES ('2', '2');
INSERT INTO `t_user_role` VALUES ('2', '3');
INSERT INTO `t_user_role` VALUES ('3', '3');

-- ----------------------------
-- Function structure for getChildLst
-- ----------------------------
DROP FUNCTION IF EXISTS `getChildLst`;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` FUNCTION `getChildLst`(rootId INT) RETURNS varchar(1000) CHARSET utf8
BEGIN
	DECLARE
		sTemp VARCHAR (1000);

DECLARE
	sTempChd VARCHAR (1000);


SET sTemp = '$';


SET sTempChd = cast(rootId AS CHAR);


WHILE sTempChd IS NOT NULL DO

SET sTemp = concat(sTemp, ',', sTempChd);

SELECT
	group_concat(id) INTO sTempChd
FROM
	treenodes
WHERE
	FIND_IN_SET(pid, sTempChd) > 0;


END
WHILE;

RETURN sTemp;


END
;;
DELIMITER ;

-- ----------------------------
-- Function structure for getMenuChildList
-- ----------------------------
DROP FUNCTION IF EXISTS `getMenuChildList`;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` FUNCTION `getMenuChildList`(menuId INT) RETURNS varchar(4000) CHARSET utf8
BEGIN
	DECLARE
		sReturnTemp VARCHAR (4000);

DECLARE
	sConcatTemp VARCHAR (4000);


SET sReturnTemp = '$';


SET sConcatTemp = CAST(menuId AS CHAR);


WHILE sConcatTemp IS NOT NULL DO

SET sReturnTemp = CONCAT(
	sReturnTemp,
	',',
	sConcatTemp
);

SELECT
	GROUP_CONCAT(id) INTO sConcatTemp
FROM
	t_menu
WHERE
	FIND_IN_SET(pid, sConcatTemp) > 0;


END
WHILE;

RETURN sReturnTemp;


END
;;
DELIMITER ;

-- ----------------------------
-- Function structure for getParLst
-- ----------------------------
DROP FUNCTION IF EXISTS `getParLst`;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` FUNCTION `getParLst`(rootId INT) RETURNS varchar(1000) CHARSET utf8
BEGIN
	DECLARE sTemp VARCHAR(1000);
    DECLARE sTempChd VARCHAR(1000);

    SET sTemp = '$';
    SET sTempChd =cast(rootId as CHAR);

    WHILE sTempChd is not null DO
    	SET sTemp = concat(sTemp,',',sTempChd);
        SELECT group_concat(id) INTO sTempChd FROM  treeNodes where FIND_IN_SET(pid,sTempChd)>0;
   	END WHILE;
    RETURN sTemp; 
END
;;
DELIMITER ;

-- ----------------------------
-- Function structure for queryChildrenAreaInfo
-- ----------------------------
DROP FUNCTION IF EXISTS `queryChildrenAreaInfo`;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` FUNCTION `queryChildrenAreaInfo`(areaId INT) RETURNS varchar(4000) CHARSET utf8
BEGIN
	DECLARE
		sTemp VARCHAR (4000);

DECLARE
	sTempChd VARCHAR (4000);


SET sTemp = '$';


SET sTempChd = cast(areaId AS CHAR);


WHILE sTempChd IS NOT NULL DO

SET sTemp = CONCAT(sTemp, ',', sTempChd);

SELECT
	group_concat(id) INTO sTempChd
FROM
	t_areainfo
WHERE
	FIND_IN_SET(parentId, sTempChd) > 0;


END
WHILE;

RETURN sTemp;


END
;;
DELIMITER ;

-- ----------------------------
-- Function structure for queryChildrenAreaInfo1
-- ----------------------------
DROP FUNCTION IF EXISTS `queryChildrenAreaInfo1`;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` FUNCTION `queryChildrenAreaInfo1`(areaId INT) RETURNS varchar(4000) CHARSET utf8
BEGIN
DECLARE sTemp VARCHAR(4000);
DECLARE sTempChd VARCHAR(4000);

SET sTemp='$';
SET sTempChd = CAST(areaId AS CHAR);
SET sTemp = CONCAT(sTemp,',',sTempChd);

SELECT parentId INTO sTempChd FROM t_areainfo WHERE id = sTempChd;
WHILE sTempChd <> 0 DO
SET sTemp = CONCAT(sTemp,',',sTempChd);
SELECT parentId INTO sTempChd FROM t_areainfo WHERE id = sTempChd;
END WHILE;
RETURN sTemp;
END
;;
DELIMITER ;
