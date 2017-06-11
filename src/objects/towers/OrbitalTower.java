package objects.towers;

import javafx.scene.image.Image;
import objects.Attacker;
import objects.Enemy;
import objects.Tower;
import ui.Main;

public class OrbitalTower extends Tower implements Attacker{

	public OrbitalTower(double x, double y, double width, double height) {
		super("orbital",Main.orbital, x, y, width, height, 50);
	}

	@Override
	public void fire(Enemy e) {
		// TODO Auto-generated method stub
		
	}

}
