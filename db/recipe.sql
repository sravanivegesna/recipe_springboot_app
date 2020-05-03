-- phpMyAdmin SQL Dump
-- version 5.0.2
-- https://www.phpmyadmin.net/
--
-- Host: localhost
-- Generation Time: May 03, 2020 at 09:58 AM
-- Server version: 10.4.11-MariaDB
-- PHP Version: 7.2.29

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `recipe`
--

-- --------------------------------------------------------

--
-- Table structure for table `hibernate_sequence`
--

CREATE TABLE `hibernate_sequence` (
  `next_val` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `hibernate_sequence`
--

INSERT INTO `hibernate_sequence` (`next_val`) VALUES
(23),
(23);

-- --------------------------------------------------------

--
-- Table structure for table `mocktail`
--

CREATE TABLE `mocktail` (
  `id` bigint(20) NOT NULL,
  `duration` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `serving` varchar(255) DEFAULT NULL,
  `user_id` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `mocktail`
--

INSERT INTO `mocktail` (`id`, `duration`, `name`, `serving`, `user_id`) VALUES
(4, '2', 'moscow mule', '3', 3),
(6, '2', 'moscow mule', '3', 5),
(7, '3', 'Blue berries', '3', 5),
(19, '5 mins', 'mojito', '4 persons', 8),
(20, '5 mins', 'mongo mocktail', '4 persons', 10),
(22, '3', 'Blue berries', '3', 21);

-- --------------------------------------------------------

--
-- Table structure for table `permission`
--

CREATE TABLE `permission` (
  `id` int(11) NOT NULL,
  `name` varchar(50) NOT NULL,
  `description` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `permission`
--

INSERT INTO `permission` (`id`, `name`, `description`) VALUES
(1, 'CREATE', 'This permission will help the user to only create a mocktail '),
(2, 'EDIT', 'This permission will help the user to only update a mocktail'),
(3, 'VIEW ', 'This permission will help the user to view the mocktails'),
(4, 'DELETE', 'This permission will help the user to delete the mocktail');

-- --------------------------------------------------------

--
-- Table structure for table `user`
--

CREATE TABLE `user` (
  `id` bigint(20) NOT NULL,
  `email` varchar(255) DEFAULT NULL,
  `first_name` varchar(255) DEFAULT NULL,
  `last_name` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `user`
--

INSERT INTO `user` (`id`, `email`, `first_name`, `last_name`) VALUES
(3, 'dog@gmail.com', 'monkey', 'donkey'),
(5, 'dog@gmail.com', 'monkey', 'donkey'),
(8, 'jonhsnow@gmail.com', 'jony', 'sigh'),
(9, 'mattwood@gmail.com', 'matt', 'wood'),
(10, 'underwood@gmail.com', 'underwood', 'mack'),
(21, 'haydun@gmail.com', 'aar', 'wood');

-- --------------------------------------------------------

--
-- Table structure for table `user_permission`
--

CREATE TABLE `user_permission` (
  `user_id` int(11) NOT NULL,
  `permission_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `user_permission`
--

INSERT INTO `user_permission` (`user_id`, `permission_id`) VALUES
(5, 2),
(5, 1),
(3, 2),
(3, 1);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `mocktail`
--
ALTER TABLE `mocktail`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKt8r719mc5qeyevcoev5de7yd3` (`user_id`);

--
-- Indexes for table `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`id`);

--
-- Constraints for dumped tables
--

--
-- Constraints for table `mocktail`
--
ALTER TABLE `mocktail`
  ADD CONSTRAINT `FKt8r719mc5qeyevcoev5de7yd3` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
