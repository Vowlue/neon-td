package objects.towers;

import javafx.scene.image.Image;
import objects.AttackingTower;
import objects.Enemy;
import objects.Tower;
import ui.Main;

public class IceTower extends AttackingTower{

	public IceTower(double x, double y, double width, double height) {
		super(1, 1, "ice", Main.ice, x, y, width, height, 150);
	}

	@Override
	public void fire(Enemy e) {
		// TODO Auto-generated method stub
		
	}

}
