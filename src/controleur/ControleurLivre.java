package controleur;

import java.util.ArrayList;
import java.util.List;

import accesseur.LivreDAO;
import accesseur.PrixDAO;
import modele.Livre;
import modele.Prix;
import vue.NavigateurDesVues;
import vue.VueAjouterLivre;
import vue.VueEditerLivre;
import vue.VueListeLivre;
import vue.VueLivre;

public class ControleurLivre {

	private NavigateurDesVues navigateur = null;
	
	private VueLivre vueLivre = null;
	private VueListeLivre vueListeLivre = null;
	private VueAjouterLivre vueAjouterLivre = null;
	private VueEditerLivre vueEditerLivre = null;
	private LivreDAO livreDAO = null;
	private PrixDAO prixDAO = null;
	
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
		this.vueEditerLivre = navigateur.getVueEditerLivre();
		
		
		Livre Livre = new Livre("Le Seigneur des Anneaux", "1954", "Fantasy");
		this.vueLivre.afficherLivre(Livre); 
		
		this.navigateur.naviguerVersVueLivre();
		//TEST//
		
		List<Livre> listeLivresTest = livreDAO.listerLivres();
		this.vueListeLivre.afficherListeLivre(listeLivresTest);
		
		
	
		
		this.navigateur.naviguerVersVueListeLivre();
		
		
		
		prixDAO = new PrixDAO();
		vueEditerLivre.afficherListePrix(prixDAO.listerPrix());
		
		
	}
	
	public void notifierEnregistrerLivre() {
		System.out.println("ControleurLivre.notifierEnregistrerLivre()");
		Livre livre = this.navigateur.getVueEditerLivre().demanderLivre();
		this.livreDAO.modifierLivre(livre);
		this.vueListeLivre.afficherListeLivre(this.livreDAO.listerLivres());
		this.navigateur.naviguerVersVueListeLivre();
		
	}
	
	public void notifierEnregistrerNouveauLivre () {
		System.out.println("ControleurLivre.notifierEnregistrerNouveauLivre()");
		Livre livre = this.navigateur.getVueAjouterLivre().demanderLivre();
		this.livreDAO.ajouterLivre(livre);
		this.vueListeLivre.afficherListeLivre(this.livreDAO.listerLivres());
		this.navigateur.naviguerVersVueListeLivre();
		
	}
	
	public void notifierNaviguerAjouterLivre() {
		System.out.println("ControleurLivre.notifierNaviguerAjouterLivre()");
		this.navigateur.naviguerVersVueAjouterLivre();
	}
	
	public void notifierNaviguerEditerLivre(int idLivre) {
		System.out.println("ControleurLivre.notifierNaviguerEditerLivre("+idLivre+")");
		
		this.vueEditerLivre.afficherLivre(this.livreDAO.rapporterLivre(idLivre));
		this.navigateur.naviguerVersVueEditerLivre();
	}
	
}
