/*
SQLyog Ultimate v12.5.1 (64 bit)
MySQL - 5.5.23 : Database - geek_db
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`geek_db` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `geek_db`;

/*Table structure for table `authorities` */

DROP TABLE IF EXISTS `authorities`;

CREATE TABLE `authorities` (
  `rolename` varchar(50) NOT NULL,
  `authority` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `authorities` */

insert  into `authorities`(`rolename`,`authority`) values 
('admin','ROLE_ADMIN'),
('user','ROLE_USER');

/*Table structure for table `course` */

DROP TABLE IF EXISTS `course`;

CREATE TABLE `course` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `description` text NOT NULL,
  `length` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=326 DEFAULT CHARSET=utf8;

/*Data for the table `course` */

insert  into `course`(`id`,`description`,`length`) values 
(305,'COURSE_0',4),
(306,'COURSE_1',4),
(307,'COURSE_2',5),
(308,'COURSE_3',5),
(309,'COURSE_4',3),
(310,'COURSE_5',5),
(311,'COURSE_6',3),
(312,'COURSE_7',5),
(313,'COURSE_8',4),
(314,'COURSE_9',4),
(315,'COURSE_10',4),
(316,'COURSE_11',3),
(317,'COURSE_12',4),
(318,'COURSE_13',4),
(319,'COURSE_14',5),
(320,'COURSE_15',4),
(321,'COURSE_16',3),
(322,'COURSE_17',3),
(323,'COURSE_18',4),
(324,'COURSE_19',4),
(325,'COURSE_20',3);

/*Table structure for table `education` */

DROP TABLE IF EXISTS `education`;

CREATE TABLE `education` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `student_id` int(11) NOT NULL,
  `course_id` int(11) NOT NULL,
  `score` int(11) NOT NULL DEFAULT '0',
  `completed` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`),
  KEY `student_id` (`student_id`),
  KEY `course_id` (`course_id`),
  CONSTRAINT `education_ibfk_1` FOREIGN KEY (`student_id`) REFERENCES `student` (`id`),
  CONSTRAINT `education_ibfk_2` FOREIGN KEY (`course_id`) REFERENCES `course` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1576 DEFAULT CHARSET=utf8;

/*Data for the table `education` */

insert  into `education`(`id`,`student_id`,`course_id`,`score`,`completed`) values 
(1477,311,312,0,0),
(1478,311,321,0,0),
(1479,311,311,0,0),
(1480,311,315,0,0),
(1481,311,324,0,0),
(1482,311,307,0,0),
(1483,311,319,0,0),
(1484,312,305,0,0),
(1485,312,324,0,0),
(1486,312,320,0,0),
(1487,312,323,0,0),
(1488,312,307,0,0),
(1489,312,315,0,0),
(1490,312,314,0,0),
(1491,313,318,0,0),
(1492,313,321,0,0),
(1493,313,308,0,0),
(1494,313,319,0,0),
(1495,314,319,0,0),
(1496,314,318,0,0),
(1497,314,323,0,0),
(1498,314,317,0,0),
(1499,315,309,0,0),
(1500,315,320,0,0),
(1501,315,321,0,0),
(1502,315,306,0,0),
(1503,316,310,0,0),
(1504,316,314,0,0),
(1505,316,308,0,0),
(1506,316,313,0,0),
(1507,316,315,0,0),
(1508,316,318,0,0),
(1509,316,324,0,0),
(1510,316,317,0,0),
(1511,316,306,0,0),
(1512,317,321,0,0),
(1513,317,320,0,0),
(1514,317,316,0,0),
(1515,317,318,0,0),
(1516,317,315,0,0),
(1517,318,322,0,0),
(1518,318,305,0,0),
(1519,318,319,0,0),
(1520,318,317,0,0),
(1521,319,308,0,0),
(1522,319,312,0,0),
(1523,320,306,0,0),
(1524,320,309,0,0),
(1525,320,323,0,0),
(1526,320,310,0,0),
(1527,320,324,0,0),
(1528,320,320,0,0),
(1529,321,308,0,0),
(1530,321,325,0,0),
(1531,321,324,0,0),
(1532,321,322,0,0),
(1533,321,311,0,0),
(1534,321,318,0,0),
(1535,321,313,0,0),
(1536,322,322,0,0),
(1537,322,312,0,0),
(1538,322,308,0,0),
(1539,322,317,0,0),
(1540,322,309,0,0),
(1541,322,307,0,0),
(1542,323,320,0,0),
(1543,323,317,0,0),
(1544,323,308,0,0),
(1545,323,318,0,0),
(1546,323,316,0,0),
(1547,323,314,0,0),
(1548,324,314,0,0),
(1549,324,322,0,0),
(1550,324,308,0,0),
(1551,325,306,0,0),
(1552,325,323,0,0),
(1553,325,319,0,0),
(1554,326,321,0,0),
(1555,326,314,0,0),
(1556,326,305,0,0),
(1557,326,317,0,0),
(1558,327,309,0,0),
(1559,327,322,0,0),
(1560,327,315,0,0),
(1561,327,325,0,0),
(1562,327,305,0,0),
(1563,327,310,0,0),
(1564,327,316,0,0),
(1565,327,306,0,0),
(1566,328,324,0,0),
(1567,328,318,0,0),
(1568,328,305,0,0),
(1569,328,323,0,0),
(1570,328,307,0,0),
(1571,328,313,0,0),
(1572,328,317,0,0),
(1573,328,320,0,0),
(1574,328,306,0,0),
(1575,328,311,0,0);

/*Table structure for table `permissions` */

DROP TABLE IF EXISTS `permissions`;

CREATE TABLE `permissions` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) NOT NULL,
  `description` varchar(300) NOT NULL,
  `module` varchar(100) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8;

/*Data for the table `permissions` */

insert  into `permissions`(`id`,`name`,`description`,`module`) values 
(1,'COURSE-LIST','Просмотр списка курсов','CourseService'),
(2,'USERS_LIST_VIEW','Просмотр списка пользователей','USERS'),
(3,'MAIN_DATA_GENERATE','Генерация случайных данных','MAIN'),
(4,'COURSE_VIEW_LIST','Просмотр списка курсов','COURSE'),
(5,'COURSE_VIEW_DETAIL','Просмотр детальной информации по курсу','COURSE'),
(6,'STUDENT_VIEW_LIST','Просмотр списка студентов','STUDENT'),
(7,'STUDENT_VIEW_DETAIL','Просмотр информации о студенте','STUDENT'),
(8,'STUDENT_EDIT_COURSE_LIST','Редактирование курсов студентов','STUDENT'),
(9,'STUDENT_EDIT_COURSE_LIST_ADD','Добавление курсов студенту','STUDENT'),
(10,'STUDENT_EDIT_COURSE_LIST_REMOVE','Удаление курсов студенту','STUDENT'),
(11,'USERS_VIEW_DETAIL','Просмотр списка пользователей','USERS');

/*Table structure for table `role_permission` */

DROP TABLE IF EXISTS `role_permission`;

CREATE TABLE `role_permission` (
  `role_id` int(11) NOT NULL,
  `permission_id` int(11) NOT NULL,
  PRIMARY KEY (`role_id`,`permission_id`),
  KEY `permission_id` (`permission_id`),
  CONSTRAINT `role_permission_ibfk_1` FOREIGN KEY (`role_id`) REFERENCES `roles` (`id`),
  CONSTRAINT `role_permission_ibfk_2` FOREIGN KEY (`permission_id`) REFERENCES `permissions` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `role_permission` */

insert  into `role_permission`(`role_id`,`permission_id`) values 
(1,1),
(1,2),
(1,3),
(1,4),
(1,5),
(2,5),
(1,6),
(2,6),
(3,6),
(1,7),
(2,7),
(1,8),
(1,9),
(1,10),
(1,11);

/*Table structure for table `roles` */

DROP TABLE IF EXISTS `roles`;

CREATE TABLE `roles` (
  `name` varchar(50) NOT NULL,
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `builtin` tinyint(1) NOT NULL DEFAULT '0',
  `description` varchar(300) NOT NULL,
  PRIMARY KEY (`name`,`id`),
  KEY `id` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

/*Data for the table `roles` */

insert  into `roles`(`name`,`id`,`builtin`,`description`) values 
('ADMIN',1,1,''),
('MANAGER',2,0,''),
('USER',3,1,'');

/*Table structure for table `student` */

DROP TABLE IF EXISTS `student`;

CREATE TABLE `student` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` text,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=329 DEFAULT CHARSET=utf8;

/*Data for the table `student` */

insert  into `student`(`id`,`name`) values 
(311,'STUDENT_0'),
(312,'STUDENT_1'),
(313,'STUDENT_2'),
(314,'STUDENT_3'),
(315,'STUDENT_4'),
(316,'STUDENT_5'),
(317,'STUDENT_6'),
(318,'STUDENT_7'),
(319,'STUDENT_8'),
(320,'STUDENT_9'),
(321,'STUDENT_10'),
(322,'STUDENT_11'),
(323,'STUDENT_12'),
(324,'STUDENT_13'),
(325,'STUDENT_14'),
(326,'STUDENT_15'),
(327,'STUDENT_16'),
(328,'STUDENT_17');

/*Table structure for table `user_role` */

DROP TABLE IF EXISTS `user_role`;

CREATE TABLE `user_role` (
  `user_id` int(11) NOT NULL,
  `role_id` int(11) NOT NULL,
  PRIMARY KEY (`user_id`,`role_id`),
  KEY `role_id` (`role_id`),
  CONSTRAINT `user_role_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`),
  CONSTRAINT `user_role_ibfk_2` FOREIGN KEY (`role_id`) REFERENCES `roles` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `user_role` */

insert  into `user_role`(`user_id`,`role_id`) values 
(1,1),
(6,2),
(2,3),
(3,3),
(4,3),
(5,3),
(7,3);

/*Table structure for table `users` */

DROP TABLE IF EXISTS `users`;

CREATE TABLE `users` (
  `name` varchar(50) NOT NULL,
  `password` varchar(100) NOT NULL,
  `enabled` tinyint(1) NOT NULL,
  `id` int(11) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`id`),
  KEY `id` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;

/*Data for the table `users` */

insert  into `users`(`name`,`password`,`enabled`,`id`) values 
('admin','{noop}admin',1,1),
('user','{noop}user',1,2),
('ivanov','{noop}petr',1,3),
('admin34','{noop}admin34',1,4),
('test','{noop}test',1,5),
('manager','{noop}manager',1,6),
('test','{noop}test',1,7);

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
