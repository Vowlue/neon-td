import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

//banana :3

public class Main extends Application{
	
	public static void main(String[] args){
		launch(args);
	}

	@Override
	public void start(Stage stage) throws Exception {
		Button b1 = new Button("IMA SHOOT OUT SOME KIDS");
		b1.setOnAction(e -> AlertBox.display("FKING BABIES", "KILL YO SELF"));
		StackPane l1 = new StackPane();
		l1.getChildren().add(b1);
		Scene s1 = new Scene(l1, 300, 300);
		stage.setScene(s1);
		stage.setTitle("onose");
		stage.show();
	}
}
