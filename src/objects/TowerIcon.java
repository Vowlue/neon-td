package objects;

import java.util.ArrayList;

import components.Main;
import javafx.geometry.Insets;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;

public abstract class TowerIcon extends ImageView{
	final static String BLUE = "blue tile";
	final static String GREEN = "green tile";
	final static String YELLOW = "yellow tile";
	
	boolean clicked;
	
	private String type;
	
	public TowerIcon(Image i, String type) {
		super(i);
		this.type = type; 
		setFitWidth(Main.iconWidth);
		setFitHeight(Main.iconHeight);
		clicked = false;
		this.setOnMouseClicked(e ->{
			if(!clicked)
				selectTower();
			else
				undoSelection();
			}
		);
	}
	private void undoSelection() {
		clicked = false;
		ArrayList<Tile> tiles = Main.getAllTiles(type);
		for(Tile t: tiles){
				t.setBackground(Background.EMPTY);
				t.setPlacement(false);
		}
	}
	private void selectTower(){
		clicked = true;
		ArrayList<Tile> tiles = Main.getAllTiles(type);
		for(Tile t: tiles){
				t.setBackground(new Background(new BackgroundFill(Color.rgb(64, 36, 123), new CornerRadii(4), Insets.EMPTY)));
				t.setPlacement(true);
				Main.storeTower(this);
		}
	}
	

}
