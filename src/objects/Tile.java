package objects;

import components.Main;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;

public class Tile extends Pane{
	private boolean canPlace;
	public Tile(ImageView iv){
		super(iv);
		canPlace = false;
		this.setOnMouseClicked(e -> {
			placeTower(Main.getTower());
		});
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
		if(canPlace){
			Main.addNode(new Rectangle(Main.mapLayout.getLayoutX()+getLayoutX(), Main.mapLayout.getLayoutY()+getLayoutY(), getWidth(), getHeight()));
		}
	}
}
