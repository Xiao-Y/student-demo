/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50614
Source Host           : localhost:3306
Source Database       : mytestdb

Target Server Type    : MYSQL
Target Server Version : 50614
File Encoding         : 65001

Date: 2017-09-27 17:21:30
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Records of act_ru_variable
-- ----------------------------

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
-- Table structure for t_dictionary
-- ----------------------------
DROP TABLE IF EXISTS `t_dictionary`;
CREATE TABLE `t_dictionary` (
  `ID` varchar(100) NOT NULL,
  `CREATE_TIME` datetime DEFAULT NULL,
  `UPDATE_TIME` datetime DEFAULT NULL,
  `DISPLAY_FIELD` varchar(100) DEFAULT NULL,
  `FIELD_CODE` varchar(100) NOT NULL,
  `FIELD_NAME` varchar(100) NOT NULL,
  `MODEL_CODE` varchar(100) NOT NULL,
  `MODEL_NAME` varchar(100) NOT NULL,
  `NOTICE` varchar(200) DEFAULT NULL,
  `VALUE_FIELD` varchar(100) NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_dictionary
-- ----------------------------
INSERT INTO `t_dictionary` VALUES ('004e12a6-8b33-4d63-8e0d-1d315d8422b0', '2016-04-17 18:55:59', '2016-04-17 19:02:46', 'Classic主题', 'theme', '主题', 'home', '主页模块', 'home', 'classic');
INSERT INTO `t_dictionary` VALUES ('09c2eaeb-23b7-450a-b82a-90bcb96b5ebc', '2016-04-17 18:20:38', '2016-04-17 18:20:38', '页面问题', 'bugType', 'BUG类型', 'bug', 'BUG管理', 'system', '4');
INSERT INTO `t_dictionary` VALUES ('1', null, null, '树枝节点', 'menuType', '节点类型', 'menu', '菜单管理', 'system', '0');
INSERT INTO `t_dictionary` VALUES ('119917d9-fb31-4c81-8d08-6e46099a7a39', '2016-04-17 18:26:46', '2016-04-17 18:26:46', '一般', 'severity', '严重程度', 'bug', 'BUG管理', 'system', '2');
INSERT INTO `t_dictionary` VALUES ('12a98e9b-e1cc-465f-8898-12371a8674df', '2016-04-17 18:55:59', '2016-04-17 19:02:46', 'Aria主题', 'theme', '主题', 'home', '主页模块', 'home', 'aria');
INSERT INTO `t_dictionary` VALUES ('13b54aac-f3e5-4334-b52d-3030f93d1a32', '2016-04-17 18:26:46', '2016-04-17 18:26:46', '致命', 'severity', '严重程度', 'bug', 'BUG管理', 'system', '4');
INSERT INTO `t_dictionary` VALUES ('17b364dd-2ba3-4b47-a70b-50d54f2c5383', '2016-04-17 18:20:38', '2016-04-17 18:20:38', '性能问题', 'bugType', 'BUG类型', 'bug', 'BUG管理', 'system', '2');
INSERT INTO `t_dictionary` VALUES ('2', '2016-04-11 17:24:31', '2016-04-17 17:24:35', '叶子节点', 'menuType', '节点类型', 'menu', '菜单管理', 'system', '1');
INSERT INTO `t_dictionary` VALUES ('2f1887d4-c2ca-4f9d-8cf5-567d0e5c6109', '2016-04-17 18:26:46', '2016-04-17 18:26:46', '提示', 'severity', '严重程度', 'bug', 'BUG管理', 'system', '1');
INSERT INTO `t_dictionary` VALUES ('3', '2016-04-17 17:52:08', '2016-04-17 17:52:11', '导出操作', 'operation', '操作类型', 'log', '日志管理', 'system', 'EXPORT');
INSERT INTO `t_dictionary` VALUES ('4', '2016-04-17 17:52:08', '2016-04-17 17:52:11', '删除操作', 'operation', '操作类型', 'log', '日志管理', 'system', 'DELETE');
INSERT INTO `t_dictionary` VALUES ('5', '2016-04-17 17:52:08', '2016-04-17 17:52:11', '修改操作', 'operation', '操作类型', 'log', '日志管理', 'system', 'UPDATE');
INSERT INTO `t_dictionary` VALUES ('54b61336-7982-4621-8da1-64c93f9fcbda', '2016-04-17 18:55:59', '2016-04-17 19:02:46', 'Neptune主题', 'theme', '主题', 'home', '主页模块', 'home', 'neptune');
INSERT INTO `t_dictionary` VALUES ('55df21ff-abb1-4c2c-b2d5-15e929414c37', '2016-04-17 18:28:42', '2016-04-17 18:28:42', '已修改', 'status', 'BUG状态', 'bug', 'BUG管理', 'system', '1');
INSERT INTO `t_dictionary` VALUES ('5ef5e8ef-8562-416e-815f-3de82ca36b48', '2016-04-17 18:55:59', '2016-04-17 19:02:46', 'Crisp主题', 'theme', '主题', 'home', '主页模块', 'home', 'crisp');
INSERT INTO `t_dictionary` VALUES ('6', '2016-04-17 17:52:08', '2016-04-17 17:52:11', '添加操作', 'operation', '操作类型', 'log', '日志管理', 'system', 'ADD');
INSERT INTO `t_dictionary` VALUES ('7', '2016-04-17 17:52:08', '2016-04-17 17:52:11', '导入操作', 'operation', '操作类型', 'log', '日志管理', 'system', 'IMPORT');
INSERT INTO `t_dictionary` VALUES ('70db278a-e652-48e8-90b4-e5446f113579', '2016-04-17 18:28:42', '2016-04-17 18:28:42', '未修改', 'status', 'BUG状态', 'bug', 'BUG管理', 'system', '2');
INSERT INTO `t_dictionary` VALUES ('7429ef61-6388-4ec5-95ae-a123e77e7e67', '2016-04-17 18:24:47', '2016-04-17 18:24:47', '随机重现', 'reappear', '重现规律', 'bug', 'BUG管理', 'system', '2');
INSERT INTO `t_dictionary` VALUES ('90948c46-d52a-40cc-be48-82f9e19060d9', '2016-04-17 18:20:38', '2016-04-17 18:20:38', '其它问题', 'bugType', 'BUG类型', 'bug', 'BUG管理', 'system', '5');
INSERT INTO `t_dictionary` VALUES ('94cc0209-61bc-493f-826b-81b693d048ac', '2016-04-17 18:20:38', '2016-04-17 18:20:38', '安全问题', 'bugType', 'BUG类型', 'bug', 'BUG管理', 'system', '3');
INSERT INTO `t_dictionary` VALUES ('9bcf8792-58cf-4b2c-b263-f0a99bdafaad', '2016-04-17 18:20:38', '2016-04-17 18:20:38', '功能问题', 'bugType', 'BUG类型', 'bug', 'BUG管理', 'system', '6');
INSERT INTO `t_dictionary` VALUES ('9ccd3eb7-9047-42a1-99f5-b3e18af31cbf', '2016-04-24 00:33:21', '2016-04-24 00:33:21', '已授权', 'authorizeStatus', '授权状态', 'role', '角色管理', 'system', '1');
INSERT INTO `t_dictionary` VALUES ('c6d501a5-730b-4430-b1fb-860431d68ec8', '2016-04-17 18:20:38', '2016-04-17 18:20:38', '规范问题', 'bugType', 'BUG类型', 'bug', 'BUG管理', 'system', '1');
INSERT INTO `t_dictionary` VALUES ('c895e68c-cefc-436b-81d4-18622fb5cd87', '2016-04-18 17:14:33', '2016-04-18 17:14:33', '按钮', 'menuType', '节点类型', 'menu', '菜单管理', 'system', '3');
INSERT INTO `t_dictionary` VALUES ('d9886963-0d89-4f8b-83fd-301862fa3429', '2016-04-24 00:33:21', '2016-04-24 00:33:21', '未授权', 'authorizeStatus', '授权状态', 'role', '角色管理', 'system', '0');
INSERT INTO `t_dictionary` VALUES ('dc243cb6-b8f6-4d7e-85ad-46f5fe31dcb5', '2016-04-17 18:55:59', '2016-04-17 19:02:46', 'Gray主题', 'theme', '主题', 'home', '主页模块', 'home', 'gray');
INSERT INTO `t_dictionary` VALUES ('e676474a-139b-43d6-8a37-9d9e2950eec1', '2016-04-17 18:24:47', '2016-04-17 18:24:47', '必然重现', 'reappear', '重现规律', 'bug', 'BUG管理', 'system', '3');
INSERT INTO `t_dictionary` VALUES ('eea6581b-c150-41fb-b03f-7c39b7c79c92', '2016-04-17 18:26:46', '2016-04-17 18:26:46', '严重', 'severity', '严重程度', 'bug', 'BUG管理', 'system', '3');
INSERT INTO `t_dictionary` VALUES ('f58496a1-0be1-4f3d-899e-854e1659ea74', '2016-04-17 18:24:47', '2016-04-17 18:24:47', '很难重现', 'reappear', '重现规律', 'bug', 'BUG管理', 'system', '1');
INSERT INTO `t_dictionary` VALUES ('fbbe587a-6644-4a5d-a34d-75edcf09dd55', '2016-04-17 18:26:46', '2016-04-17 18:26:46', '建议', 'severity', '严重程度', 'bug', 'BUG管理', 'system', '5');

-- ----------------------------
-- Table structure for t_leave
-- ----------------------------
DROP TABLE IF EXISTS `t_leave`;
CREATE TABLE `t_leave` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) DEFAULT NULL COMMENT '用户id',
  `start_time` datetime DEFAULT NULL COMMENT '请假起始时间',
  `end_time` datetime DEFAULT NULL COMMENT '请假结束时间',
  `apply_time` datetime DEFAULT NULL COMMENT '请假申请时间',
  `leave_type` varchar(3) NOT NULL COMMENT '请假类型',
  `reason` varchar(400) DEFAULT NULL COMMENT '原因',
  `status` varchar(2) NOT NULL COMMENT '状态 1-提交申请，3-审批中，5-审批通过，7-驳回',
  `user_name` varchar(11) DEFAULT NULL COMMENT '用户名称',
  `type` varchar(11) DEFAULT NULL COMMENT '流程类型',
  `process_instance_id` varchar(100) DEFAULT NULL COMMENT '流程实例id',
  PRIMARY KEY (`id`,`leave_type`,`status`)
) ENGINE=InnoDB AUTO_INCREMENT=54 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_leave
-- ----------------------------
INSERT INTO `t_leave` VALUES ('15', null, '2017-06-21 00:00:00', '2017-06-23 00:00:00', '2017-06-21 15:46:06', '1', '121212', '3', 'billow', 'ordinary', null);
INSERT INTO `t_leave` VALUES ('16', '1', '2017-06-21 00:00:00', '2017-06-23 00:00:00', '2017-06-21 16:07:02', '3', '123123', '3', 'billow', 'ordinary', null);
INSERT INTO `t_leave` VALUES ('17', '1', '2017-06-21 00:00:00', '2017-06-23 00:00:00', '2017-06-21 16:35:39', '2', '1231231', '3', 'billow', 'ordinary', null);
INSERT INTO `t_leave` VALUES ('18', '1', '2017-06-21 00:00:00', '2017-06-30 00:00:00', '2017-06-21 17:04:44', '4', '1111', '3', 'billow', 'ordinary', null);
INSERT INTO `t_leave` VALUES ('19', '1', '2017-06-24 00:00:00', '2017-06-30 00:00:00', '2017-06-23 08:34:29', '1', '1231231231', '9', 'billow', 'ordinary', null);
INSERT INTO `t_leave` VALUES ('20', '1', '2017-06-26 00:00:00', '2017-06-29 00:00:00', '2017-06-26 16:11:41', '2', '123123', '9', 'billow', 'ordinary', null);
INSERT INTO `t_leave` VALUES ('21', '1', '2017-06-26 00:00:00', '2017-06-21 00:00:00', '2017-06-26 16:29:06', '2', '444', '9', 'billow', 'ordinary', null);
INSERT INTO `t_leave` VALUES ('27', '1', '2017-06-29 00:00:00', '2017-06-30 00:00:00', '2017-06-29 16:12:35', '2', '1111', '3', 'billow', 'formkey', null);
INSERT INTO `t_leave` VALUES ('28', '1', '2017-06-30 00:00:00', '2017-07-01 00:00:00', '2017-06-30 12:38:19', '1', '123123', '3', 'billow', 'formkey', null);
INSERT INTO `t_leave` VALUES ('29', '1', '2017-07-03 00:00:00', '2017-07-13 00:00:00', '2017-07-03 09:29:18', '2', '11111', '3', 'billow', 'formkey', null);
INSERT INTO `t_leave` VALUES ('30', '1', '2017-07-03 00:00:00', '2017-07-13 00:00:00', '2017-07-03 09:39:36', '2', '222', '3', 'billow', 'formkey', null);
INSERT INTO `t_leave` VALUES ('31', '1', '2017-07-03 00:00:00', '2017-07-20 00:00:00', '2017-07-03 09:45:29', '2', '111', '3', 'billow', 'formkey', null);
INSERT INTO `t_leave` VALUES ('32', '1', '2017-07-03 00:00:00', '2017-07-12 00:00:00', '2017-07-03 09:47:46', '2', '444444444', '3', 'billow', 'formkey', null);
INSERT INTO `t_leave` VALUES ('33', '1', '2017-07-03 00:00:00', '2017-07-20 00:00:00', '2017-07-03 10:19:19', '2', '1111', '3', 'billow', 'formkey', null);
INSERT INTO `t_leave` VALUES ('34', '1', '2017-07-03 00:00:00', '2017-07-20 00:00:00', '2017-07-03 10:26:19', '2', '2222', '1', 'billow', 'formkey', null);
INSERT INTO `t_leave` VALUES ('35', '1', '2017-07-03 00:00:00', '2017-07-20 00:00:00', '2017-07-03 10:27:42', '1', '3333', '3', 'billow', 'formkey', null);
INSERT INTO `t_leave` VALUES ('36', '1', '2017-07-03 00:00:00', '2017-07-12 00:00:00', '2017-07-03 10:38:37', '1', '111', '3', 'billow', 'formkey', null);
INSERT INTO `t_leave` VALUES ('37', '1', '2017-07-11 00:00:00', '2017-07-19 00:00:00', '2017-07-03 10:43:06', '3', '222', '3', 'billow', 'formkey', null);
INSERT INTO `t_leave` VALUES ('38', '1', '2017-07-03 00:00:00', '2017-07-26 00:00:00', '2017-07-03 17:21:32', '3', '111', '3', 'billow', 'formkey', null);
INSERT INTO `t_leave` VALUES ('39', '1', '2017-07-03 00:00:00', '2017-07-05 00:00:00', '2017-07-03 17:49:52', '2', '1111', '3', 'billow', 'formkey', null);
INSERT INTO `t_leave` VALUES ('40', '1', '2017-07-03 00:00:00', '2017-07-05 00:00:00', '2017-07-03 17:49:53', '2', '1111', '3', 'billow', 'formkey', null);
INSERT INTO `t_leave` VALUES ('41', '1', '2017-07-03 00:00:00', '2017-07-06 00:00:00', '2017-07-03 18:01:00', '1', '123123123', '3', 'billow', 'formkey', null);
INSERT INTO `t_leave` VALUES ('42', '1', '2017-07-03 00:00:00', '2017-07-31 00:00:00', '2017-07-03 18:04:00', '4', '123123131', '3', 'billow', 'formkey', null);
INSERT INTO `t_leave` VALUES ('43', '1', '2017-07-03 00:00:00', '2017-07-21 00:00:00', '2017-07-03 18:06:54', '3', '1111', '3', 'billow', 'formkey', null);
INSERT INTO `t_leave` VALUES ('44', '1', '2017-07-03 00:00:00', '2017-07-13 00:00:00', '2017-07-03 18:09:35', '2', '1111', '3', 'billow', 'formkey', null);
INSERT INTO `t_leave` VALUES ('45', '1', '2017-07-03 00:00:00', '2017-07-06 00:00:00', '2017-07-03 18:25:56', '1', '1111', '3', 'billow', 'formkey', null);
INSERT INTO `t_leave` VALUES ('46', '1', '2017-07-03 00:00:00', '2017-07-21 00:00:00', '2017-07-03 18:29:55', '1', '11111', '3', 'billow', 'formkey', null);
INSERT INTO `t_leave` VALUES ('47', '1', '2017-07-19 00:00:00', '2017-06-23 00:00:00', '2017-07-03 18:33:14', '2', '111', '3', 'billow', 'formkey', null);
INSERT INTO `t_leave` VALUES ('48', '1', '2017-07-05 00:00:00', '2017-07-13 00:00:00', '2017-07-04 08:47:07', '4', '111111', '1', 'billow', 'formkey', null);
INSERT INTO `t_leave` VALUES ('49', '1', '2017-07-04 00:00:00', '2017-07-27 00:00:00', '2017-07-04 09:22:11', '3', '55555', '1', 'billow', 'formkey', null);
INSERT INTO `t_leave` VALUES ('51', '1', '2017-07-04 00:00:00', '2017-07-28 00:00:00', '2017-07-04 10:01:37', '3', '111111', '1', 'billow', 'ordinary', 'ordinary');
INSERT INTO `t_leave` VALUES ('53', '1', '2017-07-04 00:00:00', '2017-07-13 00:00:00', '2017-07-04 10:08:09', '2', '22222', '1', 'billow', 'ordinary', '80001');

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
) ENGINE=InnoDB AUTO_INCREMENT=59 DEFAULT CHARSET=utf8;

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
INSERT INTO `t_menu` VALUES ('38', '流程部署', 'fa-anchor', '0', '/sysActDeploy/queryDeployList', '37', '1', '1.000');
INSERT INTO `t_menu` VALUES ('39', '请假申请', 'fa-cogs', '0', '/applyLeave/findLeaveList', '37', '1', '2.000');
INSERT INTO `t_menu` VALUES ('40', '请假审批', 'fa-cogs', '0', '/approvalLeave/findApprovalLeave', '37', '1', '3.000');
INSERT INTO `t_menu` VALUES ('41', '请假申请-外置', 'fa-cogs', '0', '/formkey/applyLeave/findLeaveList', '37', '1', '4.000');
INSERT INTO `t_menu` VALUES ('42', '请假审批-外置', 'fa-cogs', '0', '/formkey/approvalLeave/findApprovalLeave', '37', '1', '5.000');
INSERT INTO `t_menu` VALUES ('43', '自动任务', 'fa-tasks', '0', '', '0', '1', '8.000');
INSERT INTO `t_menu` VALUES ('44', '自动任务', 'fa-tasks', '0', '/sysAutoTask/findAutoTask', '43', '1', '1.000');
INSERT INTO `t_menu` VALUES ('45', '流程模板', 'fa-anchor', '0', '/sysAct/findActModel', '37', '1', '1.500');
INSERT INTO `t_menu` VALUES ('46', 'ActiveMQ', 'fa-anchor', '0', '', '0', '1', '2.000');
INSERT INTO `t_menu` VALUES ('47', 'Queue', '', '0', '/activeMQ/index/queue', '46', '1', '1.000');
INSERT INTO `t_menu` VALUES ('48', 'Topic', '', '0', '/activeMQ/index/topic', '46', '1', '2.000');
INSERT INTO `t_menu` VALUES ('49', 'Dubbo', 'fa-anchor', '0', '', '0', '1', '4.000');
INSERT INTO `t_menu` VALUES ('50', 'dubbo调用', 'fa-anchor', '0', '/dubboController/index', '49', '1', '1.000');
INSERT INTO `t_menu` VALUES ('51', 'RabbitMQ', 'fa-anchor', '0', '', '0', '1', '4.000');
INSERT INTO `t_menu` VALUES ('52', 'Direct', '', '0', '/rabbitMQ/index/direct', '51', '1', '1.000');
INSERT INTO `t_menu` VALUES ('53', 'Topic', '', '0', '/rabbitMQ/index/topic', '51', '1', '2.000');
INSERT INTO `t_menu` VALUES ('54', 'Fanout', '', '0', '/rabbitMQ/index/fanout', '51', '1', '3.000');
INSERT INTO `t_menu` VALUES ('57', '流程定义', 'fa-anchor', '0', '/sysActProcDef/queryProcDefList', '37', '1', '1.100');
INSERT INTO `t_menu` VALUES ('58', '文件上传', '', '0', '/sysUploadController/uploadIndex', '19', '1', '5.000');

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
-- Table structure for t_reimbursement
-- ----------------------------
DROP TABLE IF EXISTS `t_reimbursement`;
CREATE TABLE `t_reimbursement` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) DEFAULT NULL COMMENT '申请人Id',
  `user_name` varchar(255) DEFAULT NULL COMMENT '申请人名称',
  `amount` decimal(8,2) DEFAULT NULL COMMENT '报销金额',
  `status` varchar(255) DEFAULT NULL COMMENT '状态',
  `apply_time` datetime DEFAULT NULL COMMENT '申请时间',
  `process_instance_id` varchar(255) DEFAULT NULL COMMENT '流程实例Id',
  `creat_date` datetime DEFAULT NULL COMMENT '创建时间',
  `update_date` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_reimbursement
