-- --------------------------------------------------------
-- Host:                         127.0.0.1
-- Server version:               5.6.21-log - MySQL Community Server (GPL)
-- Server OS:                    Win64
-- HeidiSQL version:             7.0.0.4053
-- Date/time:                    2015-04-20 12:13:10
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!40014 SET FOREIGN_KEY_CHECKS=0 */;

-- Dumping database structure for shooting_stars_database
DROP DATABASE IF EXISTS `shooting_stars_database`;
CREATE DATABASE IF NOT EXISTS `shooting_stars_database` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `shooting_stars_database`;


-- Dumping structure for table shooting_stars_database.chat
DROP TABLE IF EXISTS `chat`;
CREATE TABLE IF NOT EXISTS `chat` (
  `chatId` int(10) NOT NULL AUTO_INCREMENT,
  `user1Id` int(10) DEFAULT NULL,
  `user2Id` int(10) DEFAULT NULL,
  PRIMARY KEY (`chatId`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- Dumping data for table shooting_stars_database.chat: ~0 rows (approximately)
DELETE FROM `chat`;
/*!40000 ALTER TABLE `chat` DISABLE KEYS */;
INSERT INTO `chat` (`chatId`, `user1Id`, `user2Id`) VALUES
	(1, 14, 2),
	(2, 2, 11),
	(3, 2, 15),
	(4, 2, 8);
/*!40000 ALTER TABLE `chat` ENABLE KEYS */;


-- Dumping structure for table shooting_stars_database.considered_wish
DROP TABLE IF EXISTS `considered_wish`;
CREATE TABLE IF NOT EXISTS `considered_wish` (
  `wishId` int(10) NOT NULL,
  `userId` int(10) NOT NULL,
  PRIMARY KEY (`wishId`,`userId`),
  CONSTRAINT `FK_considered_wish_wish` FOREIGN KEY (`wishId`) REFERENCES `wish` (`wishId`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Dumping data for table shooting_stars_database.considered_wish: ~2 rows (approximately)
DELETE FROM `considered_wish`;
/*!40000 ALTER TABLE `considered_wish` DISABLE KEYS */;
INSERT INTO `considered_wish` (`wishId`, `userId`) VALUES
	(1, 2),
	(29, 2),
	(41, 2);
/*!40000 ALTER TABLE `considered_wish` ENABLE KEYS */;


-- Dumping structure for table shooting_stars_database.fulfilled_wish
DROP TABLE IF EXISTS `fulfilled_wish`;
CREATE TABLE IF NOT EXISTS `fulfilled_wish` (
  `wishId` int(10) NOT NULL DEFAULT '0',
  `userId` int(10) DEFAULT NULL,
  `wishStatusId` int(10) DEFAULT '3',
  `date` date DEFAULT NULL,
  PRIMARY KEY (`wishId`),
  CONSTRAINT `FK_fulfilled_wish_wish` FOREIGN KEY (`wishId`) REFERENCES `wish` (`wishId`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Dumping data for table shooting_stars_database.fulfilled_wish: ~6 rows (approximately)
DELETE FROM `fulfilled_wish`;
/*!40000 ALTER TABLE `fulfilled_wish` DISABLE KEYS */;
INSERT INTO `fulfilled_wish` (`wishId`, `userId`, `wishStatusId`, `date`) VALUES
	(7, 14, 2, '2015-03-19'),
	(17, 14, 2, '2015-03-19'),
	(20, 2, 2, '2015-04-09'),
	(21, 2, 3, NULL),
	(27, 14, 3, NULL),
	(36, 2, 2, '2015-04-12');
/*!40000 ALTER TABLE `fulfilled_wish` ENABLE KEYS */;


-- Dumping structure for table shooting_stars_database.message
DROP TABLE IF EXISTS `message`;
CREATE TABLE IF NOT EXISTS `message` (
  `messageId` int(10) NOT NULL AUTO_INCREMENT,
  `chatId` int(10) DEFAULT NULL,
  `message` longtext,
  `date` date DEFAULT NULL,
  `time` time DEFAULT NULL,
  `sender` int(11) DEFAULT NULL,
  `isRead` tinyint(4) DEFAULT '0',
  PRIMARY KEY (`messageId`)
) ENGINE=InnoDB AUTO_INCREMENT=37 DEFAULT CHARSET=utf8;

-- Dumping data for table shooting_stars_database.message: ~30 rows (approximately)
DELETE FROM `message`;
/*!40000 ALTER TABLE `message` DISABLE KEYS */;
INSERT INTO `message` (`messageId`, `chatId`, `message`, `date`, `time`, `sender`, `isRead`) VALUES
	(1, 1, 'bbjj', '2015-04-12', '11:02:26', 2, 1),
	(2, 1, 'hjghdjghdhkg', '2015-04-12', '18:13:11', 2, 1),
	(3, 1, 'fjgfjgjg', '2015-04-12', '18:13:27', 2, 1),
	(4, 1, 'hgjkfdhgfdg', '2015-04-12', '18:14:15', 14, 1),
	(5, 1, 'hgghfgkfdg', '2015-04-12', '18:17:06', 2, 1),
	(6, 1, 'jghjghjgj', '2015-04-12', '18:17:19', 2, 1),
	(7, 1, 'gjhgjhgjyj', '2015-04-12', '18:19:22', 14, 1),
	(8, 1, 'ghgb', '2015-04-12', '18:20:54', 2, 1),
	(9, 1, 'cancel application', '2015-04-12', '21:07:08', 2, 1),
	(10, 1, 'application was made', '2015-04-12', '21:08:30', 2, 1),
	(11, 1, 'application was accepted', '2015-04-12', '21:08:49', 14, 1),
	(12, 1, 'cancel making wish', '2015-04-12', '21:09:08', 14, 1),
	(13, 1, 'application was made', '2015-04-12', '21:09:47', 2, 1),
	(14, 1, 'application was accepted', '2015-04-12', '21:09:54', 14, 1),
	(15, 1, 'wish was made', '2015-04-12', '21:09:59', 14, 1),
	(16, 2, 'application was made', '2015-04-12', '21:11:13', 2, 0),
	(17, 1, 'qq', '2015-04-12', '21:41:19', 2, 1),
	(18, 1, 'hgfhfh', '2015-04-12', '21:43:35', 2, 1),
	(19, 1, 'ghjhhjg', '2015-04-13', '09:58:37', 2, 1),
	(20, 3, 'application was made', '2015-04-13', '12:14:54', 2, 0),
	(21, 1, 'application was accepted', '2015-04-13', '17:58:52', 14, 1),
	(22, 1, 'gffhfh', '2015-04-13', '18:22:54', 2, 1),
	(23, 1, 'gvhghg', '2015-04-14', '07:27:05', 2, 1),
	(24, 1, 'dhhdfh', '2015-04-14', '07:27:08', 2, 1),
	(25, 1, 'ghgfhf', '2015-04-14', '07:27:11', 2, 1),
	(26, 1, 'fggf', '2015-04-14', '07:27:14', 2, 1),
	(27, 1, '665', '2015-04-14', '12:22:09', 2, 1),
	(28, 1, '5454', '2015-04-14', '12:23:41', 2, 1),
	(29, 1, 'rtrt', '2015-04-14', '12:23:48', 2, 1),
	(30, 1, 'ertrtr', '2015-04-14', '12:23:52', 2, 1),
	(31, 1, 'ytyty', '2015-04-14', '12:23:59', 2, 1),
	(32, 1, 'tytt', '2015-04-14', '12:24:08', 2, 1),
	(33, 1, 'hjhhjhj', '2015-04-14', '12:29:31', 2, 1),
	(34, 1, 'application was made', '2015-04-19', '09:01:52', 2, 1),
	(35, 1, 'fjkgfjgkf', '2015-04-19', '09:02:38', 2, 1),
	(36, 1, 'ghfjghjf', '2015-04-19', '09:03:12', 14, 0);
/*!40000 ALTER TABLE `message` ENABLE KEYS */;


-- Dumping structure for table shooting_stars_database.user
DROP TABLE IF EXISTS `user`;
CREATE TABLE IF NOT EXISTS `user` (
  `userId` int(10) NOT NULL AUTO_INCREMENT,
  `login` varchar(50) DEFAULT NULL,
  `password` varchar(50) DEFAULT NULL,
  `userInfoId` int(10) DEFAULT NULL,
  `userStatusId` int(10) DEFAULT NULL,
  PRIMARY KEY (`userId`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8;

-- Dumping data for table shooting_stars_database.user: ~12 rows (approximately)
DELETE FROM `user`;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` (`userId`, `login`, `password`, `userInfoId`, `userStatusId`) VALUES
	(2, 'v', 'c4ca4238a0b923820dcc509a6f75849b', NULL, 1),
	(8, 'bbbv', 'c4ca4238a0b923820dcc509a6f75849b', NULL, NULL),
	(9, 'ghg', 'c4ca4238a0b923820dcc509a6f75849b', NULL, NULL),
	(10, 'gdfgd', 'c4ca4238a0b923820dcc509a6f75849b', NULL, NULL),
	(11, 'hfhfh', 'c4ca4238a0b923820dcc509a6f75849b', NULL, NULL),
	(12, 'fg', 'c4ca4238a0b923820dcc509a6f75849b', NULL, NULL),
	(13, 'fdgfg', 'c4ca4238a0b923820dcc509a6f75849b', NULL, NULL),
	(14, 'h', 'c4ca4238a0b923820dcc509a6f75849b', NULL, 0),
	(15, 'htbh', 'c4ca4238a0b923820dcc509a6f75849b', NULL, NULL),
	(16, '1', 'c4ca4238a0b923820dcc509a6f75849b', NULL, NULL),
	(17, 's', '202cb962ac59075b964b07152d234b70', NULL, NULL),
	(18, '123', 'e10adc3949ba59abbe56e057f20f883e', NULL, 1),
	(19, '1234', 'e10adc3949ba59abbe56e057f20f883e', NULL, 1);
/*!40000 ALTER TABLE `user` ENABLE KEYS */;


-- Dumping structure for table shooting_stars_database.user_info
DROP TABLE IF EXISTS `user_info`;
CREATE TABLE IF NOT EXISTS `user_info` (
  `userId` int(10) NOT NULL,
  `user_name` varchar(50) DEFAULT NULL,
  `surname` varchar(50) DEFAULT NULL,
  `email` varchar(50) DEFAULT NULL,
  `country` varchar(50) DEFAULT NULL,
  `city` varchar(50) DEFAULT NULL,
  `dateOfBirth` date DEFAULT NULL,
  `abilities` varchar(50) DEFAULT NULL,
  `photoName` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`userId`),
  FULLTEXT KEY `user_name` (`user_name`),
  FULLTEXT KEY `surname` (`surname`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Dumping data for table shooting_stars_database.user_info: ~12 rows (approximately)
DELETE FROM `user_info`;
/*!40000 ALTER TABLE `user_info` DISABLE KEYS */;
INSERT INTO `user_info` (`userId`, `user_name`, `surname`, `email`, `country`, `city`, `dateOfBirth`, `abilities`, `photoName`) VALUES
	(2, 'veronica', 'haritonova', 'veronica_haritonova@mail.ru', 'belarus', 'Minsk', '1994-09-29', 'jhjhj', '2.jpg'),
	(8, 'gfg', 'gfg', '', '', '', NULL, '', NULL),
	(9, 'wj', 'wqh', '', '', '', NULL, 'gff', NULL),
	(10, 'tr', 'gfgf', '', '', '', NULL, '', NULL),
	(11, 'ffs', 'fsd', '', '', '', NULL, '', NULL),
	(12, 'fgf', 'fg', '', '', '', NULL, '', NULL),
	(13, 'gfg', '', '', '', '', NULL, 'dgfg', NULL),
	(14, 'Patrick', 'Weirauch', '', '', 'Munchen', NULL, '', NULL),
	(15, 'vdvdfv', '', '', '', '', NULL, '', NULL),
	(16, '1', '', '', '', '', NULL, '', NULL),
	(17, 'Sasha', 'M', '', 'Australia', 'Sydney', '1994-12-12', 'sjsksl, skksl, sk', '17.jpg'),
	(18, 'fff', '', '', '', '', NULL, '', NULL),
	(19, 'ver', 'har', '', '', '', NULL, '', NULL);
/*!40000 ALTER TABLE `user_info` ENABLE KEYS */;


-- Dumping structure for table shooting_stars_database.user_status
DROP TABLE IF EXISTS `user_status`;
CREATE TABLE IF NOT EXISTS `user_status` (
  `userStatusId` int(10) DEFAULT NULL,
  `status` varchar(10) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Dumping data for table shooting_stars_database.user_status: ~0 rows (approximately)
DELETE FROM `user_status`;
/*!40000 ALTER TABLE `user_status` DISABLE KEYS */;
INSERT INTO `user_status` (`userStatusId`, `status`) VALUES
	(0, 'offline'),
	(1, 'online');
/*!40000 ALTER TABLE `user_status` ENABLE KEYS */;


-- Dumping structure for table shooting_stars_database.wish
DROP TABLE IF EXISTS `wish`;
CREATE TABLE IF NOT EXISTS `wish` (
  `wishId` int(10) NOT NULL AUTO_INCREMENT,
  `userId` int(10) DEFAULT NULL,
  `wish` tinytext,
  `wishStatusId` int(10) DEFAULT '0',
  PRIMARY KEY (`wishId`)
) ENGINE=InnoDB AUTO_INCREMENT=42 DEFAULT CHARSET=utf8;

-- Dumping data for table shooting_stars_database.wish: ~21 rows (approximately)
DELETE FROM `wish`;
/*!40000 ALTER TABLE `wish` DISABLE KEYS */;
INSERT INTO `wish` (`wishId`, `userId`, `wish`, `wishStatusId`) VALUES
	(1, 11, 'gfg', 0),
	(2, 11, 'fgdfg', 0),
	(3, 12, 'bggfb', 0),
	(7, 2, 'dgdgd', 2),
	(14, 13, 'dgdgd', 0),
	(15, 13, 'dgdgdfg', 0),
	(17, 2, 'fsdfsdf', 2),
	(20, 14, 'gsdgdsg', 2),
	(21, 14, 'gdfgfdgf', 0),
	(27, 2, 'vfvrfv', 0),
	(29, 15, 'yuryfh', 0),
	(30, 15, 'hghgg', 0),
	(31, 15, 'jk', 0),
	(32, 16, 'hi', 0),
	(33, 2, 'tfgfhg', 0),
	(36, 14, 'sss', 2),
	(38, 17, 'Parachute jump', 0),
	(39, 17, 'Drums', 0),
	(41, 14, 'ghghh', 0);
/*!40000 ALTER TABLE `wish` ENABLE KEYS */;


-- Dumping structure for table shooting_stars_database.wish_status
DROP TABLE IF EXISTS `wish_status`;
CREATE TABLE IF NOT EXISTS `wish_status` (
  `wishStatusId` int(10) NOT NULL DEFAULT '0',
  `wishStatus` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`wishStatusId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Dumping data for table shooting_stars_database.wish_status: ~2 rows (approximately)
DELETE FROM `wish_status`;
/*!40000 ALTER TABLE `wish_status` DISABLE KEYS */;
INSERT INTO `wish_status` (`wishStatusId`, `wishStatus`) VALUES
	(0, 'New'),
	(1, 'In progress'),
	(2, 'Fulfilled');
/*!40000 ALTER TABLE `wish_status` ENABLE KEYS */;
/*!40014 SET FOREIGN_KEY_CHECKS=1 */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
