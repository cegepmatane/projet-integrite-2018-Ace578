package vue;

import java.util.ArrayList;
import java.util.List;

import controleur.ControleurLivre;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import modele.Livre;

public class VueListeLivre extends Scene {

	
	protected GridPane grilleLivres;
	private ControleurLivre controleur = null;
	private Button actionNaviguerAjouterLivre;
	
	public VueListeLivre() {
		
		super(new GridPane(), 400, 400);
		Pane panneau = (Pane) this.getRoot();	
		grilleLivres = new GridPane();
		panneau.getChildren().add(grilleLivres);
		this.actionNaviguerAjouterLivre = new Button("Ajouter un livre");
			
		
		
	
	}
	
	
	public void afficherListeLivre(List<Livre> listeLivres) {
			
		this.grilleLivres.getChildren().clear();
		int numero = 0;
		
		this.grilleLivres.add(new Label("Titre"), 0, numero);
		this.grilleLivres.add(new Label("Annee"), 1, numero);
		for (Livre livre : listeLivres) {
			
			Button actionEditerLivre = new Button("Editer");
			actionEditerLivre.setOnAction(new EventHandler<ActionEvent>() {

				@Override
				public void handle(ActionEvent arg0) {
					controleur.notifierNaviguerEditerLivre(livre.getId());
					
				}
				
			});
			numero++;
			this.grilleLivres.add(new Label(livre.getTitre()), 0, numero);
			this.grilleLivres.add(new Label(livre.getAnnee()), 1, numero);
			this.grilleLivres.add(actionEditerLivre,2,numero);
		}
		
		this.actionNaviguerAjouterLivre.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				controleur.notifierNaviguerAjouterLivre();
				
			}
			
		});
		
		this.grilleLivres.add(this.actionNaviguerAjouterLivre, 1, ++numero);
	

	}
	
	public void setControleur(ControleurLivre controleur) {
		this.controleur = controleur;
	}
	
}