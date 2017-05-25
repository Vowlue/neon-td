package objects;

import java.util.ArrayList;
import javafx.scene.Node;
import components.Main;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class BlueTower extends Tower{

	public BlueTower(Image i, double width, double height) {
		super(i, width, height, Tower.BLUE);
		this.setOnMouseClicked(e ->{
			ArrayList<Node> nodes = Main.getAllNodes(Main.mapLayout);
			for(Node n: nodes){
				if(n.getStyleClass().contains("blue tile")){
					
				}
			}
		});
	}

}
