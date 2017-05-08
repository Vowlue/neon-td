package design;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
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
		
		//VBOX FOR "BUTTONS" FOR START MENU AND OPTIONS
		//WILL USE CSS FOR THE BACKGROUND MAYBE TITLE???
		BorderPane startLayout = new BorderPane();
		VBox startText = new VBox();
		startText.setPadding(new Insets(10));
		startText.setSpacing(8);
		Label gameTitle = new Label("TD GAME");
		startText.getChildren().add(gameTitle);
		startLayout.setCenter(startText);
		Scene startMenu = new Scene(startLayout, 1000, 700);
		
		window.setScene(startMenu);
		window.show();
	}
}
