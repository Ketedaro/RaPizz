package DAO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.sql.Date;
import java.util.List;

import pizza.Commande;
import pizza.Factory;

public class CommandeDAO extends DAO<Commande>{

	public CommandeDAO(Connection conn) {
		super(conn);
	}
	
	private static CommandeDAO INSTANCE = null;
	
	public static CommandeDAO getInstanceCommande(Connection conn){
		if(INSTANCE == null)
			INSTANCE = new CommandeDAO(conn);
		return INSTANCE;
	}
	
	@Override
	public boolean create(Commande commande) {
		String sql = "INSERT INTO commande (id_commande, prix_commande, date_commande, temps_livraison) VALUES ("
			+ null 
			+ ", " + commande.getId_commande() 
			+ ", " + commande.getPrix_commande() 
			+ ", " + commande.getDate_commande() 
			+ ", " + commande.getTemps_livraison() + ")";
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
	public boolean delete(Commande commande) {
		String sql = "DELETE * FROM commande where id_commande =" + commande.getId_commande();
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
	public boolean update(Commande commande) {
		String sql = "UPDATE commande SET prix_commande = " + commande.getPrix_commande()
		+ ", date_commande = " + commande.getDate_commande()
		+ ", temps_livraison = " + commande.getTemps_livraison()
		+ " where id_commande = "+ commande.getId_commande();
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
	public Commande find(int id) {
		return this.getCommande("SELECT * FROM commande WHERE id_commande = " + id).get(0);
	}

	private List<Commande> getCommande(String sql) {
		Statement request;
		ResultSet resultSet;
		Date date_commande = null;
		Date temps_livraison = null;
		int id_commande = 0;
		float prix_commande = 0;
		List<Commande> list_commande = new ArrayList<Commande>();
		try {
			request = connect.createStatement();
			resultSet = request.executeQuery(sql);

			while (resultSet.next()) {
				id_commande = resultSet.getInt("id_commande");
				date_commande = resultSet.getDate("date_commande");
				temps_livraison = resultSet.getDate("temps_livraison");
				prix_commande = resultSet.getInt("prix_commande");
				list_commande.add(Factory.getCommande(id_commande, prix_commande, date_commande ,temps_livraison));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list_commande;
	}


}