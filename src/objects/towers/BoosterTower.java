package objects.towers;

import objects.Tower;
import ui.Main;

public class BoosterTower extends Tower {
	private final static int RANGE = 200;
	public BoosterTower(double x, double y, double width, double height) {
		super("boost", Main.boost, x, y, width, height, RANGE);
	}
	public void boostTower(Tower t){
		t.setBoosted(true);
	}
}
