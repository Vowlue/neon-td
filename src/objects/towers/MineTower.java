package objects.towers;

import javafx.scene.image.Image;
import objects.TargetedTower;
import objects.Enemy;
import objects.Tower;
import ui.Main;

public class MineTower extends Tower{

	public MineTower(double x, double y, double width, double height) {
		super("mine", Main.mine, x, y, width, height, 100);
	}


}
