import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class VueLivre extends Application {


	@Override
	public void start(Stage stade) throws Exception {
		Pane racine = new Pane();
		stade.setScene(new Scene(racine, 300, 250));
		stade.show();
	}
}
