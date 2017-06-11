package objects.towers;

import javafx.scene.image.Image;
import objects.Tower;
import ui.Main;

public class IceTower extends Tower {

	public IceTower(double x, double y, double width, double height) {
		super("ice", Main.ice, x, y, width, height, 150);
	}

}
