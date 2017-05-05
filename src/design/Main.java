package design;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Main extends Application{
	Stage window;
	public static void main(String[] args){
		launch(args);
	}

	@Override
	public void start(Stage stage) throws Exception {
		window = stage;
		window.setTitle("TD GAME");
		
		VBox vcont = new VBox();
		Scene startMenu = new Scene(vcont, 1000, 700);
		
		window.setScene(startMenu);
		window.show();
	}
}
