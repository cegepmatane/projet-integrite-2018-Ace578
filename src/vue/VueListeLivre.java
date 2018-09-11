package vue;

import java.util.ArrayList;
import java.util.List;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import modele.Livre;

public class VueListeLivre extends Scene {

	
	protected GridPane grilleLivres;
	
	public VueListeLivre() {
		
		super(new GridPane(), 400, 400);
		Pane panneau = (Pane) this.getRoot();	
		grilleLivres = new GridPane();
		panneau.getChildren().add(grilleLivres);
			
		
		
	
	}
	
	
	public void afficherListeLivre(List<Livre> listeLivres) {
			
		
		int numero = 0;
		
		this.grilleLivres.add(new Label("Titre"), 0, numero);
		this.grilleLivres.add(new Label("Annee"), 1, numero);
		for (Livre livre : listeLivres) {
			numero++;
			this.grilleLivres.add(new Label(livre.getTitre()), 0, numero);
			this.grilleLivres.add(new Label(livre.getAnnee()), 1, numero);
		}
	

	}
	
}