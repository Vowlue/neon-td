package objects.towers;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.image.ImageView;
import javafx.util.Duration;
import objects.TargetedTower;
import objects.Enemy;
import ui.Main;

public class SniperTower extends TargetedTower{
	public SniperTower(double x, double y, double width, double height) {
		super(500, 100, 2000, "sniper", Main.sniper, x, y, width, height);
	}
	public void fire(Enemy enemy) {
		Duration halfDelay = getDelay().divide(2);
		if(canFire()){
			ImageView crosshair = new ImageView(Main.crosshair);
			crosshair.setFitWidth(enemy.getRadius()*2);
			crosshair.setFitHeight(enemy.getRadius()*2);
			crosshair.setLayoutX(enemy.getLayoutX()+enemy.getCenterX()-enemy.getRadius());
			crosshair.setLayoutY(enemy.getLayoutY()+enemy.getCenterY()-enemy.getRadius());
			crosshair.xProperty().bind(enemy.translateXProperty());
			crosshair.yProperty().bind(enemy.translateYProperty());
			Main.addNode(crosshair);
			new Timeline(new KeyFrame(halfDelay, ev -> {
				enemy.takeDamage(getDamage());
				Main.removeNode(crosshair);
			})).play();
			setCanFire(false);
			new Timeline(new KeyFrame(getDelay(), ev -> setCanFire(true))).play();
		}
	}

}
