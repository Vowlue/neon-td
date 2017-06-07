package objects;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import ui.Main;

public abstract class Tower extends ImageView{
	private Circle indicator;
	private int range;
	private boolean showingIndicator;
	//tower needs to be able to fire... maybe make this abstract
	public Tower(Image i, double x, double y, double width, double height, int range){
		super(i);
		setX(x);
		setY(y);
		setFitWidth(width);
		setFitHeight(height);
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
	private void rotate(){
		this.setRotate(this.getRotate()+1);
	}
	//needs method to get the icon based on the tower
	public TowerIcon getTowerIcon(){
		return null;
	}
}
