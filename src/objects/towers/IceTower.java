package objects.towers;

import java.util.ArrayList;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.effect.BoxBlur;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.util.Duration;
import objects.AoeTower;
import objects.Enemy;
import ui.Main;

public class IceTower extends AoeTower{

	public IceTower(double x, double y, double width, double height) {
		super(100, 1, 300, "ice", Main.ice, x, y, width, height);
	}
	//deal a little damage and apply a slowing debuff on enemies
	@Override
	public void fire(ArrayList<Enemy> enemies) {
		if(canFire()){
			Circle aoe = new Circle(getX()+getFitWidth()/2, getY()+getFitHeight()/2, getRange(), Color.rgb(102, 179, 255, 0.3));
			aoe.setStroke(Color.rgb(204, 230, 255));
			aoe.setEffect(new BoxBlur());
			Main.addNode(aoe);
			new Timeline(new KeyFrame(getDelay().divide(2), ev -> {
				Main.removeNode(aoe);
				for(Enemy e: enemies){
					e.takeDamage(getDamage());
					if(e.getTransition().getCurrentRate() == 1){
						e.getTransition().setRate(0.5);
						new Timeline(new KeyFrame(Duration.seconds(2), af -> e.getTransition().setRate(1))).play();
					}
				}
			})).play();
			setCanFire(false);
			new Timeline(new KeyFrame(getDelay(), ev -> setCanFire(true))).play();
		}
	}
	

}
