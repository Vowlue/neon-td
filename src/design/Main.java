package design;
import java.io.FileInputStream;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.SplitPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Main extends Application{
	Stage window;
	private final int GAME_HEIGHT = 700;
	private final int GAME_WIDTH = 1000;
	public static void main(String[] args){
		launch(args);
	}

	@Override
	public void start(Stage stage) throws Exception {
		window = stage;
		window.setTitle("TD GAME");
		
		BorderPane gameLayout = new BorderPane();
		
		ImageView topHold = new ImageView(new Image(new FileInputStream("images/placehold2.png")));
		topHold.setFitWidth(GAME_WIDTH);
		topHold.setFitHeight(.05*GAME_HEIGHT);
		Pane topMenu = new Pane(topHold);
		gameLayout.setTop(topMenu);
		
		ImageView shopHold = new ImageView(new Image(new FileInputStream("images/placehold3.png")));
		shopHold.setFitWidth(.2*GAME_WIDTH);
		shopHold.setFitHeight(.95*GAME_HEIGHT);
		Pane shopMenu = new Pane(shopHold);
		gameLayout.setRight(shopMenu);
		
		ImageView actionHold = new ImageView(new Image(new FileInputStream("images/placehold4.png")));
		actionHold.setFitWidth(.1*GAME_WIDTH);
		actionHold.setFitHeight(.95*GAME_HEIGHT);
		Pane actionMenu = new Pane(actionHold);
		gameLayout.setLeft(actionMenu);
		
		ImageView map = new ImageView(new Image(new FileInputStream("images/placehold1.png")));
		map.setFitWidth(.7*GAME_WIDTH);
		map.setFitHeight(.95*GAME_HEIGHT);
		Pane gameField = new Pane(map);
		gameLayout.setCenter(gameField);
		
		Scene game = new Scene(gameLayout, GAME_WIDTH, GAME_HEIGHT);
		window.setScene(game);
		window.show();
	}
}
