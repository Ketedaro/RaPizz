package DAO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import pizza.Factory;
import pizza.Ingredient;

public class IngredientDAO extends DAO<Ingredient>{

	public IngredientDAO(Connection conn) {
		super(conn);
	}
	
	private static IngredientDAO INSTANCE = null;
	
	public static IngredientDAO getInstanceIngredient(Connection conn){
		if(INSTANCE == null)
			INSTANCE = new IngredientDAO(conn);
		return INSTANCE;
	}
	
	@Override
	public boolean create(Ingredient ingredient) {
		String sql = "INSERT INTO ingredient (id_ingredient, nom_ingredient, score_ingredient) VALUES ("
				+ null +  "," + ingredient.getId_ingredient() + ", " + ingredient.getNom_ingredient() + ", " + ingredient.getScore_ingredient()+ ")";     
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
	public boolean delete(Ingredient ingredient) {
		String sql = "DELETE * FROM ingredient where id_ingredient =" + ingredient.getId_ingredient();
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
	public boolean update(Ingredient ingredient) {
		String sql = "UPDATE ingredient SET nom_ingredient = " + ingredient.getNom_ingredient()
		+ ", score_ingredient = " + ingredient.getScore_ingredient()
		+ " where id_ingredient =" + ingredient.getId_ingredient();
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
	public Ingredient find(int id) {
		return this.getIngredient("SELECT * FROM ingredient WHERE id_ingredient = " + id).get(0);
	}

	private List<Ingredient> getIngredient(String sql) {
		Statement request;
		ResultSet resultSet;
		String nom_ingredient = null;
		int id_ingredient = 0, score_ingredient = 0;
		List<Ingredient> list_ingredient = new ArrayList<Ingredient>();
		try {
			request = connect.createStatement();
			resultSet = request.executeQuery(sql);

			while (resultSet.next()) {
				id_ingredient = resultSet.getInt("id_ingredient");
				nom_ingredient = resultSet.getString("nom_ingredient");
				score_ingredient = resultSet.getInt("score_ingredient");
				list_ingredient.add(Factory.getIngredient(id_ingredient, nom_ingredient, score_ingredient));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list_ingredient;
	}


}