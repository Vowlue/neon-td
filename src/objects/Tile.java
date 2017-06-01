package objects;

import components.Main;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.Pane;

public class Tile extends Pane{
	private boolean canPlace;
	private boolean hasTower;
	public Tile(ImageView iv){
		super(iv);
		canPlace = false;
		hasTower = false;
		this.setOnMouseClicked(e -> {
			placeTower(Main.getTower());
		});
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
	public void placeTower(TowerIcon t){
		if(canPlace && !hasTower){
			ObservableList<Node> nodes = Main.getAllTiles();
			for(Node n: nodes){
				Tile tile = (Tile)n;
				tile.setCanPlace(false);
				tile.setBackground(Background.EMPTY);
			}
			this.hasTower = true;
			Main.getTower().setClicked(false);
			Main.addNode(new Tower(t.getImage(), Main.mapLayout.getLayoutX()+getLayoutX(), Main.mapLayout.getLayoutY()+getLayoutY(), getWidth(), getHeight()));
		}
	}
	public void setCanPlace(boolean b){
		canPlace = b;
	}
}
