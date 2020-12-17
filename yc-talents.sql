-- phpMyAdmin SQL Dump
-- version 5.0.2
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1:3306
-- Generation Time: Dec 17, 2020 at 03:31 PM
-- Server version: 5.7.31
-- PHP Version: 7.3.21

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `yc-talents`
--

-- --------------------------------------------------------

--
-- Table structure for table `admin`
--

DROP TABLE IF EXISTS `admin`;
CREATE TABLE IF NOT EXISTS `admin` (
  `id` bigint(20) NOT NULL,
  `password` varchar(500) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `admin`
--

INSERT INTO `admin` (`id`, `password`) VALUES
(15970010, 'ayoubgermany@@--@@');

-- --------------------------------------------------------

--
-- Table structure for table `adminsession`
--

DROP TABLE IF EXISTS `adminsession`;
CREATE TABLE IF NOT EXISTS `adminsession` (
  `id` bigint(20) NOT NULL,
  `id_admin` bigint(20) NOT NULL,
  `is_connected` tinyint(1) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `category`
--

DROP TABLE IF EXISTS `category`;
CREATE TABLE IF NOT EXISTS `category` (
  `id` bigint(20) NOT NULL,
  `name` varchar(500) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `category`
--

INSERT INTO `category` (`id`, `name`) VALUES
(1, 'La dance'),
(2, 'En chantant'),
(3, 'La scène'),
(4, 'La Comédie'),
(5, 'Calcul mental'),
(6, 'Le cube de Rubik');

-- --------------------------------------------------------

--
-- Table structure for table `participation`
--

DROP TABLE IF EXISTS `participation`;
CREATE TABLE IF NOT EXISTS `participation` (
  `id_user` bigint(20) NOT NULL,
  `id_category` bigint(20) NOT NULL,
  `description` varchar(500) NOT NULL,
  `show_start_time` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  `show_end_time` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  `attached_file` varchar(500) NOT NULL,
  `is_accepted` tinyint(1) NOT NULL,
  PRIMARY KEY (`id_user`,`id_category`),
  KEY `fk_idcategorieP` (`id_category`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `participation`
--

INSERT INTO `participation` (`id_user`, `id_category`, `description`, `show_start_time`, `show_end_time`, `attached_file`, `is_accepted`) VALUES
(732832, 1, 'test', '2020-12-10 23:00:00', '2020-12-11 23:00:00', 'test', 0),
(22252222552, 2, 'Desktop/freelancerlogo.jpg', '2020-11-30 23:00:00', '2020-12-03 23:00:00', 'Desktop/freelancerlogo.jpg', 1);

-- --------------------------------------------------------

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
CREATE TABLE IF NOT EXISTS `user` (
  `id` bigint(20) NOT NULL,
  `first_name` varchar(500) NOT NULL,
  `last_name` varchar(500) NOT NULL,
  `email` varchar(500) NOT NULL,
  `phone` varchar(500) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `user`
--

INSERT INTO `user` (`id`, `first_name`, `last_name`, `email`, `phone`) VALUES
(732832, 'mariem', 'elhadki', 'mariem@gmail.com', '0646274243'),
(15970010, 'Ahmed', 'Mahmoud', 'ayoubmousa30@gmail.com', '+212600000000'),
(22252222552, 'ayoub', 'moustahfid', 'botola2015@outlook.com', '0696396672');

--
-- Constraints for dumped tables
--

--
-- Constraints for table `admin`
--
ALTER TABLE `admin`
  ADD CONSTRAINT `admin_ibfk_1` FOREIGN KEY (`id`) REFERENCES `user` (`id`);

--
-- Constraints for table `participation`
--
ALTER TABLE `participation`
  ADD CONSTRAINT `fk_idcategorieP` FOREIGN KEY (`id_category`) REFERENCES `category` (`id`),
  ADD CONSTRAINT `fk_useridpar` FOREIGN KEY (`id_user`) REFERENCES `user` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
