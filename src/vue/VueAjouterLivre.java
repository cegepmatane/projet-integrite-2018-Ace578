package vue;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import modele.Livre;

public class VueAjouterLivre extends Scene{

	protected TextField valeurTitre;
	protected TextField valeurAnnee;
	protected TextField valeurStyle;

	public VueAjouterLivre() {
		super(new VBox(), 400, 400);
		VBox panneau = (VBox) this.getRoot();	
		GridPane grilleLivre = new GridPane();

		
		valeurTitre = new TextField();
		grilleLivre.add(new Label("Titre : "), 0, 0);
		grilleLivre.add(valeurTitre, 1, 0);
		
		valeurAnnee = new TextField("");
		grilleLivre.add(new Label("Annee : "), 0, 1);
		grilleLivre.add(valeurAnnee, 1, 1);

		valeurStyle = new TextField("");
		grilleLivre.add(new Label("Style : "), 0, 2);
		grilleLivre.add(valeurStyle, 1, 2);	
		
		panneau.getChildren().add(new Label("Ajouter un livre")); 
		panneau.getChildren().add(grilleLivre);
		panneau.getChildren().add(new Button("Enregistrer le livre"));
				
	}
	
	public Livre demanderLivre(){
		
	
		Livre livre = new Livre(this.valeurTitre.getText(), 
								this.valeurAnnee.getText(), 
								this.valeurStyle.getText()
								);
		return livre;
	}

}
