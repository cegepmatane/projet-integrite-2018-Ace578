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
	
	
	String BASEDEDONNEES_DRIVER = "org.postgresql.Driver";
	String BASEDEDONNEES_URL = "jdbc:postgresql://localhost:5432/bibliotheque";
	String BASEDEDONNEES_USAGER = "postgres";
	String BASEDEDONNEES_MOTDEPASSE = "password";
	private Connection connection = null;

	private List<Livre> simulerListerLivres() {
		List listeLivresTest = new ArrayList<Livre>();
		listeLivresTest.add(new Livre("Le Seigneur des Anneaux", "1954", "Fantasy"));
		listeLivresTest.add(new Livre("Je suis une légende", "1954", "Post-Apocalyptique"));
		listeLivresTest.add(new Livre("La ferme des animaux", "1945", "Apologue"));
		listeLivresTest.add(new Livre("Le vieil homme et la mer", "1952", "Fiction"));
		return listeLivresTest;
	}
	
	public LivreDAO() {
		
		
		
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
		
		
		
		
		
		public List<Livre> listerLivres() {
			
		List<Livre> listeLivres = new ArrayList<Livre>();
		try {
			
			
			Statement requeteListeLivres = connection.createStatement();
			ResultSet curseurListeLivres = requeteListeLivres.executeQuery("SELECT * FROM livre");
			while (curseurListeLivres.next()) {
				int id = curseurListeLivres.getInt("id");
				String titre = curseurListeLivres.getString("titre");
				String annee = curseurListeLivres.getString("annee");
				String style = curseurListeLivres.getString("style");
				System.out.println("Livre " + titre + " écris en " + annee + " : " + style);
				Livre livre = new Livre(titre, annee, style);
				livre.setId(id);
				listeLivres.add(livre);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		//return this.simulerListerLivres();
		return listeLivres;
	}
		
		
		public Livre rapporterLivre(int idLivre) {
			Statement requeteLivre;
			
			try {
				requeteLivre = connection.createStatement();
				String SQL_RAPPORTER_LIVRE = "SELECT * FROM livre WHERE id ="+idLivre;//refaire la requête préparée pour la sécurité
				System.out.println(SQL_RAPPORTER_LIVRE);
				ResultSet curseurLivre = requeteLivre.executeQuery(SQL_RAPPORTER_LIVRE);
				curseurLivre.next();
				int id = curseurLivre.getInt("id");
				String titre = curseurLivre.getString("titre");
				String annee = curseurLivre.getString("annee");
				String style = curseurLivre.getString("style");
				System.out.println("Le livre "+titre+"a été écris en "+annee+" et est du style "+style);
				Livre livre = new Livre(titre, annee, style);
				livre.setId(id);
				return livre;
			} catch (SQLException e) {
				
				e.printStackTrace();
			}
			
			
			
			return null;
		}
		
		public void ajouterLivre(Livre livre) {
			System.out.println("LivreDAO.ajouterLivre()");
			try {
				Statement requeteAjouterLivre = connection.createStatement();
				String sqlAjouterLivre = "INSERT into livre(titre, annee, style) VALUES ('"+livre.getTitre()+"','"+livre.getAnnee()+"','"+livre.getStyle()+"')";
				System.out.println("SQL : " +sqlAjouterLivre);
				requeteAjouterLivre.executeQuery(sqlAjouterLivre);
			} catch (SQLException e) {
				
				e.printStackTrace();
			}
			
			
		}
		
		public void modifierLivre(Livre livre) {
			System.out.println("LivreDAO.modifierLivre()");
			
			try {
				Statement requeteModifierLivre = connection.createStatement();
				String SQL_MODIFIER_LIVRE = "UPDATE livre SET titre ='"+livre.getTitre()+"', annee ='"+livre.getAnnee()+"', style ='"+livre.getStyle()+"'WHERE id ="+livre.getId();
				requeteModifierLivre.execute(SQL_MODIFIER_LIVRE);
			} catch (SQLException e) {
				
				e.printStackTrace();
			}
			
		}
		
}
