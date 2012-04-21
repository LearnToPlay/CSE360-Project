-- phpMyAdmin SQL Dump
-- version 3.4.10.1
-- http://www.phpmyadmin.net
--
-- Host: localhost
-- Generation Time: Apr 17, 2012 at 02:16 AM
-- Server version: 5.5.20
-- PHP Version: 5.3.10

SET SQL_MODE="NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `jitt`
--
DROP DATABASE `jitt`;
CREATE DATABASE `jitt` DEFAULT CHARACTER SET latin1 COLLATE latin1_swedish_ci;
USE `jitt`;

-- --------------------------------------------------------

--
-- Table structure for table `course`
--

CREATE TABLE IF NOT EXISTS `course` (
  `course_name` char(24) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `instructor` char(24) CHARACTER SET utf32 COLLATE utf32_bin NOT NULL,
  `semester` char(24) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  PRIMARY KEY (`course_name`),
  KEY `instructor` (`instructor`)
) ENGINE=InnoDB DEFAULT CHARSET=ascii;

--
-- Dumping data for table `course`
--

INSERT INTO `course` (`course_name`, `instructor`, `semester`) VALUES
('CSE-360', 'janeka', 'Fall - 2012'),
('CSE-361', 'janeka', 'Summer - 2012');

-- --------------------------------------------------------

--
-- Table structure for table `quiz`
--

CREATE TABLE IF NOT EXISTS `quiz` (
  `quiz_name` char(24) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `course` char(24) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `start` timestamp NULL DEFAULT '0000-00-00 00:00:00',
  `end` timestamp NULL DEFAULT '0000-00-00 00:00:00',
  PRIMARY KEY (`quiz_name`),
  KEY `course` (`course`)
) ENGINE=InnoDB DEFAULT CHARSET=ascii;

--
-- Dumping data for table `quiz`
--

INSERT INTO `quiz` (`quiz_name`, `course`, `start`, `end`) VALUES
('Quiz 1', 'Fall - 2012', '2012-04-16 18:12:39', '2012-04-16 19:31:45');

-- --------------------------------------------------------

--
-- Table structure for table `quiz_content`
--

CREATE TABLE IF NOT EXISTS `quiz_content` (
  `quiz` char(24) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `question_num` int(11) NOT NULL,
  `question_text` varchar(256) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `choice_A` varchar(256) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `choice_B` varchar(256) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `choice_C` varchar(256) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `choice_D` varchar(256) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `correct_choice` enum('A','B','C','D') CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  KEY `quiz` (`quiz`,`question_num`)
) ENGINE=InnoDB DEFAULT CHARSET=ascii;

--
-- Dumping data for table `quiz_content`
--

INSERT INTO `quiz_content` (`quiz`, `question_num`, `question_text`, `choice_A`, `choice_B`, `choice_C`, `choice_D`, `correct_choice`) VALUES
('Quiz 1', 2, 'A test question?', 'Choose Me', 'Not me', 'Not me', 'Not me', 'A'),
('Quiz 1', 1, 'Why do people become clowns?', 'Crazy?', 'Stupid?', 'Republican?', 'Democrat?', 'B');

-- --------------------------------------------------------

--
-- Table structure for table `quiz_response`
--

CREATE TABLE IF NOT EXISTS `quiz_response` (
  `quiz` char(24) NOT NULL,
  `course` char(24) NOT NULL,
  `username` char(24) NOT NULL,
  `question_num` int(11) DEFAULT NULL,
  `response` set('A','B','C','D') DEFAULT NULL,
  PRIMARY KEY (`quiz`,`course`,`username`),
  KEY `question_num` (`question_num`)
) ENGINE=InnoDB DEFAULT CHARSET=ascii;

--
-- Dumping data for table `quiz_response`
--

INSERT INTO `quiz_response` (`quiz`, `course`, `username`, `question_num`, `response`) VALUES
('Quiz 1', 'CSE-360', 'bob', 1, 'A');

-- --------------------------------------------------------

--
-- Table structure for table `semester`
--

CREATE TABLE IF NOT EXISTS `semester` (
  `semester_name` char(24) COLLATE utf8_bin NOT NULL,
  `start` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  `end` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  UNIQUE KEY `semester` (`semester_name`),
  KEY `start` (`start`,`end`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

--
-- Dumping data for table `semester`
--

INSERT INTO `semester` (`semester_name`, `start`, `end`) VALUES
('Fall - 2012', '2012-04-15 21:13:18', '2012-04-15 21:19:18'),
('Summer - 2012', '2012-04-15 22:32:40', '2012-04-15 22:38:40'),
('Fall-2012', '2012-09-01 07:00:00', '2012-12-15 07:00:00');

-- --------------------------------------------------------

--
-- Table structure for table `student_roster`
--

CREATE TABLE IF NOT EXISTS `student_roster` (
  `course_name` char(24) NOT NULL,
  `instructor` char(24) NOT NULL,
  `student_name` char(24) NOT NULL,
  KEY `course_name` (`course_name`,`instructor`)
) ENGINE=InnoDB DEFAULT CHARSET=ascii;

--
-- Dumping data for table `student_roster`
--

INSERT INTO `student_roster` (`course_name`, `instructor`, `student_name`) VALUES
('CSE-361', 'janeka', 'wayne'),
('CSE-360', 'janeka', 'betty'),
('CSE-360', 'janeka', 'wayne');

-- --------------------------------------------------------

--
-- Table structure for table `user`
--

CREATE TABLE IF NOT EXISTS `user` (
  `username` char(24) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `last` char(24) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `first` char(24) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `isInstructor` tinyint(1) NOT NULL,
  PRIMARY KEY (`username`),
  KEY `isInstructor` (`isInstructor`)
) ENGINE=InnoDB DEFAULT CHARSET=ascii;

--
-- Dumping data for table `user`
--

INSERT INTO `user` (`username`, `last`, `first`, `isInstructor`) VALUES
('betty', 'Betty', 'Sue', 0),
('billy', 'Bob', 'Billy', 0),
('bob', 'Barker', 'Bob', 1),
('janeka', 'Balasooriya', 'Janeka', 1),
('wayne', 'Brown', 'Wayne', 0);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
