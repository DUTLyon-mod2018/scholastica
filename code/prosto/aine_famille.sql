DELIMITER $$

DROP PROCEDURE IF EXISTS liste_aine_famille$$

CREATE DEFINER=`p1514568`@`%` PROCEDURE `liste_aine_famille`()
NOT DETERMINISTIC
CONTAINS SQL
SQL SECURITY DEFINER

BEGIN

    TRUNCATE TABLE p1514568.Aine_famille;

    CREATE TEMPORARY TABLE famille (
        id INT,
        parent1 INT,
        parent2 INT,
        age INT
    );

    CREATE TEMPORARY TABLE aine (
        parent1 INT,
        parent2 INT,
        maxage INT
    );

    INSERT INTO famille
        SELECT r.id_enfant as id, min(id_adulte) as parent1, max(id_adulte) as parent2, (CURRENT_DATE() - date_naissance) as age
        FROM p1514568.Responsabilite r
        JOIN p1514568.Enfant e ON r.id_enfant = e.id_enfant
        WHERE r.id_enfant IN (
            SELECT id_enfant
            FROM p1514568.Enfant_classe ec
            JOIN p1514568.Classe c ON ec.id_classe = c.id_classe AND IFNULL(c.cloture,0) = 0
            )
        AND r.lien = 'Parent'
        GROUP by r.id_enfant;

    INSERT INTO aine
        SELECT parent1, parent2, MAX(age) AS maxage
        FROM famille
        GROUP BY parent1, parent2;

    INSERT INTO p1514568.Aine_famille
        (nom_enfant, prenom_enfant, nom_classe)
    SELECT e.nom_enfant, e.prenom_enfant, c.nom_classe
    FROM famille f
    JOIN aine a
        ON f.parent1 = a.parent1
        AND f.parent2 = a.parent2
        AND f.age = a.maxage
    JOIN p1514568.Enfant e
        ON f.id = e.id_enfant
    JOIN p1514568.Enfant_classe ec
        ON ec.id_enfant = e.id_enfant
    JOIN p1514568.Classe c
        ON ec.id_classe = c.id_classe
        AND IFNULL(c.cloture,0) = 0;

END$$

DELIMITER ;
