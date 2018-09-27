package accesseur;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class BaseDeDonnees {
	
	protected String BASEDEDONNEES_DRIVER = "org.postgresql.Driver";
	protected String BASEDEDONNEES_URL = "jdbc:postgresql://localhost:5432/bibliotheque";
	protected String BASEDEDONNEES_USAGER = "postgres";
	protected String BASEDEDONNEES_MOTDEPASSE = "password";
	private Connection connection = null;
	
	private BaseDeDonnees() {
		try {
			Class.forName(BASEDEDONNEES_DRIVER);
		} catch (ClassNotFoundException e) {
			
			e.printStackTrace();
		}
		
		
		try {
			connection = DriverManager.getConnection(BASEDEDONNEES_URL, BASEDEDONNEES_USAGER, BASEDEDONNEES_MOTDEPASSE);
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
	 
	}
	
	private static BaseDeDonnees instance = null;
	
	public static BaseDeDonnees getInstance() {
		if(null == instance) {
			instance = new BaseDeDonnees();
		
		}
		return instance;
	}
	
	public Connection getConnection() {
		return this.connection;
	}
	
	

}
