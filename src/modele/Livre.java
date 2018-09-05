package modele;

public class Livre {
	
	protected String titre;
	protected String annee;
	protected String style;
	
	
	public Livre(String titre) {
		super();
		this.titre = titre;
	}


	public Livre(String titre, String annee) {
		super();
		this.titre = titre;
		this.annee = annee;
	}


	public Livre(String titre, String annee, String style) {
		super();
		this.titre = titre;
		this.annee = annee;
		this.style = style;
	}


	public String getTitre() {
		return titre;
	}


	public void setTitre(String titre) {
		this.titre = titre;
	}


	public String getAnnee() {
		return annee;
	}


	public void setAnnee(String annee) {
		this.annee = annee;
	}


	public String getStyle() {
		return style;
	}


	public void setStyle(String style) {
		this.style = style;
	}
	
	
	
	
	
	
	

}
