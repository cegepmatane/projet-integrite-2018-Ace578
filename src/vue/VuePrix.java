package vue;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import modele.Prix;

public class VuePrix extends Scene  {
	
	
	protected Label valeurNom;
	protected Label idNom;
	protected Label  valeurPromotion;
	protected Label idPromotion;
	protected Label valeurDescription;
	protected Label idDescription;
	
	
	public VuePrix() {


	
		
		super(new Pane(),400,400);
		Pane panneau = (Pane) this.getRoot();
		GridPane grillePrix = new GridPane();
		
		
		idNom = new Label("Nom : ");
		valeurNom = new Label("");		
		grillePrix.add(idNom, 0, 0);
		grillePrix.add(valeurNom, 1, 0);
		
		idPromotion = new Label("Promotion : ");
		valeurPromotion = new Label("");
		grillePrix.add(valeurPromotion, 1, 1);
		grillePrix.add(idPromotion, 0, 1);
		
		idDescription = new Label("Description : ");
		valeurDescription = new Label("");
		grillePrix.add(idDescription, 0, 3);
		grillePrix.add(valeurDescription, 1, 3);
		
		
		panneau.getChildren().add(grillePrix);	
	}
	public void afficherPrix(Prix Prix) {
		this.valeurNom.setText(Prix.getNom());
		this.valeurPromotion.setText(Prix.getPromotion());
		this.valeurDescription.setText(Prix.getDescription());
		
	}
	
	
}
