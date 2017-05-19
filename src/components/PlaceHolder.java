package components;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class PlaceHolder extends ImageView{
	public PlaceHolder(Image i, double width, double height){
		super(i);
		setFitWidth(width);
		setFitHeight(height);
	}
}
