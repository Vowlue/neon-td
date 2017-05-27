package objects;

import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

public class Tile extends Pane{
	public Tile(ImageView iv){
		super(iv);
	}
	public ImageView getImageView(){
		ObservableList<Node> l = getChildren();
		for(Node n : l){
			if(n instanceof ImageView)
				return (ImageView)n;
		}
		return null;
	}
}
