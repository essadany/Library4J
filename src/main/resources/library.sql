-- phpMyAdmin SQL Dump
-- version 5.1.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1:3306
-- Generation Time: May 19, 2022 at 09:07 AM
-- Server version: 5.7.36
-- PHP Version: 7.4.26

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `library`
--

-- --------------------------------------------------------

--
-- Table structure for table `loans`
--

DROP TABLE IF EXISTS `loans`;
CREATE TABLE IF NOT EXISTS `loans` (
  `idLoan` int(11) NOT NULL AUTO_INCREMENT,
  `userID` int(11) NOT NULL,
  `bookID` varchar(20) NOT NULL,
  `date_Borrow` date NOT NULL,
  `date_Return` date DEFAULT NULL,
  PRIMARY KEY (`idLoan`),
  KEY `userID` (`userID`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `loans`
--

INSERT INTO `loans` (`idLoan`, `userID`, `bookID`, `date_Borrow`, `date_Return`) VALUES
(1, 1, 'bpt6k5738219s', '2022-05-18', '2022-05-26'),
(2, 2, 'bpt6k6258561z', '2022-05-18', '2022-05-20');

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
CREATE TABLE IF NOT EXISTS `users` (
  `userID` int(11) NOT NULL AUTO_INCREMENT,
  `first_name` varchar(20) NOT NULL,
  `last_name` varchar(20) NOT NULL,
  `adress` varchar(30) NOT NULL,
  `password` varchar(20) NOT NULL,
  `role` enum('librarian','student') NOT NULL,
  PRIMARY KEY (`userID`),
  UNIQUE KEY `adress` (`adress`),
  UNIQUE KEY `first_name` (`first_name`,`last_name`)
) ENGINE=InnoDB AUTO_INCREMENT=30 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`userID`, `first_name`, `last_name`, `adress`, `password`, `role`) VALUES
(1, 'Yassine', 'Es-sadany', 'essadanyya@cy-tech.fr', 'yassine2022', 'librarian'),
(2, 'Ilies', 'Ait Souar', 'aitsouaril@cy-tech.fr', 'ilies2022', 'student'),
(4, 'Quentin', 'Dast', 'dastqu@cy-tech.fr', 'quentin2022', 'student'),
(5, 'Al-amin', 'Chakour', 'chakoural@cy-tech.fr', 'chakoural@cy-tech.fr', 'student'),
(6, 'Bruneck', 'Ulrich', 'ulrichbr@cy-tech.fr', 'bruneck2022', 'student'),
(7, 'Said', 'ES-SADANY', 'essadanysa@cy-tech.fr', 'said2022', 'student'),
(19, 'Hassan', 'Ait Brahim', 'aitbrahimha@cy-tech.fr', 'hassan2022', 'student'),
(20, 'Mohamed', 'Ouaziki', 'ouazikimo@cy-tech.fr', 'mohamed2022', 'student'),
(21, 'Hamza', 'Ouaziki', 'ouazikiha@cy-tech.fr', 'hamza2022', 'student'),
(22, 'Nawal', 'Ouazik', 'ouazikna@cy-tech.fr', 'nawal2022', 'student'),
(23, 'Hajar', 'Yamani', 'yamaniha@cy-tech.fr', 'hajar2022', 'student'),
(24, 'Youssef', 'Bahmad', 'bahmadyo@cy-tech.fr', 'youssef2022', 'student'),
(25, 'Walid', 'Ouaziki', 'ouazikiwa@cy-tech.fr', 'walid2022', 'student'),
(26, 'Loubna', 'Chabab', 'chabablo@cy-tech.fr', 'chabab2022', 'student'),
(27, 'Ismail', 'Ouamadin', 'ouamadinis@cy-tech.fr', 'ouamadin2022', 'student'),
(28, 'Khadija', 'Massoudi', 'massoudikh@cy-tech.fr', 'khadija2022', 'student'),
(29, 'Zakaria', 'Idrouich', 'idrouichza@cy-tech.fr', 'zakaria2022', 'student');

--
-- Constraints for dumped tables
--

--
-- Constraints for table `loans`
--
ALTER TABLE `loans`
  ADD CONSTRAINT `loans_ibfk_1` FOREIGN KEY (`userID`) REFERENCES `users` (`userID`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
