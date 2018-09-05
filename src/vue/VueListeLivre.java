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

public class VueListeLivre extends Application {

	
	protected GridPane grilleLivres;
	
	
	@Override
	public void start(Stage stade) throws Exception {
		Pane panneau = new Pane();	
		grilleLivres = new GridPane();
		panneau.getChildren().add(grilleLivres);
		stade.setScene(new Scene(panneau, 400, 400));
		stade.show();	
		
		List listeLivresTest = new ArrayList<Livre>();
		listeLivresTest.add(new Livre("Le Seigneur des Anneaux", "1954", "Fantasy"));
		listeLivresTest.add(new Livre("Je suis une légende", "1954", "Post-Apocalyptique"));
		listeLivresTest.add(new Livre("La ferme des animaux", "1945", "Apologue"));
		listeLivresTest.add(new Livre("Le vieil homme et la mer", "1952", "Fiction"));
		this.afficherListeLivre(listeLivresTest);
		
			
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