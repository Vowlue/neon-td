package objects;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public abstract class TowerIcon extends ImageView{
	final static int BLUE = 0;
	final static int GREEN = 1;
	final static int YELLOW = 2;
	
	public TowerIcon(Image i, double width, double height, int type) {
		super(i);
		setFitWidth(width);
		setFitHeight(height);
		
		this.setOnMouseClicked(e -> System.out.print("s"));
	}
	

}
