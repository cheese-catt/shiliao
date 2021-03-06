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
  `category` varchar(20) NOT NULL COMMENT '每个标签',
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
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

/*Data for the table `n_users` */

insert  into `n_users`(`id`,`Nids`,`Uid`,`Nlike`) values (1,'5,4',3,'4,6,7'),(2,'2,4,6,10',2,'2'),(3,'5,4',1,NULL),(4,NULL,4,NULL),(5,NULL,5,'3,2,4'),(6,NULL,35,NULL);

/*Table structure for table `notes` */

DROP TABLE IF EXISTS `notes`;

CREATE TABLE `notes` (
  `Nid` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `Ndetails` varchar(3072) DEFAULT NULL COMMENT '帖子内容',
  `Nvalid` tinyint(1) DEFAULT '1' COMMENT '是否显示 0为不显示',
  `Nlike_times` bigint(20) DEFAULT '0' COMMENT '点赞数',
  `Nimages` varchar(3072) DEFAULT NULL COMMENT '图片',
  `Narea` int(10) NOT NULL DEFAULT '0' COMMENT '帖子所在的分区 0为浏览区 1为精品区，2为食谱区',
  `Ntitle` varchar(3075) DEFAULT NULL COMMENT '标题',
  `Ndate` datetime DEFAULT NULL COMMENT '创建日期',
  `Nuid` bigint(20) DEFAULT NULL COMMENT '对应的用户ID',
  `Ncategory` bigint(20) DEFAULT NULL COMMENT '帖子对应的标签id',
  PRIMARY KEY (`Nid`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8;

/*Data for the table `notes` */

insert  into `notes`(`Nid`,`Ndetails`,`Nvalid`,`Nlike_times`,`Nimages`,`Narea`,`Ntitle`,`Ndate`,`Nuid`,`Ncategory`) values (1,'大神',1,0,'',0,'das','2020-04-02 00:00:00',1,1),(2,'waaw',1,1,'',0,'ddddd','2020-04-08 00:00:00',1,2),(3,'ddd',1,10,'',0,'wa','2020-04-01 00:00:00',3,3),(4,'五杀',1,7,'',0,'da','2020-03-12 00:00:00',3,1),(5,'哎呀',1,9,'',0,'44','2020-03-11 00:00:00',1,3),(6,'oo',1,0,'',0,'88','2020-03-10 00:00:00',1,2),(7,'fff',1,0,'',0,'77','2020-04-08 14:55:45',2,1),(8,'2222',1,0,'',1,'33','2020-03-11 14:55:47',2,4),(9,'吃鸡',1,0,'',1,'33','2020-03-04 14:55:50',4,2),(10,'睡个美容觉',1,0,NULL,2,'嗷嗷嗷','2020-03-28 11:12:21',5,1),(11,'快来看',1,0,NULL,2,'点点点','2020-02-06 11:14:57',5,2),(12,'怎么',1,0,NULL,0,'不来梅拿','2020-03-06 11:17:01',5,3);

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
  `NDdate` datetime DEFAULT NULL COMMENT '评论的日期',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;

/*Data for the table `notes_details` */

insert  into `notes_details`(`id`,`Nid`,`NDimages`,`NDdetails`,`NDorder`,`NDvalid`,`NDread`,`uid`,`NDdate`) values (1,1,NULL,'hahah',1,0,0,1,'2020-04-08 14:49:59'),(2,4,NULL,'yo',1,1,1,2,'2020-04-19 16:50:02'),(3,2,NULL,'hhh',2,1,1,1,'2020-03-09 14:50:15'),(4,2,NULL,'秀',3,1,0,3,'2020-03-17 14:50:12'),(5,2,NULL,'jimmy',4,1,0,1,'2020-03-04 14:50:04'),(6,5,NULL,'ddass',5,1,0,3,'2020-03-18 14:50:07'),(7,4,NULL,'hihoho',1,1,1,4,'2020-03-03 14:50:19');

/*Table structure for table `recipe` */

DROP TABLE IF EXISTS `recipe`;

CREATE TABLE `recipe` (
  `id` bigint(11) NOT NULL,
  `Rdetails` varchar(1024) DEFAULT NULL COMMENT '内容',
  `Rimages` varchar(1024) DEFAULT NULL COMMENT '图片',
  `Rvalid` tinyint(1) DEFAULT '0' COMMENT '是否显示 1为显示',
  `Rtitles` varchar(1024) DEFAULT NULL COMMENT '标题',
  `Rdate` date DEFAULT NULL COMMENT '创建日期',
  `uid` bigint(20) DEFAULT NULL COMMENT '对应的用户名',
  `ncategory` binary(20) DEFAULT NULL COMMENT '标签id',
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
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;

/*Data for the table `u_details` */

insert  into `u_details`(`UDid`,`UDnames`,`UDbirthday`,`UDsign`,`UDimage`,`UDsex`,`Uid`) values (1,'的撒大','2020-04-10','太秀了',NULL,'男',1),(2,'haha',NULL,NULL,NULL,NULL,2),(3,'jimmy',NULL,NULL,NULL,NULL,3),(4,'tiffany',NULL,NULL,NULL,NULL,4),(5,'发财阿',NULL,'肝肾',NULL,'女',5),(6,'多名阿当时','2020-04-01','撒大大',NULL,'男',6),(7,'65a63171',NULL,NULL,NULL,NULL,35);

/*Table structure for table `users` */

DROP TABLE IF EXISTS `users`;

CREATE TABLE `users` (
  `Uid` bigint(20) NOT NULL AUTO_INCREMENT,
  `Uname` varchar(1024) NOT NULL,
  `Upwd` varchar(1024) NOT NULL,
  `Umail` varchar(40) NOT NULL,
  `Udate` date NOT NULL,
  `Utype` tinyint(1) NOT NULL DEFAULT '0' COMMENT '管理员1',
  `Ustate` tinyint(1) NOT NULL DEFAULT '0' COMMENT '已经激活1',
  `Usuper` tinyint(1) DEFAULT '0' COMMENT 'true代表是超管 能够设置管理员',
  `salt` varchar(255) DEFAULT NULL COMMENT '盐',
  PRIMARY KEY (`Uid`)
) ENGINE=InnoDB AUTO_INCREMENT=40 DEFAULT CHARSET=utf8;

/*Data for the table `users` */

insert  into `users`(`Uid`,`Uname`,`Upwd`,`Umail`,`Udate`,`Utype`,`Ustate`,`Usuper`,`salt`) values (2,'lisi','1234','sad','2020-04-02',0,1,0,NULL),(3,'zhangsan1','1234','1','2020-04-13',0,1,0,NULL),(4,'zhangsan2','111','hihoho','2020-04-13',0,1,0,NULL),(5,'156258620101','123','384107082@qq.com','2020-04-13',0,1,0,NULL),(26,'zhangsann2','1234','1','2020-04-14',0,1,0,NULL),(27,'15625862010','111111','744900446@qq.com','2020-04-14',0,1,0,NULL),(28,'zhangsann','1234','1','2020-04-14',0,1,0,NULL),(29,'2','1','744900446@qq.com','2020-04-14',0,1,0,NULL),(30,'3','1','744900446@qq.com','2020-04-14',0,1,0,NULL),(31,'156258620103','123','744900446@qq.com','2020-04-14',0,1,0,NULL),(32,'156258620104','111','744900446@qq.com','2020-04-14',0,1,0,NULL),(33,'156258620105','12345678','123456@qq.com','2020-04-14',0,1,0,NULL),(34,'sunzitong','12345678','744900446@qq.com','2020-04-14',0,1,0,NULL),(35,'??','123','2813062251@qq.com','2020-04-22',0,0,0,NULL),(38,'213','213','123123','2020-04-22',0,0,0,NULL),(39,'zhangsan','10790e87bd85702a4fb95b8bc296f095','2813062251@qq.com','2020-04-22',0,0,0,'1294ff1b25b74e1088217ed72e4b2ca0');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
