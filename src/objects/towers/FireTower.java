package objects.towers;

import java.util.ArrayList;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import objects.AoeTower;
import objects.Enemy;
import ui.Main;

public class FireTower extends AoeTower{
	public FireTower(double x, double y, double width, double height) {
		super(50, 3, 100, "fire", Main.fire, x, y, width, height);
	}
	@Override
	public void fire(ArrayList<Enemy> enemies) {
		if(canFire()){
			for(Enemy e: enemies){
				e.takeDamage(getDamage());
			}
			setCanFire(false);
			new Timeline(new KeyFrame(getDelay(), ev -> setCanFire(true))).play();
		}
	}

}
