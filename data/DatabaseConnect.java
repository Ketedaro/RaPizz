package data;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import pizza.Client;
import pizza.Factory;
import pizza.Ingredient;
import pizza.Livreur;
import pizza.Pizza;
import pizza.Vehicule;


public class DatabaseConnect {
	private static DatabaseConnect INSTANCE = null;
	private Connection connect;
	
	public static DatabaseConnect getDataBase() {
		if (INSTANCE == null)
			INSTANCE = new DatabaseConnect();
		return INSTANCE;
	}
	
	public Connection getCo() {
		return connect;
	}

	private DatabaseConnect() {
		File file = new File(
				DatabaseConnect.class.getProtectionDomain().getCodeSource().getLocation().getPath() + "config.txt");

		try {
			FileReader fis = new FileReader(file);
			@SuppressWarnings("resource")
			BufferedReader buff = new BufferedReader(fis);
			try {
				String className = buff.readLine();
				String url = buff.readLine();
				String login = buff.readLine();
				String password = buff.readLine();
				try {
					Class.forName(className);
				} catch (ClassNotFoundException e1) {

					e1.printStackTrace();
				}
				try {
					this.connect = DriverManager.getConnection(url, login, password);
				} catch (SQLException e) {

					e.printStackTrace();
				}
			} catch (FileNotFoundException e2) {
				e2.printStackTrace();
			}
		} catch (IOException e2) {
			e2.printStackTrace();
		}
	}
	
