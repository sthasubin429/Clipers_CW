-- phpMyAdmin SQL Dump
-- version 4.8.5
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Jun 08, 2020 at 08:14 PM
-- Server version: 10.1.38-MariaDB
-- PHP Version: 7.3.2

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `user_management`
--

-- --------------------------------------------------------

--
-- Table structure for table `activitylog`
--

CREATE TABLE `activitylog` (
  `activity` varchar(50) NOT NULL,
  `date` date NOT NULL,
  `time` varchar(20) DEFAULT NULL,
  `username` varchar(50) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `activitylog`
--

INSERT INTO `activitylog` (`activity`, `date`, `time`, `username`) VALUES
('Logged in', '2020-06-06', '05:50 PM', 'admin'),
('Logged in', '2020-06-06', '07:32 PM', 'admin'),
('Logged in', '2020-06-06', '07:35 PM', 'admin'),
('Logged in', '2020-06-06', '07:40 PM', 'admin'),
('Logged in', '2020-06-06', '09:13 PM', 'admin'),
('Logged in', '2020-06-06', '10:25 PM', 'admin'),
('Logged in', '2020-06-06', '11:02 PM', 'admin'),
('Logged in', '2020-06-06', '11:21 PM', 'admin'),
('Logged in', '2020-06-06', '11:30 PM', 'admin'),
('Logged in', '2020-06-06', '11:30 PM', 'admin'),
('Logged in', '2020-06-06', '11:31 PM', 'admin'),
('Logged in', '2020-06-06', '11:36 PM', 'admin'),
('Logged in', '2020-06-06', '11:49 PM', 'admin'),
('Logged in', '2020-06-06', '11:50 PM', 'admin'),
('Logged in', '2020-06-06', '11:52 PM', 'admin'),
('Logged in', '2020-06-06', '11:54 PM', 'admin'),
('Logged in', '2020-06-06', '11:56 PM', 'admin'),
('Logged in', '2020-06-07', '12:09 AM', 'admin'),
('Logged in', '2020-06-07', '12:44 AM', 'admin'),
('User Created', '2020-06-07', '12:45 AM', 'subin12'),
('Logged in', '2020-06-07', '12:48 AM', 'subin12'),
('Password Changed', '2020-06-07', '12:49 AM', 'subin12'),
('Logged in', '2020-06-07', '12:55 AM', 'subin12'),
('Logged in', '2020-06-07', '12:56 AM', 'admin'),
('Logged in', '2020-06-07', '01:03 AM', 'admin'),
('Logged in', '2020-06-07', '01:12 AM', 'admin'),
('Logged in', '2020-06-07', '01:20 AM', 'admin'),
('Logged in', '2020-06-07', '09:54 PM', 'admin'),
('Logged in', '2020-06-07', '10:03 PM', 'admin'),
('Password Reset', '2020-06-07', '10:10 PM', 'subin12'),
('Password Reset', '2020-06-07', '10:13 PM', 'subin12'),
('Password Reset', '2020-06-07', '10:20 PM', 'subin12'),
('Password Reset', '2020-06-07', '10:23 PM', 'subin12'),
('Password Reset', '2020-06-07', '10:25 PM', 'subin12'),
('Password Reset', '2020-06-07', '10:26 PM', 'subin12'),
('Password Reset', '2020-06-07', '10:30 PM', 'subin12'),
('Logged in', '2020-06-07', '10:31 PM', 'subin12'),
('Password Changed', '2020-06-07', '10:32 PM', 'subin12'),
('Password Reset', '2020-06-07', '10:34 PM', 'subin12'),
('Logged in', '2020-06-07', '10:35 PM', 'subin12'),
('Password Changed', '2020-06-07', '10:35 PM', 'subin12'),
('Logged in', '2020-06-07', '10:35 PM', 'admin'),
('User Created', '2020-06-07', '10:35 PM', 'herald colz'),
('Password Reset', '2020-06-07', '10:36 PM', 'herald colz'),
('Logged in', '2020-06-07', '10:37 PM', 'herald colz'),
('Password Changed', '2020-06-07', '10:37 PM', 'herald colz'),
('Logged in', '2020-06-07', '10:38 PM', 'subin12'),
('Logged in', '2020-06-07', '10:39 PM', 'admin'),
('Password Reset', '2020-06-07', '10:39 PM', 'herald colz'),
('Logged in', '2020-06-07', '10:40 PM', 'herald colz'),
('Password Changed', '2020-06-07', '10:40 PM', 'herald colz'),
('Logged in', '2020-06-07', '11:42 PM', 'subin12'),
('Logged in', '2020-06-08', '12:32 AM', 'subin12'),
('Logged in', '2020-06-08', '12:34 AM', 'admin'),
('Logged in', '2020-06-08', '12:46 AM', 'admin'),
('Logged in', '2020-06-08', '01:16 AM', 'admin'),
('Logged in', '2020-06-08', '01:19 AM', 'admin'),
('Logged in', '2020-06-08', '01:21 AM', 'admin'),
('Logged in', '2020-06-08', '01:38 AM', 'admin'),
('Logged in', '2020-06-08', '01:39 AM', 'admin'),
('Logged in', '2020-06-08', '01:43 AM', 'admin'),
('Logged in', '2020-06-08', '01:44 AM', 'admin'),
('Logged in', '2020-06-08', '01:47 AM', 'admin'),
('Logged in', '2020-06-08', '01:49 AM', 'admin'),
('Logged in', '2020-06-08', '01:51 AM', 'admin'),
('User Created', '2020-06-08', '01:53 AM', 'qpzmasd'),
('Logged in', '2020-06-08', '01:53 AM', 'admin'),
('User Created', '2020-06-08', '01:56 AM', 'sandip'),
('Logged in', '2020-06-08', '09:40 AM', 'admin'),
('Logged in', '2020-06-08', '03:17 PM', 'admin'),
('User Created', '2020-06-08', '03:21 PM', 'smita123'),
('User Created', '2020-06-08', '03:21 PM', 'sahan1'),
('User Created', '2020-06-08', '03:22 PM', 'sankalpa1'),
('User Created', '2020-06-08', '03:22 PM', 'sagar'),
('User Created', '2020-06-08', '03:23 PM', 'sthasubin'),
('User Created', '2020-06-08', '03:28 PM', 'thapasandip'),
('Logged in', '2020-06-08', '03:31 PM', 'sthasubin'),
('Logged in', '2020-06-08', '03:38 PM', 'sthasubin'),
('Logged in', '2020-06-08', '04:00 PM', 'sthasubin'),
('Logged in', '2020-06-08', '04:01 PM', 'subin12'),
('Logged in', '2020-06-08', '04:04 PM', 'admin'),
('Logged in', '2020-06-08', '04:08 PM', 'sthasubin'),
('Logged in', '2020-06-08', '04:11 PM', 'admin'),
('Logged in', '2020-06-08', '04:14 PM', 'admin'),
('Logged in', '2020-06-08', '04:21 PM', 'admin'),
('User Edited', '2020-06-08', '04:22 PM', 'sahan1'),
('Logged in', '2020-06-08', '04:22 PM', 'sahan1'),
('Logged in', '2020-06-08', '04:23 PM', 'admin'),
('Logged in', '2020-06-08', '04:27 PM', 'admin'),
('Logged in', '2020-06-08', '04:29 PM', 'sthasubin'),
('Logged in', '2020-06-08', '04:35 PM', 'sandip'),
('Password Reset', '2020-06-08', '04:36 PM', 'subin12'),
('Logged in', '2020-06-08', '04:36 PM', 'subin12'),
('Password Changed', '2020-06-08', '04:37 PM', 'subin12'),
('Logged in', '2020-06-08', '06:01 PM', 'admin'),
('Logged in', '2020-06-08', '06:02 PM', 'admin'),
('User Created', '2020-06-08', '06:03 PM', 'ishu12341'),
('User Edited', '2020-06-08', '06:05 PM', 'ishu12341'),
('Logged in', '2020-06-08', '06:06 PM', 'admin'),
('Logged in', '2020-06-08', '06:10 PM', 'subin12'),
('User Edited', '2020-06-08', '06:11 PM', 'subin12'),
('Password Reset', '2020-06-08', '06:15 PM', 'subin12'),
('Logged in', '2020-06-08', '06:16 PM', 'subin12'),
('Password Changed', '2020-06-08', '06:18 PM', 'subin12'),
('Logged in', '2020-06-08', '06:20 PM', 'admin'),
('Logged in', '2020-06-08', '06:47 PM', 'admin'),
('Logged in', '2020-06-08', '08:34 PM', 'sthasubin'),
('User Edited', '2020-06-08', '08:34 PM', 'ishu12341'),
('Logged in', '2020-06-08', '08:39 PM', 'sthasubin'),
('User Edited', '2020-06-08', '08:40 PM', 'ishu12341'),
('Logged in', '2020-06-08', '08:42 PM', 'sthasubin'),
('User Edited', '2020-06-08', '08:43 PM', 'ishu12341'),
('Logged in', '2020-06-08', '08:44 PM', 'subin12'),
('User Edited', '2020-06-08', '08:44 PM', 'subin12'),
('Logged in', '2020-06-08', '08:46 PM', 'admin'),
('User Edited', '2020-06-08', '08:46 PM', 'sankalpa1'),
('Logged in', '2020-06-08', '09:47 PM', 'sthasubin'),
('Logged in', '2020-06-08', '10:44 PM', 'admin'),
('Logged in', '2020-06-08', '11:32 PM', 'admin'),
('Logged in', '2020-06-08', '11:33 PM', 'admin'),
('Logged in', '2020-06-08', '11:33 PM', 'subin12'),
('Logged in', '2020-06-08', '11:34 PM', 'herald colz'),
('Logged in', '2020-06-08', '11:35 PM', 'sandip'),
('Logged in', '2020-06-08', '11:37 PM', 'smita123'),
('Logged in', '2020-06-08', '11:39 PM', 'sahan1'),
('Logged in', '2020-06-08', '11:40 PM', 'sahan1'),
('Logged in', '2020-06-08', '11:41 PM', 'sahan1'),
('Logged in', '2020-06-08', '11:42 PM', 'sankalpa1'),
('Logged in', '2020-06-08', '11:42 PM', 'sagar'),
('Logged in', '2020-06-08', '11:44 PM', 'sthasubin'),
('Logged in', '2020-06-08', '11:47 PM', 'admin'),
('Logged in', '2020-06-08', '11:49 PM', 'admin'),
('User Created', '2020-06-08', '11:52 PM', 'apple1'),
('User Created', '2020-06-08', '11:52 PM', 'banana1'),
('Logged in', '2020-06-08', '11:52 PM', 'banana1'),
('User Edited', '2020-06-08', '11:52 PM', 'banana1'),
('Logged in', '2020-06-08', '11:53 PM', 'apple1'),
('Logged in', '2020-06-08', '11:57 PM', 'admin');

-- --------------------------------------------------------

--
-- Table structure for table `role`
--

CREATE TABLE `role` (
  `role_id` int(11) NOT NULL,
  `role_name` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `role`
--

INSERT INTO `role` (`role_id`, `role_name`) VALUES
(0, 'Admin'),
(1, 'Client\r\n');

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

CREATE TABLE `users` (
  `user_id` int(11) NOT NULL,
  `full_name` varchar(60) NOT NULL,
  `username` varchar(50) NOT NULL,
  `user_email` varchar(50) NOT NULL,
  `user_password` varchar(50) NOT NULL,
  `user_status` enum('Active','Suspened','Deleted') NOT NULL DEFAULT 'Active',
  `created_date` date DEFAULT NULL,
  `user_role` enum('Admin','Client') DEFAULT 'Client'
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`user_id`, `full_name`, `username`, `user_email`, `user_password`, `user_status`, `created_date`, `user_role`) VALUES
(1, 'Admin', 'admin', 'admin@gmail.com', '7b902e6ff1db9f560443f2048974fd7d386975b0', 'Active', '2020-05-30', 'Admin'),
(2, 'Subin Shrestha', 'subin12', 'subinshrestha922@gmail.com', 'b4d4f539ab6d597eac2b1fe38082d8a5445dc404', 'Active', '2020-06-07', 'Client'),
(3, 'stha subin', 'herald colz', 'np03a180072@heraldcollege.edu.np', '0c6dfd645c4ce8fa9f1832215cfcfeafc26bd023', 'Active', '2020-06-07', 'Client'),
(4, 'herald cols', 'qpzmasd', 'herald@gmail.com', '5b353b56c72cbd2e692c6078eeca1c8af2af4d89', 'Active', '2020-06-08', 'Client'),
(5, 'Sandip Thapa', 'sandip', 'sandip@gmail.com', '8ba9e72565f52733deaccbabcc350e576e16e51b', 'Active', '2020-06-08', 'Client'),
(6, 'Smita Shrestha', 'smita123', 'smita@gmail.com', '111f616e80403da0ed12060c5fb41f92818aecb6', 'Active', '2020-06-08', 'Client'),
(7, 'Sahan Dangol', 'sahan1', 'sahan@gmail.com', '5354c6d09e744f428181eeb10ec6b1d810d7d86d', 'Active', '2020-06-08', 'Client'),
(8, 'Sankalpa Shrestha', 'sankalpa1', 'sankalpa@gmail.com', '3e1953d1b2ccade9d288cc160184aa1ee7202bd4', 'Active', '2020-06-08', 'Client'),
(9, 'Sagar Kuwar', 'sagar', 'sagar@gmail.com', 'b06a40e4f0a54e8e3529d6b7a262b9aa6be0dad5', 'Active', '2020-06-08', 'Client'),
(10, 'stha subin', 'sthasubin', 'subin@gmail.com', 'b4d4f539ab6d597eac2b1fe38082d8a5445dc404', 'Active', '2020-06-08', 'Admin'),
(11, 'Thapa sandip', 'thapasandip', 'thapasandip@gmail.com', '8ba9e72565f52733deaccbabcc350e576e16e51b', 'Active', '2020-06-08', 'Client'),
(13, 'ishu stha', 'ishu12341', 'ishu1@gmail.com', 'b4d4f539ab6d597eac2b1fe38082d8a5445dc404', 'Active', '2020-06-08', 'Client'),
(14, 'apple', 'apple1', 'apple@gmail.com', '2944aee5cf18956963d150863177cf82c0b03c1d', 'Active', '2020-06-08', 'Client'),
(15, 'Banana Banana', 'banana1', 'banana@gmail.com', '6ee5c55cc46057e7aba371bd7c40f2efa867c86f', 'Active', '2020-06-08', 'Client');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `activitylog`
--
ALTER TABLE `activitylog`
  ADD KEY `username` (`username`);

--
-- Indexes for table `role`
--
ALTER TABLE `role`
  ADD PRIMARY KEY (`role_id`),
  ADD UNIQUE KEY `role_name` (`role_name`);

--
-- Indexes for table `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`user_id`),
  ADD UNIQUE KEY `user_email` (`user_email`),
  ADD UNIQUE KEY `username` (`username`);

--
-- Constraints for dumped tables
--

--
-- Constraints for table `activitylog`
--
ALTER TABLE `activitylog`
  ADD CONSTRAINT `activitylog_ibfk_1` FOREIGN KEY (`username`) REFERENCES `users` (`username`) ON DELETE CASCADE ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
