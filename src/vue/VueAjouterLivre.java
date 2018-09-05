package vue;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class VueAjouterLivre extends Application {

	@Override
	public void start(Stage stade) throws Exception {
		Pane panneau = new Pane();	
		GridPane grilleLivre = new GridPane();
		
		panneau.getChildren().add(grilleLivre);
		stade.setScene(new Scene(panneau, 400, 400));
		stade.show();		
	}

}