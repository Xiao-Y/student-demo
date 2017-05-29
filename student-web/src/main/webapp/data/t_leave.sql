/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50045
Source Host           : localhost:3306
Source Database       : mytestdb

Target Server Type    : MYSQL
Target Server Version : 50045
File Encoding         : 65001

Date: 2017-05-29 14:21:16
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for t_leave
-- ----------------------------
DROP TABLE IF EXISTS `t_leave`;
CREATE TABLE `t_leave` (
  `id` int(11) NOT NULL auto_increment,
  `user_id` int(11) default NULL COMMENT '用户id',
  `start_time` datetime default NULL COMMENT '请假起始时间',
  `end_time` datetime default NULL COMMENT '请假结束时间',
  `apply_time` datetime default NULL COMMENT '请假申请时间',
  `leave_type` varchar(3) default NULL COMMENT '请假类型',
  `reason` varchar(400) default NULL COMMENT '原因',
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_leave
-- ----------------------------
INSERT INTO `t_leave` VALUES ('1', '1', '2017-05-28 00:00:00', '2017-06-04 00:00:00', '2017-05-29 10:53:27', '2', '回家');
INSERT INTO `t_leave` VALUES ('2', '1', '2017-05-28 00:00:00', '2017-06-04 00:00:00', '2017-05-29 10:53:27', '1', '相亲');
