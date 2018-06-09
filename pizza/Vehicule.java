package pizza;

public class Vehicule {
	private int id_vehicule;
	private String type_vehicule;
	private String marque_vehicule;
	private String immatriculation;
	private int nb_utilisation;
	
	public Vehicule(int id_vehicule, String type_vehicule, String marque_vehicule, String immatriculation) {
		this.id_vehicule = id_vehicule;
		this.type_vehicule = type_vehicule;
		this.marque_vehicule = marque_vehicule;
		this.immatriculation = immatriculation;
		this.nb_utilisation = 0;
	}
	
	public Vehicule(int id_vehicule, String type_vehicule, String marque_vehicule, String immatriculation, int nb_utilisation) {
		this.id_vehicule = id_vehicule;
		this.type_vehicule = type_vehicule;
		this.marque_vehicule = marque_vehicule;
		this.immatriculation = immatriculation;
		this.nb_utilisation = nb_utilisation;
	}

	public int getId_vehicule() {
		return id_vehicule;
	}

	public String getType_vehicule() {
		return type_vehicule;
	}

	public String getMarque_vehicule() {
		return marque_vehicule;
	}

	public String getImmatriculation() {
		return immatriculation;
	}
	
	public int getNb_utilisation() {
		return nb_utilisation;
	}

}
