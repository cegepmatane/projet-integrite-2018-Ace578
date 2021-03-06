package controleur;
import java.util.List;

import accesseur.PrixDAO;
import accesseur.PrixDAO;
import modele.Livre;
import modele.Prix;
import modele.Prix;
import vue.NavigateurDesVues;
import vue.VueAjouterPrix;
import vue.VueEditerLivre;
import vue.VueEditerPrix;
import vue.VueListePrix;
import vue.VuePrix;
public class ControleurPrix {
	
	private NavigateurDesVues navigateur;
	private PrixDAO prixDAO;
	private VuePrix vuePrix = null;
	private VueListePrix vueListePrix = null;
	private VueAjouterPrix vueAjouterPrix = null;
	private VueEditerPrix vueEditerPrix = null;

	

	
	private ControleurPrix(){
		
		this.prixDAO = new PrixDAO();
		
	}
	
	private static ControleurPrix instance = null; 
	
	public static ControleurPrix getInstance() {
		
		if(instance==null) {
			instance = new ControleurPrix();
		}
		return instance;
	} 
	
	public void activerVues(NavigateurDesVues navigateur) {
		
		
		this.navigateur = navigateur;
		this.vuePrix = this.navigateur.getVuePrix();
		this.vueListePrix = this.navigateur.getVueListePrix();
		this.vueAjouterPrix = this.navigateur.getVueAjouterPrix();
		this.vueEditerPrix = navigateur.getVueEditerPrix();
		
		
		List<Prix> listeLivresTest = prixDAO.listerTousPrix();
		this.vueListePrix.afficherListePrix(listeLivresTest);
		
		//navigateur.naviguerVersVueListePrix();

	}
	
	public void notifierEnregistrerPrix() {
		System.out.println("ControleurPrix.notifierEnregistrerPrix()");
		Prix Prix = this.navigateur.getVueEditerPrix().demanderPrix();
		this.prixDAO.modifierPrix(Prix);
		this.navigateur.naviguerVersVueListePrix();
		
	}
	
	
	public void notifierAjouterPrix (int idLivre) {
		System.out.println("ControleurPrix.notifierEnregistrerNouveauPrix()");
		Prix Prix = this.navigateur.getVueAjouterPrix().demanderPrix(); 
		this.prixDAO.ajouterPrix(Prix,idLivre);
		this.vueListePrix.afficherListePrix(this.prixDAO.listerTousPrix());
		this.navigateur.naviguerVersVueListePrix();
		
	}
	
	public void notifierNaviguerEditerPrix(int idPrix) {
		System.out.println("ControleurPrix.notifierNaviguerEditerPrix("+idPrix+")");
		this.vueEditerPrix.afficherPrix(this.prixDAO.rapporterPrix(idPrix));
		this.navigateur.naviguerVersVueEditerPrix();
		
	}
	
	public void notifierNaviguerAjouterPrix(int idLivre) {
		System.out.println("ControleurLivre.notifierNaviguerAjouterPrix()");
		this.navigateur.naviguerVersVueAjouterPrix();
		this.vueAjouterPrix.setIdLivre(idLivre);
		
	}
	
	public void notifierNaviguerListePrix() {
		this.navigateur.naviguerVersVueListePrix();
	}
	
	public void notifierNaviguerListeLivre() {
		this.navigateur.naviguerVersVueListeLivre();
	}
	
	
	
	

}