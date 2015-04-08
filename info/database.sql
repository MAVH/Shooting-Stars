-- --------------------------------------------------------
-- Host:                         127.0.0.1
-- Server version:               5.6.21-log - MySQL Community Server (GPL)
-- Server OS:                    Win64
-- HeidiSQL version:             7.0.0.4053
-- Date/time:                    2015-04-08 21:25:48
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
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Dumping data for table shooting_stars_database.chat: ~0 rows (approximately)
DELETE FROM `chat`;
/*!40000 ALTER TABLE `chat` DISABLE KEYS */;
/*!40000 ALTER TABLE `chat` ENABLE KEYS */;


-- Dumping structure for table shooting_stars_database.considered_wish
DROP TABLE IF EXISTS `considered_wish`;
CREATE TABLE IF NOT EXISTS `considered_wish` (
  `wishId` int(10) NOT NULL DEFAULT '0',
  `userId` int(10) NOT NULL DEFAULT '0',
  PRIMARY KEY (`wishId`,`userId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Dumping data for table shooting_stars_database.considered_wish: ~2 rows (approximately)
DELETE FROM `considered_wish`;
/*!40000 ALTER TABLE `considered_wish` DISABLE KEYS */;
INSERT INTO `considered_wish` (`wishId`, `userId`) VALUES
	(22, 14),
	(29, 2);
/*!40000 ALTER TABLE `considered_wish` ENABLE KEYS */;


-- Dumping structure for table shooting_stars_database.fulfilled_wish
DROP TABLE IF EXISTS `fulfilled_wish`;
CREATE TABLE IF NOT EXISTS `fulfilled_wish` (
  `wishId` int(10) NOT NULL DEFAULT '0',
  `userId` int(10) DEFAULT NULL,
  `wishStatusId` int(10) DEFAULT '3',
  `date` date DEFAULT NULL,
  PRIMARY KEY (`wishId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Dumping data for table shooting_stars_database.fulfilled_wish: ~1 rows (approximately)
DELETE FROM `fulfilled_wish`;
/*!40000 ALTER TABLE `fulfilled_wish` DISABLE KEYS */;
INSERT INTO `fulfilled_wish` (`wishId`, `userId`, `wishStatusId`, `date`) VALUES
	(7, 14, 2, '2015-03-19'),
	(17, 14, 2, '2015-03-19'),
	(27, 14, 3, NULL);
/*!40000 ALTER TABLE `fulfilled_wish` ENABLE KEYS */;


-- Dumping structure for table shooting_stars_database.message
DROP TABLE IF EXISTS `message`;
CREATE TABLE IF NOT EXISTS `message` (
  `chatId` int(10) DEFAULT NULL,
  `message` longtext,
  `time` timestamp NULL DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Dumping data for table shooting_stars_database.message: ~0 rows (approximately)
DELETE FROM `message`;
/*!40000 ALTER TABLE `message` DISABLE KEYS */;
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
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8;

-- Dumping data for table shooting_stars_database.user: ~11 rows (approximately)
DELETE FROM `user`;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` (`userId`, `login`, `password`, `userInfoId`, `userStatusId`) VALUES
	(2, 'v', 'c4ca4238a0b923820dcc509a6f75849b', NULL, 0),
	(8, 'bbbv', 'c4ca4238a0b923820dcc509a6f75849b', NULL, NULL),
	(9, 'ghg', 'c4ca4238a0b923820dcc509a6f75849b', NULL, NULL),
	(10, 'gdfgd', 'c4ca4238a0b923820dcc509a6f75849b', NULL, NULL),
	(11, 'hfhfh', 'c4ca4238a0b923820dcc509a6f75849b', NULL, NULL),
	(12, 'fg', 'c4ca4238a0b923820dcc509a6f75849b', NULL, NULL),
	(13, 'fdgfg', 'c4ca4238a0b923820dcc509a6f75849b', NULL, NULL),
	(14, 'h', 'c4ca4238a0b923820dcc509a6f75849b', NULL, 0),
	(15, 'htbh', 'c4ca4238a0b923820dcc509a6f75849b', NULL, NULL),
	(16, '1', 'c4ca4238a0b923820dcc509a6f75849b', NULL, NULL),
	(17, 's', '202cb962ac59075b964b07152d234b70', NULL, NULL);
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
  `photoURL` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`userId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Dumping data for table shooting_stars_database.user_info: ~10 rows (approximately)
DELETE FROM `user_info`;
/*!40000 ALTER TABLE `user_info` DISABLE KEYS */;
INSERT INTO `user_info` (`userId`, `user_name`, `surname`, `email`, `country`, `city`, `dateOfBirth`, `abilities`, `photoURL`) VALUES
	(2, 'veronica', 'haritonova', 'veronica_haritonova@mail.ru', 'belarus', 'Minsk', NULL, 'fnjfdj nc, jdgj', '2.jpg'),
	(8, 'gfg', 'gfg', '', '', '', NULL, '', NULL),
	(9, 'wj', 'wqh', '', '', '', NULL, 'gff', NULL),
	(10, 'tr', 'gfgf', '', '', '', NULL, '', NULL),
	(11, 'ffs', 'fsd', '', '', '', NULL, '', NULL),
	(12, 'fgf', 'fg', '', '', '', NULL, '', NULL),
	(13, 'gfg', '', '', '', '', NULL, 'dgfg', NULL),
	(14, 'Patrick', 'Weirauch', '', 'Germany', 'Munchen', NULL, '', NULL),
	(15, 'vdvdfv', '', '', '', '', NULL, '', NULL),
	(16, '1', '', '', '', '', NULL, '', NULL),
	(17, 'Sasha', 'M', '', 'Australia', 'Sydney', '1994-12-12', 'sjsksl, skksl, sk', '17.jpg');
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
) ENGINE=InnoDB AUTO_INCREMENT=41 DEFAULT CHARSET=utf8;

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
	(20, 14, 'gsdgdsg', 0),
	(21, 14, 'gdfgfdgf', 0),
	(27, 2, 'vfvrfv', 0),
	(29, 15, 'yuryfh', 0),
	(30, 15, 'hghgg', 0),
	(31, 15, 'jk', 0),
	(32, 16, 'hi', 0),
	(33, 2, 'tfgfhg', 0),
	(34, 2, 'hghghg', 0),
	(36, 14, 'sss', 0),
	(38, 17, 'Parachute jump', 0),
	(39, 17, 'Drums', 0),
	(40, 2, 'jjgjgjhj', 0);
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
	(0, 'Новое'),
	(1, 'Выполняется'),
	(2, 'Выполнено');
/*!40000 ALTER TABLE `wish_status` ENABLE KEYS */;
/*!40014 SET FOREIGN_KEY_CHECKS=1 */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
