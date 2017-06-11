package objects.towers;

import javafx.animation.RotateTransition;
import javafx.scene.image.Image;
import javafx.util.Duration;
import objects.Attacker;
import objects.Enemy;
import objects.Tower;
import ui.Main;

public class ElectricTower extends Tower implements Attacker{
	private final static int RANGE = 100;
	private Duration fireDelay;
	//Tower(Image i, double x, double y, double width, double height, int range)
	public ElectricTower(double x, double y, double width, double height) {
		super("electric", Main.electric, x, y, width, height, RANGE);
		fireDelay = Duration.millis(500);
	}
	//tower will aim until it fires, time it aims is the delay on firing
	public void aim(Enemy e) {
		//constantly want to set angle to face enemy
		RotateTransition rt = new RotateTransition();
		rt.setDuration(fireDelay);
		//rt.setToAngle(); not sure if possible to use this?
	}
	@Override
	public void fire(Enemy e) {
		
	}

}
