package vue;

import controleur.ControleurLivre;
import controleur.ControleurPrix;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import modele.Livre;
import modele.Prix;
public class VueAjouterPrix extends Scene{
	protected TextField valeurNom;
	protected TextField valeurPromotion;
	protected TextField valeurDescription;
	private ControleurPrix controleur;
	protected Button actionAjouterPrix;
	private int idLivre = 0;
	
	
	public VueAjouterPrix() {
		super(new VBox(), 400, 400);
		VBox panneau = (VBox) this.getRoot();	
		GridPane grillePrix = new GridPane();
		actionAjouterPrix = new Button("Enregistrer");
		actionAjouterPrix.setOnAction(new EventHandler<ActionEvent>() {
			
			public void handle(ActionEvent arg0) {
				
				controleur.notifierAjouterPrix(idLivre);
			
		}});
		
		valeurNom = new TextField();
		grillePrix.add(new Label("Nom : "), 0, 0);
		grillePrix.add(valeurNom, 1, 0);
		
		valeurPromotion = new TextField("");
		grillePrix.add(new Label("Promotion : "), 0, 1);
		grillePrix.add(valeurPromotion, 1, 1);
		valeurDescription = new TextField("");
		grillePrix.add(new Label("Description : "), 0, 2);
		grillePrix.add(valeurDescription, 1, 2);		
						
			
		
		panneau.getChildren().add(new Label("Ajouter un Prix")); 
		panneau.getChildren().add(grillePrix);
		panneau.getChildren().add(actionAjouterPrix);
				
	}
	
	public Prix demanderPrix()
	{
		Prix Prix = new Prix(valeurNom.getText(), 
								valeurPromotion.getText(),
									valeurDescription.getText()); 
				
		return Prix;
	}
	
	public void setIdLivre(int idLivre) {
		this.idLivre = idLivre;
	}
	
	
	
	public void setControleur(ControleurPrix controleur) {
		this.controleur = controleur;
	}
}