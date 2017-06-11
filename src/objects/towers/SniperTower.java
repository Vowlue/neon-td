package objects.towers;

import javafx.scene.image.Image;
import objects.Attacker;
import objects.Enemy;
import objects.Tower;
import ui.Main;

public class SniperTower extends Tower implements Attacker{

	public SniperTower(double x, double y, double width, double height) {
		super("sniper",Main.sniper, x, y, width, height, 500);
	}

	@Override
	public void fire(Enemy e) {
		// TODO Auto-generated method stub
		
	}

}
