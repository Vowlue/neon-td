package objects;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Tower extends ImageView{
	public Tower(Image i, double width, double height) {
		super(i);
		
		setFitWidth(width);
		setFitHeight(height);
		
		this.setOnMouseClicked(e -> System.out.print("s"));
	}
	

}
