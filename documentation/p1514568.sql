-- phpMyAdmin SQL Dump
-- version 4.4.15.10
-- https://www.phpmyadmin.net
--
-- Client :  localhost
-- Généré le :  Jeu 24 Mai 2018 à 18:01
-- Version du serveur :  5.5.56-MariaDB
-- Version de PHP :  5.4.16

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données :  `p1514568`
--

-- --------------------------------------------------------

--
-- Structure de la table `Adulte`
--

CREATE TABLE IF NOT EXISTS `Adulte` (
  `id_adulte` int(7) NOT NULL,
  `id_utilisateur` varchar(30) CHARACTER SET utf8 NOT NULL,
  `nom_adulte` varchar(40) CHARACTER SET utf8 NOT NULL,
  `prenom_adulte` varchar(40) CHARACTER SET utf8 NOT NULL,
  `profession` varchar(40) CHARACTER SET utf8 NOT NULL,
  `adresse_adulte` varchar(100) CHARACTER SET utf8 NOT NULL,
  `telephone` varchar(20) CHARACTER SET utf8 NOT NULL,
  `adresse_travail` varchar(70) CHARACTER SET utf8 NOT NULL,
  `telephone_travail` varchar(20) CHARACTER SET utf8 NOT NULL,
  `horaires` varchar(20) CHARACTER SET utf8 NOT NULL,
  `decede` tinyint(1) NOT NULL,
  `enseignant` tinyint(1) NOT NULL,
  `date_debut` date NOT NULL,
  `date_fin` date NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Structure de la table `Affectation`
--

CREATE TABLE IF NOT EXISTS `Affectation` (
  `id_adulte` int(7) NOT NULL,
  `id_classe` int(7) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Structure de la table `Assurance_scolaire`
--

CREATE TABLE IF NOT EXISTS `Assurance_scolaire` (
  `id_assurance` int(7) NOT NULL,
  `nom_assurance` varchar(40) CHARACTER SET utf8 NOT NULL,
  `adresse_assurance` varchar(100) CHARACTER SET utf8 NOT NULL,
  `tel_assurance` varchar(15) CHARACTER SET utf8 NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Structure de la table `Centre_securite_sociale`
--

CREATE TABLE IF NOT EXISTS `Centre_securite_sociale` (
  `id_centre` int(7) NOT NULL,
  `nom_centre` varchar(40) CHARACTER SET utf8 NOT NULL,
  `adresse_centre` varchar(100) CHARACTER SET utf8 NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Structure de la table `Classe`
--

CREATE TABLE IF NOT EXISTS `Classe` (
  `id_classe` int(7) NOT NULL,
  `nom_classe` varchar(20) CHARACTER SET utf8 NOT NULL,
  `salle` varchar(20) CHARACTER SET utf8 NOT NULL,
  `niveau` varchar(15) CHARACTER SET utf8 NOT NULL,
  `année` varchar(15) CHARACTER SET utf8 NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Structure de la table `Compte`
--

CREATE TABLE IF NOT EXISTS `Compte` (
  `id_utilisateur` varchar(30) CHARACTER SET utf8 NOT NULL,
  `mdp` varchar(30) CHARACTER SET utf8 NOT NULL,
  `modif` tinyint(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Contenu de la table `Compte`
--

INSERT INTO `Compte` (`id_utilisateur`, `mdp`, `modif`) VALUES
('root', 'root', 0);

-- --------------------------------------------------------

--
-- Structure de la table `Enfant`
--

CREATE TABLE IF NOT EXISTS `Enfant` (
  `id_enfant` int(11) NOT NULL,
  `photo` varchar(70) CHARACTER SET utf8 NOT NULL,
  `nom_enfant` varchar(30) CHARACTER SET utf8 NOT NULL,
  `prenom_enfant` varchar(30) CHARACTER SET utf8 NOT NULL,
  `adresse_enfant` varchar(100) CHARACTER SET utf8 NOT NULL,
  `id_nationalite` varchar(60) CHARACTER SET utf8 NOT NULL,
  `date_naissance` date NOT NULL,
  `id_ville_naissance` varchar(40) CHARACTER SET utf8 NOT NULL,
  `date_inscription` date NOT NULL,
  `date_radiation` date NOT NULL,
  `port_lunettes` tinyint(1) NOT NULL,
  `situation_familiale` varchar(200) CHARACTER SET utf8 NOT NULL,
  `infos_medicales` varchar(200) CHARACTER SET utf8 NOT NULL,
  `date_vaccin` date NOT NULL,
  `pai` tinyint(1) NOT NULL,
  `avs` tinyint(1) NOT NULL,
  `evs` tinyint(1) NOT NULL,
  `id_assurance` int(11) NOT NULL,
  `id_centre` int(11) NOT NULL,
  `id_medecin` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Structure de la table `Enfant_classe`
--

CREATE TABLE IF NOT EXISTS `Enfant_classe` (
  `id_enfant` int(7) NOT NULL,
  `id_classe` int(7) NOT NULL,
  `niveau_eleve` varchar(20) CHARACTER SET utf8 NOT NULL,
  `redoublant` tinyint(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Structure de la table `Enfant_rased`
--

CREATE TABLE IF NOT EXISTS `Enfant_rased` (
  `id_enfant` int(11) NOT NULL,
  `id_rased` int(7) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Structure de la table `Log`
--

CREATE TABLE IF NOT EXISTS `Log` (
  `id_log` int(7) NOT NULL,
  `id_utilisateur` varchar(30) CHARACTER SET utf8 NOT NULL,
  `id_adulte` int(7) NOT NULL,
  `id_enfant` int(7) NOT NULL,
  `id_classe` int(7) NOT NULL,
  `horodatage` timestamp(6) NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Structure de la table `Medecin_traitant`
--

CREATE TABLE IF NOT EXISTS `Medecin_traitant` (
  `id_medecin` int(7) NOT NULL,
  `nom_medecin` varchar(40) CHARACTER SET utf8 NOT NULL,
  `prenom_medecin` varchar(40) CHARACTER SET utf8 NOT NULL,
  `adresse_medecin` varchar(100) CHARACTER SET utf8 NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Structure de la table `Nationalite`
--

CREATE TABLE IF NOT EXISTS `Nationalite` (
  `id_nationalite` varchar(60) CHARACTER SET utf8 NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Structure de la table `Rased`
--

CREATE TABLE IF NOT EXISTS `Rased` (
  `id_rased` int(11) NOT NULL,
  `libelle_rased` varchar(60) CHARACTER SET utf8 NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Structure de la table `Responsabilite`
--

CREATE TABLE IF NOT EXISTS `Responsabilite` (
  `id_enfant` int(7) NOT NULL,
  `id_adulte` int(7) NOT NULL,
  `lien` varchar(50) CHARACTER SET utf8 NOT NULL,
  `autoriser` tinyint(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Structure de la table `Ville`
--

CREATE TABLE IF NOT EXISTS `Ville` (
  `id_ville` varchar(40) CHARACTER SET utf8 NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Index pour les tables exportées
--

--
-- Index pour la table `Adulte`
--
ALTER TABLE `Adulte`
  ADD PRIMARY KEY (`id_adulte`),
  ADD KEY `id_utilisateur` (`id_utilisateur`);

--
-- Index pour la table `Affectation`
--
ALTER TABLE `Affectation`
  ADD PRIMARY KEY (`id_adulte`,`id_classe`),
  ADD KEY `id_classe` (`id_classe`);

--
-- Index pour la table `Assurance_scolaire`
--
ALTER TABLE `Assurance_scolaire`
  ADD PRIMARY KEY (`id_assurance`),
  ADD KEY `id_assurance` (`id_assurance`),
  ADD KEY `id_assurance_2` (`id_assurance`),
  ADD KEY `id_assurance_3` (`id_assurance`),
  ADD KEY `id_assurance_4` (`id_assurance`);

--
-- Index pour la table `Centre_securite_sociale`
--
ALTER TABLE `Centre_securite_sociale`
  ADD PRIMARY KEY (`id_centre`),
  ADD KEY `id_centre` (`id_centre`),
  ADD KEY `id_centre_2` (`id_centre`);

--
-- Index pour la table `Classe`
--
ALTER TABLE `Classe`
  ADD PRIMARY KEY (`id_classe`),
  ADD KEY `id_classe` (`id_classe`),
  ADD KEY `id_classe_2` (`id_classe`);

--
-- Index pour la table `Compte`
--
ALTER TABLE `Compte`
  ADD PRIMARY KEY (`id_utilisateur`),
  ADD KEY `id_utilisateur` (`id_utilisateur`),
  ADD KEY `id_utilisateur_2` (`id_utilisateur`);

--
-- Index pour la table `Enfant`
--
ALTER TABLE `Enfant`
  ADD PRIMARY KEY (`id_enfant`),
  ADD KEY `id_enfant` (`id_enfant`),
  ADD KEY `id_nationalite` (`id_nationalite`),
  ADD KEY `id_ville_naissance` (`id_ville_naissance`),
  ADD KEY `id_assurance` (`id_assurance`),
  ADD KEY `id_centre` (`id_centre`),
  ADD KEY `id_medecin` (`id_medecin`),
  ADD KEY `id_ville_naissance_2` (`id_ville_naissance`),
  ADD KEY `id_assurance_2` (`id_assurance`),
  ADD KEY `id_ville_naissance_3` (`id_ville_naissance`),
  ADD KEY `id_centre_2` (`id_centre`),
  ADD KEY `id_medecin_2` (`id_medecin`);

--
-- Index pour la table `Enfant_classe`
--
ALTER TABLE `Enfant_classe`
  ADD PRIMARY KEY (`id_enfant`,`id_classe`),
  ADD KEY `id_enfant` (`id_enfant`),
  ADD KEY `id_classe` (`id_classe`);

--
-- Index pour la table `Enfant_rased`
--
ALTER TABLE `Enfant_rased`
  ADD PRIMARY KEY (`id_enfant`,`id_rased`),
  ADD KEY `id_enfant` (`id_enfant`),
  ADD KEY `id_rased` (`id_rased`);

--
-- Index pour la table `Log`
--
ALTER TABLE `Log`
  ADD PRIMARY KEY (`id_log`),
  ADD KEY `id_utilisateur` (`id_utilisateur`),
  ADD KEY `id_adulte` (`id_adulte`),
  ADD KEY `id_enfant` (`id_enfant`),
  ADD KEY `id_classe` (`id_classe`),
  ADD KEY `id_utilisateur_2` (`id_utilisateur`),
  ADD KEY `id_adulte_2` (`id_adulte`),
  ADD KEY `id_enfant_2` (`id_enfant`),
  ADD KEY `id_classe_2` (`id_classe`);

--
-- Index pour la table `Medecin_traitant`
--
ALTER TABLE `Medecin_traitant`
  ADD KEY `id_medecin` (`id_medecin`),
  ADD KEY `id_medecin_2` (`id_medecin`);

--
-- Index pour la table `Nationalite`
--
ALTER TABLE `Nationalite`
  ADD KEY `id_nationalite` (`id_nationalite`);

--
-- Index pour la table `Rased`
--
ALTER TABLE `Rased`
  ADD PRIMARY KEY (`id_rased`);

--
-- Index pour la table `Responsabilite`
--
ALTER TABLE `Responsabilite`
  ADD KEY `id_enfant` (`id_enfant`),
  ADD KEY `id_adulte` (`id_adulte`),
  ADD KEY `id_enfant_2` (`id_enfant`),
  ADD KEY `id_adulte_2` (`id_adulte`);

--
-- Index pour la table `Ville`
--
ALTER TABLE `Ville`
  ADD PRIMARY KEY (`id_ville`),
  ADD KEY `id_ville` (`id_ville`);

--
-- AUTO_INCREMENT pour les tables exportées
--

--
-- AUTO_INCREMENT pour la table `Adulte`
--
ALTER TABLE `Adulte`
  MODIFY `id_adulte` int(7) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=2;
--
-- AUTO_INCREMENT pour la table `Assurance_scolaire`
--
ALTER TABLE `Assurance_scolaire`
  MODIFY `id_assurance` int(7) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT pour la table `Centre_securite_sociale`
--
ALTER TABLE `Centre_securite_sociale`
  MODIFY `id_centre` int(7) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT pour la table `Enfant`
--
ALTER TABLE `Enfant`
  MODIFY `id_enfant` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT pour la table `Log`
--
ALTER TABLE `Log`
  MODIFY `id_log` int(7) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT pour la table `Rased`
--
ALTER TABLE `Rased`
  MODIFY `id_rased` int(11) NOT NULL AUTO_INCREMENT;
--
-- Contraintes pour les tables exportées
--

--
-- Contraintes pour la table `Adulte`
--
ALTER TABLE `Adulte`
  ADD CONSTRAINT `fk_id_utilisateur` FOREIGN KEY (`id_utilisateur`) REFERENCES `Compte` (`id_utilisateur`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Contraintes pour la table `Affectation`
--
ALTER TABLE `Affectation`
  ADD CONSTRAINT `fk_id_classe` FOREIGN KEY (`id_classe`) REFERENCES `Classe` (`id_classe`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `fk_id_adulte` FOREIGN KEY (`id_adulte`) REFERENCES `Adulte` (`id_adulte`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Contraintes pour la table `Enfant`
--
ALTER TABLE `Enfant`
  ADD CONSTRAINT `fk_id_assurance` FOREIGN KEY (`id_assurance`) REFERENCES `Assurance_scolaire` (`id_assurance`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `fk_id_centre` FOREIGN KEY (`id_centre`) REFERENCES `Centre_securite_sociale` (`id_centre`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `fk_id_medecin` FOREIGN KEY (`id_medecin`) REFERENCES `Medecin_traitant` (`id_medecin`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `fk_id_nationalite` FOREIGN KEY (`id_nationalite`) REFERENCES `Nationalite` (`id_nationalite`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `fk_id_ville` FOREIGN KEY (`id_ville_naissance`) REFERENCES `Ville` (`id_ville`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Contraintes pour la table `Enfant_classe`
--
ALTER TABLE `Enfant_classe`
  ADD CONSTRAINT `fk_id_class` FOREIGN KEY (`id_classe`) REFERENCES `Classe` (`id_classe`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `fk_id_enfant` FOREIGN KEY (`id_enfant`) REFERENCES `Enfant` (`id_enfant`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Contraintes pour la table `Enfant_rased`
--
ALTER TABLE `Enfant_rased`
  ADD CONSTRAINT `fk_id_rased` FOREIGN KEY (`id_rased`) REFERENCES `Rased` (`id_rased`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `fk_id_enfantr` FOREIGN KEY (`id_enfant`) REFERENCES `Enfant` (`id_enfant`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Contraintes pour la table `Log`
--
ALTER TABLE `Log`
  ADD CONSTRAINT `fk_id_classec` FOREIGN KEY (`id_classe`) REFERENCES `Classe` (`id_classe`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `fk_id_adultec` FOREIGN KEY (`id_adulte`) REFERENCES `Adulte` (`id_adulte`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `fk_id_enfantc` FOREIGN KEY (`id_enfant`) REFERENCES `Enfant` (`id_enfant`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `fk_id_utilisateurc` FOREIGN KEY (`id_utilisateur`) REFERENCES `Compte` (`id_utilisateur`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Contraintes pour la table `Responsabilite`
--
ALTER TABLE `Responsabilite`
  ADD CONSTRAINT `fk_id_adultere` FOREIGN KEY (`id_adulte`) REFERENCES `Adulte` (`id_adulte`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `fk_id_enfantre` FOREIGN KEY (`id_enfant`) REFERENCES `Enfant` (`id_enfant`) ON DELETE CASCADE ON UPDATE CASCADE;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
