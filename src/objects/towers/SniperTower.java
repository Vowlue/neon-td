package objects.towers;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;
import objects.Attacker;
import objects.Enemy;
import objects.Tower;
import ui.Main;

public class SniperTower extends Tower implements Attacker{
	private int damage;
	private boolean canFire;
	private int delay; //in ms
	public SniperTower(double x, double y, double width, double height) {
		super("sniper",Main.sniper, x, y, width, height, 500);
		damage = 10;
		canFire = true;
		delay = 500;
	}
	public void setDamage(int dmg){
		damage = dmg;
	}
	//maybe add an animation 
	@Override
	public void fire(Enemy e) {
		if(canFire){
			e.takeDamage(damage);
			canFire = false;
			Timeline timeline = new Timeline(new KeyFrame(
			        Duration.millis(delay),
			        ev -> canFire = true));
			timeline.play();
		}
	}

}
