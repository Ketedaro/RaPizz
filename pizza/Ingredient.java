package pizza;

public class Ingredient {
	private int id_ingredient;
	private String nom_ingredient;
	private int score_ingredient;
	
	public Ingredient(int id_ingredient, String nom_ingredient, int score_ingredient) {
		this.id_ingredient = id_ingredient;
		this.nom_ingredient = nom_ingredient;
		this.score_ingredient = score_ingredient;
	}

	public int getId_ingredient() {
		return id_ingredient;
	}

	public String getNom_ingredient() {
		return nom_ingredient;
	}

	public int getScore_ingredient() {
		return score_ingredient;
	}

}
