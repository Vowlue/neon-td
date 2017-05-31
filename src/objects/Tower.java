package objects;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Tower extends ImageView{
	//tower needs to be able to fire... maybe make this abstract
	public Tower(Image i, double x, double y, double width, double height){
		super(i);
		setX(x);
		setY(y);
		setFitWidth(width);
		setFitHeight(height);
	}
}
