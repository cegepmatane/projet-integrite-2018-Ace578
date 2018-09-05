package vue;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import modele.Livre;

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
		valeurTitre = new Label("");		
		grilleLivre.add(idTitre, 0, 0);
		grilleLivre.add(valeurTitre, 1, 0);
		
		idAnnee = new Label("Annee : ");
		valeurAnnee = new Label("");
		grilleLivre.add(valeurAnnee, 1, 1);
		grilleLivre.add(idAnnee, 0, 1);
		
		idStyle = new Label("Style : ");
		valeurStyle = new Label("");
		grilleLivre.add(idStyle, 0, 3);
		grilleLivre.add(valeurStyle, 1, 3);
		
		
		stade.setScene(new Scene(grilleLivre, 300, 250));
		stade.show();
		
		Livre livre = new Livre("Le Seigneur des Anneaux", "1954", "Fantasy");
		this.afficherLivre(livre);
		
	}
	
	public void afficherLivre(Livre livre) {
		this.valeurTitre.setText(livre.getTitre());
		this.valeurAnnee.setText(livre.getAnnee());
		this.valeurStyle.setText(livre.getStyle());
		
	}
}