-- ----------------------------

-- ----------------------------
-- Table structure for t_role
-- ----------------------------
DROP TABLE IF EXISTS `t_role`;
CREATE TABLE `t_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `rolename` varchar(32) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `id` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_role
-- ----------------------------
INSERT INTO `t_role` VALUES ('1', 'admin');
INSERT INTO `t_role` VALUES ('2', 'manager');
INSERT INTO `t_role` VALUES ('3', 'normal');
INSERT INTO `t_role` VALUES ('4', 'sa');
INSERT INTO `t_role` VALUES ('6', 'general');
INSERT INTO `t_role` VALUES ('7', 'hr');
INSERT INTO `t_role` VALUES ('8', 'user');
INSERT INTO `t_role` VALUES ('9', 'deptLeader');

-- ----------------------------
-- Table structure for t_schedule_job
-- ----------------------------
DROP TABLE IF EXISTS `t_schedule_job`;
CREATE TABLE `t_schedule_job` (
  `job_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `job_name` varchar(20) DEFAULT NULL COMMENT '任务名称',
  `job_group` varchar(20) DEFAULT NULL COMMENT '任务分组',
  `job_status` varchar(1) DEFAULT NULL COMMENT '任务状态 是否启动任务,0禁用 1启用 2删除',
  `cron_expression` varchar(30) DEFAULT NULL COMMENT 'cron表达式',
  `description` varchar(100) DEFAULT NULL COMMENT '描述',
  `bean_class` varchar(200) DEFAULT NULL COMMENT '任务执行时调用哪个类的方法 包名+类名',
  `is_concurrent` varchar(1) DEFAULT NULL COMMENT '任务是否有状态,0-无，1-有',
  `spring_id` varchar(200) DEFAULT NULL COMMENT 'spring bean',
  `method_name` varchar(20) DEFAULT NULL COMMENT '任务调用的方法名',
  PRIMARY KEY (`job_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_schedule_job
-- ----------------------------
INSERT INTO `t_schedule_job` VALUES ('1', '2017-05-12 19:29:15', '2017-06-09 09:11:42', 'test', 'TestAutoTask', '0', '0/5 * * * * ?', '测试用', 'org.billow.service.autoTask.TestAutoTask', '0', null, 'test');

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
) ENGINE=InnoDB AUTO_INCREMENT=28 DEFAULT CHARSET=utf8;

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
INSERT INTO `t_system_log` VALUES ('23', '用户管理', '查询用户列表', '查询', '非异步', '[参数1,类型:User,值:{\"pageNo\":1,\"pageSize\":10,\"recordCount\":21}]', 'com.billow.business.controller.UserController.findUserList', '2017-01-22 14:37:11', null);
INSERT INTO `t_system_log` VALUES ('24', '用户管理', '查询用户列表', '查询', '非异步', '[参数1,类型:User,值:{\"pageNo\":1,\"pageSize\":10,\"recordCount\":21}]', 'com.billow.business.controller.UserController.findUserList', '2017-03-07 09:51:30', null);
INSERT INTO `t_system_log` VALUES ('25', '用户管理', '查询用户列表', '查询', '非异步', '[参数1,类型:User,值:{\"pageNo\":1,\"pageSize\":10,\"recordCount\":21}]', 'com.billow.business.controller.UserController.findUserList', '2017-03-23 22:18:36', null);
INSERT INTO `t_system_log` VALUES ('26', '用户管理', '查询用户列表', '查询', '非异步', '[参数1,类型:User,值:{\"pageNo\":1,\"pageSize\":10,\"recordCount\":21}]', 'org.billow.controller.UserController.findUserList', '2017-04-10 10:38:29', null);
INSERT INTO `t_system_log` VALUES ('27', '测试', '测试查询列表', '查询', '非异步', '[参数1,类型:UserDto,值:{\"pageNo\":1,\"pageSize\":10,\"recordCount\":0}]', 'org.billow.controller.TestController.testList', '2017-04-28 10:20:31', null);

-- ----------------------------
-- Table structure for t_upload_log
-- ----------------------------
DROP TABLE IF EXISTS `t_upload_log`;
CREATE TABLE `t_upload_log` (
  `id` varchar(48) NOT NULL COMMENT '主键',
  `file_name` varchar(100) NOT NULL COMMENT '文件名',
  `file_size` bigint(20) DEFAULT NULL COMMENT '文件大小',
  `file_type` varchar(100) DEFAULT NULL COMMENT '文件类型',
  `create_code` varchar(100) NOT NULL COMMENT '创建人',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `new_file_name` varchar(100) NOT NULL COMMENT '新文件名',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_upload_log
-- ----------------------------
INSERT INTO `t_upload_log` VALUES ('100001', 'gateway.zip', '13479', 'application/x-zip-compressed', 'billow', '2017-09-20 10:30:50', '100001.zip');
INSERT INTO `t_upload_log` VALUES ('102501', 'gateway.zip', '13479', 'application/x-zip-compressed', 'billow', '2017-09-21 17:46:21', '102501.zip');
INSERT INTO `t_upload_log` VALUES ('102505', 'gateway.zip', '13479', 'application/x-zip-compressed', 'billow', '2017-09-21 18:02:06', '102505.zip');
INSERT INTO `t_upload_log` VALUES ('8a8ba0fa5e990077015e990077a20000', '12306Bypass_1.12.28.zip', '5369350', 'application/x-zip-compressed', 'billow', '2017-09-19 15:18:18', '8a8ba0fa5e990077015e990077a20000');
INSERT INTO `t_upload_log` VALUES ('8a8ba0fa5e992c25015e992c25fa0000', 'gateway.zip', '13479', 'application/x-zip-compressed', 'billow', '2017-09-19 16:06:01', '8a8ba0fa5e992c25015e992c25fa0000');
INSERT INTO `t_upload_log` VALUES ('8a8ba0fa5e992c25015e992ed7100001', 'gateway.zip', '13479', 'application/x-zip-compressed', 'billow', '2017-09-19 16:08:57', '8a8ba0fa5e992c25015e992ed7100001');
INSERT INTO `t_upload_log` VALUES ('8a8ba0fa5e99b14e015e99b14eab0000', 'gateway.zip', '13479', 'application/x-zip-compressed', 'billow', '2017-09-19 18:31:28', '8a8ba0fa5e99b14e015e99b14eab0000');
INSERT INTO `t_upload_log` VALUES ('97501', 'gateway.zip', '13479', 'application/x-zip-compressed', 'billow', '2017-09-20 10:21:10', '97501');

-- ----------------------------
-- Table structure for t_user
-- ----------------------------
DROP TABLE IF EXISTS `t_user`;
CREATE TABLE `t_user` (
  `user_id` int(11) NOT NULL AUTO_INCREMENT,
  `user_name` varchar(10) DEFAULT NULL,
  `age` int(11) DEFAULT NULL,
  `password` varchar(10) DEFAULT NULL,
  `phone_number` varchar(20) DEFAULT NULL,
  `open_id` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_user
-- ----------------------------
INSERT INTO `t_user` VALUES ('1', 'billow', '19', '000000', '15555555552', 'oJSiuxPjmJdJZLkGe1y31c0ObAtg');
INSERT INTO `t_user` VALUES ('2', 'leaderuser', '20', '000000', '15555555553', null);
INSERT INTO `t_user` VALUES ('3', 'hruser', '21', '000000', '15555555554', null);
INSERT INTO `t_user` VALUES ('4', 'admin', '22', '000000', '15555555555', null);
INSERT INTO `t_user` VALUES ('5', 'ggg', '22', '000000', '15555555510', null);
INSERT INTO `t_user` VALUES ('6', '张三', '24', '000000', '15555555511', null);
INSERT INTO `t_user` VALUES ('8', 'gggr', '34', '000000', '15555555559', null);
INSERT INTO `t_user` VALUES ('9', 'hhh', '23', '000000', '15555555558', null);
INSERT INTO `t_user` VALUES ('10', 'XXXX', '34', '000000', '15555555557', null);
INSERT INTO `t_user` VALUES ('11', '张三', '23', '000000', '15555555556', null);
INSERT INTO `t_user` VALUES ('16', '张三666', '25', '000000', '15507529497', null);
INSERT INTO `t_user` VALUES ('17', '22222', '34', '000000', '15555555513', null);
INSERT INTO `t_user` VALUES ('18', 'eee', '23', '000000', '15555555512', null);

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
INSERT INTO `t_user_role` VALUES ('1', '8');
INSERT INTO `t_user_role` VALUES ('2', '9');
INSERT INTO `t_user_role` VALUES ('3', '7');
INSERT INTO `t_user_role` VALUES ('4', '1');
INSERT INTO `t_user_role` VALUES ('5', '8');
INSERT INTO `t_user_role` VALUES ('8', '8');
INSERT INTO `t_user_role` VALUES ('9', '8');
INSERT INTO `t_user_role` VALUES ('11', '8');
INSERT INTO `t_user_role` VALUES ('12', '1');
INSERT INTO `t_user_role` VALUES ('13', '7');
INSERT INTO `t_user_role` VALUES ('14', '9');
INSERT INTO `t_user_role` VALUES ('15', '8');

-- ----------------------------
-- View structure for act_id_group
-- ----------------------------
DROP VIEW IF EXISTS `act_id_group`;
CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost`  VIEW `act_id_group` AS SELECT 
  CONCAT(ROLENAME) AS ID_,
  0 AS REV_,
  ROLENAME AS NAME_,
  'assignment' AS TYPE_ 
FROM
  T_ROLE ;

-- ----------------------------
-- View structure for act_id_membership
-- ----------------------------
DROP VIEW IF EXISTS `act_id_membership`;
CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost`  VIEW `act_id_membership` AS SELECT 
  (select user_name from t_user r where r.user_id = ur.user_id) AS USER_ID_,
  (select ROLENAME from t_role r where r.id = ur.role_id) AS GROUP_ID_
FROM
  t_user_role ur ;

-- ----------------------------
-- View structure for act_id_user
-- ----------------------------
DROP VIEW IF EXISTS `act_id_user`;
CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost`  VIEW `act_id_user` AS select 
  concat(R.USER_ID) AS ID_,
  0 AS REV_,
  CONCAT(R.USER_NAME) AS FIRST_,
  '' AS LAST_,
  '' AS EMAIL_,
  R.PASSWORD AS PWD_,
  '' AS PICTURE_ID_ 
FROM
  T_USER R ;

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
