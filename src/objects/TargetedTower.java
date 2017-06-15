package objects;

import javafx.scene.image.Image;
import javafx.util.Duration;
import ui.Main;

public abstract class TargetedTower extends Tower{
	private double damage;
	private boolean canFire;
	private Duration delay; //in ms
	public TargetedTower(int range, int dmg, double dly, String idCode, Image i, double x, double y, double width, double height) {
		super(idCode, i, x, y, width, height, range);
		damage = dmg;
		canFire = true;
		delay = Duration.millis(dly);
	}
	public double getDamage() {
		if(isBoosted())
			return damage*Main.dmgMult;
		return damage;
	}
	public void setDamage(double d) {
		damage = d;
	}
	public boolean canFire() {
		return canFire;
	}
	public void setCanFire(boolean canFire) {
		this.canFire = canFire;
	}
	public Duration getDelay() {
		return delay;
	}
	public void setDelay(Duration delay) {
		this.delay = delay;
	}
	public abstract void fire(Enemy enemy);

}
