-- phpMyAdmin SQL Dump
-- version 5.1.1
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 25-05-2022 a las 08:15:57
-- Versión del servidor: 10.4.20-MariaDB
-- Versión de PHP: 8.0.9

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `ejemplo`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `app_logs`
--

CREATE TABLE `app_logs` (
  `LOG_ID` varchar(100) NOT NULL,
  `ENTRY_DATE` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp(),
  `LOGGER` varchar(100) DEFAULT NULL,
  `LOG_LEVEL` varchar(100) DEFAULT NULL,
  `MESSAGE` text DEFAULT NULL,
  `EXCEPTION` text DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `app_logs`
--

INSERT INTO `app_logs` (`LOG_ID`, `ENTRY_DATE`, `LOGGER`, `LOG_LEVEL`, `MESSAGE`, `EXCEPTION`) VALUES
('1a802a41-dbea-11ec-83cc-fc7774b47cce', '2022-05-25 05:18:23', 'main.MainApp', 'DEBUG', 'Never gonna let you down', ' '),
('1a95d522-dbea-11ec-83cc-fc7774b47cce', '2022-05-25 05:18:25', 'main.MainApp', 'INFO', 'Never gonna run around and desert you', ' '),
('1a9b0543-dbea-11ec-83cc-fc7774b47cce', '2022-05-25 05:18:25', 'main.MainApp', 'WARN', 'Never gonna make you cry', ' '),
('1a9e1284-dbea-11ec-83cc-fc7774b47cce', '2022-05-25 05:18:25', 'main.MainApp', 'ERROR', 'Never gonna say goodbye', ' '),
('1aa0f8b5-dbea-11ec-83cc-fc7774b47cce', '2022-05-25 05:18:25', 'main.MainApp', 'FATAL', 'Never gonna tell a lie and hurt you', ' '),
('1aa6c516-dbea-11ec-83cc-fc7774b47cce', '2022-05-25 05:18:25', 'main.MainApp', 'ERROR', 'Division by 0 :(', 'java.lang.ArithmeticException: / by zero\r\n	at main.MainApp.main(MainApp.java:25)\r\n ');

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `app_logs`
--
ALTER TABLE `app_logs`
  ADD PRIMARY KEY (`LOG_ID`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
