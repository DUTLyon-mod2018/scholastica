DELIMITER $$

DROP TRIGGER IF EXISTS before_delete_classe$$

CREATE TRIGGER `before_delete_classe` BEFORE DELETE ON `Classe`
 FOR EACH ROW BEGIN
	delete from p1514568.Enfant_classe where id_classe = OLD.id_classe;

	delete from p1514568.Affectation where id_classe = OLD.id_classe;
END $$
DELIMITER ;