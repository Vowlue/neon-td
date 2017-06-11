package objects.towers;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import objects.AttackingTower;
import objects.Enemy;
import ui.Main;

public class SniperTower extends AttackingTower{
	public SniperTower(double x, double y, double width, double height) {
		super(10, 500, "sniper", Main.sniper, x, y, width, height, 500);
	}
	//maybe add an animation 
	public void fire(Enemy enemy) {
		if(canFire()){
			enemy.takeDamage(getDamage());
			setCanFire(false);
			Timeline timeline = new Timeline(new KeyFrame(getDelay(), ev -> setCanFire(true)));
			timeline.play();
		}
	}

}
