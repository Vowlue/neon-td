package objects;

import javafx.animation.RotateTransition;
import javafx.scene.image.Image;
import javafx.util.Duration;

public class ElectricTower extends Tower implements Aimer{
	private final static int RANGE = 100;
	private Duration fireDelay;
	//Tower(Image i, double x, double y, double width, double height, int range)
	public ElectricTower(Image i, double x, double y, double width, double height) {
		super(i, x, y, width, height, RANGE);
		fireDelay = Duration.millis(500);
	}
	//tower will aim until it fires, time it aims is the delay on firing
	@Override
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
