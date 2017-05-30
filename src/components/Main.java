package components;
import java.io.FileInputStream;
import java.util.ArrayList;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
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
import javafx.stage.Stage;
import objects.BlueTower;
import objects.Enemy;
import objects.PlaceHolder;
import objects.Tile;
import objects.Tower;

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
	
	//MANAGEMENT VARIABLES
	private static Tower storedTower;
	
	//GAME ELEMENTS
	private Stage window;
	private Scene game;
	public static BorderPane gameLayout;
	public static GridPane mapLayout;
	
	//GAME IMAGES
	public static Image blackTile;
	public static Image uBlueTile;
	public static Image blueTile;
	public static Image uGreenTile;
	public static Image greenTile;
	public static Image uYellowTile;
	public static Image yellowTile;
	
	public static void main(String[] args){
		launch(args);
	}

	@Override
	public void start(Stage stage) throws Exception {
		blackTile = new Image(new FileInputStream("images/black tile.png"));
		uBlueTile = new Image(new FileInputStream("images/blue unshaded tile.png"));
		blueTile = new Image(new FileInputStream("images/blue shaded tile.png"));
		uGreenTile = new Image(new FileInputStream("images/green unshaded tile.png"));
		greenTile = new Image(new FileInputStream("images/green shaded tile.png"));
		uYellowTile = new Image(new FileInputStream("images/yellow unshaded tile.png"));
		yellowTile = new Image(new FileInputStream("images/yellow shaded tile.png"));
		int[][] map = generateMap();
		window = stage;
		window.setTitle("TD GAME");
		
		gameLayout = new BorderPane();
		
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
		towerTitle.setId("towerTitle");
		HBox blueMenu = new HBox(5);
		blueMenu.setPadding(new Insets(10, 5, 10, 5));
		blueMenu.setStyle("-fx-background-color:#3399FF");
		HBox yellowMenu = new HBox(5);
		yellowMenu.setPadding(new Insets(10, 5, 10, 5));
		yellowMenu.setStyle("-fx-background-color:yellow");
		HBox greenMenu = new HBox(5);
		greenMenu.setPadding(new Insets(10, 5, 10, 5));
		greenMenu.setStyle("-fx-background-color:green");
		Tower t = new BlueTower(new Image(new FileInputStream("images/star.png")), (.2*GAME_WIDTH)/4, (.95*GAME_HEIGHT)/13);
		blueMenu.getChildren().addAll(t, new PlaceHolder(new Image(new FileInputStream("images/placehold3.png")), (.2*GAME_WIDTH)/4, (.95*GAME_HEIGHT)/13), new PlaceHolder(new Image(new FileInputStream("images/placehold3.png")), (.2*GAME_WIDTH)/4, (.95*GAME_HEIGHT)/13), new PlaceHolder(new Image(new FileInputStream("images/placehold3.png")), (.2*GAME_WIDTH)/4, (.95*GAME_HEIGHT)/13));
		yellowMenu.getChildren().addAll(new PlaceHolder(new Image(new FileInputStream("images/placehold3.png")), (.2*GAME_WIDTH)/4, (.95*GAME_HEIGHT)/13), new PlaceHolder(new Image(new FileInputStream("images/placehold3.png")), (.2*GAME_WIDTH)/4, (.95*GAME_HEIGHT)/13), new PlaceHolder(new Image(new FileInputStream("images/placehold3.png")), (.2*GAME_WIDTH)/4, (.95*GAME_HEIGHT)/13), new PlaceHolder(new Image(new FileInputStream("images/placehold3.png")), (.2*GAME_WIDTH)/4, (.95*GAME_HEIGHT)/13));
		greenMenu.getChildren().addAll(new PlaceHolder(new Image(new FileInputStream("images/placehold3.png")), (.2*GAME_WIDTH)/4, (.95*GAME_HEIGHT)/13), new PlaceHolder(new Image(new FileInputStream("images/placehold3.png")), (.2*GAME_WIDTH)/4, (.95*GAME_HEIGHT)/13), new PlaceHolder(new Image(new FileInputStream("images/placehold3.png")), (.2*GAME_WIDTH)/4, (.95*GAME_HEIGHT)/13), new PlaceHolder(new Image(new FileInputStream("images/placehold3.png")), (.2*GAME_WIDTH)/4, (.95*GAME_HEIGHT)/13));
		towerMenu.getChildren().addAll(new Label("Towers"), new Label("Blues"), blueMenu, new Label("Yellows"), yellowMenu, new Label("Greens"), greenMenu);
		VBox eventContainer = new VBox();
		Label eventTitle = new Label("Events");
		HBox eventMenu = new HBox(5);
		eventMenu.setPadding(new Insets(10, 5, 10, 5));
		eventMenu.setStyle("-fx-background-color:pink");
		eventMenu.getChildren().addAll(new PlaceHolder(new Image(new FileInputStream("images/placehold3.png")), (.2*GAME_WIDTH)/4, (.95*GAME_HEIGHT)/13), new PlaceHolder(new Image(new FileInputStream("images/placehold3.png")), (.2*GAME_WIDTH)/4, (.95*GAME_HEIGHT)/13), new PlaceHolder(new Image(new FileInputStream("images/placehold3.png")), (.2*GAME_WIDTH)/4, (.95*GAME_HEIGHT)/13), new PlaceHolder(new Image(new FileInputStream("images/placehold3.png")), (.2*GAME_WIDTH)/4, (.95*GAME_HEIGHT)/13));
		eventContainer.getChildren().addAll(eventTitle, eventMenu);
		shopMenu.getChildren().addAll(towerMenu, eventContainer);
		gameLayout.setRight(shopMenu);
		//THIS IS THE CODE FOR THE FRONT END OF THE MAP
		mapLayout = new GridPane();
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
				case BLACK: iv = new ImageView(blackTile);break;
				case BLUE: iv = new ImageView(uBlueTile);break;
				case YELLOW: iv = new ImageView(uYellowTile);break;
				case GREEN: iv = new ImageView(uGreenTile);break;
				default: iv = new ImageView(new Image(new FileInputStream("images/grass.png")));break;
				}
				Tile p = new Tile(iv);
				switch(map[j][i]){
				case BLACK: p.getStyleClass().add("path");break;
				case BLUE: p.getStyleClass().add("blue tile");break;
				case YELLOW: p.getStyleClass().add("yellow tile");break;
				case GREEN: p.getStyleClass().add("green tile");break;
				default: p.getStyleClass().add("broke");break;
				}
				iv.fitWidthProperty().bind(p.widthProperty());
				iv.fitHeightProperty().bind(p.heightProperty());
				mapLayout.add(p, i, j);
			}
		}
		gameLayout.setCenter(mapLayout);
		game = new Scene(gameLayout, GAME_WIDTH, GAME_HEIGHT);
		game.getStylesheets().add("style/TDStyle.css");
		game.setOnKeyPressed(e -> {
			Enemy z = new Enemy((int)(Math.random()*5));
			z.setOnMousePressed(n -> z.setStage((int)(Math.random()*5)));
			if(e.getCode() == KeyCode.A){
				gameLayout.getChildren().add(z);
			}
		});
		
		window.setScene(game);
		window.show();//NOTHING HAS HEIGHT UNTIL THE WINDOW IS SHOWNN FOR SOME REASON
		
	}
	
	public static void addNode(Node n){
		gameLayout.getChildren().add(n);
	}
	public static void storeTower(Tower t){
		storedTower = t;
	}
	public static Tower getTower(){
		return storedTower;
	}
	private int[][] generateMap(){
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
	public static ArrayList<Node> getAllNodes(Parent root) {
	    ArrayList<Node> nodes = new ArrayList<Node>();
	    addAllDescendents(root, nodes);
	    return nodes;
	}
	private static void addAllDescendents(Parent parent, ArrayList<Node> nodes) {
	    for (Node node : parent.getChildrenUnmodifiable()) {
	        nodes.add(node);
	        if (node instanceof Parent)
	            addAllDescendents((Parent)node, nodes);
	    }
	}
	//need a get all tiles and maybe make it a overloaded method one of which with a strong for style??/
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
