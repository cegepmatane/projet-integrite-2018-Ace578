package vue;

import controleur.ControleurLivre;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
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
	private ControleurLivre controleur = null;
	protected Button actionEnregistrerLivre = null;

	public VueAjouterLivre() {
		super(new VBox(), 400, 400);
		VBox panneau = (VBox) this.getRoot();	
		GridPane grilleLivre = new GridPane();
		this.actionEnregistrerLivre = new Button("Enregistrer");
		this.actionEnregistrerLivre.setOnAction(new EventHandler<ActionEvent>() {
			
			public void handle(ActionEvent arg0) {
				
				controleur.notifierEnregistrerLivre();
			
		}});

		
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
		panneau.getChildren().add(this.actionEnregistrerLivre);
				
	}
	
	public Livre demanderLivre(){
		
	
		Livre livre = new Livre(this.valeurTitre.getText(), 
								this.valeurAnnee.getText(), 
								this.valeurStyle.getText()
								);
		return livre;
	}
	
	public void setControleur(ControleurLivre controleur) {
		this.controleur = controleur;
	}

}
