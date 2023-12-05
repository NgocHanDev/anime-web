-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Dec 05, 2023 at 09:21 AM
-- Server version: 10.4.27-MariaDB
-- PHP Version: 8.2.0

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `anime_web`
--

-- --------------------------------------------------------

--
-- Table structure for table `anime`
--

CREATE TABLE `anime` (
  `id` int(11) NOT NULL,
  `cover_image` longtext DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `release_year` int(11) DEFAULT NULL,
  `view` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `anime`
--

INSERT INTO `anime` (`id`, `cover_image`, `description`, `name`, `release_year`, `view`) VALUES
(1, 'url_to_cover_image_1', 'Description for Anime 1', 'Anime 1', 2020, 100000),
(2, 'url_to_cover_image_2', 'Description for Anime 2', 'Anime 2', 2021, 80000),
(3, 'url_to_cover_image_3', 'Description for Anime 3', 'Anime 3', 2019, 120000),
(4, 'url_to_cover_image_4', 'Description for Anime 4', 'Anime 4', 2022, 90000),
(5, 'url_to_cover_image_5', 'Description for Anime 5', 'Anime 5', 2018, 150000),
(6, 'url_to_cover_image_6', 'Description for Anime 6', 'Anime 6', 2020, 70000),
(7, 'url_to_cover_image_7', 'Description for Anime 7', 'Anime 7', 2021, 110000),
(8, 'url_to_cover_image_8', 'Description for Anime 8', 'Anime 8', 2017, 130000),
(9, 'url_to_cover_image_9', 'Description for Anime 9', 'Anime 9', 2022, 95000),
(10, 'url_to_cover_image_10', 'Description for Anime 10', 'Anime 10', 2016, 180000),
(11, 'url_to_cover_image_11', 'Description for Anime 11', 'Anime 11', 2023, 75000),
(12, 'url_to_cover_image_12', 'Description for Anime 12', 'Anime 12', 2020, 110000),
(13, 'url_to_cover_image_13', 'Description for Anime 13', 'Anime 13', 2019, 100000),
(14, 'url_to_cover_image_14', 'Description for Anime 14', 'Anime 14', 2021, 120000),
(15, 'url_to_cover_image_15', 'Description for Anime 15', 'Anime 15', 2018, 90000),
(16, 'url_to_cover_image_16', 'Description for Anime 16', 'Anime 16', 2022, 140000),
(17, 'url_to_cover_image_17', 'Description for Anime 17', 'Anime 17', 2017, 110000),
(18, 'url_to_cover_image_18', 'Description for Anime 18', 'Anime 18', 2023, 100000),
(19, 'url_to_cover_image_19', 'Description for Anime 19', 'Anime 19', 2020, 80000),
(20, 'url_to_cover_image_20', 'Description for Anime 20', 'Anime 20', 2021, 95000);

-- --------------------------------------------------------

--
-- Table structure for table `anime_category`
--

CREATE TABLE `anime_category` (
  `category_id` int(11) NOT NULL,
  `anime_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `anime_category`
--

INSERT INTO `anime_category` (`category_id`, `anime_id`) VALUES
(1, 1),
(5, 1),
(2, 2),
(7, 2),
(3, 3),
(6, 3),
(4, 4),
(1, 4),
(5, 5),
(2, 5),
(6, 6),
(3, 6),
(7, 7),
(4, 7),
(7, 8),
(5, 8),
(7, 9),
(7, 9),
(4, 10),
(3, 10),
(3, 11),
(6, 11),
(5, 12),
(1, 12),
(1, 13),
(4, 13),
(5, 14),
(5, 14),
(2, 15),
(2, 15),
(1, 16),
(6, 16),
(5, 17),
(3, 17),
(5, 18),
(1, 18),
(1, 19),
(4, 19),
(6, 20),
(5, 20);

-- --------------------------------------------------------

--
-- Table structure for table `category`
--

CREATE TABLE `category` (
  `id` int(11) NOT NULL,
  `category_name` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `category`
--

INSERT INTO `category` (`id`, `category_name`) VALUES
(1, 'Action'),
(2, 'Adventure'),
(3, 'Comedy'),
(4, 'Drama'),
(5, 'Fantasy'),
(6, 'Slice of Life'),
(7, 'Sci-Fi');

-- --------------------------------------------------------

--
-- Table structure for table `comment`
--

CREATE TABLE `comment` (
  `id` int(11) NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  `anime_id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `comment`
--

INSERT INTO `comment` (`id`, `description`, `anime_id`, `user_id`) VALUES
(1, 'Hay quo di', 1, 1);

-- --------------------------------------------------------

--
-- Table structure for table `episode`
--

CREATE TABLE `episode` (
  `id` int(11) NOT NULL,
  `episode_number` int(11) DEFAULT NULL,
  `source` varchar(255) DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  `anime_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `episode`
--

INSERT INTO `episode` (`id`, `episode_number`, `source`, `title`, `anime_id`) VALUES
(1, 1, 'Source 1', 'Episode 1 Title', 1),
(2, 2, 'Source 2', 'Episode 2 Title', 2),
(3, 3, 'Source 3', 'Episode 3 Title', 3),
(4, 4, 'Source 4', 'Episode 4 Title', 4),
(5, 5, 'Source 5', 'Episode 5 Title', 5),
(6, 6, 'Source 6', 'Episode 6 Title', 6),
(7, 7, 'Source 7', 'Episode 7 Title', 7),
(8, 8, 'Source 8', 'Episode 8 Title', 8),
(9, 9, 'Source 9', 'Episode 9 Title', 9),
(10, 10, 'Source 10', 'Episode 10 Title', 10),
(11, 11, 'Source 11', 'Episode 11 Title', 11),
(12, 12, 'Source 12', 'Episode 12 Title', 12),
(13, 13, 'Source 13', 'Episode 13 Title', 13),
(14, 14, 'Source 14', 'Episode 14 Title', 14),
(15, 15, 'Source 15', 'Episode 15 Title', 15),
(16, 16, 'Source 16', 'Episode 16 Title', 16),
(17, 17, 'Source 17', 'Episode 17 Title', 17),
(18, 18, 'Source 18', 'Episode 18 Title', 18),
(19, 19, 'Source 19', 'Episode 19 Title', 19),
(20, 20, 'Source 20', 'Episode 20 Title', 20);

-- --------------------------------------------------------

--
-- Table structure for table `favorite_anime`
--

CREATE TABLE `favorite_anime` (
  `id` int(11) NOT NULL,
  `anime_id` int(11) DEFAULT NULL,
  `user_id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `role`
--

CREATE TABLE `role` (
  `id` int(11) NOT NULL,
  `role_name` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `role`
--

INSERT INTO `role` (`id`, `role_name`) VALUES
(1, 'ADMIN');

-- --------------------------------------------------------

--
-- Table structure for table `user`
--

CREATE TABLE `user` (
  `id` int(11) NOT NULL,
  `email` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `user_name` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `user`
--

INSERT INTO `user` (`id`, `email`, `password`, `user_name`) VALUES
(1, 'cucshit2k4@gmail.com', '$2a$12$lSw.Mo4W0Zrh9Q32FVfIOejq9lr.oCUSO5Q29fhPhMqrpRkYZ0Nh6', 'admin'),
(2, NULL, '$2a$10$lgmJAYStiB0g.Tb7RpYIT.mcKAwU44j6HFad1hhT7GLX90UMHkUou', 'user'),
(3, NULL, '$2a$12$lSw.Mo4W0Zrh9Q32FVfIOejq9lr.oCUSO5Q29fhPhMqrpRkYZ0Nh6', 'anhhandeptrai');

-- --------------------------------------------------------

--
-- Table structure for table `user_role`
--

CREATE TABLE `user_role` (
  `user_id` int(11) NOT NULL,
  `role_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `user_role`
--

INSERT INTO `user_role` (`user_id`, `role_id`) VALUES
(1, 1);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `anime`
--
ALTER TABLE `anime`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `anime_category`
--
ALTER TABLE `anime_category`
  ADD KEY `FKnetm04paoc6ccsbfaidoomwj9` (`anime_id`),
  ADD KEY `FKb48u1b317i13tdv9g1plwbqqy` (`category_id`);

--
-- Indexes for table `category`
--
ALTER TABLE `category`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `comment`
--
ALTER TABLE `comment`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FK2gsad27q3ni66pqpcko7nelp4` (`anime_id`),
  ADD KEY `FK8kcum44fvpupyw6f5baccx25c` (`user_id`);

--
-- Indexes for table `episode`
--
ALTER TABLE `episode`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKff8600eu8cj98mtididvgpfld` (`anime_id`);

--
-- Indexes for table `favorite_anime`
--
ALTER TABLE `favorite_anime`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKnmlirpc8r14q4o1x6duktgdr3` (`anime_id`),
  ADD KEY `FKrkjh9x2l6fp9i3oc4jnxl0lte` (`user_id`);

--
-- Indexes for table `role`
--
ALTER TABLE `role`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `user_role`
--
ALTER TABLE `user_role`
  ADD KEY `FKa68196081fvovjhkek5m97n3y` (`role_id`),
  ADD KEY `FK859n2jvi8ivhui0rl0esws6o` (`user_id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `anime`
--
ALTER TABLE `anime`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=21;

--
-- AUTO_INCREMENT for table `category`
--
ALTER TABLE `category`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- AUTO_INCREMENT for table `comment`
--
ALTER TABLE `comment`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `episode`
--
ALTER TABLE `episode`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=21;

--
-- AUTO_INCREMENT for table `favorite_anime`
--
ALTER TABLE `favorite_anime`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- AUTO_INCREMENT for table `role`
--
ALTER TABLE `role`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `user`
--
ALTER TABLE `user`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `anime_category`
--
ALTER TABLE `anime_category`
  ADD CONSTRAINT `FKb48u1b317i13tdv9g1plwbqqy` FOREIGN KEY (`category_id`) REFERENCES `category` (`id`),
  ADD CONSTRAINT `FKnetm04paoc6ccsbfaidoomwj9` FOREIGN KEY (`anime_id`) REFERENCES `anime` (`id`);

--
-- Constraints for table `comment`
--
ALTER TABLE `comment`
  ADD CONSTRAINT `FK2gsad27q3ni66pqpcko7nelp4` FOREIGN KEY (`anime_id`) REFERENCES `anime` (`id`),
  ADD CONSTRAINT `FK8kcum44fvpupyw6f5baccx25c` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`);

--
-- Constraints for table `episode`
--
ALTER TABLE `episode`
  ADD CONSTRAINT `FKff8600eu8cj98mtididvgpfld` FOREIGN KEY (`anime_id`) REFERENCES `anime` (`id`);

--
-- Constraints for table `favorite_anime`
--
ALTER TABLE `favorite_anime`
  ADD CONSTRAINT `FKnmlirpc8r14q4o1x6duktgdr3` FOREIGN KEY (`anime_id`) REFERENCES `anime` (`id`),
  ADD CONSTRAINT `FKrkjh9x2l6fp9i3oc4jnxl0lte` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`);

--
-- Constraints for table `user_role`
--
ALTER TABLE `user_role`
  ADD CONSTRAINT `FK859n2jvi8ivhui0rl0esws6o` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`),
  ADD CONSTRAINT `FKa68196081fvovjhkek5m97n3y` FOREIGN KEY (`role_id`) REFERENCES `role` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