	/*private void setUpdate(String sql) {
		Statement request = null;
		try {
			request = connect.createStatement();
			request.executeUpdate(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
		
	//Vérifie que si le mail existe déjà
	public boolean existEmail(String email) {
		Statement request;
		ResultSet res;
		boolean tmp = false;
		try {
			String sql = "SELECT * FROM client WHERE email_client = '" + email + "'";
			request = connect.createStatement();
			res = request.executeQuery(sql);
			tmp = res.next();
		} catch (SQLException e) {

			e.printStackTrace();
		}
		return tmp;

	}

	// Vérifie qu'un client existe
	public boolean existClient(String email, String password) {
		Statement request;
		ResultSet res;
		boolean tmp = false;
		try {
			String sql = "SELECT * FROM client WHERE email_client = '" + email + "' and password_client = '" + password
					+ "'";
			request = connect.createStatement();
			res = request.executeQuery(sql);
			tmp = res.next();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return tmp;

	}
	
	// Récupérer un client avec son id
	public Client getClientById(int id) {
		return this.getListClients("SELECT * FROM client WHERE id_client = " + id).get(0);
	}
	
	// Récupérer un client avec son email
	public Client getClientByMail(String email_client) {
		return this.getListClients("select * from client where email_client = '" + email_client + "'").get(0);
	}
	
	// Récupère le client avec le score le plus élevé
	public Client getBestClient() {
		return this.getListClients("SELECT * FROM client ORDER BY score_client DESC LIMIT 1").get(0);
	}
	
	// Récupère la liste des clients
	private List<Client> getListClients(String sql) {
		Statement request;
		ResultSet resultSet;
		String nom_client = null, prenom_client = null, tel_client = null, adresse_client = null, password_client = null, email_client = null;
		int id_client = 0, score_client = 0;
		float solde_client = 0;
		List<Client> list_clients = new ArrayList<Client>();
		try {
			request = connect.createStatement();
			resultSet = request.executeQuery(sql);

			while (resultSet.next()) {
				id_client = resultSet.getInt("id_client");
				nom_client = resultSet.getString("nom_client");
				prenom_client = resultSet.getString("prenom_client");
				password_client = resultSet.getString("password_client");
				email_client = resultSet.getString("email_client");
				tel_client = resultSet.getString("tel_client");
				adresse_client = resultSet.getString("adresse_client");
				score_client = resultSet.getInt("score_client");
				solde_client = resultSet.getFloat("solde_client");
				list_clients.add(Factory.getClient(id_client, nom_client, prenom_client, email_client, password_client,
						tel_client, adresse_client, score_client, solde_client));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list_clients;
	}
	
	// Créé un nouveau compte client
	public void createClient(String nom_client, String prenom_client, String email_client, String password_client,
			String tel_client, String adresse_client) {
		this.setUpdate("INSERT INTO client (id_client, nom_client, prenom_client, email_client, password_client, tel_client, adresse_client, solde_client) VALUES (" 
			+ null +  ",'" + prenom_client + "', '" + nom_client + "', '" + email_client + "', '"+ password_client + "', '" + tel_client + "', '" + adresse_client + "', 0");
	}
	
	// Récupère une pizza avec son nom
	public Pizza getPizzaByNom(String nom_pizza) {
		return this.getListPizzas("select * from pizza where nom_pizza = " + nom_pizza).get(0);
	}

	// Récupère une pizza avec son id
	public Pizza getPizzaById(int id) {
		return this.getListPizzas("select * from pizza where id_pizza = " + id).get(0);
	}
	
	// Récupère la pizza la moins demandée
	public Pizza getWorstPizza() {
		return this.getListPizzas("SELECT * FROM pizza ORDER BY score_pizza LIMIT 1").get(0);
	}
	
	// Récupère la liste des pizzas
	private List<Pizza> getListPizzas(String sql) {
		Statement request;
		ResultSet resultSet;
		List<Pizza> list_pizza = new ArrayList<Pizza>();

		String nom_pizza = null;
		int score_pizza = 0, id_pizza = 0;
		float prix_pizza = 0;
		List<Ingredient> list_ingredient = new ArrayList<Ingredient>();
		
		try {
			request = connect.createStatement();
			resultSet = request.executeQuery(sql);
			while (resultSet.next()) {
				id_pizza = resultSet.getInt("id_pizza");
				nom_pizza = resultSet.getString("nom_pizza");
				prix_pizza = resultSet.getFloat("prix_pizza");
				score_pizza = resultSet.getInt("score_pizza");
				list_ingredient = this.getIngredientsCompose("select * from compose where id_pizza = " + id_pizza);

				try {
					list_pizza.add(Factory.getPizza(id_pizza, nom_pizza, prix_pizza, score_pizza, list_ingredient));
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return list_pizza;
	}
	
	private List<Ingredient> getIngredientsCompose(String sql) {
		Statement request;
		ResultSet resultSet;
		int id_ingredient = 0, id_pizza = 0;
		List<Ingredient> list_ingredient = new ArrayList<Ingredient>();
		try {
			request = connect.createStatement();
			resultSet = request.executeQuery(sql);
			while (resultSet.next()) {
				id_pizza = resultSet.getInt("id_pizza");
				id_ingredient = resultSet.getInt("id_ingredient");
				list_ingredient = this.getIngredients("select * from ingredient where id_ingredient = " + id_ingredient);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list_ingredient;
	}
	
	private List<Ingredient> getIngredients(String sql) {
		Statement request;
		ResultSet resultSet;
		int id_ingredient = 0, score_ingredient = 0;
		String nom_ingredient = null;
		List<Ingredient> list_ingredient = new ArrayList<Ingredient>();
		try {
			request = connect.createStatement();
			resultSet = request.executeQuery(sql);
			while (resultSet.next()) {
				score_ingredient = resultSet.getInt("score_ingredient");
				id_ingredient = resultSet.getInt("id_ingredient");
				nom_ingredient = resultSet.getString("nom_ingredient");
				try {
					list_ingredient.add(Factory.getIngredient(id_ingredient, nom_ingredient, score_ingredient));
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list_ingredient;
	}
	
	// Récupère l'ingrédient le plus demandé
	public Ingredient getBestIngredient() {
		return this.getIngredients("SELECT * FROM ingredient ORDER BY score_ingredient DESC LIMIT 1").get(0);
	}
	
	// Récupère un livreur par son id
	public Livreur getLivreurById(int id) {
		return this.getListLivreurs("SELECT * FROM livreur WHERE id_livreur = " + id).get(0);
	}
	
	// Récupère un livreur par son nom
	public Livreur getLivreurByNom(int nom) {
		return this.getListLivreurs("SELECT * FROM livreur WHERE nom_livreur = " + nom).get(0);
	}
	
	// Récupère le livreur avec le plus de retards
	public Livreur getWorstLivreur() {
		return this.getListLivreurs("SELECT * FROM livreur ORDER BY nb_retard DESC LIMIT 1").get(0);
	}
	
	private List<Livreur> getListLivreurs(String sql) {
		Statement request;
		ResultSet resultSet;
		String nom_livreur = null, prenom_livreur = null, tel_livreur = null;
		int id_livreur = 0, nb_retard = 0;
		List<Livreur> list_livreurs = new ArrayList<Livreur>();
		try {
			request = connect.createStatement();
			resultSet = request.executeQuery(sql);

			while (resultSet.next()) {
				id_livreur = resultSet.getInt("id_livreur");
				nom_livreur = resultSet.getString("nom_livreur");
				prenom_livreur = resultSet.getString("prenom_livreur");
				tel_livreur = resultSet.getString("tel_livreur");
				nb_retard = resultSet.getInt("nb_retard");
				list_livreurs.add(Factory.getLivreur(id_livreur, nom_livreur, prenom_livreur, tel_livreur, nb_retard));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list_livreurs;
	}

	// Récupère la liste des véhicules d'un livreur
	public List<Vehicule> getListVehiculeByIdLivreur(int id_livreur) {
		return this.getListVehiculesAppartient("SELECT * FROM appartient WHERE id_livreur = " + id_livreur);
	}

	private List<Vehicule> getListVehiculesAppartient(String sql) {
		Statement request;
		ResultSet resultSet;
		int id_livreur = 0, id_vehicule = 0;
		List<Vehicule> list_vehicule = new ArrayList<Vehicule>();
		try {
			request = connect.createStatement();
			resultSet = request.executeQuery(sql);
			while (resultSet.next()) {
				id_livreur = resultSet.getInt("id_livreur");
				id_vehicule = resultSet.getInt("id_vehicule");
				list_vehicule = this.getVehicules("select * from vehicule where id_vehicule = " + id_vehicule);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list_vehicule;
	}

	private List<Vehicule> getVehicules(String sql) {
		Statement request;
		ResultSet resultSet;
		int id_vehicule = 0, nb_utilisation = 0;
		String type_vehicule = null, marque_vehicule = null, immatriculation = null;
		List<Vehicule> list_vehicule = new ArrayList<Vehicule>();
		try {
			request = connect.createStatement();
			resultSet = request.executeQuery(sql);
			while (resultSet.next()) {
				id_vehicule = resultSet.getInt("id_vehicule");
				marque_vehicule = resultSet.getString("marque_vehicule");
				type_vehicule = resultSet.getString("type_vehicule");
				immatriculation = resultSet.getString("immatriculation");
				nb_utilisation = resultSet.getInt("nb_utilisation");
				try {
					list_vehicule.add(Factory.getVehicule(id_vehicule, type_vehicule, marque_vehicule, immatriculation, nb_utilisation));
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list_vehicule;
	}*/
}


