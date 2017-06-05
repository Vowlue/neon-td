package objects;

import java.util.ArrayList;

import javafx.scene.image.Image;
import ui.Main;

public class AOETower extends Tower {

	public AOETower(Image i, double x, double y, double width, double height, int range) {
		super(i, x, y, width, height, range);
	}
	public void fire(){
		ArrayList<Enemy> enemies = Main.enemies;
		for(Enemy e: enemies){
		}
	}

}
