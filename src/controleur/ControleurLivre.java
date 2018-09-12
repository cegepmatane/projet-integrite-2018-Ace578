package controleur;

import java.util.ArrayList;
import java.util.List;

import accesseur.LivreDAO;
import modele.Livre;
import vue.NavigateurDesVues;
import vue.VueAjouterLivre;
import vue.VueListeLivre;
import vue.VueLivre;

public class ControleurLivre {

	private NavigateurDesVues navigateur = null;
	
	private VueLivre vueLivre = null;
	private VueListeLivre vueListeLivre = null;
	private VueAjouterLivre vueAjouterLivre = null;
	LivreDAO livreDAO = null;
	
	private ControleurLivre() {
		System.out.println("Initialisation du controleur");
		this.livreDAO = new LivreDAO();
	}
	
	private static ControleurLivre instance = null; 
	
	
	public static ControleurLivre getInstance() {
		
		if(instance==null) {
			instance = new ControleurLivre();
		}
		return instance;
	} 
	
	
	public void activerVues(NavigateurDesVues navigateur) {
		
		
		this.navigateur = navigateur;
		this.vueLivre = this.navigateur.getVueLivre();
		this.vueListeLivre = this.navigateur.getVueListeLivre();
		this.vueAjouterLivre = this.navigateur.getVueAjouterLivre();
		
		
		Livre Livre = new Livre("Le Seigneur des Anneaux", "1954", "Fantasy");
		this.vueLivre.afficherLivre(Livre); 
		//TEST//
		LivreDAO LivreDAO = new LivreDAO();
		List<Livre> listeLivresTest = LivreDAO.listerLivres();
		this.navigateur.naviguerVersVueAjouterLivre();
		this.navigateur.naviguerVersVueListeLivre();
		this.navigateur.naviguerVersVueLivre();
		
	}
	
	public void notifierEnregistrerLivre() {
		System.out.println("ControleurLivre.notifierEnregistrerLivre()");
		Livre livre = this.navigateur.getVueAjouterLivre().demanderLivre();
		this.livreDAO.ajouterLivre(livre);
		this.navigateur.naviguerVersVueListeLivre();
		
	}
	
}
