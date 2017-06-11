package objects.towers;

import javafx.scene.image.Image;
import objects.Enemy;
import objects.Tower;
import ui.Main;

public class OrbitalTower extends Tower{

	public OrbitalTower(double x, double y, double width, double height) {
		super("orbital",Main.orbital, x, y, width, height, 50);
	}

}
