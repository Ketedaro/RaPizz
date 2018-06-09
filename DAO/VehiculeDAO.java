package DAO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import pizza.Factory;
import pizza.Vehicule;

public class VehiculeDAO extends DAO<Vehicule>{

	public VehiculeDAO(Connection conn) {
		super(conn);
	}
	
	private static VehiculeDAO INSTANCE = null;
	
	public static VehiculeDAO getInstanceVehicule(Connection conn){
		if(INSTANCE == null)
			INSTANCE = new VehiculeDAO(conn);
		return INSTANCE;
	}

	
	@Override
	public boolean create(Vehicule vehicule) {
		String sql = "INSERT INTO vehicule (id_vehicule, type_vehicule, marque_vehicule, immatriculation, nb_utilisation) VALUES ("
				+ null +  "," + vehicule.getId_vehicule() + ", " + vehicule.getType_vehicule() + ", " + vehicule.getMarque_vehicule() + ", "+ vehicule.getImmatriculation()+ ", " + vehicule.getNb_utilisation() + ")";     
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
	public boolean delete(Vehicule vehicule) {
		String sql = "DELETE * FROM vehicule where id_vehicule =" + vehicule.getId_vehicule();
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
	public boolean update(Vehicule vehicule) {
		String sql = "UPDATE vehicule SET type_vehicule = " + vehicule.getType_vehicule()
		+ ", marque_vehicule = " + vehicule.getMarque_vehicule()
		+ ", immatriculation = " + vehicule.getImmatriculation()
		+ ", nb_utilisation  = " + vehicule.getNb_utilisation()
		+ " where id_vehicule = " + vehicule.getId_vehicule();
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
	public Vehicule find(int id) {
		return this.getVehicule("SELECT * FROM vehicule WHERE id_vehicule = " + id).get(0);
	}

	private List<Vehicule> getVehicule(String sql) {
		Statement request;
		ResultSet resultSet;
		String type_vehicule = null;
		String marque_vehicule = null;
		String immatriculation = null;
		int id_vehicule = 0, nb_utilisation = 0;
		List<Vehicule> list_vehicule = new ArrayList<Vehicule>();
		try {
			request = connect.createStatement();
			resultSet = request.executeQuery(sql);

			while (resultSet.next()) {
				id_vehicule = resultSet.getInt("id_vehicule");
				type_vehicule = resultSet.getString("type_vehicule");
				marque_vehicule = resultSet.getString("marque_vehicule");
				immatriculation = resultSet.getString("immatriculation");
				nb_utilisation = resultSet.getInt("nb_utilisation");
				list_vehicule.add(Factory.getVehicule(id_vehicule, type_vehicule, marque_vehicule, immatriculation, nb_utilisation));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list_vehicule;
	}




}