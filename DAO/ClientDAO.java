package DAO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import data.Database;
import data.DatabaseConnect;
import pizza.Client;
import pizza.Factory;

public class ClientDAO extends DAO<Client>{
	
	private static ClientDAO INSTANCE = null;
	
	private ClientDAO(Connection conn) {
		super(conn);
	}
	
	public static ClientDAO getInstanceClient(Connection conn) {
		if (INSTANCE == null)
			INSTANCE = new ClientDAO(conn);
		return INSTANCE;
	}

	@Override
	public boolean create(Client client) {
		String sql = "INSERT INTO client (id_client, nom_client, prenom_client, email_client, password_client, tel_client, adresse_client, solde_client) VALUES ("
				+ null +  "," + client.getNom_client() + ", " + client.getPrenom_client() + ", " + client.getEmail_client() + ", "+ client.getPassword_client() + ", " + client.getTel_client() + ", " + client.getAdresse_client() + ", 0)";     
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
	public boolean delete(Client client) {
		String sql = "DELETE * FROM client where id_client =" + client.getId_client();
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
	public boolean update(Client client) {
		String sql = "UPDATE client SET nom_client = " + client.getNom_client() + ", prenom_client = " + client.getPrenom_client() + ", email_client = " + client.getEmail_client() + ", password_client = " + client.getPassword_client() 
		+ ", tel_client" +  client.getTel_client() + ", adresse_client = " + client.getAdresse_client() + ", solde_client = " + client.getSolde_client() + " where id_client =" + client.getId_client();
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
	public Client find(int id) {
		return this.getClient("SELECT * FROM client WHERE id_client = " + id).get(0);
	}

	private List<Client> getClient(String sql) {
		Statement request;
		ResultSet resultSet;
		String nom_client = null, prenom_client = null, tel_client = null, adresse_client = null, password_client = null, email_client = null;
		int id_client = 0, score_client = 0;
		float solde_client = 0;
		List<Client> list_clients = new ArrayList<Client>();
		try {
			request = connect.createStatement();
			resultSet = request.executeQuery(sql);

			while (resultSet.next()) {
				id_client = resultSet.getInt("id_client");
				nom_client = resultSet.getString("nom_client");
				prenom_client = resultSet.getString("prenom_client");
				password_client = resultSet.getString("password_client");
				email_client = resultSet.getString("email_client");
				tel_client = resultSet.getString("tel_client");
				adresse_client = resultSet.getString("adresse_client");
				score_client = resultSet.getInt("score_client");
				solde_client = resultSet.getFloat("solde_client");
				list_clients.add(Factory.getClient(id_client, nom_client, prenom_client, email_client, password_client,
						tel_client, adresse_client, score_client, solde_client));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list_clients;
	}
	


}
