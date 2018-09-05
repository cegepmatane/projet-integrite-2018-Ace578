import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class VueLivre extends Application {
	
	
	protected Label valeurTitre;
	protected Label idTitre;
	protected Label  valeurAnnee;
	protected Label idAnnee;
	protected Label valeurStyle;
	protected Label idStyle;
	



	@Override
	public void start(Stage stade) throws Exception {
		
		
		GridPane grilleLivre = new GridPane();
		
		idTitre = new Label("Titre : ");
		valeurTitre = new Label("Le Seigneur des Anneaux");		
		grilleLivre.add(idTitre, 0, 0);
		grilleLivre.add(valeurTitre, 1, 0);
		
		idAnnee = new Label("Annee : ");
		valeurAnnee = new Label("1954");
		grilleLivre.add(valeurAnnee, 1, 1);
		grilleLivre.add(idAnnee, 0, 1);
		
		idStyle = new Label("Style : ");
		valeurStyle = new Label("Fantasy");
		grilleLivre.add(idStyle, 0, 3);
		grilleLivre.add(valeurStyle, 1, 3);
		
		
		stade.setScene(new Scene(grilleLivre, 300, 250));
		stade.show();
		
	}
}
