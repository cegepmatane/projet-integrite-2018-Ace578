package controleur;
import java.util.List;

import accesseur.PrixDAO;
import accesseur.PrixDAO;
import modele.Prix;
import modele.Prix;
import vue.NavigateurDesVues;
import vue.VueAjouterPrix;
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
	
	public void activerVues(NavigateurDesVues navigateur) {
		
		
		this.navigateur = navigateur;
		this.vuePrix = this.navigateur.getVuePrix();
		this.vueListePrix = this.navigateur.getVueListePrix();
		this.vueAjouterPrix = this.navigateur.getVueAjouterPrix();
		this.vueEditerPrix = navigateur.getVueEditerPrix();

	}
	
	

}