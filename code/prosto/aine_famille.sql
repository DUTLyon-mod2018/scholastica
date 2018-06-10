CREATE TEMPORARY TABLE famille (
	id INT,
	parent1 INT,
	parent2 INT,
	age INT
);

INSERT INTO famille
	SELECT r.id_enfant as id, min(id_adulte) as parent1, max(id_adulte) as parent2, (CURRENT_DATE() - date_naissance) as age
	FROM p1514568.Responsabilite r
	JOIN p1514568.Enfant e ON r.id_enfant = e.id_enfant
	WHERE r.id_enfant IN (
		SELECT id_enfant
		FROM p1514568.Enfant_classe ec
		JOIN p1514568.Classe c on ec.id_classe = c.id_classe AND IFNULL(c.cloture,0) = 0
		)
	AND r.lien = 'Parent'
	GROUP by r.id_enfant;
	
SELECT id, parent1, parent2
FROM famille




SELECT nom_enfant, prenom_enfant, nom_classe
FROM p1514568.Enfant
JOIN (
	SELECT parent1, parent2, id FROM (
			SELECT r.id_enfant as id, min(id_adulte) as parent1, max(id_adulte) as parent2, (CURRENT_DATE() - date_naissance) as age
			FROM p1514568.Responsabilite r
			JOIN p1514568.Enfant e ON r.id_enfant = e.id_enfant
			WHERE r.id_enfant IN (
				SELECT id_enfant
				FROM p1514568.Enfant_classe ec
				JOIN p1514568.Classe c on ec.id_classe = c.id_classe AND IFNULL(c.cloture,0) = 0
				)
			GROUP by r.id_enfant
	) AS famille1
	GROUP BY parent1, parent2
	HAVING age = max(age)
) AS famille on p1514568.Enfant.id_enfant = famille.id
JOIN p1514568.Enfant_classe on p1514568.Enfant_classe.id_enfant = p1514568.Enfant.id_enfant
JOIN p1514568.Classe on p1514568.Classe.id_classe = p1514568.Enfant_classe.id_classe
AND IFNULL(p1514568.Classe.cloture,0) = 0

SELECT id_enfant, (CURRENT_DATE() - date_naissance) as age FROM Enfant HAVING age = max(age)

