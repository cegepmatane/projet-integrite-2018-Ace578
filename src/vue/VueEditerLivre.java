package vue;

import java.util.ArrayList;
import java.util.List;

import controleur.ControleurLivre;
import controleur.ControleurPrix;
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
import modele.Prix;

public class VueEditerLivre extends Scene{

	protected TextField valeurTitre;
	protected TextField valeurAnnee;
	protected TextField valeurStyle;
	private ControleurLivre controleur = null;
	protected Button actionEnregistrerLivre = null;
	private int idLivre = 0;
	protected GridPane grilleListePrix = new GridPane();
	private int compteur;
	protected Button actionEditerPrix = null;
	private ControleurPrix controleurPrix = null;
	private Button actionAjouterPrix;

	public VueEditerLivre() {
		super(new VBox(), 400, 400);
		VBox panneau = (VBox) this.getRoot();	
		GridPane grilleLivre = new GridPane();
		this.actionEnregistrerLivre = new Button("Enregistrer");
		this.actionEnregistrerLivre.setOnAction(new EventHandler<ActionEvent>() {
			
			public void handle(ActionEvent arg0) {
				
				controleur.notifierEnregistrerLivre();
			
		}});
		this.actionAjouterPrix = new Button("Ajouter un prix");
		this.actionAjouterPrix.setOnAction(new EventHandler<ActionEvent>() {
			
			public void handle(ActionEvent arg0) {
				
				controleurPrix.notifierNaviguerAjouterPrix(idLivre);
				
			
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
		
		
		
		panneau.getChildren().add(new Label("Editer un livre")); 
		panneau.getChildren().add(grilleLivre);
		panneau.getChildren().add(grilleListePrix);
		panneau.getChildren().add(this.actionEnregistrerLivre);
		panneau.getChildren().add(this.actionAjouterPrix);
				
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
	
	public void setControleurPrix(ControleurPrix controleurPrix) {
		this.controleurPrix = controleurPrix;
	}
	
	
	public void afficherListePrix(List<Prix> listePrix) {
		this.grilleListePrix.getChildren().clear();
		compteur = 0;
		 for(Prix prixCompteur : listePrix) {
			 this.actionEditerPrix = new Button("Editer");
				this.actionEditerPrix.setOnAction(new EventHandler<ActionEvent>() {
					
					public void handle(ActionEvent arg0) {
						
						controleurPrix.notifierNaviguerEditerPrix(prixCompteur.getId());
					
				}});;
			 grilleListePrix.add(new Label(""+prixCompteur.getNom()),0,compteur);
			 grilleListePrix.add(new Label(""+prixCompteur.getPromotion()),1,compteur);
			 grilleListePrix.add(actionEditerPrix, 2, compteur);
			 grilleListePrix.add(new Button ("Effacer"), 3, compteur);
			 compteur++;
			 
		 }
		
	}
	
	
	

}
