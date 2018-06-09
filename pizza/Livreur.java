package pizza;

public class Livreur {
	private int id_livreur;
	private String nom_livreur;
	private String prenom_livreur;
	private String tel_livreur;
	private int nb_retard;
	
	public Livreur(int id_livreur, String nom_livreur, String prenom_livreur, String tel_livreur) {
		this.id_livreur = id_livreur;
		this.nom_livreur = nom_livreur;
		this.prenom_livreur = prenom_livreur;
		this.tel_livreur = tel_livreur;
		this.nb_retard = 0;
	}
	
	public Livreur(int id_livreur, String nom_livreur, String prenom_livreur, String tel_livreur, int nb_retard) {
		this.id_livreur = id_livreur;
		this.nom_livreur = nom_livreur;
		this.prenom_livreur = prenom_livreur;
		this.tel_livreur = tel_livreur;
		this.nb_retard = nb_retard;
	}

	public int getId_livreur() {
		return id_livreur;
	}

	public String getNom_livreur() {
		return nom_livreur;
	}

	public String getPrenom_livreur() {
		return prenom_livreur;
	}

	public String getTel_livreur() {
		return tel_livreur;
	}

	public int getNb_retard() {
		return nb_retard;
	}

}
