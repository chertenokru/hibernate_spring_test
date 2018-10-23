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

/*Table structure for table `course` */

DROP TABLE IF EXISTS `course`;

CREATE TABLE `course` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `description` text NOT NULL,
  `length` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=208 DEFAULT CHARSET=utf8;

/*Data for the table `course` */

insert  into `course`(`id`,`description`,`length`) values 
(188,'COURSE_0',4),
(189,'COURSE_1',5),
(190,'COURSE_2',4),
(191,'COURSE_3',5),
(192,'COURSE_4',4),
(193,'COURSE_5',4),
(194,'COURSE_6',5),
(195,'COURSE_7',3),
(196,'COURSE_8',4),
(197,'COURSE_9',4),
(198,'COURSE_10',3),
(199,'COURSE_11',4),
(200,'COURSE_12',5),
(201,'COURSE_13',3),
(202,'COURSE_14',3),
(203,'COURSE_15',3),
(204,'COURSE_16',3),
(205,'COURSE_17',3),
(206,'COURSE_18',3),
(207,'COURSE_19',4);

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
) ENGINE=InnoDB AUTO_INCREMENT=920 DEFAULT CHARSET=utf8;

/*Data for the table `education` */

insert  into `education`(`id`,`student_id`,`course_id`,`score`,`completed`) values 
(805,186,197,0,0),
(806,186,199,0,0),
(807,186,205,0,0),
(808,186,194,0,0),
(809,186,195,0,0),
(810,186,188,0,0),
(811,187,200,0,0),
(812,187,190,0,0),
(813,187,207,0,0),
(814,187,189,0,0),
(815,187,196,0,0),
(816,187,201,0,0),
(817,187,188,0,0),
(818,188,206,0,0),
(819,188,199,0,0),
(820,188,204,0,0),
(821,188,202,0,0),
(822,188,200,0,0),
(823,188,198,0,0),
(824,188,195,0,0),
(825,189,205,0,0),
(826,189,204,0,0),
(827,189,206,0,0),
(828,189,191,0,0),
(829,189,199,0,0),
(830,189,202,0,0),
(831,189,196,0,0),
(832,190,202,0,0),
(833,190,206,0,0),
(834,190,201,0,0),
(835,190,190,0,0),
(836,190,198,0,0),
(837,190,194,0,0),
(838,190,191,0,0),
(839,191,199,0,0),
(840,191,201,0,0),
(841,191,200,0,0),
(842,191,196,0,0),
(843,192,207,0,0),
(844,192,200,0,0),
(845,192,201,0,0),
(846,192,206,0,0),
(847,192,188,0,0),
(848,192,202,0,0),
(849,192,191,0,0),
(850,192,205,0,0),
(851,193,191,0,0),
(852,193,198,0,0),
(853,193,205,0,0),
(854,193,188,0,0),
(855,193,200,0,0),
(856,193,202,0,0),
(857,194,191,0,0),
(858,194,188,0,0),
(859,194,201,0,0),
(860,194,202,0,0),
(861,194,196,0,0),
(862,194,205,0,0),
(863,194,197,0,0),
(864,194,190,0,0),
(865,195,207,0,0),
(872,197,203,0,0),
(873,197,200,0,0),
(874,197,190,0,0),
(875,197,207,0,0),
(876,197,188,0,0),
(877,197,194,0,0),
(878,197,204,0,0),
(879,198,188,0,0),
(880,198,191,0,0),
(881,199,189,0,0),
(882,199,190,0,0),
(883,199,196,0,0),
(884,199,194,0,0),
(885,199,188,0,0),
(886,199,207,0,0),
(887,200,188,0,0),
(888,200,189,0,0),
(889,200,204,0,0),
(906,196,191,0,0),
(907,196,206,0,0),
(908,196,189,0,0),
(909,196,193,0,0),
(910,196,200,0,0),
(911,196,195,0,0),
(912,201,191,0,0),
(913,201,193,0,0),
(914,201,207,0,0),
(915,201,189,0,0),
(916,201,192,0,0),
(917,201,202,0,0),
(918,201,196,0,0),
(919,201,188,0,0);

/*Table structure for table `rights` */

DROP TABLE IF EXISTS `rights`;

CREATE TABLE `rights` (
  `username` varchar(50) NOT NULL,
  `rolename` varchar(50) NOT NULL,
  UNIQUE KEY `rights_idx_1` (`username`,`rolename`),
  KEY `rights_ibfk_2` (`rolename`),
  CONSTRAINT `rights_ibfk_1` FOREIGN KEY (`username`) REFERENCES `users` (`username`),
  CONSTRAINT `rights_ibfk_2` FOREIGN KEY (`rolename`) REFERENCES `role` (`rolename`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `rights` */

insert  into `rights`(`username`,`rolename`) values 
('admin','ROLE_ADMIN'),
('man','ROLE_MANAGER'),
('admin','ROLE_USER'),
('user','ROLE_USER');

/*Table structure for table `role` */

DROP TABLE IF EXISTS `role`;

CREATE TABLE `role` (
  `rolename` varchar(50) NOT NULL,
  PRIMARY KEY (`rolename`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `role` */

insert  into `role`(`rolename`) values 
('ROLE_ADMIN'),
('ROLE_MANAGER'),
('ROLE_USER');

/*Table structure for table `student` */

DROP TABLE IF EXISTS `student`;

CREATE TABLE `student` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` text,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=202 DEFAULT CHARSET=utf8;

/*Data for the table `student` */

insert  into `student`(`id`,`name`) values 
(186,'STUDENT_0'),
(187,'STUDENT_1'),
(188,'STUDENT_2'),
(189,'STUDENT_3'),
(190,'STUDENT_4'),
(191,'STUDENT_5'),
(192,'STUDENT_6'),
(193,'STUDENT_7'),
(194,'STUDENT_8'),
(195,'STUDENT_9'),
(196,'STUDENT_10'),
(197,'STUDENT_11'),
(198,'STUDENT_12'),
(199,'STUDENT_13'),
(200,'STUDENT_14'),
(201,'STUDENT_15');

/*Table structure for table `users` */

DROP TABLE IF EXISTS `users`;

CREATE TABLE `users` (
  `username` varchar(50) NOT NULL,
  `password` varchar(100) NOT NULL,
  `enabled` tinyint(1) NOT NULL,
  PRIMARY KEY (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `users` */

insert  into `users`(`username`,`password`,`enabled`) values 
('admin','{noop}admin',1),
('man','{noop}man',1),
('user','{noop}1',1);

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
