/*
SQLyog Ultimate v12.08 (64 bit)
MySQL - 5.6.47-log : Database - shiliao
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`shiliao` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `shiliao`;

/*Table structure for table `n_category` */

DROP TABLE IF EXISTS `n_category`;

CREATE TABLE `n_category` (
  `Cid` bigint(20) NOT NULL AUTO_INCREMENT,
  `category` varchar(20) NOT NULL COMMENT '每个区',
  PRIMARY KEY (`Cid`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

/*Data for the table `n_category` */

insert  into `n_category`(`Cid`,`category`) values (1,'美容'),(2,'减肥'),(3,'消化不良'),(4,'乌发');

/*Table structure for table `n_users` */

DROP TABLE IF EXISTS `n_users`;

CREATE TABLE `n_users` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `Nids` varchar(1024) DEFAULT NULL COMMENT '用户收藏的帖子ID,多个以逗号分隔',
  `Uid` bigint(20) DEFAULT NULL COMMENT '用户的ID',
  `Nlike` varchar(1024) DEFAULT NULL COMMENT '用户点赞的帖子ID，多个以逗号分隔',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

/*Data for the table `n_users` */

insert  into `n_users`(`id`,`Nids`,`Uid`,`Nlike`) values (1,'5,4',3,'4,5');

/*Table structure for table `notes` */

DROP TABLE IF EXISTS `notes`;

CREATE TABLE `notes` (
  `Nid` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `Ndetails` varchar(3072) DEFAULT NULL COMMENT '帖子内容',
  `Nvalid` tinyint(1) DEFAULT NULL COMMENT '是否显示 0为不显示',
  `Nlike_times` bigint(20) DEFAULT NULL COMMENT '点赞数',
  `Nimages` varchar(3072) DEFAULT NULL COMMENT '图片',
  `Narea` int(10) NOT NULL DEFAULT '0' COMMENT '帖子所在的分区 0为浏览区 1为精品区',
  `Ntitle` varchar(3075) DEFAULT NULL COMMENT '标题',
  `Ndate` date DEFAULT NULL COMMENT '创建日期',
  `Nuid` bigint(20) DEFAULT NULL COMMENT '对应的用户ID',
  `Ncategory` varchar(40) DEFAULT NULL COMMENT '功效分类,多个以逗号分隔开',
  PRIMARY KEY (`Nid`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;

/*Data for the table `notes` */

insert  into `notes`(`Nid`,`Ndetails`,`Nvalid`,`Nlike_times`,`Nimages`,`Narea`,`Ntitle`,`Ndate`,`Nuid`,`Ncategory`) values (1,'大神',1,0,'',0,'das','2020-04-02',1,'1,2'),(2,'waaw',1,0,'',0,'ddddd','2020-04-08',1,'1,2'),(3,'ddd',1,0,'',0,'wa','2020-04-01',3,'2,3'),(4,'aaa',1,0,'',0,'da','2020-03-12',3,'3'),(5,'waaw',0,0,'',0,'44','2020-03-11',1,'1'),(6,'oo',1,0,'',0,'88','2020-03-10',1,'1'),(7,'fff',1,0,'',0,'77',NULL,NULL,'2'),(8,'2222',1,0,'',0,'33',NULL,NULL,'1'),(9,'aaa',0,0,'',1,'33',NULL,NULL,'1');

/*Table structure for table `notes_details` */

DROP TABLE IF EXISTS `notes_details`;

CREATE TABLE `notes_details` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `Nid` bigint(20) NOT NULL COMMENT '外键',
  `NDimages` varchar(1024) DEFAULT NULL COMMENT '图片，多个图片以‘,’分割',
  `NDdetails` varchar(1024) NOT NULL COMMENT '每层楼的具体内容',
  `NDorder` bigint(20) NOT NULL COMMENT '每层楼的顺序',
  `NDvalid` tinyint(1) DEFAULT '1' COMMENT '是否显示',
  `NDread` tinyint(1) DEFAULT '0' COMMENT '是否已读 0是未读 1是已读',
  `uid` bigint(20) DEFAULT NULL COMMENT '用户的id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;

/*Data for the table `notes_details` */

insert  into `notes_details`(`id`,`Nid`,`NDimages`,`NDdetails`,`NDorder`,`NDvalid`,`NDread`,`uid`) values (1,1,NULL,'hahah',1,0,0,1),(2,4,NULL,'yo',1,1,1,2),(3,2,NULL,'hhh',2,1,1,1),(4,2,NULL,'秀',3,1,1,3),(5,2,NULL,'jimmy',4,1,1,1),(6,5,NULL,'ddass',5,1,0,3),(7,4,NULL,'hihoho',1,1,1,4);

/*Table structure for table `recipe` */

DROP TABLE IF EXISTS `recipe`;

CREATE TABLE `recipe` (
  `id` bigint(11) NOT NULL,
  `Rdetails` varchar(1024) DEFAULT NULL COMMENT '内容',
  `Rimages` varchar(1024) DEFAULT NULL COMMENT '图片',
  `Rvalid` tinyint(1) DEFAULT NULL COMMENT '是否显示 1为显示',
  `Rtitles` varchar(1024) DEFAULT NULL COMMENT '标题',
  `Rdate` date DEFAULT NULL COMMENT '创建日期',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `recipe` */

/*Table structure for table `u_details` */

DROP TABLE IF EXISTS `u_details`;

CREATE TABLE `u_details` (
  `UDid` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `UDnames` varchar(100) DEFAULT '未命名' COMMENT '用户名',
  `UDbirthday` date DEFAULT NULL COMMENT '生日',
  `UDsign` varchar(1024) DEFAULT NULL COMMENT '个性签名',
  `UDimage` varchar(1024) DEFAULT NULL COMMENT '头像',
  `UDsex` varchar(2) DEFAULT NULL COMMENT '性别',
  `Uid` bigint(20) DEFAULT NULL COMMENT '外键',
  PRIMARY KEY (`UDid`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

/*Data for the table `u_details` */

insert  into `u_details`(`UDid`,`UDnames`,`UDbirthday`,`UDsign`,`UDimage`,`UDsex`,`Uid`) values (1,'未命名','2020-04-10','太秀了',NULL,'男',1),(2,'wow',NULL,NULL,NULL,NULL,2),(3,'jimmy',NULL,NULL,NULL,NULL,3),(4,'tiffany',NULL,NULL,NULL,NULL,4);

/*Table structure for table `users` */

DROP TABLE IF EXISTS `users`;

CREATE TABLE `users` (
  `Uid` bigint(20) NOT NULL AUTO_INCREMENT,
  `Uname` varchar(1024) NOT NULL,
  `Upwd` varchar(80) NOT NULL,
  `Umail` varchar(40) NOT NULL,
  `Udate` date NOT NULL,
  `Utype` tinyint(1) NOT NULL DEFAULT '0',
  `Ustate` tinyint(1) NOT NULL DEFAULT '0',
  PRIMARY KEY (`Uid`)
) ENGINE=InnoDB AUTO_INCREMENT=35 DEFAULT CHARSET=utf8;

/*Data for the table `users` */

insert  into `users`(`Uid`,`Uname`,`Upwd`,`Umail`,`Udate`,`Utype`,`Ustate`) values (1,'zhangsan','123','dsa','2020-04-02',0,0),(2,'lisi','234','sad','2020-04-02',0,0),(3,'zhangsan','1234','1','2020-04-13',0,0),(4,'zhangsan','12345','744900446@qq.com','2020-04-13',0,0),(5,'15625862010','123','384107082@qq.com','2020-04-13',0,0),(26,'zhangsann','1234','1','2020-04-14',0,0),(27,'15625862010','111111','744900446@qq.com','2020-04-14',0,0),(28,'zhangsann','1234','1','2020-04-14',0,0),(29,'15625862010','1','744900446@qq.com','2020-04-14',0,0),(30,'15625862010','1','744900446@qq.com','2020-04-14',0,0),(31,'15625862010','123','744900446@qq.com','2020-04-14',0,0),(32,'15625862010','111','744900446@qq.com','2020-04-14',0,0),(33,'15625862010','12345678','123456@qq.com','2020-04-14',0,0),(34,'sunzitong','12345678','744900446@qq.com','2020-04-14',0,0);

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
