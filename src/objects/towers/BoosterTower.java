package objects.towers;

import javafx.scene.image.Image;
import objects.Tower;
import ui.Main;

public class BoosterTower extends Tower {
	private final static int RANGE = 200;
	public BoosterTower(double x, double y, double width, double height) {
		super(Main.boost, x, y, width, height, RANGE);
	}

}
