package data;

public class Database {
	
	private static Database INSTANCE = null;
	private DatabaseConnect dataBConnect;
	
	private Database() {
		this.dataBConnect = DatabaseConnect.getDataBase();
	}
	
	public static Database getDataBase() {
		if (INSTANCE == null)
			INSTANCE = new Database();
		return INSTANCE;
	}


}
