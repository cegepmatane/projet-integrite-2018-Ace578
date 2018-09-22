package vue;
import java.util.ArrayList;
import java.util.List;


import controleur.ControleurLivre;
import controleur.ControleurPrix;
import javafx.application.Application;
import javafx.stage.Stage;
import modele.Livre;

public class NavigateurDesVues extends Application{
	
	private VueAjouterLivre vueAjouterLivre;
	private VueListeLivre vueListeLivre;
	private VueLivre vueLivre;
	private VueEditerLivre vueEditerLivre;
	private Stage stade;
	private VueAjouterPrix vueAjouterPrix;
	private VueListePrix vueListePrix;
	private VueEditerPrix vueEditerPrix;
	private VuePrix vuePrix;
	
	
	

	public NavigateurDesVues() 
	{
		this.vueListeLivre = new VueListeLivre();
		this.vueLivre = new VueLivre();	
		this.vueAjouterLivre = new VueAjouterLivre();
		this.vueEditerLivre = new VueEditerLivre();
		this.vueAjouterPrix = new VueAjouterPrix();
		this.vueListePrix = new VueListePrix();
		this.vueEditerPrix = new VueEditerPrix();
		this.vuePrix = new VuePrix();
		
		
	}
	
	@Override
	public void start(Stage stade) throws Exception {
		
		this.stade = stade;
		stade.setScene(null);
		stade.show();
		
		ControleurLivre controleurLivre = ControleurLivre.getInstance();
		controleurLivre.activerVues(this);
		this.vueLivre.setControleur(controleurLivre);
		this.vueListeLivre.setControleur(controleurLivre);		
		this.vueAjouterLivre.setControleur(controleurLivre);
		this.vueEditerLivre.setControleur(controleurLivre);
		
		ControleurPrix controleurPrix = ControleurPrix.getInstance();
		controleurPrix.activerVues(this);
		this.vuePrix.setControleur(controleurPrix);
		this.vueListePrix.setControleur(controleurPrix);		
		this.vueAjouterPrix.setControleur(controleurPrix);
		this.vueEditerPrix.setControleur(controleurPrix);
		
		
		
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
	
	public VueEditerLivre getVueEditerLivre() {
		return vueEditerLivre;
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
	
	public VueAjouterPrix getVueAjouterPrix() {
		return vueAjouterPrix;
	}

	public VueListePrix getVueListePrix() {
		return vueListePrix;
	}

	public VuePrix getVuePrix() {
		return vuePrix;
	}
	
	public VueEditerPrix getVueEditerPrix() {
		return vueEditerPrix;
	}
	
	public void naviguerVersVuePrix() {
		stade.setScene(this.vuePrix);
		stade.show();
	}
	
	public void naviguerVersVueAjouterPrix() {
		stade.setScene(this.vueAjouterPrix);
		stade.show();
	}
	
	public void naviguerVersVueListePrix() {
		stade.setScene(this.vueListePrix);
		stade.show();
	}
	
	public void naviguerVersVueEditerPrix() {
		stade.setScene(this.vueEditerPrix);
		stade.show();
	}
	
	
	
}
