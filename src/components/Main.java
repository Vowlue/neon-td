package components;
import java.awt.Paint;
import java.io.FileInputStream;
import java.util.Arrays;

import javafx.animation.TranslateTransition;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.SplitPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javafx.util.Duration;
import objects.Enemy;

public class Main extends Application{
	// CONSTANTS
	private final int GAME_HEIGHT = 700;
	private final int GAME_WIDTH = 1000;
	private final int EMPTY = 0;
	private final int BLACK = 1;
	private final int BLUE = 2;
	private final int YELLOW = 3;
	private final int GREEN = 4;
	private final int HEIGHT_D = 20;
	private final int WIDTH_D = 20;
	
	//GAME VARIABLES
	private static int playerHp = 3;
	
	//GAME ELEMENTS
	private Stage window;
	private Scene game;
	private static BorderPane gameLayout;
	public static void main(String[] args){
		launch(args);
	}

	@Override
	public void start(Stage stage) throws Exception {
		int[][] map = generateMap();
		window = stage;
		window.setTitle("TD GAME");
		
		gameLayout = new BorderPane();
		gameLayout.setStyle("-fx-background-color: #000000");
		
		ImageView topHold = new ImageView(new Image(new FileInputStream("images/placehold2.png")));
		topHold.setFitWidth(GAME_WIDTH);
		topHold.setFitHeight(.05*GAME_HEIGHT);
		Pane topMenu = new Pane(topHold);
		gameLayout.setTop(topMenu);
		
		ImageView actionHold = new ImageView(new Image(new FileInputStream("images/placehold4.png")));
		actionHold.setFitWidth(.1*GAME_WIDTH);
		actionHold.setFitHeight(.95*GAME_HEIGHT);
		Pane actionMenu = new Pane(actionHold);
		gameLayout.setLeft(actionMenu);
		
		//THIS IS THE CODE FOR THE FRONT END OF THE SHOP
		/*ImageView shopHold = new ImageView(new Image(new FileInputStream("images/placehold3.png")));
		shopHold.setFitWidth(.2*GAME_WIDTH);
		shopHold.setFitHeight(.95*GAME_HEIGHT);*/
		VBox shopMenu = new VBox();
		VBox towerMenu = new VBox();
		Label towerTitle = new Label("Towers");
		HBox blueMenu = new HBox(5);
		blueMenu.setPadding(new Insets(10, 5, 10, 5));
		blueMenu.setStyle("-fx-background-color:#3399FF");
		blueMenu.getChildren().addAll(new PlaceHolder(new Image(new FileInputStream("images/placehold3.png")), (.2*GAME_WIDTH)/4, (.95*GAME_HEIGHT)/13), new PlaceHolder(new Image(new FileInputStream("images/placehold3.png")), (.2*GAME_WIDTH)/4, (.95*GAME_HEIGHT)/13), new PlaceHolder(new Image(new FileInputStream("images/placehold3.png")), (.2*GAME_WIDTH)/4, (.95*GAME_HEIGHT)/13), new PlaceHolder(new Image(new FileInputStream("images/placehold3.png")), (.2*GAME_WIDTH)/4, (.95*GAME_HEIGHT)/13));
		towerMenu.getChildren().addAll(towerTitle, blueMenu);
		VBox eventMenu = new VBox();
		Label eventTitle = new Label("Events");
		GridPane eventGrid = new GridPane();
		shopMenu.getChildren().addAll(towerMenu, eventMenu);
		gameLayout.setRight(shopMenu);
		
		//THIS IS THE CODE FOR THE FRONT END OF THE MAP
		GridPane mapLayout = new GridPane();
		mapLayout.setId("map");
		ColumnConstraints c = new ColumnConstraints();
		RowConstraints r = new RowConstraints();
		c.setPercentWidth(100/WIDTH_D);
		r.setPercentHeight(100/HEIGHT_D);
		for(int i = 0; i<HEIGHT_D; i++){
			mapLayout.getColumnConstraints().add(c);
			mapLayout.getRowConstraints().add(r);
		}
		for(int i = 0; i<HEIGHT_D; i++){
			for(int j = 0; j<WIDTH_D; j++){
				ImageView iv;
				switch(map[j][i]){
				case BLACK: iv = new ImageView(new Image(new FileInputStream("images/black tile.png")));break;
				case BLUE: iv = new ImageView(new Image(new FileInputStream("images/blue unshaded tile.png")));break;
				case YELLOW: iv = new ImageView(new Image(new FileInputStream("images/yellow unshaded tile.png")));break;
				case GREEN: iv = new ImageView(new Image(new FileInputStream("images/green unshaded tile.png")));break;
				default: iv = new ImageView(new Image(new FileInputStream("images/grass.png")));break;
				}
				Pane p = new Pane(iv);
				iv.fitWidthProperty().bind(p.widthProperty());
				iv.fitHeightProperty().bind(p.heightProperty());
				mapLayout.add(p, i, j);
			}
		}
		gameLayout.setCenter(mapLayout);
		game = new Scene(gameLayout, GAME_WIDTH, GAME_HEIGHT);
		game.getStylesheets().add("style/TDStyle.css");
		game.setOnKeyPressed(e -> {
			Enemy z = new Enemy(4);
			z.setOnMousePressed(n -> z.setStage((int)(Math.random()*5)));
			if(e.getCode() == KeyCode.A){
				gameLayout.getChildren().add(z);
			}
		});
		
		window.setScene(game);
		window.show();
	}
	private int[][] generateMap(){
		// 1: path, 2: water, 3: air, 4: land
		//first, generate the path, then add some water + air, fill in rest with 
		//land. water and air will be a completely different number each time
		//however they cannot take up so much space such that the land will look
		//sparse
		
		int[][] map = new int[HEIGHT_D][WIDTH_D];
		//generate the path
		int p1 = 10;
		int p2 = 0;
		while(p2 < 5){
			map[p1][p2] = BLACK;
			p2++;
		}
		while(p1 > 4){
			map[p1][p2] = BLACK;
			p1--;
		}
		while(p2 < 10){
			map[p1][p2] = BLACK;
			p2++;
		}
		while(p1 < 16){
			map[p1][p2] = BLACK;
			p1++;
		}
		while(p2 < 15){
			map[p1][p2] = BLACK;
			p2++;
		}
		while(p1 > -1){
			map[p1][p2] = BLACK;
			p1--;
		}
		for(int i = 0; i<HEIGHT_D; i++){
			for(int j = 0; j<WIDTH_D; j++){
				if(map[i][j] == EMPTY)
					map[i][j] = randomInt(2, 4);
			}
		}
		return map;
	}
	private int randomInt(int low, int high){
		return (int)(Math.random()*(high-low+1))+low;
	}
	
	public static int getPlayerHp() {
		return playerHp;
	}

	public static void setPlayerHp(int playerHp) {
		Main.playerHp = playerHp;
	}
	public static void removeEnemy(Enemy en){
		gameLayout.getChildren().remove(en);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
