package objects.towers;

import javafx.scene.image.Image;
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
	//put a delay on firing 
	@Override
	public void fire(Enemy e) {
		if(canFire){
			e.takeDamage(damage);
			canFire = false;
			new java.util.Timer().schedule( 
			        new java.util.TimerTask() {
			            @Override
			            public void run() {
			                canFire = true;
			            }
			        }, 
			        delay 
			);
		}
	}

}
