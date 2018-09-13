package accesseur;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import modele.Prix;

public class PrixDAO {

	protected String BASEDEDONNEES_DRIVER = "org.postgresql.Driver";
	protected String BASEDEDONNEES_URL = "jdbc:postgresql://localhost:5432/bibliotheque";
	protected String BASEDEDONNEES_USAGER = "postgres";
	protected String BASEDEDONNEES_MOTDEPASSE = "password";
	private Connection connection = null;
	
	
	public PrixDAO() {
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
	
	public List<Prix> simulerListePrix(){
		Prix prix;
		List<Prix> listePrix = new ArrayList<Prix>();
		prix = new Prix("Prix d'écriture", "1960");
		listePrix.add(prix);
		prix = new Prix("Prix de style", "1970");
		listePrix.add(prix);
		prix = new Prix("Prix de la comédie", "1990");
		listePrix.add(prix);
		prix = new Prix("Prix de la couveture", "1980");
		listePrix.add(prix);
		
		return listePrix;
	}
}
