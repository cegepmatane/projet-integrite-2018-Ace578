package vue;

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
import modele.Prix;
public class VueEditerPrix extends Application {
	protected TextField valeurNom;
	protected TextField valeurPromotion;
	protected TextField valeurDescription;
	
	@Override
	public void start(Stage stade) throws Exception {
		
		
		VBox panneau = new VBox();	
		GridPane grillePrix = new GridPane();
		Button actionEnregistrerPrix = new Button("Enregistrer");
		
		
		
		valeurNom = new TextField();
		grillePrix.add(new Label("Nom : "), 0, 0);
		grillePrix.add(valeurNom, 1, 0);
		
		valeurPromotion = new TextField("");
		grillePrix.add(new Label("Promotion : "), 0, 1);
		grillePrix.add(valeurPromotion, 1, 1);
		valeurDescription = new TextField("");
		grillePrix.add(new Label("Description : "), 0, 2);
		grillePrix.add(valeurDescription, 1, 2);		
		
						
			
		
		panneau.getChildren().add(new Label("Editer un Prix")); 
		panneau.getChildren().add(grillePrix);
		panneau.getChildren().add(actionEnregistrerPrix);
		stade.setScene(new Scene(panneau, 400, 400));
		stade.show();
	}
	
	public Prix demanderPrix()
	{
		Prix Prix = new Prix(valeurNom.getText(), 
								valeurPromotion.getText()); 
								
		return Prix;
	}

	
	
	
	
}