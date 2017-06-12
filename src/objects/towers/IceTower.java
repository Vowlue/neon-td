package objects.towers;

import java.util.ArrayList;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import objects.AoeTower;
import objects.Enemy;
import ui.Main;

public class IceTower extends AoeTower{

	public IceTower(double x, double y, double width, double height) {
		super(150, 1, 300, "ice", Main.ice, x, y, width, height);
	}
	//deal a little damage and apply a slowing debuff on enemies
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
