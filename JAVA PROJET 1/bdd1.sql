-- phpMyAdmin SQL Dump
-- version 4.8.5
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1:3306
-- Generation Time: Sep 03, 2019 at 12:25 AM
-- Server version: 5.7.26
-- PHP Version: 7.2.18

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `bdd1`
--

-- --------------------------------------------------------

--
-- Table structure for table `stagiaires`
--

DROP TABLE IF EXISTS `stagiaires`;
CREATE TABLE IF NOT EXISTS `stagiaires` (
  `stagiaires_id` int(11) NOT NULL AUTO_INCREMENT,
  `nom` varchar(80) COLLATE utf8_unicode_ci NOT NULL,
  `prenom` varchar(80) COLLATE utf8_unicode_ci NOT NULL,
  `date_entree` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `motdepasse` varchar(40) COLLATE utf8_unicode_ci NOT NULL,
  `imgPath` varchar(250) COLLATE utf8_unicode_ci NOT NULL DEFAULT 'imgDefault.png',
  PRIMARY KEY (`stagiaires_id`)
) ENGINE=InnoDB AUTO_INCREMENT=131 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `stagiaires`
--

INSERT INTO `stagiaires` (`stagiaires_id`, `nom`, `prenom`, `date_entree`, `motdepasse`, `imgPath`) VALUES
(104, 'YILMAZ', 'Ahmet', '2019-08-09 16:41:54', '651', 'S_104.png'),
(105, 'MOULIN', 'Jean', '2019-08-09 16:56:15', '178969', 'S_105.png'),
(108, 'PANZANI', 'Françis', '2019-08-22 10:14:00', '303', 'imgDefault.png'),
(109, 'ACIKKOLLU', 'Sehit Gökhan', '2019-08-22 10:14:00', 'adalet', 'S_109.png'),
(114, 'LEMASSON', 'Frederick1', '2019-08-22 16:52:23', 'patron', 'S_114.png'),
(120, 'ROLLET', 'Olivier', '2019-08-28 13:50:18', 'phpmaster', 'S_120.png'),
(125, 'KUSCU', 'Arif', '2019-09-02 14:27:27', 'üzüm', 'S_125.png'),
(127, 'uzun', 'sabri', '2019-09-03 01:22:01', 'üzüm', 'S_127.png'),
(129, 'POLO', 'Ekin', '2019-09-03 02:09:48', 'pasapilo', 'S_129.png'),
(130, 'KUSCU', 'Arif', '2019-09-03 02:11:56', 'üzüm', 'S_130.png');
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
