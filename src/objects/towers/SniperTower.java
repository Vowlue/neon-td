package objects.towers;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import objects.TargetedTower;
import objects.Enemy;
import ui.Main;

public class SniperTower extends TargetedTower{
	public SniperTower(double x, double y, double width, double height) {
		super(500, 10, 500, "sniper", Main.sniper, x, y, width, height);
	}
	//maybe add an animation 
	public void fire(Enemy enemy) {
		if(canFire()){
			enemy.takeDamage(getDamage());
			setCanFire(false);
			new Timeline(new KeyFrame(getDelay(), ev -> setCanFire(true))).play();
		}
	}

}
