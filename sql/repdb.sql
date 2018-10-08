/*
SQLyog 企业版 - MySQL GUI v8.14 
MySQL - 5.5.40 : Database - repdb
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`repdb` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `repdb`;

/*Table structure for table `dept` */

DROP TABLE IF EXISTS `dept`;

CREATE TABLE `dept` (
  `did` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键的id(编号)',
  `name` varchar(255) CHARACTER SET gbk DEFAULT NULL COMMENT '部门名称',
  `tele` varchar(32) CHARACTER SET gbk DEFAULT NULL COMMENT '部门电话',
  `state` varchar(8) DEFAULT NULL COMMENT '1正常使用 0虚拟删除',
  PRIMARY KEY (`did`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8 CHECKSUM=1 DELAY_KEY_WRITE=1 ROW_FORMAT=DYNAMIC;

/*Data for the table `dept` */

insert  into `dept`(`did`,`name`,`tele`,`state`) values (1,'管理员组','123456','1'),(2,'总裁办','123123','1'),(3,'采购部','321123','1'),(4,'销售部','123321','1'),(5,'公关部','1234','1'),(6,'行政部','12345','1'),(7,'人事部','1234567','1'),(8,'运输部','3214567','1'),(9,'党办','1111111','1'),(10,'工会','9998454','1'),(11,'仓储部','3333333','1'),(12,'客服部','4444444','1'),(13,'财务部','6666666','1'),(14,'运营部','7777777','1'),(19,'梁某','99999898','1');

/*Table structure for table `emp` */

DROP TABLE IF EXISTS `emp`;

CREATE TABLE `emp` (
  `eid` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '员工编号',
  `username` varchar(32) CHARACTER SET gbk DEFAULT NULL COMMENT '登录名称',
  `pwd` varchar(64) CHARACTER SET gbk DEFAULT NULL COMMENT '登陆密码',
  `name` varchar(64) CHARACTER SET gbk DEFAULT NULL COMMENT '真实姓名',
  `gender` int(11) DEFAULT NULL COMMENT '1是男,0是女',
  `email` varchar(64) CHARACTER SET gbk DEFAULT NULL COMMENT '邮箱地址',
  `tele` varchar(64) CHARACTER SET gbk DEFAULT NULL COMMENT '联系电话',
  `address` varchar(64) CHARACTER SET gbk DEFAULT NULL COMMENT '联系地址',
  `birthday` varchar(64) CHARACTER SET gbk DEFAULT NULL COMMENT '出生年月日',
  `deptId` bigint(20) DEFAULT NULL COMMENT '对应部门的id',
  `state` varchar(8) DEFAULT NULL COMMENT '0禁用 1使用',
  PRIMARY KEY (`eid`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8 CHECKSUM=1 DELAY_KEY_WRITE=1 ROW_FORMAT=DYNAMIC;

/*Data for the table `emp` */

insert  into `emp`(`eid`,`username`,`pwd`,`name`,`gender`,`email`,`tele`,`address`,`birthday`,`deptId`,`state`) values (1,'admin','3ef7164d1f6167cb9f2658c07d3c2f0a','超级管理员',1,'baoyang@123.com','123456789','湖北武汉','1980-8-8',1,'1'),(2,'sunwukong','5bf22b7dba77bd6d0fac75def5077a61','孙悟空',1,'swk@123.com','123456789','湖北武汉','1981-8-8',2,'1'),(3,'sunquan','4f63fd2b7860f3fc2be03744e45f8aa9','孙权',1,'sq@123.com','123456789','湖北襄阳','1982-8-8',2,'1'),(4,'kongming','90bc561e24ad10ae76c254ff9103aad5','孔明',1,'km@123.com','123456789','南阳孔明','1983-8-8',3,'1'),(5,'guanyu','5b0477a18fc0e08ccdac115bb9a54a11','关羽',1,'gy@123.com','123456789','湖北咸宁','1984-8-8',3,'1'),(6,'machao','d9aea44082e5125388b39d799b7b3b4d','马超',1,'mc@123.com','123456789','湖北咸宁','1985-8-8',3,'1'),(12,'liuhao','aa7b41fb24831b32b7d97ae0df1a9b8f','刘浩公关',0,'1616@qq.com','123123','武汉软件工程职业学院','2018-09-19',5,'1'),(13,'liang','d5893278f71f3b536f2c5f0e1baa5fc9','新武',0,'1411091514@qq.com','161262616','湖北武汉赤壁','1998-03-18',5,'1'),(14,'time','b293d1bdc3589167ff4fb614d9426225','时间',1,'16161@163.com','16464616','武软','2018-10-02',4,'1');

/*Table structure for table `emp_role` */

DROP TABLE IF EXISTS `emp_role`;

CREATE TABLE `emp_role` (
  `empId` bigint(20) DEFAULT NULL COMMENT 'emp和role中间表',
  `roleId` bigint(20) DEFAULT NULL COMMENT 'role表id'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `emp_role` */

insert  into `emp_role`(`empId`,`roleId`) values (1,1),(1,2),(1,3),(1,4),(1,5),(1,6),(2,2),(6,4);

/*Table structure for table `goods` */

DROP TABLE IF EXISTS `goods`;

CREATE TABLE `goods` (
  `gsid` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '商品的id',
  `name` varchar(64) DEFAULT NULL COMMENT '商品名称',
  `origin` varchar(64) DEFAULT NULL COMMENT '商品的产地',
  `producer` varchar(64) DEFAULT NULL COMMENT '商品的厂商',
  `unit` varchar(64) DEFAULT NULL COMMENT '计量的单位',
  `inprice` double DEFAULT NULL COMMENT '进价',
  `outprice` double DEFAULT NULL COMMENT '销售价',
  `goodstypeId` bigint(20) DEFAULT NULL COMMENT '商品类型的id',
  `state` varchar(8) DEFAULT NULL COMMENT '1正常,0已经被删除',
  PRIMARY KEY (`gsid`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=gbk;

/*Data for the table `goods` */

insert  into `goods`(`gsid`,`name`,`origin`,`producer`,`unit`,`inprice`,`outprice`,`goodstypeId`,`state`) values (1,'水蜜桃','湖北','湖北水蜜桃','斤',12.12,13,1,'1'),(2,'大鸭梨','湖南','湖南大鸭梨','斤',5.6,6.2,1,'1'),(3,'猕猴桃','深圳','深圳猕猴桃','斤',11.9,12.5,1,'1'),(4,'甜面酱','湖北','湖北甜面酱','袋',3.5,4.5,2,'1'),(5,'可比克','湖北','湖北可比克','袋',3.9,4.7,3,'1'),(6,'好吃点','湖北','湖北好吃点','袋',6,6.5,3,'1'),(7,'哇哈哈','武软','武软一食堂','斤',9.8,11,5,'1'),(8,'牛奶','内蒙古','武软二食堂','瓶',10,12,5,'1'),(9,'煲仔饭','武软','武软三食堂','碗',10,12,4,'1');

/*Table structure for table `goodstype` */

DROP TABLE IF EXISTS `goodstype`;

CREATE TABLE `goodstype` (
  `gid` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '商品类型表',
  `name` varchar(64) DEFAULT NULL COMMENT '商品类型的名字',
  `state` varchar(8) DEFAULT NULL COMMENT '1正常 0虚拟删除',
  PRIMARY KEY (`gid`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=gbk;

/*Data for the table `goodstype` */

insert  into `goodstype`(`gid`,`name`,`state`) values (1,'水果','1'),(2,'调味品','1'),(3,'儿童食品','1'),(4,'生活','1'),(5,'饮料','1'),(6,'家家家','0'),(7,'啦啦啦','1');

/*Table structure for table `menu` */

DROP TABLE IF EXISTS `menu`;

CREATE TABLE `menu` (
  `menuId` varchar(32) NOT NULL COMMENT '系统菜单编号',
  `menuName` varchar(32) NOT NULL COMMENT '名称',
  `url` varchar(255) DEFAULT NULL COMMENT '对应的URL',
  `icon` varchar(100) DEFAULT NULL COMMENT '图标样式',
  `pid` varchar(30) NOT NULL COMMENT '上一级菜单编号',
  PRIMARY KEY (`menuId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `menu` */

insert  into `menu`(`menuId`,`menuName`,`url`,`icon`,`pid`) values ('0','系统菜单','-','icon-sys','-1'),('100','基础数据','-','icon-sys','0'),('101','商品类型','goodsType/toGoodsType','icon-sys','100'),('102','商品管理','goods/toAdd','icon-sys','100'),('103','供应商管理','supplier/toSupplier?type=1','icon-sys','100'),('104','客户管理','supplier/toSupplier?type=2','icon-sys','100'),('105','仓库','store/toStore','icon-sys','100'),('106','仓库数量','storedetail/toStoredetail','icon-sys','100'),('107','仓库记录','storeOper/toStoreOper','icon-sys','100'),('108','仓库预警','storeAlert/toStoreAlert','icon-sys','100'),('200','人事管理','-','icon-sys','0'),('201','部门','dept/dept_list','icon-sys','200'),('202','员工','emp/toEmp','icon-sys','200'),('300','采购管理','-','icon-sys','0'),('301','采购订单查阅','orders/toOrders?oper=orders&type=1','icon-sys','300'),('302','采购订单审核','orders/toOrders?oper=doCheck&type=1','icon-sys','300'),('303','采购订单确认','orders/toOrders?oper=doStart&type=1','icon-sys','300'),('304','采购订单入库','orders/toOrders?oper=doInStore&type=1','icon-sys','300'),('305','我的采购订单','orders/toOrders?oper=myorders&type=1','icon-sys','300'),('306','订单退货查阅','returnOrders/toReturnOrders?oper=returnOrders&type=1','icon-sys','300'),('307','我的退货订单','returnOrders/toReturnOrders?oper=myorders&type=1','icon-sys','300'),('308','退货订单审阅','returnOrders/toReturnOrders?oper=doCheck&type=1','icon-sys','300'),('309','退货订单出库','returnOrders/toReturnOrders?oper=doInStore&type=1','icon-sys','300'),('400','销售管理','-','icon-sys','0'),('401','销售订单查询','orders/toOrders?oper=orders&type=2','icon-sys','400'),('402','我的销售订单','orders/toOrders?oper=myorders&type=2','icon-sys','400'),('403','销售订单出库','orders/toOrders?oper=doOutStore&type=2','icon-sys','400'),('500','权限管理','-','icon-sys','0'),('501','重置密码','password/repwd','icon-sys','500'),('502','用户角色管理','roleEmpSet/toRoleEmpSet','icon-sys','500'),('503','角色权限管理','roleMenuSet/toRoleMenuSet','icon-sys','500'),('504','操作记录','myLog/toLog','icon-sys','500'),('600','统计报表','-','icon-sys','0'),('601','销售统计','count/toReport','icon-sys','600'),('602','销售趋势表报','count/toTrend','icon-sys','600');

/*Table structure for table `mylog` */

DROP TABLE IF EXISTS `mylog`;

CREATE TABLE `mylog` (
  `logId` varchar(64) NOT NULL COMMENT '日志主键',
  `type` varchar(16) DEFAULT NULL COMMENT '日志类型',
  `title` varchar(16) DEFAULT NULL COMMENT '日志标题',
  `remoteAddr` varchar(64) DEFAULT NULL COMMENT '请求地址',
  `requestUri` varchar(64) DEFAULT NULL COMMENT '请求的URL',
  `method` varchar(16) DEFAULT NULL COMMENT '提交方式',
  `params` varchar(255) DEFAULT NULL COMMENT '提交参数',
  `exception` varchar(64) DEFAULT NULL COMMENT '异常',
  `operateDate` datetime DEFAULT NULL COMMENT '开始时间',
  `timeout` varchar(32) DEFAULT NULL COMMENT '结束时间',
  `userId` varchar(32) DEFAULT NULL COMMENT '用户id',
  PRIMARY KEY (`logId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `mylog` */

/*Table structure for table `orders` */

DROP TABLE IF EXISTS `orders`;

CREATE TABLE `orders` (
  `oid` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '订单表的id',
  `createtime` varchar(64) DEFAULT NULL COMMENT '创建时间',
  `checktime` varchar(64) DEFAULT NULL COMMENT '审核时间',
  `starttime` varchar(64) DEFAULT NULL COMMENT '确认日期',
  `endtime` varchar(64) DEFAULT NULL COMMENT '结束日期',
  `type` varchar(8) DEFAULT NULL COMMENT '1.采购,2.销售',
  `createer` bigint(20) DEFAULT NULL COMMENT '创建人',
  `checker` bigint(20) DEFAULT NULL COMMENT '审核人',
  `starter` bigint(20) DEFAULT NULL COMMENT '确定人',
  `ender` bigint(20) DEFAULT NULL COMMENT '结束人',
  `supplierId` bigint(20) DEFAULT NULL COMMENT '供应商id',
  `totalmoney` double DEFAULT NULL COMMENT '总金额',
  `state` varchar(8) DEFAULT NULL COMMENT '采购: 0:未审核 1:已审核, 2:已确认, 3:已入库；销售：0:未出库 1:已出核',
  `waybillsn` varchar(64) DEFAULT NULL COMMENT '运单号',
  PRIMARY KEY (`oid`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=gbk;

/*Data for the table `orders` */

insert  into `orders`(`oid`,`createtime`,`checktime`,`starttime`,`endtime`,`type`,`createer`,`checker`,`starter`,`ender`,`supplierId`,`totalmoney`,`state`,`waybillsn`) values (2,'2018-09-19 00:00:00',NULL,NULL,NULL,'1',1,NULL,NULL,NULL,1,1602,'0','1'),(3,'2018-09-20 00:00:00','2018-09-19 19:54:34',NULL,NULL,'1',1,1,NULL,NULL,2,231,'1','3'),(4,'2018-09-21 00:00:00',NULL,NULL,'Wed Sep 26 16:08:27 CST 2018','2',1,NULL,NULL,1,2,750,'1','5'),(5,'2018-09-22 00:00:00','2018-09-21 11:28:02','2018-09-26 17:10:03','2018-09-26 18:15:24','1',1,1,1,6,1,6840,'3','4'),(6,'2018-09-26 18:07:07','2018-09-26 18:07:21','2018-09-26 18:07:48','2018-09-26 18:14:14','1',6,6,6,6,5,1562,'3',NULL),(7,'2018-09-26 18:16:40','2018-10-02 09:25:48','2018-10-02 09:25:56','2018-10-02 09:26:05','1',2,2,2,2,5,13050,'3',NULL),(8,'2018-09-26 18:19:11','2018-09-30 20:29:12',NULL,NULL,'1',2,1,NULL,NULL,2,71400,'1',NULL),(9,'2018-09-26 18:26:36','2018-09-26 18:26:50','2018-09-26 18:26:55','2018-09-26 18:29:35','1',1,1,1,1,6,8190,'3',NULL),(10,'2018-10-01 23:11:06',NULL,NULL,NULL,'1',1,NULL,NULL,NULL,6,4674,'0',NULL),(11,'2018-10-03 19:32:37','2018-10-03 19:53:01',NULL,'Wed Oct 03 20:39:48 CST 2018','2',1,1,NULL,1,2,603,'1',NULL),(12,'2018-10-08 10:56:38','2018-10-08 10:56:52','2018-10-08 10:57:03','2018-10-08 10:57:15','1',1,1,1,1,5,390,'3',NULL);

/*Table structure for table `ordersdetail` */

DROP TABLE IF EXISTS `ordersdetail`;

CREATE TABLE `ordersdetail` (
  `odid` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '订单明细的id',
  `goodsId` bigint(20) DEFAULT NULL COMMENT '商品id',
  `goodsname` varchar(64) DEFAULT NULL COMMENT '商品的名称',
  `price` double DEFAULT NULL COMMENT '商品价格',
  `num` bigint(20) DEFAULT NULL COMMENT '商品个数',
  `money` double DEFAULT NULL COMMENT '金额',
  `endtime` varchar(64) DEFAULT NULL COMMENT '结束日期',
  `ender` varchar(64) DEFAULT NULL COMMENT '结束人',
  `storeId` bigint(20) DEFAULT NULL COMMENT '仓库id',
  `state` varchar(64) DEFAULT NULL COMMENT '采购：0=未入库，1=已入库  销售：0=未出库，1=已出库',
  `ordersId` bigint(20) DEFAULT NULL COMMENT '订单id',
  PRIMARY KEY (`odid`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=gbk;

/*Data for the table `ordersdetail` */

insert  into `ordersdetail`(`odid`,`goodsId`,`goodsname`,`price`,`num`,`money`,`endtime`,`ender`,`storeId`,`state`,`ordersId`) values (1,1,'水蜜桃',12.12,100,1212,'Wed Sep 19 16:52:53 CST 2018','刘浩',3,'1',2),(2,5,'可比克',3.9,100,390,'Wed Sep 19 16:37:19 CST 2018','刘浩',3,'1',2),(3,2,'大鸭梨',5.6,20,112,NULL,NULL,NULL,'1',3),(4,3,'猕猴桃',11.9,10,119,NULL,NULL,NULL,'1',3),(5,2,'大鸭梨',6.2,100,620,'2018-09-26 16:08:27','超级管理员',3,'1',4),(6,6,'好吃点',6.5,20,130,'Fri Sep 21 11:55:58 CST 2018','超级管理员',3,'1',4),(7,6,'好吃点',6,300,1800,'2018-09-26 18:15:24','马超',2,'1',5),(8,2,'大鸭梨',5.6,900,5040,'Fri Sep 21 11:34:49 CST 2018','超级管理员',3,'1',5),(9,1,'水蜜桃',12.12,100,1212,'2018-09-26 18:13:25','马超',2,'1',6),(10,4,'甜面酱',3.5,100,350,'2018-09-26 18:14:14','马超',2,'1',6),(11,5,'可比克',3.9,600,2340,NULL,NULL,NULL,'1',7),(12,3,'猕猴桃',11.9,900,10710,'2018-10-02 09:26:05','孙悟空',2,'1',7),(13,3,'猕猴桃',11.9,6000,71400,NULL,NULL,NULL,'1',8),(14,4,'甜面酱',3.5,900,3150,'2018-09-26 18:27:17','超级管理员',1,'1',9),(15,2,'大鸭梨',5.6,900,5040,'2018-09-26 18:29:35','超级管理员',1,'1',9),(16,5,'可比克',3.9,100,390,NULL,NULL,NULL,'0',10),(17,3,'猕猴桃',11.9,360,4284,NULL,NULL,NULL,'0',10),(18,2,'大鸭梨',6.2,90,558,'2018-10-03 20:39:39','超级管理员',3,'1',11),(19,4,'甜面酱',4.5,10,45,'2018-10-03 20:39:48','超级管理员',3,'1',11),(20,5,'可比克',3.9,100,390,'2018-10-08 10:57:15','超级管理员',2,'1',12);

/*Table structure for table `returnorders` */

DROP TABLE IF EXISTS `returnorders`;

CREATE TABLE `returnorders` (
  `roid` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '退货的id',
  `createtime` varchar(64) DEFAULT NULL COMMENT '退货订单生成日期',
  `checktime` varchar(64) DEFAULT NULL COMMENT '检查日期',
  `endtime` varchar(64) DEFAULT NULL COMMENT '结束日期',
  `type` varchar(8) DEFAULT NULL COMMENT '订单类型 1采购 2销售',
  `createer` bigint(20) DEFAULT NULL COMMENT '下单员id',
  `checker` bigint(20) DEFAULT NULL COMMENT '检查员id',
  `ender` bigint(20) DEFAULT NULL COMMENT '确认id',
  `supplierId` bigint(20) DEFAULT NULL COMMENT '供应商或者客户id',
  `totalmoney` double DEFAULT NULL COMMENT '合计金额',
  `state` varchar(8) DEFAULT NULL COMMENT '订单的状态 0未审核 1已审核 2已退货',
  `waybillsn` varchar(64) DEFAULT NULL COMMENT '运单号',
  `ordersOid` bigint(20) DEFAULT NULL COMMENT '原订单编号',
  PRIMARY KEY (`roid`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

/*Data for the table `returnorders` */

insert  into `returnorders`(`roid`,`createtime`,`checktime`,`endtime`,`type`,`createer`,`checker`,`ender`,`supplierId`,`totalmoney`,`state`,`waybillsn`,`ordersOid`) values (1,'2018-09-30','2018-10-01','2018-10-01','1',2,1,1,6,56,'2',NULL,9),(2,'2018-09-27','2018-09-30','2018-10-01','1',3,2,2,1,120,'2',NULL,5),(3,'2018-10-01','2018-10-01','2018-10-01','1',1,1,1,5,35,'2',NULL,6),(4,'2018-10-01','2018-10-01',NULL,'1',1,1,NULL,5,121.19999999999999,'1',NULL,6),(5,'2018-10-01','2018-10-01','2018-10-01','1',1,1,1,5,277.4,'2',NULL,6);

/*Table structure for table `returnordersdetail` */

DROP TABLE IF EXISTS `returnordersdetail`;

CREATE TABLE `returnordersdetail` (
  `rosd` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '退货订单详情',
  `goodsId` bigint(20) DEFAULT NULL COMMENT '商品编号',
  `goodsName` varchar(64) DEFAULT NULL COMMENT '商品名字',
  `price` double DEFAULT NULL COMMENT '价格',
  `num` bigint(20) DEFAULT NULL COMMENT '数量',
  `money` double DEFAULT NULL COMMENT '金额',
  `endtime` varchar(64) DEFAULT NULL COMMENT '结束日期',
  `ender` bigint(20) DEFAULT NULL COMMENT '结束人id',
  `storeId` bigint(20) DEFAULT NULL COMMENT '仓库编号',
  `state` varchar(8) DEFAULT NULL COMMENT '状态  采购0未出库 1已出库  ',
  `returnordersId` bigint(20) DEFAULT NULL COMMENT '退货订单id',
  PRIMARY KEY (`rosd`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

/*Data for the table `returnordersdetail` */

insert  into `returnordersdetail`(`rosd`,`goodsId`,`goodsName`,`price`,`num`,`money`,`endtime`,`ender`,`storeId`,`state`,`returnordersId`) values (1,2,'大鸭梨',5.6,20,56,'2018-10-01',1,NULL,'1',1),(2,6,'好吃点',6,20,120,'2018-10-01',2,2,'1',2),(3,4,'甜面酱',3.5,10,35,'2018-10-01',1,3,'1',3),(4,1,'水蜜桃',12.12,10,121.19999999999999,NULL,NULL,NULL,'0',4),(5,1,'水蜜桃',12.12,20,242.39999999999998,'2018-10-01',1,3,'1',5),(6,4,'甜面酱',3.5,10,35,'2018-10-01',1,3,'1',5);

/*Table structure for table `role` */

DROP TABLE IF EXISTS `role`;

CREATE TABLE `role` (
  `rid` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '用户权限表id',
  `name` varchar(254) DEFAULT NULL COMMENT '用户权限',
  `state` varchar(8) DEFAULT NULL COMMENT '1正常 0被删除',
  PRIMARY KEY (`rid`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=gbk;

/*Data for the table `role` */

insert  into `role`(`rid`,`name`,`state`) values (1,'超级管理员','1'),(2,'采购员','1'),(3,'销售员','1'),(4,'库管员','1'),(5,'采购经理','1'),(6,'仓库主管','1');

/*Table structure for table `role_menu` */

DROP TABLE IF EXISTS `role_menu`;

CREATE TABLE `role_menu` (
  `roleId` bigint(20) DEFAULT NULL COMMENT '角色菜单表',
  `menuId` bigint(20) DEFAULT NULL COMMENT '菜单id'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `role_menu` */

insert  into `role_menu`(`roleId`,`menuId`) values (2,300),(2,301),(2,302),(2,303),(2,304),(2,305),(4,100),(4,101),(4,102),(4,103),(4,104),(4,105),(1,100),(1,101),(1,102),(1,103),(1,104),(1,105),(1,106),(1,107),(1,108),(1,200),(1,201),(1,202),(1,300),(1,301),(1,302),(1,303),(1,304),(1,305),(1,306),(1,307),(1,308),(1,309),(1,400),(1,401),(1,402),(1,403),(1,500),(1,501),(1,502),(1,503),(1,504),(1,600),(1,601),(1,602);

/*Table structure for table `store` */

DROP TABLE IF EXISTS `store`;

CREATE TABLE `store` (
  `sid` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '仓库id',
  `name` varchar(64) DEFAULT NULL COMMENT '仓库名称',
  `empId` bigint(20) DEFAULT NULL COMMENT '管理员id',
  `state` varchar(8) DEFAULT NULL COMMENT '是否停用 1正常 0停用',
  PRIMARY KEY (`sid`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=gbk;

/*Data for the table `store` */

insert  into `store`(`sid`,`name`,`empId`,`state`) values (1,'生活用品',1,'1'),(2,'零食仓库',2,'1'),(3,'饮料用品',3,'1'),(4,'厨房用品',6,'1');

/*Table structure for table `storedetail` */

DROP TABLE IF EXISTS `storedetail`;

CREATE TABLE `storedetail` (
  `sdid` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '商品仓库库存表',
  `storeId` bigint(20) DEFAULT NULL COMMENT '仓库id',
  `goodsId` bigint(20) DEFAULT NULL COMMENT '商品id',
  `num` bigint(20) DEFAULT NULL COMMENT '库存数量',
  PRIMARY KEY (`sdid`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=gbk;

/*Data for the table `storedetail` */

insert  into `storedetail`(`sdid`,`storeId`,`goodsId`,`num`) values (3,3,5,100),(6,3,1,280),(7,3,6,10),(8,3,2,700),(9,3,4,70),(10,2,1,100),(11,2,4,100),(12,2,6,5),(13,1,4,900),(14,1,2,900),(15,2,3,900),(16,2,5,100);

/*Table structure for table `storeoper` */

DROP TABLE IF EXISTS `storeoper`;

CREATE TABLE `storeoper` (
  `soid` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '商品仓库库存操作记录表',
  `empId` bigint(20) DEFAULT NULL COMMENT '员工id',
  `opertime` varchar(32) DEFAULT NULL COMMENT '操作时间',
  `storeId` bigint(20) DEFAULT NULL COMMENT '仓库id',
  `goodsId` bigint(20) DEFAULT NULL COMMENT '商品id',
  `num` bigint(20) DEFAULT NULL COMMENT '数量',
  `type` varchar(8) DEFAULT NULL COMMENT '1：入库 2：出库',
  PRIMARY KEY (`soid`)
) ENGINE=InnoDB AUTO_INCREMENT=26 DEFAULT CHARSET=gbk;

/*Data for the table `storeoper` */

insert  into `storeoper`(`soid`,`empId`,`opertime`,`storeId`,`goodsId`,`num`,`type`) values (1,11,'2018-09-20 06:03:00',3,5,100,'1'),(4,11,'2018-09-22 01:42:00',3,1,100,'1'),(5,11,'2018-09-22 01:49:41',3,1,200,'1'),(6,1,'2018-09-20 06:47:31',3,6,300,'1'),(7,1,'2018-09-20 06:52:53',3,2,900,'1'),(8,1,'2018-09-22 01:33:59',3,6,280,'2'),(9,1,'2018-09-22 01:34:49',3,6,260,'2'),(10,1,'2018-09-22 01:37:57',3,6,240,'2'),(11,1,'2018-09-23 01:38:37',3,2,800,'2'),(12,6,'2018-09-26 18:07:59',3,1,300,'1'),(13,6,'2018-09-26 18:08:04',3,4,100,'1'),(14,6,'2018-09-26 18:13:25',2,1,100,'1'),(15,6,'2018-09-26 18:14:14',2,4,100,'1'),(16,6,'2018-09-26 18:15:24',2,6,300,'1'),(17,1,'2018-09-26 18:27:17',1,4,900,'1'),(18,1,'2018-09-26 18:29:35',1,2,900,'1'),(19,1,'2018-10-01 18:30:30',3,4,10,'2'),(20,1,'2018-10-01 11:23:36',3,1,20,'2'),(21,1,'2018-10-01 11:23:40',3,4,10,'2'),(22,2,'2018-10-02 09:26:05',2,3,900,'1'),(23,1,'Wed Oct 03 20:39:39 CST 2018',3,2,700,'2'),(24,1,'Wed Oct 03 20:39:48 CST 2018',3,4,70,'2'),(25,1,'2018-10-08 10:57:15',2,5,100,'1');

/*Table structure for table `supplier` */

DROP TABLE IF EXISTS `supplier`;

CREATE TABLE `supplier` (
  `suid` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '供应商以及客户表id',
  `name` varchar(64) DEFAULT NULL COMMENT '名称',
  `address` varchar(64) DEFAULT NULL COMMENT '地址',
  `contact` varchar(64) DEFAULT NULL COMMENT '联系人',
  `tele` varchar(64) DEFAULT NULL COMMENT '电话',
  `email` varchar(64) DEFAULT NULL COMMENT '邮箱',
  `type` varchar(8) DEFAULT NULL COMMENT '1.供应商,2.客户',
  `state` varchar(8) DEFAULT NULL COMMENT '1正常 0被删除',
  PRIMARY KEY (`suid`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=gbk;

/*Data for the table `supplier` */

insert  into `supplier`(`suid`,`name`,`address`,`contact`,`tele`,`email`,`type`,`state`) values (1,'天猫','杭州','马云','1101101101','146456544@qq.com','1','1'),(2,'京东','北京','刘强东','120120120','21211@qq.com','1','1'),(3,'武软','武汉','我我','11111','151@qq.com','2','1'),(4,'北上北上','北上','你你','62626','61551@qq.com','2','1'),(5,'苏宁','武汉','苏宁老板','646462632632','16161@qq.com','1','1'),(6,'天','杭州','马云','1101101101','146456544@qq.com','1','1');

/*Table structure for table `waybill` */

DROP TABLE IF EXISTS `waybill`;

CREATE TABLE `waybill` (
  `sn` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '运单号',
  `userId` bigint(20) DEFAULT NULL COMMENT '用户id',
  `toaddress` varchar(64) DEFAULT NULL COMMENT '收货地址',
  `addressperson` varchar(64) DEFAULT NULL COMMENT '收货人',
  `tele` varchar(64) DEFAULT NULL COMMENT '收货人电话',
  `info` varchar(2000) DEFAULT NULL COMMENT '运单详情',
  `state` varchar(8) DEFAULT NULL COMMENT '运单状态',
  `createtime` varchar(32) DEFAULT NULL COMMENT '创建时间',
  `isuse` varchar(8) DEFAULT NULL COMMENT '1正常 0被删除',
  PRIMARY KEY (`sn`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

/*Data for the table `waybill` */

insert  into `waybill`(`sn`,`userId`,`toaddress`,`addressperson`,`tele`,`info`,`state`,`createtime`,`isuse`) values (1,1,'武软一食堂','帅建','16216264','鸭子十只，鸡子十只，鸡爪十只','准备发货','2018-09-26 14:03:56','1'),(2,6,'武软二食堂','李兄','1651561626','牛肉十斤，立马送到','正在派送','2018-09-26','1'),(3,16,'武软二食堂',NULL,'1651561626','牛肉十斤，立马送到','0','2018-09-26','0'),(4,26,'武软二食堂',NULL,'6461112','牛肉十斤，空运','0','2018-09-26','0'),(5,9527,'武软三食堂','李永健','4662626','牛肉十斤，空运给永健吃','正在派送','2018-09-26','1');

/*Table structure for table `waybilldetail` */

DROP TABLE IF EXISTS `waybilldetail`;

CREATE TABLE `waybilldetail` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '详情表id',
  `su` bigint(20) DEFAULT NULL COMMENT '运单号',
  `exedate` varchar(64) DEFAULT NULL COMMENT '执行日期',
  `exetime` varchar(64) DEFAULT NULL COMMENT '执行时间',
  `info` varchar(100) DEFAULT NULL COMMENT '执行信息',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

/*Data for the table `waybilldetail` */

insert  into `waybilldetail`(`id`,`su`,`exedate`,`exetime`,`info`) values (1,5,'2018-09-26 14:47:54','2018-09-26 14:47:54','正在派送');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
