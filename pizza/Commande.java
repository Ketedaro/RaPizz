package pizza;

import java.sql.Date;

public class Commande {
	private int id_commande;
	private float prix_commande;
	private Date date_commande;
	private Date temps_livraison;
	
	public Commande(int id_commande, float prix_commande, Date date_commande, Date temps_livraison) {
		super();
		this.id_commande = id_commande;
		this.prix_commande = prix_commande;
		this.date_commande = date_commande;
		this.temps_livraison = temps_livraison;
	}

	public int getId_commande() {
		return id_commande;
	}

	public float getPrix_commande() {
		return prix_commande;
	}

	public Date getDate_commande() {
		return date_commande;
	}
	
	public String getDate() {
		return date_commande.toString();
	}

	public Date getTemps_livraison() {
		return temps_livraison;
	}
	
	public String getTemps() {
		return temps_livraison.toString();
	}

}
