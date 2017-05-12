/*
SQLyog Ultimate v12.09 (64 bit)
MySQL - 5.6.14 
*********************************************************************
*/
/*!40101 SET NAMES utf8 */;

create table `t_schedule_job` (
	`job_id` int (11),
	`create_time` datetime ,
	`update_time` datetime ,
	`job_name` varchar (60),
	`job_group` varchar (60),
	`job_status` varchar (3),
	`cron_expression` varchar (90),
	`description` varchar (300),
	`bean_class` varchar (600),
	`is_concurrent` varchar (3),
	`spring_id` varchar (600),
	`method_name` varchar (60)
); 
insert into `t_schedule_job` (`job_id`, `create_time`, `update_time`, `job_name`, `job_group`, `job_status`, `cron_expression`, `description`, `bean_class`, `is_concurrent`, `spring_id`, `method_name`) values('1','2017-05-12 19:29:15','2017-05-12 19:29:18','test','TestAutoTask','1','0/5 * * * * ?','测试用','org.billow.service.autoTask.TestAutoTask','0',NULL,'test');
