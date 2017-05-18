package design;
import java.io.FileInputStream;
import java.util.Arrays;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.SplitPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Main extends Application{
	Stage window;
	private final int GAME_HEIGHT = 700;
	private final int GAME_WIDTH = 1000;
	private final int PATH = 1;
	private final int WATER = 2;
	private final int AIR = 3;
	private final int LAND = 4;
	private final int EMPTY = 0;
	private final int HEIGHT_D = 20;
	private final int WIDTH_D = 20;
	public static void main(String[] args){
		launch(args);
	}

	@Override
	public void start(Stage stage) throws Exception {
		int[][] map = generateMap();
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
		
		GridPane gridPane = new GridPane();
		ColumnConstraints c = new ColumnConstraints();
		RowConstraints r = new RowConstraints();
		c.setPercentWidth(100/WIDTH_D);
		r.setPercentHeight(100/HEIGHT_D);
		for(int i = 0; i<HEIGHT_D; i++){
			gridPane.getColumnConstraints().add(c);
			gridPane.getRowConstraints().add(r);
		}
		for(int i = 0; i<HEIGHT_D; i++){
			for(int j = 0; j<WIDTH_D; j++){
				ImageView iv;
				switch(map[j][i]){
				case PATH: iv = new ImageView(new Image(new FileInputStream("images/path.png")));break;
				case WATER: iv = new ImageView(new Image(new FileInputStream("images/two.png")));break;
				case AIR: iv = new ImageView(new Image(new FileInputStream("images/three.png")));break;
				case LAND: iv = new ImageView(new Image(new FileInputStream("images/four.png")));break;
				default: iv = new ImageView(new Image(new FileInputStream("images/grass.png")));break;
				}
				Pane p = new Pane(iv);
				iv.fitWidthProperty().bind(p.widthProperty());
				iv.fitHeightProperty().bind(p.heightProperty());
				gridPane.add(p, i, j);
			}
		}
		gameLayout.setCenter(gridPane);
		Scene game = new Scene(gameLayout, GAME_WIDTH, GAME_HEIGHT);
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
			map[p1][p2] = 1;
			p2++;
		}
		while(p1 > 4){
			map[p1][p2] = 1;
			p1--;
		}
		while(p2 < 10){
			map[p1][p2] = 1;
			p2++;
		}
		while(p1 < 16){
			map[p1][p2] = 1;
			p1++;
		}
		while(p2 < 15){
			map[p1][p2] = 1;
			p2++;
		}
		while(p1 > -1){
			map[p1][p2] = 1;
			p1--;
		}
		for(int[] a: map){
			System.out.println(Arrays.toString(a));
		}
		return map;
	}
	private int randomInt(int low, int high){
		return (int)(Math.random()*(high-low+1))+low;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
