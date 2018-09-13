import vue.NavigateurDesVues;
import vue.VueAjouterLivre;
import vue.VueAjouterPrix;
import vue.VueEditerPrix;
import vue.VueListeLivre;
import vue.VueListePrix;
import vue.VueLivre;

public class App {

	public static void main(String[] parametres) {
		//NavigateurDesVues.launch(NavigateurDesVues.class,parametres);
		VueEditerPrix.launch(VueEditerPrix.class, parametres);

	}

}
