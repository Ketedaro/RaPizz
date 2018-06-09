#------------------------------------------------------------
# Table: PIZZA
#------------------------------------------------------------

CREATE TABLE PIZZA(
        id_pizza       int (11) Auto_increment  NOT NULL ,
        nom_pizza      Varchar (25) NOT NULL ,
        prix_pizza     Float NOT NULL ,
        score_pizza    Int ,
        PRIMARY KEY (id_pizza )
)ENGINE=InnoDB;

INSERT INTO PIZZA (id_pizza, nom_pizza, prix_pizza, score_pizza) VALUES
(null, "Reine", 8, null), 
(null, "Chèvre Miel", 10, null),
(null, "Montagnarde", 12, null),
(null, "Mexicaine", 12, null),
(null, "Quatre Saisons", 12, null),
(null, "Absolute", 15, null);

#------------------------------------------------------------
# Table: CLIENT
#------------------------------------------------------------

CREATE TABLE CLIENT(
        id_client       int (11) Auto_increment  NOT NULL ,
        nom_client      Varchar (25) NOT NULL ,
        prenom_client   Varchar (25) NOT NULL ,
        email_client    Varchar (25) NOT NULL ,
        password_client Varchar (25) NOT NULL ,
        tel_client      Varchar (25) NOT NULL ,
        adresse_client  Varchar (25) NOT NULL ,
        score_client    Int NOT NULL DEFAULT 0,
        solde_client    Float NOT NULL ,
        PRIMARY KEY (id_client )
)ENGINE=InnoDB;

INSERT INTO CLIENT (id_client, nom_client, prenom_client, email_client, password_client, 
                    tel_client, adresse_client, solde_client) VALUES
(null, "Ketedaro", "Andy", "andy.ketedaro@pizza.com", 
 "6177321eac992341d1ad0823a07e76bfc4ee6909db120e377ea303fdc216756c", "08.88.88.88.88", "Tour Eiffel", 100), 
(null, "Tellatin", "Hugo", "hugo.tellatin@pizza.com", 
 "0478721f1106c2a631a90181bac7efc77767a3903eb9220687bff8a14e940fa7", "08.66.66.66.66", "Campagne", 1);


#------------------------------------------------------------
# Table: VEHICULE
#------------------------------------------------------------

CREATE TABLE VEHICULE(
        id_vehicule     int (11) Auto_increment  NOT NULL ,
        type_vehicule   Varchar (25) NOT NULL ,
		marque_vehicule Varchar (25) NOT NULL ,
        immatriculation Varchar (25) NOT NULL ,
		nb_utilisation  Int NOT NULL DEFAULT 0,
        PRIMARY KEY (id_vehicule )
)ENGINE=InnoDB;

INSERT INTO VEHICULE (id_vehicule, type_vehicule, marque_vehicule, immatriculation) VALUES
(null, "Voiture", "Lamborghini" ,"5L5L5L"),
(null, "Scooter", "Peugeot", "696969"),
(null, "Moto", "Ducati", "8K8K8K"),
(null, "Van", "Mercedes", "6A6A6A");


#------------------------------------------------------------
# Table: COMMANDE
#------------------------------------------------------------

CREATE TABLE COMMANDE(
        id_commande     int (11) Auto_increment  NOT NULL ,
        prix_commande   Float NOT NULL ,
        date_commande   Date NOT NULL ,
        temps_livraison Date NOT NULL ,
        id_pizza        Int NOT NULL ,
        date_livraison  Date NOT NULL ,
        id_livreur      Int NOT NULL ,
        id_client       Int NOT NULL ,
        id_vehicule     Int NOT NULL ,
        PRIMARY KEY (id_commande )
)ENGINE=InnoDB;

INSERT INTO COMMANDE (id_commande, prix_commande, date_commande, temps_livraison, id_pizza, date_livraison, id_livreur, id_client, id_vehicule) VALUES
(null, 10, '2008-7-04', '2008-7-04', 1, '2008-7-04', 1, 1, 1);
#------------------------------------------------------------
# Table: LIVREUR
#------------------------------------------------------------

CREATE TABLE LIVREUR(
        id_livreur     int (11) Auto_increment  NOT NULL ,
        nom_livreur    Varchar (25) NOT NULL ,
        prenom_livreur Varchar (25) NOT NULL ,
        tel_livreur    Varchar (25) NOT NULL ,
        nb_retard      Int NOT NULL DEFAULT 0 ,
        PRIMARY KEY (id_livreur )
)ENGINE=InnoDB;

INSERT INTO LIVREUR (id_livreur, nom_livreur, prenom_livreur, tel_livreur) VALUES
(null, "Hahn", "Nicolas", "01.11.11.11.11"),
(null, "Brackenier", "Jordan", "01.99.99.99.99");

#------------------------------------------------------------
# Table: INGREDIENT
#------------------------------------------------------------

CREATE TABLE INGREDIENT(
        id_ingredient    int (11) Auto_increment  NOT NULL ,
        nom_ingredient   Varchar (25) NOT NULL ,
        score_ingredient Int ,
        PRIMARY KEY (id_ingredient )
)ENGINE=InnoDB;

INSERT INTO INGREDIENT (id_ingredient, nom_ingredient) VALUES
(null, "Tomate"),
(null, "Fromage"),
(null, "Jambon"),
(null, "Lardon"),
(null, "Oignon"),
(null, "Poivrons"),
(null, "Patate"),
(null, "Miel"),
(null, "Chèvre");

#------------------------------------------------------------
# Table: compose
#------------------------------------------------------------

CREATE TABLE compose(
        id_pizza      Int NOT NULL ,
        id_ingredient Int NOT NULL ,
        PRIMARY KEY (id_pizza ,id_ingredient )
)ENGINE=InnoDB;

INSERT INTO compose (id_pizza, id_ingredient) VALUES 
(1, 1),
(1, 2),
(2, 5),
(2, 6),
(3, 4),
(3, 1);

ALTER TABLE COMMANDE ADD CONSTRAINT FK_COMMANDE_id_pizza FOREIGN KEY (id_pizza) REFERENCES PIZZA(id_pizza);
ALTER TABLE COMMANDE ADD CONSTRAINT FK_COMMANDE_id_livreur FOREIGN KEY (id_livreur) REFERENCES LIVREUR(id_livreur);
ALTER TABLE COMMANDE ADD CONSTRAINT FK_COMMANDE_id_client FOREIGN KEY (id_client) REFERENCES CLIENT(id_client);
ALTER TABLE COMMANDE ADD CONSTRAINT FK_COMMANDE_id_vehicule FOREIGN KEY (id_vehicule) REFERENCES VEHICULE(id_vehicule);
ALTER TABLE compose ADD CONSTRAINT FK_compose_id_pizza FOREIGN KEY (id_pizza) REFERENCES PIZZA(id_pizza);
ALTER TABLE compose ADD CONSTRAINT FK_compose_id_ingredient FOREIGN KEY (id_ingredient) REFERENCES INGREDIENT(id_ingredient);
