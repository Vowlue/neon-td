package objects.towers;

import javafx.scene.image.Image;
import objects.AttackingTower;
import objects.Enemy;
import objects.Tower;
import ui.Main;

public class FireTower extends AttackingTower{

	public FireTower(double x, double y, double width, double height) {
		super(1, 1, "fire", Main.fire, x, y, width, height, 100);
	}

	@Override
	public void fire(Enemy e) {
		// TODO Auto-generated method stub
		
	}

}
