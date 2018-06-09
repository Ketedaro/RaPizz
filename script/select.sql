SELECT p.nom_pizza, p.prix_pizza,  GROUP_CONCAT(i.nom_ingredient)
FROM PIZZA AS p
LEFT JOIN COMPOSE AS c
ON p.id_pizza = c.id_pizza
LEFT JOIN INGREDIENT AS i
ON c.id_ingredient = i.id_ingredient
GROUP BY p.nom_pizza;












SELECT l.nom_livreur, v.type_vehicule, cl.nom_client, co.date_commande, p.nom_pizza, p.prix_pizza
FROM LIVREUR AS l, VEHICULE AS v, CLIENT AS cl, COMMANDE AS co, PIZZA AS p,
WHERE co.id_vehicule = v.id_vehicule
AND co.id_livreur = l.id_livreur
AND co.id_pizza = p.id_pizza
AND co.id_client = cl.id_client
AND co.id_commande = 1;




SELECT p.nom_pizza, p.prix_pizza FROM INGREDIENT AS i, PIZZA AS p, COMPOSE AS c 
WHERE p.id_pizza = c.id_pizza
AND i.id_ingredient = c.id_ingredient
GROUP BY p.nom_pizza, p.prix_pizza;

SELECT c.id_ingredient FROM INGREDIENT AS i, COMPOSE AS c 
WHERE (SELECT p.nom_pizza, p.prix_pizza FROM INGREDIENT AS i, PIZZA AS p, COMPOSE AS c 
WHERE p.id_pizza = c.id_pizza
AND i.id_ingredient = c.id_ingredient
GROUP BY p.nom_pizza, p.prix_pizza);

