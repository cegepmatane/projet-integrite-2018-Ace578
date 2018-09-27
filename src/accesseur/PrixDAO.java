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
import modele.Prix;
import modele.Prix;

public class PrixDAO {


	private Connection connection = null;
	
	
	public PrixDAO() {
		this.connection = BaseDeDonnees.getInstance().getConnection();
	 
	}
	
	public List<Prix> listerPrix(int idLivre) {
		List<Prix> listePrix = new ArrayList<Prix>();
		PreparedStatement requeteListePrix;
		String SQL_LISTER_PRIX_PAR_LIVRE = "SELECT * FROM prix WHERE livre=?";
		try {
			requeteListePrix = connection.prepareStatement(SQL_LISTER_PRIX_PAR_LIVRE);
			requeteListePrix.setInt(1, idLivre);
			ResultSet curseurListePrix = requeteListePrix.executeQuery();
			
			
			while (curseurListePrix.next()) {
				int id = curseurListePrix.getInt("id");
				String nom = curseurListePrix.getString("nom");
				String promotion = curseurListePrix.getString("promotion");
				String description = curseurListePrix.getString("description");
				
				Prix prix = new Prix(nom, promotion, description);
				
				prix.setId(id);
				listePrix.add(prix);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		//return this.simulerListerPrixs();
		return listePrix;
	}
	
	public List<Prix> listerTousPrix() {
		List<Prix> listePrix = new ArrayList<Prix>();
		try {
			
			
			Statement requeteListePrix = connection.createStatement();
			ResultSet curseurListePrix = requeteListePrix.executeQuery("SELECT * FROM prix");
			while (curseurListePrix.next()) {
				int id = curseurListePrix.getInt("id");
				String nom = curseurListePrix.getString("nom");
				String promotion = curseurListePrix.getString("promotion");
				String description = curseurListePrix.getString("description");
				
				Prix prix = new Prix(nom, promotion, description);
				
				prix.setId(id);
				listePrix.add(prix);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		//return this.simulerListerPrixs();
		return listePrix;
	}
	
	public void modifierPrix(Prix prix) {
		System.out.println("PrixDAO.modifierPrix()");
		
		try {
			String SQL_MODIFIER_PRIX = "UPDATE prix SET nom = ?, promotion = ?, description = ? WHERE id=?";
			PreparedStatement requeteModifierPrix = connection.prepareStatement(SQL_MODIFIER_PRIX);
			requeteModifierPrix.setString(1, prix.getNom());
			requeteModifierPrix.setString(2, prix.getPromotion());
			requeteModifierPrix.setString(3, prix.getDescription());
			requeteModifierPrix.setInt(4, prix.getId());
			
			requeteModifierPrix.execute();
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
	}
	
	public Prix rapporterPrix(int idPrix) {
		Statement requetePrix;
		
		try {
			requetePrix = connection.createStatement();
			String SQL_RAPPORTER_PRIX = "SELECT * FROM prix WHERE id ="+idPrix;
			System.out.println(SQL_RAPPORTER_PRIX);
			ResultSet curseurPrix = requetePrix.executeQuery(SQL_RAPPORTER_PRIX);
			curseurPrix.next();
			int id = curseurPrix.getInt("id");
			String nom = curseurPrix.getString("nom");
			String promotion = curseurPrix.getString("promotion");
			String description = curseurPrix.getString("description");
			
			Prix prix = new Prix(nom, promotion, description);
			
			prix.setId(id);
			return prix;
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
		
		
		return null;
	}
	
	public void ajouterPrix(Prix prix) {
		System.out.println("PrixDAO.ajouterPrix()");
		try {
			String SQL_AJOUTER_PRIX = "INSERT into prix(nom, promotion, description) VALUES (?,?,?)";
			PreparedStatement requeteAjouterPrix = connection.prepareStatement(SQL_AJOUTER_PRIX);
			requeteAjouterPrix.setString(1, prix.getNom());
			requeteAjouterPrix.setString(2, prix.getPromotion());
			requeteAjouterPrix.setString(3, prix.getDescription());
			requeteAjouterPrix.execute();
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
		
	}
	
	/**
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
	*/
	
	
}
