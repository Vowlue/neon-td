package objects;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import objects.towers.BoosterTower;
import objects.towers.ElectricTower;
import objects.towers.FireTower;
import objects.towers.IceTower;
import objects.towers.LaserTower;
import objects.towers.MineTower;
import objects.towers.OrbitalTower;
import objects.towers.ShieldTower;
import objects.towers.SniperTower;
import ui.Main;

public abstract class Tower extends ImageView{
	private Circle indicator;
	private int range;
	private boolean showingIndicator;
	private String idCode;
	public Tower(String idCode, Image i, double x, double y, double width, double height, int range){
		super(i);
		setX(x);
		setY(y);
		setFitWidth(width);
		setFitHeight(height);
		this.idCode = idCode;
		this.range = range;
		showingIndicator = false;
		indicator = new Circle(x+width/2, y+height/2, range, Color.rgb(0, 0, 153, 0.6));
		indicator.setStroke(Color.rgb(0, 0, 128, 0.8));
		this.setOnMouseClicked(e -> showOptions());
	}
	public boolean isShowing(){
		return showingIndicator;
	}
	private void showOptions(){
		if(!showingIndicator){
			showingIndicator = true;
			Main.addNode(indicator);
			toFront();
		}
	}
	public void unshowOptions(){
		if(showingIndicator){
			showingIndicator = false;
			Main.removeNode(indicator);
		}
	}
	public int getRange(){
		return range;
	}
	public boolean inRange(Enemy e){
		return Main.getDistanceBetween(this, e) <= range;
	}
	private void rotate(){
		this.setRotate(this.getRotate()+1);
	}
	//gets a towericon based on idcode
	public TowerIcon getTowerIcon(){
		switch(idCode){
		case "boost": 
			return Main.t1;
		case "electric": 
			return Main.t2;
		case "fire": 
			return Main.t3;		
		case "ice": 
			return Main.t4;
		case "laser": 
			return Main.t5;
		case "mine": 
			return Main.t6;
		case "orbital": 
			return Main.t7;
		case "shield": 
			return Main.t8;
		case "sniper": 
			return Main.t9;
		default: return null;
		}
	}
}
