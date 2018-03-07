-- phpMyAdmin SQL Dump
-- version 4.1.14
-- http://www.phpmyadmin.net
--
-- Client :  127.0.0.1
-- Généré le :  Sam 03 Mars 2018 à 16:08
-- Version du serveur :  5.6.17
-- Version de PHP :  5.5.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Base de données :  `servicemarket`
--

-- --------------------------------------------------------

--
-- Structure de la table `categorie`
--

CREATE TABLE IF NOT EXISTS `categorie` (
  `NOM` varchar(255) NOT NULL,
  PRIMARY KEY (`NOM`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Contenu de la table `categorie`
--

INSERT INTO `categorie` (`NOM`) VALUES
('déménagement et stockage'),
('dératisation'),
('événementiel'),
('location de voiture'),
('nettoyage'),
('photographie'),
('travaux de maison');

-- --------------------------------------------------------

--
-- Structure de la table `child`
--

CREATE TABLE IF NOT EXISTS `child` (
  `ID` bigint(20) NOT NULL,
  `AGE` int(11) DEFAULT NULL,
  `DEMANDEBABYSITTING_ID` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `FK_CHILD_DEMANDEBABYSITTING_ID` (`DEMANDEBABYSITTING_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `childdemandebabysitting`
--

CREATE TABLE IF NOT EXISTS `childdemandebabysitting` (
  `ID` bigint(20) NOT NULL,
  `AGE` int(11) DEFAULT NULL,
  `DEMANDEBABYSITTING_ID` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `FK_CHILDDEMANDEBABYSITTING_DEMANDEBABYSITTING_ID` (`DEMANDEBABYSITTING_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `client`
--

CREATE TABLE IF NOT EXISTS `client` (
  `ID` varchar(255) NOT NULL,
  `ADRESSECOMPLEMENT` varchar(255) DEFAULT NULL,
  `EMAIL` varchar(255) DEFAULT NULL,
  `NOM` varchar(255) DEFAULT NULL,
  `PASSWORD` varchar(255) DEFAULT NULL,
  `PHONE` varchar(255) DEFAULT NULL,
  `PRENOM` varchar(255) DEFAULT NULL,
  `SECTEUR_ID` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `FK_CLIENT_SECTEUR_ID` (`SECTEUR_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `cuisine`
--

CREATE TABLE IF NOT EXISTS `cuisine` (
  `ID` bigint(20) NOT NULL,
  `NOM` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `cuisinedemandeevent`
--

CREATE TABLE IF NOT EXISTS `cuisinedemandeevent` (
  `ID` bigint(20) NOT NULL,
  `CUISINE_ID` bigint(20) DEFAULT NULL,
  `DEMANDEEVENT_ID` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `FK_CUISINEDEMANDEEVENT_DEMANDEEVENT_ID` (`DEMANDEEVENT_ID`),
  KEY `FK_CUISINEDEMANDEEVENT_CUISINE_ID` (`CUISINE_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `cuisinetype`
--

CREATE TABLE IF NOT EXISTS `cuisinetype` (
  `ID` bigint(20) NOT NULL,
  `NOM` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Contenu de la table `cuisinetype`
--

INSERT INTO `cuisinetype` (`ID`, `NOM`) VALUES
(1, 'cocktails'),
(2, 'asiatique'),
(3, 'menu enfant'),
(4, 'barbecue'),
(5, 'végétarien'),
(6, 'continental'),
(7, 'dessert'),
(8, 'alimentation de fête'),
(9, 'fusion'),
(10, 'nourriture saine'),
(11, 'indien'),
(12, 'italien'),
(13, 'méditerranéen'),
(14, 'mexicain'),
(15, 'autre');

-- --------------------------------------------------------

--
-- Structure de la table `day`
--

CREATE TABLE IF NOT EXISTS `day` (
  `ID` bigint(20) NOT NULL,
  `NOM` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Contenu de la table `day`
--

INSERT INTO `day` (`ID`, `NOM`) VALUES
(17, 'lundi'),
(18, 'mardi'),
(19, 'mercredi'),
(20, 'jeudi'),
(21, 'vendredi'),
(22, 'samedi'),
(23, 'dimanche');

-- --------------------------------------------------------

--
-- Structure de la table `demandebabysitting`
--

CREATE TABLE IF NOT EXISTS `demandebabysitting` (
  `ID` bigint(20) NOT NULL,
  `DETAIL` varchar(255) DEFAULT NULL,
  `FULLTIME` tinyint(1) DEFAULT '0',
  `NBRHEURES` decimal(38,0) DEFAULT NULL,
  `DEMANDESERVICE_ID` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `FK_DEMANDEBABYSITTING_DEMANDESERVICE_ID` (`DEMANDESERVICE_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `demandecleaning`
--

CREATE TABLE IF NOT EXISTS `demandecleaning` (
  `ID` bigint(20) NOT NULL,
  `BRINGEQUIPEMENT` tinyint(1) DEFAULT '0',
  `NBRCLEANER` int(11) DEFAULT NULL,
  `NBRHEURES` decimal(38,0) DEFAULT NULL,
  `DEMANDESERVICE_ID` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `FK_DEMANDECLEANING_DEMANDESERVICE_ID` (`DEMANDESERVICE_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `demandeevent`
--

CREATE TABLE IF NOT EXISTS `demandeevent` (
  `ID` bigint(20) NOT NULL,
  `DETAIL` varchar(255) DEFAULT NULL,
  `NBRINVITES` int(11) DEFAULT NULL,
  `DEMANDESERVICE_ID` bigint(20) DEFAULT NULL,
  `EVENT_ID` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `FK_DEMANDEEVENT_EVENT_ID` (`EVENT_ID`),
  KEY `FK_DEMANDEEVENT_DEMANDESERVICE_ID` (`DEMANDESERVICE_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `demandegardening`
--

CREATE TABLE IF NOT EXISTS `demandegardening` (
  `ID` bigint(20) NOT NULL,
  `DEMANDESERVICE_ID` bigint(20) DEFAULT NULL,
  `GARDENINGTYPE_ID` bigint(20) DEFAULT NULL,
  `HOME_ID` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `FK_DEMANDEGARDENING_GARDENINGTYPE_ID` (`GARDENINGTYPE_ID`),
  KEY `FK_DEMANDEGARDENING_DEMANDESERVICE_ID` (`DEMANDESERVICE_ID`),
  KEY `FK_DEMANDEGARDENING_HOME_ID` (`HOME_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `demandehandyman`
--

CREATE TABLE IF NOT EXISTS `demandehandyman` (
  `ID` bigint(20) NOT NULL,
  `DEMANDESERVICE_ID` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `FK_DEMANDEHANDYMAN_DEMANDESERVICE_ID` (`DEMANDESERVICE_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `demandemoving`
--

CREATE TABLE IF NOT EXISTS `demandemoving` (
  `ADRESSEARRIVE` varchar(255) DEFAULT NULL,
  `ADRESSEDEPART` varchar(255) DEFAULT NULL,
  `HANDYMAN` tinyint(1) DEFAULT '0',
  `ID` bigint(20) DEFAULT NULL,
  `STORAGE` tinyint(1) DEFAULT '0',
  `DEMANDESERVICE_ID` bigint(20) NOT NULL,
  `VILLEARRIVE_ID` bigint(20) DEFAULT NULL,
  `VILLEDEPART_ID` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`DEMANDESERVICE_ID`),
  KEY `FK_DEMANDEMOVING_VILLEDEPART_ID` (`VILLEDEPART_ID`),
  KEY `FK_DEMANDEMOVING_VILLEARRIVE_ID` (`VILLEARRIVE_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `demandepainting`
--

CREATE TABLE IF NOT EXISTS `demandepainting` (
  `ID` bigint(20) NOT NULL,
  `DETAIL` varchar(255) DEFAULT NULL,
  `DEMANDESERVICE_ID` bigint(20) DEFAULT NULL,
  `PAINTING_ID` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `FK_DEMANDEPAINTING_PAINTING_ID` (`PAINTING_ID`),
  KEY `FK_DEMANDEPAINTING_DEMANDESERVICE_ID` (`DEMANDESERVICE_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `demandepestcontrol`
--

CREATE TABLE IF NOT EXISTS `demandepestcontrol` (
  `ID` bigint(20) NOT NULL,
  `DETAIL` varchar(255) DEFAULT NULL,
  `DEMANDESERVICE_ID` bigint(20) DEFAULT NULL,
  `TYPEOFPESTCONTROL_ID` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `FK_DEMANDEPESTCONTROL_DEMANDESERVICE_ID` (`DEMANDESERVICE_ID`),
  KEY `FK_DEMANDEPESTCONTROL_TYPEOFPESTCONTROL_ID` (`TYPEOFPESTCONTROL_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `demandephotographie`
--

CREATE TABLE IF NOT EXISTS `demandephotographie` (
  `ID` bigint(20) NOT NULL,
  `VIDEOGRAPHIE` tinyint(1) DEFAULT '0',
  `DEMANDESERVICE_ID` bigint(20) DEFAULT NULL,
  `TYPEPHOTOGRAPHIE_ID` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `FK_DEMANDEPHOTOGRAPHIE_TYPEPHOTOGRAPHIE_ID` (`TYPEPHOTOGRAPHIE_ID`),
  KEY `FK_DEMANDEPHOTOGRAPHIE_DEMANDESERVICE_ID` (`DEMANDESERVICE_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `demandeservice`
--

CREATE TABLE IF NOT EXISTS `demandeservice` (
  `ID` bigint(20) NOT NULL,
  `DATECONFIRMATION` date DEFAULT NULL,
  `DATEDERNIERMODIF` date DEFAULT NULL,
  `DATESUPPRESSION` date DEFAULT NULL,
  `DATEDEMANDE` date DEFAULT NULL,
  `DETAIL` varchar(255) DEFAULT NULL,
  `PRIXHT` decimal(38,0) DEFAULT NULL,
  `PRIXTTC` decimal(38,0) DEFAULT NULL,
  `CLIENT_ID` varchar(255) DEFAULT NULL,
  `MANAGERCONFIRMATION_ID` varchar(255) DEFAULT NULL,
  `PLANNING_ID` bigint(20) DEFAULT NULL,
  `SECTEUR_ID` bigint(20) DEFAULT NULL,
  `SERVICE_ID` bigint(20) DEFAULT NULL,
  `SERVICEPRICING_ID` bigint(20) DEFAULT NULL,
  `SOCIETE_ID` bigint(20) DEFAULT NULL,
  `TIMING_ID` bigint(20) DEFAULT NULL,
  `TYPEDEMANDE_ID` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `FK_DEMANDESERVICE_CLIENT_ID` (`CLIENT_ID`),
  KEY `FK_DEMANDESERVICE_SOCIETE_ID` (`SOCIETE_ID`),
  KEY `FK_DEMANDESERVICE_TYPEDEMANDE_ID` (`TYPEDEMANDE_ID`),
  KEY `FK_DEMANDESERVICE_MANAGERCONFIRMATION_ID` (`MANAGERCONFIRMATION_ID`),
  KEY `FK_DEMANDESERVICE_PLANNING_ID` (`PLANNING_ID`),
  KEY `FK_DEMANDESERVICE_SERVICE_ID` (`SERVICE_ID`),
  KEY `FK_DEMANDESERVICE_TIMING_ID` (`TIMING_ID`),
  KEY `FK_DEMANDESERVICE_SECTEUR_ID` (`SECTEUR_ID`),
  KEY `FK_DEMANDESERVICE_SERVICEPRICING_ID` (`SERVICEPRICING_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `demandeserviceconfirmationdetail`
--

CREATE TABLE IF NOT EXISTS `demandeserviceconfirmationdetail` (
  `ID` bigint(20) NOT NULL,
  `DATEACTION` date DEFAULT NULL,
  `DEMANDESERVICE_ID` bigint(20) DEFAULT NULL,
  `MANAGER_ID` varchar(255) DEFAULT NULL,
  `TYPEACTION_ID` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `FK_DEMANDESERVICECONFIRMATIONDETAIL_TYPEACTION_ID` (`TYPEACTION_ID`),
  KEY `DEMANDESERVICECONFIRMATIONDETAIL_DEMANDESERVICE_ID` (`DEMANDESERVICE_ID`),
  KEY `FK_DEMANDESERVICECONFIRMATIONDETAIL_MANAGER_ID` (`MANAGER_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `demandevoiture`
--

CREATE TABLE IF NOT EXISTS `demandevoiture` (
  `ID` bigint(20) NOT NULL,
  `DEMANDESERVICE_ID` bigint(20) DEFAULT NULL,
  `VOITURE_ID` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `FK_DEMANDEVOITURE_VOITURE_ID` (`VOITURE_ID`),
  KEY `FK_DEMANDEVOITURE_DEMANDESERVICE_ID` (`DEMANDESERVICE_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `event`
--

CREATE TABLE IF NOT EXISTS `event` (
  `ID` bigint(20) NOT NULL,
  `NOM` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `eventtype`
--

CREATE TABLE IF NOT EXISTS `eventtype` (
  `ID` bigint(20) NOT NULL,
  `NOM` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `gardeningtype`
--

CREATE TABLE IF NOT EXISTS `gardeningtype` (
  `ID` bigint(20) NOT NULL,
  `NOM` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Contenu de la table `gardeningtype`
--

INSERT INTO `gardeningtype` (`ID`, `NOM`) VALUES
(24, 'entretien du jardin'),
(25, 'gazon et pelouses artificielles');

-- --------------------------------------------------------

--
-- Structure de la table `home`
--

CREATE TABLE IF NOT EXISTS `home` (
  `ID` bigint(20) NOT NULL,
  `NOMBREPIECES` int(11) DEFAULT NULL,
  `HOMETYPE_ID` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `FK_HOME_HOMETYPE_ID` (`HOMETYPE_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `hometype`
--

CREATE TABLE IF NOT EXISTS `hometype` (
  `ID` bigint(20) NOT NULL,
  `NOM` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Contenu de la table `hometype`
--

INSERT INTO `hometype` (`ID`, `NOM`) VALUES
(26, 'studio'),
(27, 'appartement'),
(28, 'maison'),
(29, 'villa'),
(30, 'bureau'),
(31, 'autre');

-- --------------------------------------------------------

--
-- Structure de la table `manager`
--

CREATE TABLE IF NOT EXISTS `manager` (
  `ID` varchar(255) NOT NULL,
  `PASSWORD` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `owner`
--

CREATE TABLE IF NOT EXISTS `owner` (
  `ID` bigint(20) NOT NULL,
  `EMAIL` varchar(255) DEFAULT NULL,
  `NOM` varchar(255) DEFAULT NULL,
  `PHONE` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `packaging`
--

CREATE TABLE IF NOT EXISTS `packaging` (
  `ID` bigint(20) NOT NULL,
  `NAME` varchar(255) DEFAULT NULL,
  `SERVICE_ID` bigint(20) DEFAULT NULL,
  `SERVICEPRICING_ID` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `FK_PACKAGING_SERVICE_ID` (`SERVICE_ID`),
  KEY `FK_PACKAGING_SERVICEPRICING_ID` (`SERVICEPRICING_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `painting`
--

CREATE TABLE IF NOT EXISTS `painting` (
  `ID` bigint(20) NOT NULL,
  `NOM` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `paintingtype`
--

CREATE TABLE IF NOT EXISTS `paintingtype` (
  `ID` bigint(20) NOT NULL,
  `NOM` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Contenu de la table `paintingtype`
--

INSERT INTO `paintingtype` (`ID`, `NOM`) VALUES
(32, 'peinture extérieure'),
(33, 'peinture accueil'),
(34, 'peinture bureau'),
(35, 'peinture chambre individuelles');

-- --------------------------------------------------------

--
-- Structure de la table `pays`
--

CREATE TABLE IF NOT EXISTS `pays` (
  `ID` bigint(20) NOT NULL,
  `NAME` varchar(255) DEFAULT NULL,
  `TVA` decimal(38,0) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Contenu de la table `pays`
--

INSERT INTO `pays` (`ID`, `NAME`, `TVA`) VALUES
(101, 'maroc', '0');

-- --------------------------------------------------------

--
-- Structure de la table `pestcontrol`
--

CREATE TABLE IF NOT EXISTS `pestcontrol` (
  `ID` bigint(20) NOT NULL,
  `NOM` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `pestcontroltype`
--

CREATE TABLE IF NOT EXISTS `pestcontroltype` (
  `ID` bigint(20) NOT NULL,
  `NOM` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `photographie`
--

CREATE TABLE IF NOT EXISTS `photographie` (
  `ID` bigint(20) NOT NULL,
  `NOM` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `photographietype`
--

CREATE TABLE IF NOT EXISTS `photographietype` (
  `ID` bigint(20) NOT NULL,
  `NOM` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `planning`
--

CREATE TABLE IF NOT EXISTS `planning` (
  `ID` bigint(20) NOT NULL,
  `DATEDEBUT` date DEFAULT NULL,
  `DATEFIN` date DEFAULT NULL,
  `DATEONCE` date DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `planningitem`
--

CREATE TABLE IF NOT EXISTS `planningitem` (
  `ID` bigint(20) NOT NULL,
  `DAY_ID` bigint(20) DEFAULT NULL,
  `PLANNING_ID` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `FK_PLANNINGITEM_PLANNING_ID` (`PLANNING_ID`),
  KEY `FK_PLANNINGITEM_DAY_ID` (`DAY_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `review`
--

CREATE TABLE IF NOT EXISTS `review` (
  `ID` bigint(20) NOT NULL,
  `DATEREVIEW` date DEFAULT NULL,
  `STARS` int(11) DEFAULT NULL,
  `TEXT` varchar(255) DEFAULT NULL,
  `SOCIETE_ID` bigint(20) DEFAULT NULL,
  `CLIENT_ID` varchar(255) DEFAULT NULL,
  `SERVICE_ID` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `FK_REVIEW_SERVICE_ID` (`SERVICE_ID`),
  KEY `FK_REVIEW_CLIENT_ID` (`CLIENT_ID`),
  KEY `FK_REVIEW_SOCIETE_ID` (`SOCIETE_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `secteur`
--

CREATE TABLE IF NOT EXISTS `secteur` (
  `ID` bigint(20) NOT NULL,
  `NOM` varchar(255) DEFAULT NULL,
  `VILLE_ID` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `FK_SECTEUR_VILLE_ID` (`VILLE_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `sequence`
--

CREATE TABLE IF NOT EXISTS `sequence` (
  `SEQ_NAME` varchar(50) NOT NULL,
  `SEQ_COUNT` decimal(38,0) DEFAULT NULL,
  PRIMARY KEY (`SEQ_NAME`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Contenu de la table `sequence`
--

INSERT INTO `sequence` (`SEQ_NAME`, `SEQ_COUNT`) VALUES
('SEQ_GEN', '150');

-- --------------------------------------------------------

--
-- Structure de la table `service`
--

CREATE TABLE IF NOT EXISTS `service` (
  `ID` bigint(20) NOT NULL,
  `NOM` varchar(255) DEFAULT NULL,
  `CATEGORIE_NOM` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `FK_SERVICE_CATEGORIE_NOM` (`CATEGORIE_NOM`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Contenu de la table `service`
--

INSERT INTO `service` (`ID`, `NOM`, `CATEGORIE_NOM`) VALUES
(36, 'nettoyage maison', 'nettoyage'),
(37, 'nettoyage complet', 'nettoyage'),
(38, 'nettoyage piscine', 'nettoyage'),
(39, 'nettoyage divers', 'nettoyage'),
(40, 'stockage', 'déménagement et stockage'),
(41, 'déménagement national', 'déménagement et stockage'),
(42, 'déménagement international', 'déménagement et stockage'),
(43, 'peinture', 'travaux de maison'),
(44, 'électricité ', 'travaux de maison'),
(45, 'plomberie', 'travaux de maison'),
(46, 'menuiserie', 'travaux de maison'),
(47, 'climatisation', 'travaux de maison'),
(48, 'dératisation complète', 'dératisation'),
(49, 'restauration', 'événementiel'),
(50, 'traiteur', 'événementiel'),
(51, 'photographie', 'photographie'),
(52, 'vidéographie', 'photographie'),
(53, 'location de voiture', 'location de voiture');

-- --------------------------------------------------------

--
-- Structure de la table `servicepricing`
--

CREATE TABLE IF NOT EXISTS `servicepricing` (
  `ID` bigint(20) NOT NULL,
  `DATEAPPLICATION` date DEFAULT NULL,
  `HEURES` decimal(38,0) DEFAULT NULL,
  `PRIX` decimal(38,0) DEFAULT NULL,
  `SERVICE_ID` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `FK_SERVICEPRICING_SERVICE_ID` (`SERVICE_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `societe`
--

CREATE TABLE IF NOT EXISTS `societe` (
  `ID` bigint(20) NOT NULL,
  `NOM` varchar(255) DEFAULT NULL,
  `NOMBREEMPLOYE` int(11) DEFAULT NULL,
  `PHONE` varchar(255) DEFAULT NULL,
  `SITEWEB` varchar(255) DEFAULT NULL,
  `OWNER_ID` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `FK_SOCIETE_OWNER_ID` (`OWNER_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `societejob`
--

CREATE TABLE IF NOT EXISTS `societejob` (
  `ID` bigint(20) NOT NULL,
  `SECTEUR_ID` bigint(20) DEFAULT NULL,
  `SERVICE_ID` bigint(20) DEFAULT NULL,
  `SOCIETE_ID` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `FK_SOCIETEJOB_SOCIETE_ID` (`SOCIETE_ID`),
  KEY `FK_SOCIETEJOB_SECTEUR_ID` (`SECTEUR_ID`),
  KEY `FK_SOCIETEJOB_SERVICE_ID` (`SERVICE_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `supplementdemandeevent`
--

CREATE TABLE IF NOT EXISTS `supplementdemandeevent` (
  `ID` bigint(20) NOT NULL,
  `DEMANDEEVENT_ID` bigint(20) DEFAULT NULL,
  `SUPPLEMENTEVENT_ID` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `FK_SUPPLEMENTDEMANDEEVENT_DEMANDEEVENT_ID` (`DEMANDEEVENT_ID`),
  KEY `FK_SUPPLEMENTDEMANDEEVENT_SUPPLEMENTEVENT_ID` (`SUPPLEMENTEVENT_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `supplementevent`
--

CREATE TABLE IF NOT EXISTS `supplementevent` (
  `ID` bigint(20) NOT NULL,
  `NOM` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Contenu de la table `supplementevent`
--

INSERT INTO `supplementevent` (`ID`, `NOM`) VALUES
(107, 'tables'),
(108, 'chaises'),
(109, 'éclairage'),
(110, 'arrangement floraux'),
(111, 'personnel'),
(112, 'coutellerie');

-- --------------------------------------------------------

--
-- Structure de la table `timing`
--

CREATE TABLE IF NOT EXISTS `timing` (
  `ID` bigint(20) NOT NULL,
  `NAME` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Contenu de la table `timing`
--

INSERT INTO `timing` (`ID`, `NAME`) VALUES
(54, '1h'),
(55, '2h'),
(56, '3h'),
(57, '4h'),
(58, '5h'),
(59, '6h'),
(60, '7h'),
(61, '8h'),
(62, '9h'),
(63, '10h'),
(64, '11h'),
(65, '12h'),
(66, '13h'),
(67, '14h'),
(68, '15h'),
(69, '16h'),
(70, '17h'),
(71, '18h'),
(72, '19h'),
(73, '20h'),
(74, '21h'),
(75, '22h'),
(76, '23h'),
(77, '00h');

-- --------------------------------------------------------

--
-- Structure de la table `typeaction`
--

CREATE TABLE IF NOT EXISTS `typeaction` (
  `ID` bigint(20) NOT NULL,
  `NAME` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Contenu de la table `typeaction`
--

INSERT INTO `typeaction` (`ID`, `NAME`) VALUES
(78, 'approuver'),
(79, 'supprimer');

-- --------------------------------------------------------

--
-- Structure de la table `typedemande`
--

CREATE TABLE IF NOT EXISTS `typedemande` (
  `ID` varchar(255) NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `ville`
--

CREATE TABLE IF NOT EXISTS `ville` (
  `ID` bigint(20) NOT NULL,
  `NOM` varchar(255) DEFAULT NULL,
  `PAYS_ID` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `FK_VILLE_PAYS_ID` (`PAYS_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Contenu de la table `ville`
--

INSERT INTO `ville` (`ID`, `NOM`, `PAYS_ID`) VALUES
(102, 'marrakech', 101),
(103, 'casablanca', 101),
(104, 'rabat', 101),
(105, 'fes', 101),
(106, 'tanger', 101);

-- --------------------------------------------------------

--
-- Structure de la table `voiture`
--

CREATE TABLE IF NOT EXISTS `voiture` (
  `ID` bigint(20) NOT NULL,
  `CARBURANT` varchar(255) DEFAULT NULL,
  `COULEUR` varchar(255) DEFAULT NULL,
  `KILOMETRAGE` varchar(255) DEFAULT NULL,
  `MATRICULE` varchar(255) DEFAULT NULL,
  `SOCIETE_ID` bigint(20) DEFAULT NULL,
  `MODELE_ID` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `FK_VOITURE_SOCIETE_ID` (`SOCIETE_ID`),
  KEY `FK_VOITURE_MODELE_ID` (`MODELE_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `voitureimage`
--

CREATE TABLE IF NOT EXISTS `voitureimage` (
  `ID` bigint(20) NOT NULL,
  `IMAGE` varchar(255) DEFAULT NULL,
  `VOITURE_ID` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `FK_VOITUREIMAGE_VOITURE_ID` (`VOITURE_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `voituremarque`
--

CREATE TABLE IF NOT EXISTS `voituremarque` (
  `ID` bigint(20) NOT NULL,
  `NOM` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `voituremodele`
--

CREATE TABLE IF NOT EXISTS `voituremodele` (
  `ID` bigint(20) NOT NULL,
  `ANNEE` varchar(255) DEFAULT NULL,
  `NOM` varchar(255) DEFAULT NULL,
  `MARQUE_ID` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `FK_VOITUREMODELE_MARQUE_ID` (`MARQUE_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Contraintes pour les tables exportées
--

--
-- Contraintes pour la table `child`
--
ALTER TABLE `child`
  ADD CONSTRAINT `FK_CHILD_DEMANDEBABYSITTING_ID` FOREIGN KEY (`DEMANDEBABYSITTING_ID`) REFERENCES `demandebabysitting` (`ID`);

--
-- Contraintes pour la table `childdemandebabysitting`
--
ALTER TABLE `childdemandebabysitting`
  ADD CONSTRAINT `FK_CHILDDEMANDEBABYSITTING_DEMANDEBABYSITTING_ID` FOREIGN KEY (`DEMANDEBABYSITTING_ID`) REFERENCES `demandebabysitting` (`ID`);

--
-- Contraintes pour la table `client`
--
ALTER TABLE `client`
  ADD CONSTRAINT `FK_CLIENT_SECTEUR_ID` FOREIGN KEY (`SECTEUR_ID`) REFERENCES `secteur` (`ID`);

--
-- Contraintes pour la table `cuisinedemandeevent`
--
ALTER TABLE `cuisinedemandeevent`
  ADD CONSTRAINT `FK_CUISINEDEMANDEEVENT_CUISINE_ID` FOREIGN KEY (`CUISINE_ID`) REFERENCES `cuisinetype` (`ID`),
  ADD CONSTRAINT `FK_CUISINEDEMANDEEVENT_DEMANDEEVENT_ID` FOREIGN KEY (`DEMANDEEVENT_ID`) REFERENCES `demandeevent` (`ID`);

--
-- Contraintes pour la table `demandebabysitting`
--
ALTER TABLE `demandebabysitting`
  ADD CONSTRAINT `FK_DEMANDEBABYSITTING_DEMANDESERVICE_ID` FOREIGN KEY (`DEMANDESERVICE_ID`) REFERENCES `demandeservice` (`ID`);

--
-- Contraintes pour la table `demandecleaning`
--
ALTER TABLE `demandecleaning`
  ADD CONSTRAINT `FK_DEMANDECLEANING_DEMANDESERVICE_ID` FOREIGN KEY (`DEMANDESERVICE_ID`) REFERENCES `demandeservice` (`ID`);

--
-- Contraintes pour la table `demandeevent`
--
ALTER TABLE `demandeevent`
  ADD CONSTRAINT `FK_DEMANDEEVENT_DEMANDESERVICE_ID` FOREIGN KEY (`DEMANDESERVICE_ID`) REFERENCES `demandeservice` (`ID`),
  ADD CONSTRAINT `FK_DEMANDEEVENT_EVENT_ID` FOREIGN KEY (`EVENT_ID`) REFERENCES `eventtype` (`ID`);

--
-- Contraintes pour la table `demandegardening`
--
ALTER TABLE `demandegardening`
  ADD CONSTRAINT `FK_DEMANDEGARDENING_DEMANDESERVICE_ID` FOREIGN KEY (`DEMANDESERVICE_ID`) REFERENCES `demandeservice` (`ID`),
  ADD CONSTRAINT `FK_DEMANDEGARDENING_GARDENINGTYPE_ID` FOREIGN KEY (`GARDENINGTYPE_ID`) REFERENCES `gardeningtype` (`ID`),
  ADD CONSTRAINT `FK_DEMANDEGARDENING_HOME_ID` FOREIGN KEY (`HOME_ID`) REFERENCES `home` (`ID`);

--
-- Contraintes pour la table `demandehandyman`
--
ALTER TABLE `demandehandyman`
  ADD CONSTRAINT `FK_DEMANDEHANDYMAN_DEMANDESERVICE_ID` FOREIGN KEY (`DEMANDESERVICE_ID`) REFERENCES `demandeservice` (`ID`);

--
-- Contraintes pour la table `demandemoving`
--
ALTER TABLE `demandemoving`
  ADD CONSTRAINT `FK_DEMANDEMOVING_DEMANDESERVICE_ID` FOREIGN KEY (`DEMANDESERVICE_ID`) REFERENCES `demandeservice` (`ID`),
  ADD CONSTRAINT `FK_DEMANDEMOVING_VILLEARRIVE_ID` FOREIGN KEY (`VILLEARRIVE_ID`) REFERENCES `ville` (`ID`),
  ADD CONSTRAINT `FK_DEMANDEMOVING_VILLEDEPART_ID` FOREIGN KEY (`VILLEDEPART_ID`) REFERENCES `ville` (`ID`);

--
-- Contraintes pour la table `demandepainting`
--
ALTER TABLE `demandepainting`
  ADD CONSTRAINT `FK_DEMANDEPAINTING_DEMANDESERVICE_ID` FOREIGN KEY (`DEMANDESERVICE_ID`) REFERENCES `demandeservice` (`ID`),
  ADD CONSTRAINT `FK_DEMANDEPAINTING_PAINTING_ID` FOREIGN KEY (`PAINTING_ID`) REFERENCES `paintingtype` (`ID`);

--
-- Contraintes pour la table `demandepestcontrol`
--
ALTER TABLE `demandepestcontrol`
  ADD CONSTRAINT `FK_DEMANDEPESTCONTROL_DEMANDESERVICE_ID` FOREIGN KEY (`DEMANDESERVICE_ID`) REFERENCES `demandeservice` (`ID`),
  ADD CONSTRAINT `FK_DEMANDEPESTCONTROL_TYPEOFPESTCONTROL_ID` FOREIGN KEY (`TYPEOFPESTCONTROL_ID`) REFERENCES `pestcontroltype` (`ID`);

--
-- Contraintes pour la table `demandephotographie`
--
ALTER TABLE `demandephotographie`
  ADD CONSTRAINT `FK_DEMANDEPHOTOGRAPHIE_DEMANDESERVICE_ID` FOREIGN KEY (`DEMANDESERVICE_ID`) REFERENCES `demandeservice` (`ID`),
  ADD CONSTRAINT `FK_DEMANDEPHOTOGRAPHIE_TYPEPHOTOGRAPHIE_ID` FOREIGN KEY (`TYPEPHOTOGRAPHIE_ID`) REFERENCES `photographietype` (`ID`);

--
-- Contraintes pour la table `demandeservice`
--
ALTER TABLE `demandeservice`
  ADD CONSTRAINT `FK_DEMANDESERVICE_CLIENT_ID` FOREIGN KEY (`CLIENT_ID`) REFERENCES `client` (`ID`),
  ADD CONSTRAINT `FK_DEMANDESERVICE_MANAGERCONFIRMATION_ID` FOREIGN KEY (`MANAGERCONFIRMATION_ID`) REFERENCES `manager` (`ID`),
  ADD CONSTRAINT `FK_DEMANDESERVICE_PLANNING_ID` FOREIGN KEY (`PLANNING_ID`) REFERENCES `planning` (`ID`),
  ADD CONSTRAINT `FK_DEMANDESERVICE_SECTEUR_ID` FOREIGN KEY (`SECTEUR_ID`) REFERENCES `secteur` (`ID`),
  ADD CONSTRAINT `FK_DEMANDESERVICE_SERVICEPRICING_ID` FOREIGN KEY (`SERVICEPRICING_ID`) REFERENCES `servicepricing` (`ID`),
  ADD CONSTRAINT `FK_DEMANDESERVICE_SERVICE_ID` FOREIGN KEY (`SERVICE_ID`) REFERENCES `service` (`ID`),
  ADD CONSTRAINT `FK_DEMANDESERVICE_SOCIETE_ID` FOREIGN KEY (`SOCIETE_ID`) REFERENCES `societe` (`ID`),
  ADD CONSTRAINT `FK_DEMANDESERVICE_TIMING_ID` FOREIGN KEY (`TIMING_ID`) REFERENCES `timing` (`ID`),
  ADD CONSTRAINT `FK_DEMANDESERVICE_TYPEDEMANDE_ID` FOREIGN KEY (`TYPEDEMANDE_ID`) REFERENCES `typedemande` (`ID`);

--
-- Contraintes pour la table `demandeserviceconfirmationdetail`
--
ALTER TABLE `demandeserviceconfirmationdetail`
  ADD CONSTRAINT `DEMANDESERVICECONFIRMATIONDETAIL_DEMANDESERVICE_ID` FOREIGN KEY (`DEMANDESERVICE_ID`) REFERENCES `demandeservice` (`ID`),
  ADD CONSTRAINT `FK_DEMANDESERVICECONFIRMATIONDETAIL_MANAGER_ID` FOREIGN KEY (`MANAGER_ID`) REFERENCES `manager` (`ID`),
  ADD CONSTRAINT `FK_DEMANDESERVICECONFIRMATIONDETAIL_TYPEACTION_ID` FOREIGN KEY (`TYPEACTION_ID`) REFERENCES `typeaction` (`ID`);

--
-- Contraintes pour la table `demandevoiture`
--
ALTER TABLE `demandevoiture`
  ADD CONSTRAINT `FK_DEMANDEVOITURE_DEMANDESERVICE_ID` FOREIGN KEY (`DEMANDESERVICE_ID`) REFERENCES `demandeservice` (`ID`),
  ADD CONSTRAINT `FK_DEMANDEVOITURE_VOITURE_ID` FOREIGN KEY (`VOITURE_ID`) REFERENCES `voiture` (`ID`);

--
-- Contraintes pour la table `home`
--
ALTER TABLE `home`
  ADD CONSTRAINT `FK_HOME_HOMETYPE_ID` FOREIGN KEY (`HOMETYPE_ID`) REFERENCES `hometype` (`ID`);

--
-- Contraintes pour la table `packaging`
--
ALTER TABLE `packaging`
  ADD CONSTRAINT `FK_PACKAGING_SERVICEPRICING_ID` FOREIGN KEY (`SERVICEPRICING_ID`) REFERENCES `servicepricing` (`ID`),
  ADD CONSTRAINT `FK_PACKAGING_SERVICE_ID` FOREIGN KEY (`SERVICE_ID`) REFERENCES `service` (`ID`);

--
-- Contraintes pour la table `planningitem`
--
ALTER TABLE `planningitem`
  ADD CONSTRAINT `FK_PLANNINGITEM_DAY_ID` FOREIGN KEY (`DAY_ID`) REFERENCES `day` (`ID`),
  ADD CONSTRAINT `FK_PLANNINGITEM_PLANNING_ID` FOREIGN KEY (`PLANNING_ID`) REFERENCES `planning` (`ID`);

--
-- Contraintes pour la table `review`
--
ALTER TABLE `review`
  ADD CONSTRAINT `FK_REVIEW_CLIENT_ID` FOREIGN KEY (`CLIENT_ID`) REFERENCES `client` (`ID`),
  ADD CONSTRAINT `FK_REVIEW_SERVICE_ID` FOREIGN KEY (`SERVICE_ID`) REFERENCES `service` (`ID`),
  ADD CONSTRAINT `FK_REVIEW_SOCIETE_ID` FOREIGN KEY (`SOCIETE_ID`) REFERENCES `societe` (`ID`);

--
-- Contraintes pour la table `secteur`
--
ALTER TABLE `secteur`
  ADD CONSTRAINT `FK_SECTEUR_VILLE_ID` FOREIGN KEY (`VILLE_ID`) REFERENCES `ville` (`ID`);

--
-- Contraintes pour la table `service`
--
ALTER TABLE `service`
  ADD CONSTRAINT `FK_SERVICE_CATEGORIE_NOM` FOREIGN KEY (`CATEGORIE_NOM`) REFERENCES `categorie` (`NOM`);

--
-- Contraintes pour la table `servicepricing`
--
ALTER TABLE `servicepricing`
  ADD CONSTRAINT `FK_SERVICEPRICING_SERVICE_ID` FOREIGN KEY (`SERVICE_ID`) REFERENCES `service` (`ID`);

--
-- Contraintes pour la table `societe`
--
ALTER TABLE `societe`
  ADD CONSTRAINT `FK_SOCIETE_OWNER_ID` FOREIGN KEY (`OWNER_ID`) REFERENCES `owner` (`ID`);

--
-- Contraintes pour la table `societejob`
--
ALTER TABLE `societejob`
  ADD CONSTRAINT `FK_SOCIETEJOB_SECTEUR_ID` FOREIGN KEY (`SECTEUR_ID`) REFERENCES `secteur` (`ID`),
  ADD CONSTRAINT `FK_SOCIETEJOB_SERVICE_ID` FOREIGN KEY (`SERVICE_ID`) REFERENCES `service` (`ID`),
  ADD CONSTRAINT `FK_SOCIETEJOB_SOCIETE_ID` FOREIGN KEY (`SOCIETE_ID`) REFERENCES `societe` (`ID`);

--
-- Contraintes pour la table `supplementdemandeevent`
--
ALTER TABLE `supplementdemandeevent`
  ADD CONSTRAINT `FK_SUPPLEMENTDEMANDEEVENT_DEMANDEEVENT_ID` FOREIGN KEY (`DEMANDEEVENT_ID`) REFERENCES `demandeevent` (`ID`),
  ADD CONSTRAINT `FK_SUPPLEMENTDEMANDEEVENT_SUPPLEMENTEVENT_ID` FOREIGN KEY (`SUPPLEMENTEVENT_ID`) REFERENCES `supplementevent` (`ID`);

--
-- Contraintes pour la table `ville`
--
ALTER TABLE `ville`
  ADD CONSTRAINT `FK_VILLE_PAYS_ID` FOREIGN KEY (`PAYS_ID`) REFERENCES `pays` (`ID`);

--
-- Contraintes pour la table `voiture`
--
ALTER TABLE `voiture`
  ADD CONSTRAINT `FK_VOITURE_MODELE_ID` FOREIGN KEY (`MODELE_ID`) REFERENCES `voituremodele` (`ID`),
  ADD CONSTRAINT `FK_VOITURE_SOCIETE_ID` FOREIGN KEY (`SOCIETE_ID`) REFERENCES `societe` (`ID`);

--
-- Contraintes pour la table `voitureimage`
--
ALTER TABLE `voitureimage`
  ADD CONSTRAINT `FK_VOITUREIMAGE_VOITURE_ID` FOREIGN KEY (`VOITURE_ID`) REFERENCES `voiture` (`ID`);

--
-- Contraintes pour la table `voituremodele`
--
ALTER TABLE `voituremodele`
  ADD CONSTRAINT `FK_VOITUREMODELE_MARQUE_ID` FOREIGN KEY (`MARQUE_ID`) REFERENCES `voituremarque` (`ID`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
