-- phpMyAdmin SQL Dump
-- version 4.0.6deb1
-- http://www.phpmyadmin.net
--
-- Host: localhost
-- Generation Time: Mar 04, 2014 at 02:46 AM
-- Server version: 5.5.35-0ubuntu0.13.10.2
-- PHP Version: 5.5.3-1ubuntu2.1

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `flooring_mastery`
--

-- --------------------------------------------------------

--
-- Table structure for table `orders`
--

CREATE TABLE IF NOT EXISTS `orders` (
  `order_id` int(11) NOT NULL AUTO_INCREMENT,
  `date` varchar(8) NOT NULL,
  `name` varchar(20) NOT NULL,
  `state` varchar(2) NOT NULL,
  `tax_rate` float NOT NULL,
  `product_type` varchar(15) NOT NULL,
  `area` float NOT NULL,
  `cpsqft` float NOT NULL,
  `lcpsqft` float NOT NULL,
  `material_cost` float NOT NULL,
  `labor_cost` float NOT NULL,
  `tax` float NOT NULL,
  `total` float NOT NULL,
  PRIMARY KEY (`order_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=7 ;

--
-- Dumping data for table `orders`
--

INSERT INTO `orders` (`order_id`, `date`, `name`, `state`, `tax_rate`, `product_type`, `area`, `cpsqft`, `lcpsqft`, `material_cost`, `labor_cost`, `tax`, `total`) VALUES
(2, '12251990', 'Alex', 'OH', 6, 'carpet', 123, 2, 2, 246, 246, 29.52, 521.52),
(3, '01252014', 'Alex Kaidan', 'MI', 5, 'laminate', 35, 1, 2, 35, 70, 5.25, 110.25),
(4, '01252014', 'Bob Saiget', 'OH', 6, 'carpet', 15, 2, 2, 30, 30, 3.6, 63.6),
(5, '01252014', 'Dany Boi', 'WI', 1, 'tile', 32, 3, 4, 96, 128, 2.24, 226.24),
(6, '03032014', 'Mike Kofferson', 'IN', 6, 'tile', 65, 3, 4, 195, 260, 27.3, 482.3);

-- --------------------------------------------------------

--
-- Table structure for table `products`
--

CREATE TABLE IF NOT EXISTS `products` (
  `prod_id` int(11) NOT NULL AUTO_INCREMENT,
  `prod_type` varchar(15) NOT NULL,
  `prod_cost` float NOT NULL,
  `prod_labor_cost` float NOT NULL,
  PRIMARY KEY (`prod_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=5 ;

--
-- Dumping data for table `products`
--

INSERT INTO `products` (`prod_id`, `prod_type`, `prod_cost`, `prod_labor_cost`) VALUES
(1, 'carpet', 2.25, 2.1),
(2, 'laminate', 1.75, 2.1),
(3, 'tile', 3.5, 4.15),
(4, 'wood', 5.15, 4.75);

-- --------------------------------------------------------

--
-- Table structure for table `taxes`
--

CREATE TABLE IF NOT EXISTS `taxes` (
  `tax_id` int(11) NOT NULL AUTO_INCREMENT,
  `tax_state` varchar(2) NOT NULL,
  `tax_rate` float NOT NULL,
  PRIMARY KEY (`tax_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=6 ;

--
-- Dumping data for table `taxes`
--

INSERT INTO `taxes` (`tax_id`, `tax_state`, `tax_rate`) VALUES
(1, 'OH', 6.25),
(2, 'PA', 6.75),
(3, 'MI', 5.75),
(4, 'IN', 6),
(5, 'WI', 1);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
