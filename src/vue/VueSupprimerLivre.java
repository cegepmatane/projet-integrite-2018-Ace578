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

public class VueSupprimerLivre extends Scene{

	protected TextField valeurTitre;
	protected TextField valeurAnnee;
	protected TextField valeurStyle;
	private int idLivre = 0;
	private ControleurLivre controleur = null;
	protected Button actionSupprimerLivre = null;

	public VueSupprimerLivre() {
		super(new VBox(), 400, 400);
		VBox panneau = (VBox) this.getRoot();	
		GridPane grilleLivre = new GridPane();
		this.actionSupprimerLivre = new Button("Supprimer");
		this.actionSupprimerLivre.setOnAction(new EventHandler<ActionEvent>() {
			
			public void handle(ActionEvent arg0) {
				

				controleur.notifierSupprimerLivre();
			
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
		
		panneau.getChildren().add(new Label("Supprimer un livre")); 
		panneau.getChildren().add(grilleLivre);
		panneau.getChildren().add(this.actionSupprimerLivre);
				
	}
	
	public Livre demanderLivre(){
		
		
		Livre livre = new Livre(this.valeurTitre.getText(), 
								this.valeurAnnee.getText(), 
								this.valeurStyle.getText()
								);
		livre.setId(idLivre);
		return livre;
	}
	
	public void afficherLivre(Livre livre) {
		this.idLivre = livre.getId();
		this.valeurTitre.setText(livre.getTitre());
		this.valeurAnnee.setText(livre.getAnnee());
		this.valeurStyle.setText(livre.getStyle());
	}
	
	public void setControleur(ControleurLivre controleur) {
		this.controleur = controleur;
	}
	
	

}
