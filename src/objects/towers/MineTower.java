package objects.towers;

import javafx.scene.image.Image;
import objects.Attacker;
import objects.Enemy;
import objects.Tower;
import ui.Main;

public class MineTower extends Tower implements Attacker{

	public MineTower(double x, double y, double width, double height) {
		super("mine", Main.mine, x, y, width, height, 100);
	}

	@Override
	public void fire(Enemy e) {
		// TODO Auto-generated method stub
		
	}

}
