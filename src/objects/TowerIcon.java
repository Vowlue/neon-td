package objects;

import components.Main;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public abstract class TowerIcon extends ImageView{
	final static int BLUE = 0;
	final static int GREEN = 1;
	final static int YELLOW = 2;
	
	public TowerIcon(Image i, int type) {
		super(i);
		setFitWidth(Main.iconWidth);
		setFitHeight(Main.iconHeight);
		
	}
	

}
