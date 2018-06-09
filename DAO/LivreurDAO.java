package DAO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import pizza.Factory;
import pizza.Livreur;

public class LivreurDAO extends DAO<Livreur>{

	public LivreurDAO(Connection conn) {
		super(conn);
	}
	
	private static LivreurDAO INSTANCE = null;
	
	public static LivreurDAO getInstanceLivreur(Connection conn){
		if(INSTANCE == null)
			INSTANCE = new LivreurDAO(conn);
		return INSTANCE;
	}
		
	@Override
	public boolean create(Livreur livreur) {
		String sql = "INSERT INTO livreur (id_livreur, nom_livreur, prenom_livreur, tel_livreur, nb_retard) VALUES ("
				+ null +  "," + livreur.getId_livreur() + ", " + livreur.getNom_livreur() + ", " + livreur.getPrenom_livreur() + ", "+ livreur.getTel_livreur()+ ", " + livreur.getNb_retard() + ")";     
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
	public boolean delete(Livreur livreur) {
		String sql = "DELETE * FROM livreur where id_livreur =" + livreur.getId_livreur();
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
	public boolean update(Livreur livreur) {
		String sql = "UPDATE livreur SET nom_livreur = " 
		+ livreur.getNom_livreur() 
		+ ", prenom_livreur = " + livreur.getPrenom_livreur() 
		+ ", tel_livreur = " + livreur.getTel_livreur() 
		+ ", nb_retard = " + livreur.getNb_retard() 
		+ " where id_livreur = " + livreur.getId_livreur();
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
	public Livreur find(int id) {
		return this.getLivreur("SELECT * FROM livreur WHERE id_livreur = " + id).get(0);
	}

	private List<Livreur> getLivreur(String sql) {
		Statement request;
		ResultSet resultSet;
		String nom_livreur = null;
		String prenom_livreur = null;
		String tel_livreur = null;
		int id_livreur = 0, nb_retard = 0;
		List<Livreur> list_livreur = new ArrayList<Livreur>();
		try {
			request = connect.createStatement();
			resultSet = request.executeQuery(sql);

			while (resultSet.next()) {
				id_livreur = resultSet.getInt("id_livreur");
				nom_livreur = resultSet.getString("nom_livreur");
				prenom_livreur = resultSet.getString("prenom_livreur");
				tel_livreur = resultSet.getString("tel_livreur");
				nb_retard = resultSet.getInt("nb_retard");
				list_livreur.add(Factory.getLivreur(id_livreur, nom_livreur, prenom_livreur, tel_livreur, nb_retard));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list_livreur;
	}


}