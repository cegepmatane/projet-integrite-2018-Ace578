package vue;

import java.util.ArrayList;
import java.util.List;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import modele.Prix;

public class VueListePrix extends Application {
	protected GridPane grillePrix;
	
	@Override
	public void start(Stage stade) throws Exception {
		Pane panneau = new Pane();	
		
		grillePrix = new GridPane();
		
		
		panneau.getChildren().add(grillePrix);
		stade.setScene(new Scene(panneau, 400, 400));
		stade.show();		
		
		/// TEST ///
		List listePrixTest = new ArrayList<Prix>();
		listePrixTest.add(new Prix("Livre le plus comique","1960"));
		listePrixTest.add(new Prix("Livre le plus beau","1970"));
		listePrixTest.add(new Prix("Livre le plus angoissant","1980"));
		listePrixTest.add(new Prix("Livre le plus effrayant","1990"));
		this.afficherListePrix(listePrixTest); 
		
		
	}
	
	public void afficherListePrix(List<Prix> listePrix)
	{
		
		int numero = 0;
		this.grillePrix.add(new Label("Nom"), 0, numero);
		this.grillePrix.add(new Label("Promotion"), 1, numero);			
		for(Prix Prix : listePrix)
		{
			numero++;
			this.grillePrix.add(new Label(Prix.getNom()), 0, numero);
			this.grillePrix.add(new Label(Prix.getPromotion()), 1, numero);			
		}
	}
}