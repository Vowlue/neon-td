package objects;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import ui.Main;
import ui.OptionsMenu;

public abstract class Tower extends ImageView{
	private Circle indicator;
	private double range;
	private boolean showingIndicator;
	private String idCode;
	private boolean boosted;
	private OptionsMenu options;
	public Tower(String idCode, Image i, double x, double y, double width, double height, int range){
		super(i);
		setX(x);
		setY(y);
		setFitWidth(width);
		setFitHeight(height);
		this.idCode = idCode;
		this.range = range;
		options = new OptionsMenu(this);
		boosted = false;
		showingIndicator = false;
		indicator = new Circle(x+width/2, y+height/2, range, Color.rgb(0, 0, 153, 0.3));
		indicator.setStroke(Color.rgb(0, 0, 128, 0.4));
		this.setOnMouseClicked(e -> showOptions());
	}
	public boolean isShowing(){
		return showingIndicator;
	}
	public boolean isBoosted() {
		return boosted;
	}
	public void setBoosted(boolean boosted) {
		this.boosted = boosted;
	}
	private void showOptions(){
		if(!showingIndicator){
			showingIndicator = true;
			Main.addNode(indicator);
			toFront();
			Main.upgradeMenu.getChildren().add(options);
		}
	}
	public void unshowOptions(){
		if(showingIndicator){
			showingIndicator = false;
			Main.removeNode(indicator);
			Main.upgradeMenu.getChildren().remove(options);
		}
	}
	public double getRange(){
		return range;
	}
	public void setRange(double d){
		range = d;
	}
	public boolean inRange(Enemy e){
		return Main.getDistanceBetween(this, e) <= range;
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
