-- --------------------------------------------------------
-- Host:                         127.0.0.1
-- Server version:               5.6.21-log - MySQL Community Server (GPL)
-- Server OS:                    Win64
-- HeidiSQL version:             7.0.0.4053
-- Date/time:                    2015-05-15 15:58:07
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
  PRIMARY KEY (`chatId`),
  KEY `FK_chat_user` (`user1Id`),
  KEY `FK_chat_user_2` (`user2Id`),
  CONSTRAINT `FK_chat_user` FOREIGN KEY (`user1Id`) REFERENCES `user` (`userId`),
  CONSTRAINT `FK_chat_user_2` FOREIGN KEY (`user2Id`) REFERENCES `user` (`userId`)
) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=utf8;

-- Dumping data for table shooting_stars_database.chat: ~3 rows (approximately)
DELETE FROM `chat`;
/*!40000 ALTER TABLE `chat` DISABLE KEYS */;
INSERT INTO `chat` (`chatId`, `user1Id`, `user2Id`) VALUES
	(19, 22, 2),
	(20, 23, 2),
	(22, 23, 22),
	(23, 2, 24);
/*!40000 ALTER TABLE `chat` ENABLE KEYS */;


-- Dumping structure for table shooting_stars_database.considered_wish
DROP TABLE IF EXISTS `considered_wish`;
CREATE TABLE IF NOT EXISTS `considered_wish` (
  `wishId` int(10) NOT NULL,
  `userId` int(10) NOT NULL,
  PRIMARY KEY (`wishId`,`userId`),
  KEY `FK_considered_wish_user` (`userId`),
  CONSTRAINT `FK_considered_wish_user` FOREIGN KEY (`userId`) REFERENCES `user` (`userId`),
  CONSTRAINT `FK_considered_wish_wish` FOREIGN KEY (`wishId`) REFERENCES `wish` (`wishId`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Dumping data for table shooting_stars_database.considered_wish: ~4 rows (approximately)
DELETE FROM `considered_wish`;
/*!40000 ALTER TABLE `considered_wish` DISABLE KEYS */;
INSERT INTO `considered_wish` (`wishId`, `userId`) VALUES
	(63, 2),
	(62, 22),
	(63, 22),
	(67, 22);
/*!40000 ALTER TABLE `considered_wish` ENABLE KEYS */;


-- Dumping structure for table shooting_stars_database.fulfilled_wish
DROP TABLE IF EXISTS `fulfilled_wish`;
CREATE TABLE IF NOT EXISTS `fulfilled_wish` (
  `wishId` int(10) NOT NULL,
  `userId` int(10) DEFAULT NULL,
  `wishStatusId` int(10) DEFAULT '1',
  `date` date DEFAULT NULL,
  PRIMARY KEY (`wishId`),
  KEY `FK_fulfilled_wish_user` (`userId`),
  KEY `FK_fulfilled_wish_wish_status` (`wishStatusId`),
  CONSTRAINT `FK_fulfilled_wish_user` FOREIGN KEY (`userId`) REFERENCES `user` (`userId`),
  CONSTRAINT `FK_fulfilled_wish_wish` FOREIGN KEY (`wishId`) REFERENCES `wish` (`wishId`) ON DELETE CASCADE,
  CONSTRAINT `FK_fulfilled_wish_wish_status` FOREIGN KEY (`wishStatusId`) REFERENCES `wish_status` (`wishStatusId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Dumping data for table shooting_stars_database.fulfilled_wish: ~4 rows (approximately)
DELETE FROM `fulfilled_wish`;
/*!40000 ALTER TABLE `fulfilled_wish` DISABLE KEYS */;
INSERT INTO `fulfilled_wish` (`wishId`, `userId`, `wishStatusId`, `date`) VALUES
	(58, 2, 2, '2015-05-15'),
	(60, 22, 2, '2015-05-14'),
	(65, 23, 1, NULL),
	(68, 2, 1, NULL);
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
  PRIMARY KEY (`messageId`),
  KEY `FK_message_user` (`sender`),
  KEY `FK_message_chat` (`chatId`),
  CONSTRAINT `FK_message_chat` FOREIGN KEY (`chatId`) REFERENCES `chat` (`chatId`),
  CONSTRAINT `FK_message_user` FOREIGN KEY (`sender`) REFERENCES `user` (`userId`)
) ENGINE=InnoDB AUTO_INCREMENT=228 DEFAULT CHARSET=utf8;

-- Dumping data for table shooting_stars_database.message: ~39 rows (approximately)
DELETE FROM `message`;
/*!40000 ALTER TABLE `message` DISABLE KEYS */;
INSERT INTO `message` (`messageId`, `chatId`, `message`, `date`, `time`, `sender`, `isRead`) VALUES
	(189, 19, 'cancel application', '2015-05-14', '07:23:56', 22, 1),
	(190, 19, 'application was made', '2015-05-14', '07:24:39', 22, 1),
	(191, 19, 'application was made', '2015-05-14', '07:26:12', 22, 1),
	(192, 19, 'cancel application', '2015-05-14', '07:35:52', 22, 1),
	(193, 19, 'application was made', '2015-05-14', '07:38:10', 22, 1),
	(194, 19, 'cancel application', '2015-05-14', '07:38:52', 22, 1),
	(195, 19, 'application was made', '2015-05-14', '07:39:16', 22, 1),
	(196, 19, 'Hi', '2015-05-14', '07:39:37', 22, 1),
	(197, 19, 'Hi', '2015-05-14', '07:40:01', 2, 1),
	(198, 19, 'hi', '2015-05-14', '07:41:59', 2, 1),
	(199, 19, 'vcvc', '2015-05-14', '07:42:47', 22, 1),
	(200, 19, 'gfgfngb', '2015-05-14', '07:44:56', 22, 1),
	(201, 19, 'ghg', '2015-05-14', '07:46:58', 2, 1),
	(202, 19, 'application was accepted', '2015-05-14', '07:49:23', 2, 1),
	(203, 20, 'application was made', '2015-05-14', '07:53:32', 23, 0),
	(204, 20, 'application was made', '2015-05-14', '07:55:46', 2, 1),
	(205, 19, 'wish was made', '2015-05-14', '07:56:52', 2, 1),
	(206, 20, 'application was made', '2015-05-14', '07:59:21', 23, 0),
	(207, 20, 'application was accepted', '2015-05-14', '07:59:29', 2, 1),
	(208, 22, 'application was made', '2015-05-14', '08:24:40', 22, 1),
	(209, 19, 'Hi', '2015-05-15', '10:45:43', 22, 1),
	(210, 20, 'cancel application', '2015-05-15', '10:46:18', 2, 0),
	(211, 19, 'application was made', '2015-05-15', '10:49:40', 22, 1),
	(212, 19, 'cancel application', '2015-05-15', '10:50:10', 2, 0),
	(213, 19, 'application was made', '2015-05-15', '10:50:27', 22, 1),
	(214, 19, 'cancel application', '2015-05-15', '10:50:32', 22, 1),
	(215, 19, 'application was made', '2015-05-15', '10:50:51', 22, 1),
	(216, 19, 'application was accepted', '2015-05-15', '10:50:54', 2, 0),
	(217, 19, 'cancel making wish', '2015-05-15', '10:51:03', 2, 0),
	(218, 19, 'application was made', '2015-05-15', '10:51:09', 22, 1),
	(219, 19, 'application was accepted', '2015-05-15', '10:51:17', 2, 0),
	(220, 19, 'cancel making wish', '2015-05-15', '10:51:21', 22, 1),
	(221, 19, 'application was made', '2015-05-15', '10:51:31', 22, 1),
	(222, 23, 'application was made', '2015-05-15', '15:48:24', 2, 0),
	(223, 23, 'application was accepted', '2015-05-15', '15:49:21', 24, 0),
	(224, 19, 'application was made', '2015-05-15', '15:49:54', 2, 0),
	(225, 19, 'application was accepted', '2015-05-15', '15:50:25', 22, 1),
	(226, 19, 'wish was made', '2015-05-15', '15:50:31', 22, 1),
	(227, 19, 'hfhfh', '2015-05-15', '15:52:09', 2, 0);
/*!40000 ALTER TABLE `message` ENABLE KEYS */;


-- Dumping structure for table shooting_stars_database.user
DROP TABLE IF EXISTS `user`;
CREATE TABLE IF NOT EXISTS `user` (
  `userId` int(10) NOT NULL AUTO_INCREMENT,
  `login` varchar(50) DEFAULT NULL,
  `password` varchar(50) DEFAULT NULL,
  `lastVisitTime` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`userId`)
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=utf8;

-- Dumping data for table shooting_stars_database.user: ~5 rows (approximately)
DELETE FROM `user`;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` (`userId`, `login`, `password`, `lastVisitTime`) VALUES
	(2, 'v', 'c4ca4238a0b923820dcc509a6f75849b', '2015-05-15 15:55:19'),
	(17, 's', '202cb962ac59075b964b07152d234b70', '2015-04-12 23:06:16'),
	(22, 'patrick', 'e10adc3949ba59abbe56e057f20f883e', '2015-05-15 15:50:31'),
	(23, 'thomas', 'e10adc3949ba59abbe56e057f20f883e', '2015-05-14 08:25:36'),
	(24, 'student', 'ae005ceb7e9a217cced2f8aa354187c7', '2015-05-15 15:49:21');
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
  `abilities` longtext,
  `photoName` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`userId`),
  FULLTEXT KEY `user_name` (`user_name`),
  FULLTEXT KEY `surname` (`surname`),
  CONSTRAINT `FK_user_info_user` FOREIGN KEY (`userId`) REFERENCES `user` (`userId`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Dumping data for table shooting_stars_database.user_info: ~5 rows (approximately)
DELETE FROM `user_info`;
/*!40000 ALTER TABLE `user_info` DISABLE KEYS */;
INSERT INTO `user_info` (`userId`, `user_name`, `surname`, `email`, `country`, `city`, `dateOfBirth`, `abilities`, `photoName`) VALUES
	(2, 'Veronica', 'Haritonova', 'veronica_haritonova@mail.ru', 'Belarus', 'Minsk', '1994-09-29', 'Play volleyball', '2.jpg'),
	(17, 'Sasha', 'M', '', 'Australia', 'Sydney', '2013-05-07', 'sjsksl, skksl, sk', '17.jpg'),
	(22, 'Patrick', 'Weirauch', 'patrick@mail.ru', 'Germany', 'Munchen', '1990-10-12', 'Play football', NULL),
	(23, 'Thomas', 'Lahm', 'tom@gmail.com', 'Germany', 'Berlin', '1994-03-12', 'Draw landscapes', NULL),
	(24, 'Константин', 'Харитонов', 'veronica_haritonova@mail.ru', 'Беларусь', 'Минск', '2000-05-18', 'Рисовать, ездить на велосипеде, строить замки из песка', '24.jpg');
/*!40000 ALTER TABLE `user_info` ENABLE KEYS */;


-- Dumping structure for table shooting_stars_database.wish
DROP TABLE IF EXISTS `wish`;
CREATE TABLE IF NOT EXISTS `wish` (
  `wishId` int(10) NOT NULL AUTO_INCREMENT,
  `userId` int(10) DEFAULT NULL,
  `wish` longtext,
  `wishStatusId` int(10) DEFAULT '0',
  `creationTime` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`wishId`),
  KEY `FK_wish_user` (`userId`),
  KEY `FK_wish_wish_status` (`wishStatusId`),
  CONSTRAINT `FK_wish_user` FOREIGN KEY (`userId`) REFERENCES `user` (`userId`),
  CONSTRAINT `FK_wish_wish_status` FOREIGN KEY (`wishStatusId`) REFERENCES `wish_status` (`wishStatusId`)
) ENGINE=InnoDB AUTO_INCREMENT=70 DEFAULT CHARSET=utf8;

-- Dumping data for table shooting_stars_database.wish: ~12 rows (approximately)
DELETE FROM `wish`;
/*!40000 ALTER TABLE `wish` DISABLE KEYS */;
INSERT INTO `wish` (`wishId`, `userId`, `wish`, `wishStatusId`, `creationTime`) VALUES
	(38, 17, 'Parachute jump', 0, '2015-05-07 16:10:09'),
	(39, 17, 'Drums', 0, '2015-05-07 16:10:09'),
	(58, 22, 'Play the guitar', 2, '2015-05-15 15:50:31'),
	(59, 22, 'Make a trip in China', 0, '2015-05-14 07:08:29'),
	(60, 2, 'Make a trip in Germany, especially in Munchen', 2, '2015-05-14 07:56:51'),
	(62, 2, 'Ride a motorcycle', 0, '2015-05-14 07:22:25'),
	(63, 23, 'Climb the mountain', 0, '2015-05-14 07:52:42'),
	(65, 2, 'Make a trip in Alps', 0, '2015-05-14 07:58:36'),
	(66, 2, 'Make a trip in Shanghai', 0, '2015-05-15 10:30:22'),
	(67, 2, 'Paragliding in Switzerland', 0, '2015-05-15 10:48:34'),
	(68, 24, 'Поехать в Дисней-ленд', 0, '2015-05-15 15:25:10'),
	(69, 24, 'Увидеть жирафа', 0, '2015-05-15 15:25:10');
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
