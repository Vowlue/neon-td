package objects;

import java.util.ArrayList;

import javafx.geometry.Insets;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;
import ui.Main;

public class TowerIcon extends ImageView{
	public final static String BLUE = "blue tile";
	public final static String GREEN = "green tile";
	public final static String YELLOW = "yellow tile";
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
	public boolean getClicked(){
		return clicked;
	}
	public void setClicked(boolean b){
		clicked = b;
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
		if(!Main.placingTower()){
			clicked = true;
			ArrayList<Tile> tiles = Main.getAllTiles(type);
			Main.storeTower(this);
			for(Tile t: tiles){
				if(!t.hasTower()){
					switch(type){
					case BLUE: 
						t.setBackground(new Background(new BackgroundFill(Color.rgb(64, 36, 123), new CornerRadii(4), Insets.EMPTY))); break;
					case YELLOW:
						t.setBackground(new Background(new BackgroundFill(Color.rgb(253, 219, 0), new CornerRadii(4), Insets.EMPTY))); break;
					case GREEN:
						t.setBackground(new Background(new BackgroundFill(Color.rgb(119, 219, 0), new CornerRadii(4), Insets.EMPTY))); break;
					default: 
						t.setBackground(Background.EMPTY); break;
					}
				t.setPlacement(true);
				}
			}
		}
	}
	
}
