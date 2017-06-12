package objects.towers;

import objects.Tower;
import ui.Main;

public class ShieldTower extends Tower{

	public ShieldTower(double x, double y, double width, double height) {
		super("shield",Main.shield, x, y, width, height, 0);
	}
}
