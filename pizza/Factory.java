package pizza;

import java.sql.Date;
import java.util.List;

public class Factory {
	
	public static Client getClient(int id_client, String nom_client, String prenom_client, String email_client, String password_client,
			String tel_client, String adresse_client, int score_client, float solde_client) {
		return new Client(id_client, nom_client, prenom_client, email_client, password_client,
				tel_client, adresse_client, score_client, solde_client);
	}
	
	public static Pizza getPizza(int id_pizza, String nom_pizza, float prix_pizza, int score_pizza, List<Ingredient> list_ingredient) {
		return new Pizza(id_pizza, nom_pizza, prix_pizza, score_pizza, list_ingredient);
	}

	public static Ingredient getIngredient(int id_ingredient, String nom_ingredient, int score_ingredient) {
		return new Ingredient(id_ingredient, nom_ingredient, score_ingredient);
	}

	public static Livreur getLivreur(int id_livreur, String nom_livreur, String prenom_livreur, String tel_livreur,
			int nb_retard) {
		return new Livreur(id_livreur, nom_livreur, prenom_livreur, tel_livreur, nb_retard);
	}

	public static Vehicule getVehicule(int id_vehicule, String type_vehicule, String marque_vehicule,
			String immatriculation, int nb_utilisation) {
		return new Vehicule(id_vehicule, type_vehicule, marque_vehicule, immatriculation, nb_utilisation);
	}
	
	public static Commande getCommande(int id_commande, float prix_commande, Date date_commande,
			Date temps_livraison) {
		return new Commande(id_commande, prix_commande, date_commande, temps_livraison);
	}
	
}
