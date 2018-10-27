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
) ENGINE=InnoDB AUTO_INCREMENT=305 DEFAULT CHARSET=utf8;

/*Data for the table `course` */

insert  into `course`(`id`,`description`,`length`) values 
(287,'COURSE_0',3),
(288,'COURSE_1',4),
(289,'COURSE_2',5),
(290,'COURSE_3',4),
(291,'COURSE_4',4),
(292,'COURSE_5',3),
(293,'COURSE_6',5),
(294,'COURSE_7',4),
(295,'COURSE_8',3),
(296,'COURSE_9',5),
(297,'COURSE_10',4),
(298,'COURSE_11',3),
(299,'COURSE_12',3),
(300,'COURSE_13',4),
(301,'COURSE_14',5),
(302,'COURSE_15',3),
(303,'COURSE_16',5),
(304,'COURSE_17',5);

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
) ENGINE=InnoDB AUTO_INCREMENT=1468 DEFAULT CHARSET=utf8;

/*Data for the table `education` */

insert  into `education`(`id`,`student_id`,`course_id`,`score`,`completed`) values 
(1334,285,299,0,0),
(1335,285,298,0,0),
(1336,285,292,0,0),
(1337,285,295,0,0),
(1338,285,304,0,0),
(1339,286,287,0,0),
(1340,286,295,0,0),
(1341,286,296,0,0),
(1342,286,303,0,0),
(1343,286,288,0,0),
(1344,287,292,0,0),
(1345,287,304,0,0),
(1346,287,303,0,0),
(1347,288,296,0,0),
(1348,288,292,0,0),
(1349,288,287,0,0),
(1350,288,297,0,0),
(1351,288,300,0,0),
(1352,289,291,0,0),
(1353,289,301,0,0),
(1354,289,288,0,0),
(1355,289,295,0,0),
(1356,290,303,0,0),
(1357,290,289,0,0),
(1358,290,300,0,0),
(1359,290,287,0,0),
(1360,291,291,0,0),
(1361,291,290,0,0),
(1362,291,293,0,0),
(1363,291,295,0,0),
(1364,292,287,0,0),
(1365,292,304,0,0),
(1366,292,295,0,0),
(1367,292,300,0,0),
(1368,292,293,0,0),
(1369,292,294,0,0),
(1370,292,297,0,0),
(1371,293,300,0,0),
(1372,293,303,0,0),
(1373,293,292,0,0),
(1374,293,301,0,0),
(1375,293,302,0,0),
(1376,294,304,0,0),
(1377,294,288,0,0),
(1378,294,303,0,0),
(1379,295,290,0,0),
(1380,295,295,0,0),
(1381,295,291,0,0),
(1382,295,289,0,0),
(1383,295,297,0,0),
(1384,295,300,0,0),
(1385,296,289,0,0),
(1386,296,304,0,0),
(1387,297,298,0,0),
(1388,297,294,0,0),
(1389,297,304,0,0),
(1390,298,292,0,0),
(1391,298,304,0,0),
(1392,298,290,0,0),
(1393,298,289,0,0),
(1394,298,294,0,0),
(1395,298,301,0,0),
(1405,300,288,0,0),
(1406,300,299,0,0),
(1407,301,298,0,0),
(1408,301,287,0,0),
(1409,301,301,0,0),
(1410,301,289,0,0),
(1411,301,291,0,0),
(1412,301,292,0,0),
(1413,301,300,0,0),
(1414,302,295,0,0),
(1415,302,303,0,0),
(1416,302,290,0,0),
(1417,302,301,0,0),
(1418,303,290,0,0),
(1419,303,293,0,0),
(1420,304,294,0,0),
(1421,304,300,0,0),
(1422,304,299,0,0),
(1423,304,304,0,0),
(1424,304,297,0,0),
(1425,305,302,0,0),
(1426,305,295,0,0),
(1427,305,293,0,0),
(1428,305,291,0,0),
(1429,305,298,0,0),
(1430,305,301,0,0),
(1431,305,300,0,0),
(1432,306,290,0,0),
(1433,306,291,0,0),
(1434,306,294,0,0),
(1435,306,301,0,0),
(1436,306,293,0,0),
(1437,307,287,0,0),
(1438,307,291,0,0),
(1439,307,294,0,0),
(1440,308,293,0,0),
(1441,308,294,0,0),
(1442,308,296,0,0),
(1443,308,292,0,0),
(1444,308,287,0,0),
(1445,309,301,0,0),
(1446,309,295,0,0),
(1447,309,292,0,0),
(1448,309,304,0,0),
(1449,309,293,0,0),
(1450,310,294,0,0),
(1459,299,299,0,0),
(1460,299,290,0,0),
(1461,299,292,0,0),
(1462,299,302,0,0),
(1463,299,297,0,0),
(1464,299,288,0,0),
(1465,299,287,0,0),
(1466,299,294,0,0),
(1467,299,291,0,0);

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
) ENGINE=InnoDB AUTO_INCREMENT=311 DEFAULT CHARSET=utf8;

/*Data for the table `student` */

insert  into `student`(`id`,`name`) values 
(285,'STUDENT_0'),
(286,'STUDENT_1'),
(287,'STUDENT_2'),
(288,'STUDENT_3'),
(289,'STUDENT_4'),
(290,'STUDENT_5'),
(291,'STUDENT_6'),
(292,'STUDENT_7'),
(293,'STUDENT_8'),
(294,'STUDENT_9'),
(295,'STUDENT_10'),
(296,'STUDENT_11'),
(297,'STUDENT_12'),
(298,'STUDENT_13'),
(299,'STUDENT_14'),
(300,'STUDENT_15'),
(301,'STUDENT_16'),
(302,'STUDENT_17'),
(303,'STUDENT_18'),
(304,'STUDENT_19'),
(305,'STUDENT_20'),
(306,'STUDENT_21'),
(307,'STUDENT_22'),
(308,'STUDENT_23'),
(309,'STUDENT_24'),
(310,'STUDENT_25');

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
(5,3);

/*Table structure for table `users` */

DROP TABLE IF EXISTS `users`;

CREATE TABLE `users` (
  `name` varchar(50) NOT NULL,
  `password` varchar(100) NOT NULL,
  `enabled` tinyint(1) NOT NULL,
  `id` int(11) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`id`),
  KEY `id` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

/*Data for the table `users` */

insert  into `users`(`name`,`password`,`enabled`,`id`) values 
('admin','{noop}admin',1,1),
('user','{noop}user',1,2),
('ivanov','{noop}petr',1,3),
('admin34','{noop}admin34',1,4),
('test','{noop}test',1,5),
('manager','{noop}manager',1,6);

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
