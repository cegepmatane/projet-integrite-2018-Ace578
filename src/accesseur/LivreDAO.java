package accesseur;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import modele.Livre;




public class LivreDAO {
	
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
		
		
		
		this.connection = BaseDeDonnees.getInstance().getConnection();
		
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
				
				String SQL_AJOUTER_LIVRE = "INSERT into livre(titre, annee, style) VALUES (?,?,?)";
				PreparedStatement requeteAjouterLivre = connection.prepareStatement(SQL_AJOUTER_LIVRE);
				requeteAjouterLivre.setString(1, livre.getTitre());
				requeteAjouterLivre.setString(2, livre.getAnnee());
				requeteAjouterLivre.setString(3, livre.getStyle());
				requeteAjouterLivre.execute();
			} catch (SQLException e) {
				
				e.printStackTrace();
			}
			
			
		}
		
		public void supprimerLivre(Livre livre) {
			String SQL_SUPPRIMER_LIVRE = "DELETE FROM livre WHERE id=?";
			try {
				PreparedStatement requeteSupprimerLivre = connection.prepareStatement(SQL_SUPPRIMER_LIVRE);
				requeteSupprimerLivre.setInt(1, livre.getId());
				requeteSupprimerLivre.execute();
				} catch (SQLException e) {
				
				e.printStackTrace();
			}
			
		}
		
		public void modifierLivre(Livre livre) {
			System.out.println("LivreDAO.modifierLivre()");
			
			try {
				
				String SQL_MODIFIER_LIVRE = "UPDATE livre SET titre = ?, annee = ?, style = ? WHERE id=?";
				PreparedStatement requeteModifierLivre = connection.prepareStatement(SQL_MODIFIER_LIVRE);
				requeteModifierLivre.setString(1, livre.getTitre());
				requeteModifierLivre.setString(2, livre.getAnnee());
				requeteModifierLivre.setString(3, livre.getStyle());
				requeteModifierLivre.setInt(4, livre.getId());
				
				requeteModifierLivre.execute();
			} catch (SQLException e) {
				
				e.printStackTrace();
			}
			
		}
		
}
