package objects.towers;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import objects.AttackingTower;
import objects.Enemy;
import ui.Main;

public class ElectricTower extends AttackingTower{
	private int bounces;
	private int bounceRange;
	public ElectricTower(double x, double y, double width, double height) {
		super(3, 100, "electric", Main.electric, x, y, width, height, 200);
		bounces = 3;
		bounceRange = 5;
	}
	@Override
	public void fire(Enemy enemy) {
		if(canFire()){
			chain(enemy, bounces);
			setCanFire(false);
			new Timeline(new KeyFrame(getDelay(), ev -> setCanFire(true))).play();
		}
	}
	private void chain(Enemy t, int b){
		t.takeDamage(getDamage());
		if(b > 0){
			for(Enemy e: Main.enemies){
				if(t.getCompletion() > e.getCompletion() && Main.getDistanceBetween(t, e) <= bounceRange){
					chain(e, b-1);
				}
			}
		}
	}
}
