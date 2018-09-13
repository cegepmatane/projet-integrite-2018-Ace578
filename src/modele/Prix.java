package modele;

public class Prix {
	
	protected int id;
	protected String nom;
	protected String promotion;
	protected String description;
	
	
	
	public Prix(String nom, String promotion) {
		super();
		this.nom = nom;
		this.promotion = promotion;
	}
	
	public Prix() {
		
	}
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public String getPromotion() {
		return promotion;
	}
	public void setPromotion(String promotion) {
		this.promotion = promotion;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	
	

}
