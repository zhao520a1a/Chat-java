-- --------------------------------------------------------
-- 主机:                           localhost
-- 服务器版本:                        5.7.16 - MySQL Community Server (GPL)
-- 服务器操作系统:                      Win64
-- HeidiSQL 版本:                  9.3.0.4984
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;

-- 导出 jsp_shop 的数据库结构
CREATE DATABASE IF NOT EXISTS `jsp_shop` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `jsp_shop`;


-- 导出  表 jsp_shop.ad_users 结构
CREATE TABLE IF NOT EXISTS `ad_users` (
  `id` int(11) NOT NULL,
  `username` varchar(50) NOT NULL,
  `password` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 正在导出表  jsp_shop.ad_users 的数据：~1 rows (大约)
/*!40000 ALTER TABLE `ad_users` DISABLE KEYS */;
INSERT INTO `ad_users` (`id`, `username`, `password`) VALUES
	(1, 'admin', 'admin');
/*!40000 ALTER TABLE `ad_users` ENABLE KEYS */;


-- 导出  表 jsp_shop.goods 结构
CREATE TABLE IF NOT EXISTS `goods` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `goodsClassId` int(11) NOT NULL,
  `goodname` varchar(20) NOT NULL,
  `provider` varchar(150) NOT NULL,
  `goodNo` varchar(30) NOT NULL,
  `content` varchar(3000) DEFAULT NULL,
  `price` float NOT NULL,
  `amount` int(11) NOT NULL,
  `leave_amount` int(11) NOT NULL,
  `regtime` date NOT NULL,
  `img` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_goods_goodsClass` (`goodsClassId`),
  CONSTRAINT `FK_goods_goodsClass` FOREIGN KEY (`goodsClassId`) REFERENCES `goodsclass` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8;

-- 正在导出表  jsp_shop.goods 的数据：~20 rows (大约)
/*!40000 ALTER TABLE `goods` DISABLE KEYS */;
INSERT INTO `goods` (`id`, `goodsClassId`, `goodname`, `provider`, `goodNo`, `content`, `price`, `amount`, `leave_amount`, `regtime`, `img`) VALUES
	(1, 1, '男士休闲鞋', '江南皮革厂', 's-x-1', '   光滑,褐色', 499, 100, 100, '2016-11-05', 'manShoes_pic1.jpg'),
	(2, 1, '男士休闲鞋', '江南皮革厂', 's-x-2', '光滑,黑色', 499, 100, 16, '2016-11-05', 'manShoes_pic2.jpg'),
	(3, 1, '男士休闲鞋', '江南皮革厂', 's-x-3', '光滑,深褐色', 499, 100, 20, '2016-11-05', 'manShoes_pic3.jpg'),
	(4, 1, '男士商务鞋', '江南皮革厂', 's-s-1', '绒面,褐色', 499, 100, 19, '2016-11-05', 'manShoes_pic4.jpg'),
	(5, 1, '男士商务鞋', '江南皮革厂', 's-s-2', '绒面,米色', 499, 100, 20, '2016-11-05', 'manShoes_pic5.jpg'),
	(6, 1, '男士商务鞋', '江南皮革厂', 's-s-3', '绒面,蓝色', 499, 100, 20, '2016-11-05', 'manShoes_pic6.jpg'),
	(7, 2, '男士召唤者外套', '江南服装厂', 'm-c-2', ' ', 300, 100, 20, '2016-11-05', 'manCoat_pic1.png'),
	(8, 2, '男士levis外套', '江南服装厂', 'm-c-3', ' ', 300, 100, 17, '2016-11-05', 'manCoat_pic2.png'),
	(9, 2, '男士大力神外套', '江南服装厂', 'm-c-3', ' ', 300, 100, 18, '2016-11-05', 'manCoat_pic3.png'),
	(10, 3, '女士印度华服', '江南服装厂', 'w-c-2', ' ', 300, 100, 19, '2016-11-05', 'womanCoat_pic1.jpg'),
	(11, 3, '女士粉红休闲衣', '江南服装厂', 'w-c-3', ' ', 300, 100, 20, '2016-11-05', 'womanCoat_pic2.jpg'),
	(12, 3, '女士居家睡衣', '江南服装厂', 'w-c-1', ' ', 300, 100, 20, '2016-11-05', 'womanCoat_pic3.jpg'),
	(13, 5, '男士休闲包', '江南服装厂', 'm-k-2', ' ', 299, 100, 20, '2016-11-05', 'manBag_pic1.jpg'),
	(14, 5, '男士商务包', '江南服装厂', 'm-k-1', ' ', 299, 100, 18, '2016-11-05', 'manBag_pic2.jpg'),
	(15, 4, '女士高贵挎包', '江南服装厂', 'w-k-4', ' ', 299, 100, 17, '2016-11-05', 'womanBag_pic1.jpg'),
	(16, 4, '女士简约挎包', '江南服装厂', 'w-k-2', ' ', 299, 100, 17, '2016-11-05', 'womanBag_pic2.jpg'),
	(17, 4, '女士洁白挎包', '江南服装厂', 'w-k-3', ' ', 299, 100, 20, '2016-11-05', 'womanBag_pic3.jpg'),
	(18, 4, '女士艳丽挎包', '江南服装厂', 'w-k-1', ' ', 299, 100, 20, '2016-11-05', 'womanBag_pic4.jpg'),
	(19, 1, '男士运动鞋', '江南皮革厂', 's-y-2', ' ', 300, 100, 19, '2016-11-05', 'manShoes_pic7.png'),
	(20, 1, '男士运动鞋', '江南皮革厂', 's-y-1', ' ', 300, 100, 20, '2016-11-05', 'manShoes_pic8.png');
/*!40000 ALTER TABLE `goods` ENABLE KEYS */;


-- 导出  表 jsp_shop.goodsclass 结构
CREATE TABLE IF NOT EXISTS `goodsclass` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `classname` varchar(30) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

-- 正在导出表  jsp_shop.goodsclass 的数据：~5 rows (大约)
/*!40000 ALTER TABLE `goodsclass` DISABLE KEYS */;
INSERT INTO `goodsclass` (`id`, `classname`) VALUES
	(1, '男鞋'),
	(2, '男士外套'),
	(3, '女士外套'),
	(4, '女士挎包'),
	(5, '男士挎包');
/*!40000 ALTER TABLE `goodsclass` ENABLE KEYS */;


-- 导出  表 jsp_shop.indent 结构
CREATE TABLE IF NOT EXISTS `indent` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `indentNo` varchar(20) NOT NULL,
  `userId` int(11) NOT NULL,
  `totalPrice` float DEFAULT NULL,
  `content` varchar(30) DEFAULT NULL,
  `isPayoff` int(11) DEFAULT '0',
  `isSales` int(11) DEFAULT '0',
  `submitTime` timestamp NULL DEFAULT NULL,
  `consignmentTime` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_indent_users` (`userId`),
  CONSTRAINT `FK_indent_users` FOREIGN KEY (`userId`) REFERENCES `users` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=61 DEFAULT CHARSET=utf8;

-- 正在导出表  jsp_shop.indent 的数据：~7 rows (大约)
/*!40000 ALTER TABLE `indent` DISABLE KEYS */;
INSERT INTO `indent` (`id`, `indentNo`, `userId`, `totalPrice`, `content`, `isPayoff`, `isSales`, `submitTime`, `consignmentTime`) VALUES
	(54, 'HYD-19-53', 19, 299, '   ', 0, 0, '2016-12-15 16:11:33', NULL),
	(55, 'HYD-1-54', 1, 798, '   ', 0, 0, '2016-12-16 09:02:24', NULL),
	(56, 'HYD-22-55', 22, 798, '   ', 0, 0, '2016-12-16 09:44:53', NULL),
	(57, 'HYD-1-56', 1, 798, '   ', 0, 0, '2016-12-18 20:33:56', NULL),
	(58, 'HYD-1-57', 1, 599, '   ', 0, 0, '2016-12-18 20:36:36', NULL),
	(59, 'HYD-1-58', 1, 600, '   ', 0, 0, '2016-12-18 20:36:57', NULL),
	(60, 'HYD-1-59', 1, 598, '   ', 0, 0, '2016-12-21 10:13:04', NULL);
/*!40000 ALTER TABLE `indent` ENABLE KEYS */;


-- 导出  表 jsp_shop.indentlist 结构
CREATE TABLE IF NOT EXISTS `indentlist` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `indentId` int(11) NOT NULL,
  `goodId` int(11) NOT NULL,
  `amount` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_indentList_indent` (`indentId`),
  KEY `FK_indentList_goods` (`goodId`),
  CONSTRAINT `FK_indentList_goods` FOREIGN KEY (`goodId`) REFERENCES `goods` (`id`),
  CONSTRAINT `FK_indentList_indent` FOREIGN KEY (`indentId`) REFERENCES `indent` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=51 DEFAULT CHARSET=utf8;

-- 正在导出表  jsp_shop.indentlist 的数据：~12 rows (大约)
/*!40000 ALTER TABLE `indentlist` DISABLE KEYS */;
INSERT INTO `indentlist` (`id`, `indentId`, `goodId`, `amount`) VALUES
	(39, 54, 14, 1),
	(40, 55, 16, 1),
	(41, 55, 2, 1),
	(42, 56, 4, 1),
	(43, 56, 15, 1),
	(44, 57, 2, 1),
	(45, 57, 16, 1),
	(46, 58, 9, 1),
	(47, 58, 15, 1),
	(48, 59, 8, 2),
	(49, 60, 15, 1),
	(50, 60, 14, 1);
/*!40000 ALTER TABLE `indentlist` ENABLE KEYS */;


-- 导出  表 jsp_shop.users 结构
CREATE TABLE IF NOT EXISTS `users` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(20) NOT NULL,
  `password` varchar(20) NOT NULL,
  `realname` varchar(20) DEFAULT NULL,
  `sex` varchar(2) DEFAULT NULL,
  `email` varchar(50) DEFAULT NULL,
  `phone` varchar(25) DEFAULT NULL,
  `address` varchar(150) DEFAULT NULL,
  `regTime` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=utf8;

-- 正在导出表  jsp_shop.users 的数据：~5 rows (大约)
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` (`id`, `username`, `password`, `realname`, `sex`, `email`, `phone`, `address`, `regTime`) VALUES
	(1, '123', '123', '李明', 'M', '21212@qq.com', '10086', '哈尔滨', '2016-12-15 14:12:24'),
	(19, '555', '123', '大蛇丸', 'M', '21212@qq.com', '10086', '哈尔滨', '2016-12-15 14:12:24'),
	(21, 'ss', '684139', 'zhao', 'M', '8654@.com', '6841396', 'aaaa', '2016-12-16 09:42:27'),
	(22, 'mmh', '960801mmh', '马明慧', 'M', '2063505398@qq.com', '18846431096', '哈理工工区', '2016-12-16 09:42:38');
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
