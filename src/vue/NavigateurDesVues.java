package vue;
import java.util.ArrayList;
import java.util.List;

import controleur.ControleurLivre;
import javafx.application.Application;
import javafx.stage.Stage;
import modele.Livre;

public class NavigateurDesVues extends Application{
	
	private VueAjouterLivre vueAjouterLivre;
	private VueListeLivre vueListeLivre;
	private VueLivre vueLivre;
	private VueEditerLivre vueEditerLivre;
	private Stage stade;
	
	
	

	public NavigateurDesVues() 
	{
		this.vueListeLivre = new VueListeLivre();
		this.vueLivre = new VueLivre();	
		this.vueAjouterLivre = new VueAjouterLivre();
		this.vueEditerLivre = new VueEditerLivre();
		
		
		
	}
	
	@Override
	public void start(Stage stade) throws Exception {
		
		this.stade = stade;
		stade.setScene(null);
		stade.show();
		
		ControleurLivre controleur = ControleurLivre.getInstance();
		controleur.activerVues(this);
		this.vueLivre.setControleur(controleur);
		this.vueListeLivre.setControleur(controleur);		
		this.vueAjouterLivre.setControleur(controleur);
		this.vueEditerLivre.setControleur(controleur);
		
	}
	
	public VueAjouterLivre getVueAjouterLivre() {
		return vueAjouterLivre;
	}

	public VueListeLivre getVueListeLivre() {
		return vueListeLivre;
	}

	public VueLivre getVueLivre() {
		return vueLivre;
	}
	
	public void naviguerVersVueLivre() {
		stade.setScene(this.vueLivre);
		stade.show();
	}
	
	public void naviguerVersVueAjouterLivre() {
		stade.setScene(this.vueAjouterLivre);
		stade.show();
	}
	
	public void naviguerVersVueListeLivre() {
		stade.setScene(this.vueListeLivre);
		stade.show();
	}
	
	public void naviguerVersVueEditerLivre() {
		stade.setScene(this.vueEditerLivre);
		stade.show();
	}
	
	
	
}
