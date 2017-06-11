package objects.towers;

import javafx.scene.image.Image;
import objects.Tower;
import ui.Main;

public class LaserTower extends Tower {

	public LaserTower(double x, double y, double width, double height) {
		super(Main.laser, x, y, width, height, 100);
	}

}
