package objects.towers;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;
import objects.AoeTower;
import objects.Tower;
import ui.Main;

public class BoosterTower extends Tower {
	private final static int RANGE = 200;
	private double dmgMult;
	private double rangMult;
	private double delMult;
	private double mnyMult;
	public BoosterTower(double x, double y, double width, double height) {
		super("boost", Main.boost, x, y, width, height, RANGE);
		dmgMult = 1.05;
		rangMult = 1.05;
		delMult = 0.95;
		mnyMult = 1.05;
	}
	public void boostTower(Tower t){
		if(t instanceof AoeTower){
			AoeTower tower = (AoeTower)t;
			double oDmg = tower.getDamage();
			tower.setDamage(oDmg * dmgMult);
			double oRange = tower.getRange();
			tower.setRange(oRange * rangMult);
			Duration oDelay = tower.getDelay();
			tower.setDelay(oDelay.multiply(delMult));
			new Timeline(new KeyFrame(Duration.millis(30), e -> {
				
			})).play();
		}
	}

}
