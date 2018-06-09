package appli;

import hash.Hashage;
import pizza.Client;
import pizza.Ingredient;
import pizza.Livreur;
import pizza.Pizza;
import pizza.Vehicule;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import DAO.ClientDAO;
import DAO.DAO;
import DAO.PizzaDAO;
import data.Database;
import data.DatabaseConnect;

public class Appli {
	
	public static void main(String[] args) {
		DatabaseConnect database = DatabaseConnect.getDataBase();
		

		
		DAO<Pizza> pizza = PizzaDAO.getInstancePizza(database.getCo());
		Pizza p = pizza.find(1);

		for (Ingredient ing: p.getList_ingredient()) {
			System.out.println(ing.getNom_ingredient());
		}
		
		Date date = new Date(118, 9, 10);
		System.out.println(date.toString());
		
		String temp;
		
		DAO<Client> clientDAO = ClientDAO.getInstanceClient(database.getCo()); 
		Client client = clientDAO.find(1);
		if (client != null) {
			temp = "Bienvenue " + client.getPrenom_client() + " " + client.getNom_client();
		} else {
			temp = "Il n'y a pas d'utilisateur associé à cet email";
		}
		
		System.out.println(temp);
	}
}
