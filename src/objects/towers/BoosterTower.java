package objects.towers;

import javafx.scene.image.Image;
import objects.Tower;
import ui.Main;

public class BoosterTower extends Tower {
	private final static int RANGE = 200;
	private double dmgMult;
	private double rangMult;
	private double delMult;
	private double mnyMult;
	public BoosterTower(double x, double y, double width, double height) {
		super("boost", Main.boost, x, y, width, height, RANGE);
		dmgMult = 1.05;
		rangMult = 1.05;
		delMult = 1.05;
		mnyMult = 1.05;
	}

}
