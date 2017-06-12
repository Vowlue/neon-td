package objects.towers;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.image.Image;
import objects.TargetedTower;
import objects.Enemy;
import objects.Tower;
import ui.Main;

public class LaserTower extends TargetedTower{

	public LaserTower(double x, double y, double width, double height) {
		super(150, 4, 50, "laser", Main.laser, x, y, width, height);
	}

	public void fire(Enemy enemy) {
		if(canFire()){
			enemy.takeDamage(getDamage());
			setCanFire(false);
			new Timeline(new KeyFrame(getDelay(), ev -> setCanFire(true))).play();
		}
	}

}
