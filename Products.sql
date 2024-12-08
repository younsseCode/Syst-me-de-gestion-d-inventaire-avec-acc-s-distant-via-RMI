-- -------------------------------------------------------------
-- -------------------------------------------------------------
-- TablePlus 1.2.2
--
-- https://tableplus.com/
--
-- Database: Invontaire
-- Generation Time: 2024-12-08 23:08:04.696462
-- -------------------------------------------------------------

CREATE TABLE `Products` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nom` varchar(255) NOT NULL,
  `Categorie` varchar(255) NOT NULL,
  `Quantite` int(11) NOT NULL,
  `Prix` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

INSERT INTO `Invontaire`.`Products` (`id`, `nom`, `Categorie`, `Quantite`, `Prix`) VALUES 
(2, 'Samsung', 'telephone', 12, 500),
(4, 'Iphone', 'Telephone', 10, 500),
(5, 'iphone18', 'Telephone', 10, 7000);

