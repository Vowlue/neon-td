package objects.towers;

import java.util.ArrayList;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import objects.AoeTower;
import objects.Enemy;
import ui.Main;

public class FireTower extends AoeTower{
	public FireTower(double x, double y, double width, double height) {
		super(50, 3, 100, "fire", Main.fire, x, y, width, height);
	}
	@Override
	public void fire(ArrayList<Enemy> enemies) {
		if(canFire()){
			Circle aoe = new Circle(getX()+getFitWidth()/2, getY()+getFitHeight()/2, getRange(), Color.rgb(0, 0, 153, 0.6));
			aoe.setStroke(Color.rgb(0, 0, 128, 0.8));
			Main.addNode(aoe);
			for(Enemy e: enemies){
				e.takeDamage(getDamage());
			}
			setCanFire(false);
			new Timeline(new KeyFrame(getDelay(), ev -> setCanFire(true))).play();
		}
	}

}
