package DAO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import pizza.Factory;
import pizza.Ingredient;
import pizza.Pizza;

public class PizzaDAO extends DAO<Pizza>{

	public PizzaDAO(Connection conn) {
		super(conn);
	}
	
	private static PizzaDAO INSTANCE = null;
	
	public static PizzaDAO getInstancePizza(Connection conn){
		if(INSTANCE == null)
			INSTANCE = new PizzaDAO(conn);
		return INSTANCE;
	}
	
	@Override
	public boolean create(Pizza pizza) {
		String sql = "INSERT INTO pizza (id_pizza, nom_pizza, prix_pizza, score_pizza) VALUES ("
				+ null +  "," + pizza.getId_pizza() + ", " + pizza.getNom_pizza() + ", " + pizza.getPrix_pizza() + ", "+ pizza.getScore_pizza()+ ")";     
		Statement request = null;
		try {
			request = connect.createStatement();
			request.executeUpdate(sql);
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean delete(Pizza pizza) {
		String sql = "DELETE * FROM pizza where id_pizza =" + pizza.getId_pizza();
		Statement request = null;
		try {
			request = connect.createStatement();
			request.executeUpdate(sql);
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean update(Pizza pizza) {
		String sql = "UPDATE pizza SET where id_pizza =" + pizza.getId_pizza()
		+ ", nom_pizza = " + pizza.getNom_pizza()
		+ ", prix_pizza = " + pizza.getPrix_pizza()
		+ ", score_pizza  = " + pizza.getScore_pizza();
		Statement request = null;
		try {
			request = connect.createStatement();
			request.executeUpdate(sql);
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public Pizza find(int id) {
		return this.getPizza("SELECT * FROM pizza WHERE id_pizza = " + id).get(0);
	}

	
	private List<Pizza> getPizza(String sql) {
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
		List<Ingredient> temp = new ArrayList<Ingredient>();
		Ingredient ing = null;
		try {
			request = connect.createStatement();
			resultSet = request.executeQuery(sql);
			while (resultSet.next()) {
				id_pizza = resultSet.getInt("id_pizza");
				id_ingredient = resultSet.getInt("id_ingredient");
				//list_ingredient = this.getIngredients("select * from ingredient where id_ingredient = " + id_ingredient);
				list_ingredient.add(this.getIngredient("select * from ingredient where id_ingredient = " + id_ingredient));
				//System.out.println(id_ingredient);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}


		return list_ingredient;
	}
	
	private Ingredient getIngredient(String sql) {
		Statement request;
		ResultSet resultSet;
		int id_ingredient = 0, score_ingredient = 0;
		String nom_ingredient = null;
		Ingredient ing = null;
		try {
			request = connect.createStatement();
			resultSet = request.executeQuery(sql);
			while (resultSet.next()) {
				score_ingredient = resultSet.getInt("score_ingredient");
				id_ingredient = resultSet.getInt("id_ingredient");
				nom_ingredient = resultSet.getString("nom_ingredient");
				
				try {
					ing = Factory.getIngredient(id_ingredient, nom_ingredient, score_ingredient);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return ing;
	}



}