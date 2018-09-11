package accesseur;

import java.util.ArrayList;
import java.util.List;

import modele.Livre;

public class LivreDAO {

	private List<Livre> simulerListerLivres() {
		List listeLivresTest = new ArrayList<Livre>();
		listeLivresTest.add(new Livre("Le Seigneur des Anneaux", "1954", "Fantasy"));
		listeLivresTest.add(new Livre("Je suis une légende", "1954", "Post-Apocalyptique"));
		listeLivresTest.add(new Livre("La ferme des animaux", "1945", "Apologue"));
		listeLivresTest.add(new Livre("Le vieil homme et la mer", "1952", "Fiction"));
		return listeLivresTest;
	}
	
	public List<Livre> listerLivres() {
		return this.simulerListerLivres();
	}
}
