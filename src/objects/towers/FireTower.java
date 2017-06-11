package objects.towers;

import javafx.scene.image.Image;
import objects.Tower;
import ui.Main;

public class FireTower extends Tower {

	public FireTower(double x, double y, double width, double height) {
		super("fire", Main.fire, x, y, width, height, 100);
	}

}
