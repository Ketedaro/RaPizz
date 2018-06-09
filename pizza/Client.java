package pizza;

public class Client {
	private int id_client;
	private String nom_client;
	private String prenom_client;
	private String email_client;
	private String password_client;
	private String tel_client;
	private String adresse_client;
	private int score_client;
	private float solde_client;
	
	public Client(int id_client, String nom_client, String prenom_client, String email_client, String password_client,
			String tel_client, String adresse_client) {
		this.id_client = id_client;
		this.nom_client = nom_client;
		this.prenom_client = prenom_client;
		this.email_client = email_client;
		this.password_client = password_client;
		this.tel_client = tel_client;
		this.adresse_client = adresse_client;
		this.score_client = 0;
		this.solde_client = 0;
	}
	
	public Client(int id_client, String nom_client, String prenom_client, String email_client, String password_client,
			String tel_client, String adresse_client, int score_client, float solde_client) {
		this.id_client = id_client;
		this.nom_client = nom_client;
		this.prenom_client = prenom_client;
		this.email_client = email_client;
		this.password_client = password_client;
		this.tel_client = tel_client;
		this.adresse_client = adresse_client;
		this.score_client = score_client;
		this.solde_client = solde_client;
	}

	public int getId_client() {
		return id_client;
	}

	public String getNom_client() {
		return nom_client;
	}

	public String getPrenom_client() {
		return prenom_client;
	}

	public String getEmail_client() {
		return email_client;
	}

	public String getPassword_client() {
		return password_client;
	}

	public String getTel_client() {
		return tel_client;
	}

	public String getAdresse_client() {
		return adresse_client;
	}

	public int getScore_client() {
		return score_client;
	}

	public float getSolde_client() {
		return solde_client;
	}
	
}
