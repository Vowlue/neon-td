package objects;

import java.util.ArrayList;
import components.Main;
import javafx.geometry.Insets;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;

public class BlueTowerIcon extends TowerIcon{

	public BlueTowerIcon(Image i) {
		super(i, TowerIcon.BLUE);
		this.setOnMouseClicked(e ->{
			ArrayList<Tile> tiles = Main.getAllTiles("blue tile");
			for(Tile t: tiles){
					t.setBackground(new Background(new BackgroundFill(Color.rgb(64, 36, 123), new CornerRadii(4), Insets.EMPTY)));
					t.setPlacement(true);
					Main.storeTower(this);
				}
			}
		);
	}

}