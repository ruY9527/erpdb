/*
 * ERP 数据库初始化脚本
 * MySQL 8.0+ 兼容版本
 * 数据库名: repdb
 * 字符集: utf8mb4
 */

-- MySQL 8 兼容设置
SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;
SET SQL_MODE = 'NO_AUTO_VALUE_ON_ZERO,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE';

-- 创建数据库
CREATE DATABASE IF NOT EXISTS `repdb` 
    DEFAULT CHARACTER SET utf8mb4 
    DEFAULT COLLATE utf8mb4_unicode_ci;

USE `repdb`;

-- ----------------------------
-- Table structure for dept (部门表)
-- ----------------------------
DROP TABLE IF EXISTS `dept`;
CREATE TABLE `dept` (
    `did` BIGINT NOT NULL AUTO_INCREMENT COMMENT '主键ID(编号)',
    `name` VARCHAR(255) DEFAULT NULL COMMENT '部门名称',
    `tele` VARCHAR(32) DEFAULT NULL COMMENT '部门电话',
    `state` VARCHAR(8) DEFAULT '1' COMMENT '状态: 1-正常使用, 0-虚拟删除',
    PRIMARY KEY (`did`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='部门表';

-- ----------------------------
-- Records of dept
-- ----------------------------
INSERT INTO `dept` (`did`, `name`, `tele`, `state`) VALUES
(1, '管理员组', '123456', '1'),
(2, '总裁办', '123123', '1'),
(3, '采购部', '321123', '1'),
(4, '销售部', '123321', '1'),
(5, '公关部', '1234', '1'),
(6, '行政部', '12345', '1'),
(7, '人事部', '1234567', '1'),
(8, '运输部', '3214567', '1'),
(9, '党办', '1111111', '1'),
(10, '工会', '9998454', '1'),
(11, '仓储部', '3333333', '1'),
(12, '客服部', '4444444', '1'),
(13, '财务部', '6666666', '1'),
(14, '运营部', '7777777', '1'),
(19, '梁某', '99999898', '1');

-- ----------------------------
-- Table structure for emp (员工表)
-- ----------------------------
DROP TABLE IF EXISTS `emp`;
CREATE TABLE `emp` (
    `eid` BIGINT NOT NULL AUTO_INCREMENT COMMENT '员工编号',
    `username` VARCHAR(32) DEFAULT NULL COMMENT '登录名称',
    `pwd` VARCHAR(64) DEFAULT NULL COMMENT '登录密码',
    `name` VARCHAR(64) DEFAULT NULL COMMENT '真实姓名',
    `gender` INT DEFAULT NULL COMMENT '性别: 1-男, 0-女',
    `email` VARCHAR(64) DEFAULT NULL COMMENT '邮箱地址',
    `tele` VARCHAR(64) DEFAULT NULL COMMENT '联系电话',
    `address` VARCHAR(64) DEFAULT NULL COMMENT '联系地址',
    `birthday` VARCHAR(64) DEFAULT NULL COMMENT '出生年月日',
    `deptId` BIGINT DEFAULT NULL COMMENT '部门ID',
    `state` VARCHAR(8) DEFAULT '1' COMMENT '状态: 0-禁用, 1-使用',
    PRIMARY KEY (`eid`),
    KEY `idx_emp_username` (`username`),
    KEY `idx_emp_deptId` (`deptId`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='员工表';

-- ----------------------------
-- Records of emp
-- 密码使用BCrypt加密，默认密码均为123456
-- BCrypt格式: $2a$10$开头的60字符字符串
-- 以下密码为明文 "123456" 的真实BCrypt加密结果（已验证通过）
-- 密码: $2a$10$llKC0enwaYtiEGj.UzLxZu460A8RIAKaDhDl.CXQL7ukP7Wk79qyK
-- ----------------------------
INSERT INTO `emp` (`eid`, `username`, `pwd`, `name`, `gender`, `email`, `tele`, `address`, `birthday`, `deptId`, `state`) VALUES
(1, 'admin', '$2a$10$llKC0enwaYtiEGj.UzLxZu460A8RIAKaDhDl.CXQL7ukP7Wk79qyK', '超级管理员', 1, 'baoyang@123.com', '123456789', '湖北武汉', '1980-8-8', 1, '1'),
(2, 'sunwukong', '$2a$10$llKC0enwaYtiEGj.UzLxZu460A8RIAKaDhDl.CXQL7ukP7Wk79qyK', '孙悟空', 1, 'swk@123.com', '123456789', '湖北武汉', '1981-8-8', 2, '1'),
(3, 'sunquan', '$2a$10$llKC0enwaYtiEGj.UzLxZu460A8RIAKaDhDl.CXQL7ukP7Wk79qyK', '孙权', 1, 'sq@123.com', '123456789', '湖北襄阳', '1982-8-8', 2, '1'),
(4, 'kongming', '$2a$10$llKC0enwaYtiEGj.UzLxZu460A8RIAKaDhDl.CXQL7ukP7Wk79qyK', '孔明', 1, 'km@123.com', '123456789', '南阳孔明', '1983-8-8', 3, '1'),
(5, 'guanyu', '$2a$10$llKC0enwaYtiEGj.UzLxZu460A8RIAKaDhDl.CXQL7ukP7Wk79qyK', '关羽', 1, 'gy@123.com', '123456789', '湖北咸宁', '1984-8-8', 3, '1'),
(6, 'machao', '$2a$10$llKC0enwaYtiEGj.UzLxZu460A8RIAKaDhDl.CXQL7ukP7Wk79qyK', '马超', 1, 'mc@123.com', '123456789', '湖北咸宁', '1985-8-8', 3, '1'),
(12, 'liuhao', '$2a$10$llKC0enwaYtiEGj.UzLxZu460A8RIAKaDhDl.CXQL7ukP7Wk79qyK', '刘浩公关', 0, '1616@qq.com', '123123', '武汉软件工程职业学院', '2018-09-19', 5, '1'),
(13, 'liang', '$2a$10$llKC0enwaYtiEGj.UzLxZu460A8RIAKaDhDl.CXQL7ukP7Wk79qyK', '新武', 0, '1411091514@qq.com', '161262616', '湖北武汉赤壁', '1998-03-18', 5, '1'),
(14, 'time', '$2a$10$llKC0enwaYtiEGj.UzLxZu460A8RIAKaDhDl.CXQL7ukP7Wk79qyK', '时间', 1, '16161@163.com', '16464616', '武软', '2018-10-02', 4, '1');

-- ----------------------------
-- Table structure for role (角色表)
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role` (
    `rid` BIGINT NOT NULL AUTO_INCREMENT COMMENT '角色ID',
    `name` VARCHAR(254) DEFAULT NULL COMMENT '角色名称',
    `state` VARCHAR(8) DEFAULT '1' COMMENT '状态: 1-正常, 0-被删除',
    PRIMARY KEY (`rid`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='角色表';

-- ----------------------------
-- Records of role
-- ----------------------------
INSERT INTO `role` (`rid`, `name`, `state`) VALUES
(1, '超级管理员', '1'),
(2, '采购员', '1'),
(3, '销售员', '1'),
(4, '库管员', '1'),
(5, '采购经理', '1'),
(6, '仓库主管', '1');

-- ----------------------------
-- Table structure for emp_role (员工角色关联表)
-- ----------------------------
DROP TABLE IF EXISTS `emp_role`;
CREATE TABLE `emp_role` (
    `empId` BIGINT NOT NULL COMMENT '员工ID',
    `roleId` BIGINT NOT NULL COMMENT '角色ID',
    PRIMARY KEY (`empId`, `roleId`),
    KEY `idx_emp_role_roleId` (`roleId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='员工角色关联表';

-- ----------------------------
-- Records of emp_role
-- ----------------------------
INSERT INTO `emp_role` (`empId`, `roleId`) VALUES
(1, 1), (1, 2), (1, 3), (1, 4), (1, 5), (1, 6),
(2, 2),
(6, 4);

-- ----------------------------
-- Table structure for menu (菜单表)
-- ----------------------------
DROP TABLE IF EXISTS `menu`;
CREATE TABLE `menu` (
    `menuId` BIGINT NOT NULL COMMENT '菜单编号',
    `menuName` VARCHAR(32) NOT NULL COMMENT '菜单名称',
    `url` VARCHAR(255) DEFAULT NULL COMMENT '对应URL',
    `icon` VARCHAR(100) DEFAULT NULL COMMENT '图标样式',
    `pid` BIGINT NOT NULL COMMENT '父菜单编号',
    `state` VARCHAR(8) DEFAULT '1' COMMENT '状态: 1-正常, 0-禁用',
    PRIMARY KEY (`menuId`),
    KEY `idx_menu_pid` (`pid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='菜单表';

-- ----------------------------
-- Records of menu
-- ----------------------------
INSERT INTO `menu` (`menuId`, `menuName`, `url`, `icon`, `pid`, `state`) VALUES
(0, '系统菜单', '-', 'icon-sys', -1, '1'),
(100, '基础数据', '-', 'icon-sys', 0, '1'),
(101, '商品类型', 'goodsType/toGoodsType', 'icon-sys', 100, '1'),
(102, '商品管理', 'goods/toAdd', 'icon-sys', 100, '1'),
(103, '供应商管理', 'supplier/toSupplier?type=1', 'icon-sys', 100, '1'),
(104, '客户管理', 'supplier/toSupplier?type=2', 'icon-sys', 100, '1'),
(105, '仓库', 'store/toStore', 'icon-sys', 100, '1'),
(106, '仓库数量', 'storedetail/toStoredetail', 'icon-sys', 100, '1'),
(107, '仓库记录', 'storeOper/toStoreOper', 'icon-sys', 100, '1'),
(108, '仓库预警', 'storeAlert/toStoreAlert', 'icon-sys', 100, '1'),
(200, '人事管理', '-', 'icon-sys', 0, '1'),
(201, '部门', 'dept/dept_list', 'icon-sys', 200, '1'),
(202, '员工', 'emp/toEmp', 'icon-sys', 200, '1'),
(300, '采购管理', '-', 'icon-sys', 0, '1'),
(301, '采购订单查阅', 'orders/toOrders?oper=orders&type=1', 'icon-sys', 300, '1'),
(302, '采购订单审核', 'orders/toOrders?oper=doCheck&type=1', 'icon-sys', 300, '1'),
(303, '采购订单确认', 'orders/toOrders?oper=doStart&type=1', 'icon-sys', 300, '1'),
(304, '采购订单入库', 'orders/toOrders?oper=doInStore&type=1', 'icon-sys', 300, '1'),
(305, '我的采购订单', 'orders/toOrders?oper=myorders&type=1', 'icon-sys', 300, '1'),
(306, '订单退货查阅', 'returnOrders/toReturnOrders?oper=returnOrders&type=1', 'icon-sys', 300, '1'),
(307, '我的退货订单', 'returnOrders/toReturnOrders?oper=myorders&type=1', 'icon-sys', 300, '1'),
(308, '退货订单审阅', 'returnOrders/toReturnOrders?oper=doCheck&type=1', 'icon-sys', 300, '1'),
(309, '退货订单出库', 'returnOrders/toReturnOrders?oper=doInStore&type=1', 'icon-sys', 300, '1'),
(400, '销售管理', '-', 'icon-sys', 0, '1'),
(401, '销售订单查询', 'orders/toOrders?oper=orders&type=2', 'icon-sys', 400, '1'),
(402, '我的销售订单', 'orders/toOrders?oper=myorders&type=2', 'icon-sys', 400, '1'),
(403, '销售订单出库', 'orders/toOrders?oper=doOutStore&type=2', 'icon-sys', 400, '1'),
(500, '权限管理', '-', 'icon-sys', 0, '1'),
(501, '重置密码', 'password/repwd', 'icon-sys', 500, '1'),
(502, '用户角色管理', 'roleEmpSet/toRoleEmpSet', 'icon-sys', 500, '1'),
(503, '角色权限管理', 'roleMenuSet/toRoleMenuSet', 'icon-sys', 500, '1'),
(504, '操作记录', 'myLog/toLog', 'icon-sys', 500, '1'),
(600, '统计报表', '-', 'icon-sys', 0, '1'),
(601, '销售统计', 'count/toReport', 'icon-sys', 600, '1'),
(602, '销售趋势报表', 'count/toTrend', 'icon-sys', 600, '1');

-- ----------------------------
-- Table structure for role_menu (角色菜单关联表)
-- ----------------------------
DROP TABLE IF EXISTS `role_menu`;
CREATE TABLE `role_menu` (
    `roleId` BIGINT NOT NULL COMMENT '角色ID',
    `menuId` BIGINT NOT NULL COMMENT '菜单ID',
    PRIMARY KEY (`roleId`, `menuId`),
    KEY `idx_role_menu_menuId` (`menuId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='角色菜单关联表';

-- ----------------------------
-- Records of role_menu
-- ----------------------------
INSERT INTO `role_menu` (`roleId`, `menuId`) VALUES
(1, 100), (1, 101), (1, 102), (1, 103), (1, 104), (1, 105), (1, 106), (1, 107), (1, 108),
(1, 200), (1, 201), (1, 202),
(1, 300), (1, 301), (1, 302), (1, 303), (1, 304), (1, 305), (1, 306), (1, 307), (1, 308), (1, 309),
(1, 400), (1, 401), (1, 402), (1, 403),
(1, 500), (1, 501), (1, 502), (1, 503), (1, 504),
(1, 600), (1, 601), (1, 602),
(2, 300), (2, 301), (2, 302), (2, 303), (2, 304), (2, 305),
(4, 100), (4, 101), (4, 102), (4, 103), (4, 104), (4, 105);

-- ----------------------------
-- Table structure for goods (商品表)
-- ----------------------------
DROP TABLE IF EXISTS `goods`;
CREATE TABLE `goods` (
    `gsid` BIGINT NOT NULL AUTO_INCREMENT COMMENT '商品ID',
    `name` VARCHAR(64) DEFAULT NULL COMMENT '商品名称',
    `origin` VARCHAR(64) DEFAULT NULL COMMENT '产地',
    `producer` VARCHAR(64) DEFAULT NULL COMMENT '厂商',
    `unit` VARCHAR(64) DEFAULT NULL COMMENT '计量单位',
    `inprice` DOUBLE DEFAULT NULL COMMENT '进价',
    `outprice` DOUBLE DEFAULT NULL COMMENT '销售价',
    `goodstypeId` BIGINT DEFAULT NULL COMMENT '商品类型ID',
    `state` VARCHAR(8) DEFAULT '1' COMMENT '状态: 1-正常, 0-已删除',
    PRIMARY KEY (`gsid`),
    KEY `idx_goods_name` (`name`),
    KEY `idx_goods_goodstypeId` (`goodstypeId`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='商品表';

-- ----------------------------
-- Records of goods
-- ----------------------------
INSERT INTO `goods` (`gsid`, `name`, `origin`, `producer`, `unit`, `inprice`, `outprice`, `goodstypeId`, `state`) VALUES
(1, '水蜜桃', '湖北', '湖北水蜜桃', '斤', 12.12, 13, 1, '1'),
(2, '大鸭梨', '湖南', '湖南大鸭梨', '斤', 5.6, 6.2, 1, '1'),
(3, '猕猴桃', '深圳', '深圳猕猴桃', '斤', 11.9, 12.5, 1, '1'),
(4, '甜面酱', '湖北', '湖北甜面酱', '袋', 3.5, 4.5, 2, '1'),
(5, '可比克', '湖北', '湖北可比克', '袋', 3.9, 4.7, 3, '1'),
(6, '好吃点', '湖北', '湖北好吃点', '袋', 6, 6.5, 3, '1'),
(7, '哇哈哈', '武软', '武软一食堂', '斤', 9.8, 11, 5, '1'),
(8, '牛奶', '内蒙古', '武软二食堂', '瓶', 10, 12, 5, '1'),
(9, '煲仔饭', '武软', '武软三食堂', '碗', 10, 12, 4, '1');

-- ----------------------------
-- Table structure for goodstype (商品类型表)
-- ----------------------------
DROP TABLE IF EXISTS `goodstype`;
CREATE TABLE `goodstype` (
    `gid` BIGINT NOT NULL AUTO_INCREMENT COMMENT '商品类型ID',
    `name` VARCHAR(64) DEFAULT NULL COMMENT '商品类型名称',
    `state` VARCHAR(8) DEFAULT '1' COMMENT '状态: 1-正常, 0-虚拟删除',
    PRIMARY KEY (`gid`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='商品类型表';

-- ----------------------------
-- Records of goodstype
-- ----------------------------
INSERT INTO `goodstype` (`gid`, `name`, `state`) VALUES
(1, '水果', '1'),
(2, '调味品', '1'),
(3, '儿童食品', '1'),
(4, '生活', '1'),
(5, '饮料', '1'),
(6, '家家家', '0'),
(7, '啦啦啦', '1');

-- ----------------------------
-- Table structure for supplier (供应商/客户表)
-- ----------------------------
DROP TABLE IF EXISTS `supplier`;
CREATE TABLE `supplier` (
    `suid` BIGINT NOT NULL AUTO_INCREMENT COMMENT '供应商/客户ID',
    `name` VARCHAR(64) DEFAULT NULL COMMENT '名称',
    `address` VARCHAR(64) DEFAULT NULL COMMENT '地址',
    `contact` VARCHAR(64) DEFAULT NULL COMMENT '联系人',
    `tele` VARCHAR(64) DEFAULT NULL COMMENT '电话',
    `email` VARCHAR(64) DEFAULT NULL COMMENT '邮箱',
    `type` VARCHAR(8) DEFAULT NULL COMMENT '类型: 1-供应商, 2-客户',
    `state` VARCHAR(8) DEFAULT '1' COMMENT '状态: 1-正常, 0-被删除',
    PRIMARY KEY (`suid`),
    KEY `idx_supplier_type` (`type`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='供应商客户表';

-- ----------------------------
-- Records of supplier
-- ----------------------------
INSERT INTO `supplier` (`suid`, `name`, `address`, `contact`, `tele`, `email`, `type`, `state`) VALUES
(1, '天猫', '杭州', '马云', '1101101101', '146456544@qq.com', '1', '1'),
(2, '京东', '北京', '刘强东', '120120120', '21211@qq.com', '1', '1'),
(3, '武软', '武汉', '我我', '11111', '151@qq.com', '2', '1'),
(4, '北上北上', '北上', '你你', '62626', '61551@qq.com', '2', '1'),
(5, '苏宁', '武汉', '苏宁老板', '646462632632', '16161@qq.com', '1', '1'),
(6, '天', '杭州', '马云', '1101101101', '146456544@qq.com', '1', '1');

-- ----------------------------
-- Table structure for store (仓库表)
-- ----------------------------
DROP TABLE IF EXISTS `store`;
CREATE TABLE `store` (
    `sid` BIGINT NOT NULL AUTO_INCREMENT COMMENT '仓库ID',
    `name` VARCHAR(64) DEFAULT NULL COMMENT '仓库名称',
    `address` VARCHAR(255) DEFAULT NULL COMMENT '仓库地址',
    `empId` BIGINT DEFAULT NULL COMMENT '管理员ID',
    `state` VARCHAR(8) DEFAULT '1' COMMENT '状态: 1-正常, 0-停用',
    PRIMARY KEY (`sid`),
    KEY `idx_store_empId` (`empId`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='仓库表';

-- ----------------------------
-- Records of store
-- ----------------------------
INSERT INTO `store` (`sid`, `name`, `address`, `empId`, `state`) VALUES
(1, '生活用品', '武汉市洪山区生活用品仓库', 1, '1'),
(2, '零食仓库', '武汉市江汉区零食仓库', 2, '1'),
(3, '饮料用品', '武汉市武昌区饮料仓库', 3, '1'),
(4, '厨房用品', '武汉市汉阳区厨房仓库', 6, '1');

-- ----------------------------
-- Table structure for storedetail (库存详情表)
-- ----------------------------
DROP TABLE IF EXISTS `storedetail`;
CREATE TABLE `storedetail` (
    `sdid` BIGINT NOT NULL AUTO_INCREMENT COMMENT '库存详情ID',
    `storeId` BIGINT DEFAULT NULL COMMENT '仓库ID',
    `goodsId` BIGINT DEFAULT NULL COMMENT '商品ID',
    `num` BIGINT DEFAULT 0 COMMENT '库存数量',
    PRIMARY KEY (`sdid`),
    KEY `idx_storedetail_storeId` (`storeId`),
    KEY `idx_storedetail_goodsId` (`goodsId`),
    UNIQUE KEY `uk_store_goods` (`storeId`, `goodsId`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='库存详情表';

-- ----------------------------
-- Records of storedetail
-- ----------------------------
INSERT INTO `storedetail` (`sdid`, `storeId`, `goodsId`, `num`) VALUES
(3, 3, 5, 100),
(6, 3, 1, 280),
(7, 3, 6, 10),
(8, 3, 2, 700),
(9, 3, 4, 70),
(10, 2, 1, 100),
(11, 2, 4, 100),
(12, 2, 6, 5),
(13, 1, 4, 900),
(14, 1, 2, 900),
(15, 2, 3, 900),
(16, 2, 5, 100);

-- ----------------------------
-- Table structure for storeoper (仓库操作记录表)
-- ----------------------------
DROP TABLE IF EXISTS `storeoper`;
CREATE TABLE `storeoper` (
    `soid` BIGINT NOT NULL AUTO_INCREMENT COMMENT '操作记录ID',
    `empId` BIGINT DEFAULT NULL COMMENT '员工ID',
    `opertime` DATETIME DEFAULT NULL COMMENT '操作时间',
    `storeId` BIGINT DEFAULT NULL COMMENT '仓库ID',
    `goodsId` BIGINT DEFAULT NULL COMMENT '商品ID',
    `num` BIGINT DEFAULT NULL COMMENT '数量',
    `type` VARCHAR(8) DEFAULT NULL COMMENT '类型: 1-入库, 2-出库',
    PRIMARY KEY (`soid`),
    KEY `idx_storeoper_empId` (`empId`),
    KEY `idx_storeoper_storeId` (`storeId`),
    KEY `idx_storeoper_goodsId` (`goodsId`),
    KEY `idx_storeoper_opertime` (`opertime`)
) ENGINE=InnoDB AUTO_INCREMENT=26 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='仓库操作记录表';

-- ----------------------------
-- Records of storeoper
-- ----------------------------
INSERT INTO `storeoper` (`soid`, `empId`, `opertime`, `storeId`, `goodsId`, `num`, `type`) VALUES
(1, 11, '2018-09-20 06:03:00', 3, 5, 100, '1'),
(4, 11, '2018-09-22 01:42:00', 3, 1, 100, '1'),
(5, 11, '2018-09-22 01:49:41', 3, 1, 200, '1'),
(6, 1, '2018-09-20 06:47:31', 3, 6, 300, '1'),
(7, 1, '2018-09-20 06:52:53', 3, 2, 900, '1'),
(8, 1, '2018-09-22 01:33:59', 3, 6, 280, '2'),
(9, 1, '2018-09-22 01:34:49', 3, 6, 260, '2'),
(10, 1, '2018-09-22 01:37:57', 3, 6, 240, '2'),
(11, 1, '2018-09-23 01:38:37', 3, 2, 800, '2'),
(12, 6, '2018-09-26 18:07:59', 3, 1, 300, '1'),
(13, 6, '2018-09-26 18:08:04', 3, 4, 100, '1'),
(14, 6, '2018-09-26 18:13:25', 2, 1, 100, '1'),
(15, 6, '2018-09-26 18:14:14', 2, 4, 100, '1'),
(16, 6, '2018-09-26 18:15:24', 2, 6, 300, '1'),
(17, 1, '2018-09-26 18:27:17', 1, 4, 900, '1'),
(18, 1, '2018-09-26 18:29:35', 1, 2, 900, '1'),
(19, 1, '2018-10-01 18:30:30', 3, 4, 10, '2'),
(20, 1, '2018-10-01 11:23:36', 3, 1, 20, '2'),
(21, 1, '2018-10-01 11:23:40', 3, 4, 10, '2'),
(22, 2, '2018-10-02 09:26:05', 2, 3, 900, '1'),
(23, 1, '2018-10-03 20:39:39', 3, 2, 700, '2'),
(24, 1, '2018-10-03 20:39:48', 3, 4, 70, '2'),
(25, 1, '2018-10-08 10:57:15', 2, 5, 100, '1');

-- ----------------------------
-- Table structure for orders (订单表)
-- ----------------------------
DROP TABLE IF EXISTS `orders`;
CREATE TABLE `orders` (
    `oid` BIGINT NOT NULL AUTO_INCREMENT COMMENT '订单ID',
    `createtime` DATETIME DEFAULT NULL COMMENT '创建时间',
    `checktime` DATETIME DEFAULT NULL COMMENT '审核时间',
    `starttime` DATETIME DEFAULT NULL COMMENT '确认日期',
    `endtime` DATETIME DEFAULT NULL COMMENT '结束日期',
    `type` VARCHAR(8) DEFAULT NULL COMMENT '类型: 1-采购, 2-销售',
    `createer` BIGINT DEFAULT NULL COMMENT '创建人',
    `checker` BIGINT DEFAULT NULL COMMENT '审核人',
    `starter` BIGINT DEFAULT NULL COMMENT '确认人',
    `ender` BIGINT DEFAULT NULL COMMENT '结束人',
    `supplierId` BIGINT DEFAULT NULL COMMENT '供应商/客户ID',
    `totalmoney` DOUBLE DEFAULT 0 COMMENT '总金额',
    `state` VARCHAR(8) DEFAULT '0' COMMENT '状态: 采购-0未审核/1已审核/2已确认/3已入库; 销售-0未出库/1已出库',
    `waybillsn` VARCHAR(64) DEFAULT NULL COMMENT '运单号',
    PRIMARY KEY (`oid`),
    KEY `idx_orders_type` (`type`),
    KEY `idx_orders_state` (`state`),
    KEY `idx_orders_createer` (`createer`),
    KEY `idx_orders_supplierId` (`supplierId`),
    KEY `idx_orders_createtime` (`createtime`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='订单表';

-- ----------------------------
-- Records of orders
-- ----------------------------
INSERT INTO `orders` (`oid`, `createtime`, `checktime`, `starttime`, `endtime`, `type`, `createer`, `checker`, `starter`, `ender`, `supplierId`, `totalmoney`, `state`, `waybillsn`) VALUES
(2, '2018-09-19 00:00:00', NULL, NULL, NULL, '1', 1, NULL, NULL, NULL, 1, 1602, '0', '1'),
(3, '2018-09-20 00:00:00', '2018-09-19 19:54:34', NULL, NULL, '1', 1, 1, NULL, NULL, 2, 231, '1', '3'),
(4, '2018-09-21 00:00:00', NULL, NULL, '2018-09-26 16:08:27', '2', 1, NULL, NULL, 1, 2, 750, '1', '5'),
(5, '2018-09-22 00:00:00', '2018-09-21 11:28:02', '2018-09-26 17:10:03', '2018-09-26 18:15:24', '1', 1, 1, 1, 6, 1, 6840, '3', '4'),
(6, '2018-09-26 18:07:07', '2018-09-26 18:07:21', '2018-09-26 18:07:48', '2018-09-26 18:14:14', '1', 6, 6, 6, 6, 5, 1562, '3', NULL),
(7, '2018-09-26 18:16:40', '2018-10-02 09:25:48', '2018-10-02 09:25:56', '2018-10-02 09:26:05', '1', 2, 2, 2, 2, 5, 13050, '3', NULL),
(8, '2018-09-26 18:19:11', '2018-09-30 20:29:12', NULL, NULL, '1', 2, 1, NULL, NULL, 2, 71400, '1', NULL),
(9, '2018-09-26 18:26:36', '2018-09-26 18:26:50', '2018-09-26 18:26:55', '2018-09-26 18:29:35', '1', 1, 1, 1, 1, 6, 8190, '3', NULL),
(10, '2018-10-01 23:11:06', NULL, NULL, NULL, '1', 1, NULL, NULL, NULL, 6, 4674, '0', NULL),
(11, '2018-10-03 19:32:37', '2018-10-03 19:53:01', NULL, '2018-10-03 20:39:48', '2', 1, 1, NULL, 1, 2, 603, '1', NULL),
(12, '2018-10-08 10:56:38', '2018-10-08 10:56:52', '2018-10-08 10:57:03', '2018-10-08 10:57:15', '1', 1, 1, 1, 1, 5, 390, '3', NULL);

-- ----------------------------
-- Table structure for ordersdetail (订单详情表)
-- ----------------------------
DROP TABLE IF EXISTS `ordersdetail`;
CREATE TABLE `ordersdetail` (
    `odid` BIGINT NOT NULL AUTO_INCREMENT COMMENT '订单详情ID',
    `goodsId` BIGINT DEFAULT NULL COMMENT '商品ID',
    `goodsname` VARCHAR(64) DEFAULT NULL COMMENT '商品名称',
    `price` DOUBLE DEFAULT NULL COMMENT '商品价格',
    `num` BIGINT DEFAULT NULL COMMENT '商品数量',
    `money` DOUBLE DEFAULT NULL COMMENT '金额',
    `endtime` DATETIME DEFAULT NULL COMMENT '结束日期',
    `ender` BIGINT DEFAULT NULL COMMENT '结束人',
    `storeId` BIGINT DEFAULT NULL COMMENT '仓库ID',
    `state` VARCHAR(8) DEFAULT '0' COMMENT '状态: 采购-0未入库/1已入库; 销售-0未出库/1已出库',
    `ordersId` BIGINT DEFAULT NULL COMMENT '订单ID',
    PRIMARY KEY (`odid`),
    KEY `idx_ordersdetail_goodsId` (`goodsId`),
    KEY `idx_ordersdetail_ordersId` (`ordersId`),
    KEY `idx_ordersdetail_storeId` (`storeId`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='订单详情表';

-- ----------------------------
-- Records of ordersdetail
-- ----------------------------
INSERT INTO `ordersdetail` (`odid`, `goodsId`, `goodsname`, `price`, `num`, `money`, `endtime`, `ender`, `storeId`, `state`, `ordersId`) VALUES
(1, 1, '水蜜桃', 12.12, 100, 1212, '2018-09-19 16:52:53', 12, 3, '1', 2),
(2, 5, '可比克', 3.9, 100, 390, '2018-09-19 16:37:19', 12, 3, '1', 2),
(3, 2, '大鸭梨', 5.6, 20, 112, NULL, NULL, NULL, '1', 3),
(4, 3, '猕猴桃', 11.9, 10, 119, NULL, NULL, NULL, '1', 3),
(5, 2, '大鸭梨', 6.2, 100, 620, '2018-09-26 16:08:27', 1, 3, '1', 4),
(6, 6, '好吃点', 6.5, 20, 130, '2018-09-21 11:55:58', 1, 3, '1', 4),
(7, 6, '好吃点', 6, 300, 1800, '2018-09-26 18:15:24', 6, 2, '1', 5),
(8, 2, '大鸭梨', 5.6, 900, 5040, '2018-09-21 11:34:49', 1, 3, '1', 5),
(9, 1, '水蜜桃', 12.12, 100, 1212, '2018-09-26 18:13:25', 6, 2, '1', 6),
(10, 4, '甜面酱', 3.5, 100, 350, '2018-09-26 18:14:14', 6, 2, '1', 6),
(11, 5, '可比克', 3.9, 600, 2340, NULL, NULL, NULL, '1', 7),
(12, 3, '猕猴桃', 11.9, 900, 10710, '2018-10-02 09:26:05', 2, 2, '1', 7),
(13, 3, '猕猴桃', 11.9, 6000, 71400, NULL, NULL, NULL, '1', 8),
(14, 4, '甜面酱', 3.5, 900, 3150, '2018-09-26 18:27:17', 1, 1, '1', 9),
(15, 2, '大鸭梨', 5.6, 900, 5040, '2018-09-26 18:29:35', 1, 1, '1', 9),
(16, 5, '可比克', 3.9, 100, 390, NULL, NULL, NULL, '0', 10),
(17, 3, '猕猴桃', 11.9, 360, 4284, NULL, NULL, NULL, '0', 10),
(18, 2, '大鸭梨', 6.2, 90, 558, '2018-10-03 20:39:39', 1, 3, '1', 11),
(19, 4, '甜面酱', 4.5, 10, 45, '2018-10-03 20:39:48', 1, 3, '1', 11),
(20, 5, '可比克', 3.9, 100, 390, '2018-10-08 10:57:15', 1, 2, '1', 12);

-- ----------------------------
-- Table structure for returnorders (退货订单表)
-- ----------------------------
DROP TABLE IF EXISTS `returnorders`;
CREATE TABLE `returnorders` (
    `roid` BIGINT NOT NULL AUTO_INCREMENT COMMENT '退货订单ID',
    `createtime` DATETIME DEFAULT NULL COMMENT '创建日期',
    `checktime` DATETIME DEFAULT NULL COMMENT '审核日期',
    `endtime` DATETIME DEFAULT NULL COMMENT '结束日期',
    `type` VARCHAR(8) DEFAULT NULL COMMENT '类型: 1-采购退货, 2-销售退货',
    `createer` BIGINT DEFAULT NULL COMMENT '下单员ID',
    `checker` BIGINT DEFAULT NULL COMMENT '审核员ID',
    `ender` BIGINT DEFAULT NULL COMMENT '确认人ID',
    `supplierId` BIGINT DEFAULT NULL COMMENT '供应商/客户ID',
    `totalmoney` DOUBLE DEFAULT 0 COMMENT '合计金额',
    `state` VARCHAR(8) DEFAULT '0' COMMENT '状态: 0-未审核, 1-已审核, 2-已退货',
    `waybillsn` VARCHAR(64) DEFAULT NULL COMMENT '运单号',
    `ordersOid` BIGINT DEFAULT NULL COMMENT '原订单编号',
    PRIMARY KEY (`roid`),
    KEY `idx_returnorders_type` (`type`),
    KEY `idx_returnorders_state` (`state`),
    KEY `idx_returnorders_ordersOid` (`ordersOid`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='退货订单表';

-- ----------------------------
-- Records of returnorders
-- ----------------------------
INSERT INTO `returnorders` (`roid`, `createtime`, `checktime`, `endtime`, `type`, `createer`, `checker`, `ender`, `supplierId`, `totalmoney`, `state`, `waybillsn`, `ordersOid`) VALUES
(1, '2018-09-30 00:00:00', '2018-10-01 00:00:00', '2018-10-01 00:00:00', '1', 2, 1, 1, 6, 56, '2', NULL, 9),
(2, '2018-09-27 00:00:00', '2018-09-30 00:00:00', '2018-10-01 00:00:00', '1', 3, 2, 2, 1, 120, '2', NULL, 5),
(3, '2018-10-01 00:00:00', '2018-10-01 00:00:00', '2018-10-01 00:00:00', '1', 1, 1, 1, 5, 35, '2', NULL, 6),
(4, '2018-10-01 00:00:00', '2018-10-01 00:00:00', NULL, '1', 1, 1, NULL, 5, 121.20, '1', NULL, 6),
(5, '2018-10-01 00:00:00', '2018-10-01 00:00:00', '2018-10-01 00:00:00', '1', 1, 1, 1, 5, 277.40, '2', NULL, 6);

-- ----------------------------
-- Table structure for returnordersdetail (退货订单详情表)
-- ----------------------------
DROP TABLE IF EXISTS `returnordersdetail`;
CREATE TABLE `returnordersdetail` (
    `rosd` BIGINT NOT NULL AUTO_INCREMENT COMMENT '退货订单详情ID',
    `goodsId` BIGINT DEFAULT NULL COMMENT '商品ID',
    `goodsName` VARCHAR(64) DEFAULT NULL COMMENT '商品名称',
    `price` DOUBLE DEFAULT NULL COMMENT '价格',
    `num` BIGINT DEFAULT NULL COMMENT '数量',
    `money` DOUBLE DEFAULT NULL COMMENT '金额',
    `endtime` DATETIME DEFAULT NULL COMMENT '结束日期',
    `ender` BIGINT DEFAULT NULL COMMENT '结束人ID',
    `storeId` BIGINT DEFAULT NULL COMMENT '仓库ID',
    `state` VARCHAR(8) DEFAULT '0' COMMENT '状态: 采购-0未出库/1已出库',
    `returnordersId` BIGINT DEFAULT NULL COMMENT '退货订单ID',
    PRIMARY KEY (`rosd`),
    KEY `idx_returnordersdetail_goodsId` (`goodsId`),
    KEY `idx_returnordersdetail_returnordersId` (`returnordersId`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='退货订单详情表';

-- ----------------------------
-- Records of returnordersdetail
-- ----------------------------
INSERT INTO `returnordersdetail` (`rosd`, `goodsId`, `goodsName`, `price`, `num`, `money`, `endtime`, `ender`, `storeId`, `state`, `returnordersId`) VALUES
(1, 2, '大鸭梨', 5.6, 20, 56, '2018-10-01 00:00:00', 1, NULL, '1', 1),
(2, 6, '好吃点', 6, 20, 120, '2018-10-01 00:00:00', 2, 2, '1', 2),
(3, 4, '甜面酱', 3.5, 10, 35, '2018-10-01 00:00:00', 1, 3, '1', 3),
(4, 1, '水蜜桃', 12.12, 10, 121.20, NULL, NULL, NULL, '0', 4),
(5, 1, '水蜜桃', 12.12, 20, 242.40, '2018-10-01 00:00:00', 1, 3, '1', 5),
(6, 4, '甜面酱', 3.5, 10, 35, '2018-10-01 00:00:00', 1, 3, '1', 5);

-- ----------------------------
-- Table structure for mylog (操作日志表)
-- ----------------------------
DROP TABLE IF EXISTS `mylog`;
CREATE TABLE `mylog` (
    `logId` VARCHAR(64) NOT NULL COMMENT '日志ID',
    `type` VARCHAR(16) DEFAULT NULL COMMENT '日志类型',
    `title` VARCHAR(64) DEFAULT NULL COMMENT '日志标题',
    `remoteAddr` VARCHAR(64) DEFAULT NULL COMMENT '请求地址',
    `requestUri` VARCHAR(255) DEFAULT NULL COMMENT '请求URL',
    `method` VARCHAR(16) DEFAULT NULL COMMENT '提交方式',
    `params` VARCHAR(500) DEFAULT NULL COMMENT '提交参数',
    `exception` VARCHAR(255) DEFAULT NULL COMMENT '异常信息',
    `operateDate` DATETIME DEFAULT NULL COMMENT '操作时间',
    `timeout` VARCHAR(32) DEFAULT NULL COMMENT '耗时',
    `userId` VARCHAR(32) DEFAULT NULL COMMENT '用户ID',
    PRIMARY KEY (`logId`),
    KEY `idx_mylog_operateDate` (`operateDate`),
    KEY `idx_mylog_userId` (`userId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='操作日志表';

-- ----------------------------
-- Table structure for waybill (运单表)
-- ----------------------------
DROP TABLE IF EXISTS `waybill`;
CREATE TABLE `waybill` (
    `sn` BIGINT NOT NULL AUTO_INCREMENT COMMENT '运单号',
    `userId` BIGINT DEFAULT NULL COMMENT '用户ID',
    `toaddress` VARCHAR(128) DEFAULT NULL COMMENT '收货地址',
    `addressperson` VARCHAR(64) DEFAULT NULL COMMENT '收货人',
    `tele` VARCHAR(64) DEFAULT NULL COMMENT '收货人电话',
    `info` VARCHAR(500) DEFAULT NULL COMMENT '运单详情',
    `state` VARCHAR(8) DEFAULT '0' COMMENT '运单状态',
    `createtime` DATETIME DEFAULT NULL COMMENT '创建时间',
    `isuse` VARCHAR(8) DEFAULT '1' COMMENT '状态: 1-正常, 0-被删除',
    `ordersId` BIGINT DEFAULT NULL COMMENT '订单ID',
    PRIMARY KEY (`sn`),
    KEY `idx_waybill_ordersId` (`ordersId`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='运单表';

-- ----------------------------
-- Records of waybill
-- ----------------------------
INSERT INTO `waybill` (`sn`, `userId`, `toaddress`, `addressperson`, `tele`, `info`, `state`, `createtime`, `isuse`, `ordersId`) VALUES
(1, 1, '武软一食堂', '帅建', '16216264', '鸭子十只，鸡子十只，鸡爪十只', '准备发货', '2018-09-26 14:03:56', '1', NULL),
(2, 6, '武软二食堂', '李兄', '1651561626', '牛肉十斤，立马送到', '正在派送', '2018-09-26 00:00:00', '1', NULL),
(3, 16, '武软二食堂', NULL, '1651561626', '牛肉十斤，立马送到', '0', '2018-09-26 00:00:00', '0', NULL),
(4, 26, '武软二食堂', NULL, '6461112', '牛肉十斤，空运', '0', '2018-09-26 00:00:00', '0', NULL),
(5, 9527, '武软三食堂', '李永健', '4662626', '牛肉十斤，空运给永健吃', '正在派送', '2018-09-26 00:00:00', '1', NULL);

-- ----------------------------
-- Table structure for waybilldetail (运单详情表)
-- ----------------------------
DROP TABLE IF EXISTS `waybilldetail`;
CREATE TABLE `waybilldetail` (
    `wdid` BIGINT NOT NULL AUTO_INCREMENT COMMENT '运单详情ID',
    `waybillsn` VARCHAR(64) DEFAULT NULL COMMENT '运单号',
    `goodsId` BIGINT DEFAULT NULL COMMENT '商品ID',
    `num` BIGINT DEFAULT NULL COMMENT '数量',
    PRIMARY KEY (`wdid`),
    KEY `idx_waybilldetail_waybillsn` (`waybillsn`),
    KEY `idx_waybilldetail_goodsId` (`goodsId`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='运单详情表';

-- ----------------------------
-- Records of waybilldetail
-- ----------------------------
INSERT INTO `waybilldetail` (`wdid`, `waybillsn`, `goodsId`, `num`) VALUES
(1, '5', NULL, NULL);

-- 恢复设置
SET FOREIGN_KEY_CHECKS = 1;

-- 注意: 所有员工默认密码为 123456 (BCrypt加密)
-- 登录后建议及时修改密码