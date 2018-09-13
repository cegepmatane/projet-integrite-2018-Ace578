package vue;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import modele.Prix;
public class VueAjouterPrix extends Application{
	protected TextField valeurNom;
	protected TextField valeurPromotion;
	protected TextField valeurDescription;
	protected TextField valeurNaissance;
	
	@Override
	public void start(Stage stade) throws Exception {
		VBox panneau = new VBox();	
		GridPane grillePrix = new GridPane();
		
		valeurNom = new TextField();
		grillePrix.add(new Label("Nom : "), 0, 0);
		grillePrix.add(valeurNom, 1, 0);
		
		valeurPromotion = new TextField("");
		grillePrix.add(new Label("Promotion : "), 0, 1);
		grillePrix.add(valeurPromotion, 1, 1);
		valeurDescription = new TextField("");
		grillePrix.add(new Label("Description : "), 0, 2);
		grillePrix.add(valeurDescription, 1, 2);		
		valeurNaissance = new TextField("");
		;				
			
		
		panneau.getChildren().add(new Label("Ajouter un Prix")); 
		panneau.getChildren().add(grillePrix);
		panneau.getChildren().add(new Button("Enregistrer"));
		stade.setScene(new Scene(panneau, 400, 400));
		stade.show();		
	}
	
	public Prix demanderPrix()
	{
		return null;
	}
}