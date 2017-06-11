package objects;

import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import ui.Main;

public class Tile extends Pane{
	private boolean canPlace;
	private boolean hasTower;
	public Tile(ImageView iv){
		super(iv);
		canPlace = false;
		hasTower = false;
		
		this.setOnMouseClicked(e -> placeTower(Main.getTower().getTowerIcon()));
	}
	public boolean hasTower() {
		return hasTower;
	}
	public void setHasTower(boolean hasTower) {
		this.hasTower = hasTower;
	}
	public ImageView getImageView(){
		ObservableList<Node> l = getChildren();
		for(Node n : l){
			if(n instanceof ImageView)
				return (ImageView)n;
		}
		return null;
	}
	public void setPlacement(boolean b){
		canPlace = b;
	}
	public void changeImage(Image i){
		getImageView().setImage(i);
	}
	public void placeTower(Tower t){
		if(canPlace && !hasTower){
			ObservableList<Node> nodes = Main.getAllTiles();
			for(Node n: nodes){
				Tile tile = (Tile)n;
				tile.setCanPlace(false);
				tile.setBackground(Background.EMPTY);
			}
			this.hasTower = true;
			t.getTowerIcon().setClicked(false);
			Main.placeTower(t, this);
		}
	}
	public void setCanPlace(boolean b){
		canPlace = b;
	}
}
