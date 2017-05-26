package objects;

import java.util.ArrayList;
import javafx.scene.Node;
import components.Main;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.collections.ObservableList;

public class BlueTower extends Tower{

	public BlueTower(Image i, double width, double height) {
		super(i, width, height, Tower.BLUE);
		this.setOnMouseClicked(e ->{
			ObservableList<Node> nodes = Main.mapLayout.getChildren();
			for(Node n: nodes){
				if(n.getStyleClass().contains("blue tile")){
					System.out.println(n);
				}
			}
		});
	}

}
