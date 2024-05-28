-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: May 27, 2024 at 10:20 AM
-- Server version: 10.4.32-MariaDB
-- PHP Version: 8.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `film_db`
--

-- --------------------------------------------------------

--
-- Table structure for table `film`
--

CREATE TABLE `film` (
  `idFilm` int(11) NOT NULL,
  `judul` varchar(100) NOT NULL,
  `genre` varchar(100) NOT NULL,
  `tahun` varchar(4) NOT NULL,
  `durasi` varchar(20) NOT NULL,
  `poster` longtext NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `film`
--

INSERT INTO `film` (`idFilm`, `judul`, `genre`, `tahun`, `durasi`, `poster`) VALUES
(1, 'How to Make Millions Before Grandma Dies', 'Drama', '2024', '2 jam 5 menit', 'https://cdn.rri.co.id/berita/Manokwari/o/1716534388789-OIP/g3xfwf6knokvpqz.jpeg');

-- --------------------------------------------------------

--
-- Table structure for table `pegawai`
--

CREATE TABLE `pegawai` (
  `idPegawai` int(11) NOT NULL,
  `nama` varchar(100) NOT NULL,
  `nim` varchar(9) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `pegawai`
--

INSERT INTO `pegawai` (`idPegawai`, `nama`, `nim`) VALUES
(1, 'Erllyta', '123220008'),
(2, 'Laras', '123220081');

-- --------------------------------------------------------

--
-- Table structure for table `tiket`
--

CREATE TABLE `tiket` (
  `idTiket` int(11) NOT NULL,
  `idFilm` int(11) NOT NULL,
  `idPegawai` int(11) NOT NULL,
  `tanggal` date NOT NULL,
  `jam` time NOT NULL,
  `jumlahTiket` int(11) NOT NULL,
  `studio` varchar(100) NOT NULL,
  `kursi` text NOT NULL,
  `bill` int(11) NOT NULL,
  `metodePembayaran` varchar(100) NOT NULL,
  `createdAt` datetime NOT NULL DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `tiket`
--

INSERT INTO `tiket` (`idTiket`, `idFilm`, `idPegawai`, `tanggal`, `jam`, `jumlahTiket`, `studio`, `kursi`, `bill`, `metodePembayaran`, `createdAt`) VALUES
(1, 1, 2, '2024-05-15', '22:00:00', 2, '2D', 'J13, J14', 70000, 'QRis', '2024-05-25 22:02:07'),
(4, 1, 2, '2024-05-12', '10:00:00', 2, '2D', 'J12, J13', 70000, 'Tunai', '2024-05-26 15:36:52'),
(5, 1, 2, '2024-07-10', '13:00:00', 3, '4D', 'E12, E13, E14, E15', 210000, 'M-Banking', '2024-05-26 22:18:21');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `film`
--
ALTER TABLE `film`
  ADD PRIMARY KEY (`idFilm`);

--
-- Indexes for table `pegawai`
--
ALTER TABLE `pegawai`
  ADD PRIMARY KEY (`idPegawai`);

--
-- Indexes for table `tiket`
--
ALTER TABLE `tiket`
  ADD PRIMARY KEY (`idTiket`),
  ADD KEY `idPegawai` (`idPegawai`),
  ADD KEY `idFilm` (`idFilm`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `film`
--
ALTER TABLE `film`
  MODIFY `idFilm` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `pegawai`
--
ALTER TABLE `pegawai`
  MODIFY `idPegawai` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `tiket`
--
ALTER TABLE `tiket`
  MODIFY `idTiket` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `tiket`
--
ALTER TABLE `tiket`
  ADD CONSTRAINT `tiket_ibfk_1` FOREIGN KEY (`idPegawai`) REFERENCES `pegawai` (`idPegawai`),
  ADD CONSTRAINT `tiket_ibfk_2` FOREIGN KEY (`idFilm`) REFERENCES `film` (`idFilm`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
