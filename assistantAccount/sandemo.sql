-- phpMyAdmin SQL Dump
-- version 5.0.4
-- https://www.phpmyadmin.net/
--
-- Host: localhost:3306
-- Generation Time: Dec 10, 2021 at 04:25 PM
-- Server version: 5.7.24
-- PHP Version: 7.4.16

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `sandemo`
--

-- --------------------------------------------------------

--
-- Table structure for table `client`
--

CREATE TABLE `client` (
  `IDclient` int(11) NOT NULL,
  `name` varchar(50) NOT NULL,
  `address` varchar(50) DEFAULT NULL,
  `birthdate` date DEFAULT NULL,
  `gender` char(1) DEFAULT NULL,
  `height` int(10) UNSIGNED DEFAULT NULL,
  `weight` int(10) UNSIGNED DEFAULT NULL,
  `bloodtype` int(10) UNSIGNED NOT NULL,
  `rezus` char(1) NOT NULL,
  `illness` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `client`
--

INSERT INTO `client` (`IDclient`, `name`, `address`, `birthdate`, `gender`, `height`, `weight`, `bloodtype`, `rezus`, `illness`) VALUES
(1, 'John Smith', '151 Stone, Hannover', '2011-06-29', 'M', 191, 80, 1, 'P', 'Leukamia'),
(2, 'Maya Christensen', '121 Street, Helsinki', '2020-12-12', 'F', 167, 60, 4, 'N', 'Bronchitis'),
(3, 'Ebrahim Mitchell', '111 Strasse, Berlin', '1984-04-13', 'M', 170, 70, 2, 'N', 'Bulimia'),
(4, 'Alanna Bernard', '161 Street, Denver', '1996-08-13', 'F', 168, 50, 1, 'P', 'Chets pain'),
(5, 'Martine Francis', '171 Avenue, New York', '1945-08-18', 'M', 181, 79, 4, 'P', 'Pancreatitis'),
(6, 'Danial Blevins', '78 Street, Stockholm', '1985-09-11', 'M', 179, 75, 3, 'P', 'Common cold'),
(7, 'Taliah Harding', '46 Avenue, Dubai', '1969-10-12', 'M', 175, 77, 1, 'N', 'Cough'),
(8, 'Ephraim Sadler', '33 Street, Baku', '1999-12-19', 'M', 178, 74, 2, 'P', 'Depression'),
(9, 'Tom King', '89 Stone, London', '1977-03-15', 'M', 195, 85, 3, 'P', 'Diabetes'),
(10, 'Alissia Robbins', '79 Strasse, Viena', '1991-01-02', 'F', 170, 65, 1, 'P', 'Diarrhoea');

-- --------------------------------------------------------

--
-- Table structure for table `client_login`
--

CREATE TABLE `client_login` (
  `ClientID` int(11) DEFAULT NULL,
  `Login` varchar(50) NOT NULL,
  `Password` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `client_login`
--

INSERT INTO `client_login` (`ClientID`, `Login`, `Password`) VALUES
(4, 'alanna', 'bernard'),
(10, 'alissia', 'robbins'),
(6, 'danial', 'blevins'),
(3, 'ebrahim', 'mitchell'),
(8, 'ephraim', 'sadler'),
(1, 'john', 'smith'),
(5, 'martine', 'francis'),
(2, 'maya', 'christensen'),
(7, 'taliah', 'harding'),
(9, 'tom', 'king');

-- --------------------------------------------------------

--
-- Table structure for table `procedures`
--

CREATE TABLE `procedures` (
  `ProcedureID` int(11) NOT NULL,
  `Name` varchar(50) NOT NULL,
  `Price` int(11) NOT NULL,
  `Start_at` time DEFAULT NULL,
  `End_at` time DEFAULT NULL,
  `Schedule` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `procedures`
--

INSERT INTO `procedures` (`ProcedureID`, `Name`, `Price`, `Start_at`, `End_at`, `Schedule`) VALUES
(123, 'Acupuncture', 150, '08:00:00', '09:00:00', 21),
(124, 'Herbal medicine', 120, '10:00:00', '10:30:00', 63),
(125, 'Phototherapy', 110, '10:30:00', '12:00:00', 42),
(126, 'Physiotherapy', 120, '13:00:00', '14:00:00', 17),
(127, 'Massage', 140, '14:00:00', '15:00:00', 96),
(128, 'Mud therapy', 130, '15:00:00', '16:00:00', 10),
(129, 'Hydromassage', 150, '18:00:00', '19:00:00', 96),
(130, 'Baths', 100, '19:00:00', '21:00:00', 127);

-- --------------------------------------------------------

--
-- Table structure for table `procedures_schedule`
--

CREATE TABLE `procedures_schedule` (
  `day` int(11) NOT NULL,
  `ProcedureID` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `procedures_schedule`
--

INSERT INTO `procedures_schedule` (`day`, `ProcedureID`) VALUES
(1, 123),
(3, 123),
(5, 123),
(1, 124),
(2, 124),
(3, 124),
(4, 124),
(5, 124),
(6, 124),
(2, 125),
(4, 125),
(6, 125),
(1, 126),
(5, 126),
(1, 130),
(2, 130),
(3, 130),
(4, 130),
(5, 130),
(6, 130),
(7, 130),
(6, 127),
(7, 127),
(2, 128),
(4, 128),
(6, 129),
(7, 129);

-- --------------------------------------------------------

--
-- Table structure for table `purchase`
--

CREATE TABLE `purchase` (
  `PurchaseID` int(11) NOT NULL,
  `ClientID` int(11) NOT NULL,
  `ProcedureID` int(11) NOT NULL,
  `Date` date DEFAULT NULL,
  `Time` time DEFAULT NULL,
  `procedure_taken` tinyint(4) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `purchase`
--

INSERT INTO `purchase` (`PurchaseID`, `ClientID`, `ProcedureID`, `Date`, `Time`, `procedure_taken`) VALUES
(101, 1, 123, '2021-10-20', '08:05:00', 1),
(102, 3, 123, '2021-10-21', '08:10:00', 1),
(103, 6, 123, '2021-10-22', '08:15:00', 1),
(104, 2, 124, '2021-10-22', '10:01:00', 1),
(105, 3, 124, '2021-10-23', '10:15:00', 1),
(106, 5, 124, '2021-10-28', '10:20:00', 1),
(107, 7, 124, '2021-10-29', '10:27:00', 1),
(108, 9, 124, '2021-10-29', '10:08:00', 1),
(109, 10, 124, '2021-10-29', '10:10:00', 1),
(110, 5, 125, '2021-10-29', '11:00:00', 1),
(111, 1, 126, '2021-10-30', '13:30:00', 1),
(112, 2, 126, '2021-10-30', '13:45:00', 1),
(113, 7, 126, '2021-10-31', '13:07:00', 1),
(114, 9, 126, '2021-10-31', '13:50:00', 1),
(115, 4, 127, '2021-11-01', '14:20:00', 1),
(116, 8, 127, '2021-11-02', '14:15:00', 1),
(117, 6, 128, '2021-11-02', '15:50:00', 1),
(118, 8, 128, '2021-11-02', '08:00:00', 1),
(119, 4, 129, '2021-11-02', '18:32:00', 1),
(120, 10, 129, '2021-11-02', '18:05:00', 1),
(121, 2, 130, '2021-11-02', '18:00:00', 1),
(122, 4, 130, '2021-11-02', '20:08:00', 1),
(123, 8, 130, '2021-11-03', '20:30:00', 1),
(124, 10, 130, '2021-11-04', '19:20:00', 1),
(127, 2, 123, '2021-11-09', '22:20:00', 1),
(128, 3, 126, '2021-11-09', '22:28:00', 1),
(130, 4, 123, '2021-11-10', '00:30:00', 1),
(131, 5, 124, '2021-11-10', '00:31:00', 1),
(132, 10, 127, '2021-11-10', '01:14:00', 1),
(133, 9, 129, '2021-11-10', '01:29:00', 1),
(134, 7, 130, '2021-11-10', '13:58:00', 0),
(135, 6, 130, '2021-11-11', '12:40:00', 1),
(136, 8, 130, '2021-11-11', '13:27:00', 1),
(137, 1, 124, '2021-12-09', '20:01:47', 1);

-- --------------------------------------------------------

--
-- Table structure for table `purchase_worker`
--

CREATE TABLE `purchase_worker` (
  `PurchaseID` int(11) NOT NULL,
  `WorkerID` int(11) NOT NULL,
  `ProcedureID` int(11) NOT NULL,
  `Date` date DEFAULT NULL,
  `Time` time DEFAULT NULL,
  `procedure_taken` tinyint(4) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `purchase_worker`
--

INSERT INTO `purchase_worker` (`PurchaseID`, `WorkerID`, `ProcedureID`, `Date`, `Time`, `procedure_taken`) VALUES
(116, 222, 123, '2021-12-10', '22:22:00', 0);

-- --------------------------------------------------------

--
-- Table structure for table `reserved`
--

CREATE TABLE `reserved` (
  `ReservationN` int(11) NOT NULL,
  `ClientID` int(11) DEFAULT NULL,
  `RoomID` int(11) NOT NULL,
  `Arrived` date DEFAULT NULL,
  `Moved_out` date DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `reserved`
--

INSERT INTO `reserved` (`ReservationN`, `ClientID`, `RoomID`, `Arrived`, `Moved_out`) VALUES
(1, 1, 101, '2021-11-01', NULL),
(2, 3, 101, '2021-11-02', NULL),
(3, 2, 107, '2021-10-31', NULL),
(4, 4, 105, '2021-11-03', NULL),
(5, 10, 105, '2021-11-03', NULL),
(6, 5, 102, '2021-10-01', NULL),
(7, 6, 102, '2021-10-19', NULL),
(8, 8, 103, '2021-10-27', NULL),
(9, 7, 109, '2021-11-07', NULL),
(10, 9, 109, '2021-10-17', NULL);

-- --------------------------------------------------------

--
-- Table structure for table `room`
--

CREATE TABLE `room` (
  `RoomID` int(11) NOT NULL,
  `Persons` int(11) DEFAULT NULL,
  `Type` varchar(50) DEFAULT NULL,
  `Available_places` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `room`
--

INSERT INTO `room` (`RoomID`, `Persons`, `Type`, `Available_places`) VALUES
(101, 2, 'standart', 0),
(102, 2, 'standart', 0),
(103, 1, 'standart', 0),
(104, 1, 'standart', 1),
(105, 2, 'deluxe', 0),
(106, 2, 'deluxe', 2),
(107, 1, 'deluxe', 0),
(108, 1, 'deluxe', 1),
(109, 3, 'standart', 2),
(110, 3, 'standart', 3);

-- --------------------------------------------------------

--
-- Table structure for table `treatment`
--

CREATE TABLE `treatment` (
  `TreatmentID` int(11) NOT NULL,
  `ClientID` int(11) DEFAULT NULL,
  `ProcedureID` int(11) NOT NULL,
  `Date` date DEFAULT NULL,
  `Time` time DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `treatment`
--

INSERT INTO `treatment` (`TreatmentID`, `ClientID`, `ProcedureID`, `Date`, `Time`) VALUES
(101, 1, 123, '2021-11-01', '08:05:00'),
(102, 3, 123, '2021-11-01', '08:10:00'),
(103, 6, 123, '2021-11-01', '08:15:00'),
(104, 2, 124, '2021-11-01', '10:01:00'),
(105, 3, 124, '2021-11-02', '10:15:00'),
(106, 5, 124, '2021-11-02', '10:20:00'),
(107, 7, 124, '2021-11-02', '10:27:00'),
(108, 9, 124, '2021-11-02', '10:08:00'),
(109, 10, 124, '2021-11-02', '10:10:00'),
(110, 5, 125, '2021-11-02', '11:00:00'),
(111, 1, 126, '2021-11-05', '13:30:00'),
(112, 2, 126, '2021-11-05', '13:45:00'),
(113, 7, 126, '2021-11-01', '13:07:00'),
(114, 9, 126, '2021-11-05', '13:50:00'),
(115, 4, 127, '2021-11-06', '14:20:00'),
(116, 8, 127, '2021-11-07', '14:15:00'),
(117, 6, 128, '2021-11-04', '15:50:00'),
(118, 8, 128, '2021-11-02', '15:03:00'),
(119, 4, 129, '2021-11-06', '18:32:00'),
(120, 10, 129, '2021-11-07', '18:05:00'),
(121, 2, 130, '2021-11-02', '19:28:00'),
(122, 4, 130, '2021-11-03', '20:08:00'),
(123, 8, 130, '2021-11-04', '20:30:00'),
(124, 10, 130, '2021-11-05', '19:20:00'),
(125, 1, 124, '2021-11-06', '20:05:00');

-- --------------------------------------------------------

--
-- Table structure for table `worker`
--

CREATE TABLE `worker` (
  `WorkerID` int(3) NOT NULL,
  `Name` varchar(50) DEFAULT NULL,
  `position` varchar(50) DEFAULT NULL,
  `Login` varchar(50) DEFAULT NULL,
  `Password` varchar(50) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `worker`
--

INSERT INTO `worker` (`WorkerID`, `Name`, `position`, `Login`, `Password`) VALUES
(111, 'Adis', 'Manager', 'youha', '12345678'),
(222, 'Bekzhan', 'Assistant', 'bekzhansan', '87654321');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `client`
--
ALTER TABLE `client`
  ADD PRIMARY KEY (`IDclient`);

--
-- Indexes for table `client_login`
--
ALTER TABLE `client_login`
  ADD UNIQUE KEY `Login` (`Login`),
  ADD KEY `fk_clientIDlogin` (`ClientID`);

--
-- Indexes for table `procedures`
--
ALTER TABLE `procedures`
  ADD PRIMARY KEY (`ProcedureID`);

--
-- Indexes for table `procedures_schedule`
--
ALTER TABLE `procedures_schedule`
  ADD KEY `fk_procedureId` (`ProcedureID`);

--
-- Indexes for table `purchase`
--
ALTER TABLE `purchase`
  ADD PRIMARY KEY (`PurchaseID`),
  ADD UNIQUE KEY `TreatmentID_UNIQUE` (`PurchaseID`),
  ADD KEY `fk_procedure` (`ProcedureID`),
  ADD KEY `fk_client_purchase` (`ClientID`);

--
-- Indexes for table `purchase_worker`
--
ALTER TABLE `purchase_worker`
  ADD PRIMARY KEY (`PurchaseID`),
  ADD UNIQUE KEY `TreatmentID_UNIQUE` (`PurchaseID`),
  ADD KEY `fk_procedure` (`ProcedureID`),
  ADD KEY `fk_worker_purchase` (`WorkerID`);

--
-- Indexes for table `reserved`
--
ALTER TABLE `reserved`
  ADD PRIMARY KEY (`ReservationN`),
  ADD KEY `fk_roomID` (`RoomID`),
  ADD KEY `fk_clientID` (`ClientID`);

--
-- Indexes for table `room`
--
ALTER TABLE `room`
  ADD PRIMARY KEY (`RoomID`);

--
-- Indexes for table `treatment`
--
ALTER TABLE `treatment`
  ADD PRIMARY KEY (`TreatmentID`),
  ADD UNIQUE KEY `TreatmentID_UNIQUE` (`TreatmentID`),
  ADD KEY `fk_procedure` (`ProcedureID`),
  ADD KEY `fk_client` (`ClientID`);

--
-- Indexes for table `worker`
--
ALTER TABLE `worker`
  ADD PRIMARY KEY (`WorkerID`),
  ADD UNIQUE KEY `Login` (`Login`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `purchase`
--
ALTER TABLE `purchase`
  MODIFY `PurchaseID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=138;

--
-- AUTO_INCREMENT for table `purchase_worker`
--
ALTER TABLE `purchase_worker`
  MODIFY `PurchaseID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=117;

--
-- AUTO_INCREMENT for table `treatment`
--
ALTER TABLE `treatment`
  MODIFY `TreatmentID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=128;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `client_login`
--
ALTER TABLE `client_login`
  ADD CONSTRAINT `fk_clientIDlogin` FOREIGN KEY (`ClientID`) REFERENCES `client` (`IDclient`);

--
-- Constraints for table `procedures_schedule`
--
ALTER TABLE `procedures_schedule`
  ADD CONSTRAINT `fk_procedureId` FOREIGN KEY (`ProcedureID`) REFERENCES `procedures` (`ProcedureID`);

--
-- Constraints for table `purchase`
--
ALTER TABLE `purchase`
  ADD CONSTRAINT `fk_client_purchase` FOREIGN KEY (`ClientID`) REFERENCES `client` (`IDclient`),
  ADD CONSTRAINT `fk_proc_purchase` FOREIGN KEY (`ProcedureID`) REFERENCES `procedures` (`ProcedureID`);

--
-- Constraints for table `purchase_worker`
--
ALTER TABLE `purchase_worker`
  ADD CONSTRAINT `fk_procedure_purchase` FOREIGN KEY (`ProcedureID`) REFERENCES `procedures` (`ProcedureID`),
  ADD CONSTRAINT `fk_worker_purchase` FOREIGN KEY (`WorkerID`) REFERENCES `worker` (`WorkerID`);

--
-- Constraints for table `reserved`
--
ALTER TABLE `reserved`
  ADD CONSTRAINT `fk_clientID` FOREIGN KEY (`ClientID`) REFERENCES `client` (`IDclient`),
  ADD CONSTRAINT `fk_roomID` FOREIGN KEY (`RoomID`) REFERENCES `room` (`RoomID`);

--
-- Constraints for table `treatment`
--
ALTER TABLE `treatment`
  ADD CONSTRAINT `fk_client` FOREIGN KEY (`ClientID`) REFERENCES `client` (`IDclient`),
  ADD CONSTRAINT `fk_procedure` FOREIGN KEY (`ProcedureID`) REFERENCES `procedures` (`ProcedureID`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
