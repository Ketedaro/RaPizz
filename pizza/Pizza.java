package pizza;

import java.util.List;

public class Pizza {
	private int id_pizza;
	private String nom_pizza;
	private float prix_pizza;
	private int score_pizza;
	private List<Ingredient> list_ingredient;
	
	public Pizza(int id_pizza, String nom_pizza, float prix_pizza, int score_pizza, List<Ingredient> list_ingredient) {
		this.id_pizza = id_pizza;
		this.nom_pizza = nom_pizza;
		this.prix_pizza = prix_pizza;
		this.score_pizza = score_pizza;
		this.list_ingredient = list_ingredient;
	}
	
	public int getId_pizza() {
		return id_pizza;
	}

	public String getNom_pizza() {
		return nom_pizza;
	}

	public float getPrix_pizza() {
		return prix_pizza;
	}

	public int getScore_pizza() {
		return score_pizza;
	}
	
	public void setScore_pizza(int score_pizza) {
		this.score_pizza = score_pizza;
	}

	public List<Ingredient> getList_ingredient() {
		return list_ingredient;
	}
}
