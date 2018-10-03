package vue;

import java.util.List;

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
public class VueEditerPrix extends Scene {
	protected TextField valeurNom;
	protected TextField valeurPromotion;
	protected TextField valeurDescription;
	private int idPrix = 0;
	protected GridPane grillePrix;
	private ControleurPrix controleur;
	private Button actionEnregistrerPrix;
	private Prix prix;
	
	public VueEditerPrix() {
		
		
		super(new VBox(), 400, 400);
		VBox panneau = (VBox) this.getRoot();
		actionEnregistrerPrix = new Button("Enregistrer");
		actionEnregistrerPrix.setOnAction(new EventHandler<ActionEvent>() {
			
			public void handle(ActionEvent arg0) {
				
				controleur.notifierEnregistrerPrix();
			
		}});
		grillePrix = new GridPane();
		
		
		
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
		
	}
	
	public Prix demanderPrix()
	{
		prix.setNom(valeurNom.getText());
		prix.setPromotion(valeurPromotion.getText());
		prix.setDescription(valeurDescription.getText());
								
		return prix;
	}

	
	public void setControleur(ControleurPrix controleur) {
		this.controleur = controleur;
	}
	
	public void afficherPrix(Prix prix) {
		this.prix = prix;
		this.idPrix = prix.getId();
		this.valeurNom.setText(prix.getNom());
		this.valeurPromotion.setText(prix.getPromotion());
		this.valeurDescription.setText(prix.getDescription());
	}
	
	/**
	public void afficherListePrix(List<Prix> listePrix) {
		this.grillePrix.getChildren().clear();
		int compteur = 0;
		 for(Prix prixCompteur : listePrix) {
			 this.actionEnregistrerPrix = new Button("Enregistrer");
				this.actionEnregistrerPrix.setOnAction(new EventHandler<ActionEvent>() {
					
					public void handle(ActionEvent arg0) {
						
						controleur.notifierNaviguerEditerPrix(prixCompteur.getId());
					
				}});;
			 grillePrix.add(new Label(""+prixCompteur.getNom()),0,compteur);
			 grillePrix.add(new Label(""+prixCompteur.getPromotion()),1,compteur);
			 grillePrix.add(actionEnregistrerPrix, 2, compteur);
			 grillePrix.add(new Button ("Effacer"), 3, compteur);
			 compteur++;
			 
		 }
		 
		
	}
	
		 */
	
}