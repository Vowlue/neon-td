package objects.towers;

import javafx.scene.image.Image;
import objects.Attacker;
import objects.Enemy;
import objects.Tower;
import ui.Main;

public class FireTower extends Tower implements Attacker{

	public FireTower(double x, double y, double width, double height) {
		super("fire", Main.fire, x, y, width, height, 100);
	}

	@Override
	public void fire(Enemy e) {
		// TODO Auto-generated method stub
		
	}

}
