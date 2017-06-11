package objects.towers;

import javafx.scene.image.Image;
import objects.Tower;
import ui.Main;

public class SniperTower extends Tower{

	public SniperTower(double x, double y, double width, double height) {
		super(Main.sniper, x, y, width, height, 1000);
	}

}
