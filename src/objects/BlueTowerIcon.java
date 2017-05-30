package objects;

import components.Main;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;

public class BlueTowerIcon extends TowerIcon{

	public BlueTowerIcon(Image i, double width, double height) {
		super(i, width, height, TowerIcon.BLUE);
		this.setOnMouseClicked(e ->{
			ObservableList<Node> nodes = Main.mapLayout.getChildren();
			for(Node n: nodes){
				if(n.getStyleClass().contains("blue tile")){
					Tile t = (Tile)n;
					t.setBackground(new Background(new BackgroundFill(Color.rgb(64, 36, 123), new CornerRadii(4), Insets.EMPTY)));
					t.setPlacement(true);
					Main.storeTower(this);
				}
			}
		});
	}

}