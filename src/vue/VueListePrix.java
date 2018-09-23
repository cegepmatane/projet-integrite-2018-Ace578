package vue;

import java.util.ArrayList;
import java.util.List;

import controleur.ControleurPrix;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import modele.Prix;

public class VueListePrix extends Scene {
	protected GridPane grillePrix;
	private ControleurPrix controleur;
	private Button actionNaviguerVersAjouter;
	
	
	public VueListePrix() {
		super(new VBox(), 400, 400);
		VBox panneau = (VBox) this.getRoot();
		grillePrix = new GridPane();
		actionNaviguerVersAjouter = new Button ("Ajouter un prix");
		actionNaviguerVersAjouter.setOnAction(new EventHandler<ActionEvent>() {
			
			public void handle(ActionEvent arg0) {
				
				controleur.notifierNaviguerAjouterPrix();
			
		}});
		
		
		panneau.getChildren().add(grillePrix);
		panneau.getChildren().add(actionNaviguerVersAjouter);
				
		
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
	
	public void setControleur(ControleurPrix controleur) {
		this.controleur = controleur;
	}
}