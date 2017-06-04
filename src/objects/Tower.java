package objects;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import ui.Main;

public class Tower extends ImageView{
	private Circle indicator;
	private boolean showingIndicator;
	//tower needs to be able to fire... maybe make this abstract
	public Tower(Image i, double x, double y, double width, double height){
		super(i);
		setX(x);
		setY(y);
		setFitWidth(width);
		setFitHeight(height);
		showingIndicator = false;
		indicator = new Circle(x+width/2, y+height/2, 50, Color.rgb(0, 0, 153, 0.6));
		indicator.setStroke(Color.rgb(0, 0, 128, 0.8));
		this.setOnMouseClicked(e -> showOptions());
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
	private void rotate(){
		this.setRotate(this.getRotate()+1);
	}
	public void fire(){
		
	}
}
