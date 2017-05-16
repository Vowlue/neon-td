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
	private final int TILE_TYPES = 4; // path, water, air, land
	public static void main(String[] args){
		launch(args);
	}

	@Override
	public void start(Stage stage) throws Exception {
		int[][] map = generateMap(10, 10);
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
		c.setPercentWidth(10);
		r.setPercentHeight(10);
		for(int i = 0; i<10; i++){
			gridPane.getColumnConstraints().add(c);
		}
		for(int i = 0; i<10; i++){
			gridPane.getRowConstraints().add(r);
		}
		for(int i = 0; i<10; i++){
			for(int j = 0; j<10; j++){
				ImageView iv;
				switch(map[j][i]){
				case 1: iv = new ImageView(new Image(new FileInputStream("images/one.png")));break;
				case 2: iv = new ImageView(new Image(new FileInputStream("images/two.png")));break;
				case 3: iv = new ImageView(new Image(new FileInputStream("images/three.png")));break;
				case 4: iv = new ImageView(new Image(new FileInputStream("images/four.png")));break;
				default: iv = null;break;
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
	private int[][] generateMap(int height, int width){
		// 1: path, 2: water, 3: air, 4: land
		//first, generate the path, then add some water + air, fill in rest with 
		//land. water and air will be a completely different number each time
		//however they cannot take up so much space such that the land will look
		//sparse
		
		int[][] map = new int[height][width];
		//generate the path
		for(int i = 0; i<width; i++){
			map[height/2][i] = 1;
		}
		//randomly fill in some water + air
		int generations = (height*width)/2;
		while(generations > 0){
			int randX = randomInt(0, width-1);
			int randY = randomInt(0, height-1);
			if(map[randY][randX] == 0){
				generations--;
				map[randY][randX] = randomInt(2,3);
			}
		}
		//land
		for(int i = 0; i<height; i++){
			for(int j = 0; j<width; j++){
				if(map[i][j] == 0)
					map[i][j] = 4;
			}
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
