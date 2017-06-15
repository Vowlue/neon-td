package objects.towers;

import objects.Tower;
import ui.Main;

public class BoosterTower extends Tower {
	private final static int RANGE = 200;
	private double dmgMult;
	private double rangMult;
	private double delMult;
	public BoosterTower(double x, double y, double width, double height) {
		super("boost", Main.boost, x, y, width, height, RANGE);
		dmgMult = 1.05;
		rangMult = 1.05;
		delMult = 0.95;
	}
	public void boostTower(Tower t){
		t.setBoosted(true);
	}
	public double getDmgMult() {
		return dmgMult;
	}
	public void setDmgMult(double dmgMult) {
		this.dmgMult = dmgMult;
	}
	public double getRangMult() {
		return rangMult;
	}
	public void setRangMult(double rangMult) {
		this.rangMult = rangMult;
	}
	public double getDelMult() {
		return delMult;
	}
	public void setDelMult(double delMult) {
		this.delMult = delMult;
	}

}
