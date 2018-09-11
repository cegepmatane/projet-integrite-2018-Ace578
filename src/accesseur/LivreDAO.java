package accesseur;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import modele.Livre;

public class LivreDAO {

	private List<Livre> simulerListerLivres() {
		List listeLivresTest = new ArrayList<Livre>();
		listeLivresTest.add(new Livre("Le Seigneur des Anneaux", "1954", "Fantasy"));
		listeLivresTest.add(new Livre("Je suis une légende", "1954", "Post-Apocalyptique"));
		listeLivresTest.add(new Livre("La ferme des animaux", "1945", "Apologue"));
		listeLivresTest.add(new Livre("Le vieil homme et la mer", "1952", "Fiction"));
		return listeLivresTest;
	}
	
	public List<Livre> listerLivres() {
		
		String BASEDEDONNEES_DRIVER = "org.postgresql.Driver";
		String BASEDEDONNEES_URL = "jdbc:postgresql://localhost:5432/bibliotheque";
		String BASEDEDONNEES_USAGER = "postgres";
		String BASEDEDONNEES_MOTDEPASSE = "jeff57880";
		
		try {
			Class.forName(BASEDEDONNEES_DRIVER);
		} catch (ClassNotFoundException e) {
			
			e.printStackTrace();
		}
		
		try {
			DriverManager.getConnection(BASEDEDONNEES_URL, BASEDEDONNEES_USAGER, BASEDEDONNEES_MOTDEPASSE);
			Connection connection = DriverManager.getConnection(BASEDEDONNEES_URL, BASEDEDONNEES_USAGER, BASEDEDONNEES_MOTDEPASSE);
			
			Statement requeteListeLivres = connection.createStatement();
			ResultSet curseurListeLivres = requeteListeLivres.executeQuery("SELECT * FROM livre");
			curseurListeLivres.next();
			String titre = curseurListeLivres.getString("titre");
			String annee = curseurListeLivres.getString("annee");
			String style = curseurListeLivres.getString("style");
			
			System.out.println("Livre " + titre + " écris en " + annee + " : " + style);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return this.simulerListerLivres();
	}
}
